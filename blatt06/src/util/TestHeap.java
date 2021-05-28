package util;

import java.util.Arrays;
import java.util.Comparator;

public class TestHeap {

    public static void main(String[] args) {
        Integer[] unsortedArray = new Integer[]{5, 8, 4, 1, 5, 7, 84, 3, 5, 6, 3, 117, 54, 456, 47, 234, 54, 3, 86, 52};
        Heap<Integer> heapSortInt = new Heap<Integer>();


        System.out.format("%-16s", "Unsorted:");
        System.out.println(Arrays.toString(unsortedArray));

        // HeapSort testet .insert und .deleteFirst
        Integer[] sortedArray = HeapSort.heapSort(heapSortInt, unsortedArray);
        System.out.format("%-16s", "HeapSort:");
        System.out.println(Arrays.toString(sortedArray));

        Comparator<Integer> integerComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return t1.compareTo(integer);
            }
        };

        // HeapSort mit Comparator
        Heap<Integer> heapSortIntComp = new Heap<Integer>(integerComparator);
        Integer[] sortedArray2 = HeapSort.heapSort(heapSortIntComp, unsortedArray);
        System.out.format("%-16s", "HeapSort2:");
        System.out.println(Arrays.toString(sortedArray2));

        try {
            heapSortInt.insert(null);
        } catch (NullPointerException e) {
            System.out.println(".insert wirft eine NullPointerException, wenn man versucht null einzufügen");
        }

        try {
            Heap<TestObject> testHeap = new Heap<TestObject>();
            testHeap.insert(new TestObject());
            testHeap.insert(new TestObject());
        } catch (ClassCastException e) {
            System.out.println("ClassCastException, wenn das Objekt nicht von Compareable ist");
        }


    }

}
