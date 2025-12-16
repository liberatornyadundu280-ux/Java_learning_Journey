
/*
* bounded
*/
import java.io.*;

class GenericConstructor {
    // Member variable of this class
    private double v;

    // Constructor of this class where T is typename and t is object
    // the generic is only for tpyes which are subclasses of NUMBER only
    <T extends Number> GenericConstructor(T t) {

        v = t.doubleValue();
    }

    void show() {
        System.out.println("v: " + v);
    }
}

// this is the implementation of a generic interface
interface MinMax<T extends Comparable<T>> {
    T min();

    T max();
}

class MyClass<T extends Comparable<T>>
        implements MinMax<T> {

    T[] values;

    // Constructor of 'MyClass' class
    MyClass(T[] obj) {
        values = obj;
    }

    // Defining abstract min() method
    public T min() {
        // 'T' is typename and 'o1' is object_name
        T o1 = values[0];

        // access of minimum element
        for (int i = 1; i < values.length; i++)
            if (values[i].compareTo(o1) < 0)
                o1 = values[i];

        // Return the minimum element in an array
        return o1;
    }

    // Defining abstract max() method
    public T max() {
        // 'T' is typename and 'o1' is object_name
        T o1 = values[0];

        // Get access of minimum element
        for (int i = 1; i < values.length; i++)
            if (values[i].compareTo(o1) > 0)
                o1 = values[i];

        // Return the maximum element in an array
        return o1;
    }
}

public class AdvancedGenerics {
    public static void main(String[] args) {
        // bounded generics
        GenericConstructor a = new GenericConstructor(5);
        a.show();

        Integer arr[] = { 3, 6, 2, 8, 6 };

        MyClass<Integer> obj1 = new MyClass<Integer>(arr);

        System.out.println("Minimum value: " + obj1.min());

        // printing the maximum value from array elements
        System.out.println("Maximum value: " + obj1.max());
    }
}
