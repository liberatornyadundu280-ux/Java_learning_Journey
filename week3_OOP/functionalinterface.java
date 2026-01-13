/*
* these contain only one methods and there are used inn lambda functions
***** types of functional interfaces include 
    1 predicate
    2 consumer
    3 function
    4 supplier

*** other implementation will be done as we work with real world scenarios ***
 */

import java.util.*;
import java.util.function.Predicate;

class Geeks {
    public static void main(String args[]) {
      
        // create a list of strings
        List<String> n = Arrays.asList("Geek", "GeeksQuiz", "g1", "QA", "Geek2");

        // declare the predicate type as string and use lambda expression to create object
        Predicate<String> p = (s) -> s.startsWith("G");

        // Iterate through the list
        for (String st : n) {
          
            // call the test method
            if (p.test(st))
                System.out.println(st);
        }
    }
}