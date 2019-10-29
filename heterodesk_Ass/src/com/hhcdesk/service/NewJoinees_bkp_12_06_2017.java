package com.hhcdesk.service;
import java.beans.PropertyVetoException;
import java.io.IOException;

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
 */
//@WebServlet("/User_Auth")
public class NewJoinees_bkp_12_06_2017 extends HttpServlet {

	public NewJoinees_bkp_12_06_2017() {
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext c = getServletContext();
		System.out.println("GetParameter"+ c.getAttribute("Venu"));
		HttpSession session = request.getSession(false);
		Statement statement=null;
		ResultSet Res=null;
		DataSource dataSource=null;
		java.sql.Connection conn=null;
		java.sql.PreparedStatement ps=null;

		Map UserMap=new HashMap();

		boolean ConnFlag=true;
		int User_Auth=0;
		GetDbData DataObj=new GetDbData();
		JSONObject jason = new JSONObject();
		JSONObject DOJ_DOB = new JSONObject();
		JSONObject EM_DOB = new JSONObject();

		JSONObject LeaveBalance = new JSONObject();
		
		JSONObject PaySlip= new JSONObject();
		JSONObject ForeCast= new JSONObject();

		int dobcnt=0;
		int dobcnt1=0;
		JSONArray values,Emp_exp;
		values = new JSONArray();
		
		Emp_exp = new JSONArray();
		
		String Routing=request.getParameter("Routing");

		System.out.println("Routing  :::" +Routing);

		BatchInsertRecords BatchInsert=new BatchInsertRecords();
		ArrayList MasterDataList = new ArrayList(); 
		ArrayList<PrepareData> list = new ArrayList<PrepareData>();
		Map FetchRc=new HashMap();
		StringBuffer biodata=new StringBuffer();
		StringBuffer Emp_DOB=new StringBuffer();
		StringBuffer Emp_DOJ=new StringBuffer();
		StringBuffer Emp_DOJ1=new StringBuffer();
		ArrayList DOB=new ArrayList();
		ArrayList DOJ=new ArrayList();
		ArrayList DOJ_DEPT=new ArrayList();


		JSONObject  Doj= new JSONObject();

		session.setAttribute("Notice" ,"N");
		ResourceBundle bundle_info =(ResourceBundle)(c.getAttribute("bundle"));

		String Query=bundle_info.getString("HHCL_DESK_USER_LOGIN");

		String HHCL_EVENT_INFO=bundle_info.getString("HHCL_DESK_EVENT");

		session.setAttribute("HHCL_EVENT_INFO" ,HHCL_EVENT_INFO);

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

		if(Routing!=null && (Routing.equalsIgnoreCase("NewJoine") || Routing.equalsIgnoreCase("HOLIDAYS") || Routing.equalsIgnoreCase("BIRTHADYS") || Routing.equalsIgnoreCase("DEPINFO") || Routing.equalsIgnoreCase("Field_Attn") || Routing.equalsIgnoreCase("MyProfile")|| Routing.equalsIgnoreCase("LEAVE_SUMMARY")|| Routing.equalsIgnoreCase("LEAVE_BALANCE") )){
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

			/*Emp_DOJ.append("SELECT concat(trim(DATE_FORMAT(A.HOLIDAYDATE,'%d-%M-%Y')),',',CONCAT(trim(DAYNAME(A.HOLIDAYDATE)))) EVENTDATE,");
			Emp_DOJ.append("A.COMMENTS EVENT,   ");
			Emp_DOJ.append("IF(NOW()<A.HOLIDAYDATE,'UPCOMING','CLOSE') FLAG,IFNULL(B.NAME,'REGULAR') HOLIDAYTYPE   ");
			Emp_DOJ.append("FROM   ");
			Emp_DOJ.append("HCLHRM_PROD.TBL_HOLIDAYS A   ");
			Emp_DOJ.append("LEFT JOIN HCLHRM_PROD.TBL_HOLIDAY_TYPE B ON A.HOLIDAYTYPEID=B.HOLIDAYTYPEID   ");
			Emp_DOJ.append("WHERE A.BUSINESSUNITID IN (   ");
			Emp_DOJ.append("SELECT COMPANYID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN ("+username+"))   ");
			Emp_DOJ.append(" and A.statusid=1001 ");
			Emp_DOJ.append("order by A.HOLIDAYDATE desc ");*/
			
			Emp_DOJ.append(" SELECT concat(trim(DATE_FORMAT(A.HOLIDAYDATE,'%d-%b-%y')),',',CONCAT(trim(DAYNAME(A.HOLIDAYDATE)))) EVENTDATE,");
			Emp_DOJ.append(" A.COMMENTS EVENT,");
			Emp_DOJ.append(" IF(NOW()<A.HOLIDAYDATE,'UPCOMING','CLOSE') FLAG,IFNULL(B.NAME,'REGULAR') HOLIDAYTYPE");
			Emp_DOJ.append(" FROM");
			Emp_DOJ.append(" HCLHRM_PROD.TBL_HOLIDAYS A");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_HOLIDAY_TYPE B ON A.HOLIDAYTYPEID=B.HOLIDAYTYPEID");
			Emp_DOJ.append(" WHERE A.BUSINESSUNITID IN (");
			Emp_DOJ.append(" SELECT COMPANYID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN (10454))");
			Emp_DOJ.append("  and A.statusid=1001");
			Emp_DOJ.append(" order by A.HOLIDAYDATE;");
			
			//System.out.println("HOLIDAYS:::"+Emp_DOJ.toString());

		}
		if(Routing.equalsIgnoreCase("BIRTHADYS")){
			Emp_DOJ.append(" SELECT A.CALLNAME NAME,E.NAME,C.NAME WISHHIM,D.EMAIL,D.MOBILE FROM    ");
			Emp_DOJ.append(" hclhrm_prod.tbl_employee_primary A   ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS B ON A.EMPLOYEEID=B.EMPLOYEEID   ");
			Emp_DOJ.append(" LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT C ON B.DEPARTMENTID=C.DEPARTMENTID   ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_CONTACT D ON A.EMPLOYEEID=D.EMPLOYEEID   ");
			Emp_DOJ.append(" LEFT JOIN hcladm_prod.tbl_businessunit E ON E.BUSINESSUNITID=A.COMPANYID   ");
			Emp_DOJ.append(" WHERE A.STATUS=1001 AND DATE_FORMAT(A.DATEOFBIRTH,'%m-%d')=DATE_FORMAT(curdate(),'%m-%d')");
		} 
		if(Routing.equalsIgnoreCase("DEPINFO")){
			
			/*Emp_DOJ.append(" SELECT A.CALLNAME EMPNAME,E.NAME businessunit,C.NAME Department,trim(F.name) designation,trim(D.EMAIL),D.MOBILE FROM    ");
			Emp_DOJ.append(" hclhrm_prod.tbl_employee_primary A   ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS B ON A.EMPLOYEEID=B.EMPLOYEEID   ");
			Emp_DOJ.append(" LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT C ON B.DEPARTMENTID=C.DEPARTMENTID   ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_CONTACT D ON A.EMPLOYEEID=D.EMPLOYEEID     ");
			Emp_DOJ.append(" LEFT JOIN hcladm_prod.tbl_businessunit E ON E.BUSINESSUNITID=A.COMPANYID   ");
			Emp_DOJ.append(" LEFT JOIN hcladm_prod.tbl_designation F ON B.designationid=F.designationid   ");
			Emp_DOJ.append(" WHERE A.STATUS=1001 and C.DEPARTMENTID in (select DEPARTMENTID from HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS   ");
			Emp_DOJ.append(" where employeeid in ( select employeeid from hclhrm_prod.tbl_employee_primary where employeesequenceno in ("+username+")))  ");*/

			
			Emp_DOJ.append(" SELECT A.CALLNAME EMPNAME,D.SHORTNAME DIVISION,E.CODE DEPT,F.NAME DESI,C.EMAIL,C.MOBILE");
			Emp_DOJ.append(" FROM");
			Emp_DOJ.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS B ON A.EMPLOYEEID=B.EMPLOYEEID");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_CONTACT C ON A.EMPLOYEEID=C.EMPLOYEEID");
			Emp_DOJ.append(" LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT D ON A.COMPANYID=D.BUSINESSUNITID");
			Emp_DOJ.append(" LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT E ON B.DEPARTMENTID=E.DEPARTMENTID");
			Emp_DOJ.append(" LEFT JOIN HCLADM_PROD.TBL_DESIGNATION F ON B.DESIGNATIONID=F.DESIGNATIONID");
			Emp_DOJ.append(" WHERE");
			Emp_DOJ.append(" A.STATUS=1001 AND");
			Emp_DOJ.append(" E.DEPARTMENTID IN (SELECT DEPARTMENTID");
			Emp_DOJ.append(" FROM");
			Emp_DOJ.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS B ON A.EMPLOYEEID=B.EMPLOYEEID");
			Emp_DOJ.append(" WHERE");
			Emp_DOJ.append(" A.STATUS=1001 AND A.EMPLOYEESEQUENCENO IN ("+username+"))");
			
			
			System.out.println("DEPINFO UPDATE::"+Emp_DOJ.toString());
		}
		
