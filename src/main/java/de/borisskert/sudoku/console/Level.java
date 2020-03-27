package de.borisskert.sudoku.console;

public enum Level {
    EASY(0.7),
    MEDIUM(0.33),
    HARD(0.20);

    private final double percentage;

    Level(double percentage) {
        this.percentage = percentage;
    }

    public static Level from(int level) {
        if (level == 1) {
            return EASY;
        } else if (level == 2) {
            return MEDIUM;
        }

        return HARD;
    }

    public double toPercentage() {
        return percentage;
    }
}
