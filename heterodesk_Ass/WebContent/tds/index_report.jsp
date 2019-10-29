<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%

  String TDS_LOC=session.getAttribute("EMP_TDS_LOC").toString();  
  String TDS_EMP=session.getAttribute("EMP").toString(); 
  String username=(String)session.getAttribute("EMP_ID");
  if(username==null){
	  username="00000";
  }

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


function EmpLoad(){
	  //  alert(1);
	if('<%=TDS_EMP%>'=="EMP"){
		try{
		document.getElementById("TDS_Deduction_spn").style.display='none';
		document.getElementById("TDS_Deduction").disabled=true;
		document.getElementById("EmpId").readOnly=true;
		
		}catch(err){
			
		}
	
	}else{
		return false;
	}
	
}

function loadBu(){
	
	// alert(1);
	 
	var Bu_Mum= ["03#HHC - GENX","05#HHC - MAIN","06#HHC - ONCOLOGY","07#HHC - KRIS","08#HHC - GASTRO","12#HHC - ARV","13#HHC - FRENZA","23#HHC - MUM_CORPORATE","MUM# MUM-ALL"];
	var Bu_Hyd= ["10#HHC - R AND D" ,"11#HHC - CORPORATE" ,"14#HHC - AZISTA" ,"15#HHC - ASSAM" ,"16#HHC - ASHYD" ,"17#AZISTA - AHMEDABAD - SATELLITE" ,"18#AZISTA - KUPPAM" ,"19#DIASPA" ,"20#VOLGA" ,"21#SPECIALITY CARE" ,"22#INSTITUTION BUSINESS" ,"24#MOOSAPET R AND D","26#OPTHALMOLOGY","HYD# HYD-ALL"];
	var Bu_Load=["0#No Rights"];
	var ALLBu=["10#HHC - R AND D" ,"11#HHC - CORPORATE" ,"14#HHC - AZISTA" ,"15#HHC - ASSAM" ,"16#HHC - ASHYD" ,"17#AZISTA - AHMEDABAD - SATELLITE" ,"18#AZISTA - KUPPAM" ,"19#DIASPA" ,"20#VOLGA" ,"21#SPECIALITY CARE" ,"22#INSTITUTION BUSINESS" ,"24#MOOSAPET R AND D","26#OPTHALMOLOGY","03#HHC - GENX","05#HHC - MAIN" , "06#HHC - ONCOLOGY","07#HHC - KRIS","08#HHC - GASTRO","12#HHC - ARV","13#HHC - FRENZA","23#HHC - MUM_CORPORATE","HYD# HYD-ALL","MUM#MUM-ALL","EMPID#By Employeeid"];
	
	var ALLBuEmp=["EMPID#By Employeeid"];

	//"EMPID#By Employeeid"
	
	if("<%=TDS_LOC%>"=="MUM" && '<%=TDS_EMP%>'=="BU"){
		Bu_Load=Bu_Mum;
	}else if("<%=TDS_LOC%>"=="HYD" && '<%=TDS_EMP%>'=="BU"){
		Bu_Load=Bu_Hyd;
	}else if("<%=TDS_LOC%>"=="ALL" && '<%=TDS_EMP%>'=="BU"){
		Bu_Load=ALLBu;
	}else if('<%=TDS_EMP%>'=="EMP"){
		Bu_Load=ALLBuEmp;
		  document.getElementById("EmpId").style.display='';
		  document.getElementById("EmpId").value='<%=username%>';
		  document.getElementById("But1").style.display='none';
		  document.getElementById("But2").style.display='';
	}
	else{
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

function Callempid(data){
	//alert(data.value);
	document.getElementById("EmpId").value='';
	document.getElementById("EmpId").style.display='none';
	
	document.getElementById("But1").style.display='none';
	document.getElementById("But2").style.display='none';
	
	if(data.value=="EMPID"){
	  document.getElementById("EmpId").style.display='';
	  document.getElementById("But2").style.display='';
	}else{
		document.getElementById("EmpId").value='';
		document.getElementById("But1").style.display='';
	}
	
}

function ReportType(data){
	
	document.forms[0].action="index.jsp";
	document.forms[0].submit();
	
}
</script>

<style>

 


</style>

</head>
<body Onload="EmpLoad();loadBu();" style="background-color: #E4F0F0;">

  <form method="post" action="../PayData_Temp" onsubmit="myFunction()" >
 
 
 <div style="width:600px;margin-top:2%; margin-left:12%; font-size:12px"> 
 <input type='radio' name='TypeSelect' id='TDS_Generate' value='TDS_Generate' checked   >TDS Generate & Forecast &nbsp;
 <span id=TDS_Deduction_spn>
    <input type='radio' name='TypeSelect' id='TDS_Deduction' value='TDS_Deduction' onclick="ReportType(this);" >TDS_Deduction Report(24Q Data)
  </span>
  </div>
  <br/> <br/>
  <div style=" margin-left:28%;">
  TDS Generate & Forecast &nbsp;&nbsp;
  <select class="form-control" id="BusinessUnit" name="BusinessUnit" style="width:180px;" onchange="Callempid(this);" >
     
   </select> 
   <input type="submit" value="submit" id='But1' style='display:;'>
    </div> 
   <!-- style="width:180px;margin-top:2%; margin-left:32%;" -->
   
  
     <br/>
  <input type='text' id="EmpId" name="EmpId" placeholder='Employee ID'  style='display:none; width:180px;margin-top:2%; margin-left:47%;'> 
  <input type="submit" value="submit" id='But2' style='display:none;'>
 
  <div> &nbsp;</div>
  <div> &nbsp;</div>
  
  
  
   
  <div id="img" align='center' style='display:none'>
  
     <!--  <img  id="img_load" src="Square.gif" > -->  
  <img  id="img_load" src="giphy.gif" height='250px' width='250px' >
  <p style='color:blue'>Please wait ..! it's taking More time to Calculate TDS & Generate Forecast Reports.... 
  
  <img  id="img_load1" height='25px' width='25px'  src="loader-gears.gif" > 
  </p>
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