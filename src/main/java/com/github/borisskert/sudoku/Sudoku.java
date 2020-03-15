package com.github.borisskert.sudoku;

import java.util.Set;

public class Sudoku {

    private final SubGrids subGrids;
    private final Formatter formatter;
    private final ChangeDetection changeDetection;
    private final ChangeHistory changeHistory;

    public Sudoku(int sizeX, int sizeY) {
        subGrids = new SubGrids(sizeX, sizeY);
        formatter = new Formatter(subGrids);
        changeHistory = new ChangeHistory(subGrids);
        changeDetection = new ChangeDetection(subGrids, changeHistory);
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
        changeHistory.performSetup(x, y, value);
    }

    public void detectChanges() {
        changeDetection.detectChanges();
    }

    public boolean isSolved() {
        return subGrids.areSolved();
    }

    public void solve() {
        Solver solver = new Solver(changeDetection, changeHistory, subGrids);
        solver.solve();
    }

    @Override
    public String toString() {
        return formatter.format();
    }
}
