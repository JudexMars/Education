package EffectiveJava.Chapter2._00_Creating_and_destroying_objects;

public class StaticClasses {
    public static void main(String ... args) {
        // It won't work
        //Utilities u = new Utilities();
    }
}

/*
 * If a class is meant to contain only static methods, the best approach is to make it final and create
 * one private contructors to ensure that this class is never instantiated in the program
 */
final class Utilities {
    private Utilities() {
        // even if for some reason this constructor is called, it throws an exception
        throw new AssertionError("Class 'Utilities' has been instantiated");
    }
}

// It won't work either
/* class SubUtilities extends Utilities {

} */