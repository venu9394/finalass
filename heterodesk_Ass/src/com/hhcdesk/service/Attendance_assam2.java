package com.hhcdesk.service;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.hhcdesk.db.GetDbData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@SuppressWarnings({"serial","unchecked","rawtypes"})
public class Attendance_assam2 extends HttpServlet {




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext c = getServletContext();


		HttpSession session = request.getSession(false);
		ResultSet Res;
		Connection conn=null;
		PreparedStatement ps=null;
		String ATT_FLAG=null;
		PrintWriter out = response.getWriter();
		Map UserMap=new HashMap();

		boolean currMonthflg=false;
		int User_Auth=0;
		GetDbData DataObj=new GetDbData();

		JSONObject ATT_MONTHS = new JSONObject();

		int dobcnt1=0;
		JSONArray values;
		values = new JSONArray();

		String Routing=request.getParameter("Routing");
		String Month="currmonth";
		Map getHoursDetails=new HashMap();

		String Routing_temp=Routing;
		if(Routing.equalsIgnoreCase("ATTENDANCE_BIO_DATES")){
			Routing="ATTENDANCE_BIO";

		}


		String date=null,Req_message=null,subject=null,Atten_Req_Message="Success Fully Submit Your Request";


		if(Routing.equalsIgnoreCase("Att_Request")){

			date=request.getParameter("id");
			Req_message=request.getParameter("message");
			subject=request.getParameter("Subject");

		}

		System.out.println("Routing  :::" +Routing+"Calling ASSAM V2.0");
		session.setAttribute("Notice" ,"N");
		StringBuffer Emp_DOJ=new StringBuffer();

		StringBuffer Months_ATT=new StringBuffer();
		StringBuffer HLDAYLIST=new StringBuffer();
		Map hlday=new HashMap();
		Map Att_Req=new HashMap();
		JSONObject  Doj= new JSONObject();
		ResourceBundle bundle_info =(ResourceBundle)(c.getAttribute("bundle"));
		String EnableDays="4";

		try{
			EnableDays=bundle_info.getString("AssamEnableDays");
		}catch(Exception erd){
			System.out.println("EnableDays Assam ::"+EnableDays);
		}
		String Query=bundle_info.getString("HHCL_DESK_USER_LOGIN");

		try {
			conn=(Connection)session.getAttribute("ConnectionObj");
		} catch (Exception e1) {
			e1.printStackTrace();
		}


		String username=request.getParameter("username");  
		String password=request.getParameter("pwd"); 

		if(Routing!=null && (Routing.equalsIgnoreCase("ATTENDANCE_BIO") || Routing.equalsIgnoreCase("Att_Request") || Routing.equalsIgnoreCase("ReqSpenthours")||Routing.equalsIgnoreCase("ReqSpenthoursInsert") )){
			username=(String)session.getAttribute("EMP_ID");
			password=(String)session.getAttribute("EMP_PASSWORD");
		}

		StringBuffer User_Authen=new StringBuffer();
		User_Authen.append(Query);





