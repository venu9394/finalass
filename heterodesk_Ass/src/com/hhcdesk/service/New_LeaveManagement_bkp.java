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
import java.util.Iterator;
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
public class New_LeaveManagement_bkp extends HttpServlet {

	public New_LeaveManagement_bkp() {
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
		java.sql.PreparedStatement ps=null,PS_lop=null;
		java.sql.PreparedStatement ps_1=null,ps_2=null,ps_3=null,ps_4=null,ps_leaveQuota=null,ps_SingleDates=null;
		PrintWriter out = response.getWriter();
		boolean ConnFlag=true;
		int User_Auth=0;
		GetDbData ATTENDANCE_REQ=new GetDbData();
		JSONArray values;
		values = new JSONArray();
		Map Leave_New=new HashMap();
		String Routing=request.getParameter("Routing");
		ArrayList LeaveUnicInsertion=new ArrayList();
		Map LeaveUnicInsertion_map=new HashMap();
		double Holiday_Count=0,LeaveCount=0 ,Leave_Diff=0;
		int PeandingLeave=0,Unic_Leave=0,lopins_count=0;
		boolean insertionFlag=false;
		StringBuffer Holiday_dates_for_month=new StringBuffer();
		double DayMode=0;
		int LOP_ID=0;
		String EMPLOYEEID_LOP=null,LOP_ydate=null;
		double prev_holidays_woff=0,prev_btw_workingdays=0,feature_holidays_woff=0,feature_btw_workingdays=0;
		System.out.println("New Leave Management :: Routing "+Routing);

		String Leave_Type=null,from_date=null,to_date=null,HalfDay=null,Hal_date=null,compoff=null,comm_date=null,to_mail=null,cc_mail=null,subject=null,reason=null,FROMDATE=null,TODATE=null,
		Atten_Req_Message="Failed to process your request please contact system admin.";
		String HR_ATT=null,HR_ATT_USER=null,From_loc=null,To_loc=null;
		
		Map EmpData=new HashMap();
		
		GetDbData DataObj=new GetDbData();
		
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
		
		System.out.println("Leave_Type" +Leave_Type);
		
		StringBuffer payperiod=new StringBuffer();
		StringBuffer Leave_Monthly_validation=new StringBuffer();
		
		payperiod.append(" SELECT ifnull(FROMDATE,'0000-00-00') FROMDATE ,ifnull(TODATE,'0000-00-00') TODATE FROM HCLADM_PROD.TBL_TRANSACTION_DATES ");
		payperiod.append(" WHERE BUSINESSUNITID IN (SELECT COMPANYID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO in("+username+")) AND ");
		payperiod.append(" TRANSACTIONDURATION IN (SELECT MAX(TRANSACTIONDURATION) FROM HCLADM_PROD.TBL_TRANSACTION_DATES) AND TRANSACTIONTYPEID=1 ");
		
		Res=null;
        
		try {
			Res=(ResultSet)DataObj.FetchData_Emp_DOB(payperiod.toString(), "NewEmplFlag", Res ,conn);
			if(Res.next()){
				
				System.out.println(Res.getString("FROMDATE").toString() +"~PayPeriod Days~"+Res.getString("TODATE").toString());
				EmpData.put("FROMDATE", Res.getString("FROMDATE").toString());
				EmpData.put("TODATE",   Res.getString("TODATE").toString());
			}
		}catch(Exception err){
		
			System.out.println("Error At Get Leave Quota" +err);
		}
	
		
		Leave_Monthly_validation.append(" SELECT COUNT(*) " );
		Leave_Monthly_validation.append(" FROM " );
		Leave_Monthly_validation.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A " );
		Leave_Monthly_validation.append(" LEFT JOIN " );
		Leave_Monthly_validation.append(" HCLHRM_PROD_OTHERS.TBL_EMP_ATTN_REQ C ON A.EMPLOYEESEQUENCENO=C.EMPLOYEEID " );
		Leave_Monthly_validation.append(" LEFT JOIN " );
		Leave_Monthly_validation.append(" HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_REQ D ON C.RID=D.RID " );
		Leave_Monthly_validation.append(" LEFT JOIN " );
		Leave_Monthly_validation.append(" HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_REPORT E ON C.RID=E.RID " );
		Leave_Monthly_validation.append(" WHERE " );
		Leave_Monthly_validation.append(" A.EMPLOYEESEQUENCENO IN ("+username+") AND C.FLAG IN ('P','A') AND C.STATUS=1001  AND" );
		Leave_Monthly_validation.append(" E.LEAVEON BETWEEN '"+EmpData.get("FROMDATE").toString()+"' AND '"+EmpData.get("TODATE").toString()+"' AND D.LEAVE_TYPE!='LOP' AND D.LEAVE_TYPE='"+Leave_Type+"' ");

		
      Res=null;
        
		try {
			Res=(ResultSet)DataObj.FetchData_Emp_DOB(Leave_Monthly_validation.toString(), "Leave_Monthly_validation", Res ,conn);
			if(Res.next()){
				
				EmpData.put("MONTH_EXIST_LEAVE", Res.getString(1).toString());
				
			}
		}catch(Exception err){
		
			System.out.println("Error At Get Leave Quota" +err);
		}
		
		
		
		StringBuffer NewEmplFlag=new StringBuffer();
		
		NewEmplFlag.append(" select a.employeeid,if(ifnull(B.leavetypeid,0)=15,B.leavetypeid,0) leaveid,date_format(now(),'%Y') ydate from hclhrm_prod.tbl_employee_primary a ");
		NewEmplFlag.append(" left join( ");
		NewEmplFlag.append(" select leavetypeid,employeeid from hclhrm_prod_others.tbl_emp_leave_quota where leavetypeid=15 and status=1001 ");
		NewEmplFlag.append(" group by employeeid ");
		NewEmplFlag.append(" )B on b.employeeid=a.employeeid where a.employeesequenceno in("+username+") ");
		
		if(Leave_Type.equalsIgnoreCase("LOP")){
			
			Res=null;
	        
			try {
				Res=(ResultSet)DataObj.FetchData_Emp_DOB(NewEmplFlag.toString(), "NewEmplFlag", Res ,conn);
				if(Res.next()){
					
					LOP_ID=Integer.parseInt(Res.getString("leaveid").toString());
					EMPLOYEEID_LOP=Res.getString("employeeid").toString();
					LOP_ydate=Res.getString("ydate").toString();
					
				}
			}catch(Exception err){
			
				System.out.println("Error At Get Leave Quota" +err);
			}
		}

		 //LOP insertion for who are not in leave quota tbl start
		 if(LOP_ID==0 && EMPLOYEEID_LOP!=null && Leave_Type.equalsIgnoreCase("LOP")){
			 
			 try {
				conn.setAutoCommit(false);
				PS_lop=conn.prepareStatement("INSERT INTO HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_QUOTA (EMPLOYEEID, LEAVETYPEID, YEAR, QUANTITY, AVAILABLEQTY, USEDQTY, HOLD, DAYMODE, MAXLEAVE, BACKDATE, COUNT_WOFF, COUNT_HOLIDAY, STATUS, LOGID, CREATEDBY, LEAVE_MODE) "
						+ "VALUES(?,15,?,0.0,0.0,0.0,0.0,1,90,4,0,0.0,1001,0,0,'OP')");
				PS_lop.setString(1,EMPLOYEEID_LOP);
				PS_lop.setString(2,LOP_ydate);
				lopins_count= PS_lop.executeUpdate();
				System.out.println("lopins_count" +lopins_count);
				if(lopins_count>0){
					//conn.setAutoCommit(true);
					conn.commit();
				}else{
					conn.rollback();
				}
				
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				 System.out.println("Error Message At lop insertion table::" +e);
				e.printStackTrace();
			}
				
				
				
			 
		 }else{
			 
			 //if(LOP_ID==15){
			 lopins_count=1;
			// }
			 
		 }
		 if(LOP_ID==15){
		 lopins_count=1;
		 }
		 //LOP insertion for who are not in leave quota tbl end ;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		StringBuffer  Leave_Quota=new  StringBuffer();
		StringBuffer  From_toDaysCal=new  StringBuffer();
		
    	Leave_Quota.append(" select B.employeeid,C.employeesequenceno,trim(A.SHORTNAME) SHORTNAME,B.quantity , B.AVAILABLEQTY , B.HOLD, B.quantity+B.HOLD as totalavl, b.USEDQTY, trim(A.NAME) Fullname,B.DAYMODE,if(B.daymode=0,if(B.availableqty<=B.maxleave,B.availableqty,B.maxleave),B.maxleave) MAXLEAVE_C ,B.COUNT_WOFF,B.BACKDATE, B.Leavetypeid , ifnull(B.FOR_MONTH,0) FOR_MONTH from  hclhrm_prod.tbl_leave_type A,  ");
    	Leave_Quota.append(" hclhrm_prod_others.tbl_emp_leave_quota B, ");
    	Leave_Quota.append(" hclhrm_prod.tbl_employee_primary C ");
    	Leave_Quota.append(" where B.employeeid=C.employeeid and C.employeesequenceno in("+username+") and ");
    	Leave_Quota.append(" B.Leavetypeid=A.Leavetypeid  and b.status=1001 and A.SHORTNAME='"+Leave_Type+"' ");
    	
		Res=null;
        boolean LevFlag=false;
		try {
			Res=(ResultSet)DataObj.FetchData_Emp_DOB(Leave_Quota.toString(), "Leave_Quota", Res ,conn);
			if(Res.next()){
				   
					Leave_New.put(username+"_EMPID", Res.getString("employeeid"));
					Leave_New.put(username+"_SEQ", Res.getString("employeesequenceno"));
					Leave_New.put(username+"_"+Res.getString("Leavetypeid"), Res.getString("AVAILABLEQTY"));
					Leave_New.put(username+"_AVAILABLEQTY", Res.getString("AVAILABLEQTY"));
					Leave_New.put(username+"COUNT_WOFF", Res.getString("COUNT_WOFF"));
					Leave_New.put(username+"_DAYMODE", Res.getString("DAYMODE"));
					Leave_New.put("LEAVE_TYPE_ID", Res.getString("Leavetypeid"));
					Leave_New.put("FOR_MONTH", Res.getString("FOR_MONTH"));
					
					Leave_New.put("MAXLEAVE", Res.getString("MAXLEAVE_C"));
					
					
					
					
			}
		}catch(Exception err){
		
			System.out.println("Error At Get Leave Quota" +err);
			
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
       //System.out.println(username +"~~username~password~~" +password);
		From_toDaysCal.append(" select selected_date,IF(DAYNAME(selected_date)='SUNDAY','WOFF','WDAY') DAYTYPE, ");
		From_toDaysCal.append(" V2.holidaydate,  ");
		From_toDaysCal.append(" ((datediff('"+to_date+"','"+from_date+"')+1)-(if('"+to_date+"'='"+Hal_date+"' OR '"+from_date+"'='"+Hal_date+"' ,0.5,0))) AS DAYESDIFF,'"+from_date+"' as fromdate,'"+to_date+"' as todate, ");
		From_toDaysCal.append(" if(DAYNAME(selected_date)='SUNDAY' && selected_date=V2.holidaydate,1,if(DAYNAME(selected_date)='SUNDAY',1,if(selected_date=V2.holidaydate,1,0))) AS HW  ");
		From_toDaysCal.append(" , '"+HalfDay+"' As HALFDAY, '"+Hal_date+"' As Hal_date,'"+subject+"' AS subject,'"+reason+"' AS reason FROM  ");
		From_toDaysCal.append(" (select adddate('1970-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) selected_date from  ");
		From_toDaysCal.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0,  ");
		From_toDaysCal.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, ");
		From_toDaysCal.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2,  ");
		From_toDaysCal.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3,  ");
		From_toDaysCal.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v ");
		From_toDaysCal.append(" left join(  ");
		From_toDaysCal.append(" select holidaydate from hclhrm_prod.tbl_holidays ");
		From_toDaysCal.append(" where statusid=1001 and businessunitid in(select companyid from hclhrm_prod.tbl_employee_primary  ");
		From_toDaysCal.append(" where employeesequenceno in("+username+")) ");
		From_toDaysCal.append(" )V2 on V2.holidaydate=V.selected_date ");
		From_toDaysCal.append(" where selected_date between '"+from_date+"' and '"+to_date+"' ");
         	
	    System.out.println("Query:: "+From_toDaysCal.toString());
		/*Leave_Type=request.getParameter("Leave_Type");
		from_date=request.getParameter("from_date");
		to_date=request.getParameter("to_date");
		HalfDay=request.getParameter("HalfDay");
		Hal_date=request.getParameter("Hal_date");
		subject=request.getParameter("subject");
		reason=request.getParameter("reason");
		*/
		
		Res=null;
       try {
			Res=(ResultSet)DataObj.FetchData_Emp_DOB(From_toDaysCal.toString(), "Leave_Quota", Res ,conn);
			while(Res.next()){
				
				//double Holiday_Count=0,LeaveCount=0 ,Leave_Diff=0;
				Leave_Diff=Double.parseDouble(Res.getString("DAYESDIFF").toString());
				
				String SleDate=Res.getString("selected_date").toString();
				LeaveUnicInsertion.add(Res.getString("selected_date"));
				LeaveUnicInsertion_map.put(SleDate+"_DAYTYPE" , Res.getString("DAYTYPE").toString());
				LeaveUnicInsertion_map.put(SleDate+"_fromdate" , Res.getString("fromdate").toString());
				LeaveUnicInsertion_map.put(SleDate+"_todate" , Res.getString("todate").toString());
				LeaveUnicInsertion_map.put(SleDate+"_HALFDAY" , Res.getString("HALFDAY").toString());
				
				LeaveUnicInsertion_map.put(SleDate+"_Hal_date" , Res.getString("Hal_date").toString());
				
				LeaveUnicInsertion_map.put(SleDate+"_subject" , Res.getString("subject").toString());
				LeaveUnicInsertion_map.put(SleDate+"_reason" , Res.getString("reason").toString());
				
				if(Double.parseDouble(Leave_New.get(username+"COUNT_WOFF").toString())>0){
				
					Holiday_Count=Holiday_Count+Double.parseDouble(Res.getString("HW").toString());
					
				}
				
				if(Res.getString("DAYTYPE").toString().equalsIgnoreCase("WOFF") || Res.getString("Hal_date")!=null){
				
					Holiday_dates_for_month.append(Res.getString("selected_date"));
				    Holiday_dates_for_month.append(",");
			   }
				
				
				
				}
		}catch(Exception err){
		
			System.out.println("From_toDaysCal Error At Get Leave Quota" +err);
			
		}
		
       
       StringBuffer  PendingLeave= new StringBuffer();
       StringBuffer  PendingApprovals= new StringBuffer();
       PendingLeave.append(" SELECT count(*) FROM hclhrm_prod_others.tbl_emp_attn_req  ");
       PendingLeave.append(" WHERE ");
       PendingLeave.append(" EMPLOYEEID in ("+username+") AND ");
       PendingLeave.append(" REQ_TYPE IN ('LR') AND FLAG IN ('P') ");
       
       Res=null;
       try {
			Res=(ResultSet)DataObj.FetchData_Emp_DOB(PendingLeave.toString(), "PendingLeave", Res ,conn);
			if(Res.next()){
				
			     PeandingLeave=Integer.parseInt(Res.getString(1).toString());
			     
			     if(Leave_Type.equalsIgnoreCase("LOP")){
			    	 PeandingLeave=0;
			     }
				
				}
		}catch(Exception err){
		
			System.out.println("From_toDaysCal Error At Get Leave Quota" +err);
			
		}
       
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PendingApprovals.append(" SELECT COUNT(*) FROM hclhrm_prod_others.tbl_emp_attn_req ");
		PendingApprovals.append(" WHERE ");
		PendingApprovals.append(" EMPLOYEEID in("+username+") AND ");
		PendingApprovals.append(" REQ_TYPE='LR' AND ");
		PendingApprovals.append(" FLAG IN ('P','A') AND ");
		PendingApprovals.append(" '"+from_date+"' BETWEEN FROM_DATE AND TO_DATE ");
		PendingApprovals.append(" UNION ALL ");
		PendingApprovals.append(" SELECT COUNT(*) FROM hclhrm_prod_others.tbl_emp_attn_req ");
		PendingApprovals.append(" WHERE ");
		PendingApprovals.append(" EMPLOYEEID in("+username+") AND ");
		PendingApprovals.append(" REQ_TYPE='LR' AND ");
		PendingApprovals.append(" FLAG IN ('P','A') AND ");
		PendingApprovals.append(" '"+to_date+"' BETWEEN FROM_DATE AND TO_DATE ");
		
		
		
		Res=null;
	       try {
				Res=(ResultSet)DataObj.FetchData_Emp_DOB(PendingApprovals.toString(), "PendingApprovals", Res ,conn);
				while(Res.next()){
					
					Unic_Leave=Unic_Leave+Integer.parseInt(Res.getString(1).toString());
					
					}
			}catch(Exception err){
			
				System.out.println("From_toDaysCal Error At Get Leave Quota" +err);
				
			}
	       
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		// ****************** For Probitionary Employess Leave Restriction Start*******************************************************
		System.out.println("Error Trace 1");
			double for_month_limit=Double.parseDouble(Leave_New.get("FOR_MONTH").toString());
			
			System.out.println("Error Trace 1.1");
			
			double maxleave_month_limit=Double.parseDouble(Leave_New.get("MAXLEAVE").toString());
			System.out.println("Error Trace 1.2");
			double for_month_day_mode=Double.parseDouble(Leave_New.get(username+"_DAYMODE").toString());
			System.out.println("Error Trace 1.3");
			boolean check_leave_month=false;
			double  btw_payperiod_leaves=0;
			double  newbtw_payperiod_leaves=0;
			System.out.println("Error Trace 2");
			StringBuffer Probitionary_emp_leave_check=new StringBuffer();
			
			StringBuffer check_lev_btw_payperiod=new StringBuffer();
			
			Probitionary_emp_leave_check.append(" SELECT LEAVE_COUNT_BT_DAYS FROM hclhrm_prod_others.tbl_emp_leave_report  ");
			Probitionary_emp_leave_check.append(" WHERE LEAVEON BETWEEN '"+EmpData.get("FROMDATE").toString()+"'  AND '"+EmpData.get("TODATE").toString()+"' AND ");
			Probitionary_emp_leave_check.append(" LEAVE_TYPE NOT IN ('OD','LOP') AND ");
			Probitionary_emp_leave_check.append(" MANAGER_STATUS IN ('P','A') AND ");
			Probitionary_emp_leave_check.append(" EMPLOYEEID in("+username+") AND LEAVE_TYPE='"+Leave_Type+"' group by RID ");
			
			check_lev_btw_payperiod.append(" SELECT if(b.selected_date='"+Hal_date+"',0.5,1 ) FROM (  ");
			check_lev_btw_payperiod.append(" (select adddate('1970-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) selected_date from ");
			check_lev_btw_payperiod.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0, ");
			check_lev_btw_payperiod.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, ");
			check_lev_btw_payperiod.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2, ");
			check_lev_btw_payperiod.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3, ");
			check_lev_btw_payperiod.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) ");
			check_lev_btw_payperiod.append(" ) a ");
			check_lev_btw_payperiod.append(" LEFT JOIN ");
			check_lev_btw_payperiod.append(" ( ");
			check_lev_btw_payperiod.append(" SELECT selected_date FROM ( ");
			check_lev_btw_payperiod.append(" (select adddate('1970-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) selected_date from ");
			check_lev_btw_payperiod.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0, ");
			check_lev_btw_payperiod.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, ");
			check_lev_btw_payperiod.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2, ");
			check_lev_btw_payperiod.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3, ");
			check_lev_btw_payperiod.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) V ");
			check_lev_btw_payperiod.append(" ) WHERE selected_date BETWEEN '"+EmpData.get("FROMDATE").toString()+"' and '"+EmpData.get("TODATE").toString()+"' ");
			check_lev_btw_payperiod.append(" )b ON a.selected_date=b.selected_date ");
			check_lev_btw_payperiod.append(" WHERE A.selected_date BETWEEN '"+from_date+"' and '"+to_date+"' and b.selected_date is not null ");
			
