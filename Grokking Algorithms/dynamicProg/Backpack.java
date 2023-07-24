package dynamicProg;

import java.util.List;

public class Backpack {
    public static void main(String[] args) {
        List<Item> items = List.of(
            new Item("Guitar", 1500, 1),
            new Item("Record player", 3000, 4),
            new Item("Laptop", 2000, 3)
        );

        int capacity = 4;

        int maxBackpack = maxBackpack(items, capacity);

        System.out.println("Available items:\n" + items);
        System.out.println("Backpack capacity: " + capacity);
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
                else table[i][j] = table[i - 1][j];
            }
        }

        return table[items.size() - 1][capacity - 1];
    }

    private record Item(String name, int cost, int weight) {
    }
}
