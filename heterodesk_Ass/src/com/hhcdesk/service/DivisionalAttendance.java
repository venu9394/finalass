package com.hhcdesk.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;



/**
 * Servlet implementation class DivisionalAttendance
 * @author Mahesh_HHCL
 */
@SuppressWarnings("serial")
public class DivisionalAttendance extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String Routing=request.getParameter("Routing");
		String[] divisionCode=request.getParameterValues("Div");
		StringBuffer divisionCodes=new StringBuffer();
		
		if(divisionCode!=null){
		for(int i=0;i<divisionCode.length;i++){
			//out.println(Integer.parseInt(a[i])); //If integer
			divisionCodes.append(divisionCode[i]);
			divisionCodes.append(",");
			}
			divisionCodes.append("0");
		}
		
		Connection conn=null;
		CallableStatement cStmt = null;
		ResultSet rs=null;
		StringBuffer tblData=new StringBuffer();
		StringBuffer tblDataHeader=new StringBuffer();
		PrintWriter out = response.getWriter();
		
		//StringBuffer getAttnData=new StringBuffer();
		try {
			conn=(java.sql.Connection)session.getAttribute("ConnectionObj");
		} catch (Exception e1) {
				e1.printStackTrace();
		}
		
		
		if(Routing!=null && Routing.equalsIgnoreCase("loadingempBu")){
			String empCode=request.getParameter("empCode");
			PreparedStatement ps=null;
			StringBuffer getempUnits=new StringBuffer();
			String empUnits=null;
			
			JSONObject m=new JSONObject();
			
			getempUnits.append(" SELECT C.NAME BU,C.BUSINESSUNITID FROM");
			getempUnits.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A");
			getempUnits.append(" JOIN HCLHRM_PROD.TBL_EMPLOYEE_BUSINESSUNIT B ON A.EMPLOYEEID=B.EMPLOYEEID");
			getempUnits.append(" JOIN HCLADM_PROD.TBL_BUSINESSUNIT C ON B.BUSINESSUNITID=C.BUSINESSUNITID AND C.STATUS=1001");
			getempUnits.append(" WHERE A.STATUS=1001 AND A.EMPLOYEESEQUENCENO IN (?)");
			
			String data=null;
			try{
				ps=conn.prepareStatement(getempUnits.toString());
				ps.setString(1, empCode);
				rs=null;
				
				rs=ps.executeQuery();
				
				//m.put("--Select--", "0");
				while(rs.next()){
					/*empUnits.append("\"");					
					empUnits.append(rs.getString(1)+"\":"+rs.getString(2));
					empUnits.append(",");*/
					
					m.put(rs.getString(1), rs.getString(2));
					
					
				}
				
				//data = empUnits.substring(0, empUnits.length() - 1);
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			out.write(m.toString());
			
		}
		
		
		
		if(Routing!=null && Routing.equalsIgnoreCase("empattendanceReport")){
		
		try {
			cStmt = conn.prepareCall("{CALL PROCEDURE.ATTENDANCE('"+divisionCodes+"','1001')}");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			rs=cStmt.executeQuery();
			
			int colCnt=0;
			try {
				ResultSetMetaData rsmd=rs.getMetaData();
				colCnt=rsmd.getColumnCount();
				
				for(int i=1;i<=colCnt;i++){
					rsmd.getColumnName(i);
					tblDataHeader.append("<th>"+rsmd.getColumnName(i)+"</th>");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}  
			
			int j=0;
			while(rs.next()){
				tblData.append("<tr>");
				for(int i=1;i<=colCnt;i++){
					tblData.append("<td>"+rs.getString(i)+"</td>");
				}
				
				
				
				tblData.append("</tr>");
			}
			
				
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		/*String page="";
		if(Routing!=null && Routing.equalsIgnoreCase("empattendanceReport")){
			page="/buattendance.jsp";
		}
		request.setAttribute("tblData",tblData.toString());
		request.setAttribute("tblDataHeader",tblDataHeader.toString());
		request.getRequestDispatcher(page).forward(request, response);  */
		
		out.write(tblData.toString()+"~"+tblDataHeader.toString());
		
		}
		
		
		
	}

}