			if(Leave_New.get(username+"COUNT_WOFF").toString().equalsIgnoreCase("1")){
			check_lev_btw_payperiod.append(" and A.selected_date not in("+Holiday_dates_for_month.toString()+") ");
			}
		
			System.out.println(for_month_limit +"SAVE MODE"+ for_month_day_mode); 
			if(for_month_limit ==1 && for_month_day_mode==0){
				
				Res=null;
			       try {
						Res=(ResultSet)DataObj.FetchData_Emp_DOB(Probitionary_emp_leave_check.toString(), "Probitionary_emp_leave_check", Res ,conn);
						if(Res.next()){
							
							btw_payperiod_leaves=Double.parseDouble(Res.getString(1).toString());
							
							}
					}catch(Exception err){
					
						System.out.println("From_toDaysCal Error At Get Leave Quota" +err);
						
					}
			       
			       Res=null;
			       try {
						Res=(ResultSet)DataObj.FetchData_Emp_DOB(check_lev_btw_payperiod.toString(), "check_lev_btw_payperiod", Res ,conn);
						while(Res.next()){
							newbtw_payperiod_leaves=newbtw_payperiod_leaves+Double.parseDouble(Res.getString(1).toString());
							}
					}catch(Exception err){
					
						System.out.println("From_toDaysCal Error At Get Leave Quota" +err);
					}   
			       
			      /* 1.0SAVE MODE0.0
			       0.0~SAVE POINT 2~2.0
			       2.0~SAVE POINT 2.1~3.0
			       2.0~SAVE POINT 3~3.0*/
			       double combination=btw_payperiod_leaves+newbtw_payperiod_leaves;
			       
			       System.out.println(btw_payperiod_leaves +"~SAVE POINT 2~" +newbtw_payperiod_leaves);
			       
			       System.out.println(combination +"~SAVE POINT 2.1~" +maxleave_month_limit);
			       
			       if(combination<=maxleave_month_limit){
			    	  
			    	   System.out.println(combination +"~SAVE POINT 3~" +maxleave_month_limit);
			    	   
			    	    check_leave_month=true;
			    	   
			       }
			       
			}else{
				
				System.out.println("False Condition at check_leave_month ");
				check_leave_month=true;
				
			}
			
