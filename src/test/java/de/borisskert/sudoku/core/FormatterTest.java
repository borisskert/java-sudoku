package de.borisskert.sudoku.core;

import org.junit.jupiter.api.Test;

import static de.borisskert.sudoku.core.Fields.empty;
import static de.borisskert.sudoku.core.Formatter.format;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FormatterTest {

    @Test
    public void shouldThrowOnNullSize() throws Exception {
        assertThrows(NullPointerException.class, () -> format(null, empty()));
    }

    @Test
    public void shouldThrowOnNullFields() throws Exception {
        assertThrows(NullPointerException.class, () -> format(Size.of(2, 2), null));
    }
}
