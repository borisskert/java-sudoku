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
                }
            }
        }
    }
}
