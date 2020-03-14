package com.github.borisskert.sudoku;

import java.util.ArrayList;
import java.util.List;

public class Fields {

    private static final List<FieldValue> defaultCandidates = List.of(
            FieldValue.of(1),
            FieldValue.of(2),
            FieldValue.of(3),
            FieldValue.of(4)
    );

    private final Field[][] fields = new Field[2][2];

    public Fields() {
        fillWithFields(fields);
    }

    public Field get(int x, int y) {
        return fields[x][y];
    }

    private void fillWithFields(Field[][] fields) {
        List<Field> allFields = new ArrayList<>();

        for (int x = 0; x < fields.length; x++) {
            for (int y = 0; y < fields[x].length; y++) {
                Field field = new Field(x, y, defaultCandidates);
                fields[x][y] = field;
                allFields.add(field);
            }
        }

        bindAllFields(fields, allFields);
    }

    private void bindAllFields(Field[][] fields, List<Field> allFields) {
        for (int x = 0; x < fields.length; x++) {
            for (int y = 0; y < fields[x].length; y++) {
                Field field = fields[x][y];

                bind(field, allFields);
            }
        }
    }

    private void bind(Field field, List<Field> allFields) {
        allFields.forEach(otherField -> {
            if (field != otherField) {
                field.register(otherField);
                otherField.register(field);
            }
        });
    }
}
