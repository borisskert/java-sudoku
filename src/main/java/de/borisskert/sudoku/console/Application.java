package de.borisskert.sudoku.console;

import java.util.Scanner;

public class Application {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int width = readWidth();
        int height = readHeight();
        Level level = readLevel();

        Game game = Game.builder()
                .width(width)
                .height(height)
                .level(level)
                .build();

        game.printPuzzle();
        game.printInfo();
        game.oneRound();

        while (!game.isSolved()) {
            game.printPuzzle();
            game.oneRound();
        }

        System.out.println("Solved!");
    }

    private static int readWidth() {
        int value;

        do {
            System.out.print("Width: ");
            value = scanner.nextInt();
        } while (value < 1 || value > 30);

        return value;
    }

    private static int readHeight() {
        int value;

        do {
            System.out.print("Height: ");
            value = scanner.nextInt();
        } while (value < 1 || value > 30);

        return value;
    }

    private static Level readLevel() {
        int level;

        do {
            System.out.print("Level (1=easy, 2=medium, 3=hard): ");
            level = scanner.nextInt();
        } while (level < 1 || level > 3);

        return Level.from(level);
    }
}
