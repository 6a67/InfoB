package util;

import java.util.Arrays;


public class HeapSort {

   /**
    * Copies the array <code>elements</code> and sorts it using the given heap
    * <code>h</code>. Then returns the sorted copy.
    * 
    * @param h
    *           Heap which is used to sort <code>elements</code>
    * @param elements
    *           Elements which should be sorted.
    * @return a sorted copy of <code>elements</code>
    */
   public static <E> E[] heapSort(Heap<E> h, E[] elements) {

      E[] copy = Arrays.copyOf(elements, elements.length);

      Arrays.fill(copy, null);

      for (E e : elements) {
         h.insert(e);
      }

      int i = 0;
      while (!h.empty()) {
         copy[i++] = h.deleteFirst();
      }

      return copy;
   }

}
