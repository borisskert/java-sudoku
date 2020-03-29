package de.borisskert.sudoku.core;

import java.util.Random;

class RandomInteger {

    private final Random random;

    private RandomInteger(Random random) {
        this.random = random;
    }

    public final int nextBetween(int min, int max) {
        int range = max - min;
        int random = this.random.nextInt();

        int normalized = Math.floorMod(random, range);

        return normalized + min;
    }

    public final int randomBetweenExcept(int min, int max, int... except) {
        int candidate;
        do {
            candidate = nextBetween(min, max);
        } while (contains(except, candidate));

        return candidate;
    }

    private boolean contains(int[] array, int element) {
        for (int e : array) {
            if (e == element) {
                return true;
            }
        }

        return false;
    }

    public static RandomInteger instance() {
        return new RandomInteger(new Random());
    }

    public static RandomInteger instance(Random random) {
        return new RandomInteger(random);
    }
}
