package com.learn.e03.collectors;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;

import com.learn.e03.collectors.Player;


public class StreamCollectorsExercise {
     
	static List<Player> players = Arrays.asList(new Player[] {
			new Player("Venkatesh", 300, 100000, 120 , new Country(101 ,"India")),
			new Player("Anwesh" , 105, 450, 100 , new Country(101 ,"India")),
			new Player("Jagadesh" , 110, 800, 98 ,new Country(102 ,"USA")),
			new Player("Bharath" , 8, 500, 80 , new Country(103 ,"London"))
	});
	
	static void getPlayersByCountry() {
		
		Map<String,List<Player>> playerList = players.stream()
				                                     .collect(Collectors.groupingBy(p -> p.getCountry().getCountryName()));
				                                     //,Collectors.mapping(Player :: getPlayerName, Collectors.toList())));
	
	   System.out.println(playerList);
	
	}
	
	static void getPlayerNamesByCountry() {
	
		Map<String,List<String>> playerNames = players.stream().filter(p->p.getMatchesPlayed()>100)
                .collect(Collectors.groupingBy(p -> p.getCountry().getCountryName(),Collectors.mapping(Player :: getPlayerName, Collectors.toList())));

		System.out.println(playerNames);
		
	}
	
	static void getTotalPlayersByCountry() {
		
	Map<String, Long> totalPlayers = 
			players.stream().collect(
					Collectors.groupingBy(
							(p -> p.getCountry().getCountryName()),Collectors.counting()));
			                                                   
    System.out.println("total players by country are" + totalPlayers);
	}
	
	static void getTotalRunsByCountry() {
		
		
		Map<String,Integer> totalRuns = players.stream().collect(
				Collectors.groupingBy((p -> p.getCountry().getCountryName()),Collectors.summingInt(Player :: getRuns)));
		
		System.out.println("total runs by country are" + totalRuns);
	}
	
	static void getMaxScoreByCountry(){
		
		Map<String,Optional<Integer>> maxScore = players.stream()
				                                        .collect(Collectors.groupingBy(
				                                        		(p -> p.getCountry().getCountryName()),Collectors.mapping(Player::getRuns, Collectors.maxBy(Integer::compare))));
		
		System.out.println("maxScore by country " + maxScore);		
		
	}
	
	static void getPlayerNamesStringByCountry() {
		Map<String,String> playerStr = players.stream().collect(Collectors.groupingBy(p->p.getCountry().getCountryName(),Collectors.mapping(Player :: getPlayerName, Collectors.joining(","))));
	
		System.out.println("Player names as String are " + playerStr);
	
	}
	
	public static void main(String[] args) {
		getPlayersByCountry();
		getPlayerNamesByCountry();
		getTotalPlayersByCountry();
		getTotalRunsByCountry();
		getMaxScoreByCountry();
		getPlayerNamesStringByCountry();
		}
}
