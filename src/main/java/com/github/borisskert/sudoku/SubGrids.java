package com.github.borisskert.sudoku;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SubGrids {

    private final List<SubGrid> subGrids = new ArrayList<>();
    private final int sizeX;
    private final int sizeY;

    public SubGrids(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        initSubGrids();
        bindNeighborSubGrids();
    }

    private void initSubGrids() {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                subGrids.add(new SubGrid(x, y, sizeX, sizeY));
            }
        }
    }

    public Field getField(int x, int y) {
        SubGrid currentSubGrid = getSubGrid(x / sizeX, y / sizeY);

        int relativeX = x % sizeX;
        int relativeY = y % sizeY;

        return currentSubGrid.get(relativeX, relativeY);
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

    public Collection<Field> getUnresolvedFields() {
        return subGrids.stream()
                .flatMap(subGrid -> subGrid.getUnresolvedFields().stream())
                .collect(Collectors.toUnmodifiableList());
    }
}
