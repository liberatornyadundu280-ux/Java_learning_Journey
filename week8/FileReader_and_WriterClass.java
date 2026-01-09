import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileReader_and_WriterClass {
    public static void main(String[] args) {
        try (FileReader f = new FileReader("myfile.txt")) {
            // i would like to read the contents if the file character by charcter hence i
            // will use the read() method
            int i = 0;
            for (; i != -1;) {
                i = f.read();
                if ((char) i == '\n') {
                    System.out.println();
                }
                System.out.print((char) i);
            }
            // we can also read lines by line using the array method
            System.out.println("\n" + f.read(new char[10]));
            f.close();
        } catch (IOException e) {
            System.err.println(e);
        }
        try (FileWriter add = new FileWriter("myfile.txt", true)) {
            add.write("\"Hello pips!\\n" + //
                    "This is about Programming\"");
            System.out.println("please enter something to ryt in you file");
            Scanner sc = new Scanner(System.in);
            add.write(sc.nextLine());
            sc.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
