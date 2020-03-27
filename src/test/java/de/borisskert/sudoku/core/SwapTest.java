package de.borisskert.sudoku.core;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class SwapTest {

    @Test
    public void shouldSwapLineZeroAndOne() throws Exception {
        Size size = Size.of(2, 2);
        Fields filled = Fields.createFilled(size);

        Fields withSwappedLines = Swap.fields(filled)
                .swapLines(0, 1);

        // @formatter:off
        String expected =
                "╔═╤═╦═╤═╗\n" +
                "║3│4║1│2║\n" +
                "╟─┼─╫─┼─╢\n" +
                "║1│2║3│4║\n" +
                "╠═╪═╬═╪═╣\n" +
                "║2│3║4│1║\n" +
                "╟─┼─╫─┼─╢\n" +
                "║4│1║2│3║\n" +
                "╚═╧═╩═╧═╝\n";
        // @formatter:on
        assertThat(Print.format(size, withSwappedLines), is(equalTo(expected)));
    }

    @Test
    public void shouldSwapLineThreeAndTwo() throws Exception {
        Size size = Size.of(2, 2);
        Fields filled = Fields.createFilled(size);

        Fields withSwappedLines = Swap.fields(filled)
                .swapLines(3, 2);

        // @formatter:off
        String expected =
                "╔═╤═╦═╤═╗\n" +
                "║1│2║3│4║\n" +
                "╟─┼─╫─┼─╢\n" +
                "║3│4║1│2║\n" +
                "╠═╪═╬═╪═╣\n" +
                "║4│1║2│3║\n" +
                "╟─┼─╫─┼─╢\n" +
                "║2│3║4│1║\n" +
                "╚═╧═╩═╧═╝\n";
        // @formatter:on
        assertThat(Print.format(size, withSwappedLines), is(equalTo(expected)));
    }

    @Test
    public void shouldSwapColumnZeroAndOne() throws Exception {
        Size size = Size.of(2, 2);
        Fields filled = Fields.createFilled(size);

        Fields withSwappedLines = Swap.fields(filled)
                .swapColumns(0, 1);

        // @formatter:off
        String expected =
                "╔═╤═╦═╤═╗\n" +
                "║2│1║3│4║\n" +
                "╟─┼─╫─┼─╢\n" +
                "║4│3║1│2║\n" +
                "╠═╪═╬═╪═╣\n" +
                "║3│2║4│1║\n" +
                "╟─┼─╫─┼─╢\n" +
                "║1│4║2│3║\n" +
                "╚═╧═╩═╧═╝\n";
        // @formatter:on
        assertThat(Print.format(size, withSwappedLines), is(equalTo(expected)));
    }

    @Test
    public void shouldSwapColumnThreeAndTwo() throws Exception {
        Size size = Size.of(2, 2);
        Fields filled = Fields.createFilled(size);

        Fields withSwappedLines = Swap.fields(filled)
                .swapColumns(3, 2);

        // @formatter:off
        String expected =
                "╔═╤═╦═╤═╗\n" +
                "║1│2║4│3║\n" +
                "╟─┼─╫─┼─╢\n" +
                "║3│4║2│1║\n" +
                "╠═╪═╬═╪═╣\n" +
                "║2│3║1│4║\n" +
                "╟─┼─╫─┼─╢\n" +
                "║4│1║3│2║\n" +
                "╚═╧═╩═╧═╝\n";
        // @formatter:on
        assertThat(Print.format(size, withSwappedLines), is(equalTo(expected)));
    }
}