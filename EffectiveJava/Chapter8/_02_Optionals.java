package EffectiveJava.Chapter8;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class _02_Optionals {
    /**
     * <pre>{@code var x = 10;
     *var y = x * 2;
     *System.out.println(x + y);}</pre>
     * @param args
     */
    public static void main(String ... args) {
        List<Integer> nums = List.of(1, 2, 3, 4, 5);
        List<Integer> none = Collections.emptyList();
        var result = findMax(nums);
        System.out.println(result.orElse(Integer.MAX_VALUE)); // returns a specified value if null
        result = findMax(none);
        //System.out.println(result.orElseThrow(IllegalArgumentException::new)); // throws a specified exception if null
        var resultInt = findMaxValue();
        System.out.println(resultInt.orElse(Integer.MAX_VALUE));
    }

    /**
     * Returns the biggest element in the collection
     * 
     * <p>This method may return an <i>empty</i> Optional object if the collection is itself <i>empty</i></p>
     * 
     * @param <T> type of each element in the collection
     * @param c the collection
     * @return the biggest element in the collection in the form of {@code Optional<T>}
     */
    private static <T extends Comparable<T>> Optional<T> findMax(Collection<T> c) {
        T res = null;
        for (var x: c) if (res == null || x.compareTo(res) > 0) res = x;
        if (res == null) return Optional.empty();
        return Optional.of(res);
    }

    // It is more efficient to use specific Optional- classes for primitive types
    /**
     * Returns the biggest int value from the arguments
     * 
     * <p>Only accepts integers</p>
     * 
     * @implSpec this implementation uses a simple for-each loop
     * 
     * @param args numbers
     * @return the biggest number from the arguments in the form of {@code OptionalInt}
     */
    private static OptionalInt findMaxValue(int ... args) {
        if (args.length == 0) return OptionalInt.empty();
        int res = args[0];
        for (var x: args) if (x > res) res = x;
        return OptionalInt.of(res);
    }
}
