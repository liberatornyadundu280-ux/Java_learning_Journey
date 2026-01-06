/*
Threading in java "it is a class dont forget so everyclass thatyou use and might need threading u just extend thread "
what is threading in java?
A Java thread is the smallest unit of execution within a program. 
It is a lightweight subprocess that runs independently but shares the same memory space as the process, allowing multiple tasks to execute concurrently.

Life Cycle of a Thread <3
During its thread life cycle, a Java thread transitions through several states from creation to termination.
New State
Runnable State
Blocked State
Waiting State
Timed Waiting State
Terminated State

Running Threads in Java
There are two methods used for running Threads in Java:
run() Method: Contains the code for the thread. Calling it directly behaves like a normal method call.
start() Method: Launches a new thread and internally calls run() concurrently.

what if you have inherited another class in java what do you do as java does npot support multiple inheritance?
thats where you implement the runnable interface that will be able to make all the methods of thread 

what Synchronization?
Synchronization in Java is a mechanism that ensures that only one thread can access a shared resource (like a variable, object, or method) at a time.
It prevents concurrent threads from interfering with each other while modifying shared data.

Why is Synchronization Needed?
Prevents Data Inconsistency: Ensures that multiple threads don’t corrupt shared data when accessing it simultaneously.
Avoids Race Conditions: Allows only one thread to execute a critical section at a time, maintaining predictable results.
Maintains Thread Safety: Protects shared resources from concurrent modification by multiple threads.
Ensures Data Integrity: Keeps shared data accurate and consistent throughout program execution.


Ways we can achieve Synchronization
by using synchronized blocks, methods and static methods 
here the implementation is the same but what differs is the purpose and where it is applied 

 */
class TicketBooking {

    // Shared resource (available tickets)
    private int availableTickets = 10;

    // Synchronized method for booking tickets
    public synchronized void bookTicket(int tickets) {

        if (availableTickets >= tickets) {

            availableTickets -= tickets;
            System.out.println(
                    "Booked " + tickets
                            + " tickets, Remaining tickets: "
                            + availableTickets);
        } else {
            System.out.println(
                    "Not enough tickets available to book "
                            + tickets);
        }
    }

    public int getAvailableTickets() {

        return availableTickets;
    }
}

public class Synchronization {

    public static void main(String[] args) {

        // Shared resource
        TicketBooking booking = new TicketBooking();

        // Thread 1 to book tickets
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {

                // Trying to book 2
                // tickets each time
                booking.bookTicket(2);

                try {

                    // Simulate delay
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Thread 2 to book tickets
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {

                // Trying to book 3
                // tickets each time
                booking.bookTicket(3);

                try {

                    // Simulate delay
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        t1.start();
        t2.start();

        // Wait for threads to finish
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print final remaining tickets
        System.out.println("Final Available Tickets: "
                + booking.getAvailableTickets());
    }
}
