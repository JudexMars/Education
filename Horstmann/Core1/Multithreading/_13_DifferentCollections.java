package Horstmann.Core1.Multithreading;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class _13_DifferentCollections {
    public static void main(String ... args) {
        Set<String> set = ConcurrentHashMap.newKeySet();
        set.add("Daniel");
        set.add("Ivan");

        System.out.println("Set: " + set);

        int[] simpleArray = {9, 3, 2, 18, 0};
        Arrays.parallelSort(simpleArray);

        //Arrays.parallelSetAll(simpleArray, i -> i + new Random().nextInt(i, (i + 1) * 100));
        Arrays.parallelSetAll(simpleArray, i -> i + 1);
        System.out.println(Arrays.toString(simpleArray));

        Arrays.parallelPrefix(simpleArray, (x, y) -> x * y);
        System.out.println(Arrays.toString(simpleArray));

        
    }
}