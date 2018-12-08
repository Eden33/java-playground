package com.eden33.generics;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.eden33.generics2.GenericPlayground2;

/**
 * @author edi
 *
 * Most of the code and comments are based on:
 * https://docs.oracle.com/javase/tutorial/java/generics/QandE/generics-questions.html
 */
public class GenericPlaygroundExersice {

	public static void execute() {
	
		/*
		 * Exercise 1:
		 * 
		 * Write a generic method to count the number of elements in a collection that have a 
		 * specific property (for example, odd integers, prime numbers, palindromes).
		 */
		int count  = countElements(Arrays.asList(1, 2, 3), new OddPredicate());
		System.out.println("Count : " + count);
		
		/*
		 * Exercise 2:
		 * 
		 * Will the method compile? If not, why?
		 * 
		 * Answer: No. The greater than (>) operator applies only to primitive numeric types.
		 */
		Integer max = max(1, 2);
		
		/*
		 * Exercise 3:
		 * 
		 * Write a generic method to exchange the positions of two different elements in an array.
		 */
		Integer[] elements = new Integer[] {1,2,3};
		swap(elements, 0, 2);
		System.out.println("idx 0: " + elements[0] + " idx 2: " + elements[2]);
		
		/*
		 * Exercise 4:
		 * 
		 * If the compiler erases all type parameters at compile time, why should you use generics?
		 * 
		 * Answer: 
		 * - The Java compiler enforces tighter type checks on generic code at compile time.
		 * - Generics support programming types as parameters.
		 * - Generics enable you to implement generic algorithms.
		 */
		
		/* 
		 * Exercise 5:
		 * 
		 * What is the class {@code GenericPlaygroundExersice.Pair1} converted to after type erasure?
		 * 
		 * Answer: {@code GenericPlaygroundExersice.Pair2}
		 */
		
		/*
		 * Exercise 6:
		 * 
		 * What is the following method converted to after type erasure?
		 * 
		 * public static <T extends Comparable<T>> int findFirstGreaterThan(T[] at, T elem) { ... }
		 *       
		 * Answer: 
		 * 
		 * public static int findFirstGreaterThan(Comparable[] at, Comparable elem) { ... }      
		 */
		
		/*
		 * Exercise 7:
		 * 
		 * Will the following method compile? If not, why?
		 * 
		 * Answer: Yes.
		 */
		print(Arrays.asList(1,2,3));
		
		/*
		 * Exercise 8:
		 * 
		 * Write a generic method to find the maximal element in a list of T elements.
		 */
		List<Integer> intList = Arrays.asList(2,4,1,8,6);
		max = Collections.max(intList);
		System.out.println("Max value: " + max);
		max = max(intList);
		System.out.println("Max value: " + max);
		
		/*
		 * Exercise 9: Will the following class compile?
		 * 
		 *   public class Singleton<T> {
		 *       
		 *       public static T getInstance() {
		 *           if (instance == null)
		 *               instance = new Singleton<T>();
		 *           return instance;
		 *       }
		 *       
		 *       private static T instance = null;
		 *   }
		 * 
		 * Answer: No. 
		 *         You cannot create a static field of the type parameter T. 
		 */
		
		/*
		 * Exercise 10: Given the following classes:
		 *  
		 *    class Shape {}
		 *    class Circle extends Shape {}
		 *    class Rectangle extends Shape {}
		 *    class Node<T> {}
		 *    
		 *    Will the following code compile: 
		 *    
		 *    Node<Circle> nc = new Node<>();
		 *    Node<Shape>  ns = nc;
		 *    
		 * Answer: No.
		 *         Because Node<Circle> is not a subtype of Node<Shape>.
		 */
		
		/*
		 * Exercise 11: Consider this class:
		 * 
		 *    class Node<T> implements Comparable<T> {
		 *        public int compareTo(T obj) {}
		 *    }
		 *    
		 *    Will the following code compile? If not why?
		 *    
		 *    Node<String> node = new Node<>();
		 *    Comparable<String> comp = node;
		 *    
		 *    Answer: Yes.
		 */
	}
	
	private static <E> int countElements(Collection<E> collection, Predicate<E> predicate) {
		int i = 0;
		for (E e : collection) {
			if(predicate.test(e)) {
				i++;
			}
		}
		return i;
	}
	
	private interface Predicate<E> {
		boolean test(E element);
	}
	
	private static class OddPredicate implements Predicate<Integer> {

		@Override
		public boolean test(Integer i) {
			return i % 2 != 0; 
		}
		
	}
	
    private static <T> T max(T x, T y) {
        // return x > y ? x : y;
        return null; //last expression doesn't work.
    }
    
    private static <T> void swap(T[] array, int idx1, int idx2) {
    	T e1 = array[idx1];
    	array[idx1] = array[idx2];
    	array[idx2] = e1;
    }
    
    @SuppressWarnings("unused")
    private class Pair1<K, V> {

        public Pair1(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }

        public void setKey(K key)     { this.key = key; }
        public void setValue(V value) { this.value = value; }

        private K key;
        private V value;
    }
	
    @SuppressWarnings("unused")
    private class Pair2 {

        public Pair2(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() { return key; }
        public Object getValue() { return value; }

        public void setKey(Object key)     { this.key = key; }
        public void setValue(Object value) { this.value = value; }

        private Object key;
        private Object value;
    }
    
    private static void print(List<? extends Number> list) {
        for (Number n : list)
            System.out.print(n + " ");
        System.out.println();
    }
    
    /**
     * <p>This is a copy of {@code Collections#max(Collection)}.
     * 
     * <p>With the upper bound <tt>&lt;T extends Object ...&gt; T</tt> the function declaration preserves binary compatibility with 
     * older versions of Java before generics were introduced as the return type of the method must be of type Object.
     * 
     * <p>
     * With the lower bound <tt> Comparable&lt;? super T&gt;</tt> the function declaration makes sure that all elements in List&lt;T&gt; 
     * implement Comparable&lt;T&gt; and that each instance is either of specific type T or a supertype of T.
     * Due to this fact the Comparable interface can be used to compare all instances in the collection with each other.
     * 
     * For further reference:
     * <ul>
     * <li>you can read: http://www.angelikalanger.com/GenericsFAQ/FAQSections/ProgrammingIdioms.html#FAQ104 
     * (see chapter: "How do I avoid breaking binary compatibility when I generify an existing type or method?")</li>
     * <li>{@link GenericPlayground2#pecsInAcctionCollectionsMax}</li>
     * </ul>
     *  
     * 
     * @param coll
     * @return
     */
    public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) {
        Iterator<? extends T> i = coll.iterator();
        T candidate = i.next();

        while (i.hasNext()) {
            T next = i.next();
            if (next.compareTo(candidate) > 0)
                candidate = next;
        }
        return candidate;
    }
}
