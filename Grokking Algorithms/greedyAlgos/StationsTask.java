package greedyAlgos;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StationsTask {
    public static void main(String[] args) {
        // States that need to be covered by radio stations
        Set<String> states = Set.of("mt", "wa", "or", "id", "nv", "ut", "ca", "az");

        // Stations which can be bought
        Map<String, Set<String>> stations = Map.of(
            "kone", Set.of("id", "nv", "ut"),
            "ktwo", Set.of("wa", "id", "mt"),
            "kthree", Set.of("or", "nv", "ca"),
            "kfour", Set.of("nv", "ut"),
            "kfive", Set.of("ca", "az")
        );

        Set<String> chosenStations = solve(states, stations);

        System.out.println("States:\n" + states);
        System.out.println("Stations:\n" + stations);
        System.out.println("\nChosen stations:\n" + chosenStations);
    }

    private static Set<String> solve(Set<String> states, Map<String, Set<String>> stations) {
  
        Set<String> result = new HashSet<>(); // chosen stations
        Set<String> neededStates = new HashSet<>(states); // states that need to be covered

        while (!neededStates.isEmpty()) {
            String bestStation = ""; // the best station from available
            Set<String> coveredStates = new HashSet<>();
            for (var station : stations.entrySet()) {
                String name = station.getKey(); // station's name
                Set<String> coverage = station.getValue(); // states that this station covers

                Set<String> covered = new HashSet<>(neededStates);
                covered.retainAll(coverage);
                if (covered.size() > coveredStates.size()) {
                    bestStation = name;
                    coveredStates = covered;
                }
            }
            result.add(bestStation);
            neededStates.removeAll(coveredStates);
        }
        return result;
    }
}
