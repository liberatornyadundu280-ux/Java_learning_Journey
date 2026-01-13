
/*
 * A set interface is the one which is implemented all classes that store unique
 * values only
 * it is part of that java collection frame work meaning it has common methods
 * like add and remove
 * A set is an interface hence can not contain or have any objects
 * Types of set
 * hashSet: A set that stores unique elements without any specific order, using
 * a
 * hash table and allows one null element.
 * EnumSet : A high-performance set designed specifically for enum types, where
 * all elements must belong to the same enum.
 * LinkedHashSet: A set that maintains the order of insertion while storing
 * unique elements.
 * TreeSet: A set that stores unique elements in sorted order, either by natural
 * ordering or a specified comparator.
 * 
 * 
 * 
 *A set is immutable hence we can not use set method on it 
 common methods on sets 
add(element)	Adds element if not already present. Returns true if added.
addAll(collection)	Adds all elements from the given collection.
clear()	Removes all elements from the set.
contains(element)	Checks if the set contains the specified element.
containsAll(collection)	Checks if the set contains all elements from the given collection.
hashCode()	Returns the hash code of the set.
isEmpty()	This method is used to check whether the set is empty or not.
iterator()	This method is used to return the iterator of the set.
remove(element)	Removes the specified element from the set.
removeAll(collection)	Removes all elements in the given collection from the set.
retainAll(collection)	Retains only elements present in the given collection.
size()	Returns the number of elements in the set.
toArray()	This method is used to form an array of the same elements as that of the Set.

 */
// i am going to implement the basic operations on sets 
import java.util.*;

class intro {

    public static void main(String[] args) {
        // Creating object of Set and declaring String type
        Set<String> h = new HashSet<String>();

        // Adding elements to Set using add() method, Custom input elements
        h.add("A");
        h.add("B");
        h.add("C");
        h.add("B");
        h.add("D");
        h.add("E");

        // Iterating through the Set via for-each loop
        for (String value : h)

            // Printing all the values inside the object
            System.out.print(value + ", ");
        // removing an element from a set
        h.remove("A");
        System.out.println();
    }

}