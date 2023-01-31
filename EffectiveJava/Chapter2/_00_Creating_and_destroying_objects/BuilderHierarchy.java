package EffectiveJava.Chapter2._00_Creating_and_destroying_objects;

import java.util.Objects;

public class BuilderHierarchy {
    public static void main(String ... args) {
        ActualCharacter character = new ActualCharacter.CharacterBuilder("David", 22)
        .addBiography("He's a honorouble knight")
        .addModel("models/placeholder.mdl")
        .build();

        System.out.println(character);
    }
}

abstract class AbstractCharacter {
    // required fields
    private final String name;
    private final int age;

    // optional fields
    private final String biography;

    public String getBiography() {
        return biography;
    }
    public abstract static class AbstractBuilder<T extends AbstractBuilder<T>> {
        private String name;
        private int age;
        private String biography = "Unknown";

        public T addBiography(String biography) {
            this.biography = biography;
            return self();
        }

        public AbstractBuilder(String name, int age) {
            this.name = Objects.requireNonNull(name);
            this.age = age;
        }

        public abstract AbstractCharacter build(); // must be overwritten
        protected abstract T self(); // must return 'this' (an instance of a specific subclass)
    }

    public AbstractCharacter(AbstractBuilder<?> builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.biography = builder.biography;
    }

    public String getName() { return this.name; }
    public int getAge() { return this.age; }
}

class ActualCharacter extends AbstractCharacter {

    private final String model;

    public String getModel() {
        return model;
    }

    public static class CharacterBuilder extends AbstractCharacter.AbstractBuilder<CharacterBuilder> {

        public CharacterBuilder(String name, int age) {
            super(name, age);
        }

        private String model = "/models/placeholder.mdl";

        public CharacterBuilder addModel(String model) {
            this.model = Objects.requireNonNull(model);
            return self();
        }

        @Override
        public ActualCharacter build() {
            return new ActualCharacter(this);
        }

        @Override
        protected CharacterBuilder self() {
            return this;
        }

    }

    public ActualCharacter(CharacterBuilder builder) {
        super(builder);
        this.model = builder.model;
    }

    @Override public String toString() {
        return String.join(" ", this.getName(), Integer.toString(this.getAge()), this.getBiography(), this.getModel());
    }
}