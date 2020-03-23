package de.borisskert.sudoku.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Sudoku (2/2)")
class SudokuTwoTwoTest {

    private Sudoku sudoku;

    @BeforeEach
    public void setup() throws Exception {
        sudoku = Sudoku.create(2, 2);
    }

    @Test
    public void shouldNotBeSolved() throws Exception {
        assertThat(sudoku.isSolved(), is(equalTo(false)));
    }

    @Test
    public void shouldPrintFields() throws Exception {
        // @formatter:off
        String expected =
                "╔═╤═╦═╤═╗\n" +
                "║ │ ║ │ ║\n" +
                "╟─┼─╫─┼─╢\n" +
                "║ │ ║ │ ║\n" +
                "╠═╪═╬═╪═╣\n" +
                "║ │ ║ │ ║\n" +
                "╟─┼─╫─┼─╢\n" +
                "║ │ ║ │ ║\n" +
                "╚═╧═╩═╧═╝\n";
        // @formatter:on
        assertThat(sudoku.toString(), is(equalTo(expected)));
    }

    @Nested
    @DisplayName("(0,0) -> 1")
    class SetZeroZeroToOne {
        @BeforeEach
        public void setup() throws Exception {
            sudoku.set(0, 0, 1);
        }

        @Test
        @DisplayName("candidates of (0,0) == []")
        public void shouldThrowWhenTryToResetValue() throws Exception {
            try {
                sudoku.set(0, 0, 2);
                fail("Should throw IllegalStateException");
            } catch (IllegalStateException e) {
                assertThat(e.getMessage(), is(equalTo("Already contains the value '1'")));
            }
        }

        @Test
        public void shouldThrowWhenTryToSetValueNotInCandidates() throws Exception {
            assertThrows(IllegalStateException.class, () -> sudoku.set(1, 0, 1));
        }

        @Test
        public void shouldNotBeSolved() throws Exception {
            assertThat(sudoku.isSolved(), is(equalTo(false)));
        }

        @Test
        public void shouldPrintFields() throws Exception {
            // @formatter:off
            String expected =
                    "╔═╤═╦═╤═╗\n" +
                    "║1│ ║ │ ║\n" +
                    "╟─┼─╫─┼─╢\n" +
                    "║ │ ║ │ ║\n" +
                    "╠═╪═╬═╪═╣\n" +
                    "║ │ ║ │ ║\n" +
                    "╟─┼─╫─┼─╢\n" +
                    "║ │ ║ │ ║\n" +
                    "╚═╧═╩═╧═╝\n";
            // @formatter:on
            assertThat(sudoku.toString(), is(equalTo(expected)));
        }

        @Nested
        @DisplayName("(1,0) -> 2")
        class SetOneZeroToTwo {
            @BeforeEach
            public void setup() throws Exception {
                sudoku.set(1, 0, 2);
            }

            @Test
            public void shouldThrowWhenTryToSetAlreadyTakenValueInSubGrid() throws Exception {
                assertThrows(IllegalStateException.class, () -> sudoku.set(1, 1, 2));
            }

            @Test
            public void shouldThrowWhenTryToSetAlreadyTakenValueInSameLine() throws Exception {
                assertThrows(IllegalStateException.class, () -> sudoku.set(3, 0, 2));
            }

            @Test
            public void shouldThrowWhenTryToSetAlreadyTakenValueInSameColumn() throws Exception {
                assertThrows(IllegalStateException.class, () -> sudoku.set(1, 3, 2));
            }

            @Test
            public void shouldNotBeSolved() throws Exception {
                assertThat(sudoku.isSolved(), is(equalTo(false)));
            }

            @Test
            public void shouldPrintFields() throws Exception {
                // @formatter:off
                String expected =
                        "╔═╤═╦═╤═╗\n" +
                        "║1│2║ │ ║\n" +
                        "╟─┼─╫─┼─╢\n" +
                        "║ │ ║ │ ║\n" +
                        "╠═╪═╬═╪═╣\n" +
                        "║ │ ║ │ ║\n" +
                        "╟─┼─╫─┼─╢\n" +
                        "║ │ ║ │ ║\n" +
                        "╚═╧═╩═╧═╝\n";
                // @formatter:on
                assertThat(sudoku.toString(), is(equalTo(expected)));
            }

