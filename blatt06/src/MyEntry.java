public class MyEntry<T> implements Cloneable {

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

    /**
     * Clone Methode
     * @return cloned object
     * @throws CloneNotSupportedException
     */
    protected Object clone() throws CloneNotSupportedException {
        MyEntry<T>  cloned = (MyEntry<T>) super.clone();
        if(cloned.next != null) {
            cloned.next = (MyEntry<T>) cloned.next.clone();
        }
        return cloned;
    }
}
