package quickSort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        List<Integer> list = new Random().ints(20, 0, 1000).boxed().toList();
        System.out.println("Original:\n" + list);
        list = sort(list);
        System.out.println("Sorted:\n" + list);
    }

    private static List<Integer> sort(List<Integer> list) {
        if (list.size() <= 1) return list;
        int pivot = list.get(0);
        List<Integer> less = list.stream().filter(x -> x < pivot).toList();
        List<Integer> greater = list.stream().filter(x -> x > pivot).toList();
        
        List<Integer> result = new ArrayList<>();
        result.addAll(sort(less));
        result.add(pivot);
        result.addAll(sort(greater));
        return result;
    }
}
