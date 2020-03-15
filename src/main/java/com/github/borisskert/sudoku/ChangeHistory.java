package com.github.borisskert.sudoku;

import java.util.Stack;

public class ChangeHistory {

    private final SubGrids subGrids;
    private final Stack<Change> changes = new Stack<>();
    private final Formatter formatter;

    public ChangeHistory(SubGrids subGrids) {
        this.subGrids = subGrids;
        formatter = new Formatter(subGrids);
    }

    public void performSetup(final int x, final int y, final int value) {
        performChange(x, y, value, Change.ChangeType.SETUP);
    }

    public void performTrial(int x, int y, int value) {
        performChange(x, y, value, Change.ChangeType.TRIAL);
    }

    public void performDefinite(int x, int y, int value) {
        performChange(x, y, value, Change.ChangeType.DEFINITE);
    }

    private void performChange(int x, int y, int value, Change.ChangeType setup) {
        Change change = createChange(x, y, value, setup);

        change.perform();
        changes.push(change);
    }

    private Change createChange(final int x, final int y, final int value, final Change.ChangeType type) {
        return new Change() {
                @Override
                public void perform() {
                    subGrids.getField(x, y).setValue(FieldValue.of(value));
                }

                @Override
                public void undo() {

                }

                @Override
                public ChangeType getType() {
                    return type;
                }
            };
    }
}
