package com.github.borisskert.sudoku;

import com.github.borisskert.observableproperties.*;

import java.util.ArrayList;
import java.util.List;

public class Field implements ChangeListener<FieldValue> {

    private final OptionalProperty<FieldValue> valueProperty = new SimpleOptionalProperty<>();
    private final int x;
    private final int y;
    private final List<FieldValue> candidates;

    public Field(int x, int y, List<FieldValue> candidates) {
        this.x = x;
        this.y = y;
        this.candidates = new ArrayList<>(candidates);
    }

    public List<FieldValue> getCandidates() {
        return candidates;
    }

    public ReadonlyProperty<FieldValue> getValue() {
        return valueProperty;
    }

    public void setValue(FieldValue value) {
        candidates.clear();
        valueProperty.set(value);
    }

    public void register(Field otherField) {
        otherField.getValue().addListener(this);
    }

    @Override
    public void onChange(ReadonlyProperty<FieldValue> property, FieldValue oldValue, FieldValue newValue) {
        candidates.remove(newValue);
    }
}
