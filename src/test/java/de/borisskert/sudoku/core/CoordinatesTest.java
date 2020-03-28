package de.borisskert.sudoku.core;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;

class CoordinatesTest {

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

    @Test
    public void shouldCreateAbsoluteCoordinatesFromFirstQuadrantSubGrid() throws Exception {
        SubGridCoordinates subGrid = SubGridCoordinates.from(0, 0);
        WithinSubGridCoordinates withinSubGrid = WithinSubGridCoordinates.from(1, 1);

        AbsoluteCoordinates absolute = subGrid.toAbsolute(withinSubGrid, Size.of(2, 2));

        AbsoluteCoordinates expected = AbsoluteCoordinates.from(1, 1);
        assertThat(absolute, is(equalTo(expected)));
    }

    @Test
    public void shouldCreateAbsoluteCoordinatesFromForthQuadrantSubGridInTwoTwoSudoku() throws Exception {
        SubGridCoordinates subGrid = SubGridCoordinates.from(1, 1);
        WithinSubGridCoordinates withinSubGrid = WithinSubGridCoordinates.from(0, 1);

        AbsoluteCoordinates absolute = subGrid.toAbsolute(withinSubGrid, Size.of(2, 2));

        AbsoluteCoordinates expected = AbsoluteCoordinates.from(2, 3);
        assertThat(absolute, is(equalTo(expected)));
    }

    @Test
    public void shouldCreateAbsoluteCoordinatesFromForthQuadrantSubGridInThreeThreeSudoku() throws Exception {
        SubGridCoordinates subGrid = SubGridCoordinates.from(1, 1);
        WithinSubGridCoordinates withinSubGrid = WithinSubGridCoordinates.from(0, 1);

        AbsoluteCoordinates absolute = subGrid.toAbsolute(withinSubGrid, Size.of(3, 3));

        AbsoluteCoordinates expected = AbsoluteCoordinates.from(3, 4);
        assertThat(absolute, is(equalTo(expected)));
    }

    @Test
    public void shouldCreateAbsoluteCoordinatesFromForthQuadrantSubGridInFourThreeSudoku() throws Exception {
        SubGridCoordinates subGrid = SubGridCoordinates.from(1, 1);
        WithinSubGridCoordinates withinSubGrid = WithinSubGridCoordinates.from(1, 2);

        AbsoluteCoordinates absolute = subGrid.toAbsolute(withinSubGrid, Size.of(4, 3));

        AbsoluteCoordinates expected = AbsoluteCoordinates.from(5, 5);
        assertThat(absolute, is(equalTo(expected)));
    }
}