		if(Routing.equalsIgnoreCase("Field_Attn")){
			
			
			Emp_DOJ.append(" SELECT A.EMPLOYEESEQUENCENO  ID,DATE_FORMAT(B.TRANSACTIONDATE,'%d-%m-%Y')DATE,IF(B.DAYSTATUS='P','Reported','On Leave') DAYSTATUS FROM ");
			Emp_DOJ.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
			Emp_DOJ.append(" JOIN HCLHRM_PROD_SFA.TBL_EMPLOYEE_MANUAL_ATTENDANCE_SFA B ON A.EMPLOYEESEQUENCENO=B.EMPLOYEEID ");
			Emp_DOJ.append(" WHERE A.STATUS=1001 AND ");
			Emp_DOJ.append(" TRANSACTIONDATE BETWEEN DATE_SUB(LAST_DAY(NOW()),INTERVAL DAY(LAST_DAY(NOW()))-1 DAY) AND NOW() ");
			Emp_DOJ.append(" AND A.EMPLOYEESEQUENCENO in ("+username+") ");
			Emp_DOJ.append(" ORDER BY B.TRANSACTIONDATE ");
			
			
			/*Emp_DOJ.append(" SELECT A.EMPLOYEESEQUENCENO ID,DATE_FORMAT(B.TRANSACTIONDATE,'%d-%m-%Y')DATE,IF(B.DAYSTATUS='P','Reported','On Leave')DAYSTATUS FROM "); 
			Emp_DOJ.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A "); 
			Emp_DOJ.append(" JOIN HCLHRM_PROD_SFA.TBL_EMPLOYEE_MANUAL_ATTENDANCE_SFA B ON A.EMPLOYEESEQUENCENO=B.EMPLOYEEID "); 
			Emp_DOJ.append(" WHERE A.STATUS=1001 AND "); 
			Emp_DOJ.append(" TRANSACTIONDATE BETWEEN DATE_SUB(LAST_DAY(NOW()),INTERVAL DAY(LAST_DAY(NOW()))-1 DAY) AND NOW() "); 
			Emp_DOJ.append(" GROUP BY B.TRANSACTIONDATE  and A.EMPLOYEESEQUENCENO in("+username+")  "); */

		}
		
		
	if(Routing.equalsIgnoreCase("DEPINFO_ATTENDANCE")){
		Emp_DOJ.append("SELECT    ");
		Emp_DOJ.append("DATE_FORMAT(SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime, ' ', 1), ' ', -1),'%d-%m-%Y') DAY,   ");
		Emp_DOJ.append("SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime, ' ', 2), ' ', -1) FIN,   ");
		Emp_DOJ.append("MAX(CASE WHEN  SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime,' ', 2), ' ', -1) END) FOUT,   ");
		Emp_DOJ.append("SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN  SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime, ' ', 2), ' ', 2), ' ', -1)),':',2)PERDAY,   ");
		Emp_DOJ.append("IF(DAYNAME(transactiontime)='SUNDAY','WOFF','WDAY')DAYTYPE   ");
		Emp_DOJ.append("FROM   ");
		Emp_DOJ.append("unit_local_db.tbl_reader_log A   ");
		Emp_DOJ.append("LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY B ON A.EMPLOYEEID=B.EMPLOYEESEQUENCENO   ");
		Emp_DOJ.append("LEFT JOIN HCLHRM_PROD.TBL_HOLIDAYS C ON B.COMPANYID=C.BUSINESSUNITID   ");
		Emp_DOJ.append("WHERE TRANSACTIONTIME  BETWEEN date(CURDATE()-(day(CURDATE())-1)) AND now() AND A.EMPLOYEEID="+username+" GROUP BY DAY   ");
		Emp_DOJ.append("UNION ALL   ");
		Emp_DOJ.append("select  DATE_FORMAT(DATE,'%d-%m-%Y')DAY,'00:00:00' AS FIN,'00:00:00' AS LOUT,'00:00' AS PERDAY,IF(DAYNAME(DATE)='SUNDAY','WOFF','WDAY')DAYTYPE from (   ");
		Emp_DOJ.append("select date_add(CONCAT(YEAR(NOW()),'-',MONTH(NOW()),'-01'), INTERVAL n5.num*10000+n4.num*1000+n3.num*100+n2.num*10+n1.num DAY ) as date from   ");
		Emp_DOJ.append("(select 0 as num   ");
		Emp_DOJ.append("   union all select 1   ");
		Emp_DOJ.append("   union all select 2   ");
		Emp_DOJ.append("   union all select 3   ");
		Emp_DOJ.append("   union all select 4   ");
		Emp_DOJ.append("   union all select 5   ");
		Emp_DOJ.append("   union all select 6   ");
		Emp_DOJ.append("   union all select 7   ");
		Emp_DOJ.append("   union all select 8   ");
		Emp_DOJ.append("   union all select 9) n1,   ");
		Emp_DOJ.append("(select 0 as num   ");
		Emp_DOJ.append("   union all select 1   ");
		Emp_DOJ.append("   union all select 2   ");
		Emp_DOJ.append("   union all select 3   ");
		Emp_DOJ.append("   union all select 4   ");
		Emp_DOJ.append("   union all select 5   ");
		Emp_DOJ.append("   union all select 6   ");
		Emp_DOJ.append("   union all select 7   ");
		Emp_DOJ.append("   union all select 8   ");
		Emp_DOJ.append("   union all select 9) n2,   ");
		Emp_DOJ.append("(select 0 as num   ");
		Emp_DOJ.append("   union all select 1   ");
		Emp_DOJ.append("   union all select 2   ");
		Emp_DOJ.append("   union all select 3   ");
		Emp_DOJ.append("   union all select 4   ");
		Emp_DOJ.append("   union all select 5   ");
		Emp_DOJ.append("   union all select 6   ");
		Emp_DOJ.append("   union all select 7   ");
		Emp_DOJ.append("   union all select 8   ");
		Emp_DOJ.append("   union all select 9) n3,   ");
		Emp_DOJ.append("(select 0 as num   ");
		Emp_DOJ.append("   union all select 1   ");
		Emp_DOJ.append("   union all select 2   ");
		Emp_DOJ.append("   union all select 3   ");
		Emp_DOJ.append("   union all select 4   ");
		Emp_DOJ.append("   union all select 5   ");
		Emp_DOJ.append("   union all select 6   ");
		Emp_DOJ.append("   union all select 7   ");
		Emp_DOJ.append("   union all select 8   ");
		Emp_DOJ.append("   union all select 9) n4,   ");
		Emp_DOJ.append("(select 0 as num   ");
		Emp_DOJ.append("   union all select 1   ");
		Emp_DOJ.append("   union all select 2   ");
		Emp_DOJ.append("   union all select 3   ");
		Emp_DOJ.append("   union all select 4   ");
		Emp_DOJ.append("   union all select 5   ");
		Emp_DOJ.append("   union all select 6   ");
		Emp_DOJ.append("   union all select 7   ");
		Emp_DOJ.append("   union all select 8   ");
		Emp_DOJ.append("   union all select 9) n5   ");
		Emp_DOJ.append(") a   ");
		Emp_DOJ.append("where date BETWEEN DATE_FORMAT(CONCAT(YEAR(NOW()),'-',MONTH(NOW()),'-00'),'%Y-%m-%d') and NOW()   ");
		Emp_DOJ.append("and date not in (select DATE_FORMAT(TRANSACTIONTIME,'%Y-%m-%d') from unit_local_db.tbl_reader_log where employeeid="+username+" and transactiontime BETWEEN date(CURDATE()-(day(CURDATE())-1)) AND now())   ");
		Emp_DOJ.append("ORDER BY DAY ");
		
	  }
	
