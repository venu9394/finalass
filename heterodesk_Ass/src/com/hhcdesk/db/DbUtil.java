package com.hhcdesk.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbUtil
{

	
	public static void main(String args[]){
		
		try {
			DbUtil.createConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static DataSource createConnection() throws SQLException {
		Connection connection = null;
		DataSource dataSource=null;
	try {
        // Get DataSource
        Context initContext  = new InitialContext();
        Context envContext  = (Context)initContext.lookup("java:/comp/env");
        dataSource = (DataSource)envContext.lookup("jdbc/testdb");
    
        System.out.println("LoadFile::"+dataSource.getConnection());
         
    } catch (NamingException e) {
        e.printStackTrace();
    }
	 return dataSource;
	}
	
	
}
	 
	/*    private static DbUtil instance = new DbUtil();
	    public static final String URL = "jdbc:mysql://localhost/test";
	    public static final String USER = "root";
	    public static final String PASSWORD = "umajava";
	    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 
	     
	    //private constructor
	    private  DbUtil() {
	        try {
	            Class.forName(DRIVER_CLASS);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
	     
	    private Connection createConnection() {
	        Connection connection = null;
	        try {
	            connection = DriverManager.getConnection(URL, USER, PASSWORD);
	        } catch (SQLException e) {
	            System.out.println("ERROR: Unable to Connect to Database.");
	        }
	        return connection;
	    }   
	     
	    public static Connection getConnection() {
	        return instance.createConnection();
	    }*/
	//}

