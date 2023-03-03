package CodeWars;

public class TestPolygon {
    public static void main(String ... args) {
        fRunnable.run();
    }

    private static String field;

    private static Runnable fRunnable = () -> {
        field = "test";
        System.out.println(field);
    };
}
