package com.mk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * The Class FilteringIteratorTest , This class provided unit test case for FilteringIterator.
 */
@RunWith(MockitoJUnitRunner.class)
public class FilteringIteratorTest {
	private List<Person> peopleList;
	private Iterator<Person> itr;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		peopleList = Arrays.asList(new Person("Charles", 5), 
				new Person("Lewis", 20), 
				new Person("Thomas", 15),
				new Person("Charlotte", 25), 
				new Person("Matthew", 18));
		itr = peopleList.iterator();
	}

	
	/**
	 * Test hasNext() , hasNext Should throw NullPointerException exception for null Filter
	 */
	@Test(expected = NullPointerException.class)
	public void testHasNextForNullFilter() {
		peopleList = Arrays.asList(new Person("Charles", 50));
		itr = peopleList.iterator();
		FilteringIterator filteringIterator = new FilteringIterator(itr,null);
		
		filteringIterator.hasNext();
	}
	
	/**
	 * Test hasNext() , hasNext Should throw NullPointerException exception for null Iterator
	 */
	@Test(expected = NullPointerException.class)
	public void testHasNextForNullIterator() {
		int voteEligibilityAge = 18;
		peopleList = Arrays.asList(new Person("Charles", 50));
		itr = peopleList.iterator();
		FilteringIterator filteringIterator = new FilteringIterator(null,new PersonFilter(voteEligibilityAge));
		filteringIterator.hasNext();
	}
	
	
	/**
	 * Test hasNext() , hasNext should return true when next filter element available, else false
	 */
	@Test
	public void testHasNext() {
		int voteEligibilityAge = 18;
		peopleList = Arrays.asList(new Person("Charles", 50));
		itr = peopleList.iterator();
		FilteringIterator filteringIterator = new FilteringIterator(itr,new PersonFilter(voteEligibilityAge));
		
		// Calling hasNext Multiple time for single element, it shouldn't be fail until next()
		assertTrue(filteringIterator.hasNext());
		assertTrue(filteringIterator.hasNext());
		assertTrue(filteringIterator.hasNext());
		
		// Fetching one and only element from the list 
		filteringIterator.next();
		
		// Because one and only element is out, it should return false 
		assertFalse(filteringIterator.hasNext());
		assertFalse(filteringIterator.hasNext());
		assertFalse(filteringIterator.hasNext());
	}
	
	
	/**
	 * Test next.
	 */
	@Test
	public void testNext() {
		int voteEligibilityAge = 18;
		peopleList = Arrays.asList(new Person("Charles", 50));
		itr = peopleList.iterator();
		FilteringIterator filteringIterator = new FilteringIterator(itr,new PersonFilter(voteEligibilityAge));
		
		Person person = (Person) filteringIterator.next();
		assertEquals(person.getName(),"Charles");
		assertEquals(person.getAge(),50);
	}
	
	/**
	 * Test no such element exception.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testNoSuchElementException() {
		int voteEligibilityAge = 18;
		peopleList = Arrays.asList(new Person("Charles", 50));
		itr = peopleList.iterator();
		FilteringIterator filteringIterator = new FilteringIterator(itr,new PersonFilter(voteEligibilityAge));
		
		filteringIterator.next();
		// 2nd attempt to next() throw runtime NoSuchElementException exception 
		filteringIterator.next();
	}
	
	
	
	/**
	 * lower edge case test, so all element should be return in this case 
	 */
	@Test
	public void testLowerEdge() {
		int voteEligibilityAge = 0;
		FilteringIterator filteringIterator = new FilteringIterator(itr,new PersonFilter(voteEligibilityAge));
		
		int count = 0;
		while (filteringIterator.hasNext()) {
			Person person = (Person) filteringIterator.next();
			count++;
		}
		assertEquals(peopleList.size(), count);
	}
	
	/**
	 * Test upper edge, so no element should be return in this case 
	 */
	@Test
	public void testUpperEdge() {
		int voteEligibilityAge = 10000;
		FilteringIterator filteringIterator = new FilteringIterator(itr,new PersonFilter(voteEligibilityAge));
		
		int count = 0;
		while (filteringIterator.hasNext()) {
			Person person = (Person) filteringIterator.next();
			count++;
		}
		assertEquals(0, count);
	}
	
	/**
	 * Test filtered result, Result should be return based on custom filter provided.
	 */
	@Test
	public void testFilteredResult() {
		int voteEligibilityAge = 18;
		FilteringIterator filteringIterator = new FilteringIterator(itr,new PersonFilter(voteEligibilityAge));
		
		int count = 0;
		while (filteringIterator.hasNext()) {
			Person person = (Person) filteringIterator.next();
			count++;
		}
		assertEquals(3, count);
	}
}
