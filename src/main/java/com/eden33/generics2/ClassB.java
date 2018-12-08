package com.eden33.generics2;

/**
 * @author edi
 *
 */
public class ClassB extends ClassA {

	@Override
	public int compareTo(ClassA o) {
		
		// This instance of ClassB is lower than ClassA o instance.
		if(o.getClass().getName() == ClassA.class.getName()) {
			return -1;
		}
		// This instance of ClassB is greater than ClassC o instance.
		if(o.getClass().getName() == ClassC.class.getName()) {
			return 1;
		}
		
		// ClassB is compared with another instance of ClassB
		// they are equal in this implementation
		return 0;
	}
}
