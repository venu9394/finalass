package com.hhcdesk.service;
import java.io.IOException;
import java.io.PrintWriter;

import com.mysql.jdbc.ResultSetMetaData;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.sql.DataSource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mysql.jdbc.Statement;
import com.hhcdesk.db.*;


public class Attendance_flexi extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext c = getServletContext();
		
		String PPWise="NO";
		HttpSession session = request.getSession(false);
		Statement statement=null;
		ResultSet Res;
		DataSource dataSource=null;
		java.sql.Connection conn=null;
		java.sql.PreparedStatement ps=null;
		
		PrintWriter out = response.getWriter();
		Map UserMap=new HashMap();

		boolean ConnFlag=true;
		int User_Auth=0;
		GetDbData DataObj=new GetDbData();
		JSONObject jason = new JSONObject();
		JSONObject DOJ_DOB = new JSONObject();
		JSONObject EM_DOB = new JSONObject();

		JSONObject PaySlip= new JSONObject();
		JSONObject ForeCast= new JSONObject();

		JSONObject ATT_MONTHS = new JSONObject();
		
		int dobcnt=0;
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
	    	  System.out.println("Email fetching Error" +Err);
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
		String HHCL_EVENT_INFO=bundle_info.getString("HHCL_DESK_EVENT");
