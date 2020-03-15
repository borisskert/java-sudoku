package com.github.borisskert.sudoku;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SubGrid {

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
        int candidates = sizeX * sizeY;

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

    public FieldWithAbsoluteCoordinates get(int x, int y) {
        return fields.stream()
                .filter(field -> field.getX() == x)
                .filter(field -> field.getY() == y)
                .map(absoluteCoordinates())
                .findFirst().get();
    }

    public boolean isNotSolved() {
        return fields.stream().anyMatch(Field::isEmpty);
    }

    private void fillWithFields() {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                Field field = new Field(new Coordinates(x, y), new HashSet<>(defaultCandidates));
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

    Collection<FieldWithAbsoluteCoordinates> getUnresolvedFields() {
        return fields.stream()
                .filter(Field::isEmpty)
                .filter(field -> field.getCandidates().size() > 0)
                .map(absoluteCoordinates())
                .collect(Collectors.toUnmodifiableList());
    }

    private Function<Field, FieldWithAbsoluteCoordinates> absoluteCoordinates() {
        return field -> field.withinSubGrid(
                x * sizeX,
                y * sizeY
        );
    }

    FieldsWithAbsoluteCoordinates getFields() {
        return fields.stream()
                .map(absoluteCoordinates())
                .collect(FieldsWithAbsoluteCoordinates.collect());
    }

    Collection<FieldWithAbsoluteCoordinates> getFieldsToBeSolved() {
        return fields.stream()
                .filter(field -> field.getCandidates().size() == 1)
                .map(absoluteCoordinates())
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String toString() {
        return "SubGrid{" +
                "defaultCandidates=" + defaultCandidates +
                ", fields=" + fields +
                ", x=" + x +
                ", y=" + y +
                ", sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                '}';
    }
}
