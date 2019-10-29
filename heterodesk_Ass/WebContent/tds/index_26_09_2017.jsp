<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%

  String TDS_LOC=session.getAttribute("EMP_TDS_LOC").toString();  

%>
<script>

function myFunction(){
	
	
	
	var Data=document.getElementById("BusinessUnit").value;
	
	if(Data!="0"){
	document.getElementById("img").style.display='';
	return true;
	
	}else{
		alert("Please Select BusinessUnit..!");
		return false;
	}
	
	
	
}

function loadBu(){
	
	// alert(1);
	 
	var Bu_Mum= ["03#HHC - GENX","04#GENX - ARV","05#HHC - MAIN","06#HHC - ONCOLOGY","07#HHC - KRIS","08#HHC - GASTRO","12#HHC - ARV","13#HHC - FRENZA"];
	var Bu_Hyd= ["10#HHC - R AND D","11#HHC - CORPORATE","14#HHC - AZISTA","15#HHC - ASSAM","15#HHC - ASHYD"];
	var Bu_Load=["0#No Rights"];
	var ALLBu=["03#HHC - GENX","04#GENX - ARV","05#HHC - MAIN","06#HHC - ONCOLOGY","07#HHC - KRIS","08#HHC - GASTRO","12#HHC - ARV","13#HHC - FRENZA","10#HHC - R AND D","11#HHC - CORPORATE","14#HHC - AZISTA","15#HHC - ASSAM","15#HHC - ASHYD"];
	
	if("<%=TDS_LOC%>"=="MUM"){
		Bu_Load=Bu_Mum;
	}else if("<%=TDS_LOC%>"=="HYD"){
		Bu_Load=Bu_Hyd;
	}else if("<%=TDS_LOC%>"=="ALL"){
		Bu_Load=ALLBu;
	}else{
		Bu_Load=Bu_Load;
	}
	var sel = document.getElementById('BusinessUnit');
	
	//alert("Bu_Load.length"+Bu_Load.length);
	
	try{
	for(var i = 0; i < Bu_Load.length; i++) {
	    var opt = document.createElement('option');
	    opt.innerHTML = Bu_Load[i].split("#")[1];
	    opt.value = Bu_Load[i].split("#")[0];
	    sel.appendChild(opt);
	}
	}catch(err){
		
	}
	
}

</script>

<style>

 


</style>

</head>
<body Onload="loadBu();">

  <form method="post" action="../PayData_Temp" onsubmit="myFunction()" >
 
 
  <select class="form-control" id="BusinessUnit" name="BusinessUnit" style="width:180px;margin-top:2%; margin-left:32%;">
       
    </select>   
  
 <input type="submit" value="submit">
  
  
  <div> &nbsp;</div>
  <div> &nbsp;</div>
   
  <div id="img" align='center' style='display:none'>
  
      <img  id="img_load" src="Square.gif" > 
  
  <p style='color:blue'>Please wait .. While Calculating TDS & Generate Forecast Reports.... 
  
  <img  id="img_load1" height='25px' width='25px'  src="loader-gears.gif" > </p>
  </div>
  
<!--   <form method="post" action="UploadServlet"
        enctype="multipart/form-data">
        Select file to upload: <input type="file" name="file" /><br />
        <Select type='text' name="description" >
        <option value="test.tablename" checked>upload</option>
        <option value="test.tds_prev_employer_income_temp" >Preview Employee Data Load</option>
        
        
        
        </Select>
        <br/>
        ID Condition Required &nbsp;<input type='radio' name='ckrad' value='Y' checked>Y &nbsp; <input type='radio' name='ckrad' value='N'>&nbsp; N
 <br /> <input type="submit" value="Upload" /> -->
         
<!--  <input type="hiden" value="http://10.30.0.41:8080/EmployeeAuth/download/files/10418/072016-PAYSLIP-10418.pdf" name="url"> -->
 <!--  <form method="post" action="UploadServlet"
        enctype="multipart/form-data">
        Select file to upload: <input type="file" name="file" /><br />
        <Select type='text' name="description" >
        <option value="test.tablename" checked>upload</option>
        </Select>
        <br/>
        ID Condition Required &nbsp;<input type='radio' name='ckrad' value='Y' checked>Y &nbsp; <input type='radio' name='ckrad' value='N'>&nbsp; N
        <br /> <input type="submit" value="Upload" />-->
 
<!--   <form method="Post" action="PayData_Temp" >
 TDS PAY DATA
 <!--
 <input type='file' name="file " >
 <form method="Post" action="Sample_Retrive" >
 TDS PAY DATA
 <input type="hidden" name="pdfclick" value="10454">
 Sample_Retrive
 <form method="get" action="pdfServletconfirm" >
 <form method="post" action="PayData_Temp" >
 TDS PAY DATA
 <form method="post" action="GetPaySlip" >
 TDS PAY DATA
 ConFormationLetter
  <form method="post" action="PayData_Temp" >
 TDS PAY DATA
  -->
 <!-- <form method="post" action="PayData_Temp" >
 TDS PAY DATA -->
<!--   Year<select name='FinancialYear'>
<option value="201604-201703">2016-07</option>
</select> -->
<!--  <br/>
Select Months<select name='FinancialYear'>
<option value="01">JAN</option>
<option value="02">Feb</option>
<option value="03">Mar</option>
<option value="04">Apr</option>
</select> -->
<!-- 
  <form method="post" action="PayData" >
 <form method="post" action="MySQLDbcpServlet" > -->
<!--  
 <form method="post" action="DriverCall" > -->
<!--   <form method="POST" action="download">--> 
<!-- MySQLDbcpServlet <form method="POST" action="download"> -->



</form>

</body>
</html>