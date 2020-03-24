package de.borisskert.sudoku.core;

import java.util.stream.Stream;

class Column {

    private final Fields fields;

    private Column(Fields fields) {
        this.fields = fields;
    }

    public static Column of(Fields fields) {
        return new Column(fields);
    }

    public Fields resolved() {
        return fields.resolve();
    }

    public Column withValueAt(AbsoluteCoordinates coordinates, FieldValue value) {
        Fields changedFields = this.fields.withValueAt(coordinates, value);
        return new Column(changedFields);
    }

    public Column withoutValueAt(AbsoluteCoordinates coordinates) {
        Fields changedFields = this.fields.withoutValueAt(coordinates);
        return new Column(changedFields);
    }

    public Stream<Field> stream() {
        return fields.stream();
    }

    public boolean has(AbsoluteCoordinates other) {
        return fields.stream().anyMatch(field -> field.hasSameX(other));
    }

    public Fields fields() {
        return fields;
    }
}