System.out.println("EnableDays--->"+EnableDays);
		//session.setAttribute("HHCL_EVENT_INFO" ,HHCL_EVENT_INFO);

		String message=null;
		message=bundle_info.getString("HHCL_DESK_NEWJOINEE");
		System.out.println("Query:::"+Query);
		/*try {
			dataSource=(DataSource)(c.getAttribute("dataSource"));

		} catch (Exception e) {
			ConnFlag=false;
			e.printStackTrace();
		}
		try {
			conn =dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
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
		System.out.println("Step 1");
         
		
		
	
		String _FAIL_PAGE="/NewJoinee_Faild.jsp";
		String _SUCCESS_PAGE=null;
		String username=request.getParameter("username");  
		String password=request.getParameter("pwd"); 

		if(Routing!=null && (Routing.equalsIgnoreCase("NewJoine") || Routing.equalsIgnoreCase("HOLIDAYS") || Routing.equalsIgnoreCase("BIRTHADYS") || Routing.equalsIgnoreCase("ATTENDANCE_BIO") || Routing.equalsIgnoreCase("Att_Request") )){
			username=(String)session.getAttribute("EMP_ID");
			password=(String)session.getAttribute("EMP_PASSWORD");
		}

		String invalid=null;
		StringBuffer User_Authen=new StringBuffer();
		User_Authen.append(Query);
       
     
		/*Emp_DOJ.append(" SELECT A.CALLNAME NAME,D.NAME DEPT FROM  ");
 		Emp_DOJ.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A  ");
 		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFILE B ON A.EMPLOYEEID=B.EMPLOYEEID  ");
 		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS C ON C.EMPLOYEEID=A.EMPLOYEEID  ");
 		Emp_DOJ.append(" LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT D ON C.DEPARTMENTID=D.DEPARTMENTID  ");
 		Emp_DOJ.append(" WHERE A.STATUS=1001 AND  ");
 		Emp_DOJ.append(" B.DATEOFJOIN >=DATE_FORMAT(curdate(),'%Y-%m-%d')- INTERVAL DAYOFWEEK(DATE_FORMAT(curdate(),'%Y-%m-%d')) DAY  ");*/
		if(Routing.equalsIgnoreCase("NewJoine")){
			Emp_DOJ.append("SELECT A.CALLNAME NAME,D.NAME DEPT,E.EMAIL,E.MOBILE,F.NAME FROM ");
			Emp_DOJ.append("HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
			Emp_DOJ.append("LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFILE B ON A.EMPLOYEEID=B.EMPLOYEEID ");
			Emp_DOJ.append("LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS C ON C.EMPLOYEEID=A.EMPLOYEEID ");
			Emp_DOJ.append("LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT D ON C.DEPARTMENTID=D.DEPARTMENTID ");
			Emp_DOJ.append("LEFT JOIN HCLHRM_PROD.tbl_employee_professional_contact E ON A.EMPLOYEEID=E.EMPLOYEEID ");
			Emp_DOJ.append(" LEFT JOIN hcladm_prod.tbl_businessunit F ON F.BUSINESSUNITID=A.COMPANYID ");
			Emp_DOJ.append("WHERE A.STATUS=1001 AND ");
			Emp_DOJ.append("B.DATEOFJOIN >=DATE_FORMAT(curdate() ,'%Y-%m-%d')- INTERVAL DAYOFWEEK(DATE_FORMAT(curdate() ,'%Y-%m-%d')) DAY ");

		}
		if(Routing.equalsIgnoreCase("HOLIDAYS")){

			Emp_DOJ.append("SELECT concat(trim(DATE_FORMAT(A.HOLIDAYDATE,'%d-%M-%Y')),',',CONCAT(trim(DAYNAME(A.HOLIDAYDATE)))) EVENTDATE,");
			Emp_DOJ.append("A.COMMENTS EVENT,   ");
			Emp_DOJ.append("IF(NOW()<A.HOLIDAYDATE,'UPCOMING','CLOSE') FLAG,IFNULL(B.NAME,'REGULAR') HOLIDAYTYPE   ");
			Emp_DOJ.append("FROM   ");
			Emp_DOJ.append("HCLHRM_PROD.TBL_HOLIDAYS A   ");
			Emp_DOJ.append("LEFT JOIN HCLHRM_PROD.TBL_HOLIDAY_TYPE B ON A.HOLIDAYTYPEID=B.HOLIDAYTYPEID   ");
			Emp_DOJ.append("WHERE A.BUSINESSUNITID IN (   ");
			Emp_DOJ.append("SELECT COMPANYID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN ("+username+"))   ");
			Emp_DOJ.append(" and A.statusid=1001 ");
			Emp_DOJ.append("order by A.HOLIDAYDATE desc ");

		}
		if(Routing.equalsIgnoreCase("BIRTHADYS")){
			Emp_DOJ.append(" SELECT A.CALLNAME NAME,E.NAME,C.NAME WISHHIM,D.EMAIL,D.MOBILE FROM    ");
			Emp_DOJ.append(" hclhrm_prod.tbl_employee_primary A   ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS B ON A.EMPLOYEEID=B.EMPLOYEEID   ");
			Emp_DOJ.append(" LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT C ON B.DEPARTMENTID=C.DEPARTMENTID   ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_CONTACT D ON A.EMPLOYEEID=D.EMPLOYEEID   ");
			Emp_DOJ.append(" LEFT JOIN hcladm_prod.tbl_businessunit E ON E.BUSINESSUNITID=A.COMPANYID   ");
			Emp_DOJ.append(" WHERE A.STATUS=1001 AND DATE_FORMAT(A.DATEOFBIRTH,'%m-%d')=DATE_FORMAT(now(),'%m-%d')");
		}
		if(Routing.equalsIgnoreCase("DEPINFO_1")){
			Emp_DOJ.append(" SELECT A.CALLNAME EMPNAME,E.NAME businessunit,C.NAME Department,trim(F.name) designation,trim(D.EMAIL),D.MOBILE FROM    ");
			Emp_DOJ.append(" hclhrm_prod.tbl_employee_primary A   ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS B ON A.EMPLOYEEID=B.EMPLOYEEID   ");
			Emp_DOJ.append(" LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT C ON B.DEPARTMENTID=C.DEPARTMENTID   ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_CONTACT D ON A.EMPLOYEEID=D.EMPLOYEEID     ");
			Emp_DOJ.append(" LEFT JOIN hcladm_prod.tbl_businessunit E ON E.BUSINESSUNITID=A.COMPANYID   ");
			Emp_DOJ.append(" LEFT JOIN hcladm_prod.tbl_designation F ON B.designationid=F.designationid   ");
			Emp_DOJ.append(" WHERE A.STATUS=1001 and C.DEPARTMENTID=(select DEPARTMENTID from HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS   ");
			Emp_DOJ.append(" where employeeid in(select employeeid from hclhrm_prod.tbl_employee_primary where employeesequenceno in("+username+")))  ");

		}
		
		
		
		
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
			System.out.println("Exception At Er::"+Er);
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
			System.out.println("Exception At Er::"+Er);
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
			
			
          
			   /* 04-12-2017 */
			/*Emp_DOJ.append(" SELECT DATE_FORMAT(W.DAY,'%d-%m-%Y')DAY,IFNULL(G.IN,'00:00')'IN', ");
			Emp_DOJ.append(" IFNULL(G.OUT,'00:00')'OUT',IFNULL(G.NET,'00:00')'NET', ");
			Emp_DOJ.append(" IF(DAYNAME(W.DAY)='SUNDAY','WOFF','WDAY')DAYTYPE,IFNULL(G.STATUS,0) STATUS,DATE_FORMAT(W.DAY,'%Y-%m-%d')DAYSP, ");
			Emp_DOJ.append(" IF(sum(IFNULL(G.DED,0)+IFNULL(G.EVE,0))>4,4, SUM(IFNULL(G.DED,0)+IFNULL(G.EVE,0))) DED, ");
			Emp_DOJ.append(" IF(W.DAY<=SUBDATE(CURDATE(),"+EnableDays+"),0,1)REQ,IFNULL(TIME_FORMAT(TIMEDIFF(G.NET,'09:00'),'%H:%i'),'00:00')PLS ");
			Emp_DOJ.append(" ,date_format(curdate(),'%d-%m-%Y') MYDTS FROM ");
			Emp_DOJ.append(" (SELECT ADDDATE('"+FromDate+"', INTERVAL @i:=@i+1 DAY) AS DAY ");
			Emp_DOJ.append(" FROM ( ");
			Emp_DOJ.append(" SELECT a.a ");
			Emp_DOJ.append(" FROM (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a ");
			Emp_DOJ.append(" CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b ");
			Emp_DOJ.append(" CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS c ");
			Emp_DOJ.append(" ) a ");
			Emp_DOJ.append(" JOIN (SELECT @i := -1) r1 ");
			Emp_DOJ.append(" WHERE ");
			Emp_DOJ.append(" @i < DATEDIFF('"+ToDate+"', '"+FromDate+"'))W ");
			Emp_DOJ.append(" LEFT JOIN ");
			Emp_DOJ.append(" ( ");
			Emp_DOJ.append(" SELECT DATE_FORMAT(TRANSACTIONTIME,'%Y-%m-%d') DAY, ");
			Emp_DOJ.append(" TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i') 'IN', ");
			Emp_DOJ.append(" TIME_FORMAT(MAX(TRANSACTIONTIME),'%H:%i') 'OUT', ");
			Emp_DOJ.append(" TIME_FORMAT(TIMEDIFF(TIME_FORMAT(MAX(TRANSACTIONTIME),'%H:%i'),TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')),'%H:%i') DIFF1, ");
			Emp_DOJ.append(" IF(TIME_FORMAT(TIMEDIFF(TIME_FORMAT(MAX(TRANSACTIONTIME),'%H:%i'),IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))),'%H:%i')<'00:00','00:00', ");
			Emp_DOJ.append(" TIME_FORMAT(TIMEDIFF(TIME_FORMAT(MAX(TRANSACTIONTIME),'%H:%i'),IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))),'%H:%i') ");
			Emp_DOJ.append(" ) 'NET', ");
			Emp_DOJ.append(" IF( ");
			Emp_DOJ.append(" IF( ");
			Emp_DOJ.append(" IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))>'09:00' AND ");
			Emp_DOJ.append(" IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))<='09:45','0', ");
			Emp_DOJ.append(" IF(IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))>='09:46' AND ");
			Emp_DOJ.append("   IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))<='10:00','1', ");
			Emp_DOJ.append(" IF( ");
			Emp_DOJ.append(" IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))>'10:00' AND ");
			Emp_DOJ.append(" IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))='00:00' AND ");
			Emp_DOJ.append(" IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))=TIME_FORMAT(MAX(TRANSACTIONTIME),'%H:%i'),'4',0 ");
			Emp_DOJ.append(" ) ");
			Emp_DOJ.append(" ) ");
			Emp_DOJ.append(" )+ ");
			Emp_DOJ.append(" IF(TIME_FORMAT(TIMEDIFF(TIME_FORMAT(MAX(TRANSACTIONTIME),'%H:%i'),IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))),'%H:%i')>='08:00' AND ");
			Emp_DOJ.append("    TIME_FORMAT(TIMEDIFF(TIME_FORMAT(MAX(TRANSACTIONTIME),'%H:%i'),IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))),'%H:%i')<='08:30','2', ");
			Emp_DOJ.append(" IF(TIME_FORMAT(TIMEDIFF(TIME_FORMAT(MAX(TRANSACTIONTIME),'%H:%i'),IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))),'%H:%i')>='08:31' AND ");
			Emp_DOJ.append("   TIME_FORMAT(TIMEDIFF(TIME_FORMAT(MAX(TRANSACTIONTIME),'%H:%i'),IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))),'%H:%i')<'09:00','1', ");
			Emp_DOJ.append(" IF(TIME_FORMAT(TIMEDIFF(TIME_FORMAT(MAX(TRANSACTIONTIME),'%H:%i'),IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))),'%H:%i')<'08:00' AND ");
			Emp_DOJ.append("   TIME_FORMAT(TIMEDIFF(TIME_FORMAT(MAX(TRANSACTIONTIME),'%H:%i'),IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))),'%H:%i')='00:00','4', ");
			Emp_DOJ.append(" IF(TIME_FORMAT(TIMEDIFF(TIME_FORMAT(MAX(TRANSACTIONTIME),'%H:%i'),IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))),'%H:%i')<'00:00','4','0' ");
			Emp_DOJ.append(" ))))>'0',1,0) 'STATUS', ");
			Emp_DOJ.append(" IF( ");
			Emp_DOJ.append(" IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))>'09:00' AND ");
			Emp_DOJ.append(" IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))<='09:45','0', ");
			Emp_DOJ.append(" IF(IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))>='09:46' AND ");
			Emp_DOJ.append("   IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))<='10:00','1', ");
			Emp_DOJ.append(" IF( ");
			Emp_DOJ.append(" IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))>='10:01' ");
			Emp_DOJ.append(" ,'4','0' ");
			Emp_DOJ.append(" ) ");
			Emp_DOJ.append(" ) ");
			Emp_DOJ.append(" )'DED', ");
			Emp_DOJ.append(" IF(TIME_FORMAT(TIMEDIFF(TIME_FORMAT(MAX(TRANSACTIONTIME),'%H:%i'),IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))),'%H:%i')>='08:00' AND ");
			Emp_DOJ.append("    TIME_FORMAT(TIMEDIFF(TIME_FORMAT(MAX(TRANSACTIONTIME),'%H:%i'),IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))),'%H:%i')<='08:30','2', ");
			Emp_DOJ.append(" IF(TIME_FORMAT(TIMEDIFF(TIME_FORMAT(MAX(TRANSACTIONTIME),'%H:%i'),IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))),'%H:%i')>='08:31' AND ");
			Emp_DOJ.append("   TIME_FORMAT(TIMEDIFF(TIME_FORMAT(MAX(TRANSACTIONTIME),'%H:%i'),IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))),'%H:%i')<'09:00','1', ");
			Emp_DOJ.append(" IF(TIME_FORMAT(TIMEDIFF(TIME_FORMAT(MAX(TRANSACTIONTIME),'%H:%i'),IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))),'%H:%i')<'08:00' AND ");
			Emp_DOJ.append("   TIME_FORMAT(TIMEDIFF(TIME_FORMAT(MAX(TRANSACTIONTIME),'%H:%i'),IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))),'%H:%i')='00:00','4', ");
			Emp_DOJ.append(" IF(TIME_FORMAT(TIMEDIFF(TIME_FORMAT(MAX(TRANSACTIONTIME),'%H:%i'),IF(TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i')<'09:00','09:00',TIME_FORMAT(MIN(TRANSACTIONTIME),'%H:%i'))),'%H:%i')<'00:00','4','0' ");
			Emp_DOJ.append(" )) ");
			Emp_DOJ.append(" )) 'EVE' ");
			Emp_DOJ.append(" FROM ");
			Emp_DOJ.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
			Emp_DOJ.append(" JOIN UNIT_LOCAL_DB.TBL_READER_LOG B ON A.EMPLOYEESEQUENCENO=B.EMPLOYEEID ");
			Emp_DOJ.append(" WHERE ");
			Emp_DOJ.append(" A.EMPLOYEESEQUENCENO IN ("+username+") GROUP BY DATE_FORMAT(B.TRANSACTIONTIME,'%Y-%m-%d') ");
			Emp_DOJ.append(" )G ON W.DAY=G.DAY  GROUP BY G.DAY");

			*/
			String BuID=session.getAttribute("EMPBUID").toString();
			Emp_DOJ.append(" select DATE_FORMAT(y.selected_date ,'%d-%m-%Y') DAY ,if(y.selected_date=x.attdate,x.mindat,'00:00') AS 'IN',  ");
			Emp_DOJ.append(" IF(y.selected_date=x.attdate,x.maxdat,'00:00') AS 'OUT', ");
			Emp_DOJ.append(" IFNULL(time_format(x.nethours,'%H:%i'),0.00) AS 'NET', ");
		
			//Changeed on 17-07-2018
			//Emp_DOJ.append(" IF(DAYNAME(y.selected_date)='SUNDAY','WOFF','WDAY') DAYTYPE, ");
			if(BuID.equalsIgnoreCase("15") ||BuID.equalsIgnoreCase("16") ){
			Emp_DOJ.append(" IF(DAYNAME(y.selected_date)='Saturday','WOFF','WDAY') DAYTYPE, ");
			}else{
				Emp_DOJ.append(" IF(DAYNAME(y.selected_date)='SUNDAY','WOFF','WDAY') DAYTYPE, ");
			}
			
			
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
			
			 /* Emp_DOJ.append(" SELECT DATE_FORMAT(W.DAY,'%d-%m-%Y')DAY,IFNULL(G.IN,'00:00')'IN',");
				Emp_DOJ.append(" IFNULL(G.OUT,'00:00')'OUT',IFNULL(G.NET,'00:00')'NET',");
				Emp_DOJ.append(" IF(DAYNAME(W.DAY)='SUNDAY','WOFF','WDAY')DAYTYPE,IFNULL(G.STATUS,0) STATUS,DATE_FORMAT(W.DAY,'%Y-%m-%d')DAYSP,");
				Emp_DOJ.append(" IF(IFNULL(G.DED,0)+IFNULL(G.EVE,0)>4,4, IFNULL(G.DED,0)+IFNULL(G.EVE,0)) DED,");
				Emp_DOJ.append(" IF(W.DAY<=SUBDATE(CURDATE(),"+EnableDays+"),0,1)REQ,IFNULL(TIME_FORMAT(TIMEDIFF(G.NET,'09:00'),'%H:%i'),'00:00')PLS");
				Emp_DOJ.append(" , date_format(curdate(),'%d-%m-%Y') MYDTS  FROM");
				Emp_DOJ.append(" (SELECT ADDDATE('"+FromDate+"', INTERVAL @i:=@i+1 DAY) AS DAY");
				Emp_DOJ.append(" FROM (");
				Emp_DOJ.append(" SELECT a.a");
				Emp_DOJ.append(" FROM (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a");
				Emp_DOJ.append(" CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b");
				Emp_DOJ.append(" CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS c");
				Emp_DOJ.append(" ) a");
				Emp_DOJ.append(" JOIN (SELECT @i := -1) r1");
				Emp_DOJ.append(" WHERE");
				Emp_DOJ.append(" @i < DATEDIFF('"+ToDate+"', '"+FromDate+"'))W");
				Emp_DOJ.append(" LEFT JOIN");
				Emp_DOJ.append(" (SELECT DATE_FORMAT(B.TRANSACTIONTIME,'%Y-%m-%d')DAY,");
				Emp_DOJ.append(" TIME_FORMAT(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ',2),' ',-1),'%H:%i')'IN',");
				Emp_DOJ.append(" TIME_FORMAT(MAX(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END),'%H:%i')'OUT',");
				Emp_DOJ.append(" TIME_FORMAT(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2),'%H:%i')'NET',");
				Emp_DOJ.append(" IF(IF(TIME_FORMAT(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ',2),' ',-1),'%H:%i')>='09:46' AND TIME_FORMAT(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ',2),' ',-1),'%H:%i')<='10:00',1,");
				Emp_DOJ.append(" IF(TIME_FORMAT(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ',2),' ',-1),'%H:%i')>='10:01',4,0)) +");
				Emp_DOJ.append(" IF(TIME_FORMAT(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2),'%H:%i')>='08:00' AND TIME_FORMAT(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2),'%H:%i')<='08:30',2,");
				Emp_DOJ.append(" IF(TIME_FORMAT(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2),'%H:%i')>='08:31' AND TIME_FORMAT(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2),'%H:%i')<'09:00',1,");
				Emp_DOJ.append(" IF(TIME_FORMAT(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2),'%H:%i')<='08:00' AND TIME_FORMAT(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2),'%H:%i')<'00:01',4,0)");
				Emp_DOJ.append(" ))>0,1,0)STATUS,");
				Emp_DOJ.append(" IF(TIME_FORMAT(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ',2),' ',-1),'%H:%i')>='09:46' AND TIME_FORMAT(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ',2),' ',-1),'%H:%i')<='10:00',1,");
				Emp_DOJ.append(" IF(TIME_FORMAT(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ',2),' ',-1),'%H:%i')>='10:01',4,0)) DED,");
				Emp_DOJ.append(" IF(TIME_FORMAT(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2),'%H:%i')>='08:00' AND TIME_FORMAT(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2),'%H:%i')<='08:30',2,");
				Emp_DOJ.append(" IF(TIME_FORMAT(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2),'%H:%i')>='08:31' AND TIME_FORMAT(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2),'%H:%i')<'09:00',1,");
				Emp_DOJ.append(" IF(TIME_FORMAT(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2),'%H:%i')<='08:00' AND TIME_FORMAT(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2),'%H:%i')<'00:01',4,0)");
				Emp_DOJ.append(" ))EVE");
				Emp_DOJ.append(" ");
				Emp_DOJ.append(" FROM");
				Emp_DOJ.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A");
				Emp_DOJ.append(" JOIN UNIT_LOCAL_DB.TBL_READER_LOG B ON A.EMPLOYEESEQUENCENO=B.EMPLOYEEID");
				Emp_DOJ.append(" WHERE ");
				Emp_DOJ.append(" A.EMPLOYEESEQUENCENO IN ("+username+") GROUP BY DATE_FORMAT(B.TRANSACTIONTIME,'%Y-%m-%d'))G ON W.DAY=G.DAY");*/
				
		/*	Emp_DOJ.append(" SELECT DATE_FORMAT(W.DAY,'%d-%m-%Y')DAY,IFNULL(G.IN,'00:00')'IN',IFNULL(G.OUT,'00:00')'OUT',IFNULL(G.NET,'00:00')'NET',");
			Emp_DOJ.append(" IF(DAYNAME(W.DAY)='SUNDAY','WOFF','WDAY')DAYTYPE,IFNULL(G.STATUS,0) STATUS,DATE_FORMAT(W.DAY,'%Y-%m-%d')DAYSP,");
			Emp_DOJ.append(" IF(G.DED>4,'4.0',IFNULL(G.DED,0))DED,IF(W.DAY<=SUBDATE(CURDATE(),"+EnableDays+"),0,1)REQ,IFNULL(TIME_FORMAT(TIMEDIFF(G.NET,'09:00'),'%H:%i'),'00:00')PLS");
			Emp_DOJ.append(" , date_format(curdate(),'%d-%m-%Y') MYDTS FROM");
			Emp_DOJ.append(" (SELECT ADDDATE('"+FromDate+"', INTERVAL @i:=@i+1 DAY) AS DAY");
			Emp_DOJ.append(" FROM (");
			Emp_DOJ.append(" SELECT a.a");
			Emp_DOJ.append(" FROM (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a");
			Emp_DOJ.append(" CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b");
			Emp_DOJ.append(" CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS c");
			Emp_DOJ.append(" ) a");
			Emp_DOJ.append(" JOIN (SELECT @i := -1) r1");
			Emp_DOJ.append(" WHERE");
			Emp_DOJ.append(" @i < DATEDIFF('"+ToDate+"', '"+FromDate+"'))W");
			Emp_DOJ.append(" LEFT JOIN");
			Emp_DOJ.append(" (SELECT DATE_FORMAT(B.TRANSACTIONTIME,'%Y-%m-%d')DAY,");
			Emp_DOJ.append(" TIME_FORMAT(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ',2),' ',-1),'%H:%i')'IN',");
			Emp_DOJ.append(" TIME_FORMAT(MAX(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END),'%H:%i')'OUT',");
			Emp_DOJ.append(" TIME_FORMAT(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2),'%H:%i')'NET',");
			Emp_DOJ.append(" IF(IF(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN  SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2)>='08:31' AND SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN  SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2)<'09:00',1,IF(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN  SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2)>='08:00' AND SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN  SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2)<'08:30',2,IF(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN  SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2)<'08:00',4.5,0)))>0,1,0)STATUS,");
			Emp_DOJ.append(" IF(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN  SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2)>='08:31' AND SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN  SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2)<'09:00',1,IF(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN  SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2)>='08:00' AND SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN  SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2)<'08:30',2,IF(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN  SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(B.TRANSACTIONTIME, ' ', 2), ' ', 2), ' ', -1)),':',2)<'08:00',4.5,0)))DED");
			Emp_DOJ.append(" FROM");
			Emp_DOJ.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A");
			Emp_DOJ.append(" JOIN UNIT_LOCAL_DB.TBL_READER_LOG B ON A.EMPLOYEESEQUENCENO=B.EMPLOYEEID");
			Emp_DOJ.append(" WHERE");
			Emp_DOJ.append(" A.EMPLOYEESEQUENCENO IN ("+username+") GROUP BY DATE_FORMAT(B.TRANSACTIONTIME,'%Y-%m-%d'))G ON W.DAY=G.DAY");
*/
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
	
		System.out.println(LeavesData.toString());
		
		Res=null;
	    
		try {
			Res=(ResultSet)DataObj.FetchData(LeavesData.toString(), "LeavesData", Res ,conn);
			while(Res.next()){
				
				Leaves.put("L$"+Res.getString(2), Res.getString(3));
				Leaves.put("Leave_"+Res.getString(2), Res.getString(4));
				Leaves.put("LeaveTrans"+Res.getString(2), Res.getString(2));
				
			}  
		}catch(Exception Er){
			System.out.println("Exception At Er::"+Er);
		}
		
		
		Res=null;
	    
		try {
			Res=(ResultSet)DataObj.FetchData(Req_Adj.toString(), "Req_Adj", Res ,conn);
			while(Res.next()){
				
				Adj.put("TRANSACTIONDATE_"+Res.getString(2), Res.getString(2));
				
			}  
		}catch(Exception Er){
			System.out.println("Exception At Er::"+Er);
		}
	
		
		
		
		Res=null;
    
		try {
			Res=(ResultSet)DataObj.FetchData(HLDAYLIST.toString(), "Emp_DOJ", Res ,conn);
			while(Res.next()){
				
				hlday.put(Res.getString(1), Res.getString(2));
				
			}  
		}catch(Exception Er){
			System.out.println("Exception At Er::"+Er);
		}
		
		
		
		Res=null;
	    
		try {
			Res=(ResultSet)DataObj.FetchData("select date_format(REQ_DATE,'%d-%m-%Y'),if(FLAG='P','PROCESSED',if(FLAG='A','APPROVED','REJECT')) statusflg from hclhrm_prod_others.tbl_emp_attn_req where employeeid="+username+" and REQ_TYPE='AR' ", "EMPLOYEE_ATTENDANCELIST", Res ,conn);
			while(Res.next()){
				
				Att_Req.put(Res.getString(1), Res.getString(2));
				
			}  
			
		}catch(Exception Er){
			System.out.println("Exception At Er::"+Er);
		}
		
		
		
     Res=null;
	    
		try {
			 //ATT_MONTHS.put("currmonth", "Current Month");
			 Res=(ResultSet)DataObj.FetchData(Months_ATT.toString(), "EMPLOYEE_ATTENDANCELIST", Res ,conn);
			while(Res.next()){
				
				ATT_MONTHS.put(Res.getString(1), Res.getString(2));
				
			}  
			
		}catch(Exception Er){
			System.out.println("Exception At Er::"+Er);
		}
		
		
		
	  }
		
		Res=null;
		try {
			ps=conn.prepareStatement(User_Authen.toString());
			ps.setInt(1,Integer.parseInt(username));
			ps.setString(2,password);
			Res=(ResultSet)DataObj.FetchDataPrepare_2(ps, "User Authentication",Res,conn);
			//Res=(ResultSet)DataObj.FetchData("Select * from hclhrm_prod.tbl_employee_primary", "UserAuthentiCation", Res ,conn);
			if(Res.next()){

				User_Auth=Res.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Res=null;
		ps=null;
		Res=null;
		ResultSetMetaData rsmd=null;
		
		if(Routing.equalsIgnoreCase("Att_Request")  && User_Auth==1){
			ps=null;
			try{	


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
					  System.out.println("Errr--->at Att Req ::"+Errr);
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


				//out.write(""+count.toString()+"");  

			}
			catch (Exception e2)
			{
				Atten_Req_Message="Faild To Process Your Request Please Contact System Admin.";
				e2.printStackTrace();  
			}

} // IF Close For Insertion
		
		
		
		if(User_Auth==1 && !Routing.equalsIgnoreCase("Att_Request") ){

			if(Routing.equalsIgnoreCase("NewJoine")){
				_SUCCESS_PAGE=bundle_info.getString("HHCL_DESK_DIVERT_NEWJOINEE");
			}else if(Routing.equalsIgnoreCase("HOLIDAYS")){

				_SUCCESS_PAGE=bundle_info.getString("HHCL_DESK_DIVERT_HOLIDAYS");
			}else if(Routing.equalsIgnoreCase("BIRTHADYS")){

				_SUCCESS_PAGE=bundle_info.getString("HHCL_DESK_DIVERT_BIRTHDAYS");
			}
			else if(Routing.equalsIgnoreCase("ATTENDANCE_BIO")){

				//_SUCCESS_PAGE=bundle_info.getString("HHCL_DESK_DIVERT_ATTENDANCE");
				
				_SUCCESS_PAGE="HHCL_ATTENDANCE_flexi.jsp";
			}



			Res=null;
            double Ded_Hours=0.00;
			try {
				Res=(ResultSet)DataObj.FetchData_Emp_DOB(Emp_DOJ.toString(), "Emp_DOJ", Res ,conn);
				while(Res.next()){

					Doj= new JSONObject();


					if(Routing.equalsIgnoreCase("NewJoine")){	
						Doj.put("CALLNAME" , Res.getString(1)); 
						Doj.put("DEPT" , Res.getString(2));
						Doj.put("EMAIL" , Res.getString(3));
						Doj.put("MOBILE" , Res.getString(4));
						Doj.put("BUNAME" , Res.getString(5));

					}else if(Routing.equalsIgnoreCase("HOLIDAYS")){

						Doj.put("EVENTDATE" , Res.getString(1)); 
						Doj.put("EVENT" , Res.getString(2));
						Doj.put("FLAG" , Res.getString(3));
						Doj.put("HOLIDAYTYPE" , Res.getString(4));


					}
					else if(Routing.equalsIgnoreCase("BIRTHADYS")){
						Doj.put("EVENTDATE" , Res.getString(1)); 
						Doj.put("BUNAME" , Res.getString(2));
						Doj.put("EVENT" , Res.getString(3));
						Doj.put("FLAG" , Res.getString(4));
						Doj.put("HOLIDAYTYPE" , Res.getString(5));

					}
					else if(Routing.equalsIgnoreCase("ATTENDANCE_BIO")){
				
						
                          
						/*Doj.put("DATE" , Res.getString(1)); 
						Doj.put("FIN" , Res.getString(2));
						Doj.put("FOUT" , Res.getString(3));
						Doj.put("PERDAY" , Res.getString(4));
						Doj.put("DAYTYPE" , Res.getString(5));
						Doj.put("LESSHRS" ,  Res.getString(8));
						Doj.put("PLS" ,  Res.getString(10));*/
						
						Doj.put("DATE" , Res.getString("DAY")); 
						Doj.put("FIN" , Res.getString("IN"));
						Doj.put("FOUT" , Res.getString("OUT"));
						Doj.put("PERDAY" , Res.getString("NET"));
						Doj.put("DAYTYPE" , Res.getString("DAYTYPE"));
						Doj.put("LESSHRS" ,  Res.getString("DED"));
						Doj.put("PLS" , "0.00");
						//Double PlsHrs=Double.parseDouble(Res.getString(10).toString());
						
						
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
						// Leaves Showing End
						
					//	Doj.put("PLS" ,  ""+PlsHrs+"");
						
						/*
						
					//	hlday
						
						String Fin=Res.getString(2);
						String Fout=Res.getString(3);
						
						Doj.put("DATE" , Res.getString(1)); 
						Doj.put("FIN" , Res.getString(2));
						Doj.put("FOUT" , Res.getString(3));
						
						Doj.put("FIN" , Fin);
						Doj.put("FOUT" ,Fout);
						
						Doj.put("PERDAY" , Res.getString(4));
						
						if(Month.equalsIgnoreCase("currmonth")){
							Doj.put("INNER" , Res.getString("DAYSP")+"#"+Res.getString(2)+"#"+Res.getString(3)+"#"+Res.getString(4));
							Doj.put("DAYTYPE" , Res.getString(5));
						}
						
						if(hlday.get(Res.getString(1))!=null){
							
								Doj.put("DAYTYPE" , hlday.get(Res.getString(1)).toString());
								Doj.put("DAF" , "none");
							
						}else{
							
							if(Res.getString(5).toString().equalsIgnoreCase("WOFF")){
									Doj.put("DAF" , "none");
							}else{
								
								// add for curr date
								if((Res.getString(1).toString()).equalsIgnoreCase(strTime)){
									 Doj.put("DAF" , "none");
								}else{
									
									
									 Doj.put("DAF" , " ");
									 
									 
								}
								
								
							}
							Doj.put("DAYTYPE" , Res.getString(5));
							
						}
						
						if(Month.equalsIgnoreCase("currmonth")){
							
						if(Res.getString(6).toString().equalsIgnoreCase("0")){
							
							//Doj.put("DAREQ" ,  Att_Req.get(Res.getString(1)).toString());
							
							// FOr Time Factor Caliculation 
							
							String []INTime;
							String []OUTTime;
							
							int INH=0,INM=0,OUTH=0,OUTM=0;
							  
							try{
								  INTime=Fin.split(":");
								  OUTTime=Fout.split(":");
								
								  INH=Integer.parseInt(INTime[0]);
								  INM=Integer.parseInt(INTime[1]);
								  OUTH=Integer.parseInt(OUTTime[0]);
								  OUTM=Integer.parseInt(OUTTime[1]);
								
								  
								  if(INH==0 && INM==0 && OUTH==0){
									  Doj.put("DAF" , "none");
								  }else if(INH<9 && OUTH>=18){
									
							           Doj.put("DAF" , "none");
									
								}else if((INH>=9 && INH<10 )&& OUTH>=18){
								  
									   if(INM<=10){
										   Doj.put("DAF" , "none");
									   }else{
										   Doj.put("DAF" , " ");
									   }
									
								
								}else if(INH>=10 | OUTH<18){
								  
									 Doj.put("DAF" , " ");
									 
								}else{
									 Doj.put("DAF" , "none");
								}
							    
							    
							    
							    
							}catch(Exception edr){
								 Doj.put("DAF" , "none");
								System.out.println("edr - Exception::"+edr);
							}
						
							 // FOr Time Factor Caliculation 
							    
							
						}
						}else{
							
							/// TIme For Caliculation
							
							
							String []INTime;
							String []OUTTime;
							
							int INH=0,INM=0,OUTH=0,OUTM=0;
							  
							try{
								  INTime=Fin.split(":");
								  OUTTime=Fout.split(":");
								
								  INH=Integer.parseInt(INTime[0]);
								  INM=Integer.parseInt(INTime[1]);
								  OUTH=Integer.parseInt(OUTTime[0]);
								  OUTM=Integer.parseInt(OUTTime[1]);
								
								  
								  if(INH==0 && INM==0 && OUTH==0){
									  Doj.put("DAF" , "none");
								  }else if(INH<9 && OUTH>=18){
									
							           Doj.put("DAF" , "none");
									
								}else if((INH>=9 && INH<10 )&& OUTH>=18){
								  
									   if(INM<=10){
										   Doj.put("DAF" , "none");
									   }else{
										  // Doj.put("DAF" , " ");
										   
										   Doj.put("DAF" , "none");
									   }
									
								
								}else if(INH>=10 | OUTH<18){
								  
									// Doj.put("DAF" , " ");
									 Doj.put("DAF" , "none");
								}else{
									 Doj.put("DAF" , "none");
								}
							
							}catch(Exception err){
							
								Doj.put("DAF" , "none");
							}
							
							
						//TIME For Caliculation
							    //Doj.put("DAF" , "none");
						}
						
							System.out.println("Res.getString(1)"+Res.getString(1));
						
						
						if(Att_Req.get(Res.getString(1))!=null){
							
							Doj.put("DAREQ" ,  Att_Req.get(Res.getString(1)).toString());
							
							Doj.put("DAF" , "none");
							if((Res.getString(1).toString()).equalsIgnoreCase(strTime)){
								 Doj.put("DAF" , "none");
								}else{
									 Doj.put("DAF" , " ");
								}
							
						}
						
						
						
						else{
							Doj.put("DAREQ" ,  "No Request");
						}
					*/
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
				System.out.println("Exception At Er::"+Er);
			}
			
			
		}else{
			_SUCCESS_PAGE="/login.html";
			request.setAttribute("message",bundle_info.getString("HHCL_DESK_LOGI_FAILD")); 
		}

		System.out.println("dobcnt1::" + dobcnt1);
		if(dobcnt1<1){

			System.out.println("dobcnt1-2::" + dobcnt1);
			values = new JSONArray();
			if(Routing.equalsIgnoreCase("NewJoine")){	
				Doj.put("CALLNAME" , "No Records"); 
				Doj.put("DEPT" , "No Records");
				Doj.put("EMAIL" , "No Records");
				Doj.put("MOBILE" , "No Records");
				Doj.put("BUNAME" , "No Records");

			}else if(Routing.equalsIgnoreCase("HOLIDAYS")){

				Doj.put("EVENTDATE" , "No Records"); 
				Doj.put("EVENT" , "No Records");
				Doj.put("FLAG" , "No Records");
				Doj.put("HOLIDAYTYPE" , "No Records");


			}
			else if(Routing.equalsIgnoreCase("BIRTHADYS")){
				Doj.put("EVENTDATE" , "No Records"); 
				Doj.put("BUNAME" , "No Records");
				Doj.put("EVENT" , "No Records");
				Doj.put("FLAG" , "No Records");
				Doj.put("HOLIDAYTYPE" ,"No Records");

			}else if(Routing.equalsIgnoreCase("ATTENDANCE_BIO")){

				/*Doj.put("EMPNAME" , "No Records"); 
				Doj.put("businessunit" , "No Records");
				Doj.put("Department" , "No Records");
				Doj.put("designation" , "No Records");
				Doj.put("EMAIL" , "No Records");
				Doj.put("MOBILE" , "No Records");*/
				
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
		
		if(Routing==null){
			session.setAttribute("EMP_ID", username);
			session.setAttribute("EMP_PASSWORD", password);

		}
		 try {
			     if(statement!=null){
			       statement.close();
			     }
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
			
		}
		
		
		else{
			request.getRequestDispatcher(_SUCCESS_PAGE).forward(request, response);  
		}
		System.out.println("values::"+values.toString());
	}  
}
