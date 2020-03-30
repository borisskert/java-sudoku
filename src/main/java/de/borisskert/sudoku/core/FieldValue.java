package de.borisskert.sudoku.core;

import java.util.Objects;

/**
 * Represents a value of a {@link Field}.
 * Every {@link FieldValue} instance is immutable.
 */
final class FieldValue implements Comparable<FieldValue> {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final int value;

    /* *****************************************************************************************************************
     * Constructor(s)
     **************************************************************************************************************** */

    private FieldValue(int value) {
        this.value = value;
    }

    /* *****************************************************************************************************************
     * Accessor methods
     **************************************************************************************************************** */

    public int getValue() {
        return value;
    }

    /* *****************************************************************************************************************
     * Implementation of Comparable
     **************************************************************************************************************** */

    @Override
    public int compareTo(FieldValue o) {
        return Integer.compare(this.value, o.value);
    }

    /* *****************************************************************************************************************
     * Overrides of Object
     **************************************************************************************************************** */

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

    /* *****************************************************************************************************************
     * Factory methods
     **************************************************************************************************************** */

    public static FieldValue of(int value) {
        assert value != 0;
        return new FieldValue(value);
    }
}
