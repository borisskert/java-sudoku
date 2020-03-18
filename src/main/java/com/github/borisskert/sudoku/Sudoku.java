package com.github.borisskert.sudoku;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

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

        Fields changedFields = SubGrids.create(size, currentFields).withValueAt(coordinates, fieldValue);
        changedFields = Lines.of(changedFields).withValueAt(coordinates, fieldValue);
        changedFields = Columns.of(changedFields).withValueAt(coordinates, fieldValue);

        currentFields = changedFields;
    }

    public void resolve() {
        currentFields = resolveRecursively(this.currentFields);
    }

    public boolean isSolved() {
        return currentFields.areSolved();
    }

    public void solve() {
        long seed = new Random().nextLong();

        Solver solver = new Solver(size, seed);

        currentFields = solver.solve(this.currentFields);
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
        Size size = Size.from(width, height);

        Set<Field> createdFields = size.toAbsoluteCoordinates().stream()
                .map(coordinates -> Field.empty(coordinates, size))
                .collect(Collectors.toUnmodifiableSet());

        Fields fields = Fields.of(createdFields);

        return new Sudoku(fields, size);
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
