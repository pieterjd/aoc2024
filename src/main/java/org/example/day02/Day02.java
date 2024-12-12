package org.example.day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day02 {
    public Day02() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                getClass()
                        .getClassLoader().getResourceAsStream("day02.txt")
        ));
        List<Report> reports = new ArrayList<>();
        for(String line = br.readLine(); line!=null;line = br.readLine()){
            reports.add(new Report(line));
        }
        System.out.println(reports.stream().filter(Report::isSafe).count());
        System.out.println(reports.stream().filter(Report::isSafeWithDampener).count());
        //reports.stream().filter(r->r.isSafeWithDampener()).forEach(System.out::println);

    }
    public static void main(String[] args) throws IOException {
        new Day02();
    }
}
