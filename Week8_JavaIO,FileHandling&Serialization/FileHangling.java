/*
what are files?
In Java, file handling means working with files like creating them, reading data, writing data or deleting them.
 It helps a program save and use information permanently on the computer.
why files?
To store data permanently instead of keeping it only in memory.
To read and write data from/to files for later use.
To share data between different programs or systems.
To organize and manage large data efficiently.
How are they used ?
too use a file class u need to have the file calls imported in you program
all operations done on a file depend on the File class
To get the working of files u also need to get the understanding of i/o streams of files
there are basicall two of them that are a must to know 
1 The character i/o stream which will the one which will be using mainly in my projects 
--> why ?
    because it used to deal with up to 16 bits of data meaning it can deal with unicodes and all the languages in the world
    character_streams
    Character Stream
    The two main abstract classes for character streams are:
    Reader: Base class for all character-based input streams (reading).
    Writer: Base class for all character-based output streams (writing).
    Since abstract classes cannot be used directly, we use their implementation classes to perform actual I/O operations.
    FileReader: reads characters from a file.
    FileWriter: writes characters to a file.
    BufferedReader: reads text efficiently using buffering; also provides readLine() for reading lines.
    BufferedWriter: writes text efficiently using buffering.
    StringReader: reads characters from a string.
    StringWriter: writes characters into a string buffer.
2 The byte i/o stream
    these are used for reading not text data like images videos audios and many more 
    The two main abstract classes for byte streams are:
    InputStream: for reading data (input)
    OutputStream: for writing data (output)
    Since abstract classes cannot be used directly, we use their implementation classes to perform actual I/O operations.
    FileInputStream: reads raw bytes from a file.
    FileOutputStream: writes raw bytes to a file.
    BufferedInputStream / BufferedOutputStream: use buffering for faster performance.
    ByteArrayInputStream: reads data from a byte array as if it were an input stream.
    ByteArrayOutputStream: writes data into a byte array, which grows automatically.*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHangling {
    public static void main(String[] args) {
        File file = new File("myfile.txt");

        try {
            // Create file if it does not exist
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
            /*
             * we can also use .exist() method to check is a vile exists and it does not
             * automatically create a new file
             */
            // Write to the file (auto-closed)
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("This is a file I created using Java File I/O.");
            }

        } catch (IOException e) {
            System.out.println("An error occurred while handling the file.");
            e.printStackTrace();
        }
    }

    public class DeleteFile {
        public static void main(String[] args) {
            File Obj = new File("myfile.txt");

            // Deleting File
            if (Obj.delete()) {
                System.out.println("The deleted file is : " + Obj.getName());
            } else {
                System.out.println(
                        "Failed in deleting the file.");
            }
        }
    }

    public class ReadFile {
        public static void main(String[] args) {
            // Reading File
            try {
                File Obj = new File("myfile.txt");
                Scanner Reader = new Scanner(Obj);

                // Traversing File Data
                while (Reader.hasNextLine()) {
                    String data = Reader.nextLine();
                    System.out.println(data);
                }

                Reader.close();
            }

            // Exception Cases
            catch (FileNotFoundException e) {
                System.out.println("An error has occurred.");
                e.printStackTrace();
            }
        }
    }
}