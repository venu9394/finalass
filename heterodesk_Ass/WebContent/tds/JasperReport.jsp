<%@ page  import="java.io.*"%> 
<%@ page  import="java.sql.Connection"%> 
<%@ page  import="java.sql.DriverManager"%>
<%@ page  import="java.util.HashMap"%>
<%@ page  import="java.util.Map"%>
<%@ page  import="net.sf.jasperreports.engine.*"%>
 
 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Hello World!</h2>
        <%
            Connection conn = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            //
            
           // File reportFile = new File(application.getRealPath("report_name.jasper"));//your report_name.jasper file
             File reportFile = new File("D:/example.jasper");
            Map parameters = new HashMap();
            byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), null, conn);
           
          /*   JasperPrint jprint1 = (JasperPrint) JasperFillManager.fillReport(reportFile, parameters, null);
            JasperExportManager.exportReportToPdfFile(jprint1, "D:/EmployeesReport.pdf");
             */
           // JasperExportManager.exportReportToPdfFile(jprint1, "D:/EmployeesReport.pdf");
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
           // outStream.close();
        %>
    </body>
</html>