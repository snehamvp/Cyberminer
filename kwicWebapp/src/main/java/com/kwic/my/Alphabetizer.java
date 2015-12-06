package com.kwic.my;

import java.util.ArrayList;
import java.util.Collections;

public class Alphabetizer {
public static ArrayList<String> alphabetize(ArrayList<String> allListOfCircularShifts){
	ArrayList<String> listAlphabetized = new ArrayList<String>();
	for(int k =0 ; k<allListOfCircularShifts.size();k++){
		listAlphabetized.add(allListOfCircularShifts.get(k));
	}
	Collections.sort(listAlphabetized,String.CASE_INSENSITIVE_ORDER);
	return listAlphabetized;
}
}
