package EffectiveJava.Chapter9;

import java.io.IOException;
import java.net.URL;

public class _01_Url {
    @SuppressWarnings("deprecation")
    public static void main(String ... args) {
        try (var urlStream = new URL("https://www.google.com").openStream()) {
            urlStream.transferTo(System.out);
        }
        catch (IOException e) {
            System.err.println("Bad!");
        }
    }
}
