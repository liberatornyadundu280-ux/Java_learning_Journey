class Parent {
    void show() {
        System.out.println("Parent's show method");
    }
}   
class Child extends Parent {
    @Override
    void show() {
        System.out.println("Child's show method");
    }
}

// concept of overriding and runtime polymorphism

public class polymorphisim {
    public static void main(String[] args) {
        Parent obj = new Child();
        obj.show(); // Outputs: Child's show method
    }
}
