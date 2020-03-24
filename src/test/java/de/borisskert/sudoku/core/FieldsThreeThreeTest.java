package de.borisskert.sudoku.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class FieldsThreeThreeTest {

    @Nested
    class CreateFilledFields {

        private Fields filled;
        private Size size;

        @BeforeEach
        public void setup() throws Exception {
            size = Size.of(3, 3);
            filled = Fields.createFilled(size);
        }

        @Test
        public void shouldPrintFilledFields() throws Exception {
            // @formatter:off
            String expected =
                    "╔═╤═╤═╦═╤═╤═╦═╤═╤═╗\n" +
                    "║1│2│3║4│5│6║7│8│9║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║4│5│6║7│8│9║1│2│3║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║7│8│9║1│2│3║4│5│6║\n" +
                    "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                    "║2│3│4║5│6│7║8│9│1║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║5│6│7║8│9│1║2│3│4║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║8│9│1║2│3│4║5│6│7║\n" +
                    "╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n" +
                    "║3│4│5║6│7│8║9│1│2║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║6│7│8║9│1│2║3│4│5║\n" +
                    "╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n" +
                    "║9│1│2║3│4│5║6│7│8║\n" +
                    "╚═╧═╧═╩═╧═╧═╩═╧═╧═╝\n";
            // @formatter:on
            assertThat(Formatter.format(size, filled), is(equalTo(expected)));
        }
    }
}