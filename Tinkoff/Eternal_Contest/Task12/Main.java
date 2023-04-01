package Tinkoff.Eternal_Contest.Task12;

import java.util.Scanner;

public class Main {
    public static void main(String ... args) {
        try(Scanner in = new Scanner(System.in)) {
            long n = in.nextLong();

            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();

            long res = count(n, 1, 1, a, b, c);
            System.out.println(res);
        }
    }

    private static long count(long n, long curr, int x, int a, int b, int c) {
        //System.out.print((curr <= n ? "+" + x + " = " + curr + "\n": ""));
        if (curr > n) return 0;

        long res = 1;
        // System.out.println(x + " ? " + a);
        // System.out.println(x + " ? " + b);
        // System.out.println(x + " ? " + c);
        // if (x <= c) System.out.println("Ало блять");

        if (x <= a) res += count(n, curr + a, a, a, b, c);
        if (x <= b) res += count(n, curr + b, b, a, b, c);
        if (x <= c) res += count(n, curr + c, c, a, b, c);

        return res;
    }

    //private static long altCount
}
