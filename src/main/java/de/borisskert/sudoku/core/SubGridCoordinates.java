package de.borisskert.sudoku.core;

import java.util.Comparator;
import java.util.Objects;

/**
 * Represent coordinates within a sub-grid.
 * Every {@link SubGridCoordinates} instance is immutable.
 */
final class SubGridCoordinates implements Comparable<SubGridCoordinates> {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final int x;
    private final int y;

    /* *****************************************************************************************************************
     * Constructor(s)
     **************************************************************************************************************** */

    private SubGridCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /* *****************************************************************************************************************
     * Accessor methods
     **************************************************************************************************************** */

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /* *****************************************************************************************************************
     * Transfer methods
     **************************************************************************************************************** */

    public AbsoluteCoordinates toAbsolute(WithinSubGridCoordinates coordinates, Size size) {
        int x = this.x * size.getWidth() + coordinates.getX();
        int y = this.y * size.getHeight() + coordinates.getY();

        return AbsoluteCoordinates.from(x, y);
    }

    /* *****************************************************************************************************************
     * Implementation of Comparable interface
     **************************************************************************************************************** */

    @Override
    public int compareTo(SubGridCoordinates o) {
        return Comparator.<SubGridCoordinates>comparingInt(coordinates -> coordinates.x)
                .thenComparingInt(coordinates -> coordinates.y)
                .compare(this, o);
    }

    /* *****************************************************************************************************************
     * Overrides of Object
     **************************************************************************************************************** */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubGridCoordinates that = (SubGridCoordinates) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" +
                "x=" + x +
                ", y=" + y +
                ')';
    }

    /* *****************************************************************************************************************
     * Factory method(s)
     **************************************************************************************************************** */

    static SubGridCoordinates from(int x, int y) {
        return new SubGridCoordinates(x, y);
    }
}
