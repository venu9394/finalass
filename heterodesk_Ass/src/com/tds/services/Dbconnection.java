package com.tds.services;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.Properties;
public class Dbconnection {
	@SuppressWarnings("unused")
	public  static com.mysql.jdbc.Connection  getDBConection(){
		Connection con = null;
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
			String dir = System.getProperty("user.dir");
			System.out.println("Get Connection From Main Class"+dir);
			FileInputStream in = new FileInputStream(dir+"/config.properties");
			String Driver=null,URL=null,User=null,Password=null;
			if (in != null) {
				prop.load(in );
				Driver=prop.getProperty("Driver");
				URL=prop.getProperty("URL");
				User=prop.getProperty("User");
				Password=prop.getProperty("Password");
			} else {
				throw new FileNotFoundException("Property File '" + propFileName + "' not found in the classpath");
			}
			Class.forName(Driver);
			con = DriverManager.getConnection(URL,User,Password);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Get Connection From Main Class is Failed Due to"+e);
		}
		return (com.mysql.jdbc.Connection) con;
	}
}
