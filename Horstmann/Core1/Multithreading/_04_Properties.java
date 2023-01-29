package Horstmann.Core1.Multithreading;

public class _04_Properties {
    public static void main(String ... args) {
        // Thread names can be changed in case you don't like the default ones
        var t = new Thread(() -> {
            throw new RuntimeException("You fucked up hard man");
        });
        t.setName("Handsome thread");

        /*
         * Thread can't throw checked exceptions but they are able to throw unchecked exceptions
         * that are handled by Thread.UncaughtExceptionHandler instances
         */
        t.setUncaughtExceptionHandler((Thread th, Throwable e) -> {
            System.out.println("I don't know what to do with it");
            System.out.println("Thread name: " + th.getName());
            System.out.println("Message: " + e.getMessage());
            System.out.println("Cause: " + e.getCause());
        });

        // Obviously, it allows you to set a handler for all threads
        //Thread.setDefaultUncaughtExceptionHandler(null);

        /**
         * Thread groups are used to combine several threads and control them with ease.
         * You can specify a thread's thread group in its constructor
         */

        ThreadGroup tg = new ThreadGroup("Handsome group");
        t = new Thread(tg, () -> {
            System.out.println("I'm alive!");
            try {
                while (!Thread.currentThread().isInterrupted())
                    Thread.sleep(1000);
            } catch (InterruptedException e1) {
                System.out.println("Somebody has interrupted my group! Wtf!");
                Thread.currentThread().interrupt();
            }
        });
        t.start();

        System.out.println("Amount of active threads in group " + tg.getName() + ": " + tg.activeCount());
        tg.interrupt();

        /*
         * Threads in Java have their own priority system. In earlier days, it was used
         * to determine which thread has to be executed by the scheduler.
         * Unfortunately, this system has different implementations on certain platforms.
         * Nowadays, it is not recommended to use it: there are more reliable systems.
         */
        t = new Thread(tg, () -> {
            System.out.println("Very important task");
        });
        // The highest available priority is set to 10, the lowest - to 1.
        t.setPriority(Thread.MAX_PRIORITY);
    }
}
