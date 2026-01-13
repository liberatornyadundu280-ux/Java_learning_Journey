import java.util.Arrays;// importing the Arrays class from java.util package to use its methods

/* creating a user defined data type called Person */
class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }


public class arrays_and_its_methods {
    public void main(String args[])
   { 
    /*declaration of an array in java ie 1d and setting it size */
    int[] array= new int[5];
    for(int i=0;i<5;i++)
    {
        /* showing what arrray holds when just initialized */
        System.out.println("the value at index "+i+" is "+array[i]);
    }
    /*initialization of an array in java */
    int arr[] = {10, 20, 30, 40, 50};
    /*assigning an array to nother array ie copying the array */
    int arr2[] = arr;
    System.out.println("the elements of the copied array are :");
    /*determining if the arrays are equal by means of in built functions */
    System.out.println("Are the two arrays equal? " + Arrays.equals(arr, arr2));
    
     /*now calling all the methods used to see their implementation on cmdline */
    arrayMethodsDemo();
    finalArrayDemo();
    arrayOfObjectsandUserDefinedDataTypesDemo();
    multiDimensionalArrayDemo();
    jeggedArrayDemo();
}

void arrayOfObjectsandUserDefinedDataTypesDemo() {
    /*implementing array of objects/ user defined data types */
    Person people[] = {new Person("Alice", 30), new Person("Bob", 25)};
    System.out.println("the elements of the object array are :");
    for(Person p : people)
    {
        System.out.println("Name: " + p.name + ", Age: " + p.age);
    }
    /*also created arrays using predefined data types */
    /*below im going to implemenet differnt array of differnet data types  */
    String strArr[] = {"apple", "banana", "cherry"};
    System.out.println("the elements of the string array are :");
    for(String fruit : strArr)
    {
        System.out.print(fruit+" ");
    }
    System.out.println();
    double doubleArr[] = {1.1, 2.2, 3.3};
    System.out.println("the elements of the double array are :");       
    for(double num : doubleArr)
    {
        System.out.print(num+" ");  
    }
    System.out.println();
    /*using in built function to sort an array */
    int arr[] = {10, 20, 30, 40, 50};
    Arrays.sort(arr);
    System.out.println("the sorted elements of the integer array are :");
    for(int val : arr)
    {   
        System.out.print(val+" ");
    }
    System.out.println();
 }

void multiDimensionalArrayDemo() {
    /*implementing a 2D array in java */
    int multiArr[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    System.out.println("the elements of the 2D array are :");
    for(int i=0;i<multiArr.length;i++)
    {
        for(int j=0;j<multiArr[i].length;j++)  
        {
            System.out.print(multiArr[i][j]+" ");    
        }
    }
    System.out.println();
    /*implementing a 3D array in java */
    int threeDArr[][][] = {{{1, 2}, {3, 4}}, {{5, 6}, {7, 8}}};
    System.out.println("the elements of the 3D array are :");
    for(int i=0;i<threeDArr.length;i++)
    {
        for(int j=0;j<threeDArr[i].length;j++)  
        {
            for(int k=0;k<threeDArr[i][j].length;k++)  
            {
                System.out.print(threeDArr[i][j][k]+" ");   
            }
        }
    }
    System.out.println();
    /*you can implement n dimensional arrays in java but its not common practice as it becomes complex to manage */
    /* i will update when i really understand what happens under the wood of 3d im still not good at it  */

 }

 void jeggedArrayDemo() {

    /*implementing jagged array in java  which has different columns*/
    int jaggedArr[][] = {{1, 2}, {3, 4, 5}, {6}};
    System.out.println("the elements of the jagged array are :");
    for(int i=0;i<jaggedArr.length;i++)
    {
        for(int j=0;j<jaggedArr[i].length;j++)  
        {
            System.out.print(jaggedArr[i][j]+" ");    
        }
    }
    System.out.println();

    /*creatinga jegged array which takes diomensions from user through cmd */
    int rows = 3;
    int jaggedArr2[][] = new int[rows][];
    jaggedArr2[0] = new int[2];
    jaggedArr2[1] = new int[3];
    jaggedArr2[2] = new int[1];
    System.out.println("the elements of the jagged array 2 are :");
    for(int i=0;i<jaggedArr2.length;i++)
    {  
        for(int j=0;j<jaggedArr2[i].length;j++)  
        {
            jaggedArr2[i][j] = i + j; // assigning some values
            System.out.print(jaggedArr2[i][j]+" ");    
        }
    }
    System.out.println();
 }

 /*
 **** most of the predefined funcions in java are mainly applicable to arrays of primitive data typescls
 */

    /*below is a funtion containg kost used and fundermentals methods of functions in java
     * most developers use these methods in day to day programming
     * 1. Arrays.toString() - Converts an array to a string representation.
     * 2. Arrays.sort() - Sorts the elements of an array in ascending order.
     * 3. Arrays.copyOf() - Creates a new array by copying elements from an existing array.
     * 4. Arrays.equals() - Compares two arrays for equality.
     * 5. Arrays.fill() - Fills an array with a specified value.
     *6. Arrays.binarySearch() - Searches for a specific value in a sorted array.
     *7. Arrays.asList() - Converts an array to a fixed-size list.
     *8. Arrays.stream() - Creates a stream from an array for functional-style operations.
     *9. Arrays.deepToString() - Converts a multi-dimensional array to a string representation.
     *10. Arrays.parallelSort() - Sorts an array in parallel for improved performance on large datasets.
     *11. Arrays.setAll() - Sets all elements of an array using a specified function.
     *12. Arrays.spliterator() - Creates a spliterator for traversing and partitioning elements of an array.
     *13. Arrays.compare() - Compares two arrays lexicographically.
     *14. Arrays.mismatch() - Finds the first index where two arrays differ.
     *15. Arrays.parallelPrefix() - Computes the prefix sum of an array in parallel.
     *16. Arrays.copyOfRange() - Creates a new array by copying a range of elements from an existing array.
     *17. Arrays.setAll() - Sets all elements of an array using a specified function.
     *18. Arrays.spliterator() - Creates a spliterator for traversing and partitioning elements of an array.
     *19. Arrays.compare() - Compares two arrays lexicographically.
     *20. Arrays.mismatch() - Finds the first index
        * where two arrays differ.
     *21. Arrays.length() - Returns the length of the array.
     */
    void arrayMethodsDemo() {
        int[] sampleArray = {5, 2, 8, 1, 3};

        // 1. Arrays.toString()
        System.out.println("Array as String: " + Arrays.toString(sampleArray));

        // 2. Arrays.sort()
        Arrays.sort(sampleArray);
        System.out.println("Sorted Array: " + Arrays.toString(sampleArray));

        // 3. Arrays.copyOf()
        int[] copiedArray = Arrays.copyOf(sampleArray, sampleArray.length);
        System.out.println("Copied Array: " + Arrays.toString(copiedArray));

        // 4. Arrays.equals()
        System.out.println("Arrays are equal: " + Arrays.equals(sampleArray, copiedArray));

        // 5. Arrays.fill()
        int[] filledArray = new int[5];
        Arrays.fill(filledArray, 7);
        System.out.println("Filled Array: " + Arrays.toString(filledArray));    
        /*other methods will be applied in projects if needed */
    }

    /*the final array which are arrays that can not be
     * assigned to another array ie constant arrays
     * but their values can be changed 
     * final int finalArray[]= {1,2,3,4,5};
     * finalArray[0]=10; // valid
     * finalArray=new int[]{6,7,8,9,10}; // invalid
     * they keep the same structure but their values can be changed
     * you can change all the value one at a time but cant assign it to another array
     */
    /*implementation of the final array */
    public void finalArrayDemo() {
        final int finalArray[] = {1, 2, 3, 4, 5};
        // Changing values of the final array
        for (int i = 0; i < finalArray.length; i++) {
            finalArray[i] = finalArray[i] * 2; // valid
        }
        System.out.println("Final Array after modification: " + Arrays.toString(finalArray));

        // Attempting to reassign the final array (uncommenting the below line will cause a compilation error)
        // finalArray = new int[]{6, 7, 8, 9, 10}; // invalid
    }
}