	if(Routing.equalsIgnoreCase("MyProfile")){
		
		/*Emp_DOJ.append(" SELECT A.EMPLOYEESEQUENCENO ID,G.CALLNAME RO,C.NAME DEPT,D.NAME DESI,F.DATEOFJOIN,PINFO.PAN ,  ");
		Emp_DOJ.append(" OD.PFNO,OD.ESINO,OD.PFUAN,BD.BANKNAME,OD.ACCOUNTNO,PC.EMAIL PRO_EMAIL,PC.MOBILE PRO_MOBILE, ");
		Emp_DOJ.append(" PPC.EMAIL PER_EMAIL,PPC.MOBILE PER_MOBILE, ");
		Emp_DOJ.append(" CONCAT(ICE.FIRSTNAME, ' ' ,ICE.LASTNAME) ICENAME,CONCAT(ICE.ADDRESS, ' ' ,ICE.ADDRESS2)ICEADDRESS, ");
		Emp_DOJ.append(" LOC.NAME LOCATION,ICE.MOBILE ICE_MOBILE, ");
		Emp_DOJ.append(" CONCAT(PPC.COMMUNICATIONADDRESS, ' ' ,PPC.COMMUNICATIONADDRESS2, ' ' ,LOC.NAME)COM_ADDRESS, ");
		Emp_DOJ.append(" CONCAT(PPC.PERMANENTADDRESS, ' ' ,PPC.PERMANENTADDRESS2, ' ' ,LOC.NAME)PER_ADDRESS ");
		Emp_DOJ.append(" FROM ");
		Emp_DOJ.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS B ON A.EMPLOYEEID=B.EMPLOYEEID ");
		Emp_DOJ.append(" LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT C ON C.DEPARTMENTID=B.DEPARTMENTID ");
		Emp_DOJ.append(" LEFT JOIN HCLADM_PROD.TBL_DESIGNATION D ON B.DESIGNATIONID=D.DESIGNATIONID ");
		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFILE F ON A.EMPLOYEEID=F.EMPLOYEEID ");
		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY  G ON B.MANAGERID=G.EMPLOYEEID ");
		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_OTHER_DETAIL OD ON A.EMPLOYEEID=OD.EMPLOYEEID ");
		Emp_DOJ.append(" LEFT JOIN HCLADM_PROD.TBL_BANK_DETAILS BD ON OD.BANKID=BD.BANKID ");
		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_CONTACT PC ON A.EMPLOYEEID=PC.EMPLOYEEID ");
		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PERSONAL_CONTACT PPC ON A.EMPLOYEEID=PPC.EMPLOYEEID ");
		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_ICE_CONTACT ICE ON A.EMPLOYEEID=ICE.EMPLOYEEID ");
		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PERSONALINFO PINFO ON A.EMPLOYEEID=PINFO.EMPLOYEEID ");
		Emp_DOJ.append(" LEFT JOIN HCLLCM_PROD.TBL_LOCATION LOC ON ICE.LOCATIONID=LOC.LOCATIONID AND PC.LOCATIONID=LOC.LOCATIONID AND PPC.COMMUNICATIONLOCATIONID=LOC.LOCATIONID AND PPC.PERMANENTLOCATIONID=LOC.LOCATIONID ");
		Emp_DOJ.append(" WHERE A.STATUS=1001 AND A.EMPLOYEESEQUENCENO in ("+username+") ");
		*/
		Emp_DOJ.append(" SELECT A.EMPLOYEESEQUENCENO ID,G.CALLNAME RO,C.NAME DEPT,D.NAME DESI,F.DATEOFJOIN,PINFO.PAN ,");
		Emp_DOJ.append(" OD.PFNO,OD.ESINO,OD.PFUAN,BD.BANKNAME,OD.ACCOUNTNO,PC.EMAIL PRO_EMAIL,PC.MOBILE PRO_MOBILE, ");
		Emp_DOJ.append(" PPC.EMAIL PER_EMAIL,PPC.MOBILE PER_MOBILE, ");
		Emp_DOJ.append(" CONCAT(ICE.FIRSTNAME, ' ' ,ICE.LASTNAME) ICENAME,CONCAT(ICE.ADDRESS, ' ' ,ICE.ADDRESS2)ICEADDRESS, ");
		Emp_DOJ.append(" LOC.NAME LOCATION,ICE.MOBILE ICE_MOBILE, ");
		Emp_DOJ.append(" CONCAT(PPC.COMMUNICATIONADDRESS, ' ' ,PPC.COMMUNICATIONADDRESS2, ' ' ,LOC2.NAME)COM_ADDRESS,");
		Emp_DOJ.append(" CONCAT(PPC.PERMANENTADDRESS, ' ' ,PPC.PERMANENTADDRESS2, ' ' ,LOC3.NAME)PER_ADDRESS");
		Emp_DOJ.append(" FROM ");
		Emp_DOJ.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A");
		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS B ON A.EMPLOYEEID=B.EMPLOYEEID");
		Emp_DOJ.append(" LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT C ON C.DEPARTMENTID=B.DEPARTMENTID");
		Emp_DOJ.append(" LEFT JOIN HCLADM_PROD.TBL_DESIGNATION D ON B.DESIGNATIONID=D.DESIGNATIONID ");
		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFILE F ON A.EMPLOYEEID=F.EMPLOYEEID");
		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY  G ON B.MANAGERID=G.EMPLOYEEID");
		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_OTHER_DETAIL OD ON A.EMPLOYEEID=OD.EMPLOYEEID");
		Emp_DOJ.append(" LEFT JOIN HCLADM_PROD.TBL_BANK_DETAILS BD ON OD.BANKID=BD.BANKID ");
		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_CONTACT PC ON A.EMPLOYEEID=PC.EMPLOYEEID ");
		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PERSONAL_CONTACT PPC ON A.EMPLOYEEID=PPC.EMPLOYEEID");
		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_ICE_CONTACT ICE ON A.EMPLOYEEID=ICE.EMPLOYEEID");
		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PERSONALINFO PINFO ON A.EMPLOYEEID=PINFO.EMPLOYEEID");
		Emp_DOJ.append(" LEFT JOIN HCLLCM_PROD.TBL_LOCATION LOC ON ICE.LOCATIONID=LOC.LOCATIONID");
		Emp_DOJ.append(" LEFT JOIN HCLLCM_PROD.TBL_LOCATION LOC2 ON PPC.COMMUNICATIONLOCATIONID=LOC2.LOCATIONID");
		Emp_DOJ.append(" LEFT JOIN HCLLCM_PROD.TBL_LOCATION LOC3 ON PPC.PERMANENTLOCATIONID=LOC3.LOCATIONID");
		Emp_DOJ.append(" WHERE A.STATUS=1001 AND A.EMPLOYEESEQUENCENO in ("+username+")");
		
		
		
		 Emp_DOJ1.append(" SELECT B.QUALIFICATIONID QID,B.BRANCHID BID,B.EXPERIENCE EXP,IF(B.ISEXPERIENCERELAVANT=1,'YES','NO')RELAVENAT,");
		 Emp_DOJ1.append(" B.DESIGNATION,B.EMPLOYERNAME FROM ");
		 Emp_DOJ1.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		 Emp_DOJ1.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_EXPERIENCE_details B ON A.EMPLOYEEID=B.EMPLOYEEID ");
		 Emp_DOJ1.append(" WHERE A.EMPLOYEESEQUENCENO in ("+username+") AND A.STATUS=1001  ");
		
		 	//LIMIT 1
		
	}if(Routing.equalsIgnoreCase("LEAVE_SUMMARY")){
		
		
		Emp_DOJ.append(" SELECT A.REQ_DATE,A.FROM_DATE,A.TO_DATE,A.MESSAGE,  ");
		Emp_DOJ.append(" IF(A.MAIL_STATUS='S','Sent',IF(A.MAIL_STATUS='F','Failed','Processing')) MAIL_STATUS, ");
		Emp_DOJ.append(" IF(FLAG='P','Pending',if(FLAG='A','APPROVED',if(FLAG='SC','SELF CANCELLED BY EMPLOYEE:',IF(FLAG='MC','CANCELLED BY YOUR MANAGER',IF(FLAG='R','REJECTED','UNKNOWN'))))) FLAG,A.COMMENTS,A.RID RID_R,A.FLAG FLAG_S,IF(DATE_FORMAT(A.FROM_DATE,'%Y-%m-%d')<=CURDATE(),1,0) DTFLAG ,A.APPROVEDNAME FROM ");
		Emp_DOJ.append(" HCLHRM_PROD_OTHERS.TBL_EMP_ATTN_REQ A ");
		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY B ON A.EMPLOYEEID=B.EMPLOYEESEQUENCENO ");
		Emp_DOJ.append(" WHERE REQ_TYPE!='AR' AND A.EMPLOYEEID="+username+" AND A.FLAG in('A','R','P','MC') and A.RID>73 and A.status=1001  ORDER BY A.REQ_DATE DESC LIMIT 60 ");
		
	}
	
