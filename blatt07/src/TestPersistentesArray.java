import java.io.IOException;

/**
 *
 */
public class TestPersistentesArray {
    static Integer[] int_array  = {0, 1, 2, 3, 4, 5, 6};
    PersistentesArray totest_normal;
    PersistentesArray totest_justname;
    static String name = "TestFile";

    /**
     * New instance of this class is generated, on which the testAll method is called
     */
    public static void main (String[] args){
        TestPersistentesArray test = new TestPersistentesArray(name, int_array);
        test.testAllMethods();
    }

    /**
     * Constructor, creates a new instance of PersistentesArray
     * @param name Name of the file
     * @param array The array to be saved in the file
     */
    public TestPersistentesArray(String name, Integer[] array) {
        try {
            totest_normal = new PersistentesArray(name, array);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor, creates a new instance of PersistentesArray with only the corresponding file name
     * of an already existing file
     * @param name Name of the file
     */
    public TestPersistentesArray(String name) {
        try {
            totest_justname = new PersistentesArray(name);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.err.println("No such file exists");
        }
    }

    /**
     * Tests all methods of the PersistentesArray class
     */
    private void testAllMethods () {
        System.out.println("!Testing instance with name and array!");
        TestPersistentesArray testing_meth = new TestPersistentesArray(name, int_array);
        try {
            Integer x = testing_meth.totest_normal.getIntAtPos(2);
            System.out.println("Value of getIntAtPos(2) should be 2: " + x);
            testing_meth.totest_normal.setIntAtPos(2, 4);
            x = testing_meth.totest_normal.getIntAtPos(2);
            System.out.println("Value of getIntAtPos(2) should be 4 after change with " +
                    "setIntAtPos: " + x);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error occurred during getting and setting of normal instance");
        }

        try {
            System.out.println("!Testing instance with only name!");
            TestPersistentesArray testing_meth2 = new TestPersistentesArray(name);
            Integer y = testing_meth2.totest_justname.getIntAtPos(3);
            System.out.println("Value of getIntAtPos(3) should be 3: " + y);
            testing_meth2.totest_justname.setIntAtPos(3, 9);
            y = testing_meth2.totest_justname.getIntAtPos(3);
            System.out.println("Value of getIntAtPos(3) should be 9 after change with " +
                    "setIntAtPos: " + y);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error occurred during getting and setting of justname instance");
        }


        try {
            System.out.println("Length of file from testing_meth should be "
                    + int_array.length * 4 + ": " + testing_meth.totest_normal.getLength());
        }
        catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error occurred while getting length of file");
        }

        System.out.println("Testing closing of file");
        try {
            testing_meth.totest_normal.closeFile();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to close file");
        }

        System.out.println("All tests done");


    }
}
