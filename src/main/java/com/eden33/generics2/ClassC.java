package com.eden33.generics2;

/**
 * @author edi
 *
 */
public class ClassC extends ClassB {

	@Override
	public int compareTo(ClassA o) {
		
		// This instance of ClassC is lower than ClassA o instance.
		if(o.getClass().getName() == ClassA.class.getName()) {
			return -1;
		}
		// This instance of ClassC is lower than ClassB o instance.
		if(o.getClass().getName() == ClassB.class.getName()) {
			return -1;
		}
		
		// ClassC is compared with another instance of ClassC
		// they are equal in this implementation
		return 0;
	}
}
