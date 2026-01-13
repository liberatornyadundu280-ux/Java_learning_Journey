
/*
****the reader class is used to reade data from a file 
 it can ce fileReader or bufferedReader 
 The Reader class in Java is an abstract class for reading character streams. Its subclasses (FileReader, BufferedReader) provide implementations, with read() being the main method to read characters.
It implements the Readable interface that defines the read(CharBuffer cb) method.
It implements the Closeable interface that defines the close() method to release resources.
u can also perform read by character where u will be reading the contents of a file character by character 
*** the writer class 
thie writer class is used to write data into a file 
what do we mean by write dat into a file ?
this is same as when u are entering your notes to your note pad 
this is the input part of files and it contains 
bufferedWriter class which extends the writer class which is the main class
the most efficient way of writing data to a file is using the buffered writer 
*/
import java.io.*;
import java.nio.CharBuffer;
import java.util.Arrays;

public class ReaderAndWriter {
    public static void main(String[] args)
            throws IOException {

        // Open a file reader
        Reader r = new FileReader("file.txt");
        PrintStream out = System.out;

        // Create a character array and CharBuffer
        char[] buffer = new char[10];
        CharBuffer charBuffer = CharBuffer.wrap(buffer);

        // Check if the reader supports marking
        if (r.markSupported()) {
            r.mark(100); // Mark the current position
            out.println("mark method is supported");
        }

        // Skip 5 characters in the stream
        r.skip(5);

        // Check if the stream is ready to read
        if (r.ready()) {
            // Read 10 characters into the buffer
            r.read(buffer, 0, 10);
            out.println("Buffer after reading 10 chars: "
                    + Arrays.toString(buffer));

            // Read characters into the CharBuffer
            r.read(charBuffer);
            out.println(
                    "CharBuffer contents: "
                            + Arrays.toString(charBuffer.array()));

            // Read a single character
            out.println("Next character: "
                    + (char) r.read());
        }

        // Close the reader
        r.close();
    }
}

public class GFG {
    public static void main(String[] args) {
        try (Writer writer = new BufferedWriter(
                new FileWriter("buffered.txt"))) {
            writer.write(
                    "BufferedWriter makes writing more efficient.");
            writer.write(
                    "\nIt reduces disk I/O by using a buffer.");
            System.out.println(
                    "Data written using BufferedWriter.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}