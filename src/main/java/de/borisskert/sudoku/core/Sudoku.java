package de.borisskert.sudoku.core;

public class Sudoku {

    /* *****************************************************************************************************************
     * Private fields
     **************************************************************************************************************** */

    private final Size size;
    private Fields currentFields;

    /* *****************************************************************************************************************
     * Constructor
     **************************************************************************************************************** */

    /**
     * Prevents public instance creating
     */
    private Sudoku(Fields initialFields, Size size) {
        this.currentFields = initialFields;
        this.size = size;
    }

    /* *****************************************************************************************************************
     * Public contract
     **************************************************************************************************************** */

    public void set(int x, int y, int value) {
        AbsoluteCoordinates coordinates = AbsoluteCoordinates.from(x, y);
        FieldValue fieldValue = FieldValue.of(value);

        currentFields = ValuedFields.forSize(size)
                .and(currentFields)
                .withValueAt(coordinates, fieldValue)
                .fields();
    }

    public void resolve() {
        currentFields = resolveRecursively(this.currentFields);
    }

    public boolean isSolved() {
        return currentFields.areSolved();
    }

    public void solve() {
        currentFields = Solve.withSize(size)
                .andFields(currentFields)
                .solve();
    }

    /* *****************************************************************************************************************
     * Overrides of Object
     **************************************************************************************************************** */

    @Override
    public String toString() {
        return Formatter.format(size, currentFields);
    }

    /* *****************************************************************************************************************
     * Static factory methods
     **************************************************************************************************************** */

    public static Sudoku create(int width, int height) {
        Size size = Size.of(width, height);
        Fields fields = Fields.create(size);

        return new Sudoku(fields, size);
    }

    public static Sudoku createPuzzle(int width, int height) {
        Size size = Size.of(width, height);

        Fields puzzle = Puzzle.with(size)
                .andPercentage(0.3)
                .build()
                .newPuzzle();

        return new Sudoku(puzzle, size);
    }

    /* *****************************************************************************************************************
     * Private methods
     **************************************************************************************************************** */

    private Fields resolveRecursively(Fields fields) {
        Fields resolvedFields = SubGrids.create(size, fields)
                .resolved();

        resolvedFields = Lines.of(resolvedFields).resolve();
        resolvedFields = Columns.of(resolvedFields).resolve();

        if (!resolvedFields.equals(fields)) {
            resolvedFields = resolveRecursively(resolvedFields);
        }

        return resolvedFields;
    }
}
