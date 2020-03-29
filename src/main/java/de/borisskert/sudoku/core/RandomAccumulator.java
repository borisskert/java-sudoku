package de.borisskert.sudoku.core;

import java.util.Random;
import java.util.function.BinaryOperator;

class RandomAccumulator<T> implements BinaryOperator<T> {
    private final Coin coin;

    private RandomAccumulator(Random random) {
        this.coin = Coin.create(random);
    }

    @Override
    public T apply(T value, T value2) {
        coin.flip();

        if (coin.isOnHeads()) {
            return value;
        } else {
            return value2;
        }
    }

    public static <T> BinaryOperator<T> newInstance(long seed) {
        Random random = new Random(seed);
        return new RandomAccumulator<>(random);
    }

    public static <T> BinaryOperator<T> newInstance(Random random) {
        return new RandomAccumulator<>(random);
    }
}
