package com.github.borisskert.sudoku;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SubGrids {

    private final Size size;
    private final Set<SubGrid> subGrids;

    private SubGrids(Size size, Set<SubGrid> subGrids) {
        this.size = size;
        this.subGrids = subGrids;
    }

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

    public static SubGrids create(Size size, Fields fields) {
        Set<SubGrid> subGrids = size.toSubGridCoordinates().stream()
                .map(coordinates -> Tuple.create(coordinates).with(fields.filterSubGridOnly(coordinates)))
                .map(t -> SubGrid.create(t.getA(), t.getB(), size))
                .collect(Collectors.toUnmodifiableSet());

        return new SubGrids(size, subGrids);
    }

    public Fields resolved() {
        Set<Field> resolvedFields = this.subGrids.stream()
                .map(SubGrid::resolvedFields)
                .flatMap(Fields::stream)
                .collect(Collectors.toUnmodifiableSet());

        return Fields.of(resolvedFields);
    }

    public int getWidth() {
        return size.getWidth();
    }

    public int getHeight() {
        return size.getHeight();
    }

    public SubGrid getSubGrid(SubGridCoordinates coordinates) {
        Optional<SubGrid> maybeFoundSubGrid = subGrids.stream()
                .filter(subGrid -> subGrid.getCoordinates().equals(coordinates))
                .findFirst();

        if (maybeFoundSubGrid.isEmpty()) {
            throw new RuntimeException("Cannot find subgrid with coordinates: " + coordinates.toString());
        }

        return maybeFoundSubGrid
                .get();
    }

    public Size getSize() {
        return size;
    }

    public Set<Fields> fields() {
        return subGrids.stream()
                .map(SubGrid::fields)
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubGrids that = (SubGrids) o;
        return Objects.equals(size, that.size) &&
                Objects.equals(subGrids, that.subGrids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, subGrids);
    }

    public Stream<SubGrid> stream() {
        return subGrids.stream();
    }
}
