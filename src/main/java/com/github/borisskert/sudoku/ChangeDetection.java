package com.github.borisskert.sudoku;

import java.util.Collection;

public class ChangeDetection {

    private final SubGrids subGrids;
    private final ChangeHistory changeHistory;

    public ChangeDetection(SubGrids subGrids, ChangeHistory changeHistory) {
        this.subGrids = subGrids;
        this.changeHistory = changeHistory;
    }

    public void detectChanges() {
        Collection<FieldWithAbsoluteCoordinates> definiteFields = subGrids.findDefiniteFields();

        definiteFields
                .stream()
                .findFirst()
                .ifPresent(field -> {
                            int lastValue = field.lastCandidate().getValue();
                            changeHistory.performDefinite(field.getAbsoluteX(), field.getAbsoluteY(), lastValue);
                            detectChanges();
                        }
                );
    }

    public void set(int x, int y, int value) {
        subGrids.getField(x, y).setValue(FieldValue.of(value));
    }
}
