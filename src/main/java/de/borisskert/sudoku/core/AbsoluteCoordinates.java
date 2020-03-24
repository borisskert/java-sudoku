package de.borisskert.sudoku.core;

import java.util.Comparator;
import java.util.Objects;

class AbsoluteCoordinates implements Comparable<AbsoluteCoordinates> {

    private final int x;
    private final int y;

    private AbsoluteCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public AbsoluteCoordinates withX(AbsoluteCoordinates other) {
        return new AbsoluteCoordinates(other.x, y);
    }

    public AbsoluteCoordinates withY(AbsoluteCoordinates other) {
        return new AbsoluteCoordinates(x, other.y);
    }

    public AbsoluteCoordinates xOnly() {
        return AbsoluteCoordinates.from(x, -1);
    }

    public AbsoluteCoordinates yOnly() {
        return AbsoluteCoordinates.from(-1, y);
    }

    public SubGridCoordinates subGrid(Size size) {
        return SubGridCoordinates.from(x / size.getWidth(), y / size.getHeight());
    }

    public WithinSubGridCoordinates withinSubGrid(Size size) {
        return WithinSubGridCoordinates.from(x % size.getWidth(), y % size.getHeight());
    }

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

    public static AbsoluteCoordinates from(int x, int y) {
        return new AbsoluteCoordinates(x, y);
    }

    public static AbsoluteCoordinates fromLineOnly(int y) {
        return new AbsoluteCoordinates(-1, y);
    }

    public static AbsoluteCoordinates fromColumnOnly(int x) {
        return new AbsoluteCoordinates(x, -1);
    }

    public boolean hasSameX(AbsoluteCoordinates other) {
        return this.x == other.x;
    }

    public boolean hasSameY(AbsoluteCoordinates other) {
        return this.y == other.y;
    }

    @Override
    public int compareTo(AbsoluteCoordinates o) {
        return Comparator.<AbsoluteCoordinates>comparingInt(coordinates -> coordinates.x)
                .thenComparingInt(coordinates -> coordinates.y)
                .compare(this, o);
    }
}
