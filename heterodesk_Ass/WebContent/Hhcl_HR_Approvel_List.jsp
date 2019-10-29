<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!doctype html>
<html class="fixed">
	<head>
	
	 <% 
  String Emp_Payslip=(String)session.getAttribute("PaySlip");
  
  String Emp_ForeCast=(String)session.getAttribute("ForeCast");
  
  
  String Emp_id=(String)session.getAttribute("EMP_ID");
  String TDSFLAG=(String)session.getAttribute("TDSFLAG");
  String EMP_NAME=(String)session.getAttribute("EMP_NAME");
  System.out.println("BioData at JSP::" +Emp_Payslip);
  System.out.println("Emp_BioData at JSP::" +Emp_ForeCast);
  
  String HR_LINKS=(String)session.getAttribute("HR_LINKS");
  String SelectBox=(String)session.getAttribute("SelectBox");
  
  System.out.println("BioData at JSP::" +Emp_Payslip);
  System.out.println("Emp_BioData at JSP::" +Emp_ForeCast);
  
%>

		<!-- Basic -->
		<meta charset="UTF-8">

		
		<meta name="keywords" content="HETERO" />
		<meta name="description" content="Hetero">
		<meta name="author" content="Hetero">

         <title>Hetero Healthcare LTD</title>
          <link rel="icon" href="LOH.png" />
          
		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />


		<!-- Web Fonts  -->
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

		<!-- Vendor CSS -->
		<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
		<link rel="stylesheet" href="assets/vendor/magnific-popup/magnific-popup.css" />
	
<!-- <link rel="stylesheet" href="assets/stylesheets/GScroll.css" />
 -->		<link rel="shortcut icon" href="/images.png" type="image/x-icon" />

		<!-- Specific Page Vendor CSS -->
		<link rel="stylesheet" href="assets/vendor/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css" />
		

		<!-- Theme CSS -->
		<link rel="stylesheet" href="assets/stylesheets/theme.css" />

		<!-- Skin CSS -->
		<link rel="stylesheet" href="assets/stylesheets/skins/default.css" />
	
		<!-- Theme Custom CSS -->
		<link rel="stylesheet" href="assets/stylesheets/theme-custom.css">
			<!-- <script src="assets/vendor/jquery/jquery.js"></script> -->
			<!--  <link rel="stylesheet" href="colorbox.css" /> -->
	<!-- <script src="jquery.colorbox.js"></script>  -->
	

<!-- 	 <script src="assets/vendor/nanoscroller/nanoscroller.js"></script>  -->
<!-- 	<link rel="stylesheet" href="assets/vendor/nanoscroller/nanoscroller.css"> -->
		
	<!--  <script src="assets/javascripts/jquery-1.8.3.js"></script> 
	<script src="assets/javascripts/GScroll.js"></script> 
	
	 
      
        
		<!-- Head Libs -->
		<!-- <script src="assets/vendor/modernizr/modernizr.js"></script>
		<link rel="stylesheet" type="text/css" href="css3clock.css" /> -->

<!-- <link href="scroll/perfect-scrollbar.css" rel="stylesheet">
      <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
      <script src="scroll/jquery.mousewheel.js"></script>
      <script src="scroll/perfect-scrollbar.js"></script> -->
      
  
  
  <link rel="stylesheet" href="css/icon-font.min.css" type='text/css' />
<link rel="stylesheet" href="css/jqueryui.css" type='text/css' />
<link rel="stylesheet" href="jquery-ui-1.12.1.custom/jquery-ui.css">
   <script src="jquery-ui-1.12.1.custom/jquery-1.12.4.js"></script>
   <script src="jquery-ui-1.12.1.custom/jquery-ui.js"></script> 
  <style>
  .modal {
}
.vertical-alignment-helper {
    display:table;
    height: 100%;
    width: 100%;
}
.vertical-align-center {
    /* To center vertically */
    display: table-cell;
    vertical-align: middle;
}
.modal-content {
    /* Bootstrap sets the size of the modal in the modal-dialog class, we need to inherit it */
    width:inherit;
    height:inherit;
    /* To center horizontally */
    margin: 0 auto;
}
select {
    border: 1px solid #E5E7E9;
    border-radius: 6px;
   height:30px;
    padding: 6px;
    outline: none;
}
  
  </style>
  
  <script src="MyAng.js"></script>

