package com.github.borisskert.sudoku;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SubGrids {

    private final List<SubGrid> subGrids = new ArrayList<>();
    private final int sizeX;
    private final int sizeY;

    public SubGrids(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        initSubGrids();
        bindNeighborSubGrids();
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    private void initSubGrids() {
        for (int x = 0; x < sizeY; x++) {
            for (int y = 0; y < sizeX; y++) {
                subGrids.add(new SubGrid(x, y, sizeX, sizeY));
            }
        }
    }

    public FieldWithAbsoluteCoordinates getField(int x, int y) {
        SubGrid currentSubGrid = getSubGrid(x / sizeX, y / sizeY);

        int relativeX = x % sizeX;
        int relativeY = y % sizeY;

        return currentSubGrid.get(relativeX, relativeY);
    }

    private FieldsWithAbsoluteCoordinates getColumn(int x) {
        return subGrids.stream()
                .map(SubGrid::getFields)
                .flatMap(fields -> fields.getFields().stream())
                .filter(field -> field.getAbsoluteX() == x)
                .collect(FieldsWithAbsoluteCoordinates.collect());
    }

    private FieldsWithAbsoluteCoordinates getLine(int y) {
        return subGrids.stream()
                .map(SubGrid::getFields)
                .flatMap(fields -> fields.getFields().stream())
                .filter(field -> field.getAbsoluteY() == y)
                .collect(FieldsWithAbsoluteCoordinates.collect());
    }

    public boolean areSolved() {
        return subGrids.stream().noneMatch(SubGrid::isNotSolved);
    }

    final SubGrid getSubGrid(int x, int y) {
        return subGrids.stream()
                .filter(subGrid -> subGrid.getX() == x)
                .filter(subGrid -> subGrid.getY() == y)
                .findFirst().get();
    }

    private void bindNeighborSubGrids() {
        subGrids.forEach(subGrid -> {
            int x = subGrid.getX();
            int y = subGrid.getY();

            List<SubGrid> neighborSubGridsX = subGridsX(x);

            neighborSubGridsX.forEach(neighborSubGrid -> {
                if (subGrid != neighborSubGrid) {
                    subGrid.registerToX(neighborSubGrid);
                }
            });

            List<SubGrid> neighborSubGridsY = subGridsY(y);

            neighborSubGridsY.forEach(neighborSubGrid -> {
                if (subGrid != neighborSubGrid) {
                    subGrid.registerToY(neighborSubGrid);
                }
            });
        });
    }

    private List<SubGrid> subGridsX(int x) {
        return subGrids.stream()
                .filter(subGrid -> subGrid.getX() == x)
                .collect(Collectors.toList());
    }

    private List<SubGrid> subGridsY(int y) {
        return subGrids.stream()
                .filter(subGrid -> subGrid.getY() == y)
                .collect(Collectors.toList());
    }

    Collection<FieldWithAbsoluteCoordinates> getUnresolvedFields() {
        return subGrids.stream()
                .flatMap(subGrid -> subGrid.getUnresolvedFields().stream())
                .collect(Collectors.toUnmodifiableList());
    }

    Collection<FieldsWithAbsoluteCoordinates> getSubGridFields() {
        return subGrids.stream()
                .map(SubGrid::getFields)
                .collect(Collectors.toUnmodifiableList());
    }

    Collection<FieldsWithAbsoluteCoordinates> getLines() {
        return IntStream.range(0, sizeX * sizeY)
                .mapToObj(this::getLine)
                .collect(Collectors.toUnmodifiableList());
    }

    Collection<FieldsWithAbsoluteCoordinates> getColumns() {
        return IntStream.range(0, sizeX * sizeY)
                .mapToObj(this::getColumn)
                .collect(Collectors.toUnmodifiableList());
    }

    Collection<FieldWithAbsoluteCoordinates> findDefiniteFields() {
        return subGrids.stream()
                .flatMap(subGrid -> subGrid.getFieldsToBeSolved().stream())
                .collect(Collectors.toUnmodifiableList());
    }

    Collection<FieldWithAbsoluteCoordinates> getFields() {
        return subGrids.stream()
                .flatMap(subGrid -> subGrid.getFields().getFields().stream())
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String toString() {
        return "SubGrids{" +
                "subGrids=" + subGrids +
                ", sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                '}';
    }
}
