package de.borisskert.sudoku.core;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

class Solve {

    private final Size size;
    private final Fields fields;
    private final RandomFields randomFields;

    private final RestorePoints restorePoints = new RestorePoints();
    private final Trials trials = new Trials();

    private Solve(Size size, Fields fields, RandomFields randomFields) {
        this.size = size;
        this.fields = fields;
        this.randomFields = randomFields;
    }

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

    private Fields solveOneFieldRandomly(Fields originalFields) {
        Field randomField;
        FieldValue randomCandidate;

        do {
            randomField = randomFields.selectRandomField(originalFields);
            randomCandidate = randomFields.selectRandomCandidate(randomField);
        } while (trials.isIllegal(randomField.absoluteCoordinates(), randomCandidate));

        Fields fieldsWithRandomValue = ValuedFields.forSize(size)
                .and(originalFields)
                .withValueAt(randomField.absoluteCoordinates(), randomCandidate)
                .fields();

        trials.addTrial(randomField.absoluteCoordinates(), randomCandidate);

        return fieldsWithRandomValue;

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

    private static class RestorePoints {
        private final Stack<Fields> stack = new Stack<>();

        public void push(Fields restorePoint) {
            stack.push(restorePoint);
        }

        public Fields pop() {
            return stack.pop();
        }
    }

    public static class Trials {
        private final Set<Trial> currentTrials = new HashSet<>();
        private final Set<Trial> illegalTrials = new HashSet<>();

        public void addTrial(AbsoluteCoordinates coordinates, FieldValue value) {
            currentTrials.add(new Trial(coordinates, value));
        }

        public boolean isIllegal(AbsoluteCoordinates coordinates, FieldValue value) {
            return illegalTrials.contains(new Trial(coordinates, value));
        }
    }

    public static class Trial {
        private final AbsoluteCoordinates coordinates;
        private final FieldValue value;

        public Trial(AbsoluteCoordinates coordinates, FieldValue fieldValue) {
            this.coordinates = coordinates;
            value = fieldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Trial trial = (Trial) o;
            return Objects.equals(coordinates, trial.coordinates) &&
                    Objects.equals(value, trial.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(coordinates, value);
        }
    }

    private static class IllegalDefiniteTrialException extends RuntimeException {
        public IllegalDefiniteTrialException(Throwable cause) {
            super(cause);
        }
    }
}
