package de.borisskert.sudoku.core;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SuppressWarnings("GrazieInspection")
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
                "║ │ ║ │3║\n" +
                "╟─┼─╫─┼─╢\n" +
                "║1│ ║ │ ║\n" +
                "╠═╪═╬═╪═╣\n" +
                "║ │4║ │1║\n" +
                "╟─┼─╫─┼─╢\n" +
                "║ │ ║ │ ║\n" +
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
                "║ │ │ ║4│ │2║9│ │ ║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║ │ │3║ │ │ ║ │5│ ║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║ │ │6║ │ │ ║4│ │ ║\n" +
                "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                "║4│6│ ║ │1│9║ │ │5║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║ │ │ ║6│ │4║8│ │ ║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║ │8│ ║ │5│7║ │ │2║\n" +
                "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                "║ │ │ ║ │ │ ║ │ │ ║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║ │5│ ║ │7│ ║1│ │ ║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║3│ │ ║ │ │ ║ │ │ ║\n" +
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
                "║  │ 6│  │  ║  │  │ 5│  ║  │  │  │  ║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║  │  │  │  ║ 2│  │10│  ║ 6│11│  │  ║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║  │  │ 2│  ║  │  │  │  ║  │  │ 5│  ║\n" +
                "╠══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╣\n" +
                "║  │ 3│  │ 7║ 9│ 2│  │  ║  │ 1│  │ 4║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║12│  │  │  ║  │  │  │  ║  │  │  │  ║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║  │10│ 4│ 1║  │ 7│  │  ║  │  │  │ 9║\n" +
                "╠══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╣\n" +
                "║  │ 8│10│  ║  │  │  │  ║12│  │  │  ║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║  │12│  │  ║  │ 6│ 1│  ║  │  │  │  ║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║ 7│  │  │  ║ 5│  │ 2│  ║ 8│  │  │10║\n" +
                "╠══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╣\n" +
                "║ 6│  │  │  ║11│  │  │  ║ 2│  │  │  ║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║  │  │  │  ║  │  │  │ 1║  │  │  │  ║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║ 9│  │  │ 5║  │  │  │  ║  │  │  │  ║\n" +
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
                "║ 3│ 6│ 1│11║ 7│12│ 5│ 9║ 4│ 8│10│ 2║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║ 5│ 9│ 7│12║ 2│ 8│10│ 4║ 6│11│ 3│ 1║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║10│ 4│ 2│ 8║ 1│11│ 3│ 6║ 9│12│ 5│ 7║\n" +
                "╠══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╣\n" +
                "║11│ 3│ 6│ 7║ 9│ 2│12│ 5║10│ 1│ 8│ 4║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║12│ 5│ 9│ 2║ 4│ 1│ 8│10║ 3│ 7│11│ 6║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║ 8│10│ 4│ 1║ 6│ 7│11│ 3║ 5│ 2│12│ 9║\n" +
                "╠══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╣\n" +
                "║ 1│ 8│10│ 6║ 3│ 9│ 7│11║12│ 4│ 2│ 5║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║ 2│12│ 5│ 4║10│ 6│ 1│ 8║11│ 9│ 7│ 3║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║ 7│11│ 3│ 9║ 5│ 4│ 2│12║ 8│ 6│ 1│10║\n" +
                "╠══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╣\n" +
                "║ 6│ 1│ 8│ 3║11│ 5│ 9│ 7║ 2│10│ 4│12║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║ 4│ 2│12│10║ 8│ 3│ 6│ 1║ 7│ 5│ 9│11║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║ 9│ 7│11│ 5║12│10│ 4│ 2║ 1│ 3│ 6│ 8║\n" +
                "╚══╧══╧══╧══╩══╧══╧══╧══╩══╧══╧══╧══╝\n";
        // @formatter:on
        assertThat(Print.format(size, solved), is(equalTo(expected)));
    }
}
