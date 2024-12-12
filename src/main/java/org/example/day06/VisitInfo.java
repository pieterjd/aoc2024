package org.example.day06;

import org.example.util.Direction;
import org.example.util.Position;

import java.util.Objects;

public class VisitInfo {
    private Position position;
    private Direction direction;

    public VisitInfo(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitInfo visitInfo = (VisitInfo) o;
        return Objects.equals(position, visitInfo.position) && direction == visitInfo.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, direction);
    }
}
