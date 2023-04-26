package EffectiveJava.Chapter11;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;

public class ExcessiveSync {
    public static void main(String ... args) {
        List<Integer> source =  List.of(1, 2, 3, 4, 5);
        ListHolder<Integer> holder = new ListHolder<>(source);
        holder.add(6);
        holder.addAll(List.of(7, 8, 9, 10));

        PrintStream ps = new PrintStream(System.out, true);
        ps.println(holder);

        /*
         * Here we make a functional interface that, unfortunately, tries to modify the list that
         * it was provided. It is a deliberate mistake to give you an example of unwise programming.
         * Now head to the method implementation in the class ListHolder.
         */
        holder.doAction((l, e) -> {
            l.remove(0);
            System.out.println(e);
        });

        ps.println(holder);
    }

    /**
     * This class is designed to be a wrapper of a simple list, just another abstraction over it.
     * It's purpose is to show how dangerous excessive synchronization can be
     */
    static class ListHolder<T> {
        private final List<T> list = new ArrayList<>();

        public ListHolder(Collection<? extends T> source) {
            for (var e : source) list.add(e);
        }

        public void add(T e) {
            synchronized(list) {
                list.add(e);
            }
        }

        public void addAll(Collection<? extends T> source) {
            synchronized (list) {
                list.addAll(source);
            }
        }

        @Override
        public String toString() {
            return list.toString();
        }

        /*
         * It is highly disencouraged to lose control over your objects in synchronized block,
         * one simple rookie mistake and whole your program crashes or, even worse, finds itself
         * in a deadlock leaving user frustrated.
         * You should never mindlessly let 'alien' methods be invoked from the synchronized block.
         * 
         * The problem in this method can be fixed by making a snapshot of the list or using
         * one of the concurrent collections instead (for example, CopyOnWriteArrayList)
         */
        public void doAction(BiConsumer<List<? super T>, ? super T> consumer) {
            synchronized (list) {
                for (var e : list) { 
                    System.out.println(e.toString());
                    consumer.accept(list, e);
                 }

            }
        }
    }
}