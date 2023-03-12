package EffectiveJava.Chapter7;

import java.util.Iterator;
import java.util.List;
import java.util.stream.*;

public class _02_Iterator {
    public static void main(String ... args) {
        Stream<String> s = Stream.of("I am the storm that is approaching".split(" "));
        for (String word : iteratorOf(s)) {
            System.out.println(word);
        }

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        streamOf(numbers).forEach(System.out::println);
    }

    /*
     * When you use a for-each loop (see above), Java 1) expects the object that you provide to be of type Iterable<T>,
     * 2) invokes its 'iterator' method. In the method below we can either return a stream iterator as a method reference
     * or return a lambda expression or return an anonymous class that implements iterator()
     */
    private static <T> Iterable<T> iteratorOf(Stream<T> stream) {
        //return stream::iterator;
        //return () -> stream.iterator();
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return stream.iterator();
            }
        };
    }

    private static <T> Stream<T> streamOf(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
