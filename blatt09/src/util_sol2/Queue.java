package util_sol2;

import java.util.NoSuchElementException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * An implementation of a Queue with a limited capacity.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 * 
 * @param <E>
 */
public class Queue<E> {

	private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
	private final Lock rLock = rwLock.readLock();
	private final Lock wLock = rwLock.writeLock();
	private final Condition cond = wLock.newCondition();

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
		try {
			wLock.lock();
			while(full()) cond.await();
			if (this.full()) {
				throw new RuntimeException("queue is full");
			}
			objects[(first + size) % objects.length] = o;
			size++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			cond.signalAll();
			wLock.unlock();
		}
	}

	/**
	 * Removes the object at the first position of this {@code Queue}.
	 * 
	 * @return the removed object
	 * @throws NoSuchElementException
	 *             if this {@code Queue} is already empty
	 */
	public E deq() {
		try {
			wLock.lock();
			while(empty()) cond.await();
			if (this.empty()) {
				throw new NoSuchElementException();
			}

			E o = (E) objects[first];
			first = (first + 1) % objects.length;
			size--;
			return o;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			cond.signalAll();
			wLock.unlock();
		}
		return null;
	}

	/**
	 * Returns the object at the first position of this {@code Queue}
	 * 
	 * @return the first element of this {@code Queue}
	 * @throws NoSuchElementException
	 *             if this {@code Queue} is already empty
	 */
	public E front() {
		try {
			rLock.lock();
			if (this.empty()) {
				throw new NoSuchElementException();
			}
			return (E) objects[first];
		} finally {
			rLock.unlock();
		}
	}

	/**
	 * 
	 * @return {@code true} if this {@code Queue} is empty
	 */
	public boolean empty() {
		try {
			rLock.lock();
			return this.size == 0;
		} finally {
			rLock.unlock();
		}
	}

	/**
	 * 
	 * @return {@code true} if this {@code Queue} is full
	 */
	public boolean full() {
		try {
			rLock.lock();
			return this.size == objects.length;
		} finally {
			rLock.unlock();
		}
	}

}
