/*
* This Java program demonstrates the use of the 'final' keyword.
* A final variable cannot be reassigned once it has been initialized.
* In this example, MAX_VALUE is declared as final and assigned a value of 100.
* Attempting to change the value of MAX_VALUE will result in a compilation error.
* final varaibles can not be reassigned but their internal state can be changed if they are objects.
* For example, a final ArrayList can have elements added or removed, but the reference to the ArrayList cannot be changed.
* types/ forms of final in java
* 1. final variable
* 2. final method
* 3. final class
* 4. final parameter
* 5. final local variable
* 6. final static variable
*/

// final class example
final class FinalClassExample {
    void display() {
        System.out.println("This is a final class.");
    }
    //a final static method example
    static final void finalStaticMethod() {
        System.out.println("This is a final static method.");
    }
}

/*
* trying to inherit from a final class will result in a compilation error
* class SubClass extends FinalClassExample { // This will cause an error
*  void show() {}  }
*/

public class final_key_word_in_java {
    final int MAX_VALUE = 100;

    void displayMaxValue() {
        System.out.println("The maximum value is: " + MAX_VALUE);
    }

    public static void main(String[] args) {
        
        // implementing the final object that can not be reassigned but can be changed internally
        StringBuilder sb = new StringBuilder("Hello");
        final StringBuilder finalSb = sb; // final reference
        finalSb.append(" World"); // modifying internal state
        System.out.println(finalSb.toString()); // Outputs: Hello World

        final_key_word_in_java obj = new final_key_word_in_java();
        obj.displayMaxValue();
        // Attempting to reassign MAX_VALUE will cause a compilation error
        // obj.MAX_VALUE = 200; // Uncommenting this line will result in an error
        FinalClassExample finalClassObj = new FinalClassExample();
        finalClassObj.display();
        FinalClassExample.finalStaticMethod();
        //trying to override a final method will result in a compilation error
        /*class SubClass extends FinalClassExample {
            void finalStaticMethod() { // This will cause an error
                System.out.println("Trying to override final static method.");
                */
    }   
}
