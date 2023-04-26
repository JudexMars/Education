package EffectiveJava.Chapter9;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class _00_Random {

    private static Random rnd = new Random();
    private static ThreadLocalRandom thdRnd = ThreadLocalRandom.current();

    public static void main(String ... args) {
        System.out.println("Bad Random:");
        badRandom();
        System.out.println("Good Random:");
        goodRandom();
        /*
         * Moral of this story: know your libraries! Smart people in higher offices have already developed nice solutions
         * for your problems!
         * In case of pseudorandom generation, it would have been even better to use ThreadLocalRandom which is faster and
         * more useful in parallel computations
         */
        System.out.println("Perfect Random:");
        perfectRandom();
    }

    private static void badRandom() {
        int n = (int) (Integer.MAX_VALUE / 3);
        int low = 0, high = 0;
        for (int i = 0; i < n; i++) {
            if (rnd.nextInt() % n < n / 2) low++;
            else high++;
        }
        System.out.println("Low: " + low);
        System.out.println("High: " + high);
        System.out.println("Total: " + high + low);
        System.out.println("Percentage of low: " + Math.round((double) (low) / (double)(high + low) * 100) + "%");
    }

    private static void goodRandom() {
        int n = (int) (Integer.MAX_VALUE / 3);
        int low = 0, high = 0;
        for (int i = 0; i < n; i++) {
            if (rnd.nextInt(n) < n / 2) low++;
            else high++;
        }
        System.out.println("Low: " + low);
        System.out.println("High: " + high);
        System.out.println("Total: " + high + low);
        System.out.println("Percentage of low: " + Math.round((double) (low) / (double)(high + low) * 100) + "%");
    }

    private static void perfectRandom() {
        int n = (int) (Integer.MAX_VALUE / 3);
        int low = 0, high = 0;
        for (int i = 0; i < n; i++) {
            if (thdRnd.nextInt(n) < n / 2) low++;
            else high++;
        }
        System.out.println("Low: " + low);
        System.out.println("High: " + high);
        System.out.println("Total: " + high + low);
        System.out.println("Percentage of low: " + Math.round((double) (low) / (double)(high + low) * 100) + "%");
    }
}