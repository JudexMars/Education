package EffectiveJava.Chapter5;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class _02_TypeTokens {
    public static void main(String ... args) {
        Storage storage = new Storage();
        storage.put(String.class, "Text");
        storage.put(Integer.class, 15);
        storage.put(Boolean.class, true);

        String str = storage.get(String.class);
        Integer integer = storage.get(Integer.class);
        Boolean bool = storage.get(Boolean.class);

        System.out.println(str);
        System.out.println(integer);
        System.out.println(bool);

        //storage.put(String.class, 2); // won't work
    }

    // Class<?> is a type token
    static class Storage {
        private Map<Class<?>, Object> internals = new HashMap<>();

        <T> void put(Class<T> type, T object) {
            internals.put(Objects.requireNonNull(type), object);
        }
        
        <T> T get(Class<T> type) {
            return type.cast(internals.get(type));
        }
    }
}
