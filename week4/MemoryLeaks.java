import java.util.ArrayList;
import java.util.List;

public class MemoryLeaks{
    
    public static void main(String[] args){
        
        List<byte[]> list = new ArrayList<>();
        System.out.println("Supp nigga");
        while (true) {
            // Each iteration creates a 1 MB object
            list.add(new byte[1024 * 1024]);
        }
    }
}
/*
Tools to Find Memory Leaks
There are multiple tools that help us to detect memory leaks by showing which object is using the most memory, and the list of such tools are listed below:

VisualVM (comes with JDK)
Eclipse Memory Analyzer (MAT)
Java Mission Control
YourKit Java Profiler
*/