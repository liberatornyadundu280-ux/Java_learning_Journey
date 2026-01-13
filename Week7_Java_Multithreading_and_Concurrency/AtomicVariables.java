/*
Atomic variables are made to me thread safe that swhy we use them 
also there are very fast as there don't lock hence less prone to deadlock/livelock 
what is livelock and deadlock
--> is when two or more objects or variables are locked ie wont function due to the fact that they will be 
    depending on they values for the next operarion there by keeping the program froze for good 
-->  thats where atonomisity comes in it makes sure that all the variables are thread safe meaning there
    only one threaded is accessible at a time 
examples of atomic variables 
AtomicInteger
AtomicBoolean
AtomicReferenceArray
AtomicReference

********************main definition***********************
Atomic is a type of variable that performs read, write and update in a single uninterruptible step,
 ensuring thread-safe operations and preventing race conditions

It ensures data consistency without using synchronization or locks.
It improves performance through non-blocking, lock-free operations.
Simplify thread-safe programming for common operations like increment and compare-and-set.

Atomic Variables provide lock-free, thread-safe operations on single variables. 
They ensure atomicity and visibility using low-level Compare-And-Swap (CAS) operations without using synchronization.
 */

import java.util.concurrent.atomic.AtomicInteger;

class Counter extends Thread {

    // Atomic counter Variable
    AtomicInteger count;

    // Constructor of class
    Counter() {
        count = new AtomicInteger();
    }

    // method which would be called upon the start of execution of a thread
    public void run() {

        int max = 1_000_00_000;

        // incrementing counter total of max times
        for (int i = 0; i < max; i++) {
            count.addAndGet(1);
        }
    }
}

public class AtomicVariables {
    public static void main(String[] args)
            throws InterruptedException {
        // Instance of Counter Class
        Counter c = new Counter();

        // Defining Two different threads
        Thread first = new Thread(c, "First");
        Thread second = new Thread(c, "Second");

        // Threads start executing
        first.start();
        second.start();

        // main thread will wait for both threads to complete execution
        first.join();
        second.join();

        // Printing final value of count variable
        System.out.println(c.count);
    }
}