
/*
    why?
    because we want our data to be be thread safe whe were are using our applications.
    what is thread safety?
    making sure every modifucation of data is refelcted to the stirage ie when we withdraw the money we must see the change 
    but how?
    concurrent collections allow us to interact with some of our data structure such that even is it is done concurrently the changes will be seen 
    hence bellow im going to show some of the thread safe collections 
 */
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class hashmap {

    public static void main(String[] args) {

        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

        // Adding elements
        map.put(1, "Java");
        map.put(2, "Python");
        map.put(3, "C++");

        // Accessing elements
        System.out.println(map.get(1)); // Java

        // Safe update with putIfAbsent
        map.putIfAbsent(2, "Go");

        System.out.println(map);
    }
}

public class arraylist {
    public static void main(String[] args) {

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        list.add("Apple");
        list.add("Banana");
        list.add("Orange");

        // Iteration is safe even if modified concurrently
        for (String fruit : list) {
            System.out.println(fruit);
            list.add("Grapes"); // Won't cause ConcurrentModificationException
        }

        System.out.println(list);
    }
}

public class arrayblockqueue {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        queue.put(10);
        queue.put(20);

        // Will block until space is available
        new Thread(() -> {
            try {

                queue.take(); // frees space
            } catch (Exception e) {
            }
        }).start();

        queue.put(30);
        System.out.println(queue);
    }
}

public class ConcurrentCollectionsInJava {
    public static void main(String[] args) {
        System.out.println("Explanation of concurrent collections");
    }
}
/*
 * Concurrent Collections in Java are thread-safe versions of standard
 * collections. They allow multiple threads to access or modify data at the same
 * time safely and efficiently, without causing data corruption or throwing
 * exceptions such as ConcurrentModificationException.
 * 
 * Thread Safety: Automatically handles synchronization, so you don’t need to
 * worry about manual locking.
 * Performance: Uses segment-level locking, allowing multiple threads to read
 * and write simultaneously.
 * Error Prevention: Eliminates common concurrency issues like inconsistent data
 * or runtime exceptions.
 * Scalability: Optimized for multi-core systems to maintain high performance
 * even under heavy concurrency
 */