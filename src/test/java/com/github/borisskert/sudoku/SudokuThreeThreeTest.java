package com.github.borisskert.sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

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
    public void randomFieldShouldBeInitialized() throws Exception {
        assertField(5, 4, allCandidates(), null);
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

    @DisplayName("setup middle subgrid")
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

        @Test
        public void shouldUpdateCandidatesInRandomFields() throws Exception {
            assertField(1, 1, allCandidates(), null);
            assertField(1, 4, Set.of(
                    FieldValue.of(1),
                    FieldValue.of(2),
                    FieldValue.of(4),
                    FieldValue.of(5),
                    FieldValue.of(6),
                    FieldValue.of(7),
                    FieldValue.of(9)
            ), null);
            assertField(1, 7, allCandidates(), null);
            assertField(4, 1, Set.of(
                    FieldValue.of(1),
                    FieldValue.of(3),
                    FieldValue.of(4),
                    FieldValue.of(5),
                    FieldValue.of(7),
                    FieldValue.of(8),
                    FieldValue.of(9)
            ), null);
            assertField(4, 4, Set.of(
                    FieldValue.of(1),
                    FieldValue.of(4),
                    FieldValue.of(5),
                    FieldValue.of(7),
                    FieldValue.of(9)
            ), null);
            assertField(4, 7, Set.of(
                    FieldValue.of(1),
                    FieldValue.of(3),
                    FieldValue.of(4),
                    FieldValue.of(5),
                    FieldValue.of(7),
                    FieldValue.of(8),
                    FieldValue.of(9)
            ), null);
            assertField(7, 1, allCandidates(), null);
            assertField(7, 4, Set.of(
                    FieldValue.of(1),
                    FieldValue.of(2),
                    FieldValue.of(4),
                    FieldValue.of(5),
                    FieldValue.of(6),
                    FieldValue.of(7),
                    FieldValue.of(9)
            ), null);
            assertField(7, 7, allCandidates(), null);
        }

        @DisplayName("setup right center subgrid")
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

            @Test
            public void shouldUpdateCandidatesInRandomFields() throws Exception {
                assertField(1, 1, allCandidates(), null);
                assertField(1, 4, Set.of(
                        FieldValue.of(2),
                        FieldValue.of(4),
                        FieldValue.of(5),
                        FieldValue.of(6),
                        FieldValue.of(7),
                        FieldValue.of(9)
                ), null);
                assertField(1, 7, allCandidates(), null);
                assertField(4, 1, Set.of(
                        FieldValue.of(1),
                        FieldValue.of(3),
                        FieldValue.of(4),
                        FieldValue.of(5),
                        FieldValue.of(7),
                        FieldValue.of(8),
                        FieldValue.of(9)
                ), null);
                assertField(4, 4, Set.of(
                        FieldValue.of(4),
                        FieldValue.of(5),
                        FieldValue.of(7),
                        FieldValue.of(9)
                ), null);
                assertField(4, 7, Set.of(
                        FieldValue.of(1),
                        FieldValue.of(3),
                        FieldValue.of(4),
                        FieldValue.of(5),
                        FieldValue.of(7),
                        FieldValue.of(8),
                        FieldValue.of(9)
                ), null);
                assertField(7, 1, allCandidates(), null);
                assertField(7, 4, Set.of(
                        FieldValue.of(2),
                        FieldValue.of(4),
                        FieldValue.of(5),
                        FieldValue.of(7),
                        FieldValue.of(9)
                ), null);
                assertField(7, 7, allCandidates(), null);
            }

            @DisplayName("setup bottom (middle) subgrid")
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

                @Test
                public void shouldUpdateCandidatesInRandomFields() throws Exception {
                    assertField(1, 1, allCandidates(), null);
                    assertField(1, 4, Set.of(
                            FieldValue.of(2),
                            FieldValue.of(4),
                            FieldValue.of(5),
                            FieldValue.of(6),
                            FieldValue.of(7),
                            FieldValue.of(9)
                    ), null);
                    assertField(1, 7, Set.of(
                            FieldValue.of(2),
                            FieldValue.of(3),
                            FieldValue.of(5),
                            FieldValue.of(6),
                            FieldValue.of(7),
                            FieldValue.of(8)
                    ), null);
                    assertField(4, 1, Set.of(
                            FieldValue.of(3),
                            FieldValue.of(4),
                            FieldValue.of(5),
                            FieldValue.of(7),
                            FieldValue.of(9)
                    ), null);
                    assertField(4, 4, Set.of(
                            FieldValue.of(4),
                            FieldValue.of(5),
                            FieldValue.of(7),
                            FieldValue.of(9)
                    ), null);
                    assertField(4, 7, Set.of(), FieldValue.of(1));
                    assertField(7, 1, allCandidates(), null);
                    assertField(7, 4, Set.of(
                            FieldValue.of(2),
                            FieldValue.of(4),
                            FieldValue.of(5),
                            FieldValue.of(7),
                            FieldValue.of(9)
                    ), null);
                    assertField(7, 7, Set.of(
                            FieldValue.of(2),
                            FieldValue.of(3),
                            FieldValue.of(5),
                            FieldValue.of(6),
                            FieldValue.of(7),
                            FieldValue.of(8)
                    ), null);
                }

                @DisplayName("setup top left subgrid")
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

                    @Test
                    public void shouldUpdateCandidatesInRandomFields() throws Exception {
                        assertField(1, 1, Set.of(
                                FieldValue.of(1),
                                FieldValue.of(2),
                                FieldValue.of(4),
                                FieldValue.of(7)
                        ), null);
                        assertField(1, 4, Set.of(
                                FieldValue.of(2),
                                FieldValue.of(4),
                                FieldValue.of(5),
                                FieldValue.of(6),
                                FieldValue.of(7)
                        ), null);
                        assertField(1, 7, Set.of(
                                FieldValue.of(2),
                                FieldValue.of(5),
                                FieldValue.of(6),
                                FieldValue.of(7),
                                FieldValue.of(8)
                        ), null);
                        assertField(4, 1, Set.of(
                                FieldValue.of(3),
                                FieldValue.of(4),
                                FieldValue.of(5),
                                FieldValue.of(7),
                                FieldValue.of(9)
                        ), null);
                        assertField(4, 4, Set.of(
                                FieldValue.of(4),
                                FieldValue.of(5),
                                FieldValue.of(7),
                                FieldValue.of(9)
                        ), null);
                        assertField(4, 7, Set.of(), FieldValue.of(1));
                        assertField(7, 1, Set.of(
                                FieldValue.of(1),
                                FieldValue.of(2),
                                FieldValue.of(3),
                                FieldValue.of(4),
                                FieldValue.of(5),
                                FieldValue.of(7),
                                FieldValue.of(8),
                                FieldValue.of(9)
                        ), null);
                        assertField(7, 4, Set.of(
                                FieldValue.of(2),
                                FieldValue.of(4),
                                FieldValue.of(5),
                                FieldValue.of(7),
                                FieldValue.of(9)
                        ), null);
                        assertField(7, 7, Set.of(
                                FieldValue.of(2),
                                FieldValue.of(3),
                                FieldValue.of(5),
                                FieldValue.of(6),
                                FieldValue.of(7),
                                FieldValue.of(8)
                        ), null);
                    }

                    @DisplayName("Setup top right subgrid")
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

                        @Test
                        public void shouldUpdateCandidatesInRandomFields() throws Exception {
                            assertField(1, 1, Set.of(
                                    FieldValue.of(1),
                                    FieldValue.of(2),
                                    FieldValue.of(4),
                                    FieldValue.of(7)
                            ), null);
                            assertField(1, 4, Set.of(
                                    FieldValue.of(2),
                                    FieldValue.of(4),
                                    FieldValue.of(5),
                                    FieldValue.of(6),
                                    FieldValue.of(7)
                            ), null);
                            assertField(1, 7, Set.of(
                                    FieldValue.of(2),
                                    FieldValue.of(5),
                                    FieldValue.of(6),
                                    FieldValue.of(7),
                                    FieldValue.of(8)
                            ), null);
                            assertField(4, 1, Set.of(
                                    FieldValue.of(3),
                                    FieldValue.of(4),
                                    FieldValue.of(5),
                                    FieldValue.of(7),
                                    FieldValue.of(9)
                            ), null);
                            assertField(4, 4, Set.of(
                                    FieldValue.of(4),
                                    FieldValue.of(5),
                                    FieldValue.of(7),
                                    FieldValue.of(9)
                            ), null);
                            assertField(4, 7, Set.of(), FieldValue.of(1));
                            assertField(7, 1, Set.of(
                                    FieldValue.of(1),
                                    FieldValue.of(2),
                                    FieldValue.of(3),
                                    FieldValue.of(4),
                                    FieldValue.of(5),
                                    FieldValue.of(7),
                                    FieldValue.of(8),
                                    FieldValue.of(9)
                            ), null);
                            assertField(7, 4, Set.of(
                                    FieldValue.of(2),
                                    FieldValue.of(4),
                                    FieldValue.of(5),
                                    FieldValue.of(7),
                                    FieldValue.of(9)
                            ), null);
                            assertField(7, 7, Set.of(
                                    FieldValue.of(2),
                                    FieldValue.of(3),
                                    FieldValue.of(5),
                                    FieldValue.of(7),
                                    FieldValue.of(8)
                            ), null);
                        }

                        @DisplayName("setup bottom left subgrid")
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

                            @Test
                            public void shouldUpdateCandidatesInRandomFields() throws Exception {
                                assertField(1, 1, Set.of(
                                        FieldValue.of(1),
                                        FieldValue.of(2),
                                        FieldValue.of(4),
                                        FieldValue.of(7)
                                ), null);
                                assertField(1, 4, Set.of(
                                        FieldValue.of(2),
                                        FieldValue.of(4),
                                        FieldValue.of(5),
                                        FieldValue.of(7)
                                ), null);
                                assertField(1, 7, Set.of(
                                        FieldValue.of(2),
                                        FieldValue.of(5),
                                        FieldValue.of(7),
                                        FieldValue.of(8)
                                ), null);
                                assertField(4, 1, Set.of(
                                        FieldValue.of(3),
                                        FieldValue.of(4),
                                        FieldValue.of(5),
                                        FieldValue.of(7),
                                        FieldValue.of(9)
                                ), null);
                                assertField(4, 4, Set.of(
                                        FieldValue.of(4),
                                        FieldValue.of(5),
                                        FieldValue.of(7),
                                        FieldValue.of(9)
                                ), null);
                                assertField(4, 7, Set.of(), FieldValue.of(1));
                                assertField(7, 1, Set.of(
                                        FieldValue.of(1),
                                        FieldValue.of(2),
                                        FieldValue.of(3),
                                        FieldValue.of(4),
                                        FieldValue.of(5),
                                        FieldValue.of(7),
                                        FieldValue.of(8),
                                        FieldValue.of(9)
                                ), null);
                                assertField(7, 4, Set.of(
                                        FieldValue.of(2),
                                        FieldValue.of(4),
                                        FieldValue.of(5),
                                        FieldValue.of(7),
                                        FieldValue.of(9)
                                ), null);
                                assertField(7, 7, Set.of(
                                        FieldValue.of(2),
                                        FieldValue.of(3),
                                        FieldValue.of(5),
                                        FieldValue.of(7),
                                        FieldValue.of(8)
                                ), null);
                            }

                            @DisplayName("Setup top (middle) subgrid")
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

                                @Test
                                public void shouldUpdateCandidatesInRandomFields() throws Exception {
                                    assertField(1, 1, Set.of(
                                            FieldValue.of(2),
                                            FieldValue.of(4),
                                            FieldValue.of(7)
                                    ), null);
                                    assertField(1, 4, Set.of(
                                            FieldValue.of(2),
                                            FieldValue.of(4),
                                            FieldValue.of(5),
                                            FieldValue.of(7)
                                    ), null);
                                    assertField(1, 7, Set.of(
                                            FieldValue.of(2),
                                            FieldValue.of(5),
                                            FieldValue.of(7),
                                            FieldValue.of(8)
                                    ), null);
                                    assertField(4, 1, Set.of(), FieldValue.of(9));
                                    assertField(4, 4, Set.of(
                                            FieldValue.of(4),
                                            FieldValue.of(5)
                                    ), null);
                                    assertField(4, 7, Set.of(), FieldValue.of(1));
                                    assertField(7, 1, Set.of(
                                            FieldValue.of(2),
                                            FieldValue.of(3),
                                            FieldValue.of(4),
                                            FieldValue.of(7),
                                            FieldValue.of(8)
                                    ), null);
                                    assertField(7, 4, Set.of(
                                            FieldValue.of(2),
                                            FieldValue.of(4),
                                            FieldValue.of(5),
                                            FieldValue.of(7),
                                            FieldValue.of(9)
                                    ), null);
                                    assertField(7, 7, Set.of(
                                            FieldValue.of(2),
                                            FieldValue.of(3),
                                            FieldValue.of(5),
                                            FieldValue.of(7),
                                            FieldValue.of(8)
                                    ), null);
                                }

                                @DisplayName("Setup left center subgrid")
                                @Nested
                                class SetupLeftCenterSubGrid {
                                    @BeforeEach
                                    public void setup() throws Exception {
                                        sudoku.set(0, 3, 8);
                                        sudoku.set(0, 4, 4);
                                        sudoku.set(0, 5, 7);
                                        sudoku.detectChanges();
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

                                    @Test
                                    public void shouldUpdateCandidatesInRandomFields() throws Exception {
                                        assertField(1, 1, Set.of(
                                                FieldValue.of(4),
                                                FieldValue.of(7)
                                        ), null);
                                        assertField(1, 4, Set.of(), FieldValue.of(2));
                                        assertField(1, 7, Set.of(
                                                FieldValue.of(5),
                                                FieldValue.of(7),
                                                FieldValue.of(8)
                                        ), null);
                                        assertField(4, 1, Set.of(), FieldValue.of(9));
                                        assertField(4, 4, Set.of(), FieldValue.of(5));
                                        assertField(4, 7, Set.of(), FieldValue.of(1));
                                        assertField(7, 1, Set.of(
                                                FieldValue.of(2),
                                                FieldValue.of(3),
                                                FieldValue.of(4),
                                                FieldValue.of(7),
                                                FieldValue.of(8)
                                        ), null);
                                        assertField(7, 4, Set.of(
                                                FieldValue.of(7),
                                                FieldValue.of(9)
                                        ), null);
                                        assertField(7, 7, Set.of(
                                                FieldValue.of(2),
                                                FieldValue.of(3),
                                                FieldValue.of(5),
                                                FieldValue.of(7),
                                                FieldValue.of(8)
                                        ), null);
                                    }

                                    @DisplayName("Setup 1st part of buttom right subgrid")
                                    @Nested
                                    class SetupBottomRightSubGrid {
                                        @BeforeEach
                                        public void setup() throws Exception {
                                            sudoku.set(6, 6, 2);
                                            sudoku.set(7, 6, 8);
                                            sudoku.set(7, 8, 7);

                                            sudoku.detectChanges();
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
                                        public void shouldUpdateCandidatesInRandomFields() throws Exception {
                                            assertField(1, 1, Set.of(), FieldValue.of(7));
                                            assertField(1, 4, Set.of(), FieldValue.of(2));
                                            assertField(1, 7, Set.of(), FieldValue.of(8));
                                            assertField(4, 1, Set.of(), FieldValue.of(9));
                                            assertField(4, 4, Set.of(), FieldValue.of(5));
                                            assertField(4, 7, Set.of(), FieldValue.of(1));
                                            assertField(7, 1, Set.of(), FieldValue.of(4));
                                            assertField(7, 4, Set.of(), FieldValue.of(9));
                                            assertField(7, 7, Set.of(), FieldValue.of(3));
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

    private Set<FieldValue> allCandidates() {
        return Set.of(
                FieldValue.of(1),
                FieldValue.of(2),
                FieldValue.of(3),
                FieldValue.of(4),
                FieldValue.of(5),
                FieldValue.of(6),
                FieldValue.of(7),
                FieldValue.of(8),
                FieldValue.of(9)
        );
    }

    private void assertField(int x, int y, Set<FieldValue> expectedCandidates, FieldValue expectedValue) {
        assertThat(sudoku.getCandidates(x, y), is(equalTo(expectedCandidates)));
        assertThat(sudoku.get(x, y), is(equalTo(expectedValue)));
    }
}
