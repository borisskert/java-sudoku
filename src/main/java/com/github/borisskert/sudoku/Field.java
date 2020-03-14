package com.github.borisskert.sudoku;

import com.github.borisskert.observableproperties.ChangeListener;
import com.github.borisskert.observableproperties.OptionalProperty;
import com.github.borisskert.observableproperties.ReadonlyProperty;
import com.github.borisskert.observableproperties.SimpleOptionalProperty;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Field implements ChangeListener<FieldValue> {

    private final OptionalProperty<FieldValue> valueProperty = new SimpleOptionalProperty<>();

    private final int x;
    private final int y;

    private final Set<FieldValue> candidates;

    public Field(int x, int y, Set<FieldValue> candidates) {
        this.x = x;
        this.y = y;
        this.candidates = new HashSet<>(candidates);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Set<FieldValue> getCandidates() {
        return candidates;
    }

    public ReadonlyProperty<FieldValue> getValue() {
        return valueProperty;
    }

    public void setValue(FieldValue value) {
        throwIfAlreadySet();

        candidates.clear();
        valueProperty.set(value);
    }

    public void register(Field otherField) {
        otherField.getValue().addListener(this);
    }

    @Override
    public void onChange(ReadonlyProperty<FieldValue> property, FieldValue oldValue, FieldValue newValue) {
        candidates.remove(newValue);

        if (candidates.size() == 1) {
            setValue(candidates.iterator().next());
        }
    }

    public boolean isEmpty() {
        return valueProperty.asOptional().isEmpty();
    }

    private void throwIfAlreadySet() {
        Optional<FieldValue> maybeValue = valueProperty.asOptional();

        if (maybeValue.isPresent()) {
            FieldValue fieldValue = maybeValue.get();
            throw new IllegalStateException("Already contains the value '" + fieldValue.getValue() + "'");
        }
    }
}
