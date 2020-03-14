package com.github.borisskert.sudoku;

import java.util.List;

public class Sudoku {

    private final SubGrids subGrids;

    public Sudoku() {
        subGrids = new SubGrids();
    }

    public static Sudoku create(int size) {
        return new Sudoku();
    }

    public List<FieldValue> getCandidates(int x, int y) {
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
}
