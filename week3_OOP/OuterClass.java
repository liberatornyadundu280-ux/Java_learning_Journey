public class OuterClass {
    private int outerVar;
    
    public OuterClass(int var) {
        this.outerVar = var;
    }
    
    public void outerMethod() {
        System.out.println("This is an outer method");
    }
    
    // Member inner class
    public class InnerClass {
        private int innerVar;
        
        public InnerClass(int var) {
            this.innerVar = var;
        }
        
        public void innerMethod() {
            System.out.println("This is an inner method");
            System.out.println("Inner variable: " + innerVar);
        }
        
        public void accessOuterVar() {
            System.out.println("Outer variable from inner class: " + outerVar);
        }
    }
    
    public static void main(String[] args) {
        // Create outer class instance
        OuterClass outer = new OuterClass(10);
        
        // Create inner class instance
        OuterClass.InnerClass inner = outer.new InnerClass(20);
        
        // Call inner class methods
        inner.innerMethod();
        inner.accessOuterVar();
    }
}