import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * This class is a representation of a persistent Integer array
 */
public class PersistentesArray {
    private RandomAccessFile file;

    /**
     * @param file_name Name of the file
     * @param int_array The array to be saved in the file
     * @throws IOException If an exception occurs
     */
    public PersistentesArray(String file_name, Integer[] int_array) throws IOException {
        file = new RandomAccessFile(file_name, "rws");

        for (Integer i : int_array) {
            file.writeInt(i);
        }
    }

    /**
     * @param file_name Name of the file
     * @throws IOException if an exception occurs
     */
    public PersistentesArray(String file_name) throws IOException {
        File test_file = new File(file_name);
        if (!test_file.exists()) {
            throw new FileNotFoundException();
        }
        file = new RandomAccessFile(file_name, "rws");
    }

    /**
     * @param pos Position of the Integer to be changed
     * @param value New Integer to be placed at <code>pos</code>
     * @throws IOException if an exception occurs
     */
    public void setIntAtPos (Integer pos, Integer value) throws IOException {
        if (pos < 0 || pos >= file.length()) {
            throw new ArrayIndexOutOfBoundsException(pos);
        }
        file.seek(pos * 4);
        file.writeInt(value);
    }

    /**
     * @param pos Position of the Integer to be returned
     * @return Integer at <code>pos</code>
     * @throws IOException if an exception occurs
     */
    public Integer getIntAtPos (Integer pos) throws IOException {
        if (pos < 0 || pos >= getLength()) {
            throw new ArrayIndexOutOfBoundsException(pos);
        }
        file.seek(pos * 4);
        return file.readInt();
    }

/*    public Integer[] getIntArray () throws IOException {
        file.seek(0);
        Integer[] int_array = new Integer[(int)file.length()];

            for (int i = 0; i < file.length(); i++) {
                file.seek(i);
                int_array[i] = file.readInt();
            }

        return int_array;
    }*/

    /**
     * @return length of the file
     * @throws IOException if an exception occurs
     */
    public long getLength () throws IOException {
        return file.length() / 4;
    }

    /**
     * Closes the file
     * @throws IOException if an exception occurs
     */
    public void closeFile () throws IOException {
        file.close();
    }

}
