package com.kwic.my;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.kwic.dao.urlDAO;

@Path("/hello")
public class SimpleResource {
	@GET
	@Path("{param}/{url}")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getMsg(@PathParam("param") String msg,@PathParam("url") String url) {
		JSONObject joReturn = new JSONObject();
		urlDAO u = new urlDAO();
		ArrayList<String> allListOfCircularShifts = new ArrayList<String>();
		ArrayList<String> listAlphabetized = new ArrayList<String>();
		ArrayList<String> noiseRemoved = new ArrayList<String>();
		allListOfCircularShifts=CircularShifter.circularshifthelper(msg);
		listAlphabetized=Alphabetizer.alphabetize(allListOfCircularShifts);
		for(String s:listAlphabetized){
			String[] tokens = s.split(" ");
			if(!(tokens[0].equalsIgnoreCase("the")||tokens[0].equalsIgnoreCase("a")||tokens[0].equalsIgnoreCase("an")||tokens[0].equalsIgnoreCase("of")||tokens[0].equalsIgnoreCase("for"))){
				noiseRemoved.add(s);
			}
		}

		System.out.println("Calling add URLs");
		u.addURLs(url,noiseRemoved);
		try {
			joReturn.put("circularlyshifted", allListOfCircularShifts);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			joReturn.put("alphabetized", noiseRemoved);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			joReturn.put("url", url);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return joReturn;
	}


	@GET
	@Path("/url/{words}/{option}")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getURL(@PathParam("words") String words,@PathParam("option") int option) {
		JSONObject joReturn = new JSONObject();
		Set<String> url = new HashSet<String>();
		Set<String> urlAnd = new HashSet<String>();
		Set<String> urlNot = new HashSet<String>();
		System.out.println("--------In 2nd webservice-------------");
		switch(option){
		case 1:
			System.out.println("--------In case 1-------------");
			String[] strArray11 = words.split(" ");
			ArrayList<String> returnList1 = new ArrayList<String>();
			for(String str:strArray11){
				ArrayList<String> urls = new ArrayList<String>();
				urlDAO u1 = new urlDAO();
				urls = u1.getANDurls(str);
				returnList1 = intersect(returnList1,urls);
				Collections.sort(returnList1,String.CASE_INSENSITIVE_ORDER);
			}
			for(String url1 : returnList1){
				urlAnd.add(url1);
			}
			try {
				joReturn.put("url", urlAnd);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			System.out.println("--------In case 2-------------");
			String[] strArray = words.split(" ");
			for(String str:strArray){
				ArrayList<String> urls = new ArrayList<String>();
				urlDAO u1 = new urlDAO();
				urls = u1.getORurls(str);
				Collections.sort(urls,String.CASE_INSENSITIVE_ORDER);
				for(String url1 : urls){
					url.add(url1);
				}
			}
			try {
				joReturn.put("url", url);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			System.out.println("--------In case 3-------------");
			String[] strArray1 = words.split(" ");
			ArrayList<String> returnList = new ArrayList<String>();
			for(String str:strArray1){
				ArrayList<String> urls = new ArrayList<String>();
				urlDAO u1 = new urlDAO();
				urls = u1.getNOTurls(str);
				returnList = intersect(returnList,urls);
				Collections.sort(returnList,String.CASE_INSENSITIVE_ORDER);
			}
			for(String url1 : returnList){
				urlNot.add(url1);
			}
			try {
				joReturn.put("url", urlNot);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		return joReturn;
	}


	private ArrayList<String> intersect(ArrayList<String> returnList,ArrayList<String> urls) {
		ArrayList<String> list = new ArrayList<String>();
		if(returnList.size()<=0){
			return urls;
		}
		for (String t : returnList) {
			if(urls.contains(t)) {
				list.add(t);
			}
		}

		return list;
	}


	@GET
	@Path("/query")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getNewUrl(@QueryParam("msg") String msg,@QueryParam("url") String url) {
		JSONObject joReturn = new JSONObject();
		urlDAO u = new urlDAO();
		ArrayList<String> allListOfCircularShifts = new ArrayList<String>();
		ArrayList<String> listAlphabetized = new ArrayList<String>();
		ArrayList<String> noiseRemoved = new ArrayList<String>();
		allListOfCircularShifts=CircularShifter.circularshifthelper(msg);
		listAlphabetized=Alphabetizer.alphabetize(allListOfCircularShifts);
		for(String s:listAlphabetized){
			String[] tokens = s.split(" ");
			if(!(tokens[0].equalsIgnoreCase("the")||tokens[0].equalsIgnoreCase("a")||tokens[0].equalsIgnoreCase("an")||tokens[0].equalsIgnoreCase("of")||tokens[0].equalsIgnoreCase("for"))){
				noiseRemoved.add(s);
			}
		}

		System.out.println("Calling add URLs");
		u.addURLs(url,noiseRemoved);
		try {
			joReturn.put("circularlyshifted", allListOfCircularShifts);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			joReturn.put("alphabetized", noiseRemoved);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			joReturn.put("url", url);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return joReturn;
	}
}