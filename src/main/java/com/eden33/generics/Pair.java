package com.eden33.generics;

/**
 * @author edi
 *
 */
public class Pair<K, V> {

	private K key;
	private V value;
		
	public Pair(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return this.key;
	}

	public V getValue() {
		return this.value;
	}

}
