package com.hhcdesk.service;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hhcdesk.db.DbUtil;


public class GetGeoLocation {
	// http://localhost:8080/RESTfulExample/json/product/get
	
	public static void main(String[] args) throws IOException{
		
		DataSource dataSource=null;
		/*try {
			dataSource=DbUtil.createConnection();
			System.out.println("dataSource::"+dataSource);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	}
/*//=================================================================	
	public static  Object CallQuote(String UserID,String Password){	
	 	 boolean flag=false;
		 Object obj2=null;
		 String stream = null;
		try{
	        URL url = new URL("http://quotes.rest/qod.json?category=management");
	        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
	        if(urlConnection.getResponseCode() == 200)
	        {
	            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
	            BufferedReader r = new BufferedReader(new InputStreamReader(in));
	            StringBuilder sb = new StringBuilder();
	            String line;
	            while ((line = r.readLine()) != null) {
	                sb.append(line);
	            }
	            stream = sb.toString();
	            urlConnection.disconnect();
	            flag=true;
	        }
	        else
	        {
	        	System.out.println("URL-CONNECTION::"+urlConnection.getResponseCode());
	        }
	    }catch (MalformedURLException e){
	    	obj2=e;
	        e.printStackTrace();
	        
	    }catch(IOException e){
	    	obj2=e;
	        e.printStackTrace();
	    }finally {

	    }
	    // Return the data from specified url
		if(flag){
	    return stream;
		}else{
			 return null;
		}
	 }// Function Closed	
	*/
	//=====================================
	/*public String getMACAddress(String ip){ 
        String str = ""; 
        String macAddress = ""; 
        try { 
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip); 
            InputStreamReader ir = new InputStreamReader(p.getInputStream()); 
            LineNumberReader input = new LineNumberReader(ir); 
            for (int i = 1; i <100; i++) { 
                str = input.readLine(); 
                if (str != null) { 
                    if (str.indexOf("MAC Address") > 1) { 
                        macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length()); 
                        break; 
                    } 
                } 
            } 
        } catch (IOException e) { 
            e.printStackTrace(System.out); 
        } 
        return macAddress; 
    }*/
	
	//====================================
	public static String GetGeoLocation(String username,String ipAddress,java.sql.Connection Dbconn) throws JSONException, MalformedURLException, IOException {
		
		 String city=null;
		 
		 PreparedStatement ps = null;
		 String sqlInsert="insert into hclhrm_prod_others.tbl_iconnect_usage_monitoring (USERID,IP,country_code,country_name, Object) values(?,?,?,?,?)";
		 try {
			 ps = (PreparedStatement) Dbconn.prepareStatement(sqlInsert);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
	String ip_add= ipAddress;
	 String country_code="IND";
	 String region_code="NA";
	 String country_name="INDIA";
	/* String country_code=jObject1.getString("country_code");
	 String country_name=jObject1.getString("country_name");*/
	 /*String region_code=jObject1.getString("region_code");
	 String region_name=jObject1.getString("region_name");
	 String city3=jObject1.getString("city");
	 String zip_code=jObject1.getString("zip_code");
	 String time_zone=jObject1.getString("time_zone");
	 String latitude= jObject1.getString("latitude");
	 String longitude=jObject1.getString("longitude");
	 String metro_code=jObject1.getString("metro_code");*/
	 
	 try{
		 
		    ps.setString(1, username);
			ps.setString(2, ipAddress);
			ps.setString(3, country_code);
			ps.setString(4, country_name);
			ps.setString(5, "NO_DATA");
		 
	 }catch(Exception err){
		 
		 err.printStackTrace();
	 }finally{
		 
		 try {
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
		 if(ps!=null)
		 ps.close();
		}catch(Exception erf){
			erf.printStackTrace();
		}
	 }
	 
	 System.out.println("ip_add::"+ip_add);
	    return city;
	}
}