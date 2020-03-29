package de.borisskert.sudoku.core;

import java.util.Objects;

/**
 * Represent coordinates within a {@link SubGrid}
 * Every {@link SubGridCoordinates} is immutable.
 */
class WithinSubGridCoordinates {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final int x;
    private final int y;

    /* *****************************************************************************************************************
     * Constructor(s)
     **************************************************************************************************************** */

    private WithinSubGridCoordinates(int x, int y) {
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
     * Transfer method(s)
     **************************************************************************************************************** */

    public AbsoluteCoordinates absolute(Size size, SubGridCoordinates subGridCoordinates) {
        int x = this.x + size.getWidth() * subGridCoordinates.getX();
        int y = this.y + size.getHeight() * subGridCoordinates.getY();

        return AbsoluteCoordinates.from(x, y);
    }

    /* *****************************************************************************************************************
     * Overrides of Object
     **************************************************************************************************************** */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WithinSubGridCoordinates that = (WithinSubGridCoordinates) o;
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

    public static WithinSubGridCoordinates from(int x, int y) {
        return new WithinSubGridCoordinates(x, y);
    }
}
