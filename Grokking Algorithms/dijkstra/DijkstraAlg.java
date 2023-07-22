package dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DijkstraAlg {
    public static void main(String[] args) {
        System.out.println("Hello world");

        Map<String, Map<String, Integer>> graph = new HashMap<>(); // граф
        Map<String, Integer> costs = new HashMap<>(); // стоимости узлов
        Map<String, String> parents = new HashMap<>(); // родители узлов

        graph.put("start", new HashMap<>(Map.of("a", 6, "b", 2)));
        graph.put("a", new HashMap<>(Map.of("fin", 1)));
        graph.put("b", new HashMap<>(Map.of("a", 3, "fin", 5)));
        graph.put("fin", Collections.emptyMap());

        costs.put("a", 6);
        costs.put("b", 2);
        costs.put("fin", Integer.MAX_VALUE);

        parents.put("a", "start");
        parents.put("b", "start");

        System.out.println("Beginning Dijkstra's algorithm");

        int result = dijkstra(graph, costs, parents);
        System.out.println("Shortest path: " + result);
    }

    private static int dijkstra(Map<String, Map<String, Integer>> graph,
        Map<String, Integer> costs,
        Map<String, String> parents) {
            List<String> processed = new ArrayList<>();
            String node = findLowestCostNode(costs, processed);
            while (node != null) {
                var cost = costs.get(node);
                var neighbours = graph.get(node);

                for (var n : neighbours.keySet()) {
                    var newCost = cost + neighbours.get(n);
                    if (costs.get(n) > newCost) {
                        costs.put(n, newCost);
                        parents.put(n, node);
                    }
                }

                processed.add(node);
                node = findLowestCostNode(costs, processed);
            }
        String parent = parents.get("fin");
        System.out.print("fin");
        while (parent != null) {
            System.out.print(" <- " + parent);
            parent = parents.get(parent);
        }
        System.out.println();
        return costs.get("fin");
    }

    private static String findLowestCostNode(Map<String, Integer> costs, List<String> processed) {
        int lowest = Integer.MAX_VALUE;
        String result = null;
        for (var entry : costs.entrySet()) 
            if (entry.getValue() < lowest && !processed.contains(entry.getKey())) result = entry.getKey();
        return result;
    }
}
