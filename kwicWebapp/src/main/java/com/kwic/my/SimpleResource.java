package com.kwic.my;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/hello")
public class SimpleResource {
 
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getMsg(@PathParam("param") String msg) {
		JSONObject joReturn = new JSONObject();
		ArrayList<String> allListOfCircularShifts = new ArrayList<String>();
		ArrayList<String> listAlphabetized = new ArrayList<String>();
		allListOfCircularShifts=CircularShifter.circularshifthelper(msg);
		listAlphabetized=Alphabetizer.alphabetize(allListOfCircularShifts);
		try {
			joReturn.put("circularlyshifted", allListOfCircularShifts);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			joReturn.put("alphabetized", listAlphabetized);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return joReturn;
	}
 
}