package com.github.borisskert.sudoku;

import java.util.List;

public class Sudoku {

    private final Fields fields;

    public Sudoku() {
        fields = new Fields();
    }

    public static Sudoku create(int size) {
        return new Sudoku();
    }

    public List<FieldValue> getCandidates(int x, int y) {
        return fields.get(x, y).getCandidates();
    }

    public void set(int x, int y, int value) {
        fields.get(x, y).setValue(FieldValue.of(value));
    }
}
