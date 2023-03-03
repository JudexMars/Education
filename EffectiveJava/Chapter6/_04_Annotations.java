package EffectiveJava.Chapter6;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class _04_Annotations {
    public static void main(String ... args) {
        //@Test int x = 0; // will not work

        try (Scanner in = new Scanner(System.in)) {
            String className = in.nextLine();
            runTests(Class.forName(className));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    static void m() {}

    @Test
    static void wrong() { throw new AssertionError(); }

    static void runTests(Class<?> cl) {
        int total = 0;
        int passed = 0;
        var methods = cl.getDeclaredMethods();
        for (var method : methods) {
            if (method.isAnnotationPresent(Test.class) || method.isAnnotationPresent(ExceptionTest.class)) {
                ++total;
                try {
                    method.invoke(null);
                    ++passed;
                }
                catch(InvocationTargetException ex) {
                    var excAnn = method.getAnnotation(ExceptionTest.class);
                    if (excAnn != null && excAnn.value().isInstance(ex))
                        passed++;
                    else
                        System.err.println("Test failed: " + method + " | " + ex.getCause());
                }
                catch (IllegalAccessException ex) {
                    System.err.println("Invalid @Test " + method + "\n");
                    ex.printStackTrace();
                }
            }
        }
        System.out.println("Total amount of tests: " + total);
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + (total - passed));
    }

    // We must specify what particular exception we are expecting from the method
    @ExceptionTest(ArithmeticException.class)
    static int moronicDiv() {
        return 1 / 0;
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Test {

}

/*
 * We use this kind of annotation to check that our program fails on tests that are not meant to be passable
 * For example, we may want to be sure that the calculator class always throws arithemtic exception when we
 * try to divide by zero
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ExceptionTest {
    Class<? extends Throwable> value();
}