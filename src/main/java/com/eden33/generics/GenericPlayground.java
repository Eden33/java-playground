package com.eden33.generics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * @author edi
 * 
 * Deep dive into generics. Most of the code and comments are based on:
 * https://docs.oracle.com/javase/tutorial/java/generics/index.html
 * 
 */
public class GenericPlayground {
	
	/**
	 * <p><tt>Type Argument(s)</tt> are used on the left side while we declare a generic instance variable.
	 * <p><tt>Type Parameter(s)</tt> are passed the the constructor (like in this example) or function of a generic instance.
	 * <p><tt>Parameterized Types</tt> are instances of generic classes like <tt>integerBox</tt> and <tt>orderedPair</tt> in 
	 * this example.
	 */
	public static void typArgumentsVsParameters() {
		Box<Integer> integerBox = new Box<>();
		Pair<String, Integer> orderedPair = new Pair<>("Foo", 10);
	}
	

	/**
	 * <p>If you instantiate a <tt>Parameterized Type<tt/> without the <tt>Type argument(s)</tt> defined 
	 * in the class definition you end up with a <tt>Raw-type</tt>. 
	 * 
	 * <p>
	 * <strong>Note:</strong> A non-generic class or interface is not a <tt>Raw-type</tt>. This term belongs to generics only.
	 */
	public static void rawType() {		
		Box rawBox = new Box();

		// For backward compatibility of Java applications (generics were introduced in JDK 5.0) 
		// assigning a Parameterized Type to a Taw-type is allowed.
		Box<String> stringBox = new Box<>();
		rawBox = stringBox;
		// but if you assign a Raw-type to a Parameterized type the java compiler will output
		// a "unchecked conversion" warning if configured properly (-Xlint:unchecked)
		stringBox = rawBox;
		// the compiler will also output a "unchecked call to set(T)" warning if you use a Raw-type to invoke a generic method.
		rawBox.set(8);		
	}
	
	/**
	 *  The syntax of a generic method includes a list of <tt>Type Parameter(s)</tt> inside angle brackets 
	 *  in front of the return type of the method.
	 */
	public static void methodDeclaration() {
		Pair<String, Integer> pair1 = new Pair<>("Foo", 1);
		Pair<String, Integer> pair2 = new Pair<>("Bar", 2);
		
		Util.compare(pair1, pair2);
	}
	
	/**
	 * <p>There may be times when you want to restrict the types that can be used as <tt>Type Arguments(s)</tt>.
	 * For example, a method that operates on numbers might only want to accept instances of <tt>Number</tt> or its subclasses. 
	 * This is what bounded type parameters are for.
	 * 
	 * <p>
	 * To declare a bounded type parameter, list the type parameter's name, followed by the extends keyword,
	 * followed by its upper bound, which in this example is Number. 
	 * Note that, in this context, extends is used in a general sense to mean either "extends" (as in classes) 
	 * or "implements" (as in interfaces).
	 * 
	 * <p>
	 * <strong>Note:</strong> The example shown in this method illustrates the use of a <tt>Type Parameter</tt> with a single bound.
	 * However, <tt>Type Parameter</tt> can have multiple bounds as well:
	 * 
	 * <p>
	 * &lt;T extends B1 & B2 & B3&gt;
	 * <p>
	 * A type variable with multiple bounds is a subtype of all the types listed in the bound. 
	 * If one of the bounds is a class, it must be specified first. For example:
	 * 
	 * <p>
	 * Class A {}<br/>
	 * interface B {}<br/>
	 * interface C {}<br/>
	 * 
	 * <p>
	 * class D &lt;T extends A & B & C&gt; {}
	 * 
	 * <p>
	 * If bound A is not specified first, you get a compile-time error:
	 * 
	 * <p>
	 * class D &lt;T extends B & A & C&gt; {}  // compile-time error
	 */
	public static void boundedTypeParameters() {
		Util.isEven(new Double(1));
	}
	
