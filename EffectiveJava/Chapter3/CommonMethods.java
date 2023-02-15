package EffectiveJava.Chapter3;
import java.util.Comparator;
import java.util.Objects;
@SuppressWarnings("unused")
public class CommonMethods {
    public static void main(String ... args) {
        Profile p1 = new Profile("Donald Kimble", 22, Profile.Sex.MALE);
        Profile p2 = new Profile("Donald Kimble", 22, Profile.Sex.MALE);
        Profile p3 = new Profile("Jack Torrance", 37, Profile.Sex.MALE);

        System.out.println("Does p1 equal p2? " + p1.equals(p2));
        System.out.println("Does p2 equal p3? " + p2.equals(p3));
        System.out.println("Does p1 equal p3? " + p1.equals(p3));

        System.out.println("p1 hash: " + p1.hashCode());
        System.out.println("p2 hash: " + p2.hashCode());
        System.out.println("p3 hash: " + p3.hashCode());

        System.out.println("p1 toString():");
        System.out.println(p1);

        var clone = p3.clone();
        System.out.println("clone of p3:");
        System.out.println(clone);

        System.out.println("Comparison between p1 and p3:");
        System.out.println("Is p1 > p3? " + p1.compareTo(p3));
    }

    private static class Profile implements Cloneable, Comparable<Profile> {
        private final String name;
        private final int age;
        private final Sex sex;
        
        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public Sex getSex() {
            return sex;
        }

        public Profile() {
            this.name = "Unknown";
            this.age = 0;
            this.sex = Sex.UNSPECIFIED;
        }

        public Profile(String name, int age, Sex sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }
        
        private enum Sex { MALE, FEMALE, UNSPECIFIED };

        @Override public boolean equals(Object o) {
            if (!(o instanceof Profile)) return false;
            if (this.name.equals(((Profile)o).getName()) 
            && Integer.compare(this.age, ((Profile)o).getAge()) == 0 
            && this.sex.equals(((Profile)o).getSex())) 
                return true;
            return false;
        }

        @Override public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + Integer.hashCode(age);
            result = 31 * result + sex.hashCode();
            return result;
        }

        // Code below is identical to the code that I wrote before but it is a little slower
        // because each field is put into an array in the process of calculating hashcode.
/*         @Override public int hashCode() {
            return Objects.hash(name, age, sex);
        } */

        @Override public String toString() {
            return String.join(", ", name, Integer.toString(age), sex.toString());
        }

        // By default, super.clone() copies every field of the source-object. You are able to
        // define its behaviour more precisely if it causes unwanted problems in your program
        @Override public Profile clone() {
            try {
                return (Profile)super.clone();
            }
            catch (CloneNotSupportedException e) {
                // if a class implements Cloneable interface, then it shall always succeed
                throw new AssertionError();
            }
        }

        private static final Comparator<Profile> COMPARATOR = Comparator.comparing((Profile pf) -> pf.getName())
        .thenComparingInt(pf -> pf.getAge()).thenComparing(pf -> pf.getSex());

        @Override
        public int compareTo(Profile o) {
            return COMPARATOR.compare(this, o);
        }

        /*
         * Unfortunately, it is not a good idea to actually implement Cloneable interface.
         * First of all, it forces subclasses to override the clone method and, if a programmer
         * doesn't want to use it (he prefers factories etc) he will be obliged to throw an exception which
         * isn't good. Furthermore, clone() does not take any arguments and always returns an object of the same
         * type as the class from which it is called.
         * It is practically always better and safer to write your own methods (they are often called copy() or smth).
         * Sometimes, you could just make a constructor that accepts another object as a parameter. It such case you would
         * be able to also initialize final fields. This option is not permitted in the clone() method because it returns
         * an instance of an object immediately.
         * There is, however, an exception: arrays. Arrays are best copied with the use of clone() method.
         */
    }
}
