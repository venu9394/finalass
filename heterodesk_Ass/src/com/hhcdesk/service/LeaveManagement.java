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
 * Servlet implementation class LeaveManagement
 * Hetero Health Care Limited
 * By  HHCL Java Tem 
 * Written By Venu 
 * Employee LeaveManagement
 */
//@WebServlet("/User_Auth")
public class LeaveManagement extends HttpServlet {

	public LeaveManagement() {
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
		java.sql.PreparedStatement ps_1=null;
		PrintWriter out = response.getWriter();
		boolean ConnFlag=true;
		int User_Auth=0;
		GetDbData ATTENDANCE_REQ=new GetDbData();
		JSONArray values;
		values = new JSONArray();
		String Routing=request.getParameter("Routing");

		String Leave_Type=null,from_date=null,to_date=null,HalfDay=null,Hal_date=null,compoff=null,comm_date=null,to_mail=null,cc_mail=null,subject=null,reason=null,Atten_Req_Message=null;
		String HR_ATT=null,HR_ATT_USER=null,From_loc=null,To_loc=null;
		if(Routing.equalsIgnoreCase("LeaveMgM")){

			Leave_Type=request.getParameter("Leave_Type");
			from_date=request.getParameter("from_date");
			to_date=request.getParameter("to_date");
			HalfDay=request.getParameter("HalfDay");
			Hal_date=request.getParameter("Hal_date");
			compoff=request.getParameter("compoff");
			comm_date=request.getParameter("comm_date");
			to_mail=request.getParameter("to_mail");
			cc_mail=request.getParameter("cc_mail");
			subject=request.getParameter("subject");
			reason=request.getParameter("reason");
			HR_ATT=request.getParameter("HR_ATT");
			HR_ATT_USER=request.getParameter("HR_ATT_USER");
			From_loc=request.getParameter("From_loc");
			To_loc=request.getParameter("To_loc");
			
			if(HR_ATT==null){
				HR_ATT="OLD";
			}if(HR_ATT=="OLD"){
				
				try{
					if(subject==null){
						subject="NA";
					}
					
				   if(Leave_Type.equalsIgnoreCase("OD")){
					   subject=subject.concat(" (").concat(Leave_Type).concat(":").concat(From_loc).concat("-"+To_loc).concat(")");
				    }else{
				    	subject=subject.concat(" (").concat(Leave_Type).concat(")");
				    }
				}catch(Exception Err){
					Err.printStackTrace();
				}
			}
			try{
			to_mail=to_mail.replaceAll(";", ",");
			cc_mail=cc_mail.replaceAll(";", ",");
			}catch(Exception Err){
				System.out.println("Error At Leave Management::"+Err);
			}

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

		if(Routing!=null && (Routing.equalsIgnoreCase("LeaveMgM"))){
			username=(String)session.getAttribute("EMP_ID");
			password=(String)session.getAttribute("EMP_PASSWORD");
		}

       //System.out.println(username +"~~username~password~~" +password);
		
       if(username!=null && password!=null && Routing.equalsIgnoreCase("LeaveMgM")){
			ps=null;
			try{	

				/*Leave_Type=request.getParameter("Leave_Type");
				from_date=request.getParameter("from_date");
				to_date=request.getParameter("to_date");
				HalfDay=request.getParameter("HalfDay");
				Hal_date=request.getParameter("Hal_date");
				compoff=request.getParameter("compoff");
				comm_date=request.getParameter("comm_date");
				to_mail=request.getParameter("to_mail");
				cc_mail=request.getParameter("cc_mail");
				subject=request.getParameter("subject");
				reason=request.getParameter("reason");*/
				Random rand = new Random();
				int nRand = rand.nextInt(200000) + 12000;


				conn.setAutoCommit(false);
				
			if(HR_ATT.equalsIgnoreCase("OLD")){
				ps=conn.prepareStatement("insert into hclhrm_prod_others.tbl_emp_attn_req(EMPLOYEEID,FROM_DATE,TO_DATE,SUBJECT,TO_EMAIL,CC_EMAIL,MESSAGE,REQ_TYPE,REQ_DATE,RANDOMID) values(?,?,?,?,?,?,?,'LR',curdate(),?)" ,new String[] {"RID"});
				ps.setString(1,username);
				ps.setString(2,from_date);
				ps.setString(3,to_date);
				ps.setString(4,subject);
				ps.setString(5,to_mail);
				ps.setString(6,cc_mail);
				ps.setString(7,reason);
				ps.setInt(8,nRand);
			} if(HR_ATT.equalsIgnoreCase("HRATT")){
				ps=conn.prepareStatement("insert into hclhrm_prod_others.tbl_emp_attn_req(EMPLOYEEID,FROM_DATE,TO_DATE,SUBJECT,TO_EMAIL,CC_EMAIL,MESSAGE,REQ_TYPE,REQ_DATE,RANDOMID,MAIL_STATUS,FLAG,COMMENTS,APPROVEDBY) values(?,?,?,?,?,?,?,'LR',curdate(),?,?,?,?,?)" ,new String[] {"RID"});
				ps.setString(1,HR_ATT_USER);
				ps.setString(2,from_date);
				ps.setString(3,to_date);
				ps.setString(4,subject);
				ps.setString(5,to_mail);
				ps.setString(6,cc_mail);
				ps.setString(7,reason);  
				ps.setInt(8,nRand);
				ps.setString(9,"S");
				ps.setString(10,"A");
				ps.setString(11,"Auto Approved By HR");
				ps.setString(12,username);
			}
               
				int count= ps.executeUpdate();
				
				
				Long primaryKey=(long) 0;
		    try{		
		    	ResultSet generatedKeys = ps.getGeneratedKeys();
		    	
		    	if (null != generatedKeys && generatedKeys.next()) {
		    	      primaryKey = generatedKeys.getLong(1);
		    	     System.out.println("primaryKey:::"+primaryKey);
		    	}
		    }catch(Exception Genkey){
		    	System.out.println("Genkey:::" +Genkey);
		    }
		    
		    
		    if(HR_ATT.equalsIgnoreCase("OLD")){
				ps_1=conn.prepareStatement("insert into hclhrm_prod_others.tbl_emp_Leave_req(EMPLOYEEID,FROM_DATE,TO_DATE,SUBJECT,TO_EMAIL,CC_EMAIL,MESSAGE,REQ_TYPE,REQ_DATE,RANDOMID,HALF_DAY_STATUS,HALF_DAY_DATE,COMPOFF_DAY_STATUS,COMPOFF_DAY_DATE,Leave_Type,RID,from_loc,to_loc) values(?,?,?,?,?,?,?,'LR',curdate(),?,?,?,?,?,?,?,?,?)");
				ps_1.setString(1,username);
				ps_1.setString(2,from_date);
				ps_1.setString(3,to_date);
				ps_1.setString(4,subject);
				ps_1.setString(5,to_mail);
				ps_1.setString(6,cc_mail);
				ps_1.setString(7,reason);
				ps_1.setInt(8,nRand);
				ps_1.setString(9,HalfDay);
				ps_1.setString(10,Hal_date);
				ps_1.setString(11,compoff);
				ps_1.setString(12,comm_date);
				ps_1.setString(13,Leave_Type);
				ps_1.setLong(14,primaryKey);
				ps_1.setString(15,From_loc); // Add for location
				ps_1.setString(16,To_loc);   // Add for location
		    }else if(HR_ATT.equalsIgnoreCase("HRATT")){
		    	ps_1=conn.prepareStatement("insert into hclhrm_prod_others.tbl_emp_Leave_req(EMPLOYEEID,FROM_DATE,TO_DATE,SUBJECT,TO_EMAIL,CC_EMAIL,MESSAGE,REQ_TYPE,REQ_DATE,RANDOMID,HALF_DAY_STATUS,HALF_DAY_DATE,COMPOFF_DAY_STATUS,COMPOFF_DAY_DATE,Leave_Type,RID) values(?,?,?,?,?,?,?,'LR',curdate(),?,?,?,?,?,?,?)");
				ps_1.setString(1,HR_ATT_USER);
				ps_1.setString(2,from_date);
				ps_1.setString(3,to_date);
				ps_1.setString(4,subject);
				ps_1.setString(5,to_mail);
				ps_1.setString(6,cc_mail);
				ps_1.setString(7,reason);
				ps_1.setInt(8,nRand);
				ps_1.setString(9,HalfDay);
				ps_1.setString(10,Hal_date);
				ps_1.setString(11,compoff);
				ps_1.setString(12,comm_date);
				ps_1.setString(13,Leave_Type);
				ps_1.setLong(14,primaryKey);
				
		    	
		    }
				//int count= ps.executeUpdate();
				int count1= ps_1.executeUpdate();
				System.out.println("add Batch Count::"+count);
				if(count>0 && count1>0){
					conn.commit();
					Atten_Req_Message="Your leave successfully processed..";
				}else{
					conn.rollback();
					Atten_Req_Message="Your Leave process failed please contact system admin..";
				}
			}
			catch (Exception e2)
			{
				Atten_Req_Message="Failed to process your request please contact system admin.";
				e2.printStackTrace(); 
				
			}finally{
				
				try {
					/*if(conn!=null){
						conn.close();
					}*/
					if(ps!=null){
						ps.close();
					}
					if(ps_1!=null){
						ps_1.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}

		} else{
			Atten_Req_Message="Authentication failed please Re-login..!";

		}// IF Close For Insertion

		if(Routing.equalsIgnoreCase("LeaveMgM")){
			System.out.println("Atten_Req_Message::"+Atten_Req_Message);
			out.write(""+Atten_Req_Message+"");  
		}else{
			request.getRequestDispatcher("/login.html").forward(request, response);  
		}
	}  
}
