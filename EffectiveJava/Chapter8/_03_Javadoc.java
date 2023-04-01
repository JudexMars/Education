package EffectiveJava.Chapter8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class _03_Javadoc {
    /**
     * This is the {@index start} method
     * {@literal > < =}
     * {@code System.out.println("Hello World")}
     * @param args anything that is passed through terminal
     */
    public static void main(String ... args) {
        System.out.println(Constants.Zero.getValue() + Constants.One.getValue() + Constants.Two.getValue());
    }

    /**
     * Contains numerical constants
     */
    private enum Constants {
        /** Abstraction describing a complete absence of everything */
        Zero(0),
        /** Solo number */
        One(1),
        /** Pair */ 
        Two(2);

        /** value that the constant represents */
        private final int value;
        /**
         * Returns the value that the constant represents
         * 
         * @return the value that the constant represents
         */
        public int getValue() { return this.value; }

        private Constants(int x) { this.value = x; }

    }

    /**
     * Indicates that the field is somehow unique
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    private @interface Unique {

    }
}
