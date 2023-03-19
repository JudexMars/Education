package Tinkoff.Task4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String ... args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt(); // кол-во чисел на бумажке
            int k = in.nextInt(); // ограничение на число операций
            List<Integer> nums = new ArrayList<>();
            
            long oldSum = 0;
            for (int i = 0; i < n; i++) {
                int num = in.nextInt();
                oldSum += num;
                nums.add(num);
            }
            
            long sum = oldSum;

            while (k > 0) {
                Collections.sort(nums, Collections.reverseOrder());
                List<List<Integer>> diffs = new ArrayList<>(nums.size());
                for (int i = 0; i < nums.size(); i++) {
                    int select = nums.get(i);
                    int x = makeBigger(select);
                    diffs.add(List.of(i, x - select));
                }
                Collections.sort(diffs, Comparator.comparingInt(e -> e.get(1)));
                Collections.reverse(diffs);
                int index = diffs.get(0).get(0);
                int diff = diffs.get(0).get(1);
                nums.set(index, nums.get(index) + diff);
                sum += diff;
                k--;
            }
            System.out.println(sum - oldSum);
        }
    }

    private static int makeBigger(int x) {
        String str = "" + x;
        int length = str.length();
        int res = 0;
        boolean change = true;
        for (int i = 0; i < length; i++) {
            char ch = str.charAt(i);
            if (change && str.charAt(i) != '9') {
                res += 9 * Math.pow(10, length - 1 - i);
                change = false;
            }
            else {
                res += Integer.parseInt(String.valueOf(ch)) * Math.pow(10, length - 1 - i);
            }
        }
        return res;
    }
}
