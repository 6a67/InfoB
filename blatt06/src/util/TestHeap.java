package util;

public class TestHeap {

    public static void main(String[] args) {
        Heap<Integer> heapInt = new Heap<Integer>();
        Integer[] sortedArray = HeapSort.heapSort(heapInt, new Integer[]{5, 8, 4, 1, 5, 7, 84, 3, 5, 6, 3, 117, 54, 456, 47, 234, 54, 3, 86, 52});

        for(Integer i: sortedArray) {
            System.out.println(i);
        }
    }

}