			//Leave_New.put(username+"_DAYMODE", Res.getString("DAYMODE"));
			
			
			
	   // ****************** For Probitionary Employess Leave Restriction  End   *******************************************************
			
        /// ***************************** This part for clubbed holiday **************************************
			boolean holidayexistpreview=false ,holidayexistfeature=false;
			StringBuffer  preview_feature_leaves= new StringBuffer();
			StringBuffer  less_fromdate= new StringBuffer();
			StringBuffer  max_todate= new StringBuffer();
			
			   EmpData.put("lessmindate", "0");
			   EmpData.put("lessmintype", "0");
			   EmpData.put("gratertype", "0");
			   EmpData.put("graterdate", "0");
			   
			preview_feature_leaves.append(" SELECT LR.EMPLOYEEID,ifnull(LR.TO_DATE,0) as lessmindate,ifnull(LR.LEAVE_TYPE,0) lessmintype,ifnull(AR.FROM_DATE,0) graterdate ,ifnull(AR.LEAVE_TYPE,0) gratertype  ");
			preview_feature_leaves.append(" FROM (SELECT A.EMPLOYEEID,A.TO_DATE,B.LEAVE_TYPE ");
			preview_feature_leaves.append(" FROM HCLHRM_PROD_OTHERS.TBL_EMP_ATTN_REQ A ");
			preview_feature_leaves.append(" LEFT JOIN HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_REQ B ON A.RID=B.RID ");
			preview_feature_leaves.append(" WHERE A.EMPLOYEEID in("+username+") AND A.REQ_TYPE='LR' and B.LEAVE_TYPE not in ('OD','LOP') ");
			preview_feature_leaves.append(" GROUP BY TO_DATE HAVING MAX(A.TO_DATE) < '"+from_date+"'  ORDER BY A.TO_DATE DESC LIMIT 1 ");
			preview_feature_leaves.append(" ) LR ");
			preview_feature_leaves.append(" LEFT JOIN ( ");
			preview_feature_leaves.append(" SELECT A.EMPLOYEEID,A.FROM_DATE,B.LEAVE_TYPE ");
			preview_feature_leaves.append(" FROM HCLHRM_PROD_OTHERS.TBL_EMP_ATTN_REQ A ");
			preview_feature_leaves.append(" LEFT JOIN HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_REQ B ON A.RID=B.RID ");
			preview_feature_leaves.append(" WHERE A.EMPLOYEEID in("+username+")  AND A.REQ_TYPE='LR' and B.LEAVE_TYPE not in ('OD','LOP') ");
			preview_feature_leaves.append(" GROUP BY A.TO_DATE HAVING MAX(A.TO_DATE) >'"+to_date+"'  ORDER BY A.TO_DATE DESC LIMIT 1 ");
			preview_feature_leaves.append(" ) AR ON LR.EMPLOYEEID=AR.EMPLOYEEID ");
			Res=null;
		       try {
					Res=(ResultSet)DataObj.FetchData_Emp_DOB(preview_feature_leaves.toString(), "PendingApprovals", Res ,conn);
					if(Res.next()){
						   EmpData.put("lessmindate", Res.getString("lessmindate").toString());
						   EmpData.put("lessmintype", Res.getString("lessmintype").toString());
						   EmpData.put("gratertype", Res.getString("gratertype").toString());
						   EmpData.put("graterdate", Res.getString("graterdate").toString());
						   if(!Res.getString("lessmindate").toString().equalsIgnoreCase("0")){
						     holidayexistpreview=true;
						   }
						   if(!Res.getString("graterdate").toString().equalsIgnoreCase("0")){
							   holidayexistfeature=true;
							}
						}
				}catch(Exception err){
					System.out.println("Less Min Date Cal -From_toDaysCal Error At Get Leave Quota" +err);
				}
		       
		       
		       less_fromdate.append(" select selected_date,IF(DAYNAME(selected_date)='SUNDAY','WOFF','WDAY') DAYTYPE, " );
		       less_fromdate.append(" IF(IF(DAYNAME(selected_date)='SUNDAY','WOFF','WDAY')='WDAY',1,0) STATUS,V2.holidaydate, " );
		       less_fromdate.append(" if(DAYNAME(selected_date)='SUNDAY' && selected_date=V2.holidaydate,1,if(DAYNAME(selected_date)='SUNDAY',1,if(selected_date=V2.holidaydate,1,0))) AS HW " );
		       less_fromdate.append(" FROM " );
		       less_fromdate.append(" (select adddate('1970-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) selected_date from " );
		       less_fromdate.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0, " );
		       less_fromdate.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, " );
		       less_fromdate.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2, " );
		       less_fromdate.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3, " );
		       less_fromdate.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v " );
		       less_fromdate.append(" left join( " );
		       less_fromdate.append(" select holidaydate from hclhrm_prod.tbl_holidays " );
		       less_fromdate.append(" where statusid=1001 and businessunitid in(select companyid from hclhrm_prod.tbl_employee_primary " );
		       less_fromdate.append(" where employeesequenceno in("+username+") ) " );
		       less_fromdate.append(" )V2 on V2.holidaydate=V.selected_date " );
		       less_fromdate.append(" where selected_date between '"+EmpData.get("lessmindate").toString()+"'+INTERVAL 1 DAY and '"+from_date+"'+INTERVAL -1 DAY " );

