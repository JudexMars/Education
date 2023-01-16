package Horstmann.Core1.Multithreading;

public class _01_Introduction {
    public static void main(String ... args) {
        System.out.println("Everything works just fine");

        // An introduction to the concept of threads
        // A Thread instance accept a functional interface as an argument

        /* Each thread has different states.
        NEW - when it is created
        RUNNABLE - when the run() method is already executed (it doesn't mean that the thread 
        is already being executed, it just means that the scheduler will do it at some point eventually)
        BLOCKED, WAITING, TIMED_WAITING - happens for three reasons: 
        1) this thread is trying to acquire an intrinsic object lock
        2) it is waiting for a notification from another thread
        3) this thread is in timed waiting state until it gets a notification or the time runs out
        TERMINATED - it means that the thread has stopped execution
        There are methods for more direct control of the thread's state but they are deprecated and
        you shouldn't use them
        */ 

        Runnable r1 = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("[A] Step " + i + " State: " + Thread.currentThread().getState());
                    Thread.sleep(300); // Sleep method is used to cease execution of the thread
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
         };
        var t1 = new Thread(r1);
        System.out.println("Thread A - State: " + t1.getState());
        Runnable r2 = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("[B] Step " + i);
                    Thread.sleep(300); 
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
         };
        var t2 = new Thread(r2);

        // As we can see, each thread is being executed in parallel
        t1.start();
        t2.start();
    }
}