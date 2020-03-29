package de.borisskert.sudoku.core;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a grid size.
 */
class Size {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final int width;
    private final int height;

    /* *****************************************************************************************************************
     * Constructor(s)
     **************************************************************************************************************** */

    private Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /* *****************************************************************************************************************
     * Accessor methods
     **************************************************************************************************************** */

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /* *****************************************************************************************************************
     * Transfer methods
     **************************************************************************************************************** */

    public Set<FieldValue> toFieldValues() {
        return IntStream.range(1, width * height + 1)
                .mapToObj(FieldValue::of)
                .collect(Collectors.toUnmodifiableSet());
    }

    public Set<SubGridCoordinates> toSubGridCoordinates() {
        return IntStream.range(0, width).boxed()
                .flatMap(x -> IntStream.range(0, height)
                        .mapToObj(y -> SubGridCoordinates.from(y, x))
                )
                .collect(Collectors.toUnmodifiableSet());
    }

    public Set<AbsoluteCoordinates> toAbsoluteCoordinates() {
        return IntStream.range(0, width * height).boxed()
                .flatMap(x -> IntStream.range(0, width * height)
                        .mapToObj(y -> AbsoluteCoordinates.from(x, y))
                ).collect(Collectors.toUnmodifiableSet());
    }

    /* *****************************************************************************************************************
     * Overrides of Object
     **************************************************************************************************************** */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size size = (Size) o;
        return width == size.width &&
                height == size.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    @Override
    public String toString() {
        return "Size{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    /* *****************************************************************************************************************
     * Factory method(s)
     **************************************************************************************************************** */

    public static Size of(int width, int height) {
        return new Size(width, height);
    }
}
