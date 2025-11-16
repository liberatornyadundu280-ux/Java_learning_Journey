public class java_loops_input_output_stringtypecasting {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // loops in java 
        // for loop 
        for(int i=0;i<5;i++) {
            System.out.println("the value of i is "+i);
        }
        int[] arr={10,20,30,40,50};
        int i=1;
        // for each loop
        for(int val:arr) {
            System.out.println("the "+ (i++) + "value from the array is "+val);
        }
        // while loop 
        int j=0;
        while(j<5) {
            System.out.println("the value of j is "+j);
            j++;
        }
        // do while loop 
        int k=0;
        do {
            System.out.println("the value of k is "+k);
            k++;
        }while(k<5);

        // taking input from the user 
        // we have to import the scanner class from the util package 
        java.util.Scanner sc=new java.util.Scanner(System.in);
        System.out.println("enter your name ");
        String name=sc.nextLine();
        System.out.println("hello "+name);

        // string type casting 
        System.out.println("enter a number ");
        String numStr=sc.nextLine();
        // converting string to integer 
        int num=Integer.parseInt(numStr);
        System.out.println("the number you entered is "+num);
        int sum=num+10;
        String number=String.valueOf(sum);
        System.out.println("the sum after adding 10 and typecasting is"+number);
        sc.close(); // closing the scanner object to prevent memory leaks
    }
}
