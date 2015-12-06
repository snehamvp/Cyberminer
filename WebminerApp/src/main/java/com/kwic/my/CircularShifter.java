package com.kwic.my;

import java.util.ArrayList;

public class CircularShifter {
	public static ArrayList<String> circularshifthelper(String newString){
		ArrayList<String> allListOfCircularShifts = new ArrayList<String>();
		String stringArray[] = newString.split("\\$");
		for(String s:stringArray){
			ArrayList<String> listOfCircularShifts = new ArrayList<String>();
			listOfCircularShifts=circularShift(s);
			for(int k =0 ; k<listOfCircularShifts.size();k++){
				allListOfCircularShifts.add(listOfCircularShifts.get(k));
			}
		}
		//System.out.println(allListOfCircularShifts);
		return allListOfCircularShifts;
	}
	
	private static ArrayList<String> circularShift(String newString1) {
		String stringArray[] = newString1.split(" ");
		int len = stringArray.length;
		ArrayList<String> listOfCircularShifts = new ArrayList<String>();
		listOfCircularShifts.add(newString1.trim());
		String tempString="";
		while(len>1){
			tempString = convert(stringArray);
			listOfCircularShifts.add(tempString.trim());
			stringArray = tempString.trim().split(" ");
			len--;
		}
		return listOfCircularShifts;
	}
	private static String convert(String[] stringArray) {
		String temp = stringArray[0];
		String tempString="";
		for (int i = 1; i < stringArray.length; i++){
			stringArray[i - 1] = stringArray[i];
		}
		stringArray[stringArray.length - 1] = temp;
		for(String s : stringArray){
			tempString = tempString + s + " ";
		}
		return tempString;
	}

	
//	public static void main(String[] args) {
//		System.out.println("hello");
//		 circularshifthelper("I am\r\nMe too");
//	}
}
