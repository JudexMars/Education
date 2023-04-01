package EffectiveJava.Chapter8;

public class _01_Varargs {
    public static void main(String ... args) {
        int[] arr = new int[] {1, 2, 3, 4, 5};
        var result = findMin(arr);
        System.out.println(result);
        result = findMinValue(1, 2, 3, 4, 5);
        System.out.println(result);
    }

    // Don't do it. This is wrong!
    private static int findMin(int ... args) {
        if (args.length == 0) throw new IllegalArgumentException("Too few arguments");
        int res = args[0];
        for (var x: args) 
            if (x < res) res = x;
        return res;
    }

    // This is OK. Now the method requires you to provide atleast one argument
    private static int findMinValue(int first, int ... remaining) {
        int res = first;
        for (var x: remaining) if (x < res) res = x;
        return res;
    }
}
