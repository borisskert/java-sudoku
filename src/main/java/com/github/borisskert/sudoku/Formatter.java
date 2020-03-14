package com.github.borisskert.sudoku;

import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Formatter {

    private final int sizeX;
    private final int sizeY;
    private final int maxValueLength;
    private final Function<FieldValue, String> valueFormatter;
    private final String emptyValuePlaceholder;
    private final SubGrids subGrids;

    public Formatter(int sizeX, int sizeY, SubGrids subGrids) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.subGrids = subGrids;

        this.maxValueLength = getMaxValueLength(sizeX, sizeY);
        this.emptyValuePlaceholder = getEmptyValuePlaceholder();
        this.valueFormatter = getValueFormatter();
    }

    public String format() {
        StringBuilder builder = new StringBuilder(formatHeader());

        StringJoiner lineJoiner = new StringJoiner(doubleVerticalDelimiter());
        for (int y = 0; y < sizeX; y++) {
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
        IntStream.range(0, sizeY)
                .mapToObj(x -> new StringJoiner("╤"))
                .forEach(innerJoiner -> {
                    IntStream.range(0, sizeX).forEach(i -> innerJoiner.add(formatDoubleBar()));
                    joiner.add(innerJoiner.toString());
                });

        header.append(joiner);
        header.append("╗\n");

        return header.toString();
    }

    private String formatFooter() {
        StringBuilder footer = new StringBuilder("╚");

        StringJoiner joiner = new StringJoiner("╩");
        IntStream.range(0, sizeY)
                .mapToObj(x -> new StringJoiner("╧"))
                .forEach(innerJoiner -> {
                    IntStream.range(0, sizeX).forEach(i -> innerJoiner.add(formatDoubleBar()));
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
        for (int y = 0; y < sizeY; y++) {
            String subGrids = formatSubGrids(subGridY, y);

            subGridLineJoiner.add(subGrids);
        }

        builder.append(subGridLineJoiner);

        return builder.toString();
    }

    private String formatSubGrids(int subGridY, int y) {
        StringBuilder builder = new StringBuilder();

        StringJoiner subGridJoiner = new StringJoiner("║");

        for (int x = 0; x < sizeY; x++) {
            SubGrid subGrid = subGrids.getSubGrid(x, subGridY);

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
        IntStream.range(0, sizeY)
                .mapToObj(x -> new StringJoiner("┼"))
                .forEach(innerJoiner -> {
                    IntStream.range(0, sizeX).forEach(i -> innerJoiner.add(formatSingleBar()));
                    joiner.add(innerJoiner.toString());
                });

        delimiter.append(joiner);
        delimiter.append("╢\n");

        return delimiter.toString();
    }

    private String doubleVerticalDelimiter() {
        StringBuilder delimiter = new StringBuilder("╠");

        StringJoiner joiner = new StringJoiner("╬");
        IntStream.range(0, sizeY)
                .mapToObj(x -> new StringJoiner("╪"))
                .forEach(innerJoiner -> {
                    IntStream.range(0, sizeX).forEach(i -> innerJoiner.add(formatDoubleBar()));
                    joiner.add(innerJoiner.toString());
                });

        delimiter.append(joiner);
        delimiter.append("╣\n");

        return delimiter.toString();
    }

    private String formatSubGrid(SubGrid subGrid, int y) {
        StringJoiner joiner = new StringJoiner("│");

        for (int x = 0; x < sizeX; x++) {
            Field field = subGrid.get(x, y);
            joiner.add(getFormattedField(field));
        }

        return joiner.toString();
    }

    private String getFormattedField(Field field) {
        FieldValue fieldValue = field.getValue().get();
        return fieldValue != null ? getFormattedValue(fieldValue) : emptyValuePlaceholder;
    }

    private String getFormattedValue(FieldValue fieldValue) {
        return valueFormatter.apply(fieldValue);
    }

    private int getMaxValueLength(int sizeX, int sizeY) {
        final int maxValue = sizeX * sizeY;
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
