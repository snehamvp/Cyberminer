package com.kwic.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.kwic.database.DatabaseAccess;
import com.kwic.pojo.url;



public class urlDAO {
	DatabaseAccess dba;
	Connection connection;
	public urlDAO(){
		try 
		{
			dba = new DatabaseAccess();
			connect();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void connect() throws SQLException
	{
		try
		{
			dba.connect();
			connection = dba.connection;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public List<url> getAllUrls() {
		PreparedStatement prepStmt = null;
		List<url> urls = new ArrayList<url>();
		try {
			String cSQL = "select * from url";
			prepStmt = connection.prepareStatement(cSQL);
			ResultSet result = prepStmt.executeQuery();
			while (result.next())
			{
				url u = new url();
				u.setUrl(result.getString(1));
				u.setAlphabetized(result.getString(2));
				u.setDateadded(result.getTimestamp(3));
				System.out.println(u);
				urls.add(u);
			}
			return urls;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			prepStmt = null;
			return null;
		}
		finally
		{
			try 
			{
				dba.disconnect();
				connection.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	//	public static void main(String[] args) {
	//		urlDAO u = new urlDAO();
	//		u.getAllUrls();
	//
	//	}
	public void addURLs(String url, ArrayList<String> listAlphabetized) {
		System.out.println("in add url..");
		PreparedStatement prepStmt = null;
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		for(String alpha : listAlphabetized){
			System.out.println("hehe");
			try {
				String cSQL = "INSERT INTO URL VALUES(?,?,?)";
				prepStmt = connection.prepareStatement(cSQL);
				prepStmt.setString(1, url);
				prepStmt.setString(2, alpha);
				prepStmt.setTimestamp(3, currentTimestamp);	
				prepStmt.executeUpdate();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				prepStmt = null;
			}
		}

		try 
		{
			dba.disconnect();
			connection.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public ArrayList<String> getORurls(String str) {
		ArrayList<String> urls = new ArrayList<String>();
		PreparedStatement prepStmt = null;
		System.out.println("in get OR urls..");
		try {
			String cSQL = "select * from url where alphabetized like ?";
			prepStmt = connection.prepareStatement(cSQL);
			prepStmt.setString(1, "%"+str+"%");
			ResultSet result = prepStmt.executeQuery();
			while (result.next())
			{
				urls.add(result.getString(1));
			}
			return urls;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			prepStmt = null;
			return null;
		}
		finally
		{
			try 
			{
				dba.disconnect();
				connection.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	public ArrayList<String> getNOTurls(String str) {
		ArrayList<String> urls = new ArrayList<String>();
		PreparedStatement prepStmt = null;
		System.out.println("in get NOT urls..");
		try {
			String cSQL = "select * from url where alphabetized not like ?";
			prepStmt = connection.prepareStatement(cSQL);
			String setString = "%"+str+"%";
			prepStmt.setString(1, setString);
			ResultSet result = prepStmt.executeQuery();
			while (result.next())
			{
				urls.add(result.getString(1));
			}
			return urls;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			prepStmt = null;
			return null;
		}
		finally
		{
			try 
			{
				dba.disconnect();
				connection.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	public ArrayList<String> getANDurls(String str) {
		ArrayList<String> urls = new ArrayList<String>();
		PreparedStatement prepStmt = null;
		System.out.println("in get AND urls..");
		try {
			String cSQL = "select * from url where alphabetized like ?";
			prepStmt = connection.prepareStatement(cSQL);
			String setString = "%"+str+"%";
			prepStmt.setString(1, setString);
			ResultSet result = prepStmt.executeQuery();
			while (result.next())
			{
				urls.add(result.getString(1));
			}
			return urls;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			prepStmt = null;
			return null;
		}
		finally
		{
			try 
			{
				dba.disconnect();
				connection.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
