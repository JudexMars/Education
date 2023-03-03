package EffectiveJava.Chapter6;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _00_Enums {
    public static void main(String ... args) {
        for (var god : God.values()) System.out.println(god + "\n");

        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Enter two numbers");
            String line = in.nextLine();
            Pattern pattern = Pattern.compile("\\+|\\-|\\*|\\/");
            var numbers = pattern.split(line);
            double a = Double.parseDouble(numbers[0]);
            double b = Double.parseDouble(numbers[1]);
            Matcher matcher = pattern.matcher(line);
            String operation = matcher.find() ? matcher.group() : null;

            var op = Calculator.fromString(operation);
            System.out.println(a + op.toString() + b + "=" + op.apply(a, b));

            //for (var op : Calculator.values()) System.out.println(a + op.toString() + b + "=" + op.apply(a, b));

            int minutesWorked = 8 * 60;
            int payRate = 1;
            var weekday = PayrollDay.MONDAY;
            System.out.println("An employee worked for " + minutesWorked + " mins this " + weekday + " (His pay rate: $" + payRate + ")");
            System.out.println("His payment: $" + weekday.pay(minutesWorked, payRate));
        }
    }

    /*
     * As you can see, enums are fully fledged classes that can have methods and fields
     */
    static enum God {
        // These are constant instances
        Akatosh("Time", "Along with Lorkhan and Mara, he is one of three deities found in almost every Tamrielic religion, with only the Dunmer, Orsimer, Argonian, and Kothringi not having a dragon god of time in their pantheons. He is generally considered to be the first of the gods to form in the Beginning Place. After his establishment, other spirits followed his example, and the various pantheons of the world emerged. Before the Ages of Man suggests that he and Auri-El are the same being, but further confirms that after his formation, time began. Akatosh is considered the god-defender of the Empire."),
        Arkay("Birth, death", "epresents the qualities of birth and death, most particularly burials and funeral rites, as well as on occasion being linked to the cyclical nature of the seasons. Some sources state that Arkay's life began as a mortal, who joined the ranks of the gods."),
        Zenithar("Work, commerce, trade", "He is almost always portrayed as male, and is associated with the Bosmeri/Breton god Z'en, as well as with a blue star that sometimes shines over Tamriel.");

        private God(String aspects, String description) {
            this.aspects = aspects;
            this.description = description;
        }

        private final String aspects;
        private final String description;

        public String getAspects() { return aspects; }
        public String getDescription() { return description; }

        @Override public String toString() {
            return super.toString() + " | " + aspects + " | " + description;
        }
    }

    // This pattern forces a programmer to implement apply method in every enum-constant that he wants to add
    static enum Calculator {
        // Every constant has a constant-specific field 'symbol'
        PLUS("+") { public double apply(double x, double y) { return x + y; } },
        MINUS("-") { public double apply(double x, double y) { return x - y; } },
        MULTIPLY("*") { public double apply(double x, double y) { return x * y; } },
        DIVIDE("/") { public double apply(double x, double y) { return x / y; } };

        // Keys are operations as strings ('+', '-', '*', '/') and values are operations as constants
        /*
         * Пояснение для самого себя, которое тут просто необходимо:
         * Сначала мы создаем поток, содержащий имена всех констант в порядке их объявления в enum.
         * Затем вызывается метод collect, который позволяет упорядочить их в Map.
         * Чтобы не морочить себе голову лишним кодом, используем метод toMap из класса Collectors..
         * В аргументах метода нужно всего лишь указать, каким образом вычисляются ключи и значения
         * на основе values(), то есть содержимого потока, с которым мы работаем.
         * Соответственно, ключи вычисляются вызовом toString(), то есть это просто символы операций
         * в формате строк, а значения - это сами константы. Таким образом, устанавливается однозначная
         * связь между строкой и определенной константой
         */
        private static final Map<String, Calculator> stringToEnum =
        Stream.of(values()).collect(
            Collectors.toMap(Object::toString, e -> e));

        // This method is better be replaced with the one that uses Optional class (as it is in the book)
        // but for now I've decided to not implement it for more simplicity
        public static Calculator fromString(String symbol) {
            return stringToEnum.get(symbol);
        }

        private Calculator(String symbol) {
            this.symbol = symbol;
        }

        private final String symbol;

        @Override public String toString() {
            return this.symbol;
        }

        public abstract double apply(double x, double y);
    }

    static enum PayrollDay {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
        SATURDAY, SUNDAY;

        private final PayType payType;

        private PayrollDay() { this.payType = PayType.WEEKDAY; }
        private PayrollDay(PayType payType) { this.payType = payType; }

        int pay(int minutesWorked, int payRate) {
            return payType.pay(minutesWorked, payRate);
        }

        private static enum PayType {
            WEEKDAY {
                @Override
                int overtimePay(int minutesWorked, int payRate) {
                    return minutesWorked <= MINS_PER_SHIFT ? 0 : (minutesWorked - MINS_PER_SHIFT) * payRate / 60 / 2;
                }
                
            },

            WEEKEND {
                @Override
                int overtimePay(int minutesWorked, int payRate) {
                    return minutesWorked * payRate / 60 / 2;
                }
            };

            abstract int overtimePay(int minutesWorked, int payRate);
            private static final int MINS_PER_SHIFT = 60 * 8;

            int pay(int minutesWorked, int payRate) {
                int base = minutesWorked * payRate / 60;
                int overtime = overtimePay(minutesWorked, payRate);
                return base + overtime;
            }
        }
    }
}