		       System.out.println("less_fromdate::" +less_fromdate.toString());
		       
		       max_todate.append(" select selected_date,IF(DAYNAME(selected_date)='SUNDAY','WOFF','WDAY') DAYTYPE, " );
		       max_todate.append(" IF(IF(DAYNAME(selected_date)='SUNDAY','WOFF','WDAY')='WDAY',1,0) STATUS,V2.holidaydate, " );
		       max_todate.append(" if(DAYNAME(selected_date)='SUNDAY' && selected_date=V2.holidaydate,1,if(DAYNAME(selected_date)='SUNDAY',1,if(selected_date=V2.holidaydate,1,0))) AS HW " );
		       max_todate.append(" FROM " );
		       max_todate.append(" (select adddate('1970-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) selected_date from " );
		       max_todate.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0, " );
		       max_todate.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, " );
		       max_todate.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2, " );
		       max_todate.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3, " );
		       max_todate.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v " );
		       max_todate.append(" left join( " );
		       max_todate.append(" select holidaydate from hclhrm_prod.tbl_holidays " );
		       max_todate.append(" where statusid=1001 and businessunitid in(select companyid from hclhrm_prod.tbl_employee_primary " );
		       max_todate.append(" where employeesequenceno in("+username+") ) " );
		       max_todate.append(" )V2 on V2.holidaydate=V.selected_date " );
		       max_todate.append(" where selected_date between '"+to_date+"' +INTERVAL 1 DAY and '"+EmpData.get("graterdate").toString()+"'+INTERVAL -1 DAY " );
               
