package com.eden33;

import com.eden33.generics.GenericPlayground;
import com.eden33.generics.GenericPlaygroundExersice;
import com.eden33.generics2.GenericPlayground2;
import com.eden33.reflection.ClassAnalyzer;
import com.eden33.reflection.JsonWriter6;
import com.eden33.reflection.PaymentServiceTest;
import com.eden33.reflection.TestingFramework;
import com.eden33.reflection.model.Address6;
import com.eden33.reflection.model.AnalyzeMyInterfaces;
import com.eden33.reflection.ObjectSizeCalculator;
import com.eden33.reflection.model.AccountSummary;

/**
 * @author edi
 * 
 */
public class Main {

	public static void main(String args[]) {

		/**
		 * GenericPlayground
		 */
		
		System.out.println("\n ------ Generic Playground: Type Arguments vs Parameters ------ START\n");
		GenericPlayground.typArgumentsVsParameters();
		System.out.println("\n ------ Generic Playground: Type Arguments vs Parameters ------ END\n");

		System.out.println("\n ------ Generic Playground: Raw Type ------ START\n");
		GenericPlayground.rawType();
		System.out.println("\n ------ Generic Playground: Raw Type ------ END\n");

		System.out.println("\n ------ Generic Playground: Method Declaration ------ START\n");
		GenericPlayground.methodDeclaration();
		System.out.println("\n ------ Generic Playground: Method Declaration ------ END\n");

		System.out.println("\n ------ Generic Playground: Bounded Type Parameters ------ START\n");
		GenericPlayground.boundedTypeParameters();
		System.out.println("\n ------ Generic Playground: Bounded Type Parameters ------ END\n");

		System.out.println("\n ------ Generic Playground: Understanding Subtyping 1 ------ START\n");
		GenericPlayground.understandSubTyping1();
		System.out.println("\n ------ Generic Playground: Understanding Subtyping 1 ------ END\n");

		System.out.println("\n ------ Generic Playground: Understanding Subtyping 2 ------ START\n");
		GenericPlayground.understandSubTyping2();
		System.out.println("\n ------ Generic Playground: Understanding Subtyping 2 ------ END\n");

		System.out.println("\n ------ Generic Playground: Understanding Subtyping 3 ------ START\n");
		GenericPlayground.understandSubTyping3();
		System.out.println("\n ------ Generic Playground: Understanding Subtyping 3 ------ END\n");

		/**
		 * Type inference is a Java compiler's ability to look at each method invocation and corresponding
		 * declaration to determine the type argument (or arguments) that makes the invocation applicable. 
		 * The inference algorithm determines the types of the arguments and, if available, the type that the result
		 * is being assigned to, or returned. Finally, the inference algorithm tries to find the most specific type 
		 * that works with all of the arguments.
		 */
		System.out.println("\n ------ Generic Playground: Type Inference Based on Return Type 1 ------ START\n");
		GenericPlayground.typeInferenceBasedOnReturnType1();
		System.out.println("\n ------ Generic Playground: Type Inference Based on Return Type 1 ------ END\n");

		System.out.println("\n ------ Generic Playground: Type Inference Based on Return Type 2 ------ START\n");
		GenericPlayground.typeInferenceBasedOnReturnType2();
		System.out.println("\n ------ Generic Playground: Type Inference Based on Return Type 2 ------ END\n");

		System.out.println("\n ------ Generic Playground: Type Inference Based on Method Arguments ------ START\n");
		GenericPlayground.typeInferenceBasedOnMethodArguments();
		System.out.println("\n ------ Generic Playground: Type Inference Based on Method Arguments ------ END\n");

		System.out.println("\n ------ Generic Playground: Type Inference Based on the Diamond ------ START\n");
		GenericPlayground.typeInferenceBasedOnTheDiamond();
		System.out.println("\n ------ Generic Playground: Type Inference Based on the Diamond ------ END\n");

		System.out.println("\n ------ Generic Playground: Type Inference for Class Constructors ------ START\n");
		GenericPlayground.typeInferenceForClassConstructors();
		System.out.println("\n ------ Generic Playground: Type Inference for Class Constructors ------ END\n");
		
		/**
		 * In generic code, the question mark (?), called the wildcard, represents an unknown type. 
		 * The wildcard can be used in a variety of situations: as the type of a parameter, field, or local variable; 
		 * sometimes as a return type (though it is better programming practice to be more specific). 
		 * The wildcard is never used as a type argument for a generic method invocation, a generic class instance creation, or a supertype.
		 */
		System.out.println("\n ------ Generic Playground: Upper Bound Wildcard ------ START\n");
		GenericPlayground.upperBoundWildcard();
		System.out.println("\n ------ Generic Playground: Upper Bound Wildcard ------ END\n");

		System.out.println("\n ------ Generic Playground: Unbound Wildcard ------ START\n");
		GenericPlayground.unboundWildcard();
		System.out.println("\n ------ Generic Playground: Unbound Wildcard ------ END\n");

		System.out.println("\n ------ Generic Playground: Lower Bound Wildcard ------ START\n");
		GenericPlayground.lowerBoundWildcard();
		System.out.println("\n ------ Generic Playground: Lower Bound Wildcard ------ END\n");

		System.out.println("\n ------ Generic Playground: PECS Principle ------ START\n");
		GenericPlayground.pecs();
		System.out.println("\n ------ Generic Playground: PECS Principle ------ END\n");
		
		System.out.println("\n ------ Generic Playground Exercise ------ START\n");
		GenericPlaygroundExersice.execute();
		System.out.println("\n ------ Generic Playground Exercise ------ END\n");
		
		/**
		 * GenericPlayeround2
		 */
		System.out.println("\n ------ Generic Playground 2: Generic Subtyping Works Differently ------ START\n");
		GenericPlayground2.genericSubTypingWorksDifferent();
		System.out.println("\n ------ Generic Playground 2: Generic Subtyping Works Differently ------ END\n");

		System.out.println("\n ------ Generic Playground 2: Understanding PECS Producer ------ START\n");
		GenericPlayground2.understandPecsProducer();
		System.out.println("\n ------ Generic Playground 2: Understanding PECS Producer ------ END\n");

		System.out.println("\n ------ Generic Playground 2: Understanding PECS Consumer ------ START\n");
		GenericPlayground2.understandPecsConsumer();
		System.out.println("\n ------ Generic Playground 2: Understanding PECS Consumer ------ END\n");

		System.out.println("\n ------ Generic Playground 2: PECS in Action with Collections.max ------ START\n");
		GenericPlayground2.pecsInAcctionCollectionsMax();
		System.out.println("\n ------ Generic Playground 2: PECS in Action with Collections.max ------ END\n");

		/**
		 * Reflection
		 */

		System.out.println("\n ------ Play with Reflection - Analyze classes ------ START\n");

		ClassAnalyzer.analyze(Boolean.class);
        ClassAnalyzer.analyze(boolean.class);
        Address6 address = new Address6("Main St", (short) 101, "12345");
        ClassAnalyzer.analyze(address.getClass());
		ClassAnalyzer.analyze(AnalyzeMyInterfaces.class);

		System.out.println("\n ------ Play with Reflection - Analyze classes ------ END\n");

		System.out.println("\n ------ Play with Reflection - Methods Discovery ------ START\n");
		
		TestingFramework testingFramework = new TestingFramework();
		try {
			testingFramework.runTestSuite(PaymentServiceTest.class);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		System.out.println("\n ------ Play with Reflection - Methods Discovery ------ END\n");

		System.out.println("\n ------ Play with Reflection - Java Modifiers Discovery ------ START\n");

		Address6 address6 = new Address6("Main Street", (short) 1, "12345");
		try {
			System.out.println(JsonWriter6.objectToJson(address6));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		System.out.println("\n ------ Play with Reflection - Java Modifiers Discovery ------ END\n");

		System.out.println("\n ------ Play with Reflection - Inspection of Fields & Arrays ------ START\n");

		AccountSummary accountSummary = new AccountSummary("John", "Smith", (short) 20, 100_000);
		ObjectSizeCalculator calculator = new ObjectSizeCalculator();
		long size = calculator.sizeOfObject(accountSummary);
		System.out.println("Estimated size of AccountSummary object: " + size + " bytes");

		System.out.println("\n ------ Play with Reflection - Inspection of Fields & Arrays ------ END\n");
	}
}