		if(Routing.equalsIgnoreCase("ATTENDANCE_BIO")){
			ATT_FLAG=request.getParameter("ATT_FLAG");
			String Month_FROM=request.getParameter("Month_FROM");
			String Month_TO=request.getParameter("Month_TO");

			Month=request.getParameter("Month");

			if(Month==null){
				Month="currmonth";
				ATT_FLAG="DATES";
			}

			StringBuffer PayperiodDayes=new StringBuffer();
			StringBuffer Request_Enable=new StringBuffer();
			String EmpBuid=session.getAttribute("EMPBUID").toString();
			UserMap.put("FROMDATE", "0000-00-00");
			UserMap.put("TODATE", "0000-00-00");

			if(Month!=null && Month.equalsIgnoreCase("currmonth")  ){

				PayperiodDayes.append(" select A.fromdate,A.TODATE,if(month(now())<10,concat('0',month(now())) ,month(now())) AS CurrMonth from hcladm_prod.tbl_transaction_dates A ");
				PayperiodDayes.append("  join( ");
				PayperiodDayes.append("   select max(transactionduration) txnid from hcladm_prod.tbl_transaction_dates where ");
				PayperiodDayes.append("   transactiontypeid=1 and businessunitid="+EmpBuid+" ");
				PayperiodDayes.append("  )B ON A.transactionduration=B.txnid ");
				PayperiodDayes.append("  and A.businessunitid="+EmpBuid+" and A.transactiontypeid=1 ");
				currMonthflg=true;


			}else{

				PayperiodDayes.append(" select A.fromdate,A.TODATE,MONTH(now()) from hcladm_prod.tbl_transaction_dates A ");
				PayperiodDayes.append("  join( ");
				PayperiodDayes.append("   select concat(YEAR(now()),'"+Month+"') txnid ");
				PayperiodDayes.append("  )B ON A.transactionduration=B.txnid ");
				PayperiodDayes.append("  and A.businessunitid="+EmpBuid+" and A.transactiontypeid=1 ");

			}
			Res=null;
			try {
				Res=(ResultSet)DataObj.FetchData(PayperiodDayes.toString(), "Emp_DOJ", Res ,conn);
				while(Res.next()){
					UserMap.put("FROMDATE", Res.getString("fromdate"));
					UserMap.put("TODATE", Res.getString("TODATE"));

					if(Month!=null && Month.equalsIgnoreCase("CurrMonth") &&currMonthflg ){
						Month=Res.getString("CurrMonth");
					}
				}  
			}catch(Exception Er){
				System.out.println("Exception At Er::"+Er);
			}

			if(ATT_FLAG!=null && ATT_FLAG.equalsIgnoreCase("DATES")){

				if(EmpBuid.equalsIgnoreCase("16") || EmpBuid.equalsIgnoreCase("15")){


					Request_Enable.append(" select distinct dateon from test_mum.tbl_attendance_date_limit_shift ");
					Request_Enable.append(" where DATEON  between date_ADD(current_date(),INTERVAL -30 DAY) and current_date() and STATUS=1002");
					Request_Enable.append(" union ");
					Request_Enable.append(" select distinct transactiondate from hclhrm_prod_others.tbl_attendance_adjustments ");
					Request_Enable.append(" where transactiondate between "+UserMap.get("FROMDATE")+" and "+UserMap.get("TODATE")+" ");
					Request_Enable.append(" and employeeid="+username+"  ");

				}else{

					Request_Enable.append(" select distinct dateon from test_mum.tbl_attendance_date_limit ");
					Request_Enable.append(" where DATEON between date_ADD(current_date(),INTERVAL -30 DAY) and current_date() and STATUS=1002");
					Request_Enable.append(" union ");
					Request_Enable.append(" select distinct transactiondate from hclhrm_prod_others.tbl_attendance_adjustments ");
					Request_Enable.append(" where transactiondate between "+UserMap.get("FROMDATE")+" and "+UserMap.get("TODATE")+" ");
					Request_Enable.append(" and employeeid="+username+" ");

				}
			}else{

				if(EmpBuid.equalsIgnoreCase("16") || EmpBuid.equalsIgnoreCase("15")){

					Request_Enable.append(" select distinct dateon from test_mum.tbl_attendance_date_limit_shift ");
					Request_Enable.append(" where DATEON between date_ADD(current_date(),INTERVAL -"+EnableDays+" DAY) and current_date() and STATUS=1002");
					Request_Enable.append(" union ");
					Request_Enable.append(" select distinct transactiondate from hclhrm_prod_others.tbl_attendance_adjustments ");
					Request_Enable.append(" where transactiondate between CONCAT(YEAR(NOW()),'-"+Month+"','-01')  and LAST_DAY(CONCAT(YEAR(NOW()),'-"+Month+"','-01')) ");
					Request_Enable.append(" and employeeid="+username+" ");

				}else{

					Request_Enable.append(" select distinct dateon from test_mum.tbl_attendance_date_limit ");
					Request_Enable.append(" where DATEON between date_ADD(current_date(),INTERVAL -"+EnableDays+" DAY) and current_date() and STATUS=1002");
					Request_Enable.append(" union ");
					Request_Enable.append(" select distinct transactiondate from hclhrm_prod_others.tbl_attendance_adjustments ");
					Request_Enable.append(" where transactiondate between CONCAT(YEAR(NOW()),'-"+Month+"','-01') and LAST_DAY(CONCAT(YEAR(NOW()),'-"+Month+"','-01')) ");
					Request_Enable.append(" and employeeid="+username+" ");

				}


			}

			Res=null;
			try {
				Res=(ResultSet)DataObj.FetchData(Request_Enable.toString(), "Enable Days", Res ,conn);
				while(Res.next()){
					UserMap.put(Res.getString("dateon"), Res.getString("dateon"));
				}  
			}catch(Exception Er){
				System.out.println("Exception At Er::"+Er);
			}


			if(ATT_FLAG!=null && ATT_FLAG.equalsIgnoreCase("MONTHS")){

				Emp_DOJ.append(" select distinct A.DATEON AS DAY ,A.ATT_IN AS FIN,A.ATT_OUT AS FOUT,  ");
				Emp_DOJ.append(" A.NET_HOURS AS PERDAY, if(A.DATEON=ifnull(C.LEAVEON,'0000-00-00'),C.LEAVE_TYPE, A.DAYTYPE) AS DAYTYPE, ");
				Emp_DOJ.append(" IF(ifnull(D.FLAG,'No Request')='A','APPROVED',if(ifnull(D.FLAG,'No Request')='R','Reject',if(ifnull(D.FLAG,'No Request')='P','Processed','No Request'))) STATUS, ");
				Emp_DOJ.append(" D.FLAG, ");
				Emp_DOJ.append(" if(A.DATEON=ifnull(C.LEAVEON,'0000-00-00'),concat(C.LEAVE_TYPE,' / ',C.LEAVE_COUNT,' DAY'), A.DAYTYPE) AS DAYTYPE2, ");
				Emp_DOJ.append(" ifnull(A.SHIFT,'General') AS SHIFT, ");
				Emp_DOJ.append(" CASE ");
				Emp_DOJ.append(" WHEN A.SHIFT='Morning Shift' THEN   TIMEDIFF(A.NET_HOURS,'08:00:00') ");
				Emp_DOJ.append(" WHEN A.SHIFT='Second Shift'  THEN   TIMEDIFF(A.NET_HOURS,'08:00:00') ");
				Emp_DOJ.append(" WHEN A.SHIFT='Night Shift'   THEN   TIMEDIFF(A.NET_HOURS,'08:00:00') ");
				Emp_DOJ.append(" WHEN A.SHIFT='General'       THEN   TIMEDIFF(A.NET_HOURS,'09:00:00') ");
				Emp_DOJ.append(" ELSE concat('#',TIMEDIFF(A.NET_HOURS,'09:00:00')) ");
				Emp_DOJ.append(" END AS DIFFHOURS, ");
				Emp_DOJ.append(" CASE ");
				Emp_DOJ.append(" WHEN A.SHIFT='Morning Shift' THEN    IF(TIMEDIFF(A.NET_HOURS,'08:00:00')<'00:00:00','true','false') ");
				Emp_DOJ.append(" WHEN A.SHIFT='Second Shift'  THEN    IF(TIMEDIFF(A.NET_HOURS,'08:00:00')<'00:00:00','true','false') ");
				Emp_DOJ.append(" WHEN A.SHIFT='Night Shift'   THEN    IF(TIMEDIFF(A.NET_HOURS,'08:00:00')<'00:00:00','true','false') ");
				Emp_DOJ.append(" WHEN A.SHIFT='General'       THEN    IF(TIMEDIFF(A.NET_HOURS,'09:00:00')<'00:00:00','true','false') ");
				Emp_DOJ.append(" ELSE  IF(TIMEDIFF(A.NET_HOURS,'09:00:00')<'00:00:00','true','false') ");
				Emp_DOJ.append(" END AS DEDHOURS ,");
				Emp_DOJ.append("  date_format(A.DATEON,'%d-%m-%Y') AS DAYVIEW ,date_format(now(),'%d-%m-%Y') As Newdate from test_mum.tbl_att_leave_in_out_status_report A ");
				Emp_DOJ.append(" join hclhrm_prod.tbl_employee_primary B ");
				Emp_DOJ.append(" ON B.employeesequenceno=A.employeeid and A.PAYPERIOD=0 ");
				Emp_DOJ.append(" left join hclhrm_prod_others.tbl_emp_leave_report C ");
				Emp_DOJ.append(" ON C.employeeid=B.employeesequenceno AND C.LEAVEON=A.DATEON AND C.MANAGER_STATUS IN ('A','P') and C.STATUS=1001 ");
				Emp_DOJ.append(" left join hclhrm_prod_others.tbl_emp_attn_req D ON D.EMPLOYEEID=B.employeesequenceno AND D.REQ_DATE=A.DATEON AND ");
				Emp_DOJ.append(" D.REQ_TYPE='AR' ");
				Emp_DOJ.append(" where B.employeesequenceno in("+username+") ");
				Emp_DOJ.append(" and a.DATEON BETWEEN CONCAT(YEAR(NOW()),'-"+Month+"','-01') ");
				Emp_DOJ.append(" AND LAST_DAY(CONCAT(YEAR(NOW()),'-"+Month+"','-01')) ");
				Emp_DOJ.append(" order by a.DATEON ");

			}else{
				Emp_DOJ.append(" select distinct A.DATEON AS DAY ,A.ATT_IN AS FIN,A.ATT_OUT AS FOUT,  ");
				Emp_DOJ.append(" A.NET_HOURS AS PERDAY, if(A.DATEON=ifnull(C.LEAVEON,'0000-00-00'),C.LEAVE_TYPE, A.DAYTYPE) AS DAYTYPE, ");
				Emp_DOJ.append(" IF(ifnull(D.FLAG,'No Request')='A','APPROVED',if(ifnull(D.FLAG,'No Request')='R','Reject',if(ifnull(D.FLAG,'No Request')='P','Processed','No Request'))) STATUS, ");
				Emp_DOJ.append(" D.FLAG, ");
				Emp_DOJ.append(" if(A.DATEON=ifnull(C.LEAVEON,'0000-00-00'),concat(C.LEAVE_TYPE,' / ',C.LEAVE_COUNT,' DAY'), A.DAYTYPE) AS DAYTYPE2, ");
				Emp_DOJ.append(" ifnull(A.SHIFT,'General') AS SHIFT, ");
				Emp_DOJ.append(" CASE ");
				Emp_DOJ.append(" WHEN A.SHIFT='Morning Shift' THEN   TIMEDIFF(A.NET_HOURS,'08:00:00') ");
				Emp_DOJ.append(" WHEN A.SHIFT='Second Shift'  THEN   TIMEDIFF(A.NET_HOURS,'08:00:00') ");
				Emp_DOJ.append(" WHEN A.SHIFT='Night Shift'   THEN   TIMEDIFF(A.NET_HOURS,'08:00:00') ");
				Emp_DOJ.append(" WHEN A.SHIFT='General'       THEN   TIMEDIFF(A.NET_HOURS,'09:00:00') ");
				Emp_DOJ.append(" ELSE concat('#',TIMEDIFF(A.NET_HOURS,'09:00:00')) ");
				Emp_DOJ.append(" END AS DIFFHOURS, ");
				Emp_DOJ.append(" CASE ");
				Emp_DOJ.append(" WHEN A.SHIFT='Morning Shift' THEN    IF(TIMEDIFF(A.NET_HOURS,'08:00:00')<'00:00:00','true','false') ");
				Emp_DOJ.append(" WHEN A.SHIFT='Second Shift'  THEN    IF(TIMEDIFF(A.NET_HOURS,'08:00:00')<'00:00:00','true','false') ");
				Emp_DOJ.append(" WHEN A.SHIFT='Night Shift'   THEN    IF(TIMEDIFF(A.NET_HOURS,'08:00:00')<'00:00:00','true','false') ");
				Emp_DOJ.append(" WHEN A.SHIFT='General'       THEN    IF(TIMEDIFF(A.NET_HOURS,'09:00:00')<'00:00:00','true','false') ");
				Emp_DOJ.append(" ELSE  IF(TIMEDIFF(A.NET_HOURS,'09:00:00')<'00:00:00','true','false') ");
				Emp_DOJ.append(" END AS DEDHOURS , ");
				Emp_DOJ.append(" date_format(A.DATEON,'%d-%m-%Y') AS DAYVIEW , date_format(now(),'%d-%m-%Y') As Newdate from test_mum.tbl_att_leave_in_out_status_report A ");
				Emp_DOJ.append(" join hclhrm_prod.tbl_employee_primary B ");
				Emp_DOJ.append(" ON B.employeesequenceno=A.employeeid and A.PAYPERIOD=0 ");
				Emp_DOJ.append(" left join hclhrm_prod_others.tbl_emp_leave_report C ");
				Emp_DOJ.append(" ON C.employeeid=B.employeesequenceno AND C.LEAVEON=A.DATEON AND C.MANAGER_STATUS IN ('A','P') and C.STATUS=1001  ");
				Emp_DOJ.append(" left join hclhrm_prod_others.tbl_emp_attn_req D ON D.EMPLOYEEID=B.employeesequenceno AND D.REQ_DATE=A.DATEON AND ");
				Emp_DOJ.append(" D.REQ_TYPE='AR' ");
				Emp_DOJ.append(" where B.employeesequenceno in("+username+") ");
				Emp_DOJ.append(" and a.DATEON BETWEEN  '"+UserMap.get("FROMDATE")+"' ");
				Emp_DOJ.append(" AND '"+UserMap.get("TODATE")+"' ");
				Emp_DOJ.append(" order by a.DATEON ");

			}




			HLDAYLIST.append("SELECT DISTINCT DATE_FORMAT(A.HOLIDAYDATE,'%d-%m-%Y') DAY,");
			HLDAYLIST.append(" TRIM(CONCAT(A.COMMENTS,'  ',IF(A.HOLIDAYTYPEID=0,'','(OPTINAL HLDAY)')))OCCASION");
			HLDAYLIST.append(" FROM");
			HLDAYLIST.append(" hclhrm_prod.tbl_holidays A");
			HLDAYLIST.append(" LEFT JOIN HCLHRM_PROD.TBL_HOLIDAY_TYPE B ON A.HOLIDAYTYPEID=B.HOLIDAYTYPEID");
			HLDAYLIST.append(" WHERE  A.statusid=1001  ");
			HLDAYLIST.append(" and A.businessunitid in( ");
			HLDAYLIST.append(" select companyid from hclhrm_prod.tbl_employee_primary where ");
			HLDAYLIST.append(" employeesequenceno in("+username+")) ");


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
				Res=(ResultSet)DataObj.FetchData(Months_ATT.toString(), "EMPLOYEE_ATTENDANCELIST", Res ,conn);
				while(Res.next()){

					ATT_MONTHS.put(Res.getString(1), Res.getString(2));

				}  

			}catch(Exception Er){
				System.out.println("Exception At Er::"+Er);
			}
			
			
			/*****************************Lines Added Spending OutSide Hours Permission Start************************************/
	        
