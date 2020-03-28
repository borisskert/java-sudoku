package de.borisskert.sudoku.core;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a set of still allowed {@link FieldValue}s in empty (unsolved) {@link Field}s.
 * Every {@link Candidates} instance is immutable.
 */
class Candidates {

    /* *****************************************************************************************************************
     * Static fields
     **************************************************************************************************************** */

    private static final Candidates EMPTY = new Candidates(Set.of());

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final Set<FieldValue> values;

    /* *****************************************************************************************************************
     * Constructor(s)
     **************************************************************************************************************** */

    /**
     * Prevent public instance creation.
     */
    private Candidates(Set<FieldValue> values) {
        this.values = Collections.unmodifiableSet(values);
    }

    /* *****************************************************************************************************************
     * Indicator methods
     **************************************************************************************************************** */

    /**
     * Indicates if this instance contains a specified {@link FieldValue}.
     *
     * @param value the {@link FieldValue} to be searched for.
     * @return {@code true} if this instance contains the specified {@link FieldValue}, {@code false} if not.
     */
    public boolean contains(FieldValue value) {
        return values.contains(value);
    }

    /**
     * Indicates if this instance is definite, this means it does contain one last, single {@link FieldValue}.
     *
     * @return {@code true} if this instance contains one last {@link FieldValue}, {@code false} if it contains more than one.
     */
    public boolean isDefinite() {
        return values.size() == 1;
    }

    /**
     * Indicates if this instance is empty, this means it does not contain any {@link FieldValue}s.
     *
     * @return {@code true} if this instance does not contain any {@link FieldValue}s, {@code false} if it contains at least one value.
     */
    public boolean isEmpty() {
        return values.isEmpty();
    }

    /* *****************************************************************************************************************
     * Accessor methods
     **************************************************************************************************************** */

    /**
     * Provides one single {@link FieldValue} of the containing.
     *
     * @return a {@link FieldValue}.
     * @throws IllegalStateException if this instance is empty.
     */
    public FieldValue any() {
        return values.stream()
                .sorted()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("More more candidates"));
    }

    /**
     * Provides the last one {@link FieldValue} candidate.
     *
     * @return a {@link FieldValue} candidate.
     * @throws IllegalStateException if this instance contains more or less than one {@link FieldValue} candidate.
     */
    public FieldValue lastOne() {
        if (values.size() > 1) {
            throw new IllegalStateException("More than one candidate");
        }

        if (values.size() < 1) {
            throw new IllegalStateException("No more candidates");
        }

        return values.iterator().next();
    }

    /**
     * Provides all containing {@link FieldValue} candidates as immutable {@link Set}.
     *
     * @return an immutable {@link Set} containing all {@link FieldValue} candidates.
     */
    public Set<FieldValue> values() {
        return values;
    }

    /**
     * Provides a {@link Stream} containing all corresponding {@link FieldValue} candidates of this instance.
     *
     * @return a new {@link Stream} instance.
     */
    public Stream<FieldValue> stream() {
        return this.values.stream();
    }

    /* *****************************************************************************************************************
     * Wither methods
     **************************************************************************************************************** */

    /**
     * Wither method to remove a specified {@link FieldValue} candidate.
     *
     * @param value the {@link FieldValue} to be removed.
     * @return a new {@link Candidates} instance not containing the specified {@link FieldValue} candidate.
     */
    public Candidates without(FieldValue value) {
        Set<FieldValue> valuesWithoutSpecifiedValue = values.stream()
                .filter(v -> !v.equals(value))
                .collect(Collectors.toUnmodifiableSet());

        return new Candidates(valuesWithoutSpecifiedValue);
    }

    /**
     * Wither method to add a specified {@link FieldValue} candidate.
     *
     * @param value the {@link FieldValue} to be added.
     * @return a new {@link Candidates} instance containing the specified {@link FieldValue} candidate.
     */
    public Candidates with(FieldValue value) {
        assert value != null;

        Set<FieldValue> valuesWithSpecifiedValue = Stream.concat(
                values.stream(),
                Stream.of(value)
        ).collect(Collectors.toUnmodifiableSet());

        return new Candidates(valuesWithSpecifiedValue);
    }

    /**
     * Wither method to remove a specified {@link Set} of {@link FieldValue} candidates.
     *
     * @param values the {@link FieldValue} candidates to be added.
     * @return a new {@link Candidates} instance containing all {@link FieldValue} candidates of the specified {@link Set}.
     */
    public Candidates without(Set<FieldValue> values) {
        Set<FieldValue> changedValues = this.values.stream()
                .filter(v -> !values.contains(v))
                .collect(Collectors.toUnmodifiableSet());

        return new Candidates(changedValues);
    }

    /* *****************************************************************************************************************
     * Overrides of Object
     **************************************************************************************************************** */

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

    /* *****************************************************************************************************************
     * Factory methods
     **************************************************************************************************************** */

    /**
     * Factory method to create {@link Candidates} with all the {@link FieldValue} candidates for the specified {@link Size}.
     *
     * @param size the grid size.
     * @return a new {@link Candidates} instance containing all possible {@link FieldValue} candidates for the specified {@link Size}.
     */
    public static Candidates createFor(Size size) {
        return new Candidates(size.toFieldValues());
    }

    /**
     * Factory method to return an empty {@link Candidates} instance.
     *
     * @return the empty {@link Candidates} instance.
     */
    public static Candidates empty() {
        return EMPTY;
    }

    /**
     * Factory method to create a {@link Candidates} instance containing all {@link FieldValue} candidates of the specified {@link Set}.
     *
     * @param values the {@link Set} containing the corresponding {@link FieldValue} candidates.
     * @return a new {@link Candidates} instance containing all {@link FieldValue} candidates of the specified {@link Set}.
     */
    public static Candidates of(Set<FieldValue> values) {
        return new Candidates(values);
    }
}
