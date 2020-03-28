package de.borisskert.sudoku.core;

import java.util.stream.Stream;

/**
 * Represents a line containing {@link Field}s.
 * Every {@link Line} instance is immutable.
 */
class Line {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final Fields fields;

    /* *****************************************************************************************************************
     * Constructor(s)
     **************************************************************************************************************** */

    private Line(Fields fields) {
        this.fields = fields;
    }

    /* *****************************************************************************************************************
     * Indicator methods
     **************************************************************************************************************** */

    public boolean has(AbsoluteCoordinates other) {
        return fields.stream().anyMatch(field -> field.hasSameY(other));
    }

    /* *****************************************************************************************************************
     * Wither methods
     **************************************************************************************************************** */

    public Fields resolve() {
        return fields.resolve();
    }

    public Line withValueAt(AbsoluteCoordinates coordinates, FieldValue value) {
        Fields changedFields = this.fields.withValueAt(coordinates, value);
        return new Line(changedFields);
    }

    public Line withoutValueAt(AbsoluteCoordinates coordinates) {
        Fields changedFields = this.fields.withoutValueAt(coordinates);
        return new Line(changedFields);
    }

    /* *****************************************************************************************************************
     * Accessor methods
     **************************************************************************************************************** */

    public Stream<Field> stream() {
        return fields.stream();
    }

    public Fields fields() {
        return fields;
    }

    /* *****************************************************************************************************************
     * Factory methods
     **************************************************************************************************************** */

    public static Line of(Fields fields) {
        return new Line(fields);
    }
}
