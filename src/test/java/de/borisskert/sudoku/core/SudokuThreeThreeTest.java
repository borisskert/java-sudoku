package de.borisskert.sudoku.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Use this example for tests
 * https://commons.wikimedia.org/wiki/File:Sudoku_Puzzle_by_L2G-20050714_standardized_layout.svg#/media/File:Sudoku_Puzzle_by_L2G-20050714_standardized_layout.svg
 */
@DisplayName("Sudoku (3/3)")
class SudokuThreeThreeTest {

    private Sudoku sudoku;

    @BeforeEach
    public void setup() throws Exception {
        sudoku = Sudoku.create(3, 3);
    }

    @Test
    public void shouldPrintFields() throws Exception {
        // @formatter:off
        String expected =
                "╔═╤═╤═╦═╤═╤═╦═╤═╤═╗\n" +
                "║ │ │ ║ │ │ ║ │ │ ║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║ │ │ ║ │ │ ║ │ │ ║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║ │ │ ║ │ │ ║ │ │ ║\n" +
                "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                "║ │ │ ║ │ │ ║ │ │ ║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║ │ │ ║ │ │ ║ │ │ ║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║ │ │ ║ │ │ ║ │ │ ║\n" +
                "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                "║ │ │ ║ │ │ ║ │ │ ║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║ │ │ ║ │ │ ║ │ │ ║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║ │ │ ║ │ │ ║ │ │ ║\n" +
                "╚═╧═╧═╩═╧═╧═╩═╧═╧═╝\n";
        // @formatter:on
        assertThat(sudoku.toString(), is(equalTo(expected)));
    }

    @DisplayName("setup middle sub-grid")
    @Nested
    class SetMiddleSubGridExampleNumbers {
        @BeforeEach
        public void setup() throws Exception {
            sudoku.set(4, 3, 6);
            sudoku.set(3, 4, 8);
            sudoku.set(5, 4, 3);
            sudoku.set(4, 5, 2);
        }

        @Test
        public void shouldPrintFields() throws Exception {
            // @formatter:off
            String expected =
                    "╔═╤═╤═╦═╤═╤═╦═╤═╤═╗\n" +
                    "║ │ │ ║ │ │ ║ │ │ ║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║ │ │ ║ │ │ ║ │ │ ║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║ │ │ ║ │ │ ║ │ │ ║\n" +
                    "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                    "║ │ │ ║ │6│ ║ │ │ ║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║ │ │ ║8│ │3║ │ │ ║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║ │ │ ║ │2│ ║ │ │ ║\n" +
                    "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                    "║ │ │ ║ │ │ ║ │ │ ║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║ │ │ ║ │ │ ║ │ │ ║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║ │ │ ║ │ │ ║ │ │ ║\n" +
                    "╚═╧═╧═╩═╧═╧═╩═╧═╧═╝\n";
            // @formatter:on
            assertThat(sudoku.toString(), is(equalTo(expected)));
        }

        @DisplayName("setup right center sub-grid")
        @Nested
        class SetupRightCenterSubGrid {
            @BeforeEach
            public void setup() throws Exception {
                sudoku.set(8, 3, 3);
                sudoku.set(8, 4, 1);
                sudoku.set(8, 5, 6);
            }

            @Test
            public void shouldPrintFields() throws Exception {
                // @formatter:off
                String expected =
                        "╔═╤═╤═╦═╤═╤═╦═╤═╤═╗\n" +
                        "║ │ │ ║ │ │ ║ │ │ ║\n" +
                        "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                        "║ │ │ ║ │ │ ║ │ │ ║\n" +
                        "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                        "║ │ │ ║ │ │ ║ │ │ ║\n" +
                        "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                        "║ │ │ ║ │6│ ║ │ │3║\n" +
                        "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                        "║ │ │ ║8│ │3║ │ │1║\n" +
                        "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                        "║ │ │ ║ │2│ ║ │ │6║\n" +
                        "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                        "║ │ │ ║ │ │ ║ │ │ ║\n" +
                        "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                        "║ │ │ ║ │ │ ║ │ │ ║\n" +
                        "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                        "║ │ │ ║ │ │ ║ │ │ ║\n" +
                        "╚═╧═╧═╩═╧═╧═╩═╧═╧═╝\n";
                // @formatter:on
                assertThat(sudoku.toString(), is(equalTo(expected)));
            }

            @DisplayName("setup bottom (middle) sub-grid")
            @Nested
            class SetupBottomSubGrid {
                @BeforeEach
                public void setup() throws Exception {
                    sudoku.set(3, 7, 4);
                    sudoku.set(4, 7, 1);
                    sudoku.set(5, 7, 9);
                    sudoku.set(4, 8, 8);
                }

