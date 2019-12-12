package com.learn.e03.collectors;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SpecialStreamsExercise {

	
	static void getSquaresOfThree() {
		
		System.out.println(" squares of three are ");
		
	LinkedList<Integer> squres = IntStream.rangeClosed(20,50)
		                          .filter(n->(n%2)!=0)
		                          .filter(n-> n%3==0)
		                          .map(n-> n*n)
		                          .boxed()
		                          .collect(Collectors.toCollection(LinkedList :: new));
	
		squres.forEach(System.out :: println);
	}
	
	static void getMultiplesOfFive() {
		 System.out.println("multiples of five are ");
		 int[] result =Stream.iterate(5, n->n+5).limit(20)
				             .mapToInt(n->n) 
		                     .toArray();
		 for(int i : result) {
			 System.out.println(i);
		 }
	}
	
	public static void main(String[] args) {
		
		getSquaresOfThree();
		getMultiplesOfFive();
	}
	
}
