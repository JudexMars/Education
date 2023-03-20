package Tinkoff.Eternal_Contest.Task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String ... args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt(); // кол-во сотрудников
            int t = in.nextInt(); // время, когда один из сотрудников покинет офис (в минутах)

            List<Integer> floors = new ArrayList<>();

            for (int i = 0; i < n; i++) 
                floors.add(in.nextInt());
            int leaving = in.nextInt() - 1; // номер этажа с уходящим сотрудником
        
            // Успеваем ли мы добраться до уходящего сотрудника, если начнем снизу
            if (floors.get(leaving) - floors.get(0) <= t) {
                int currentFloor = floors.get(0);
                System.out.println(floors.get(n - 1) - currentFloor);
            }
            // Успеваем ли мы добраться до уходящего сотрудника, если начнем сверху
            else if (floors.get(n - 1) - floors.get(leaving) <= t) {
                int currentFloor = floors.get(n - 1);
                System.out.println(currentFloor - floors.get(0));
            }
            // Едем сразу к нему на этаж
            else {
                int currentFloor = floors.get(leaving);
                int up = floors.get(n - 1) - currentFloor;
                int down = currentFloor - floors.get(0);
                if (up < down) {
                    System.out.println(2 * up + down);
                }
                else {
                    System.out.println(2 * down + up);
                }
            }
        }
    }
}
