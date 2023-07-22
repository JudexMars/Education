import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Task2 {
    public static void main(String ... args) {
        List<Integer> list = IntStream.range(0, 256).boxed().toList();
        System.out.println(list);
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Enter the number");
            int target = in.nextInt();
            int result = search(list, target);
            System.out.println("Number position: " + (result != -1 ? result : "Not found"));
        }
    }

    private static int search(List<Integer> list, int target) {
        int low = 0;
        int high = list.size() - 1;
        int checksCount = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (list.get(mid).equals(target)) { System.out.println("Amount of checks: " + checksCount); return mid; }
            if (list.get(mid) > target) high = mid - 1;
            if (list.get(mid) < target) low = mid + 1;
            checksCount++;
        }

        return -1;
    }
}