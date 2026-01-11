package movie_system.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputValidator {

    // Shared BufferedReader for entire application
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    /*
     * ===============================
     * INTEGER VALIDATION (unchanged)
     * ===============================
     */

    public static int getValidInt(String prompt) {
        return getValidInt(prompt, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static int getValidInt(String prompt, int min) {
        return getValidInt(prompt, min, Integer.MAX_VALUE);
    }

    public static int getValidInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = READER.readLine();
                if (input == null) {
                    System.out.println(">>> Error: Input stream closed.");
                    continue;
                }

                input = input.trim();
                int value = Integer.parseInt(input);

                if (value < min || value > max) {
                    System.out.println(
                            ">>> Error: Enter a number between " + min + " and " + max + ".");
                    continue;
                }

                return value;

            } catch (NumberFormatException e) {
                System.out.println(">>> Error: Enter a valid whole number.");
            } catch (IOException e) {
                System.out.println(">>> Error: Failed to read input.");
            }
        }
    }

    /*
     * ===============================
     * STRING VALIDATION (NEW)
     * ===============================
     */

    /**
     * Mandatory string input (non-null, non-empty)
     */
    public static String getRequiredString(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = READER.readLine();

                if (input == null) {
                    System.out.println(">>> Error: Input stream closed.");
                    continue;
                }

                input = input.trim();

                if (input.isEmpty()) {
                    System.out.println(">>> Error: This field cannot be empty.");
                    continue;
                }

                return input;

            } catch (IOException e) {
                System.out.println(">>> Error: Failed to read input.");
            }
        }
    }

    /**
     * Optional string input (empty allowed)
     */
    public static String getOptionalString(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = READER.readLine();
                if (input == null) {
                    System.out.println(">>> Error: Input stream closed.");
                    continue;
                }
                return input.trim(); // empty is allowed
            } catch (IOException e) {
                System.out.println(">>> Error: Failed to read input.");
            }
        }
    }

    /**
     * Constrained string input (length + regex)
     */
    public static String getValidatedString(
            String prompt,
            int minLength,
            int maxLength,
            String regex,
            String errorMessage) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = READER.readLine();
                if (input == null) {
                    System.out.println(">>> Error: Input stream closed.");
                    continue;
                }

                input = input.trim();

                if (input.length() < minLength || input.length() > maxLength) {
                    System.out.println(
                            ">>> Error: Length must be between "
                                    + minLength + " and " + maxLength + ".");
                    continue;
                }

                if (regex != null && !input.matches(regex)) {
                    System.out.println(">>> Error: " + errorMessage);
                    continue;
                }

                return input;

            } catch (IOException e) {
                System.out.println(">>> Error: Failed to read input.");
            }
        }
    }

    // Default: no bounds, not empty
    public static java.util.List<Integer> getIntList(String prompt) {
        return getIntList(
                prompt,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE,
                false);
    }

    // Ranged list (e.g. seats)
    public static java.util.List<Integer> getIntList(
            String prompt, int min, int max) {
        return getIntList(prompt, min, max, false);
    }

    // Optional list (rare but useful)
    public static java.util.List<Integer> getOptionalIntList(String prompt) {
        return getIntList(
                prompt,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE,
                true);
    }

    /**
     * Reads a comma-separated list of integers.
     * Example input: 1,2,5,7
     */
    public static java.util.List<Integer> getIntList(
            String prompt,
            int min,
            int max,
            boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);

            try {
                String input = READER.readLine();

                if (input == null) {
                    System.out.println(">>> Error: Input stream closed.");
                    continue;
                }

                input = input.trim();

                if (input.isEmpty()) {
                    if (allowEmpty) {
                        return java.util.List.of();
                    }
                    System.out.println(">>> Error: At least one number is required.");
                    continue;
                }

                String[] parts = input.split(",");
                java.util.List<Integer> numbers = new java.util.ArrayList<>();

                for (String part : parts) {
                    int value = Integer.parseInt(part.trim());

                    if (value < min || value > max) {
                        System.out.println(
                                ">>> Error: Numbers must be between "
                                        + min + " and " + max + ".");
                        numbers.clear();
                        break;
                    }

                    numbers.add(value);
                }

                if (!numbers.isEmpty()) {
                    return numbers;
                }

            } catch (NumberFormatException e) {
                System.out.println(
                        ">>> Error: Enter numbers separated by commas (e.g. 1,2,3).");
            } catch (IOException e) {
                System.out.println(">>> Error: Failed to read input.");
            }
        }
    }

}
