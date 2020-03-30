package de.borisskert.sudoku.core;

/**
 * Creates default values based on {@link AbsoluteCoordinates}.
 */
final class DefaultValue {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final Size size;

    /* *****************************************************************************************************************
     * Constructor(s)
     **************************************************************************************************************** */

    private DefaultValue(Size size) {
        this.size = size;
    }

    /* *****************************************************************************************************************
     * Wither methods
     **************************************************************************************************************** */

    public FieldValue andFor(AbsoluteCoordinates coordinates) {
        SubGridCoordinates subGrid = coordinates.subGrid(size);

        int subGridY = subGrid.getY();
        int value = coordinates.getX() + coordinates.getY() * size.getWidth() + subGridY;
        int normalized = mod(value);

        return FieldValue.of(normalized);
    }

    /* *****************************************************************************************************************
     * Factory methods
     **************************************************************************************************************** */

    public static DefaultValue within(Size size) {
        return new DefaultValue(size);
    }

    /* *****************************************************************************************************************
     * Private methods
     **************************************************************************************************************** */

    private int mod(int value) {
        return Math.floorMod(value, max()) + 1;
    }

    private int max() {
        return size.getWidth() * size.getHeight();
    }
}
