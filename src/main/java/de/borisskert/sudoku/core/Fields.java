package de.borisskert.sudoku.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a set of {@link Field}s.
 * Every {@link Fields} instance is immutable.
 */
final class Fields {

    /* *****************************************************************************************************************
     * Private fields
     **************************************************************************************************************** */

    private final Set<Field> fields;

    /* *****************************************************************************************************************
     * Constructors
     **************************************************************************************************************** */

    private Fields(Set<Field> fields) {
        this.fields = fields;
    }

    /* *****************************************************************************************************************
     * Indicator methods
     **************************************************************************************************************** */

    public boolean areSolved() {
        return fields.stream()
                .noneMatch(Field::isEmpty);
    }

    /* *****************************************************************************************************************
     * Accessor methods
     **************************************************************************************************************** */

    public Stream<Field> stream() {
        return fields.stream();
    }

    public Field get(WithinSubGridCoordinates coordinates) {
        return fields.stream()
                .filter(field -> field.has(coordinates))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cannot find field with within-sub-grid coordinates " + coordinates));
    }

    public Field get(AbsoluteCoordinates coordinates) {
        return searchWith(coordinates)
                .orElseThrow(() -> new RuntimeException("Cannot find field " + coordinates));
    }

    public Optional<Field> searchWith(AbsoluteCoordinates coordinates) {
        return fields.stream()
                .filter(field -> field.has(coordinates))
                .findFirst();
    }


    public Set<Field> filterSubGridOnly(SubGridCoordinates coordinates) {
        return fields.stream()
                .filter(field -> field.isInSubGrid(coordinates))
                .collect(Collectors.toUnmodifiableSet());
    }

    public Fields filterColumnOnly(AbsoluteCoordinates coordinates) {
        Set<Field> lineFields = fields.stream()
                .filter(f -> f.hasSameX(coordinates))
                .collect(Collectors.toUnmodifiableSet());

        return Fields.of(lineFields);
    }

    public Fields filterLineOnly(AbsoluteCoordinates coordinates) {
        Set<Field> lineFields = fields.stream()
                .filter(f -> f.hasSameY(coordinates))
                .collect(Collectors.toUnmodifiableSet());

        return Fields.of(lineFields);
    }

    /* *****************************************************************************************************************
     * Wither methods
     **************************************************************************************************************** */

    public Fields resolve() {
        Fields beforeChanges;
        Fields changedFields = this;

        do {
            beforeChanges = changedFields;

            changedFields = changedFields.solveFieldValues();
            changedFields = changedFields.clearCandidates();
        } while (!beforeChanges.equals(changedFields));

        return changedFields;
    }

    public Fields withValueAt(WithinSubGridCoordinates coordinates, FieldValue fieldValue) {
        throwIfValueTaken(coordinates, fieldValue);

        Set<Field> updatedFields = fields.stream()
                .map(field -> {
                    if (field.has(coordinates)) {
                        return field.withValue(fieldValue);
                    } else {
                        return field.withoutCandidate(fieldValue);
                    }
                }).collect(Collectors.toUnmodifiableSet());

        return new Fields(updatedFields);
    }

    public Fields withValueAt(AbsoluteCoordinates coordinates, FieldValue fieldValue) {
        throwIfValueTaken(coordinates, fieldValue);

        Set<Field> updatedFields = fields.stream()
                .map(field -> {
                    if (field.has(coordinates)) {
                        return field.withValue(fieldValue);
                    } else {
                        return field.withoutCandidate(fieldValue);
                    }
                }).collect(Collectors.toUnmodifiableSet());

        return new Fields(updatedFields);
    }

    public Candidates definiteCandidates() {
        Set<FieldValue> values = countCandidates().entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toUnmodifiableSet());

        return Candidates.of(values);
    }

    public Fields clearCandidates() {
        Set<FieldValue> values = values();

        Set<Field> clearedCandidates = fields.stream()
                .map(field -> field.withoutCandidates(values))
                .collect(Collectors.toUnmodifiableSet());

        return Fields.of(clearedCandidates);
    }

    /* *****************************************************************************************************************
     * Overrides of Object
     **************************************************************************************************************** */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fields fields1 = (Fields) o;
        return Objects.equals(fields, fields1.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fields);
    }

    @Override
    public String toString() {
        return "{" + fields + '}';
    }

    /* *****************************************************************************************************************
     * Factory methods and collectors
     **************************************************************************************************************** */

    static Fields of(Set<Field> fields) {
        return new Fields(fields);
    }

    static Fields create(Size size) {
        Set<Field> createdFields = size.toAbsoluteCoordinates().stream()
                .map(coordinates -> Field.empty(coordinates, size))
                .collect(Collectors.toUnmodifiableSet());

        return new Fields(createdFields);
    }

    static Fields empty() {
        return new Fields(Set.of());
    }

    static Fields createFilled(Size size) {
        Set<Field> createdFields = size.toAbsoluteCoordinates().stream()
                .map(coordinates -> Field.filled(coordinates, size, DefaultValue.within(size).andFor(coordinates)))
                .collect(Collectors.toUnmodifiableSet());

        return new Fields(createdFields);
    }

    public static Collector<Fields, Set<Field>, Fields> collect() {
        return new FieldsCollector();
    }

    /* *****************************************************************************************************************
     * Private methods
     **************************************************************************************************************** */

    private Map<FieldValue, Integer> countCandidates() {
        Map<FieldValue, Integer> counts = new HashMap<>();

        for (Field field : fields) {
            for (FieldValue candidate : field.getCandidates().values()) {
                Integer count = counts.getOrDefault(candidate, 0);
                counts.put(candidate, count + 1);
            }
        }

        return counts;
    }

    private Fields solveFieldValues() {
        Set<Field> changedFields = fields.stream()
                .map(field -> {
                    if (field.canBeSolved()) {
                        return field.solve();
                    } else {
                        return field;
                    }
                })
                .collect(Collectors.toUnmodifiableSet());

        return Fields.of(changedFields);
    }

    private Set<FieldValue> values() {
        return fields.stream()
                .filter(Field::isSolved)
                .map(Field::getValue)
                .collect(Collectors.toUnmodifiableSet());
    }

    private void throwIfValueTaken(AbsoluteCoordinates coordinates, FieldValue fieldValue) {
        if (fields.stream()
                .filter(field -> !field.has(coordinates))
                .filter(Field::isSolved)
                .anyMatch(field -> field.hasValue(fieldValue))
        ) {
            throw new IllegalStateException("Value '" + fieldValue + "' already taken");
        }
    }

    private void throwIfValueTaken(WithinSubGridCoordinates coordinates, FieldValue fieldValue) {
        if (fields.stream()
                .filter(field -> !field.has(coordinates))
                .filter(Field::isSolved)
                .anyMatch(field -> field.hasValue(fieldValue))
        ) {
            throw new IllegalStateException("Value '" + fieldValue + "' already taken");
        }
    }

    /* *****************************************************************************************************************
     * Inner classes
     **************************************************************************************************************** */

    private static class FieldsCollector implements Collector<Fields, Set<Field>, Fields> {

        @Override
        public Supplier<Set<Field>> supplier() {
            return HashSet::new;
        }

        @Override
        public BiConsumer<Set<Field>, Fields> accumulator() {
            return (set, fields) -> set.addAll(fields.fields);
        }

        @Override
        public BinaryOperator<Set<Field>> combiner() {
            return (set, other) -> {
                set.addAll(other);
                return set;
            };
        }

        @Override
        public Function<Set<Field>, Fields> finisher() {
            return Fields::new;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of();
        }
    }
}
