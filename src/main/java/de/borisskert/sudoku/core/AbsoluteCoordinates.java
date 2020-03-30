package de.borisskert.sudoku.core;

import java.util.Comparator;
import java.util.Objects;

/**
 * Represents absolute coordinates within the whole grid.
 * Every {@link AbsoluteCoordinates} instance is immutable.
 */
final class AbsoluteCoordinates implements Comparable<AbsoluteCoordinates> {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final int x;
    private final int y;

    /* *****************************************************************************************************************
     * Constructors
     **************************************************************************************************************** */

    /**
     * Prevent public instance creation
     */
    private AbsoluteCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /* *****************************************************************************************************************
     * Getters
     **************************************************************************************************************** */

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /* *****************************************************************************************************************
     * Indicator methods
     **************************************************************************************************************** */

    public boolean hasSameX(AbsoluteCoordinates other) {
        return this.x == other.x;
    }

    public boolean hasSameY(AbsoluteCoordinates other) {
        return this.y == other.y;
    }

    /* *****************************************************************************************************************
     * Wither methods
     **************************************************************************************************************** */

    /**
     * Wither method to change the X coordinate.
     *
     * @param other the other {@link AbsoluteCoordinates} instance which contains the corresponding X coordinate.
     * @return a new {@link AbsoluteCoordinates} instance containing the other's X coordinate.
     */
    public AbsoluteCoordinates withX(AbsoluteCoordinates other) {
        return new AbsoluteCoordinates(other.x, y);
    }

    /**
     * Wither method to change the Y coordinate.
     *
     * @param other the other {@link AbsoluteCoordinates} instance which contains the corresponding Y coordinate.
     * @return a new {@link AbsoluteCoordinates} instance containing the other's Y coordinate.
     */
    public AbsoluteCoordinates withY(AbsoluteCoordinates other) {
        return new AbsoluteCoordinates(x, other.y);
    }

    /* *****************************************************************************************************************
     * Transfer methods
     **************************************************************************************************************** */

    /**
     * Extract the X coordinate only.
     *
     * @return a new {@link AbsoluteCoordinates} instance with the X coordinate and without the Y coordinate.
     */
    public AbsoluteCoordinates xOnly() {
        return AbsoluteCoordinates.from(x, -1);
    }

    /**
     * Extract the Y coordinate only.
     *
     * @return a new {@link AbsoluteCoordinates} instance with the Y coordinate and without the X coordinate.
     */
    public AbsoluteCoordinates yOnly() {
        return AbsoluteCoordinates.from(-1, y);
    }

    /**
     * Extract the corresponding SubGrid coordinates.
     *
     * @param size the specified grid size.
     * @return a new {@link SubGridCoordinates} instance.
     */
    public SubGridCoordinates subGrid(Size size) {
        return SubGridCoordinates.from(x / size.getWidth(), y / size.getHeight());
    }

    /**
     * Transfers this {@link AbsoluteCoordinates} into {@link WithinSubGridCoordinates}.
     *
     * @param size the specified grid size.
     * @return a new {@link WithinSubGridCoordinates} instance.
     */
    public WithinSubGridCoordinates withinSubGrid(Size size) {
        return WithinSubGridCoordinates.from(x % size.getWidth(), y % size.getHeight());
    }

    /* *****************************************************************************************************************
     * Implementation of Comparable interface
     **************************************************************************************************************** */

    @Override
    public int compareTo(AbsoluteCoordinates o) {
        return Comparator.<AbsoluteCoordinates>comparingInt(coordinates -> coordinates.x)
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
        AbsoluteCoordinates that = (AbsoluteCoordinates) o;
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
     * Factory methods
     **************************************************************************************************************** */

    public static AbsoluteCoordinates from(int x, int y) {
        return new AbsoluteCoordinates(x, y);
    }

    public static AbsoluteCoordinates fromLineOnly(int y) {
        return new AbsoluteCoordinates(-1, y);
    }

    public static AbsoluteCoordinates fromColumnOnly(int x) {
        return new AbsoluteCoordinates(x, -1);
    }
}
