package org.example.util;

public enum Direction {
    TOP(-1,0),
    TOP_RIGHT(-1, 1),
    RIGHT(0,1),
    DOWN_RIGHT(1,1),
    DOWN(1,0),
    DOWN_LEFT(1,-1),
    LEFT(0, -1),
    TOP_LEFT(-1,-1);

    private final int rowDiff, collDiff;
    Direction(int rowDiff, int collDiff) {
        this.rowDiff = rowDiff;
        this.collDiff = collDiff;
    }

    public int getRowDiff() {
        return rowDiff;
    }

    public int getCollDiff() {
        return collDiff;
    }
}
