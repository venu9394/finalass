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
import java.util.ListIterator;
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


@SuppressWarnings("serial")
public class ManagerApprovals extends HttpServlet {



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext c = getServletContext();


		HttpSession session = request.getSession(false);
		Statement statement=null;
		ResultSet Res;
		DataSource dataSource=null;
		java.sql.Connection conn=null;
		java.sql.PreparedStatement ps=null;

		Map User_obj=(Map)session.getAttribute("User_Main_Obj");

		PrintWriter out = response.getWriter();
		Map UserMap=new HashMap();
		int call_month=0; 
		boolean ConnFlag=true;
		int User_Auth=0;
		GetDbData DataObj=new GetDbData();
		JSONObject jason = new JSONObject();
		JSONObject DOJ_DOB = new JSONObject();
		JSONObject EM_DOB = new JSONObject();

		JSONObject Login_bu_jason = new JSONObject();


		JSONObject PaySlip= new JSONObject();
		JSONObject ForeCast= new JSONObject();

		JSONObject ATT_MONTHS = new JSONObject();
		JSONObject ATT_MONTHS_1 = new JSONObject();

		JSONObject Doj22 = new JSONObject();


		int dobcnt=0;
		int dobcnt1=0;
		JSONArray values;
		JSONArray values2;
		values = new JSONArray();
		values2 = new JSONArray();
		int Month_Size=0,Month_Size1=0;
		Map BulkDates_map=new HashMap();

		Map BulkDates_map1=new HashMap();

		String Routing=request.getParameter("Routing");
		String Routing_type=request.getParameter("Routing_type");
		String Month_Sel=request.getParameter("Month_Sel");
		String EMPID=null,RID=null,TB_STATUS=null;

		String APPMOD=(String)request.getParameter("APP_MOD");

		String HR_ATT_CAL_MODE=(String)request.getParameter("ATT_MOD_F_HR");

		String ATT_MOD_F_HR_BU=(String)request.getParameter("ATT_MOD_F_HR_BU");


		if(HR_ATT_CAL_MODE==null){
			HR_ATT_CAL_MODE="MONTH";
		}
		String Month="currmonth";
		if(Month_Sel==null){

			Month_Sel="currmonth";
		}
		///ManagerAppr

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

		if(Routing.equalsIgnoreCase("ManagerAppr_req")){





			EMPID=request.getParameter("EMPID");
			RID=request.getParameter("RID");
			TB_STATUS=request.getParameter("TAB_NAME");

		}


		session.setAttribute("Notice" ,"N");
		BatchInsertRecords BatchInsert=new BatchInsertRecords();
		ArrayList MasterDataList = new ArrayList(); 
		ArrayList<PrepareData> list = new ArrayList<PrepareData>();
		Map FetchRc=new HashMap();
		StringBuffer biodata=new StringBuffer();
		StringBuffer Emp_DOB=new StringBuffer();
		StringBuffer Emp_DOJ=new StringBuffer();

		StringBuffer Emp_DOJ1=new StringBuffer();

		StringBuffer Months_ATT=new StringBuffer();
		StringBuffer Months_ATT_1=new StringBuffer();
		StringBuffer DepAtt_Leav=new StringBuffer();

		Map DepAtt_Leav_map=new HashMap();
		Map DepAtt_Leav_map_HL=new HashMap();

		Map ATT_APP=new HashMap();
		ArrayList DOB=new ArrayList();
		ArrayList DOJ=new ArrayList();
		ArrayList DOJ_DEPT=new ArrayList();


		StringBuffer HLDAYLIST=new StringBuffer();
		Map hlday=new HashMap();
		Map Att_Req=new HashMap();
		JSONObject  Doj= new JSONObject();


		ResourceBundle bundle_info =(ResourceBundle)(c.getAttribute("bundle"));

		String Query=bundle_info.getString("HHCL_DESK_USER_LOGIN");

		String HHCL_EVENT_INFO=bundle_info.getString("HHCL_DESK_EVENT");

		//session.setAttribute("HHCL_EVENT_INFO" ,HHCL_EVENT_INFO);

		String message=null;
		message=bundle_info.getString("HHCL_DESK_NEWJOINEE");

		try {
			conn=(java.sql.Connection)session.getAttribute("ConnectionObj");
		} catch (Exception e1) {
			e1.printStackTrace();
		}




		String _FAIL_PAGE="/NewJoinee_Faild.jsp";
		String _SUCCESS_PAGE=null;
		String username=request.getParameter("username");  
		String password=request.getParameter("pwd"); 

