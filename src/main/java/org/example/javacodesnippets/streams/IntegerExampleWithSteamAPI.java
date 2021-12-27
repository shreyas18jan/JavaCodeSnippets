package org.example.javacodesnippets.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Frequently used methods are discussed here.
public class IntegerExampleWithSteamAPI {
    public static final int[] intArrayExample = {10, 40, 90, 20, 10, 20, 40};
    public static final List<Integer> integerListExample = Arrays.asList(10, 40, 90, 20, 10, 20, 40);
    public static final IntStream intStreamExample = Arrays.stream(intArrayExample);

    public static void main(String[] args) {
        //
        // Print all the entries in the List
        // forEach() - Performs the given action, Takes Consumer Function.
        //
        System.out.println("Integer List : ");
        integerListExample.forEach(entry -> System.out.print(entry + " "));
        System.out.println("\n");

        //
        // To get the Unique entries in the list
        // distinct() - compares and outputs unique entries based on Object.equals(Object) fashion. ParallelStream + Distinct might be expensive.
        //
        System.out.println("To get the Unique entries in the list : ");
        integerListExample.stream().distinct().forEach(entry -> System.out.print(entry + " "));
        System.out.println("\n");

        //
        // Sort the entries
        // sorted() - Follow Merge Sort approach which takes NlogN time Complexity.
        //
        System.out.print("Duplicate Entries + Sorted : ");
        integerListExample.stream().sorted().forEach(entry -> System.out.print(entry + " "));
        System.out.println("\n");
        System.out.print("Distinct Entries + Sorted : ");
        integerListExample.stream().distinct().sorted().forEach(entry -> System.out.print(entry + " "));
        System.out.println("\n");
        System.out.print("Distinct Entries + Sorted in Reverse Order : ");
        integerListExample.stream().distinct().sorted(Comparator.reverseOrder()).forEach(entry -> System.out.print(entry + " "));
        System.out.println("\n");

        //
        // Get the Statics out of an Integer List
        // summaryStatistics() - Object that has Average, Count, Sum, Min and Max variations for a given Int Stream.
        //
        System.out.println("Statistics Summary from IntStream : ");
        IntSummaryStatistics summaryStatistics = intStreamExample.summaryStatistics();
        System.out.println(" toString() - " + summaryStatistics.toString());
        System.out.println(" getAverage() - " + summaryStatistics.getAverage());
        System.out.println(" getCount() - " + summaryStatistics.getCount());
        System.out.println(" getSum() - " + summaryStatistics.getSum());
        System.out.println(" getMin() - " + summaryStatistics.getMin());
        System.out.println(" getMax() - " + summaryStatistics.getMax());
        System.out.println();

        //
        // Get the Statics out of an Integer List
        // Once the Stream is used , we cannot reuse the same Stream.
        //
        try{
            intStreamExample.forEach(System.out::print);
        } catch (IllegalStateException ise) {
            System.out.println("try-catch: IllegalStateException caught!");
        } finally {
            System.out.println("finally: Once Stream is used, we cannot reuse it again.");
        }
        System.out.println();

        //
        // Modify each entry in list by multiplying with 10
        // map() - Ability to transform/map each entry in the stream to a different entry.
        //
        System.out.print("Modify each entry in list by multiplying with 10 : ");
        integerListExample.stream().map(entry -> entry*10).forEach(entry -> System.out.print(entry + " "));
        System.out.println("\n");

        //
        // Select only those entries which are multiples of 20
        // filter() - Ability to select entries out of the given stream based on the condition.
        //
        System.out.print("Select only those entries which are multiples of 20 : ");
        integerListExample.stream().filter(entry -> (entry%20) == 0 ).forEach(entry -> System.out.print(entry + " "));
        System.out.println("\n");

        //
        // Add each entry in the List
        // reduce() -  Terminal / Kind of Aggregator that can be used on stream.
        //
        System.out.print("Apply Bitwise OR on each entry in the List one by one : " + integerListExample.stream().reduce(0, (entry1, entry2) -> entry1|entry2 ));
        System.out.println("\n");

        //
        // Collect the entries in String fashion
        // collect() -  Terminal operation which helps to collect the transformed data.
        //
        String collectedValues = integerListExample.stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.print("Collect the entries in String fashion : " + collectedValues);
        System.out.println("\n");
    }
}
