<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%
String apprisalPwd=(String)session.getAttribute("apprisalPwd");
String username_apprisal=(String)session.getAttribute("EMP_ID");
String apprisalPwdBut="none";
if(apprisalPwd!=null){
	  
	  apprisalPwdBut="";
	  
}

%>
<script>
function action(){

	try{
		document.getElementById("myForm").submit();
	}catch(er){
		alert(er.message);
	}
	
}
</script>

<title>Insert title here</title>
</head>
<body onload="myFunction();" >
<!-- <form method='get' action="http://services.heterohcl.com/php/appraisal/authenticate"> -->
<form id="myForm" method='post'  action="http://services.heterohcl.com/php/appraisal/authenticate" >
<!--  apprasal.jsp
	href="http://services.heterohcl.com/php/appraisal/authenticate?username=<%=username_apprisal%>&password=<%=apprisalPwd%>&submit=Login" target="_blank">
	-->
								
<input type='text' name="username" value="<%=username_apprisal%>">
<input type='text' name="password" value="<%=apprisalPwd%>">
<input type='text' name="submit" value="Login">

<input type='submit' value='click'>
</form>
<script>
function myFunction() {
	alert(1);
	try{
    document.getElementById("myForm").submit();
	}catch(err){
		alert(err.message);
	}
}
</script>
</body>
</html>