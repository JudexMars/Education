package Tinkoff.Eternal_Contest.Task6;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String ... args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();

            int wrongEven = -1;
            int wrongOdd = -1;

            List<Integer> safeEven = new ArrayList<>();
            List<Integer> safeOdd = new ArrayList<>();

            boolean itsOver = false;
            for (int i = 0; i < n; i++) {
                int height = in.nextInt();
                if (height % 2 == 0 && (i + 1) % 2 != 0) {
                    if (wrongEven != -1) { itsOver = true; }
                    wrongEven = i + 1;
                }
                else if (height % 2 != 0 && (i + 1) % 2 == 0) {
                    if (wrongOdd != -1) { itsOver = true; }
                    wrongOdd = i + 1;
                }
                else {
                    if (height % 2 == 0) safeEven.add(i + 1);
                    else if (height % 2 != 0) safeOdd.add(i + 1);
                }
            }


            if (wrongOdd == -1 && wrongEven == -1) {
                if (safeEven.size() >= 2) {
                    wrongEven = safeEven.get(0);
                    wrongOdd = safeEven.get(1);
                }
                else if (safeOdd.size() >= 2) {
                    wrongEven = safeOdd.get(0);
                    wrongOdd = safeOdd.get(1);
                }
            }

            if (wrongOdd == -1 || wrongEven == -1) itsOver = true;

            if (itsOver) { 
                wrongEven = -1; wrongOdd = -1;
             }

            System.out.println(wrongEven + " " + wrongOdd);
        }
    }
}
