
/*
    *****comparator********
    mainly is used when dealing with user defined data types like example in a student ms
    so they help use to sort the data in any order we want provided we give a custom comparator with our logic
    generally it is a class that implements a comaperator interface ie 
    class MyComparator implements Comparator<Type> {
           public int compare(Type obj1, Type obj2) {
               // comparison logic
      }
}

    *********comparable********
    this is used in the class in such a way that the user defined data type becomes a comparable object by implementing the comparable interface
    so here we make use of the compareto() methode that will help us to to compare our values ina the specified order
    the class should implement the comparable interface to be able to pull of this 
    this is how it is implemented below 
 */
import java.util.*;

// Define the Student class
class Student {

    int rollno;
    String name;

    Student(int rollno, String name) {

        this.rollno = rollno;
        this.name = name;
    }

    @Override
    public String toString() {

        return rollno + ": " + name;
    }
}

// Helper class implementing Comparator interface
class SortByRoll implements Comparator<Student> {

    public int compare(Student a, Student b) {
        return a.rollno - b.rollno; // Ascending order
        /*
         * a-b---> will return in ascending order
         * b-a-----> will return in descending order
         */
    }
}

// Driver class
public class ComparatorClass {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();
        students.add(new Student(111, "Mayank"));
        students.add(new Student(131, "Anshul"));
        students.add(new Student(121, "Solanki"));
        students.add(new Student(101, "Aggarwal"));

        // Sort students by roll number
        Collections.sort(students, new SortByRoll());
        students.sort(Comparator.comparing(Student::getName).thenComparing(Student::getAge));
        // this is the lambda function for sorting still does the same as the class
        // implemented
        System.out.println("Sorted by Roll Number:");
        for (Student s : students) {
            System.out.println(s);
        }
    }
}

class Student implements Comparable<Student> {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    @Override
    public int compareTo(Student other) {
        return this.marks - other.marks; // ascending order by marks
    }

    @Override
    public String toString() {
        return name + ": " + marks;
    }
}

public class ComparableExample {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 85));
        students.add(new Student("Bob", 92));
        students.add(new Student("Charlie", 78));

        Collections.sort(students);
        /*
         * here there wont be any need for you to say what methods you ar calling but
         * just implement the collection.sort() call as the objects are made comparable
         */

        for (Student s : students) {
            System.out.println(s);
        }
    }
}

public class Comparator_Vs_Comparable {
    public static void main(String[] args) {
        System.out.println("this program is to implement the comparable and the comparator interface");
    }
}
/*
 ************ comparable vs comparator**********************
 * comparable only allow us to use it once ie u can only sort the elements using
 * only one property eg
 * in movies you can sort then in the year of release but u can sort them letter
 * using their rating coz
 * the compare to method is now take making it impossible use the comparable
 * interface to implement that sorting
 **************** comparable vs comparator****************
 * Definition
 * It defines natural ordering within the class. vs It defines external or
 * custom sorting logic.
 * Method
 * --> compareTo() vs compare()
 * Implementation
 * --> It is implemented in the class. vs It is implemented in a separate class.
 * Sorting Criteria
 * -->Natural order sorting vs Custom order sorting
 * Usage
 * -->It is used for a single sorting order. vs It is used for multiple sorting
 * orders.
 */
