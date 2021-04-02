package com.gitfollowers.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class CommonFunctions {

	public static List<String> getConsecutiveOccurence(List<String> lstOfStrings) {
		List<String> lstOfConsecStringsCount = new ArrayList<>();
		
		for (String word: lstOfStrings) {
			lstOfConsecStringsCount.add(getConsecutiveOccurenceOfChar(word.toCharArray()));
		}
		
		return lstOfConsecStringsCount;
	}
	
	private static String getConsecutiveOccurenceOfChar(char[] chars) {
		
		String finalWord = null;
		Collection<String> charCounts = new ArrayList<>();
		char current = chars[0];
	    int count = 1;
	    
	    for(int i = 1; i < chars.length; i++){
	      char c = chars[i];
	      if(current == c) {
	        count++;
	      } else {
	        charCounts.add(current + "" + count);
	        count = 1;
	        current = c;
	      }
	    }
	    	    
	    charCounts.add(current + "" + count);
	    finalWord = String.join("", charCounts);
	    
		return finalWord;
	}
	
	
	
}
 
