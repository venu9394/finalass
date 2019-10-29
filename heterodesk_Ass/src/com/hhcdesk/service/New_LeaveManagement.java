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
public class New_LeaveManagement extends HttpServlet {

	
	private static final Logger LOGGER = Logger.getLogger("New_LeaveManagement");
	public New_LeaveManagement() {
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext c = getServletContext();
		HttpSession session = request.getSession(false);
		Statement statement=null;
		String OTHFLAG="N";
		ResultSet Res=null;
		DataSource dataSource=null;
		java.sql.Connection conn=null;
		boolean Holiday_dates_for_month_flg=false;
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
		String EMPLOYEEID_LOP=null,LOP_ydate=null ;
		int User_Auth_auth=0;
		double prev_holidays_woff=0,prev_btw_workingdays=0,feature_holidays_woff=0,feature_btw_workingdays=0;
		System.out.println("New Leave Management :: Routing "+Routing);

		String BuID=session.getAttribute("EMPBUID").toString();
		
		String Leave_Type=null,from_date=null,to_date=null,HalfDay=null,Hal_date=null,compoff=null,comm_date=null,to_mail=null,cc_mail=null,subject=null,reason=null,FROMDATE=null,TODATE=null,
		Atten_Req_Message="Failed to process your request please contact system admin.";
		String HR_ATT=null,HR_ATT_USER=null,From_loc=null,To_loc=null;
		
		//***************************** Authentication
		
		Map EMAILDATA=new HashMap();
		String HR_MAIL=" ",EMP_MAIL=" ";
	      try{
	        EMAILDATA=(Map)session.getAttribute("EMAILDATA_MAP");
	        HR_MAIL=EMAILDATA.get("BULOC").toString();
	        EMP_MAIL=EMAILDATA.get("EMPEMAIL").toString();
	      }catch(Exception Err){
	    	  System.out.println("Email fetching Error" +Err);
	      }
		
		ResourceBundle bundle_info =(ResourceBundle)(c.getAttribute("bundle"));
		String Query=bundle_info.getString("HHCL_DESK_USER_LOGIN_AUTH");
		StringBuffer User_Authen=new StringBuffer();
		User_Authen.append(Query);
		
		
		//*****************************
		
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
		
		
		// ********************************** User Authentication
		Res=null;
		try {
			ps=conn.prepareStatement(User_Authen.toString());
			ps.setInt(1,Integer.parseInt(username));
			ps.setString(2,password);
			Res=(ResultSet)DataObj.FetchDataPrepare_2(ps, "User Authentication",Res,conn);
			//Res=(ResultSet)DataObj.FetchData("Select * from hclhrm_prod.tbl_employee_primary", "UserAuthentiCation", Res ,conn);
			if(Res.next()){
				
				User_Auth_auth=Res.getInt(1);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
// ********************************** User Authentication	
		
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
			OTHFLAG=request.getParameter("OTHFLAG");
			
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
		
	//	String Query=bundle_info.getString("HHCL_DESK_USER_LOGIN");
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
		
	    Res=null;
        
        StringBuffer StrReverse=new StringBuffer();
        //Hal_date
        StrReverse.append(" select date_format(STR_TO_DATE('"+from_date+"' ,'%d-%m-%Y'),'%Y-%m-%d') , date_format(STR_TO_DATE('"+to_date+"' ,'%d-%m-%Y'),'%Y-%m-%d'), ifnull(date_format(STR_TO_DATE('"+Hal_date+"' ,'%d-%m-%Y'),'%Y-%m-%d'),'0000-00-00') from dual ");
         
 		try {
 			  Res=(ResultSet)DataObj.FetchData_Emp_DOB(StrReverse.toString(), "Leave_Quota", Res ,conn);
 			if(Res.next()){
 				
 				from_date=Res.getString(1).toString();
 				to_date=Res.getString(2).toString();
 				Hal_date=Res.getString(3).toString();
 				
 			}
 			}catch(Exception err){
 				System.out.println("Exception at reverse" +err);
 			}
 		
 		
 		
 		
		
		StringBuffer payperiod=new StringBuffer();
		StringBuffer Leave_Monthly_validation=new StringBuffer();
		
		/*payperiod.append(" SELECT if('"+from_date+"' > '"+to_date+"',1,0) As DateValidation,if('"+from_date+"'<= now() AND '"+to_date+"'<= now() ,1,0) As DateValidationSL, ifnull(FROMDATE,'0000-00-00') FROMDATE ,ifnull(TODATE,'0000-00-00') TODATE FROM HCLADM_PROD.TBL_TRANSACTION_DATES ");
		payperiod.append(" WHERE BUSINESSUNITID IN (SELECT COMPANYID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO in("+username+")) AND ");
		payperiod.append(" TRANSACTIONDURATION IN (SELECT MAX(TRANSACTIONDURATION) FROM HCLADM_PROD.TBL_TRANSACTION_DATES) AND TRANSACTIONTYPEID=1 ");*/
		
		
		payperiod.append(" SELECT if('"+from_date+"' >'"+to_date+"',1,0) As DateValidation, ");
		payperiod.append(" if('"+from_date+"'<= now() AND '"+to_date+"'<= now() ,1,0) As DateValidationSL, ");
		payperiod.append(" ifnull(FROMDATE,'0000-00-00') FROMDATE , ");
		payperiod.append(" ifnull(TODATE,'0000-00-00') TODATE,PAYPERIOD ");
		payperiod.append(" FROM ");
		payperiod.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY T1 ");
		payperiod.append(" JOIN ");
		payperiod.append(" (SELECT MAX(TRANSACTIONDURATION) PAYPERIOD,BUSINESSUNITID FROM HCLADM_PROD.TBL_TRANSACTION_DATES WHERE TRANSACTIONTYPEID=1 ");
		payperiod.append(" GROUP BY BUSINESSUNITID)T2 ON T1.COMPANYID=T2.BUSINESSUNITID ");
		payperiod.append(" JOIN HCLADM_PROD.TBL_TRANSACTION_DATES T3 ON T2.PAYPERIOD=T3.TRANSACTIONDURATION AND T2.BUSINESSUNITID=T3.BUSINESSUNITID AND T3.TRANSACTIONTYPEID=1 ");
		payperiod.append(" WHERE ");
		payperiod.append(" T1.EMPLOYEESEQUENCENO IN ("+username+") ");


		
		
		
		
		
		
		System.out.println("payperiod::" +payperiod.toString());
		Res=null;
		
		
        
		try {
			Res=(ResultSet)DataObj.FetchData_Emp_DOB(payperiod.toString(), "NewEmplFlag", Res ,conn);
			if(Res.next()){
				
				System.out.println(Res.getString("FROMDATE").toString() +"~PayPeriod Days~"+Res.getString("TODATE").toString());
				EmpData.put("FROMDATE", Res.getString("FROMDATE").toString());
				EmpData.put("TODATE",   Res.getString("TODATE").toString());
				
				EmpData.put("TODATE_VALID",   Res.getString("DateValidation").toString());
				
				if(Leave_Type.equalsIgnoreCase("SL") ){
				EmpData.put("DATE_VALID_SL",   Res.getString("DateValidationSL").toString());
				}else{
					EmpData.put("DATE_VALID_SL",   "1");
				}
				
				
			}
		}catch(Exception err){
		
			System.out.println("Error At Get Leave Quota" +err);
		}
	
		if(EmpData.get("FROMDATE").toString()!=null){
			System.out.println("Mahesh~~~"+EmpData.put("FROMDATE","''"));
		
		
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
		Leave_Monthly_validation.append(" E.LEAVEON BETWEEN '"+EmpData.get("FROMDATE").toString()+"' AND '"+EmpData.get("TODATE").toString()+"' AND D.LEAVE_TYPE NOT IN ('LOP','OD') AND D.LEAVE_TYPE='"+Leave_Type+"' ");
		}
		
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
				//PS_lop.setString(2,LOP_ydate);
				PS_lop.setString(2,"2018"); // for year changes
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
		 
		 
		/*try {
			Thread.sleep();
		} catch (InterruptedException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}*/
		
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
		
	
		
    	/*From_toDaysCal.append(" select selected_date,IF(DAYNAME(selected_date)='SUNDAY','WOFF','WDAY') DAYTYPE, ");
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
		*/
		 if(BuID.equalsIgnoreCase("15") || BuID.equalsIgnoreCase("16") ){	
		    From_toDaysCal.append(" select selected_date,IF(DAYNAME(selected_date)='Saturday','WOFF','WDAY') DAYTYPE, ");
		    From_toDaysCal.append(" ifnull(V2.holidaydate,'0000-00-00') holidaydate,  ");
		    From_toDaysCal.append(" ((datediff('"+to_date+"','"+from_date+"')+1)-(if('"+to_date+"'='"+Hal_date+"' OR '"+from_date+"'='"+Hal_date+"' ,0.5,0))) AS DAYESDIFF,'"+from_date+"' as fromdate,'"+to_date+"' as todate, ");
		    From_toDaysCal.append(" if(DAYNAME(selected_date)='Saturday' && selected_date=ifnull(V2.holidaydate,'0000-00-00'),1,if(DAYNAME(selected_date)='Saturday',1,if(selected_date=ifnull(V2.holidaydate,'0000-00-00'),1,0))) AS HW  ");
		 }else{
			 
				From_toDaysCal.append(" select selected_date,IF(DAYNAME(selected_date)='SUNDAY','WOFF','WDAY') DAYTYPE, ");
				From_toDaysCal.append(" ifnull(V2.holidaydate,'0000-00-00') holidaydate,  ");
				From_toDaysCal.append(" ((datediff('"+to_date+"','"+from_date+"')+1)-(if('"+to_date+"'='"+Hal_date+"' OR '"+from_date+"'='"+Hal_date+"' ,0.5,0))) AS DAYESDIFF,'"+from_date+"' as fromdate,'"+to_date+"' as todate, ");
				From_toDaysCal.append(" if(DAYNAME(selected_date)='SUNDAY' && selected_date=ifnull(V2.holidaydate,'0000-00-00'),1,if(DAYNAME(selected_date)='SUNDAY',1,if(selected_date=ifnull(V2.holidaydate,'0000-00-00'),1,0))) AS HW  ");
			
			 
		 }
		From_toDaysCal.append(" , '"+HalfDay+"' As HALFDAY, '"+Hal_date+"' As Hal_date FROM  ");
		From_toDaysCal.append(" (select adddate('1970-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) selected_date from  ");
		From_toDaysCal.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0,  ");
		From_toDaysCal.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, ");
		From_toDaysCal.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2,  ");
		From_toDaysCal.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3,  ");
		From_toDaysCal.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v ");
		From_toDaysCal.append("  left join(   ");
		From_toDaysCal.append(" select holidaydate from hclhrm_prod.tbl_holidays ");
		From_toDaysCal.append(" where statusid=1001 and businessunitid in (select companyid from hclhrm_prod.tbl_employee_primary  ");
		From_toDaysCal.append(" where employeesequenceno in("+username+")) ");
		From_toDaysCal.append(" ) V2 on V2.holidaydate=V.selected_date ");
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
				
				
				LeaveUnicInsertion_map.put(SleDate+"_subject" , subject);
				LeaveUnicInsertion_map.put(SleDate+"_reason" , reason);
				
				/*LeaveUnicInsertion_map.put(SleDate+"_subject" , Res.getString("subject").toString());
				LeaveUnicInsertion_map.put(SleDate+"_reason" , Res.getString("reason").toString());*/
				
				if(Double.parseDouble(Leave_New.get(username+"COUNT_WOFF").toString())>0){
				
					Holiday_Count=Holiday_Count+Double.parseDouble(Res.getString("HW").toString());
					
				}
				
				if(Res.getString("DAYTYPE").toString().equalsIgnoreCase("WOFF") || Res.getString("Hal_date")!=null){
				
					Holiday_dates_for_month_flg=true;
					
					Holiday_dates_for_month.append(Res.getString("selected_date"));
					
				    Holiday_dates_for_month.append(", ");
			   }
				
				
		///********************************* leave Quota For MUM & HYD **************	
				
				
				}
		}catch(Exception err){
		
			System.out.println("Quey From_toDaysCal Error At Get Leave Quota" +err);
			
		}
		
      try{
    	  if(Holiday_dates_for_month_flg){
    	  Holiday_dates_for_month.append("0000-00-00");
    	  }
      }catch(Exception err){
    	System.out.println("Holiday_dates_for_month::" +Holiday_dates_for_month.toString());  
      }
     
 // currently not used
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
		
			System.out.println("1 From_toDaysCal Error At Get Leave Quota" +err);
			
		}
       
