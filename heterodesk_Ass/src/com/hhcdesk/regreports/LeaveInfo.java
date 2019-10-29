package com.hhcdesk.regreports;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.json.simple.JSONObject;

import com.hhcdesk.db.GetDbData;

@SuppressWarnings({"serial","rawtypes","unchecked"})
public class LeaveInfo extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		java.sql.Connection conn=null;
		HttpSession session = request.getSession(false);
		
		String LogID=(String) session.getAttribute("EMP_ID");
		String EmpID=request.getParameter("EmpID");
		String Year=request.getParameter("Year");
		String Quantity=request.getParameter("Quantity");
		GetDbData DataObj=new GetDbData();
		ResultSet Res=null;
		PrintWriter out = response.getWriter();
		
		StringBuffer EmpDetails=new StringBuffer();
		StringBuffer EmpQuota=new StringBuffer();
		
		StringBuffer LeaveTypes=new StringBuffer();
		
		EmpDetails.append(" SELECT A.CALLNAME NAME,DATE_FORMAT(B.DATEOFJOIN,'%d-%m-%Y') DOJ,D.CODE DESI,E.CODE DEPT,F.NAME TYPE FROM");
		EmpDetails.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A");
		EmpDetails.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFILE B ON A.EMPLOYEEID=B.EMPLOYEEID");
		EmpDetails.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS C ON A.EMPLOYEEID=C.EMPLOYEEID");
		EmpDetails.append(" LEFT JOIN HCLADM_PROD.TBL_DESIGNATION D ON C.DESIGNATIONID=D.DESIGNATIONID");
		EmpDetails.append(" LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT E ON C.DEPARTMENTID=E.DEPARTMENTID");
		EmpDetails.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYMENT_TYPES F ON A.EMPLOYMENTTYPEID=F.EMPLOYMENTTYPEID");
		EmpDetails.append(" WHERE");
		EmpDetails.append(" A.EMPLOYEESEQUENCENO IN ("+EmpID+")"); 
		
		EmpQuota.append("SELECT C.SHORTNAME,B.QUANTITY FROM");
		EmpQuota.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A");
		EmpQuota.append(" LEFT JOIN HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_QUOTA B ON A.EMPLOYEEID=B.EMPLOYEEID");
		EmpQuota.append(" LEFT JOIN HCLHRM_PROD.TBL_LEAVE_TYPE C ON B.LEAVETYPEID=C.LEAVETYPEID");
		EmpQuota.append(" WHERE");
		EmpQuota.append(" C.STATUS=1001 AND B.YEAR="+Year+" AND A.EMPLOYEESEQUENCENO IN ("+EmpID+")");
		
		LeaveTypes.append("SELECT LEAVETYPEID,SHORTNAME FROM HCLHRM_PROD.TBL_LEAVE_TYPE where status=1001");
		
		 Enumeration $e = request.getParameterNames();
		 Map<Object, String> ParamsMap = new HashMap<Object, String>();
		
		 while($e.hasMoreElements())
		    {
		      Object obj = $e.nextElement();
		      String fieldName = (String) obj;
		      String fieldValue = request.getParameter(fieldName);
		      ParamsMap.put(fieldName, fieldValue);
		    }        
		
		JSONObject Test=new JSONObject();
		
		System.out.println(request.getParameter("Routing"));
		
		
		try {
			conn=(java.sql.Connection)session.getAttribute("ConnectionObj");
		} catch (Exception e1) {
				e1.printStackTrace();
		}
		if(request.getParameter("Routing") != null && request.getParameter("Routing").equalsIgnoreCase("EmpInfo")){
			try {
				Res=(ResultSet)DataObj.FetchData(EmpDetails.toString(), "EmpInfo", Res ,conn);
				while(Res.next()){
		        	Test.put("EmpName",Res.getString(1));
		        	Test.put("EmpDOJ",Res.getString(2));
		        	Test.put("EmpDESI",Res.getString(3));
		        	Test.put("EmpDEPT",Res.getString(4));
		        	Test.put("EmpTYPE",Res.getString(5));
		        }
				System.out.println(Test.toString());
				out.write(Test.toString());
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(request.getParameter("Routing") != null && request.getParameter("Routing").equalsIgnoreCase("LoadQuota")){
			try {
				Res=(ResultSet)DataObj.FetchData(EmpQuota.toString(), "LoadQuota", Res ,conn);
				
				Test.put("CL",0);
				Test.put("SL",0);
				Test.put("EL",0);
				
				
				while(Res.next()){
		        	Test.put(Res.getString(1),Res.getInt(2));
		        }
				System.out.println(Test.toString());
				out.write(Test.toString());
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		/*if(request.getParameter("Routing") != null && request.getParameter("Routing").equalsIgnoreCase("LeaveTypes")){
			
			
			try {
				 JSONArray Jsonarray=new JSONArray();
				Res=(ResultSet)DataObj.FetchData(LeaveTypes.toString(), "LeaveTypes", Res ,conn);
               
					LinkedHashMap<String, String> Jobj=new LinkedHashMap<String, String>();
					Jobj.put("value","Select");
					Jobj.put("label","Select");
					while (Res.next()) {
						 
						Jobj=new LinkedHashMap<String, String>();
						Jobj.put("value", Res.getString(1));
						Jobj.put("label", Res.getString(2));
						Jsonarray.add(Jobj);
					}
				
					out.write(Jsonarray.toString());
					
					System.out.println(Jsonarray.toString());
				
			} catch (SQLException e) {
				e.printStackTrace();
				
				//out.write(Jsonarray.toString());
				
			}
		}*/
		
		if(request.getParameter("Routing") != null && request.getParameter("Routing").equalsIgnoreCase("QuotaSubmission")){
			
			System.out.println("Sending...");
			
			String LeveTypeid=request.getParameter("LeveTypeid");
			
			String MINIMU_LEAVE="0";
			
			if(LeveTypeid.equalsIgnoreCase("4"))
			{
				MINIMU_LEAVE="3";
			}
			
			String MaxLeave=request.getParameter("MaxLeave");
			String CountWeekOff=request.getParameter("CountWeekOff");
			String Status=request.getParameter("Status");
			
		//	System.out.println(MaxLeave+"******"+CountWeekOff+"******"+Status);
			 
			 
			try {
				
				 
				 
				// String comma="";
				
		     //  System.out.println(Leave+"555");
				//System.out.println(Leave.split(",").length+"OOO");
				StringBuffer CLI=new StringBuffer();
				
				StringBuffer leaveQuotaAssign=new StringBuffer();
				
				
				leaveQuotaAssign.append(" INSERT INTO");
				leaveQuotaAssign.append(" HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_QUOTA");
				leaveQuotaAssign.append(" (EMPLOYEEID,LEAVETYPEID,YEAR,QUANTITY,AVAILABLEQTY,USEDQTY,DAYMODE,BACKDATE,COUNT_WOFF,COUNT_HOLIDAY,");
				leaveQuotaAssign.append(" STATUS,CREATEDBY, DATECREATED, LUPDATE, MINIMU_LEAVE, FOR_MONTH, LEAVE_MODE,MAXLEAVE)");

				leaveQuotaAssign.append(" SELECT EMPLOYEEID,? LEAVETYPEID,? YEAR,? QTY,? AVILABLEQTY,0.0 USEDQTY,? DAYMODE,");
				leaveQuotaAssign.append(" 30 BACKDATE,? COUNTWOFF,? COUNTHLDAY,? STATUS,? CREATEDBY,NOW() DATECREATED,");
				leaveQuotaAssign.append(" NOW()LUPDATE,? MINIMUMLEAVE,IF(EMPLOYMENTTYPEID=1,0,1) FORMONTH,'OP' LEAVEMODE ,IF(EMPLOYMENTTYPEID=1,?,1)MAXLEAVE");
				leaveQuotaAssign.append(" FROM");
				leaveQuotaAssign.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN (?)");
				
				
				
				PreparedStatement ps = conn.prepareStatement(leaveQuotaAssign.toString());
				
				ps.setString(1, LeveTypeid);
				ps.setString(2, Year);
				
				if(LeveTypeid.equalsIgnoreCase("14")){
					ps.setString(3, "0.0");
				}else{
					ps.setString(3, Quantity);
				}
				
				if(LeveTypeid.equalsIgnoreCase("14")){
					ps.setString(4, "0.0");
				}else{
					ps.setString(4, Quantity);
				}
				
				//Mode Configuration ~ Mahesh
				if(LeveTypeid.equalsIgnoreCase("14") || LeveTypeid.equalsIgnoreCase("15")){
					ps.setInt(5, 1);
				}else{
					ps.setInt(5, 0);
				}
				
				//Sundays Exemption ~ Mahesh
				if(LeveTypeid.equalsIgnoreCase("1")){
					ps.setInt(6, 1);
					ps.setString(7, "1.0");
				}else{
					ps.setInt(6, 0);
					ps.setString(7, "0.0");
				}
				
				ps.setInt(8,1001);
				ps.setInt(9, Integer.parseInt(LogID));
				if(LeveTypeid.equalsIgnoreCase("4")){
					ps.setInt(10,3);
				}else{
					ps.setInt(10,0);
				}
				if(LeveTypeid.equalsIgnoreCase("1")){
					ps.setInt(11,3);
				}else if(LeveTypeid.equalsIgnoreCase("14")){
					ps.setInt(11,365);
				}else{
					ps.setInt(11,Integer.parseInt(Quantity));
				}
				
				ps.setInt(12, Integer.parseInt(EmpID));
				
				
//				CLI.append("INSERT INTO HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_QUOTA(EMPLOYEEID, LEAVETYPEID,YEAR,QUANTITY,AVAILABLEQTY,USEDQTY,BACKDATE,STATUS,LOGID,CREATEDBY,DATECREATED,LUPDATE,MAXLEAVE,COUNT_WOFF,FOR_MONTH,COUNT_HOLIDAY,MINIMU_LEAVE) ");
//				CLI.append(" SELECT A.EMPLOYEEID,?,?,?,?,0,4,1001,0, ");
//				CLI.append(" (SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN (?)),NOW(),NOW(),?,?,?,?,? FROM ");
//				CLI.append("HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A WHERE EMPLOYEESEQUENCENO IN (?) ");
//				CLI.append(" ON DUPLICATE KEY UPDATE EMPLOYEEID=A.EMPLOYEEID,LEAVETYPEID=?,YEAR=?, ");
//				CLI.append(" MODIFIEDBY=(SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN (?)),DATEMODIFIED=NOW(),QUANTITY=?,MAXLEAVE=?,COUNT_WOFF=?,FOR_MONTH=?,COUNT_HOLIDAY=?,MINIMU_LEAVE=?,AVAILABLEQTY=?");
				
			//	System.out.println(CLI.toString());
				
				PreparedStatement ps1 = conn.prepareStatement(CLI.toString());
				 
				/* for(int i=0;i<LeveTypeid.split(",").length;i++)
				 {*/
					 
//					 System.out.println(Leave.split(",")[i]);
//					 System.out.println(Quantity.split(",")[i]);
//					 System.out.println(EmpID.split(",")[i]);
					 
					/* ps1.setString(1, LeveTypeid);
                     ps1.setString(2, Year);
                     ps1.setString(3, Quantity);
                     ps1.setString(4, Quantity);
                     ps1.setString(5, EmpID);
                     ps1.setString(6, MaxLeave);
                     ps1.setString(7, CountWeekOff);
                     ps1.setString(8,Status);
                     ps1.setString(9,CountWeekOff);
                     ps1.setString(10,MINIMU_LEAVE);
                    // ps1.setString(7, EmpID);
                     ps1.setString(11, EmpID);
                     ps1.setString(12, LeveTypeid);
                     ps1.setString(13, Year);
                     ps1.setString(14, EmpID);
                     ps1.setString(15, Quantity);
                     ps1.setString(16, MaxLeave);
                     ps1.setString(17, CountWeekOff);
                     ps1.setString(18,Status);
                     ps1.setString(19,CountWeekOff);
                     ps1.setString(20,MINIMU_LEAVE);
                     ps1.setString(21,Quantity);*/
                    
                    // System.out.println(ps1.toString());
                     String errCode="";
                     try{
                    	 System.out.println("Before Quota-->"+leaveQuotaAssign.toString());
                    	 //ps.executeUpdate();
                    	 ps.execute();
                    	 errCode="1";
                     }catch(Exception e){
                    	 System.out.println("1~~>"+e.getMessage());
                    	 System.out.println("~~>"+e.getMessage().split(" ")[0]+" "+e.getMessage().split(" ")[1]);
                    	 errCode="2";
                    	 
                     }
					 
					/* Buffer.append(comma).append(prop.getProperty(ColumNames.split(",")[i]));
					 comma = ",";*/
					 
				/*
				CLI.append("INSERT INTO HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_QUOTA(EMPLOYEEID, LEAVETYPEID,YEAR,QUANTITY,AVAILABLEQTY,USEDQTY,BACKDATE,STATUS,LOGID,CREATEDBY,DATECREATED,LUPDATE) ");
				CLI.append(" SELECT A.EMPLOYEEID,"+Leave.split(",")[i]+","+Year+","+Quantity.split(",")[i]+",0.0,0,0.0,1001,0, ");
				CLI.append(" (SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN ("+EmpID.split(",")[i]+")),NOW(),NOW() FROM ");
				CLI.append("HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A WHERE EMPLOYEESEQUENCENO IN ("+EmpID.split(",")[i]+") ");
				CLI.append(" ON DUPLICATE KEY UPDATE EMPLOYEEID=A.EMPLOYEEID,LEAVETYPEID="+Leave.split(",")[i]+",YEAR="+Year+", ");
				CLI.append(" MODIFIEDBY=(SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN ("+EmpID.split(",")[i]+")),DATEMODIFIED=NOW(),QUANTITY="+Leave.split(",")[i]+"");
				*/
				
				
				//System.out.println(CLI.toString());
				/*CLI.append("INSERT INTO HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_QUOTA");
				CLI.append(" (EMPLOYEEID, LEAVETYPEID,YEAR,QUANTITY,AVAILABLEQTY,USEDQTY,STATUS,LOGID,CREATEDBY,DATECREATED,LUPDATE)");
				CLI.append(" SELECT A.EMPLOYEEID,"+CL+","+Year+","+ParamsMap.get("CL")+",0.0,0.0,1001,0,(SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN ("+LogID+")),NOW(),NOW() FROM");
				CLI.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A WHERE EMPLOYEESEQUENCENO IN ("+EmpID+")");
				CLI.append(" ON DUPLICATE KEY UPDATE EMPLOYEEID=A.EMPLOYEEID,LEAVETYPEID="+CL+",YEAR="+Year+",MODIFIEDBY=(SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN ("+LogID+")),DATEMODIFIED=NOW(),");
				CLI.append(" QUANTITY="+ParamsMap.get("CL")+" ");
				
				SLI.append("INSERT INTO HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_QUOTA");
				SLI.append(" (EMPLOYEEID, LEAVETYPEID,YEAR,QUANTITY,AVAILABLEQTY,USEDQTY,STATUS,LOGID,CREATEDBY,DATECREATED,LUPDATE)");
				SLI.append(" SELECT A.EMPLOYEEID,"+SL+","+Year+","+ParamsMap.get("SL")+",0.0,0.0,1001,0,(SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN ("+LogID+")),NOW(),NOW() FROM");
				SLI.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A WHERE EMPLOYEESEQUENCENO IN ("+EmpID+")");
				SLI.append(" ON DUPLICATE KEY UPDATE EMPLOYEEID=A.EMPLOYEEID,LEAVETYPEID="+SL+",YEAR="+Year+",MODIFIEDBY=(SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN ("+LogID+")),DATEMODIFIED=NOW(),");
				SLI.append(" QUANTITY="+ParamsMap.get("SL")+" ");
				
				ELI.append("INSERT INTO HCLHRM_PROD_OTHERS.TBL_EMP_LEAVE_QUOTA");
				ELI.append(" (EMPLOYEEID, LEAVETYPEID,YEAR,QUANTITY,AVAILABLEQTY,USEDQTY,STATUS,LOGID,CREATEDBY,DATECREATED,LUPDATE)");
				ELI.append(" SELECT A.EMPLOYEEID,"+EL+","+Year+","+ParamsMap.get("EL")+",0.0,0.0,1001,0,(SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN ("+LogID+")),NOW(),NOW() FROM");
				ELI.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A WHERE EMPLOYEESEQUENCENO IN ("+EmpID+")");
				ELI.append(" ON DUPLICATE KEY UPDATE EMPLOYEEID=A.EMPLOYEEID,LEAVETYPEID="+EL+",YEAR="+Year+",MODIFIEDBY=(SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN ("+LogID+")),DATEMODIFIED=NOW(),");
				ELI.append(" QUANTITY="+ParamsMap.get("EL")+" ");*/
				
				
				 
				 /*  ps1.addBatch();
				
				 }
				 ps1.executeBatch();*/
				/*try{
					PreparedStatement ps = conn.prepareStatement(CLI.toString());
					ps.executeUpdate();
				}
				catch(SQLException p){
					p.printStackTrace();
				} 
				out.write("1");*/
				 
                     out.write(errCode);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		//----
		
		
  if(request.getParameter("Routing") != null && request.getParameter("Routing").equalsIgnoreCase("GetQuantity")){
			
			System.out.println("Sending...");
			
			String Leve=request.getParameter("Leave");
			
			StringBuffer Leave=new StringBuffer();
			 
			try {
				//StringBuffer CLI=new StringBuffer();
				Leave.append("SELECT QUANTITY, AVAILABLEQTY, USEDQTY, HOLD, DAYMODE, MAXLEAVE FROM hclhrm_prod_others.tbl_emp_leave_quota where employeeid in (select employeeid from hclhrm_prod.tbl_employee_primary where employeesequenceno="+EmpID+") and leavetypeid="+Leve+" and year="+Year+"");
				 
				 System.out.println(Leave.toString());
				//PreparedStatement ps1 = conn.prepareStatement(CLI.toString());
				 
				Res=(ResultSet)DataObj.FetchData(Leave.toString(), "Leave", Res ,conn);
				
				if(!Res.isBeforeFirst())
				{
					Test.put("QUANTITY","0");
		        	Test.put("AVAILABLEQTY","0");
		        	Test.put("USEDQTY","0");
		        	Test.put("HOLD","0");
		        	Test.put("DAYMODE","0");
		        	Test.put("MAXLEAVE","0");
				}
				
				
				while(Res.next()){
		        	Test.put("QUANTITY",Res.getString(1));
		        	Test.put("AVAILABLEQTY",Res.getString(2));
		        	Test.put("USEDQTY",Res.getString(3));
		        	Test.put("HOLD",Res.getString(4));
		        	Test.put("DAYMODE",Res.getString(5));
		        	Test.put("MAXLEAVE",Res.getString(6));
		        	//values.add(Test);
		        }
				System.out.println(Test.toString()+"*************");
				out.write(Test.toString());
				 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Designation Class 
		
		StringBuffer DesiList = new StringBuffer();
		
		DesiList.append("SELECT @a:=@a+1 SNO,DESIGNATIONID,NAME,CODE,IF(STATUS=1001,'ACTIVE','INACTIVE')Status FROM HCLADM_PROD.TBL_DESIGNATION,(SELECT @a:= 0) AS a WHERE STATUS=1001 ORDER BY NAME");
		
		if(request.getParameter("Routing") != null && request.getParameter("Routing").equalsIgnoreCase("DesignationLoad")){
			try {
				JSONArray values=new JSONArray();;
				Res=(ResultSet)DataObj.FetchData(DesiList.toString(), "DesignationLoad", Res ,conn);
				while(Res.next()){
					String Name=Res.getString(3);
					String Code=Res.getString(4);
					Test.put("SNO", Res.getInt(1));
					Test.put("DesiID", Res.getInt(2));
		        	Test.put("Name",Name);
		        	Test.put("Code",Code);
		        	Test.put("Status",Res.getString(5));
		        	values.add(Test);
		        }
				out.write(values.toString());
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("Routing") != null && request.getParameter("Routing").equalsIgnoreCase("GetBUDesiList")){
			
			StringBuffer BUDesi=new StringBuffer();
			String BUID=request.getParameter("CompanyID");
			
			BUDesi.append(" SELECT B.DESIGNATIONID,1 ISCHECK,A.NAME,A.CODE,'ACTIVE' STATUS FROM");
			BUDesi.append(" HCLADM_PROD.TBL_DESIGNATION A");
			BUDesi.append(" LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT_DESIGNATIONS B ON A.DESIGNATIONID=B.DESIGNATIONID");
			BUDesi.append(" WHERE B.BUSINESSUNITID="+BUID+" AND B.STATUS=1001 AND A.STATUS=1001");
			BUDesi.append(" UNION ALL");
			BUDesi.append(" SELECT DESIGNATIONID,0 ISCHECK,NAME,CODE,'ACTIVE' STATUS FROM");
			BUDesi.append(" HCLADM_PROD.TBL_DESIGNATION");
			BUDesi.append(" WHERE DESIGNATIONID NOT IN");
			BUDesi.append(" (SELECT DESIGNATIONID FROM HCLADM_PROD.TBL_BUSINESSUNIT_DESIGNATIONS WHERE BUSINESSUNITID="+BUID+" AND STATUS=1001)");
			BUDesi.append(" AND STATUS=1001");
			BUDesi.append(" ORDER BY NAME");
			
			
			try {
				JSONArray values=new JSONArray();;
				Res=(ResultSet)DataObj.FetchData(BUDesi.toString(), "GetBUDesiList", Res ,conn);
				while(Res.next()){
					String Name=Res.getString(3);
					String Code=Res.getString(4);
					Test.put("SNO", Res.getInt(1));
					Test.put("DesiID", Res.getInt(1));
		        	Test.put("Name",Name);
		        	Test.put("Code",Code);
		        	Test.put("Status",Res.getString(5));
		        	Test.put("ISCHECK",Res.getInt(2));
		        	values.add(Test);
					
		        }
				
				out.write(values.toString());
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//Assign
		if(request.getParameter("Routing") != null && request.getParameter("Routing").equalsIgnoreCase("Assign")){
			
			StringBuffer BUDesi=new StringBuffer();
			String BUID=request.getParameter("CompanyID");
			String DesiID=request.getParameter("DesiID");
//			String Status=request.getParameter("Check");
			
			boolean Status= Boolean.parseBoolean(request.getParameter("Check")); 
			System.out.println(Status+"Status");
			
			if(Status){
				System.out.println("If");
			BUDesi.append("INSERT INTO HCLADM_PROD.TBL_BUSINESSUNIT_DESIGNATIONS (BUSINESSUNITID,DESIGNATIONID,STATUS,CREATEDBY,DATECREATED,LUPDATE) VALUES");
			BUDesi.append(" ("+BUID+","+DesiID+",1001,(SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN ("+LogID+")),NOW(),NOW()) ");
			BUDesi.append("ON DUPLICATE KEY UPDATE  BUSINESSUNITID="+BUID+",DESIGNATIONID="+DesiID+" , STATUS=1001,DATECREATED=NOW()");
			}else{
				System.out.println("Else");
			BUDesi.append("INSERT INTO HCLADM_PROD.TBL_BUSINESSUNIT_DESIGNATIONS (BUSINESSUNITID,DESIGNATIONID,STATUS,CREATEDBY,DATECREATED,LUPDATE) VALUES");
			BUDesi.append(" ("+BUID+","+DesiID+",1002,(SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN ("+LogID+")),NOW(),NOW()) ");
			BUDesi.append("ON DUPLICATE KEY UPDATE  BUSINESSUNITID="+BUID+",DESIGNATIONID="+DesiID+" , STATUS=1002,DATECREATED=NOW()");
			}
			
			try{
				PreparedStatement ps = conn.prepareStatement(BUDesi.toString());
				System.out.println(ps);
				ps.executeUpdate();
				out.write("1");
			}
			catch(SQLException p){
				p.printStackTrace();
			}
		}
		
		if(request.getParameter("Routing") != null && request.getParameter("Routing").equalsIgnoreCase("ReDec-BuList")){
			
			StringBuffer GetBU=new StringBuffer();
			 GetBU.append(" SELECT C.BUSINESSUNITID,C.SHORTNAME FROM");
			 GetBU.append(" HCLHRM_PROD.TBL_EMPLOYEE_BUSINESSUNIT B");
			 GetBU.append(" LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT C ON B.BUSINESSUNITID=C.BUSINESSUNITID");
			 GetBU.append(" WHERE");
			 GetBU.append(" B.EMPLOYEEID IN (SELECT EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN ("+LogID+")) AND C.STATUS=1001 ORDER BY BUSINESSUNITID");
			try{
				Res=(ResultSet)DataObj.FetchData(GetBU.toString(), "GetBUList", Res ,conn);
				while(Res.next()){
					Test.put(Res.getString(2), Res.getInt(1));
		        }
				out.write(Test.toString());
			}
			catch(SQLException p){
				p.printStackTrace();
			}
		}
		if(request.getParameter("Routing") != null && request.getParameter("Routing").equalsIgnoreCase("ReDec-EmpList")){
			String BUID=request.getParameter("BU");
			
			StringBuffer GetEmpList=new StringBuffer();
			GetEmpList.append("SELECT A.EMPLOYEESEQUENCENO EmpCode,A.CALLNAME Name,F.NAME BU,K.NAME EmpStatus,");
			GetEmpList.append(" DATE_FORMAT(IFNULL(B.DATEOFJOIN,''),'%d.%m.%Y')DOJ,IFNULL(H.CODE,'--') DEPT,IFNULL(G.CODE,'--')DESI,IFNULL(E.PAN,'--')PAN,");
			GetEmpList.append(" CAST(IFNULL(J.GROSS+J.ADHOC,0) AS DECIMAL(20,2))GROSS,");
			GetEmpList.append(" IF(D.STATUS=1001,'Declared',IFNULL(D.STATUS,'Not Declared'))Status,");
			GetEmpList.append(" IFNULL(DATE_FORMAT(D.LUPDATE,'%d.%m.%Y %H:%m'),'--')LastUpdate,");
			GetEmpList.append(" IF(D.STATUS=1001,1,0)ReDeclare FROM");
			GetEmpList.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A");
			GetEmpList.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFILE B ON A.EMPLOYEEID=B.EMPLOYEEID");
			GetEmpList.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS C ON A.EMPLOYEEID=C.EMPLOYEEID");
			GetEmpList.append(" LEFT JOIN");
			GetEmpList.append(" (SELECT EMPLOYEEID,FY,STATUS,LUPDATE FROM TEST.TBL_EMP_IT_TDS WHERE FY= '2017-2018')D ON A.EMPLOYEESEQUENCENO=D.EMPLOYEEID" );
			GetEmpList.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PERSONALINFO E ON A.EMPLOYEEID=E.EMPLOYEEID");
			GetEmpList.append(" LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT F ON A.COMPANYID=F.BUSINESSUNITID");
			GetEmpList.append(" LEFT JOIN HCLADM_PROD.TBL_DESIGNATION G ON C.DESIGNATIONID=G.DESIGNATIONID");
			GetEmpList.append(" LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT H ON H.DEPARTMENTID=C.DEPARTMENTID");
			GetEmpList.append(" LEFT JOIN");
			GetEmpList.append(" (SELECT MAX(CTCTRANSACTIONID)CID,EMPLOYEEID FROM HCLHRM_PROD.TBL_EMPLOYEE_CTC GROUP BY EMPLOYEEID)I ON I.EMPLOYEEID=A.EMPLOYEEID");
			GetEmpList.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_CTC J ON J.CTCTRANSACTIONID=I.CID");
			GetEmpList.append(" LEFT JOIN HCLADM_PROD.TBL_STATUS_CODES K ON A.STATUS=K.STATUS");
			GetEmpList.append(" WHERE A.COMPANYID IN ("+BUID+") GROUP BY A.EMPLOYEEID ORDER BY GROSS DESC");
			
			
			try{
				JSONArray values=new JSONArray();
				Res=(ResultSet)DataObj.FetchData(GetEmpList.toString(), "GetBUList", Res ,conn);
				while(Res.next()){
					Test.put("ID", Res.getString(1));
					Test.put("Name", Res.getString(2));
					Test.put("BU", Res.getString(3));
					Test.put("EmpStatus", Res.getString(4));
					Test.put("DOJ", Res.getString(5));
					Test.put("DEPT", Res.getString(6));
					Test.put("DESI", Res.getString(7));
					Test.put("PAN", Res.getString(8));
					Test.put("GR", Res.getString(9));
					Test.put("Status", Res.getString(10));
					Test.put("LUP", Res.getString(11));
					Test.put("ReD", Res.getString(12));
					values.add(Test);
					
		        }
				out.write(values.toString());
			}
			catch(SQLException p){
				p.printStackTrace();
			}
			
		}
		
		
		
		
		
	}
}