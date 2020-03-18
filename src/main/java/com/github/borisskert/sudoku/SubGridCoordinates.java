package com.github.borisskert.sudoku;

import java.util.Objects;

class SubGridCoordinates {

    private final int x;
    private final int y;

    private SubGridCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public WithinSubGridCoordinates withinSubGrid(Size size) {
        return WithinSubGridCoordinates.from(x % size.getWidth(), y % size.getHeight());
    }

    public AbsoluteCoordinates toAbsolute(WithinSubGridCoordinates coordinates, Size size) {
        int x = this.x * size.getWidth() + coordinates.getX();
        int y = this.y * size.getHeight() + coordinates.getY();

        return AbsoluteCoordinates.from(x, y);
    }

    static SubGridCoordinates from(int x, int y) {
        return new SubGridCoordinates(x, y);
    }

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
}
