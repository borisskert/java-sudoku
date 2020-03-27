package de.borisskert.sudoku.core;

import java.util.Objects;

class FieldValue implements Comparable<FieldValue> {

    private final int value;

    private FieldValue(int value) {
        this.value = value;
    }

    public static FieldValue of(int value) {
        assert value != 0;
        return new FieldValue(value);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldValue that = (FieldValue) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int compareTo(FieldValue o) {
        return Integer.compare(this.value, o.value);
    }
}