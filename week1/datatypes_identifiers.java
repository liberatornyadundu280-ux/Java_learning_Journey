interface base {
    void print();
    
}

class derived implements base {
public
    void print()
    {
        System.out.println("\nthis is a derived class im implementing");
    }
}

public class datatypes_identifiers {
    public static void main(String[] args)
    {
        String str="Wassup";  // what makes string uniques that there are immutable
        System.out.println("\nthis is a string variable " + str);
        // creating class objects
        // derived b; // object identifier created 
        // b.print();
        base a=new derived();
        System.out.println("\nthis is a object identifier of base class");
        a.print();
        System.out.println("implementation of an array which is boring lin im doing c😂");
        int[] arr={1,2,3,4,2,13,4,98};// an array of integers
        String[] s={"Geek","rocks","for","ever","so","do","I"};
        char[] ch={'A','r','r','c','h','a','r','s'};
        System.out.println("\na character form the array of chars "+ch[4]);
        System.out.println("\na string form the array of strings "+s[4]);
        System.out.println("\na number form the array of integers  "+arr[4]);
        System.out.println( "i would have implemented more but its not necessary\n");
        System.out.println("here im implementing the number concatenation  "+arr[0]+ arr[6]);
        int n1=24;
        double n2=356.786;
        double implicit=n1;
        int explicit= (int) n2;
        System.out.println("before implicit "+n1+" after implicit "+implicit);
        System.out.println("before explicit "+n2+" after explicit "+explicit);
    }
}
/*
 * as you know most of the primitive data types are declared the same
 * in c and c++ also in java so i wont get to the i will focus 
 * on the non primitive data tpyesie array, strings,etc
 * Non_primitive data type are created by user mainly ie user defined 
 * in java strings are immutable 
 * 
 * class identifier mainly the blueprint of the object 
 * object identifier which exploits the characteristics ie blueprint of the class
 * interface is an abstract class
 * ---> can not have an instance ie object 
 * ---> allows for multiple inheritance since it will contain all the methods
 * ---> has some desired characteristics of other class
 * array which is dynamical and is implemented as below
 * ---> data-type[] array_name;
 * -->as simple like that 
 * in java you can concatenate number and strings to make a string which is veru cool
 * 
 * -------------------------------------------------------------------------------------------------------------------------
 * 
 * type conversion in java 
 * 1-> explicit where were are trimming  or shortening a data type
 * --->syntax----> int i=(int)d "double"
 * 2-> implicit where you will be making the emelents int bigger ie widening
 * --->char->byte->short->int->long->float->double 
 * ---> syntax double= i "int"---> reduces precision
 */