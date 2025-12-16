class Bound<T extends A> {// the sonly works for the sub classes of A otherwise it will throw an error
    private T objRef;

    public Bound(T obj) {
        this.objRef = obj;
    }

    public void doRunTest() {
        this.objRef.displayClass();
    }
}

class A {
    public void displayClass() {
        System.out.println("Inside super class A");
    }
}

class B extends A {
    public void displayClass() {
        System.out.println("Inside sub class B");
    }
}

class C extends A {
    public void displayClass() {
        System.out.println("Inside sub class C");
    }
}

public class BoundedClass {
    public static void main(String a[]) {
        Bound<C> bec = new Bound<C>(new C());
        bec.doRunTest();

        Bound<B> beb = new Bound<B>(new B());
        beb.doRunTest();

        Bound<A> bea = new Bound<A>(new A());
        bea.doRunTest();

        Bound<String> bes = new Bound<String>(new String());
        bes.doRunTest();
    }
}

// syntax for multiple bounds
// <T extends superClassName & Interface>