package de.borisskert.sudoku.core;

import java.util.Random;

class Coin {

    private final Random random;

    private Boolean isOnHeads;

    private Coin(Random random) {
        this.random = random;
    }

    public void flip() {
        isOnHeads = random.nextDouble() < 0.5;
    }

    public void flip(double probability) {
        if (0.0 < probability && probability < 1.0) {
            isOnHeads = random.nextDouble() < probability;
        } else {
            throw new IllegalArgumentException("Parameter 'probability' must be between 0.0 and 1.0 (both exclusive)");
        }
    }

    public boolean isOnHeads() {
        if (isOnHeads == null) {
            throw new IllegalStateException("Coin must be flipped");
        }

        return isOnHeads;
    }

    public static Coin create() {
        Random random = new Random();
        return new Coin(random);
    }

    public static Coin create(Random random) {
        return new Coin(random);
    }

    public static Coin create(long seed) {
        Random random = new Random(seed);
        return new Coin(random);
    }
}
