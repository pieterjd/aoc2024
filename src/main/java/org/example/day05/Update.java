package org.example.day05;

import java.util.List;
import java.util.stream.Collectors;

public class Update {
    private final List<Integer> list;

    public Update(List<Integer> list) {
        this.list = list;
    }

    public List<Integer> getList() {
        return list;
    }

    public int getMiddleIndex(){
        return list.size()/2;
    }

    public int getMiddle(){
        return list.get(getMiddleIndex());
    }

    public String toString(){
        return list.stream().map(i->Integer.toString(i)).collect(Collectors.joining(","));
    }

    public void checkRules(List<OrderRule> rules){
        for (OrderRule rule: rules){
            boolean matches = rule.matches(this);
            if(!matches) System.out.println(String.format("Rule %s matches update %s? %s",rule,this,matches));
        }
    }

    public List<OrderRule> failedRules(List<OrderRule> rules){
        return rules.stream()
                .filter(rule-> !rule.matches(this))
                .collect(Collectors.toList());
    }

    public void fixRules(List<OrderRule> rules){
        List<OrderRule> failedRules = List.of();
        do {

            failedRules = failedRules(rules);
            failedRules.forEach(this::fixRule);
        }
        while(!failedRules.isEmpty());

    }

    public void fixRule(OrderRule rule){
        if(rule.matches(this)) return ;
        int indexFirst = list.indexOf(rule.getFirst());
        int indexLast = list.indexOf(rule.getLast());
        //swap
        list.set(indexFirst, rule.getLast());
        list.set(indexLast, rule.getFirst());
    }
}
