package de.borisskert.sudoku.core;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class CoordinatesTest {

    @Test
    public void shouldEqualOtherWithSameValues() throws Exception {
        AbsoluteCoordinates coordinates = AbsoluteCoordinates.from(123, 456);
        AbsoluteCoordinates other = AbsoluteCoordinates.from(123, 456);

        assertThat(coordinates, is(equalTo(other)));
        assertThat(other, is(equalTo(coordinates)));
        assertThat(coordinates, is(not(sameInstance(other))));
    }

    @Test
    public void shouldNotEqualOtherWhenDifferInX() throws Exception {
        AbsoluteCoordinates coordinates = AbsoluteCoordinates.from(123, 456);
        AbsoluteCoordinates other = AbsoluteCoordinates.from(124, 456);

        assertThat(coordinates, is(not(equalTo(other))));
        assertThat(other, is(not(equalTo(coordinates))));
        assertThat(coordinates, is(not(sameInstance(other))));
    }

    @Test
    public void shouldNotEqualOtherWhenDifferInY() throws Exception {
        AbsoluteCoordinates coordinates = AbsoluteCoordinates.from(123, 456);
        AbsoluteCoordinates other = AbsoluteCoordinates.from(123, 455);

        assertThat(coordinates, is(not(equalTo(other))));
        assertThat(other, is(not(equalTo(coordinates))));
        assertThat(coordinates, is(not(sameInstance(other))));
    }

    @Test
    public void shouldCreateFirstQuarterSubGridCoordinates() throws Exception {
        AbsoluteCoordinates coordinates = AbsoluteCoordinates.from(1, 1);
        WithinSubGridCoordinates withinSubGrid = coordinates.withinSubGrid(Size.of(2, 2));

        WithinSubGridCoordinates expected = WithinSubGridCoordinates.from(1, 1);

        assertThat(withinSubGrid, is(equalTo(expected)));
    }

    @Test
    public void shouldCreateForthQuarterSubGridCoordinates() throws Exception {
        AbsoluteCoordinates coordinates = AbsoluteCoordinates.from(2, 2);
        WithinSubGridCoordinates withinSubGrid = coordinates.withinSubGrid(Size.of(2, 2));

        WithinSubGridCoordinates expected = WithinSubGridCoordinates.from(0, 0);

        assertThat(withinSubGrid, is(equalTo(expected)));
    }

    @Test
    public void shouldCreateForthQuarterNonQuadraticSubGridCoordinates() throws Exception {
        AbsoluteCoordinates coordinates = AbsoluteCoordinates.from(5, 5);
        WithinSubGridCoordinates withinSubGrid = coordinates.withinSubGrid(Size.of(4, 3));

        WithinSubGridCoordinates expected = WithinSubGridCoordinates.from(1, 2);

        assertThat(withinSubGrid, is(equalTo(expected)));
    }

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
    public void shouldCreateSubGridCoordinatesForFirstQuarter() throws Exception {
        AbsoluteCoordinates coordinates = AbsoluteCoordinates.from(1, 1);
        SubGridCoordinates subGrid = coordinates.subGrid(Size.of(2, 2));

        SubGridCoordinates expected = SubGridCoordinates.from(0, 0);

        assertThat(subGrid, is(equalTo(expected)));
    }

    @Test
    public void shouldCreateSubGridCoordinatesForForthQuarter() throws Exception {
        AbsoluteCoordinates coordinates = AbsoluteCoordinates.from(2, 2);
        SubGridCoordinates subGrid = coordinates.subGrid(Size.of(2, 2));

        SubGridCoordinates expected = SubGridCoordinates.from(1, 1);

        assertThat(subGrid, is(equalTo(expected)));
    }

    @Test
    public void shouldCreateSubGridCoordinatesForForthQuarterInNonQuadratic() throws Exception {
        AbsoluteCoordinates coordinates = AbsoluteCoordinates.from(5, 5);
        SubGridCoordinates subGrid = coordinates.subGrid(Size.of(4, 3));

        SubGridCoordinates expected = SubGridCoordinates.from(1, 1);

        assertThat(subGrid, is(equalTo(expected)));
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