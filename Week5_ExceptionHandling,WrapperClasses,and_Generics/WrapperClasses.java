/*
* what are theyy used for ?
-->basicallly these are used for collections as primitive data types can not be used 
--> this is what sets them apart and and neccesary
--> by default the wrapper calsses are objects meanning their initial values are "NULL"
--> generally defined by staing the full name of a primitive type starting with a capital letter ie int----->Integer
* Problems with using java wrapperclasses
--> there are generally slow compared to primitive types
*** Autoboxing and Unboxing of wrapper classes
** Autoboxing
--> this is when we assign a wrapper class object a corresponding primitive type there by automatically converting it to the desired primitive type yet an object
--> The automatic conversion of primitive types to the object of their corresponding wrapper classes is known as autoboxing.
    For example -> conversion of int to Integer, long to Long, double to Double, etc. 
** Unboxing
--> this is the opposite of Autoboxing
--> this is when you assign a primitive data type to a wrapper class "conversion"
-->It is just the reverse process of autoboxing. 
   Automatically converting an object of a wrapper class to its corresponding primitive type is known as unboxing.
   For example, conversion of Integer to int, Long to long, Double to double, etc. 


****** Primitive Data Types vs Wrapper Classes  *******
--->** Primitive Data Types
   Fast and memory efficient
   Cannot be null
   Cannot be serialized directly
   Not suitable for collections
   Used in basic arithmetic or loops
   
---->** Wrapper Classes
Slightly slower due to it creates an object.
Can be null
Can be serialized directly
Required when using Java Collections like ArrayList
Used when methods return objects or null
 */

import java.util.ArrayList;

public class WrapperClasses {
    public static void main(String[] args) {
        // this is the example of autoboxing
        char ch = 'a';

        // Autoboxing- primitive to Character object
        // conversion
        Character a = ch;
        System.out.println(a);
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        // Autoboxing because ArrayList stores only objects
        arrayList.add(25);

        // printing the values from object
        System.out.println(arrayList.get(0));

        // this is the example for unboxing
        Character ch1 = 'a';

        // unboxing - Character object to primitive conversion
        char a1 = ch1;
        System.out.println(a1);
        ArrayList<Integer> arrayList1 = new ArrayList<Integer>();
        arrayList1.add(24);

        // unboxing because get method returns an Integer object
        int num = arrayList1.get(0);

        // printing the values from primitive data types
        System.out.println(num);
    }
}
