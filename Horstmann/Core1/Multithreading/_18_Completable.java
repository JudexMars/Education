package Horstmann.Core1.Multithreading;

import java.util.concurrent.*;

public class _18_Completable {
    public static void main(String ... args) {
        /*
         * Essentially, CompletableFuture is just a better version of normal Future (it extends Future).
         * It has multiple useful methods for asynchronous computation. You provide needed Suppliers to
         * the Future in a chain to form a list of things that it has to do with the received result.
         */
        CompletableFuture<String> completableFuture = CompletableFuture
        .supplyAsync(() -> "Hello")
        .thenApply(s -> s + " World!");

        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("You fucked up really hard");
        }

        CompletableFuture<String> test2 = CompletableFuture.supplyAsync(() -> "Hello");
        test2.thenAccept(s -> System.out.println(s + " people"));

        CompletableFuture<String> test3 = CompletableFuture.supplyAsync(() -> "Hello");
        test3 = test3.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
        try {
            System.out.println(test3.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
