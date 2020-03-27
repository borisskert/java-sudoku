package de.borisskert.sudoku.core;

import org.junit.jupiter.api.Test;

import static de.borisskert.sudoku.core.Fields.empty;
import static de.borisskert.sudoku.core.Print.format;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PrintTest {

    @Test
    public void shouldThrowOnNullSize() throws Exception {
        assertThrows(NullPointerException.class, () -> format(null, empty()));
    }

    @Test
    public void shouldThrowOnNullFields() throws Exception {
        assertThrows(NullPointerException.class, () -> format(Size.of(2, 2), null));
    }
}
