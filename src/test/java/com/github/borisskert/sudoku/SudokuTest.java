package com.github.borisskert.sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@DisplayName("Sudoku 4")
class SudokuTest {

    private Sudoku sudoku;

    @BeforeEach
    public void setup() throws Exception {
        sudoku = Sudoku.create(4);
    }

    @Test
    public void shouldCreateField() throws Exception {
        List<FieldValue> candidates = sudoku.getCandidates(0, 0);

        assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
    }

    @Test
    public void shouldCreateNeighborSubGridToX() throws Exception {
        List<FieldValue> candidates = sudoku.getCandidates(2, 0);

        assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
    }

    @Test
    public void shouldCreateNeighborSubGridToY() throws Exception {
        List<FieldValue> candidates = sudoku.getCandidates(0, 2);

        assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
    }

    @Test
    public void shouldCreateNeighborSubGridXY() throws Exception {
        List<FieldValue> candidates = sudoku.getCandidates(2, 2);

        assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
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
        public void shouldRemoveAllPotentialCandidatesFromFieldZeroZero() throws Exception {
            List<FieldValue> candidates = sudoku.getCandidates(0, 0);

            assertThat(candidates, is(equalTo(List.of())));
        }

        @Test
        @DisplayName("(1,0) == [2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldOneZero() throws Exception {
            List<FieldValue> candidates = sudoku.getCandidates(1, 0);

            assertThat(candidates, is(equalTo(List.of(FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(0,1) == [2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldZeroOne() throws Exception {
            List<FieldValue> candidates = sudoku.getCandidates(0, 1);

            assertThat(candidates, is(equalTo(List.of(FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(1,1) == [2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldOneOne() throws Exception {
            List<FieldValue> candidates = sudoku.getCandidates(1, 1);

            assertThat(candidates, is(equalTo(List.of(FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(2,0) == [2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldTwoZero() throws Exception {
            List<FieldValue> candidates = sudoku.getCandidates(2, 0);

            assertThat(candidates, is(equalTo(List.of(FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(3,0) == [2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldThreeZero() throws Exception {
            List<FieldValue> candidates = sudoku.getCandidates(3, 0);

            assertThat(candidates, is(equalTo(List.of(FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(2,1) == [1,2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldTwoOne() throws Exception {
            List<FieldValue> candidates = sudoku.getCandidates(2, 1);

            assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(3,1) == [1,2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldThreeOne() throws Exception {
            List<FieldValue> candidates = sudoku.getCandidates(3, 1);

            assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(0,2) == [2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldZeroTwo() throws Exception {
            List<FieldValue> candidates = sudoku.getCandidates(0, 2);

            assertThat(candidates, is(equalTo(List.of(FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(0,3) == [2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldZeroThree() throws Exception {
            List<FieldValue> candidates = sudoku.getCandidates(0, 3);

            assertThat(candidates, is(equalTo(List.of(FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(1,2) == [1,2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldOneTwo() throws Exception {
            List<FieldValue> candidates = sudoku.getCandidates(1, 2);

            assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(1,3) == [1,2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldOneThree() throws Exception {
            List<FieldValue> candidates = sudoku.getCandidates(1, 3);

            assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(2,2) == [1,2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldTwoTwo() throws Exception {
            List<FieldValue> candidates = sudoku.getCandidates(2, 2);

            assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(2,3) == [1,2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldTwoThree() throws Exception {
            List<FieldValue> candidates = sudoku.getCandidates(2, 3);

            assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(3,2) == [1,2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldThreeTwo() throws Exception {
            List<FieldValue> candidates = sudoku.getCandidates(3, 2);

            assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(3,3) == [1,2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldThreeThree() throws Exception {
            List<FieldValue> candidates = sudoku.getCandidates(3, 3);

            assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Nested
        @DisplayName("(1,0) -> 2")
        class SetOneZeroToTwo {
            @BeforeEach
            public void setup() throws Exception {
                sudoku.set(1, 0, 2);
            }

            @Test
            @DisplayName("(0,1) == [3,4]")
            public void shouldRemovePotentialCandidatesFromFieldZeroOne() throws Exception {
                List<FieldValue> candidates = sudoku.getCandidates(0, 1);

                assertThat(candidates, is(equalTo(List.of(FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(1,1) == [3,4]")
            public void shouldRemovePotentialCandidatesFromFieldOneOne() throws Exception {
                List<FieldValue> candidates = sudoku.getCandidates(1, 1);

                assertThat(candidates, is(equalTo(List.of(FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(2,0) == [3,4]")
            public void shouldRemovePotentialCandidatesFromFieldTwoZero() throws Exception {
                List<FieldValue> candidates = sudoku.getCandidates(2, 0);

                assertThat(candidates, is(equalTo(List.of(FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(3,0) == [3,4]")
            public void shouldRemovePotentialCandidatesFromFieldThreeZero() throws Exception {
                List<FieldValue> candidates = sudoku.getCandidates(3, 0);

                assertThat(candidates, is(equalTo(List.of(FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(2,1) == [1,2,3,4]")
            public void shouldRemovePotentialCandidatesFromFieldTwoOne() throws Exception {
                List<FieldValue> candidates = sudoku.getCandidates(2, 1);

                assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(3,1) == [1,2,3,4]")
            public void shouldRemovePotentialCandidatesFromFieldThreeOne() throws Exception {
                List<FieldValue> candidates = sudoku.getCandidates(3, 1);

                assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(0,2) == [2,3,4]")
            public void shouldRemovePotentialCandidatesFromFieldZeroTwo() throws Exception {
                List<FieldValue> candidates = sudoku.getCandidates(0, 2);

                assertThat(candidates, is(equalTo(List.of(FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(0,3) == [2,3,4]")
            public void shouldRemovePotentialCandidatesFromFieldZeroThree() throws Exception {
                List<FieldValue> candidates = sudoku.getCandidates(0, 3);

                assertThat(candidates, is(equalTo(List.of(FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(1,2) == [1,3,4]")
            public void shouldRemovePotentialCandidatesFromFieldOneTwo() throws Exception {
                List<FieldValue> candidates = sudoku.getCandidates(1, 2);

                assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(1,3) == [1,3,4]")
            public void shouldRemovePotentialCandidatesFromFieldOneThree() throws Exception {
                List<FieldValue> candidates = sudoku.getCandidates(1, 3);

                assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(2,2) == [1,2,3,4]")
            public void shouldRemovePotentialCandidatesFromFieldTwoTwo() throws Exception {
                List<FieldValue> candidates = sudoku.getCandidates(2, 2);

                assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(2,3) == [1,2,3,4]")
            public void shouldRemovePotentialCandidatesFromFieldTwoThree() throws Exception {
                List<FieldValue> candidates = sudoku.getCandidates(2, 3);

                assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(3,2) == [1,2,3,4]")
            public void shouldRemovePotentialCandidatesFromFieldThreeTwo() throws Exception {
                List<FieldValue> candidates = sudoku.getCandidates(3, 2);

                assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(3,3) == [1,2,3,4]")
            public void shouldRemovePotentialCandidatesFromFieldThreeThree() throws Exception {
                List<FieldValue> candidates = sudoku.getCandidates(3, 3);

                assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
            }

            @Nested
            @DisplayName("(0,1) -> 3")
            class SetZeroOneToThree {
                @BeforeEach
                public void setup() throws Exception {
                    sudoku.set(0, 1, 3);
                }

                @Test
                @DisplayName("(1,1) == [4]")
                public void shouldRemovePotentialCandidatesFromFieldOneOne() throws Exception {
                    List<FieldValue> candidates = sudoku.getCandidates(1, 1);

                    assertThat(candidates, is(equalTo(List.of(FieldValue.of(4)))));
                }

                @Test
                @DisplayName("(2,0) == [3,4]")
                public void shouldRemovePotentialCandidatesFromFieldTwoZero() throws Exception {
                    List<FieldValue> candidates = sudoku.getCandidates(2, 0);

                    assertThat(candidates, is(equalTo(List.of(FieldValue.of(3), FieldValue.of(4)))));
                }

                @Test
                @DisplayName("(3,0) == [3,4]")
                public void shouldRemovePotentialCandidatesFromFieldThreeZero() throws Exception {
                    List<FieldValue> candidates = sudoku.getCandidates(3, 0);

                    assertThat(candidates, is(equalTo(List.of(FieldValue.of(3), FieldValue.of(4)))));
                }

                @Test
                @DisplayName("(2,1) == [1,2,4]")
                public void shouldRemovePotentialCandidatesFromFieldTwoOne() throws Exception {
                    List<FieldValue> candidates = sudoku.getCandidates(2, 1);

                    assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(4)))));
                }

                @Test
                @DisplayName("(3,1) == [1,2,4]")
                public void shouldRemovePotentialCandidatesFromFieldThreeOne() throws Exception {
                    List<FieldValue> candidates = sudoku.getCandidates(3, 1);

                    assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(4)))));
                }

                @Test
                @DisplayName("(0,2) == [2,4]")
                public void shouldRemovePotentialCandidatesFromFieldZeroTwo() throws Exception {
                    List<FieldValue> candidates = sudoku.getCandidates(0, 2);

                    assertThat(candidates, is(equalTo(List.of(FieldValue.of(2), FieldValue.of(4)))));
                }

                @Test
                @DisplayName("(0,3) == [2,4]")
                public void shouldRemovePotentialCandidatesFromFieldZeroThree() throws Exception {
                    List<FieldValue> candidates = sudoku.getCandidates(0, 3);

                    assertThat(candidates, is(equalTo(List.of(FieldValue.of(2), FieldValue.of(4)))));
                }

                @Test
                @DisplayName("(1,2) == [1,3,4]")
                public void shouldRemovePotentialCandidatesFromFieldOneTwo() throws Exception {
                    List<FieldValue> candidates = sudoku.getCandidates(1, 2);

                    assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(3), FieldValue.of(4)))));
                }

                @Test
                @DisplayName("(1,3) == [1,3,4]")
                public void shouldRemovePotentialCandidatesFromFieldOneThree() throws Exception {
                    List<FieldValue> candidates = sudoku.getCandidates(1, 3);

                    assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(3), FieldValue.of(4)))));
                }

                @Test
                @DisplayName("(2,2) == [1,2,3,4]")
                public void shouldRemovePotentialCandidatesFromFieldTwoTwo() throws Exception {
                    List<FieldValue> candidates = sudoku.getCandidates(2, 2);

                    assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
                }

                @Test
                @DisplayName("(2,3) == [1,2,3,4]")
                public void shouldRemovePotentialCandidatesFromFieldTwoThree() throws Exception {
                    List<FieldValue> candidates = sudoku.getCandidates(2, 3);

                    assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
                }

                @Test
                @DisplayName("(3,2) == [1,2,3,4]")
                public void shouldRemovePotentialCandidatesFromFieldThreeTwo() throws Exception {
                    List<FieldValue> candidates = sudoku.getCandidates(3, 2);

                    assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
                }

                @Test
                @DisplayName("(3,3) == [1,2,3,4]")
                public void shouldRemovePotentialCandidatesFromFieldThreeThree() throws Exception {
                    List<FieldValue> candidates = sudoku.getCandidates(3, 3);

                    assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
                }

                @Nested
                @DisplayName("(1,1) -> 4")
                class SetOneOneToFour {
                    @BeforeEach
                    public void setup() throws Exception {
                        sudoku.set(1, 1, 4);
                    }

                    @Test
                    @DisplayName("(1,1) == []")
                    public void shouldRemoveLastPotentialCandidatesFromFieldOneOne() throws Exception {
                        List<FieldValue> candidates = sudoku.getCandidates(1, 1);

                        assertThat(candidates, is(equalTo(List.of())));
                    }


                    @Test
                    @DisplayName("(2,0) == [3,4]")
                    public void shouldRemovePotentialCandidatesFromFieldTwoZero() throws Exception {
                        List<FieldValue> candidates = sudoku.getCandidates(2, 0);

                        assertThat(candidates, is(equalTo(List.of(FieldValue.of(3), FieldValue.of(4)))));
                    }

                    @Test
                    @DisplayName("(3,0) == [3,4]")
                    public void shouldRemovePotentialCandidatesFromFieldThreeZero() throws Exception {
                        List<FieldValue> candidates = sudoku.getCandidates(3, 0);

                        assertThat(candidates, is(equalTo(List.of(FieldValue.of(3), FieldValue.of(4)))));
                    }

                    @Test
                    @DisplayName("(2,1) == [1,2]")
                    public void shouldRemovePotentialCandidatesFromFieldTwoOne() throws Exception {
                        List<FieldValue> candidates = sudoku.getCandidates(2, 1);

                        assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2)))));
                    }

                    @Test
                    @DisplayName("(3,1) == [1,2]")
                    public void shouldRemovePotentialCandidatesFromFieldThreeOne() throws Exception {
                        List<FieldValue> candidates = sudoku.getCandidates(3, 1);

                        assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2)))));
                    }

                    @Test
                    @DisplayName("(0,2) == [2,4]")
                    public void shouldRemovePotentialCandidatesFromFieldZeroTwo() throws Exception {
                        List<FieldValue> candidates = sudoku.getCandidates(0, 2);

                        assertThat(candidates, is(equalTo(List.of(FieldValue.of(2), FieldValue.of(4)))));
                    }

                    @Test
                    @DisplayName("(0,3) == [2,4]")
                    public void shouldRemovePotentialCandidatesFromFieldZeroThree() throws Exception {
                        List<FieldValue> candidates = sudoku.getCandidates(0, 3);

                        assertThat(candidates, is(equalTo(List.of(FieldValue.of(2), FieldValue.of(4)))));
                    }

                    @Test
                    @DisplayName("(1,2) == [1,3]")
                    public void shouldRemovePotentialCandidatesFromFieldOneTwo() throws Exception {
                        List<FieldValue> candidates = sudoku.getCandidates(1, 2);

                        assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(3)))));
                    }

                    @Test
                    @DisplayName("(1,3) == [1,3]")
                    public void shouldRemovePotentialCandidatesFromFieldOneThree() throws Exception {
                        List<FieldValue> candidates = sudoku.getCandidates(1, 3);

                        assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(3)))));
                    }

                    @Test
                    @DisplayName("(2,2) == [1,2,3,4]")
                    public void shouldRemovePotentialCandidatesFromFieldTwoTwo() throws Exception {
                        List<FieldValue> candidates = sudoku.getCandidates(2, 2);

                        assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
                    }

                    @Test
                    @DisplayName("(2,3) == [1,2,3,4]")
                    public void shouldRemovePotentialCandidatesFromFieldTwoThree() throws Exception {
                        List<FieldValue> candidates = sudoku.getCandidates(2, 3);

                        assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
                    }

                    @Test
                    @DisplayName("(3,2) == [1,2,3,4]")
                    public void shouldRemovePotentialCandidatesFromFieldThreeTwo() throws Exception {
                        List<FieldValue> candidates = sudoku.getCandidates(3, 2);

                        assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
                    }

                    @Test
                    @DisplayName("(3,3) == [1,2,3,4]")
                    public void shouldRemovePotentialCandidatesFromFieldThreeThree() throws Exception {
                        List<FieldValue> candidates = sudoku.getCandidates(3, 3);

                        assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
                    }

                    @Nested
                    @DisplayName("(2,0) -> 3")
                    class SetTwoZeroToThree {
                        @BeforeEach
                        public void setup() throws Exception {
                            sudoku.set(2, 0, 3);
                        }

                        @Test
                        @DisplayName("(2,0) == []")
                        public void shouldRemovePotentialCandidatesFromFieldTwoZero() throws Exception {
                            List<FieldValue> candidates = sudoku.getCandidates(2, 0);

                            assertThat(candidates, is(equalTo(List.of())));
                        }

                        @Test
                        @DisplayName("(3,0) == [4]")
                        public void shouldRemovePotentialCandidatesFromFieldThreeZero() throws Exception {
                            List<FieldValue> candidates = sudoku.getCandidates(3, 0);

                            assertThat(candidates, is(equalTo(List.of(FieldValue.of(4)))));
                        }

                        @Test
                        @DisplayName("(2,1) == [1,2]")
                        public void shouldRemovePotentialCandidatesFromFieldTwoOne() throws Exception {
                            List<FieldValue> candidates = sudoku.getCandidates(2, 1);

                            assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2)))));
                        }

                        @Test
                        @DisplayName("(3,1) == [1,2]")
                        public void shouldRemovePotentialCandidatesFromFieldThreeOne() throws Exception {
                            List<FieldValue> candidates = sudoku.getCandidates(3, 1);

                            assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2)))));
                        }

                        @Test
                        @DisplayName("(0,2) == [2,4]")
                        public void shouldRemovePotentialCandidatesFromFieldZeroTwo() throws Exception {
                            List<FieldValue> candidates = sudoku.getCandidates(0, 2);

                            assertThat(candidates, is(equalTo(List.of(FieldValue.of(2), FieldValue.of(4)))));
                        }

                        @Test
                        @DisplayName("(0,3) == [2,4]")
                        public void shouldRemovePotentialCandidatesFromFieldZeroThree() throws Exception {
                            List<FieldValue> candidates = sudoku.getCandidates(0, 3);

                            assertThat(candidates, is(equalTo(List.of(FieldValue.of(2), FieldValue.of(4)))));
                        }

                        @Test
                        @DisplayName("(1,2) == [1,3]")
                        public void shouldRemovePotentialCandidatesFromFieldOneTwo() throws Exception {
                            List<FieldValue> candidates = sudoku.getCandidates(1, 2);

                            assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(3)))));
                        }

                        @Test
                        @DisplayName("(1,3) == [1,3]")
                        public void shouldRemovePotentialCandidatesFromFieldOneThree() throws Exception {
                            List<FieldValue> candidates = sudoku.getCandidates(1, 3);

                            assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(3)))));
                        }

                        @Test
                        @DisplayName("(2,2) == [1,2,4]")
                        public void shouldRemovePotentialCandidatesFromFieldTwoTwo() throws Exception {
                            List<FieldValue> candidates = sudoku.getCandidates(2, 2);

                            assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(4)))));
                        }

                        @Test
                        @DisplayName("(2,3) == [1,2,4]")
                        public void shouldRemovePotentialCandidatesFromFieldTwoThree() throws Exception {
                            List<FieldValue> candidates = sudoku.getCandidates(2, 3);

                            assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(4)))));
                        }

                        @Test
                        @DisplayName("(3,2) == [1,2,4]")
                        public void shouldRemovePotentialCandidatesFromFieldThreeTwo() throws Exception {
                            List<FieldValue> candidates = sudoku.getCandidates(3, 2);

                            assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
                        }

                        @Test
                        @DisplayName("(3,3) == [1,2,3,4]")
                        public void shouldRemovePotentialCandidatesFromFieldThreeThree() throws Exception {
                            List<FieldValue> candidates = sudoku.getCandidates(3, 3);

                            assertThat(candidates, is(equalTo(List.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
                        }
                    }
                }
            }
        }
    }
}