		       System.out.println("less_fromdate::" +max_todate.toString());
		       
			// Applied leave should be todate < applied leave < from date of max
		        
		       if(holidayexistpreview){
		       Res=null;
		       try {
					Res=(ResultSet)DataObj.FetchData_Emp_DOB(less_fromdate.toString(), "less_fromdate", Res ,conn);
					while(Res.next()){
						 //preview Date
						if(Double.parseDouble(Res.getString("HW").toString())>0){
						  prev_holidays_woff=prev_holidays_woff+Double.parseDouble(Res.getString("HW").toString());
						}else if(Double.parseDouble(Res.getString("HW").toString())==0){
							prev_btw_workingdays=prev_btw_workingdays+1;
						}
					}
				}catch(Exception err){
					System.out.println("4From_toDaysCal Error At Get Leave Quota" +err);
				}
		       }
		       if(holidayexistfeature){
			       Res=null;
			       try {
						Res=(ResultSet)DataObj.FetchData_Emp_DOB(max_todate.toString(), "max_todate", Res ,conn);
						while(Res.next()){
						   // Feature date
							if(Double.parseDouble(Res.getString("HW").toString())>0){
								feature_holidays_woff=feature_holidays_woff+Double.parseDouble(Res.getString("HW").toString());
								}else if(Double.parseDouble(Res.getString("HW").toString())==0){
									feature_btw_workingdays=feature_btw_workingdays+1;
							 }
							  
						}
					}catch(Exception err){
						System.out.println("3From_toDaysCal Error At Get Leave Quota" +err);
					}
			    }
		       //Flag 1
		       
		       
	       ///****************************************************************************************************
			
			
			
