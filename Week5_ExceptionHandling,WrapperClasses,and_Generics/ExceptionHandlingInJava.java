import java.io.*;

public class ExceptionHandlingInJava {
    public static void main(String[] args) {

        int n = 10;
        int m = 0;
        // this is the try and catch exception

        try {
            int ans = n / m;
            System.out.println("Answer: " + ans);
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by 0!");
        }
        // with the final block
        int[] numbers = { 1, 2, 3 };
        try {
            // This will throw ArrayIndexOutOfBoundsException
            System.out.println(numbers[5]);

        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("Exception caught: " + e);
        } finally {// this part is ran no metter what used for cleaning mostly
            System.out.println("This block always executes.");
        }
        System.out.println("Program continues...");

        try {
            readFile("test.txt");
        } catch (IOException e) {

            System.out.println("File not found: " + e.getMessage());
        }

        // nested try catch
        try {
            System.out.println("Outer try block");
            try {
                int a = 10 / 0; // This causes ArithmeticException
            } catch (ArithmeticException e) {
                System.out.println("Inner catch: " + e);
            }
            String str = null;
            System.out.println(str.length()); // This causes NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Outer catch: " + e);
        }

        // example of multiple catch try-catch block
        try {

            // Code that may throw an exception

        } catch (ArithmeticException e) {

            // Code to handle the exception

        } catch (ArrayIndexOutOfBoundsException e) {

            // Code to handle the another exception

        } catch (NumberFormatException e) {

            // Code to handle the another exception
        }
    }

    // throw is used to throw a user defined exception for example in voting
    static void checkAge(int age) {

        if (age < 18) {
            throw new ArithmeticException("Age must be 18 or above");
        }
    }

    // throws is used when a functioncreated is goin to throw an error so it is
    // defined at the top
    // so any error which occure within the function will be thrown
    static void readFile(String fileName) throws IOException {

        FileReader file = new FileReader(fileName);
    }
}
