<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script type='text/javascript'>
function directload(){
	//alert("1");
	document.forms[0].submit();
}
</script>
</head>
<body onload="setTimeout(directload, 1000);">
<form action="AssessmentForm" method='post' >
<span style='color:red'>Please wait .....! </span>
<input type='hidden' name="Routing" value="FormAssessment">
</form>
</body>
</html>