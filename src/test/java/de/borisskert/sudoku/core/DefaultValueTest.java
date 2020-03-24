package de.borisskert.sudoku.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class DefaultValueTest {

    private Size size22;
    private Size size32;
    private Size size33;

    @BeforeEach
    public void setup() throws Exception {
        size22 = Size.of(2, 2);
        size32 = Size.of(3, 2);
        size33 = Size.of(3, 3);
    }

    @Test
    public void shouldProvideDefaultValuesForSize22() throws Exception {
        assertThat(defaultValue22(0, 0), is(equalTo(FieldValue.of(1))));
        assertThat(defaultValue22(0, 1), is(equalTo(FieldValue.of(3))));
        assertThat(defaultValue22(1, 0), is(equalTo(FieldValue.of(2))));
        assertThat(defaultValue22(1, 1), is(equalTo(FieldValue.of(4))));

        assertThat(defaultValue22(0, 2), is(equalTo(FieldValue.of(2))));
        assertThat(defaultValue22(0, 3), is(equalTo(FieldValue.of(4))));
        assertThat(defaultValue22(1, 2), is(equalTo(FieldValue.of(3))));
        assertThat(defaultValue22(1, 3), is(equalTo(FieldValue.of(1))));

        assertThat(defaultValue22(2, 0), is(equalTo(FieldValue.of(3))));
        assertThat(defaultValue22(2, 1), is(equalTo(FieldValue.of(1))));
        assertThat(defaultValue22(3, 0), is(equalTo(FieldValue.of(4))));
        assertThat(defaultValue22(3, 1), is(equalTo(FieldValue.of(2))));

        assertThat(defaultValue22(2, 2), is(equalTo(FieldValue.of(4))));
        assertThat(defaultValue22(2, 3), is(equalTo(FieldValue.of(2))));
        assertThat(defaultValue22(3, 2), is(equalTo(FieldValue.of(1))));
        assertThat(defaultValue22(3, 3), is(equalTo(FieldValue.of(3))));
    }

    @Test
    public void shouldProvideDefaultValuesForSize32() throws Exception {
        assertThat(defaultValue32(0, 0), is(equalTo(FieldValue.of(1))));
        assertThat(defaultValue32(0, 1), is(equalTo(FieldValue.of(4))));
        assertThat(defaultValue32(1, 0), is(equalTo(FieldValue.of(2))));
        assertThat(defaultValue32(1, 1), is(equalTo(FieldValue.of(5))));
        assertThat(defaultValue32(2, 0), is(equalTo(FieldValue.of(3))));
        assertThat(defaultValue32(2, 1), is(equalTo(FieldValue.of(6))));

        assertThat(defaultValue32(0, 2), is(equalTo(FieldValue.of(2))));
        assertThat(defaultValue32(0, 3), is(equalTo(FieldValue.of(5))));
        assertThat(defaultValue32(1, 2), is(equalTo(FieldValue.of(3))));
        assertThat(defaultValue32(1, 3), is(equalTo(FieldValue.of(6))));
        assertThat(defaultValue32(2, 2), is(equalTo(FieldValue.of(4))));
        assertThat(defaultValue32(2, 3), is(equalTo(FieldValue.of(1))));

        assertThat(defaultValue32(0, 4), is(equalTo(FieldValue.of(3))));
        assertThat(defaultValue32(0, 5), is(equalTo(FieldValue.of(6))));
        assertThat(defaultValue32(1, 4), is(equalTo(FieldValue.of(4))));
        assertThat(defaultValue32(1, 5), is(equalTo(FieldValue.of(1))));
        assertThat(defaultValue32(2, 4), is(equalTo(FieldValue.of(5))));
        assertThat(defaultValue32(2, 5), is(equalTo(FieldValue.of(2))));

        assertThat(defaultValue32(3, 0), is(equalTo(FieldValue.of(4))));
        assertThat(defaultValue32(3, 1), is(equalTo(FieldValue.of(1))));
        assertThat(defaultValue32(4, 0), is(equalTo(FieldValue.of(5))));
        assertThat(defaultValue32(4, 1), is(equalTo(FieldValue.of(2))));
        assertThat(defaultValue32(5, 0), is(equalTo(FieldValue.of(6))));
        assertThat(defaultValue32(5, 1), is(equalTo(FieldValue.of(3))));

        assertThat(defaultValue32(3, 2), is(equalTo(FieldValue.of(5))));
        assertThat(defaultValue32(3, 3), is(equalTo(FieldValue.of(2))));
        assertThat(defaultValue32(4, 2), is(equalTo(FieldValue.of(6))));
        assertThat(defaultValue32(4, 3), is(equalTo(FieldValue.of(3))));
        assertThat(defaultValue32(5, 2), is(equalTo(FieldValue.of(1))));
        assertThat(defaultValue32(5, 3), is(equalTo(FieldValue.of(4))));

        assertThat(defaultValue32(3, 4), is(equalTo(FieldValue.of(6))));
        assertThat(defaultValue32(3, 5), is(equalTo(FieldValue.of(3))));
        assertThat(defaultValue32(4, 4), is(equalTo(FieldValue.of(1))));
        assertThat(defaultValue32(4, 5), is(equalTo(FieldValue.of(4))));
        assertThat(defaultValue32(5, 4), is(equalTo(FieldValue.of(2))));
        assertThat(defaultValue32(5, 5), is(equalTo(FieldValue.of(5))));

    }

    @Test
    public void shouldProvideDefaultValuesForSize33() throws Exception {
        assertThat(defaultValue33(0, 0), is(equalTo(FieldValue.of(1))));
        assertThat(defaultValue33(0, 1), is(equalTo(FieldValue.of(4))));
        assertThat(defaultValue33(0, 2), is(equalTo(FieldValue.of(7))));
        assertThat(defaultValue33(1, 0), is(equalTo(FieldValue.of(2))));
        assertThat(defaultValue33(1, 1), is(equalTo(FieldValue.of(5))));
        assertThat(defaultValue33(1, 2), is(equalTo(FieldValue.of(8))));
        assertThat(defaultValue33(2, 0), is(equalTo(FieldValue.of(3))));
        assertThat(defaultValue33(2, 1), is(equalTo(FieldValue.of(6))));
        assertThat(defaultValue33(2, 2), is(equalTo(FieldValue.of(9))));

        assertThat(defaultValue33(0, 3), is(equalTo(FieldValue.of(2))));
        assertThat(defaultValue33(0, 4), is(equalTo(FieldValue.of(5))));
        assertThat(defaultValue33(0, 5), is(equalTo(FieldValue.of(8))));
        assertThat(defaultValue33(1, 3), is(equalTo(FieldValue.of(3))));
        assertThat(defaultValue33(1, 4), is(equalTo(FieldValue.of(6))));
        assertThat(defaultValue33(1, 5), is(equalTo(FieldValue.of(9))));
        assertThat(defaultValue33(2, 3), is(equalTo(FieldValue.of(4))));
        assertThat(defaultValue33(2, 4), is(equalTo(FieldValue.of(7))));
        assertThat(defaultValue33(2, 5), is(equalTo(FieldValue.of(1))));

        assertThat(defaultValue33(0, 6), is(equalTo(FieldValue.of(3))));
        assertThat(defaultValue33(0, 7), is(equalTo(FieldValue.of(6))));
        assertThat(defaultValue33(0, 8), is(equalTo(FieldValue.of(9))));
        assertThat(defaultValue33(1, 6), is(equalTo(FieldValue.of(4))));
        assertThat(defaultValue33(1, 7), is(equalTo(FieldValue.of(7))));
        assertThat(defaultValue33(1, 8), is(equalTo(FieldValue.of(1))));
        assertThat(defaultValue33(2, 6), is(equalTo(FieldValue.of(5))));
        assertThat(defaultValue33(2, 7), is(equalTo(FieldValue.of(8))));
        assertThat(defaultValue33(2, 8), is(equalTo(FieldValue.of(2))));

        assertThat(defaultValue33(3, 0), is(equalTo(FieldValue.of(4))));
        assertThat(defaultValue33(3, 1), is(equalTo(FieldValue.of(7))));
        assertThat(defaultValue33(3, 2), is(equalTo(FieldValue.of(1))));
        assertThat(defaultValue33(4, 0), is(equalTo(FieldValue.of(5))));
        assertThat(defaultValue33(4, 1), is(equalTo(FieldValue.of(8))));
        assertThat(defaultValue33(4, 2), is(equalTo(FieldValue.of(2))));
        assertThat(defaultValue33(5, 0), is(equalTo(FieldValue.of(6))));
        assertThat(defaultValue33(5, 1), is(equalTo(FieldValue.of(9))));
        assertThat(defaultValue33(5, 2), is(equalTo(FieldValue.of(3))));

        assertThat(defaultValue33(3, 3), is(equalTo(FieldValue.of(5))));
        assertThat(defaultValue33(3, 4), is(equalTo(FieldValue.of(8))));
        assertThat(defaultValue33(3, 5), is(equalTo(FieldValue.of(2))));
        assertThat(defaultValue33(4, 3), is(equalTo(FieldValue.of(6))));
        assertThat(defaultValue33(4, 4), is(equalTo(FieldValue.of(9))));
        assertThat(defaultValue33(4, 5), is(equalTo(FieldValue.of(3))));
        assertThat(defaultValue33(5, 3), is(equalTo(FieldValue.of(7))));
        assertThat(defaultValue33(5, 4), is(equalTo(FieldValue.of(1))));
        assertThat(defaultValue33(5, 5), is(equalTo(FieldValue.of(4))));

        assertThat(defaultValue33(3, 6), is(equalTo(FieldValue.of(6))));
        assertThat(defaultValue33(3, 7), is(equalTo(FieldValue.of(9))));
        assertThat(defaultValue33(3, 8), is(equalTo(FieldValue.of(3))));
        assertThat(defaultValue33(4, 6), is(equalTo(FieldValue.of(7))));
        assertThat(defaultValue33(4, 7), is(equalTo(FieldValue.of(1))));
        assertThat(defaultValue33(4, 8), is(equalTo(FieldValue.of(4))));
        assertThat(defaultValue33(5, 6), is(equalTo(FieldValue.of(8))));
        assertThat(defaultValue33(5, 7), is(equalTo(FieldValue.of(2))));
        assertThat(defaultValue33(5, 8), is(equalTo(FieldValue.of(5))));

        assertThat(defaultValue33(6, 0), is(equalTo(FieldValue.of(7))));
        assertThat(defaultValue33(6, 1), is(equalTo(FieldValue.of(1))));
        assertThat(defaultValue33(6, 2), is(equalTo(FieldValue.of(4))));
        assertThat(defaultValue33(7, 0), is(equalTo(FieldValue.of(8))));
        assertThat(defaultValue33(7, 1), is(equalTo(FieldValue.of(2))));
        assertThat(defaultValue33(7, 2), is(equalTo(FieldValue.of(5))));
        assertThat(defaultValue33(8, 0), is(equalTo(FieldValue.of(9))));
        assertThat(defaultValue33(8, 1), is(equalTo(FieldValue.of(3))));
        assertThat(defaultValue33(8, 2), is(equalTo(FieldValue.of(6))));

        assertThat(defaultValue33(6, 3), is(equalTo(FieldValue.of(8))));
        assertThat(defaultValue33(6, 4), is(equalTo(FieldValue.of(2))));
        assertThat(defaultValue33(6, 5), is(equalTo(FieldValue.of(5))));
        assertThat(defaultValue33(7, 3), is(equalTo(FieldValue.of(9))));
        assertThat(defaultValue33(7, 4), is(equalTo(FieldValue.of(3))));
        assertThat(defaultValue33(7, 5), is(equalTo(FieldValue.of(6))));
        assertThat(defaultValue33(8, 3), is(equalTo(FieldValue.of(1))));
        assertThat(defaultValue33(8, 4), is(equalTo(FieldValue.of(4))));
        assertThat(defaultValue33(8, 5), is(equalTo(FieldValue.of(7))));

        assertThat(defaultValue33(6, 6), is(equalTo(FieldValue.of(9))));
        assertThat(defaultValue33(6, 7), is(equalTo(FieldValue.of(3))));
        assertThat(defaultValue33(6, 8), is(equalTo(FieldValue.of(6))));
        assertThat(defaultValue33(7, 6), is(equalTo(FieldValue.of(1))));
        assertThat(defaultValue33(7, 7), is(equalTo(FieldValue.of(4))));
        assertThat(defaultValue33(7, 8), is(equalTo(FieldValue.of(7))));
        assertThat(defaultValue33(8, 6), is(equalTo(FieldValue.of(2))));
        assertThat(defaultValue33(8, 7), is(equalTo(FieldValue.of(5))));
        assertThat(defaultValue33(8, 8), is(equalTo(FieldValue.of(8))));

    }

    private FieldValue defaultValue22(int x, int y) {
        AbsoluteCoordinates coordinates = AbsoluteCoordinates.from(x, y);
        return DefaultValue.within(size22).andFor(coordinates);
    }

    private FieldValue defaultValue32(int x, int y) {
        AbsoluteCoordinates coordinates = AbsoluteCoordinates.from(x, y);
        return DefaultValue.within(size32).andFor(coordinates);
    }

    private FieldValue defaultValue33(int x, int y) {
        AbsoluteCoordinates coordinates = AbsoluteCoordinates.from(x, y);
        return DefaultValue.within(size33).andFor(coordinates);
    }
}