			 Res=null;
	        
	        String FromDate=UserMap.get("FROMDATE").toString();
	        String ToDate=UserMap.get("TODATE").toString();
	        
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
				conn.setAutoCommit(false);
				Random rand = new Random();
				int nRand = rand.nextInt(90000) + 10000;

				ps=conn.prepareStatement("INSERT INTO hclhrm_prod_others.tbl_emp_attn_req (EMPLOYEEID,SUBJECT,REQ_DATE,MESSAGE,RANDOMID,FROM_DATE,TO_DATE,TOTA_HOURS,TO_EMAIL,CC_EMAIL) VALUES (?,?,?,?,?,?,?,?,?,?)");
				date=request.getParameter("id");
				Req_message=request.getParameter("message");
				subject=request.getParameter("Subject");

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
				if(count.length>0){
					conn.commit();
				}else{
					conn.rollback();
					Atten_Req_Message="Request Not Processed Please contact system admin";
				}
			}
			catch (Exception e2)
			{
				Atten_Req_Message="Faild To Process Your Request Please Contact System Admin.";
				e2.printStackTrace();  
			}

		} 


		if(User_Auth==1 && !Routing.equalsIgnoreCase("Att_Request") ){


			Res=null;
			DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            Date netHours;
            Date spendHours;
            if(Routing!=null && !Routing.equalsIgnoreCase("ReqSpenthours")&& !Routing.equalsIgnoreCase("ReqSpenthoursInsert")){

			try {
				Res=(ResultSet)DataObj.FetchData_Emp_DOB(Emp_DOJ.toString(), "Emp_DOJ", Res ,conn);
				while(Res.next()){

					Doj= new JSONObject();


					if(Routing.equalsIgnoreCase("ATTENDANCE_BIO")){

						Doj.put("DATE" , Res.getString("DAYVIEW")); 
						Doj.put("FIN" , Res.getString("FIN"));
						Doj.put("FOUT" ,Res.getString("FOUT"));
						Doj.put("PERDAY" , Res.getString("PERDAY"));
						Doj.put("INNER" , Res.getString("DAY")+"#"+Res.getString("FIN")+"#"+Res.getString("FOUT")+"#"+Res.getString("PERDAY"));
						Doj.put("DAYTYPE" , Res.getString("DAYTYPE"));
						Doj.put("DAREQ",Res.getString("STATUS"));
						netHours = sdf.parse(Res.getString("PERDAY").toString());
						try{

							Doj.put("LESSHOURS",Res.getString("DIFFHOURS").replace("#", ""));


						}catch(Exception err){
							Doj.put("LESSHOURS","00:00:00");
						}
						if(Res.getString("FIN").equalsIgnoreCase("00:00:00") && Res.getString("FOUT").equalsIgnoreCase("00:00:00")){

							Doj.put("LESSHOURS","00:00:00");
						}

						Doj.put("SHIFT",Res.getString("SHIFT"));
						if(Res.getString("SHIFT")!=null && Res.getString("SHIFT").equalsIgnoreCase("Morning Shift")){
							Doj.put("SHIFT","A");
						}else if(Res.getString("SHIFT")!=null && Res.getString("SHIFT").equalsIgnoreCase("Second Shift")){
							Doj.put("SHIFT","B");
						}else if(Res.getString("SHIFT")!=null && Res.getString("SHIFT").equalsIgnoreCase("Night Shift")){
							Doj.put("SHIFT","C");
						}else{
							Doj.put("SHIFT","G");
						}

						if((Res.getString("DAYTYPE").equalsIgnoreCase("WDAY") ||Res.getString("DAYTYPE").equalsIgnoreCase("WOFF")
								||Res.getString("DAYTYPE").equalsIgnoreCase("HL"))	&& (Res.getString("STATUS").equalsIgnoreCase("No Request") 
										&& !Res.getString("FIN").equalsIgnoreCase("00:00:00") && !Res.getString("FOUT").equalsIgnoreCase("00:00:00") 
										&& Res.getString("DEDHOURS").equalsIgnoreCase("true") )){


							if(currMonthflg && ATT_FLAG.equalsIgnoreCase("DATES") && !Res.getString("DAYVIEW").toString().equalsIgnoreCase(""+Res.getString("Newdate").toString()+"") ){

								Doj.put("DAF" , " ");

							}else{

								Doj.put("DAF" , "none");
							}

						}else{
							Doj.put("DAF" , "none");
						}
						
						
						/*******Mahesh Lines Added************************/
						String newHours;
						
						
						if(getHoursDetails!=null && getHoursDetails.get(username+"_Date_"+Res.getString("DAYVIEW").toString())!=null){
							
							newHours=getHoursDetails.get(username+"_Date_"+Res.getString("DAYVIEW")).toString();
							System.out.println(Doj.get("PERDAY").toString()+"<---newHours--->"+newHours);
							spendHours=sdf.parse(newHours);
							int totalSecs=(int) ((netHours.getTime() - spendHours.getTime())/1000);
							//System.out.println("~~~~~~~~~>"+(netHours.getTime() - spendHours.getTime())/1000);
							
							
							int posneg=(int)((sdf.parse(Doj.get("LESSHOURS").toString()).getTime()/1000)-(spendHours.getTime())/1000);
							
							int poh=posneg / 3600;
							int pom=(posneg % 3600) / 60;
							int pos=posneg % 60;
							
							int hours = totalSecs / 3600;
							int minutes = (totalSecs % 3600) / 60;
							int seconds = totalSecs % 60;
							
							System.out.println("Final Time--->"+String.format("%02d:%02d:%02d", hours, minutes,seconds));
							
						//	diff = (24 * 60 * 60 * 1000) + diff;
							
							String showTime;
							if(poh<0||pom<0||pos<0){
								showTime=String.format("-%02d:%02d:%02d",Integer.parseInt(Integer.toString(poh).replace("-", "")),Integer.parseInt(Integer.toString(pom).replace("-", "")),Integer.parseInt(Integer.toString(pos).replace("-", "")));
								Doj.put("DAF" , "");
							}else{
								showTime=String.format("%02d:%02d:%02d", poh, pom,pos);
							}
							
							System.out.println("showTime~~>"+showTime);
							Doj.put("LESSHOURS",showTime);
							if(totalSecs>0){
							Doj.put("PERDAY" ,String.format("%02d:%02d:%02d", hours, minutes,seconds));
							}
							
							
							
						}
						
						/********Mahesh Lines End************************/
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
					}

					values.add(Doj);
					dobcnt1++;

				}  
			}catch(Exception Er){
				System.out.println("Exception At Er::"+Er);
			}
            }

		}else{
			request.setAttribute("message",bundle_info.getString("HHCL_DESK_LOGI_FAILD")); 
		}

		if(dobcnt1<1){
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
				Doj.put("LESSHOURS","00:00:00");
				Doj.put("SHIFT","  ");


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
			request.getRequestDispatcher("HHCL_ATTENDANCE_ASSAM2.jsp").forward(request, response);  
		}
	}  

}