	/**
	 * Given two concrete types A and B (for example, <tt>Number</tt> and <tt>Integer</tt>), MyClass&lt;A&gt; 
	 * has no relationship to MyClass&lt;B&gt;,  regardless of whether or not A and B are related. 
	 * The common parent of MyClass&lt;A&gt; and MyClass&lt;B&gt; is <tt>Object</tt>.
	 */
	public static void understandSubTyping1() {
		Box<Number> numberBox = new Box<>();
		Util.isEmpty(numberBox);
		Box<Integer> intBox = new Box<>();
		
		//compile error: incompatible types: Box<java.lang.Integer> cannot be converted to Box<java.lang.Number>
		//Util.isEmpty(intBox); 
	}
	
	/**
	 * <p>
	 * You can subtype a generic class or interface by extending or implementing it. 
	 * The relationship between the <tt>Type Parameter(s)</tt> of one class or interface and the <tt>Type Parameter(s)</tt> 
	 * of another are determined by the extends and implements clauses.
	 * 
	 * <p>For example: <tt>ArrayList&lt;E&gt;</tt> as well as <tt>Vector&lt;E&gt;</tt> extend <tt>AbstractList&lt;E&gt;</tt>. 
	 * Therefore both of them are subtypes of <tt>AbstractList&lt;E&gt;</tt>.
	 */
	public static void understandSubTyping2() {
		ArrayList<Integer> intList1 = new ArrayList<>();
		Vector<Integer> intList2 = new Vector<>();
		Util.compare(intList1, intList2);		
	}
	
	/**
	 * Although <tt>Integer</tt> is a subtype of <tt>Object</tt> List&lt;Integer&gt; is not a subtype of List&lt;Object&gt; and, in fact, 
	 * these two types are not related. The common parent of List&lt;Object&gt; and List&lt;Integer&gt; is List&lt;?&gt;.
	 * 
	 * In order to create a relationship between these classes use an upper bounded wildcard.
	 */
	public static void understandSubTyping3() {
		List<Object> objList = new ArrayList<>();
		List<Integer> intList = new ArrayList<>();
		//objList = intList; // compile error: cannot convert from List<Integer> to List<Object>
		
		List<? extends Object> objList2 = new ArrayList<>();
		objList2 = intList;
	}

	/**
	 * <p>The inference algorithm determines the <tt>Type Argument(s)</tt> and, if available, the type that the result is
	 * being assigned, or returned. 
	 * 
	 * <p>In this example the inference algorithm cares about the return type assignment and that it is the 
	 * <i>most specific type</i> that works for all the <tt>Type Arguments</tt> used in the method execution as <tt>Type Parameter</tt>.
	 * This compiler behavior is also known as <strong>target typing<strong>.
	 */
	public static void typeInferenceBasedOnReturnType1() {
		// String as well as ArrayList<String> implement Serializable, so the most specific type determined for T 
		// is Serializable 
		Serializable serializable = Util.randomChoice("Foo", new ArrayList<String>());
		
		// Type mismatch: cannot convert from Object to Serializable
		//Serializable serializable = Util.randomChoice("Foo", new Object());
		
		// But if you omit the return type assignment the compiler is fine with String- and Object-arguments 
		// as now the most specific type determined for T is Object
		Util.randomChoice("Foo", new Object());
	}
	
	/**
	 * As in {@code GenericPlayground#typeInferenceBasedOnReturnType1()} another example for <strong>target typing<strong>. 
	 */
	public static void typeInferenceBasedOnReturnType2() {
		List<String> strList = Collections.emptyList();
		List<Integer> intList = Collections.emptyList();
		
		//returns List<Object> as no return type assignment specified.
		Collections.emptyList();
	}


	/**
	 * 	<p>Type inference is a Java compiler's ability to look at each method invocation and corresponding
	 *  declaration to determine the type argument (or arguments) that makes the invocation applicable.
	 *  
	 *  <p>In this example the inference algorithm determines for <tt>Util.randomChoice</tt> that the <i>most specific type</i>
	 *  is of type <tt>Integer</tt>. Of course you can also provide this information in code as you can see after the first 
	 *  execution of <tt>Util.randomChoice</tt>.
	 */
	public static void typeInferenceBasedOnMethodArguments() {
		
		Util.randomChoice(1, 2);
		Util.<Integer>randomChoice(1, 2);
		Util.randomChoice(new Integer(1), new Integer(2));
	}