		 /*if(Leave_Type.equalsIgnoreCase("LOP")){
			 
			 PeandingLeave=0;
		 }*/
		 boolean prev_flag_of_leave=false,feature_flag_of_leave=false,prev_combflag_of_leave=false,feature_combflag_of_leave=false;
		 String Message=null;
		 
         if(holidayexistpreview){
        	 
        	 //prev_holidays_woff/prev_btw_workingdays
        	  if(prev_btw_workingdays==0 && prev_holidays_woff>0){
        		if(EmpData.get("lessmintype").toString().equalsIgnoreCase(""+Leave_Type+"")){
        			
        			prev_combflag_of_leave=true;
        			
        		}else if(EmpData.get("lessmintype").toString().equalsIgnoreCase("SL") && Leave_Type.equalsIgnoreCase("EL") ){
        		
        			prev_combflag_of_leave=true;
        			
        			Holiday_Count=0;  // this is Add for holiday not consider with combination of both
        			
        		}else{
        			
        			prev_combflag_of_leave=false;
        			Message="Combination of "+EmpData.get("lessmintype").toString()+" & "+Leave_Type+"  not applicable please check with previous leave .. " ;
        		}
        	  }else if(prev_btw_workingdays>0 && (prev_holidays_woff==0 || prev_holidays_woff>0) ){
        		  
        		
          			prev_combflag_of_leave=true;
          			
          		
        		  
        	  }else if(prev_btw_workingdays==0 && prev_holidays_woff==0){
        		  
        		  
        		  if(EmpData.get("lessmintype").toString().equalsIgnoreCase(""+Leave_Type+"")){
          			
          			  prev_combflag_of_leave=true;
          			
          		}else if(EmpData.get("lessmintype").toString().equalsIgnoreCase("SL") && Leave_Type.equalsIgnoreCase("EL") ){
          		
          			prev_combflag_of_leave=true;
          			
          			Holiday_Count=0;  // this is Add for holiday not consider with combination of both
          			
          		}else{
          			
          			prev_combflag_of_leave=false;
          			Message="Combination of "+EmpData.get("lessmintype").toString()+" & "+Leave_Type+"  not applicable please check with previous leave .. " ;
          		}
        		  
        		  
        	  }
        	  
        	  System.out.println(prev_combflag_of_leave + "::1less_fromdate::" + prev_flag_of_leave);
        	  
        	  System.out.println(prev_btw_workingdays + "::Dates::" + prev_holidays_woff);
        	  
        	  if(prev_combflag_of_leave){
        		  
        		  if(prev_btw_workingdays==0 && prev_holidays_woff > 0){
        		 	  prev_flag_of_leave=false;
        		      Message=" Immediate Leave after weakoff/Holiday not eligible,should consider weakoff&Holiday also..OR Cancel previous leave and choose proper dates";
        	       }else if(prev_btw_workingdays>0 && (prev_holidays_woff>0||prev_holidays_woff==0)){
        	    	   prev_flag_of_leave=true;
        	    	   
        	       }else if(prev_btw_workingdays==0 && prev_holidays_woff==0){
        	    	   prev_flag_of_leave=true;
        	       
        	       }
        		
        	  System.out.println(prev_combflag_of_leave + "::21less_fromdate::" + prev_flag_of_leave);
        		 
         }
       } // Main preview if closed
         
         
 if(holidayexistfeature){ 
        	 
	/* feature_btw_workingdays
	 feature_holidays_woff
	 flg
	 feature_flag_of_leave
	 feature_combflag_of_leave
	 
	 EmpData.put("gratertype", Res.getString("gratertype").toString());
	   EmpData.put("graterdate", Res.getString("graterdate").toString());*/
	 
	 
        	 //prev_holidays_woff/prev_btw_workingdays
        	  if(feature_btw_workingdays==0 && feature_holidays_woff>0){
        		if(EmpData.get("gratertype").toString().equalsIgnoreCase(""+Leave_Type+"")){
        			
        			feature_combflag_of_leave=true;
        			
        		}else if(EmpData.get("gratertype").toString().equalsIgnoreCase("SL") && Leave_Type.equalsIgnoreCase("EL") ){
        		
        			feature_combflag_of_leave=true;
        			Holiday_Count=0;  // this is Add for holiday not consider with combination of both
        			
        		}else{
        			
        			feature_combflag_of_leave=false;
        			Message="Combination of "+EmpData.get("gratertype").toString()+" & "+Leave_Type+"  not applicable please check with previous leave .. " ;
        		}
        	  }else if(feature_btw_workingdays>0 && (feature_holidays_woff==0 || feature_holidays_woff>0) ){
        		  
        		 
          			feature_combflag_of_leave=true;
          			
          		
        		  
        	  }else if(feature_btw_workingdays==0 && feature_holidays_woff==0){
        		  
        		  
        		  if(EmpData.get("gratertype").toString().equalsIgnoreCase(""+Leave_Type+"")){
          			
        			  feature_combflag_of_leave=true;
        			  //Holiday_Count=0;
          			
          		}else if(EmpData.get("gratertype").toString().equalsIgnoreCase("SL") && Leave_Type.equalsIgnoreCase("EL") ){
          		
          			feature_combflag_of_leave=true;
          			Holiday_Count=0;  // this is Add for holiday not consider with combination of both
          			
          		}else{
          			
          			feature_combflag_of_leave=false;
          			Message="Combination of "+EmpData.get("gratertype").toString()+" & "+Leave_Type+"  not applicable please check with previous leave ..! " ;
          		}
        		  
        		  
        	  }
        	  
        	  System.out.println(feature_combflag_of_leave + "::feature 1less_fromdate::" + feature_flag_of_leave);
        	  
        	  System.out.println(feature_btw_workingdays + "::feature Dates::" + feature_holidays_woff);
        	  
        	  if(feature_combflag_of_leave){
        		  
        		  if(feature_btw_workingdays==0 && feature_holidays_woff > 0){
        			  feature_flag_of_leave=false;
        		      Message=" Immediate Leave after weakoff/Holiday not eligible,should consider weakoff&Holiday also..OR Cancel previous leave and choose proper dates..!";
        	       }else if(feature_btw_workingdays>0 && (feature_holidays_woff>0||feature_holidays_woff==0)){
        	    	   feature_flag_of_leave=true;
        	    	   
        	       }else if(feature_btw_workingdays==0 && feature_holidays_woff==0){
        	    	   feature_flag_of_leave=true;
        	       
        	       }
        		
        	  System.out.println(feature_combflag_of_leave + "::feature21less_fromdate::" + feature_flag_of_leave);
        		 
         }
       } // Main max if closed
 
 
         
