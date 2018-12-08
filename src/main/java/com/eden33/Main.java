package com.eden33;

import com.eden33.generics.GenericPlayground;
import com.eden33.generics.GenericPlaygroundExersice;
import com.eden33.generics2.GenericPlayground2;

/**
 * @author edi
 * 
 */
public class Main {

	public static void main(String args[]) {
		
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
	}
}
