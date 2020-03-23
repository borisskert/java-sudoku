package de.borisskert.sudoku.core;

import java.util.stream.Stream;

class Line {

    private final Fields fields;

    private Line(Fields fields) {
        this.fields = fields;
    }

    public static Line of(Fields fields) {
        return new Line(fields);
    }

    public Fields resolve() {
        return fields.resolve();
    }

    public Line withValueAt(AbsoluteCoordinates coordinates, FieldValue value) {
        Fields changedFields = this.fields.withValueAt(coordinates, value);
        return new Line(changedFields);
    }

    public Stream<Field> stream() {
        return fields.stream();
    }

    public boolean has(AbsoluteCoordinates other) {
        return fields.stream().anyMatch(field -> field.hasSameY(other));
    }

    public Fields fields() {
        return fields;
    }
}
