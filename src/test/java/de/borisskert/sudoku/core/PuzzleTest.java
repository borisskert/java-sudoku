package de.borisskert.sudoku.core;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class PuzzleTest {

    @Test
    public void shouldCreatePuzzle22() throws Exception {
        Size size = Size.of(2, 2);
        Fields puzzled = Puzzle.with(size)
                .andSeed(123L)
                .build()
                .newPuzzle();

        // @formatter:off
        String expected =
                "╔═╤═╦═╤═╗\n" +
                "║ │ ║ │1║\n" +
                "╟─┼─╫─┼─╢\n" +
                "║ │ ║ │3║\n" +
                "╠═╪═╬═╪═╣\n" +
                "║1│4║ │ ║\n" +
                "╟─┼─╫─┼─╢\n" +
                "║ │ ║ │4║\n" +
                "╚═╧═╩═╧═╝\n";
        // @formatter:on
        assertThat(Print.format(size, puzzled), is(equalTo(expected)));
    }

    @Test
    public void shouldCreatePuzzle33() throws Exception {
        Size size = Size.of(3, 3);
        Fields puzzled = Puzzle.with(size)
                .andSeed(123L)
                .build()
                .newPuzzle();

        // @formatter:off
        String expected =
                "╔═╤═╤═╦═╤═╤═╦═╤═╤═╗\n" +
                "║ │ │ ║ │ │5║ │8│7║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║ │6│ ║ │ │ ║ │ │ ║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║ │ │ ║ │ │2║ │5│ ║\n" +
                "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                "║ │ │ ║ │ │ ║4│ │ ║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║3│ │ ║ │5│ ║ │ │ ║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║9│1│ ║ │2│ ║7│6│ ║\n" +
                "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                "║ │8│6║ │ │ ║ │ │3║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║ │ │ ║ │ │ ║ │ │6║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║ │ │ ║ │ │ ║2│ │ ║\n" +
                "╚═╧═╧═╩═╧═╧═╩═╧═╧═╝\n";
        // @formatter:on
        assertThat(Print.format(size, puzzled), is(equalTo(expected)));
    }

    @Test
    public void shouldCreatePuzzle43() throws Exception {
        Size size = Size.of(4, 3);
        Fields puzzled = Puzzle.with(size)
                .andSeed(123L)
                .build()
                .newPuzzle();

        // @formatter:off
        String expected =
                "╔══╤══╤══╤══╦══╤══╤══╤══╦══╤══╤══╤══╗\n" +
                "║  │  │  │ 8║  │12│  │  ║  │  │ 3│  ║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║  │10│  │  ║ 1│  │  │  ║  │  │ 7│  ║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║  │ 2│  │  ║  │ 8│  │  ║  │  │  │ 9║\n" +
                "╠══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╣\n" +
                "║ 8│  │  │  ║  │  │  │  ║  │  │  │ 2║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║  │  │  │  ║  │ 5│ 4│  ║  │ 9│  │  ║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║  │ 3│  │  ║ 6│ 9│  │  ║  │  │  │  ║\n" +
                "╠══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╣\n" +
                "║  │  │ 3│  ║  │  │  │  ║  │  │  │  ║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║ 1│  │11│  ║ 3│ 6│  │  ║  │  │  │  ║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║ 9│  │  │  ║  │  │  │  ║  │ 6│  │ 3║\n" +
                "╠══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╣\n" +
                "║ 6│  │ 4│ 7║ 8│  │10│  ║  │  │  │  ║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║  │  │  │  ║  │  │  │  ║  │  │  │  ║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║  │  │  │  ║12│ 3│ 2│  ║  │  │  │  ║\n" +
                "╚══╧══╧══╧══╩══╧══╧══╧══╩══╧══╧══╧══╝\n";
        // @formatter:on
        assertThat(Print.format(size, puzzled), is(equalTo(expected)));
    }

    @Test
    public void shouldSolveCreatedPuzzle43() throws Exception {
        Size size = Size.of(4, 3);
        Fields puzzle = Puzzle.with(size)
                .andSeed(123L)
                // TODO solve with less percentage
                .andPercentage(0.50)
                .build()
                .newPuzzle();

        Fields solved = Solve.withSize(size).andSeed(123L).andFields(puzzle).solve();

        // @formatter:off
        String expected =
                "╔══╤══╤══╤══╦══╤══╤══╤══╦══╤══╤══╤══╗\n" +
                "║ 7│ 6│ 5│ 8║ 9│12│11│10║ 2│ 4│ 3│ 1║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║11│10│ 9│12║ 1│ 4│ 3│ 2║ 6│ 8│ 7│ 5║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║ 3│ 2│ 1│ 4║ 5│ 8│ 7│ 6║10│12│11│ 9║\n" +
                "╠══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╣\n" +
                "║ 8│ 7│ 6│ 9║10│ 1│12│11║ 3│ 5│ 4│ 2║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║12│11│10│ 1║ 2│ 5│ 4│ 3║ 7│ 9│ 8│ 6║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║ 4│ 3│ 2│ 5║ 6│ 9│ 8│ 7║11│ 1│12│10║\n" +
                "╠══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╣\n" +
                "║ 5│ 4│ 3│ 6║ 7│10│ 9│ 8║12│ 2│ 1│11║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║ 1│ 8│11│ 2║ 3│ 6│ 5│12║ 4│10│ 9│ 7║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║ 9│12│ 7│10║11│ 2│ 1│ 4║ 8│ 6│ 5│ 3║\n" +
                "╠══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╣\n" +
                "║ 6│ 5│ 4│ 7║ 8│11│10│ 9║ 1│ 3│ 2│12║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║ 2│ 1│12│ 3║ 4│ 7│ 6│ 5║ 9│11│10│ 8║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║10│ 9│ 8│11║12│ 3│ 2│ 1║ 5│ 7│ 6│ 4║\n" +
                "╚══╧══╧══╧══╩══╧══╧══╧══╩══╧══╧══╧══╝\n";
        // @formatter:on
        assertThat(Print.format(size, solved), is(equalTo(expected)));
    }
}