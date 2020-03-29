package de.borisskert.sudoku.core;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Creates a sudoku puzzle with random values and empty fields.
 */
class Puzzle {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final Coin coin;
    private final Size size;
    private final double percentage;
    private final Shuffle.Builder shuffle;

    /* *****************************************************************************************************************
     * Constructors
     **************************************************************************************************************** */

    private Puzzle(Size size, double percentage) {
        this.coin = Coin.create();
        this.size = size;
        this.percentage = percentage;
        this.shuffle = Shuffle.build().withSize(size);
    }

    private Puzzle(Size size, double percentage, Random random) {
        this.coin = Coin.create(random);
        this.size = size;
        this.percentage = percentage;
        this.shuffle = Shuffle.build().withSize(size).withRandom(random);
    }

    /* *****************************************************************************************************************
     * Public contract
     **************************************************************************************************************** */

    public Fields newPuzzle() {
        Fields filled = Fields.createFilled(size);
        Fields shuffled = shuffle.withFields(filled).shuffle();

        Set<Field> puzzleFields = shuffled.stream()
                .sorted()
                .map(field -> {
                    coin.flip(percentage);

                    if (coin.isOnHeads()) {
                        return field;
                    } else {
                        return field.forceEmptyValue();
                    }
                }).collect(Collectors.toUnmodifiableSet());

        ValuedFields puzzle = puzzleFields.stream()
                .filter(Field::isSolved)
                .collect(ValuedFields.collect(size));

        return puzzle.fields();
    }

    /* *****************************************************************************************************************
     * Factory methods and Builder
     **************************************************************************************************************** */

    public static Builder with(Size size) {
        return new Builder(size);
    }

    public static class Builder {
        private final Size size;
        private Long seed;
        private Double percentage = 0.25;

        private Builder(Size size) {
            this.size = size;
        }

        public Builder andSeed(long seed) {
            this.seed = seed;
            return this;
        }

        public Builder andPercentage(double percentage) {
            this.percentage = percentage;
            return this;
        }

        public Puzzle build() {
            if (seed == null) {
                return new Puzzle(size, percentage);
            } else {
                Random random = new Random(seed);
                return new Puzzle(size, percentage, random);
            }
        }
    }
}
