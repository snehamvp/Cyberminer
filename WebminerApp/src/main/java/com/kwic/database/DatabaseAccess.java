package com.kwic.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseAccess {
	
	public Connection connection = null;

	public void connect() throws SQLException
	{
		String DRIVER = "com.mysql.jdbc.Driver";
		String URL = "jdbc:mysql://localhost:3306/asad";
		String UserName = "root";
		String Password = "root";
		try
		{
			Class.forName(DRIVER);
			
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		try
		{
			connection = DriverManager.getConnection(URL,UserName,Password);
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean isConnectionOpen(){
		return (connection != null);
	}
	public void disconnect() throws SQLException
	{
		if(isConnectionOpen()){
			connection.close();
		}
	}
}
