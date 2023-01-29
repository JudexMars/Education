package Horstmann.Core1.Multithreading;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.*;

public class _19_Processes {
    public static void main(String ... args) throws IOException {
        var builder = new ProcessBuilder("clang", "cprog.cpp");
        builder.directory(new File("Horstmann/Core1/Multithreading"));
        builder.inheritIO();

        Process p = builder.start(); // it should work but it doesn't and it has to do something with arm64 architecture
        // i'm too lazy to fix it and i hate c++ so we'll just move along
        // Hopefully, I'll make it right a little later
    }
}
