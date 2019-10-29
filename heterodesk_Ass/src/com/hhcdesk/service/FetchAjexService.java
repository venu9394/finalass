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

import org.apache.log4j.Logger;
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
public class FetchAjexService extends HttpServlet {

	
	private static final Logger LOGGER = Logger.getLogger("FetchAjexService");
	public FetchAjexService() {
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
		
		java.sql.PreparedStatement ps_1=null,ps_2=null,ps_3=null,ps_4=null;
		PrintWriter out = response.getWriter();
		GetDbData DataObj=new GetDbData();
		double availableQuantity=0,holidays=0,Weak_holiday=0,MinLeaveLimit=0, availableQuantity_temp=0,holidays_temp=0,Weak_holiday_temp=0,MinLeaveLimit_temp=0;
		boolean ConnFlag=true;
		int User_Auth=0;
		JSONObject jason = new JSONObject();
		StringBuffer  Leave_Quota=new  StringBuffer();
		
		//StringBuffer  holidaycount=new  StringBuffer();
		
		GetDbData ATTENDANCE_REQ=new GetDbData();
		JSONArray values;
		values = new JSONArray();
		String Routing=request.getParameter("Routing");
        String Atten_Req_Message="No Data";
		String Leave_Type=null,from_date=null,todaydate=null;
		boolean todayDateFlag=false;
		
		if(Routing.equalsIgnoreCase("FetchLeaveDays") || Routing.equalsIgnoreCase("FetchLeaveDays1")){

			Leave_Type=request.getParameter("Leave_Type");
			from_date=request.getParameter("from_date");
			todaydate=request.getParameter("todaydate");
			
			//StringBuffer reverse = new StringBuffer(from_date).reverse();
					
			//from_date=reverse.toString();
			
			System.out.println("todaydate-:: "+todaydate);

		}
		
		ArrayList<PrepareData> list = new ArrayList<PrepareData>();
		Map FetchRc=new HashMap();
		StringBuffer biodata=new StringBuffer();
		ResourceBundle bundle_info =(ResourceBundle)(c.getAttribute("bundle"));
		String Query=bundle_info.getString("HHCL_DESK_USER_LOGIN");
		String message=null;
		message=bundle_info.getString("HHCL_DESK_NEWJOINEE");
		
		try {
			conn=(java.sql.Connection)session.getAttribute("ConnectionObj");
		} catch (Exception e1) {
				e1.printStackTrace();
		}
		
		String username=null;  
		String password=null; 

		jason.put("availableQuantity", "0");
		jason.put("Atten_Req_Message", "No Data");
		jason.put("DATES", from_date);
		jason.put("OFFDAY", "0");
		jason.put("ACTUALAVAIL", "0");
		jason.put("SL_MX_LEAVE",0); 
		jason.put("todayDateFlag","false"); 
		
		
		if(Routing!=null && (Routing.equalsIgnoreCase("FetchLeaveDays") || Routing.equalsIgnoreCase("FetchLeaveDays1") )){
			username=(String)session.getAttribute("EMP_ID");
			password=(String)session.getAttribute("EMP_PASSWORD");
		}
		
	/*	LOGGER.debug(todaydate+"TODAY DATE SELECTION " +username);
	*/	
       if(conn!=null && username!=null && password!=null && Routing.equalsIgnoreCase("FetchLeaveDays")){
    	   
    	   
    	   
    	   Res=null;  
    	   try {
      			
      			Res=(ResultSet)DataObj.FetchData_Emp_DOB("select if(date_format('"+todaydate+"','%Y-%m-%d')=date_format(now(),'%Y-%m-%d'),0,1 ) validflag from dual ", "date&serverDateValidation", Res ,conn);
      			
      			if(Res.next()){
      				
      				if(Res.getInt("validflag")==0){
      				 todayDateFlag=true;
      				
      				}else{
      					todayDateFlag=false;
      				}

      			}
      			
      			
      		}catch(Exception ERFD){
      			   System.out.println("Error Loaded At::" +ERFD);
      		}  
       	   
    	   
    	   Res=null;
           
         /* StringBuffer StrReverse=new StringBuffer();
          
          StrReverse.append(" select date_format(STR_TO_DATE('"+from_date+"' ,'%d-%m-%Y'),'%Y-%m-%d') from dual ");
           
   		try {
   			  Res=(ResultSet)DataObj.FetchData_Emp_DOB(StrReverse.toString(), "Leave_Quota", Res ,conn);
   			if(Res.next()){
   				
   				from_date=Res.getString(1).toString();
   			}
   			}catch(Exception err){
   				System.out.println("Exception at reverse" +err);
   			}*/
    	   
    	   
    	System.out.println(from_date +":::venu from_date ~ Leave_Type:::"+Leave_Type);
    	
    	if(Leave_Type.equalsIgnoreCase("SL")){
    		
    	/*
    		Leave_Quota.append(" select C.employeesequenceno,DATE_ADD('"+from_date+"' , INTERVAL if(ifnull(B.MINIMU_LEAVE,0)=0,1,");
    		Leave_Quota.append(" if(B.MINIMU_LEAVE>=round(b.availableqty,0),round(b.availableqty,0),B.MINIMU_LEAVE)  ) -1 DAY ),");
    		Leave_Quota.append("  if(B.daymode=0,if(round(b.availableqty,0)<=ifnull(B.MINIMU_LEAVE,0),round(b.availableqty,0),ifnull(B.MINIMU_LEAVE,0)),B.MINIMU_LEAVE)   MINLEAVE ");
    		Leave_Quota.append(" , b.count_woff,b.count_holiday,trim(A.SHORTNAME) SHORTNAME,B.quantity , round(b.availableqty,0) AS availableqty , " );
    		Leave_Quota.append(" B.HOLD, B.quantity+B.HOLD as totalavl, b.USEDQTY, trim(A.NAME) Fullname, ");
    		Leave_Quota.append(" B.DAYMODE,if(B.daymode=0,if(round(b.availableqty,0)<=B.maxleave,round(b.availableqty,0),B.maxleave),B.maxleave) MAXLEAVE_C ,B.COUNT_WOFF,B.BACKDATE ,");
    		Leave_Quota.append(" if(B.daymode=0,if(b.availableqty<=B.maxleave,b.availableqty,B.maxleave),B.maxleave) MAXLEAVE_C1 ,"); //dummy
    		Leave_Quota.append(" if(B.daymode=0,if(b.availableqty<=ifnull(B.MINIMU_LEAVE,0),b.availableqty,ifnull(B.MINIMU_LEAVE,0)),B.MINIMU_LEAVE)   MINLEAVE1 "); // dummy
    		Leave_Quota.append(", b.availableqty AS Ablt ,abs(round(b.availableqty,0)-b.availableqty) AS offday,ifnull(B.FOR_MONTH,0) As permonth " );
    		Leave_Quota.append(", (abs(datediff('"+from_date+"', now()))+1 ) as SLdatdiff from  hclhrm_prod.tbl_leave_type A,  "); //dummy
    		Leave_Quota.append(" hclhrm_prod_others.tbl_emp_leave_quota B, ");
    		Leave_Quota.append(" hclhrm_prod.tbl_employee_primary C ");
    		Leave_Quota.append(" where B.employeeid=C.employeeid and C.employeesequenceno in("+username+") and ");
    		Leave_Quota.append(" B.Leavetypeid=A.Leavetypeid and  b.status=1001 and A.SHORTNAME='"+Leave_Type+"'");*/
    		

    		Leave_Quota.append(" select C.employeesequenceno,DATE_ADD('"+from_date+"' , INTERVAL if(ifnull(B.MINIMU_LEAVE,0)=0,1,");
    		Leave_Quota.append(" if(B.MINIMU_LEAVE>=round(b.availableqty,0),round(b.availableqty,0),B.MINIMU_LEAVE)  ) -1 DAY ),");
    		Leave_Quota.append("  if(B.daymode=0,if(round(b.availableqty,0)<=ifnull(B.MINIMU_LEAVE,0),round(b.availableqty,0),ifnull(B.MINIMU_LEAVE,0)),B.MINIMU_LEAVE)   MINLEAVE ");
    		Leave_Quota.append(" , b.count_woff,b.count_holiday,trim(A.SHORTNAME) SHORTNAME,B.quantity , round(b.availableqty,0) AS availableqty , " );
    		Leave_Quota.append(" B.HOLD, B.quantity+B.HOLD as totalavl, b.USEDQTY, trim(A.NAME) Fullname, ");
    		Leave_Quota.append(" B.DAYMODE,if(B.daymode=0,if(round(b.availableqty,0)<=B.maxleave,round(b.availableqty,0),B.maxleave),B.maxleave) MAXLEAVE_C ,B.COUNT_WOFF,B.BACKDATE ,");
    		Leave_Quota.append(" if(B.daymode=0,if(b.availableqty<=B.maxleave,b.availableqty,B.maxleave),B.maxleave) MAXLEAVE_C1 ,"); //dummy
    		Leave_Quota.append(" if(B.daymode=0,if(b.availableqty<=ifnull(B.MINIMU_LEAVE,0),b.availableqty,ifnull(B.MINIMU_LEAVE,0)),B.MINIMU_LEAVE)   MINLEAVE1 "); // dummy
    		Leave_Quota.append(", b.availableqty AS Ablt ,abs(round(b.availableqty,0)-b.availableqty) AS offday,ifnull(B.FOR_MONTH,0) As permonth " );
    		Leave_Quota.append(", (abs(datediff('"+from_date+"', if(date_format(DATE_ADD('"+from_date+"',INTERVAL if(B.daymode=0,if(round(b.availableqty,0)<=B.maxleave,round(b.availableqty,0),B.maxleave),B.maxleave) DAY),'%Y-%m-%d')<=now(), " );
    		Leave_Quota.append(" date_format(DATE_ADD('"+from_date+"',INTERVAL if(B.daymode=0,if(round(b.availableqty,0)<=B.maxleave,round(b.availableqty,0),B.maxleave),B.maxleave) DAY),'%Y-%m-%d')  ,now())    )) +1) as SLdatdiff " );
    		
    		Leave_Quota.append(" ,");
    		
    		Leave_Quota.append(" if( date_format(DATE_ADD('"+from_date+"', INTERVAL 1 DAY ),'%Y-%m-%d')=date_format(now(),'%Y-%m-%d'),2,1) VENUFLG ");
    		
    		Leave_Quota.append(" from  hclhrm_prod.tbl_leave_type A,  "); //dummy
    		Leave_Quota.append(" hclhrm_prod_others.tbl_emp_leave_quota B, ");
    		Leave_Quota.append(" hclhrm_prod.tbl_employee_primary C ");
    		Leave_Quota.append(" where B.employeeid=C.employeeid and C.employeesequenceno in("+username+") and ");
    		Leave_Quota.append(" B.Leavetypeid=A.Leavetypeid and  b.status=1001 and A.SHORTNAME='"+Leave_Type+"'");
    		

    		System.out.println("Leave_Quota::"+Leave_Quota.toString());
    	 
    	}else{
    		
    		Leave_Quota.append(" select C.employeesequenceno,DATE_ADD('"+from_date+"' , INTERVAL if(ifnull(B.MINIMU_LEAVE,0)=0,1,");
    		Leave_Quota.append(" if(B.MINIMU_LEAVE>=round(b.availableqty,0),round(b.availableqty,0),B.MINIMU_LEAVE)  )-1 DAY ),");
    		Leave_Quota.append("  if(B.daymode=0,if(round(b.availableqty,0)<=ifnull(B.MINIMU_LEAVE,0),round(b.availableqty,0),ifnull(B.MINIMU_LEAVE,0)),B.MINIMU_LEAVE)   MINLEAVE ");
    		Leave_Quota.append(" , b.count_woff,b.count_holiday,trim(A.SHORTNAME) SHORTNAME,B.quantity , round(b.availableqty,0) AS availableqty , " );
    		Leave_Quota.append(" B.HOLD, B.quantity+B.HOLD as totalavl, b.USEDQTY, trim(A.NAME) Fullname, ");
    		Leave_Quota.append(" B.DAYMODE,if(B.daymode=0,if(round(b.availableqty,0)<=B.maxleave,round(b.availableqty,0),B.maxleave),B.maxleave) MAXLEAVE_C ,B.COUNT_WOFF,B.BACKDATE ,");
    		Leave_Quota.append(" if(B.daymode=0,if(b.availableqty<=B.maxleave,b.availableqty,B.maxleave),B.maxleave) MAXLEAVE_C1 ,"); //dummy
    		Leave_Quota.append(" if(B.daymode=0,if(b.availableqty<=ifnull(B.MINIMU_LEAVE,0),b.availableqty,ifnull(B.MINIMU_LEAVE,0)),B.MINIMU_LEAVE)   MINLEAVE1 "); // dummy
    		Leave_Quota.append(", b.availableqty AS Ablt ,abs(round(b.availableqty,0)-b.availableqty) AS offday,ifnull(B.FOR_MONTH,0) As permonth " );
    		Leave_Quota.append(", abs(datediff('"+from_date+"', now())) SLdatdiff from  hclhrm_prod.tbl_leave_type A,  "); //dummy
    		Leave_Quota.append(" hclhrm_prod_others.tbl_emp_leave_quota B, ");
    		Leave_Quota.append(" hclhrm_prod.tbl_employee_primary C ");
    		Leave_Quota.append(" where B.employeeid=C.employeeid and C.employeesequenceno in("+username+") and ");
    		Leave_Quota.append(" B.Leavetypeid=A.Leavetypeid and  b.status=1001 and A.SHORTNAME='"+Leave_Type+"'");

    		
    	}
       	//count_woff/count_holiday
       	//1 exempted/0 not exempted
		
       	Res=null;
        boolean LevFlag=false;
        Map Combination=new HashMap();
        
		try {
			Res=(ResultSet)DataObj.FetchData_Emp_DOB(Leave_Quota.toString(), "Leave_Quota", Res ,conn);
			while(Res.next()){
			//	LeaveBalance.put(Res.getString("SHORTNAME") , Res.getString("quantity"));
				availableQuantity=Double.parseDouble(Res.getString("MAXLEAVE_C"));
				Weak_holiday=Double.parseDouble(Res.getString("count_woff"));
				MinLeaveLimit=Double.parseDouble(Res.getString("MINLEAVE").toString());
				
				availableQuantity_temp=Double.parseDouble(Res.getString("MAXLEAVE_C1"));
				Weak_holiday_temp=Double.parseDouble(Res.getString("count_woff"));
				MinLeaveLimit_temp=Double.parseDouble(Res.getString("MINLEAVE1").toString());
				double permonth=Double.parseDouble(Res.getString("permonth").toString());
				/*if(permonth>0){ // for for month consider
				
					Atten_Req_Message=	"Available  "+Res.getString("SHORTNAME")+" Quantity :"+Res.getString("Ablt") +",Eligible Limit : Min ~"+MinLeaveLimit_temp+" & Max ~"+permonth+"";
					
					
				}else{
					
					Atten_Req_Message=	"Available  "+Res.getString("SHORTNAME")+" Quantity :"+Res.getString("Ablt") +",Eligible Limit : Min ~"+MinLeaveLimit_temp+" & Max ~"+availableQuantity_temp+"";
					
				}*/
				Atten_Req_Message=	"Available  "+Res.getString("SHORTNAME")+" Quantity :"+Res.getString("Ablt") +",Eligible Limit : Min ~"+MinLeaveLimit_temp+" & Max ~"+availableQuantity_temp+"";
				
				
				
				
				/*if(permonth>0){
				   jason.put("availableQuantity", permonth);
				}else{
					jason.put("availableQuantity", availableQuantity);
				}*/
				jason.put("availableQuantity", availableQuantity);
				jason.put("Atten_Req_Message", Atten_Req_Message);
				jason.put("DATES", Res.getString(2));
				jason.put("OFFDAY", Res.getString("offday"));
				jason.put("ACTUALAVAIL", Res.getString("Ablt"));
				
				double  SLdatdiff= Double.parseDouble(Res.getString("SLdatdiff").toString()); 
				double  VENUFLG=0;
				
				if(Leave_Type.equalsIgnoreCase("SL")){
					VENUFLG=Double.parseDouble(Res.getString("VENUFLG").toString());
			       }
				
				System.out.println(VENUFLG+ "::"+Leave_Type+"::" +SLdatdiff);
				
				     /*  if(SLdatdiff==0 && Leave_Type.equalsIgnoreCase("SL")){
				    	   SLdatdiff=1;
				       }else if(SLdatdiff>0 && Leave_Type.equalsIgnoreCase("SL") && VENUFLG>1){
				    	   
				    	   SLdatdiff= VENUFLG;
				       }*/
				       
				       System.out.println(VENUFLG+ "::"+Leave_Type+"::" +SLdatdiff);
				       
				if(Leave_Type.equalsIgnoreCase("SL") && availableQuantity_temp <= SLdatdiff){
					
					jason.put("SL_MX_LEAVE", availableQuantity_temp);
					
					//jason.put("SL_MX_LEAVE", Double.parseDouble(Res.getString("SLdatdiff")));
				}else if(Leave_Type.equalsIgnoreCase("SL") && availableQuantity_temp >= SLdatdiff){
					
					jason.put("SL_MX_LEAVE", SLdatdiff); 
				}else{
					
					jason.put("SL_MX_LEAVE", SLdatdiff); 
				}
				
				
			
			}
		}catch(Exception ERFD){
			   System.out.println("Error Loaded At::" +ERFD);
		}   
    	Res=null;
    	
		/*holidaycount.append(" select selected_date,IF(DAYNAME(selected_date)='SUNDAY','WOFF','WDAY') DAYTYPE,  ");
		holidaycount.append(" IF(IF(DAYNAME(selected_date)='SUNDAY','WOFF','WDAY')='WDAY',1,0) STATUS,V2.holidaydate, ");
		holidaycount.append(" if(DAYNAME(selected_date)='SUNDAY' && selected_date=V2.holidaydate,1,if(DAYNAME(selected_date)='SUNDAY',1,if(selected_date=V2.holidaydate,1,0))) AS HW ");
		holidaycount.append(" FROM ");
		holidaycount.append(" (select adddate('1970-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) selected_date from ");
		holidaycount.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0, ");
		holidaycount.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, ");
		holidaycount.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2, ");
		holidaycount.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3, ");
		holidaycount.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v ");
		holidaycount.append(" left join( ");
		holidaycount.append(" select holidaydate from hclhrm_prod.tbl_holidays ");
		holidaycount.append(" where statusid=1001 and businessunitid in(select companyid from hclhrm_prod.tbl_employee_primary ");
		holidaycount.append(" where employeesequenceno in("+username+")) ");
		holidaycount.append(" )V2 on V2.holidaydate=V.selected_date ");
		holidaycount.append(" where selected_date between '"+from_date+"' and ");*/
		
		
		//holidaycount.append("( SELECT '"+from_date+"'+INTERVAL ("+availableQuantity+"-1) DAY) ");
		
		//holidaycount.append("( SELECT '"+from_date+"'+INTERVAL ("+availableQuantity+" -1 ) DAY) ");
		
	//	System.out.println(holidaycount.toString() +"holidaycount.toString()");
	
//********************************
    	 String BuID=session.getAttribute("EMPBUID").toString();
		
 if(Weak_holiday>0){
	 
	 int looplimit=0;
	 double workingdates=0;
	 int interval=0;
	
	 double Incr=availableQuantity-1;
	 for(int i=0;i<=5;i++)		
	 {	
		 
		 double inceloop=Incr+i;
		 holidays=0;
		 workingdates=0;
		    StringBuffer holidaycount=new StringBuffer();
		 
		    if(BuID.equalsIgnoreCase("15") ||BuID.equalsIgnoreCase("16") ){
		         holidaycount.append(" select selected_date,IF(DAYNAME(selected_date)='Saturday','WOFF','WDAY') DAYTYPE,  ");
		    }else{
		    	 holidaycount.append(" select selected_date,IF(DAYNAME(selected_date)='SUNDAY','WOFF','WDAY') DAYTYPE,  ");
		    }
		    
		    if(BuID.equalsIgnoreCase("15") ||BuID.equalsIgnoreCase("16") ){ 
			holidaycount.append(" IF(IF(DAYNAME(selected_date)='Saturday','WOFF','WDAY')='WDAY',1,0) STATUS,V2.holidaydate, ");
			holidaycount.append(" if(DAYNAME(selected_date)='Saturday' && selected_date=V2.holidaydate,1,if(DAYNAME(selected_date)='Saturday',1,if(selected_date=V2.holidaydate,1,0))) AS HW ");
		    }else{
		    	holidaycount.append(" IF(IF(DAYNAME(selected_date)='SUNDAY','WOFF','WDAY')='WDAY',1,0) STATUS,V2.holidaydate, ");
				holidaycount.append(" if(DAYNAME(selected_date)='SUNDAY' && selected_date=V2.holidaydate,1,if(DAYNAME(selected_date)='SUNDAY',1,if(selected_date=V2.holidaydate,1,0))) AS HW ");
			  
		    	
		    }
			
			holidaycount.append(" FROM ");
			holidaycount.append(" (select adddate('1970-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) selected_date from ");
			holidaycount.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0, ");
			holidaycount.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, ");
			holidaycount.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2, ");
			holidaycount.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3, ");
			holidaycount.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v ");
			holidaycount.append(" left join( ");
			holidaycount.append(" select holidaydate from hclhrm_prod.tbl_holidays ");
			holidaycount.append(" where statusid=1001 and businessunitid in(select companyid from hclhrm_prod.tbl_employee_primary ");
			holidaycount.append(" where employeesequenceno in("+username+")) ");
			holidaycount.append(" )V2 on V2.holidaydate=V.selected_date ");
			holidaycount.append(" where selected_date between '"+from_date+"' and ");
			holidaycount.append("( SELECT '"+from_date+"'+INTERVAL ("+inceloop+" ) DAY) ");
			
			System.out.println("holidaycount ::"+holidaycount.toString());
			System.out.println(workingdates +"holidaycount loop ::"+inceloop);
		 
		try {
			
			Res=(ResultSet)DataObj.FetchData_Emp_DOB(holidaycount.toString(), "holidaycount", Res ,conn);
			
			while(Res.next()){
			//	LeaveBalance.put(Res.getString("SHORTNAME") , Res.getString("quantity"));
				holidays=holidays+Double.parseDouble(Res.getString("HW").toString());
				if(Double.parseDouble(Res.getString("HW").toString())==0){
				   workingdates=workingdates+1;
				}
			
			}
			
			 if(availableQuantity==workingdates){
				break;
			 }
			 
			
		}catch(Exception ERFD){
			   System.out.println("Error Loaded At::" +ERFD);
		}  
     }  // for loop closed
	 
	 /// Close At Weak_holiday
	 
 }
 
//********************************
    	   
       } else if(conn!=null && username!=null && password!=null && Routing.equalsIgnoreCase("FetchLeaveDays1")){
       
    	   Res=null;
    	   try {
   			
   			Res=(ResultSet)DataObj.FetchData_Emp_DOB("select if(date_format('"+todaydate+"','%Y-%m-%d')=date_format(now(),'%Y-%m-%d'),0,1 ) validflag from dual ", "date&serverDateValidation", Res ,conn);
   			
   			if(Res.next()){
   				
   				if(Res.getInt("validflag")==0){
   				 todayDateFlag=true;
   				
   				}else{
   					todayDateFlag=false;
   				}

   			}
   			
   			
   		}catch(Exception ERFD){
   			   System.out.println("Error Loaded At::" +ERFD);
   		}  
    	   
    	   
       
       }else{
			Atten_Req_Message="Authentication failed please Re-login..!";

		}// IF Close For Insertion
     
        
       session.setAttribute("MAX_LEAVE_"+username+"_"+Leave_Type+"", availableQuantity_temp);
        
		if(Routing.equalsIgnoreCase("FetchLeaveDays")){
			System.out.println("Atten_Req_Message::"+Atten_Req_Message);
			
			double finalcount=availableQuantity+holidays;
			jason.put("availableQuantity", finalcount);
			jason.put("todayDateFlag",""+todayDateFlag+""); 
			
			if(!todayDateFlag){
				jason.put("Atten_Req_Message", "Invalid system date please update and try again..!");
				}
			
			out.write(""+jason+"");
			
			//out.write(""+finalcount+"");
			
		}else if(Routing.equalsIgnoreCase("FetchLeaveDays1")){
			
			jason.put("todayDateFlag",""+todayDateFlag+"");
			
		/*	LOGGER.debug(todaydate+"TODAY DATE SELECTION "+todayDateFlag+" " +username);
		*/	
			if(!todayDateFlag){
			jason.put("Atten_Req_Message", "Invalid system date please update and try again..!");
			}else{
				jason.put("Atten_Req_Message", " ");
			}
			
			out.write(""+jason+"");
			
		}else{
		
			request.getRequestDispatcher("/login.html").forward(request, response);  
		}
	}  
}
