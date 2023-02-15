package EffectiveJava.Chapter5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class _00_Wildcards {
    public static void main(String ... args) {
        ArrayList<Integer> ints = new ArrayList<Integer>(List.of(1, 2, 3, 4, 5));
        LinkedList<Double> doubles = new LinkedList<>(List.of(1.0, 2.0, 3.0, 4.5));
        var u = union(ints, doubles); // Java even understands what common super-type they have
        System.out.println(u);

        ArrayList<Number> nums = new ArrayList<>();
        fill(ints, nums);
        System.out.println(nums);
    }

    /*
     * PECS - Producer extends, Consumer super
     * This is a mnemonic for the usage of wildcard arguments
     */
    static <E> Set<E> union(Collection<? extends E> s1, Collection<? extends E> s2) {
        Set<E> res = new HashSet<>();
        for (var x : s1) res.add(x);
        for (var y : s2) res.add(y);
        return res;
    }

    /*
     * Bad example actually: Collection's add and addAll already accept objects of subtypes.
     * 'If the user of a class has to think about wildcard types, there is probably something wrong with its API.'
     * Thankfully, creators of Collections framework have made a good API
     */
    static <E> void fill(Collection<E> src, Collection<? super E> c) {
        for (var x : src) c.add(x);
    }
}