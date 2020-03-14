package com.github.borisskert.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubGrid {

    private static final List<FieldValue> defaultCandidates = List.of(
            FieldValue.of(1),
            FieldValue.of(2),
            FieldValue.of(3),
            FieldValue.of(4)
    );

    private final List<Field> fields = new ArrayList<>();

    private final int x;
    private final int y;

    public SubGrid(int x, int y) {
        this.x = x;
        this.y = y;

        fillWithFields();
        bindAllFields();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Field get(int x, int y) {
        return fields.stream()
                .filter(field -> field.getX() == x)
                .filter(field -> field.getY() == y)
                .findFirst().get();
    }

    public boolean isNotSolved() {
        return fields.stream().anyMatch(Field::isEmpty);
    }

    private void fillWithFields() {
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                Field field = new Field(x, y, defaultCandidates);
                fields.add(field);
            }
        }
    }

    private void bindAllFields() {
        fields.forEach(this::bindToOthers);
    }

    private void bindToOthers(Field field) {
        fields.forEach(otherField -> {
            if (field != otherField) {
                field.register(otherField);
            }
        });
    }

    public void registerToX(SubGrid otherSubGrid) {
        fields.forEach(field -> {
            int x = field.getX();

            List<Field> fieldsForX = otherSubGrid.getFieldsForX(x);
            fieldsForX.forEach(field::register);
        });
    }

    public void registerToY(SubGrid otherSubGrid) {
        fields.forEach(field -> {
            int y = field.getY();

            List<Field> fieldsForY = otherSubGrid.getFieldsForY(y);
            fieldsForY.forEach(field::register);
        });
    }

    private List<Field> getFieldsForX(int x) {
        return fields.stream()
                .filter(field -> field.getX() == x)
                .collect(Collectors.toList());
    }

    private List<Field> getFieldsForY(int y) {
        return fields.stream()
                .filter(field -> field.getY() == y)
                .collect(Collectors.toList());
    }
}
