package de.borisskert.sudoku.core;

import java.util.Random;
import java.util.function.BinaryOperator;

class RandomFields {

    private final Size size;
    private final BinaryOperator<SubGrid> randomSubGridSelect;
    private final BinaryOperator<FieldValue> randomCandidateSelect;
    private final BinaryOperator<Field> randomFieldSelect;


    private RandomFields(Size size, Random random) {
        this.size = size;
        randomSubGridSelect = RandomAccumulator.newInstance(random);
        randomFieldSelect = RandomAccumulator.newInstance(random);
        randomCandidateSelect = RandomAccumulator.newInstance(random);
    }

    private RandomFields(Size size, long seed) {
        this.size = size;
        randomSubGridSelect = RandomAccumulator.newInstance(seed);
        randomFieldSelect = RandomAccumulator.newInstance(seed);
        randomCandidateSelect = RandomAccumulator.newInstance(seed);
    }

    public static RandomFields create(Size size) {
        Random random = new Random();
        return new RandomFields(size, random);
    }

    public static RandomFields create(Size size, long seed) {
        return new RandomFields(size, seed);
    }

    public final FieldValue selectRandomCandidate(Field randomField) {
        return randomField.getCandidates().stream()
                .sorted()
                .reduce(randomCandidateSelect)
                .orElseThrow(() -> new RuntimeException("Cannot find a candidate"));
    }

    public final Field selectRandomField(Fields fields) {
        SubGrid randomSubGrid = selectRandomSubGrid(fields);

        return randomSubGrid.fields().stream()
                .filter(Field::isEmpty)
                .filter(Field::hasCandidates)
                .sorted()
                .reduce(randomFieldSelect)
                .orElseThrow(() -> new RuntimeException("Cannot find a empty field"));
    }

    public final SubGrid selectRandomSubGrid(Fields fields) {
        SubGrids subGrids = SubGrids.create(size, fields);

        return subGrids.stream()
                .filter(subgrid -> !subgrid.isSolved())
                .sorted()
                .reduce(randomSubGridSelect)
                .orElseThrow(() -> new RuntimeException("Cannot find unsolved Subgrid"));
    }
}
