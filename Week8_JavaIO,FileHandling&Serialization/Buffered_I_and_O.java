import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// this one overrides the current content so if you use it you lose you previous data
public class Buffered_I_and_O {
    public static void main(String[] args) {
        try (BufferedReader bf = new BufferedReader(new FileReader("myfile.txt"))) {
            String line;
            while ((line = bf.readLine()) != null)
                System.out.println(line);
            bf.close();
        } catch (IOException e) {
            System.err.println("error occurred: " + e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("myfile.txt"))) {
            writer.write("Hello, World!");
            writer.newLine();
            writer.write(
                    "This is written using BufferedWriter.");

            writer.close();
            System.out.println(
                    "File written successfully.");
        } catch (IOException e) {
            System.err.println("error occurred: " + e);
        }
    }
}