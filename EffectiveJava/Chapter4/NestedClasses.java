package EffectiveJava.Chapter4;

import java.util.Arrays;

public class NestedClasses {
    public static void main(String ... args) {
        var enclosingObject = new EnclosingClass(2.505_203_101);
        enclosingObject.nestedClass.print();

        // Local class
        class LocalClass {
            private int[] array;
            public LocalClass(Integer ... numbers) {
                array = new int[numbers.length];
                for (int i = 0; i < numbers.length; i++) array[i] = numbers[i];
            }
            public void print() { System.out.println(Arrays.toString(array)); };
        }

        LocalClass localObject = new LocalClass(1, 2, 3, 4, 5);
        localObject.print();

        // Anonymous class
        var anonymousObject = new LocalClass(1, 5, 2, 6, 4) {
            private String[] words;
            public void fillWords(String ... words) { this.words = words.clone(); }
            public void printWords() { System.out.println(Arrays.toString(words)); }
        };

        anonymousObject.fillWords("Hello", "Привет", "Bonjour");
        anonymousObject.printWords();
    }
}

class EnclosingClass {
    private Object data;

    public EnclosingClass(Object data) { this.data = data; nestedClass = new NestedClass(); }

    public NestedClass nestedClass;

    // Nested instance class. 'Static' keyword would have made it accesible without instantiation of the enclosing class,
    // although you'd have lost means to access private fields of a particular NestedClass object
    class NestedClass {
        public void print() { System.out.println(EnclosingClass.this.data); }
    }
}