package EffectiveJava.Chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.*;

public class _01_Varargs {
    public static void main(String ... args) {
        int[] a = {1, 2, 3, 4};
        int[] b = {5, 6, 7, 8};
        List<int[]> c = Arrays.asList(a, b);
        System.out.println(Arrays.toString(c.get(0)));

        try {
            var x = pickTwo(1, 2, 3);
            System.out.println(Arrays.toString(x));
        }
        catch (ClassCastException e) {
            System.out.println("pickTwo method doesn't work because the program puts an invisible cast to Integer[] that is not applicable to Object[] array which is returned by the toArray method");
            System.out.println("Why is it Object[] and not Integer[]? Because T parameter is unbounded. At the compilation time the program only knows that it could be any time ever. That's why it chooses to store parameters in Object[]");
        }

        var x = safePickTwo(1, 2, 3);
        System.out.println("Chosen number: " + x);

        printColumn(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @SuppressWarnings("unchecked")
    static <T> T[] toArray(T... args) {
        Logger.getGlobal().log(Level.INFO, "toArray args type: " + args.getClass().getName());
        return args;
    }

    @SuppressWarnings("unchecked")
    static <T> T[] pickTwo(T a, T b, T c) {
        Logger.getGlobal().log(Level.INFO, "pickTwo arg type: " + a.getClass().getName());
        switch(ThreadLocalRandom.current().nextInt(3)) {
          case 0: return toArray(a, b);
          case 1: return toArray(a, c);
          case 2: return toArray(b, c);
        }
        throw new AssertionError(); // Can't get here
    }

    // This approach is generally better
    static <T> List<T> safePickTwo(T a, T b, T c) {
        switch(ThreadLocalRandom.current().nextInt(3)) {
            case 0: return List.of(a, b);
            case 1: return List.of(a, c);
            case 2: return List.of(b, c);
          }
          throw new AssertionError(); // Can't get here
    }

    // This method does not store anything in varargs nor gives them to some other untrusted code. It is considered safe.
    @SafeVarargs
    static <T> void printColumn(T ... args) {
        for (var x : args) System.out.println(x);
    }
}
