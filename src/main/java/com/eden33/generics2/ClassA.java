package com.eden33.generics2;

/**
 * @author edi
 *
 */
public class ClassA implements Comparable<ClassA> {

	@Override
	public int compareTo(ClassA o) {
		
		// This instance of ClassA is greater than ClassB o instance.
		if(o.getClass().getName() == ClassB.class.getName()) {
			return 1;
		}
		// This instance of ClassA is greater than ClassC o instance.
		if(o.getClass().getName() == ClassC.class.getName()) {
			return 1;
		}
		
		// always called in case a ClassA is compared to a ClassA instance
		// they are equal in this implemenation
		
		return 0;
	}
	
}
