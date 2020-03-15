package com.github.borisskert.sudoku;

import java.util.Random;

public class Coin {

    private final Random random;

    private Boolean isOnHeads;

    public Coin() {
        this.random = new Random();
    }

    public Coin(long seed) {
        this.random = new Random(seed);
    }

    public void flip() {
        isOnHeads = random.nextDouble() < 0.5;
    }

    public void flip(double probability) {
        if (probability > 0.0 && probability < 1.0) {
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
}
