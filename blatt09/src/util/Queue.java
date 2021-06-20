package util;

import java.util.NoSuchElementException;

/**
 * An implementation of a Queue with a limited capacity.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 * 
 * @param <E>
 */
public class Queue<E> {

	/**
	 * Holds the objects stored by this {@code Queue}.
	 */
	private Object[] objects;
	/**
	 * index of the first instance stored by this {@code Queue}.
	 */
	private int first;
	/**
	 * number of elements contained in this {@code Queue}
	 */
	private int size;

	/**
	 * @param capacity
	 *            number of objects which may be hold in this {@code Queue}.
	 */
	public Queue(int capacity) {
		this.objects = new Object[capacity];
		this.first = 0;
		this.size = 0;
	}

	/**
	 * Inserts {@code o} at the first free position of this {@code Queue}
	 * 
	 * @param o
	 *            object to be inserted
	 * 
	 * @throws RuntimeException
	 *             if this {@code Queue} is already full
	 */
	public void enq(E o) {

		if (this.full()) {
			throw new RuntimeException("queue is full");
		}
		objects[(first + size) % objects.length] = o;
		size++;
	}

	/**
	 * Removes the object at the first position of this {@code Queue}.
	 * 
	 * @return the removed object
	 * @throws NoSuchElementException
	 *             if this {@code Queue} is already empty
	 */
	public E deq() {
		if (this.empty()) {
			throw new NoSuchElementException();
		}

		E o = (E) objects[first];
		first = (first + 1) % objects.length;
		size--;
		return o;
	}

	/**
	 * Returns the object at the first position of this {@code Queue}
	 * 
	 * @return the first element of this {@code Queue}
	 * @throws NoSuchElementException
	 *             if this {@code Queue} is already empty
	 */
	public E front() {
		if (this.empty()) {
			throw new NoSuchElementException();
		}
		return (E) objects[first];
	}

	/**
	 * 
	 * @return {@code true} if this {@code Queue} is empty
	 */
	public boolean empty() {
		return this.size == 0;
	}

	/**
	 * 
	 * @return {@code true} if this {@code Queue} is full
	 */
	public boolean full() {
		return this.size == objects.length;
	}

}
