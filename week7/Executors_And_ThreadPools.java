/*
the thread pools are used in place of threads as there are monitored by JVM its self so ni need for thr user to directly monitor what it does
this means that we rarely be using the threads ie run() and start() but instead we will be using thread pools and executors in their place in most of the projects
*******Understanding Executor framework *********
Executor Framework is a part of java.util.concurrent package introduced in Java 5 provides a high-level API for managing thread execution. 
It lets developers submit tasks without manually creating or controlling threads, as the framework handles scheduling and execution.
The hierarchy of the executor framework.
1. Executor Interface
Executor Interface is root interface of the framework is used to execute submitted tasks without explicitly creating threads. 
Defines a single method:
Executor executor = command -> new Thread(command).start();
executor.execute(() -> System.out.println("Task executed"));

2. ExecutorService Interface
The ExecutorService extends the Executor interface and provides advanced methods for task management, such as submitting tasks that return results and controlling executor shutdown.
Supports both Runnable and Callable tasks.
Provides lifecycle management (shutdown, awaitTermination).
Can execute multiple tasks simultaneously.
ExecutorService service = Executors.newFixedThreadPool(2);
service.submit(() -> System.out.println("Running a task"));
service.shutdown();

3. ScheduledExecutorService Interface
ScheduledExecutorService Interface is extends ExecutorService and supports task scheduling, running tasks periodically or after a delay.
ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);

4. ThreadPoolExecutor Class
ThreadPoolExecutor is the most commonly used implementation of ExecutorService. It manages a pool of worker threads to execute tasks efficiently, reusing threads to reduce overhead.
Controls core pool size, maximum pool size, and queue capacity.
Supports custom thread factory and rejection policies.
ExecutorService executor = Executors.newFixedThreadPool(3);
executor.execute(task);

5. AbstractExecutorService Class
A base class that provides default implementations for ExecutorService methods. 
Simplifies creating custom executors by handling common functionalities like submit() and invokeAll().
Common Types of Executors in Java
The Executors utility class provides factory methods to easily create different kinds of thread pools. 
Each type is designed for specific concurrency requirements.
 */

import java.util.concurrent.*;

class Task implements Callable<String> {

    private String message;

    public Task(String message) {
        this.message = message;
    }

    public String call() throws Exception {
        return "Hi " + message + "!";
    }
}

public class Executors_And_ThreadPools {

    public static void main(String[] args) {

        Task task = new Task("GeeksForGeeks");

        // Creating object of ExecutorService class and Future object Class
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<String> result = executorService.submit(task);

        // Try block to check for exceptions
        try {
            System.out.println(result.get());
        }

        // Catch block to handle the exception
        catch (InterruptedException | ExecutionException e) {

            System.out.println("Error occurred while executing the submitted task");
            e.printStackTrace();
        }

        // Cleaning resource and shutting down JVM by saving JVM state using shutdown()
        // method
        executorService.shutdown();
    }
}