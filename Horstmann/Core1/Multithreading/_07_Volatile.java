package Horstmann.Core1.Multithreading;

public class _07_Volatile {
    public static void main(String ... args) {
        VolatileExample exp = new VolatileExample();

        Thread t = new Thread(new LongRunnable(exp, Long.MAX_VALUE, false));
        Thread t2 = new Thread(new LongRunnable(exp, 0, true));

        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();

        /*
         * I know this is a really-really dumb example but I couldn't think of anything better and I don't actually
         * care that much.
         * В любом случае понятное хорошее объяснение есть по этой ссылке https://javarush.com/groups/posts/1998-upravlenie-potokami-metodih-volatile-i-yield
         */
    }
}

class VolatileExample {
    /*
     * Sometimes different threads can get in each other's way when they operate on the same variables.
     * Data which is stored in the variable gets cached before being updated by
     * the thread B and the thread A operates using older information unknowingly. To prevent such behaviour
     * you use a 'volatile' keyword. Volatile keyword is used with fields only.
     */
    private volatile long data = 0;

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }
}

class LongRunnable implements Runnable {

    private VolatileExample target;
    private long amount;
    private boolean output = false;

    public LongRunnable(VolatileExample target, long amount, boolean output) {
        super();
        this.target = target;
        this.amount = amount;
        this.output = output;
    }

    @Override
    public void run() {
        if (output) System.out.println(target.getData());
        else target.setData(amount);
    }
}