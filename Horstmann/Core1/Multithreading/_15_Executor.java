package Horstmann.Core1.Multithreading;

import java.util.*;
import java.util.concurrent.*;

@SuppressWarnings("unused")
public class _15_Executor {
    public static void main(String ... args) {
        var exec = Executors.newCachedThreadPool();
        
        Callable<Integer> simpleTask = () -> {
            for (int i = 0; i < 10; i++) {
          System.out.println(Thread.currentThread().getName());
          try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            }  
        }
            return 0;
        };

        var x = exec.submit(simpleTask);
        try {
            System.out.println(x.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("It's over");
            Thread.currentThread().interrupt();
        }

        var scheduledExec = Executors.newScheduledThreadPool(3);
        Runnable scheduledTask = () -> {
            System.out.println(new Date());
        };
        var y = scheduledExec.scheduleAtFixedRate(scheduledTask, 2, 1, TimeUnit.SECONDS);
        System.out.println("Scheduled task");
    }
}
