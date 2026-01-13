import java.io.*;
/*
* when dealing with an interface all the classes that implement an interface should 
* implement all it methods 
* static methods are accessed using the interface name only 
*private methods can only called inside default or static methods 

**** Properties of interfaces 
    * they have default methods
    * they have static  methods
    * they have abstract mehtods---->"no implementation"
    * when writing code you do not need to state if it it static final the compiler automatically des that for you 
    * they  allow multiple inheritance unlike classes 
    * all of it members are public and default


*** an interface can not have private or protected method and can not have a member 
 */
// Interface Declared
interface testInterface {
  
    // public, static and final
    final int a = 10;

    // public and abstract
    void display();

    // static default method of an interface
    static void demo()//called directly using the interface name 
    {
        System.out.println("I'm static");
    }

    // dafault method in interfaces
    default void demo_default(){
        System.out.println("I'm   default method");
    }

}

// Class implementing interface
class TestClass implements testInterface {
  
    // Implementing the capabilities of Interface
    public void display(){ 
      System.out.println("Geek"); 
    }
}

class interfaces{
    
    public static void main(String[] args){
        
        TestClass t = new TestClass();
        t.display();
        System.out.println(t.a);
    }
}