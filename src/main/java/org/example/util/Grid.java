package org.example.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Grid<T> {
    private Map<Position, T> grid;


    public Grid(){
        grid = new HashMap<>();
    }


    public Map<Position, T> getGrid() {
        return grid;
    }



    public void put(Position pos, T value){
        grid.put(pos, value);
    }

    public T getAtPosition(Position p){
        return grid.get(p);
    }

    public List<T> getValuesInDirection(Position start, Direction dir,int nr){
        List<T> result = new ArrayList<>();
        Position current = start;
        int count = 0;
        while(grid.containsKey(current) && count<nr){
            result.add(grid.get(current));
            current = current.goIntoDirection(dir);
            count++;
        }

        return result;
    }

    public Set<Position> getNeighbours(Position p) {
        return Arrays.stream(Direction.values())
                .map(dir -> new Position(p.getRow() + dir.getRowDiff(), p.getCol() + dir.getCollDiff()))
                .filter(pos -> grid.containsKey(pos))
                .collect(Collectors.toSet());

    }

    public boolean isInsideGrid(Position p){
        return grid.containsKey(p);
    }

    public String toString(){
        IntSummaryStatistics stats = grid.keySet().stream()
                .mapToInt(p -> p.getRow())
                .summaryStatistics();

        return IntStream.range(stats.getMin(), stats.getMax()+1)
                .mapToObj(this::getRow)
                .map(list -> list.stream().map(Object::toString).collect(Collectors.joining()))
                .collect(Collectors.joining("\n")) + "\n";

    }

    public List<T> getRow(int row){
        return grid.entrySet().stream()
                .filter(entry-> entry.getKey().getRow() == row)
                .sorted(Comparator.comparingInt(e->e.getKey().getCol()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}
