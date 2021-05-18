public class MyEntry<T> {

    T o;
    MyEntry<T> next;

    MyEntry() {
        this(null, null);
    }

    MyEntry(T o) {
        this(o, null);
    }

    MyEntry(T o, MyEntry<T> e) {
        this.o = o;
        this.next = e;
    }

}
