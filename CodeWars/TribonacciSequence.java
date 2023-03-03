package CodeWars;

import java.util.Arrays;

public class TribonacciSequence {
    public static void main(String ... args) {
        double[] a = { 1, 1, 1 };
        System.out.println(Arrays.toString(tribonacci(a, 12)));
    }

    static double[] tribonacci(double[] s, int n) {
        if (n == 0) return new double[0];
        double[] res = new double[n];
        res[0] = s[0]; 
        if (n >= 2) res[1] = s[1]; 
        if (n >= 3) res[2] = s[2];
        for (int i = 3; i < n; i++) 
            res[i] = res[i - 3] + res[i - 2] + res[i - 1];
        return res;
    }
}
