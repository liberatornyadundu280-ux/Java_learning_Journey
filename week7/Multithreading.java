/*
Multithreading in Java is a feature that enables a program to run multiple threads simultaneously, 
allowing tasks to execute in parallel and utilize the CPU more efficiently. 
A thread is a lightweight, independent unit of execution inside a program (process).
A process can have multiple threads.
Each thread runs independently but shares the same memory.
Example: Imagine a restaurant kitchen. Multiple chefs (threads) are preparing different dishes at the same time.
 This speeds up service and utilizes all available resources (CPU).
 Ways to implement threads in java
 1 extending the thread class
 2 implementing the runnable interface 

When to Use Which?
Use extends Thread: if your class does not extend any other class.
Use implements Runnable: if your class already extends another class (preferred because Java doesn’t support multiple inheritance).
Advantages of Multithreading in Java
Improved Performance: Multiple tasks can run simultaneously, reducing execution time.
Efficient CPU Utilization: Threads keep the CPU busy by running tasks in parallel.
Responsiveness: Applications (like GUIs) remain responsive while performing background tasks.
Resource Sharing: Threads within the same process share memory and resources, avoiding duplication.
Better User Experience: Smooth execution of tasks like file downloads, animations, and real-time updates.
*/
class CookingTask extends Thread {
    private String task;

    CookingTask(String task) {
        this.task = task;
    }

    public void run() {
        System.out.println(task + " is being prepared by " +
                Thread.currentThread().getName());
    }
}

public class Multithreading {
    public static void main(String[] args) {
        Thread t1 = new CookingTask("Pasta");
        Thread t2 = new CookingTask("Salad");
        Thread t3 = new CookingTask("Dessert");
        Thread t4 = new CookingTask("Rice");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}