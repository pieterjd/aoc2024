package org.example.day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day05 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
               Day05.class.getClassLoader().getResourceAsStream("day05.txt")
        ));
        List<OrderRule> rules = new ArrayList<>();
        for(String line=br.readLine();line.length()!=0;line=br.readLine()){
            String[] parts = line.split("\\|");
            OrderRule or = new OrderRule(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
            rules.add(or);
        }

        List<Update> updates = new ArrayList<>();
        for(String line=br.readLine();line!=null;line=br.readLine()){
            String[] parts = line.split(",");
            List<Integer> numbers = Arrays.stream(parts)
                    .map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList());
            Update u = new Update(numbers);
            updates.add(u);
        }

        List<Update> validUpdates = updates.stream()
                .filter(update -> rules.stream().allMatch(rule -> rule.matches(update)))
                .collect(Collectors.toList());

        //System.out.println(validUpdates);
        long sum = validUpdates.stream()
                .mapToLong(up -> up.getMiddle())
                .sum();
        //System.out.println(sum);
        List<Update> invalidUpdates = updates.stream()
                .filter(update -> !rules.stream().allMatch(rule -> rule.matches(update)))
                .collect(Collectors.toList());

        invalidUpdates.forEach(update-> update.fixRules(rules));
        System.out.println(invalidUpdates);
        long sumFixed = invalidUpdates.stream()
                .mapToLong(up -> up.getMiddle())
                .sum();
        System.out.println(sumFixed);
    }
}
