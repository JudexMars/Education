package Horstmann.Core1.Multithreading;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class _11_EffectiveCollections {
    public static void main(String ... args) {
        /*
         * Concurrent collections allow us to both change and inspect them with no exceptions or anything.
         * It just works. Though, obviously
         */
        ConcurrentLinkedQueue<AtomicInteger> cQueue = new ConcurrentLinkedQueue<AtomicInteger>(List
        .of(new AtomicInteger(1),
        new AtomicInteger(2),
        new AtomicInteger(3),
        new AtomicInteger(4),
        new AtomicInteger(5),
        new AtomicInteger(6),
        new AtomicInteger(7)));

        Runnable rWriter = () -> {
            for (var x : cQueue) {
                x.set(x.get() * 2);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable rReader = () -> {
            for (var x : cQueue) {
                System.out.println(x);
                try {
                    Thread.sleep(299);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread tWriter = new Thread(rWriter);
        Thread tReader = new Thread(rReader);

        tWriter.start();
        tReader.start();
    }
}
