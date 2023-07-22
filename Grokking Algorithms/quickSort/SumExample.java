package quickSort;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SumExample {
    public static void main(String[] args) {
        int[] arr = IntStream.range(0, 100).toArray();
        System.out.println(Arrays.toString(arr));
        int sum = sumOfArray(arr, 0);
        System.out.println("Sum of array: " + sum);
    }

    // Unfortunately, Java does not have Unmodifiable view of an array (or any view, actually),
    // hence I need to use index parameter
    private static int sumOfArray(int[] arr, int i) {
        if (i == arr.length) return 0;
        else return arr[i] + sumOfArray(arr, ++i);
    }
}
