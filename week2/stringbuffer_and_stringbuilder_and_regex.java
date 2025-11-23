public class stringbuffer_and_stringbuilder_and_regex {
    public static void main(String[] args) {
        // Using StringBuffer
        StringBuffer stringBuffer = new StringBuffer("Hello");
        stringBuffer.append(", World!");
        System.out.println("StringBuffer: " + stringBuffer.toString());

        // Using StringBuilder
        StringBuilder stringBuilder = new StringBuilder("Hello");
        stringBuilder.append(", Java!");
        System.out.println("StringBuilder: " + stringBuilder.toString());

        // Using Regular Expressions
        String text = "The rain in Spain stays mainly in the plain.";
        String regex = "ain";
        String replacedText = text.replaceAll(regex, "XXX");
        System.out.println("Original Text: " + text);
        System.out.println("Replaced Text: " + replacedText);
        
    }
}
