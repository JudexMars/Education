package Horstmann.Core1.Multithreading;

public class _06_Sync_objects {
    public static void main(String ... args) {
        /*
         * There is also another way to synchronize your application.
         * You can use so called synchronization blocks/objects for this matter
         */

        Object lock = new Object();
        synchronized (lock) {
            /*
             * Everything that happens here is synchronized with the use of the defined object.
             */
        }

        synchronized (_06_Sync_objects.class) {
            /*
             * This block of code is synchronized for the whole class. You could see the same behaviour
             * if you had written a static synchronized method.
             */
        }
    }
}

class SyncBlocksExample {

    private final Object lock = new Object(); 

    public void doSmth() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + ": It's me!");
            /*
             * This block of code equals to just writing a synchronized method.
             */
        }
    }

    public void saySmth() {
        /*
         * We've created our own analogue for ReentrantLock. It works basically the same
         */
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + ": Hello?");
        }
    }
}