
/*Object Serialization in Java allows you to save (serialize) and restore (deserialize) the state of an object, even when it’s part of an inheritance hierarchy.
 When dealing with inheritance, the serialization behavior depends on whether the superclass and subclass implement the Serializable interface.

Serialization: It is a mechanism of converting the state of an object into a byte stream.
 The byte array can be the class, version, and internal state of the object.
Deserialization: It is the reverse process where the byte stream is used to recreate the actual Java object in memory.
 This mechanism is used to persist the object.
Understanding Serialization with Inheritance
Serialization behavior changes depending on whether the superclass or subclass implements the Serializable interface. 
There are three main cases to understand: state below
*/
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// superclass A implementing Serializable interface 
class A implements Serializable {
    int i;

    // parameterized constructor
    public A(int i) {
        this.i = i;
    }
}

// subclass B, B class doesn't implement Serializable interface.
class B extends A {
    int j;

    // parameterized constructor
    public B(int i, int j) {
        super(i);
        this.j = j;
    }
}

public class SerializableTests {
    public static void main(String[] args)
            throws Exception {
        B b1 = new B(10, 20);

        System.out.println("i = " + b1.i);
        System.out.println("j = " + b1.j);

        // Saving of object in a file
        FileOutputStream fos = new FileOutputStream("abc.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        // Method for serialization of B's class object
        oos.writeObject(b1);

        // closing streams
        oos.close();
        fos.close();

        System.out.println("Object has been serialized");

        // Reading the object from a file
        FileInputStream fis = new FileInputStream("abc.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);

        // Method for de-serialization of B's class object
        B b2 = (B) ois.readObject();

        // closing streams
        ois.close();
        fis.close();

        System.out.println("Object has been deserialized");

        System.out.println("i = " + b2.i);
        System.out.println("j = " + b2.j);
    }
}

/*
 * // superclass A, A class doesn't implement Serializable interface.
 * class A {
 * int i;
 * 
 * // parameterized constructor
 * public A(int i) {
 * this.i = i;
 * }
 * 
 * // default constructor, this constructor must be present otherwise we will
 * get
 * // runtime exception
 * public A() {
 * i = 50;
 * System.out.println("A's class constructor called");
 * }
 * }
 * 
 * // subclass B, implementing Serializable interface
 * class B extends A implements Serializable {
 * int j;
 * 
 * // parameterized constructor
 * public B(int i, int j) {
 * super(i);
 * this.j = j;
 * }
 * }
 * 
 * // Driver class
 * public class Test {
 * public static void main(String[] args) throws Exception {
 * B b1 = new B(10, 20);
 * 
 * System.out.println("i = " + b1.i);
 * System.out.println("j = " + b1.j);
 * 
 * // Serializing B's(subclass) object
 * 
 * FileOutputStream fos = new FileOutputStream("abc.ser");
 * ObjectOutputStream oos = new ObjectOutputStream(fos);
 * 
 * // Method for serialization of B's class object
 * oos.writeObject(b1);
 * 
 * // closing streams
 * oos.close();
 * fos.close();
 * 
 * System.out.println("Object has been serialized");
 * 
 * // De-Serializing B's(subclass) object
 * 
 * FileInputStream fis = new FileInputStream("abc.ser");
 * ObjectInputStream ois = new ObjectInputStream(fis);
 * 
 * // Method for de-serialization of B's class object
 * B b2 = (B) ois.readObject();
 * 
 * // closing streams
 * ois.close();
 * fis.close();
 * 
 * System.out.println("Object has been deserialized");
 * 
 * System.out.println("i = " + b2.i);
 * System.out.println("j = " + b2.j);
 * }
 * }
 * 
 * // superclass A implementing Serializable interface
 * class A implements Serializable {
 * int i;
 * 
 * // parameterized constructor
 * public A(int i) {
 * this.i = i;
 * }
 * 
 * }
 * 
 * // subclass B, B class doesn't implement Serializable interface.
 * class B extends A {
 * int j;
 * 
 * // parameterized constructor
 * public B(int i, int j) {
 * super(i);
 * this.j = j;
 * }
 * 
 * // By implementing writeObject method, we can prevent subclass from
 * // serialization
 * private void writeObject(ObjectOutputStream out) throws IOException {
 * throw new NotSerializableException();
 * }
 * 
 * // By implementing readObject method, we can prevent subclass from
 * // de-serialization
 * private void readObject(ObjectInputStream in) throws IOException {
 * throw new NotSerializableException();
 * }
 * }
 * 
 * public class Test {
 * public static void main(String[] args)
 * throws Exception {
 * B b1 = new B(10, 20);
 * 
 * System.out.println("i = " + b1.i);
 * System.out.println("j = " + b1.j);
 * 
 * // Serializing B's(subclass) object
 * FileOutputStream fos = new FileOutputStream("abc.ser");
 * ObjectOutputStream oos = new ObjectOutputStream(fos);
 * 
 * // Method for serialization of B's class object
 * oos.writeObject(b1);
 * 
 * // closing streams
 * oos.close();
 * fos.close();
 * 
 * System.out.println("Object has been serialized");
 * 
 * // De-Serializing B's(subclass) object
 * FileInputStream fis = new FileInputStream("abc.ser");
 * ObjectInputStream ois = new ObjectInputStream(fis);
 * 
 * // Method for de-serialization of B's class object
 * B b2 = (B) ois.readObject();
 * 
 * // closing streams
 * ois.close();
 * fis.close();
 * 
 * System.out.println("Object has been deserialized");
 * 
 * System.out.println("i = " + b2.i);
 * System.out.println("j = " + b2.j);
 * }
 * }
 */