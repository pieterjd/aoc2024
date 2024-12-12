package org.example.day06;

import org.example.util.Direction;
import org.example.util.Grid;
import org.example.util.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FloorMap {
    private static final Set<Character> OBSTACLES = Set.of('#');
    private static final Map<Character, Direction> GUARDDIRECTIONMAPPING = Map.of(
            '^', Direction.TOP,
            '>', Direction.RIGHT,
            'v', Direction.DOWN,
            '<', Direction.LEFT
    );

    private static final Map<Direction, Character> REVERSE_GUARDDIRECTIONMAPPING = GUARDDIRECTIONMAPPING.entrySet()
            .stream()
            .collect(Collectors.toMap(entry-> entry.getValue(), entry-> entry.getKey()));

    private static final Map<Direction, Direction> TURNRIGHTMAPPING = Map.of(
            Direction.TOP, Direction.RIGHT,
            Direction.RIGHT, Direction.DOWN,
            Direction.DOWN, Direction.LEFT,
            Direction.LEFT, Direction.TOP
    );
    Grid<Character> grid = new Grid<>();

    Direction currentDirection = Direction.TOP;
    Position currentPosition;

    private Set<VisitInfo> visitInfos = new HashSet<>();

    public Map<Position, Character> getGrid() {
        return grid.getGrid();
    }

    public void put(Position pos, Character value) {
        if(GUARDDIRECTIONMAPPING.containsKey(value)){
            currentPosition = pos;
            currentDirection = GUARDDIRECTIONMAPPING.get(value);
        }
        grid.put(pos, value);
    }

    public Character getAtPosition(Position p) {
        return grid.getAtPosition(p);
    }

    public List<Character> getValuesInDirection(Position start, Direction dir, int nr) {
        return grid.getValuesInDirection(start, dir, nr);
    }

    public Set<Position> getNeighbours(Position p) {
        return grid.getNeighbours(p);
    }
    public boolean canAccess(Position p){
        if(!getGrid().containsKey(p)){
            return false;
        }
        return !OBSTACLES.contains(getGrid().get(p));
    }

    public Position moveGuard(){
        Position next = currentPosition.goIntoDirection(currentDirection);
        if(!grid.isInsideGrid(next)) {
            grid.put(currentPosition, 'X');
            return null;
        }
        if(OBSTACLES.contains(getAtPosition(next))){
            // next contains obstacle, so turn right
            currentDirection = TURNRIGHTMAPPING.get(currentDirection);
        }
        else{
            //update grid
            grid.put(currentPosition, 'X');
            grid.put(next, REVERSE_GUARDDIRECTIONMAPPING.get(currentDirection));

            // move into current direction
            currentPosition = next;
        }
        return currentPosition;
    }

    public String toString(){
        return grid.toString();
    }

    public long part1(){
        while(moveGuard()!=null){

        }
        System.out.println(this);
        return getGrid().values().stream()
                .filter(ch->ch=='X')
                .count();
    }

    public VisitInfo moveGuardPart2(){
        grid.put(currentPosition, 'X');//mark current as visited
        VisitInfo info = new VisitInfo(currentPosition, currentDirection);
        Position next = currentPosition.goIntoDirection(currentDirection);
        if(!grid.isInsideGrid(next)) {
            return null;
        }
        if(OBSTACLES.contains(getAtPosition(next))){
            // next contains obstacle, so turn right
            currentDirection = TURNRIGHTMAPPING.get(currentDirection);
        }
        else{


            //update grid

            grid.put(next, REVERSE_GUARDDIRECTIONMAPPING.get(currentDirection));//put guard in correct direction on next

            currentPosition = next; // move into current direction
        }
        return info;
    }

    public void part2(){
        visitInfos = new HashSet<>();
        for(VisitInfo vi = moveGuardPart2(); vi!=null; vi = moveGuardPart2()){
            if(visitInfos.contains(vi)){
                System.out.println("found loop");
                return;
            }
            visitInfos.add(vi);
        }
        System.out.println("vi was null, so exited the grid");
    }

}
