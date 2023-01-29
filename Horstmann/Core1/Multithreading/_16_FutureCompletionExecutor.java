package Horstmann.Core1.Multithreading;

import java.util.concurrent.*;
import java.util.*;

public class _16_FutureCompletionExecutor {
    public static void main(String ... args) {
        var executor = Executors.newCachedThreadPool();
        var completionExecutor = new ExecutorCompletionService<EquationAnswer>(executor);
        //List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) 
            for (int j = 0; j < 10; j ++) 
                for (int k = 0; k < 10; k++) 
                    completionExecutor.submit(new QuadraticEquation(i, j, k));

        /*
         * The main difference between the default ExecutorService and the ExecutorCompletionService
         * is that the later one allows us to get results in an order or completion.
         * On the other hand ExecutorService gives us results in the same order as
         * we submitted tasks. That's it. In the terminal you can see that this program prints results for those
         * equations that have been completed earlier. Essentially, this way you are able to see
         * which thread acts first etc. 
         */

        for (int i = 0; i < 1000; i++) {
            try {
                var task = completionExecutor.take();
                System.out.println(task.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }
}

class EquationAnswer {
    private final String info;
    private final List<Double> roots = new ArrayList<>();

    public EquationAnswer(String info, double ... roots) { 
        for (var r : roots) if (!this.roots.contains(r)) this.roots.add(r);
        this.info = info;
    }
    public List<Double> getRoots() {
        return new ArrayList<>(roots);
    }

    public String toString() {
        String result = String.format(this.info + "\n");
        for (int i = 0; i < roots.size(); i++) result += String.format("x" + i + " = " + roots.get(i) + " ");
        return result;
    }
}

class QuadraticEquation implements Callable<EquationAnswer> { 
    private double a;
    private double b;
    private double c;

    public QuadraticEquation(double a, double b, double c) {
        this.a =  a;
        this.b = b;
        this.c = c;
    }

    public EquationAnswer call() {
        double D = Math.sqrt(b * b - 4 * a * c);
        if (D == Double.NaN) return null;
        double x1 = (-b + D) / (2 * a);
        double x2 = (-b - D) / (2 * a);

        return new EquationAnswer("a: " + a + " b: " + b + " c: " + c, x1, x2);
    }
}
