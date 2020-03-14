package com.github.borisskert.sudoku;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Set;

public class Sudoku {

    private final SubGrids subGrids;

    public Sudoku(int sizeX, int sizeY) {
        subGrids = new SubGrids(sizeX, sizeY);
    }

    public static Sudoku create(int sizeX, int sizeY) {
        return new Sudoku(sizeX, sizeY);
    }

    public Set<FieldValue> getCandidates(int x, int y) {
        Field field = subGrids.getField(x, y);
        return field.getCandidates();
    }

    public FieldValue get(int x, int y) {
        Field field = subGrids.getField(x, y);
        return field.getValue().get();
    }

    public void set(int x, int y, int value) {
        subGrids.getField(x, y).setValue(FieldValue.of(value));
    }

    public boolean isSolved() {
        return subGrids.areSolved();
    }

    public Collection<Field> getUnresolvedFields() {
        return subGrids.getUnresolvedFields();
    }

    @Override
    public String toString() {
        return "╔═╤═╦═╤═╗\n" +
                MessageFormat.format(
                        "║{0}│{1}║{2}│{3}║\n",
                        getFormattedValue(0, 0),
                        getFormattedValue(1, 0),
                        getFormattedValue(2, 0),
                        getFormattedValue(3, 0)
                ) +
                "╟─┼─╫─┼─╢\n" +
                MessageFormat.format(
                        "║{0}│{1}║{2}│{3}║\n",
                        getFormattedValue(0, 1),
                        getFormattedValue(1, 1),
                        getFormattedValue(2, 1),
                        getFormattedValue(3, 1)
                ) +
                "╠═╪═╬═╪═╣\n" +
                MessageFormat.format(
                        "║{0}│{1}║{2}│{3}║\n",
                        getFormattedValue(0, 2),
                        getFormattedValue(1, 2),
                        getFormattedValue(2, 2),
                        getFormattedValue(3, 2)
                ) +
                "╟─┼─╫─┼─╢\n" +
                MessageFormat.format(
                        "║{0}│{1}║{2}│{3}║\n",
                        getFormattedValue(0, 3),
                        getFormattedValue(1, 3),
                        getFormattedValue(2, 3),
                        getFormattedValue(3, 3)
                ) +
                "╚═╧═╩═╧═╝\n";
    }

    private String getFormattedValue(int x, int y) {
        Field field = subGrids.getField(x, y);
        FieldValue fieldValue = field.getValue().get();

        return fieldValue != null ? String.valueOf(fieldValue.getValue()) : " ";
    }
}
