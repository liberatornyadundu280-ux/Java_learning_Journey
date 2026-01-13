/*
what sets synchronous apart is that it is used on methods and block where as the volatile and the atomic are used on variables 

*************formal definition *************************
When working with multithreading in Java, ensuring thread safety becomes crucial. Java provides different mechanisms, 
such as synchronized, volatile, and atomic variables, to handle shared data across threads.
 While all three help in managing concurrent access, they differ significantly in what they provide:

synchronized ensures mutual exclusion and visibility."locks methods hence danger for deadlocks and livelocks "
volatile ensures visibility only (not synchronization or mutual exclusion).
atomic variables provide lock-free, thread-safe operations for specific actions."well explain in it platform "

Feature----------------->  Synchronized-------------->	Volatile-------------->	Atomic
Applies to---------->	Methods/blocks---------->	Variables---------->	Variables
Purpose---->	Ensures mutual exclusion and consistency (via locks)-->	Ensures visibility (no atomicity)-->	Provides atomic operations (no locks)
Performance--->	Lower (due to locking)---->	Higher than synchronized---------->	Higher than both synchronized and volatile
Concurrency---------->	Prone to deadlocks/livelocks---------->	Immune (no locks)---------->	Immune (no locks)
 */

public class Av_vs_SM_Vs_VV {

    /*
     ************ Volatile implementation***************
     * // ensures visibility, not atomicity
     * private volatile int count = 0;
     * public void increment(){
     * // not atomic (read + modify + write)
     * count++;
     * }
     */
    private int count = 0;

    public synchronized void increment() {
        // atomic due to synchronization
        count++;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args)
            throws InterruptedException {
        GFG demo = new GFG();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++)
                demo.increment();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++)
                demo.increment();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final count (synchronized): " + demo.getCount());
    }
}