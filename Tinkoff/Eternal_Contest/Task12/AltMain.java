package Tinkoff.Eternal_Contest.Task12;

import java.util.Scanner;

public class AltMain {

    //private static final int max = Integer.MAX_VALUE;
    //private static final int max = 100;

    public static void main(String ... args) {
        try(Scanner in = new Scanner(System.in)) {
            long n = in.nextLong();

            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();

            //long[][][] dp = new long[max][max][max];

            long count = 0;

            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - a * i; j++) {
                    for (int k = 0; k < n - a * i - b * j; k++) {
                        long x = i * a + j * b + k * c + 1;
                        //dp[i][j][k] = i * a + j * b + k * c + 1;
                        // if (dp[i][j][k] > n) break;
                        //if (x > n) break;
                        if (x <= n) count++;
                        //if (x <= n) System.out.println(x);
                    }
                }
            }

            System.out.println(count);
        }
    }
}
