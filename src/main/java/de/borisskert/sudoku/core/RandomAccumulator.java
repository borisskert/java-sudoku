package de.borisskert.sudoku.core;

import java.util.Random;
import java.util.function.BinaryOperator;

/**
 * Selects a random element from a {@link java.util.stream.Stream}
 *
 * @param <T> the element type of the {@link java.util.stream.Stream}
 */
class RandomAccumulator<T> implements BinaryOperator<T> {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final Coin coin;

    /* *****************************************************************************************************************
     * Constructor(s)
     **************************************************************************************************************** */

    private RandomAccumulator(Random random) {
        this.coin = Coin.create(random);
    }

    /* *****************************************************************************************************************
     * Implementation of BinaryOperator interface
     **************************************************************************************************************** */

    @Override
    public T apply(T value, T value2) {
        coin.flip();

        if (coin.isOnHeads()) {
            return value;
        } else {
            return value2;
        }
    }

    /* *****************************************************************************************************************
     * Factory methods
     **************************************************************************************************************** */

    public static <T> BinaryOperator<T> newInstance(long seed) {
        Random random = new Random(seed);
        return new RandomAccumulator<>(random);
    }

    public static <T> BinaryOperator<T> newInstance(Random random) {
        return new RandomAccumulator<>(random);
    }
}
