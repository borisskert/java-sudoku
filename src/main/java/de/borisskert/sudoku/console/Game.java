package de.borisskert.sudoku.console;

import de.borisskert.sudoku.core.Sudoku;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {

    private static final Scanner scanner = new Scanner(System.in);

    private final int width;
    private final int height;
    private final Sudoku puzzle;

    private Game(int width, int height, Sudoku puzzle) {
        this.width = width;
        this.height = height;
        this.puzzle = puzzle;
    }

    public void printInfo() {
        System.out.println("Columns: right to left, 1 to " + width * height);
        System.out.println("Lines: top to bottom, 1 to " + width * height);
        System.out.println("Values: 1 to " + width * height);
        System.out.println("Type in 'SOLVE' to solve the puzzle");
    }

    public void printPuzzle() {
        System.out.println(puzzle.toString());
    }

    public void oneRound() {
        boolean isInvalid;

        try {
            do {
                int x = readColumn();
                int y = readLine();
                int value = readValue();

                try {
                    puzzle.set(x - 1, y - 1, value);
                    isInvalid = false;
                } catch (IllegalStateException e) {
                    System.out.println("Illegal move!");
                    isInvalid = true;
                }
            } while (isInvalid);
        } catch (GameAbortException e) {
            System.out.println("Solving...");
            puzzle.solve();
        }

        printPuzzle();
    }

    public boolean isSolved() {
        return puzzle.isSolved();
    }

    private int readColumn() {
        int column;

        do {
            System.out.print("Column: ");
            column = readInt();
        } while (column < 1 || column > width * height);

        return column;
    }

    Pattern intPattern = Pattern.compile("^[0-9]+$");

    private int readInt() {
        Matcher matcher;
        Integer inputAsInt = null;

        do {
            String input = scanner.next();

            matcher = intPattern.matcher(input);
            if (matcher.matches()) {
                inputAsInt = Integer.parseInt(input);
            } else {
                if (Objects.equals(input, "SOLVE")) {
                    throw new GameAbortException();
                }
            }
        } while (inputAsInt == null);

        return inputAsInt;
    }

    private int readLine() {
        int line;

        do {
            System.out.print("Line: ");
            line = readInt();
        } while (line < 1 || line > width * height);

        return line;
    }

    private int readValue() {
        int value;

        do {
            System.out.print("Value: ");
            value = readInt();
        } while (value < 1 || value > width * height);

        return value;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int width;
        private int height;
        private Level level = Level.EASY;

        private Builder() {
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder level(Level level) {
            this.level = level;
            return this;
        }

        public Game build() {
            System.out.println("Creating puzzle...");

            Sudoku puzzle = Sudoku.createPuzzle(width, height, level.toPercentage());

            return new Game(width, height, puzzle);
        }
    }

    private static class GameAbortException extends RuntimeException {
    }
}
