package com.github.borisskert.sudoku;

import java.util.Stack;

class ChangeHistory {

    private final Stack<Change> changes = new Stack<>();

    public void trial(Fields changedFields) {
        performChange(changedFields, Change.ChangeType.TRIAL);
    }

    public void definite(Fields changedFields) {
        performChange(changedFields, Change.ChangeType.DEFINITE);
    }

    public void setup(Fields originalFields) {
        performChange(originalFields, Change.ChangeType.SETUP);
    }

    private void performChange(Fields changedFields, Change.ChangeType type) {
        Change change = new Change(changedFields, type);
        changes.push(change);
    }

    public Fields rollBack() {
        do {
            changes.pop();
        } while (changes.peek().type != Change.ChangeType.TRIAL);

        return changes.peek().fields;
    }

    private static class Change {
        private final Fields fields;
        private final ChangeType type;

        public Change(Fields fields, ChangeType type) {
            this.fields = fields;
            this.type = type;
        }

        enum ChangeType {
            SETUP,
            TRIAL,
            DEFINITE
        }
    }
}
