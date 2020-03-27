package de.borisskert.sudoku.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class SolveTest {

    private static final long SEED = 123L;

    @Nested
    class WithinSize22 {
        private Size size;

        @BeforeEach
        public void setup() throws Exception {
            size = Size.of(2, 2);
        }

        @Test
        public void shouldSolveEasy() throws Exception {
            Fields puzzle = Puzzle.with(size)
                    .andSeed(SEED)
                    .andPercentage(0.33)
                    .build()
                    .newPuzzle();

            Fields solved = Solve.withSize(size)
                    .andSeed(SEED)
                    .andFields(puzzle)
                    .solve();

            // @formatter:off
            String expected =
                    "╔═╤═╦═╤═╗\n" +
                    "║4│3║2│1║\n" +
                    "╟─┼─╫─┼─╢\n" +
                    "║2│1║4│3║\n" +
                    "╠═╪═╬═╪═╣\n" +
                    "║1│4║3│2║\n" +
                    "╟─┼─╫─┼─╢\n" +
                    "║3│2║1│4║\n" +
                    "╚═╧═╩═╧═╝\n";
            // @formatter:on
            assertThat(Formatter.format(size, solved), is(equalTo(expected)));
        }

        @Test
        public void shouldSolveMedium() throws Exception {
            Fields puzzle = Puzzle.with(size)
                    .andSeed(SEED)
                    .andPercentage(0.30)
                    .build()
                    .newPuzzle();

            Fields solved = Solve.withSize(size)
                    .andSeed(SEED)
                    .andFields(puzzle)
                    .solve();

            // @formatter:off
            String expected =
                    "╔═╤═╦═╤═╗\n" +
                    "║4│3║2│1║\n" +
                    "╟─┼─╫─┼─╢\n" +
                    "║2│1║4│3║\n" +
                    "╠═╪═╬═╪═╣\n" +
                    "║1│4║3│2║\n" +
                    "╟─┼─╫─┼─╢\n" +
                    "║3│2║1│4║\n" +
                    "╚═╧═╩═╧═╝\n";
            // @formatter:on
            assertThat(Formatter.format(size, solved), is(equalTo(expected)));
        }

        @Test
        public void shouldSolveHarder() throws Exception {
            Fields puzzle = Puzzle.with(size)
                    .andSeed(SEED)
                    .andPercentage(0.20)
                    .build()
                    .newPuzzle();

            Fields solved = Solve.withSize(size)
                    .andSeed(SEED)
                    .andFields(puzzle)
                    .solve();

            // @formatter:off
            String expected =
                    "╔═╤═╦═╤═╗\n" +
                    "║4│3║2│1║\n" +
                    "╟─┼─╫─┼─╢\n" +
                    "║2│1║4│3║\n" +
                    "╠═╪═╬═╪═╣\n" +
                    "║1│4║3│2║\n" +
                    "╟─┼─╫─┼─╢\n" +
                    "║3│2║1│4║\n" +
                    "╚═╧═╩═╧═╝\n";
            // @formatter:on
            assertThat(Formatter.format(size, solved), is(equalTo(expected)));
        }

        @Test
        public void shouldSolveHardest() throws Exception {
            Fields puzzle = Puzzle.with(size)
                    .andSeed(SEED)
                    .andPercentage(0.11)
                    .build()
                    .newPuzzle();

            Fields solved = Solve.withSize(size)
                    .andSeed(SEED)
                    .andFields(puzzle)
                    .solve();

            // @formatter:off
            String expected =
                    "╔═╤═╦═╤═╗\n" +
                    "║2│1║4│3║\n" +
                    "╟─┼─╫─┼─╢\n" +
                    "║4│3║2│1║\n" +
                    "╠═╪═╬═╪═╣\n" +
                    "║1│4║3│2║\n" +
                    "╟─┼─╫─┼─╢\n" +
                    "║3│2║1│4║\n" +
                    "╚═╧═╩═╧═╝\n";
            // @formatter:on
            assertThat(Formatter.format(size, solved), is(equalTo(expected)));
        }

        @Test
        public void shouldSolveUltraHard() throws Exception {
            Fields puzzle = Puzzle.with(size)
                    .andSeed(SEED)
                    .andPercentage(0.00001)
                    .build()
                    .newPuzzle();

            Fields solved = Solve.withSize(size)
                    .andSeed(SEED)
                    .andFields(puzzle)
                    .solve();

            // @formatter:off
            String expected =
                    "╔═╤═╦═╤═╗\n" +
                    "║4│1║2│3║\n" +
                    "╟─┼─╫─┼─╢\n" +
                    "║2│3║4│1║\n" +
                    "╠═╪═╬═╪═╣\n" +
                    "║3│4║1│2║\n" +
                    "╟─┼─╫─┼─╢\n" +
                    "║1│2║3│4║\n" +
                    "╚═╧═╩═╧═╝\n";
            // @formatter:on
            assertThat(Formatter.format(size, solved), is(equalTo(expected)));
        }
    }

    @Nested
    class WithinSize33 {
        private Size size;

        @BeforeEach
        public void setup() throws Exception {
            size = Size.of(3, 3);
        }

        @Test
        public void shouldSolveEasy() throws Exception {
            Fields puzzle = Puzzle.with(size)
                    .andSeed(SEED)
                    .andPercentage(0.35)
                    .build()
                    .newPuzzle();

            Fields solved = Solve.withSize(size)
                    .andSeed(SEED)
                    .andFields(puzzle)
                    .solve();

            // @formatter:off
            String expected =
                    "╔═╤═╤═╦═╤═╤═╦═╤═╤═╗\n" +
                    "║2│4│3║9│6│5║1│8│7║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║5│6│1║7│4│8║3│2│9║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║8│9│7║3│1│2║6│5│4║\n" +
                    "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                    "║6│2│8║1│7│9║4│3│5║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║3│7│4║6│5│8║9│1│2║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║9│1│5║4│2│3║7│6│8║\n" +
                    "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                    "║1│8│6║2│9│7║5│4│3║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║7│3│2║5│1│4║8│9│6║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║4│5│9║8│3│6║2│7│1║\n" +
                    "╚═╧═╧═╩═╧═╧═╩═╧═╧═╝\n";
            // @formatter:on
            assertThat(Formatter.format(size, solved), is(equalTo(expected)));
        }

        @Test
        public void shouldSolveMedium() throws Exception {
            Fields puzzle = Puzzle.with(size)
                    .andSeed(SEED)
                    .andPercentage(0.20)
                    .build()
                    .newPuzzle();

            Fields solved = Solve.withSize(size)
                    .andSeed(SEED)
                    .andFields(puzzle)
                    .solve();

            // @formatter:off
            String expected =
                    "╔═╤═╤═╦═╤═╤═╦═╤═╤═╗\n" +
                    "║2│3│9║1│6│5║8│4│7║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║5│6│7║4│3│8║9│2│1║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║1│4│8║7│9│2║3│5│6║\n" +
                    "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                    "║6│8│2║9│1│7║4│3│5║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║3│7│4║8│5│6║1│9│2║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║9│1│5║3│2│4║7│6│8║\n" +
                    "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                    "║8│9│6║2│4│1║5│7│3║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║7│2│3║5│8│9║6│1│4║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║4│5│1║6│7│3║2│8│9║\n" +
                    "╚═╧═╧═╩═╧═╧═╩═╧═╧═╝\n";
            // @formatter:on
            assertThat(Formatter.format(size, solved), is(equalTo(expected)));
        }

        @Test
        public void shouldSolveHard() throws Exception {
            Fields puzzle = Puzzle.with(size)
                    .andSeed(SEED)
                    .andPercentage(0.05)
                    .build()
                    .newPuzzle();

            Fields solved = Solve.withSize(size)
                    .andSeed(SEED)
                    .andFields(puzzle)
                    .solve();

            // @formatter:off
            String expected =
                    "╔═╤═╤═╦═╤═╤═╦═╤═╤═╗\n" +
                    "║2│1│3║4│8│5║6│9│7║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║7│5│6║9│2│1║8│3│4║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║4│8│9║6│7│3║2│1│5║\n" +
                    "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                    "║5│9│1║8│6│4║7│2│3║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║3│4│8║7│5│2║1│6│9║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║6│2│7║3│1│9║4│5│8║\n" +
                    "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                    "║9│3│2║1│4│7║5│8│6║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║8│7│5║2│3│6║9│4│1║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║1│6│4║5│9│8║3│7│2║\n" +
                    "╚═╧═╧═╩═╧═╧═╩═╧═╧═╝\n";
            // @formatter:on
            assertThat(Formatter.format(size, solved), is(equalTo(expected)));
        }

        @Test
        public void shouldSolveHarder() throws Exception {
            Fields puzzle = Puzzle.with(size)
                    .andSeed(SEED)
                    .andPercentage(0.01)
                    .build()
                    .newPuzzle();

            Fields solved = Solve.withSize(size)
                    .andSeed(SEED)
                    .andFields(puzzle)
                    .solve();

            // @formatter:off
            String expected =
                    "╔═╤═╤═╦═╤═╤═╦═╤═╤═╗\n" +
                    "║6│2│5║9│4│3║7│8│1║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║1│4│8║5│2│7║6│9│3║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║3│7│9║6│8│1║5│4│2║\n" +
                    "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                    "║9│5│3║4│7│8║2│1│6║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║4│6│7║1│5│2║9│3│8║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║8│1│2║3│9│6║4│5│7║\n" +
                    "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                    "║5│8│6║7│1│9║3│2│4║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║7│9│1║2│3│4║8│6│5║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║2│3│4║8│6│5║1│7│9║\n" +
                    "╚═╧═╧═╩═╧═╧═╩═╧═╧═╝\n";
            // @formatter:on
            assertThat(Formatter.format(size, solved), is(equalTo(expected)));
        }
    }
}