package de.borisskert.sudoku.core;

import java.util.Random;

/**
 * Utility to generate random {@link Integer}s withing specific ranges.
 */
final class RandomInteger {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final Random random;

    /* *****************************************************************************************************************
     * Constructor(s)
     **************************************************************************************************************** */

    private RandomInteger(Random random) {
        this.random = random;
    }

    /* *****************************************************************************************************************
     * Public contract
     **************************************************************************************************************** */

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

    /* *****************************************************************************************************************
     * Private methods
     **************************************************************************************************************** */

    private boolean contains(int[] array, int element) {
        for (int e : array) {
            if (e == element) {
                return true;
            }
        }

        return false;
    }

    /* *****************************************************************************************************************
     * Factory method(s)
     **************************************************************************************************************** */

    public static RandomInteger instance() {
        return new RandomInteger(new Random());
    }

    public static RandomInteger instance(Random random) {
        return new RandomInteger(random);
    }
}
