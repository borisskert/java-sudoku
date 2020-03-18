package com.github.borisskert.sudoku;

import java.util.Objects;
import java.util.Set;

class Field {

    /* *****************************************************************************************************************
     * Private fields
     **************************************************************************************************************** */

    private final Size size;
    private final AbsoluteCoordinates coordinates;
    private final Candidates candidates;
    private final FieldValue fieldValue;

    /* *****************************************************************************************************************
     * Constructors
     **************************************************************************************************************** */

    private Field(Size size, AbsoluteCoordinates coordinates, Candidates candidates) {
        this.size = size;
        this.coordinates = coordinates;
        this.candidates = candidates;
        this.fieldValue = null;
    }

    private Field(Size size, AbsoluteCoordinates coordinates, FieldValue value) {
        this.size = size;
        this.coordinates = coordinates;
        this.fieldValue = value;
        candidates = Candidates.empty();
    }

    private Field(Size size, AbsoluteCoordinates coordinates, Candidates candidates, FieldValue fieldValue) {
        this.size = size;
        this.coordinates = coordinates;
        this.candidates = candidates;
        this.fieldValue = fieldValue;
    }

    /* *****************************************************************************************************************
     * Public contract
     **************************************************************************************************************** */

    public boolean hasSameX(AbsoluteCoordinates other) {
        return this.coordinates.hasSameX(other);
    }

    public boolean hasSameY(AbsoluteCoordinates other) {
        return this.coordinates.hasSameY(other);
    }

    public boolean has(AbsoluteCoordinates coordinates) {
        return this.coordinates.equals(coordinates);
    }

    public boolean has(WithinSubGridCoordinates coordinates) {
        return this.coordinates.withinSubGrid(size).equals(coordinates);
    }

    public boolean isInSubGrid(SubGridCoordinates coordinates) {
        return this.coordinates.subGrid(size)
                .equals(coordinates);
    }

    public boolean isEmpty() {
        return fieldValue == null;
    }

    public boolean isSolved() {
        return fieldValue != null;
    }

    public boolean canBeSolved() {
        return this.candidates.isDefinite();
    }

    public boolean hasCandidate(FieldValue candidate) {
        return candidates.contains(candidate);
    }

    public boolean hasCandidates() {
        return !candidates.isEmpty();
    }

    public boolean hasValue(FieldValue value) {
        return Objects.equals(this.fieldValue, value);
    }

    public AbsoluteCoordinates absoluteCoordinates() {
        return coordinates;
    }

    public FieldValue getValue() {
        return fieldValue;
    }

    public Candidates getCandidates() {
        return candidates;
    }

    public Field solve() {
        return new Field(size, coordinates, candidates.lastOne());
    }

    public Field withoutCandidate(FieldValue value) {
        return new Field(size, coordinates, candidates.without(value), fieldValue);
    }

    public Field withoutCandidates(Set<FieldValue> values) {
        Candidates changedCandidates = candidates.without(values);

        if (fieldValue == null && changedCandidates.isEmpty()) {
            throw new IllegalStateException(coordinates.toString() + ": No value and no candidates left");
        }

        return new Field(size, coordinates, changedCandidates, fieldValue);
    }

    public Field withValue(FieldValue value) {
        if (Objects.equals(this.fieldValue, value)) {
            return this;
        }

        if (this.fieldValue != null) {
            throw new IllegalStateException("Already contains the value '" + this.fieldValue.toString() + "'");
        }

        if (!candidates.contains(value)) {
            throw new IllegalStateException("Value '" + value.toString() + "' not in candidates");
        }

        return new Field(size, coordinates, value);
    }

    /* *****************************************************************************************************************
     * Overrides of Object
     **************************************************************************************************************** */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field that = (Field) o;
        return Objects.equals(size, that.size) &&
                Objects.equals(coordinates, that.coordinates) &&
                Objects.equals(candidates, that.candidates) &&
                Objects.equals(fieldValue, that.fieldValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, coordinates, candidates, fieldValue);
    }

    @Override
    public String toString() {
        return "<" + coordinates + ", {" + fieldValue + "}, " + candidates + '>';
    }

    /* *****************************************************************************************************************
     * Factory methods
     **************************************************************************************************************** */

    public static Field empty(AbsoluteCoordinates coordinates, Size size) {
        return new Field(size, coordinates, Candidates.create(size));
    }
}
