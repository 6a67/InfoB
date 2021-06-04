package visitor;

/**
 * An Entry holds an Object <code>o</code> and a reference <code>next</code> to
 * the next Entry such that a linked List of Entry elements is generated.
 * 
 * @author Lars Huning
 */
class MyEntry<E> implements Cloneable {

	MyEntry<E> next;
	E o;

	MyEntry() {
		this(null, null);
	}

	MyEntry(E o) {
		this(o, null);
	}

	MyEntry(E o, MyEntry<E> e) {
		this.o = o;
		this.next = e;
	}

	@Override
	public MyEntry<E> clone() {
		MyEntry<E> clone;
		try {
			clone = (MyEntry<E>) super.clone();
			if (next != null) {
				clone.next = next.clone();
			}
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyEntry other = (MyEntry) obj;
		if (next == null) {
			if (other.next != null)
				return false;
		} else if (!next.equals(other.next))
			return false;
		if (o == null) {
			if (other.o != null)
				return false;
		} else if (!o.equals(other.o))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((next == null) ? 0 : next.hashCode());
		result = prime * result + ((o == null) ? 0 : o.hashCode());
		return result;
	}


}
