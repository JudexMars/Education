package Horstmann.Core1.Multithreading;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class _12_BulkMethods {
    public static void main(String ... args) {
        ConcurrentHashMap<String, Integer> hm = new ConcurrentHashMap<>(Map
        .of("God", 120,
        "Divine", 92,
        "King", 86,
        "Kill", 189));

        var frequentWords = hm.reduce(1, (k, v) -> v * 2, Integer::max);
        System.out.println(frequentWords + " " + hm.get("God"));

        var x = hm.searchKeys(1, k -> k.length() < 4 ? k : null);
        System.out.println("A word of length < 4: " + x);

        hm.forEach(2, (k, v) -> System.out.println(k + " : " + v));

        System.out.println("Total keys' length: " + hm.reduce(1, (k, v) -> k.length(), Integer::sum));
        System.out.println("Sum of all values: " + hm.reduceValues(1, Integer::sum));
    }
}
