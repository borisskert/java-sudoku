package de.borisskert.sudoku.core;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class WithinSubGridCoordinatesTest {

    @Test
    public void shouldCreateAbsoluteCoordinatesFromFirstQuarterSubGridCoordinates() throws Exception {
        WithinSubGridCoordinates coordinates = WithinSubGridCoordinates.from(1, 1);
        AbsoluteCoordinates absolute = coordinates.absolute(Size.of(2, 2), SubGridCoordinates.from(0, 0));

        AbsoluteCoordinates expected = AbsoluteCoordinates.from(1, 1);

        assertThat(absolute, is(equalTo(expected)));
    }

    @Test
    public void shouldCreateAbsoluteCoordinatesFromFourthQuarterSubGridCoordinates() throws Exception {
        WithinSubGridCoordinates coordinates = WithinSubGridCoordinates.from(0, 0);
        AbsoluteCoordinates absolute = coordinates.absolute(Size.of(2, 2), SubGridCoordinates.from(1, 1));

        AbsoluteCoordinates expected = AbsoluteCoordinates.from(2, 2);

        assertThat(absolute, is(equalTo(expected)));
    }

    @Test
    public void shouldCreateAbsoluteCoordinatesFromFourthQuarterNonQuadraticSubGridCoordinates() throws Exception {
        WithinSubGridCoordinates coordinates = WithinSubGridCoordinates.from(1, 2);
        AbsoluteCoordinates absolute = coordinates.absolute(Size.of(4, 3), SubGridCoordinates.from(1, 1));

        AbsoluteCoordinates expected = AbsoluteCoordinates.from(5, 5);

        assertThat(absolute, is(equalTo(expected)));
    }
}