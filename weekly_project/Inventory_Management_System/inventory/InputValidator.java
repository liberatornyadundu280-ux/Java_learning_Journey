package inventory;

import java.util.Scanner;

public class InputValidator {

    // Use a static Scanner shared across all validator methods.
    private static final Scanner SCANNER = new Scanner(System.in);

    // --- Version 1: Basic Integer Validation (No Range) ---
    // User can call: InputValidator.getValidInt("Age: ");
    public static int getValidInt(String prompt) {
        // Calls the full version, using the smallest and largest possible integers for
        // the range.
        return getValidInt(prompt, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // --- Version 2: Integer Validation with Minimum (Min Only) ---
    // User can call: InputValidator.getValidInt("ID: ", 100);
    public static int getValidInt(String prompt, int min) {
        // Calls the full version, using the largest possible integer as max.
        return getValidInt(prompt, min, Integer.MAX_VALUE);
    }

    // --- Version 3: Full Integer Validation (Min and Max) ---
    /**
     * Prompts the user for an integer until a valid number within the range is
     * provided.
     * 
     * @param prompt The message displayed to the user.
     * @param min    The minimum acceptable integer value (inclusive).
     * @param max    The maximum acceptable integer value (inclusive).
     * @return A valid integer provided by the user.
     */
    public static int getValidInt(String prompt, int min, int max) {
        int inputInt = -1;
        boolean isValid = false;

        // Loop until a valid input is received
        while (!isValid) {
            System.out.print(prompt);
            String input = SCANNER.nextLine();

            try {
                // 1. Check for data type error (NumberFormatException)
                inputInt = Integer.parseInt(input);

                // 2. Check for range error
                if (inputInt >= min && inputInt <= max) {
                    isValid = true; // Input is valid and in range, exit loop
                } else {
                    // Provide clear feedback based on the constraints
                    if (min == Integer.MIN_VALUE && max == Integer.MAX_VALUE) {
                        // This case is for the default (no range) call, but we still print an error
                        System.out.println(">>> Error: Number out of expected bounds.");
                    } else if (max == Integer.MAX_VALUE) {
                        System.out.println(">>> Error: Please enter a number greater than or equal to " + min + ".");
                    } else {
                        System.out.println(">>> Error: Please enter a number between " + min + " and " + max + ".");
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println(">>> Error: Invalid input. Please enter a whole number.");
                // isValid remains false, loop continues
            }
        }
        return inputInt;
    }
}