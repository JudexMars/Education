package Tinkoff.Task9;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String ... args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            List<Integer> prices = new ArrayList<>();
            for (int i = 0; i < n; i++) prices.add(in.nextInt());

            int[][] table = new int[n  + 1][n + 1];
             

            fill(table, 100000);

            table[0][0] = 0;

            // for (int i = 0; i < n; i++) {
            //     table[0][i] = Integer.MAX_VALUE;
            // }

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= i; j++) {
                    table[i][j] = Math.min(table[i][j], table[i - 1][j] + prices.get(i - 1));
                    if (prices.get(i - 1) > 100) {
                        table[i][j + 1] = Math.min(table[i][j + 1], table[i - 1][j] + prices.get(i - 1));
                    }
                    if (j >= 1) {
                        table[i][j - 1] = Math.min(table[i][j - 1], table[i - 1][j]);
                    }

                }
            }
            //print2DArray(table);
            System.out.println(findMinInColumn(table, n));
        }
    }

    private static void print2DArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + ", ");
            }
            System.out.println();
        }   
    }

    private static int[][] fill(int[][] arr, int x) {
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[i].length; j++)
                arr[i][j] = x;
        return arr;
    }

    private static int findMinInColumn(int[][] arr, int column) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[column][i]);
        }
        return min;
    }
}
