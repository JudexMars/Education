package Horstmann.Core1.Multithreading;

import java.util.concurrent.*;
import java.util.*;

public class _17_ForkJoin {
    public static void main(String ... args) {
        final int arrSize = 100;
        int[] arr = new int[arrSize];

        for (int i = 0; i < arrSize; i++) 
            arr[i] = new Random().nextInt(0, 100);
        
        System.out.println("Original:\n" + Arrays.toString(arr));
        
        /*
         * ForkJoinPool allows us to compute tasks sim
         */
        try (var pool = new ForkJoinPool()) {
            pool.invoke(new parallelQuickSort(arr, 0, arrSize - 1));
        }
        System.out.println("Sorted:\n" + Arrays.toString(arr));
    }
}

class parallelQuickSort extends RecursiveAction {

    private int[] arr;
    private int begin;
    private int end;

    public parallelQuickSort(int[] arr, int begin, int end) {
        this.arr = arr;
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
    
            var first = new parallelQuickSort(arr, begin, partitionIndex-1);
            var second = new parallelQuickSort(arr, partitionIndex+1, end);
            invokeAll(first, second);
        }
    }

    private static int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin-1);
    
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
    
                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }
    
        int swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;
    
        return i+1;
    }
}