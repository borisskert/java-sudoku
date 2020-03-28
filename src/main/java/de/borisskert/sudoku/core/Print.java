package de.borisskert.sudoku.core;

import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Prints the fields as an ASCII grid.
 */
class Print {

    /* *****************************************************************************************************************
     * Static methods
     **************************************************************************************************************** */

    public static String format(Size size, Fields fields) {
        SubGrids subGrids = SubGrids.create(size, fields);

        SubGridPrint printSubGrid = new SubGridPrint(subGrids);

        return printSubGrid.toFormattedString();
    }

    /* *****************************************************************************************************************
     * Inner classes
     **************************************************************************************************************** */

    private static class SubGridPrint {
        private final int maxValueLength;
        private final Function<FieldValue, String> valueFormatter;
        private final String emptyValuePlaceholder;
        private final SubGrids subGrids;

        private SubGridPrint(SubGrids subGrids) {
            this.subGrids = subGrids;

            this.maxValueLength = getMaxValueLength();
            this.emptyValuePlaceholder = getEmptyValuePlaceholder();
            this.valueFormatter = getValueFormatter();
        }

        public String toFormattedString() {
            StringBuilder builder = new StringBuilder(formatHeader());

            StringJoiner lineJoiner = new StringJoiner(doubleVerticalDelimiter());
            for (int y = 0; y < subGrids.getWidth(); y++) {
                String subGridLine = formatSubGridLine(y);

                lineJoiner.add(subGridLine);
            }

            builder.append(lineJoiner);
            builder.append(formatFooter());

            return builder.toString();
        }

        private String formatHeader() {
            StringBuilder header = new StringBuilder("╔");

            StringJoiner joiner = new StringJoiner("╦");
            IntStream.range(0, subGrids.getHeight())
                    .mapToObj(x -> new StringJoiner("╤"))
                    .forEach(innerJoiner -> {
                        IntStream.range(0, subGrids.getWidth()).forEach(i -> innerJoiner.add(formatDoubleBar()));
                        joiner.add(innerJoiner.toString());
                    });

            header.append(joiner);
            header.append("╗\n");

            return header.toString();
        }

        private String formatFooter() {
            StringBuilder footer = new StringBuilder("╚");

            StringJoiner joiner = new StringJoiner("╩");
            IntStream.range(0, subGrids.getHeight())
                    .mapToObj(x -> new StringJoiner("╧"))
                    .forEach(innerJoiner -> {
                        IntStream.range(0, subGrids.getWidth()).forEach(i -> innerJoiner.add(formatDoubleBar()));
                        joiner.add(innerJoiner.toString());
                    });

            footer.append(joiner);
            footer.append("╝\n");

            return footer.toString();
        }

        private String formatDoubleBar() {
            return IntStream.range(0, maxValueLength)
                    .mapToObj(i -> "═")
                    .collect(Collectors.joining());
        }

        private String formatSingleBar() {
            return IntStream.range(0, maxValueLength)
                    .mapToObj(i -> "─")
                    .collect(Collectors.joining());
        }

        private String formatSubGridLine(int subGridY) {
            StringBuilder builder = new StringBuilder();

            StringJoiner subGridLineJoiner = new StringJoiner(verticalSingleDelimiter());
            for (int y = 0; y < subGrids.getHeight(); y++) {
                String subGrids = formatSubGrids(subGridY, y);

                subGridLineJoiner.add(subGrids);
            }

            builder.append(subGridLineJoiner);

            return builder.toString();
        }

        private String formatSubGrids(int subGridY, int y) {
            StringBuilder builder = new StringBuilder();

            StringJoiner subGridJoiner = new StringJoiner("║");

            for (int x = 0; x < subGrids.getHeight(); x++) {
                SubGridCoordinates coordinates = SubGridCoordinates.from(x, subGridY);
                SubGrid subGrid = subGrids.getSubGrid(coordinates);

                String formattedSubGrid = formatSubGrid(subGrid, y);
                subGridJoiner.add(formattedSubGrid);
            }

            builder.append("║");
            builder.append(subGridJoiner);
            builder.append("║\n");

            return builder.toString();
        }

        private String verticalSingleDelimiter() {
            StringBuilder delimiter = new StringBuilder("╟");

            StringJoiner joiner = new StringJoiner("╫");
            IntStream.range(0, subGrids.getHeight())
                    .mapToObj(x -> new StringJoiner("┼"))
                    .forEach(innerJoiner -> {
                        IntStream.range(0, subGrids.getWidth()).forEach(i -> innerJoiner.add(formatSingleBar()));
                        joiner.add(innerJoiner.toString());
                    });

            delimiter.append(joiner);
            delimiter.append("╢\n");

            return delimiter.toString();
        }

        private String doubleVerticalDelimiter() {
            StringBuilder delimiter = new StringBuilder("╠");

            StringJoiner joiner = new StringJoiner("╬");
            IntStream.range(0, subGrids.getHeight())
                    .mapToObj(x -> new StringJoiner("╪"))
                    .forEach(innerJoiner -> {
                        IntStream.range(0, subGrids.getWidth()).forEach(i -> innerJoiner.add(formatDoubleBar()));
                        joiner.add(innerJoiner.toString());
                    });

            delimiter.append(joiner);
            delimiter.append("╣\n");

            return delimiter.toString();
        }

        private String formatSubGrid(SubGrid subGrid, int y) {
            StringJoiner joiner = new StringJoiner("│");

            for (int x = 0; x < subGrids.getWidth(); x++) {
                WithinSubGridCoordinates coordinates = WithinSubGridCoordinates.from(x, y);
                Field field = subGrid.getField(coordinates);

                joiner.add(getFormattedField(field));
            }

            return joiner.toString();
        }

        private String getFormattedField(Field field) {
            FieldValue fieldValue = field.getValue();
            return field.isSolved() ? getFormattedValue(fieldValue) : emptyValuePlaceholder;
        }

        private String getFormattedValue(FieldValue fieldValue) {
            return valueFormatter.apply(fieldValue);
        }

        private int getMaxValueLength() {
            final int maxValue = subGrids.getWidth() * subGrids.getHeight();
            final String maxValueAsText = String.valueOf(maxValue);

            return maxValueAsText.length();
        }

        /**
         * https://stackoverflow.com/a/391978
         */
        private Function<FieldValue, String> getValueFormatter() {
            return value -> String.format(
                    "%" + maxValueLength + "s",
                    value.getValue()
            );
        }

        private String getEmptyValuePlaceholder() {
            return IntStream.range(0, maxValueLength)
                    .mapToObj(i -> " ")
                    .collect(Collectors.joining());
        }
    }
}
