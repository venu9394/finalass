<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script>

function formSubmit(){
	document.forms[0].action="Version2?Routing=LEAVE_BALANCE";
	document.forms[0].submit();
}
setTimeout(formSubmit, 1500);
</script>
</head>
<body  onload="formSubmit();">
<form action="NewJoinees?Routing=LEAVE_BALANCE" method="Post" >
 <span align='center' style='color:blue;font:italic'>Please Wait ....</span>
 <div align='center'>
   <img src="assets/images/load1.gif" width='100%' height='100%' />
 </div>
</form>
</body>
</html>