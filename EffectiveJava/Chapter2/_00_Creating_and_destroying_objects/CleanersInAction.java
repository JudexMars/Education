package EffectiveJava.Chapter2._00_Creating_and_destroying_objects;

import java.lang.ref.Cleaner;

public class CleanersInAction {
    public static void main(String ... args) {
        /* 
         * Before Cleaners there were Finalizers. According to the book, they are not trustworthy and
         * you cannot really rely on them because their behaviour never guarantees you the result that
         * you want. Cleaners are somehow better, especially when we talk about multithreading.
         * But, unfortunately, it is not recommended to use either Cleaners or Finalizers.
         */

        try (Room room = new Room(10)) {
            System.out.println("We have entered the room");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

/*         Room room = new Room(5);
        System.gc(); */
    }
}

class Room implements AutoCloseable {

    private static final Cleaner cleaner = Cleaner.create();

    private final State state;

    private final Cleaner.Cleanable cleanable;

    private static class State implements Runnable {

        private int contaminationLevel;

        @Override
        public void run() {
            System.out.println("Cleaning the room. Current level of contamination is " + contaminationLevel);
            contaminationLevel = 0;
        }

        public State(int contaminationLevel) {
            this.contaminationLevel = contaminationLevel;
        }
    }

    public Room(int contaminationLevel) {
        state = new State(contaminationLevel);
        cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() throws Exception {
        cleanable.clean();
    }
}