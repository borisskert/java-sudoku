package com.github.borisskert.sudoku;

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
}
