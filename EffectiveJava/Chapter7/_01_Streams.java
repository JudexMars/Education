package EffectiveJava.Chapter7;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class _01_Streams {
    public static void main(String ... args) {
        Path dictionary = Paths.get("EffectiveJava/Chapter7/dict.txt");
        int minGroupSize = 1;

        //Map<String, Set<String>> groups = new HashMap<>();
        try (Stream<String> words = Files.lines(dictionary)) {
            words.collect(Collectors.groupingBy(word -> alphabetize(word)))
                    .values().stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .forEach(g -> System.out.println(g.size() + ": " + g));
        }
        catch (Exception ex) {}

        /*
         * The code below makes a table of word frequency in the provided text.
         * And while you may think that it is a good code, it is not
         * The problem here in the basic understanding of functional programming and streams themselves
         * Functional programming paradigm strongly prohibits 
         * 1) changing of mutable objects that are not a part of the stream
         * 2) depending on parameters which are located outside of the expression
         * So basically what you see here is an iterative approach mimicking the streams paradigm
         */
        String text = "World operation warmonger world over heaven desperado love money rocknroll over world";
        var s = Arrays.stream(text.split(" "));
        Map<String, Integer> frequencyTable = new HashMap<>();
        s.forEach(word -> frequencyTable.merge(word.toLowerCase(), 1, Integer::sum));
        System.out.println("Scanned text:\n" + text);
        frequencyTable.forEach((k, v) -> System.out.println(k + ": " + v));

        /*
         * And that's how it should be
         */
        System.out.println("The appropriate way works the same");
        s = Arrays.stream(text.split(" "));
        Map<String, Long> newFrequencyTable = s.collect(Collectors
            .groupingBy(word -> word.toLowerCase(), Collectors.counting()));
        newFrequencyTable.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}