package Horstmann.Core1.Multithreading;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _14_FutureTask {
    public static void main(String ... args) {
        Callable<Integer> call = () -> {
            System.out.println("Generating new number...");
            Thread.sleep(3000);
            return new Random().nextInt();
        };

        var randomNumber = new FutureTask<>(call);
        var t = new Thread(randomNumber);
        t.start();

        try {
            System.out.println(randomNumber.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Operation aborted!");
        }

        randomNumber = new FutureTask<>(call);
        t = new Thread(randomNumber);

        try {
            System.out.println(randomNumber.get(1000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println("Operation aborted!");
        }
    }
}
