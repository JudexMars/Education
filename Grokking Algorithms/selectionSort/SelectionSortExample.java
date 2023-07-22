package selectionSort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SelectionSortExample {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(new Random()
        .ints(100, 0, 1000)
        .boxed().toList());

        System.out.println("Original list:\n" + list);
        list = selectionSort(list);
        System.out.println("Sorted list:\n" + list);
    }

    private static List<Integer> selectionSort(List<Integer> list) {
        List<Integer> result = new ArrayList<>();

        while (!list.isEmpty()) {
            Integer smallest = smallestInteger(list);
            list.remove(smallest);
            result.add(smallest);
        }

        return result;
    }

    private static Integer smallestInteger(List<Integer> list) {
        Integer smallest = list.get(0);
        for (var e : list) if (e < smallest) smallest = e;
        return smallest;
    }
}