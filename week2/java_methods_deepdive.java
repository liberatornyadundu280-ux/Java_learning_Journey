/* This Java class demonstrates the use of methods including
   a method to calculate factorial and a method to check for prime numbers.
*/
/*the below class demonstrates the use of methods in Java like static and non static and abstract classes how they work amnd called */
abstract class demo {
    void print(){}
    /*the abstract class does not have any objects */
}

class demo2 extends demo {
    @Override
    void print() {
        System.out.println("this is an abstract class method implementation");
    }
}       

public class java_methods_deepdive {
    // method to calculate the factorial of a number
    public static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    // method to check if a number is prime
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }  
    // main method to test the above methods
    public static void main(String[] args) {
        /*this part of the code demonstrate that java takes these args from the command line */
        for(String arg : args) {
            System.out.println("Argument: " + arg);
        }   
        /*this peace of code demonstrate the use the factorial and isPrime methods */
        int number = 5;
        System.out.println("Factorial of " + number + " is: " + factorial(number));

        int primeCheck = 29;
        if (isPrime(primeCheck)) {
            System.out.println(primeCheck + " is a prime number.");
        } else {
            System.out.println(primeCheck + " is not a prime number.");
        }
        demo2 obj = new demo2();
        obj.print();

        /*implementation of varargs  that do not have a defined number of arguments 
         * they take the arguments as an array 
         */        System.out.println("Sum of 1, 2, 3: " + sum(1, 2, 3));
        System.out.println("Sum of 10, 20, 30, 40, 50: " + sum(10, 20, 30, 40, 50));
    }
    // method to demonstrate varargs
    /*varargs can also work together with other arguments but need to be put as the last argument
     * only one varargs is allowed per method
     * varargs are treated as arrays within the method
     * varargs can be called with zero or more arguments
     * varargs improve code readability and flexibility
     * varargs can lead to ambiguity if not used carefully
     * varargs can be overloaded with other methods
     */
    public static int sum(int... numbers) {
        int total = 0;
        System.out.print("Adding numbers: ");
        for (int num : numbers) {
            total += num;           
    }
    System.out.println("the numbers are "+java.util.Arrays.toString(numbers));
        return total;
    }
    /*static vs instance methods */
    public void instanceMethod() {
        System.out.println("This is an instance method.");
    }
    public static void staticMethod() {
        System.out.println("This is a static method.");
    }
    /*as static method does not allow the use of this pointer 
     * instance methods can use this pointer to refer to the current object
     * static methods can be called without creating an object of the class
     * static methods can only access static variables and static methods directly
     * instance methods can access both instance and static variables and methods
     * instance methods require an object of the class to be called
     * static methods can be called using the class name directly
     * static methods are often used for utility or helper methods
     * static initialized onces when the class is called
     * instance initialized when the object is created
     * static methods can not be overloaded with instance methods
     * static methods can be used in static blocks
     * instance methods can not be used in static blocks
     */
}
