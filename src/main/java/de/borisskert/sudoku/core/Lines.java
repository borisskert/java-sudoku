package de.borisskert.sudoku.core;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a set of lines containing {@link Line}s.
 * Every {@link Lines} instance is immutable.
 */
final class Lines {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final Set<Line> lines;

    /* *****************************************************************************************************************
     * Constructors
     **************************************************************************************************************** */

    private Lines(Set<Line> lines) {
        this.lines = lines;
    }

    /* *****************************************************************************************************************
     * Wither methods
     **************************************************************************************************************** */

    public Fields resolve() {
        Set<Field> fields = lines.stream()
                .map(Line::resolve)
                .flatMap(Fields::stream)
                .collect(Collectors.toUnmodifiableSet());

        return Fields.of(fields);
    }

    public Fields withValueAt(AbsoluteCoordinates coordinates, FieldValue fieldValue) {
        Set<Field> changedFields = lines.stream()
                .map(line -> {
                    if (line.has(coordinates)) {
                        return line.withValueAt(coordinates, fieldValue);
                    } else {
                        return line;
                    }
                })
                .flatMap(Line::stream)
                .collect(Collectors.toUnmodifiableSet());

        return Fields.of(changedFields);
    }

    /* *****************************************************************************************************************
     * Accessor methods
     **************************************************************************************************************** */

    public Set<Fields> fields() {
        return this.lines.stream()
                .map(Line::fields)
                .collect(Collectors.toUnmodifiableSet());
    }

    /* *****************************************************************************************************************
     * Factory methods
     **************************************************************************************************************** */

    public static Lines of(Fields fields) {
        Set<Line> lines = fields.stream()
                .map(Field::absoluteCoordinates)
                .map(AbsoluteCoordinates::yOnly)
                .map(fields::filterLineOnly)
                .map(Line::of)
                .collect(Collectors.toUnmodifiableSet());

        return new Lines(lines);
    }
}
