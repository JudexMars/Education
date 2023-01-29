package Horstmann.Core1.Multithreading;

import java.util.concurrent.atomic.*;
import java.util.function.BinaryOperator;

public class _08_Atomic {
    public static void main(String ... args) {
        /*
         * Atomic containers are used to perform defined operations with no interruptions.
         * Basically, your computer execute a set of operations as one operation, thus
         * it is called atomic (atomos means indivisible in Ancient Greek)
         */
        AtomicInteger aInt = new AtomicInteger(0);
        int result;

        result = aInt.accumulateAndGet(10, Math::max);
        System.out.println(result);

        result = aInt.updateAndGet(x -> Math.min(x, 4));
        System.out.println(result);
        
        result = aInt.getAndDecrement(); // Return the value first, then decrement it
        System.out.println(result);
        System.out.println("Actual value: " + aInt);

        /*
         * As you can see atomic classes have lots of methods to work with data.
         * There is even an AtomicReference class!
         */

        StringBuilder builder = new StringBuilder("First");
        StringBuilder builder2 = new StringBuilder("Second");

        AtomicReference<StringBuilder> aStringBuilder = new AtomicReference<>(builder);
        BinaryOperator<StringBuilder> op = (a, b) -> a.append(b);
        aStringBuilder.accumulateAndGet(builder2, op);

        System.out.println(aStringBuilder);

        /*
         * LongAdder and LongAccumulator are classes that are preferred to AtomicLong: they simply are more efficient.
         * The difference is in the way they use to store and update data. Each instance has many fields containing
         * results of different calls that you do in your program. When you need to get the final result, you use the
         * 'sum' method.
         * LongAdder is a simplified version of LongAccumulator.
         * Of course, there are similar classes for other types
         */
        
        LongAdder adder = new LongAdder();
        adder.add(10);
        adder.add(20);
        adder.decrement();
        System.out.println(adder.sum());

        LongAccumulator accumulator =  new LongAccumulator((x, y) -> {
            return x + 2 * y;
        }, 0L);
        for (int i = 0; i < 10; i++) accumulator.accumulate(i);
        System.out.println(accumulator.get());
    }
}
