import java.util.HashSet;
import java.util.Iterator;

/*
common methods on hash set 
add(E e)	Used to add the specified element if it is not present, if it is present then return false.
clear()	Used to remove all the elements from the set.
contains(Object o)	Used to return true if an element is present in a set.
remove(Object o)	Used to remove the element if it is present in set.
iterator()	Used to return an iterator over the element in the set.
isEmpty()	Used to check whether the set is empty or not. Returns true for empty and false for a non-empty condition for set.
size()	Used to return the size of the set.
clone()	Used to create a shallow copy of the set. */
public class hash_Set {
    public static void main(String[] args) {
        // Create a HashSet of Strings
        HashSet<String> hs = new HashSet<>();

        // Add elements to the HashSet
        hs.add("A");
        hs.add("B");
        hs.add("Geeks");
        hs.add("For");
        hs.add("Geeks");
        hs.add("Z");

        // Using iterator() method to iterate Over the HashSet
        System.out.print("Using iterator : ");
        Iterator<String> iterator = hs.iterator();

        // Traversing HashSet
        while (iterator.hasNext())
            System.out.print(iterator.next() + ", ");
        // removing ana item from a hash set
        hs.remove("A");
        System.out.println();

        // Using enhanced for loop to iterate Over the HashSet
        System.out.print("Using enhanced for loop : ");
        for (String element : hs)
            System.out.print(element + " , ");
    }
}