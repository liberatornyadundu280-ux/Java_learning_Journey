
/*
 this interface provides methds for 
--> arraylist
--> stack
--> linkedlist
--> vector
*/
import java.util.*;

public class ListInterface {
    // creating an array list using the list interface
    public static void main(String[] args) {
        List<String> al = new ArrayList<>();
        // geting the length of the list we use .size();
        int len = al.size();
        // removing all the elements in the list we use .clear();
        al.clear();
        // adding an item
        al.add("nigga");
        al.add("come Over");
        // adding to a specific index
        al.add(1, "please");
        // updating a an arraylist
        al.set(2, "come over here");
        // searching for an element in the array using the index
        al.indexOf("please"); // if its not available it will return -1 and return first occurance
        al.lastIndexOf("nigga");// return the last occurance of the object
        // removing an item from a array list this one it overloaded as it you can pass
        // an index to it
        al.remove("please");
        // get the item from an array list
        String first = al.get(0);
        // checking if the item is fount in the list "object"
        System.out.println("does the array list contain 'You' " + al.contins("You"));
        // iterating through the collection using the for each loop
        for (String s : al) {
            System.out.print(s + "  ");
        }
        // adding all elememnts of an iterator to another using add all
        System.out.println(al.addAll(al));
    }
}
