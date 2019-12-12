package com.loggers.LoggerEx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LambdaExercise {
	private static final Logger logger = LoggerFactory.getLogger(LambdaExercise.class);
	static List<String> countries = new ArrayList<>();
	 static Map<String,String> countryCapitals = new HashMap<>();
	static {
		countries.addAll(Arrays.asList("India" , "USA", "China", "UK" , "Switzerland"));		
		countryCapitals.put("India", "Delhi");
		countryCapitals.put("USA", "NYC");
		countryCapitals.put("China", "Bejing");
		countryCapitals.put("UK", "London");
	
	}
	
	static void displayCountries() {
		
		countries.forEach(c -> System.out.println(c));
		logger.info("displayed list of countries");
	}
	
	static void displayCountryCapitals() {
		
		countryCapitals.forEach((key,value) -> System.out.println("Country is " + key + "capital is " + value));
	}
	
	static void sortCountriesByName() {
		
		Collections.sort(countries, (c1,c2) -> c2.compareToIgnoreCase(c1));
		
		System.out.println("countries after sorting by name ");
		
		countries.forEach(c -> System.out.println(c));
	}
	
	static void sortCountriesBylength() {
		Collections.sort(countries, (c1,c2) -> 
		{
			if((c1.length()-c2.length())==0) 
			{
			return c1.compareToIgnoreCase(c2);
		}
		else 
		{
			 return  c2.length()-c1.length();
			 }});
		System.out.println("countries after sorting by length ");
		countries.forEach(c -> System.out.println(c));
	}
	
	static void removeCountry(String name) {
		
		if(name.length()>6) {
			countries.remove(name);
		}
		
		logger.info("after removing the country"); 
		countries.forEach(c -> System.out.println(c));
		
	}
	
	static void removeCountry(List<String>names ,Predicate<String> predicate) {
		
		Iterator itr = names.iterator();
		while(itr.hasNext()) {
			if(predicate.test((String) itr.next())) {
			
				itr.remove();
				logger.warn("collections remove method should not use here");
			}
		}
		
		System.out.println("after removing countries of length more than six");
		
		countries.forEach(c -> System.out.println(c));
		
	}
	public static void main(String[] args) {
		
		displayCountries();
		sortCountriesByName();
		sortCountriesBylength();
       removeCountry(countries , p -> p.length()>6);
	}
	
	

}
