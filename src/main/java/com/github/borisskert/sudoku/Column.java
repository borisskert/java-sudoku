package com.github.borisskert.sudoku;

import java.util.stream.Stream;

class Column {

    private final Fields fields;

    private Column(Fields fields) {
        this.fields = fields;
    }

    public static Column of(Fields fields) {
        return new Column(fields);
    }

    public Fields resolved() {
        return fields.resolve();
    }

    public Column withValueAt(AbsoluteCoordinates coordinates, FieldValue value) {
        Fields changedFields = this.fields.withValueAt(coordinates, value);
        return new Column(changedFields);
    }

    public Stream<Field> stream() {
        return fields.stream();
    }

    public boolean has(AbsoluteCoordinates other) {
        return fields.stream().anyMatch(field -> field.hasSameX(other));
    }

    public Fields fields() {
        return fields;
    }

//    public static Set<Fields> createLines(Set<ImmutableSubGrid> subGrids, Size size) {
//        SubGridCoordinates subGridCoordinates = coordinates.subGridCoordinates(size);
//        WithinSubGridCoordinates withinSubGrid = coordinates.withinSubGrid(size);
//
//        Set<ImmutableField> fieldsOfLine = subGrids.stream()
//                .filter(subGrid -> subGrid.hasSameY(subGridCoordinates))
//                .flatMap(subGrid -> subGrid.getFieldsForY(withinSubGrid).stream())
//                .collect(Collectors.toUnmodifiableSet());
//
//        return Fields.of(fieldsOfLine);
//    }
//
//    public static Set<Fields> create(Set<ImmutableField> fields, Size size) {
//        return size.toAbsoluteCoordinates().stream()
//                .map(AbsoluteCoordinates::yOnly)
//                .distinct()
//                .map(y -> getFieldsOfLine(fields, y, size))
//                .collect(Collectors.toUnmodifiableSet());
//    }
//
//    private static Fields getFieldsOfLine(Set<ImmutableField> fields, AbsoluteCoordinates coordinates, Size size) {
//        WithinSubGridCoordinates withinSubGrid = coordinates.withinSubGrid(size);
//
//        Set<ImmutableField> fieldsOfLine = fields.stream()
//                .filter(field -> field.hasSameY(withinSubGrid))
//                .collect(Collectors.toUnmodifiableSet());
//
//        return Fields.of(fieldsOfLine);
//    }


}
