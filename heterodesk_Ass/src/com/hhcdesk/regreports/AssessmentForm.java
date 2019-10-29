package com.hhcdesk.regreports;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.hhcdesk.db.GetDbData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Assessment Form-Mahesh-HHCL
*/

@SuppressWarnings({"serial","rawtypes"})
public class AssessmentForm extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Date date= new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year =cal.get(Calendar.YEAR);
		int month = 1+cal.get(Calendar.MONTH);
		
		JSONArray detailsarray =  new JSONArray();
		
		JSONObject  jsnObj= new JSONObject();
		JSONArray jsnAry = new JSONArray();
		GetDbData DataObj=new GetDbData();
		HttpSession session = request.getSession(false);
		Connection conn=null;
		
		ResultSet Res=null;
		String eCode=(String)session.getAttribute("EMP_ID");
		String Routing=request.getParameter("Routing");
		System.out.println("Routing~~>"+Routing);
		
		String mrgFiller=request.getParameter("mrgFiller");
		System.out.println("mrgFiller~~>"+mrgFiller);
		
		//getEmpDetails
		
		if(Routing!=null && Routing.equalsIgnoreCase("FormAssessment") ){
			// Check Form Filled or Not
		}
		StringBuffer empConfList=new StringBuffer();
		
		empConfList.append("SELECT distinct A.EMPLOYEESEQUENCENO ID,A.CALLNAME NAME,F.NAME,E.NAME BU,DATE_FORMAT(B.DATEOFJOIN,'%d-%m-%Y')DOJ,");
		empConfList.append(" DATE_FORMAT(IF (NOW()>DATE_ADD(DATE_FORMAT(DATE_ADD(B.DATEOFJOIN, INTERVAL (IF(DAY(B.DATEOFJOIN)>15,1,0)) MONTH),'%Y-%m-01'),");
		empConfList.append(" INTERVAL 6 MONTH),DATE_ADD(DATE_FORMAT(DATE_ADD(B.DATEOFJOIN, INTERVAL (IF(DAY(B.DATEOFJOIN)>15,1,0)) MONTH),'%Y-%m-01'),INTERVAL 6 MONTH),'Next Month'),'%d-%m-%Y')DOC,");
		empConfList.append(" C.NAME TYPE,IFNULL(CONCAT(UPPER(D.COMMENTS),\" FROM \",DATE_FORMAT(D.ONDATE,'%d-%m-%Y')),'---')ANYREASON,");
		// change 2
		empConfList.append(" if(ed.employeeid is null,'Update Details',if(ad.lupdate is null,IF(I.CREATEDBY=H.EMPLOYEESEQUENCENO,'View','Fill Form'), ");
		empConfList.append(" if( DATE_ADD(ad.lupdate, INTERVAL ad.EXTENDMONTHS MONTH)< now() , IF(I.CREATEDBY=H.EMPLOYEESEQUENCENO,'View','Fill Form'),'Probitionary extended'))  )   Button ");
		
		empConfList.append(" ,IF(I.CREATEDBY=H.EMPLOYEESEQUENCENO,1001,1002) status, ");
		empConfList.append(" if(ed.employeeid is null,1001, ");
		empConfList.append(" if(ad.lupdate is null,IF(I.CREATEDBY=H.EMPLOYEESEQUENCENO,1002,1002), ");
		empConfList.append(" if( DATE_ADD(ad.lupdate, INTERVAL ad.EXTENDMONTHS MONTH)< now() , ");
		empConfList.append("  IF(I.CREATEDBY=H.EMPLOYEESEQUENCENO,1002,1002),1001))  )   Buttonstatus ");
		empConfList.append(" ");
	
		///
		empConfList.append(" FROM");
		empConfList.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A");
		empConfList.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFILE B ON A.EMPLOYEEID=B.EMPLOYEEID");
		empConfList.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYMENT_TYPES C ON A.EMPLOYMENTTYPEID=C.EMPLOYMENTTYPEID");
		empConfList.append(" LEFT JOIN TEST.TBL_EMPLOYEE_CONFIRMATION_DETAILS D ON A.EMPLOYEEID=D.EMPLOYEEID");
		empConfList.append(" LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT E ON A.COMPANYID=E.BUSINESSUNITID");
		empConfList.append(" LEFT JOIN HCLADM_PROD.TBL_COSTCENTER F ON A.COSTCENTERID=F.COSTCENTERID");
		empConfList.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS G ON A.EMPLOYEEID=G.EMPLOYEEID");
		empConfList.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY H ON G.MANAGERID=H.EMPLOYEEID");
		
		empConfList.append(" LEFT JOIN (select * from TEST.TBL_EMPLOYEE_ASSESSMENT_COMMENTS where approvalstatus='1001' ) I ON A.EMPLOYEESEQUENCENO=I.EMPLOYEESEQUENCENO AND I.CREATEDBY=H.EMPLOYEESEQUENCENO");
		// chnages 2
		empConfList.append(" LEFT JOIN TEST.TBL_EMPLOYEE_ASSESSMENT_DETAILS  ad ON A.EMPLOYEESEQUENCENO=ad.EMPLOYEESEQUENCENO ");
		empConfList.append(" left join (select employeeid from HCLHRM_PROD.TBL_EMPLOYEE_EDUCATION_DETAILS  group by employeeid) ed on a.employeeid=ed.employeeid ");
		//
		empConfList.append(" WHERE");
		empConfList.append(" E.CALLNAME IN ('HYD') AND E.CODE IN ('HHC','AZISTA') AND A.EMPLOYMENTTYPEID!=1 AND A.STATUS=1001");
		empConfList.append(" AND DATE_ADD(DATE_FORMAT(DATE_ADD(B.DATEOFJOIN, INTERVAL (IF(DAY(B.DATEOFJOIN)>15,1,0)) MONTH),'%Y-%m-01'),INTERVAL 6 MONTH) BETWEEN B.DATEOFJOIN AND '"+year+"-"+month+"-15' ");
		empConfList.append(" AND A.EMPLOYEESEQUENCENO NOT IN (20206,70000,70001) AND IF(E.CODE='AZISTA',F.NAME IN('OFFICE','FIELD'),F.NAME='OFFICE')");
	 	empConfList.append(" AND H.EMPLOYEESEQUENCENO IN ("+eCode+")");
		//empConfList.append(" ORDER BY B.DATEOFJOIN DESC");
		empConfList.append(" UNION ALL");
		empConfList.append(" SELECT distinct  B.EMPLOYEESEQUENCENO ID,B.CALLNAME,'',C.NAME BU,DATE_FORMAT(D.DATEOFJOIN,'%d-%m-%Y')DOJ,");
		// change 2
		// empConfList.append(" '',E.NAME TYPE,'',IF(F.CREATEDBY=A.NEXTAPPROVAL,'View','Fill Form')Button ");
		empConfList.append(" '',E.NAME TYPE,'',if(edu.employeeid is null,'Update Details',if(ead.lupdate is null,IF(F.CREATEDBY=A.NEXTAPPROVAL,'View','Fill Form'), ");
		empConfList.append("  if( DATE_ADD(ead.lupdate, INTERVAL ead.EXTENDMONTHS MONTH)< now() , ");
		empConfList.append(" IF(F.CREATEDBY=A.NEXTAPPROVAL,'View','Fill Form'),'Probitionary extended')) ) Button");
		empConfList.append(" ,IF(F.CREATEDBY=A.NEXTAPPROVAL,1001,1002) status, ");
		
		empConfList.append(" if(edu.employeeid is null,1001, ");
		empConfList.append(" if(ead.lupdate is null,IF(F.CREATEDBY=A.NEXTAPPROVAL,1002,1002), ");
		empConfList.append(" if( DATE_ADD(ead.lupdate, INTERVAL ead.EXTENDMONTHS MONTH)< now() , ");
		empConfList.append("  IF(F.CREATEDBY=A.NEXTAPPROVAL,1002,1002),1001)) ) Buttonstatus ");
		
		///
		empConfList.append(" FROM (select * from TEST.TBL_EMPLOYEE_ASSESSMENT_COMMENTS where approvalstatus='1001' ) A");
		empConfList.append(" JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY B ON A.EMPLOYEESEQUENCENO=B.EMPLOYEESEQUENCENO");
		empConfList.append(" LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT C ON B.COMPANYID=C.BUSINESSUNITID");
		empConfList.append(" JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFILE D ON B.EMPLOYEEID=D.EMPLOYEEID");
		empConfList.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYMENT_TYPES E ON B.EMPLOYMENTTYPEID=E.EMPLOYMENTTYPEID");
		empConfList.append(" LEFT JOIN");
		empConfList.append(" (SELECT EMPLOYEESEQUENCENO,CREATEDBY FROM");
		empConfList.append(" TEST.TBL_EMPLOYEE_ASSESSMENT_COMMENTS WHERE approvalstatus='1001' and CREATEDBY IN ("+eCode+"))F ON A.EMPLOYEESEQUENCENO=F.EMPLOYEESEQUENCENO");
		// changes 2
		empConfList.append(" LEFT JOIN TEST.TBL_EMPLOYEE_ASSESSMENT_DETAILS  ead ON B.EMPLOYEESEQUENCENO=ead.EMPLOYEESEQUENCENO ");
		empConfList.append(" left join (select employeeid from HCLHRM_PROD.TBL_EMPLOYEE_EDUCATION_DETAILS  group by employeeid) edu on b.employeeid=edu.employeeid ");
		//
		empConfList.append(" WHERE");
		empConfList.append(" A.NEXTAPPROVAL IN ("+eCode+") AND B.EMPLOYMENTTYPEID!=1 AND A.STATUS=1001 ");
		System.out.println("empConfList~~>"+empConfList);
		
      try {
    	  
			conn=(java.sql.Connection)session.getAttribute("ConnectionObj");
			conn.setAutoCommit(false);
		} catch (Exception e1) {
				e1.printStackTrace();
		}
		Res=null; 
		jsnObj.put("Code","");
		jsnObj.put("Name","");
		jsnObj.put("Bu","");
		jsnObj.put("DOJ","");
		jsnObj.put("empType","");
		jsnObj.put("empButton","");
		jsnObj.put("empButtonstatus","");
		
		try {
			Res=(ResultSet)DataObj.FetchData(empConfList.toString(), "ConfmList", Res ,conn);
		int count=0;	
			while(Res.next()){
				jsnObj= new JSONObject();
				jsnObj.put("Code",Res.getString(1));
				jsnObj.put("Name",Res.getString(2));
				jsnObj.put("Bu",Res.getString(4));
				jsnObj.put("DOJ",Res.getString(5));
				jsnObj.put("empType",Res.getString(7));
				jsnObj.put("empButton",Res.getString("Button"));
				jsnObj.put("empButtonstatus",Res.getString(11));
				jsnAry.add(jsnObj);
				System.out.println(count+"hheeeemaaaa"+jsnAry);
				System.out.println(count+"hheeeemaaaa222"+jsnObj);
				count ++;
			}  
		}catch(Exception Er){
			System.out.println("Exception At Er:1:"+Er);
		}finally {
			
			try {
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		System.out.println("--------------------------------------------------------"+jsnAry);
		/*StringBuffer pendingEmps=new StringBuffer();
		pendingEmps.append(" SELECT B.EMPLOYEESEQUENCENO ID,B.CALLNAME,'',C.NAME BU,DATE_FORMAT(D.DATEOFJOIN,'%d-%m-%Y')DOJ,");
		pendingEmps.append(" '',E.NAME TYPE,'',IF(COUNT(*)=2,'View','Fill Form') Button FROM");
		pendingEmps.append(" TEST.TBL_EMPLOYEE_ASSESSMENT_COMMENTS A");
		pendingEmps.append(" JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY B ON A.EMPLOYEESEQUENCENO=B.EMPLOYEESEQUENCENO");
		pendingEmps.append(" LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT C ON B.COMPANYID=C.BUSINESSUNITID");
		pendingEmps.append(" JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFILE D ON B.EMPLOYEEID=D.EMPLOYEEID");
		pendingEmps.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYMENT_TYPES E ON B.EMPLOYMENTTYPEID=E.EMPLOYMENTTYPEID");
		pendingEmps.append(" WHERE");
		pendingEmps.append(" A.NEXTAPPROVAL IN ("+eCode+") OR A.CREATEDBY=20206 AND B.EMPLOYMENTTYPEID!=1 AND A.STATUS=1001 ");
		Res=null;
		try {
			Res=(ResultSet)DataObj.FetchData(pendingEmps.toString(), "ConfmList", Res ,conn);
			while(Res.next()){
				jsnObj= new JSONObject();
				jsnObj.put("Code",Res.getString(1));
				jsnObj.put("Name",Res.getString(2));
				jsnObj.put("Bu",Res.getString(4));
				jsnObj.put("DOJ",Res.getString(5));
				jsnObj.put("empType",Res.getString(7));
				jsnObj.put("empButton",Res.getString(9));
				jsnAry.add(jsnObj);
			}  
		}catch(Exception Er){
			System.out.println("Exception At Er:1:"+Er);
		}*/
		request.setAttribute("assessmentObj",jsnAry.toString());
		
	//} // by VENU
		/*
		SELECT A.EMPLOYEESEQUENCENO ID,A.CALLNAME NAME,F.NAME,E.NAME BU,DATE_FORMAT(B.DATEOFJOIN,'%d-%m-%Y')DOJ,
		DATE_FORMAT(IF (NOW()>DATE_ADD(DATE_FORMAT(DATE_ADD(B.DATEOFJOIN, INTERVAL (IF(DAY(B.DATEOFJOIN)>15,1,0)) MONTH),'%Y-%m-01'),
		INTERVAL 6 MONTH),DATE_ADD(DATE_FORMAT(DATE_ADD(B.DATEOFJOIN, INTERVAL (IF(DAY(B.DATEOFJOIN)>15,1,0)) MONTH),'%Y-%m-01'),INTERVAL 6 MONTH),'Next Month'),'%d-%m-%Y')DOC,
		C.NAME TYPE,IFNULL(CONCAT(UPPER(D.COMMENTS)," FROM ",DATE_FORMAT(D.ONDATE,'%d-%m-%Y')),'---')ANYREASON
		FROM
		HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A
		LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFILE B ON A.EMPLOYEEID=B.EMPLOYEEID
		LEFT JOIN HCLHRM_PROD.TBL_EMPLOYMENT_TYPES C ON A.EMPLOYMENTTYPEID=C.EMPLOYMENTTYPEID
		LEFT JOIN TEST.TBL_EMPLOYEE_CONFIRMATION_DETAILS D ON A.EMPLOYEEID=D.EMPLOYEEID
		LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT E ON A.COMPANYID=E.BUSINESSUNITID
		LEFT JOIN HCLADM_PROD.TBL_COSTCENTER F ON A.COSTCENTERID=F.COSTCENTERID
		LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS G ON A.EMPLOYEEID=G.EMPLOYEEID
		LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY H ON G.MANAGERID=H.EMPLOYEEID
		WHERE
		E.CALLNAME IN ('HYD') AND E.CODE IN ('HHC') AND A.EMPLOYMENTTYPEID!=1 AND A.STATUS=1001
		AND DATE_ADD(DATE_FORMAT(DATE_ADD(B.DATEOFJOIN, INTERVAL (IF(DAY(B.DATEOFJOIN)>15,1,0)) MONTH),'%Y-%m-01'),INTERVAL 6 MONTH) BETWEEN B.DATEOFJOIN AND '2018-11-15'
		AND A.EMPLOYEESEQUENCENO NOT IN (20206,70000,70001) AND F.NAME!='FIELD'
		AND H.EMPLOYEESEQUENCENO IN (10332)
		ORDER BY B.DATEOFJOIN DESC*/
		
		if(Routing!=null && Routing.equalsIgnoreCase("getEmpDetails")){
			
		String empCode=request.getParameter("employeeid");
		StringBuffer empList=new StringBuffer();
		empList.append("SELECT * FROM (   (SELECT A.EMPLOYEESEQUENCENO EmpCode,A.CALLNAME EmpName,DATE_FORMAT(B.DATEOFJOIN,'%d-%m-%Y') DOJ,");
		empList.append(" GROUP_CONCAT(QUALIFICATIONID)EduDetails,E.NAME Dept,F.NAME Desig,G.NAME Bu,");
		empList.append(" IFNULL(EX.EXPERIENCE,0.0)Expe,CAST((DATEDIFF(CURDATE(),B.DATEOFJOIN)+1)/365 AS DECIMAL(5,1))HeteroEx,");
		empList.append(" IFNULL((EX.EXPERIENCE),0.0) + (CAST((DATEDIFF(CURDATE(),B.DATEOFJOIN)+1)/365 AS DECIMAL(5,1)))TotalEx");
		empList.append(" FROM");
		empList.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A");
		empList.append(" JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFILE B ON A.EMPLOYEEID=B.EMPLOYEEID");
		empList.append(" JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS C ON B.EMPLOYEEID=C.EMPLOYEEID");
		empList.append(" JOIN HCLHRM_PROD.TBL_EMPLOYEE_EDUCATION_DETAILS D ON C.EMPLOYEEID=D.EMPLOYEEID");
		empList.append(" LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT E ON C.DEPARTMENTID=E.DEPARTMENTID");
		empList.append(" LEFT JOIN HCLADM_PROD.TBL_DESIGNATION F ON C.DESIGNATIONID=F.DESIGNATIONID");
		empList.append(" LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT G ON A.COMPANYID=G.BUSINESSUNITID");
		empList.append(" LEFT JOIN");
		empList.append(" (SELECT T1.EMPLOYEEID,CAST(SUM(T2.EXPERIENCE)/12 AS DECIMAL(5,1)) EXPERIENCE FROM");
		empList.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY T1");
		empList.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_EXPERIENCE_DETAILS T2 ON T1.EMPLOYEEID=T2.EMPLOYEEID");
		empList.append(" GROUP BY T1.EMPLOYEEID) EX ON EX.EMPLOYEEID=A.EMPLOYEEID");
		empList.append(" WHERE");
		empList.append(" A.EMPLOYEESEQUENCENO IN ("+empCode+") GROUP BY A.EMPLOYEEID)A,");
		empList.append(" (SELECT C.EMPLOYEESEQUENCENO MgrCode,C.CALLNAME MrgName FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A");
		empList.append(" JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS B ON A.EMPLOYEEID=B.EMPLOYEEID");
		empList.append(" JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY C ON B.MANAGERID=C.EMPLOYEEID");
		empList.append(" WHERE A.EMPLOYEESEQUENCENO IN ("+eCode+"))B)");
		
		/*
		SELECT * FROM (   (SELECT A.EMPLOYEESEQUENCENO EmpCode,A.CALLNAME EmpName,DATE_FORMAT(B.DATEOFJOIN,'%d-%m-%Y') DOJ,
		GROUP_CONCAT(DISTINCT D.QUALIFICATIONID)EduDetails,E.NAME Dept,F.NAME Desig,G.NAME Bu,
		IFNULL(EX.EXPERIENCE,0.0)Expe,CAST((DATEDIFF(CURDATE(),B.DATEOFJOIN)+1)/365 AS DECIMAL(5,1))HeteroEx,
		IFNULL((EX.EXPERIENCE),0.0) + (CAST((DATEDIFF(CURDATE(),B.DATEOFJOIN)+1)/365 AS DECIMAL(5,1)))TotalEx
		FROM
		HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A
		JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFILE B ON A.EMPLOYEEID=B.EMPLOYEEID
		JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS C ON B.EMPLOYEEID=C.EMPLOYEEID
		JOIN HCLHRM_PROD.TBL_EMPLOYEE_EDUCATION_DETAILS D ON C.EMPLOYEEID=D.EMPLOYEEID
		LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT E ON C.DEPARTMENTID=E.DEPARTMENTID
		LEFT JOIN HCLADM_PROD.TBL_DESIGNATION F ON C.DESIGNATIONID=F.DESIGNATIONID
		LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT G ON A.COMPANYID=G.BUSINESSUNITID
		LEFT JOIN
		(SELECT T1.EMPLOYEEID,CAST(SUM(T2.EXPERIENCE)/12 AS DECIMAL(5,1)) EXPERIENCE FROM
		HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY T1
		LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_EXPERIENCE_DETAILS T2 ON T1.EMPLOYEEID=T2.EMPLOYEEID
		GROUP BY T1.EMPLOYEEID) EX ON EX.EMPLOYEEID=A.EMPLOYEEID
		WHERE
		A.EMPLOYEESEQUENCENO IN (11498) GROUP BY A.EMPLOYEEID)A,
		(SELECT C.EMPLOYEESEQUENCENO MgrCode,C.CALLNAME MrgName FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A
		JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS B ON A.EMPLOYEEID=B.EMPLOYEEID
		JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY C ON B.MANAGERID=C.EMPLOYEEID
		WHERE A.EMPLOYEESEQUENCENO IN (10332))B)*/
		
		
		PrintWriter out = response.getWriter();
		StringBuffer fetchDetails = new StringBuffer();
		StringBuffer oldEmpDataBuf = new StringBuffer();
		
		System.out.println("1111------------------------------"+empList);
		
		try {
			Res=(ResultSet)DataObj.FetchData(empList.toString(), "empList", Res ,conn);
			ResultSetMetaData rsmd=Res.getMetaData();
			while(Res.next()){
				for(int i=1;i<=rsmd.getColumnCount();i++){
					fetchDetails.append(Res.getString(i).toString());
					fetchDetails.append("~");
				}
				fetchDetails.deleteCharAt(fetchDetails.length()-1);
			}  
		}catch(Exception Er){
			System.out.println("Exception At Er:2:"+Er);
		}
		Res=null;
		StringBuffer oldEmpData=new StringBuffer();
		oldEmpData.append(" SELECT IFNULL(A.EMPLOYEESEQUENCENO,'')EMPLOYEESEQUENCENO,IFNULL(A.CATEGORY,'')CAT,IFNULL(A.HODCOMMENTS,'')HODC,IFNULL(A.CONTRIBUTION,'')CONTR,IFNULL(A.EXTENDMONTHS,'')Months,");
		oldEmpData.append(" IFNULL(GROUP_CONCAT(distinct(B.AREACODE)),'')AREAS,IFNULL(TRIM(REPLACE(GROUP_CONCAT(B.COMMENTS),',',' ')),'')AreaComments,");
		oldEmpData.append(" IFNULL(GROUP_CONCAT(distinct(C.TRAININGCODE)),'')Trainings,IFNULL(TRIM(REPLACE(GROUP_CONCAT(C.COMMENTS),',',' ')),'')TrainingComments,");
		oldEmpData.append(" IFNULL(GROUP_CONCAT(distinct D.COMMENTS SEPARATOR '\n'),'') AnyOther,ifnull(group_concat( distinct(e.callname) order by d.level),'' )apprids,ifnull(group_concat(distinct(d.level) order by d.level),'') apprlevel ");
		oldEmpData.append(" FROM");
		oldEmpData.append(" TEST.TBL_EMPLOYEE_ASSESSMENT_DETAILS A");
		oldEmpData.append(" LEFT JOIN (select * from TEST.TBL_EMPLOYEE_ASSESSMENT_AREAS where activestatus=1001) B ON A.EMPLOYEESEQUENCENO=B.EMPLOYEESEQUENCENO");
		oldEmpData.append(" LEFT JOIN  (select * from TEST.TBL_EMPLOYEE_ASSESSMENT_TRAINING  where activestatus=1001)  C ON A.EMPLOYEESEQUENCENO=C.EMPLOYEESEQUENCENO");
		oldEmpData.append(" LEFT JOIN (select * from TEST.TBL_EMPLOYEE_ASSESSMENT_COMMENTS where approvalstatus='1001') D ON A.EMPLOYEESEQUENCENO=D.EMPLOYEESEQUENCENO");
		oldEmpData.append(" left join HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY e on e.employeesequenceno=d.createdby ");
		oldEmpData.append(" WHERE");
		oldEmpData.append(" A.EMPLOYEESEQUENCENO IN ("+empCode+")");
		System.out.println("22222222---------------"+oldEmpData);
		  
		//a[0]=10;//initialization  
		
		try {
			Res=(ResultSet)DataObj.FetchData(oldEmpData.toString(), "oldEmpData", Res ,conn);
			ResultSetMetaData rsmd=Res.getMetaData();
			
		
			while(Res.next()){
				for(int i=1;i<=rsmd.getColumnCount();i++){
					if(Res.getString(i).toString()!=null){
					oldEmpDataBuf.append(Res.getString(i).toString());
					oldEmpDataBuf.append("~");
					}
				}
			} 
			if(oldEmpDataBuf.length()>0){
				oldEmpDataBuf.deleteCharAt(oldEmpDataBuf.length()-1);
				}
		}catch(Exception Er){
			System.out.println("Exception At Er:3:"+Er);
		}
		System.out.println("oldEmpData.toString()~~>"+oldEmpDataBuf.toString().replace("~", "").length());
		//-----------------------------------------------------
		
		Res=null;
		StringBuffer Allcomments=new StringBuffer();
		StringBuffer AllcommentsBuf = new StringBuffer();
		
		
		Allcomments.append("  select distinct(ad.employeesequenceno),p.callname,ad.category,ad.hodcomments,ad.extendmonths,ifnull(aa.areacode,'nodata') areacode,ifnull(at.trainingcode,'nodata') trainingcode,ad.activestatus ");
		Allcomments.append("  from test.tbl_employee_assessment_details_backup ad ");
		Allcomments.append(" left join (select employeesequenceno,group_concat(ma.name) as  areacode,createdby ");
		Allcomments.append("  from TEST.TBL_EMPLOYEE_ASSESSMENT_AREAS a left join test.tbl_employee_assessment_main_areas ma on a.areacode=ma.id where employeesequenceno="+empCode+" and a.status='1001' group by createdby) aa ");
		Allcomments.append(" on ad.employeesequenceno=aa.employeesequenceno and ad.createdby=aa.createdby  ");
		Allcomments.append(" left join (select employeesequenceno,group_concat(mt.name) as trainingcode,createdby ");
		Allcomments.append("  from TEST.TBL_EMPLOYEE_ASSESSMENT_TRAINING t left join test.tbl_employee_assessment_main_training  mt on t.trainingcode=mt.id where employeesequenceno="+empCode+" and t.status='1001'  group by createdby) at ");
		Allcomments.append(" on ad.employeesequenceno=at.employeesequenceno and ad.createdby=at.createdby ");
		Allcomments.append(" left join HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY p on ad.createdby=p.employeesequenceno  where ad.employeesequenceno="+empCode+" and activestatus='1001' "); 
		
		System.out.println("33333333333333---------------"+Allcomments);
	
		try {
			Res=(ResultSet)DataObj.FetchData(Allcomments.toString(), "Allcomments", Res ,conn);
			ResultSetMetaData rsmd=Res.getMetaData();
		//	System.out.println("---"+rsmd);
			
			while(Res.next()){
				//System.out.println("here");
				 JSONObject empdetails = new JSONObject();	
				// employeesequenceno, createdby, category, hodcomments, extendmonths, areacode, trainingcode
				 empdetails.put("id", Res.getString("employeesequenceno"));
				 empdetails.put("createdby", Res.getString("callname"));
				 empdetails.put("rating", Res.getString("category"));
				 empdetails.put("hodcomments", Res.getString("hodcomments"));
				 empdetails.put("extendmonths", Res.getString("extendmonths"));
				 empdetails.put("areacode", Res.getString("areacode"));
				 empdetails.put("trainingcode", Res.getString("trainingcode"));
				 empdetails.put("activestatus", Res.getString("activestatus"));
				 detailsarray.add(empdetails);
				/*for(int i=1;i<=rsmd.getColumnCount();i++){
					
					 empdetails.append(Res.getString(i).toString());
					AllcommentsBuf.append("~");
				}
				AllcommentsBuf.append("$");
				*/
			} 
			//System.out.println(AllcommentsBuf+"------------oldEmpData.toString()~~>"+AllcommentsBuf.toString().replace("~", "").length());
			
		}catch(Exception Er){
			System.out.println("Exception At Er:3:"+Er);
		}
		//--------------------------------------------------------
		
		String disCode=null;
		if(oldEmpDataBuf.toString().replace("~", "").length()>0){
			disCode="1";
		}else{
			disCode="0";
		}
		out.write(fetchDetails.toString()+"#"+oldEmpDataBuf.toString()+"#"+disCode+"#"+detailsarray.toString());  
		}
		
		/*SELECT A.EMPLOYEESEQUENCENO,A.CATEGORY,A.HODCOMMENTS,A.CONTRIBUTION,A.EXTENDMONTHS,
          IFNULL(GROUP_CONCAT(B.AREACODE),'')AREAS,IFNULL(B.COMMENTS,'')AreaComments,
          IFNULL(GROUP_CONCAT(C.TRAININGCODE),'')Trainings,IFNULL(C.COMMENTS,'')TrainingComments,
          D.COMMENTS AnyOther
          FROM
          TEST.TBL_EMPLOYEE_ASSESSMENT_DETAILS A
          LEFT JOIN TEST.TBL_EMPLOYEE_ASSESSMENT_AREAS B ON A.EMPLOYEESEQUENCENO=B.EMPLOYEESEQUENCENO
          LEFT JOIN TEST.TBL_EMPLOYEE_ASSESSMENT_TRAINING C ON A.EMPLOYEESEQUENCENO=C.EMPLOYEESEQUENCENO
          LEFT JOIN TEST.TBL_EMPLOYEE_ASSESSMENT_COMMENTS D ON A.EMPLOYEESEQUENCENO=D.EMPLOYEESEQUENCENO
          WHERE
          A.EMPLOYEESEQUENCENO IN (11498)
        */
		
		
		else{
			System.out.println("else case --------------------------------------------------");
			String ApprovalStatus="1001";
			if(request.getParameter("FormSub")!=null && request.getParameter("FormSub").equalsIgnoreCase("Submit Form")){
				System.out.println("----------------------------------------------------------------------------------for submit");
			
				
				Enumeration e = request.getParameterNames();
				Map<String,String> fieldParams=new HashMap<String,String>();
				while(e.hasMoreElements())
				{
					Object obj = e.nextElement();
					String fieldName = (String) obj;
					String fieldValue = request.getParameter(fieldName);
					fieldParams.put(fieldName, fieldValue);
				}
				// Table-1 Insert  test.tbl_employee_assessment_details
				System.out.println("fieldParams~"+fieldParams);
				PreparedStatement psObj=null;
				
		//	if(request.getParameter("FormSub")!=null && !request.getParameter("mrgFiller").equalsIgnoreCase("1")){
				if(request.getParameter("FormSub")!=null){
					
				StringBuffer insertEAD=new StringBuffer();
				StringBuffer insertEADbck=new StringBuffer();
				/**** EMPLOYEEID, EMPLOYEESEQUENCENO, CATEGORY, HODCOMMENTS, CONTRIBUTION, EXTENDMONTHS, CREATEDBY, DATECREATED, STATUS, LUPDATE ***/
				
				
				insertEAD.append("INSERT INTO TEST.TBL_EMPLOYEE_ASSESSMENT_DETAILS");
				insertEAD.append(" SELECT EMPLOYEEID,EMPLOYEESEQUENCENO,?,?,?,?,?,NOW(),?,NOW()");
				insertEAD.append(" FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN ("+fieldParams.get("employeeid")+") ");
				insertEAD.append(" ON DUPLICATE KEY UPDATE  Category=?,hodcomments=?,contribution=?,extendmonths=?,createdby=? ");
				
				
				insertEADbck.append(" insert into test.tbl_employee_assessment_details_backup ");
				insertEADbck.append(" (select Employeeid, Employeesequenceno, Category, HodComments, Contribution, ExtendMonths, ");
				insertEADbck.append(" CreatedBy, DateCreated,1002, now(),1001 from TEST.TBL_EMPLOYEE_ASSESSMENT_DETAILS where employeesequenceno=? );");
				
			
				
				//--------------------------
				
				psObj=null;
				try{
					psObj=conn.prepareStatement(insertEAD.toString());
					psObj.setString(1, fieldParams.get("overall_rating"));
					psObj.setString(7, fieldParams.get("overall_rating"));
					psObj.setString(2, fieldParams.get("hodComments"));
					psObj.setString(8, fieldParams.get("hodComments"));
					psObj.setString(3, fieldParams.get("Contribution"));
					psObj.setString(9, fieldParams.get("Contribution"));

					if(fieldParams.get("Contribution")!=null && fieldParams.get("Contribution").equalsIgnoreCase("N")){
						psObj.setString(4, fieldParams.get("monthCount"));
						psObj.setString(10, fieldParams.get("monthCount"));
					}else{
						psObj.setString(4, "0");
						psObj.setString(10, "0");
					}
					psObj.setString(5, eCode);
					psObj.setString(11, eCode);
					psObj.setString(6, "1001");
				}catch(Exception e1){
					e1.printStackTrace();
					System.out.println("1st inesrt exception here ");
				}
				try{
					int affectedRows =psObj.executeUpdate();
				}catch(Exception e2){
					e2.printStackTrace();
				}
			//---
				
				psObj=null;
				
				try{
					psObj=conn.prepareStatement(insertEADbck.toString());
					psObj.setString(1, fieldParams.get("employeeid"));
				}catch(Exception e1){
					e1.printStackTrace();
					System.out.println("1-1st inesrt exception here ");
				}
				try{
					
					System.out.println("bckipqury---"+insertEADbck);
					int count=psObj.executeUpdate();
						/*
						 * if(count == 1){ conn.commit(); }
						 */

				}catch(Exception e2){
					e2.printStackTrace();
				}
				
				//----
				
				if(fieldParams.get("Contribution").equalsIgnoreCase("Y")) {
					ApprovalStatus="1001";
				}else {
					ApprovalStatus="1002";
				}
	/*	//	************************************chnages by H************************
				StringBuffer updateapprvstat=new StringBuffer();
				updateapprvstat.append(" ");
				updateapprvstat.append(" ");
				updateapprvstat.append(" ");
				try{
					psObj=conn.prepareStatement(updateapprvstat.toString());
					psObj.setString(1,"");
					psObj.setString(2,"" );
			}catch(Exception e1){
					e1.printStackTrace();
				}
				try{
					psObj.executeUpdate();
				}catch(Exception e2){
					e2.printStackTrace();
				}
			//*************************************************************H change	
				*/
				
				/*** EMPLOYEEID, EMPLOOYEESEQUENCENO, AREACODE, CREATEDBY, DATECREATED, COMMENTS, LUPDATE ***/
				//test.tbl_employee_assessment_areas 
				StringBuffer insertEAA=new StringBuffer();
				StringBuffer insertEAAtracking=new StringBuffer();

				insertEAA.append("INSERT INTO TEST.TBL_EMPLOYEE_ASSESSMENT_AREAS");
				insertEAA.append(" SELECT EMPLOYEEID,EMPLOYEESEQUENCENO,?,?,NOW(),?,NOW(),1001,1001 FROM");
				insertEAA.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN (?) ON DUPLICATE KEY UPDATE  status=? ");
				
				
				
				insertEAAtracking.append(" update TEST.TBL_EMPLOYEE_ASSESSMENT_AREAS set ActiveStatus=1002 where employeesequenceno=?  ");
			
				System.out.println("--------------------------------------------");
				psObj=null;
				try{
					psObj=conn.prepareStatement(insertEAAtracking.toString());
				
				psObj.setString( 1, fieldParams.get("employeeid") );
				//psObj.setString( 2, Arealist.get(1) );
				//	psObj.addBatch();
				System.out.println("updateqry---"+psObj);
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try{
					int count = psObj.executeUpdate();
				}catch(Exception e3){
					e3.printStackTrace();
					System.out.println("3-1rd inesrt exception here ");
				}
			System.out.println("update---");	
			//---------------------------------------------------------------------------------------------	
				
				psObj=null;
				try{
					psObj=conn.prepareStatement(insertEAA.toString());
				}catch(Exception e2){
					e2.printStackTrace();
					System.out.println("2nd  exception here ");
				}
		    	for(int i=1;i<9;i++){
		    				    		
					if(fieldParams.get("achk"+i)!=null ){
						try{
							psObj.setInt( 1, Integer.parseInt(fieldParams.get("achk"+i)));
							psObj.setString( 2, eCode );

							if(fieldParams.get("achk"+i).equalsIgnoreCase("8")){
								psObj.setString( 3, fieldParams.get("achkOthers"));
							}else{
								psObj.setString( 3, "");
							}
							psObj.setString( 4, fieldParams.get("employeeid") );
						
							psObj.setString( 5, ApprovalStatus );
							System.out.println("-----"+psObj);
							psObj.addBatch();
						}catch(Exception e2){
							e2.printStackTrace();
						}
						
						
					}
				}
				try{
					int[] count = psObj.executeBatch();
				}catch(Exception e3){
					e3.printStackTrace();
					System.out.println("3rd inesrt exception here ");
				}
			//	System.out.println("--------------------------------------------"+Arealist.size());
				/***EMPLOYEEID, EMPLOYEESEQUENCENO, TRAININGCODE, CREATEDBY, DATECREATED, STATUS, LUPDATE***/
				// test.tbl_employee_assessment_training

				StringBuffer insertEAT=new StringBuffer();
				
				StringBuffer insertEATtracking=new StringBuffer();
				
				insertEAT.append("INSERT INTO TEST.TBL_EMPLOYEE_ASSESSMENT_TRAINING");
				insertEAT.append(" SELECT EMPLOYEEID,EMPLOYEESEQUENCENO,?,?,NOW(),?,NOW(),1001,1001 FROM");
				insertEAT.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN (?) ON DUPLICATE KEY UPDATE ActiveStatus=? ");

				
				insertEATtracking.append(" update TEST.TBL_EMPLOYEE_ASSESSMENT_TRAINING set ActiveStatus=1002 where employeesequenceno=?  ");
				
				System.out.println("--------------------------------------------");
				psObj=null;
				try{
					psObj=conn.prepareStatement(insertEATtracking.toString());
				
				psObj.setString( 1, fieldParams.get("employeeid") );
				//psObj.setString( 2, Arealist.get(1) );
				//	psObj.addBatch();
				System.out.println("updateqry---"+psObj);
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try{
					int count = psObj.executeUpdate();
				}catch(Exception e3){
					e3.printStackTrace();
					System.out.println("3-1rd inesrt exception here ");
				}
			System.out.println("update2---");	
			//---------------------------------------------------------------------------------------------	
				
				psObj=null;
				
				
				
				try{
					psObj=conn.prepareStatement(insertEAT.toString());
				}catch(Exception e2){
					e2.printStackTrace();
					System.out.println("4th exception here ");
				}
				for(int i=1;i<5;i++){
					if(fieldParams.get("tchk"+i)!=null){
						try{
							psObj.setInt( 1, Integer.parseInt(fieldParams.get("tchk"+i)));
							psObj.setString( 2, eCode );
							if(fieldParams.get("tchk"+i).equalsIgnoreCase("4")){
								psObj.setString( 3, fieldParams.get("tckOthers"));
							}else{
								psObj.setString( 3, "");
							}
							psObj.setString( 4, fieldParams.get("employeeid") );
							psObj.setString( 5, ApprovalStatus );
							psObj.addBatch();
						}catch(Exception e2){
							e2.printStackTrace();
							System.out.println("5th  exception here ");
						}
					}
				}
				try{
					int[] count = psObj.executeBatch();
				}catch(Exception e3){
					e3.printStackTrace();
					System.out.println("6th inesrt exception here ");
				}
			}// if end
				/**EMPLOYEEID, EMPLOYEESEQUENCNO, CREATEDBY, COMMENTS, NEXTAPPROVAL, STATUS***/
				/*test.tbl_employee_assessment_comments*/
		// else	
			
			//System.out.println("elseeeeeeeee");
			
				psObj=null;
				StringBuffer insertEAC=new StringBuffer();

				
				insertEAC.append("INSERT INTO TEST.TBL_EMPLOYEE_ASSESSMENT_COMMENTS");
				insertEAC.append(" SELECT EMPLOYEEID,EMPLOYEESEQUENCENO,?,?,?,1001, ");
				insertEAC.append(" (select  sum((select ifnull(max(level) ,0) from TEST.TBL_EMPLOYEE_ASSESSMENT_COMMENTS where employeesequenceno=?)+1)),? ");
				insertEAC.append("  FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN (?) ON DUPLICATE KEY UPDATE Approvalstatus=?,NEXTAPPROVAL=?");
				try{
					psObj=conn.prepareStatement(insertEAC.toString());
					psObj.setString(1, eCode);
					psObj.setString(2, fieldParams.get("otherRemarks"));
					psObj.setInt(3, Integer.parseInt(fieldParams.get("docFwd")));
					psObj.setString(4, fieldParams.get("employeeid"));
					psObj.setString(5, ApprovalStatus);
					psObj.setString(6, fieldParams.get("employeeid"));
					psObj.setString(7, ApprovalStatus);
					psObj.setString(8,  (fieldParams.get("docFwd")));
					try{
						
						//System.out.println("============================"+psObj);
						psObj.executeUpdate();
						System.out.println("Hetero:1"+psObj.toString());
					}catch(Exception e2){
						e2.printStackTrace();
						System.out.println("7th  exception here ");
					}
				}catch(Exception e2){
					e2.printStackTrace();
					System.out.println("8th  exception here ");
				}
				/*******test.tbl_employee_assessment_details***********/
				//
				if(fieldParams.get("Contribution").equalsIgnoreCase("N")) {
                       System.out.println("reject ");
				psObj=null;
				StringBuffer NhodComments=new StringBuffer();
				NhodComments.append(" update TEST.TBL_EMPLOYEE_ASSESSMENT_COMMENTS set Approvalstatus=1002,NEXTAPPROVAL=? where employeesequenceno=?  ");

				try{
					//System.out.println("----------------------------------"+NhodComments);
					psObj=conn.prepareStatement(NhodComments.toString());
				
					psObj.setString(2, fieldParams.get("employeeid"));
					psObj.setString(1,  (fieldParams.get("docFwd")));
			        try{
						psObj.executeUpdate();
						System.out.println("Hetero:1"+psObj.toString());
					}catch(Exception e2){
						e2.printStackTrace();
						System.out.println("9th  exception here ");
					}
				}catch(Exception e2){
					e2.printStackTrace();
					System.out.println("10th  exception here ");
				}
	
				psObj=null;
				StringBuffer Nbckup=new StringBuffer();
				Nbckup.append(" update test.tbl_employee_assessment_details_backup set ActiveStatus=1002 where employeesequenceno=? ");
				
				try{
					psObj=conn.prepareStatement(Nbckup.toString());
				
					psObj.setString(1, fieldParams.get("employeeid"));
			        try{
						psObj.executeUpdate();
						System.out.println("Hetero:1"+psObj.toString());
					}catch(Exception e2){
						e2.printStackTrace();
						System.out.println("9th  exception here ");
					}
				}catch(Exception e2){
					e2.printStackTrace();
					System.out.println("10th  exception here ");
				}
				
				psObj=null;
				StringBuffer Narea=new StringBuffer();
				Narea.append(" update  TEST.TBL_EMPLOYEE_ASSESSMENT_AREAS a set status= ");
				Narea.append("  ((select status from ");
				Narea.append(" (select ifnull(max(status),'1001') status from TEST.TBL_EMPLOYEE_ASSESSMENT_AREAS where employeesequenceno=?) as b )+1)  ");
				Narea.append(" where a.employeesequenceno in (? ) and a.status='1001' ");
				Narea.append("  ");
				
				try{
					psObj=conn.prepareStatement(Narea.toString());
				
					psObj.setString(1, fieldParams.get("employeeid"));
					psObj.setString(2, fieldParams.get("employeeid"));
			        try{
						psObj.executeUpdate();
						System.out.println("Hetero:1"+psObj.toString());
					}catch(Exception e2){
						e2.printStackTrace();
						System.out.println("9th  exception here ");
					}
				}catch(Exception e2){
					e2.printStackTrace();
					System.out.println("10th  exception here ");
				}
			
				psObj=null;
				StringBuffer Ntraining=new StringBuffer();
				Ntraining.append(" update  TEST.TBL_EMPLOYEE_ASSESSMENT_TRAINING  a set status= ");
				Ntraining.append(" ((select status from ");
				Ntraining.append(" (select ifnull(max(status),'1001') status from TEST.TBL_EMPLOYEE_ASSESSMENT_TRAINING  where employeesequenceno=?) as b )+1) ");
				Ntraining.append(" where a.employeesequenceno in (? ) and a.status='1001'; ");
			
				
				try{
					psObj=conn.prepareStatement(Ntraining.toString());
				
					psObj.setString(1, fieldParams.get("employeeid"));
					psObj.setString(2, fieldParams.get("employeeid"));
			        try{
						psObj.executeUpdate();
						System.out.println("Hetero:1"+psObj.toString());
					}catch(Exception e2){
						e2.printStackTrace();
						System.out.println("9th  exception here ");
					}
				}catch(Exception e2){
					e2.printStackTrace();
					System.out.println("10th  exception here ");
				}
	
				}
				//
				psObj=null;
				StringBuffer hodComments=new StringBuffer();
				hodComments.append("UPDATE TEST.TBL_EMPLOYEE_ASSESSMENT_DETAILS SET HODCOMMENTS=?");
				hodComments.append(" WHERE EMPLOYEESEQUENCENO IN (?) ");
				hodComments.append("");
				try{
					psObj=conn.prepareStatement(hodComments.toString());
					psObj.setString(1, fieldParams.get("hodComments"));
					psObj.setString(2, fieldParams.get("employeeid"));
			        try{
						psObj.executeUpdate();
						System.out.println("Hetero:1"+psObj.toString());
					}catch(Exception e2){
						e2.printStackTrace();
						System.out.println("9th  exception here ");
					}
				}catch(Exception e2){
					e2.printStackTrace();
					System.out.println("10th  exception here ");
				}finally {
					try {
						conn.commit();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				//
				
				
				//request.getRequestDispatcher("/AssessmentForm?Routing=FormAssessment").forward(request, response);
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				request.getRequestDispatcher("/Special_Routing.jsp?Routing=FormAssessment").forward(request, response);
				
				//response.sendRedirect("AssessmentForm?Routing=FormAssessment");
				
				
				//response.sendRedirect("Special_Routing.jsp?Routing=FormAssessment");
				
			}else if(request.getParameter("FormSub")==null){
				
				//Special_Routing.jsp
			request.getRequestDispatcher("AssessmentForm.jsp").forward(request, response);
			
			//request.getRequestDispatcher("AssessmentForm.jsp").forward(request, response);
			
			
			}
		}
		//System.out.println("formSbu~~"+request.getParameter("FormSub"));
	}
}