	/**
	 * You can replace the <tt>Type Argument(s)</tt> required to invoke the constructor of a generic class with an empty set 
	 * of <tt>Type Parameter(s)</tt> (&lt;&gt;) as long as the compiler can infer the <tt>Type Argument(s)</tt> from the context. 
	 * This pair of angle brackets is informally called the diamond.
	 */
	public static void typeInferenceBasedOnTheDiamond() {
		Box<Integer> intBox1 = new Box<>();
		//VS
		Box<Integer> intBox2 = new Box<Integer>();
		
		Map<String, List<Integer>> map1 = new HashMap<>();
		//VS
		Map<String, List<Integer>> map2 = new HashMap<String, List<Integer>>();
	}


	/**
	 * <p>
	 * Constructors can be generic (in other words, declare their own formal <tt>Type Parameter(s)</tt>) in both generic and non-generic classes.
	 * 
	 * <p>
	 * <strong>Note:</strong> I don't see much sense in it but you can do it :)
	 */
	public static void typeInferenceForClassConstructors() {
		new GenericConstructor<>("");
	}


	/**
	 * <p>
	 * You can use an upper bounded wildcard to relax the restrictions on a variable. 
	 * For example, say you want to write a method that works on List&lt;Integer&gt;, List&lt;Double&gt;, and List&lt;Number&gt;; 
	 * you can achieve this by using an upper bounded wildcard.
	 * 
	 * <p>
	 * To declare an upper-bounded wildcard, use the wildcard character ('?'), followed by the extends keyword, 
	 * followed by its upper bound. 
	 * 
	 * <p>
	 * <strong>Note:</strong> In this context, extends is used in a general sense to mean either "extends" (as in classes) or "implements" (as in interfaces).
	 * 
	 * <p>
	 * <strong>When to work with upper bound restriction:</strong> 
	 * <ul>
	 * <li>In case you only need read access to the upper bound (in this example <tt>Number</tt>) in collections.</li>
	 * <li>In case you don't need to modify collections. 
	 * See {@link #unboundWildcard()} which demonstrates that you are only able to add null values if such a constraint is applied.</li>
	 * </ul>
	 */
	public static void upperBoundWildcard() {
		List<Integer> intList = Arrays.asList(1, 2, 3);
		double sum = Util.sumOfList(intList);
		List<Double> doubleList = Arrays.asList(1.2, 1.3, 2.5);
		sum = Util.sumOfList(doubleList);		
	}


