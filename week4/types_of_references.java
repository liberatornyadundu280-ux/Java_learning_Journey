/*
this code is full of errors due to memory leaks
making it a good example for references
 */



public class types_of_references {
    public static void main(String[] args)
    {System.out.println("This program shows the typpes of references and how gabbage collection is done in them");
}}

// Java program to illustrate Strong reference
public class Example
{
    public static void main(String[] args)
    {
         //Strong Reference - by default
        Geeks g = new Geeks();    
        
        //Now, object to which 'g' was pointing earlier is 
        //eligible for garbage collection.
        g = null; 
    }
}


//Java Code to illustrate Weak reference
import java.lang.ref.WeakReference;
class Gfg
{
    //code
    public void x()
    {
        System.out.println("GeeksforGeeks");
    }
}

public class Geeks
{
    public static void main(String[] args)
    {
        // Strong Reference
        Gfg g = new Gfg();     
        g.x();
        
        // Creating Weak Reference to Gfg-type object to which 'g' 
        // is also pointing.
        WeakReference<Gfg> weakref = new WeakReference<Gfg>(g);
        
        //Now, Gfg-type object to which 'g' was pointing earlier
        //is available for garbage collection.
        //But, it will be garbage collected only when JVM needs memory.
        g = null; 
        
        // You can retrieve back the object which
        // has been weakly referenced.
        // It successfully calls the method.
        g = weakref.get(); 
        
        g.x();
    }
}

//Code to illustrate Soft reference
import java.lang.ref.SoftReference;
class Gfg
{
    public void x()
    {
        System.out.println("GeeksforGeeks");
    }
}

public class Geeks
{
    public static void main(String[] args)
    {
        // Strong Reference
        Gfg g = new Gfg();     
        g.x();
        
        // Creating Soft Reference to Gfg-type object to which 'g' 
        // is also pointing.
        SoftReference<Gfg> softref = new SoftReference<Gfg>(g);
        
        // Now, Gfg-type object to which 'g' was pointing
        // earlier is available for garbage collection.
        g = null; 
        
        // You can retrieve back the object which
        // has been weakly referenced.
        // It successfully calls the method.
        g = softref.get(); 
        
        g.x();
    }
}


//Code to illustrate Phantom reference
import java.lang.ref.*;

class Gfg
{
    //code
    public void x()
    {
        System.out.println("GeeksforGeeks");
    }
}

public class Geeks
{
    public static void main(String[] args)
    {
        //Strong Reference
        Gfg g = new Gfg();     
        g.x();
        
        //Creating reference queue
        ReferenceQueue<Gfg> refQueue = new ReferenceQueue<Gfg>();

        //Creating Phantom Reference to Gfg-type object to which 'g' 
        //is also pointing.
        PhantomReference<Gfg> phantomRef = null;
        
        phantomRef = new PhantomReference<Gfg>(g,refQueue);
        
        //Now, Gfg-type object to which 'g' was pointing
        //earlier is available for garbage collection.
        //But, this object is kept in 'refQueue' before 
        //removing it from the memory.
        g = null; 
        
        //It always returns null. 
        g = phantomRef.get(); 
        
        //It shows NullPointerException.
        g.x();
    }
}