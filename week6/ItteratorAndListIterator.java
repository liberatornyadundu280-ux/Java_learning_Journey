
/*
 ***Iterators
    are used to traverse all object of clases that implement the iterable interface
    there are only unidirectional meaning they can only move forward only 
    there are applied to any object that iimplemets the iterable interface
    when you start or declare an iterator it will be pointing at the address b4 the starting of the object
    hence allowing it to have next() pointing to the next item
    hasnext() which return boolen that confirms if there are still any items in the object 
    you can also use .remove() method to remove an item
  */
import java.util.ArrayList;
import java.util.Iterator;

public class ItteratorAndListIterator {

    public static void main(String[] args) {

        // Creating an ArrayList of Integer type
        ArrayList<Integer> al = new ArrayList<>();

        // Adding elements to the ArrayList
        for (int i = 0; i < 10; i++) {
            al.add(i);
        }

        // Printing the original list
        System.out.println("Original List: " + al);

        // Creating an Iterator for the ArrayList
        Iterator<Integer> itr = al.iterator();

        // Iterating through the list and removing odd elements
        while (itr.hasNext()) {

            // Getting the next element
            int i = itr.next();

            System.out.print(i + " ");

            // Removing odd elements
            if (i % 2 != 0) {
                itr.remove();
            }
        }

        System.out.println();

        // Printing the modified list after removal of odd elements
        System.out.println("Modified List: " + al);
    }
}
/*
 **** ListIterator
 * these are bidirectional and there are only applicable to object of classes
 * that implement the list interface only
 * they have alll the properties of the itterator and even added some advance
 * and better ones
 * Supports Create, Read, Update, Delete (CRUD) operations.
 * the cursor is alway bointig betwenn the indexes ie the addresses but never to
 * the item hence allowing for next and previous methods
 */

public class Listiterator {

    public static void main(String[] args) {
        // list of names
        List<String> names = new LinkedList<>();
        names.add("learn");
        names.add("from");
        names.add("Geeksforgeeks");

        // Getting ListIterator
        ListIterator<String> listIterator = names.listIterator();

        // Traversing elements
        System.out.println("Forward Direction Iteration:");
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }

        // Traversing elements, the iterator is at the end at this point
        System.out.println("Backward Direction Iteration:");
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
    }
}
//

/*
 ****************** iterator vs ListIterator*************************
 * --->It can traverse a collection of any type. vs It traverses only list
 * collection implemented classes like LinkedList, ArrayList, etc.
 * Traversal can only be done in forwarding direction. vs Traversal of elements
 * can be done in both forward and backward direction.
 * Iterator object can be created by calling iterator() method of the collection
 * interface. vs ListIterator object can be created by calling listIterator()
 * method of the collection interface.
 * Deletion of elements is allowed using remove() method.vs Deletion of elements
 * is allowed.
 * It throws ConcurrentModificationException on doing addition operation. Hence,
 * addition is not allowed. vs Addition of elements is allowed.
 * In iterator, we can't access the index of the traversed element. vs In
 * listIterator, we have nextIndex() and previousIndex() methods for accessing
 * the indexes of the traversed or the next traversing element.
 * Modification of any element is not allowed. vs Modification is allowed
 */
