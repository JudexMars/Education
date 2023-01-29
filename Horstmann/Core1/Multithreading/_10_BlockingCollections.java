package Horstmann.Core1.Multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class _10_BlockingCollections {
    public static void main(String ... args) throws InterruptedException {
        /*
         * Java already has a bunch of collections designed for multithreading specifically. It is easier to use them
         * instead of introducing your own synchronization solutions in most of the cases.
         * There isn't anything too difficult in understanding it.
         */
        System.out.println("BlockingQueue test:");
        BlockingQueue<Integer> exampleQueue = new ArrayBlockingQueue<>(3);

        exampleQueue.add(0); // will thrown an exception if the queue is already full
        exampleQueue.offer(1); // will return false if the queue is already full
        exampleQueue.put(3); // will block itself if the queue is already full and wait until enough space is available

        System.out.println(exampleQueue);

        //exampleQueue.put(4); // this line would block the main thread!

        // So we create two additional threads in which we try to add a new value to the queue as well
        // as to retrieve the head of it
        new Thread(() -> {try {
            exampleQueue.put(4);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }}).start();

        System.out.println(exampleQueue);

        new Thread(() -> {try {
            System.out.println(exampleQueue.take());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }}).start();

        // Wait a bit to see the end result
        Thread.sleep(100);
        System.out.println(exampleQueue);
        
        /*
         * DelayQueue manages the whole process of working with the collection
         * by introducing a delay mechanism. You have to simply provide an object of a class
         * that implements 'Delayed' interface and specify the delay.
         */
        Thread.sleep(5000);
        System.out.println("DelayQueue test:");
        BlockingQueue<DelayedObject<Integer>> delayQueue = new DelayQueue<>();
        
        new Thread(new Producer<DelayedObject<Integer>>(new DelayedObject<>(1, 3000), delayQueue)).start();
        new Thread(new Producer<DelayedObject<Integer>>(new DelayedObject<>(2, 1000), delayQueue)).start();
        new Thread(new Producer<DelayedObject<Integer>>(new DelayedObject<>(3, 5000), delayQueue)).start();

        new Thread(new Consumer<>(delayQueue)).start();
        new Thread(new Consumer<>(delayQueue)).start();
        new Thread(new Consumer<>(delayQueue)).start();

        System.out.println(delayQueue);

        /*
         * There is also a collection called TransferQueue. The main feature is that the Producer is going to block itself
         * until the element that is being transfered is received by the Consumer
         */
        Thread.sleep(5000);
        System.out.println("TransferQueue test:");
        TransferQueue<String> transferQueue = new LinkedTransferQueue<>();
        // It may sound complicated so let's test it right away
        new Thread(new TransferProducer<>("Can you hear us?", transferQueue)).start();;
        Thread.sleep(5000);
        new Thread(new Consumer<>(transferQueue)).start();
    }
}

class DelayedObject<E> implements Delayed {

    private E data;
    private long startTime;

    public DelayedObject(E data, long delayInMillis) {
        this.data = data;
        this.startTime = delayInMillis + System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        return (int)(this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.startTime - System.currentTimeMillis(), unit);
    }

    @Override
    public String toString() {
        return data.toString();
    }
}

class Producer<E> implements Runnable {

    private E data;
    private BlockingQueue<E> queue;

    public Producer(E data, BlockingQueue<E> queue) { this.data = data; this.queue = queue; }

    @Override
    public void run() {
        try {
            queue.put(data);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer<E> implements Runnable {

    private BlockingQueue<E> queue;

    public Consumer(BlockingQueue<E> queue) { this.queue = queue; }

    @Override
    public void run() {
        try {
            Object o = queue.take();
            System.out.println(o.getClass().getName() + ": " + o);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class TransferProducer<E> implements Runnable {

    private E data;
    private TransferQueue<E> queue;

    public TransferProducer(E data, TransferQueue<E> queue) { this.data = data; this.queue = queue; }

    @Override
    public void run() {
        try {
            System.out.println("Delivery in process...");
            queue.transfer(data); // Producer waits for the Consumer!
            System.out.println("The message was delievered!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}