                @Test
                public void shouldPrintFields() throws Exception {
                    // @formatter:off
                    String expected =
                            "╔═╤═╤═╦═╤═╤═╦═╤═╤═╗\n" +
                            "║ │ │ ║ │ │ ║ │ │ ║\n" +
                            "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                            "║ │ │ ║ │ │ ║ │ │ ║\n" +
                            "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                            "║ │ │ ║ │ │ ║ │ │ ║\n" +
                            "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                            "║ │ │ ║ │6│ ║ │ │3║\n" +
                            "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                            "║ │ │ ║8│ │3║ │ │1║\n" +
                            "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                            "║ │ │ ║ │2│ ║ │ │6║\n" +
                            "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                            "║ │ │ ║ │ │ ║ │ │ ║\n" +
                            "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                            "║ │ │ ║4│1│9║ │ │ ║\n" +
                            "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                            "║ │ │ ║ │8│ ║ │ │ ║\n" +
                            "╚═╧═╧═╩═╧═╧═╩═╧═╧═╝\n";
                    // @formatter:on
                    assertThat(sudoku.toString(), is(equalTo(expected)));
                }

                @DisplayName("setup top left sub-grid")
                @Nested
                class SetupTopLeftSubGrid {
                    @BeforeEach
                    public void setup() throws Exception {
                        sudoku.set(0, 0, 5);
                        sudoku.set(1, 0, 3);
                        sudoku.set(0, 1, 6);
                        sudoku.set(1, 2, 9);
                        sudoku.set(2, 2, 8);
                    }

                    @Test
                    public void shouldPrintFields() throws Exception {
                        // @formatter:off
                        String expected =
                                "╔═╤═╤═╦═╤═╤═╦═╤═╤═╗\n" +
                                "║5│3│ ║ │ │ ║ │ │ ║\n" +
                                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                "║6│ │ ║ │ │ ║ │ │ ║\n" +
                                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                "║ │9│8║ │ │ ║ │ │ ║\n" +
                                "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                                "║ │ │ ║ │6│ ║ │ │3║\n" +
                                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                "║ │ │ ║8│ │3║ │ │1║\n" +
                                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                "║ │ │ ║ │2│ ║ │ │6║\n" +
                                "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                                "║ │ │ ║ │ │ ║ │ │ ║\n" +
                                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                "║ │ │ ║4│1│9║ │ │ ║\n" +
                                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                "║ │ │ ║ │8│ ║ │ │ ║\n" +
                                "╚═╧═╧═╩═╧═╧═╩═╧═╧═╝\n";
                        // @formatter:on
                        assertThat(sudoku.toString(), is(equalTo(expected)));
                    }

                    @DisplayName("Setup top right sub-grid")
                    @Nested
                    class SetupTopRightSubGrid {
                        @BeforeEach
                        public void setup() throws Exception {
                            sudoku.set(7, 2, 6);
                        }

                        @Test
                        public void shouldPrintFields() throws Exception {
                            // @formatter:off
                            String expected =
                                    "╔═╤═╤═╦═╤═╤═╦═╤═╤═╗\n" +
                                    "║5│3│ ║ │ │ ║ │ │ ║\n" +
                                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                    "║6│ │ ║ │ │ ║ │ │ ║\n" +
                                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                    "║ │9│8║ │ │ ║ │6│ ║\n" +
                                    "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                                    "║ │ │ ║ │6│ ║ │ │3║\n" +
                                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                    "║ │ │ ║8│ │3║ │ │1║\n" +
                                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                    "║ │ │ ║ │2│ ║ │ │6║\n" +
                                    "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                                    "║ │ │ ║ │ │ ║ │ │ ║\n" +
                                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                    "║ │ │ ║4│1│9║ │ │ ║\n" +
                                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                    "║ │ │ ║ │8│ ║ │ │ ║\n" +
                                    "╚═╧═╧═╩═╧═╧═╩═╧═╧═╝\n";
                            // @formatter:on
                            assertThat(sudoku.toString(), is(equalTo(expected)));
                        }

                        @DisplayName("setup bottom left sub-grid")
                        @Nested
                        class SetupBottomLeftSubGrid {
                            @BeforeEach
                            public void setup() throws Exception {
                                sudoku.set(1, 6, 6);
                            }

                            @Test
                            public void shouldPrintFields() throws Exception {
                                // @formatter:off
                                String expected =
                                        "╔═╤═╤═╦═╤═╤═╦═╤═╤═╗\n" +
                                        "║5│3│ ║ │ │ ║ │ │ ║\n" +
                                        "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                        "║6│ │ ║ │ │ ║ │ │ ║\n" +
                                        "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                        "║ │9│8║ │ │ ║ │6│ ║\n" +
                                        "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                                        "║ │ │ ║ │6│ ║ │ │3║\n" +
                                        "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                        "║ │ │ ║8│ │3║ │ │1║\n" +
                                        "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                        "║ │ │ ║ │2│ ║ │ │6║\n" +
                                        "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                                        "║ │6│ ║ │ │ ║ │ │ ║\n" +
                                        "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                        "║ │ │ ║4│1│9║ │ │ ║\n" +
                                        "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                        "║ │ │ ║ │8│ ║ │ │ ║\n" +
                                        "╚═╧═╧═╩═╧═╧═╩═╧═╧═╝\n";
                                // @formatter:on
                                assertThat(sudoku.toString(), is(equalTo(expected)));
                            }

