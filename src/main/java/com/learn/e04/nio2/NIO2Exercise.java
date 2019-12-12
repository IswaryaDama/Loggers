package com.learn.e04.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NIO2Exercise {

	private static final Logger logger = LoggerFactory.getLogger(NIO2Exercise.class);	
	static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static void main(String[] args) {
		Map<String, LocalDate> tabDetails = new HashMap<>(); 
		tabDetails =getExpiredTablets("Tablet.txt", "India");
		System.out.println("Expired Tablet Details are " + tabDetails);
		
		getFileswithjava("src");
        findFiles("Tablet.txt","src/main/resources/");
	}
static Map<String,LocalDate>getExpiredTablets(String filename, String manufacturer) {
	Map<String, LocalDate> tabDetails = new HashMap<>();
	try(Stream<String>datarepo = Files.lines(Paths.get("src/main/resources/", "Tablet.txt"))){
		
		tabDetails = datarepo.map(NIO2Exercise :: getTabletDetails).filter(t ->t.getManifacturer().equals(manufacturer)).filter(t->t.getExDate().isBefore(LocalDate.now()))
		        .collect(Collectors.toMap(Tablet::getName,Tablet::getExDate));   
		
		//return tabDetails;
		
	}catch(IOException ex) {
		logger.error("exception is " + ex);
	}
	
	return tabDetails;
	
}

public static Tablet getTabletDetails(String data){
	
	String[] details= data.split(",");
	LocalDate expireData = LocalDate.parse(details[3],format);
	Tablet t = new Tablet(details[0],details[1],details[2],expireData);
	
	return t;
	
}

static void getFileswithjava(String dirpath) {
	
	try(Stream<Path>path = Files.walk(Paths.get(dirpath))){
		
		System.out.println("*** Files ending with .java are ****");
		
		path.filter(p->p.toString().endsWith(".java")).forEach(System.out :: println);
		logger.warn("may get filenot founf exception if didn't mention proper file");
	}catch(IOException ex) {
		System.out.println(ex);
	}
	
}

static void findFiles(String file, String absPath) {
try(Stream<Path>path = Files.walk(Paths.get(absPath))){
		
		System.out.println("*** Files in abspath  are ****");
		
		path.filter(p->p.getFileName().toString().equals(file)).forEach(System.out::println);
}catch(IOException ex) {
	System.out.println(ex);
}
}	
}
