package quickSort;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class BinarySearchRevisited {
    public static void main(String[] args) {
        List<Integer> list = IntStream.range(0, 100).boxed().toList();
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Enter number:");
            int target = in.nextInt();
            int result = search(list, target, 0, list.size() - 1);
            System.out.println("Number position: " + (result != -1 ? result : "Not found"));
        }
    }

    private static int search(List<Integer> list, int target, int low, int high) {
        if (low > high) return -1;
        else {
            int mid = (low + high) / 2;
            if (list.get(mid) == target) return mid;
            else if (list.get(mid) > target) return search(list, target, low, mid - 1);
            else return search(list, target, mid + 1, high);
        }
    }
}
