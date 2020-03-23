package de.borisskert.sudoku.core;

public class ValuedFields {

    private final Size size;
    private final Fields fields;

    private ValuedFields(Size size, Fields fields) {
        this.size = size;
        this.fields = fields;
    }

    public ValuedFields withValueAt(AbsoluteCoordinates coordinates, FieldValue fieldValue) {
        SubGrids subGrids = SubGrids.create(size, fields);
        Fields changedFields = subGrids.withValueAt(coordinates, fieldValue);

        Lines lines = Lines.of(changedFields);
        changedFields = lines.withValueAt(coordinates, fieldValue);

        Columns columns = Columns.of(changedFields);
        changedFields = columns.withValueAt(coordinates, fieldValue);
        
        return new ValuedFields(size, changedFields);
    }
    
    public Fields fields() {
        return fields;
    }

    public static Builder forSize(Size size) {
        return new Builder(size);
    }
    
    public static class Builder {
        private final Size size;
        
        public Builder(Size size) {
            this.size = size;
        }
        
        public ValuedFields and(Fields fields) {
            return new ValuedFields(size, fields);
        }
    }
}
