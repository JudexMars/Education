import java.util.ArrayList;
import java.util.HashSet;

public class CodeWars {
    public static void main(String ... args) {
        /* String sentence = "The quick brown fox jumps over the lazy dog";
        boolean res = check(sentence);
        System.out.println(res); */

        /* System.out.println(makeComplement("AATGC")); */

        /* var a = "xyaabbbccccdefww";
        var b = "xxxxyyyyabklmopq";
        var res = longest(a, b); // "abcdefklmopqwxy";
        System.out.println(res); */

        /* System.out.println(accum("abcd")); */

        System.out.println(digital_root(942));
        System.out.println(DGRoot(942));
    }

    static boolean check(String sentence) {
        HashSet<String> used = new HashSet<>();
        for (int i = 0; i < sentence.length(); i++) {
            String l = Character.toString(sentence.charAt(i));
            if (l.matches("[a-zA-Z]")) 
                used.add(l.toLowerCase());
        }
        if (used.size() == 26) return true;
        return false;
    }

    static String makeComplement(String dna) {
        StringBuilder res = new StringBuilder(dna);
        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'A') res.replace(i, i + 1, "T");
            else if (dna.charAt(i) == 'C') res.replace(i, i + 1, "G");
            else if (dna.charAt(i) == 'T') res.replace(i, i + 1, "A");
            else if (dna.charAt(i) == 'G') res.replace(i, i + 1, "C");
        }
        return res.toString();
    }

    /*
     * Take 2 strings s1 and s2 including only letters from a to z. 
     * Return a new sorted string, the longest possible, containing distinct letters - each taken only once - coming 
     * from s1 or s2.
     * 
     * a = "xyaabbbccccdefww"
     *   b = "xxxxyyyyabklmopq"
     *   longest(a, b) -> "abcdefklmopqwxy"
     */
    public static String longest (String s1, String s2) {
        return (s1 + s2).chars().filter(Character::isAlphabetic).distinct().sorted().collect(StringBuilder::new, (result, element) -> ((StringBuilder)result).append(Character.toString(element)), (b1, b2) -> b1.append(b2)).toString();
        /* String a = s1.chars().filter(Character::isAlphabetic).distinct().sorted().collect(StringBuilder::new, (result, element) -> ((StringBuilder)result).append(Character.toString(element)), (b1, b2) -> b1.append(b2)).toString();
        String b = s2.chars().filter(Character::isAlphabetic).distinct().sorted().collect(StringBuilder::new, (result, element) -> ((StringBuilder)result).append(Character.toString(element)), (b1, b2) -> b1.append(b2)).toString(); */
    }

    /*
     * accum("abcd") -> "A-Bb-Ccc-Dddd"
     * accum("RqaEzty") -> "R-Qq-Aaa-Eeee-Zzzzz-Tttttt-Yyyyyyy"
     * accum("cwAt") -> "C-Ww-Aaa-Tttt"
     */
    public static String accum(String s) { 
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            var ch = Character.toString(s.charAt(i));
            sb.append(ch.toUpperCase() + new String(new char[i]).replace("\0", ch.toLowerCase()) + (i != s.length() - 1 ? "-" : ""));
        }
        return sb.toString();
    }

    /*
     * Digital root is the recursive sum of all the digits in a number.
     * Given n, take the sum of the digits of n. If that value has more than one digit, continue reducing in this way 
     * until a single-digit number is produced. The input will be a non-negative integer.
     */
    public static int digital_root(int n) {
        int sum = 0;
        while (n > 0) { sum += n % 10;  n /= 10; }
        if (sum > 9) return digital_root(sum);
        return sum;
    }

    // Interesting solution
    public static int DGRoot(int n) {
        return n < 10 ? n : DGRoot(DGRoot(n / 10) + n % 10);
    }
}
