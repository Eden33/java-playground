package com.eden33.generics2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.eden33.generics.GenericPlayground;
import com.eden33.generics.GenericPlaygroundExersice;

/**
 * @author edi
 *
 */
public class GenericPlayground2 {
	
	/**
	 * <p>Generic subtyping works different than you are used from non-generic subtyping.
	 * 
	 * <p><tt>ClassB</tt> is a subtype of <tt>ClassA</tt>. Therefore you can assign an instance of <tt>ClassB</tt>
	 * to an instance of <tt>ClassA</tt>. However, the same is not possible for generic instances. The subtype for 
	 * both generic list instances given is <tt>List&lt;?&gt;</tt>.
	 * 
	 * <p>
	 * See also: 
	 * <ul>
	 * <li>{@link GenericPlayground#understandSubTyping1}</li>
	 * <li>{@link GenericPlayground#understandSubTyping2}</li>
	 * <li>{@link GenericPlayground#understandSubTyping3}</li>
	 * </ul>
	 */
	public static void genericSubTypingWorksDifferent() {
		ClassB cB = new ClassB();
		ClassA ca = cB;
		
		List<ClassB> lB = new ArrayList<>();
		// compliation error:
		// incompatible types: List<ClassB> cannot be converted to List<ClassA>
		// List<ClassA> lA = lB;
	}

	/**
	 * <p>Like {@link GenericPlayground#pecs()} already explains for producers:
	 * 
	 * <p><strong>Add new values:</strong> 
	 * You cannot add any new instances to a producer <tt>List&lt;? extends ClassA&gt;</tt>. This is due too 
	 * the fact that compiler cannot guarantee the exact <tt>Parameterized Type</tt> the producer finally references at runtime. 
	 * It may be <tt>List&lt;ClassA&gt;</tt> or a <tt>List&lt;ClassB&gt;</tt> or a <tt>List&lt;ClassC&gt;.</tt>
	 * 
	 * <p><strong>Read values:</strong>
	 * The upper bound is the only value you can read. This is due to the fact 
	 * that compiler cannot guarantee the exact <tt>Parameterized Type</tt> the producer finally references at runtime.
	 * However, you can be sure that it always references the the upper bound <tt>Type Argument</tt> (in this case 
	 * <tt>ClassA</tt>) or a subtype of that class. 
	 **/
	public static void understandPecsProducer() {
		List<ClassA> lA = new ArrayList<>();
		List<ClassB> lB = new ArrayList<>();
		List<ClassC> lC = new ArrayList<>();
		List<? extends ClassA> lAProducer = new ArrayList<>();
		
		double randValue = Math.random();
		if(randValue <= 0.33) {
			lAProducer = lA;
		} else if(randValue <= 0.66){
			lAProducer = lB;
		} else {
			lAProducer = lC;
		}
		
		// WRITE 
		
		// Compilation error: 
		// The method add(capture#4-of ? extends ClassA) in the type List<capture#4-of ? extends ClassA>
		// is not applicable for the arguments (ClassA)
//		lAProducer.add(new ClassA());
//		lAProducer.add(new ClassB());
//		lAProducer.add(new ClassC());
		
		// READ
		
		try {
			ClassA cA = lAProducer.get(0);
		} catch(IndexOutOfBoundsException e) {}

		// Compilation error:
		// cannot convert from capture#5-of ? extends ClassA to ClassB
//		ClassB cB = lAProducer.get(0);
//		ClassC cC = lAProducer.get(0);
		
	}
	
	/**
	 * <p>Like {@link GenericPlayground#pecs()} already explains for consumers:
	 * 
	 * <p><strong>Add new values:</strong> 
	 * You can add the lower bound or any subtype to a consumer <tt>List&lt;? super ClassB&gt;</tt>. This is due to the 
	 * fact that the compiler is able to guarantee that these types are always compatible with the exact <tt>Parameterized Type</tt> 
	 * the consumer references at runtime.
	 * 
	 * <p><strong>Read values:</strong>
	 * Object is the only value you can read. This is due to the fact that the compiler cannot guarantee the exact <tt>Parameterized Type</tt> the consumer 
	 * finally references at runtime. It may be <tt>List&lt;ClassA&gt;</tt> or <tt>List&lt;ClassB&gt;</tt>.
	 */
	public static void understandPecsConsumer() {
		List<ClassA> lA = new ArrayList<>();
		List<ClassB> lB = new ArrayList<>();
		List<ClassC> lC = new ArrayList<>();
		List<? super ClassB> lBConsumer = new ArrayList<>();
		
		double randValue = Math.random();
		if(randValue <= 0.50) {
			lBConsumer = lA;
		} else {
			lBConsumer = lB;
		} 
		
		// Compilation error: 
		// cannot convert from List<ClassC> to List<? super ClassB>
		// ---
		// Note: The compiler restricts the exact "Parameterized Type" of the generic class to be the lower bound or 
		// a child of that type.
		// ---
//		lBConsumer = lC;
		
		// WRITE 
		
		// Compilation error:
		// The method add(capture#7-of ? super ClassB) in the type List<capture#7-of ? super ClassB>
		// is not applicable for the arguments (ClassA)
//		lBConsumer.add(new ClassA());
		lBConsumer.add(new ClassB());
		lBConsumer.add(new ClassC());
		
		// READ
		
		// Compilation error: 
		// Type mismatch: cannot convert from capture#9-of ? super ClassB to ClassA
//		ClassA cA = lBConsumer.get(0);
//		ClassB cB = lBConsumer.get(0);
//		ClassC cC = lBConsumer.get(0);
		Object obj = lBConsumer.get(0);
	}
	
	public static void pecsInAcctionCollectionsMax() {
		List<ClassA> lA = new ArrayList<>();
		lA.add(new ClassA());
		lA.add(new ClassB());
		lA.add(new ClassC());
		lA.add(new ClassA());
		lA.add(new ClassB());
		lA.add(new ClassC());
		Collections.shuffle(lA);
		ClassA maxC = GenericPlaygroundExersice.max(lA);
		System.out.println("Max class: " + maxC);
	}
}
