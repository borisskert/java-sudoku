package de.borisskert.sudoku.core;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

class SubGrid {

    private final SubGridCoordinates coordinates;
    private final Size size;
    private final Fields fields;

    private SubGrid(SubGridCoordinates coordinates, Size size, Fields fields) {
        this.coordinates = coordinates;
        this.size = size;
        this.fields = fields;
    }

    public static SubGrid create(SubGridCoordinates coordinates, Set<Field> fields, Size size) {
        return new SubGrid(coordinates, size, Fields.of(fields));
    }

    public SubGrid withValueAt(WithinSubGridCoordinates coordinates, FieldValue fieldValue) {
        Fields updatedFields = fields.withValueAt(coordinates, fieldValue);
        return new SubGrid(this.coordinates, size, updatedFields);
    }

    public SubGrid withoutValueAt(WithinSubGridCoordinates coordinates) {
        Fields updatedFields = fields.withoutValueAt(coordinates);
        return new SubGrid(this.coordinates, size, updatedFields);
    }

    public SubGridCoordinates getCoordinates() {
        return coordinates;
    }

    public boolean isSolved() {
        return fields.areSolved();
    }

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

    public Field getField(WithinSubGridCoordinates coordinates) {
        return fields.get(coordinates);
    }

    public Fields resolvedFields() {
        return fields.resolve();
    }

    public boolean has(SubGridCoordinates other) {
        return this.coordinates.equals(other);
    }

    public Stream<Field> stream() {
        return fields.stream();
    }

    public Fields fields() {
        return this.fields;
    }
}
