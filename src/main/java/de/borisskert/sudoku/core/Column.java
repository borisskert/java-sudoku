package de.borisskert.sudoku.core;

import java.util.stream.Stream;

/**
 * Represents a column containing {@link Field}s.
 * Every {@link Column} instance is immutable.
 */
class Column {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final Fields fields;

    /* *****************************************************************************************************************
     * Constructor(s)
     **************************************************************************************************************** */

    private Column(Fields fields) {
        this.fields = fields;
    }

    /* *****************************************************************************************************************
     * Indicator methods
     **************************************************************************************************************** */

    public Fields resolved() {
        return fields.resolve();
    }

    public boolean has(AbsoluteCoordinates other) {
        return fields.stream().anyMatch(field -> field.hasSameX(other));
    }

    /* *****************************************************************************************************************
     * Wither methods
     **************************************************************************************************************** */

    public Column withValueAt(AbsoluteCoordinates coordinates, FieldValue value) {
        Fields changedFields = this.fields.withValueAt(coordinates, value);
        return new Column(changedFields);
    }

    public Column withoutValueAt(AbsoluteCoordinates coordinates) {
        Fields changedFields = this.fields.withoutValueAt(coordinates);
        return new Column(changedFields);
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

    public static Column of(Fields fields) {
        return new Column(fields);
    }
}
