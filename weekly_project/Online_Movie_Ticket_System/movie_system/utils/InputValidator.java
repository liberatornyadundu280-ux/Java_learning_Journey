package movie_system.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputValidator {
    // Shared BufferedReader for the entire application
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    // --- Version 1: Basic Integer Validation (No Range) ---
    public static int getValidInt(String prompt) {
        return getValidInt(prompt, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // --- Version 2: Integer Validation with Minimum (Min Only) ---
    public static int getValidInt(String prompt, int min) {
        return getValidInt(prompt, min, Integer.MAX_VALUE);
    }

    // --- Version 3: Full Integer Validation (Min and Max) ---
    public static int getValidInt(String prompt, int min, int max) {
        int inputInt;
        boolean isValid = false;

        while (!isValid) {
            System.out.print(prompt);

            try {
                String input = READER.readLine();

                if (input == null) {
                    System.out.println(">>> Error: Input stream closed unexpectedly.");
                    continue;
                }

                input = input.trim();

                // Data type validation
                inputInt = Integer.parseInt(input);

                // Range validation
                if (inputInt >= min && inputInt <= max) {
                    return inputInt;
                } else {
                    if (min == Integer.MIN_VALUE && max == Integer.MAX_VALUE) {
                        System.out.println(">>> Error: Number out of expected bounds.");
                    } else if (max == Integer.MAX_VALUE) {
                        System.out.println(">>> Error: Please enter a number >= " + min + ".");
                    } else {
                        System.out.println(
                                ">>> Error: Please enter a number between " + min + " and " + max + ".");
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println(">>> Error: Invalid input. Please enter a whole number.");
            } catch (IOException e) {
                System.out.println(">>> Error: Failed to read input. Please try again.");
            }
        }

        // Unreachable logically, but required by compiler
        throw new IllegalStateException("Unexpected input validation state");
    }
}
