package com.github.borisskert.sudoku;

import com.github.borisskert.observableproperties.ChangeListener;
import com.github.borisskert.observableproperties.OptionalProperty;
import com.github.borisskert.observableproperties.ReadonlyProperty;
import com.github.borisskert.observableproperties.SimpleOptionalProperty;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Field implements ChangeListener<FieldValue> {
    static final Comparator<Field> COMPARATOR = new FieldComparator();

    private final OptionalProperty<FieldValue> valueProperty;
    private final Coordinates coordinates;
    private final Set<FieldValue> candidates;

    public Field(Coordinates coordinates, Set<FieldValue> candidates) {
        this.coordinates = coordinates;
        this.candidates = candidates;
        this.valueProperty = new SimpleOptionalProperty<>();
    }

    protected Field(Coordinates coordinates, Set<FieldValue> candidates, OptionalProperty<FieldValue> valueProperty) {
        this.coordinates = coordinates;
        this.candidates = candidates;
        this.valueProperty = valueProperty;
    }

    public int getX() {
        return this.coordinates.getX();
    }

    public int getY() {
        return this.coordinates.getY();
    }

    public Set<FieldValue> getCandidates() {
        return candidates;
    }

    public FieldValue lastCandidate() {
        if(candidates.size() > 1) {
            throw new IllegalStateException("More than one candidate");
        }

        return candidates.iterator().next();
    }

    public ReadonlyProperty<FieldValue> getValue() {
        return valueProperty;
    }

    void setValue(FieldValue value) {
        throwIfAlreadySet();
        throwIfNotInCandidates(value);

        candidates.clear();
        valueProperty.set(value);
    }

    void register(Field otherField) {
        otherField.getValue().addListener(this);
    }

    @Override
    public void onChange(ReadonlyProperty<FieldValue> property, FieldValue oldValue, FieldValue newValue) {
        failIfValueAlreadyOccupied(newValue);

        candidates.remove(newValue);
    }

    private void failIfValueAlreadyOccupied(FieldValue newValue) {
        Optional<FieldValue> maybeValue = valueProperty.asOptional();

        if (maybeValue.isPresent()) {
            FieldValue fieldValue = maybeValue.get();

            if (fieldValue.equals(newValue)) {
                throw new IllegalStateException("Value '" + newValue + "' already occupied");
            }
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

    private void throwIfNotInCandidates(FieldValue value) {
        if (!candidates.contains(value)) {
            throw new IllegalArgumentException("Value '" + value.getValue() + "' not in candidates");
        }
    }

    private static class FieldComparator implements Comparator<Field> {
        @Override
        public int compare(Field o1, Field o2) {
            if (o1.isEmpty() == o2.isEmpty()) {
                if (o1.getCandidates().size() == o2.getCandidates().size()) {
                    if (o1.getX() == o2.getX()) {
                        return Objects.compare(
                                o1.getY(),
                                o2.getY(),
                                Integer::compareTo
                        );
                    } else {
                        return Objects.compare(
                                o1.getX(),
                                o2.getX(),
                                Integer::compareTo
                        );
                    }
                } else {
                    return Objects.compare(
                            o1.getCandidates().size(),
                            o2.getCandidates().size(),
                            Integer::compareTo
                    );
                }
            } else {
                return Objects.compare(
                        !o1.isEmpty(),
                        !o2.isEmpty(),
                        Boolean::compareTo
                );
            }
        }
    }

    FieldWithAbsoluteCoordinates withinSubGrid(int offsetX, int offsetY) {
        int x = this.coordinates.getX();
        int y = this.coordinates.getY();

        return new FieldWithAbsoluteCoordinates(
                this.coordinates,
                this.candidates,
                this.valueProperty,
                new Coordinates(x + offsetX, y + offsetY)
        );
    }

    @Override
    public String toString() {
        return "Field{" +
                "valueProperty=" + valueProperty +
                ", x=" + this.coordinates.getX() +
                ", y=" + this.coordinates.getY() +
                ", candidates=" + candidates +
                '}';
    }
}
