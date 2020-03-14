package com.github.borisskert.sudoku;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SubGrid {

    private final Set<FieldValue> defaultCandidates;

    private final List<Field> fields = new ArrayList<>();

    private final int x;
    private final int y;

    private final int sizeX;
    private final int sizeY;

    public SubGrid(int x, int y, int sizeX, int sizeY) {
        this.x = x;
        this.y = y;

        this.sizeX = sizeX;
        this.sizeY = sizeY;

        defaultCandidates = createDefaultCandidates();

        fillWithFields();
        bindAllFields();
    }

    private Set<FieldValue> createDefaultCandidates() {
        int candidates = sizeX * sizeX;

        return IntStream.range(1, candidates + 1)
                .mapToObj(FieldValue::of)
                .collect(Collectors.toSet());
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
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
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

    public Collection<Field> getUnresolvedFields() {
        return fields.stream()
                .filter(Field::isEmpty)
                .collect(Collectors.toUnmodifiableList());
    }
}
