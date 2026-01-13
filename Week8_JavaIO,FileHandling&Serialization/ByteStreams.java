
/*
whats a fileInputStream?
The FileInputStream class in Java is used to read data from a file in the form of bytes.
 It’s ideal for reading binary data such as images or audio files. 
 For reading text files, it’s better to use FileReader.
Direct Access: It directly reads the file content from the disk without buffering
Platform Independent: It can work on any operating system 

whats a fileOutput Stream?
The FileOutputStream class in Java is used to write data to a file in the form of bytes. 
It is ideal for writing binary data, such as images, audio, or video files.
For writing character data, We should use FileWriter instead.
Byte-Oriented Stream: Writes data byte by byte.
Direct Access: Writes data directly to the disk without buffering.
Platform Independent: Works across all operating systems.
*/
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;

public class ByteStreams {
    public static void main(String[] args) {
        System.out.print("were implementing the Byte stream for big data like images");
    }
}

public class fileInputStreamClass {

    public static void main(String[] args) {

        // Use try-with-resources to automatically close the
        // stream
        try (FileInputStream fi = new FileInputStream("file1.txt")) {

            // Display file channel information
            System.out.println("Channel: "
                    + fi.getChannel());

            // Display file descriptor
            System.out.println("File Descriptor: "
                    + fi.getFD());

            // Show available bytes in the stream
            System.out.println("Number of remaining bytes: "
                    + fi.available());

            // Skip first few bytes
            fi.skip(4);

            System.out.println("File Contents:");

            // Read and print file content
            int ch;
            while ((ch = fi.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (FileNotFoundException e) {
            System.out.println(
                    "File not found: Ensure 'file1.txt' exists in the working directory.");
        } catch (IOException e) {
            System.out.println(
                    "An error occurred while reading the file: "
                            + e.getMessage());
        }
    }
}

class FileOutputStreamClass {
    public static void main(String[] args) {
        String data = "Hello, World!";

        try (FileOutputStream fos = new FileOutputStream("output.txt")) {

            // Convert the string into bytes
            byte[] dataBytes = data.getBytes();

            // Write the bytes to the file
            fos.write(dataBytes);

            System.out.println("Data successfully written to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}