public class strings_and_its_methods {
    public static void main(String[] args) {
        String str = "Hello, World!";
        
        // Length of the string
        int length = str.length();
        System.out.println("Length: " + length);
        
        // Convert to uppercase
        String upperStr = str.toUpperCase();
        System.out.println("Uppercase: " + upperStr);
        
        // Convert to lowercase
        String lowerStr = str.toLowerCase();
        System.out.println("Lowercase: " + lowerStr);
        
        // Substring
        String subStr = str.substring(7, 12);
        System.out.println("Substring: " + subStr);
        
        // Replace characters
        String replacedStr = str.replace("World", "Java");
        System.out.println("Replaced String: " + replacedStr);
        
        // Trim whitespace
        String strWithSpaces = "   Hello, World!   ";
        String trimmedStr = strWithSpaces.trim();
        System.out.println("Trimmed String: '" + trimmedStr + "'");
        
        // Check if string contains a substring
        boolean contains = str.contains("World");
        System.out.println("Contains 'World': " + contains);
        
        // Split string
        String[] splitStr = str.split(", ");
        System.out.println("Split String: ");
        for (String s : splitStr) {
            System.out.println(s);
        }
        //find the reversal of a string 
        String original = "Hello";
        String reversed = "";
        for(int i = original.length() - 1; i >= 0; i--) {
            reversed += original.charAt(i);     
    }
        System.out.println("Reversed String: " + reversed);
        //now implementing a method to check if a string is a palindrome suing an inbuilt method
        String palindrome = "madam";
        String reversedPalindrome = new StringBuilder(palindrome).reverse().toString();
        if(palindrome.equals(reversedPalindrome)) {
            System.out.println(palindrome + " is a palindrome.");
        } else {
            System.out.println(palindrome + " is not a palindrome.");
        }
        // converting a string's first letter to uppercase
        String lowerCaseStr = "hello";
        String capitalizedStr = lowerCaseStr.substring(0, 1).toUpperCase() + lowerCaseStr.substring(1);
        System.out.println("Capitalized String: " + capitalizedStr);
        System.out.println(lowerCaseStr.substring(0,1).toUpperCase()+lowerCaseStr.substring(1));

        //checking if a string is panagram ie contains all characters of alphabet
        String panagram = "The quick brown fox jumps over a lazy dog";
        panagram = panagram.toLowerCase().replaceAll("[^a-z]", "");// this part uses [^a-z] to remove all non-alphabetic characters
        boolean isPanagram = true;
        for(char ch = 'a'; ch <= 'z'; ch++) {
            if(!panagram.contains(String.valueOf(ch))) {
                isPanagram = false;
                break;
            }
        }
        if(isPanagram) {
            System.out.println("The string is a panagram.");
        } else {
            System.out.println("The string is not a panagram.");
        }
        // finding an extra character between two strings
        String str1 = "abcd";
        String str2 = "abcde";
        int[] charCount = new int[256]; // assuming ASCII character set
        for(char ch : str1.toCharArray()) {
            charCount[ch]++;    
        }
        for(char ch : str2.toCharArray()) {
            charCount[ch]--;    
        }
        char extraChar = '\0';
        for(int i = 0; i < charCount.length; i++) {
            if(charCount[i] < 0) {
                extraChar = (char) i;
                break;
            }
        }
        System.out.println("The extra character is: " + extraChar);
        // much simple way to find the extra character
        String s1 = "abcd";
        String s2 = "abcde";
        int result = 0;
        for(char ch : s1.toCharArray()) {
            result ^= ch;
        }
        for(char ch : s2.toCharArray()) {
            result ^= ch;
        }
        System.out.println("The extra character is: " + (char)result);
        /*how the above code really works for extra character finding
        The above code uses the XOR bitwise operation to find the extra character between two strings. Here's how it works:
        1. Initialization: An integer variable result is initialized to 0. This variable will hold the cumulative XOR result of all characters from both strings.
        2. XOR Operation on First String: The code iterates through each character in the first string (s1). For each character, it performs the XOR operation between the current value of result and the ASCII value of the character. The result is updated with this new value.
        3. XOR Operation on Second String: The code then iterates through each character in the second string (s2) and performs the same XOR operation as in step 2. This means that each character in s2 is also XORed with the current value of result.
        4. Cancellation Effect: The key property of the XOR operation is that when you XOR a number with itself, the result is 0 (i.e., a ^ a = 0). Therefore, when the same characters from both strings are XORed together, they cancel each other out. For example, if both strings contain the character 'a', the XOR operation will result in 0 for that character.
        5. Resulting Extra Character: After processing both strings, the only character that remains in the result variable is the extra character that is present in s2 but not in s1. This is because all other characters that appeared in both strings have canceled each other out to 0.
        6. Output: Finally, the code casts the resulting integer value in result back to a character and prints it as the extra character.
        This method is efficient because it only requires a single pass through both strings and uses constant space, making it optimal for this type of problem.
         */
    }
}
