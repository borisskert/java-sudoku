package de.borisskert.sudoku.core;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Represents a sub-grid within a sudoku puzzle.
 */
class SubGrid implements Comparable<SubGrid> {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final SubGridCoordinates coordinates;
    private final Size size;
    private final Fields fields;

    /* *****************************************************************************************************************
     * Constructor(s)
     **************************************************************************************************************** */

    private SubGrid(SubGridCoordinates coordinates, Size size, Fields fields) {
        this.coordinates = coordinates;
        this.size = size;
        this.fields = fields;
    }

    /* *****************************************************************************************************************
     * Wither methods
     **************************************************************************************************************** */

    public SubGrid withValueAt(WithinSubGridCoordinates coordinates, FieldValue fieldValue) {
        Fields updatedFields = fields.withValueAt(coordinates, fieldValue);
        return new SubGrid(this.coordinates, size, updatedFields);
    }

    /* *****************************************************************************************************************
     * Transfer methods
     **************************************************************************************************************** */

    public Fields resolvedFields() {
        return fields.resolve();
    }

    /* *****************************************************************************************************************
     * Accessor methods
     **************************************************************************************************************** */

    public SubGridCoordinates getCoordinates() {
        return coordinates;
    }

    public Field getField(WithinSubGridCoordinates coordinates) {
        return fields.get(coordinates);
    }

    public Stream<Field> stream() {
        return fields.stream();
    }

    public Fields fields() {
        return this.fields;
    }

    /* *****************************************************************************************************************
     * Indicator methods
     **************************************************************************************************************** */

    public boolean isSolved() {
        return fields.areSolved();
    }

    public boolean has(SubGridCoordinates other) {
        return this.coordinates.equals(other);
    }

    /* *****************************************************************************************************************
     * Implementation of Comparable interface
     **************************************************************************************************************** */

    @Override
    public int compareTo(SubGrid o) {
        return Comparator.<SubGrid, SubGridCoordinates>comparing(subGrid -> subGrid.coordinates)
                .compare(this, o);
    }

    /* *****************************************************************************************************************
     * Overrides of Object
     **************************************************************************************************************** */

    @Override
    public String toString() {
        return "ImmutableSubGrid{" +
                "coordinates=" + coordinates +
                ", size=" + size +
                ", fields=" + fields +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubGrid that = (SubGrid) o;
        return coordinates.equals(that.coordinates) &&
                size.equals(that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, size);
    }

    /* *****************************************************************************************************************
     * Factory method(s)
     **************************************************************************************************************** */

    public static SubGrid create(SubGridCoordinates coordinates, Set<Field> fields, Size size) {
        return new SubGrid(coordinates, size, Fields.of(fields));
    }
}
