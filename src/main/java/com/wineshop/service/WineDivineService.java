package com.wineshop.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wineshop.model.User;
import com.wineshop.model.Wine;
import com.wineshop.model.WineDistance;
import com.wineshop.repository.UserRepository;
import com.wineshop.repository.WineRepository;

@Service
public class WineDivineService {
	@Autowired
	WineRepository wineRepo;
	
	
	public String SentenceToTokens(String description) {
		StringBuilder descriptors =new StringBuilder("");
		try(Scanner sc = new Scanner(new File("CWW.txt"))) {
			sc.useDelimiter("\n");   //sets the delimiter pattern  

			while (sc.hasNext())  //returns a boolean value  
			{  
				String[] words = sc.next().split("	");
				if(description.toLowerCase().contains(words[2].toLowerCase()))
				{
					descriptors.append(words[3]+',');
//					System.out.println(words[2] + ": "+ words[3]);
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return descriptors.substring(0, descriptors.length()-1).toString();
	}
	

	public List<Wine> getSimilarWines(Wine wine1, String region){
		List<Wine> wineList=null;
		if(region.isEmpty())
			wineList = wineRepo.findAll();
		else {
	
		
		}
		List<WineDistance> wineDistance = new ArrayList<>();
		for(Wine wine2:wineList) {
			if(!wine1.getTitle().equals(wine2.getTitle()))
			{	
				wineDistance.add(new WineDistance(wine2,jaccardDistance(wine1, wine2)));
			}
		}
		Collections.sort(wineDistance);
		List<Wine> similarWines = new ArrayList<>();
		for(int i =0; i<10; i++) {
//			System.out.println(wineDistance.get(i));
			similarWines.add(wineDistance.get(i).getWine());
		}
		return similarWines;
	}
	
	//Calculate jaccard distance between two wines 
	public double jaccardDistance(Wine wine1, Wine wine2) {
		Set<String> setWine1 = new HashSet<String>(Arrays.asList(wine1.getDescriptors().split(",")));
		Set<String> setWine2 = new HashSet<String>( Arrays.asList(wine2.getDescriptors().split(",")));
		Set<String> intersection = new HashSet<String>(setWine1); // use the copy constructor
		intersection.retainAll(setWine2);
		
		Set<String> union = new HashSet<String>(setWine1);
		union.addAll(setWine2);	
		return intersection.size()/(double)union.size();
	
}
	

	

}
