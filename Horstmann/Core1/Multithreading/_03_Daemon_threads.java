package Horstmann.Core1.Multithreading;

public class _03_Daemon_threads {
    public static void main(String ... args) {
        /*
         * There is nothing demonic about these threads.
         * Any thread that is used only to server other threads is called daemon
         * Such threads are considered not important and the VM will terminate itself
         * in case there are only daemon threads left
         */

         Thread importantThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Essential thread is running: " + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
         });

         Thread daemonThread = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    // Daemon thread will never reach the 20th iteration but
                    // can suprisingly still reach the 11th iteration
                    System.out.println("Daemon thread is running: " + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
         });
         daemonThread.setDaemon(true);

         importantThread.start();
         daemonThread.start();
    }
}
