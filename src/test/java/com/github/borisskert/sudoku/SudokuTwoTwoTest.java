package com.github.borisskert.sudoku;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Sudoku (2/2)")
class SudokuTwoTwoTest {

    private Sudoku sudoku;

    @BeforeEach
    public void setup() throws Exception {
        sudoku = Sudoku.create(2, 2);
    }

    @Test
    public void shouldCreateField() throws Exception {
        Set<FieldValue> candidates = sudoku.getCandidates(0, 0);

        assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
    }

    @Test
    public void shouldCreateNeighborSubGridToX() throws Exception {
        Set<FieldValue> candidates = sudoku.getCandidates(2, 0);

        assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
    }

    @Test
    public void shouldCreateNeighborSubGridToY() throws Exception {
        Set<FieldValue> candidates = sudoku.getCandidates(0, 2);

        assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
    }

    @Test
    public void shouldCreateNeighborSubGridXY() throws Exception {
        Set<FieldValue> candidates = sudoku.getCandidates(2, 2);

        assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
    }

    @Test
    public void shouldNotBeSolved() throws Exception {
        assertThat(sudoku.isSolved(), is(equalTo(false)));
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
                assertThat(e.getMessage(), Is.is(equalTo("Already contains the value '1'")));
            }
        }

        @Test
        public void shouldThrowWhenTryToSetValueNotInCandidates() throws Exception {
            try {
                sudoku.set(1, 0, 1);
                fail("Should throw IllegalArgumentException");
            } catch (IllegalArgumentException e) {
                assertThat(e.getMessage(), Is.is(equalTo("Value '1' not in candidates")));
            }
        }

        @Test
        @DisplayName("candidates of (0,0) == []")
        public void shouldRemoveAllPotentialCandidatesFromFieldZeroZero() throws Exception {
            Set<FieldValue> candidates = sudoku.getCandidates(0, 0);

            assertThat(candidates, is(equalTo(Set.of())));
        }

        @Test
        @DisplayName("(1,0) == [2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldOneZero() throws Exception {
            Set<FieldValue> candidates = sudoku.getCandidates(1, 0);

            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(0,1) == [2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldZeroOne() throws Exception {
            Set<FieldValue> candidates = sudoku.getCandidates(0, 1);

            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(1,1) == [2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldOneOne() throws Exception {
            Set<FieldValue> candidates = sudoku.getCandidates(1, 1);

            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(2,0) == [2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldTwoZero() throws Exception {
            Set<FieldValue> candidates = sudoku.getCandidates(2, 0);

            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(3,0) == [2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldThreeZero() throws Exception {
            Set<FieldValue> candidates = sudoku.getCandidates(3, 0);

            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(2,1) == [1,2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldTwoOne() throws Exception {
            Set<FieldValue> candidates = sudoku.getCandidates(2, 1);

            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(3,1) == [1,2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldThreeOne() throws Exception {
            Set<FieldValue> candidates = sudoku.getCandidates(3, 1);

            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(0,2) == [2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldZeroTwo() throws Exception {
            Set<FieldValue> candidates = sudoku.getCandidates(0, 2);

            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(0,3) == [2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldZeroThree() throws Exception {
            Set<FieldValue> candidates = sudoku.getCandidates(0, 3);

            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(1,2) == [1,2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldOneTwo() throws Exception {
            Set<FieldValue> candidates = sudoku.getCandidates(1, 2);

            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(1,3) == [1,2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldOneThree() throws Exception {
            Set<FieldValue> candidates = sudoku.getCandidates(1, 3);

            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(2,2) == [1,2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldTwoTwo() throws Exception {
            Set<FieldValue> candidates = sudoku.getCandidates(2, 2);

            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(2,3) == [1,2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldTwoThree() throws Exception {
            Set<FieldValue> candidates = sudoku.getCandidates(2, 3);

            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(3,2) == [1,2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldThreeTwo() throws Exception {
            Set<FieldValue> candidates = sudoku.getCandidates(3, 2);

            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        @DisplayName("(3,3) == [1,2,3,4]")
        public void shouldRemovePotentialCandidatesFromFieldThreeThree() throws Exception {
            Set<FieldValue> candidates = sudoku.getCandidates(3, 3);

            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
        }

        @Test
        public void shouldNotBeSolved() throws Exception {
            assertThat(sudoku.isSolved(), is(equalTo(false)));
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
                Set<FieldValue> candidates = sudoku.getCandidates(0, 1);

                assertThat(candidates, is(equalTo(Set.of(FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(1,1) == [3,4]")
            public void shouldRemovePotentialCandidatesFromFieldOneOne() throws Exception {
                Set<FieldValue> candidates = sudoku.getCandidates(1, 1);

                assertThat(candidates, is(equalTo(Set.of(FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(2,0) == [3,4]")
            public void shouldRemovePotentialCandidatesFromFieldTwoZero() throws Exception {
                Set<FieldValue> candidates = sudoku.getCandidates(2, 0);

                assertThat(candidates, is(equalTo(Set.of(FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(3,0) == [3,4]")
            public void shouldRemovePotentialCandidatesFromFieldThreeZero() throws Exception {
                Set<FieldValue> candidates = sudoku.getCandidates(3, 0);

                assertThat(candidates, is(equalTo(Set.of(FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(2,1) == [1,2,3,4]")
            public void shouldRemovePotentialCandidatesFromFieldTwoOne() throws Exception {
                Set<FieldValue> candidates = sudoku.getCandidates(2, 1);

                assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(3,1) == [1,2,3,4]")
            public void shouldRemovePotentialCandidatesFromFieldThreeOne() throws Exception {
                Set<FieldValue> candidates = sudoku.getCandidates(3, 1);

                assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(0,2) == [2,3,4]")
            public void shouldRemovePotentialCandidatesFromFieldZeroTwo() throws Exception {
                Set<FieldValue> candidates = sudoku.getCandidates(0, 2);

                assertThat(candidates, is(equalTo(Set.of(FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(0,3) == [2,3,4]")
            public void shouldRemovePotentialCandidatesFromFieldZeroThree() throws Exception {
                Set<FieldValue> candidates = sudoku.getCandidates(0, 3);

                assertThat(candidates, is(equalTo(Set.of(FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(1,2) == [1,3,4]")
            public void shouldRemovePotentialCandidatesFromFieldOneTwo() throws Exception {
                Set<FieldValue> candidates = sudoku.getCandidates(1, 2);

                assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(1,3) == [1,3,4]")
            public void shouldRemovePotentialCandidatesFromFieldOneThree() throws Exception {
                Set<FieldValue> candidates = sudoku.getCandidates(1, 3);

                assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(2,2) == [1,2,3,4]")
            public void shouldRemovePotentialCandidatesFromFieldTwoTwo() throws Exception {
                Set<FieldValue> candidates = sudoku.getCandidates(2, 2);

                assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(2,3) == [1,2,3,4]")
            public void shouldRemovePotentialCandidatesFromFieldTwoThree() throws Exception {
                Set<FieldValue> candidates = sudoku.getCandidates(2, 3);

                assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(3,2) == [1,2,3,4]")
            public void shouldRemovePotentialCandidatesFromFieldThreeTwo() throws Exception {
                Set<FieldValue> candidates = sudoku.getCandidates(3, 2);

                assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            @DisplayName("(3,3) == [1,2,3,4]")
            public void shouldRemovePotentialCandidatesFromFieldThreeThree() throws Exception {
                Set<FieldValue> candidates = sudoku.getCandidates(3, 3);

                assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
            }

            @Test
            public void shouldNotBeSolved() throws Exception {
                assertThat(sudoku.isSolved(), is(equalTo(false)));
            }

            @Nested
            @DisplayName("(0,1) -> 3")
            class SetZeroOneToThree {
                @BeforeEach
                public void setup() throws Exception {
                    sudoku.set(0, 1, 3);
                }

                @Test
                @DisplayName("(1,1) == <4>[]")
                public void shouldRemovePotentialCandidatesFromFieldOneOne() throws Exception {
                    assertThat(sudoku.getCandidates(1, 1), is(equalTo(Set.of())));
                    assertThat(sudoku.get(1, 1), is(equalTo(FieldValue.of(4))));
                }

                @Test
                @DisplayName("(2,0) == [3,4]")
                public void shouldRemovePotentialCandidatesFromFieldTwoZero() throws Exception {
                    Set<FieldValue> candidates = sudoku.getCandidates(2, 0);

                    assertThat(candidates, is(equalTo(Set.of(FieldValue.of(3), FieldValue.of(4)))));
                }

                @Test
                @DisplayName("(3,0) == [3,4]")
                public void shouldRemovePotentialCandidatesFromFieldThreeZero() throws Exception {
                    Set<FieldValue> candidates = sudoku.getCandidates(3, 0);

                    assertThat(candidates, is(equalTo(Set.of(FieldValue.of(3), FieldValue.of(4)))));
                }

                @Test
                @DisplayName("(2,1) == [1,2]")
                public void shouldRemovePotentialCandidatesFromFieldTwoOne() throws Exception {
                    Set<FieldValue> candidates = sudoku.getCandidates(2, 1);

                    assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2)))));
                }

                @Test
                @DisplayName("(3,1) == [1,2]")
                public void shouldRemovePotentialCandidatesFromFieldThreeOne() throws Exception {
                    Set<FieldValue> candidates = sudoku.getCandidates(3, 1);

                    assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2)))));
                }

                @Test
                @DisplayName("(0,2) == [2,4]")
                public void shouldRemovePotentialCandidatesFromFieldZeroTwo() throws Exception {
                    Set<FieldValue> candidates = sudoku.getCandidates(0, 2);

                    assertThat(candidates, is(equalTo(Set.of(FieldValue.of(2), FieldValue.of(4)))));
                }

                @Test
                @DisplayName("(0,3) == [2,4]")
                public void shouldRemovePotentialCandidatesFromFieldZeroThree() throws Exception {
                    Set<FieldValue> candidates = sudoku.getCandidates(0, 3);

                    assertThat(candidates, is(equalTo(Set.of(FieldValue.of(2), FieldValue.of(4)))));
                }

                @Test
                @DisplayName("(1,2) == [1,3]")
                public void shouldRemovePotentialCandidatesFromFieldOneTwo() throws Exception {
                    Set<FieldValue> candidates = sudoku.getCandidates(1, 2);

                    assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(3)))));
                }

                @Test
                @DisplayName("(1,3) == [1,3]")
                public void shouldRemovePotentialCandidatesFromFieldOneThree() throws Exception {
                    Set<FieldValue> candidates = sudoku.getCandidates(1, 3);

                    assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(3)))));
                }

                @Test
                @DisplayName("(2,2) == [1,2,3,4]")
                public void shouldRemovePotentialCandidatesFromFieldTwoTwo() throws Exception {
                    Set<FieldValue> candidates = sudoku.getCandidates(2, 2);

                    assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
                }

                @Test
                @DisplayName("(2,3) == [1,2,3,4]")
                public void shouldRemovePotentialCandidatesFromFieldTwoThree() throws Exception {
                    Set<FieldValue> candidates = sudoku.getCandidates(2, 3);

                    assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
                }

                @Test
                @DisplayName("(3,2) == [1,2,3,4]")
                public void shouldRemovePotentialCandidatesFromFieldThreeTwo() throws Exception {
                    Set<FieldValue> candidates = sudoku.getCandidates(3, 2);

                    assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
                }

                @Test
                @DisplayName("(3,3) == [1,2,3,4]")
                public void shouldRemovePotentialCandidatesFromFieldThreeThree() throws Exception {
                    Set<FieldValue> candidates = sudoku.getCandidates(3, 3);

                    assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3), FieldValue.of(4)))));
                }

                @Test
                public void shouldNotBeSolved() throws Exception {
                    assertThat(sudoku.isSolved(), is(equalTo(false)));
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
                        Set<FieldValue> candidates = sudoku.getCandidates(2, 0);

                        assertThat(candidates, is(equalTo(Set.of())));
                    }

                    @Test
                    @DisplayName("(3,0) == <4>[]")
                    public void shouldRemovePotentialCandidatesFromFieldThreeZero() throws Exception {
                        assertThat(sudoku.getCandidates(3, 0), is(equalTo(Set.of())));
                        assertThat(sudoku.get(3, 0), is(equalTo(FieldValue.of(4))));
                    }

                    @Test
                    @DisplayName("(2,1) == [1,2]")
                    public void shouldRemovePotentialCandidatesFromFieldTwoOne() throws Exception {
                        Set<FieldValue> candidates = sudoku.getCandidates(2, 1);

                        assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2)))));
                    }

                    @Test
                    @DisplayName("(3,1) == [1,2]")
                    public void shouldRemovePotentialCandidatesFromFieldThreeOne() throws Exception {
                        Set<FieldValue> candidates = sudoku.getCandidates(3, 1);

                        assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2)))));
                    }

                    @Test
                    @DisplayName("(0,2) == [2,4]")
                    public void shouldRemovePotentialCandidatesFromFieldZeroTwo() throws Exception {
                        Set<FieldValue> candidates = sudoku.getCandidates(0, 2);

                        assertThat(candidates, is(equalTo(Set.of(FieldValue.of(2), FieldValue.of(4)))));
                    }

                    @Test
                    @DisplayName("(0,3) == [2,4]")
                    public void shouldRemovePotentialCandidatesFromFieldZeroThree() throws Exception {
                        Set<FieldValue> candidates = sudoku.getCandidates(0, 3);

                        assertThat(candidates, is(equalTo(Set.of(FieldValue.of(2), FieldValue.of(4)))));
                    }

                    @Test
                    @DisplayName("(1,2) == [1,3]")
                    public void shouldRemovePotentialCandidatesFromFieldOneTwo() throws Exception {
                        Set<FieldValue> candidates = sudoku.getCandidates(1, 2);

                        assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(3)))));
                    }

                    @Test
                    @DisplayName("(1,3) == [1,3]")
                    public void shouldRemovePotentialCandidatesFromFieldOneThree() throws Exception {
                        Set<FieldValue> candidates = sudoku.getCandidates(1, 3);

                        assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(3)))));
                    }

                    @Test
                    @DisplayName("(2,2) == [1,2,4]")
                    public void shouldRemovePotentialCandidatesFromFieldTwoTwo() throws Exception {
                        Set<FieldValue> candidates = sudoku.getCandidates(2, 2);

                        assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(4)))));
                    }

                    @Test
                    @DisplayName("(2,3) == [1,2,4]")
                    public void shouldRemovePotentialCandidatesFromFieldTwoThree() throws Exception {
                        Set<FieldValue> candidates = sudoku.getCandidates(2, 3);

                        assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(4)))));
                    }

                    @Test
                    @DisplayName("(3,2) == [1,2]")
                    public void shouldRemovePotentialCandidatesFromFieldThreeTwo() throws Exception {
                        Set<FieldValue> candidates = sudoku.getCandidates(3, 2);

                        assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3)))));
                    }

                    @Test
                    @DisplayName("(3,3) == [1,2,3]")
                    public void shouldRemovePotentialCandidatesFromFieldThreeThree() throws Exception {
                        Set<FieldValue> candidates = sudoku.getCandidates(3, 3);

                        assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(2), FieldValue.of(3)))));
                    }

                    @Test
                    public void shouldNotBeSolved() throws Exception {
                        assertThat(sudoku.isSolved(), is(equalTo(false)));
                    }

                    @Nested
                    @DisplayName("(2,1) -> 2")
                    class SetTwoOneToTwo {
                        @BeforeEach
                        public void setup() throws Exception {
                            sudoku.set(2, 1, 2);
                        }

                        @Test
                        @DisplayName("(2,1) == <2>[]")
                        public void shouldRemovePotentialCandidatesFromFieldThreeZero() throws Exception {
                            assertThat(sudoku.getCandidates(2, 1), is(equalTo(Set.of())));
                            assertThat(sudoku.get(2, 1), is(equalTo(FieldValue.of(2))));
                        }


                        @Test
                        @DisplayName("(3,1) == <1>[]")
                        public void shouldRemovePotentialCandidatesFromFieldThreeOne() throws Exception {
                            assertThat(sudoku.getCandidates(3, 1), is(equalTo(Set.of())));
                            assertThat(sudoku.get(3, 1), is(equalTo(FieldValue.of(1))));
                        }

                        @Test
                        @DisplayName("(0,2) == [2,4]")
                        public void shouldRemovePotentialCandidatesFromFieldZeroTwo() throws Exception {
                            Set<FieldValue> candidates = sudoku.getCandidates(0, 2);

                            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(2), FieldValue.of(4)))));
                        }

                        @Test
                        @DisplayName("(0,3) == [2,4]")
                        public void shouldRemovePotentialCandidatesFromFieldZeroThree() throws Exception {
                            Set<FieldValue> candidates = sudoku.getCandidates(0, 3);

                            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(2), FieldValue.of(4)))));
                        }

                        @Test
                        @DisplayName("(1,2) == [1,3]")
                        public void shouldRemovePotentialCandidatesFromFieldOneTwo() throws Exception {
                            Set<FieldValue> candidates = sudoku.getCandidates(1, 2);

                            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(3)))));
                        }

                        @Test
                        @DisplayName("(1,3) == [1,3]")
                        public void shouldRemovePotentialCandidatesFromFieldOneThree() throws Exception {
                            Set<FieldValue> candidates = sudoku.getCandidates(1, 3);

                            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(3)))));
                        }

                        @Test
                        @DisplayName("(2,2) == [1,4]")
                        public void shouldRemovePotentialCandidatesFromFieldTwoTwo() throws Exception {
                            Set<FieldValue> candidates = sudoku.getCandidates(2, 2);

                            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(4)))));
                        }

                        @Test
                        @DisplayName("(2,3) == [1,4]")
                        public void shouldRemovePotentialCandidatesFromFieldTwoThree() throws Exception {
                            Set<FieldValue> candidates = sudoku.getCandidates(2, 3);

                            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(1), FieldValue.of(4)))));
                        }

                        @Test
                        @DisplayName("(3,2) == [2,3]")
                        public void shouldRemovePotentialCandidatesFromFieldThreeTwo() throws Exception {
                            Set<FieldValue> candidates = sudoku.getCandidates(3, 2);

                            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(2), FieldValue.of(3)))));
                        }

                        @Test
                        @DisplayName("(3,3) == [2,3]")
                        public void shouldRemovePotentialCandidatesFromFieldThreeThree() throws Exception {
                            Set<FieldValue> candidates = sudoku.getCandidates(3, 3);

                            assertThat(candidates, is(equalTo(Set.of(FieldValue.of(2), FieldValue.of(3)))));
                        }

                        @Test
                        public void shouldNotBeSolved() throws Exception {
                            assertThat(sudoku.isSolved(), is(equalTo(false)));
                        }

                        @Nested
                        @DisplayName("(3,3) -> 3")
                        class SetThreeThreeToThree {
                            @BeforeEach
                            public void setup() throws Exception {
                                sudoku.set(3, 3, 3);
                            }

                            @Test
                            @DisplayName("(3,3) == <3>[]")
                            public void shouldRemovePotentialCandidatesFromFieldThreeThree() throws Exception {
                                assertThat(sudoku.getCandidates(3, 3), is(equalTo(Set.of())));
                                assertThat(sudoku.get(3, 3), is(equalTo(FieldValue.of(3))));
                            }

                            @Test
                            @DisplayName("(3,2) == <2>[]")
                            public void shouldRemovePotentialCandidatesFromFieldThreeTwo() throws Exception {
                                assertThat(sudoku.getCandidates(3, 2), is(equalTo(Set.of())));
                                assertThat(sudoku.get(3, 2), is(equalTo(FieldValue.of(2))));
                            }

                            @Test
                            @DisplayName("(0,2) == <4>[]")
                            public void shouldRemovePotentialCandidatesFromFieldZeroTwo() throws Exception {
                                assertThat(sudoku.getCandidates(0, 2), is(equalTo(Set.of())));
                                assertThat(sudoku.get(0, 2), is(equalTo(FieldValue.of(4))));
                            }

                            @Test
                            @DisplayName("(0,3) == <2>[]")
                            public void shouldRemovePotentialCandidatesFromFieldZeroThree() throws Exception {
                                assertThat(sudoku.getCandidates(0, 3), is(equalTo(Set.of())));
                                assertThat(sudoku.get(0, 3), is(equalTo(FieldValue.of(2))));
                            }

                            @Test
                            @DisplayName("(2,2) == <1>[]")
                            public void shouldRemovePotentialCandidatesFromFieldTwoTwo() throws Exception {
                                assertThat(sudoku.getCandidates(2, 2), is(equalTo(Set.of())));
                                assertThat(sudoku.get(2, 2), is(equalTo(FieldValue.of(1))));
                            }

                            @Test
                            @DisplayName("(2,3) == <4>[]")
                            public void shouldRemovePotentialCandidatesFromFieldTwoThree() throws Exception {
                                assertThat(sudoku.getCandidates(2, 3), is(equalTo(Set.of())));
                                assertThat(sudoku.get(2, 3), is(equalTo(FieldValue.of(4))));
                            }

                            @Test
                            @DisplayName("(1,2) == <3>[]")
                            public void shouldRemovePotentialCandidatesFromFieldOneTwo() throws Exception {
                                assertThat(sudoku.getCandidates(1, 2), is(equalTo(Set.of())));
                                assertThat(sudoku.get(1, 2), is(equalTo(FieldValue.of(3))));
                            }

                            @Test
                            @DisplayName("(1,3) == <1>[]")
                            public void shouldRemovePotentialCandidatesFromFieldOneThree() throws Exception {
                                assertThat(sudoku.getCandidates(1, 3), is(equalTo(Set.of())));
                                assertThat(sudoku.get(1, 3), is(equalTo(FieldValue.of(1))));
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