            @Nested
            @DisplayName("(0,1) -> 3")
            class SetZeroOneToThree {
                @BeforeEach
                public void setup() throws Exception {
                    sudoku.set(0, 1, 3);
                    sudoku.resolve();
                }

                @Test
                public void shouldNotBeSolved() throws Exception {
                    assertThat(sudoku.isSolved(), is(equalTo(false)));
                }

                @Test
                public void shouldPrintFields() throws Exception {
                    // @formatter:off
                    String expected =
                            "╔═╤═╦═╤═╗\n" +
                            "║1│2║ │ ║\n" +
                            "╟─┼─╫─┼─╢\n" +
                            "║3│4║ │ ║\n" +
                            "╠═╪═╬═╪═╣\n" +
                            "║ │ ║ │ ║\n" +
                            "╟─┼─╫─┼─╢\n" +
                            "║ │ ║ │ ║\n" +
                            "╚═╧═╩═╧═╝\n";
                    // @formatter:on
                    assertThat(sudoku.toString(), is(equalTo(expected)));
                }

                @Nested
                @DisplayName("(2,0) -> 3")
                class SetTwoZeroToThree {
                    @BeforeEach
                    public void setup() throws Exception {
                        sudoku.set(2, 0, 3);
                        sudoku.resolve();
                    }

                    @Test
                    public void shouldNotBeSolved() throws Exception {
                        assertThat(sudoku.isSolved(), is(equalTo(false)));
                    }

                    @Test
                    public void shouldPrintFields() throws Exception {
                        // @formatter:off
                        String expected =
                                "╔═╤═╦═╤═╗\n" +
                                "║1│2║3│4║\n" +
                                "╟─┼─╫─┼─╢\n" +
                                "║3│4║ │ ║\n" +
                                "╠═╪═╬═╪═╣\n" +
                                "║ │ ║ │ ║\n" +
                                "╟─┼─╫─┼─╢\n" +
                                "║ │ ║ │ ║\n" +
                                "╚═╧═╩═╧═╝\n";
                        // @formatter:on
                        assertThat(sudoku.toString(), is(equalTo(expected)));
                    }

                    @Nested
                    @DisplayName("(2,1) -> 2")
                    class SetTwoOneToTwo {
                        @BeforeEach
                        public void setup() throws Exception {
                            sudoku.set(2, 1, 2);
                            sudoku.resolve();
                        }

                        @Test
                        public void shouldNotBeSolved() throws Exception {
                            assertThat(sudoku.isSolved(), is(equalTo(false)));
                        }

                        @Test
                        public void shouldPrintFields() throws Exception {
                            // @formatter:off
                            String expected =
                                    "╔═╤═╦═╤═╗\n" +
                                    "║1│2║3│4║\n" +
                                    "╟─┼─╫─┼─╢\n" +
                                    "║3│4║2│1║\n" +
                                    "╠═╪═╬═╪═╣\n" +
                                    "║ │ ║ │ ║\n" +
                                    "╟─┼─╫─┼─╢\n" +
                                    "║ │ ║ │ ║\n" +
                                    "╚═╧═╩═╧═╝\n";
                            // @formatter:on
                            assertThat(sudoku.toString(), is(equalTo(expected)));
                        }

                        @Nested
                        @DisplayName("(3,3) -> 3")
                        class SetThreeThreeToThree {
                            @BeforeEach
                            public void setup() throws Exception {
                                sudoku.set(3, 3, 3);
                                sudoku.resolve();
                            }

                            @Test
                            public void shouldBeSolved() throws Exception {
                                assertThat(sudoku.isSolved(), is(equalTo(true)));
                            }

                            @Test
                            public void shouldPrintFields() throws Exception {
                                // @formatter:off
                                String expected =
                                        "╔═╤═╦═╤═╗\n" +
                                        "║1│2║3│4║\n" +
                                        "╟─┼─╫─┼─╢\n" +
                                        "║3│4║2│1║\n" +
                                        "╠═╪═╬═╪═╣\n" +
                                        "║4│3║1│2║\n" +
                                        "╟─┼─╫─┼─╢\n" +
                                        "║2│1║4│3║\n" +
                                        "╚═╧═╩═╧═╝\n";
                                // @formatter:on
                                assertThat(sudoku.toString(), is(equalTo(expected)));
                            }
                        }

                        @Nested
                        class TryToSolve {
                            @BeforeEach
                            public void setup() throws Exception {
                                sudoku.solve();
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
