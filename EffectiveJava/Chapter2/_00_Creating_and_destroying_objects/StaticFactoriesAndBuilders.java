package EffectiveJava.Chapter2._00_Creating_and_destroying_objects;

public class StaticFactoriesAndBuilders {
    public static void main(String ... args) {
        /*
         * There are countless situations when you need you to make a class that has many constructors
         * with different parameters: both required and optional. You have three ways to deal with this problem
         */

        CharacterA charA = new CharacterA("Johnny Guitar", 39);
        System.out.println(charA);

        CharacterB charB = new CharacterB();
        charB.setName("Fdola");
        charB.setAge(45);
        charB.setBiography("Long story");
        charB.setAppearance("Cute");
        charB.setPersonality("Not too kind");
        System.out.println(charB);

        CharacterC charC = new CharacterC.CharBuilder("Jack", 22)
        .biography("Short")
        .personality("Good")
        .appearance("Handsome").build();
        System.out.println(charC);
    }
}

// Way 1. Telescoping contructor pattern.
class CharacterA {
    // required fields
    private final String name;
    private final int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // optional fields
    private final String biography;
    private final String personality;
    private final String appearance;

    public String getBiography() {
        return biography;
    }

    public String getPersonality() {
        return personality;
    }

    public String getAppearance() {
        return appearance;
    }

    public CharacterA(String name, int age) {
        this(name, age, "Unknown");
    }

    public CharacterA(String name, int age, String biography) {
        this(name, age, biography, "Unknown");
    }

    public CharacterA(String name, int age, String biography, String personality) {
        this(name, age, biography, personality, "Unrecognizable");
    }

    public CharacterA(String name, int age, String biography, String personality, String appearance) {
        this.name = name;
        this.age = age;
        this.biography = biography;
        this.personality = personality;
        this.appearance = appearance;
    }

    @Override
    public String toString() {
        return String.join(" ", getName(), Integer.toString(getAge()), getAppearance(),
        getBiography(), getPersonality());
    }

    /*
     * Now we have many constructors that differ from each other only in the amount of parameters they take.
     * Main downside - you can't skip some parameters in between and continue writing.
     * Furthermore, these constructors each have one name and it is easy to mess it up.
     */
}

// Way 2. JavaBeans pattern.
class CharacterB {
    // required fields
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // optional fields
    private String biography;
    private String personality;
    private String appearance;

    public String getBiography() {
        return biography;
    }

    public String getPersonality() {
        return personality;
    }

    public String getAppearance() {
        return appearance;
    }

    public CharacterB() {
        // literally nothing
    }

    // And here we go. This is the only way to initialize our object.
    // To use setters.

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    @Override
    public String toString() {
        return String.join(" ", getName(), Integer.toString(getAge()), getAppearance(),
        getBiography(), getPersonality());
    }
}

// Way 3. Builder pattern.
class CharacterC {
    // required fields
    private final String name;
    private final int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // optional fields
    private final String biography;
    private final String personality;
    private final String appearance;

    public String getBiography() {
        return biography;
    }

    public String getPersonality() {
        return personality;
    }

    public String getAppearance() {
        return appearance;
    }

    private CharacterC(CharBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.biography = builder.biography;
        this.appearance = builder.appearance;
        this.personality = builder.personality;
    }

    public static class CharBuilder {
        // required fields
        private final String name;
        private final int age;

        // optional fields
        private String biography = "Unknown";
        private String personality = "Unknown";
        private String appearance = "Unrecognizable";

        public CharBuilder(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public CharBuilder biography(String biography) {
            this.biography = biography;
            return this;
        }

        public CharBuilder personality(String personality) {
            this.personality = personality;
            return this;
        }

        public CharBuilder appearance(String appearance) {
            this.appearance = appearance;
            return this;
        }

        public CharacterC build() {
            return new CharacterC(this);
        }
    }

    @Override
    public String toString() {
        return String.join(" ", getName(), Integer.toString(getAge()), getAppearance(),
        getBiography(), getPersonality());
    }
}