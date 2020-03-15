package com.github.borisskert.sudoku;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FieldsWithAbsoluteCoordinates {

    private final Collection<FieldWithAbsoluteCoordinates> fields;

    private FieldsWithAbsoluteCoordinates(Collection<FieldWithAbsoluteCoordinates> fields) {
        this.fields = fields;
    }

    public static Collector<FieldWithAbsoluteCoordinates, List<FieldWithAbsoluteCoordinates>, FieldsWithAbsoluteCoordinates> collect() {
        return new FieldsCollector();
    }

    public static FieldsWithAbsoluteCoordinates of(Collection<FieldWithAbsoluteCoordinates> fields) {
        return new FieldsWithAbsoluteCoordinates(fields);
    }

    public Collection<FieldValue> getDefiniteFieldValues() {
        return countCandidates().entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toUnmodifiableList());
    }

    private Map<FieldValue, Integer> countCandidates() {
        Map<FieldValue, Integer> counts = new HashMap<>();

        for (FieldWithAbsoluteCoordinates field : fields) {
            for (FieldValue candidate : field.getCandidates()) {
                Integer count = counts.getOrDefault(candidate, 0);
                counts.put(candidate, count + 1);
            }
        }

        return counts;
    }

    public Collection<FieldWithAbsoluteCoordinates> getFields() {
        return Collections.unmodifiableCollection(fields);
    }

    private static class FieldsCollector implements Collector<FieldWithAbsoluteCoordinates, List<FieldWithAbsoluteCoordinates>, FieldsWithAbsoluteCoordinates> {
        @Override
        public Supplier<List<FieldWithAbsoluteCoordinates>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<FieldWithAbsoluteCoordinates>, FieldWithAbsoluteCoordinates> accumulator() {
            return List::add;
        }

        @Override
        public BinaryOperator<List<FieldWithAbsoluteCoordinates>> combiner() {
            return (left, right) -> {
                left.addAll(right);
                return left;
            };
        }

        @Override
        public Function<List<FieldWithAbsoluteCoordinates>, FieldsWithAbsoluteCoordinates> finisher() {
            return FieldsWithAbsoluteCoordinates::new;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.emptySet();
        }
    }
}
