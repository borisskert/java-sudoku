package de.borisskert.sudoku.core;

import java.util.function.BinaryOperator;

class RandomAccumulator<T> implements BinaryOperator<T> {
    private final Coin coin;

    private RandomAccumulator() {
        this.coin = new Coin();
    }

    private RandomAccumulator(long seed) {
        this.coin = new Coin(seed);
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
        return new RandomAccumulator<>(seed);
    }

    public static <T> BinaryOperator<T> newInstance() {
        return new RandomAccumulator<>();
    }
}
