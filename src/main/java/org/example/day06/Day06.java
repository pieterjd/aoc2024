package org.example.day06;

import org.example.util.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day06 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                Day06.class.getClassLoader().getResourceAsStream("day06.txt")
        ));
        int row = 0;
        FloorMap map = new FloorMap();
        for(String line=br.readLine();line!=null;line=br.readLine()){
            for(int col=0;col<line.length();col++){
                Position p = new Position(row, col);
                Character ch  = line.charAt(col);
                map.put(p, ch);
            }
            row++;
        }

        //System.out.println(map.part1());
        map.part2();
    }
}
