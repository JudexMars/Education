package dynamicProg;

import java.util.Arrays;
import java.util.stream.IntStream;

public class CommonSequence {
    public static void main(String[] args) {
        String input = "blue";
        String sample = "clues";

        System.out.println("Input: " + input);
        System.out.println("Sample: " + sample);
        var result = solve(sample, input);
        System.out.println("Max length of the common sequence: " + result);
    }

    private static int solve(String a, String b) {
        int[][] table = new int[a.length()][b.length()];

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (a.charAt(i) == b.charAt(j)) table[i][j] = 1 + (i != 0 && j != 0 ? table[i - 1][j - 1] : 0);
                else table[i][j] = Math
                .max(j != 0 ? table[i][j - 1] : 0,
                     i != 0 ? table[i - 1][j] : 0);
            }
        }

        return Arrays
        .stream(table)
        .flatMapToInt(IntStream::of)
        .max()
        .getAsInt();
    }
}
