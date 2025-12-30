/*
The Queue interface is part of the java.util package. It extends the Collection interface.
Elements are processed in the order determined by the queue implementation (First In First Out or FIFO for LinkedList, priority order for PriorityQueue).
Elements cannot be accessed directly by index.
A queue can store duplicate elements. 

*****Common Implementations of Queue Interface*****
LinkedList: A FIFO queue that allows null elements and implements both Queue and Deque interfaces.
ArrayDeque: A resizable array-based queue that is faster than LinkedList and does not allow nulls.
PriorityQueue: A queue where elements are processed according to their priority instead of insertion order.
ConcurrentLinkedQueue: A thread-safe, non-blocking queue suitable for concurrent environments.
BlockingQueue: A thread-safe queue that supports blocking operations for producer-consumer scenarios.


****** Common methods of queues**********
add(E e)	Inserts the specified element; throws exception if insertion fails.
offer(E e)	Inserts the specified element; returns false if insertion fails.
remove()	Removes and returns the head of the queue; throws exception if empty.
poll()	Removes and returns the head; returns null if empty.
peek()	Retrieves, but does not remove, the head; returns null if empty.
size()	Returns the number of elements in the queue.
isEmpty()	Returns true if the queue contains no elements.
contains(Object o)	Returns true if the queue contains the specified element.
iterator()	Returns an iterator over the elements in the queue.
toArray()	Converts the queue elements into an array.
addFirst(E e)	Inserts element at the front (Deque only).
addLast(E e)	Inserts element at the end (Deque only).
offerFirst(E e)	Inserts element at the front; returns false if fails (Deque only).
offerLast(E e)	Inserts element at the end; returns false if fails (Deque only).
removeFirst()	Removes and returns the first element (Deque only).
removeLast()	Removes and returns the last element (Deque only).
pollFirst()	Removes and returns the first element; returns null if empty (Deque only).
pollLast()	Removes and returns the last element; returns null if empty (Deque only).
getFirst()	Retrieves, but does not remove, the first element (Deque only).
getLast()	Retrieves, but does not remove, the last element (Deque only).
peekFirst()	Retrieves, but does not remove, the first element; returns null if empty (Deque only).
peekLast()	Retrieves, but does not remove, the last element; returns null if empty (Deque only).
put(E e)	Inserts element, waits if necessary (BlockingQueue only).
take()	Removes and returns head element, waits if empty (BlockingQueue only).
*/

import java.util.PriorityQueue;
import java.util.Queue;

public class QueueInterfaceAndClasses {
    public static void main(String args[]) {
        Queue<String> pq = new PriorityQueue<>();

        pq.add("Geeks");
        pq.add("For");
        pq.add("Geeks");

        Iterator iterator = pq.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}

// array Deque
/*
 * ArrayDeque is a resizable-array implementation of the Deque interface in
 * Java. It is part of java.util package provides a double-ended queue
 * implementation, allowing elements to be added or removed from both ends
 * efficiently.
 * 
 * ArrayDeque grows dynamically.
 * It generally provides faster operations than LinkedList as it is more cache
 * friendly and does not require overhead of next previous reference management.
 * Operations like addFirst(), addLast(), removeFirst(), removeLast() are all
 * done in constant time O(1).
 * ArrayDeque is not Thread-Safe
 */
import java.util.*;

public class Geeks {
    public static void main(String[] args) {
        // Declaring and initializing an deque
        Deque<String> d = new ArrayDeque<String>();

        // Adding elements at the back using add() method
        d.add("For");

        // Adding element at the front using addFirst() method
        d.addFirst("Geeks");

        // add element at the last using addLast() method
        d.addLast("Geeks");
        d.add("is so good");

        // Iterate using Iterator interface from the front of the queue
        System.out.println("Iterating in ForwardOrder:");
        for (Iterator i = d.iterator(); i.hasNext();) {

            System.out.print(i.next() + " ");
        }

        System.out.println();
        d.remove("For");
        // Iterate in reverse sequence in a queue
        System.out.println("Iterating in ReverseOrder:");
        for (Iterator i = d.descendingIterator(); i.hasNext();) {

            System.out.print(i.next() + " ");
        }
    }
}

/*
 ***** ===>>Deque interface
 * The Deque interface is part of the java.util package and extends the Queue
 * interface. It stands for Double-Ended Queue and represents a linear
 * collection that allows insertion, removal, and retrieval of elements from
 * both ends.
 * 
 * Flexible Data Structure: Can be used as a stack (LIFO) or a queue (FIFO).
 * Deque allows null depending on implementation, but most throw
 * NullPointerException (ArrayDeque, ConcurrentLinkedDeque).
 * Resizable Implementations: Implementations like ArrayDeque grow dynamically
 * as needed.
 * Thread Safety: Most implementations are not thread-safe; synchronization is
 * required for concurrent use.
 * 
 * 
 *********** Common Implementations classes***************
 * ArrayDeque: Resizable array implementation; fast for insertion/removal at
 * both ends; not thread-safe.
 * LinkedList: Doubly-linked list; supports all deque operations; allows nulls.
 * ConcurrentLinkedDeque: Thread-safe deque based on linked nodes; suitable for
 * concurrent use.
 * 
 * 
 * 
 * 
 **************** Priority Queue********************
 * A PriorityQueue in Java is a queue where elements are ordered based on their
 * priority, rather than the order of insertion. By default, it uses natural
 * ordering (min-heap), but a custom comparator can be used to define different
 * priorities.
 * 
 * Elements are processed based on priority rather than insertion order.
 * Supports standard queue operations like add(), poll(), and peek().
 * Automatically grows as elements are added.
 * Uses a heap data structure internally to ensure efficient insertion and
 * removal of the highest-priority element.
 * Declaration of PriorityQueue
 ****** Common methods
 * add(E e) Inserts the specified element into this priority queue.
 * clear() Removes all of the elements from this priority queue.
 * comparator() Returns the comparator used to order the elements in this queue
 * or null if this queue is sorted according to the natural ordering of its
 * elements.
 * contains?(Object o) Returns true if this queue contains the specified
 * element.
 * forEach?(Consumer<? super E> action) Performs the given action for each
 * element of the Iterable until all elements have been processed or the action
 * throws an exception.
 * iterator() Returns an iterator over the elements in this queue.
 * offer?(E e) Inserts the specified element into this priority queue.
 * remove?(Object o) Removes a single instance of the specified element from
 * this queue, if it is present.
 * removeAll?(Collection<?> c) Removes all of this collection's elements that
 * are also contained in the specified collection (optional operation).
 * removeIf?(Predicate<? super E> filter) Removes all of the elements of this
 * collection that satisfy the given predicate.
 * retainAll?(Collection<?> c) Retains only the elements in this collection that
 * are contained in the specified collection (optional operation).
 * spliterator() Creates a late-binding and fail-fast Spliterator over the
 * elements in this queue.
 * toArray() Returns an array containing all of the elements in this queue.
 * toArray?(T[] a) Returns an array containing all of the elements in this
 * queue; the runtime type of the returned array is that of the specified array.
 */
import java.util.*;

public class Geeks {
    public static void main(String args[]) {
        PriorityQueue<String> pq = new PriorityQueue<>();

        pq.add("Geeks");
        pq.add("For");
        pq.add("Geeks");

        Iterator iterator = pq.iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}