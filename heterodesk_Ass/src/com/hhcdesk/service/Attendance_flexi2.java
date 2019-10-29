package com.hhcdesk.service;
import java.io.IOException;
import java.io.PrintWriter;

import com.mysql.jdbc.ResultSetMetaData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mysql.jdbc.Statement;
import com.hhcdesk.db.*;


@SuppressWarnings({"serial","rawtypes","unchecked","unused"})
public class Attendance_flexi2 extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext c = getServletContext();
		System.out.println("** Start **");
		String PPWise="NO";
		HttpSession session = request.getSession(false);
		ResultSet Res;
		java.sql.Connection conn=null;
		java.sql.PreparedStatement ps=null;
		
		Map getHoursDetails=new HashMap();
		
		PrintWriter out = response.getWriter();

		int User_Auth=0;
		GetDbData DataObj=new GetDbData();

		JSONObject ATT_MONTHS = new JSONObject();
		
		int dobcnt1=0;
		JSONArray values;
		values = new JSONArray();

		String Routing=request.getParameter("Routing");
		
		Map EMAILDATA=new HashMap();
		
		// ADD for Manager Id geting
		String HR_MAIL=" ",EMP_MAIL=" ";
	      try{
	        EMAILDATA=(Map)session.getAttribute("EMAILDATA_MAP");
	        HR_MAIL=EMAILDATA.get("BULOC").toString();
	        EMP_MAIL=EMAILDATA.get("EMPEMAIL").toString();
	      }catch(Exception Err){
	    	  System.out.println("1: Email fetching Error" +Err);
	      }
		// ADD for Manager ID Geting
	      
		String Month="currmonth";
		
		System.out.println("Routing___Attendance_ ::"+Routing);
		SimpleDateFormat sdfTime;
		 String strTime="0";
		try{
		sdfTime = new SimpleDateFormat("dd-MM-yyyy");
	    Date now = new Date();
	     strTime="0";
	     strTime = sdfTime.format(now);
		}catch(Exception err){
			err.printStackTrace();
		}
		String Routing_temp=Routing;
		
		if(Routing.equalsIgnoreCase("ATTENDANCE_BIO_DATES")){
			
			Routing="ATTENDANCE_BIO";
			
		}
		
		
		String date=null,Req_message=null,subject=null,random=null,Atten_Req_Message="Success Fully Submit Your Request";
		
		
		if(Routing.equalsIgnoreCase("Att_Request")){
			
			date=request.getParameter("id");
			Req_message=request.getParameter("message");
			subject=request.getParameter("Subject");
			random=request.getParameter("RanDm");
		}

		System.out.println("Routing  :::" +Routing);
		session.setAttribute("Notice" ,"N");
		BatchInsertRecords BatchInsert=new BatchInsertRecords();
		ArrayList MasterDataList = new ArrayList(); 
		ArrayList<PrepareData> list = new ArrayList<PrepareData>();
		Map FetchRc=new HashMap();
		StringBuffer biodata=new StringBuffer();
		StringBuffer Emp_DOB=new StringBuffer();
		StringBuffer Emp_DOJ=new StringBuffer();
		
		StringBuffer Months_ATT=new StringBuffer();
		
		ArrayList DOB=new ArrayList();
		ArrayList DOJ=new ArrayList();
		ArrayList DOJ_DEPT=new ArrayList();


		StringBuffer HLDAYLIST=new StringBuffer();
		Map hlday=new HashMap();
		Map Att_Req=new HashMap();
		Map Adj=new HashMap();
		Map Leaves=new HashMap();
		JSONObject  Doj= new JSONObject();


		ResourceBundle bundle_info =(ResourceBundle)(c.getAttribute("bundle"));
		String EnableDays="4";
		String Query=bundle_info.getString("HHCL_DESK_USER_LOGIN");
		EnableDays=bundle_info.getString("EnableDays");
		System.out.println("EnableDays--->"+EnableDays);
		//session.setAttribute("HHCL_EVENT_INFO" ,HHCL_EVENT_INFO);

		String message=null;
		message=bundle_info.getString("HHCL_DESK_NEWJOINEE");
		System.out.println("Query:::"+Query);
		
		try {
			conn=(java.sql.Connection)session.getAttribute("ConnectionObj");
		} catch (Exception e1) {
				e1.printStackTrace();
		}
		System.out.println("Step 1");
         
		
		
	
		String _SUCCESS_PAGE=null;
		String username=request.getParameter("username");  
		String password=request.getParameter("pwd"); 

		if(Routing!=null && (Routing.equalsIgnoreCase("NewJoine") || Routing.equalsIgnoreCase("HOLIDAYS") || Routing.equalsIgnoreCase("BIRTHADYS") || Routing.equalsIgnoreCase("ATTENDANCE_BIO") || Routing.equalsIgnoreCase("Att_Request") || Routing.equalsIgnoreCase("ReqSpenthours")||Routing.equalsIgnoreCase("ReqSpenthoursInsert"))){
			username=(String)session.getAttribute("EMP_ID");
			password=(String)session.getAttribute("EMP_PASSWORD");
		}

		StringBuffer User_Authen=new StringBuffer();
		User_Authen.append(Query);
       
     
		
		
		if(Routing.equalsIgnoreCase("ATTENDANCE_BIO")){
		
		System.out.println("Attendance BIO");
		
		//url : "Attendance?Routing=ATTENDANCE_BIO_DATES&ATT_FLAG='DATES'&Month_FROM="+Month_FROM+" & Month_TO="+Month_TO+" ",
		String From=request.getParameter("username");  
		String to=request.getParameter("pwd"); 
		
		String ATT_FLAG=request.getParameter("ATT_FLAG");
		String Month_FROM=request.getParameter("Month_FROM");
		String Month_TO=request.getParameter("Month_TO");
		
		boolean PeriodWise=Boolean.parseBoolean(request.getParameter("PPWise"));
		
		Month=request.getParameter("Month");

		if(Month==null){
			Month="currmonth";
		}
		if(PeriodWise){
			PPWise="YES";
		}else{
			PPWise="NO";
		}
		
		
		//*****************************************************************************************
		
		Map Dates=new HashMap();
		String mymonth=null;
		if(Month.equalsIgnoreCase("currmonth")){
			mymonth="DATE_FORMAT(STR_TO_DATE(MONTH(NOW()),'%m'),'%m')";	
			// mymonth="12";
		}else{
			mymonth="'"+Month+"'";
		}
		StringBuffer TDates=new StringBuffer();
		
		/*TDates.append("SELECT B.TRANSACTIONDURATION,IF(C.DATEOFJOIN<B.FROMDATE,B.FROMDATE,C.DATEOFJOIN)FROMDATE,IF(CURDATE()<B.TODATE,CURDATE(),B.TODATE)TODATE FROM");
		TDates.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A");
		TDates.append(" LEFT JOIN HCLADM_PROD.TBL_TRANSACTION_DATES B ON A.COMPANYID=B.BUSINESSUNITID");
		TDates.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFILE C ON A.EMPLOYEEID=C.EMPLOYEEID");
		TDates.append(" WHERE");
		TDates.append(" A.EMPLOYEESEQUENCENO IN ("+username+") AND B.TRANSACTIONTYPEID=1 AND");
		TDates.append(" B.TRANSACTIONDURATION=CONCAT(YEAR(NOW()),"+mymonth+")");*/
		
		
		TDates.append(" SELECT B.TRANSACTIONDURATION,");
		TDates.append(" IF(CONCAT(YEAR(NOW()),'-',"+mymonth+",'-01')<C.DATEOFJOIN,C.DATEOFJOIN,CONCAT(YEAR(NOW()),'-',"+mymonth+",'-01'))MS,");
		TDates.append(" IF(LAST_DAY(IF(CONCAT(YEAR(NOW()),'-',"+mymonth+",'-01')<C.DATEOFJOIN,C.DATEOFJOIN,CONCAT(YEAR(NOW()),'-',"+mymonth+",'-01')))>CURDATE()" +
				" ,CURDATE(),LAST_DAY(IF(CONCAT(YEAR(NOW()),'-',"+mymonth+",'-01')<C.DATEOFJOIN,C.DATEOFJOIN,CONCAT(YEAR(NOW()),'-',"+mymonth+",'-01'))))ME,");
		TDates.append(" IF(C.DATEOFJOIN<B.FROMDATE,B.FROMDATE,C.DATEOFJOIN)FROMDATE,");
		TDates.append(" IF(CURDATE()<B.TODATE,CURDATE(),B.TODATE)TODATE FROM");
		TDates.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A");
		TDates.append(" LEFT JOIN HCLADM_PROD.TBL_TRANSACTION_DATES B ON A.COMPANYID=B.BUSINESSUNITID");
		TDates.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFILE C ON A.EMPLOYEEID=C.EMPLOYEEID");
		TDates.append(" WHERE");
		TDates.append(" A.EMPLOYEESEQUENCENO IN ("+username+") AND B.TRANSACTIONTYPEID=1 AND");
		TDates.append(" B.TRANSACTIONDURATION=CONCAT(YEAR(NOW()),"+mymonth+")");
		
		StringBuffer TDates_pay=new StringBuffer();
		
		TDates_pay.append(" select B.TRANSACTIONDURATION,A.FROMDATE,  if(A.TODATE>now(),now(),A.TODATE) TODATE ,substr(B.TRANSACTIONDURATION,5,6) curdate,MONTH(now()) nowmnth  from HCLADM_PROD.TBL_TRANSACTION_DATES A  ");
		TDates_pay.append(" left join( ");
		TDates_pay.append(" select max(TRANSACTIONDURATION) TRANSACTIONDURATION ,businessunitid from HCLADM_PROD.TBL_TRANSACTION_DATES where ");
		TDates_pay.append(" businessunitid in (select companyid from hclhrm_prod.tbl_employee_primary where employeesequenceno in("+username+")) ");
		TDates_pay.append(" and transactiontypeid=1 ");
		TDates_pay.append(" )B ON B.TRANSACTIONDURATION=A.TRANSACTIONDURATION and A.businessunitid=B.businessunitid ");
		TDates_pay.append(" where a.businessunitid in (select companyid from hclhrm_prod.tbl_employee_primary where employeesequenceno in("+username+")) ");
		TDates_pay.append(" and A.transactiontypeid=1 and B.TRANSACTIONDURATION is not null ");
		
		
		Res=null;
		
		Dates.put("MyFrm","0000-00-00");
		Dates.put("MyTo","0000-00-00");
		Dates.put("MyPeriod","0000-00-00");
		
		Dates.put("MyFrm1","0000-00-00");
		Dates.put("MyTo1","0000-00-00");
		Dates.put("MyPeriod1","0000-00-00");
		
		Dates.put("MS","0000-00-00");
		Dates.put("ME","0000-00-00");
	    
		try {
			Res=(ResultSet)DataObj.FetchData(TDates.toString(), "TransactionDates", Res ,conn);
			while(Res.next()){
				
				Dates.put("MyFrm",Res.getString(4));
				Dates.put("MyTo",Res.getString(5));
				Dates.put("MyPeriod",Res.getString(1));
				Dates.put("MS",Res.getString(2));
				Dates.put("ME",Res.getString(3));
			}  
		}catch(Exception Er){
			System.out.println("2: Exception At Er::"+Er);
		}
		
		Res=null;
		try {
			Res=(ResultSet)DataObj.FetchData(TDates_pay.toString(), "TransactionDates by pay period", Res ,conn);
			while(Res.next()){
				
				Dates.put("MyFrm1",Res.getString(2));
				Dates.put("MyTo1",Res.getString(3));
				Dates.put("MyPeriod1",Res.getString(1));
				
			}  
		}catch(Exception Er){
			System.out.println("3: Exception At Er::"+Er);
		}
		
		//*****************************************************************************************
		
		
		/*System.out.println("********************************************************************");
		System.out.println("                  Month-->"+Month);
		System.out.println("                  PPWise--->"+PPWise);
		System.out.println("                  ATT_FLAG--->"+ATT_FLAG);
		System.out.println("********************************************************************");*/
		
		
		
			// ****************************** Pay-period Wise Clause Start*******************
			
			String FromDate=null;
			String ToDate=null;
			
			if(ToDate!=null && FromDate!=null && Month!=null&&Month.equalsIgnoreCase("currmonth")&&PPWise.equalsIgnoreCase("NO")&&ATT_FLAG==null){
				FromDate=Dates.get("MS").toString();
				ToDate=Dates.get("ME").toString();
				System.out.println("Case-I");
			}else if(Month!=null&&Month.equalsIgnoreCase("currmonth")&&PPWise.equalsIgnoreCase("YES")&&ATT_FLAG!=null&&ATT_FLAG.equalsIgnoreCase("MONTHS")){
				FromDate=Dates.get("MyFrm1").toString();  // 30-12-2017
				ToDate=Dates.get("MyTo1").toString();   // 30-12-2017
				System.out.println(FromDate + "Case-II" +ToDate);
			}else if(Month!=null&&Month.length()==2&&PPWise.equalsIgnoreCase("NO")&&ATT_FLAG!=null&&ATT_FLAG.equalsIgnoreCase("MONTHS")){
				FromDate=Dates.get("MS").toString();
				ToDate=Dates.get("ME").toString();
				System.out.println("Case-III");
			}else if(Month!=null&&Month.length()==2&&PPWise.equalsIgnoreCase("YES")&&ATT_FLAG!=null&&ATT_FLAG.equalsIgnoreCase("MONTHS")){
				FromDate=Dates.get("MyFrm").toString();
				ToDate=Dates.get("MyTo").toString();
				System.out.println("Case-IV");
			}else if(Month!=null&&Month.equalsIgnoreCase("currmonth")&&PPWise.equalsIgnoreCase("NO")&&ATT_FLAG!=null&&ATT_FLAG.equalsIgnoreCase("DATES")){
				FromDate=Month_FROM;
				ToDate=Month_TO;
				System.out.println("Case-V");
			}else{
				FromDate=Dates.get("MS").toString();
				ToDate=Dates.get("ME").toString();
				System.out.println("Case-VI");
			}
			
			
          
			 
			Emp_DOJ.append(" select DATE_FORMAT(y.selected_date ,'%d-%m-%Y') DAY ,if(y.selected_date=x.attdate,x.mindat,'00:00') AS 'IN',  ");
			Emp_DOJ.append(" IF(y.selected_date=x.attdate,x.maxdat,'00:00') AS 'OUT', ");
			Emp_DOJ.append(" IFNULL(time_format(x.nethours,'%H:%i'),'00:00') AS 'NET', ");
		
			Emp_DOJ.append(" IF(DAYNAME(y.selected_date)='SUNDAY','WOFF','WDAY') DAYTYPE, ");
			
			
			
			Emp_DOJ.append(" DATE_FORMAT(y.selected_date,'%Y-%m-%d') DAYSP, ");
			Emp_DOJ.append(" IF(ifnull(x.status,0)=1001,1,0) AS 'status', ");
			Emp_DOJ.append(" IF(ifnull(REQ.seldate,0)=0,0,1) AS REQ, x.attdate, date_format(curdate(),'%d-%m-%Y') MYDTS, ");
			Emp_DOJ.append(" CASE ");
			Emp_DOJ.append(" WHEN y.selected_date=x.attdate AND x.mindat>='09:46' AND  x.mindat<='10:00' ");
			Emp_DOJ.append(" THEN 1 ");
			Emp_DOJ.append(" ELSE IF(x.mindat>='10:01',4,0) ");
			Emp_DOJ.append(" END AS MINTIME, ");
			Emp_DOJ.append(" CASE ");
			Emp_DOJ.append(" WHEN y.selected_date=x.attdate AND time_format(x.nethours,'%H:%i')>='08:31' AND  time_format(x.nethours,'%H:%i')<'09:00' ");
			Emp_DOJ.append(" THEN 1 ");
			Emp_DOJ.append(" ELSE IF(time_format(x.nethours,'%H:%i')<='08:30' AND time_format(x.nethours,'%H:%i')>='08:00' ,2, if(time_format(x.nethours,'%H:%i')<'08:00',4,0)) ");
			Emp_DOJ.append(" END AS OUTTIME, ");
			Emp_DOJ.append(" if( ");
			Emp_DOJ.append(" CASE ");
			Emp_DOJ.append(" WHEN y.selected_date=x.attdate AND x.mindat>='09:46' AND  x.mindat<='10:00' ");
			Emp_DOJ.append(" THEN 1 ");
			Emp_DOJ.append(" ELSE IF(x.mindat>='10:01',4,0) ");
			Emp_DOJ.append(" END + ");
			Emp_DOJ.append(" CASE ");
			Emp_DOJ.append(" WHEN y.selected_date=x.attdate AND time_format(x.nethours,'%H:%i')>='08:31' AND  time_format(x.nethours,'%H:%i')<'09:00' ");
			Emp_DOJ.append(" THEN 1 ");
			Emp_DOJ.append(" ELSE IF(time_format(x.nethours,'%H:%i')<='08:30' AND time_format(x.nethours,'%H:%i')>='08:00' ,2, if(time_format(x.nethours,'%H:%i')<'08:00',4,0)) ");
			Emp_DOJ.append(" END>4,4, ");
			Emp_DOJ.append(" CASE ");
			Emp_DOJ.append(" WHEN y.selected_date=x.attdate AND x.mindat>='09:46' AND  x.mindat<='10:00' ");
			Emp_DOJ.append(" THEN 1 ");
			Emp_DOJ.append(" ELSE IF(x.mindat>='10:01',4,0) ");
			Emp_DOJ.append(" END + ");
			Emp_DOJ.append(" CASE ");
			Emp_DOJ.append(" WHEN y.selected_date=x.attdate AND time_format(x.nethours,'%H:%i')>='08:31' AND  time_format(x.nethours,'%H:%i')<'09:00' ");
			Emp_DOJ.append(" THEN 1 ");
			Emp_DOJ.append(" ELSE IF(time_format(x.nethours,'%H:%i')<='08:30' AND time_format(x.nethours,'%H:%i')>='08:00' ,2, if(time_format(x.nethours,'%H:%i')<'08:00',4,0)) ");
			Emp_DOJ.append(" END ) AS DED ");
			Emp_DOJ.append(" from( ");
			Emp_DOJ.append(" (select adddate('1970-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) selected_date from ");
			Emp_DOJ.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0, ");
			Emp_DOJ.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, ");
			Emp_DOJ.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2, ");
			Emp_DOJ.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3, ");
			Emp_DOJ.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) ");
			Emp_DOJ.append(" )y left join( ");
			Emp_DOJ.append(" SELECT distinct status as 'status',date_format(transactiontime,'%Y-%m-%d') attdate,time_format(min(transactiontime),'%H:%i') mindat, time_format(max(transactiontime),'%H:%i') maxdat, ");
			Emp_DOJ.append(" if(timediff( time_format(max(transactiontime),'%H:%i') , if(time_format(min(transactiontime),'%H:%i')<'09:00','09:00',time_format(min(transactiontime),'%H:%i')))<'00:00:00','00:00:00',timediff( time_format(max(transactiontime),'%H:%i') , if(time_format(min(transactiontime),'%H:%i')<'09:00','09:00',time_format(min(transactiontime),'%H:%i')))) ");
			Emp_DOJ.append(" nethours ");
			Emp_DOJ.append(" FROM unit_local_db.tbl_reader_log where employeeid="+username+" ");
			Emp_DOJ.append(" and date_format(transactiontime,'%Y-%m-%d') ");
			Emp_DOJ.append(" between '"+FromDate+"' and date_format('"+ToDate+"' ,'%Y-%m-%d') "); // 20-01-2018
			Emp_DOJ.append(" group by date_format(transactiontime,'%Y-%m-%d') ");
			Emp_DOJ.append(" )x ON y.selected_date=x.attdate ");
			Emp_DOJ.append(" left join( ");
			Emp_DOJ.append(" select V1.selected_date seldate from( ");
			Emp_DOJ.append(" (select adddate('1970-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) selected_date from ");
			Emp_DOJ.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0, ");
			Emp_DOJ.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, ");
			Emp_DOJ.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2, ");
			Emp_DOJ.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3, ");
			Emp_DOJ.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) ");
			Emp_DOJ.append(" )V1 ");
			Emp_DOJ.append(" left join( ");
			Emp_DOJ.append(" select businessunitid,transactionduration,fromdate,if(todate<now(), todate ,now()) as 'todate' from hcladm_prod.tbl_transaction_dates TXD ");
			Emp_DOJ.append(" left join( ");
			Emp_DOJ.append(" select max(transactionduration) txnMaxid from hcladm_prod.tbl_transaction_dates ");
			Emp_DOJ.append(" where businessunitid in (select companyid from hclhrm_prod.tbl_employee_primary where employeesequenceno in("+username+")) ");
			Emp_DOJ.append(" and transactiontypeid=1 limit 1 ");
			Emp_DOJ.append(" ) TXD1 ON TXD1.txnMaxid=TXD.transactionduration ");
			Emp_DOJ.append(" where TXD.businessunitid in (select companyid from hclhrm_prod.tbl_employee_primary where employeesequenceno in("+username+")) ");
			Emp_DOJ.append(" and TXD.transactiontypeid=1 and TXD1.txnMaxid=TXD.transactionduration limit 1 ");
			Emp_DOJ.append(" )V2 ON 1=1 where V1.selected_date between v2.fromdate and v2.todate ");
			Emp_DOJ.append(" order by V1.selected_date desc limit "+EnableDays+"  ");
			Emp_DOJ.append(" )REQ ON REQ.seldate=x.attdate ");
			Emp_DOJ.append(" where y.selected_date between '"+FromDate+"' and date_format('"+ToDate+"','%Y-%m-%d') ");  // 20-01-2018
			Emp_DOJ.append(" group by y.selected_date ");
			
			System.out.println("Emp_DOJ::"+Emp_DOJ);
			
			 
			// ****************************** Pay-period Wise Clause Close*******************
		
		
		
		
		HLDAYLIST.append("SELECT DISTINCT DATE_FORMAT(A.HOLIDAYDATE,'%d-%m-%Y') DAY,");
		HLDAYLIST.append(" TRIM(CONCAT(A.COMMENTS,'  ',IF(A.HOLIDAYTYPEID=0,'','(OPTINAL HLDAY)')))OCCASION");
		HLDAYLIST.append(" FROM");
		HLDAYLIST.append(" hclhrm_prod.tbl_holidays A");
		HLDAYLIST.append(" LEFT JOIN HCLHRM_PROD.TBL_HOLIDAY_TYPE B ON A.HOLIDAYTYPEID=B.HOLIDAYTYPEID");
		//HLDAYLIST.append(" WHERE  year(A.HOLIDAYDATE)=year(now()) ");
		HLDAYLIST.append(" WHERE  A.statusid=1001  ");
		HLDAYLIST.append(" and A.businessunitid in( ");
		HLDAYLIST.append(" select companyid from hclhrm_prod.tbl_employee_primary where ");
		HLDAYLIST.append(" employeesequenceno in("+username+")) ");
		
		
		
		Months_ATT.append(" SELECT DISTINCT IF(MONTH(TRANSACTIONTIME)<10,CONCAT(0,MONTH(TRANSACTIONTIME)),MONTH(TRANSACTIONTIME))MONTHS,MONTHNAME(TRANSACTIONTIME) MONTHNAME ");
		Months_ATT.append(" FROM unit_local_db.tbl_reader_log where ");
		Months_ATT.append(" MONTH(TRANSACTIONTIME)!=MONTH(CURDATE()) AND ");
		Months_ATT.append(" employeeid="+username+" AND YEAR(TRANSACTIONTIME)=YEAR(CURDATE()) ");
		Months_ATT.append(" ORDER BY MONTH(TRANSACTIONTIME) DESC ");
		
		
		
		StringBuffer Req_Adj=new StringBuffer();
		
		Req_Adj.append("SELECT EMPLOYEEID,TRANSACTIONDATE,FLAG");
		Req_Adj.append("  FROM");
		Req_Adj.append(" HCLHRM_PROD_OTHERS.TBL_ATTENDANCE_ADJUSTMENTS WHERE EMPLOYEEID="+username);
		Req_Adj.append(" AND TRANSACTIONDATE BETWEEN '"+FromDate+"' AND date_format('"+ToDate+"','%Y-%m-%d') ");
		
		
		StringBuffer LeavesData=new StringBuffer();
		LeavesData.append("");
		LeavesData.append(" SELECT A.EMPLOYEEID,SELECTED_DATE Day,");
		LeavesData.append(" IF(DATE_FORMAT(SELECTED_DATE,'%Y-%m-%d')=STR_TO_DATE(E.HALF_DAY_DATE,'%Y-%m-%d'),CONCAT('Half Day-',E.LEAVE_TYPE),E.LEAVE_TYPE)TYPE, ");
		LeavesData.append(" IF(A.FLAG='A','APPROVED','PENDING')FLAG ,");
		LeavesData.append(" CONCAT(E.LEAVE_TYPE,'-',IF(A.FLAG='A','APPROVED','PENDING'))FLAG");
		LeavesData.append(" from");
		LeavesData.append(" HCLHRM_PROD_OTHERS.TBL_EMP_ATTN_REQ A");
		LeavesData.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY B ON A.EMPLOYEEID=B.EMPLOYEESEQUENCENO");
		LeavesData.append(" LEFT JOIN HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_REQ E ON A.RID=E.RID,");
		LeavesData.append(" (select adddate('"+FromDate+"',t4*10000 + t3*1000 + t2*100 + t1*10 + t0) SELECTED_DATE from");
		LeavesData.append("  (select 0 t0 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0,");
		LeavesData.append("  (select 0 t1 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1,");
		LeavesData.append("  (select 0 t2 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2,");
		LeavesData.append("  (select 0 t3 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3,");
		LeavesData.append("  (select 0 t4 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v");
		LeavesData.append(" where SELECTED_DATE between");
		LeavesData.append(" DATE_FORMAT(A.FROM_DATE,'%Y-%m-%d') and DATE_FORMAT(A.TO_DATE,'%Y-%m-%d')");
		LeavesData.append(" AND A.FLAG IN ('A','P') AND A.REQ_TYPE='LR' AND A.STATUS=1001 AND A.RID>73");
		LeavesData.append(" AND SELECTED_DATE>='"+FromDate+"' AND  SELECTED_DATE<=date_format('"+ToDate+"','%Y-%m-%d') AND B.EMPLOYEESEQUENCENO IN ("+username+")");
		LeavesData.append(" GROUP BY A.RID,SELECTED_DATE ORDER BY A.RID,SELECTED_DATE");
	
		Res=null;
	    
		try {
			Res=(ResultSet)DataObj.FetchData(LeavesData.toString(), "LeavesData", Res ,conn);
			while(Res.next()){
				
				Leaves.put("L$"+Res.getString(2), Res.getString(3));
				Leaves.put("Leave_"+Res.getString(2), Res.getString(4));
				Leaves.put("LeaveTrans"+Res.getString(2), Res.getString(2));
				
			}  
		}catch(Exception Er){
			System.out.println("4: Exception At Er::"+Er);
		}
		
		
		Res=null;
	    
		try {
			Res=(ResultSet)DataObj.FetchData(Req_Adj.toString(), "Req_Adj", Res ,conn);
			while(Res.next()){
				
				Adj.put("TRANSACTIONDATE_"+Res.getString(2), Res.getString(2));
				
			}  
		}catch(Exception Er){
			System.out.println("5: Exception At Er::"+Er);
		}
	
		
		
		
		Res=null;
    
		try {
			Res=(ResultSet)DataObj.FetchData(HLDAYLIST.toString(), "Emp_DOJ", Res ,conn);
			while(Res.next()){
				
				hlday.put(Res.getString(1), Res.getString(2));
				
			}  
		}catch(Exception Er){
			System.out.println("6: Exception At Er::"+Er);
		}
		
		
		
		Res=null;
	    
		try {
			Res=(ResultSet)DataObj.FetchData("select date_format(REQ_DATE,'%d-%m-%Y'),if(FLAG='P','PROCESSED',if(FLAG='A','APPROVED','REJECT')) statusflg from hclhrm_prod_others.tbl_emp_attn_req where employeeid="+username+" and REQ_TYPE='AR' ", "EMPLOYEE_ATTENDANCELIST", Res ,conn);
			while(Res.next()){
				
				Att_Req.put(Res.getString(1), Res.getString(2));
				
			}  
			
		}catch(Exception Er){
			System.out.println("7: Exception At Er::"+Er);
		}
		
		
		
     Res=null;
	    
		try {
			 //ATT_MONTHS.put("currmonth", "Current Month");
			 Res=(ResultSet)DataObj.FetchData(Months_ATT.toString(), "EMPLOYEE_ATTENDANCELIST", Res ,conn);
			while(Res.next()){
				
				ATT_MONTHS.put(Res.getString(1), Res.getString(2));
				
			}  
			
		}catch(Exception Er){
			System.out.println("8: Exception At Er::"+Er);
		}
		
		
		
		   /*****************************Lines Added Spending OutSide Hours Permission Start************************************/
        
		 Res=null;
        
        
        StringBuffer getDatesWithHours=new StringBuffer();
        
        getDatesWithHours.append("SELECT EMPLOYEEID,DATE_FORMAT(TRANSACTIONDATE,'%d-%m-%Y')Date,SEC_TO_TIME(SUM( TIME_TO_SEC( NETTIME))) SpendOut FROM");
        getDatesWithHours.append(" HCLHRM_PROD_OTHERS.TBL_EMPLOYEE_PERMISSION_HOURS");
        getDatesWithHours.append(" WHERE EMPLOYEEID IN ("+username+") AND TRANSACTIONDATE BETWEEN '"+FromDate+"' AND '"+ToDate+"' AND MRGFLAG IN ('N','P','R') AND STATUS=1001 GROUP BY TRANSACTIONDATE");
        
        
        
        
        try{
        	Res=(ResultSet)DataObj.FetchData(getDatesWithHours.toString(), "HoursPermission", Res ,conn);
        		
        	while(Res.next()){
        		getHoursDetails.put(Res.getString(1)+"_Date_"+Res.getString(2), Res.getString(3));
        	}
        	
        }catch(Exception e){
        	e.printStackTrace();
        }
        
        
        /*****************************Lines Added Spending OutSide Hours Permission End**************************************/
		
       
		
		
		
		
		
		
		
		
		
		
	  }
		
		Res=null;
		try {
			ps=conn.prepareStatement(User_Authen.toString());
			ps.setInt(1,Integer.parseInt(username));
			ps.setString(2,password);
			Res=(ResultSet)DataObj.FetchDataPrepare_2(ps, "User Authentication",Res,conn);
			if(Res.next()){

				User_Auth=Res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Res=null;
		ps=null;
		Res=null;
		
		if(Routing.equalsIgnoreCase("Att_Request")  && User_Auth==1){
			ps=null;
			try{	

				Res=null;
			    
				StringBuffer AttBuff=new StringBuffer();
				AttBuff.append(" select count(*) from hclhrm_prod_others.tbl_emp_attn_req where employeeid in("+username+") and date_format(REQ_DATE,'%Y-%m-%d')=date_format('"+date+"','%Y-%m-%d') and req_type='AR' ");
				int OldAttflag=0;	
					try {
						 //ATT_MONTHS.put("currmonth", "Current Month");
						 Res=(ResultSet)DataObj.FetchData(AttBuff.toString(), "AttBuff", Res ,conn);
						while(Res.next()){
							
							OldAttflag=Integer.parseInt(Res.getString(1).toString());
							
							
						}  
						
					}catch(Exception Er){
						OldAttflag=0 ;
						System.out.println("Exception At Er::"+Er);
					}
					
					
					
					if(OldAttflag==0){

				conn.setAutoCommit(false);
				System.out.println("Connection Established....!");
				
				Random rand = new Random();
				int nRand = rand.nextInt(90000) + 10000;
				
				//ps=conn.prepareStatement("INSERT INTO hclhrm_prod_others.tbl_emp_attn_req (EMPLOYEEID,SUBJECT,REQ_DATE,MESSAGE,RANDOMID) VALUES (?,?,STR_TO_DATE(?,'%Y-%m-%d'),?,?)");
				//RID, EMPLOYEEID, TO_EMAIL, CC_EMAIL, SUBJECT, REQ_DATE, FROM_DATE, TO_DATE, MESSAGE, URL, MAIL_STATUS, MAIL_ERROR, STATUS, FLAG, COMMENTS, LUPDATE, RANDOMID, REQ_TYPE, TOTA_HOURS
				ps=conn.prepareStatement("INSERT INTO hclhrm_prod_others.tbl_emp_attn_req (EMPLOYEEID,SUBJECT,REQ_DATE,MESSAGE,RANDOMID,FROM_DATE,TO_DATE,TOTA_HOURS,TO_EMAIL,CC_EMAIL,HR_CC_MAIL,EMPMAIL) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
				
				date=request.getParameter("id");
				Req_message=request.getParameter("message");
				subject=request.getParameter("Subject");
				random=request.getParameter("RanDm");
				String FIN=request.getParameter("FIN");
				String FOUT=request.getParameter("FOUT");
				String TIME=request.getParameter("TIME");
				
				String toemail=request.getParameter("toemail");
				String ccemail=request.getParameter("ccemail");
				
				  try{
					  toemail=toemail.replaceAll(";", ",");
					  ccemail=ccemail.replaceAll(";", ",");
					  
				  }catch(Exception Errr){
					  System.out.println("9: Errr--->at Att Req ::"+Errr);
				  }
				
				ps.setString(1,username);
				ps.setString(2,subject);
				ps.setString(3,date);
				ps.setString(4,Req_message);
				ps.setInt(5,nRand);
				ps.setString(6,FIN);
				ps.setString(7,FOUT);
				ps.setString(8,TIME);
				ps.setString(9,toemail);
				ps.setString(10,ccemail);
				//ADD BOTH For Revert Mail
				ps.setString(11,HR_MAIL); //
				ps.setString(12,EMP_MAIL);//
				
				ps.addBatch();
				
				int count[] = ps.executeBatch();

				System.out.println("add Batch Count::"+count.length);

				if(count.length>0){
				conn.commit();
				}else{
					conn.rollback();
					Atten_Req_Message="Request Not Processed Please contact system admin";
				}
					}else{
						
						Atten_Req_Message="Duplicate Request ...!";
					}

				//out.write(""+count.toString()+"");  

			}
			catch (Exception e2)
			{
				Atten_Req_Message="Faild To Process Your Request Please Contact System Admin.";
				e2.printStackTrace();  
			}

} // IF Close For Insertion
		
		
		
		if(User_Auth==1 && !Routing.equalsIgnoreCase("Att_Request") ){

			
			if(Routing.equalsIgnoreCase("ATTENDANCE_BIO")){

				
				_SUCCESS_PAGE="HHCL_ATTENDANCE_flexi2.jsp";
			}



			Res=null;
            double Ded_Hours=0.00;
            
            
            
            DateFormat sdf = new SimpleDateFormat("hh:mm");
            Date netHours;
            Date spendHours;
            Date actualnetHours;
            if(Routing!=null && !Routing.equalsIgnoreCase("ReqSpenthours")&& !Routing.equalsIgnoreCase("ReqSpenthoursInsert")){
            	
           
			try {
				Res=(ResultSet)DataObj.FetchData_Emp_DOB(Emp_DOJ.toString(), "Emp_DOJ", Res ,conn);
				while(Res.next()){

					Doj= new JSONObject();

					
					 if(Routing.equalsIgnoreCase("ATTENDANCE_BIO")){
				
						
                          
						
						
						Doj.put("DATE" , Res.getString("DAY")); 
						Doj.put("FIN" , Res.getString("IN"));
						Doj.put("FOUT" , Res.getString("OUT"));
						Doj.put("PERDAY" , Res.getString("NET"));
						Doj.put("DAYTYPE" , Res.getString("DAYTYPE"));
						Doj.put("LESSHRS" ,  Res.getString("DED"));
						Doj.put("PLS" , "0.00");
						//Double PlsHrs=Double.parseDouble(Res.getString(10).toString());
						netHours = sdf.parse(Res.getString("NET").toString());
						
						Ded_Hours=Ded_Hours+Double.parseDouble(Res.getString("DED").toString());
						
						
						System.out.println("DED_ACTUAL::"+Res.getString("DED").toString() + ":DEDUCTIONTOTAL:"+Ded_Hours +":Month:"+Month+":strTime:"+strTime+":DAY:"+Res.getString("DAY")+":PPWise:"+PPWise);
						
						if(Double.parseDouble(Res.getString("DED").toString())>0 || Double.parseDouble(Res.getString("DED").toString())>0.00){
						 Doj.put("DEDCOLOR" , "#F7D358");
						}else{
							
							 Doj.put("DEDCOLOR" , "");
						}
						if(Month.equalsIgnoreCase("currmonth")){
							
							System.out.println("1");
							Doj.put("INNER" , Res.getString("DAYSP")+"#"+Res.getString("IN")+"#"+Res.getString("OUT")+"#"+Res.getString("NET"));
							Doj.put("DAYTYPE" , Res.getString("DAYTYPE"));
						}
						
						if(!(Res.getString("DAY").toString()).equalsIgnoreCase(strTime) && (Res.getString("status").equalsIgnoreCase("1"))&& ((Res.getString("REQ").equalsIgnoreCase("1")&& Double.parseDouble(Res.getString("DED").toString())>0 )||Res.getString("DAYSP").equalsIgnoreCase(""+Adj.get("TRANSACTIONDATE_"+Res.getString("DAYSP"))+"")) && Month.equalsIgnoreCase("currmonth")){
							Doj.put("DAF" , "");
							
							
							System.out.println("2");
							
						}else if (!(Res.getString("DAY").toString()).equalsIgnoreCase(strTime) && Res.getString("REQ").toString().length()==0 && Month.equalsIgnoreCase("currmonth")){
							Doj.put("DAF" , "none");
							
							System.out.println("3");
							
							if(PPWise=="YES"){
								
								System.out.println("4");
								//Ded_Hours=Ded_Hours-Double.parseDouble(Res.getString("DED").toString());
							  // Doj.put("LESSHRS" ,  "0");// by v
							
						//	PlsHrs=PlsHrs-Double.parseDouble(Res.getString(8).toString());
							}
							
						}else if(!(Res.getString("DAY").toString()).equalsIgnoreCase(strTime) && Res.getString("REQ").equalsIgnoreCase("0") && Month.equalsIgnoreCase("currmonth")){
							Doj.put("DAF" , "none");
							
							System.out.println("5");
							if(PPWise=="YES"){
								System.out.println("5");
							  //Doj.put("LESSHRS" ,  "0");// by v
							 // Ded_Hours=Ded_Hours-Double.parseDouble(Res.getString("DED").toString());
							}
							//PlsHrs=PlsHrs-Double.parseDouble(Res.getString(8).toString());
							
						}else {
							Doj.put("DAF" , "none");
							System.out.println("6");
						}
						
					
						
						Doj.put("DAREQ" ,  "No Request");
						
						
						if(hlday.get(Res.getString("DAY"))!=null){
							
							
							System.out.println("7");
							
							Doj.put("DAYTYPE" , hlday.get(Res.getString("DAY")).toString());
							Doj.put("DAF" , "none");
							Doj.put("LESSHRS" ,  "0");// by v
							Ded_Hours=Ded_Hours-Double.parseDouble(Res.getString("DED").toString());
						//	PlsHrs=PlsHrs-Double.parseDouble(Res.getString(8).toString());

						}if(Res.getString("DAYTYPE").toString().equalsIgnoreCase("WOFF")){
							Doj.put("DAF" , "none");
							
							System.out.println("8");
							
							Doj.put("LESSHRS" ,  "0");// by v
						    Ded_Hours=Ded_Hours-Double.parseDouble(Res.getString("DED").toString());
							// PlsHrs=PlsHrs-Double.parseDouble(Res.getString(8).toString());
						}
						
						// Attendance Request Start
						
						if(Att_Req.get(Res.getString("DAY"))!=null){
							
							Doj.put("DAREQ" ,  Att_Req.get(Res.getString(1)).toString());
							Doj.put("DAF" , "none");
							
							System.out.println("9");
							//'PROCESSED','APPROVED','REJECT'
							
							
						if(Att_Req.get(Res.getString("DAY")).toString().equalsIgnoreCase("APPROVED")){
							Doj.put("LESSHRS" ,  "0");
							System.out.println("11");
							Ded_Hours=Ded_Hours-Double.parseDouble(Res.getString("DED").toString());
							Doj.put("DEDCOLOR" , "#00ffbf");
						}else if(Att_Req.get(Res.getString("DAY")).toString().equalsIgnoreCase("REJECT")){
							//Doj.put("LESSHRS" ,  "0.0");
							Doj.put("DEDCOLOR" , "#ffb3ff");
							System.out.println("12");
						}
							
						}
						else{
							Doj.put("DAREQ" ,  "No Request");
							
							System.out.println("13");
							
						}
						// Attendance Request End
						
						
						
						
						
						// Leaves Showing Start
						
						if(Res.getString("DAYSP").equalsIgnoreCase(""+Leaves.get("LeaveTrans"+Res.getString("DAYSP")+""))){
							Doj.put("DAREQ" ,  Leaves.get("Leave_"+Res.getString("DAYSP").toString()));
							Doj.put("DAYTYPE",Leaves.get("L$"+Res.getString("DAYSP").toString()));
							
							System.out.println("14");
							
						}
						
						
						if(Res.getString("MYDTS").toString().equalsIgnoreCase(Res.getString("DAY").toString())){
							
							Doj.put("LESSHRS" ,  "0");
							Ded_Hours=Ded_Hours-Double.parseDouble(Res.getString("DED").toString());
							
							System.out.println("15");
							
						}
						
						
						/*******Mahesh Lines Added************************/
						String newHours;
						
						System.out.println("getHoursDetails"+getHoursDetails);
						
						if(getHoursDetails!=null && getHoursDetails.get(username+"_Date_"+Res.getString("DAY"))!=null){
							newHours=getHoursDetails.get(username+"_Date_"+Res.getString("DAY")).toString();
							System.out.println(Doj.get("PERDAY").toString()+"<---newHours--->"+newHours);
							spendHours=sdf.parse(newHours);
							int totalSecs=(int) ((netHours.getTime() - spendHours.getTime())/1000);
							//System.out.println("~~~~~~~~~>"+(netHours.getTime() - spendHours.getTime())/1000);
							
							
							
							int hours = totalSecs / 3600;
							int minutes = (totalSecs % 3600) / 60;
							int seconds = totalSecs % 60;
							
							System.out.println("Final Time--->"+String.format("%02d:%02d", hours, minutes));
							
							/*long diffq = netHours.getTime() - spendHours.getTime();
							long diffMinutesq = (diffq / (60 * 1000) % 60);
							long diffHoursq = diffq / (60 * 60 * 1000) % 24;
							
							if(){
								
							}*/
							System.out.println("totalSecs~~>"+totalSecs);
							if(totalSecs>0){
							Doj.put("PERDAY" ,String.format("%02d:%02d", hours, minutes));
							if(totalSecs<32400){
								//Doj.put("PERDAY",String.format("%02d:%02d", hours, minutes));
								/**********Calculate Net Hours Based******************************/
								if(totalSecs>=30660 && totalSecs<32399){
									Doj.put("LESSHRS" , "1");
									Doj.put("DAF" , "");
									Ded_Hours=Ded_Hours+1;
									Doj.put("DEDCOLOR" , "#CDDB95");
								}else if(totalSecs>=28800 && totalSecs<=30600){
									Doj.put("LESSHRS" , "2");
									Doj.put("DAF" , "");
									Ded_Hours=Ded_Hours+2;
									Doj.put("DEDCOLOR" , "#CDDB95");
								}else if(totalSecs<28800){
									Doj.put("LESSHRS" , "4");
									Doj.put("DAF" , "");
									Ded_Hours=Ded_Hours+4;
									Doj.put("DEDCOLOR" , "#CDDB95");
								}
								/*****************************************************************/
							}
							
							}
							
							
							
						}
						
						/********Mahesh Lines End************************/
						
						
						
						
						
						
						// Leaves Showing End
					}
					// ********************Closing Attendance BIO************************
					if(Ded_Hours<0){
						
						Ded_Hours=0;
					}
					
					Doj.put("DEDHOURS",""+Ded_Hours+"");
					values.add(Doj);
					dobcnt1++;
				}  
			}catch(Exception Er){
				System.out.println("10: Exception At Er::"+Er);
			}
            }
			
		}else{
			_SUCCESS_PAGE="/login.html";
			request.setAttribute("message",bundle_info.getString("HHCL_DESK_LOGI_FAILD")); 
		}

		System.out.println("dobcnt1::" + dobcnt1);
		if(dobcnt1<1){

			System.out.println("dobcnt1-2::" + dobcnt1);
			values = new JSONArray();
			 if(Routing.equalsIgnoreCase("ATTENDANCE_BIO")){

				Doj.put("DATE" , "NO_DATA"); 
				Doj.put("FIN" , "NO_DATA");
				Doj.put("FOUT" , "NO_DATA");
				Doj.put("PERDAY" , "NO_DATA");
				Doj.put("INNER" , "NO_DATA");
				Doj.put("DAYTYPE" , "NO_DATA");
				Doj.put("DAF" , "none");
				Doj.put("DAREQ" ,  "NO_DATA");
				Doj.put("LESSHRS" ,  "NO_DATA");

			}
			values.add(Doj);
		}
		request.setAttribute("DOJ_DOB",values.toString());
		request.setAttribute("ATT_MONTHS",ATT_MONTHS.toString());
		
		
		 try {
			     
			   if(Res!=null){
				    Res.close();
	  			}
			 if(ps!=null){
				ps.close();
	       }
	 		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(Routing.equalsIgnoreCase("Att_Request")){
			
			out.write(""+Atten_Req_Message+"");  
			
		}else if(Routing_temp.equalsIgnoreCase("ATTENDANCE_BIO_DATES")){
			
			out.write(""+values.toString()+"");  
			
		}else if(Routing_temp.equalsIgnoreCase("ReqSpenthours")){
			
			String onDate=request.getParameter("onDate");
			JSONObject  individualData= new JSONObject();
			values = new JSONArray();
			StringBuffer getSpenthours=new StringBuffer();
			getSpenthours.append("SELECT * FROM");
			getSpenthours.append(" HCLHRM_PROD_OTHERS.TBL_EMPLOYEE_PERMISSION_HOURS");
			getSpenthours.append(" WHERE");
			getSpenthours.append(" EMPLOYEEID IN ("+username+") AND");
			getSpenthours.append(" TRANSACTIONDATE IN (STR_TO_DATE('"+onDate+"','%d-%m-%Y' )) AND STATUS=1001 AND MRGFLAG IN ('N','P','R','A')");
			
			Res=null;
			try{
	        	Res=(ResultSet)DataObj.FetchData(getSpenthours.toString(), "getSpenthours", Res ,conn);
	        	while(Res.next()){
	        		individualData= new JSONObject();
	        		individualData.put("OUT", Res.getString(3));
	        		individualData.put("IN", Res.getString(4));
	        		individualData.put("NET", Res.getString(5));
	        		individualData.put("ReqCode", Res.getString(9));
	        		individualData.put("btnFlag", Res.getString(6));
	        		
	        		if(Res.getString(6).equalsIgnoreCase("P")){
	        			individualData.put("btnValue", "Pending");
	        		}else if(Res.getString(6).equalsIgnoreCase("N")){
	        			individualData.put("btnValue", "Send Request");
	        		}else if(Res.getString(6).equalsIgnoreCase("A")){
	        			individualData.put("btnValue", "Approved");
	        		}else if(Res.getString(6).equalsIgnoreCase("R")){
	        			individualData.put("btnValue", "Rejected");
	        		}
	        		
	        		
	        		
	        		
	        		
	        		values.add(individualData.toString());
	        	}
	        	
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
			
			//System.out.println(values.size()+"------~~~~~~~>"+values.toString());  
			
			//out.write("yesdata");
			out.write(""+values.toString()+"");
		}else if(Routing_temp.equalsIgnoreCase("ReqSpenthoursInsert")){
			
			//snoCode,reqMsg,ccEmail,toEmail,newSubject
			
			
			
			StringBuffer updateHoursFlag=new StringBuffer();
			updateHoursFlag.append("UPDATE HCLHRM_PROD_OTHERS.TBL_EMPLOYEE_PERMISSION_HOURS");
			updateHoursFlag.append(" SET MRGFLAG='P', ");
			updateHoursFlag.append(" SUBJECT=?,");
			updateHoursFlag.append(" MESSAGE=?,");
			updateHoursFlag.append(" TOEMAIL=?,");
			updateHoursFlag.append(" CCEMAIL=?");
			updateHoursFlag.append(" WHERE SNO=?");
			updateHoursFlag.append("");
			updateHoursFlag.append("");
			updateHoursFlag.append("");
			
			
			ps=null;
			try{
				ps=conn.prepareStatement(updateHoursFlag.toString());
				ps.setString(1, request.getParameter("newSubject").toString());
				ps.setString(2, request.getParameter("reqMsg").toString());
				ps.setString(3, request.getParameter("toEmail").toString());
				ps.setString(4, request.getParameter("ccEmail").toString());
				ps.setString(5, request.getParameter("snoCode").toString());
				
				
				System.out.println("------->"+ps.toString());
				int rows=ps.executeUpdate();
				
				if(rows>0){
					out.write("Request Sent");
				}else{
					out.write("Something Went Wrong");
				}
				
			}catch(Exception e){
				out.write("Request Failed");
				e.printStackTrace();
			}
			
			
					
			
			
			
			
			
			
		}
		
		
		else{
			request.getRequestDispatcher(_SUCCESS_PAGE).forward(request, response);  
		}
		System.out.println("values::"+values.toString());
	}  
}
