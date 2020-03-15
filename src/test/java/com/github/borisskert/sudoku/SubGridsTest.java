package com.github.borisskert.sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class SubGridsTest {

    private SubGrids subgrids;

    @BeforeEach
    public void setup() throws Exception {
        subgrids = new SubGrids(2, 2);
    }

    @Test
    public void shouldProvideFourColumns() throws Exception {
        Collection<FieldsWithAbsoluteCoordinates> columns = subgrids.getColumns();

        assertThat(columns.size(), is(equalTo(4)));
    }

    @Test
    public void shouldProvideFourLines() throws Exception {
        Collection<FieldsWithAbsoluteCoordinates> lines = subgrids.getLines();

        assertThat(lines.size(), is(equalTo(4)));
    }
}
