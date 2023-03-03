package EffectiveJava.Chapter6;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import EffectiveJava.Chapter6._02_EnumMaps.Animal.LifeCycle;
import EffectiveJava.Chapter6._02_EnumMaps.Phase.Transition;

public class _02_EnumMaps {
    public static void main(String ... args) {
        List<Animal> animals = List.of(new Animal("Bear", LifeCycle.BABY),
        new Animal("Dolphin", LifeCycle.OLD), new Animal("Bee", LifeCycle.MATURE),
        new Animal("Penguin", LifeCycle.BABY), new Animal("Monkey", LifeCycle.YOUNG));

        Map<LifeCycle, Set<Animal>> zoo;
        zoo = new EnumMap<>(LifeCycle.class);

        for (var age : LifeCycle.values()) zoo.put(age, new HashSet<>());
        for (var animal : animals) zoo.get(animal.lifeCycle).add(animal);

        System.out.println(zoo);

        /*
         * Why would I use EnumMap instead of HashMap? EnumMap is simply more optimizied for the usage of enums.
         * Internally, it works as an array, which stores constants in their natural order using ordinal() method.
         * You could do the same without Set at all but it would require you to write unchecked casts (remember how
         * array deal with generics) therefore making your code more error-prone. EnumMap already has everything you need
         * so why bother with your own implementation? The same logic applies to the usage of arrays as well. You shouldn't
         * use arrays in Java 95% of time: Collections API does the job.
         */

        Phase from = Phase.SOLID;
        Phase to = Phase.GAS;
        Transition transition = Transition.from(from, to);

        System.out.println(transition);
    }

    static class Animal {
        enum LifeCycle { BABY, YOUNG, MATURE, OLD }

        private final String name;
        LifeCycle lifeCycle;

        Animal(String name, LifeCycle lifeCycle) {
            this.name = name;
            this.lifeCycle = lifeCycle;
        }

        @Override
        public String toString() {
            return name + " | " + lifeCycle.toString();
        }
    }

    static enum Phase {
        SOLID, LIQUID, GAS;
        public enum Transition {
           MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
           BOIL(LIQUID, GAS),   CONDENSE(GAS, LIQUID),
           SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);
           private final Phase from;
           private final Phase to;
           Transition(Phase from, Phase to) {
              this.from = from;
              this.to = to;
  }
           // Initialize the phase transition map
           private static final Map<Phase, Map<Phase, Transition>>
             m = Stream.of(values()).collect(Collectors.groupingBy(t -> t.from,
              () -> new EnumMap<>(Phase.class),
              Collectors.toMap(t -> t.to, t -> t,
                 (x, y) -> y, () -> new EnumMap<>(Phase.class))));
           public static Transition from(Phase from, Phase to) {
              return m.get(from).get(to);
  } }
  }
}
