package com.hhcdesk.service;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.PrintWriter;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.json.JSONException;

import com.mysql.jdbc.Statement;
import com.hhcdesk.EventCapture.MySessionListener;
import com.hhcdesk.db.*;

/**
 * Servlet implementation class LoginServlet
 * Hetero Health Care Limited
 * By Java HHCL Java Tem 
 * Written By Venu 
 * Employee Change Password
 */
//@WebServlet("/User_Auth")
public class ChangePassword extends HttpServlet {

	public ChangePassword() {
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext c = getServletContext();
		HttpSession session = request.getSession(false);
		Statement statement=null;
		ResultSet Res=null;
		DataSource dataSource=null;
		java.sql.Connection conn=null;
		java.sql.PreparedStatement ps=null;
		PrintWriter out = response.getWriter();
		boolean ConnFlag=true;
		int User_Auth=0;
		GetDbData ATTENDANCE_REQ=new GetDbData();
		JSONArray values;
		values = new JSONArray();
		String Routing=request.getParameter("Routing");
		
		String currentpassword=null,newPassword=null,ConformPassword=null,Atten_Req_Message=null;
		
		if(Routing.equalsIgnoreCase("changePwd")){
			currentpassword=request.getParameter("currentpassword");
			newPassword=request.getParameter("newPassword");
			ConformPassword=request.getParameter("ConformPassword");
		}
		ArrayList<PrepareData> list = new ArrayList<PrepareData>();
		Map FetchRc=new HashMap();
		StringBuffer biodata=new StringBuffer();
		ResourceBundle bundle_info =(ResourceBundle)(c.getAttribute("bundle"));
		String Query=bundle_info.getString("HHCL_DESK_USER_LOGIN");
		String message=null;
		message=bundle_info.getString("HHCL_DESK_NEWJOINEE");
		/*try {
			dataSource=(DataSource)(c.getAttribute("dataSource"));

		} catch (Exception e) {
			ConnFlag=false;
			e.printStackTrace();
		}
		try {
			conn =dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}*/
		/*DataSource_Cls ConnObj=null;
		try {
			 ConnObj=new DataSource_Cls();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (PropertyVetoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			//conn =dataSource.getConnection();
			conn=ConnObj.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		try {
			//conn =dataSource.getConnection();
			conn=(java.sql.Connection)session.getAttribute("ConnectionObj");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
				e1.printStackTrace();
		}
		String username=null;  
		String password=null; 

		if(Routing!=null && (Routing.equalsIgnoreCase("changePwd"))){
			username=(String)session.getAttribute("EMP_ID");
			password=(String)session.getAttribute("EMP_PASSWORD");
		}

		
if(Routing.equalsIgnoreCase("changePwd")  && currentpassword.equals(password)){
			ps=null;
			try{	
			conn.setAutoCommit(false);
			ps=conn.prepareStatement("update hclhrm_prod.tbl_employee_login SET PASSWORD=md5(?),DATEMODIFIED=now(),modifiedby=? where employeecode=? and STATUS=1001 limit 1;");
			ps.setString(1,newPassword);
			ps.setString(2,username);
			ps.setString(3,username);
			int count= ps.executeUpdate();
			System.out.println("add Batch Count::"+count);
			if(count>0){
				   conn.commit();
        	 Atten_Req_Message="Your password successfully updated please Re-login..";
			}else{
					conn.rollback();
				Atten_Req_Message="Your password updation failed please contact system admin..";
			}
	     }
			catch (Exception e2)
			{
				Atten_Req_Message="Failed to process your request please contact system admin.";
				e2.printStackTrace();  
			}
			
			session.removeAttribute("EMP_PASSWORD");
			session.setAttribute("EMP_PASSWORD","NO_DATA");

} else{
	Atten_Req_Message="Invalid Current Password..!";
	
}// IF Close For Insertion
		
try {
	if(statement!=null){
		statement.close();
	}
	if(Res!=null){
		Res.close();
	}
	/*if(conn!=null){
		conn.close();
	}*/
	if(ps!=null){
		ps.close();
	}

} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		if(Routing.equalsIgnoreCase("changePwd")){
			System.out.println("Atten_Req_Message::"+Atten_Req_Message);
			out.write(""+Atten_Req_Message+"");  
		}else{
			request.getRequestDispatcher("/login.html").forward(request, response);  
		}
	}  
}
