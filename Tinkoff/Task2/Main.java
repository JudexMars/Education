package Tinkoff.Task2;

import java.util.Scanner;


public class Main {
    public static void main(String ... args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt(); // количество людей (а соответственно и кусков)
            System.out.println(f(n));
        }
    }

    private static int f(int n){
        if (n % 2 == 0) return n / 2;
        return (n + 1) / 2;
    }
}
