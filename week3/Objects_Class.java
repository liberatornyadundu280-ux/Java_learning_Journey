/*
* This class demonstrates the use of objects in Java.
* It has two instance variables: name and value.
* The constructor initializes these variables.
* The main method creates two objects and prints their details.
* common methods of Object class are
* 1. toString()
* 2. equals()
* 3. hashCode()
* 4. getClass()
* 5. clone()
* 6. finalize()
**** A must to know for java developers ****
    * if objects are compared using .equals() this means their hashcodes are alsoo equal
    * but if hashcodes are equal it does not mean objects are equal
    * this is because hashcode is just an integer representation of the object memory address
    * two different objects can have same hashcode due to collision
    * hence when overriding equals() method always override hashcode() method as well
    * this will ensure that if two objects are equal their hashcodes will also be equal
    * but if two objects have same hashcode they may or may not be equal
    * this is important for collections like HashMap, HashSet etc.
    * when storing objects in such collections the hashcode is used to determine the bucket location
    * and then equals() method is used to check for equality within that bucket
    * hence always override both methods to maintain consistency
    * for example if you have a class demoObject with id and description fields
    * you can override equals() and hashcode() methods as shown below
 */
class demoObject {
    int id;
    String description;

    public demoObject(int id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return "demoObject [id=" + id + ", description=" + description + "]";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())// here we are checking if obj is null or not of same class edge case
            return false;
        demoObject other = (demoObject) obj;
        return id == other.id && description.equals(other.description);
    }
    @Override
    public int hashCode() {
        int result = Integer.hashCode(id);
        result = 31 * result + description.hashCode();
        return result;
    }
}  

/*
* now you are asking why override methods of Object class?
* the reason is that the default implementation of these methods in Object class
* may not be suitable for your class
* for example the default toString() method returns a string representation of the object
* which is not very informative
* by overriding it you can provide a more meaningful representation of your object
* similarly the default equals() method compares object references
* which may not be what you want
* by overriding it you can define your own criteria for object equality
* same goes for hashCode() method
*/

public class Objects_Class {
    String name;
    int value;

    public Objects_Class(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static void main(String[] args) {
        Objects_Class obj1 = new Objects_Class("Object1", 10);
        Objects_Class obj2 = new Objects_Class("Object2", 20);

        System.out.println("Name: " + obj1.name + ", Value: " + obj1.value);
        System.out.println("Name: " + obj2.name + ", Value: " + obj2.value);
    }
}
