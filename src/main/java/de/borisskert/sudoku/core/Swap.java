package de.borisskert.sudoku.core;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

class Swap {

    private final Fields fields;

    private Swap(Fields fields) {
        this.fields = fields;
    }

    public Fields swapLines(int lineIndex, int otherLineIndex) {
        AbsoluteCoordinates line = AbsoluteCoordinates.fromLineOnly(lineIndex);
        AbsoluteCoordinates otherLine = AbsoluteCoordinates.fromLineOnly(otherLineIndex);

        Set<Field> swapped = fields.stream()
                .map(swapLine(line, otherLine))
                .collect(Collectors.toUnmodifiableSet());

        return Fields.of(swapped);
    }

    public Fields swapColumns(int columnIndex, int otherColumnIndex) {
        AbsoluteCoordinates column = AbsoluteCoordinates.fromColumnOnly(columnIndex);
        AbsoluteCoordinates otherColumn = AbsoluteCoordinates.fromColumnOnly(otherColumnIndex);

        Set<Field> swapped = fields.stream()
                .map(swapColumn(column, otherColumn))
                .collect(Collectors.toUnmodifiableSet());

        return Fields.of(swapped);
    }

    private Function<Field, Field> swapLine(AbsoluteCoordinates line, AbsoluteCoordinates otherLine) {
        return field -> {
            if (field.hasSameY(line)) {
                AbsoluteCoordinates otherCoordinates = otherLine.withX(field.absoluteCoordinates());
                Field other = fields.get(otherCoordinates);

                return field.forceValue(other.getValue());
            } else if (field.hasSameY(otherLine)) {
                AbsoluteCoordinates otherCoordinates = line.withX(field.absoluteCoordinates());
                Field other = fields.get(otherCoordinates);

                return field.forceValue(other.getValue());
            } else {
                return field;
            }
        };
    }

    private Function<Field, Field> swapColumn(AbsoluteCoordinates column, AbsoluteCoordinates otherColumn) {
        return field -> {
            if (field.hasSameX(column)) {
                AbsoluteCoordinates otherCoordinates = otherColumn.withY(field.absoluteCoordinates());
                Field other = fields.get(otherCoordinates);

                return field.forceValue(other.getValue());
            } else if (field.hasSameX(otherColumn)) {
                AbsoluteCoordinates otherCoordinates = column.withY(field.absoluteCoordinates());
                Field other = fields.get(otherCoordinates);

                return field.forceValue(other.getValue());
            } else {
                return field;
            }
        };
    }

    public static Swap fields(Fields fields) {
        return new Swap(fields);
    }
}