       //  if((feature_btw_workingdays>=0 && feature_holidays_woff==0) )
		       
		       
			// PeandingLeave==0 &&  pending leave opption removed
       if(check_leave_month && (holidayexistpreview==false||(prev_combflag_of_leave && prev_flag_of_leave)) && (holidayexistfeature==false|| (feature_flag_of_leave && feature_combflag_of_leave))  &&  lopins_count==1 && Unic_Leave==0 && conn!=null && username!=null && password!=null && Routing.equalsIgnoreCase("LeaveMgM")){
			
    	   double Leav_avail=0;
    	   ps=null;
			//  Validation Data For Insertion
			try{
				LeaveCount=Leave_Diff-Holiday_Count;
				//LeaveCount=Leave_Diff-Holiday_Count;
			     DayMode=Double.parseDouble(Leave_New.get(username+"_DAYMODE").toString());
						
				 Leav_avail=Double.parseDouble(Leave_New.get(username+"_AVAILABLEQTY").toString());
				if(DayMode==0 && Leav_avail>=LeaveCount ){
					
					insertionFlag=true;
					
				}else if(DayMode>0){
					
					insertionFlag=true;
				
				}else{
					
					Atten_Req_Message="Request Not processed  :Eligible quantity "+Leav_avail+" , Applied Leave Count should be <= "+Leav_avail+" ";
					insertionFlag=false;
				}
				
			}catch(Exception err){
				
				System.out.println("Exception At Validation  "+err);
				
			}
			
			System.out.println(Leav_avail+ "~LEAVE MGM ~"+LeaveCount);
			////  Validation Data For Insertion
			if(insertionFlag){ // Insertion Part Started
				
				
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
		    	
		    	
				ps_1=conn.prepareStatement("insert into hclhrm_prod_others.tbl_emp_Leave_req(EMPLOYEEID,FROM_DATE,TO_DATE,SUBJECT,TO_EMAIL,CC_EMAIL,MESSAGE,REQ_TYPE,REQ_DATE,RANDOMID,HALF_DAY_STATUS,HALF_DAY_DATE,COMPOFF_DAY_STATUS,COMPOFF_DAY_DATE,Leave_Type,RID,from_loc,to_loc,TOTA_DAYS) values(?,?,?,?,?,?,?,'LR',curdate(),?,?,?,?,?,?,?,?,?,?)");
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
				ps_1.setString(17, ""+LeaveCount+"");
				
				
				if(DayMode==0){
				ps_leaveQuota=conn.prepareStatement("update hclhrm_prod_others.tbl_emp_leave_quota set USEDQTY=USEDQTY+?,AVAILABLEQTY=AVAILABLEQTY-? where EMPLOYEEID=? AND LEAVETYPEID=? and status=1001 limit 1");
				
				ps_leaveQuota.setString(1,""+LeaveCount+"");
				ps_leaveQuota.setString(2,""+LeaveCount+"");
				ps_leaveQuota.setString(3,Leave_New.get(username+"_EMPID").toString());
				ps_leaveQuota.setString(4,Leave_New.get("LEAVE_TYPE_ID").toString());
				
				}else{
					
					ps_leaveQuota=conn.prepareStatement("update hclhrm_prod_others.tbl_emp_leave_quota set USEDQTY=USEDQTY+?  where EMPLOYEEID=? AND LEAVETYPEID=? and status=1001 limit 1");
					
					ps_leaveQuota.setString(1,""+LeaveCount+"");
					ps_leaveQuota.setString(2,Leave_New.get(username+"_EMPID").toString());
					ps_leaveQuota.setString(3,Leave_New.get("LEAVE_TYPE_ID").toString());
				}
				
				Iterator singledate=LeaveUnicInsertion.iterator();
				ps_SingleDates=conn.prepareStatement("INSERT INTO hclhrm_prod_others.tbl_emp_leave_report (EMPLOYEEID, RID, LEAVEON, FROM_DATE, TO_DATE, LEAVE_COUNT, LEAVE_COUNT_BT_DAYS, LEAVE_TYPE, HALF_DAY,DAYSTATUS) VALUES ("
		    			+ "?,?,?,?,?,?,?,?,?,?)");
				
				System.out.println(LeaveUnicInsertion_map.toString()+"MAP DATA");
				while(singledate.hasNext()){
					
					        String SinglDate=singledate.next().toString();
					        String DayType=LeaveUnicInsertion_map.get(SinglDate+"_DAYTYPE").toString(); 
					        String fromdate=LeaveUnicInsertion_map.get(SinglDate+"_fromdate").toString();
					        String todate=LeaveUnicInsertion_map.get(SinglDate+"_todate").toString();
					        String Halfday=LeaveUnicInsertion_map.get(SinglDate+"_HALFDAY").toString();
					        String half_day_date=LeaveUnicInsertion_map.get(SinglDate+"_Hal_date").toString();
					        String Subject=LeaveUnicInsertion_map.get(SinglDate+"_subject").toString();
					        String Reason=LeaveUnicInsertion_map.get(SinglDate+"_reason").toString();
					    	
					   
					        System.out.println(half_day_date +"~~~~" +SinglDate );
					        
					    	ps_SingleDates.setString(1,username);
					    	ps_SingleDates.setLong(2,primaryKey);
					    	ps_SingleDates.setString(3,SinglDate);
					    	ps_SingleDates.setString(4,fromdate);
					    	ps_SingleDates.setString(5,todate);
					    	if(SinglDate.equalsIgnoreCase(half_day_date)){
					    		ps_SingleDates.setString(6,"0.5");
					    		ps_SingleDates.setString(9,Halfday);
					    	}else{
					    	   ps_SingleDates.setString(6,"1");
					    		ps_SingleDates.setString(9,"false");
					    	}
					    	ps_SingleDates.setString(7,""+LeaveCount+"");
					    	ps_SingleDates.setString(8,Leave_Type);
					    
					    	ps_SingleDates.setString(10,DayType);
					    	ps_SingleDates.addBatch();
					    	
				}
				
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
				
				
				if(DayMode==0){
					ps_leaveQuota=conn.prepareStatement("update hclhrm_prod_others.tbl_emp_leave_quota set USEDQTY=USEDQTY+?,AVAILABLEQTY=AVAILABLEQTY-? where EMPLOYEEID=? AND LEAVETYPEID=? and status=1001 limit 1");
					
					ps_leaveQuota.setString(1,""+LeaveCount+"");
					ps_leaveQuota.setString(2,""+LeaveCount+"");
					ps_leaveQuota.setString(3,Leave_New.get(username+"_EMPID").toString());
					ps_leaveQuota.setString(4,Leave_New.get("LEAVE_TYPE_ID").toString());
					
					}else{
						
						ps_leaveQuota=conn.prepareStatement("update hclhrm_prod_others.tbl_emp_leave_quota set USEDQTY=USEDQTY+?  where EMPLOYEEID=? AND LEAVETYPEID=? and status=1001 limit 1");
						
						ps_leaveQuota.setString(1,""+LeaveCount+"");
						ps_leaveQuota.setString(2,Leave_New.get(username+"_EMPID").toString());
						ps_leaveQuota.setString(3,Leave_New.get("LEAVE_TYPE_ID").toString());
					}
				
		    	
		    }
			//int count= ps.executeUpdate();
				
		    int count1= ps_1.executeUpdate();
		    int count2= ps_leaveQuota.executeUpdate();
		    int [] Batchup=ps_SingleDates.executeBatch();
				System.out.println(Batchup + "add Batch Count::"+count);
				System.out.println(Batchup + "add Batch length::"+Batchup.length);
			if(count>0 && count1>0 && count2>0){
					conn.commit();
					Atten_Req_Message="Your leave successfully processed..";
				}else{
					conn.rollback();
					Atten_Req_Message="Your Leave process failed please contact system admin..";
				}
			}
			catch (Exception e2)
			{
				Atten_Req_Message="Failed to process your request Please try again/ contact system admin.";
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
			
			}//   Insertion Flag Closing

		} else{
			
			if( Unic_Leave>0){
				Atten_Req_Message="Duplicate Dates not allowed Please Check & Re-process.";
			/*}else if(PeandingLeave>0){
				Atten_Req_Message="Request not Processed due to Leave Pending  approvals..";
			}*/
			}else if(check_leave_month==false){
				Atten_Req_Message="Monthly leave quota exceed..!";
			}else if(holidayexistpreview && !prev_combflag_of_leave){
				Atten_Req_Message=Message;
			}else if(holidayexistfeature && !prev_flag_of_leave){
				
				     Atten_Req_Message=Message;
			}else{
				Atten_Req_Message="Authentication failed please Re-login..!";
			}

		}// IF Close For Insertion
         
		if(Routing.equalsIgnoreCase("LeaveMgM")){
			System.out.println("Atten_Req_Message::"+Atten_Req_Message);
			out.write(""+Atten_Req_Message+"");  
		}else{
			request.getRequestDispatcher("/login.html").forward(request, response);  
		}
	}  
}
