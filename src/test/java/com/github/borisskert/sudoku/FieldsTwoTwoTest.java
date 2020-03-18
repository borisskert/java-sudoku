package com.github.borisskert.sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class FieldsTwoTwoTest {

    private Fields fields;

    private AbsoluteCoordinates zeroZero;
    private AbsoluteCoordinates zeroOne;
    private AbsoluteCoordinates oneZero;
    private AbsoluteCoordinates oneOne;

    private Field fieldZeroZero;
    private Field fieldZeroOne;
    private Field fieldOneZero;
    private Field fieldOneOne;

    @BeforeEach
    public void setup() throws Exception {
        zeroZero = AbsoluteCoordinates.from(0, 0);
        zeroOne = AbsoluteCoordinates.from(0, 1);
        oneZero = AbsoluteCoordinates.from(1, 0);
        oneOne = AbsoluteCoordinates.from(1, 1);

        fieldZeroZero = Field.empty(zeroZero, Size.from(2, 2));
        fieldZeroOne = Field.empty(zeroOne, Size.from(2, 2));
        fieldOneZero = Field.empty(oneZero, Size.from(2, 2));
        fieldOneOne = Field.empty(oneOne, Size.from(2, 2));

        fields = Fields.of(Set.of(fieldZeroZero, fieldZeroOne, fieldOneZero, fieldOneOne));
    }

    @Test
    public void shouldNotDetectChanges() throws Exception {
        assertThat(fields.resolve(), is(equalTo(fields)));
    }

    @Nested
    class SetZeroZero {
        private Fields changedFields;

        @BeforeEach
        public void setup() throws Exception {
            changedFields = fields.withValueAt(zeroZero, FieldValue.of(1));
        }

        @Test
        public void shouldChangeFields() throws Exception {
            Fields expectedFields = Fields.of(Set.of(
                    fieldZeroZero.withValue(FieldValue.of(1)),
                    fieldZeroOne.withoutCandidate(FieldValue.of(1)),
                    fieldOneZero.withoutCandidate(FieldValue.of(1)),
                    fieldOneOne.withoutCandidate(FieldValue.of(1))
            ));

            assertThat(changedFields, is(equalTo(expectedFields)));
        }

        @Test
        public void shouldGetChangedField() throws Exception {
            Field changedField = changedFields.get(zeroZero);

            Field expectedField = fieldZeroZero.withValue(FieldValue.of(1));

            assertThat(changedField, is(equalTo(expectedField)));
        }

        @Test
        public void shouldNotDetectChanges() throws Exception {
            assertThat(changedFields.resolve(), is(equalTo(changedFields)));
        }

        @Nested
        class SetOneZero {
            private Fields changedFields;

            @BeforeEach
            public void setup() throws Exception {
                changedFields = SetZeroZero.this.changedFields.withValueAt(oneZero, FieldValue.of(2));
            }

            @Test
            public void shouldChangeFields() throws Exception {
                Fields expectedFields = Fields.of(Set.of(
                        fieldZeroZero.withValue(FieldValue.of(1)),
                        fieldZeroOne.withoutCandidate(FieldValue.of(1)).withoutCandidate(FieldValue.of(2)),
                        fieldOneZero.withValue(FieldValue.of(2)),
                        fieldOneOne.withoutCandidate(FieldValue.of(1)).withoutCandidate(FieldValue.of(2))
                ));

                assertThat(changedFields, is(equalTo(expectedFields)));
            }

            @Test
            public void shouldGetChangedField() throws Exception {
                Field changedField = changedFields.get(oneZero);

                Field expectedField = fieldOneZero.withValue(FieldValue.of(2));

                assertThat(changedField, is(equalTo(expectedField)));
            }

            @Test
            public void shouldNotDetectChanges() throws Exception {
                assertThat(changedFields.resolve(), is(equalTo(changedFields)));
            }

            @Nested
            class SetZeroOne {
                private Fields changedFields;

                @BeforeEach
                public void setup() throws Exception {
                    changedFields = SetOneZero.this.changedFields.withValueAt(zeroOne, FieldValue.of(3));
                }

                @Test
                public void shouldChangeFields() throws Exception {
                    Fields expectedFields = Fields.of(Set.of(
                            fieldZeroZero.withValue(FieldValue.of(1)),
                            fieldZeroOne.withValue(FieldValue.of(3)),
                            fieldOneZero.withValue(FieldValue.of(2)),
                            fieldOneOne.withoutCandidate(FieldValue.of(1)).withoutCandidate(FieldValue.of(2)).withoutCandidate(FieldValue.of(3))
                    ));

                    assertThat(changedFields, is(equalTo(expectedFields)));
                }

                @Test
                public void shouldGetChangedField() throws Exception {
                    Field changedField = changedFields.get(zeroOne);

                    Field expectedField = fieldZeroOne.withValue(FieldValue.of(3));

                    assertThat(changedField, is(equalTo(expectedField)));
                }

                @Test
                public void shouldDetectChanges() throws Exception {
                    Fields expectedFields = Fields.of(Set.of(
                            fieldZeroZero.withValue(FieldValue.of(1)),
                            fieldZeroOne.withValue(FieldValue.of(3)),
                            fieldOneZero.withValue(FieldValue.of(2)),
                            fieldOneOne.withValue(FieldValue.of(4))
                    ));

                    assertThat(changedFields.resolve(), is(equalTo(expectedFields)));
                }

                @Test
                public void shouldResolve() throws Exception {
                    Fields expectedFields = Fields.of(Set.of(
                            fieldZeroZero.withValue(FieldValue.of(1)),
                            fieldZeroOne.withValue(FieldValue.of(3)),
                            fieldOneZero.withValue(FieldValue.of(2)),
                            fieldOneOne.withValue(FieldValue.of(4))
                    ));

                    assertThat(changedFields.resolve(), is(equalTo(expectedFields)));
                }
            }
        }
    }
}