		if(Routing!=null && (Routing.equalsIgnoreCase("NewJoine") || Routing.equalsIgnoreCase("HOLIDAYS") || Routing.equalsIgnoreCase("BIRTHADYS") || Routing.equalsIgnoreCase("ATTENDANCE_BIO") || Routing.equalsIgnoreCase("Att_Request")||Routing.equalsIgnoreCase("ManagerAppr") ||Routing.equalsIgnoreCase("ManagerAppr_att") ||Routing.equalsIgnoreCase("ManagerAppr_req") ||Routing.equalsIgnoreCase("ManagerAppr_Resi") ||Routing.equalsIgnoreCase("Dept_att")||Routing.equalsIgnoreCase("Dept_att_HR") )){
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


		if(Routing.equalsIgnoreCase("ManagerAppr")){

			/*Emp_DOJ.append(" SELECT A.EMPLOYEESEQUENCENO ID,A.CALLNAME NAME,E.NAME DEPT,B.SUBJECT,  ");
			Emp_DOJ.append(" CONCAT(DATE_FORMAT(B.FROM_DATE,'%d-%m-%Y'),'<--->',DATE_FORMAT(B.TO_DATE,'%d-%m-%Y')) DURATION, ");
			Emp_DOJ.append(" IFNULL(C.TOTA_DAYS,DATEDIFF(B.TO_DATE,B.FROM_DATE)+1) DAYS,C.LEAVE_TYPE, ");
			Emp_DOJ.append(" IF(B.FLAG='A','Approved',IF(B.FLAG='R','Rejected','Pending'))Manager_Status,B.RID,B.FLAG,B.MESSAGE ");
			Emp_DOJ.append(" FROM ");
			Emp_DOJ.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD_OTHERS.TBL_EMP_ATTN_REQ B ON A.EMPLOYEESEQUENCENO=B.EMPLOYEEID ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_REQ C ON B.RID=C.RID ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS D ON A.EMPLOYEEID=D.EMPLOYEEID ");
			Emp_DOJ.append(" LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT E ON D.DEPARTMENTID=E.DEPARTMENTID ");
			Emp_DOJ.append(" WHERE B.RID>73 AND  B.REQ_TYPE='LR' and  B.EMPLOYEEID!="+username+"  and  B.FLAG in('P','A') AND B.EMPLOYEEID is not null  ");
			Emp_DOJ.append(" AND B.FROM_DATE >= test.CallEmp_Bu(B.EMPLOYEEID) AND LOWER(B.TO_EMAIL) LIKE '%"+User_obj.get("emp_mailid")+"%' ");
			Emp_DOJ.append("OR A.EMPLOYEEID in(D.MANAGERID ) AND B.FLAG!=NULL ");*/
			
			
			
			Emp_DOJ.append(" SELECT A.EMPLOYEESEQUENCENO ID,A.CALLNAME NAME,E.NAME DEPT,B.SUBJECT,  ");
			Emp_DOJ.append(" CONCAT(DATE_FORMAT(B.FROM_DATE,'%d-%m-%Y'),'<--->',DATE_FORMAT(B.TO_DATE,'%d-%m-%Y')) DURATION, ");
			Emp_DOJ.append(" IFNULL(C.TOTA_DAYS,DATEDIFF(B.TO_DATE,B.FROM_DATE)+1) DAYS,C.LEAVE_TYPE, ");
			Emp_DOJ.append(" IF(B.FLAG='A','Approved',IF(B.FLAG='R','Rejected','Pending'))Manager_Status,B.RID,B.FLAG,B.MESSAGE ");
			Emp_DOJ.append(" FROM ");
			Emp_DOJ.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD_OTHERS.TBL_EMP_ATTN_REQ B ON A.EMPLOYEESEQUENCENO=B.EMPLOYEEID ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_REQ C ON B.RID=C.RID ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS D ON A.EMPLOYEEID=D.EMPLOYEEID ");
			Emp_DOJ.append(" LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT E ON D.DEPARTMENTID=E.DEPARTMENTID ");
			
			
			// *********** Removed Function & Added few Lines by-Mahesh_HHCL ******//
			Emp_DOJ.append(" LEFT JOIN	(SELECT BUSINESSUNITID,MAX(PAYPERIOD)PAYPERIOD FROM	HCLHRM_PROD.TBL_BUSINESSUNIT_PAYROLL_PROCESS GROUP BY BUSINESSUNITID) F ON A.COMPANYID=F.BUSINESSUNITID");
			Emp_DOJ.append(" LEFT JOIN HCLADM_PROD.TBL_TRANSACTION_DATES G ON F.PAYPERIOD=G.TRANSACTIONDURATION AND G.TRANSACTIONTYPEID=1 AND G.BUSINESSUNITID=A.COMPANYID");
			
			
			Emp_DOJ.append(" WHERE B.RID>73 AND  B.REQ_TYPE='LR' and  B.EMPLOYEEID!="+username+"  and  B.FLAG in('P','A') AND B.EMPLOYEEID is not null  ");
			Emp_DOJ.append(" AND B.FROM_DATE >= G.TODATE AND LOWER(B.TO_EMAIL) LIKE '%"+User_obj.get("emp_mailid")+"%' ");
			Emp_DOJ.append(" OR D.MANAGERID IN (SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN ("+username+")) AND B.FLAG!=NULL ");
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
System.out.println("---->"+Emp_DOJ.toString());
		}

		if(Routing.equalsIgnoreCase("ManagerAppr_att")){




			Emp_DOJ.append(" SELECT A.EMPLOYEESEQUENCENO ID,A.CALLNAME NAME,E.NAME DEPT,B.SUBJECT, ");
			Emp_DOJ.append(" CONCAT(B.FROM_DATE,'<--->',B.TO_DATE) ");
			Emp_DOJ.append(" DURATION,  B.TOTA_HOURS NET_HOURS,IF(B.FLAG='A','Approved',IF(B.FLAG='R','Rejected','Pending'))Manager_Status,B.RID,B.FLAG, ");
			Emp_DOJ.append(" B.MESSAGE,B.REQ_DATE ");
			Emp_DOJ.append(" FROM ");
			Emp_DOJ.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD_OTHERS.TBL_EMP_ATTN_REQ B ON A.EMPLOYEESEQUENCENO=B.EMPLOYEEID ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_REQ C ON B.RID=C.RID ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS D ON A.EMPLOYEEID=D.EMPLOYEEID ");
			Emp_DOJ.append(" LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT E ON D.DEPARTMENTID=E.DEPARTMENTID ");
			Emp_DOJ.append(" WHERE B.RID>73 AND B.REQ_TYPE='AR' AND B.EMPLOYEEID!="+username+" AND  B.FLAG in('P') ");
			Emp_DOJ.append(" AND B.REQ_DATE >= TEST.CALLEMP_BU(B.EMPLOYEEID) AND B.EMPLOYEEID IS NOT NULL ");
			Emp_DOJ.append(" AND B.EMPLOYEEID IS NOT NULL ");
			Emp_DOJ.append(" AND LOWER(B.TO_EMAIL) ");
			Emp_DOJ.append(" LIKE '%"+User_obj.get("emp_mailid")+"%' ");
			Emp_DOJ.append(" OR A.EMPLOYEEID IN(D.MANAGERID) AND B.FLAG!=NULL ");





		}

		if(Routing.equalsIgnoreCase("ManagerAppr_Resi")){	

			if(APPMOD!=null && APPMOD.equalsIgnoreCase("MG")){
				Emp_DOJ.append(" select c.employeeid,a.callname,c.REASON,c.FEEDBACK,c.DATEMODIFIED,if(C.MANAGER_MSTATUS='A','Approved',if(C.MANAGER_MSTATUS='R','Reject','Pending')) A1,if(C.CC_STATUS='A','Approved',if(C.CC_STATUS='R','Reject','Pending')) R1, C.MANAGER_MSTATUS,C.CC_STATUS,C.MANAGERS_COMMENTS,C.HRS_COMMENTS FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
				Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS B ON A.EMPLOYEEID=B.EMPLOYEEID ");
				Emp_DOJ.append(" JOIN hclhrm_prod_others.tbl_emp_resignation C ON A.employeesequenceno=c.employeeid  AND C.MANAGER_MSTATUS in ('P','A','R') and C.CC_STATUS in('P','A','R')  and C.STATUS=1001 and C.MGR_OK=0");
				Emp_DOJ.append(" where  B.MANAGERID in(select employeeid from HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY where employeesequenceno="+username+") ");
				Emp_DOJ.append(" OR C.TO_MAIL like '%"+User_obj.get("emp_mailid")+"%' ");
				//Emp_DOJ.append(" AND C.MANAGER_MSTATUS='P' and C.STATUS=1001 ");
			}else if(APPMOD!=null && APPMOD.equalsIgnoreCase("HR")){

				Emp_DOJ.append(" select c.employeeid,a.callname,c.REASON,c.FEEDBACK,c.DATEMODIFIED,if(C.MANAGER_MSTATUS='A','Approved',if(C.MANAGER_MSTATUS='R','Reject','Pending')) A1,if(C.CC_STATUS='A','Approved',if(C.CC_STATUS='R','Reject','Pending')) R1,C.MANAGER_MSTATUS,C.CC_STATUS,C.MANAGERS_COMMENTS,C.HRS_COMMENTS FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
				Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS B ON A.EMPLOYEEID=B.EMPLOYEEID ");
				Emp_DOJ.append(" JOIN hclhrm_prod_others.tbl_emp_resignation C ON A.employeesequenceno=c.employeeid  AND C.MANAGER_MSTATUS in('A','P','R') and CC_STATUS='P' and C.STATUS=1001");

				/*Emp_DOJ.append(" where  B.MANAGERID in(select employeeid from HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY where employeesequenceno="+username+") ");
 		Emp_DOJ.append(" OR C.TO_MAIL like '%"+User_obj.get("emp_mailid")+"%' ");*/

			}

		}

		if(Routing.equalsIgnoreCase("Dept_att")){

			DepAtt_Leav_map=new HashMap();
			DepAtt_Leav_map_HL=new HashMap();
			ATT_APP=new HashMap();

			DepAtt_Leav.append(" SELECT A.EMPLOYEEID,DATE_FORMAT(SELECTED_DATE,'%e') Day,SELECTED_DATE,A.FLAG,E.LEAVE_TYPE from  ");
			DepAtt_Leav.append(" HCLHRM_PROD_OTHERS.TBL_EMP_ATTN_REQ A  ");
			DepAtt_Leav.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY B ON A.EMPLOYEEID=B.EMPLOYEESEQUENCENO  ");
			DepAtt_Leav.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS C ON B.EMPLOYEEID=C.EMPLOYEEID  ");
			DepAtt_Leav.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY D ON C.MANAGERID=D.EMPLOYEEID  ");
			DepAtt_Leav.append(" LEFT JOIN HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_REQ E ON A.RID=E.RID,  ");
			DepAtt_Leav.append(" (select adddate('1970-01-01',t4*10000 + t3*1000 + t2*100 + t1*10 + t0) SELECTED_DATE from  ");
			DepAtt_Leav.append(" (select 0 t0 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0,  ");
			DepAtt_Leav.append(" (select 0 t1 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1,  ");
			DepAtt_Leav.append(" (select 0 t2 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2,  ");
			DepAtt_Leav.append(" (select 0 t3 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3,  ");
			DepAtt_Leav.append(" (select 0 t4 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v  ");
			DepAtt_Leav.append(" where SELECTED_DATE between  ");
			DepAtt_Leav.append(" DATE_FORMAT(A.FROM_DATE,'%Y-%m-%d') and DATE_FORMAT(A.TO_DATE,'%Y-%m-%d')  ");
			DepAtt_Leav.append(" AND A.FLAG IN ('A','P','R') AND A.REQ_TYPE='LR'  AND A.STATUS=1001 AND A.RID>73  ");
			DepAtt_Leav.append(" AND D.EMPLOYEESEQUENCENO IN ("+username+")  ");
			DepAtt_Leav.append(" AND A.FROM_DATE BETWEEN  ");

			if(Month_Sel.equalsIgnoreCase("currmonth")){

				DepAtt_Leav.append(" (DATE_SUB(CONCAT_WS('-', YEAR(CURDATE()),MONTH(CURDATE()),'01'),INTERVAL DAY(CONCAT_WS('-', YEAR(CURDATE()),MONTH(CURDATE()),'01'))-1 DAY))  ");
				DepAtt_Leav.append(" AND  ");
				DepAtt_Leav.append(" (LAST_DAY(CONCAT_WS('-', YEAR(CURDATE()),MONTH(CURDATE()),'01')))  ");
				DepAtt_Leav.append(" GROUP BY A.RID,SELECTED_DATE ORDER BY A.RID,SELECTED_DATE ");

			}else{

				DepAtt_Leav.append(" (DATE_SUB(CONCAT_WS('-', YEAR(CURDATE()),'"+Month_Sel+"','01'),INTERVAL DAY(CONCAT_WS('-', YEAR(CURDATE()),'"+Month_Sel+"','01'))-1 DAY))  ");
				DepAtt_Leav.append(" AND  ");
				DepAtt_Leav.append(" (LAST_DAY(CONCAT_WS('-', YEAR(CURDATE()),'"+Month_Sel+"','01')))  ");
				DepAtt_Leav.append(" GROUP BY A.RID,SELECTED_DATE ORDER BY A.RID,SELECTED_DATE ");

			}


			Months_ATT_1.append(" SELECT DISTINCT IF(MONTH(TRANSACTIONTIME)<10,CONCAT(0,MONTH(TRANSACTIONTIME)),MONTH(TRANSACTIONTIME))MONTHS,MONTHNAME(TRANSACTIONTIME) MONTHNAME ");
			Months_ATT_1.append(" FROM unit_local_db.tbl_reader_log where ");
			Months_ATT_1.append(" MONTH(TRANSACTIONTIME)!=MONTH(CURDATE()) AND ");
			Months_ATT_1.append(" employeeid="+username+" AND YEAR(TRANSACTIONTIME)=YEAR(CURDATE()) ");
			Months_ATT_1.append(" ORDER BY MONTH(TRANSACTIONTIME) DESC ");

			Res=null;

			try {
				Res=(ResultSet)DataObj.FetchData(DepAtt_Leav.toString(), "DepAtt_Leav_Colors", Res ,conn);

				while(Res.next()){

					DepAtt_Leav_map.put(Res.getString("EMPLOYEEID")+"_"+Res.getString("Day"), Res.getString("Day"));
					DepAtt_Leav_map.put(Res.getString("EMPLOYEEID")+"_FLAG_"+Res.getString("Day"), Res.getString("FLAG"));
					DepAtt_Leav_map.put(Res.getString("EMPLOYEEID")+"_TYP_"+Res.getString("Day"), Res.getString("LEAVE_TYPE"));
				}  

			}catch(Exception Er){
				System.out.println(" Get Months Exception At 202 Er::"+Er);
			}

			if(Routing_type==null){
				Res=null;

				try {
					ATT_MONTHS_1.put("currmonth", "Current Month");
					Res=(ResultSet)DataObj.FetchData(Months_ATT_1.toString(), "EMPLOYEE_ATTENDANCELIST", Res ,conn);
					while(Res.next()){

						ATT_MONTHS_1.put(Res.getString(1), Res.getString(2));

					}  

				}catch(Exception Er){
					System.out.println(" Get Months Exception At 201 Er::"+Er);
				}
			}
			String CallYear=null,CallMonth=null;
			Res=null;

			try {

				if(Month_Sel.equalsIgnoreCase("currmonth")){
					Res=(ResultSet)DataObj.FetchData(" SELECT trim(EXTRACT(DAY FROM  LAST_DAY(now()) )),EXTRACT(YEAR FROM  now()) ,EXTRACT(MONTH FROM  now()) from dual", "Last Date OF Given Month", Res ,conn);
				}else{
					Res=(ResultSet)DataObj.FetchData("select date_format(LAST_DAY(date_format(concat(EXTRACT(YEAR FROM LAST_DAY(now())),'-',"+Month_Sel+",'-',01),'%Y-%m-%d')),'%d'),EXTRACT(YEAR FROM  now()) ,'"+Month_Sel+"' ", "Last Date OF Given Month", Res ,conn);

				}
				while(Res.next()){ 

					call_month=Res.getInt(1);
					CallYear=Res.getString(2);
					CallMonth=Res.getString(3);

				}  

			}catch(Exception Er){
				System.out.println("Last Day Of Given Month Exception At 99 Er::"+Er);
			}
			// =================================================Holidays List
			StringBuffer HL_LV_Q=new StringBuffer();

			HL_LV_Q.append(" select BUSINESSUNITID,HOLIDAYDATE,'HL',DATE_FORMAT(HOLIDAYDATE,'%e'),COMMENTS from HCLHRM_PROD.TBL_HOLIDAYS ");
			HL_LV_Q.append(" where DATE_FORMAT(HOLIDAYDATE,'%m')="+CallMonth+"  and DATE_FORMAT(HOLIDAYDATE,'%Y')="+CallYear+" "); 


			Res=null;

			try {
				Res=(ResultSet)DataObj.FetchData(HL_LV_Q.toString(), "DepAtt_Holidays", Res ,conn);

				while(Res.next()){

					int dayaaa=Res.getString(4).toString().length();

					if(dayaaa==1){

						DepAtt_Leav_map_HL.put("HL$0"+Res.getString(4).toString()+"$"+Res.getString("BUSINESSUNITID").toString(), Res.getString("COMMENTS").toString());

					}else{
						DepAtt_Leav_map_HL.put("HL$"+Res.getString(4).toString()+"$"+Res.getString("BUSINESSUNITID").toString(), Res.getString("COMMENTS").toString());
					}
				}  

			}catch(Exception Er){
				System.out.println(" 345 Get Months Exception At 88 Er::"+Er);
			}

			// Public Holiday List

			StringBuffer ATT_APP_buf=new StringBuffer();

			ATT_APP_buf.append("select a.EMPLOYEEID, a.REQ_DATE ,DATE_FORMAT(REQ_DATE,'%e'),a.FLAG from hclhrm_prod_others.tbl_emp_attn_req A ");
			ATT_APP_buf.append("LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY B ON A.EMPLOYEEID=B.EMPLOYEESEQUENCENO ");
			ATT_APP_buf.append("LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS C ON B.EMPLOYEEID=C.EMPLOYEEID ");
			ATT_APP_buf.append("LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY D ON C.MANAGERID=D.EMPLOYEEID ");
			ATT_APP_buf.append("where A.REQ_TYPE='AR' and A.STATUS=1001 AND DATE_FORMAT(REQ_DATE,'%m')="+CallMonth+" and DATE_FORMAT(REQ_DATE,'%Y')="+CallYear+" and a.FLAG='A' ");

			Res=null;

			try {
				//ATT_MONTHS_1.put("currmonth", "Current Month");
				Res=(ResultSet)DataObj.FetchData(ATT_APP_buf.toString(), "DepAtt_Holidays", Res ,conn);

				while(Res.next()){

					ATT_APP.put(Res.getString(1).toString()+"$"+Res.getString(3).toString(), Res.getString("FLAG").toString());
					//DepAtt_Leav_map.put(Res.getString("EMPLOYEEID")+"_FLAG_"+Res.getString("Day"), Res.getString("FLAG"));
					//DepAtt_Leav_map.put(Res.getString("EMPLOYEEID")+"_TYP_"+Res.getString("Day"), Res.getString("LEAVE_TYPE"));
					//ATT_MONTHS_1.put(Res.getString(1), Res.getString(2));

				}  

			}catch(Exception Er){
				System.out.println(" Get Months Exception 77 At Er::"+Er);
			}

			Emp_DOJ.append(" SELECT A.EMPLOYEESEQUENCENO ID,A.CALLNAME NAME,  ");

			String DummyYear="0000-00-00";
			// call_month=2;
			for(int i=1;i<=call_month;i++){

				String k="0";
				if(CallMonth.length()==1){

					CallMonth="0".concat(CallMonth); 
				}
				if(i<10){

					k="0".concat(""+i+"");
				}else{
					k=""+i+"";
				}

				DummyYear=CallYear+"-"+CallMonth+"-"+k;



				if(i!=call_month){

					Doj22.put(DummyYear, DummyYear);	 
					//  Doj22.put("DAY"+i+"", "DAY"+i+"");

					//ATT_APP

					// Emp_DOJ.append(" CONCAT(FINDAY"+i+",'-',LOUTDAY"+i+")DAY"+i+", IF(D.DAY"+i+"<'08.50',if(date('"+DummyYear+"')<=date(now()),'red',''),' ') DAY"+i+"CL , ");

					Emp_DOJ.append(" CONCAT(FINDAY"+i+",'-',LOUTDAY"+i+")DAY"+i+", IF(FINDAY"+i+">'09.11' OR LOUTDAY"+i+"<'18.00' ,if(date('"+DummyYear+"')<=date(now()),'red',''),' ') DAY"+i+"CL , ");

					// Emp_DOJ.append(" CONCAT(FINDAY"+i+",'-',LOUTDAY"+i+")DAY"+i+", IF(D.DAY"+i+"<'08.50',IF(ATT_APP.get(A.EMPLOYEESEQUENCENO.concat('$"+i+"'))='A','green','red'),' ') DAY"+i+"CL , ");
					//Emp_DOJ.append("IF((DATE_FORMAT('"+DummyYear+"','%w')=0 OR DATE_FORMAT('"+DummyYear+"','%w')=1 OR DATE_FORMAT('"+DummyYear+"','%w')=3) AND DAYNAME('"+DummyYear+"')='Saturday','WOFF-Sa', ");
					Emp_DOJ.append("if(DAYNAME('"+DummyYear+"')='Sunday','WOFF','WDAY') HLWD_TYPE_"+i+" , concat('HL','$',IF(length('"+i+"')=1,concat('0','"+i+"'), '"+i+"'),'$', A.companyid) HL$"+i+" , ");
					/*if(Month_Sel.equalsIgnoreCase("currmonth")){

			 Emp_DOJ.append("IF((DATE_FORMAT('"+DummyYear+"','%w')=0 OR DATE_FORMAT('"+DummyYear+"','%w')=1 OR DATE_FORMAT(YEAR(CURDATE())-MONTH('"+DummyYear+"','%w')=3) AND DAYNAME('"+DummyYear+"')='Saturday','WOFF-Sa', ");
			 Emp_DOJ.append("if(DAYNAME('"+DummyYear+"')='Sunday','WOFF-Su','WDAY')) HLWD_TYPE_"+i+" , ");

		 }else{
			 Emp_DOJ.append("IF((DATE_FORMAT(YEAR(CURDATE())-'"+Month_Sel+"'-"+i+"','%w')=0 OR DATE_FORMAT(YEAR(CURDATE())-'"+Month_Sel+"'-"+i+"','%w')=1 OR DATE_FORMAT(YEAR(CURDATE())-'"+Month_Sel+"'-"+i+"','%w')=3) AND DAYNAME(YEAR(CURDATE())-'"+Month_Sel+"'-'"+i+"')='Saturday','WOFF-Sa', ");
			 Emp_DOJ.append(" if(DAYNAME(YEAR(CURDATE())-'"+Month_Sel+"'-'"+i+"')='Sunday','WOFF-Su','WDAY')) HLWD_TYPE_"+i+" , ");


		 }*/

				}else if(i==call_month){

					Doj22.put(DummyYear, DummyYear);
					//Doj22.put("DAY"+i+"", "DAY"+i+"");


					Emp_DOJ.append(" CONCAT(FINDAY"+i+",'-',LOUTDAY"+i+") DAY"+i+",IF(FINDAY"+i+">'09.11' OR LOUTDAY"+i+"<'18.00' ,if(date('"+DummyYear+"')<=date(now()),'red',''),' ') DAY"+i+"CL ");
					// Emp_DOJ.append(",IF((DATE_FORMAT('"+DummyYear+"','%w')=0 OR DATE_FORMAT('"+DummyYear+"','%w')=1 OR DATE_FORMAT('"+DummyYear+"','%w')=3) AND DAYNAME('"+DummyYear+"')='Saturday','WOFF-Sa', ");
					Emp_DOJ.append(", if(DAYNAME('"+DummyYear+"')='Sunday','WOFF','WDAY') HLWD_TYPE_"+i+" , concat('HL','$',IF(length('"+i+"')=1,concat('0','"+i+"'), '"+i+"'),'$', A.companyid) HL$"+i+" ");
					// Emp_DOJ.append(",if(DAYNAME('"+DummyYear+"')='Sunday','WOFF','WDAY') HLWD_TYPE_"+i+" , concat('"+DummyYear+"','$', A.companyid) HL$"+i+" ");

					/*if(Month_Sel.equalsIgnoreCase("currmonth")){

				 Emp_DOJ.append(",IF((DATE_FORMAT('"+DummyYear+"','%w')=0 OR DATE_FORMAT('"+DummyYear+"','%w')=1 OR DATE_FORMAT(YEAR(CURDATE())-MONTH('"+DummyYear+"','%w')=3) AND DAYNAME('"+DummyYear+"')='Saturday','WOFF-Sa', ");
				 Emp_DOJ.append(" if(DAYNAME('"+DummyYear+"')='Sunday','WOFF-Su','WDAY')) HLWD_TYPE_"+i+"  ");

			 }else{
				 Emp_DOJ.append(",IF((DATE_FORMAT('"+DummyYear+"','%w')=0 OR DATE_FORMAT('"+DummyYear+"','%w')=1 OR DATE_FORMAT('"+DummyYear+"','%w')=3) AND DAYNAME('"+DummyYear+"')='Saturday','WOFF-Sa', ");
				 Emp_DOJ.append(" if(DAYNAME('"+DummyYear+"')='Sunday','WOFF-Su','WDAY')) HLWD_TYPE_"+i+" ");


			 }*/
				}



			}


			// Emp_DOJ.append(" SELECT IF((DATE_FORMAT('2017-03-12','%w')=0 OR DATE_FORMAT('2017-03-12','%w')=1 OR DATE_FORMAT('2017-03-12','%w')=3) AND DAYNAME('2017-03-12')='Saturday','WOFF-Sa', ");
			// Emp_DOJ.append(" if(DAYNAME('2017-03-12')='Sunday','WOFF-Su','WDAY')) ");
			/* Emp_DOJ.append(" CONCAT(FINDAY2,'-',LOUTDAY2)DAY2, ");
	 Emp_DOJ.append(" CONCAT(FINDAY3,'-',LOUTDAY3)DAY3, ");
	 Emp_DOJ.append(" CONCAT(FINDAY4,'-',LOUTDAY4)DAY4, ");
	 Emp_DOJ.append(" CONCAT(FINDAY5,'-',LOUTDAY5)DAY5, ");
	 Emp_DOJ.append(" CONCAT(FINDAY6,'-',LOUTDAY6)DAY6, ");
	 Emp_DOJ.append(" CONCAT(FINDAY7,'-',LOUTDAY7)DAY7, ");
	 Emp_DOJ.append(" CONCAT(FINDAY8,'-',LOUTDAY8)DAY8, ");
	 Emp_DOJ.append(" CONCAT(FINDAY9,'-',LOUTDAY9)DAY9, ");
	 Emp_DOJ.append(" CONCAT(FINDAY10,'-',LOUTDAY10)DAY10, ");
	 Emp_DOJ.append(" CONCAT(FINDAY11,'-',LOUTDAY11)DAY11, ");
	 Emp_DOJ.append(" CONCAT(FINDAY12,'-',LOUTDAY12)DAY12, ");
	 Emp_DOJ.append(" CONCAT(FINDAY13,'-',LOUTDAY13)DAY13, ");
	 Emp_DOJ.append(" CONCAT(FINDAY14,'-',LOUTDAY14)DAY14, ");
	 Emp_DOJ.append(" CONCAT(FINDAY15,'-',LOUTDAY15)DAY15, ");
	 Emp_DOJ.append(" CONCAT(FINDAY16,'-',LOUTDAY16)DAY16, ");
	 Emp_DOJ.append(" CONCAT(FINDAY17,'-',LOUTDAY17)DAY17, ");
	 Emp_DOJ.append(" CONCAT(FINDAY18,'-',LOUTDAY18)DAY18, ");
	 Emp_DOJ.append(" CONCAT(FINDAY19,'-',LOUTDAY19)DAY19, ");
	 Emp_DOJ.append(" CONCAT(FINDAY20,'-',LOUTDAY20)DAY20, ");
	 Emp_DOJ.append(" CONCAT(FINDAY21,'-',LOUTDAY21)DAY21, ");
	 Emp_DOJ.append(" CONCAT(FINDAY22,'-',LOUTDAY22)DAY22, ");
	 Emp_DOJ.append(" CONCAT(FINDAY23,'-',LOUTDAY23)DAY23, ");
	 Emp_DOJ.append(" CONCAT(FINDAY24,'-',LOUTDAY24)DAY24, ");
	 Emp_DOJ.append(" CONCAT(FINDAY25,'-',LOUTDAY25)DAY25, ");
	 Emp_DOJ.append(" CONCAT(FINDAY26,'-',LOUTDAY26)DAY26, ");
	 Emp_DOJ.append(" CONCAT(FINDAY27,'-',LOUTDAY27)DAY27, ");
	 Emp_DOJ.append(" CONCAT(FINDAY28,'-',LOUTDAY28)DAY28, ");
	 Emp_DOJ.append(" CONCAT(FINDAY29,'-',LOUTDAY29)DAY29, ");
	 Emp_DOJ.append(" CONCAT(FINDAY30,'-',LOUTDAY30)DAY30, ");
	 Emp_DOJ.append(" CONCAT(FINDAY31,'-',LOUTDAY31)DAY31 ");*/
			Emp_DOJ.append(" FROM ");
			Emp_DOJ.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS B ON A.EMPLOYEEID=B.EMPLOYEEID  ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_ATTENDANCE_FIN_LOUT_SUMMARY C ON A.EMPLOYEEID=C.EMPLOYEEID  ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_ATTENDANCE_HOURS_SUMMARY D ON A.EMPLOYEEID=D.EMPLOYEEID  ");
			Emp_DOJ.append(" WHERE ");
			if(Month_Sel.equalsIgnoreCase("currmonth")){
				Emp_DOJ.append(" A.STATUS=1001   AND C.MONTH=EXTRACT(MONTH FROM LAST_DAY(now())) AND C.YEAR=EXTRACT(YEAR FROM LAST_DAY(now())) AND D.MONTH=EXTRACT(MONTH FROM LAST_DAY(now())) AND D.YEAR=EXTRACT(YEAR FROM LAST_DAY(now())) ");
			}else{
				Emp_DOJ.append(" A.STATUS=1001   AND C.MONTH="+Month_Sel+" AND C.YEAR=EXTRACT(YEAR FROM LAST_DAY(now())) AND D.MONTH="+Month_Sel+" AND D.YEAR=EXTRACT(YEAR FROM LAST_DAY(now()))");

			}
			Emp_DOJ.append(" AND B.MANAGERID=(SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN ("+username+")) ");

			
			
			System.out.println("Mahesh Emp_DOJ--->"+Emp_DOJ.toString());
			
			request.setAttribute("TITLES_TITLES", Doj22.toString());

		}

		String CompanySelected=null;
		if(Routing.equalsIgnoreCase("Dept_att_HR") && HR_ATT_CAL_MODE!=null && HR_ATT_CAL_MODE.equalsIgnoreCase("MONTH")){


			StringBuffer Login_bu=new StringBuffer();

			Login_bu.append(" SELECT b.employeesequenceno,a.EMPLOYEEID,b.companyid, a.BUSINESSUNITID,C.NAME  ");
			Login_bu.append(" FROM hclhrm_prod.tbl_employee_businessunit a ");
			Login_bu.append(" left join hclhrm_prod.tbl_employee_primary b on a.employeeid=b.employeeid ");
			Login_bu.append(" left join hcladm_prod.tbl_businessunit c on a.BUSINESSUNITID=c.BUSINESSUNITID ");
			Login_bu.append(" where b.employeesequenceno="+username+" and c.status=1001 ");

			if(Routing_type==null){
				Res=null;

				try {
					/// ATT_MONTHS_1.put("currmonth", "Current Month");
					Res=(ResultSet)DataObj.FetchData(Login_bu.toString(), "Employee Business Unit", Res ,conn);
					while(Res.next()){

						Login_bu_jason.put(Res.getString("BUSINESSUNITID"), Res.getString("NAME"));
						CompanySelected=Res.getString("companyid");

					}  

					request.setAttribute("Login_bu_jason", Login_bu_jason.toString());
					request.setAttribute("CompanySelected", CompanySelected);

				}catch(Exception Er){
					//System.out.println(" Get Months Exception At Er::"+Er);
				}

				if(ATT_MOD_F_HR_BU==null){
					ATT_MOD_F_HR_BU=CompanySelected;
				}

				Doj22 = new JSONObject();
				values = new JSONArray();

				DepAtt_Leav_map=new HashMap();
				DepAtt_Leav_map_HL=new HashMap();
				DepAtt_Leav.append(" SELECT A.EMPLOYEEID,DATE_FORMAT(SELECTED_DATE,'%e') Day,SELECTED_DATE,A.FLAG,E.LEAVE_TYPE from  ");
				DepAtt_Leav.append(" HCLHRM_PROD_OTHERS.TBL_EMP_ATTN_REQ A  ");
				DepAtt_Leav.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY B ON A.EMPLOYEEID=B.EMPLOYEESEQUENCENO  ");
				DepAtt_Leav.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS C ON B.EMPLOYEEID=C.EMPLOYEEID  ");

				// DepAtt_Leav.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY D ON C.MANAGERID=D.EMPLOYEEID  ");

				DepAtt_Leav.append(" LEFT JOIN HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_REQ E ON A.RID=E.RID,  ");
				DepAtt_Leav.append(" (select adddate('1970-01-01',t4*10000 + t3*1000 + t2*100 + t1*10 + t0) SELECTED_DATE from  ");
				DepAtt_Leav.append(" (select 0 t0 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0,  ");
				DepAtt_Leav.append(" (select 0 t1 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1,  ");
				DepAtt_Leav.append(" (select 0 t2 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2,  ");
				DepAtt_Leav.append(" (select 0 t3 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3,  ");
				DepAtt_Leav.append(" (select 0 t4 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v  ");

				DepAtt_Leav.append(" where DATE_FORMAT(SELECTED_DATE,'%Y-%m-%d')>=   ");
				DepAtt_Leav.append(" DATE_FORMAT(A.FROM_DATE,'%Y-%m-%d') and  DATE_FORMAT(SELECTED_DATE,'%Y-%m-%d')<=DATE_FORMAT(A.TO_DATE,'%Y-%m-%d')  ");
				DepAtt_Leav.append(" AND A.FLAG IN ('A','P','R') AND A.REQ_TYPE='LR'  AND A.STATUS=1001 AND A.RID>73  ");

				/* DepAtt_Leav.append(" where SELECTED_DATE between  ");
	 DepAtt_Leav.append(" DATE_FORMAT(A.FROM_DATE,'%Y-%m-%d') and DATE_FORMAT(A.TO_DATE,'%Y-%m-%d')  ");
	 DepAtt_Leav.append(" AND A.FLAG IN ('A','P','R') AND A.REQ_TYPE='LR'  AND A.STATUS=1001 AND A.RID>73  ");
				 */
				DepAtt_Leav.append(" AND B.COMPANYID="+ATT_MOD_F_HR_BU+"  ");

				// DepAtt_Leav.append(" AND D.EMPLOYEESEQUENCENO IN ("+username+")  ");
				DepAtt_Leav.append(" AND A.FROM_DATE BETWEEN  ");

				if(Month_Sel.equalsIgnoreCase("currmonth")){

					DepAtt_Leav.append(" (DATE_SUB(CONCAT_WS('-', YEAR(CURDATE()),MONTH(CURDATE()),'01'),INTERVAL DAY(CONCAT_WS('-', YEAR(CURDATE()),MONTH(CURDATE()),'01'))-1 DAY))  ");
					DepAtt_Leav.append(" AND  ");
					DepAtt_Leav.append(" (LAST_DAY(CONCAT_WS('-', YEAR(CURDATE()),MONTH(CURDATE()),'01')))  ");
					DepAtt_Leav.append(" GROUP BY A.RID,SELECTED_DATE ORDER BY A.RID,SELECTED_DATE ");

				}else{

					DepAtt_Leav.append(" (DATE_SUB(CONCAT_WS('-', YEAR(CURDATE()),'"+Month_Sel+"','01'),INTERVAL DAY(CONCAT_WS('-', YEAR(CURDATE()),'"+Month_Sel+"','01'))-1 DAY))  ");
					DepAtt_Leav.append(" AND  ");
					DepAtt_Leav.append(" (LAST_DAY(CONCAT_WS('-', YEAR(CURDATE()),'"+Month_Sel+"','01')))  ");
					DepAtt_Leav.append(" GROUP BY A.RID,SELECTED_DATE ORDER BY A.RID,SELECTED_DATE ");

				}


				Months_ATT_1.append(" SELECT DISTINCT IF(MONTH(TRANSACTIONTIME)<10,CONCAT(0,MONTH(TRANSACTIONTIME)),MONTH(TRANSACTIONTIME))MONTHS,MONTHNAME(TRANSACTIONTIME) MONTHNAME ");
				Months_ATT_1.append(" FROM unit_local_db.tbl_reader_log where ");
				Months_ATT_1.append(" MONTH(TRANSACTIONTIME)!=MONTH(CURDATE()) AND ");
				Months_ATT_1.append(" YEAR(TRANSACTIONTIME)=YEAR(CURDATE()) ");
				Months_ATT_1.append(" ORDER BY MONTH(TRANSACTIONTIME) DESC ");


				Res=null;
				try {
					Res=(ResultSet)DataObj.FetchData(DepAtt_Leav.toString(), "DepAtt_Leav_Colors", Res ,conn);
					while(Res.next()){
						DepAtt_Leav_map.put(Res.getString("EMPLOYEEID")+"_"+Res.getString("Day"), Res.getString("Day"));
						DepAtt_Leav_map.put(Res.getString("EMPLOYEEID")+"_FLAG_"+Res.getString("Day"), Res.getString("FLAG"));
						DepAtt_Leav_map.put(Res.getString("EMPLOYEEID")+"_TYP_"+Res.getString("Day"), Res.getString("LEAVE_TYPE"));

					}  

				}catch(Exception Er){
				}
				Res=null;

				try {
					ATT_MONTHS_1.put("currmonth", "Current Month");
					Res=(ResultSet)DataObj.FetchData(Months_ATT_1.toString(), "EMPLOYEE_ATTENDANCELIST", Res ,conn);
					while(Res.next()){

						ATT_MONTHS_1.put(Res.getString(1), Res.getString(2));

					}  

				}catch(Exception Er){
				}
			}

			String CallYear=null,CallMonth=null;
			Res=null;
			try {
				if(Month_Sel.equalsIgnoreCase("currmonth")){
					//Res=(ResultSet)DataObj.FetchData("select EXTRACT(DAY FROM now()) from dual ", "Last Date OF Given Month", Res ,conn);
					Res=(ResultSet)DataObj.FetchData(" SELECT trim(EXTRACT(DAY FROM  LAST_DAY(now()) )),EXTRACT(YEAR FROM  now()) ,EXTRACT(MONTH FROM  now()) from dual", "Last Date OF Given Month", Res ,conn);
				}else{
					Res=(ResultSet)DataObj.FetchData("select date_format(LAST_DAY(date_format(concat(EXTRACT(YEAR FROM LAST_DAY(now())),'-',"+Month_Sel+",'-',01),'%Y-%m-%d')),'%d'),EXTRACT(YEAR FROM  now()) ,'"+Month_Sel+"' ", "Last Date OF Given Month", Res ,conn);
				}
				while(Res.next()){
					call_month=Res.getInt(1);
					CallYear=Res.getString(2);
					CallMonth=Res.getString(3);
				}  
			}catch(Exception Er){
				//System.out.println("Last Day Of Given Month Exception At Er::"+Er);
			}
			// =================================================Holidays List
			StringBuffer HL_LV_Q=new StringBuffer();

			HL_LV_Q.append(" select BUSINESSUNITID,HOLIDAYDATE,'HL',DATE_FORMAT(HOLIDAYDATE,'%e'),COMMENTS from HCLHRM_PROD.TBL_HOLIDAYS ");
			HL_LV_Q.append(" where DATE_FORMAT(HOLIDAYDATE,'%m')="+CallMonth+" and DATE_FORMAT(HOLIDAYDATE,'%Y')="+CallYear+" "); 
			Res=null;

			try {
				//ATT_MONTHS_1.put("currmonth", "Current Month");
				Res=(ResultSet)DataObj.FetchData(HL_LV_Q.toString(), "DepAtt_Holidays", Res ,conn);
				while(Res.next()){


					int dayaaa=Res.getString(4).toString().length();

					if(dayaaa==1){

						DepAtt_Leav_map_HL.put("HL$0"+Res.getString(4).toString()+"$"+Res.getString("BUSINESSUNITID").toString(), Res.getString("COMMENTS").toString());

					}else{
						DepAtt_Leav_map_HL.put("HL$"+Res.getString(4).toString()+"$"+Res.getString("BUSINESSUNITID").toString(), Res.getString("COMMENTS").toString());
					}


					//DepAtt_Leav_map_HL.put("HL$"+Res.getString(4).toString()+"$"+Res.getString("BUSINESSUNITID").toString(), Res.getString("COMMENTS").toString());
				}  

			}catch(Exception Er){
				//System.out.println(" Get Months Exception At Er::"+Er);
			}




			// Public Holiday List
			Emp_DOJ.append(" SELECT A.EMPLOYEESEQUENCENO ID,A.CALLNAME NAME,  ");
			String DummyYear="0000-00-00";
			// call_month=2;
			for(int i=1;i<=call_month;i++){


				String k="0";
				if(CallMonth.length()==1){

					CallMonth="0".concat(CallMonth); 
				}
				if(i<10){

					k="0".concat(""+i+"");
				}else{
					k=""+i+"";
				}


				DummyYear=CallYear+"-"+CallMonth+"-"+k;
				if(i!=call_month){


					Doj22.put(DummyYear, DummyYear);

					//Doj22.put("DAY"+i+"", "DAY"+i+"");

					// Emp_DOJ.append(" CONCAT(FINDAY"+i+",'-',LOUTDAY"+i+")DAY"+i+", IF(D.DAY"+i+"<'08.50','red',' ') DAY"+i+"CL , ");
					//if(DATE_FORMAT('2017-03-25','%Y-%m-%d')<=DATE_FORMAT(now(),'%Y-%m-%d'),'A','B')

					Emp_DOJ.append(" IF(FINDAY"+i+"='0.00' AND LOUTDAY"+i+"='0.00',if(DATE_FORMAT('"+DummyYear+"','%Y-%m-%d')<=DATE_FORMAT(now(),'%Y-%m-%d'),'A','--'),if(DATE_FORMAT('"+DummyYear+"','%Y-%m-%d')<=DATE_FORMAT(now(),'%Y-%m-%d'),'P','--')) DAY"+i+", IF(FINDAY"+i+">'09.11' OR LOUTDAY"+i+"<'18.00', if(DATE_FORMAT('"+DummyYear+"','%Y-%m-%d')<=DATE_FORMAT(now(),'%Y-%m-%d'),'red',' '),' ') DAY"+i+"CL , ");
					//  Emp_DOJ.append(" IF(FINDAY"+i+"='0.00' && LOUTDAY"+i+"='0.00','A','P') DAY"+i+", IF(D.DAY"+i+"<'08.50','red',' ') DAY"+i+"CL , ");

					Emp_DOJ.append("if(DAYNAME('"+DummyYear+"')='Sunday','WOFF','WDAY') HLWD_TYPE_"+i+" , concat('HL','$',IF(length('"+i+"')=1,concat('0','"+i+"'), '"+i+"'),'$', A.companyid) HL$"+i+" , FINDAY"+i+" IN"+i+",LOUTDAY"+i+" OUT"+i+",'"+DummyYear+"' ATTDATE"+i+", ");
				}else if(i==call_month){

					Doj22.put(DummyYear, DummyYear);
					// Doj22.put("DAY"+i+"", "DAY"+i+"");

					Emp_DOJ.append(" IF(FINDAY"+i+"='0.00' AND LOUTDAY"+i+"='0.00',if(DATE_FORMAT('"+DummyYear+"','%Y-%m-%d')<=DATE_FORMAT(now(),'%Y-%m-%d'),'A','--'),if(DATE_FORMAT('"+DummyYear+"','%Y-%m-%d')<=DATE_FORMAT(now(),'%Y-%m-%d'),'P','--')) DAY"+i+", IF(FINDAY"+i+">'09.11' OR LOUTDAY"+i+"<'18.00', if(DATE_FORMAT('"+DummyYear+"','%Y-%m-%d')<=DATE_FORMAT(now(),'%Y-%m-%d'),'red',' '),' ') DAY"+i+"CL ");
					//**OLD RIGHT ***//	 Emp_DOJ.append(" IF(FINDAY"+i+"='0.00' AND LOUTDAY"+i+"='0.00','A','P') DAY"+i+", IF(FINDAY"+i+">'09.11' OR LOUTDAY"+i+"<'18.00' , if(DATE_FORMAT('"+DummyYear+"','%Y-%m-%d')<=DATE_FORMAT(now(),'%Y-%m-%d'),'red',' '),' ') DAY"+i+"CL ");
					// Emp_DOJ.append(" CONCAT(FINDAY"+i+",'-',LOUTDAY"+i+")DAY"+i+",IF(D.DAY"+i+"<'08.50','red',' ') DAY"+i+"CL ");

					Emp_DOJ.append(", if(DAYNAME('"+DummyYear+"')='Sunday','WOFF','WDAY') HLWD_TYPE_"+i+" , concat('HL','$',IF(length('"+i+"')=1,concat('0','"+i+"'), '"+i+"'),'$', A.companyid) HL$"+i+" ,FINDAY"+i+" IN"+i+" , LOUTDAY"+i+" OUT"+i+" ,'"+DummyYear+"' ATTDATE"+i+" ");

				}
			}

			Emp_DOJ.append(" FROM ");
			Emp_DOJ.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS B ON A.EMPLOYEEID=B.EMPLOYEEID  ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_ATTENDANCE_FIN_LOUT_SUMMARY C ON A.EMPLOYEEID=C.EMPLOYEEID  ");
			Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_ATTENDANCE_HOURS_SUMMARY D ON A.EMPLOYEEID=D.EMPLOYEEID  ");
			Emp_DOJ.append(" WHERE ");

			if(Month_Sel.equalsIgnoreCase("currmonth")){
				Emp_DOJ.append(" A.STATUS=1001   AND C.MONTH=EXTRACT(MONTH FROM LAST_DAY(now())) AND C.YEAR=EXTRACT(YEAR FROM LAST_DAY(now())) AND D.MONTH=EXTRACT(MONTH FROM LAST_DAY(now())) AND D.YEAR=EXTRACT(YEAR FROM LAST_DAY(now())) ");
			}else{
				Emp_DOJ.append(" A.STATUS=1001   AND C.MONTH="+Month_Sel+" AND C.YEAR=EXTRACT(YEAR FROM LAST_DAY(now())) AND D.MONTH="+Month_Sel+" AND D.YEAR=EXTRACT(YEAR FROM LAST_DAY(now()))");

			}
			Emp_DOJ.append(" AND A.companyid="+ATT_MOD_F_HR_BU+" ");
			// Emp_DOJ.append(" AND A.companyid=(SELECT companyid FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN ("+username+")) ");

			request.setAttribute("TITLES_TITLES", Doj22.toString());

		}
		/// HR Attendance Approval -------------------------------------------------->
		Map empatt_Data=new HashMap();
		ArrayList MyHeaderlist=new ArrayList();
		/////////////////////*************************** PAYPERIOD *********************************************
		if(Routing.equalsIgnoreCase("Dept_att_HR") && HR_ATT_CAL_MODE!=null && HR_ATT_CAL_MODE.equalsIgnoreCase("PAYPERIOD")){
			Doj22 = new JSONObject();
			values = new JSONArray();
			StringBuffer TxnDates=new StringBuffer();
			StringBuffer Txn_Bulk_Dats=new StringBuffer();
			if(Month_Sel.equalsIgnoreCase("currmonth")){  // this Is Sub Data
				TxnDates.append(" SELECT distinct A.FROMDATE,TODATE FROM hcladm_prod.tbl_transaction_dates A  ");
				TxnDates.append(" where  A.BUSINESSUNITID in("+ATT_MOD_F_HR_BU+") ");
				//TxnDates.append(" where employeesequenceno in("+username+")) and ");
				TxnDates.append(" and  A.TRANSACTIONTYPEID=1 and A.TRANSACTIONDURATION=date_format(now(),'%Y%m') ");
			}else{
				String Da_Month="0";
				if(Month_Sel.length()==1 && Integer.parseInt(Month_Sel)<10 ){
					Da_Month=Da_Month.concat(Month_Sel);
				}else{
					Da_Month=Month_Sel;
				}
				TxnDates.append(" SELECT distinct A.FROMDATE,TODATE FROM hcladm_prod.tbl_transaction_dates A ");
				TxnDates.append(" where  A.BUSINESSUNITID in("+ATT_MOD_F_HR_BU+") and A.TRANSACTIONTYPEID=1 and A.TRANSACTIONDURATION=concat(date_format(now(),'%Y'),'"+Da_Month+"') ");
			}
			String Txn_FromDate="00-00-0000";
			String Txn_Todate="00-00-0000";
			Res=null;
			try {
				//ATT_MONTHS_1.put("currmonth", "Current Month");
				Res=(ResultSet)DataObj.FetchData(TxnDates.toString(), "DepAtt_Leav_Colors", Res ,conn);
				if(Res.next()){
					Txn_FromDate=Res.getString("FROMDATE");
					Txn_Todate=Res.getString("TODATE");
				}  
			}catch(Exception Er){
				System.out.println(" Get Txn FatesAt Er::"+Er);
			}
			//Txn_FromDate="2017-06-01";
			//Txn_Todate="2017-06-26";

			if(Routing_type==null){
				Res=null;
				try {
					ATT_MONTHS_1.put("currmonth", "Current Month");
					Res=(ResultSet)DataObj.FetchData(Months_ATT_1.toString(), "EMPLOYEE_ATTENDANCELIST", Res ,conn);
					while(Res.next()){
						ATT_MONTHS_1.put(Res.getString(1), Res.getString(2));
					}  
				}catch(Exception Er){
				}
			}
			Txn_Bulk_Dats.append(" select selected_date,date_format(selected_date,'%d'),date_format(selected_date,'%m'),date_format(selected_date,'%Y') from  ");
			Txn_Bulk_Dats.append(" (select adddate('1970-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) selected_date from ");
			Txn_Bulk_Dats.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0, ");
			Txn_Bulk_Dats.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, ");
			Txn_Bulk_Dats.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2, ");
			Txn_Bulk_Dats.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3, ");
			Txn_Bulk_Dats.append(" (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v ");
			Txn_Bulk_Dats.append(" where selected_date between '"+Txn_FromDate+"' and '"+Txn_Todate+"'  ");
			MyHeaderlist=new ArrayList();
			try {
				Res=(ResultSet)DataObj.FetchData(Txn_Bulk_Dats.toString(), "Txn_Bulk_Dats", Res ,conn);
				while(Res.next()){
					MyHeaderlist.add(Res.getString("selected_date"));
					Doj22.put(Res.getString("selected_date"), Res.getString("selected_date"));
				}  
			}catch(Exception Er){
				System.out.println(" Txn_Bulk_Dats Get Txn FatesAt Er::"+Er);
			}

			Emp_DOJ.append(" SELECT distinct a.EMPLOYEEID,b.callname ");
			Emp_DOJ.append(" FROM hclhrm_prod_others.tbl_employee_iot_dup a, ");
			Emp_DOJ.append(" hclhrm_prod.tbl_employee_primary b where a.employeeid=b.employeesequenceno ");
			Emp_DOJ.append(" and b.companyid="+ATT_MOD_F_HR_BU+" and a.TRANSACTIONDATE between '"+Txn_FromDate+"' and '"+Txn_Todate+"' ");


			StringBuffer Emp_DOJ_Att_hr=new StringBuffer(); 
			Emp_DOJ_Att_hr.append(" SELECT a.EMPLOYEEID,b.callname,a.TRANSACTIONDATE, a.FIN, a.LOUT,a.NET,a.DAYSTATUS,a.STATUSID, ifnull(a.REQ_STATUS,0) rqstatus,  ");
			Emp_DOJ_Att_hr.append(" ifnull(a.STATUS_TYPE,0) STATUSTYPE  FROM hclhrm_prod_others.tbl_employee_iot_dup a, ");
			Emp_DOJ_Att_hr.append(" hclhrm_prod.tbl_employee_primary b where a.employeeid=b.employeesequenceno ");
			Emp_DOJ_Att_hr.append(" and b.companyid="+ATT_MOD_F_HR_BU+" and a.TRANSACTIONDATE between '"+Txn_FromDate+"' and '"+Txn_Todate+"' ");



			empatt_Data=new HashMap();
			try {
				Res=(ResultSet)DataObj.FetchData(Emp_DOJ_Att_hr.toString(), "Emp_DOJ_Att_hr", Res ,conn);
				while(Res.next()){

					empatt_Data.put(Res.getString("EMPLOYEEID")+"_NAME", Res.getString("callname"));
					empatt_Data.put(Res.getString("EMPLOYEEID")+"_FIN_"+Res.getString("TRANSACTIONDATE"), Res.getString("FIN"));
					empatt_Data.put(Res.getString("EMPLOYEEID")+"_LOUT_"+Res.getString("TRANSACTIONDATE"), Res.getString("LOUT"));
					empatt_Data.put(Res.getString("EMPLOYEEID")+"_NET_"+Res.getString("TRANSACTIONDATE"), Res.getString("NET"));

					empatt_Data.put(Res.getString("EMPLOYEEID")+"_DAYSTATUS_"+Res.getString("TRANSACTIONDATE"), Res.getString("DAYSTATUS"));
					empatt_Data.put(Res.getString("EMPLOYEEID")+"_STATUSID_"+Res.getString("TRANSACTIONDATE"), Res.getString("STATUSID"));
					empatt_Data.put(Res.getString("EMPLOYEEID")+"_REQ_STATUS_"+Res.getString("TRANSACTIONDATE"), Res.getString("rqstatus"));
					empatt_Data.put(Res.getString("EMPLOYEEID")+"_REQ_STATUS_TY_"+Res.getString("TRANSACTIONDATE"), Res.getString("STATUSTYPE"));


				}  
			}catch(Exception Er){
				System.out.println(" Get Txn FatesAt Er::"+Er);
			}



			///////////////////////////////////////////////////////////////////////////////////////////// For Second Data Columns//////////////////

		}///////////////////////////////////////////////////////////////////////////////////////////Attendance Bu Period

		/////////////////////*************************** PAYPERIOD *********************************************
		if(Routing.equalsIgnoreCase("ATTENDANCE_BIO")){

			//url : "Attendance?Routing=ATTENDANCE_BIO_DATES&ATT_FLAG='DATES'&Month_FROM="+Month_FROM+" & Month_TO="+Month_TO+" ",
			String From=request.getParameter("username");  
			String to=request.getParameter("pwd"); 
			String ATT_FLAG=request.getParameter("ATT_FLAG");
			String Month_FROM=request.getParameter("Month_FROM");
			String Month_TO=request.getParameter("Month_TO");
			Month=request.getParameter("Month");
			if(Month==null){
				Month="currmonth";
			}
			if(Month!=null && Month.equalsIgnoreCase("currmonth")  ){

				if(ATT_FLAG!=null && ATT_FLAG.equalsIgnoreCase("DATES")){

					Emp_DOJ.append(" SELECT DATE_FORMAT(TRANSACTIONDATE,'%d-%m-%Y') DAY,FIN,LOUT FOUT,NET PERDAY,IF(DAYNAME(TRANSACTIONDATE)='SUNDAY','WOFF','WDAY')DAYTYPE,");
					Emp_DOJ.append("IF(NET<'08:50',IF(TRANSACTIONDATE BETWEEN (SELECT MAX(TODATE) FROM hcladm_prod.tbl_transaction_dates ");
					Emp_DOJ.append(" WHERE BUSINESSUNITID=(select companyid from hclhrm_prod.tbl_employee_primary where employeesequenceno in("+username+"))) AND SUBDATE(CURDATE(),1),1,0),0)STATUS ");
					Emp_DOJ.append(",TRANSACTIONDATE DAYSP ");
					Emp_DOJ.append(" FROM ");
					Emp_DOJ.append(" HCLHRM_PROD_OTHERS.TBL_EMPLOYEE_IOT ");
					Emp_DOJ.append(" WHERE ");
					Emp_DOJ.append(" TRANSACTIONDATE BETWEEN '"+Month_FROM+"' AND '"+Month_TO+"' AND EMPLOYEEID="+username);

				}else{

					Emp_DOJ.append("SELECT    ");
					Emp_DOJ.append("DATE_FORMAT(SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime, ' ', 1), ' ', -1),'%d-%m-%Y') DAY,   ");
					Emp_DOJ.append("SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime, ' ', 2), ' ', -1) FIN,   ");
					Emp_DOJ.append("MAX(CASE WHEN  SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime,' ', 2), ' ', -1) END) FOUT,   ");
					Emp_DOJ.append("SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN  SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime, ' ', 2), ' ', 2), ' ', -1)),':',2)PERDAY,   ");
					Emp_DOJ.append("IF(DAYNAME(transactiontime)='SUNDAY','WOFF','WDAY') DAYTYPE,   ");
					Emp_DOJ.append(" IF(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN  SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime, ' ', 2), ' ', 2), ' ', -1)),':',2)<'08:50',1,0) STATUS ");
					Emp_DOJ.append(" , DATE_FORMAT(SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime, ' ', 1), ' ', -1),'%Y-%m-%d') DAYSP ");
					Emp_DOJ.append("FROM   ");
					Emp_DOJ.append("unit_local_db.tbl_reader_log A   ");
					Emp_DOJ.append("LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY B ON A.EMPLOYEEID=B.EMPLOYEESEQUENCENO   ");
					Emp_DOJ.append("LEFT JOIN HCLHRM_PROD.TBL_HOLIDAYS C ON B.COMPANYID=C.BUSINESSUNITID   ");
					Emp_DOJ.append("WHERE TRANSACTIONTIME  BETWEEN date(CURDATE()-(day(CURDATE())-1)) AND now() AND A.EMPLOYEEID="+username+" GROUP BY DAY   ");
					Emp_DOJ.append("UNION ALL   ");
					Emp_DOJ.append("select  DATE_FORMAT(DATE,'%d-%m-%Y')DAY,'00:00:00' AS FIN,'00:00:00' AS LOUT,'00:00' AS PERDAY,IF(DAYNAME(DATE)='SUNDAY','WOFF','WDAY') DAYTYPE ,IF(DAYNAME(DATE)='SUNDAY','','0') STATUS,DATE_FORMAT(DATE,'%Y-%m-%d') DAYSP from (   ");
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
			}else{
				// Based on Month

				if(ATT_FLAG!=null && ATT_FLAG.equalsIgnoreCase("DATES")){

					Emp_DOJ.append(" SELECT DATE_FORMAT(TRANSACTIONDATE,'%d-%m-%Y') DAY,FIN,LOUT FOUT,NET PERDAY,IF(DAYNAME(TRANSACTIONDATE)='SUNDAY','WOFF','WDAY')DAYTYPE,");
					Emp_DOJ.append("IF(NET<'08:50',IF(TRANSACTIONDATE BETWEEN (SELECT MAX(TODATE) FROM hcladm_prod.tbl_transaction_dates ");
					Emp_DOJ.append(" WHERE BUSINESSUNITID=(select companyid from hclhrm_prod.tbl_employee_primary where employeesequenceno in("+username+"))) AND SUBDATE(CURDATE(),1),1,0),0)STATUS ");
					Emp_DOJ.append(",TRANSACTIONDATE DAYSP ");
					Emp_DOJ.append(" FROM ");
					Emp_DOJ.append(" HCLHRM_PROD_OTHERS.TBL_EMPLOYEE_IOT ");
					Emp_DOJ.append(" WHERE ");
					Emp_DOJ.append(" TRANSACTIONDATE BETWEEN '"+Month_FROM+"' AND '"+Month_TO+"' AND EMPLOYEEID="+username);

				}else{


					Emp_DOJ.append(" SELECT  ");
					Emp_DOJ.append(" DATE_FORMAT(SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime, ' ', 1), ' ', -1),'%d-%m-%Y')DAY, ");
					Emp_DOJ.append(" SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime, ' ', 2), ' ', -1)FIN, ");
					Emp_DOJ.append(" MAX(CASE WHEN  SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime,' ', 2), ' ', -1)END)FOUT, ");
					Emp_DOJ.append(" SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN  SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime, ' ', 2), ' ', 2), ' ', -1)),':',2)PERDAY, ");
					Emp_DOJ.append(" IF(DAYNAME(transactiontime)='SUNDAY','WOFF','WDAY')DAYTYPE ");
					Emp_DOJ.append(" FROM ");
					Emp_DOJ.append(" unit_local_db.tbl_reader_log A ");
					Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY B ON A.EMPLOYEEID=B.EMPLOYEESEQUENCENO ");
					Emp_DOJ.append(" WHERE TRANSACTIONTIME  BETWEEN '2017-"+Month+"-01' AND '2017-"+Month+"-31' AND A.EMPLOYEEID="+username+" GROUP BY DAY ");
					Emp_DOJ.append(" UNION ALL ");
					Emp_DOJ.append(" select  DATE_FORMAT(DATE,'%d-%m-%Y')DAY,'00:00:00' AS FIN,'00:00:00' AS LOUT,'00:00' AS PERDAY,IF(DAYNAME(DATE)='SUNDAY','WOFF','WDAY')DAYTYPE ");
					Emp_DOJ.append(" from ( ");
					Emp_DOJ.append(" select date_add(CONCAT(2017,'-',"+Month+",'-01'), INTERVAL n5.num*10000+n4.num*1000+n3.num*100+n2.num*10+n1.num DAY ) as date from ");
					Emp_DOJ.append(" (select 0 as num ");
					Emp_DOJ.append("    union all select 1 ");
					Emp_DOJ.append("    union all select 2 ");
					Emp_DOJ.append("    union all select 3 ");
					Emp_DOJ.append("    union all select 4 ");
					Emp_DOJ.append("    union all select 5 ");
					Emp_DOJ.append("    union all select 6 ");
					Emp_DOJ.append("    union all select 7 ");
					Emp_DOJ.append("    union all select 8 ");
					Emp_DOJ.append("    union all select 9) n1, ");
					Emp_DOJ.append(" (select 0 as num ");
					Emp_DOJ.append("    union all select 1 ");
					Emp_DOJ.append("    union all select 2 ");
					Emp_DOJ.append("    union all select 3 ");
					Emp_DOJ.append("    union all select 4 ");
					Emp_DOJ.append("    union all select 5 ");
					Emp_DOJ.append("    union all select 6 ");
					Emp_DOJ.append("    union all select 7 ");
					Emp_DOJ.append("    union all select 8 ");
					Emp_DOJ.append("    union all select 9) n2, ");
					Emp_DOJ.append(" (select 0 as num ");
					Emp_DOJ.append("    union all select 1 ");
					Emp_DOJ.append("    union all select 2 ");
					Emp_DOJ.append("    union all select 3 ");
					Emp_DOJ.append("    union all select 4 ");
					Emp_DOJ.append("    union all select 5 ");
					Emp_DOJ.append("    union all select 6 ");
					Emp_DOJ.append("    union all select 7 ");
					Emp_DOJ.append("    union all select 8 ");
					Emp_DOJ.append("    union all select 9) n3, ");
					Emp_DOJ.append(" (select 0 as num ");
					Emp_DOJ.append("    union all select 1 ");
					Emp_DOJ.append("    union all select 2 ");
					Emp_DOJ.append("    union all select 3 ");
					Emp_DOJ.append("    union all select 4 ");
					Emp_DOJ.append("    union all select 5 ");
					Emp_DOJ.append("    union all select 6 ");
					Emp_DOJ.append("    union all select 7 ");
					Emp_DOJ.append("    union all select 8 ");
					Emp_DOJ.append("    union all select 9) n4, ");
					Emp_DOJ.append(" (select 0 as num ");
					Emp_DOJ.append("    union all select 1 ");
					Emp_DOJ.append("    union all select 2 ");
					Emp_DOJ.append("    union all select 3 ");
					Emp_DOJ.append("    union all select 4 ");
					Emp_DOJ.append("    union all select 5 ");
					Emp_DOJ.append("    union all select 6 ");
					Emp_DOJ.append("    union all select 7 ");
					Emp_DOJ.append("    union all select 8 ");
					Emp_DOJ.append("    union all select 9) n5 ");
					Emp_DOJ.append(" ) a ");
					Emp_DOJ.append(" where date BETWEEN DATE_FORMAT(CONCAT(2017,'-',"+Month+",'-00'),'%Y-%m-%d') and '2017-"+Month+"-31' ");
					Emp_DOJ.append(" and date not in (select DATE_FORMAT(TRANSACTIONTIME,'%Y-%m-%d') from unit_local_db.tbl_reader_log where employeeid="+username+" and transactiontime BETWEEN '2017-"+Month+"-01' AND '2017-"+Month+"-31') ");
					Emp_DOJ.append(" ORDER BY DAY ");

				}
			}





			HLDAYLIST.append("SELECT DISTINCT DATE_FORMAT(A.HOLIDAYDATE,'%d-%m-%Y') DAY,");
			HLDAYLIST.append(" TRIM(CONCAT(A.COMMENTS,'  ',IF(A.HOLIDAYTYPEID=0,'','(OPTINAL HLDAY)')))OCCASION");
			HLDAYLIST.append(" FROM");
			HLDAYLIST.append(" hclhrm_prod.tbl_holidays A");
			HLDAYLIST.append(" LEFT JOIN HCLHRM_PROD.TBL_HOLIDAY_TYPE B ON A.HOLIDAYTYPEID=B.HOLIDAYTYPEID");
			HLDAYLIST.append(" WHERE  year(A.HOLIDAYDATE)=year(now()) ");


			Months_ATT.append(" SELECT DISTINCT IF(MONTH(TRANSACTIONTIME)<10,CONCAT(0,MONTH(TRANSACTIONTIME)),MONTH(TRANSACTIONTIME))MONTHS,MONTHNAME(TRANSACTIONTIME) MONTHNAME ");
			Months_ATT.append(" FROM unit_local_db.tbl_reader_log where ");
			Months_ATT.append(" MONTH(TRANSACTIONTIME)!=MONTH(CURDATE()) AND ");
			Months_ATT.append(" employeeid="+username+" AND YEAR(TRANSACTIONTIME)=YEAR(CURDATE()) ");
			Months_ATT.append(" ORDER BY MONTH(TRANSACTIONTIME) DESC ");



			Res=null;

			try {
				Res=(ResultSet)DataObj.FetchData(HLDAYLIST.toString(), "Emp_DOJ", Res ,conn);
				while(Res.next()){

					hlday.put(Res.getString(1), Res.getString(2));

				}  
			}catch(Exception Er){
				System.out.println("Exception At 55 Er::"+Er);
			}

			Res=null;

			try {
				Res=(ResultSet)DataObj.FetchData("select date_format(REQ_DATE,'%d-%m-%Y'),if(FLAG='P','PROCESSED',if(FLAG='A','APPROVED','REJECT')) statusflg from hclhrm_prod_others.tbl_emp_attn_req where employeeid="+username+" and REQ_TYPE='AR' ", "EMPLOYEE_ATTENDANCELIST", Res ,conn);
				while(Res.next()){

					Att_Req.put(Res.getString(1), Res.getString(2));

				}  

			}catch(Exception Er){
				System.out.println("Exception At 44 Er::"+Er);
			}



			Res=null;

			try {
				ATT_MONTHS.put("currmonth", "Current Month");
				Res=(ResultSet)DataObj.FetchData(Months_ATT.toString(), "EMPLOYEE_ATTENDANCELIST", Res ,conn);
				while(Res.next()){

					ATT_MONTHS.put(Res.getString(1), Res.getString(2));

				}  

			}catch(Exception Er){
				System.out.println("Exception At 333 Er::"+Er);
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
				ps=conn.prepareStatement("INSERT INTO hclhrm_prod_others.tbl_emp_attn_req (EMPLOYEEID,SUBJECT,REQ_DATE,MESSAGE,RANDOMID,FROM_DATE,TO_DATE,TOTA_HOURS,TO_EMAIL,CC_EMAIL) VALUES (?,?,?,?,?,?,?,?,?,?)");

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

		//***************************************************  Leave Req Process
		Res=null;
		ps=null;
		Res=null;
		rsmd=null;

		if(Routing.equalsIgnoreCase("ManagerAppr_req")  && User_Auth==1){
			ps=null;
			try{	
				conn.setAutoCommit(false);
				System.out.println("Connection Established....!");
				ps=conn.prepareStatement("update hclhrm_prod_others.tbl_emp_attn_req set FLAG=? ,MAIL_STATUS='N' where EMPLOYEEID=? and RID=?   ");

				//String EMPID=null,RID=null,TB_STATUS=null;

				ps.setString(1,TB_STATUS);
				ps.setString(2,subject);

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
		//**************************************************** Leave Req Process End

		if(User_Auth==1 && !Routing.equalsIgnoreCase("Att_Request") ){

			if(Routing.equalsIgnoreCase("NewJoine")){
				_SUCCESS_PAGE=bundle_info.getString("HHCL_DESK_DIVERT_NEWJOINEE");
			}else if(Routing.equalsIgnoreCase("HOLIDAYS")){

				_SUCCESS_PAGE=bundle_info.getString("HHCL_DESK_DIVERT_HOLIDAYS");
			}else if(Routing.equalsIgnoreCase("BIRTHADYS")){

				_SUCCESS_PAGE=bundle_info.getString("HHCL_DESK_DIVERT_BIRTHDAYS");
			}
			else if(Routing.equalsIgnoreCase("ATTENDANCE_BIO")){

				_SUCCESS_PAGE=bundle_info.getString("HHCL_DESK_DIVERT_ATTENDANCE");
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

					}else if(Routing.equalsIgnoreCase("ManagerAppr")){

						// ID,NAME,DEPT,SUBJECT,DURATION,DAYS,LEAVE_TYPE,Manager_Status



						Doj.put("ID" , Res.getString(1)); 
						Doj.put("NAME" , Res.getString(2));
						Doj.put("DEPT" , Res.getString(3));
						Doj.put("SUBJECT" , Res.getString(4));
						Doj.put("DURATION" , Res.getString(5));
						Doj.put("DAYS" , Res.getString(6));
						Doj.put("LEAVE_TYPE" , Res.getString(7));
						Doj.put("Manager_Status" , Res.getString(8));


						Doj.put("MESSAGE" , Res.getString("MESSAGE"));

						Doj.put("RID" , Res.getString(9));   /// Update request id


						Doj.put("FLAG" , Res.getString(10));

						if(Res.getString(10).equalsIgnoreCase("P")){

							Doj.put("B1" , ' ');
							Doj.put("B2" , ' ');

							Doj.put("B1T" , "Approve");
							Doj.put("B2T" , "Reject");


						}else if(Res.getString(10).equalsIgnoreCase("A")){

							Doj.put("B1" , " ");
							Doj.put("B2" , "none");

							Doj.put("B1T" , "Cancel");
							Doj.put("B2T" , "Reject");



						}/*else if(Res.getString(10).equalsIgnoreCase("R")){

							Doj.put("B1" , ' ');
							Doj.put("B2" , "Disabled");

						}*/
						dobcnt1++;

					}

					else if(Routing.equalsIgnoreCase("ManagerAppr_att")){

						// ID,NAME,DEPT,SUBJECT,DURATION,DAYS,LEAVE_TYPE,Manager_Status



						/*Emp_DOJ.append(" SELECT A.EMPLOYEESEQUENCENO ID,A.CALLNAME NAME,E.NAME DEPT,B.SUBJECT,  ");
	Emp_DOJ.append(" IF(B.REQ_TYPE='LR', CONCAT(DATE_FORMAT(B.FROM_DATE,'%d-%m-%Y'),'<--->',DATE_FORMAT(B.TO_DATE,'%d-%m-%Y')),CONCAT(B.FROM_DATE,'<--->',B.TO_DATE)) DURATION, ");
	Emp_DOJ.append(" IF(B.FLAG='A','Approved',IF(B.FLAG='R','Rejected','Pending')) Manager_Status,B.RID,B.FLAG,B.MESSAGE, ");
	Emp_DOJ.append(" B.TOTA_HOURS DAYS,REQ_DATE ");
	Emp_DOJ.append(" FROM ");
	Emp_DOJ.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
	Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD_OTHERS.TBL_EMP_ATTN_REQ B ON A.EMPLOYEESEQUENCENO=B.EMPLOYEEID ");
	Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS D ON A.EMPLOYEEID=D.EMPLOYEEID ");
	Emp_DOJ.append(" LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT E ON D.DEPARTMENTID=E.DEPARTMENTID ");
	Emp_DOJ.append(" WHERE B.RID>73 AND  B.REQ_TYPE='AR' and  B.EMPLOYEEID!="+username+"  and  B.FLAG in('P') AND B.EMPLOYEEID is not null ");
	Emp_DOJ.append(" AND LOWER(B.TO_EMAIL) LIKE  '%"+User_obj.get("emp_mailid")+"%' ");
						 */

						Doj.put("ID" , Res.getString(1)); 
						Doj.put("NAME" , Res.getString(2));
						Doj.put("DEPT" , Res.getString(3));
						Doj.put("SUBJECT" , Res.getString(4));
						Doj.put("DURATION" , Res.getString(5));
						Doj.put("DAYS" , Res.getString("NET_HOURS"));

						Doj.put("REQ_DATE" , Res.getString("REQ_DATE"));

						Doj.put("LEAVE_TYPE" , Res.getString("SUBJECT"));
						Doj.put("Manager_Status" , Res.getString("Manager_Status"));


						Doj.put("MESSAGE" , Res.getString("MESSAGE"));

						Doj.put("RID" , Res.getString("RID"));   /// Update request id


						Doj.put("FLAG" , Res.getString("FLAG"));

						if(Res.getString("FLAG").equalsIgnoreCase("P")){

							Doj.put("B1" , ' ');
							Doj.put("B2" , ' ');

							Doj.put("B1T" , "Approve");
							Doj.put("B2T" , "Reject");


						}else if(Res.getString("FLAG").equalsIgnoreCase("A")){

							Doj.put("B1" , " ");
							Doj.put("B2" , "none");

							Doj.put("B1T" , "Cancel");
							Doj.put("B2T" , "Reject");

							dobcnt1++;

						}/*else if(Res.getString(10).equalsIgnoreCase("R")){

							Doj.put("B1" , ' ');
							Doj.put("B2" , "Disabled");

						}*/
						dobcnt1++;

					}else if(Routing.equalsIgnoreCase("BIRTHADYS")){



						Doj.put("EVENTDATE" , Res.getString(1)); 
						Doj.put("BUNAME" , Res.getString(2));
						Doj.put("EVENT" , Res.getString(3));
						Doj.put("FLAG" , Res.getString(4));
						Doj.put("HOLIDAYTYPE" , Res.getString(5));


					}else if( Routing.equalsIgnoreCase("Dept_att_HR") && HR_ATT_CAL_MODE!=null && HR_ATT_CAL_MODE.equalsIgnoreCase("MONTH") ){



						System.out.println("Attendance Formation2::"+Emp_DOJ.toString());

						String Emp_id=Res.getString("ID").toString();
						String Emp_Name=Res.getString("NAME").toString();

						//Doj.put("ID" ,  Emp_id);
						//Doj.put("NAME" ,  Emp_Name);

						//Doj.put("NAME" ,  Res.getString("NAME").toString());
						String color=" ";



						System.out.println("Before while loop");
						String HL_MAP="WDAY";

						Doj.put("ID" ,  Emp_id);
						Doj.put("NAME" ,  Emp_Name);


						JSONArray values_dumm = new JSONArray();

						for(int i=1;i<=call_month;i++){


							JSONObject Doj1=new JSONObject();



							color=" ";
							String WeekDayType=Res.getString("HLWD_TYPE_"+i).toString();

							//	System.out.println("WeekDayType::"+WeekDayType);

							String HL_WEEKDAY=Res.getString("HL$"+i).toString();

							//System.out.println("HL_WEEKDAY::"+HL_WEEKDAY);

							Doj1.put("ATTDATE" ,  Res.getString("ATTDATE"+i+""));

							try{
								// System.out.println();
								//HL_MAP=DepAtt_Leav_map_HL.get("HL$1$11").toString();
								HL_MAP=DepAtt_Leav_map_HL.get(""+HL_WEEKDAY+"").toString();
								//  System.out.println(HL_WEEKDAY+"::HL_MAP:::"+HL_MAP);
								if(HL_MAP==null){
									HL_MAP="WDAY";
								}
							}catch(Exception MAPP){
								HL_MAP="WDAY";
								// System.out.println("MAPP:::"+MAPP);

							}
							// System.out.println(WeekDayType+":::"+HL_WEEKDAY+"::Before while loop::"+HL_MAP);

							//System.out.println("IN IF ATT"+DepAtt_Leav_map.get(Res.getString("ID").toString()+"_"+i));

							if(WeekDayType!=null && WeekDayType.equalsIgnoreCase("WDAY") && HL_MAP!=null && HL_MAP.equalsIgnoreCase("WDAY")  ){   // Main IF 

								if(DepAtt_Leav_map.get(Res.getString("ID").toString()+"_"+i)!=null){

									String Leave_Type=DepAtt_Leav_map.get(Res.getString("ID").toString()+"_TYP_"+i).toString();
									String Leave_FLAG=DepAtt_Leav_map.get(Res.getString("ID").toString()+"_FLAG_"+i).toString();

									if(Leave_FLAG.equalsIgnoreCase("A")){
										color="green"; 
									}else if(Leave_FLAG.equalsIgnoreCase("R")){
										color="red"; 
									}else{
										color=" "; 
									}


									Doj1.put("DAY" ,  Leave_Type.concat("#").toString().concat(color));
									// Doj.put("DAY"+i+"" ,  DepAtt_Leav_map.get(Res.getString("ID").toString()+"_TYP_"+i).toString().concat("#green"));

									// Doj.put("DAY"+i+"" ,  DepAtt_Leav_map.get(Res.getString("ID").toString()+"_TYP").toString().concat("#"+Res.getString("DAY"+i+"CL").toString()));

								}else {

									// Emp_DOJ.append(" CONCAT(FINDAY"+i+",'-',LOUTDAY"+i+")DAY"+i+", ");Emp_id
									String MainColor="NA";

									try{

										MainColor=ATT_APP.get(Emp_id+"$"+i).toString();
									}catch(Exception Edf){
										MainColor="NA";
										System.out.println("Exception at Attendance Color::"+Edf);
									}
									if(MainColor!=null && MainColor!="NA" && Res.getString("DAY"+i+"CL").toString().equals("red") ){

										Doj1.put("DAY" ,  Res.getString("DAY"+i+"").toString().concat("#green"));
									}else{


										Doj1.put("DAY" ,  Res.getString("DAY"+i+"").toString().concat("#"+Res.getString("DAY"+i+"CL").toString()));

										Doj1.put("DAY_MODE" ,  Res.getString("IN"+i+"") +"-"+ Res.getString("OUT"+i+"") );

									}

								}
							} else{// Main IF

								String color1="";
								if(WeekDayType.equalsIgnoreCase("WOFF")){
									Doj1.put("DAY" ,  WeekDayType.concat("#").concat(color1));

									Doj1.put("DAY_MODE" ,  "Sunday");

								}else{
									//HL_MAP
									Doj1.put("DAY" ,  "HL".concat("#").concat(color1));

									Doj1.put("DAY_MODE" ,  HL_MAP);

									//Doj.put("DAY"+i+"" ,  HL_MAP.concat("#").concat(color1));
								}
								/*if(HL_MAP!=null && HL_MAP.equalsIgnoreCase("HL") ){
								Doj.put("DAY"+i+"" ,  HL_MAP.concat("#").concat(color));

							}else{
								Doj.put("DAY"+i+"" ,  WeekDayType.concat("#").concat(color));

							}*/




							}// Main Else		
							// Doj.put("DAY"+i+"_CL" ,  Res.getString("DAY"+i+"CL").toString()); 
							//Doj.put("DAY"+i+"_NS" , " ");

							values_dumm.add(Doj1.toString());
						}   /// For loop Ending YESSSSS

						Doj.put("ATTENDANCE",values_dumm.toString());
						/*Doj.put("DAY1" ,  Res.getString("DAY1").toString());
						Doj.put("DAY2" ,  Res.getString("DAY2").toString());
						Doj.put("DAY3" ,  Res.getString("DAY3").toString());
						Doj.put("DAY4" ,  Res.getString("DAY4").toString());
						Doj.put("DAY5" ,  Res.getString("DAY5").toString());
						Doj.put("DAY6" ,  Res.getString("DAY6").toString());
						Doj.put("DAY7" ,  Res.getString("DAY7").toString());
						Doj.put("DAY8" ,  Res.getString("DAY8").toString());
						Doj.put("DAY9" ,  Res.getString("DAY9").toString());
						Doj.put("DAY10" ,  Res.getString("DAY10").toString());
						Doj.put("DAY11" ,  Res.getString("DAY11").toString());
						Doj.put("DAY12" ,  Res.getString("DAY12").toString());
						Doj.put("DAY13" ,  Res.getString("DAY13").toString());
						Doj.put("DAY14" ,  Res.getString("DAY14").toString());
						Doj.put("DAY15" ,  Res.getString("DAY15").toString());
						Doj.put("DAY16" ,  Res.getString("DAY16").toString());
						Doj.put("DAY17" ,  Res.getString("DAY17").toString());
						Doj.put("DAY18" ,  Res.getString("DAY18").toString());
						Doj.put("DAY19" ,  Res.getString("DAY19").toString());
						Doj.put("DAY20" ,  Res.getString("DAY20").toString());
						Doj.put("DAY21" ,  Res.getString("DAY21").toString());
						Doj.put("DAY22" ,  Res.getString("DAY22").toString());
						Doj.put("DAY23" ,  Res.getString("DAY23").toString());
						Doj.put("DAY24" ,  Res.getString("DAY24").toString());
						Doj.put("DAY25" ,  Res.getString("DAY25").toString());
						Doj.put("DAY26" ,  Res.getString("DAY26").toString());
						Doj.put("DAY27" ,  Res.getString("DAY27").toString());
						Doj.put("DAY28" ,  Res.getString("DAY28").toString());
						Doj.put("DAY29" ,  Res.getString("DAY29").toString());
						Doj.put("DAY30" ,  Res.getString("DAY30").toString());
						Doj.put("DAY31" ,  Res.getString("DAY31").toString());*/

						System.out.println("HR_ATT_CAL_MODE::"+HR_ATT_CAL_MODE.equalsIgnoreCase("PAYPERIOD"));

					}else if( Routing.equalsIgnoreCase("Dept_att_HR") && HR_ATT_CAL_MODE!=null && HR_ATT_CAL_MODE.equalsIgnoreCase("PAYPERIOD") ){


						////////////////////////////////////////////////////////////////////////////////////Main Data Attendance Approach /////////////////////////////	
						// System.out.println("Attendance Formation2~PAYPERIOD::"+Emp_DOJ.toString());
						// Doj=new JSONObject();
						JSONObject Doj1=new JSONObject();
						JSONArray values_dumm = new JSONArray();
						String Employeeid=Res.getString("employeeid").toString();
						ListIterator itr=null;
						try{
							Doj.put("ID" , Employeeid);
							Doj.put("NAME" ,empatt_Data.get(Employeeid+"_NAME").toString());
							try{
								itr=MyHeaderlist.listIterator();
							}catch(Exception erdd){
								System.out.println("Exception at erdd ::" +erdd);
							}
							/* empatt_Data.put(Res.getString("EMPLOYEEID")+"_NAME", Res.getString("callname"));
								empatt_Data.put(Res.getString("EMPLOYEEID")+"_FIN_"+Res.getString("TRANSACTIONDATE"), Res.getString("FIN"));
								empatt_Data.put(Res.getString("EMPLOYEEID")+"_LOUT_"+Res.getString("TRANSACTIONDATE"), Res.getString("LOUT"));
								empatt_Data.put(Res.getString("EMPLOYEEID")+"_NET_"+Res.getString("TRANSACTIONDATE"), Res.getString("NET"));

								empatt_Data.put(Res.getString("EMPLOYEEID")+"_DAYSTATUS_"+Res.getString("TRANSACTIONDATE"), Res.getString("DAYSTATUS"));
								empatt_Data.put(Res.getString("EMPLOYEEID")+"_STATUSID_"+Res.getString("STATUSID"), Res.getString("STATUSID"));
								empatt_Data.put(Res.getString("EMPLOYEEID")+"_REQ_STATUS_"+Res.getString("STATUSID"), Res.getString("REQ_STATUS"))

                                empatt_Data.put(Res.getString("EMPLOYEEID")+"_REQ_STATUS_TY_"+Res.getString("TRANSACTIONDATE"), Res.getString("STATUSTYPE"));
							 */
							int P_days=0,A_days=0,W_Off=0,HL_days=0,workingDays=0,CL=0,EL=0,OD=0,SL=0;
							while(itr.hasNext()){  

								Doj1=new JSONObject();
								String AttDate=itr.next().toString();
								//  System.out.println("AttDate::"+AttDate);

								Doj1.put("ATTDATE" ,  AttDate);
								String DayStatus="--";
								try{
									DayStatus=empatt_Data.get(Employeeid+"_DAYSTATUS_"+AttDate).toString();
								}catch(Exception erd){
									DayStatus="--";
									System.out.println("Exception erd :: "+erd);
								}


								workingDays++;

								String Day_in="--:--:--";
								String Day_out="--:--:--";

								if( DayStatus!="--"){

									String StatusID=empatt_Data.get(Employeeid+"_STATUSID_"+AttDate).toString();
									String A_StatusID=empatt_Data.get(Employeeid+"_REQ_STATUS_"+AttDate).toString();

									String A_StatusID_ty=empatt_Data.get(Employeeid+"_REQ_STATUS_TY_"+AttDate).toString();


									if(DayStatus.equalsIgnoreCase("WOFF") && StatusID.equalsIgnoreCase("WOFF")){
										W_Off=W_Off+1;
										Doj1.put("DAY", DayStatus.concat("#").concat(" "));

									}else if(DayStatus.equalsIgnoreCase("P") && StatusID.equalsIgnoreCase("COMLATE") ){

										P_days ++;
										if(A_StatusID.equalsIgnoreCase("0")){
											Doj1.put("DAY", DayStatus.concat("#").concat("red"));
										}else if(A_StatusID.equalsIgnoreCase("AR")){
											if(A_StatusID_ty.equalsIgnoreCase("A")){
												Doj1.put("DAY", DayStatus.concat("#").concat("green"));
											}else if(A_StatusID_ty.equalsIgnoreCase("P")){
												Doj1.put("DAY", DayStatus.concat("#").concat(" "));
											}else if(A_StatusID_ty.equalsIgnoreCase("R")){
												Doj1.put("DAY", DayStatus.concat("#").concat("red"));
											}else{
												Doj1.put("DAY", DayStatus.concat("#").concat("red"));
											}

										}



									}else if(DayStatus.equalsIgnoreCase("P") && StatusID.equalsIgnoreCase("PERFECT") ){

										P_days ++;
										Doj1.put("DAY", DayStatus.concat("#").concat(" "));



									}else if(DayStatus.equalsIgnoreCase("P") && StatusID.equalsIgnoreCase("OK") ){
										P_days ++;
										Doj1.put("DAY", DayStatus.concat("#").concat(" "));

									}else if(DayStatus.equalsIgnoreCase("P") && StatusID.equalsIgnoreCase("INCOMLATE") ){
										// Doj1.put("DAY", DayStatus.concat("#").concat("red "));
										P_days ++;
										if(A_StatusID.equalsIgnoreCase("0")){
											Doj1.put("DAY", DayStatus.concat("#").concat("red"));
										}else if(A_StatusID.equalsIgnoreCase("AR")){
											if(A_StatusID_ty.equalsIgnoreCase("A")){
												Doj1.put("DAY", DayStatus.concat("#").concat("green"));
											}else if(A_StatusID_ty.equalsIgnoreCase("P")){
												Doj1.put("DAY", DayStatus.concat("#").concat(" "));
											}else if(A_StatusID_ty.equalsIgnoreCase("R")){
												Doj1.put("DAY", DayStatus.concat("#").concat("red"));
											}else{
												Doj1.put("DAY", DayStatus.concat("#").concat("red"));
											}

										}


									}else if(DayStatus.equalsIgnoreCase("P") && StatusID.equalsIgnoreCase("EARLYOUT") ){
										P_days ++;
										//Doj1.put("DAY", DayStatus.concat("#").concat("red "));
										if(A_StatusID.equalsIgnoreCase("0")){
											Doj1.put("DAY", DayStatus.concat("#").concat("red"));
										}else if(A_StatusID.equalsIgnoreCase("AR")){
											if(A_StatusID_ty.equalsIgnoreCase("A")){
												Doj1.put("DAY", DayStatus.concat("#").concat("green"));
											}else if(A_StatusID_ty.equalsIgnoreCase("P")){
												Doj1.put("DAY", DayStatus.concat("#").concat(" "));
											}else if(A_StatusID_ty.equalsIgnoreCase("R")){
												Doj1.put("DAY", DayStatus.concat("#").concat("red"));
											}else{
												Doj1.put("DAY", DayStatus.concat("#").concat("red"));
											}

										}


									}else if(DayStatus.equalsIgnoreCase("P") && StatusID.equalsIgnoreCase("LESSHrs.") ){

										P_days ++;
										// Doj1.put("DAY", DayStatus.concat("#").concat("red "));
										if(A_StatusID.equalsIgnoreCase("0")){
											Doj1.put("DAY", DayStatus.concat("#").concat("red"));
										}else if(A_StatusID.equalsIgnoreCase("AR")){
											if(A_StatusID_ty.equalsIgnoreCase("A")){
												Doj1.put("DAY", DayStatus.concat("#").concat("green"));
											}else if(A_StatusID_ty.equalsIgnoreCase("P")){
												Doj1.put("DAY", DayStatus.concat("#").concat(" "));
											}else if(A_StatusID_ty.equalsIgnoreCase("R")){
												Doj1.put("DAY", DayStatus.concat("#").concat("red"));
											}else{
												Doj1.put("DAY", DayStatus.concat("#").concat("red"));
											}

										}

									} else if(DayStatus.equalsIgnoreCase("P") && StatusID.equalsIgnoreCase("ICT") ){
										P_days ++;
										// Doj1.put("DAY", DayStatus.concat("#").concat("red "));
										if(A_StatusID.equalsIgnoreCase("0")){
											Doj1.put("DAY", DayStatus.concat("#").concat("red"));
										}else if(A_StatusID.equalsIgnoreCase("AR")){
											if(A_StatusID_ty.equalsIgnoreCase("A")){
												Doj1.put("DAY", DayStatus.concat("#").concat("green"));
											}else if(A_StatusID_ty.equalsIgnoreCase("P")){
												Doj1.put("DAY", DayStatus.concat("#").concat(" "));
											}else if(A_StatusID_ty.equalsIgnoreCase("R")){
												Doj1.put("DAY", DayStatus.concat("#").concat("red"));
											}else{
												Doj1.put("DAY", DayStatus.concat("#").concat("red"));
											}

										}

									}else if(DayStatus.equalsIgnoreCase("A") && StatusID.equalsIgnoreCase("A") ){

										A_days++;
										System.out.println("A_StatusID:::"+A_StatusID);
										if(A_StatusID.equalsIgnoreCase("0")){   
											Doj1.put("DAY", DayStatus.concat("#").concat("red"));

										}else if(A_StatusID.equalsIgnoreCase("CL") ){


											if(A_StatusID_ty.equalsIgnoreCase("A")){
												Doj1.put("DAY", "CL".concat("#").concat("green"));
												CL++;
											}else if(A_StatusID_ty.equalsIgnoreCase("R")){
												Doj1.put("DAY", "CL".concat("#").concat("red"));
											}else{
												Doj1.put("DAY", "CL".concat("#").concat(" ")); 
												CL++;
											}

										}else if(A_StatusID.equalsIgnoreCase("SL") ){

											if(A_StatusID_ty.equalsIgnoreCase("A")){
												SL++;
												Doj1.put("DAY", "SL".concat("#").concat("green"));
											}else if(A_StatusID_ty.equalsIgnoreCase("R")){
												Doj1.put("DAY", "SL".concat("#").concat("red"));
											}else{
												SL++;
												Doj1.put("DAY", "SL".concat("#").concat(" ")); 
											}

										}else if(A_StatusID.equalsIgnoreCase("EL") ){

											if(A_StatusID_ty.equalsIgnoreCase("A")){
												EL++;
												Doj1.put("DAY", "EL".concat("#").concat("green"));
											}else if(A_StatusID_ty.equalsIgnoreCase("R")){
												Doj1.put("DAY", "EL".concat("#").concat("red"));
											}else{
												EL++;
												Doj1.put("DAY", "EL".concat("#").concat(" ")); 
											}

										}else if(A_StatusID.equalsIgnoreCase("OD") ){

											if(A_StatusID_ty.equalsIgnoreCase("A")){
												OD++;
												Doj1.put("DAY", "OD".concat("#").concat("green"));
											}else if(A_StatusID_ty.equalsIgnoreCase("R")){
												Doj1.put("DAY", "OD".concat("#").concat("red"));
											}else{
												OD++;
												Doj1.put("DAY", "OD".concat("#").concat(" ")); 
											}

										}

										else{
											Doj1.put("DAY", DayStatus.concat("#").concat("red"));
										}

									}

									Day_in=empatt_Data.get(Employeeid+"_FIN_"+AttDate).toString();
									Day_out=empatt_Data.get(Employeeid+"_FIN_"+AttDate).toString();
									Doj1.put("DAY_MODE" ,  Day_in +"-"+ Day_out );

								}
								else{
									//DayStatus="NA";
									Doj1.put("DAY", DayStatus.concat("#").concat(" "));
									Doj1.put("DAY_MODE" ,  Day_in +"-"+ Day_out );
								}

								/* Doj.put("Total_work_Days" ,  workingDays );
						    	  Doj.put("Working_Days" ,  P_days );
						    	  Doj.put("Total_Absents" ,  A_days );
						    	  Doj.put("Total_Weakoff" ,  W_Off );*/
								values_dumm.add(Doj1.toString());

							}  
							Doj.put("Total_work_Days" ,  workingDays );
							Doj.put("Working_Days" ,  P_days );
							Doj.put("Total_Absents" ,  A_days );
							Doj.put("Total_Weakoff" ,  W_Off );

							Doj.put("CL" ,  CL );
							Doj.put("SL" ,  SL);
							Doj.put("EL" ,  EL);
							Doj.put("OD" ,  OD);
							Doj.put("TOT" ,  SL+EL+CL);

							Doj.put("ATTENDANCE",values_dumm.toString());

							System.out.println("Doj ::" +Doj.toString());

						}catch(Exception er){

							System.out.println("er at MgmApprovals: "+er);

						}
						/*try{ 



						String Emp_id=Res.getString("ID").toString();
						String Emp_Name=Res.getString("NAME").toString();

						//Doj.put("ID" ,  Emp_id);
						//Doj.put("NAME" ,  Emp_Name);

						//Doj.put("NAME" ,  Res.getString("NAME").toString());
						String color=" ";



						//System.out.println("PAYPERIOD CALLING  ~ Before while loop");
						String HL_MAP="WDAY";

						Doj.put("ID" ,  Emp_id);
						Doj.put("NAME" ,  Emp_Name);

						//<<<!--------------VENU PAYPERIOD>>>>>>>>>>>>>>>>>>>>>
						JSONArray values_dumm = new JSONArray();

						String i="0";

					//	System.out.println("PAYPERIOD~Month_Size at rotate for::"+Month_Size);
						for(int x=1;x<Month_Size;x++){
							i= ""+Integer.parseInt(BulkDates_map.get(x+"_DAY").toString())+"";
						//	 System.out.println("i:::~"+i);
							JSONObject Doj1=new JSONObject();
							color=" ";
							 String WeekDayType=Res.getString("HLWD_TYPE_"+i).toString();
					//	System.out.println("WeekDayType::"+WeekDayType);
							String HL_WEEKDAY=Res.getString("HL$"+i).toString();
							//System.out.println("HL_WEEKDAY::"+HL_WEEKDAY);
							Doj1.put("ATTDATE" ,  Res.getString("ATTDATE"+i+""));

							 try{
							// System.out.println();
								 //HL_MAP=DepAtt_Leav_map_HL.get("HL$1$11").toString();
								 HL_MAP=DepAtt_Leav_map_HL.get(""+HL_WEEKDAY+"").toString();
							 //  System.out.println(HL_WEEKDAY+"::HL_MAP:::"+HL_MAP);
							  if(HL_MAP==null){
								  HL_MAP="WDAY";
							  }
							 }catch(Exception MAPP){
								 HL_MAP="WDAY";
								// System.out.println("MAPP:::"+MAPP);

							 }
							// System.out.println(WeekDayType+":::"+HL_WEEKDAY+"::Before while loop::"+HL_MAP);

							//System.out.println("IN IF ATT"+DepAtt_Leav_map.get(Res.getString("ID").toString()+"_"+i));

							if(WeekDayType!=null && WeekDayType.equalsIgnoreCase("WDAY") && HL_MAP!=null && HL_MAP.equalsIgnoreCase("WDAY")  ){   // Main IF 

							if(DepAtt_Leav_map.get(Res.getString("ID").toString()+"_"+i)!=null){

							String Leave_Type=DepAtt_Leav_map.get(Res.getString("ID").toString()+"_TYP_"+i).toString();
							String Leave_FLAG=DepAtt_Leav_map.get(Res.getString("ID").toString()+"_FLAG_"+i).toString();

							 if(Leave_FLAG.equalsIgnoreCase("A")){
								 color="green"; 
							 }else if(Leave_FLAG.equalsIgnoreCase("R")){
								 color="red"; 
							 }else{
								 color=" "; 
							 }


							 Doj1.put("DAY" ,  Leave_Type.concat("#").toString().concat(color));
						    // Doj.put("DAY"+i+"" ,  DepAtt_Leav_map.get(Res.getString("ID").toString()+"_TYP_"+i).toString().concat("#green"));

						     // Doj.put("DAY"+i+"" ,  DepAtt_Leav_map.get(Res.getString("ID").toString()+"_TYP").toString().concat("#"+Res.getString("DAY"+i+"CL").toString()));

							}else {

							// Emp_DOJ.append(" CONCAT(FINDAY"+i+",'-',LOUTDAY"+i+")DAY"+i+", ");Emp_id
								String MainColor="NA";

								try{

								  MainColor=ATT_APP.get(Emp_id+"$"+i).toString();
								}catch(Exception Edf){
									MainColor="NA";
									//System.out.println("Exception at Attendance Color::"+Edf);
								}
								if(MainColor!=null && MainColor!="NA" && Res.getString("DAY"+i+"CL").toString().equals("red") ){

									Doj1.put("DAY" ,  Res.getString("DAY"+i+"").toString().concat("#green"));
								}else{

								try{
							 Doj1.put("DAY" ,  Res.getString("DAY"+i+"").toString().concat("#"+Res.getString("DAY"+i+"CL").toString()));

							 Doj1.put("DAY_MODE" ,  Res.getString("IN"+i+"") +"-"+ Res.getString("OUT"+i+"") );
								}catch(Exception erd){

								//	System.out.println("Exception ERD::"+erd);

								}
								}

							}
						} else{// Main IF

							String color1="";
							if(WeekDayType.equalsIgnoreCase("WOFF")){

								Doj1.put("DAY" ,  WeekDayType.concat("#").concat(color1));
								Doj1.put("DAY_MODE" ,  "Sunday");

							}else{
								//HL_MAP
								Doj1.put("DAY" ,  "HL".concat("#").concat(color1));

								Doj1.put("DAY_MODE" ,  HL_MAP);

								//Doj.put("DAY"+i+"" ,  HL_MAP.concat("#").concat(color1));
							}
							if(HL_MAP!=null && HL_MAP.equalsIgnoreCase("HL") ){
								Doj.put("DAY"+i+"" ,  HL_MAP.concat("#").concat(color));

							}else{
								Doj.put("DAY"+i+"" ,  WeekDayType.concat("#").concat(color));

							}




						}// Main Else		
							// Doj.put("DAY"+i+"_CL" ,  Res.getString("DAY"+i+"CL").toString()); 
							 //Doj.put("DAY"+i+"_NS" , " ");
						//	System.out.println("Doj1.toString():::"+Doj1.toString());

							values_dumm.add(Doj1.toString());
						 }   /// For loop Ending YESSSSS

						Doj.put("ATTENDANCE",values_dumm.toString());

					//	System.out.println("Doj.put(ATTENDANCE):::"+Doj.toString());

					//	values.add(Doj.toString());
						Doj.put("DAY1" ,  Res.getString("DAY1").toString());
						Doj.put("DAY2" ,  Res.getString("DAY2").toString());
						Doj.put("DAY3" ,  Res.getString("DAY3").toString());
						Doj.put("DAY4" ,  Res.getString("DAY4").toString());
						Doj.put("DAY5" ,  Res.getString("DAY5").toString());
						Doj.put("DAY6" ,  Res.getString("DAY6").toString());
						Doj.put("DAY7" ,  Res.getString("DAY7").toString());
						Doj.put("DAY8" ,  Res.getString("DAY8").toString());
						Doj.put("DAY9" ,  Res.getString("DAY9").toString());
						Doj.put("DAY10" ,  Res.getString("DAY10").toString());
						Doj.put("DAY11" ,  Res.getString("DAY11").toString());
						Doj.put("DAY12" ,  Res.getString("DAY12").toString());
						Doj.put("DAY13" ,  Res.getString("DAY13").toString());
						Doj.put("DAY14" ,  Res.getString("DAY14").toString());
						Doj.put("DAY15" ,  Res.getString("DAY15").toString());
						Doj.put("DAY16" ,  Res.getString("DAY16").toString());
						Doj.put("DAY17" ,  Res.getString("DAY17").toString());
						Doj.put("DAY18" ,  Res.getString("DAY18").toString());
						Doj.put("DAY19" ,  Res.getString("DAY19").toString());
						Doj.put("DAY20" ,  Res.getString("DAY20").toString());
						Doj.put("DAY21" ,  Res.getString("DAY21").toString());
						Doj.put("DAY22" ,  Res.getString("DAY22").toString());
						Doj.put("DAY23" ,  Res.getString("DAY23").toString());
						Doj.put("DAY24" ,  Res.getString("DAY24").toString());
						Doj.put("DAY25" ,  Res.getString("DAY25").toString());
						Doj.put("DAY26" ,  Res.getString("DAY26").toString());
						Doj.put("DAY27" ,  Res.getString("DAY27").toString());
						Doj.put("DAY28" ,  Res.getString("DAY28").toString());
						Doj.put("DAY29" ,  Res.getString("DAY29").toString());
						Doj.put("DAY30" ,  Res.getString("DAY30").toString());
						Doj.put("DAY31" ,  Res.getString("DAY31").toString());

					//	System.out.println("DepAtt_Leav_map_HL::"+DepAtt_Leav_map_HL.toString());

					}catch(Exception Err){

						System.out.println("Exception At end By Venu::"+Err);

						System.out.println("Exception At end By Venu::"+Err.toString());

					}
						 */	
					}  //////////////////        MAIN DOCUMENT APPROACH --------------------/////////////////////////////////////////////////
					else if(Routing.equalsIgnoreCase("Dept_att")){




						String Emp_id=Res.getString("ID").toString();
						Doj.put("ID" ,  Emp_id);
						Doj.put("NAME" ,  Res.getString("NAME").toString());
						String color=" ";

						System.out.println("Before while loop");
						String HL_MAP="WDAY";
						for(int i=1;i<=call_month;i++){
							color=" ";
							String WeekDayType=Res.getString("HLWD_TYPE_"+i).toString();

							//	System.out.println("WeekDayType::"+WeekDayType);

							String HL_WEEKDAY=Res.getString("HL$"+i).toString();

							//System.out.println("HL_WEEKDAY::"+HL_WEEKDAY);


							try{
								// System.out.println();
								//HL_MAP=DepAtt_Leav_map_HL.get("HL$1$11").toString();
								HL_MAP=DepAtt_Leav_map_HL.get(""+HL_WEEKDAY+"").toString();
								//  System.out.println(HL_WEEKDAY+"::HL_MAP:::"+HL_MAP);
								if(HL_MAP==null){
									HL_MAP="WDAY";
								}
							}catch(Exception MAPP){
								HL_MAP="WDAY";
								// System.out.println("MAPP:::"+MAPP);

							}
							// System.out.println(WeekDayType+":::"+HL_WEEKDAY+"::Before while loop::"+HL_MAP);

							//System.out.println("IN IF ATT"+DepAtt_Leav_map.get(Res.getString("ID").toString()+"_"+i));

							if(WeekDayType!=null && WeekDayType.equalsIgnoreCase("WDAY") && HL_MAP!=null && HL_MAP.equalsIgnoreCase("WDAY")  ){   // Main IF 

								if(DepAtt_Leav_map.get(Res.getString("ID").toString()+"_"+i)!=null){

									String Leave_Type=DepAtt_Leav_map.get(Res.getString("ID").toString()+"_TYP_"+i).toString();
									String Leave_FLAG=DepAtt_Leav_map.get(Res.getString("ID").toString()+"_FLAG_"+i).toString();

									if(Leave_FLAG.equalsIgnoreCase("A")){
										color="green"; 
									}else if(Leave_FLAG.equalsIgnoreCase("R")){
										color="red"; 
									}else{
										color=" "; 
									}


									Doj.put("DAY"+i+"" ,  Leave_Type.concat("#").toString().concat(color));
									// Doj.put("DAY"+i+"" ,  DepAtt_Leav_map.get(Res.getString("ID").toString()+"_TYP_"+i).toString().concat("#green"));

									// Doj.put("DAY"+i+"" ,  DepAtt_Leav_map.get(Res.getString("ID").toString()+"_TYP").toString().concat("#"+Res.getString("DAY"+i+"CL").toString()));

								}else {

									// Emp_DOJ.append(" CONCAT(FINDAY"+i+",'-',LOUTDAY"+i+")DAY"+i+", ");Emp_id
									String MainColor="NA";

									try{

										MainColor=ATT_APP.get(Emp_id+"$"+i).toString();
									}catch(Exception Edf){
										MainColor="NA";
										//System.out.println("Exception at Attendance Color::"+Edf);  // Blocked By VENU
									}
									if(MainColor!=null && MainColor!="NA" && Res.getString("DAY"+i+"CL").toString().equals("red") ){

										Doj.put("DAY"+i+"" ,  Res.getString("DAY"+i+"").toString().concat("#green"));
									}else{


										Doj.put("DAY"+i+"" ,  Res.getString("DAY"+i+"").toString().concat("#"+Res.getString("DAY"+i+"CL").toString()));
									}

								}
							} else{// Main IF

								String color1="";
								if(WeekDayType.equalsIgnoreCase("WOFF")){
									Doj.put("DAY"+i+"" ,  WeekDayType.concat("#").concat(color1));
								}else{
									//HL_MAP
									Doj.put("DAY"+i+"" ,  "HL".concat("#").concat(color1));
									//Doj.put("DAY"+i+"" ,  HL_MAP.concat("#").concat(color1));
								}
								/*if(HL_MAP!=null && HL_MAP.equalsIgnoreCase("HL") ){
								Doj.put("DAY"+i+"" ,  HL_MAP.concat("#").concat(color));

							}else{
								Doj.put("DAY"+i+"" ,  WeekDayType.concat("#").concat(color));

							}*/

							}// Main Else		
							// Doj.put("DAY"+i+"_CL" ,  Res.getString("DAY"+i+"CL").toString()); 
							//Doj.put("DAY"+i+"_NS" , " ");

						}

						System.out.println("DepAtt_Leav_map_HL::"+DepAtt_Leav_map_HL.toString());

					}






					else if(Routing.equalsIgnoreCase("ManagerAppr_Resi")){

						Doj.put("ID" ,  Res.getString("employeeid").toString());
						Doj.put("NAME" ,  Res.getString("callname").toString());
						Doj.put("REASON" ,  Res.getString("REASON").toString());
						Doj.put("FEEDBACK" ,  Res.getString("FEEDBACK").toString());
						Doj.put("Applied" ,  Res.getString("DATEMODIFIED").toString());

						Doj.put("Applied" ,  Res.getString("DATEMODIFIED").toString());

						Doj.put("Applied" ,  Res.getString("DATEMODIFIED").toString());

						Doj.put("MANAGERS_COMMENTS" ,  Res.getString("MANAGERS_COMMENTS").toString());
						Doj.put("HRS_COMMENTS" ,  Res.getString("HRS_COMMENTS").toString());


						Doj.put("MANAGER_MSTATUS" ,  Res.getString("A1").toString());
						Doj.put("CC_STATUS" ,  Res.getString("R1").toString());

						Doj.put("C" ,  "none");

						if(APPMOD!=null && APPMOD.equalsIgnoreCase("MG") && Res.getString("MANAGER_MSTATUS").toString().equalsIgnoreCase("P") && Res.getString("CC_STATUS").toString().equalsIgnoreCase("P") ){
							Doj.put("A" ,  " ");
							Doj.put("R" ,  " ");
						}else if(APPMOD!=null && APPMOD.equalsIgnoreCase("MG") && Res.getString("MANAGER_MSTATUS").toString().equalsIgnoreCase("A") && Res.getString("CC_STATUS").toString().equalsIgnoreCase("P")){

							Doj.put("A" ,  "none");
							Doj.put("R" ,  "none");
						}else if(APPMOD!=null && APPMOD.equalsIgnoreCase("MG")  && (Res.getString("MANAGER_MSTATUS").toString().equalsIgnoreCase("A")||Res.getString("MANAGER_MSTATUS").toString().equalsIgnoreCase("R")) && (Res.getString("CC_STATUS").toString().equalsIgnoreCase("R")||Res.getString("CC_STATUS").toString().equalsIgnoreCase("A")) ){

							Doj.put("C" ,  " ");
							Doj.put("A" ,  "none");
							Doj.put("R" ,  "none");

						}else if(APPMOD!=null && APPMOD.equalsIgnoreCase("HR")  && Res.getString("MANAGER_MSTATUS").toString().equalsIgnoreCase("P") && Res.getString("CC_STATUS").toString().equalsIgnoreCase("P") ){

							Doj.put("A" ,  "none");
							Doj.put("R" ,  "none");

						}else if(APPMOD!=null && APPMOD.equalsIgnoreCase("HR")  && (Res.getString("MANAGER_MSTATUS").toString().equalsIgnoreCase("A")||Res.getString("MANAGER_MSTATUS").toString().equalsIgnoreCase("R")) && Res.getString("CC_STATUS").toString().equalsIgnoreCase("P") ){

							Doj.put("A" ,  " ");
							Doj.put("R" ,  " ");

						}

					}else if(Routing.equalsIgnoreCase("ATTENDANCE_BIO")){
						//	hlday
						Doj.put("DATE" , Res.getString(1)); 
						Doj.put("FIN" , Res.getString(2));
						Doj.put("FOUT" , Res.getString(3));
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
								Doj.put("DAF" , "none");

							}

						}else{

							Doj.put("DAF" , "none");
						}

						System.out.println("Res.getString(1)"+Res.getString(1));


						if(Att_Req.get(Res.getString(1))!=null){

							Doj.put("DAREQ" ,  Att_Req.get(Res.getString(1)).toString());

							Doj.put("DAF" , "none");
							/*if((Res.getString(1).toString()).equalsIgnoreCase(strTime)){
								 Doj.put("DAF" , "none");
								}else{
									 Doj.put("DAF" , " ");
								}*/

						}
						else{
							Doj.put("DAREQ" ,  "No Request");
						}



						//Doj.put("DAYTYPE" , Res.getString(5));
						//Doj.put("MOBILE" , Res.getString(6));

					}

					//DOJ_DOB.put("EMPDATA" ,DOJ_DOB.toString());

					values.add(Doj);


					/*DOJ_DOB.put(Res.getString(1) , Res.getString(2)); 
					 * */


					dobcnt1++;
				}  
			}catch(Exception Er){
				System.out.println("Exception222 At Er::"+Er);
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


			}else if(Routing.equalsIgnoreCase("ManagerAppr_Resi")){

				Doj.put("ID" ,  "NO_DATA");
				Doj.put("NAME" ,  "NO_DATA");
				Doj.put("REASON" ,  "NO_DATA");
				Doj.put("FEEDBACK" ,  "NO_DATA");
				Doj.put("MANAGERS_COMMENTS" ,  "NO_DATA");
				Doj.put("HRS_COMMENTS" ,  "NO_DATA");
				Doj.put("A" ,  "none");
				Doj.put("R" ,  "none");
				Doj.put("C" ,  "none");

			}else if(Routing.equalsIgnoreCase("BIRTHADYS")){
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


			}else if(Routing.equalsIgnoreCase("ManagerAppr") || Routing.equalsIgnoreCase("ManagerAppr_att") || Routing.equalsIgnoreCase("Dept_att")||Routing.equalsIgnoreCase("Dept_att_HR")){

				if(Routing.equalsIgnoreCase("Dept_att") || Routing.equalsIgnoreCase("Dept_att_HR")){

					Doj.put("ID" ,  "--");
					Doj.put("NAME" ,  "--");

					for(int i=1;i<=call_month;i++){


						// Emp_DOJ.append(" CONCAT(FINDAY"+i+",'-',LOUTDAY"+i+")DAY"+i+", ");
						Doj.put("DAY"+i+"" ,  "--".concat("#''"));


						// Doj.put("DAY"+i+"_CL" ,  Res.getString("DAY"+i+"CL").toString()); 
						//Doj.put("DAY"+i+"_NS" , " ");

					}


				}else{

					Doj.put("ID" , "No_Data"); 
					Doj.put("NAME" , "No_Data");
					Doj.put("DEPT" , "No_Data");
					Doj.put("SUBJECT" , "No_Data");
					Doj.put("REQ_DATE" , "No_Data");
					Doj.put("DURATION" , "No_Data");



					Doj.put("DAYS" , "No_Data");
					Doj.put("LEAVE_TYPE" , "No_Data");
					Doj.put("Manager_Status" ,"No_Data");


					Doj.put("B1" , "none");
					Doj.put("B2" , "none");

					Doj.put("B1T" , "Cancel");
					Doj.put("B2T" , "Reject");


					Doj.put("ID" ,  "NA");
					Doj.put("NAME" ,  "NA");
					Doj.put("DAY1" ,  "NA");
					Doj.put("DAY2" ,  "NA");
					Doj.put("DAY3" ,  "NA");
					Doj.put("DAY4" ,  "NA");
					Doj.put("DAY5" ,  "NA");
					Doj.put("DAY6" ,  "NA");
					Doj.put("DAY7" ,  "NA");
					Doj.put("DAY8" ,  "NA");
					Doj.put("DAY9" ,  "NA");
					Doj.put("DAY10" ,  "NA");
					Doj.put("DAY11" ,  "NA");
					Doj.put("DAY12" ,  "NA");
					Doj.put("DAY13" ,  "NA");
					Doj.put("DAY14" ,  "NA");
					Doj.put("DAY15" ,  "NA");
					Doj.put("DAY16" ,  "NA");
					Doj.put("DAY17" ,  "NA");
					Doj.put("DAY18" ,  "NA");
					Doj.put("DAY19" ,  "NA");
					Doj.put("DAY20" ,  "NA");
					Doj.put("DAY21" ,  "NA");
					Doj.put("DAY22" ,  "NA");
					Doj.put("DAY23" ,  "NA");
					Doj.put("DAY24" ,  "NA");
					Doj.put("DAY25" ,  "NA");
					Doj.put("DAY26" ,  "NA");
					Doj.put("DAY27" ,  "NA");
					Doj.put("DAY28" ,  "NA");
					Doj.put("DAY29" ,  "NA");
					Doj.put("DAY30" ,  "NA");
					Doj.put("DAY31" ,  "NA");
				}

			}
			values.add(Doj);
			values2.add(Doj22);
			System.out.println("values::"+values.toString());
		}
		request.setAttribute("DOJ_DOB",values.toString());
		request.setAttribute("ATT_MONTHS",ATT_MONTHS.toString());

		System.out.println("ATT_MONTHS::"+ATT_MONTHS.toString());
		request.setAttribute("ATT_MONTHS_1",ATT_MONTHS_1.toString());


		if(Routing==null){
			session.setAttribute("EMP_ID", username);
			session.setAttribute("EMP_PASSWORD", password);

		}
		System.out.println("_SUCCESS_PAGE::"+_SUCCESS_PAGE);
		try {
			if(statement!=null){
				statement.close();
			}
			if(Res!=null){
				Res.close();
			}
			/* if(conn!=null){
				conn.close();
	       }*/
			if(ps!=null){
				ps.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		if(Routing.equalsIgnoreCase("Att_Request")){

			System.out.println("Atten_Req_Message::"+Atten_Req_Message);
			out.write(""+Atten_Req_Message+"");  

		}else if(Routing_temp.equalsIgnoreCase("ATTENDANCE_BIO_DATES")){

			System.out.println("Atten_Req_Message::"+Atten_Req_Message);

			System.out.println("values.toString()::"+values.toString());
			out.write(""+values.toString()+"");  

		}else if(Routing.equalsIgnoreCase("ManagerAppr")){

			System.out.println("Atten_Req_Message::"+Atten_Req_Message);

			System.out.println("values.toString()::"+values.toString());
			request.getRequestDispatcher("ManagerApprovals.jsp").forward(request, response);

		}else if(Routing.equalsIgnoreCase("ManagerAppr_att")){

			System.out.println("Atten_Req_Message::"+Atten_Req_Message);

			System.out.println("values.toString()::"+values.toString());
			request.getRequestDispatcher("ManagerApprovals_att.jsp").forward(request, response);

		}else if((Routing.equalsIgnoreCase("Dept_att")) && Routing_type==null ){

			System.out.println("Atten_Req_Message::"+Atten_Req_Message);

			System.out.println("values.toString()::"+values.toString());
			request.getRequestDispatcher("Dept_attendance.jsp").forward(request, response);

		}else if(Routing.equalsIgnoreCase("Dept_att_HR") && Routing_type==null){

			System.out.println("values.toString()::"+values.toString());
			request.getRequestDispatcher("Bu_attendance.jsp").forward(request, response);

		}else if((Routing.equalsIgnoreCase("Dept_att")||Routing.equalsIgnoreCase("Dept_att_HR")) && Routing_type!=null && Routing_type.equalsIgnoreCase("AJEX") ){


			//System.out.println(Doj22.toString()+"~VENU~"+values.toString());
			System.out.println("ATT_PAYPERIOD::"+Emp_DOJ.toString());
			out.write(""+values.toString()+" $#$ "+""+Doj22.toString()+"");

		}else if(Routing.equalsIgnoreCase("ManagerAppr_Resi")){

			String Page="";
			if(APPMOD.equalsIgnoreCase("HR")){
				Page="Hr_Manager_reg_approvals.jsp";
			}else if(APPMOD.equalsIgnoreCase("MG")){
				Page="Manager_reg_approvals.jsp";
			}


			System.out.println("Manager Approvals--->"+Page);
			request.getRequestDispatcher(Page).forward(request, response);

		}else{
			request.getRequestDispatcher(_SUCCESS_PAGE).forward(request, response);  
		}
	}  
}