<script>
var app = angular.module('myApp', []);
app.controller('formCtrl', function($scope , $http) {
	var Data="";
	$scope.Data_2="";
	$scope.Data_1="";
	$scope.Data_3="";
	$scope.HR_LINKS="";
	$scope.SelectBox="";
	
	try{
		 
		$scope.Approve=function(event){
		
			//var Comm_data="NO_DATA";
			
			//alert(event.target.value);
			/* if(val.value=="reject"){
				
				document.getElementById("comment").style.display="" ;
				//Comm_data= prompt("Please enter Reason", "invalid data");
			}else{
				document.getElementById("comment").style.display="none" ;
			} */
			var comment=document.getElementById("comment_box").value;
			
			
			var button="Approved";
			
			
			
			  if(event.target.value=="reject" && comment.length==0){
					
					alert("Comment for reject reason..!");
					return false;
					
					
					//Comm_data= prompt("Please enter Reason", "invalid data");
				} 
			  
			  if(event.target.value=="reject")
				  {
				  button="Reject"; 
				 
				  
				  }
			  
			  
			 
			 
			 //button="Approved";
			 
			 $http({
		         method : "POST",
		         url : "Fetch_Data?type="+event.target.alt+"&data="+event.target.name+"&Mode="+event.target.value+"&comment="+comment+"&Rejectid="+event.target.id.replace("_R","")+"",
		    }).then(function mySucces(response) {
		    	
		    	//alert(response.data);
		    	if(eval(response.data)==1)
		    		
		    		{
		    		//alert(""+button)
		    		
		    		//alert(event.target.id);
		    	try{	
		    		
		    		//button="Reject"; Approved
		    		
		    		if(button=="Reject"){
		    			$('#'+event.target.id+'').hide();
		    			$('#'+event.target.id.replace("_R","").concat("_S")+'').show();
		    			$('#'+event.target.id.replace("_R","").concat("_S")+'').val(button);
			    		$('#'+event.target.id.replace("_R","")+'').hide();
			    		
		    		}else if(button=="Approved"){
		    			
		    			$('#'+event.target.id+'').hide();
		    			$('#'+event.target.id.concat("_R")+'').hide();
		    			$('#'+event.target.id.concat("_S")+'').val(button);
			    		$('#'+event.target.id.concat("_S")+'').show();
		    			
		    			
		    		}
		    		 
		    		
		    	}catch(err){
		    		alert(err.message+"err message")
		    	}
		    		
		    		}
		    	
		    	
		    	
		    },function myError(response){
		      
		        document.getElementById("image_scrl").style.display="none" ;
		    });
	 }
    
	$scope.myFunction2=function(val) {
    	
    	 // alert("HAI"+val);
    	 
    	 var val2=document.getElementById("type");
    	 var val=val2.value;
    	 
    	 if(val=="Select"){
    		
    		 return false;
    	 }
		 /* document.getElementById("errMessage").innerHTML="";
		 document.getElementById("image_scrl").style.display="";
		 document.getElementById("errMessage").innerHTML=""; */
			/* document.getElementById("from_1").value="";
			document.getElementById("to_1").value=""; */
			
		 //var Month_Sel=document.getElementById("Month_Sel").value;
		/*  $scope.Data_1=[{"ID":"--","NAME":"--","DAY1":"--#''","DAY2":"--#''","DAY3":"--#''","DAY4":"--#''","DAY5":"--#''","DAY6":"--#''","DAY7":"--#''","DAY8":"--#''","DAY9":"--#''","DAY10":"--#''","DAY11":"--#''","DAY12":"--#''","DAY13":"--#''","DAY14":"--#''","DAY15":"--#''","DAY16":"--#''","DAY17":"--#''","DAY18":"--#''","DAY19":"--#''","DAY20":"--#''","DAY21":"--#''","DAY22":"--#''","DAY23":"--#''","DAY24":"--#''","DAY25":"--#''","DAY26":"--#''","DAY27":"--#''","DAY28":"--#''","DAY29":"--#''","DAY30":"--#''","DAY31":"--#''"}];
		 $scope.Data_2={"DAY1":"DAY1","DAY2":"DAY2","DAY3":"DAY3","DAY4":"DAY4","DAY5":"DAY5","DAY6":"DAY6","DAY7":"DAY7","DAY8":"DAY8","DAY9":"DAY9","DAY10":"DAY10","DAY11":"DAY11","DAY12":"DAY12","DAY13":"DAY13","DAY14":"DAY14","DAY15":"DAY15","DAY16":"DAY16","DAY17":"DAY17","DAY18":"DAY18","DAY19":"DAY19","DAY20":"DAY20","DAY21":"DAY21","DAY22":"DAY22","DAY23":"DAY23","DAY24":"DAY24","DAY25":"DAY25","DAY26":"DAY26","DAY27":"DAY27","DAY28":"DAY28","DAY29":"DAY29","DAY30":"DAY30","DAY31":"DAY31"};
		 */ 
		
		 
		  $http({
		         method : "POST",
		         url : "Data?type="+val+"",
		    }).then(function mySucces(response) {
		    	
		    	 //alert(response.data);
		    	if(val=="EmployeeName"){
		    		
		    		 $('#tr1').show();
		        	 $('#tr2').hide();
		        	 $('#tr3').hide();
		        	/* $('#tr4').hide();*/
		        	 $('#tr5').hide(); 
		        	 $('#tr6').hide();
		        	 $('#tr7').hide();
		        	 $('#tr8').hide(); 
		         
		        	 
		        	 
		    		
		    	}else if(val=="DateOfBirth"){
		    		
		    		$('#tr1').hide();
		        	 $('#tr2').show();
		        	 $('#tr3').hide();
		        	/* $('#tr4').hide();*/
		        	 $('#tr5').hide();
		        	 $('#tr6').hide();
		        	 $('#tr7').hide();
		        	 
		        	 $('#tr8').hide();
		         
		        	 
		    		
		    	}else if(val=="PFNO"){
		    		
		    		 $('#tr1').hide();
		        	 $('#tr2').hide();
		        	 $('#tr3').show();
		        	/* $('#tr4').hide();*/
		        	 $('#tr5').hide();
		        	 $('#tr6').hide();
		        	 $('#tr7').hide();
		        	 $('#tr8').hide();
		    		
		    	} else if(val=="PFUAN"){
		    		 $('#tr1').hide();
		        	 $('#tr2').hide();
		        	 $('#tr3').hide();
		        	/* $('#tr4').hide();*/
		        	 $('#tr5').show();
		        	 $('#tr6').hide();
		        	 $('#tr7').hide();
		        	 $('#tr8').hide();
		    		
		    	}else if(val=="BANKDETAILS"){
		    		
		    		 $('#tr1').hide();
		        	 $('#tr2').hide();
		        	 $('#tr3').hide();
		        	/* $('#tr4').hide();*/
		        	 $('#tr5').hide();
		        	 $('#tr6').show();
		        	 $('#tr7').hide();
		        	 $('#tr8').hide();
		    		
		    	}else if(val=="PerAddress"){
		    		
		    		$('#tr1').hide();
		        	 $('#tr2').hide();
		        	 $('#tr3').hide();
		        	/* $('#tr4').hide();*/
		        	 $('#tr5').hide();
		        	 $('#tr6').hide();
		        	 $('#tr7').show();
		        	 $('#tr8').hide();
		    	}
		    	
		    	
                 else if(val=="Others"){
		    		
		    		$('#tr1').hide();
		        	 $('#tr2').hide();
		        	 $('#tr3').hide();
		        	/* $('#tr4').hide();*/
		        	 $('#tr5').hide();
		        	 $('#tr6').hide();
		        	 $('#tr7').hide();
		        	 $('#tr8').show();
		    	}
		    	
			
		    	
		    	
		    	$scope.Data_2=response.data;
		    	
		    		//alert(response.data.FIRSTNAME);
		    	
		    },function myError(response){
		        $scope.Data_1 = response.statusText;
		        document.getElementById("image_scrl").style.display="none" ;
		    }); 
    }
	
    
	Data="";
	$scope.Data_1="";
    $scope.HR_LINKS=<%=HR_LINKS%>;
    $scope.SelectBox=<%=SelectBox%>;
    $scope.Data_2="";
    $scope.Data_3="";
	 
  /*  // var json = {"forum":[{"id":"1","created":"2010-03-19 ","updated":"2010-03-19 ","user_id":"1","vanity":"gamers","displayname":"gamers","private":"0","description":"All things gaming","count_followers":"62","count_members":"0","count_messages":"5","count_badges":"0","top_badges":"","category_id":"5","logo":"gamers.jpeg","theme_id":"1"}]};
    
    
    var json3 =[
       		{
            "title": "Professional JavaScript",
            "author": "Nicholas C. Zakas"
        	},
        	{
            "title": "JavaScript: The Definitive Guide",
            "author": "David Flanagan"
        	},
        	{
            "title": "High Performance JavaScript",
            "author": "Nicholas C. Zakas"
        	}];
    
    
    
    
    $scope.Data_3=json3;
    
    
   alert("$scope.Data_3::"+$scope.Data_3[0].title) */
    
    //alert("$scope.Data_3::"+$scope.Data_3[0]);
    
    //EVENTDATE


	}catch(err){
		alert(err.message);
	}
    
    $scope.empid = Data.EMPLOYEESEQUENCENO;
    $scope.empname= Data.CALLNAME;
    $scope.emp_doj= Data.DATEOFJOIN;
    $scope.emp_Dep= Data.DEPARTMENT;
    $scope.emp_Des= Data.DESIGNATION;
    $scope.emp_job_type= Data.TYPE;
   
    
   // $scope.HOLIDAYS = Data_3.EVENTDATE;
    
   // alert("$scope.HOLIDAYS::"+$scope.HOLIDAYS);
    
 });
 
