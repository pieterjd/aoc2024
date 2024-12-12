package org.example.day05;

import java.util.List;

public class OrderRule {
    private int first, last;

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }

    public OrderRule(int first, int last) {
        this.first = first;
        this.last = last;
    }

    public boolean matches(Update up){
        return matches(up.getList());
    }

    public boolean matches(List<Integer> list){
        int firstIndex = list.indexOf(first);
        int lastIndex = list.indexOf(last);
        if(firstIndex==-1 || lastIndex == -1){
            return true;
        }

        return firstIndex < lastIndex;

    }
    public String toString(){
        return String.format("%d|%d", first, last);
    }
}
