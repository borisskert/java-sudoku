package com.github.borisskert.sudoku;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;

class Solver {

    private final Size size;
    private final ChangeHistory changeHistory;
    private final BinaryOperator<SubGrid> randomSubGridSelect;
    private final BinaryOperator<FieldValue> randomCandidateSelect;
    private final BinaryOperator<Field> randomFieldSelect;

    private SolveTrial lastTrial;
    private final Set<SolveTrial> trials = new HashSet<>();
    private final Set<SolveTrial> illegalTrials = new HashSet<>();

    public Solver(Size size, long seed) {
        this.size = size;
        randomSubGridSelect = RandomAccumulator.newInstance(seed);
        randomFieldSelect = RandomAccumulator.newInstance(seed);
        randomCandidateSelect = RandomAccumulator.newInstance(seed);
        changeHistory = new ChangeHistory();
    }

    public Fields solve(final Fields originalFields) {
        changeHistory.setup(originalFields);

        Fields changedFields = originalFields;
        Fields beforeChange;

        do {
            beforeChange = changedFields;

            try {
                changedFields = tryToSolve(originalFields);
            } catch (IllegalStateException e) {
                illegalTrials.addAll(trials);
                trials.clear();

                changedFields = changeHistory.rollBack();
            }
        } while (changedFields.equals(beforeChange));

        return changedFields;
    }

    private Fields tryToSolve(Fields originalFields) {
        Fields beforeChanges;
        Fields changedFields = originalFields;

        do {
            beforeChanges = changedFields;

            try {
                changedFields = solveDefinite(changedFields);
            } catch (IllegalStateException e) {
                trials.add(lastTrial);
                illegalTrials.addAll(trials);
                trials.clear();

                changedFields = changeHistory.rollBack();
            }

            if (!changedFields.areSolved() && changedFields.equals(beforeChanges)) {
                changedFields = tryToSolveOneRandomly(changedFields);
            }
        }
        while (!changedFields.areSolved() && !changedFields.equals(beforeChanges));

        return changedFields;
    }

    private Fields tryToSolveOneRandomly(Fields changedFields) {
        Fields beforeChange;
        do {
            beforeChange = changedFields;

            try {
                changedFields = solveOneFieldRandomly(changedFields);
            } catch (IllegalStateException e) {
                illegalTrials.addAll(trials);
                trials.clear();
            }
        } while (changedFields.equals(beforeChange));

        return changedFields;
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

            changeHistory.definite(changedFields);

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
                    .findAny()
                    .map(tryToSetValue(changedFields, fieldValue))
                    .orElseThrow(() -> {
                        throw new RuntimeException("No field with candidate " + fieldValue + " found");
                    }).clearCandidates();
        }
    }

    private Function<AbsoluteCoordinates, Fields> tryToSetValue(Fields changedFields, FieldValue fieldValue) {
        return coordinates -> {
            lastTrial = new SolveTrial(coordinates, fieldValue);
            return changedFields.withValueAt(coordinates, fieldValue);
        };
    }

    private Fields solveOneFieldRandomly(Fields originalFields) {
        SubGrids subGrids = SubGrids.create(size, originalFields);

        SolveTrial trial;
        do {
            SubGrid randomSubGrid = subGrids.stream()
                    .filter(subgrid -> !subgrid.isSolved())
                    .reduce(randomSubGridSelect)
                    .orElseThrow(() -> new RuntimeException("Cannot find unsolved Subgrid"));

            Field randomField = randomSubGrid.fields().stream()
                    .filter(Field::isEmpty)
                    .filter(Field::hasCandidates)
                    .reduce(randomFieldSelect)
                    .orElseThrow(() -> new RuntimeException("Cannot find a empty field"));

            FieldValue randomCandidate = randomField.getCandidates().stream()
                    .reduce(randomCandidateSelect)
                    .orElseThrow(() -> new RuntimeException("Cannot find a candidate"));

            trial = new SolveTrial(randomField.absoluteCoordinates(), randomCandidate);

        } while (illegalTrials.contains(trial));

        trials.add(trial);

        Fields changedFields = subGrids.withValueAt(trial.coordinates(), trial.fieldValue());
        changeHistory.trial(changedFields);

        return clearCandidates(changedFields);
    }

    private Fields clearCandidates(Fields originalFields) {
        Fields changedFields = SubGrids.create(size, originalFields).fields().stream()
                .map(Fields::clearCandidates)
                .collect(Fields.collect());

        changedFields = Columns.of(changedFields).fields().stream()
                .map(Fields::clearCandidates)
                .collect(Fields.collect());

        changedFields = Lines.of(changedFields).fields().stream()
                .map(Fields::clearCandidates)
                .collect(Fields.collect());

        return changedFields;
    }

    public static class SolveTrial {
        private final AbsoluteCoordinates coordinates;
        private final FieldValue value;

        public SolveTrial(AbsoluteCoordinates coordinates, FieldValue fieldValue) {
            this.coordinates = coordinates;
            value = fieldValue;
        }

        public AbsoluteCoordinates coordinates() {
            return coordinates;
        }

        public FieldValue fieldValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SolveTrial trial = (SolveTrial) o;
            return Objects.equals(coordinates, trial.coordinates) &&
                    Objects.equals(value, trial.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(coordinates, value);
        }
    }
}
