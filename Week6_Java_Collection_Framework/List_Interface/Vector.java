/*
    this is generally a modificationn of a to the arraylist
    why is it better then?
    -> because it is thread safe but still arraylist is still prefered i working with thread safe environment
    In Java, a Vector is a dynamic array that can grow or shrink in size as elements are added or removed. It is part of the java.util package and extends the AbstractList class.

Maintains insertion order and allows duplicate and null values.
Dynamically grows its size when capacity is exceeded.
Implements List, RandomAccess, Cloneable, and Serializable interfaces.
Vector is a Legacy class that was introduced in early versions of Java.
Thread-safe: All methods are synchronized for safe multi-threaded access.
ArrayList is preferred over vector in general when in-built thread synchronization is not required..

in the program i will just show how its declareed in general as there is nothing much for mw to say 
*/

import java.util.*;

public class Vector {

    public static void main(String args[]) {
        // create an instance of vector
        Vector<String> v = new Vector<>();

        // Add elements using add() method
        v.add("Geeks");
        v.add("Geeks");
        v.add(1, "For");

        // Using the Get method and the for loop
        for (int i = 0; i < v.size(); i++) {

            System.out.print(v.get(i) + " ");
        }
        v.remove(1);// deleting an item from index 1
        v.set(1, "nuts");// updating
        System.out.println();

        for (String str : v)
            System.out.print(str + " ");
    }
}
