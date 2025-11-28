class Demo2 {
    int a;
    String name;
    float f;
    void display(){
        System.out.println("since no constructor was made java automatically creates onr for us");
        System.out.println("Value of a: " + a+", name: "+name+", f: "+f);
    }
}

// Once you make a constructor java will not create a default constructor for you
class Demo1{
    int a;
    String name;
    float f;
    //parameterized constructor
    Demo1(int a, String name, float f){
        this.a=a; // 'this.a' refers to the instance variable, 'a' refers to the constructor parameter
        this.name=name; // 'this.name' refers to the instance   
        this.f=f; // 'this.f' refers to the instance variable, 'f' refers to the constructor parameter
    }
    void display(){
        System.out.println("Value of a: " + a+", name: "+name+", f: "+f);
    }
}
class Demo{
    int a;
    String name;
    float f;

    //default constructor
    /*
    *this class is called default constructor because it does not have any parameter.
    *It is used to initialize the instance variables with default values.
    * it is called automatically when the object is created in the main class or other classes
    */
    Demo(){
        a=10;
        name="Hello";
        f=12.5f;
    }

    //parameterized constructor
    /*
    *this class is called parameterized constructor because it has parameters.
    *It is used to initialize the instance variables with user-defined values.
    */
   Demo(int a, String name, float f){
        this.a=a; // 'this.a' refers to the instance variable, 'a' refers to the constructor parameter
        this.name=name; // 'this.name' refers to the instance variable, 'name' refers to the constructor parameter
        this.f=f; // 'this.f' refers to the instance variable, 'f' refers to the constructor parameter
   }

   // copy constructor
    /*
    *this class is called copy constructor because it has an object of the same class as a parameter.
    *It is used to initialize the instance variables with the values of another object.
    * unlike in c++ we can not automatically create it so we just pass the object as a parameter
    */
   Demo(Demo obj){
    this.a=obj.a;
    this.name=obj.name;
    this.f=obj.f;
   }

    void display(){
          System.out.println("Value of a: " + a+", name: "+name+", f: "+f);
    }
}

public class javaClassesandObjects_ThisKeyword {

    public static void main(String[] args) {

        // ways of creating an object in java
        Demo d; // declaration
        d = new Demo(); // instantiation

        // Demo class with default constructor
        Demo d1 = new Demo();
        d1.display();

        //Demo class with parameterized constructor
        Demo d2 = new Demo(20, "World", 25.5f);
        d2.display();
        //Demo class with copy constructor
        Demo d3 = new Demo(d2);
        d3.display();

        //this will result in an error since we did not create a ddefault constructor
        // so java wont create one
        /*
        Demo1 d4 = new Demo1();
        d4.display();  
         */

        //Demo1 class with parameterized constructor
        Demo1 d5 = new Demo1(30, "Java", 35.5f);
        d5.display();

        //Demo2 class without any constructor
        Demo2 d6 = new Demo2();
        d6.display();
    }
}