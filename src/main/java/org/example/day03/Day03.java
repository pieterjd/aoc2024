package org.example.day03;

import org.example.day02.Day02;
import org.example.day02.Report;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {
    public static final String MUL_REGEX="mul\\((\\d{1,3}),(\\d{1,3})\\)";
    public static final String DO_REGEX="do\\(\\)";
    public static final String DONT_REGEX="don't\\(\\)";
    public static final String ALL_REGEX = String.format("(%s|%s|%s)", MUL_REGEX, DO_REGEX, DONT_REGEX);
    public Day03() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                getClass()
                        .getClassLoader().getResourceAsStream("day03.txt")
        ));
        Pattern p = Pattern.compile(ALL_REGEX);
        long sum = 0;
        boolean enabled= true;
        for(String line = br.readLine(); line!=null;line = br.readLine()){
            Matcher matcher = p.matcher(line);
            while(matcher.find()){
                if(matcher.group().contains("do")){
                    enabled = !matcher.group().contains("don't");
                }
                if(enabled && matcher.group().contains("mul")) {
                    long left = Long.parseLong(matcher.group(2));
                    long right = Long.parseLong(matcher.group(3));
                    sum += left * right;
                }
            }
        }
        System.out.println(sum);
    }
    public static void main(String[] args) throws IOException {

       new Day03();

    }
}