    if(Routing.equalsIgnoreCase("LEAVE_BALANCE")){
		
    // ************** For Leave Balance Caliculation *****************************/////////////////////////
		
    	Emp_DOJ.append(" SELECT A.EMPLOYEEID 'Emp Code',  ");
    	Emp_DOJ.append(" SUM(DATEDIFF(A.TO_DATE,A.FROM_DATE)+1)Leaves,C.LEAVE_TYPE,A.FLAG FROM  ");
    	Emp_DOJ.append(" HCLHRM_PROD_OTHERS.TBL_EMP_ATTN_REQ A  ");
    	Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY B ON A.EMPLOYEEID=B.EMPLOYEESEQUENCENO  ");
    	Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_REQ C ON A.RID=C.RID  ");
    	Emp_DOJ.append(" WHERE A.RID>73 AND A.REQ_TYPE='LR' AND A.FLAG IN ('A','R','P')  ");
    	Emp_DOJ.append(" AND A.FROM_DATE > CONCAT(YEAR(CURDATE()),'-01','-01') AND B.EMPLOYEESEQUENCENO IN ("+username+")  ");
    	Emp_DOJ.append(" GROUP BY A.EMPLOYEEID,C.LEAVE_TYPE,A.FLAG ");
    	
    	
    	String QueryLop=" select if(count(*)=0,0,sum(A.LOPCOUNT)) from HCLHRM_PROD.TBL_EMPLOYEE_LOP A "
    			+ "LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY B ON A.EMPLOYEEID=B.EMPLOYEEID "
    			+ "where B.EMPLOYEESEQUENCENO IN ("+username+") and A.LOPTRANSACTIONID between CONCAT(YEAR(CURDATE()),'01') and CONCAT(YEAR(CURDATE()),'12') ";
    	
    	
    	
    	LeaveBalance.put("CL_P" , "0");
    	LeaveBalance.put("CL_A" , "0");
    	LeaveBalance.put("CL_USED" , "0");
    	LeaveBalance.put("CL_TOT" , "0");
    	LeaveBalance.put("CL_REM" , "0");

    	LeaveBalance.put("CL_R" , "0");
    	LeaveBalance.put("SL_P" , "0");
    	LeaveBalance.put("SL_A" , "0");
    	LeaveBalance.put("SL_USED" , "0");
    	LeaveBalance.put("SL_TOT" , "0");
    	LeaveBalance.put("SL_REM" , "0");
    	LeaveBalance.put("SL_R" , "0");

    	LeaveBalance.put("EL_P" , "0");
    	LeaveBalance.put("EL_A" , "0");
    	LeaveBalance.put("EL_USED" , "0");
    	LeaveBalance.put("EL_TOT" , "0");
    	LeaveBalance.put("EL_REM" , "0");

    	LeaveBalance.put("EL_R" , "0");
    	LeaveBalance.put("OD_P" , "0");
    	LeaveBalance.put("OD_A" , "0");
    	LeaveBalance.put("OD_USED" , "0");
    	LeaveBalance.put("OD_R" , "0");
    	LeaveBalance.put("CF_P" , "0");
    	LeaveBalance.put("CF_A" , "0");
    	LeaveBalance.put("CF_USED" , "0");
    	LeaveBalance.put("CF_R" , "0");								
    
    	LeaveBalance.put("LOP" , "0");	
    	
    	Res=null;

		try {
			Res=(ResultSet)DataObj.FetchData_Emp_DOB(QueryLop, "QueryLop", Res ,conn);
			if(Res.next()){
				LeaveBalance.put("LOP" , Res.getString(1));	
			}
			
		}catch(Exception ERFD){
			
			System.out.println("Error At Load From :QueryLop:: "+ERFD);
		}
		
    	
	 }  /// End at Leave Balance If Condition

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
		if(User_Auth==1){

			if(Routing.equalsIgnoreCase("NewJoine")){
				_SUCCESS_PAGE=bundle_info.getString("HHCL_DESK_DIVERT_NEWJOINEE");
			}else if(Routing.equalsIgnoreCase("HOLIDAYS")){

				_SUCCESS_PAGE=bundle_info.getString("HHCL_DESK_DIVERT_HOLIDAYS");
			}else if(Routing.equalsIgnoreCase("BIRTHADYS")){

				_SUCCESS_PAGE=bundle_info.getString("HHCL_DESK_DIVERT_BIRTHDAYS");
			}
			else if(Routing.equalsIgnoreCase("DEPINFO")){

				_SUCCESS_PAGE=bundle_info.getString("HHCL_DESK_DIVERT_DEPTINFO");
			}
			else if(Routing.equalsIgnoreCase("Field_Attn")){

				_SUCCESS_PAGE=bundle_info.getString("HHCL_DESK_DIVERT_FIELDATT");
			}else if(Routing.equalsIgnoreCase("MyProfile")){

				_SUCCESS_PAGE=bundle_info.getString("HHCL_DESK_DIVERT_MYPROFILE");
			}else if(Routing.equalsIgnoreCase("LEAVE_SUMMARY")){

				_SUCCESS_PAGE=bundle_info.getString("HHCL_DESK_LEAVE_SUMMARY");
				
			}else if(Routing.equalsIgnoreCase("LEAVE_BALANCE")){

				_SUCCESS_PAGE="HHCL_LEAVE_SUMMARY_Balance.jsp";
			}
			
			Res=null;

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
					else if(Routing.equalsIgnoreCase("DEPINFO")){

						 
						Doj.put("EMPNAME" , Res.getString(1)); 
						Doj.put("businessunit" , Res.getString(2));
						Doj.put("Department" , Res.getString(3));
						Doj.put("designation" , Res.getString(4));
						Doj.put("EMAIL" , Res.getString(5));
						Doj.put("MOBILE" , Res.getString(6));

					}else if(Routing.equalsIgnoreCase("Field_Attn")){

						Doj.put("DATE" , Res.getString(2)); 
						Doj.put("REPORT_STA" , Res.getString(3));
						
						//Doj.put("MOBILE" , Res.getString(6));

					}else if(Routing.equalsIgnoreCase("MyProfile")){
                       
						Doj.put("ID" , Res.getString(1));
						Doj.put("callname" , Res.getString(2)); 
						Doj.put("dept" , Res.getString(3));
						Doj.put("desi" , Res.getString(4)); 
						Doj.put("doj" , Res.getString(5));
						Doj.put("pan" , Res.getString(6));
						Doj.put("pfno" , Res.getString(7));
						Doj.put("esino" , Res.getString(8));
						Doj.put("pfuan" , Res.getString(9));
						Doj.put("bankname" , Res.getString(10));
						Doj.put("accountno" , Res.getString(11));
						Doj.put("email" , Res.getString(12));
						Doj.put("mobile" , Res.getString(13));
						Doj.put("pemail" , Res.getString(14));
						Doj.put("pmobile" , Res.getString(15));
						Doj.put("iecname" , Res.getString(16));
						Doj.put("iecaddress" , Res.getString(17));
						Doj.put("ieclocation" , Res.getString(18));
						Doj.put("iecmobile" , Res.getString(19));
						Doj.put("comaddress" , Res.getString(20));
						Doj.put("peraddress" , Res.getString(21));
						//Doj.put("MOBILE" , Res.getString(6));
			     }else if(Routing.equalsIgnoreCase("LEAVE_SUMMARY")){
			    	 
						Doj.put("REQ_DATE" , Res.getString(1)); 
						Doj.put("FRM_DATE" , Res.getString(2));
						Doj.put("TO_DATE" , Res.getString(3));
						Doj.put("LEAVE_STATUS" , Res.getString(6));
						Doj.put("MAIL_STATUS" , Res.getString(5));
						Doj.put("COMMENT" , Res.getString(7));
						
						Doj.put("MESSAGE" , Res.getString("MESSAGE"));
						
						Doj.put("APPROVEDNAME" , Res.getString("APPROVEDNAME"));
						
						Doj.put("RID" ,Res.getString("RID_R"));
						
						
						if((Res.getString("FLAG_S").toString()).equalsIgnoreCase("P") && (Res.getString("DTFLAG").toString()).equalsIgnoreCase("0") ){
							Doj.put("BUTSTA" , " ");
						}else{
							Doj.put("BUTSTA" ,"none");
						}

					}else if(Routing.equalsIgnoreCase("LEAVE_BALANCE")){
						
						//Emp Code, Leaves, LEAVE_TYPE, FLAG
						
						if(Res.getString("LEAVE_TYPE").equalsIgnoreCase("CL")){
							
							if(Res.getString("FLAG").equalsIgnoreCase("P")){
								
								LeaveBalance.put("CL_P" , Res.getString("Leaves"));
								
							}else if(Res.getString("FLAG").equalsIgnoreCase("A")){
								LeaveBalance.put("CL_A" , Res.getString("Leaves"));
								LeaveBalance.put("CL_USED" , Res.getString("Leaves"));
								LeaveBalance.put("CL_TOT" , "0");
								LeaveBalance.put("CL_REM" , 0- Integer.parseInt(Res.getString("Leaves")));
								
							}else if(Res.getString("FLAG").equalsIgnoreCase("R")){
								LeaveBalance.put("CL_R" , Res.getString("Leaves"));
							}
							
						}else if(Res.getString("LEAVE_TYPE").equalsIgnoreCase("SL")){
							
							if(Res.getString("FLAG").equalsIgnoreCase("P")){
								
								LeaveBalance.put("SL_P" , Res.getString("Leaves"));
								
							}else if(Res.getString("FLAG").equalsIgnoreCase("A")){
								LeaveBalance.put("SL_A" , Res.getString("Leaves"));
								LeaveBalance.put("SL_USED" , Res.getString("Leaves"));
								LeaveBalance.put("SL_TOT" , "0");
								LeaveBalance.put("SL_REM" , 0- Integer.parseInt(Res.getString("Leaves")));
								
							}else if(Res.getString("FLAG").equalsIgnoreCase("R")){
								LeaveBalance.put("SL_R" , Res.getString("Leaves"));
							}
							
						}else if(Res.getString("LEAVE_TYPE").equalsIgnoreCase("EL")){
							
							if(Res.getString("FLAG").equalsIgnoreCase("P")){
								
								LeaveBalance.put("EL_P" , Res.getString("Leaves"));
								
							}else if(Res.getString("FLAG").equalsIgnoreCase("A")){
								LeaveBalance.put("EL_A" , Res.getString("Leaves"));
								LeaveBalance.put("EL_USED" , Res.getString("Leaves"));
								LeaveBalance.put("EL_TOT" , "0");
								LeaveBalance.put("EL_REM" , 0- Integer.parseInt(Res.getString("Leaves")));
								
							}else if(Res.getString("FLAG").equalsIgnoreCase("R")){
								LeaveBalance.put("EL_R" , Res.getString("Leaves"));
							}
							
						}else if(Res.getString("LEAVE_TYPE").equalsIgnoreCase("OD")){
							
							if(Res.getString("FLAG").equalsIgnoreCase("P")){
								
								LeaveBalance.put("OD_P" , Res.getString("Leaves"));
								
							}else if(Res.getString("FLAG").equalsIgnoreCase("A")){
								LeaveBalance.put("OD_A" , Res.getString("Leaves"));
								LeaveBalance.put("OD_USED" , Res.getString("Leaves"));
								/*Doj.put("OD_TOT" , "0");
								Doj.put("OD_REM" , 0- Integer.parseInt(Res.getString("Leaves")));*/
								
							}else if(Res.getString("FLAG").equalsIgnoreCase("R")){
								LeaveBalance.put("OD_R" , Res.getString("Leaves"));
							}
							
						}else if(Res.getString("LEAVE_TYPE").equalsIgnoreCase("CF")){
							
							if(Res.getString("FLAG").equalsIgnoreCase("P")){
								
								LeaveBalance.put("CF_P" , Res.getString("Leaves"));
								
							}else if(Res.getString("FLAG").equalsIgnoreCase("A")){
								LeaveBalance.put("CF_A" , Res.getString("Leaves"));
								LeaveBalance.put("CF_USED" , Res.getString("Leaves"));
								/*Doj.put("OD_TOT" , "0");
								Doj.put("OD_REM" , 0- Integer.parseInt(Res.getString("Leaves")));*/
								
							}else if(Res.getString("FLAG").equalsIgnoreCase("R")){
								LeaveBalance.put("CF_R" , Res.getString("Leaves"));
							}
							
						}
						
						
						
						/*Doj.put("REQ_DATE" , Res.getString(1)); 
						Doj.put("FRM_DATE" , Res.getString(2));
						Doj.put("TO_DATE" , Res.getString(3));
						Doj.put("LEAVE_STATUS" , Res.getString(6));
						Doj.put("MAIL_STATUS" , Res.getString(5));
						Doj.put("COMMENT" , Res.getString(7));*/
						
					}
					//DOJ_DOB.put("EMPDATA" ,DOJ_DOB.toString());
					
					values.add(Doj);
					
					
				
					dobcnt1++;
				}  
				
