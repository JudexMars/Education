package dynamicProg;

import java.util.List;

public class Hike {
    public static void main(String[] args) {
        List<Item> items = List.of(
            new Item("Water", 10, 3),
            new Item("Book", 3, 1),
            new Item("Food", 9, 2),
            new Item("Jacket", 5, 2),
            new Item("Camera", 6, 1)
        );

        int capacity = 6;

        System.out.println("Available items:\n" + items);
        System.out.println("Backpack capacity: " + capacity);

        int maxBackpack = maxBackpack(items, capacity);


        System.out.println("Maximum cost of the backpack: " + maxBackpack);
    }

    private static int maxBackpack(List<Item> items, int capacity) {
        int[][] table = new int[items.size()][capacity];

        for (int i = 0; i < table.length; i++) {
            var item = items.get(i);
            for (int j = 0; j < table[i].length; j++) {
                if (item.weight() <= j + 1) {
                    if (i == 0) table[i][j] = item.cost();
                    else {
                        if (item.weight() == j + 1) table[i][j] = Math.max(item.cost(), table[i - 1][j]);
                        else table[i][j] = Math.max(item.cost() + table[i - 1][j - item.weight()], table[i - 1][j]);
                    }
                }
                else if (i != 0) table[i][j] = table[i - 1][j];
            }
        }

        return table[items.size() - 1][capacity - 1];
    }

    private record Item(String name, int cost, int weight) {
    }
}
