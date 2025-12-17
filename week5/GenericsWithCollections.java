/*
** What are Collections
   these are are data structures which are used to store our data 
   they only work with wrapper classes ie they don't work with premitive data types 

** Why are are generics used on them 
   because they ensure type safety
   reusability
   saves codding time 
   avoids casting
   maintainability
generally they work teh same way as i have been explaining the previous works nothing new 
ie they can have wild cards ie upper bound, unknown
******i have provided one example for that demonstration****

** type casting and type safety
   to ensure to maintain tpye safty avoid using generic collections ie 
   using a collection without specifying the type of data they take 
   why?
   -> because every time when you need to assign to a variable u will need to type cast ie specify the type other wise it will throw an error
   make sure you specify type unless u want to take different data sets and types
   -> but it come with a cost as it wont allow you to stringfy your data as it wont be valid due to different types
   **** i have provided a program that would throw an error due to type casting ******

** Type erasure in java
    Type Erasure is the process by which the Java compiler removes all generic type information and replaces type parameters with their upper bounds (or Object if unbounded).

    During compilation, the compiler performs:

    Replacing type parameters with their upper bound or Object.
    Inserting casts where needed to maintain type safety.
    Generating bridge methods to preserve method overriding in generic hierarchies.
    
****** when it comes to this one i really did not understand how it affects our lives so i will just provide an example****** 

public class type erasure{
    
    public static void main(String args[]){
        
        List<String> list = new ArrayList<>();
        list.add("Hello");

        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            String s = iter.next();
            System.out.println(s);
        }
    }
}

*/

import java.util.*;

public class GenericsWithCollections {

    // Method accepts any type of List
    public static void printList(List<?> list) {
        for (Object item : list) {
            System.out.println(item);
        }
    }

    // they function will coz type casting error ie need for type casting
    public void errorCozing() {
        ArrayList al = new ArrayList();
        al.add("Vivek Yadav");
        al.add("Ravi");
        String name1 = al.get(0);
    }

    public static void main(String[] args) {
        List<String> strList = Arrays.asList("Geeks", "for", "Geeks");
        List<Integer> intList = Arrays.asList(1, 2, 3);

        printList(strList); // Works with List<String>
        printList(intList); // Works with List<Integer>
    }
}
