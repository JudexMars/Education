package EffectiveJava.Chapter8;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class _00_Checking {
    public static void main(String ... args) {
        List<Integer> source = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        var modified = Helper.reshape(source, 5, 2);
        System.out.println(modified);
    }

    private static class Helper {
        /**
         * @param <T> type of elements in the source list
         * @param src source list (must be non-null)
         * @param m number of elements in each sublist
         * @param n number of sublists
         * @return a List containing all the same elements stored in different lists in number of n.
         * Each list has exactly m elements.
         * @throws ArithmeticException if the shape size is <= 0
         * @throws NullPointerException if the source list is null
         */
        public static <T> List<List<T>> reshape(List<T> src, int m, int n) {
            Objects.requireNonNull(src, "Source list must be non-null"); // easy checking of null
            if (m <= 0 || n <= 0) 
                throw new ArithmeticException("Shape variables must be > 0: m=" + m + "; n=" + n);
            List<List<T>> res = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                List<T> line = new ArrayList<>();
                for (int j = 0; j < m && m * i + j < src.size(); j++) 
                    line.add(src.get(m * i + j));
                res.add(line);
            }
            return res;
        }
    }
}
