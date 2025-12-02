/*
*** nested interfaces
    -> these are interfaces inside other interfaces and there are twi types which are 
    =>1 nested interface inside class------> the defination is as it describes its self
    =>2 nested interface inside interface ----> same
    -> THESE ARE ACCESSED THROUGH THE OTHER CLASS/ INTERFACE NAE ie . operator
*** marker
    -> an interface without a method 
    types of marker interfaces
    => cloneable 
    =>Serializable
    => Remote Interface
**** comperator
    ->its main purpose it to compare objects 
    -> mainly used for sorting 
    -> it is the one which is used im omost of the sorting algorithms in java
 */

 // i just implemented the use of a comparator only 
import java.util.*;

// Define the Student class
class Student{
    
    String name;
    Integer age;

    Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + " : " + age;
    }
}

// Comparator for multiple fields
class StudentComparator implements Comparator<Student>{
    
    public int compare(Student s1, Student s2) {
        int nameCompare = s1.getName().compareTo(s2.getName());
        int ageCompare = s1.getAge().compareTo(s2.getAge());
        return (nameCompare == 0) ? ageCompare : nameCompare;
    }
}

public class nested_marker_comperator_in_java{
    
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Ajay", 27));
        students.add(new Student("Sneha", 23));
        students.add(new Student("Simran", 37));

        System.out.println("Original List:");
        for (Student s : students) {
            System.out.println(s);
        }

        // Sort by name, then by age
        Collections.sort(students, new StudentComparator());

        System.out.println("\nAfter Sorting:");
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
