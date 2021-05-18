package util;

import java.util.Arrays;
import java.util.Comparator;

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

    private void increaseArraySize(int i) {
        elements = Arrays.copyOf(elements, elements.length + i);
        //arraySize += i;
    }

    public void insert(T o) {
        // If array is full, increase its size to 1.5
        if(elementsInArray >= elements.length) {
            increaseArraySize((int) (elements.length * 0.5) + 1);
        }
        elements[elementsInArray++] = o;
        siftUp();
        return;
    }


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

    private void siftUpCompareTo() {
        int i = elementsInArray - 1; // Index of the last element
        while(i > 0) {
            int p = (i - 1) / 2; // Index of the parent
            // Element has to have to the compareTo function
            Comparable<? super T> element = (Comparable<? super T>) elements[i];
            T parent = elements[p];

            if(element.compareTo(parent) > 0) {
                // swap
                swap(i, p);

                // move the the next level
                i = p;
            } else {
                break;
            }
        }
    }


    private void siftDownCompareTo() {
        int i = 0; // Index of the first element
        int leftChildIndex = 2 * i + 1;
        while(leftChildIndex < elementsInArray) {
            int rightChildIndex = leftChildIndex + 1;
            int biggestChildIndex = leftChildIndex;

            // Compare left child to right child
            if(rightChildIndex < elementsInArray) { // Checks if a right child exists
                Comparable<? super T> rightChild = (Comparable<? super T>) elements[rightChildIndex];
                if(rightChild.compareTo(elements[leftChildIndex]) > 0) {
                    biggestChildIndex = rightChildIndex;
                }
            }

            // Check if the biggest child is bigger than the node
            Comparable<? super T> parent = (Comparable<? super T>) elements[i];
            if(parent.compareTo(elements[biggestChildIndex]) < 0) {
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
     * Same as siftUpCompareTo, but uses comparator.compare instead of compareTo
     * And removed the Comparable<? super T> cast
     */
    private void siftUpComparator() {
        int i = elementsInArray - 1; // Index of the last element
        while(i > 0) {
            int p = (i - 1) / 2; // Index of the parent
            // Element has to have to the compareTo function
            T element = elements[i];
            T parent = elements[p];

            //if(element.compareTo(parent) > 0) {
            if(comparator.compare(element, parent) > 0) {
                // swap
                swap(i, p);

                // move the the next level
                i = p;
            } else {
                break;
            }
        }
    }


    private void siftDownComparator() {
        int i = 0; // Index of the first element
        int leftChildIndex = 2 * i + 1;
        while(leftChildIndex < elementsInArray) {
            int rightChildIndex = leftChildIndex + 1;
            int biggestChildIndex = leftChildIndex;

            // Compare left child to right child
            if(rightChildIndex < elementsInArray) { // Checks if a right child exists
                T rightChild = elements[rightChildIndex];
                if(comparator.compare(rightChild, elements[leftChildIndex]) > 0) {
                    biggestChildIndex = rightChildIndex;
                }
            }

            // Check if the biggest child is bigger than the node
            T parent =  elements[i];
            if(comparator.compare(parent, elements[biggestChildIndex]) > 0) {
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

    private void siftUp() {
        if(comparator == null) {
            siftUpCompareTo();
        } else {
            siftUpComparator();
        }
    }

    private void siftDown() {
        if(comparator == null) {
            siftDownCompareTo();
        } else {
            siftDownComparator();
        }
    }


    public boolean empty() {
        return elementsInArray == 0;
    }
}
