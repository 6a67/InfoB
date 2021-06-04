import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntegerArrayWrapper {

    Integer[] integerArray;
    String name;

    FileOutputStream fos = new FileOutputStream(name);
    FileInputStream fis = new FileInputStream(name);

    public IntegerArrayWrapper(Integer[] inputArray, String name) throws IOException {
        integerArray = inputArray;
        this.name = name;
        export();
    }

    public IntegerArrayWrapper(String name) throws IOException, ClassNotFoundException {
        this.name = name;
        importFile();
    }


    private void export() throws IOException {
        FileOutputStream fos = new FileOutputStream(name);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    private void importFile() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(name);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Integer[] integerArray = (Integer[]) ois.readObject();
    }

    public void set(Integer o, int index) {
        integerArray[index] = o;
    }

    public Integer getElement(int index) {
        return integerArray[index];
    }

    public Integer[] changeSize(Integer[] inputArray, int size) {
        return Arrays.copyOf(inputArray, size);
    }

    public Iterator<Integer> iterator(Integer inputArray[]) {
        return new Iterator<Integer>() {
            int pos = 0;
            int modCount = 0;

            @Override
            public boolean hasNext() {
                return pos != inputArray.length - 1;
            }

            @Override
            public Integer next() {
                if(!hasNext()) throw new NoSuchElementException();
                return inputArray[pos++];
            }
        };
    }

    /*public int length() {

    }

    public void close() {

    }*/

}
