package org.example.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day01 {
    private List<Integer> left, right;
    private Map<Integer, Integer> leftHeap, rightHeap;
    private List<Integer> diffs = new ArrayList<>();
    public Day01() throws IOException {
        left = new ArrayList<>();
        right = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(
                getClass()
                        .getClassLoader().getResourceAsStream("day01.txt")
        ));
        for(String line=br.readLine();line != null; line=br.readLine()){
            String[] parts = line.split("\\s+");
            left.add(Integer.parseInt(parts[0]));
            right.add(Integer.parseInt(parts[1]));
        }
        left = left.stream().sorted().collect(Collectors.toList());
        right = right.stream().sorted().collect(Collectors.toList());

    }
    public void calcDiffs(){
        diffs = IntStream.range(0, left.size())
                .mapToObj(i -> Math.abs(left.get(i) - right.get(i)))
                .collect(Collectors.toList());
    }

    public long part2(){
        rightHeap = right.stream().collect(Collectors.toMap(
                i -> i,
                i->1,
                (s, i) -> s+1

        ));

        return left.stream()
                .mapToLong( i -> (long) rightHeap.getOrDefault(i, 0) * i)
                .sum();
    }

    public long sumOfDiffs(){
        return diffs.stream()
                .reduce(0, Integer::sum);
    }

    public static void main(String[] args) throws IOException {
        Day01 d= new Day01();
        d.calcDiffs();
        System.out.println(d.sumOfDiffs());
        System.out.println(d.part2());
    }
}