</script> 

	</head>
	
	<body  onpageshow="if (event.persisted) noBack(); "   ng-app="myApp" ng-controller="formCtrl" >
		<section class="body">

			<!-- start: header onload="disableBackButton();-->
			<header class="header">
				<div class="logo-container">
					<a href="#" class="logo">
						<img src="assets/images/logo.png" alt="" />
					</a>
					<div class="visible-xs toggle-sidebar-left" data-toggle-class="sidebar-left-opened" data-target="html" data-fire-event="sidebar-left-opened">
						<i class="fa fa-bars" aria-label="Toggle sidebar"></i>
					</div>
				</div>
			
				<!-- start: search & user box -->
				<div class="header-right">
			
					<div id="userbox" class="userbox">
						<a href="#" data-toggle="dropdown">
							<div class="clear"></div>
							<div class="profile-info" data-lock-name="" data-lock-email="">
								<span class="name"><%=EMP_NAME %></span>
								
							</div>
			
							<i class="fa custom-caret"></i>
						</a>
			
						<div class="dropdown-menu">
							<ul class="list-unstyled">
								<li class="divider"></li>
								<!-- <li>
									<a role="menuitem" tabindex="-1" href="data-toggle="modal" data-target="#myModal"><i class="fa fa-cogs"></i>Change Password</a>
									<a role="menuitem" tabindex="-1" data-toggle="modal" href="#changepassword" ><i class="fa fa-cogs"></i>Change Password</a>
								</li> -->
								<li>
									<a role="menuitem" tabindex="-1" href="logout"><i class="fa fa-power-off"></i> Logout</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!-- end: search & user box -->
			</header>
			<!-- end: header -->

			<div class="inner-wrapper">
				<!-- start: sidebar -->
				<aside id="sidebar-left" class="sidebar-left">
					<div class="nano">
						<div class="nano-content">
							<nav id="menu" class="nav-main" role="navigation">
								<ul class="nav nav-main">
									
									<li class="active">
									  <a href="User_Auth?Routing=DashBoard">
										<i class="fa fa-tachometer"></i>
										<span class="font-bold">Home</span>
									  </a>
									</li>
									
									<li  style='display:{{ HR_LINKS.LINK1 }}'>
									  <a href="PayslipDownload?Routing=UPDATE_emp"  >
										<i class="fa fa-user"></i>
										<span class="font-bold">Emp Profile Update</span>
									  </a>
									</li>
									
									<li style='display:{{ HR_LINKS.LINK2 }}'>
									  <a href="PayslipDownload?Routing=APPROVAL"  >
										<i class="fa fa-check" ></i>
										<span class="font-bold">Profile Update approval</span>
									  </a>
									</li>
									
									
									<li style='display:{{ HR_LINKS.LINK3 }}'>
									  <a href="ManagerApprovals?Routing=ManagerAppr_Resi&APP_MOD=HR" target="_parent">
										<i class="fa fa-check" ></i>
										<span class="font-bold">Resignation Approvals</span>
									  </a>
									</li>
									
									 <li  style='display:{{ HR_LINKS.LINK4 }}' >
									  <a href="ManagerApprovals?Routing=Dept_att_HR" target="_parent">
										<i class="fa fa-users" ></i>
										<span class="font-bold">BusinessUnit.Attendance</span>
									  </a>
									</li> 
									
									 <li  style='display:{{ HR_LINKS.LINK5 }}' >
									  <a href="ManagerApprovals?Routing=LeaveQuota" target="_parent">
										<i class="fa fa-users" ></i>
										<span class="font-bold">Leave Quota</span>
									  </a>
									</li> 
									
									<!-- <li>
									  <a href="NewJoinees?Routing=MyProfile"  >
										<i class="fa fa-user"></i>
										<span class="font-bold">Profile</span>
									  </a>
									</li>
									<li>
									  <a href="PayslipDownload">
										<i class="fa fa-download"></i>
										<span class="font-bold">Downloads</span>
									  </a>
									</li>
									
									<li>
									  <a href="hhcl_careers.jsp">
										<i class="fa fa-briefcase"></i>
										<span class="font-bold">Careers</span>
									  </a>
									</li> -->
									
									
							     <!-- Manager Approvals Start -->
							
									 
									
									<!-- <li>
									  <a href="NewJoinees?Routing=DEPINFO">
										<i class="fa fa-users"></i>
										<span class="font-bold">Department Information</span>
									  </a>
									</li>
									<li>
									  <a href="resignation.html" target="_blank">
										<i class="fa fa-sign-out"></i>
										<span class="font-bold">Resignation</span>
									  </a>
									</li>
									<li>
									  <a href="http://www.heterohealthcare.com/" target="_blank">
										<i class="fa fa-info-circle"></i>
										<span class="font-bold">About Our Organization</span>
									  </a>
									</li> -->
									
									
									
								</ul>
								
							</nav>
							
					</div>
				
							
