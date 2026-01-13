public class hello {
    
    public static void main(String[] args) {
        System.out.println("hello world");

        // now involving the variables 
        int num1=0;
        int num2=10;
        int res=num1+78 + num2%20;
        System.out.println(res); // this automatically print a new line after printing the contents 
    }
}
/*thing to know about java it does not contain the main class like the the c and c++
 *yet it is no dynamically tyoed ie its not like python 
 *the explanation of the code 
 the public class need to have the same name as the name of the file 
 other wise the code wont run 
 * most crucial part is that java works with class unlike c which works with functions 
 * it also has 1--> instances/ non static members and methods 
 *             2--> stactic members and methods 
 * **there have the same meaning in java also ie 
 * Static members are only called by a class and there can not have an object 
 * -->ie there can noit by called bby object of a class 
 * -->there are also defined by the static key word where as the instances are not
 * 
 * as you can see i have implemented comments there are just like in c++ 
 */