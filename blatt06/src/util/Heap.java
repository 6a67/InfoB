package util;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Implementation of a Heap
 * @param <T> The type that the Heap should be
 */
public class Heap<T> {

    private T[] elements;
    //private int arraySize;
    private int elementsInArray;
    // Comparators generic has to be of super type of T, so that it has all of T s functions
    private Comparator<? super T> comparator;

    // There are no default args for functions in java :(
    public Heap() {
        this(null);
    }

    public Heap(Comparator<? super T> comparator) {
        // Gives the array a default size of 10
        elements = (T[]) new Object[10];
        //arraySize = elements.length;
        elementsInArray = 0;
        this.comparator = comparator;
    }

    /**
     * Increases the size of the Array elements by i
     * @param i amount to increase the array by
     */
    private void increaseArraySize(int i) {
        elements = Arrays.copyOf(elements, elements.length + i);
        //arraySize += i;
    }

    /**
     * Inserts the given object into the heap
     * @param o object to insert
     */
    public void insert(T o) {
        if(o == null) {
            // Null itself would be a valid input for most other Collection types, but a Heap
            // is build upon comparisons and it is not really possible to compare null
            throw new NullPointerException();
        }

        // If array is full, increase its size to 1.5
        if(elementsInArray >= elements.length) {
            increaseArraySize((int) (elements.length * 0.5) + 1);
        }
        elements[elementsInArray++] = o;
        siftUp();
    }

    /**
     * Deletes the first/biggest element of the heap and return it
     * @return first element of the heap
     */
    public T deleteFirst() {
        if(empty()) return null;

        T root = elements[0];
        elements[0] = elements[--elementsInArray];
        elements[elementsInArray] = null;

        if(elementsInArray > 0) {
            siftDown();
        }

        return root;

    }

    /**
     * Returns the first element without deleting it from the heap
     * @return the first element in the heap
     */
    public T getFirst() {
        return elements[0];
    }

    /**
     * Sifts the heap up
     */
    private void siftUp() {
        int i = elementsInArray - 1; // Index of the last element
        while(i > 0) {
            int p = (i - 1) / 2; // Index of the parent

            T element = elements[i];
            T parent = elements[p];

            if(compareElements(element, parent) < 0) {
                // swap
                swap(i, p);

                // move the the next level
                i = p;
            } else {
                break;
            }
        }
    }

    /**
     * Sifts the heap down
     */
    private void siftDown() {
        int i = 0; // Index of the first element
        int leftChildIndex = 2 * i + 1;
        while(leftChildIndex < elementsInArray) {
            int rightChildIndex = leftChildIndex + 1;
            int biggestChildIndex = leftChildIndex;

            // Compare left child to right child
            if(rightChildIndex < elementsInArray) { // Checks if a right child exists
                T rightChild = elements[rightChildIndex];
                if(compareElements(rightChild, elements[leftChildIndex]) < 0) {
                    biggestChildIndex = rightChildIndex;
                }
            }

            // Check if the biggest child is bigger than the node
            T parent = elements[i];
            if(compareElements(parent, elements[biggestChildIndex]) > 0) {
                swap(biggestChildIndex, i);

                // move the the next level
                i = biggestChildIndex;
                leftChildIndex = 2 * i + 1;
            } else {
                break;
            }
        }
    }


    /**
     * Swaps the position of two elements in the array by their index
     * @param i index of the first element
     * @param j index of the second element
     */
    private void swap(int i, int j) {
        T tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;
    }

    /**
     * Compares two elements with compareTo or a comparator
     * @param a first element
     * @param b seconds element
     * @return result of the comparison
     */
    private int compareElements(T a, T b) {
        if(comparator == null) {
            return ((Comparable<T>)a).compareTo(b);
        } else {
            return comparator.compare(a, b);
        }
    }

    /**
     * Checks if there are no elements in the heap
     * @return true if there are no elements in the heap, false if there are elements in the heap
     */
    public boolean empty() {
        return elementsInArray == 0;
    }
}
