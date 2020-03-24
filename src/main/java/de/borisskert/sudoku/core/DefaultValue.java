package de.borisskert.sudoku.core;

class DefaultValue {

    private final Size size;

    private DefaultValue(Size size) {
        this.size = size;
    }

    public FieldValue andFor(AbsoluteCoordinates coordinates) {
        SubGridCoordinates subGrid = coordinates.subGrid(size);

        int subGridY = subGrid.getY();
        int value = coordinates.getX() + coordinates.getY() * size.getWidth() + subGridY;
        int normalized = mod(value);

        return FieldValue.of(normalized);
    }

    public static DefaultValue within(Size size) {
        return new DefaultValue(size);
    }

    private int mod(int value) {
        return Math.floorMod(value, max()) + 1;
    }

    private int max() {
        return size.getWidth() * size.getHeight();
    }
}
