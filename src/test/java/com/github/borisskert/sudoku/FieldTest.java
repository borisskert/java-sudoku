package com.github.borisskert.sudoku;

import com.github.borisskert.observableproperties.SimpleOptionalProperty;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class FieldTest {

    @Test
    public void shouldCompareCandidateSize() throws Exception {
        Field one = new TestField(0, 0, Set.of(FieldValue.of(1), FieldValue.of(2)));
        Field two = new TestField(1, 0, Set.of(FieldValue.of(1)));

        assertThat(Field.COMPARATOR.compare(one, two), is(equalTo(1)));
    }

    @Test
    public void shouldWithNonEmptyField() throws Exception {
        Field one = new TestField(1, 0, Set.of(FieldValue.of(1)));
        Field two = new TestField(0, 0, FieldValue.of(2));

        assertThat(Field.COMPARATOR.compare(one, two), is(equalTo(-1)));
    }

    @Test
    public void shouldCompareXCoordinates() throws Exception {
        Field one = new TestField(0, 0, Set.of(FieldValue.of(2)));
        Field two = new TestField(1, 0, Set.of(FieldValue.of(1)));

        assertThat(Field.COMPARATOR.compare(one, two), is(equalTo(-1)));
    }

    @Test
    public void shouldCompareYCoordinates() throws Exception {
        Field one = new TestField(0, 0, Set.of(FieldValue.of(2)));
        Field two = new TestField(0, 1, Set.of(FieldValue.of(1)));

        assertThat(Field.COMPARATOR.compare(one, two), is(equalTo(-1)));
    }

    private static class TestField extends Field {

        public TestField(int x, int y, Set<FieldValue> candidates) {
            super(new Coordinates(x, y), candidates);
        }

        public TestField(int x, int y, FieldValue value) {
            super(new Coordinates(x, y), Set.of(), new SimpleOptionalProperty<>(value));
        }
    }
}