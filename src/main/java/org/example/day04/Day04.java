package org.example.day04;

import org.example.util.Direction;
import org.example.util.Grid;
import org.example.util.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Day04 {
    private Grid<Character> grid;
    private final static String XMAS = "XMAS";

    public Day04() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                getClass()
                        .getClassLoader().getResourceAsStream("day04.txt")
        ));
        int rowNr = 0;
        grid = new Grid<>();
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            String finalLine = line;
            int finalRowNr = rowNr;
            IntStream.range(0, line.length())
                    .forEach(colNr -> {
                        Position pos = new Position(finalRowNr, colNr);
                        Character ch = finalLine.charAt(colNr);
                        grid.put(pos, ch);
                    });
            rowNr++;
        }
    }

    public Grid<Character> getGrid() {
        return grid;
    }

    public long findXmas() {
        return getGrid().getGrid().keySet().stream()
                .mapToLong(pos -> findXmas(pos))
                .sum();
    }

    public long findXmas(Position pos) {
        long result = Arrays.stream(Direction.values())
                .filter(dir -> findXmas(pos, dir))
                .map(dir -> {
                    System.out.println(String.format("Found XMAS at pos %s in direction %s", pos, dir));
                    return dir;
                })
                .count();
        System.out.println("Total: " + result);
        return result;
    }

    public boolean findXmas(Position pos, Direction dir) {
        return findXmas(pos, dir, 'X');
    }

    private boolean findXmas(Position pos, Direction dir, Character expectedChar) {
        //check if pos in grid
        if (!grid.getGrid().containsKey(pos)) {
            return false;
        }
        //check if pos contains expected char
        if (!grid.getAtPosition(pos).equals(expectedChar)) {
            return false;
        }
        int index = XMAS.indexOf(expectedChar);
        if (index == XMAS.length() - 1) {
            //found last letter
            return true;
        }

        //try to find next letter
        Position nextPos = pos.goIntoDirection(dir);
        Character nextExpectedChar = XMAS.charAt(index + 1);
        return findXmas(nextPos, dir, nextExpectedChar);


    }

    public long countPart2() {
        return getGrid().getGrid().keySet().stream()
                .filter(pos -> findPart2(pos))
                .count();
    }

    public boolean findPart2(Position p) {
        if(getGrid().getAtPosition(p) != 'A'){
            return false;
        }
        Position topleft = p.goIntoDirection(Direction.TOP_LEFT);
        if(getGrid().getAtPosition(topleft)== null){
            return false;
        }
        List<Character> valuesInDirection = getGrid().getValuesInDirection(topleft, Direction.DOWN_RIGHT, 3);
        if(valuesInDirection.size()!=3){
            return false;
        }
        if(! valuesInDirection.containsAll(List.of('M', 'A', 'S'))){
            return false;
        }


        Position topright = p.goIntoDirection(Direction.TOP_RIGHT);
        if(getGrid().getAtPosition(topright)== null){
            return false;
        }
        List<Character> valuesInDirectionAnti = getGrid().getValuesInDirection(topright, Direction.DOWN_LEFT, 3);
        if(valuesInDirectionAnti.size()!=3){
            return false;
        }
        return valuesInDirectionAnti.containsAll(List.of('M', 'A', 'S'));
    }


    public static void main(String[] args) throws IOException {
        Day04 d = new Day04();

        System.out.println(d.countPart2());
    }
}
