package com.mk;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * FilteringIterator provides Iterator that allow user to iterate through the
 * data structure based on the user define filter This class have 2 dependency,
 * those need to inject while creating instance 
 * Filter : To Build the filter,IObjectTest interface boolean test(Object o) method need to implement
 * Iterator : Iterator of data structure
 * 
 * 
 * @author Mohit Kumar
 */
public class FilteringIterator implements Iterator {

	/** The filter. This dependency will be injected by the user of this class */
	private final IObjectTest filter;

	/** The iterator, This iterator will be injected by the user of this class */
	private final Iterator iterator;

	/** The nextfilter object. */
	private Object nextfilterObject = null;

	/**
	 * Instantiates a new filtering iterator.
	 * @param iterator
	 *            Iterator of data structure
	 * @param parameter
	 *            will be use to filter data, to build the filter, IObjectTest
	 *            interface boolean test(Object o) method need to implement

	 */
	public FilteringIterator(Iterator iterator,IObjectTest filter) {
		this.iterator = iterator;
		this.filter = filter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		
		if(filter == null || iterator == null) {
			// We can throw custom exception also here
			throw new NullPointerException();
		}
		
		if (nextfilterObject != null) {
			return true;
		}

		while (iterator.hasNext()) {
			Object item = iterator.next();
			if (filter.test(item)) {
				nextfilterObject = item;
				return true;
			}
		}
		nextfilterObject = null;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#next()
	 */
	@Override
	public Object next() {
		Object next = null;

		if (nextfilterObject != null) {
			next = nextfilterObject;
			nextfilterObject = null;
		} else if (hasNext()) {
			next = nextfilterObject;
		}
		nextfilterObject = null;

		if (next == null) {
			throw new NoSuchElementException();
		}
		return next;
	}
}