 // currently not used	
		
	/*	PendingApprovals.append(" SELECT COUNT(*) FROM hclhrm_prod_others.tbl_emp_attn_req ");
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
		PendingApprovals.append(" '"+to_date+"' BETWEEN FROM_DATE AND TO_DATE ");*/
		
		PendingApprovals.append(" SELECT IF(COUNT(*)>1,1,COUNT(*))CNT FROM");
		PendingApprovals.append(" (SELECT B.EMPLOYEESEQUENCENO EmpCode,B.CALLNAME EmpName,DATE_FORMAT(SELECTED_DATE,'%d-%m-%Y') Day,A.FLAG,E.LEAVE_TYPE,");
 if(BuID.equalsIgnoreCase("15") ||BuID.equalsIgnoreCase("16")){
		PendingApprovals.append(" IF(SELECTED_DATE='Saturday','WOFF',");
 }else{
	 PendingApprovals.append(" IF(SELECTED_DATE='SUNDAY','WOFF',"); 
 }
		
		PendingApprovals.append(" E.LEAVE_TYPE) CODE FROM");
		PendingApprovals.append(" HCLHRM_PROD_OTHERS.TBL_EMP_ATTN_REQ A");
		PendingApprovals.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY B ON A.EMPLOYEEID=B.EMPLOYEESEQUENCENO");
		PendingApprovals.append(" LEFT JOIN HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_REQ E ON A.RID=E.RID,");
		PendingApprovals.append(" (SELECT ADDDATE('"+from_date+"', INTERVAL @i:=@i+1 DAY) AS SELECTED_DATE");
		PendingApprovals.append(" FROM (");
		PendingApprovals.append(" SELECT a.a");
		PendingApprovals.append(" FROM (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a");
		PendingApprovals.append(" CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b");
		PendingApprovals.append(" CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS c");
		PendingApprovals.append(" ) a");
		PendingApprovals.append(" JOIN (SELECT @i := -1) r1");
		PendingApprovals.append(" WHERE");
		PendingApprovals.append(" @i < DATEDIFF('"+to_date+"', '"+from_date+"'))Q");
		PendingApprovals.append(" where SELECTED_DATE between");
		PendingApprovals.append(" DATE_FORMAT(A.FROM_DATE,'%Y-%m-%d') and DATE_FORMAT(A.TO_DATE,'%Y-%m-%d')");		
		PendingApprovals.append(" AND A.FLAG IN ('A','P') AND A.REQ_TYPE='LR' AND A.STATUS=1001 AND A.RID>73 AND A.EMPLOYEEID IN ("+username+")");
		PendingApprovals.append(" GROUP BY A.RID,SELECTED_DATE ORDER BY A.RID,SELECTED_DATE)L");

		 
		
		
		Res=null;
	       try {
				Res=(ResultSet)DataObj.FetchData_Emp_DOB(PendingApprovals.toString(), "PendingApprovals", Res ,conn);
				if(Res.next()){
					
					Unic_Leave=Integer.parseInt(Res.getString(1).toString());
					
					}
			}catch(Exception err){
			
				System.out.println("0 From_toDaysCal Error At Get Leave Quota" +err);
				
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
			
			Probitionary_emp_leave_check.append(" SELECT sum(ifnull(LEAVE_COUNT_BT_DAYS,0)) FROM hclhrm_prod_others.tbl_emp_leave_report  ");
			Probitionary_emp_leave_check.append(" WHERE LEAVEON BETWEEN '"+EmpData.get("FROMDATE").toString()+"'  AND '"+EmpData.get("TODATE").toString()+"' AND ");
			Probitionary_emp_leave_check.append(" LEAVE_TYPE NOT IN ('OD','LOP') AND ");
			Probitionary_emp_leave_check.append(" MANAGER_STATUS IN ('P','A') AND ");
			
			Probitionary_emp_leave_check.append(" EMPLOYEEID in("+username+") AND LEAVE_TYPE in('CL','SL','EL') ");
			
		//	Probitionary_emp_leave_check.append(" EMPLOYEEID in("+username+") AND LEAVE_TYPE='"+Leave_Type+"' ");
			
		    System.out.println("Probitionary_emp_leave_check::" +Probitionary_emp_leave_check.toString());
			
			
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
			
			if(for_month_limit ==1 && for_month_day_mode==0 ){
				
				Res=null;
			       try {
						Res=(ResultSet)DataObj.FetchData_Emp_DOB(Probitionary_emp_leave_check.toString(), "Probitionary_emp_leave_check", Res ,conn);
						if(Res.next()){
							
							btw_payperiod_leaves=Double.parseDouble(Res.getString(1).toString());
							
							}
					}catch(Exception err){
					
						System.out.println("prob check: pFrom_toDaysCal Error At Get Leave Quota ::" +err);
						
					}
			       
			       Res=null;
			       System.out.println("check_lev_btw_payperiod::" +check_lev_btw_payperiod.toString());
			       try {
						Res=(ResultSet)DataObj.FetchData_Emp_DOB(check_lev_btw_payperiod.toString(), "check_lev_btw_payperiod", Res ,conn);
						while(Res.next()){
							newbtw_payperiod_leaves=newbtw_payperiod_leaves+Double.parseDouble(Res.getString(1).toString());
							}
					}catch(Exception err){
					
						System.out.println("chk lev bet days From_toDaysCal Error At Get Leave Quota" +err);
					}   
			       
			}     /// if closing ...!
			       
			      /* 1.0SAVE MODE0.0
			       0.0~SAVE POINT 2~2.0
			       2.0~SAVE POINT 2.1~3.0
			       2.0~SAVE POINT 3~3.0*/
			       double combination=btw_payperiod_leaves+newbtw_payperiod_leaves;
			       
			       System.out.println(btw_payperiod_leaves +"~SAVE POINT 2~" +newbtw_payperiod_leaves);
			       
			       System.out.println(combination +"~SAVE POINT 2.1~" +maxleave_month_limit);
			     
			       
			      if(for_month_limit==1){ 
			       if(combination<=maxleave_month_limit ){
			    	  
			    	   System.out.println(combination +"~SAVE POINT 3~" +maxleave_month_limit);
			    	   
			    	    check_leave_month=true;
			    	   
			       }else if(combination>maxleave_month_limit ){
			    	   
			       
			    	   check_leave_month=false;
			}else{
				
				System.out.println(" False Condition at check_leave_month ");
				check_leave_month=false;
				
			}
			      }else{
			    	  
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
			preview_feature_leaves.append(" WHERE A.EMPLOYEEID in("+username+") AND A.REQ_TYPE='LR' AND A.FLAG IN ('P','A') and B.LEAVE_TYPE not in ('OD','LOP') ");
			preview_feature_leaves.append(" GROUP BY TO_DATE HAVING MAX(A.TO_DATE) < '"+from_date+"'  ORDER BY A.TO_DATE DESC LIMIT 1 ");
			preview_feature_leaves.append(" ) LR ");
			preview_feature_leaves.append(" LEFT JOIN ( ");
			preview_feature_leaves.append(" SELECT A.EMPLOYEEID,A.FROM_DATE,B.LEAVE_TYPE ");
			preview_feature_leaves.append(" FROM HCLHRM_PROD_OTHERS.TBL_EMP_ATTN_REQ A ");
			preview_feature_leaves.append(" LEFT JOIN HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_REQ B ON A.RID=B.RID ");
			preview_feature_leaves.append(" WHERE A.EMPLOYEEID in("+username+")  AND A.REQ_TYPE='LR' AND A.FLAG IN ('P','A') and B.LEAVE_TYPE not in ('OD','LOP') ");
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
		       
		       
		       if(BuID.equalsIgnoreCase("15") ||BuID.equalsIgnoreCase("16") ){
		       less_fromdate.append(" select selected_date,IF(DAYNAME(selected_date)='Saturday','WOFF','WDAY') DAYTYPE, " );
		       }else{
		    	    less_fromdate.append(" select selected_date,IF(DAYNAME(selected_date)='SUNDAY','WOFF','WDAY') DAYTYPE, " );
		 		     
		       }
		       
		       if(BuID.equalsIgnoreCase("15") ||BuID.equalsIgnoreCase("16") ){   
		       less_fromdate.append(" IF(IF(DAYNAME(selected_date)='Saturday','WOFF','WDAY')='WDAY',1,0) STATUS,V2.holidaydate, " );
		       less_fromdate.append(" if(DAYNAME(selected_date)='Saturday' && selected_date=V2.holidaydate,1,if(DAYNAME(selected_date)='Saturday',1,if(selected_date=V2.holidaydate,1,0))) AS HW " );
		       }else{
		    	   less_fromdate.append(" IF(IF(DAYNAME(selected_date)='SUNDAY','WOFF','WDAY')='WDAY',1,0) STATUS,V2.holidaydate, " );
			       less_fromdate.append(" if(DAYNAME(selected_date)='SUNDAY' && selected_date=V2.holidaydate,1,if(DAYNAME(selected_date)='SUNDAY',1,if(selected_date=V2.holidaydate,1,0))) AS HW " );
			    
		       }
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
		       if(BuID.equalsIgnoreCase("15") ||BuID.equalsIgnoreCase("16")){
		    	   
		       max_todate.append(" select selected_date,IF(DAYNAME(selected_date)='Saturday','WOFF','WDAY') DAYTYPE, " );
		       max_todate.append(" IF(IF(DAYNAME(selected_date)='Saturday','WOFF','WDAY')='WDAY',1,0) STATUS,V2.holidaydate, " );
		       max_todate.append(" if(DAYNAME(selected_date)='Saturday' && selected_date=V2.holidaydate,1,if(DAYNAME(selected_date)='Saturday',1,if(selected_date=V2.holidaydate,1,0))) AS HW " );
			    
		       }else{
		    	   max_todate.append(" select selected_date,IF(DAYNAME(selected_date)='SUNDAY','WOFF','WDAY') DAYTYPE, " );
		    	   max_todate.append(" IF(IF(DAYNAME(selected_date)='SUNDAY','WOFF','WDAY')='WDAY',1,0) STATUS,V2.holidaydate, " );
		    	   max_todate.append(" if(DAYNAME(selected_date)='SUNDAY' && selected_date=V2.holidaydate,1,if(DAYNAME(selected_date)='SUNDAY',1,if(selected_date=V2.holidaydate,1,0))) AS HW " );
				    
				    
		       }
		      
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
		 
         if(holidayexistpreview && (!Leave_Type.equalsIgnoreCase("LOP") && !Leave_Type.equalsIgnoreCase("OD") )){
        	 
        	 //prev_holidays_woff/prev_btw_workingdays
        	 
        	 System.out.println(feature_btw_workingdays +"BABUV 1"+feature_holidays_woff);
        	 
        	  if(prev_btw_workingdays==0 && prev_holidays_woff>0){
        		  
        		  System.out.println(feature_btw_workingdays +"BABUV 1.1"+feature_holidays_woff);
        		  
        		  System.out.println(prev_btw_workingdays +"~test 1~"+ prev_holidays_woff);
        		  
        		  
        		if(EmpData.get("lessmintype").toString().equalsIgnoreCase(""+Leave_Type+"")){
        			
        			System.out.println(feature_btw_workingdays +"BABUV 1.1.1"+feature_holidays_woff);
        			
        			prev_combflag_of_leave=true;
        			
        			//Below el& sl leave combination eligible condition check
        		}else if((EmpData.get("lessmintype").toString().equalsIgnoreCase("SL") && Leave_Type.equalsIgnoreCase("EL")) ||EmpData.get("lessmintype").toString().equalsIgnoreCase("EL") && Leave_Type.equalsIgnoreCase("SL") ){
        		
        			System.out.println(feature_btw_workingdays +"BABUV 1.1.2"+feature_holidays_woff);
        			
        			prev_combflag_of_leave=true;
        			
        			Holiday_Count=0;  // this is Add for holiday not consider with combination of both
        			
        		}
        		
        		
        		else{
        			
        			System.out.println(feature_btw_workingdays +"BABUV 1.1.3"+feature_holidays_woff);
        			
        			if(Leave_Type.equalsIgnoreCase("OD") || Leave_Type.equalsIgnoreCase("LOP")){
        				
        				prev_combflag_of_leave=true;
        			}else{
        			prev_combflag_of_leave=false;
        			Message="Combination of "+EmpData.get("lessmintype").toString()+" & "+Leave_Type+"  not applicable please check with previous leave .. " ;
        			}
        		}  ///////////////////////////
        		
        	  }else if(prev_btw_workingdays>0 && (prev_holidays_woff==0 || prev_holidays_woff>0) ){
        		  
        		
        		  
        		  System.out.println(feature_btw_workingdays +"BABUV 3"+feature_holidays_woff);
        		  
          			prev_combflag_of_leave=true;
          			
          		
        		  
        	  }else if(prev_btw_workingdays==0 && prev_holidays_woff==0){
        		  
        		  
        		  System.out.println(feature_btw_workingdays +"BABUV 2"+feature_holidays_woff);
        		  
        		  if(EmpData.get("lessmintype").toString().equalsIgnoreCase(""+Leave_Type+"")){
          			
          			  prev_combflag_of_leave=true;
          			
          		}else if((EmpData.get("lessmintype").toString().equalsIgnoreCase("SL") && Leave_Type.equalsIgnoreCase("EL")) || (EmpData.get("lessmintype").toString().equalsIgnoreCase("EL") && Leave_Type.equalsIgnoreCase("SL")) ){
          		
          			prev_combflag_of_leave=true;
          			
          			Holiday_Count=0;  // this is Add for holiday not consider with combination of both
          			
          		}else{
          			
          			 System.out.println(feature_btw_workingdays +"BABUV 3"+feature_holidays_woff);
          			 
          			if(Leave_Type.equalsIgnoreCase("OD") || Leave_Type.equalsIgnoreCase("LOP")){
          				prev_combflag_of_leave=true;
          			}else{
          			prev_combflag_of_leave=false;
          			Message="Combination of "+EmpData.get("lessmintype").toString()+" & "+Leave_Type+"  not applicable please check with previous leave .. " ;
          			}
          		}
        		  
        		  
        	  }
        	  
        	  System.out.println(prev_combflag_of_leave + "::1less_fromdate::" + prev_flag_of_leave);
        	  
        	  System.out.println(prev_btw_workingdays + "::Dates::" + prev_holidays_woff);
        	  
        	  if(prev_combflag_of_leave ){
        		  
        		  System.out.println(feature_btw_workingdays +"BABUV 4"+feature_holidays_woff);
        		  
        		  if(prev_btw_workingdays==0 && prev_holidays_woff > 0 && (!Leave_Type.equalsIgnoreCase("LOP") && !Leave_Type.equalsIgnoreCase("OD") && !Leave_Type.equalsIgnoreCase("CL"))){
        		 	 
        			  System.out.println(feature_btw_workingdays +"BABUV 4.1"+feature_holidays_woff);
        			  
        			  prev_flag_of_leave=false;
        		      Message=" Immediate Leave, after week off/Holiday not eligible,should consider week off&Holiday also OR Cancel previous leave and choose proper dates";
        	       }else if(prev_btw_workingdays>0 && (prev_holidays_woff>0||prev_holidays_woff==0) && (!Leave_Type.equalsIgnoreCase("LOP") && !Leave_Type.equalsIgnoreCase("OD") && !Leave_Type.equalsIgnoreCase("CL") )){
        	    	   prev_flag_of_leave=true;
        	    	   System.out.println(feature_btw_workingdays +"BABUV 4.2"+feature_holidays_woff);
        	    	   
        	       }else if( (prev_btw_workingdays==0 ||prev_btw_workingdays>0 ) && (prev_holidays_woff==0||prev_holidays_woff>0) && Leave_Type.equalsIgnoreCase("CL")){
        	    	   
        	    	   System.out.println(feature_btw_workingdays +"BABUV 4.3"+feature_holidays_woff);
        	    	   prev_flag_of_leave=true;
        	       
        	       }
        	       else if(prev_btw_workingdays==0 && prev_holidays_woff==0){
        	    	   
        	    	   System.out.println(feature_btw_workingdays +"BABUV 4.4"+feature_holidays_woff);
        	    	   prev_flag_of_leave=true;
        	       
        	       }
        		
        	  System.out.println(prev_combflag_of_leave + "::21less_fromdate::" + prev_flag_of_leave);
        		 
         }
       } else{
    	   prev_combflag_of_leave=true;
    	   prev_flag_of_leave=true;
    	   
       }
         
         // Main preview if closed
         
         
 if( holidayexistfeature && (!Leave_Type.equalsIgnoreCase("LOP") && !Leave_Type.equalsIgnoreCase("OD") )){ 
        	 
	/* feature_btw_workingdays
	 feature_holidays_woff
	 flg
	 feature_flag_of_leave
	 feature_combflag_of_leave
	 
	 EmpData.put("gratertype", Res.getString("gratertype").toString());
	   EmpData.put("graterdate", Res.getString("graterdate").toString());*/
	 
	 System.out.println(feature_btw_workingdays +"BABU 1"+feature_holidays_woff);
	 
        	 //prev_holidays_woff/prev_btw_workingdays
        	  if(feature_btw_workingdays==0 && feature_holidays_woff>0){
        		  
        		if(EmpData.get("gratertype").toString().equalsIgnoreCase(""+Leave_Type+"")){
        			
        			feature_combflag_of_leave=true;
        			
        		}else if((EmpData.get("gratertype").toString().equalsIgnoreCase("SL") && Leave_Type.equalsIgnoreCase("EL")) || (EmpData.get("gratertype").toString().equalsIgnoreCase("EL") && Leave_Type.equalsIgnoreCase("SL"))){
        		
        			feature_combflag_of_leave=true;
        			Holiday_Count=0;  // this is Add for holiday not consider with combination of both
        			
        		}else{
        			
        			feature_combflag_of_leave=false;
        			Message="Combination of "+EmpData.get("gratertype").toString()+" & "+Leave_Type+"  not applicable please check with previous leave .. " ;
        		}
        		
        		
        	  }else if(feature_btw_workingdays>0 && (feature_holidays_woff==0 || feature_holidays_woff>0) ){
        		  
        		 
        		  System.out.println(feature_btw_workingdays +"BABU 2"+feature_holidays_woff);
        		  
          			feature_combflag_of_leave=true;
          			
          		
        		  
        	  }else if(feature_btw_workingdays==0 && feature_holidays_woff==0){
        		  
        		  System.out.println(feature_btw_workingdays +"BABU 3"+feature_holidays_woff);
        		  
        		  if(EmpData.get("gratertype").toString().equalsIgnoreCase(""+Leave_Type+"")){
          			
        			  System.out.println(EmpData.get("gratertype").toString() +"BABU 3.1 "+feature_holidays_woff);
        			  
        			  feature_combflag_of_leave=true;
        			  //Holiday_Count=0;
          			
          		}else if((EmpData.get("gratertype").toString().equalsIgnoreCase("SL") && Leave_Type.equalsIgnoreCase("EL")) || (EmpData.get("gratertype").toString().equalsIgnoreCase("EL") && Leave_Type.equalsIgnoreCase("SL"))  ){
          		
          			 System.out.println(EmpData.get("gratertype").toString() +"BABU 3.2 "+feature_holidays_woff);
          			 
          			feature_combflag_of_leave=true;
          			Holiday_Count=0;  // this is Add for holiday not consider with combination of both
          			
          		}else{
          			
          			 System.out.println(EmpData.get("gratertype").toString() +"BABU 3.3 "+feature_holidays_woff);
          			 
          			feature_combflag_of_leave=false;
          			
          			try{
          			Message="Combination of "+EmpData.get("gratertype").toString()+" & "+Leave_Type+"  not applicable please check with previous leave ..! " ;
          			}catch(Exception ewr){
          				
          				System.out.println("ewr::" +ewr);
          				
          			}
          		}
        		  
        		  
        	  }
        	  
        	  System.out.println(feature_combflag_of_leave + "::feature 1less_fromdate::" + feature_flag_of_leave);
        	  
        	  System.out.println(feature_btw_workingdays + "::feature Dates::" + feature_holidays_woff);
        	  
        	  if(feature_combflag_of_leave){
        		  
        		  System.out.println(feature_btw_workingdays +"BABU 4"+feature_holidays_woff);
        		  
        		  if(feature_btw_workingdays==0 && feature_holidays_woff > 0 && (!Leave_Type.equalsIgnoreCase("LOP") && !Leave_Type.equalsIgnoreCase("OD") && !Leave_Type.equalsIgnoreCase("CL"))){
        			  feature_flag_of_leave=false;
        		      Message=" Immediate Leave , after week off/Holiday not eligible,should consider week off & Holiday also OR Cancel previous leave and choose proper dates..!";
        	       }else if(feature_btw_workingdays>0 && (feature_holidays_woff>0||feature_holidays_woff==0) && (!Leave_Type.equalsIgnoreCase("LOP") && !Leave_Type.equalsIgnoreCase("OD") && !Leave_Type.equalsIgnoreCase("CL"))){
        	    	  
        	    	   feature_flag_of_leave=true;
        	    	   
        	       }else if((feature_btw_workingdays==0||feature_btw_workingdays>0 ) && (feature_holidays_woff==0 || feature_holidays_woff>0) && Leave_Type.equalsIgnoreCase("CL")){
        	       
        	    	   feature_flag_of_leave=true;
            	       
        	       }else if(feature_btw_workingdays==0 && feature_holidays_woff==0){
        	    	   feature_flag_of_leave=true;
        	       
        	       }
        		
        	  System.out.println(feature_combflag_of_leave + "::feature21less_fromdate::" + feature_flag_of_leave);
        		 
         }
       }else{
    	   
    	   feature_combflag_of_leave=true;
    	   feature_flag_of_leave=true;
       }
 
 // Main max if closed
 
 
         
       //  if((feature_btw_workingdays>=0 && feature_holidays_woff==0) )
		       
  //DATE_VALID_SL  
 
 
			// PeandingLeave==0 &&  pending leave opption removed
      try{
    	
    	/*  LOGGER.debug(User_Auth_auth +":User_Auth_auth~"+Integer.parseInt(EmpData.get("DATE_VALID_SL").toString()) +":DATE_VALID_SL~" 
    			  +Integer.parseInt(EmpData.get("TODATE_VALID").toString())+":TODATE_VALID~" +check_leave_month +":check_leave_month~"
    			  +holidayexistpreview +":holidayexistpreview~" +prev_combflag_of_leave+":prev_combflag_of_leave~"
    			  +feature_combflag_of_leave +":feature_combflag_of_leave~"
    			  +lopins_count+"~lopins_count:"
    			  +Unic_Leave+"~Unic_Leave:"
    			  +username + ":username~"
    			  +OTHFLAG+":OTHFLAG");*/
    	  
    	  
      
       if(User_Auth_auth==1 && Integer.parseInt(EmpData.get("DATE_VALID_SL").toString())==1 && 
    		   Integer.parseInt(EmpData.get("TODATE_VALID").toString())==0 && 
    		   check_leave_month && (holidayexistpreview==false||(prev_combflag_of_leave && prev_flag_of_leave)) &&
    		   (holidayexistfeature==false || (feature_flag_of_leave && feature_combflag_of_leave))  && 
    		   lopins_count==1 && Unic_Leave==0 && 
    		   conn!=null && username!=null && 
    		   password!=null && Routing.equalsIgnoreCase("LeaveMgM"))
       {
			
    	   double Leav_avail=0,MAX_LEAVE_=0;
    	   
    	   try{
    	     MAX_LEAVE_=Double.parseDouble( session.getAttribute("MAX_LEAVE_"+username+"_"+Leave_Type+"").toString());
    	   }catch(Exception err){
    		 System.out.println("err ::" +err); 
    		 MAX_LEAVE_=0;
    	   }
    	   
    	  /* if(Leave_Type.equalsIgnoreCase("OD") || Leave_Type.equalsIgnoreCase("LOP") ){
    		   MAX_LEAVE_=0; 
    	   }*/
    	   
    	   ps=null;
			//  Validation Data For Insertion
			try{
				boolean maxleaveflag=true;
				
				
				System.out.println("MAX_LEAVE_ ::" +MAX_LEAVE_);
				
				
				System.out.println("Holiday_Count ::" +Holiday_Count);
				
				
			/*	LOGGER.error(Holiday_Count +":Holiday_Count~"+MAX_LEAVE_ +"~MAX_LEAVE_:" +Leave_Diff +":Leave_Diff~");
			*/	
				LeaveCount=Leave_Diff-Holiday_Count;
				
			/*	LOGGER.error(LeaveCount +":LeaveCount");
			*/	if(LeaveCount<0){
					LeaveCount=0;
				}
				
				if(DayMode==0 && MAX_LEAVE_<LeaveCount){
					maxleaveflag=false;
				}
				
				//LeaveCount=Leave_Diff-Holiday_Count;
			     DayMode=Double.parseDouble(Leave_New.get(username+"_DAYMODE").toString());
						
				 Leav_avail=Double.parseDouble(Leave_New.get(username+"_AVAILABLEQTY").toString());
				if(DayMode==0 && Leav_avail>=LeaveCount && LeaveCount>0 && maxleaveflag){
					
					System.out.println("flag1"+LeaveCount);
					
					insertionFlag=true;
					
				}else if(DayMode>0 && LeaveCount>0){
					
					System.out.println("flag2"+LeaveCount);
					insertionFlag=true;
					maxleaveflag=true;
				
				}else{
					
					System.out.println("flag3"+LeaveCount);
					
					if(LeaveCount<=0){
						Atten_Req_Message="Improper date selection, please check with holiday & week off .  ";
						insertionFlag=false;
					}else if(!maxleaveflag){
						Atten_Req_Message="Improper date selection, elegible leave quantity is "+MAX_LEAVE_+"  ";
					}else{
					Atten_Req_Message="Request Not processed  :Eligible quantity "+Leav_avail+" , Applied Leave Count should be <= "+Leav_avail+" ";
					insertionFlag=false;
					}
				}
				
			}catch(Exception err){
				
				System.out.println("Exception At Validation  "+err);
				
			}
			
			System.out.println(Leav_avail+ "~LEAVE MGM ~"+LeaveCount);
			////  Validation Data For Insertion
			if(insertionFlag & OTHFLAG.equalsIgnoreCase("N")){ // Insertion Part Started
				
				
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
				ps=conn.prepareStatement("insert into hclhrm_prod_others.tbl_emp_attn_req(EMPLOYEEID,FROM_DATE,TO_DATE,SUBJECT,TO_EMAIL,CC_EMAIL,MESSAGE,REQ_TYPE,REQ_DATE,RANDOMID,HR_CC_MAIL,EMPMAIL) values(?,?,?,?,?,?,?,'LR',curdate(),?,?,?)" ,new String[] {"RID"});
				
				//ps=conn.prepareStatement("insert into hclhrm_prod_others.tbl_emp_attn_req(EMPLOYEEID,FROM_DATE,TO_DATE,SUBJECT,TO_EMAIL,CC_EMAIL,MESSAGE,REQ_TYPE,REQ_DATE,RANDOMID) values(?,?,?,?,?,?,?,'LR',curdate(),?)" ,new String[] {"RID"});
				ps.setString(1,username);
				ps.setString(2,from_date);
				ps.setString(3,to_date);
				ps.setString(4,subject);
				ps.setString(5,to_mail);
				ps.setString(6,cc_mail);
				ps.setString(7,reason);
				ps.setInt(8,nRand);
				ps.setString(9,HR_MAIL); //
				ps.setString(10,EMP_MAIL);//
				
				
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
				   try{
					conn.commit();
					conn.setAutoCommit(true);
				   }catch(Exception erd){
					  System.out.println("Exception at ERD" +erd); 
				   }
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
			
			}else{
				
				if(!OTHFLAG.equalsIgnoreCase("N")){
					
					if(Leave_Type.equalsIgnoreCase("OD") ){
						Atten_Req_Message=" Number of days considered "+LeaveCount+" ";
					}else if(Leave_Type.equalsIgnoreCase("LOP")){
						Atten_Req_Message=" Number of days considered "+LeaveCount+" ";
					}else{
			     	  Atten_Req_Message=" Number of days considered "+LeaveCount+" & it will be deducted from your leave balance ";
					}
				}
			}
			
			//   Insertion Flag Closing

		} else{
			
			if(User_Auth_auth==0){
				
				Atten_Req_Message="your are Inactive employee";
				
			}else if(Integer.parseInt(EmpData.get("TODATE_VALID").toString())==1){
			  
				Atten_Req_Message="improper Date selection Please Check & Re-process.";
				
			}else if( Unic_Leave>0){
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
			}else if(Integer.parseInt(EmpData.get("DATE_VALID_SL").toString())==0 && Leave_Type.equalsIgnoreCase("SL")){
			
				Atten_Req_Message="SL Dates Should be <= Current Date (Please select previous dates to apply SL)  ";
			  
			}else if(!feature_combflag_of_leave){
				
				 Atten_Req_Message=Message;
				
			}else if(!prev_combflag_of_leave){
			
				 Atten_Req_Message=Message;
				
			}else if(!prev_flag_of_leave){
				
				 Atten_Req_Message=Message;
				
			}else if(!feature_flag_of_leave){
				
				 Atten_Req_Message=Message;
				
			}
			
			else{
				Atten_Req_Message="Authentication failed please Re-login/contact admin..!";
			}

		}// IF Close For Insertion
       
      }catch(Exception Errr){
    	  
    	  System.out.println("Exception At Last Error Code "+Errr);
    	  
    	  Atten_Req_Message="invalid credientails please relogin/contact admin"; 
      }
         
		if(Routing.equalsIgnoreCase("LeaveMgM")){
			System.out.println("Atten_Req_Message::"+Atten_Req_Message);
			out.write(""+Atten_Req_Message+"");  
		}else{
			request.getRequestDispatcher("/login.html").forward(request, response);  
		}
	 
	}
}