	/**
	 * The unbounded wildcard type is specified using the wildcard character (?), for example, List&lt;?&gt;. 
	 * This is called a list of unknown type. There are two scenarios where an unbounded wildcard is a useful approach:
	 * <ul>
	 * <li>If you are writing a method that can be implemented using functionality provided in the Object class (like we do in this example).</li>
	 * <li>When the code is using methods in the generic class that don't depend on the type parameter. For example, List.size or List.clear.
	 * In fact, Class&lt;?&gt; is so often used because most of the methods in Class&lt;T&gt; do not depend on T.</li>
	 * </ul>
	 * 
	 * <p>
	 * <strong>Note:</strong> It's important to note that List&lt;Object&gt; and List&lt;?&gt; are not the same. 
	 * You can insert an Object, or any subtype of Object, into a List&lt;Object&gt;. But you can only insert null into a List&lt;?&gt;.
	 */
	public static void unboundWildcard() {
		List<Object> objList = new ArrayList<>(Arrays.asList("foo", "bar", 1));
		List<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3));
		List<String> strList = new ArrayList<>(Arrays.asList("foo", null, "bar"));
		Util.printList(intList);
		Util.printList(strList);
		
		// Next two methods demonstrate that you can only insert null values into an unbound or upper bound list.
		// The reason why: The "Type Argument" of the list may be more specific than the provided instance to be added.
		// Therefore the only valid value for all "Type Arguments" possible is null.
	    Util.addObjectToListWithUnknownType1(intList, null);
	    Util.addObjectToListWithUnknownType2(intList, null);
	    
	    // Next method demonstrates that you can insert null values, the specific lower bound type or any subtype of that type
	    // to a lower bound list.
	    // The reason why: They are all compatible with the lower bound list as they can be casted to the "Type Argument" if needed.
	    // Note: They are only casted to the "Type Argument" if needed like in next foreach-loop!
	    Util.addObjectToListWithUnknownType3(objList, new Object());
	    	    
	    System.out.println("----------- unboundWildcard objList content START");
	    for (Object obj : objList) {
			System.out.println(obj);
		}
	    System.out.println("----------- unboundWildcard objList content END");
	}

	/**
	 * <p>
	 * The {@link GenericPlayground#upperBoundWildcard()} shows that an upper bounded wildcard restricts the unknown type 
	 * to be a specific type or a subtype of that type and is represented using the extends keyword.
	 * In a similar way, a lower bounded wildcard restricts the unknown type to be a specific type or a supertype of that type. 
	 * 
	 * <p>
	 * A lower bounded wildcard is expressed using the wildcard character ('?'), followed by the super keyword, 
	 * followed by its lower bound: &lt;? super A&gt;.
	 * 
	 * <p>
	 * Say you want to write a method that puts Integer objects into a list. To maximise flexibility, 
	 * you would like the method to work on List&lt;Integer&gt;, List&lt;Number&gt;, and List&lt;Object&gt; — anything that can hold Integer values.
	 * <p>
	 * <strong>When to work with lower bound restriction:</strong> 
	 * <ul>
	 * <li>In case you need write access to the lower bound (in this example <tt>Integer</tt>) in collections.</li>
	 * <li>In case you need to be able to write any subtype of the lower bound in collections. See {@link #unboundWildcard()}.</li>
	 * </ul>
	 */
	public static void lowerBoundWildcard() {
		
		List<Object> objList = new ArrayList<>();
		List<Number> numList = new ArrayList<>();
		List<Integer> intList = new ArrayList<>();
		List<Double> doubleList = new ArrayList<>();
		
		Integer i = new Integer(1);
		Util.addIntegerToSuperTypeList(objList, i);
		Util.addIntegerToSuperTypeList(numList, i);
		Util.addIntegerToSuperTypeList(intList, i);
		//note: now all three lists contain an instance of type Integer
		
		// You will the following compilation error in case you uncomment next method execution.
		// This is due to the fact that Double is not of type Integer nor is it a subtype of it.
		// incompatible types: java.util.List<java.lang.Double> cannot be converted to java.util.List<? super java.lang.Integer>
		// Util.addIntegerToSuperTypeList(doubleList, i);
	}


	/**
	 * <p>PECS: <strong>"Producer Extends, Consumer Super".</strong>
	 * 
	 * <ul>
	 * <li><strong>"Producer Extends"</strong> - If you need a List to produce T values (=you want to read Ts from the list), you need to declare it with ? 
	 * extends T, e.g. List&lt;? extends Integer&gt;. But you cannot add new instances to this list.</li>
	 * <li><strong>"Consumer Super"</strong> - If you need a List to consume T values (=you want to add Ts into the list), you need to declare it with ? 
	 * super T, e.g. List&lt;? super Integer&gt;. But there is no guarantee about the type of object you may read from this list.</li>
	 * <li>If you need to read and add items to a list than you need to declare it exactly with no wildcard included, e.g. List&lt;Integer&gt;.</li>
	 * </ul>
	 * 
	 * <p>
	 * See also: https://stackoverflow.com/questions/4343202/difference-between-super-t-and-extends-t-in-java#4343547
	 */
	public static void pecs() {
		List<Object> objList = new ArrayList<>();
		List<Integer> intList = Arrays.asList(1, 2, 3);
		Util.copy(objList, intList);
		Util.printList(objList);
	}
}
