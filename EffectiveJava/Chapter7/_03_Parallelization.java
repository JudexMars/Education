package EffectiveJava.Chapter7;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

public class _03_Parallelization {
    public static void main(String ... args) {
        long resultA, resultB;

        Instant nowA = Instant.now();
        resultA = pi(1000000);
        Instant endA = Instant.now();

        Instant nowB = Instant.now();
        resultB = piParallel(1000000);
        Instant endB = Instant.now();

        var timeA = Duration.between(nowA, endA).getNano();
        var timeB = Duration.between(nowB, endB).getNano();
        double difference = (double)timeA / (double)timeB;

        System.out.println("Sequential computation time:\n" + timeA + " ns");
        System.out.println("Answer: " + resultA);
        System.out.println("Parallelized computation time:\n" + timeB + " ns");
        System.out.println("Answer: " + resultB);
        System.out.println("Coefficient: " + difference);
    }

    /*
     * This method computes the number of primes less than or equal to n
     */
    private static long pi(long n) {
        return LongStream.rangeClosed(2, n)
            .mapToObj(BigInteger::valueOf)
            .filter(i -> i.isProbablePrime(50))
            .count();
    }

    private static long piParallel(long n) {
        return LongStream.rangeClosed(2, n)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .filter(i -> i.isProbablePrime(50))
            .count();
    }
}
