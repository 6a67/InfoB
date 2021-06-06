import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

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
            //e.printStackTrace();
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
            testing_meth.totest_normal.getIntAtPos(7);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error occurred during getting and setting of normal instance");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            //e.printStackTrace();
            System.err.println("ArrayIndexOutOfBoundsException for out of bounds element");
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
        catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        TestPersistentesArray testing_meth3 = new TestPersistentesArray("Baby");

        try {
            System.out.println("Length of file from testing_meth should be "
                    + int_array.length + ": " + testing_meth.totest_normal.getLength());
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

        try {
            testing_meth.totest_normal.setIntAtPos(2, 5);
        }
        catch (IOException e) {
            System.err.println("IOException: File was closed, so no operation can be made on this object");
        }

        System.out.println("All tests done");

        File test_file = new File(name);
        test_file.delete();
        try {
            testing_meth.totest_normal.setIntAtPos(2, 6);
        }
        catch (IOException e) {
            System.err.println("File no longer exists");
        }

        try{
            RandomAccessFile random_file = new RandomAccessFile(name, "rws");
            random_file.writeChars("Aawdjkawldjkalodjaopdjadfaso[fas;dfkcacfk");
            Integer x = testing_meth.totest_normal.getIntAtPos(5);
        }
        catch (IOException e) {
            System.err.println("IOException for invalid reading of different type saved in file");
        }
    }
}
