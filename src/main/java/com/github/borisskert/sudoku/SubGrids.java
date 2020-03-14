package com.github.borisskert.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubGrids {

    private final List<SubGrid> subGrids = new ArrayList<>();

    public SubGrids() {
        initSubGrids();
        bindNeighborSubGrids();
    }

    private void initSubGrids() {
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                subGrids.add(new SubGrid(x, y));
            }
        }
    }

    public Field getField(int x, int y) {
        SubGrid currentSubGrid = getSubGrid(x, y);

        int relativeX = x % 2;
        int relativeY = y % 2;

        return currentSubGrid.get(relativeX, relativeY);
    }

    public boolean areSolved() {
        return subGrids.stream().noneMatch(SubGrid::isNotSolved);
    }

    private SubGrid getSubGrid(int x, int y) {
        int fieldX = x / 2;
        int fieldY = y / 2;

        return subGrids.stream()
                .filter(subGrid -> subGrid.getX() == fieldX)
                .filter(subGrid -> subGrid.getY() == fieldY)
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
}
