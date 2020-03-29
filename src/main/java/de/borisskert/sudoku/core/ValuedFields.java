package de.borisskert.sudoku.core;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Implements the algorithm to change values within a sudoku puzzle considering these rules:
 * - a {@link FieldValue} has to be unique within a {@link Line}.
 * - a {@link FieldValue} has to be unique within a {@link Column}.
 * - a {@link FieldValue} has to be unique within a {@link SubGrid}
 */
class ValuedFields {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final Size size;
    private final Fields fields;

    /* *****************************************************************************************************************
     * Constructor(s)
     **************************************************************************************************************** */

    private ValuedFields(Size size, Fields fields) {
        this.size = size;
        this.fields = fields;
    }

    /* *****************************************************************************************************************
     * Wither methods
     **************************************************************************************************************** */

    public ValuedFields withValueAt(AbsoluteCoordinates coordinates, FieldValue fieldValue) {
        SubGrids subGrids = SubGrids.create(size, fields);
        Fields changedFields = subGrids.withValueAt(coordinates, fieldValue);

        Lines lines = Lines.of(changedFields);
        changedFields = lines.withValueAt(coordinates, fieldValue);

        Columns columns = Columns.of(changedFields);
        changedFields = columns.withValueAt(coordinates, fieldValue);

        return new ValuedFields(size, changedFields);
    }

    /* *****************************************************************************************************************
     * Accessor methods
     **************************************************************************************************************** */

    public Fields fields() {
        return fields;
    }

    /* *****************************************************************************************************************
     * Factory method(s)
     **************************************************************************************************************** */

    public static Builder forSize(Size size) {
        return new Builder(size);
    }

    public static Collector<Field, Set<Field>, ValuedFields> collect(Size size) {
        return new ValuedFieldsCollector(size);
    }

    /* *****************************************************************************************************************
     * Builder
     **************************************************************************************************************** */

    public static class Builder {
        private final Size size;

        private Builder(Size size) {
            this.size = size;
        }

        public ValuedFields and(Fields fields) {
            return new ValuedFields(size, fields);
        }

        public ValuedFields empty() {
            return new ValuedFields(size, Fields.create(size));
        }
    }

    /* *****************************************************************************************************************
     * Another inner class(es)
     **************************************************************************************************************** */

    private static class ValuedFieldsCollector implements Collector<Field, Set<Field>, ValuedFields> {
        private final Size size;

        public ValuedFieldsCollector(Size size) {
            this.size = size;
        }

        @Override
        public Supplier<Set<Field>> supplier() {
            return HashSet::new;
        }

        @Override
        public BiConsumer<Set<Field>, Field> accumulator() {
            return Set::add;
        }

        @Override
        public BinaryOperator<Set<Field>> combiner() {
            return (fields, fields2) -> {
                fields.addAll(fields2);
                return fields;
            };
        }

        @Override
        public Function<Set<Field>, ValuedFields> finisher() {
            return set -> {
                ValuedFields valued = ValuedFields.forSize(size).empty();

                for (Field field : set) {
                    if (field.isSolved()) {
                        valued = valued.withValueAt(field.absoluteCoordinates(), field.getValue());
                    }
                }

                return valued;
            };
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of();
        }
    }
}
