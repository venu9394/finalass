package com.hhcdesk.regreports;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.hhcdesk.db.DataSource_Cls;

public class AssessmentDownloadServlet extends HttpServlet {

public void doPost(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException {
	doGet(request,response);
		
	}
public void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
//System.out.println("downloadservlet");
response.setContentType("text/html");
Connection conn=null;
PrintWriter out = response.getWriter();
HttpSession session = request.getSession();

String returnstatus="";
String returnfilename="";


String param=request.getParameter("reports" );
String filename ="C:\\uploads\\Assessment\\"+param+"Report.xlsx";
AssessmentWriteExcel export;

System.out.println("----------"+request.getParameter("reports" )+"Report Generation");

String reporttype=param+"ListQry";

try{
	   
	DataSource_Cls concls=new DataSource_Cls();
	conn=concls.getConnection();
	}catch(Exception err){
		err.printStackTrace();
	}
try {
	Thread.sleep(1000);
//*********EXCEL GENERATION***********//
 export= new AssessmentWriteExcel();
 Map result=export.generate(conn,filename,reporttype);
 returnstatus=(String) result.get("status");
 returnfilename=(String) result.get("filepath");
//***************END*********************//

}catch(Exception e){
	e.printStackTrace();
}finally {
	if(conn!=null){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
if(returnstatus.equalsIgnoreCase("1001")){
 
 File file= new File(filename);
 if(!file.exists()){
	 filename = "blank.xls";
 }
}else{
	 filename = "blank.xls";
}



response.setContentType("APPLICATION/OCTET-STREAM");
response.setHeader("Content-Disposition","attachment; filename=\"" + param + "Report.xlsx\"");

FileInputStream fileInputStream = new FileInputStream( filename);

int i;
while ((i=fileInputStream.read()) != -1) {
out.write(i);
}
fileInputStream.close();
out.close();
}

}