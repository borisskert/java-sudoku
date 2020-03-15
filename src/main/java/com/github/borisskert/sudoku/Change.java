package com.github.borisskert.sudoku;

public interface Change {
    void perform();

    void undo();

    ChangeType getType();

    enum ChangeType {
        SETUP,
        TRIAL,
        DEFINITE
    }
}
