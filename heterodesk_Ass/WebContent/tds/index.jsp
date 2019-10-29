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

%>
<script>

function myFunction(){
	
	
	//document.forms[0].disabled='true';
	var Data=document.getElementById("BusinessUnit").value;
	
	if(Data!="0"){
	document.getElementById("img").style.display='';
	document.forms[0].disabled='false';
	return true;
	
	}else{
		alert("Please Select BusinessUnit..!");
	//	document.forms[0].disabled='false';
		return false;
	}
	
	
	
}

function loadBu(){
	
	// alert(1);
	document.getElementById("ErrMsg").innerHTML="";
	 document.getElementById('BusinessUnit').length=0; 
	 document.getElementById("But1").style.display='';
	var Bu_Mum= ["03#HHC - GENX","05#HHC - MAIN","06#HHC - ONCOLOGY","07#HHC - KRIS","08#HHC - GASTRO","12#HHC - ARV","13#HHC - FRENZA","EMPID#By Employeeid","MUM# HR By MUM"];
	var Bu_Hyd= ["10#HHC - R AND D","11#HHC - CORPORATE","14#HHC - AZISTA","15#HHC - ASSAM","15#HHC - ASHYD","EMPID#By Employeeid","HYD#HR By HYD"];
	var Bu_Load=["0#No Rights"];
	var ALLBu=["03#HHC - GENX","05#HHC - MAIN","06#HHC - ONCOLOGY","07#HHC - KRIS","08#HHC - GASTRO","12#HHC - ARV","13#HHC - FRENZA","10#HHC - R AND D","11#HHC - CORPORATE","14#HHC - AZISTA","15#HHC - ASSAM","15#HHC - ASHYD","EMPID#By Employeeid","HYD#HR By HYD","MUM# HR By MUM"];
	
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

function loadBu_report(){
	
	// alert(1);
	document.getElementById("ErrMsg").innerHTML="";
	
	 document.getElementById('BusinessUnit').length=0;
	var Bu_Mum= ["MUM#MUMBAI BUSINESSUNITS"];
	var Bu_Hyd= ["HYD#HYD BUSINESSUNITS,HYDAZ#AZISTA"];
	var Bu_Load=["0#No Rights"];
	var ALLBu=["MUM#MUMBAI BUSINESSUNITS","HYD#HYD BUSINESSUNITS","HYDAZ#AZISTA","ADM#HHC-ALL BUSINESSUNITS"];
	
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

function Callempid(data){
	
	document.getElementById("ErrMsg").innerHTML="";
//	alert(document.getElementById("TDS_Deduction").checked+"~"+data.value+"~"+document.getElementById("TDS_Generate").checked);
	
if(document.getElementById("TDS_Generate").checked){
	

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
}else if(document.getElementById("TDS_Deduction").checked){
	
	
}
	
}

function EmpLoad(){
	  //  alert(1);
	if('<%=TDS_EMP%>'=="EMP"){
		try{
		document.getElementById("TDS_Deduction_spn").style.display='none';
		document.getElementById("TDS_Deduction").disabled=true;
		}catch(err){
			
		}
		document.forms[0].action="index_report.jsp";
		document.forms[0].submit();
	}else{
		return false;
	}
	
}


function ReportType(data){
	
	document.getElementById("ErrMsg").innerHTML="";
	if(data.value=="TDS_Deduction")
	{
		loadBu_report();
		document.getElementById("Ded_Report").style.display='';
		document.getElementById("But1").style.display='none';
		document.getElementById("But2").style.display='none';
		
		document.getElementById("EmpId").style.display='none';
		
		
		
	}else if(data.value=="TDS_Generate"){
		
		document.forms[0].action="index_report.jsp";
		document.forms[0].submit();
		/* loadBu();
		document.getElementById("Ded_Report").style.display='none'; */
	}else{
		document.getElementById("Ded_Report").style.display='';
	}
		
	
	
}



function ReportType1(){
		loadBu_report();
		document.getElementById("Ded_Report").style.display='';
		document.getElementById("But1").style.display='none';
		document.getElementById("But2").style.display='none';
		document.getElementById("EmpId").style.display='none';
}


function SelectMonth(data){
	document.getElementById("ErrMsg").innerHTML="";
	var Period=document.getElementById("FromPeriod").value;
	
	var Data=eval(Period)+2;
	    if(Data>201803){
	    	Data=201803;
	    }
	document.getElementById("ToPeriod").value=Data;
	
}

function SelectMonth1(data){
	
	document.getElementById("But3").disabled=false;
	document.getElementById("ErrMsg").innerHTML="";
	var PeriodFrom=document.getElementById("FromPeriod").value;
	var PeriodTo=document.getElementById("ToPeriod").value;
	
	if(eval(PeriodFrom) > eval(PeriodTo)){
		
		document.getElementById("ToPeriod").value=PeriodFrom;
		//alert("Selected To Month Should Be >= From Month ");
		document.getElementById("ErrMsg").innerHTML="Selected To Month Should Be grater then from Month";
		document.getElementById("But3").disabled=true;
		return false;
	}
	
	
}

function DownloadReport(data){
	
	
	document.getElementById("But3").disabled=false;
	document.getElementById("ErrMsg").innerHTML="";
	var PeriodFrom=document.getElementById("FromPeriod").value;
	var PeriodTo=document.getElementById("ToPeriod").value;
	if(eval(PeriodFrom) > eval(PeriodTo)){
		document.getElementById("ToPeriod").value=PeriodFrom;
		//alert("Selected To Month Should Be >= From Month ");
		document.getElementById("ErrMsg").innerHTML="Selected To Month Should Be grater then from Month";
		//document.getElementById("But3").disabled=true;
		return false;
	}else{
		document.getElementById("img").style.display='';
		document.forms[0].action="../TDS_caliculation";
		document.forms[0].submit();
		document.getElementById("img").style.display='none';
	}
	
	//if(data.id=="But1" || data.id=="But2" ){
	
	/* }else if(data.value=="But3"){
		
		document.getElementById("img").style.display='';
		document.forms[0].action="../TDS_caliculation";
		document.forms[0].submit();
		document.getElementById("img").style.display='none';
		
		
	}else{
		return false;
	} */
	
	
	//myFunction1();
}

function myFunction1(){
	
	alert("HI");
}

/* function ReportType(data){
	
	
	
} */


</script>

<style>

 


</style>

</head>
<body Onload="loadBu();ReportType1();EmpLoad()" style="background-color: #E4F0F0;">

  <!-- <form method="post" action="../PayData_Temp" onsubmit="myFunction()" > -->
 
  <form method="post" action="../TDS_caliculation"  >
  <!-- onsubmit="myFunction()" -->
  <div style="width:600px;margin-top:2%; margin-left:12%; font-size:12px"> 
    <input type='radio' name='TypeSelect' id='TDS_Generate' value='TDS_Generate'   onclick="ReportType(this);" >TDS Generate & Forecast 
    <span id='TDS_Deduction_spn'>
    <input type='radio' name='TypeSelect' id='TDS_Deduction' value='TDS_Deduction' checked onclick="ReportType(this);" >TDS_Deduction Report(24Q Data)
    </span>
  </div>
 
 <div style='margin-left:10%;'>
 Select Business Unit/Group &nbsp;
  <select class="form-control" id="BusinessUnit" name="BusinessUnit" style="width:180px;margin-top:2%;" onchange="Callempid(this);" >
  </select>
  
   <input type="submit" value="submit" id='But1'  style='display:;'>
     <br/>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
  <input type='text' id="EmpId" name="EmpId" placeholder='Employee ID' style='display:none; width:180px;margin-top:2%; '> 
  <input type="button" value="submit" id='But2'  onclick="DownloadReport(this);" style='display:none;'>
 
 <div>
  <div id="Ded_Report" style='margin-left:2px;display:none;'> 
  
    <table border='0' cellpading='1'>
     <tr><th colspan='2' style='font-size:12px'  align='left'>Report Condition's & Taxable Components </th></tr>
  
      <tr><td style='font-size:12px'><input type='checkbox' name='chkBox' value='22' checked Disabled >Paid Gross</td>
       <td style='font-size:12px'><input type='checkbox' name='chkBox' value='87' checked > Arrear </td>
       </tr>
      <tr><td style='font-size:12px'><input type='checkbox' name='chkBox' value='56' checked > LTA </td>
       <td style='font-size:12px'><input type='checkbox' name='chkBox' value='71' checked > PBHYD</td></tr>
      <tr><td style='font-size:12px'><input type='checkbox' name='chkBox' value='80' checked > Incentive </td>
       <td style='font-size:12px'><input type='checkbox' name='chkBox' value='69' checked > Annual Bonus</td></tr>
      <tr><td style='font-size:12px'><input type='checkbox' name='chkBox' value='94' checked  > Bonus </td>
       <td style='font-size:12px'><input type='checkbox' name='chkBox' value='57' checked > PB</td></tr>
      <tr>
      
      <td style='font-size:12px'><input type='checkbox' name='chkBox' value='99'  checked > Ref_Incentive</td>
       <td style='font-size:12px'><input type='checkbox' name='chkBox' value='100' checked > Oth_LTA </td></tr>
    
        <tr>
      
      <td style='font-size:12px'><input type='checkbox' name='chkBox' value='88'  checked > ADDITIONS</td>
       
       </tr>
       
     
      
      
     <td style='font-size:12px;color:red''>
       <input type='radio' name='chkBox_emp' value='ALL' >  All Employess(Active&Inactive & etc..) (FY:2017-2018) </td> 
       
     <td style='font-size:12px;color:red''><input type='radio' name='chkBox_emp' value='TDS_DEDUCT' checked >  TDS Deducted Employess(Between From&To Months) (FY:2017-2018) </td> 
     
       
      
      </tr>
      
      <tr><td style='font-size:12px'>
      
      &nbsp;&nbsp; From (FY:2017-2018) <select class="form-control" id="FromPeriod" name="FromPeriod" style="width:100px;margin-top:2%; " onchange="SelectMonth(this);" >
      
      <option value='201704' > Apr-17 (1Q)</option>
      <option value='201705'> May-17</option>
      <option value='201706'> Jun-17</option>
      <option value='201707' > July-17(2Q)</option>
      <option value='201708'> Aug-17</option>
      <option value='201709'> Sep-17</option>
      <option value='201710' selected > Oct-17(3Q)</option>
      <option value='201711'> Nov-17</option>
      <option value='201712'> Dec-17</option>
      <option value='201801'> Jan-18(4Q)</option>
      <option value='201802'> Feb-18</option>
      <option value='201803'> Mar-18</option>
      
      </select> 
      
      </td> 
      
      <td style='font-size:12px'>
      
     &nbsp;&nbsp; To (FY:2017-2018) <select class="form-control" id="ToPeriod" name="ToPeriod" style="width:100px;margin-top:2%;" onchange="SelectMonth1(this);" >
      
      <option value='201704'> Apr-17</option>
      <option value='201705'> May-17</option>
      <option value='201706' >Jun-17(1Q)</option>
      <option value='201707'> July-17</option>
      <option value='201708'> Aug-17</option>
      <option value='201709' > Sep-17(2Q)</option>
      <option value='201710'> Oct-17</option>
      <option value='201711'> Nov-17</option>
      <option value='201712' selected> Dec-17(3Q)</option>
      <option value='201801'> Jan-18</option>
      <option value='201802'> Feb-18</option>
      <option value='201803'> Mar-18(4Q)</option>
      
      </select> 
       
       </td> 
      </tr>
      
      
      
      
      <tr><td colspan='2'  align='center' id="ErrMsg" style="color:red;font-size:12px;">  &nbsp; </td></tr>
      
      <tr><td colspan='2'  align='center'><input type="submit" value="submit" id='But3' style='display:;'> </td></tr>
      
      <!-- onclick="DownloadReport(this);"  -->
      
    </table>
  
 <!--  <input type='checkbox' name='chkBox' value='22' style='display:none;' checked > --> 
   <!--  <input type='checkbox' name='chkBox' value='87' style='display:none;' checked >  -->
  </div>
  
  
  
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