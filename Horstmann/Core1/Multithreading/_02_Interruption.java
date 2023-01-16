package Horstmann.Core1.Multithreading;

public class _02_Interruption {
    public static void main(String ... args) {
        // An example of the thread that can be safely terminated from the outside
        Runnable r = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Thread is running: " + i);
                    // This line of code is needed for a situation when the thread
                    // has to be interrupted while it's in the RUNNABLE state
                    // Therefore it is basically useless in this example because
                    // our thread invokes sleep method after every action
                    if (Thread.currentThread().isInterrupted()) break;
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                // It happens when the thread is in the WAITING state (both timed and ordinary)
                System.out.println("The thread has been interrupted while in timed waiting state");
            }
            finally {
                // Happens everytime in any circumstances (your average try-catch)
                System.out.println("Execution of the thread has come to its logical end");
            }
        };

        Thread t = new Thread(r);
        t.start();

        Runnable rX = () -> {
            try {
                Thread.sleep(3000);
                t.interrupt(); // That's how we request an interruption of any thread
            } catch (InterruptedException e) {
                System.out.println("The interrupting thread has been interruped"); 
                // This is not going to happen in this program
            }
        };

        Thread interruptingThread = new Thread(rX);
        interruptingThread.start();
    }
}
