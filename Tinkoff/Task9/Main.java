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

            int[][] table = new int[n + 1][n + 1];
            
            //fill(table, Integer.MAX_VALUE);
            table[0][0] = 0;

            for (int i = 1; i < n + 1; i++) {
                table[0][i] = Integer.MAX_VALUE;
                //if (i != 0) table[0][i] += table[0][i - 1];
                table[i][0] = prices.get(i - 1);
                //table[i][0] = 0;
            }

            for (int i = 1; i < n; i++) {
                for (int j = 1; j < n; j++) {
                    //if (i == 0 && j == 0) continue;
                    if (prices.get(i - 1) <= 100) {
                        System.out.println(table[i - 1][j]);
                        table[i][j] = Math.min(table[i][j - 1] + prices.get(i - 1), table[i - 1][j + 1]);
                    }
                        else {
                        table[i][j] = Math.min(table[i - 1][j - 1] + prices.get(i - 1), table[i - 1][j + 1]);
                    }
                }
            }
            print2DArray(table);
            System.out.println(findMinInColumn(table, n - 1));
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
