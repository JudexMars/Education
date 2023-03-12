package EffectiveJava.Chapter7;

import java.util.function.ToIntBiFunction;

public class _00_Lambdas {
    public static void main(String ... args) {
        ToIntBiFunction<String, String> x = String::compareTo;
        System.out.println(x.applyAsInt("One", "Three"));
    }

    @FunctionalInterface
    interface MyComparator<T> {
        public int compare(T first, T second);
        //public boolean pred(T condition); // won't work because a Functional interface is not allowed to have >1 abstract methods
    }
}