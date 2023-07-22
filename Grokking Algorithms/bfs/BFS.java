package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFS {
    public static void main(String[] args) {
        Map<String, List<String>> graph = Map.of(
            "you", List.of("alice", "bob", "claire"),
            "bob", List.of("anuj", "peggy"),
            "alice", List.of("peggy"),
            "claire", List.of("thom", "jonny"),
            "thom", List.of("thane")
        );

        int result = bfs(graph, "you", "thane");

        System.out.println("Steps required: " + (result != -1 ? result : "Not found"));
    }

    private static int bfs(Map<String, List<String>> graph, String source, String target) {
        Queue<String> nodes = new ArrayDeque<>();
        List<String> searched = new ArrayList<>();
        int steps = 0;
        nodes.add(source);
        while (!nodes.isEmpty()) {
            int levelSize = nodes.size();
            while (levelSize-- != 0) {
                var node = nodes.poll();
                if (node.equals(target)) return steps;
                searched.add(node);
                for (var neighbour : graph.getOrDefault(node, Collections.emptyList()))
                    if (!searched.contains(neighbour)) nodes.add(neighbour);
            }
            steps++;
        }
        return -1;
    }
}
