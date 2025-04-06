package com.eden33;

import com.eden33.generics.GenericPlayground;
import com.eden33.generics.GenericPlaygroundExersice;
import com.eden33.generics2.GenericPlayground2;
import com.eden33.reflection.Address6;
import com.eden33.reflection.ClassAnalyzer;
import com.eden33.reflection.AnalyzeMyInterfaces;
import com.eden33.reflection.JsonWriter6;
import com.eden33.reflection.PaymentServiceTest;
import com.eden33.reflection.TestingFramework;

/**
 * @author edi
 * 
 */
public class Main {

	public static void main(String args[]) {

		/**
		 * GenericPlayground
		 */
		
		GenericPlayground.typArgumentsVsParameters();
		GenericPlayground.rawType();
		GenericPlayground.methodDeclaration();
		GenericPlayground.boundedTypeParameters();
		GenericPlayground.understandSubTyping1();
		GenericPlayground.understandSubTyping2();
		GenericPlayground.understandSubTyping3();

		/**
		 * Type inference is a Java compiler's ability to look at each method invocation and corresponding
		 * declaration to determine the type argument (or arguments) that makes the invocation applicable. 
		 * The inference algorithm determines the types of the arguments and, if available, the type that the result
		 * is being assigned to, or returned. Finally, the inference algorithm tries to find the most specific type 
		 * that works with all of the arguments.
		 */
		GenericPlayground.typeInferenceBasedOnReturnType1();
		GenericPlayground.typeInferenceBasedOnReturnType2();
		GenericPlayground.typeInferenceBasedOnMethodArguments();
		GenericPlayground.typeInferenceBasedOnTheDiamond();
		GenericPlayground.typeInferenceForClassConstructors();
		
		/**
		 * In generic code, the question mark (?), called the wildcard, represents an unknown type. 
		 * The wildcard can be used in a variety of situations: as the type of a parameter, field, or local variable; 
		 * sometimes as a return type (though it is better programming practice to be more specific). 
		 * The wildcard is never used as a type argument for a generic method invocation, a generic class instance creation, or a supertype.
		 */
		GenericPlayground.upperBoundWildcard();
		GenericPlayground.unboundWildcard();
		GenericPlayground.lowerBoundWildcard();
		GenericPlayground.pecs();
		
		GenericPlaygroundExersice.execute();
		
		/**
		 * GenericPlayeround2
		 */
		GenericPlayground2.genericSubTypingWorksDifferent();
		GenericPlayground2.understandPecsProducer();
		GenericPlayground2.understandPecsConsumer();
		GenericPlayground2.pecsInAcctionCollectionsMax();

		/**
		 * Reflection
		 */

		System.out.println("\r\n ------ Play with Reflection - Analyze classes ------ START\r\n");

		ClassAnalyzer.analyze(Boolean.class);
        ClassAnalyzer.analyze(boolean.class);
        Address6 address = new Address6("Main St", (short) 101, "12345");
        ClassAnalyzer.analyze(address.getClass());
		ClassAnalyzer.analyze(AnalyzeMyInterfaces.class);

		System.out.println("\r\n ------ Play with Reflection - Analyze classes ------ END\r\n");

		TestingFramework testingFramework = new TestingFramework();
		try {
			testingFramework.runTestSuite(PaymentServiceTest.class);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		System.out.println("\r\n ------ Play with Reflection - Methods Discovery ------ END\r\n");

		System.out.println(" ------ Play with Reflection - Java Modifiers Discovery ------ START\r\n");

		Address6 address6 = new Address6("Main Street", (short) 1, "12345");
		try {
			System.out.println(JsonWriter6.objectToJson(address6));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		System.out.println("\r\n ------ Play with Reflection - Java Modifiers Discovery ------ END\r\n");


	}
}