<!-- <script type="text/javascript">
				$(function() {
  $( "#datepicker" ).datepicker({ firstDay: 1});
});
				</script>	 -->		
						</div>
				</aside>
				<!-- end: sidebar -->

				<section role="main" class="content-body">
					<header class="page-header">
					<div class="clear col-md-5" style="color:red;"> <marquee> <%=session.getAttribute("HHCL_EVENT_INFO")%>  </marquee></div>
					
				  <div class="col-md-2 tool-tip-header">
			<!-- 	  <div class="weather">
						<a href="#" class="tooltip">
							<img src="images/ww.png" data-trigger-modal="modal" class="icon-img" />
							<span>
								<img class="callout" src="images/callout_black.gif" />
								<img src="images/css3-tooltip-image.png" style="float:right;" />
								<strong>CSS only Tooltip</strong><br />
								Pure CSS popup tooltips with clean semantic XHTML.
							</span>
						</a>
				   </div>
				   <div class="quote">
						<a href="#" class="tooltip">
							<img src="images/hand.png" class="icon-img" />
							<span>
								<img class="callout" src="images/callout_black.gif" />
								<img src="images/css3-tooltip-image.png" style="float:right;" />
								<strong>CSS only Tooltip</strong><br />
								Pure CSS popup tooltips with clean semantic XHTML.
							</span>
						</a>
				   </div> --> 
				 
				 </div> 
				 
					<div class="col-md-3 col-sm-3" style="float: right;">
					<span><b>Date:</b> <span>
					<script>
									var mydate=new Date()
									var year=mydate.getYear()
									if (year < 1000)
									year+=1900
									var day=mydate.getDay()
									var month=mydate.getMonth()
									var daym=mydate.getDate()
									if (daym<10)
									daym="0"+daym
									var dayarray=new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday")
									var montharray=new Array("January","February","March","April","May","June","July","August","September","October","November","December")
									document.write("<small><font color='#0088cc' face='Arial'><b>"+dayarray[day]+", "+montharray[month]+" "+daym+", "+year+"</b></font></small>")

								</script>
						 
					</span> </span>
					</div>
					<!--  <div class="col-md-2 user">
					  
					  <ul class="user-img">
					  <li> <img src="images/user1.png" alt="user">:</li>
					  <li><span> <p>12389</p> </span></li>
					  
					  </ul>
					
					  
					</div> -->
					
					
					
					
					
					
				</header>
					<!-- start: page -->
					<div class="row">
						<div class="col-md-12">
							<!-- start: page -->
					<div id="description" class="wrapper" style="margin-top:10%;">
					<div class="container">
						<div class="row " >
							<div class="col-md-10 middle-reg1">
								<div class="panel panel-danger" >
									<div class="panel-heading1">
										<span><b>HR Approval</b> </span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								        <!-- <span id="Responce_Message" style="color:white;" >  </span> -->
									</div>
									<div class="panel-body">
										
											<div class="col-sm-12 col-md-12 center_div2">
											
												<div class="form-group" style="margin-left: 9%;">
													<label class="col-md-1 control-label">Types </label>
													<div class="col-md-10">
															<!-- class="form-control " ng-change="myFunction2(this.value);"  -->
															<select id="type" name="type"   class="form-control1 col-md-4" >
														    <option value="Select">--Select One--</option>
															<option  value="{{key}}"   ng-repeat="(key, value) in SelectBox" />{{value}}</option> 
															<!-- <option value="EmployeeName">EMPLOYEE NAME</option>
															<option value="DateOfBirth">DATE OF BIRTH</option>
															<option value="PFNO">PF NUMBER</option>
															<option value="ACCOUNTNO">ACCOUNT NO</option>
															<option value="PFUAN">PF UAN NO</option>
															<option value="BANKDETAILS">BANK DETAILS</option>
															<option value="PerAddress">PERMANENT ADDRESS</option>
															 -->
															</select>
															 
															&nbsp;&nbsp;&nbsp; <button class="btn1 btn btn-primary   "  ng-click="myFunction2('My')">View </button> 							

                                                  &nbsp;  &nbsp;
                                                  
                                                  <span id='comment'>Comment : <input type='text' value='' id='comment_box'></span>
													</div>
												</div>
											
												
												
												
											
												
												</div>
											
											<div class="col-md-12">
											<div class="table1">
												 


												  <div class="table-responsive">
													<table class="table table-bordered" id="table" style="margin-top: 20px;">
													  <thead class="td-table">
														<tr class="tr-table" id="tr1" style=display:none;>
														 
														  <th>EMPLOYEE ID</th>
						                                  <th>FULL NAME</th>
						                                   <th>LAST NAME</th>
						                                  <th>ACTION</th>
						                                    
														</tr>
														
														
														<tr class="tr-table" id="tr2" style=display:none;>
														 
														  <th>EMPLOYEE ID</th>
														 
						                                  <th>DATEOFBIRTH</th>
						                                   <th>ACTION</th>
						                                   
														</tr>
														
														
														
														<tr class="tr-table" id="tr3" style=display:none;>
									                           <th>EMPLOYEEID</th>
									                          <th>PFNO</th>
									                          <th>ACTION</th>
									                   </tr>
														
														
													<!-- 	<tr class="tr-table" id="tr4" style=display:none;>
									<th>EMPLOYEEID</th>
									<th>ACCOUNTNO</th>
									<th>ACTION</th>
									</tr> -->
												  <tr class="tr-table" id="tr5" style=display:none;>
									<th>EMPLOYEEID</th>
									<th>ACCOUNTNO</th>
									<th>ACTION</th>
									</tr>	  
									
									<tr class="tr-table" id="tr6" style=display:none;>
									<th>EMPLOYEEID</th>
									<th>BANKNAME</th>
									<th>ACCOUNTNO</th>
									<th>ACTION</th>
									</tr>
									
									<tr class="tr-table" id="tr7" style=display:none;>
									<th>EMPLOYEEID</th>
									<th>PER ADDRESS1</th>
									<th>PER ADDRESS2</th>
									<th>MOBILE NO</th>
									<th>EMAILID</th>
									<th>STATE</th>
									<th>CITY</th>
									<th>ACTION</th>
									</tr>
									
									<tr class="tr-table" id="tr8" style=display:none;>
									<th style="width: 11%;">EMPLOYEEID</th>
									<th style="width: 28%;">E-Mail</th>
									<th>Reason</th>
									<th style='width: 19%;'>ACTION</th>
									</tr>
									
									
									
										
													  </thead>
													  <tbody id="tableBody">
									<tr ng-repeat="x in Data_2"  > 
									
									<!-- obj.put("EMPLOYEEID", "NODATA");
		           		 			obj.put("FIRSTNAME", "NODATA");
		           				 	obj.put("LASTNAME", "NODATA");
		            				obj.put("BUTTON","NODATA");
		            				obj.put("BUTTON1","NODATA"); -->
		            
									<td style='display:{{x.td1}}'> {{x.EMPLOYEEID}} </td>
									<td style='display:{{x.td2}}'> {{x.FIRSTNAME}}  </td>
									<td style='display:{{x.td3}}'> {{x.LASTNAME}} </td>
									<td style='display:{{x.td4}}'> {{x.MOBILE}} </td>
									<td style='display:{{x.td5}}'> {{x.EMAILID}} </td>
									<td style='display:{{x.td6}}'> {{x.STATE}} </td>
									<td style='display:{{x.td7}}'> {{x.CITY}} </td>
									 
									
									<!-- <td> {{x.DATEOFBIRTH}} </td> -->
									
									<!-- <td> {{x.FIRSTNAME}} </td> -->
									
									<td> <input type='button' value='approved' alt="{{x.alt}}" name="{{x.Data}}"  class="btn btn-primary app-re" id="{{x.id}}" ng-click='Approve($event)' style='display:{{x.BUTTON}}'>  &nbsp; <input type='button' name="{{x.Data}}"  id="{{x.id}}_R" class='btn btn-primary app-re' ng-click='Approve($event)' alt="{{x.alt}}" value='reject' style='display:{{x.BUTTON1}}'> <input type="button" id="{{x.id}}_S" class='btn btn-primary app-re'  style='display:none;' ></td>
									
									</tr>
													  </tbody>
													</table>
													<div  id="noteid" align="center">Note <input type="text" ></div>
												  </div>
												</div>
											</div>
												<div class="clearfix"></div>
										</div>
									</div>
									
									
								</div>
							</div>
						</div>	
						
					</div>
					</div>
					<!-- end: page -->
							   
							   
							   
							   
							   
							   
							   
						</div>
					</div>
					
								
					
					<!-- end: page -->
					<!--Change Password Modal-->
					<div class="modal fade" id="changepassword" tabindex="-1" role="dialog" aria-labelledby="myModalLabel-pwd" aria-hidden="true">
					<div class="vertical-alignment-helper">
					  <div class="modal-dialog vertical-align-center" role="document">
						<div class="modal-content ">
						  <div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							  <span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel-pwd">Change Password</h4>
						  </div>
							<div class="modal-body">
								<form action="" method="post" enctype="multipart/form-data">
									<div class="form-group ">
											<label class="col-sm-5 control-label">Current Password</label>
											<div class="col-sm-7">
												<input type="password" class="form-control" name="currentpassword" id="currentpassword" placeholder="Current Password">
											</div>
										</div>
										<div class="form-group ">
											<label class="col-sm-5 control-label">New Password</label>
											<div class="col-sm-7">
												<input type="password" class="form-control"  name="newpassword" id="newpassword" placeholder="New Password" onblur="validatePassword('N');">
												
											</div>
										</div>
										<div class="form-group ">
											<label class="col-sm-5 control-label">Confirm Password</label>
											<div class="col-sm-7">
												<input type="password" class="form-control" name="confirmpassword" id="confirmpassword" onblur="validatePassword('N');" placeholder="Confirm Password">
											</div>
										</div>
										<br>
										<span style="color:red;">Instructions:</span>Password should be minimum 6 & maximum 15 Characters with <br>minimum one letter [a-z(OR)A-Z] ,minimum one special Character [!,@,#,$,&] and Number[0-9].
										<span style="color:#08c;">Example:Hetero@123</span>	
										
																		
								</form>
							</div>
							<div class="modal-footer">
							<span id="Responce_Message" style="color:red;" class="align-left" >  </span>
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>&nbsp;
							<button type="button" id="Responce_Message_btn" class="btn btn-primary" onclick="validatePassword('Y')" >Save</button>
						  </div>
						</div>
					  </div>
					  </div>
					</div>
					
				</section>
			</div>
		</section>
		<!-- Vendor -->
		<!-- <script src="assets/vendor/jquery/jquery.js"></script> -->
		<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
		<!-- Specific Page Vendor -->
		<!-- <script src="assets/vendor/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script> -->
		<!-- Theme Base, Components and Settings -->
		<script src="assets/javascripts/theme.js"></script>
		<!-- Theme Custom -->
		<script src="assets/javascripts/theme.custom.js"></script>
		<!-- Theme Initialization Files -->
		<script src="assets/javascripts/theme.init.js"></script>
		<!-- Examples -->
		<script src="assets/javascripts/dashboard/examples.dashboard.js"></script>
				<!-- start popup ad here -->
			<script src="assets/javascripts/jquery.colorbox.js"></script>
	<!--	<script>
			$(document).ready(function(){
                 setTimeout(function() {
					$(".group1").colorbox({rel:'group1' ,open:true});  

                ///$.fn.colorbox({href:"img/image002.png", open:true});
				}, 1500);
				//Examples of how to assign the Colorbox event to elements
				//$(".group1").colorbox({rel:'group1'});
				//$.fn.colorbox({href:"../content/01.jpg", open:true});
			});
		</script>
		<div style="display:none;">
		<h1>Colorbox Demonstration</h1>
		<h2>Elastic Transition</h2>
		<p><a class="group1" href="assets/images/01.jpg" title="Drawing & Painting Competition">Grouped Photo 1</a></p>
		<p><a class="group1" href="assets/images/01.jpg" title="Drawing & Painting Competition">Grouped Photo 2</a></p>
		<p><a class="group1" href="assets/images/01.jpg" title="Drawing & Painting Competition">Grouped Photo 3</a></p>
		<p><a class="group1" href="assets/images/01.jpg" title="Drawing & Painting Competition">Grouped Photo 4</a></p>
		</div>-->
	<!-- end popup ad here -->
<!-- Change Password -->
   <script src="hr_approve.js"></script>
 	</body>
</html>