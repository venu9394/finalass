<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hetero HealthCare</title>
</head>
<body>
<% 
String User=request.getParameter("reset");
ServletContext c = getServletContext();
String message=null;
if(User!=null && c.getAttribute(User)=="Y"){
	
	message="Your Login is reset please try again.<a href='login.html' style='cursor:pointer;'> LOGIN </a>";
	c.removeAttribute(User);
	
}else{
	message="Please check your user id.<a href='login.html' style='cursor:pointer;'> LOGIN </a>";
   
}

%>
<span style='color:red'><%=message %></span>

</body>
</html>