				System.out.println("Hai------>"+values.toString());
				
				Res=null;
				if(Routing.equalsIgnoreCase("MyProfile")){
				try {
					
					System.out.println("Emp_DOJ1.toString()::"+Emp_DOJ1.toString());
					
					Res=(ResultSet)DataObj.FetchData_Emp_DOB(Emp_DOJ1.toString(), "Emp_Experience", Res ,conn);
					while(Res.next()){
						Doj= new JSONObject();
						Doj.put("qualification" , Res.getString(1));
						Doj.put("branch_id" , Res.getString(2));
						Doj.put("experience" , Res.getString(3));
						Doj.put("revlavent" , Res.getString(4));
						Doj.put("exp_desg" , Res.getString(5));
						Doj.put("employername" , Res.getString(6));
						
						Emp_exp.add(Doj);
					}
					
				}catch(Exception DOJ1) {
					System.out.println("Exception At DOJ1::"+DOJ1);
				}
			}
							/*DOJ_DOB.put(Res.getString(1) , Res.getString(2)); 
				 * */
				
			}catch(Exception Er){
				System.out.println("Exception At Er::"+Er);
			}
		}else{
			_SUCCESS_PAGE="/login.html";
			request.setAttribute("message",bundle_info.getString("HHCL_DESK_LOGI_FAILD")); 
		}
			System.out.println("dobcnt1::" + dobcnt1);
		if(dobcnt1<1 ){

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

			}else if(Routing.equalsIgnoreCase("DEPINFO")){

				Doj.put("EMPNAME" , "No Records"); 
				Doj.put("businessunit" , "No Records");
				Doj.put("Department" , "No Records");
				Doj.put("designation" , "No Records");
				Doj.put("EMAIL" , "No Records");
				Doj.put("MOBILE" , "No Records");

			}else if(Routing.equalsIgnoreCase("Field_Attn")){

				Doj.put("DATE" , "No Records"); 
				Doj.put("REPORT_STA" , "No Records");
				//Doj.put("MOBILE" , Res.getString(6));

			}else if(Routing.equalsIgnoreCase("MyProfile")){
				Doj.put("ID" , "No Data");
				Doj.put("callname" , "No Data"); 
				Doj.put("dept" , "No Data");
				Doj.put("desi" , "No Data"); 
				Doj.put("doj" , "No Data");
				Doj.put("pan" , "No Data");
				Doj.put("pfno" , "No Data");
				Doj.put("esino" , "No Data");
				Doj.put("pfuan" , "No Data");
				Doj.put("bankname" , "No Data");
				Doj.put("accountno" , "No Data");
				Doj.put("email" , "No Data");
				Doj.put("mobile" , "No Data");
				Doj.put("pemail" , "No Data");
				Doj.put("pmobile" , "No Data");
				Doj.put("iecname" , "No Data");
				Doj.put("iecaddress" , "No Data");
				Doj.put("ieclocation" , "No Data");
				Doj.put("iecmobile" , "No Data");
				Doj.put("comaddress" , "No Data");
				Doj.put("peraddress" , "No Data");
			//Doj.put("MOBILE" , Res.getString(6));
				
				Doj.put("qualification" , "No Data");
				Doj.put("branch_id" , "No Data");
				Doj.put("experience" , "No Data");
				Doj.put("revlavent" , "No Data");
				Doj.put("exp_desg" , "No Data");
				Doj.put("employername" , "No Data");

		}else if(Routing.equalsIgnoreCase("LEAVE_SUMMARY")){

			Doj.put("REQ_DATE" , "No Data"); 
			Doj.put("FRM_DATE" , "No Data");
			Doj.put("TO_DATE" , "No Data");
			Doj.put("LEAVE_STATUS" , "No Data");
			Doj.put("MAIL_STATUS" , "No Data");
			Doj.put("COMMENT" , "No Data");
			Doj.put("BUTSTA" ,"none");
			Doj.put("APPROVEDNAME" , "No_Data");

		}

			values.add(Doj);
		}
		request.setAttribute("DOJ_DOB",values.toString());
		request.setAttribute("Emp_exp",Emp_exp.toString());
		
		
		request.setAttribute("LeaveBalance",LeaveBalance.toString());
		
		
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
  	 
		System.out.println("_SUCCESS_PAGE::"+_SUCCESS_PAGE);
		request.getRequestDispatcher(_SUCCESS_PAGE).forward(request, response);  
	}  
}
