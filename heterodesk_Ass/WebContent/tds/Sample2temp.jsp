<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<% 
		
		ArrayList TableInf = (ArrayList) request.getAttribute("Tableinfo");
		Map Basics = (Map) request.getAttribute("TableData");
		java.util.Iterator TableInfo = TableInf.iterator();
%>	
		
<body>
<% 
   while (TableInfo.hasNext()) {
				String TbId = TableInfo.next().toString();
	%>			
		 <table> 
		 <tr><th><%=Basics.get(TbId+"_H")%></th><th><%=Basics.get(TbId+"_H2")%></th><th><%=Basics.get(TbId+"_H3")%></th></tr> 
		 
		   <%for(int i=0;i<=Integer.parseInt(Basics.get("DataLent").toString());i++){
		       boolean flag=false;
		       String row1=null,row2=null,row3=null;
			   try{
			    	row1=Basics.get(TbId+"_F_"+i).toString();
		        	row2=Basics.get(TbId+"_S_"+i).toString();
		        	row3=Basics.get(TbId+"_T_"+i).toString();
		       }catch(Exception Ee){
		    	   System.out.println("Exception::"+Ee);
		       }
			   if(row1!=null &row2!=null&row3!=null){
				   flag=true;
			   }
		      if(flag!=false){
		    %>		   
		     <tr><td><%=row1 %></td><td><%=row2 %></td><td><%=row3 %></td> </tr>
		 
		 <%} 
		 }%>
		 </table>
		<%}%>

</body>
</html>