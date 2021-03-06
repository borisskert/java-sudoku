package de.borisskert.sudoku.core;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represent a set of {@link SubGrid}
 * Every {@link SubGrids} instance is immutable.
 */
final class SubGrids {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final Size size;
    private final Set<SubGrid> subGrids;

    /* *****************************************************************************************************************
     * Constructor(s)
     **************************************************************************************************************** */

    private SubGrids(Size size, Set<SubGrid> subGrids) {
        this.size = size;
        this.subGrids = subGrids;
    }

    /* *****************************************************************************************************************
     * Wither methods
     **************************************************************************************************************** */

    public Fields withValueAt(AbsoluteCoordinates coordinates, FieldValue value) {
        SubGridCoordinates subGridCoordinates = coordinates.subGrid(size);
        WithinSubGridCoordinates withInSubGrid = coordinates.withinSubGrid(size);

        Set<Field> fields = subGrids.stream()
                .map(subGrid -> {
                    if (subGrid.has(subGridCoordinates)) {
                        return subGrid.withValueAt(withInSubGrid, value);
                    } else {
                        return subGrid;
                    }
                })
                .flatMap(SubGrid::stream)
                .collect(Collectors.toUnmodifiableSet());

        return Fields.of(fields);
    }

    /* *****************************************************************************************************************
     * Transfer methods
     **************************************************************************************************************** */

    public Fields resolved() {
        Set<Field> resolvedFields = this.subGrids.stream()
                .map(SubGrid::resolvedFields)
                .flatMap(Fields::stream)
                .collect(Collectors.toUnmodifiableSet());

        return Fields.of(resolvedFields);
    }

    /* *****************************************************************************************************************
     * Accessor methods
     **************************************************************************************************************** */

    public int getWidth() {
        return size.getWidth();
    }

    public int getHeight() {
        return size.getHeight();
    }

    public SubGrid getSubGrid(SubGridCoordinates coordinates) {
        return subGrids.stream()
                .filter(subGrid -> subGrid.getCoordinates().equals(coordinates))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cannot find sub-grid with coordinates: " + coordinates.toString()));
    }

    public Set<Fields> fields() {
        return subGrids.stream()
                .map(SubGrid::fields)
                .collect(Collectors.toUnmodifiableSet());
    }

    public Stream<SubGrid> stream() {
        return subGrids.stream();
    }

    /* *****************************************************************************************************************
     * Factory method(s)
     **************************************************************************************************************** */

    public static SubGrids create(Size size, Fields fields) {
        Set<SubGrid> subGrids = size.toSubGridCoordinates().stream()
                .map(coordinates -> Tuple.create(coordinates).with(fields.filterSubGridOnly(coordinates)))
                .map(t -> SubGrid.create(t.getA(), t.getB(), size))
                .collect(Collectors.toUnmodifiableSet());

        return new SubGrids(size, subGrids);
    }
}
