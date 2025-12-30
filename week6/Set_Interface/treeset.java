
/*
A TreeSet is a collection class that stores unique elements in a sorted order. 
It is part of java.util package that implements the SortedSet interface, and internally uses a Red-Black tree to maintain sorting.
Does not allow duplicates and null values. From JDK 7 onward, inserting null throws NullPointerException.
Implements the NavigableSet interface and provides navigation methods like higher(), lower(), ceiling() and floor().
TreeSet is not synchronized. it must be synchronized using Collections.synchronizedSet(). 
*****COMMOM METHODS OF A TREESET********
add(Object o)	Adds element in sorted order; ignores duplicates.
addAll(Collection c)	Adds all elements from a collection; ignores duplicates..
ceiling?(E e)	This method returns the least element in this set greater than or equal to the given element, or null if there is no such element.
clear()	This method will remove all the elements.
clone()	The method is used to return a shallow copy of the set, which is just a simple copied set.
Comparator comparator()	This method will return the Comparator used to sort elements in TreeSet or it will return null if the default natural sorting order is used.
contains(Object o)	This method will return true if a given element is present in TreeSet else it will return false.
descendingIterator?()	This method returns an iterator over the elements in this set in descending order.
descendingSet?()	This method returns a reverse order view of the elements contained in this set.
first()	This method will return the first element in TreeSet if TreeSet is not null else it will throw NoSuchElementException.
floor?(E e)	This method returns the greatest element in this set less than or equal to the given element, or null if there is no such element.
headSet(Object toElement)	This method will return elements of TreeSet which are less than the specified element.
higher?(E e)	This method returns the least element in this set strictly greater than the given element, or null if there is no such element.
isEmpty()	This method is used to return true if this set contains no elements or is empty and false for the opposite case.
Iterator iterator()	Returns an iterator for iterating over the elements of the set.
last()	This method will return the last element in TreeSet if TreeSet is not null else it will throw NoSuchElementException.
lower?(E e)	This method returns the greatest element in this set strictly less than the given element, or null if there is no such element.
pollFirst?()	This method retrieves and removes the first (lowest) element, or returns null if this set is empty.
pollLast?()	This method retrieves and removes the last (highest) element, or returns null if this set is empty.
remove(Object o)	It returns true/false whether the element was removed
size()	This method is used to return the size of the set or the number of elements present in the set.
spliterator()	This method creates a late-binding and fail-fast Spliterator over the elements in this set.
subSet(Object fromElement, Object toElement)	This method will return elements ranging from fromElement to toElement. fromElement is inclusive and toElement is exclusive.
tailSet(Object fromElement)	This method will return elements of TreeSet which are greater than or equal to the specified element.
*/
import java.util.*;

class treeset {

    public static void main(String[] args) {
        // Creating a TreeSet with a custom Comparator
        Set<StringBuffer> ts = new TreeSet<>(new Comparator<StringBuffer>() {
            @Override
            public int compare(StringBuffer sb1, StringBuffer sb2) {
                return sb1.toString().compareTo(sb2.toString());
            }
        });

        // Adding elements to the TreeSet
        ts.add(new StringBuffer("A"));
        ts.add(new StringBuffer("Z"));
        ts.add(new StringBuffer("L"));
        ts.add(new StringBuffer("B"));
        ts.add(new StringBuffer("O"));
        ts.add(new StringBuffer("1"));

        // Printing the elements
        System.out.println(ts);
        // itteration throught the set
        for (String value : ts)
            System.out.print(value + ", ");
    }
}
