package com.hhcdesk.service;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.mysql.jdbc.Statement;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@SuppressWarnings({"serial","rawtypes","unchecked","unused"})
public class Attendance2 extends HttpServlet {



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext c = getServletContext();


		HttpSession session = request.getSession(false);
		Statement statement=null;
		ResultSet Res;
		Connection conn=null;
		PreparedStatement ps=null;

		PrintWriter out = response.getWriter();

		int User_Auth=0;
		GetDbData DataObj=new GetDbData();


		JSONObject ATT_MONTHS = new JSONObject();

		int dobcnt1=0;
		JSONArray values;
		values = new JSONArray();

		String Routing=request.getParameter("Routing");
		String Month="currmonth";

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


		String date=null,Req_message=null,subject=null,Atten_Req_Message="Success Fully Submit Your Request";


		if(Routing.equalsIgnoreCase("Att_Request")){

			date=request.getParameter("id");
			Req_message=request.getParameter("message");
			subject=request.getParameter("Subject");
		}

		System.out.println("Routing  :::" +Routing);
		session.setAttribute("Notice" ,"N");
		StringBuffer Emp_DOJ=new StringBuffer();

		StringBuffer Months_ATT=new StringBuffer();



		StringBuffer HLDAYLIST=new StringBuffer();
		Map hlday=new HashMap();
		Map Att_Req=new HashMap();
		JSONObject  Doj= new JSONObject();


		ResourceBundle bundle_info =(ResourceBundle)(c.getAttribute("bundle"));

		String Query=bundle_info.getString("HHCL_DESK_USER_LOGIN");


		try {
			conn=(java.sql.Connection)session.getAttribute("ConnectionObj");
		} catch (Exception e1) {
			e1.printStackTrace();
		}


		String _SUCCESS_PAGE=null;
		String username=null;
		String password=null; 

		if(Routing.equalsIgnoreCase("ATTENDANCE_BIO") || Routing.equalsIgnoreCase("Att_Request") ){
			username=(String)session.getAttribute("EMP_ID");
			password=(String)session.getAttribute("EMP_PASSWORD");
		}

		StringBuffer User_Authen=new StringBuffer();
		User_Authen.append(Query);

		if(Routing.equalsIgnoreCase("ATTENDANCE_BIO")){

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
				System.out.println("Emp_DOJ:1"+Emp_DOJ);


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
					Emp_DOJ.append(" IF(DAYNAME(transactiontime)='SUNDAY','WOFF','WDAY')DAYTYPE, ");
					Emp_DOJ.append(" IF(SUBSTRING_INDEX(TIMEDIFF(SUBSTRING_INDEX(MAX(CASE WHEN  SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime, ' ', 1), ' ', -1) THEN SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime,' ', 2), ' ', -1)END), ' ', -1),SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(transactiontime, ' ', 2), ' ', 2), ' ', -1)),':',2)<'08:50',1,0)STATUS ");
					Emp_DOJ.append(" FROM ");
					Emp_DOJ.append(" unit_local_db.tbl_reader_log A ");
					Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY B ON A.EMPLOYEEID=B.EMPLOYEESEQUENCENO ");
					Emp_DOJ.append(" WHERE DATE_FORMAT(TRANSACTIONTIME,'%Y-%m-%d')  >=CONCAT(YEAR(NOW()),'-"+Month+"','-01') and DATE_FORMAT(TRANSACTIONTIME,'%Y-%m-%d')  <=LAST_DAY(CONCAT(YEAR(NOW()),'-"+Month+"','-01')) AND A.EMPLOYEEID="+username+" GROUP BY DAY ");
					Emp_DOJ.append(" UNION ALL ");
					Emp_DOJ.append(" select  DATE_FORMAT(DATE,'%d-%m-%Y')DAY,'00:00:00' AS FIN,'00:00:00' AS LOUT,'00:00' AS PERDAY,IF(DAYNAME(DATE)='SUNDAY','WOFF','WDAY')DAYTYPE,IF(DAYNAME(DATE)='SUNDAY','','AB') STATUS ");
					Emp_DOJ.append(" from ( ");
					Emp_DOJ.append(" select date_add(CONCAT(YEAR(NOW()),'-"+Month+"','-01'), INTERVAL n5.num*10000+n4.num*1000+n3.num*100+n2.num*10+n1.num DAY ) as date from ");
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
					Emp_DOJ.append(" where DATE_FORMAT(DATE,'%Y-%m-%d') >=CONCAT(YEAR(NOW()),'-"+Month+"','-01') and DATE_FORMAT(DATE,'%Y-%m-%d') <=LAST_DAY(CONCAT(YEAR(NOW()),'-"+Month+"','-01')) ");
					Emp_DOJ.append(" and date not in (select DATE_FORMAT(TRANSACTIONTIME,'%Y-%m-%d') from unit_local_db.tbl_reader_log ");
					Emp_DOJ.append(" where employeeid="+username+"  and DATE_FORMAT(TRANSACTIONTIME,'%Y-%m-%d') >= CONCAT(YEAR(NOW()),'-"+Month+"','-01') AND DATE_FORMAT(TRANSACTIONTIME,'%Y-%m-%d')<=LAST_DAY(CONCAT(YEAR(NOW()),'-"+Month+"','-01'))) ");
					Emp_DOJ.append(" ORDER BY DAY ");		

				}
				System.out.println("Emp_DOJ:::---------->"+Emp_DOJ.toString());	

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
				Res=(ResultSet)DataObj.FetchData(HLDAYLIST.toString(), "HLDAYLIST", Res ,conn);
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
				ATT_MONTHS.put("currmonth", "Current Month");
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
			
