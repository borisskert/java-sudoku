package de.borisskert.sudoku.core;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a set of columns containing {@link Column}s.
 * Every {@link Columns} instance is immutable.
 */
final class Columns {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final Set<Column> columns;

    /* *****************************************************************************************************************
     * Constructor(s)
     **************************************************************************************************************** */

    private Columns(Set<Column> columns) {
        this.columns = columns;
    }

    /* *****************************************************************************************************************
     * Wither methods
     **************************************************************************************************************** */

    public Fields withValueAt(AbsoluteCoordinates coordinates, FieldValue fieldValue) {
        Set<Field> changedFields = columns.stream()
                .map(column -> {
                    if (column.has(coordinates)) {
                        return column.withValueAt(coordinates, fieldValue);
                    } else {
                        return column;
                    }
                })
                .flatMap(Column::stream)
                .collect(Collectors.toUnmodifiableSet());

        return Fields.of(changedFields);
    }

    public Fields resolve() {
        Set<Field> fields = columns.stream()
                .map(Column::resolved)
                .flatMap(Fields::stream)
                .collect(Collectors.toUnmodifiableSet());

        return Fields.of(fields);
    }

    /* *****************************************************************************************************************
     * Accessor methods
     **************************************************************************************************************** */

    public Set<Fields> fields() {
        return this.columns.stream()
                .map(Column::fields)
                .collect(Collectors.toUnmodifiableSet());
    }

    /* *****************************************************************************************************************
     * Factory methods
     **************************************************************************************************************** */

    public static Columns of(Fields fields) {
        Set<Column> columns = fields.stream()
                .map(Field::absoluteCoordinates)
                .map(AbsoluteCoordinates::xOnly)
                .map(fields::filterColumnOnly)
                .map(Column::of)
                .collect(Collectors.toUnmodifiableSet());

        return new Columns(columns);
    }
}
