package utilList;

/**
 * An Entry holds an Object <code>o</code> and a reference <code>next</code> to
 * the next Entry such that a linked List of Entry elements is generated.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
class Entry {

   Object o;
   Entry next;

   Entry() {
      this(null, null);
   }

   Entry(Object o) {
      this(o, null);
   }

   Entry(Object o, Entry e) {
      this.o = o;
      this.next = e;
   }

}
