package utils;

/**
 * A professional utility class for validating numeric families.
 * Being static and generic, it works for Integer, Double, Float, etc.,
 * without needing to instantiate the class.
 */
public class NumberValidator {

    /**
     * Checks if a number is greater than zero.
     * Use this for Price and Quantity.

        // 
        // 
    public static <T extends Number> boolean isPositive(T input) {
        if (input == null) return false;
        // All Number subclasses can be compared via doubleValue()
        return input.doubleValue() > 0;
    }

    /**
     * Checks if a number is zero or greater.
     * Use this if '0' is a valid stock level.
     */
    public static <T extends Number> boolean isNonNegative(T input) {
        if (input == null) return false;
        return input.doubleValue() >= 0;
    }

    /**
     * Checks if a number falls between a specific range (inclusive).
     */
    public static <T extends Number> boolean isWithinRange(T input, double min, double max) {
        if (input == null) return false;
        double value = input.doubleValue();
        return value >= min && value <= max;
    }

    /**
     * Validates if a String input can actually be parsed into a Number.
     * Helps prevent the "InputMismatchException" during Scanner use.
     */
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) return false;
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}