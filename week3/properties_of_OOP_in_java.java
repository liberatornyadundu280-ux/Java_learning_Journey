public class properties_of_OOP_in_java {
    // Abstraction: Hiding complex implementation details and showing only the necessary parts.
    public static void main(String[] args) {
        Animal myDog = new Dog();
        myDog.sound(); // Outputs: Woof
    }
}
    abstract class Animal {// this one can not have objects
        abstract void sound();
    }

    class Dog extends Animal {
        void sound() {
            System.out.println("Woof");
        }
    }
    class Cat extends Animal {
        void sound() {
            System.out.println("Meow");
        }
    }


// Encapsulation: Bundling data and methods that operate on that data within one unit (class).
class Person {
    private String name; // private variable
    private int age; // private variable
    public String getName() { // public getter
        return name;
    }
    public void setName(String name) { // public setter
        this.name = name;
    }
    public int getAge() { // public getter
        return age;
    }
    public void setAge(int age) { // public setter
        if (age > 0) {
            this.age = age;
        }
    }
}

// Inheritance: Mechanism where one class can inherit properties and methods from another class.
class Vehicle {
    void start() {
        System.out.println("Vehicle started");
    }
}
class Car extends Vehicle {
    void honk() {
        System.out.println("Car honking");
    }
}
// Polymorphism: Ability to take many forms, typically through method overriding or overloading.
class Shape {
    void draw() {
        System.out.println("Drawing a shape");
    }
}
class Circle extends Shape {
    void draw() {
        System.out.println("Drawing a circle");
    }
}
class Square extends Shape {
    void draw() {
        System.out.println("Drawing a square");
    }
}  
class TestPolymorphism {
    public static void main(String[] args) {
        Shape shape1 = new Circle();
        Shape shape2 = new Square();
        shape1.draw(); // Outputs: Drawing a circle
        shape2.draw(); // Outputs: Drawing a square
    }
}   

/*
* types of polymorphism in java
* 1. compile time polymorphism (method overloading, operator overloading)
* 2. runtime polymorphism (method overriding)

 */

//example of method overloading which is compile time polymorphism
class MathOperations {
    int add(int a, int b) {
        return a + b;
    }
    double add(double a, double b) {
        return a + b;
    }
}
// above add method is overloaded with different parameter types
class TestMathOperations {
    public static void main(String[] args) {
        MathOperations math = new MathOperations();
        System.out.println(math.add(5, 10)); // Outputs: 15
        System.out.println(math.add(5.5, 10.5)); // Outputs: 16.0
    }
}

// example of method overriding which is runtime polymorphism
class Parent {
    void show() {
        System.out.println("Parent's show method");
    }
}
class Child extends Parent {
    void show() {
        System.out.println("Child's show method");
    }
}
class TestMethodOverriding {
    public static void main(String[] args) {
        Parent obj = new Child();
        obj.show(); // Outputs: Child's show method
    }
}
