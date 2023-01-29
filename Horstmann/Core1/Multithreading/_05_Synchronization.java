package Horstmann.Core1.Multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class _05_Synchronization {
    public static void main(String ... args) {
        /*
         * In multithreaded applications in a lot of times many threads share the same data
         * which can result in situations when one thread is stepping on another one's toes
         * by trying to access and change the data that is already being used in some process.
         * Synchronization is a tool to prevent it from happening.
         * There are several mechanisms which can be used by a programmer.
         */

        LockClass l = new LockClass();
        
        for (int i = 0; i < 10; i++) { 
            var t = new Thread(() -> {
                l.use();
            });
            t.start();
        }

        /*
         * Here we examine a concept of conditions objects.
         * dataBase contains different bank accounts (actually, only amounts of money they have)
         * ConditionsClass gives us a method to transfer any money between two accounts
         * but it has a flaw: it doesn't work if the first account has 0 or less funds at the time.
         * This class uses a condition to 'freeze' the current thread if the account doesn't have enough money.
         * When any instance of the class somehow changes the database it notifies all other objects about it and
         * they check their condition again.
         */
        ArrayList<Integer> dataBase = new ArrayList<>(List.of(100, 0, 300, 200));
        ConditionClass cc = new ConditionClass();

        var t = new Thread(() -> {
            try {
                cc.transfer(dataBase, 1, 0, 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();

        t = new Thread(() -> {
            try {
                cc.transfer(dataBase, 0, 1, 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();

        try {
            Thread.sleep(1000);
            System.out.println(dataBase);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SynchronizedClass sc = new SynchronizedClass();

        t = new Thread(() -> {
            try {
                sc.transfer(dataBase, 0, 2, 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();

        t = new Thread(() -> {
            try {
                sc.transfer(dataBase, 1, 0, 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();

        try {
            Thread.sleep(1000);
            System.out.println(dataBase);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class LockClass {
    /*
     * The main purpose of the ReentrantLock is to ensure that only one thread at the time
     * is able to execute that code. If another thread spots that the lock has already
     * been acquired it becomes disabled until it is free once again.
     * This example class is very simplistic but I guess it's not too hard concept to grasp.
     * By the way, ReentrantLock counts how many times it was locked. Thus,
     * it is still considered to be locked until every one of unlock statements in every call is executed.
     * NOTE: ReentrantLock has no influence on other objects besides the one in which it was initialized,
     * which means that one thread, for example, can still access two different objects even if they
     * both have its own ReentrantLocks.
     */
    private ReentrantLock lock = new ReentrantLock();
    /*
     * ReentrantLock's constructor has a parameter called 'fair'. Fair locks favour those threads
     * that have been waiting for the longest time. However, this feature has a bad impact on
     * the program's overall perfomance and should not be used unless it is crucial in your
     * application and you know what you are doing. Moreover, the scheduler may not be completely
     * fair: no guarantees.
     */

    public void use() {
        System.out.println(Thread.currentThread().getName());
        lock.lock();
        try {
            System.out.println("The lock has been acquired by " + Thread.currentThread().getName());
            // Important operations that must be synchronized
        }
        finally {
            System.out.println("The lock has been released by " + Thread.currentThread().getName());
            lock.unlock(); // now other threads are able to use this object and execute try statement
        }
    }
}

class ConditionClass {
    private ReentrantLock lock;
    private Condition insufficientFunds;

    public ConditionClass() {
        lock = new ReentrantLock();
        insufficientFunds = lock.newCondition();
    }

    /*
     * This method is supposed to transfer money from one bank account to another.
     * Parameter db serves as a database containing many accounts
     */
    public void transfer(ArrayList<Integer> db, int from, int to, int amount) throws InterruptedException {
        lock.lock();
        try {
            while(db.get(from) <= 0) insufficientFunds.await();
            db.set(from, db.get(from) - amount);
            db.set(to, db.get(to) + amount);
            insufficientFunds.signalAll(); // Notifies all threads waiting for the condition
            // It is not recommended to use the 'signal' method because it is not as reliable
            // though it costs less resources
        }
        finally {
            lock.unlock();
        }
    }
}

class SynchronizedClass {
    public synchronized void transfer(ArrayList<Integer> db, int from, int to, int amount) throws InterruptedException {
        while (db.get(from) <= 0) wait();
        db.set(from, db.get(from) - amount);
        db.set(to, db.get(to) + amount);
        notifyAll();
    }
}