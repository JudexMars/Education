import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Client test = new Client("John Smith", 21, true);
        Lazy<String> composed = Lazy.from(test)
                .map(Client::name)
                .map(s -> s.split(" "))
                .map((result) -> {
                    System.out.println(result[0]);
                    return result[0];
                }
        );

        composed.get();
    }

    private record Client(String name, int age, boolean isActive) {}

    private static class Lazy<T> {
        private T value;

        private Supplier<T> supplier;

        public T get() {
            if (value == null)
                value = supplier.get();
            return value;
        }

        private Lazy(T value) {
            this.value = value;
        }

        private Lazy(Supplier<T> supplier) {
            this.supplier = supplier;
        }

        public static <T> Lazy<T> from(T value) {
            return new Lazy<>(value);
        }

        public <U> Lazy<U> flatMap(Function<T, Lazy<U>> flatMapFunc) {
            return flatMapFunc.apply(value);
        }

        public <U> Lazy<U> map(Function<T, U> mapFunc) {
            return new Lazy<>(() -> mapFunc.apply(get()));
        }
    }
}
