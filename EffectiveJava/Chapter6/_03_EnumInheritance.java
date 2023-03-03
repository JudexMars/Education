package EffectiveJava.Chapter6;

public class _03_EnumInheritance {
    public static void main(String ... args) {
        double a = 10;
        double b = 5;

        applyAllOperations(BasicOperation.class, a, b);
        applyAllOperations(ExtendedOperation.class, a, b);
    }

    /*
     * This declaration means that T type parameter is both of enum type and implements Operation interface.
     * Fortunately, Class class already has a method that we can use to extract constants
     */
    static <T extends Enum<T> & Operation> void applyAllOperations(Class<T> ops, double x, double y) {
        for (var op : ops.getEnumConstants()) System.out.println(x + op.toString() + y + "=" + op.apply(x, y));
    }

    /*
     * Enums cannot extend each other. But we have a solution for this - interfaces.
     * Simply make enums that implement one interface which you will later use in your program.
     * By doing it, you make it much easier for other programmers and for youself to extend your program
     * further.
     */
    static interface Operation {
        public double apply(double x, double y);
    }

    static enum BasicOperation implements Operation {
        PLUS("+") {
            public double apply(double x, double y) { return x + y; } },
        MINUS("-") {
            public double apply(double x, double y) { return x - y; }
        },
        TIMES("*") {
            public double apply(double x, double y) { return x * y; } },
        DIVIDE("/") {
            public double apply(double x, double y) { return x / y; }
        };

        private final String symbol;

        BasicOperation(String symbol) {
           this.symbol = symbol;
        }

        @Override public String toString() {
           return symbol;
        }
    }

    static enum ExtendedOperation implements Operation {
        EXP("^") {
            public double apply(double x, double y) {
                return Math.pow(x, y);
            } 
        },
        REMAINDER("%") {
            public double apply(double x, double y) {
                return x % y; 
            }
        };

        private final String symbol;

        ExtendedOperation(String symbol) {
            this.symbol = symbol;
        }

        @Override public String toString() {
            return symbol;
        }
    }
}
