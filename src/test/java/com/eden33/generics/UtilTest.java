package com.eden33.generics;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author edi
 *
 */
public class UtilTest {
	
	@Test
	public void testCompareList() {
		
		//test first comparison in implementation 
		ArrayList<Integer> l1 = null;
		Vector<Integer> l2 = null;
		boolean compareResult = Util.compare(l1, l2);
		assertTrue(compareResult);
		
		//test second comparison in implementation
		l1 = new ArrayList<>();
		compareResult = Util.compare(l1, l2);
		assertFalse(compareResult);
		
		//test second comparison in implementation
		l1 = null;
		l2 = new Vector<>();
		compareResult = Util.compare(l1, l2);
		assertFalse(compareResult);
		
		l1 = new ArrayList<>();
		l2 = new Vector<>();
		
		Random rand = new Random();
		int numElements = rand.nextInt(10);
		for(int i = 0; i <= numElements; i++) {
			l1.add(i);
			
			if(i == numElements) {
				//test third comparison in implementation
				compareResult = Util.compare(l1, l2);
				assertFalse(compareResult);
			}
			l2.add(i);
		}
		
		//finally they should be equal
		compareResult = Util.compare(l1, l2);
		assertTrue(compareResult);
		
	}
	
	@Test
	public void testRandomChoice() {
		Serializable serializable = Util.randomChoice("Foo", new ArrayList<String>());
		
		// Type mismatch: cannot convert from Object to Serializable
		//Serializable serializable = Util.randomChoice("Foo", new Object());
		
		Util.randomChoice("Foo", new Object());
		assertNotSame(Serializable.class, serializable);
	}
}
