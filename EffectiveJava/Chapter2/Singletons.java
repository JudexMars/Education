package EffectiveJava.Chapter2;

import java.io.Serializable;

public class Singletons {
    public static void main(String ... args) {
        System.out.println(Patrick.getInstance());

        System.out.println(Paul.getInstance());
    }
}

// An example of singletone class
class Patrick implements Serializable {
    
    private final String name;
    private final int age;

    /*
     * You may simply make this field public and refer to it directly but what if you change your mind and decide 
     * that you do not want this class to be a singletone? That's why it's easier to work with the getter.
     * Besides, with the getter you are able to implement whatever behaviour you want. For example,
     * you may want to have different instances of Patrick Bateman for every thread.
     */
    private transient static final Patrick INSTANCE = new Patrick();

    private Patrick() {
        name = "Patrick";
        age = 27;
    }

    @Override
    public String toString() {
        return name + " " + age;
    }

    public static Patrick getInstance() { return INSTANCE; }

    private Object readResolve() {
        return INSTANCE;
    }
}

// enum approach to solve serialization errors
enum Paul {
    INSTANCE("Paul", 2);

    private final String name;
    private final int age;

    private Paul(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " " + age;
    }

    public static Paul getInstance() { return INSTANCE; }
}