                            @DisplayName("Setup top (middle) sub-grid")
                            @Nested
                            class SetupTopSubGrid {
                                @BeforeEach
                                public void setup() throws Exception {
                                    sudoku.set(4, 0, 7);
                                    sudoku.set(3, 1, 1);
                                    sudoku.set(4, 1, 9);
                                    sudoku.set(5, 1, 5);
                                }

                                @Test
                                public void shouldPrintFields() throws Exception {
                                    // @formatter:off
                                    String expected =
                                            "╔═╤═╤═╦═╤═╤═╦═╤═╤═╗\n" +
                                            "║5│3│ ║ │7│ ║ │ │ ║\n" +
                                            "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                            "║6│ │ ║1│9│5║ │ │ ║\n" +
                                            "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                            "║ │9│8║ │ │ ║ │6│ ║\n" +
                                            "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                                            "║ │ │ ║ │6│ ║ │ │3║\n" +
                                            "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                            "║ │ │ ║8│ │3║ │ │1║\n" +
                                            "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                            "║ │ │ ║ │2│ ║ │ │6║\n" +
                                            "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                                            "║ │6│ ║ │ │ ║ │ │ ║\n" +
                                            "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                            "║ │ │ ║4│1│9║ │ │ ║\n" +
                                            "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                            "║ │ │ ║ │8│ ║ │ │ ║\n" +
                                            "╚═╧═╧═╩═╧═╧═╩═╧═╧═╝\n";
                                    // @formatter:on
                                    assertThat(sudoku.toString(), is(equalTo(expected)));
                                }

                                @DisplayName("Setup left center sub-grid")
                                @Nested
                                class SetupLeftCenterSubGrid {
                                    @BeforeEach
                                    public void setup() throws Exception {
                                        sudoku.set(0, 3, 8);
                                        sudoku.set(0, 4, 4);
                                        sudoku.set(0, 5, 7);
                                        sudoku.resolve();
                                    }

                                    @Test
                                    public void shouldPrintFields() throws Exception {
                                        // @formatter:off
                                        String expected =
                                                "╔═╤═╤═╦═╤═╤═╦═╤═╤═╗\n" +
                                                "║5│3│ ║6│7│8║ │ │ ║\n" +
                                                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                                "║6│ │ ║1│9│5║ │ │ ║\n" +
                                                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                                "║1│9│8║3│4│2║ │6│ ║\n" +
                                                "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                                                "║8│ │ ║7│6│ ║ │ │3║\n" +
                                                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                                "║4│2│ ║8│5│3║ │ │1║\n" +
                                                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                                "║7│ │ ║9│2│ ║ │ │6║\n" +
                                                "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                                                "║ │6│ ║ │3│7║ │ │ ║\n" +
                                                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                                "║ │ │ ║4│1│9║ │ │ ║\n" +
                                                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                                "║ │ │ ║ │8│6║ │ │ ║\n" +
                                                "╚═╧═╧═╩═╧═╧═╩═╧═╧═╝\n";
                                        // @formatter:on
                                        assertThat(sudoku.toString(), is(equalTo(expected)));
                                    }

                                    @DisplayName("Setup 1st part of bottom right sub-grid")
                                    @Nested
                                    class SetupBottomRightSubGrid {
                                        @BeforeEach
                                        public void setup() throws Exception {
                                            sudoku.set(6, 6, 2);
                                            sudoku.set(7, 6, 8);
                                            sudoku.set(7, 8, 7);

                                            sudoku.resolve();
                                        }

                                        @Test
                                        public void shouldPrintFields() throws Exception {
                                            // @formatter:off
                                            String expected =
                                                    "╔═╤═╤═╦═╤═╤═╦═╤═╤═╗\n" +
                                                    "║5│3│4║6│7│8║9│1│2║\n" +
                                                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                                    "║6│7│2║1│9│5║3│4│8║\n" +
                                                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                                    "║1│9│8║3│4│2║5│6│7║\n" +
                                                    "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                                                    "║8│5│9║7│6│1║4│2│3║\n" +
                                                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                                    "║4│2│6║8│5│3║7│9│1║\n" +
                                                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                                    "║7│1│3║9│2│4║8│5│6║\n" +
                                                    "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                                                    "║9│6│1║5│3│7║2│8│4║\n" +
                                                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                                    "║2│8│7║4│1│9║6│3│5║\n" +
                                                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                                                    "║3│4│5║2│8│6║1│7│9║\n" +
                                                    "╚═╧═╧═╩═╧═╧═╩═╧═╧═╝\n";
                                            // @formatter:on
                                            assertThat(sudoku.toString(), is(equalTo(expected)));
                                        }

                                        @Test
                                        public void shouldBeSolved() throws Exception {
                                            assertThat(sudoku.isSolved(), is(equalTo(true)));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
