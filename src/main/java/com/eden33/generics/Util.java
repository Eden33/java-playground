package com.eden33.generics;

import java.util.AbstractList;
import java.util.List;
import java.util.Random;

/**
 * @author edi
 *
 */
public class Util {
	
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
               p1.getValue().equals(p2.getValue());
    }
    
    public static <N extends Number> boolean isEven(N number) {
    	return number.intValue() % 2 == 0;
    }
    
    public static boolean isEmpty(Box<Number> box) {
    	if(box.get() == null) {
    		return true;
    	}
    	return false;
    }
    
    public static <E> boolean compare(AbstractList<E> l1, AbstractList<E> l2) {
    	boolean equal = true;
    	if(l1 == null && l2 == null) {
    		return true;
    	}
    	if(l1 == null || l2 == null) {
    		return false;
    	}
    	if(l1.size() != l2.size()) {
    		return false;
    	}
    	int i = 0;
    	E e2 = null;
    	for(E e : l1) {
    		e2 = l2.get(i);
    		if(!e.equals(e2)) {
    			equal = false;
    			break;
    		}
    		i++;
    	}
    	return equal;
    }
    
    public static <T> T randomChoice(T t1 , T t2) {    	
    	Random rand = new Random();
    	double d = rand.nextDouble();
    	if(d <= 0.5) {
    		return t1;
    	}
    	return t2;
    }
    
    public static double sumOfList(List<? extends Number> list) {
    	double sum = 0;
    	for(Number num : list) {
    		sum += num.doubleValue();
    	}
    	return sum;
    }
    
    public static void printList(List<?> list) {
    	for(Object elem : list) {
    		System.out.println(elem);
    	}
    }
    
    public static void addObjectToListWithUnknownType1(List<?> aList, Object o) {
    	//"Compilation error" if you uncomment: no suitable method found for add(java.lang.Object)
    	//aList.add(o);
    	aList.add(null);
    }
    
    public static void addObjectToListWithUnknownType2(List<? extends Object> aList, Object o) {
    	//"Compilation error" if you uncomment: no suitable method found for add(java.lang.Object)
    	//aList.add(o);
    	aList.add(null);
    }
    public static void addObjectToListWithUnknownType3(List<? super Object> aList, Object o) {
    	aList.add(null);
    	aList.add(o);
    	aList.add(new Integer(2));
    }
    
    public static void addIntegerToSuperTypeList(List<? super Integer> aList, Integer i) {
    	aList.add(i);
    	//"Compilation error" if you uncomment: The method add(capture#6-of ? super Integer) in the type 
    	//List<capture#6-of ? super Integer> is not applicable for the arguments (Object)
    	//aList.add(new Object());
    }
    
    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        for (int i = 0; i < src.size(); i++) {
            dest.add(i, src.get(i));         	
        }
    }
}
