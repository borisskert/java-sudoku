package de.borisskert.sudoku.core;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class ShuffleTest {

    @Test
    public void shouldShuffle22() throws Exception {
        Size size = Size.of(2, 2);

        Fields filled = Fields.createFilled(size);
        Fields shuffled = Shuffle.build().withSeed(123L).withSize(size).withFields(filled).shuffle();

        // @formatter:off
        String expected =
                "╔═╤═╦═╤═╗\n" +
                "║4│3║2│1║\n" +
                "╟─┼─╫─┼─╢\n" +
                "║2│1║4│3║\n" +
                "╠═╪═╬═╪═╣\n" +
                "║1│4║3│2║\n" +
                "╟─┼─╫─┼─╢\n" +
                "║3│2║1│4║\n" +
                "╚═╧═╩═╧═╝\n";
        // @formatter:on

        assertThat(Formatter.format(size, shuffled), is(equalTo(expected)));
    }

    @Test
    public void shouldShuffle33() throws Exception {
        Size size = Size.of(3, 3);

        Fields filled = Fields.createFilled(size);
        Fields shuffled = Shuffle.build().withSeed(123L).withSize(size).withFields(filled).shuffle();

        // @formatter:off
        String expected =
                "╔═╤═╤═╦═╤═╤═╦═╤═╤═╗\n" +
                "║2│3│1║6│4│5║9│8│7║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║5│6│4║9│7│8║3│2│1║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║8│9│7║3│1│2║6│5│4║\n" +
                "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                "║6│7│5║1│8│9║4│3│2║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║3│4│2║7│5│6║1│9│8║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║9│1│8║4│2│3║7│6│5║\n" +
                "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                "║7│8│6║2│9│1║5│4│3║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║1│2│9║5│3│4║8│7│6║\n" +
                "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                "║4│5│3║8│6│7║2│1│9║\n" +
                "╚═╧═╧═╩═╧═╧═╩═╧═╧═╝\n";
        // @formatter:on

        assertThat(Formatter.format(size, shuffled), is(equalTo(expected)));
    }

    @Test
    public void shouldShuffle43() throws Exception {
        Size size = Size.of(4, 3);

        Fields filled = Fields.createFilled(size);
        Fields shuffled = Shuffle.build().withSeed(123L).withSize(size).withFields(filled).shuffle();

        // @formatter:off
        String expected =
                "╔══╤══╤══╤══╦══╤══╤══╤══╦══╤══╤══╤══╗\n" +
                "║ 7│ 6│ 5│ 8║ 9│12│11│10║ 2│ 4│ 3│ 1║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║11│10│ 9│12║ 1│ 4│ 3│ 2║ 6│ 8│ 7│ 5║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║ 3│ 2│ 1│ 4║ 5│ 8│ 7│ 6║10│12│11│ 9║\n" +
                "╠══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╣\n" +
                "║ 8│ 7│ 6│ 9║10│ 1│12│11║ 3│ 5│ 4│ 2║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║12│11│10│ 1║ 2│ 5│ 4│ 3║ 7│ 9│ 8│ 6║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║ 4│ 3│ 2│ 5║ 6│ 9│ 8│ 7║11│ 1│12│10║\n" +
                "╠══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╣\n" +
                "║ 5│ 4│ 3│ 6║ 7│10│ 9│ 8║12│ 2│ 1│11║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║ 1│12│11│ 2║ 3│ 6│ 5│ 4║ 8│10│ 9│ 7║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║ 9│ 8│ 7│10║11│ 2│ 1│12║ 4│ 6│ 5│ 3║\n" +
                "╠══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╣\n" +
                "║ 6│ 5│ 4│ 7║ 8│11│10│ 9║ 1│ 3│ 2│12║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║ 2│ 1│12│ 3║ 4│ 7│ 6│ 5║ 9│11│10│ 8║\n" +
                "╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢\n" +
                "║10│ 9│ 8│11║12│ 3│ 2│ 1║ 5│ 7│ 6│ 4║\n" +
                "╚══╧══╧══╧══╩══╧══╧══╧══╩══╧══╧══╧══╝\n";
        // @formatter:on
        assertThat(Formatter.format(size, shuffled), is(equalTo(expected)));
    }

    @Test
    public void shouldShuffle26() throws Exception {
        Size size = Size.of(3, 5);

        Fields filled = Fields.createFilled(size);
        Fields shuffled = Shuffle.build().withSeed(123L).withSize(size).withFields(filled).shuffle();

        System.out.println(Formatter.format(size, shuffled));
    }
}