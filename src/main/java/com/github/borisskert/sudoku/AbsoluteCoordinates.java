package com.github.borisskert.sudoku;

import java.util.Objects;

class AbsoluteCoordinates {

    private final int x;
    private final int y;

    private AbsoluteCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
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

    public boolean hasSameX(AbsoluteCoordinates other) {
        return this.x == other.x;
    }

    public boolean hasSameY(AbsoluteCoordinates other) {
        return this.y == other.y;
    }
}
