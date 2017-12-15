package com.mk;

public class PersonFilter  implements IObjectTest {

	private final int ageEligibility;
	
	public PersonFilter(int ageEligibility) {
		this.ageEligibility = ageEligibility;
	}
	
	@Override
	public boolean test(Object obj) {
		boolean result = false;
		Person person = (Person)obj;
		if(person.getAge() >= ageEligibility) {
			result = true;
		}
		return result;
	}
}
