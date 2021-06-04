package iterator;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simple linked list. One may go through this list by {@link #advance()}
 * until the last position ({@link #endpos()}) is reached. One also may
 * {@link #delete()} and {@link #add(Object)} elements. After advancing it is
 * possible to go back to the beginning by {@link #reset()}.
 * 
 * @author Lars Huning
 * 
 */
public class MyList<E> implements Cloneable, Iterable<E> {

	/**
	 * Reference on the first Entry of this List
	 */
	private MyEntry<E> begin;
	/**
	 * References before the actual Entry of this List
	 */
	private MyEntry<E> pos;

	private int modCount;

	/**
	 * Create a new empty List.
	 */
	public MyList() {
		pos = begin = new MyEntry<E>();
		modCount = 0;
	}

	/**
	 * Determines if this List is empty or not.
	 * 
	 * @return <code>true</code>, if there are no elements in this List
	 */
	public boolean empty() {
		return begin.next == null;
	}

	/**
	 * Determines if it is possible to {@link #advance()} in this List. Returns
	 * <code>true</code> if the last position of this List has been reached. An
	 * {@link #empty()} List will alway deliver <code>true</code>
	 * 
	 * @return <code>true</code> if the last Entry in this List already has been
	 *         reached.
	 */
	public boolean endpos() { // true, wenn am Ende
		return pos.next == null;
	}

	/**
	 * Returns to the beginning of this List.
	 */
	public void reset() {
		pos = begin;
		modCount++;
	}

	/**
	 * Advances one step in this List.
	 * 
	 * @throws NoSuchElementException
	 *            if the last Entry of this List already has been reached.
	 */
	public void advance() {
		if (endpos()) {
			throw new NoSuchElementException("Already at the end of this List");
		}
		pos = pos.next;
		modCount++;
	}

	/**
	 * Returns the actual element of this List.
	 * 
	 * @return the actual element
	 * 
	 * @throws RuntimeException
	 *            if the last Entry of this List already has been reached.
	 */
	public E elem() {
		if (endpos()) {
			throw new NoSuchElementException("Already at the end of this List");
		}
		return pos.next.o;
	}

	/**
	 * Inserts <code>o</code> in this List. It will be placed before the actual
	 * element. After insertion the inserted element will become the actual
	 * element.
	 * 
	 * @param x
	 *           the element to be inserted
	 */
	public void add(E x) {
		MyEntry<E> newone = new MyEntry<E>(x, pos.next);

		pos.next = newone;

		modCount++;
	}

	/**
	 * Deletes the actual element of this List. The element after the actual
	 * element will become the new actual element.
	 * 
	 * @throws NoSuchElementException
	 *            if the last Entry of this List already has been reached.
	 */
	public void delete() {
		if (endpos()) {
			throw new NoSuchElementException("Already at the end of this List");
		}
		pos.next = pos.next.next;
		modCount++;
	}

	/**
	 * Clones this MyList. Will create a new independent MyList which actual
	 * position lies at the beginning of this MyList. This clone operation also
	 * fulfills the optional requirements defined by the {@link Object#clone()}
	 * operation. NOTE: Inserted elements will not be cloned, due to the fact,
	 * that the {@link Object#clone()} is <code>protected</code>.
	 * 
	 * @see Object#clone()
	 */
	public MyList<E> clone() {
		try {

			MyList<E> clone = (MyList<E>) super.clone();
			clone.begin = this.begin.clone();
			clone.pos = clone.begin;

			return clone;
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((begin == null) ? 0 : begin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyList other = (MyList) obj;
		if (!begin.equals(other.begin))
			return false;
		return true;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {

			private final int modCountAtCreation = modCount;
			private MyEntry<E> current = begin;
			private MyEntry<E> before = begin;


			@Override
			public boolean hasNext() {
				if(modCountAtCreation != modCount) throw new ConcurrentModificationException();
				return current.next != null;
			}

			@Override
			public E next() {
				if(modCountAtCreation != modCount) throw new ConcurrentModificationException();
				if(!hasNext()) throw new NoSuchElementException();
				E returnValue = (E) current.next.o;
				before = current;
				current = current.next;
				return returnValue;
			}

			@Override
			public void remove() {
				if (!hasNext()) {
					throw new NoSuchElementException("Already at the end of this List");
				}
				if(before == begin) {
					begin = begin.next;
					current = begin;
					before = begin;
				} else {
					before.next = before.next.next;
					before = current;
				}
			}
		};
	}

	public int getModCount() {
		return this.modCount;
	}
}

/*class MyListIterator<E> implements Iterator<E> {

	private MyList<E> list;
	private MyEntry<E> current;

	private final int modCountAtCreation;

	MyListIterator(MyList<E> list, MyEntry<E> current) {
		this.list = list;
		this.current = current;
		modCountAtCreation = this.list.getModCount();

	}

	@Override
	public boolean hasNext() {
		if(modCountAtCreation != list.getModCount()) throw new ConcurrentModificationException();
		return !list.endpos();
	}

	@Override
	public E next() {
		if(modCountAtCreation != list.getModCount()) throw new ConcurrentModificationException();
		if(!hasNext()) throw new NoSuchElementException();
		E returnValue = (E) current.next.o;
		current = current.next;
		return returnValue;
	}
}*/
