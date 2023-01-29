package Horstmann.Core1.Multithreading;

import java.text.SimpleDateFormat;
import java.util.Date;

public class _09_ThreadLocal {
    public static void main(String ... args) {
        DateContainer dc = new DateContainer();
        System.out.println(dc.writeDate());
    }
}

class DateContainer {
    /*
     * ThreadLocal allows you to get a thread-specific instance of an object
     */
    private static final ThreadLocal<SimpleDateFormat> dateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd-MM-yyyy"));

    public String writeDate() {
        return dateFormat.get().format(new Date());
    }
}