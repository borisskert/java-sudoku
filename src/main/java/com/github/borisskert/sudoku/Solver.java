package com.github.borisskert.sudoku;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Solver {

    private final ChangeDetection changeDetection;
    private final ChangeHistory changeHistory;
    private final SubGrids subGrids;
    private final Random random;

    private final RandomAccumulator<FieldValue> randomAccumulator;

    Solver(ChangeDetection changeDetection, ChangeHistory changeHistory, SubGrids subGrids) {
        this.changeDetection = changeDetection;
        this.changeHistory = changeHistory;
        this.subGrids = subGrids;
        this.random = new Random();
        this.randomAccumulator = new RandomAccumulator<>();
    }

    void solve() {
        while (!this.subGrids.areSolved()) {
            setupDefiniteFieldValues();

            if (!this.subGrids.areSolved()) {
                setupRandomField();
            }
        }
    }

    private void setupRandomField() {
        Optional<FieldWithAbsoluteCoordinates> maybeField = selectRandomField();

        maybeField.ifPresentOrElse(selectedField -> {
            FieldValue value = selectedField.getCandidates()
                    .stream()
                    .reduce(randomAccumulator)
                    .get();

            changeHistory.performTrial(selectedField.getAbsoluteX(), selectedField.getAbsoluteY(), value.getValue());
            changeDetection.detectChanges();
        }, () -> { throw new RuntimeException("No empty field found"); });
    }

    private void setupDefiniteFieldValues() {
        int count = 0;
        do {
            count = setupDefiniteFieldValues(this::getSubGridFieldsWithDefiniteFieldValues);

            if (!this.subGrids.areSolved()) {
                count += setupDefiniteFieldValues(this::getLinesWithDefiniteFieldValues);
            }

            if (!this.subGrids.areSolved()) {
                count += setupDefiniteFieldValues(this::getColumnsWithDefiniteFieldValues);
            }

            changeDetection.detectChanges();
        }
        while (!this.subGrids.areSolved() && count > 0);
    }

    private int setupDefiniteFieldValues(Supplier<Collection<FieldsWithAbsoluteCoordinates>> fieldsSupplier) {
        int counter = 0;

        Collection<FieldsWithAbsoluteCoordinates> fieldsCollection = fieldsSupplier.get();

        while (fieldsCollection.size() > 0) {
            FieldsWithAbsoluteCoordinates selectedFields = fieldsCollection.iterator().next();
            Collection<FieldValue> definiteFieldValues = selectedFields.getDefiniteFieldValues();
            FieldValue selectedFieldValue = definiteFieldValues.iterator().next();

            FieldWithAbsoluteCoordinates selectedField = selectedFields.getFields().stream()
                    .filter(Field::isEmpty)
                    .filter(field -> field.getCandidates().contains(selectedFieldValue))
                    .findFirst().get();

            changeHistory.performDefinite(selectedField.getAbsoluteX(), selectedField.getAbsoluteY(), selectedFieldValue.getValue());
            counter++;

            changeDetection.detectChanges();

            fieldsCollection = fieldsSupplier.get();
        }

        return counter;
    }

    private Collection<FieldsWithAbsoluteCoordinates> getLinesWithDefiniteFieldValues() {
        return subGrids.getLines().stream()
                .filter(line -> line.getDefiniteFieldValues().size() > 0)
                .collect(Collectors.toUnmodifiableList());
    }

    private Collection<FieldsWithAbsoluteCoordinates> getColumnsWithDefiniteFieldValues() {
        Collection<FieldsWithAbsoluteCoordinates> columns = subGrids.getColumns();
        return columns.stream()
                .filter(column -> column.getDefiniteFieldValues().size() > 0)
                .collect(Collectors.toUnmodifiableList());
    }

    private Collection<FieldsWithAbsoluteCoordinates> getSubGridFieldsWithDefiniteFieldValues() {
        return this.subGrids.getSubGridFields().stream()
                .filter(subGrid -> subGrid.getDefiniteFieldValues().size() > 0)
                .collect(Collectors.toUnmodifiableList());
    }

    private static class RandomAccumulator<T> implements BinaryOperator<T> {
        private final Coin coin;

        private RandomAccumulator() {
            this.coin = new Coin();
        }

        private RandomAccumulator(long seed) {
            this.coin = new Coin(seed);
        }

        @Override
        public T apply(T value, T value2) {
            coin.flip();

            if (coin.isOnHeads()) {
                return value;
            } else {
                return value2;
            }
        }

        public static <T> BinaryOperator<T> newInstance(long seed) {
            return new RandomAccumulator<>(seed);
        }
    }

    private Optional<FieldWithAbsoluteCoordinates> selectRandomField() {
        Collection<FieldWithAbsoluteCoordinates> unresolvedFields = subGrids.getUnresolvedFields();
        Optional<Integer> minimumCountOfCandidates = unresolvedFields.stream()
                .map(Field::getCandidates)
                .map(Set::size)
                .min(Integer::compareTo);

        return minimumCountOfCandidates.map(min -> {
            List<FieldWithAbsoluteCoordinates> collect = unresolvedFields.stream()
                    .filter(field -> field.getCandidates().size() == min)
                    .collect(Collectors.toList());

            Collections.shuffle(collect, random);

            return collect.get(0);
        });
    }
}
