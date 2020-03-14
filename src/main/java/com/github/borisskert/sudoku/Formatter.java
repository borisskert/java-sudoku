package com.github.borisskert.sudoku;

import java.util.StringJoiner;
import java.util.stream.IntStream;

class Formatter {

    private final int sizeX;
    private final int sizeY;
    private final SubGrids subGrids;

    public Formatter(int sizeX, int sizeY, SubGrids subGrids) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.subGrids = subGrids;
    }

    public String format() {
        StringBuilder builder = new StringBuilder(formatHeader());

        StringJoiner lineJoiner = new StringJoiner(doubleVerticalSubGridDelimiter());
        for (int y = 0; y < sizeY; y++) {
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
        IntStream.range(0, sizeX)
                .mapToObj(x -> new StringJoiner("╤"))
                .forEach(innerJoiner -> {
                    IntStream.range(0, sizeX).forEach(i -> innerJoiner.add("═"));
                    joiner.add(innerJoiner.toString());
                });

        header.append(joiner);
        header.append("╗\n");

        return header.toString();
    }

    private String formatFooter() {
        StringBuilder footer = new StringBuilder("╚");

        StringJoiner joiner = new StringJoiner("╩");
        IntStream.range(0, sizeX)
                .mapToObj(x -> new StringJoiner("╧"))
                .forEach(innerJoiner -> {
                    IntStream.range(0, sizeX).forEach(i -> innerJoiner.add("═"));
                    joiner.add(innerJoiner.toString());
                });

        footer.append(joiner);
        footer.append("╝\n");

        return footer.toString();
    }

    private String formatSubGridLine(int subGridY) {
        StringBuilder builder = new StringBuilder();

        StringJoiner subGridLineJoiner = new StringJoiner(verticalSubGridDelimiter());
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

        for (int x = 0; x < sizeX; x++) {
            SubGrid subGrid = subGrids.getSubGrid(x, subGridY);

            String formattedSubGrid = formatSubGrid(subGrid, y);
            subGridJoiner.add(formattedSubGrid);
        }

        builder.append("║");
        builder.append(subGridJoiner);
        builder.append("║\n");

        return builder.toString();
    }

    private String verticalSubGridDelimiter() {
        StringBuilder delimiter = new StringBuilder("╟");

        StringJoiner joiner = new StringJoiner("╫");
        IntStream.range(0, sizeX)
                .mapToObj(x -> new StringJoiner("┼"))
                .forEach(innerJoiner -> {
                    IntStream.range(0, sizeX).forEach(i -> innerJoiner.add("─"));
                    joiner.add(innerJoiner.toString());
                });

        delimiter.append(joiner);
        delimiter.append("╢\n");

        return delimiter.toString();
    }

    private String doubleVerticalSubGridDelimiter() {
        StringBuilder delimiter = new StringBuilder("╠");

        StringJoiner joiner = new StringJoiner("╬");
        IntStream.range(0, sizeX)
                .mapToObj(x -> new StringJoiner("╪"))
                .forEach(innerJoiner -> {
                    IntStream.range(0, sizeX).forEach(i -> innerJoiner.add("═"));
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
            joiner.add(getFormattedValue(field));
        }

        return joiner.toString();
    }

    private String getFormattedValue(Field field) {
        FieldValue fieldValue = field.getValue().get();
        return fieldValue != null ? String.valueOf(fieldValue.getValue()) : " ";
    }
}
