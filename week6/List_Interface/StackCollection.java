import java.util.*;
/*
    In Java, a Stack is a linear data structure that follows the Last In First Out (LIFO) principle and is defined in the java.util package. Internally, it extends the Vector class.

    Stack class maintains insertion order and allows duplicates and null values.
    Grows dynamically when its capacity is exceeded.
    All the methods of Stack are synchronized. It is thread-safe.
    Stack is considered a legacy class, introduced in early versions of Java and a preferred solution to implement Stack Data Structure (especially when thread synchronization is not needed) is either to use ArrayDeque or LinkedList
    Stack class implements List, RandomAccess, Cloneable, and Serializable interfaces.
***Here we are going to implement stack in java and state why we use deque instead of stack 
    so stacks FOLLOW lifo
*** why use deque in place of stack?
    because they maintain the priority meaning the top element remains the last elemnt as in the queue un;ike in stack where it will be inserted in opposite oder
    thats why deque are mostly prefered but still stacks are fast as deques and are implemented by linked list or arrays
 */
import java.util.stream.Collectors;

class StackCollection {
    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        Deque<Integer> deque = new ArrayDeque<>();

        stack.push(1);
        deque.push(1);
        stack.push(2);
        deque.push(2);
        // peek is used to see the top element
        System.out.println(stack.peek());
        List<Integer> list1 = stack.stream().collect(Collectors.toList());
        System.out.println("Using Stack: ");
        for (int i = 0; i < list1.size(); i++) {
            System.out.print(list1.get(i) + " ");
        }
        System.out.println();

        List<Integer> list2 = deque.stream().collect(Collectors.toList());
        System.out.println("Using Deque: ");
        for (int i = 0; i < list2.size(); i++) {
            System.out.print(list2.get(i) + " ");
        }
        System.out.println();
    }
}