package CodeWars;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("unused")
public class FindTheParityOutlier {
    public static void main(String ... args) {
        int[] a = {2, 4, 0, 100, 4, 11, 2602, 36};
        System.out.println(find(a));
    }

    static int find(int[] integers) {
        var even = Arrays.stream(integers).filter(e -> e % 2 == 0).toArray();
        if (even.length == 1) return even[0];
        else return Arrays.stream(integers).filter(e -> e % 2 != 0).findFirst().getAsInt();
    }

    static int find2(int[] integers) {
        Map<Boolean, List<Integer>> even = Arrays.stream(integers).boxed().collect(Collectors.partitioningBy(x -> x % 2 == 0));
        return even.get(true).size() == 1 ? even.get(true).get(0) : even.get(false).get(0);
    }

    public static int find3(int[] integers) {
        // Since we are warned the array may be very large, we should avoid counting values any more than we need to.

        // We only need the first 3 integers to determine whether we are chasing odds or evens.
        // So, take the first 3 integers and compute the value of Math.abs(i) % 2 on each of them.
        // It will be 0 for even numbers and 1 for odd numbers.
        // Now, add them. If sum is 0 or 1, then we are chasing odds. If sum is 2 or 3, then we are chasing evens.
        int sum = Arrays.stream(integers).limit(3).map(i -> Math.abs(i) % 2).sum();
        int mod = (sum == 0 || sum == 1) ? 1 : 0;

        return Arrays.stream(integers).parallel() // call parallel to get as much bang for the buck on a "large" array
                .filter(n -> Math.abs(n) % 2 == mod).findFirst().getAsInt();
    }
}
