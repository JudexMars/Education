package Tinkoff.Eternal_Contest.Task5;

import java.util.Scanner;

public class Main {
    public static void main(String ... args) {
        try (Scanner in = new Scanner(System.in)) {
            long l = in.nextLong();
            long r = in.nextLong();

            int count = 0;

            for (long i = l; i <= r; i++) {
                String str = "" + i;
                boolean correct = true;
                for (int j = 0; j < str.length(); j++) {
                    if (str.charAt(j) != str.charAt(0)) {
                        correct = false;
                        break;
                    }
                }
                if (correct) {count++; System.out.println("..." + i); }
            }

            System.out.println(count);
        }
    }
}
