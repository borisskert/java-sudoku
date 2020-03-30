package de.borisskert.sudoku.core;

import java.util.Stack;

/**
 * Implements a solving algorithm for sudoku puzzles.
 * <p>
 * Steps:
 * (1) try to solve all definite fields and candidates
 * (2) if not solved, solve one random field with random value
 * (3) continue with step 1 until the puzzle gets solved, or an error occurs.
 * (4) if an error occurs continue with step 1 with backtracking
 */
final class Solve {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final Size size;
    private final Fields fields;
    private final RandomFields randomFields;

    private final RestorePoints restorePoints = new RestorePoints();

    /* *****************************************************************************************************************
     * Constructor(s)
     **************************************************************************************************************** */

    private Solve(Size size, Fields fields, RandomFields randomFields) {
        this.size = size;
        this.fields = fields;
        this.randomFields = randomFields;
    }

    /* *****************************************************************************************************************
     * Public contract
     **************************************************************************************************************** */

    public Fields solve() {
        Fields solvedFields = solveDefinite(this.fields);
        restorePoints.push(solvedFields);

        while (!solvedFields.areSolved()) {
            try {
                solvedFields = solveOneFieldRandomly(solvedFields);
                solvedFields = tryToSolveDefinite(solvedFields);
                restorePoints.push(solvedFields);
            } catch (IllegalDefiniteTrialException e) {
                solvedFields = restorePoints.pop();
            }
        }

        return solvedFields;
    }

    /* *****************************************************************************************************************
     * Private methods
     **************************************************************************************************************** */

    private Fields solveOneFieldRandomly(Fields originalFields) {
        Field randomField = randomFields.selectRandomField(originalFields);
        FieldValue randomCandidate = randomFields.selectRandomCandidate(randomField);

        return ValuedFields.forSize(size)
                .and(originalFields)
                .withValueAt(randomField.absoluteCoordinates(), randomCandidate)
                .fields();
    }

    private Fields tryToSolveDefinite(Fields originalFields) {
        try {
            return solveDefinite(originalFields);
        } catch (IllegalStateException e) {
            throw new IllegalDefiniteTrialException(e);
        }
    }

    private Fields solveDefinite(Fields originalFields) {
        Fields beforeChanges;
        Fields changedFields = originalFields;

        do {
            beforeChanges = changedFields;

            changedFields = SubGrids.create(size, changedFields).fields().stream()
                    .map(this::setupOneDefinite)
                    .collect(Fields.collect());

            changedFields = Columns.of(changedFields).fields().stream()
                    .map(this::setupOneDefinite)
                    .collect(Fields.collect());

            changedFields = Lines.of(changedFields).fields().stream()
                    .map(this::setupOneDefinite)
                    .collect(Fields.collect());

        } while (!beforeChanges.equals(changedFields));

        return changedFields;
    }

    private Fields setupOneDefinite(Fields fields) {
        Fields changedFields = fields.clearCandidates();

        Candidates candidates = changedFields.definiteCandidates();

        if (candidates.isEmpty()) {
            return changedFields;
        } else {
            FieldValue fieldValue = candidates.any();

            return changedFields.stream()
                    .filter(field -> field.hasCandidate(fieldValue))
                    .map(Field::absoluteCoordinates)
                    .sorted()
                    .findFirst()
                    .map(coordinates -> changedFields.withValueAt(coordinates, fieldValue))
                    .orElseThrow(() -> {
                        throw new RuntimeException("No field with candidate " + fieldValue + " found");
                    }).clearCandidates();
        }
    }

    /* *****************************************************************************************************************
     * Factory methods and Builder(s)
     **************************************************************************************************************** */

    public static Builder withSize(Size size) {
        return new Builder(size);
    }

    public static class Builder {
        private final Size size;
        private Long seed;

        private Builder(Size size) {
            this.size = size;
        }

        public Builder andSeed(long seed) {
            this.seed = seed;
            return this;
        }

        public Solve andFields(Fields fields) {
            if (seed == null) {
                return new Solve(size, fields, RandomFields.create(size));
            } else {
                return new Solve(size, fields, RandomFields.create(size, 123L));
            }
        }
    }

    /* *****************************************************************************************************************
     * Inner classes
     **************************************************************************************************************** */

    private static class RestorePoints {
        private final Stack<Fields> stack = new Stack<>();

        public void push(Fields restorePoint) {
            stack.push(restorePoint);
        }

        public Fields pop() {
            return stack.pop();
        }
    }

    private static class IllegalDefiniteTrialException extends RuntimeException {
        public IllegalDefiniteTrialException(Throwable cause) {
            super(cause);
        }
    }
}
