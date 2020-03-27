package de.borisskert.sudoku.core;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Candidates {

    private final Set<FieldValue> values;

    public Candidates(Set<FieldValue> values) {
        this.values = Collections.unmodifiableSet(values);
    }

    public boolean contains(FieldValue value) {
        return values.contains(value);
    }

    public int size() {
        return values.size();
    }

    public FieldValue any() {
        return values.stream()
                .sorted()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("More more candidates"));
    }

    public FieldValue lastOne() {
        if (values.size() > 1) {
            throw new IllegalStateException("More than one candidate");
        }

        if (values.size() < 1) {
            throw new IllegalStateException("No more candidates");
        }

        return values.iterator().next();
    }

    public boolean isDefinite() {
        return values.size() == 1;
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public Candidates without(FieldValue value) {
        Set<FieldValue> valuesWithoutSpecifiedValue = values.stream()
                .filter(v -> !v.equals(value))
                .collect(Collectors.toUnmodifiableSet());

        return new Candidates(valuesWithoutSpecifiedValue);
    }

    public Candidates with(FieldValue value) {
        assert value != null;

        Set<FieldValue> valuesWithSpecifiedValue = Stream.concat(
                values.stream(),
                Stream.of(value)
        ).collect(Collectors.toUnmodifiableSet());

        return new Candidates(valuesWithSpecifiedValue);
    }

    public Candidates without(Set<FieldValue> values) {
        Set<FieldValue> changedValues = this.values.stream()
                .filter(v -> !values.contains(v))
                .collect(Collectors.toUnmodifiableSet());

        return new Candidates(changedValues);
    }

    public Set<FieldValue> values() {
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidates that = (Candidates) o;
        return Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

    @Override
    public String toString() {
        return values.toString();
    }

    public static Candidates create(Size size) {
        return new Candidates(size.toFieldValues());
    }

    public static Candidates empty() {
        return new Candidates(Set.of());
    }

    public static Candidates of(Set<FieldValue> values) {
        return new Candidates(values);
    }

    public Stream<FieldValue> stream() {
        return this.values.stream();
    }
}
