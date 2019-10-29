 
<%@page import="java.util.Properties"%>
<jsp:directive.page import="java.io.FileInputStream" />

<%@ page import = "java.io.*,java.util.*" %>
<%@ page import = "javax.servlet.*,java.text.*" %>
<%@ page import = "java.sql.*" %>

<html>
	<head>
		<title>Download File</title>
	</head>
	<body>	
		<%
			String data = request.getParameter("filename");	
		
		//System.out.println(data);
		
		String fileName = data;	
		
		
	/* 	String employeeid = data.split(",")[1];	
		
		String reporttype = data.split(",")[2];	 */
		
		
	//System.out.println(employeeid);
		
		
		
		
			/* Date date = new Date();
			SimpleDateFormat ft =new SimpleDateFormat ("M yy");
			String date2 = ft.format(new Date()); */
			String filePath = "C:/TDSReports/"+fileName;
			FileInputStream fileToDownload = new FileInputStream(filePath);
			ServletOutputStream output = response.getOutputStream();
			if(fileName.endsWith(".csv")){
				response.setContentType("text/csv");
			}else if(fileName.endsWith(".xls") || fileName.endsWith(".xlsx")){
				response.setContentType("application/vnd.ms-excel");
			}else if(fileName.endsWith(".pdf")){
				response.setContentType("application/octet-stream");
			}else if(fileName.endsWith(".txt")){
				response.setContentType("text/plain");
			}
			
			response.setHeader("Content-Disposition", "attachment; filename="+ fileName );
			response.setContentLength(fileToDownload.available());
			int c;
			while ((c = fileToDownload.read()) != -1) {
				output.write(c);
			}
			out.clear(); // where out is a JspWriter
			out = pageContext.pushBody();
			output.flush();
			output.close();
			fileToDownload.close();
			
			/* Connection con=null;
			PreparedStatement pstmt=null; */
		/* 	try{
				System.out.println("bhanu");
				con=Util.getConnection();
				String downloadHistory="INSERT INTO test.tbl_reports_history (EMPLOYEEID, REPORTTYPE, FILEPATH, FILENAME) VALUES (?,?,?,?)";
				pstmt=con.prepareStatement(downloadHistory);
				
				pstmt.setString(1,employeeid);
				pstmt.setString(2,reporttype);
				pstmt.setString(3,filePath);
				pstmt.setString(4,fileName);
				
				System.out.println(pstmt);
				int i=pstmt.executeUpdate();		
				
				
			}catch(Exception e){
				
			} */
			
			
			
			
		%>
		
		
	</body>
</html>
