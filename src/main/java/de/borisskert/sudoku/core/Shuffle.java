package de.borisskert.sudoku.core;

import java.util.Random;

/**
 * Implements a shuffle algorithm to shuffle specified {@link Fields}
 */
final class Shuffle {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final RandomInteger random;
    private final Size size;
    private final Fields fields;

    /* *****************************************************************************************************************
     * Constructor(s)
     **************************************************************************************************************** */

    private Shuffle(Size size, Fields fields) {
        this.size = size;
        this.fields = fields;
        this.random = RandomInteger.instance();
    }

    private Shuffle(Size size, Fields fields, Random random) {
        this.size = size;
        this.fields = fields;
        this.random = RandomInteger.instance(random);
    }

    /* *****************************************************************************************************************
     * Public contract
     **************************************************************************************************************** */

    public Fields shuffle() {
        Fields shuffled = shuffleLines(fields);
        shuffled = shuffleColumns(shuffled);

        return shuffled;
    }

    /* *****************************************************************************************************************
     * Private methods
     **************************************************************************************************************** */

    private Fields shuffleLines(Fields fields) {
        int steps = steps();
        Fields shuffled = fields;

        for (int i = 0; i < steps; i++) {
            Tuple<AbsoluteCoordinates, AbsoluteCoordinates> randomLines = selectTwoRandomLines();

            int lineIndex = randomLines.getA().getY();
            int otherLineIndex = randomLines.getB().getY();

            shuffled = Swap.fields(shuffled).swapLines(lineIndex, otherLineIndex);
        }

        return shuffled;
    }

    private Fields shuffleColumns(Fields fields) {
        int steps = steps();
        Fields shuffled = fields;

        for (int i = 0; i < steps; i++) {
            Tuple<AbsoluteCoordinates, AbsoluteCoordinates> randomColumn = selectTwoRandomColumns();

            int columnIndex = randomColumn.getA().getX();
            int otherColumnIndex = randomColumn.getB().getX();

            shuffled = Swap.fields(shuffled).swapColumns(columnIndex, otherColumnIndex);
        }

        return shuffled;
    }

    private int steps() {
        int steps = size.getWidth() * size.getHeight();
        return steps * steps;
    }

    private Tuple<AbsoluteCoordinates, AbsoluteCoordinates> selectTwoRandomLines() {
        int range = size.getWidth() * size.getHeight();
        AbsoluteCoordinates line;
        AbsoluteCoordinates otherLine;

        do {
            int lineIndex = random.nextBetween(0, range);
            line = AbsoluteCoordinates.fromLineOnly(lineIndex);

            int otherLineIndex = random.randomBetweenExcept(0, range, lineIndex);
            otherLine = AbsoluteCoordinates.fromLineOnly(otherLineIndex);
        } while (!areLinesInSameSubGrid(line, otherLine));

        return Tuple.create(line).with(otherLine);
    }

    private Tuple<AbsoluteCoordinates, AbsoluteCoordinates> selectTwoRandomColumns() {
        int range = size.getWidth() * size.getHeight();
        AbsoluteCoordinates column;
        AbsoluteCoordinates otherColumn;

        do {
            int columnIndex = random.nextBetween(0, range);
            column = AbsoluteCoordinates.fromColumnOnly(columnIndex);

            int otherColumnIndex = random.randomBetweenExcept(0, range, columnIndex);
            otherColumn = AbsoluteCoordinates.fromColumnOnly(otherColumnIndex);
        } while (!areColumnsInSameSubGrid(column, otherColumn));

        return Tuple.create(column).with(otherColumn);
    }

    private boolean areLinesInSameSubGrid(AbsoluteCoordinates line, AbsoluteCoordinates otherLine) {
        SubGridCoordinates subGrid = line.subGrid(size);
        SubGridCoordinates otherSubGrid = otherLine.subGrid(size);

        return subGrid.getY() == otherSubGrid.getY();
    }

    private boolean areColumnsInSameSubGrid(AbsoluteCoordinates column, AbsoluteCoordinates otherColumn) {
        SubGridCoordinates subGrid = column.subGrid(size);
        SubGridCoordinates otherSubGrid = otherColumn.subGrid(size);

        return subGrid.getX() == otherSubGrid.getX();
    }

    /* *****************************************************************************************************************
     * Factories and Builder(s)
     **************************************************************************************************************** */

    public static Builder build() {
        return new Builder();
    }

    public static class Builder {
        private Size size;
        private Long seed;
        private Random random;

        public Builder withSeed(long seed) {
            this.seed = seed;
            return this;
        }

        public Builder withRandom(Random random) {
            this.random = random;
            return this;
        }

        public Builder withSize(Size size) {
            this.size = size;
            return this;
        }

        public Shuffle withFields(Fields fields) {
            if (random != null) {
                return new Shuffle(size, fields, random);
            } else if (seed == null) {
                return new Shuffle(size, fields);
            } else {
                Random random = new Random(seed);
                return new Shuffle(size, fields, random);
            }
        }
    }
}
