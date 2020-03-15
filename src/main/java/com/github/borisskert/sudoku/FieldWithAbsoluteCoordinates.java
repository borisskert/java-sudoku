package com.github.borisskert.sudoku;

import com.github.borisskert.observableproperties.OptionalProperty;

import java.util.Set;

public class FieldWithAbsoluteCoordinates extends Field {

    private final Coordinates absoluteCoordinates;

    protected FieldWithAbsoluteCoordinates(Coordinates coordinates, Set<FieldValue> candidates, OptionalProperty<FieldValue> valueProperty, Coordinates absoluteCoordinates) {
        super(coordinates, candidates, valueProperty);
        this.absoluteCoordinates = absoluteCoordinates;
    }

    public int getAbsoluteX() {
        return this.absoluteCoordinates.getX();
    }

    public int getAbsoluteY() {
        return this.absoluteCoordinates.getY();
    }
}