			StringBuffer updateReq=new StringBuffer();
			updateReq.append("INSERT INTO HCLHRM_PROD_OTHERS.TBL_EMP_ATTN_REQ");
			updateReq.append(" (EMPLOYEEID,SUBJECT,REQ_DATE,MESSAGE,RANDOMID,FROM_DATE,TO_DATE,TOTA_HOURS,TO_EMAIL,CC_EMAIL) ");
			updateReq.append(" VALUES");
			updateReq.append(" (?,?,?,?,?,?,?,?,?,?)");
			
			ps=null;
			try{	
				conn.setAutoCommit(false);

				Random rand = new Random();
				int nRand = rand.nextInt(90000) + 10000;

				ps=conn.prepareStatement(updateReq.toString());
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

		} // IF Close For Insertion



		if(User_Auth==1 && !Routing.equalsIgnoreCase("Att_Request") ){
			 
			if(Routing.equalsIgnoreCase("ATTENDANCE_BIO")){
				_SUCCESS_PAGE="HHCL_ATTENDANCE2.jsp";
			}

			Res=null;

			try {
				Res=(ResultSet)DataObj.FetchData_Emp_DOB(Emp_DOJ.toString(), "Emp_DOJ", Res ,conn);
				while(Res.next()){

					Doj= new JSONObject();


					if(Routing.equalsIgnoreCase("ATTENDANCE_BIO")){

						String Fin=Res.getString(2);
						String Fout=Res.getString(3);
						Doj.put("DATE" , Res.getString(1)); 
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

								// FOr Time Factor Calculation 
								String []INTime;
								String []OUTTime;

								int INH=0,INM=0,OUTH=0;

								try{
									INTime=Fin.split(":");
									OUTTime=Fout.split(":");

									INH=Integer.parseInt(INTime[0]);
									INM=Integer.parseInt(INTime[1]);
									OUTH=Integer.parseInt(OUTTime[0]);


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
							}
						}else{

							String []INTime;
							String []OUTTime;

							int INH=0,INM=0,OUTH=0;

							try{
								INTime=Fin.split(":");
								OUTTime=Fout.split(":");

								INH=Integer.parseInt(INTime[0]);
								INM=Integer.parseInt(INTime[1]);
								OUTH=Integer.parseInt(OUTTime[0]);


								if(INH==0 && INM==0 && OUTH==0){
									Doj.put("DAF" , "none");
								}else if(INH<9 && OUTH>=18){

									Doj.put("DAF" , "none");

								}else if((INH>=9 && INH<10 )&& OUTH>=18){

									if(INM<=10){
										Doj.put("DAF" , "none");
									}else{
										Doj.put("DAF" , "none");
									}


								}else if(INH>=10 | OUTH<18){

									Doj.put("DAF" , "none");
								}else{
									Doj.put("DAF" , "none");
								}

							}catch(Exception err){

								Doj.put("DAF" , "none");
							}
						}



						if(Att_Req.get(Res.getString(1))!=null){

							Doj.put("DAREQ" ,  Att_Req.get(Res.getString(1)).toString());
							Doj.put("DAF" , "none");


						}
						else{
							Doj.put("DAREQ" ,  "No Request");
						}

					}
					Doj.put("DAF" , "none"); // this is adding for new DAF Button Display none


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
		}else{
			request.getRequestDispatcher(_SUCCESS_PAGE).forward(request, response);  
		}
	}  
}