package org.example.day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Report {
    private List<Integer> numbers = new ArrayList<>();
    private List<Integer> diffs = new ArrayList<>();
    private Boolean safe = null;

    public Report(String line){
        this(Arrays.stream(line.split(" "))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList()));
    }
    public Report(List<Integer> numbers) {
        this.numbers = new ArrayList<>(numbers) ;
        diffs = IntStream.range(0, numbers.size() - 1)
                .mapToObj(i -> numbers.get(i) - numbers.get(i + 1))
                .collect(Collectors.toList());
        boolean allDecreasing = diffs.stream().allMatch(i -> i < 0);
        long nrOfDecreasing = diffs.stream().filter(i -> i < 0).count();
        boolean allIncreasing = diffs.stream().allMatch(i -> i > 0);
        long nrOfIncreasing = diffs.stream().filter(i -> i > 0).count();
        boolean withinBounds = diffs.stream().allMatch(i -> Math.abs(i) >= 1 && Math.abs(i) <= 3);
        long nrOfWithinBounds = diffs.stream().filter(i -> Math.abs(i) >= 1 && Math.abs(i) <= 3).count();
        safe = (allDecreasing || allIncreasing) && withinBounds;
        safe = checkDiffs(nrOfDecreasing,nrOfIncreasing,nrOfWithinBounds);

    }
    public boolean checkDiffs(long nrOfDecreasing, long nrOfIncreasing, long nrOfWithinBounds){
        return (nrOfDecreasing == diffs.size() || nrOfIncreasing==diffs.size()) && nrOfWithinBounds==diffs.size();
    }



    public String toString() {
        return numbers.stream()
                .map(i -> Integer.toString(i))
                .collect(Collectors.joining(" "));
    }

    Boolean isSafe() {
        return safe;
    }

    Boolean isSafeWithDampener() {
        if (isSafe()) {
            return true;
        }
        boolean foundSafe = false;
        for(int i = 0; i <size() && !foundSafe; i++){
            // remove number at position i
            List<Integer> oneLess =new ArrayList<>(numbers);
            oneLess.remove(i);
            Report reduced = new Report(oneLess);
            foundSafe = reduced.isSafe();
        }
        return foundSafe;
    }

    //

    public int size() {
        return numbers.size();
    }

    public boolean isEmpty() {
        return numbers.isEmpty();
    }

    public boolean contains(Object o) {
        return numbers.contains(o);
    }

    public Iterator<Integer> iterator() {
        return numbers.iterator();
    }

    public Object[] toArray() {
        return numbers.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return numbers.toArray(a);
    }

    public boolean add(Integer integer) {
        return numbers.add(integer);
    }

    public boolean remove(Object o) {
        return numbers.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return numbers.containsAll(c);
    }

    public boolean addAll(Collection<? extends Integer> c) {
        return numbers.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends Integer> c) {
        return numbers.addAll(index, c);
    }

    public boolean removeAll(Collection<?> c) {
        return numbers.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return numbers.retainAll(c);
    }

    public void replaceAll(UnaryOperator<Integer> operator) {
        numbers.replaceAll(operator);
    }

    public void sort(Comparator<? super Integer> c) {
        numbers.sort(c);
    }

    public void clear() {
        numbers.clear();
    }

    public Integer get(int index) {
        return numbers.get(index);
    }

    public Integer set(int index, Integer element) {
        return numbers.set(index, element);
    }

    public void add(int index, Integer element) {
        numbers.add(index, element);
    }

    public Integer remove(int index) {
        return numbers.remove(index);
    }

    public int indexOf(Object o) {
        return numbers.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return numbers.lastIndexOf(o);
    }

    public ListIterator<Integer> listIterator() {
        return numbers.listIterator();
    }

    public ListIterator<Integer> listIterator(int index) {
        return numbers.listIterator(index);
    }

    public List<Integer> subList(int fromIndex, int toIndex) {
        return numbers.subList(fromIndex, toIndex);
    }

    public Spliterator<Integer> spliterator() {
        return numbers.spliterator();
    }


    public boolean removeIf(Predicate<? super Integer> filter) {
        return numbers.removeIf(filter);
    }

    public Stream<Integer> stream() {
        return numbers.stream();
    }

    public Stream<Integer> parallelStream() {
        return numbers.parallelStream();
    }

    public void forEach(Consumer<? super Integer> action) {
        numbers.forEach(action);
    }
}
