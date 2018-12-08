package com.eden33.generics;

/**
 * @author edi
 *
 * @param <T> Any non-primitive type to be boxed. 
 * Most commonly used type parameter names are: 
 * 
 * <ul>
 * <li>E (Element)</li>
 * <li>K (Key)</li>
 * <li>N (Number)</li>
 * <li>T (Type)</li>
 * <li>V (Value)</li>
 * </ul>
 */
public class Box<T> {

	private T t;
	

	public void set(T t) {
		this.t = t;
	}

	public T get() {
		return t;
	}
}
