package org.example.util;

import java.util.Objects;

public class Position {
    private int row, col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return getRow() == position.getRow() && getCol() == position.getCol();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRow(), getCol());
    }

    public Position goIntoDirection(Direction dir){
        return new Position(getRow()+ dir.getRowDiff(), getCol() + dir.getCollDiff());
    }

    public String toString(){
        return String.format("(%d,%d)", getRow(), getCol());
    }
}
