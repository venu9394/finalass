

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <% 
  String Emp_Payslip=(String)session.getAttribute("PaySlip");
  String Emp_ForeCast=(String)session.getAttribute("ForeCast");
  String Emp_id=(String)session.getAttribute("EMP_ID");
  String TDSFLAG=(String)session.getAttribute("TDSFLAG");
  String EMP_NAME=(String)session.getAttribute("EMP_NAME");
  String HR_LINKS=(String)session.getAttribute("HR_LINKS");
  String SelectBox=(String)session.getAttribute("SelectBox");
%>
    
    
    
<!doctype html>
<html class="fixed">
	<head>

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
	
		<link rel="stylesheet" href="assets/stylesheets/GScroll.css" />
		<link rel="shortcut icon" href="/images.png" type="image/x-icon" />

		<!-- Specific Page Vendor CSS -->
		<link rel="stylesheet" href="assets/vendor/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css" />
		

		<!-- Theme CSS -->
		<link rel="stylesheet" href="assets/stylesheets/theme.css" />

		<!-- Skin CSS -->
		<link rel="stylesheet" href="assets/stylesheets/skins/default.css" />
	
		<!-- Theme Custom CSS -->
		<link rel="stylesheet" href="assets/stylesheets/theme-custom.css">
			<!-- <script src="assets/vendor/jquery/jquery.js"></script> -->
			 <script src="assets/javascripts/jquery-1.8.3.js" ></script>

			<script src="jquery.colorbox.js"></script>
			<link rel="stylesheet" href="colorbox.css" />
			
			<script src="jquery-ui-1.12.1.custom/jquery-1.12.4.js"></script>
			<script src="jquery-ui-1.12.1.custom/jquery-ui.js"></script>
			
			<script src="MyAng.js"></script>
			



<script type="text/javascript" language="javascript">   
			function disableBackButton()
				{
				window.history.forward();
				}
				setTimeout("disableBackButton()", 0);

			function myalert() {
			    alert("Please logIn Again");
			} 
		</script>
      
        
		<!-- Head Libs -->
		<script src="assets/vendor/modernizr/modernizr.js"></script>
		<link rel="stylesheet" type="text/css" href="css3clock.css" />


      
 <script>
var app = angular.module('myApp', []);
app.controller('formCtrl', function($scope) {
	var Data="";
	$scope.Data_2="";
	$scope.Data_1="";
	$scope.Data_3="";
	$scope.HR_LINKS="";
	$scope.SelectBox="";
	
	try{
		Data="";
	$scope.Data_1="";
    $scope.HR_LINKS=<%=HR_LINKS%>;
    $scope.SelectBox=<%=SelectBox%>;
    $scope.Data_2="";
    $scope.Data_3="";
    
  
  
    
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
   
    
    
 });
 
</script>  
       
 
 
	
	 
    
  <script>
  function disableBackButton() {
	   window.history.forward();
	}
	setTimeout("disableBackButton()", 0);


	document.onkeydown = function(){
	       if(event.keyCode == 116) {
	            event.returnValue = false;
	            event.keyCode = 0;
	            return false;
	        }

	}
</script>
	<SCRIPT type="text/javascript">
		window.history.forward();
		function noBack() { window.history.forward(); }
	
  </script>
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
  
  </style>
	</head>
	
	<body   ng-app="myApp" ng-controller="formCtrl" >
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
							<div class="clear"></div>
							<a href="#" data-toggle="dropdown" class="profile-info" data-lock-name="" >
								<span class="name"><%=EMP_NAME %>&nbsp;&nbsp;<i class="fa custom-caret"></i></span>
							</a>
			
						<div class="dropdown-menu">
							<ul class="list-unstyled">
								<li class="divider"></li>
								
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
									  <a href="PayslipDownload?Routing=LeaveQuota" target="_parent" >
										<i class="fa fa-users" ></i>
										<span class="font-bold">Leave Quota</span>
									  </a>
									</li> 
									
									
									<li  style='display:{{ HR_LINKS.LINK6 }}' >
									  <a href="PayslipDownload?Routing=Designation" target="_parent" >
										<i class="fa fa-users" ></i>
										<span class="font-bold">Designation Master</span>
									  </a>
									</li> 
									
							     <!-- <li  style='display:{{ HR_LINKS.LINK6 }}' >
									  <a href="DivisionalAttendance?Routing=empattendanceReport" target="_parent" >
										<i class="fa fa-users" ></i>
										<i class="fa fa-calendar"></i>
										<span class="font-bold">Attendance Report</span>
									  </a>
									</li> -->
									
									<li  style='display:{{ HR_LINKS.LINK9 }}' >
									  <a href="buattendance.jsp" target="_parent" >
										<!-- <i class="fa fa-users" ></i> -->
										<i class="fa fa-calendar"></i>
										<span class="font-bold">Attendance Report</span>
									  </a>
									</li> 
							
									 
									
									
									
								</ul>
								
							</nav>
							
					</div>
				
							
		
						</div>
				</aside>
				<!-- end: sidebar -->

				<section role="main" class="content-body resignation-body">
					<header class="page-header">
					<div class="clear col-md-7" style="color:red;"> <marquee> <%=session.getAttribute("HHCL_EVENT_INFO")%>  </marquee></div>
					
				  
				 
					<div class="col-md-3 col-sm-3">
					<span><b>Date:</b> <span>
					
					
					<script>
									var mydate=new Date();
									var year=mydate.getYear();
									if (year < 1000)
									year+=1900;
									var day=mydate.getDay();
									var month=mydate.getMonth();
									var daym=mydate.getDate();
									if (daym<10)
									daym="0"+daym;
									var dayarray=new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday");
									var montharray=new Array("January","February","March","April","May","June","July","August","September","October","November","December");
									document.write("<small><font color='#0088cc' face='Arial'><b>"+dayarray[day]+", "+montharray[month]+" "+daym+", "+year+"</b></font></small>");

								</script>
							
					</span> </span>
					</div>
					
				</header>
					<!-- start: page -->
					
									
					<!-- start: page -->
					<div id="description" class="wrapper" style="margin-top: 0%;">
					
					<div class="container">
						<div class="row " >
							<div class="col-md-10 middle-reg2">
								<div class="panel panel-danger" >
                                     <img src="Training-HR.jpg" width='942px'> </div>
                                     
                                        <div id="common_text" style="display:none" >
									 
									 <div class="col-md-4"></div>
                                       </div>
									 
								</div>
								
							</div>
							
						</div>	
						
									
					</div>
					</div>
					<!-- end: page -->
				

					
				</section>
			
		
	


<script type="text/javascript">
$("#Date").datepicker({
	dateFormat : 'yy-mm-dd',
	changeMonth: true,
    changeYear: true,
    yearRange: '1950:2050'

});


</script>

<script src="Test.js"></script>
 <script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
	<script src="assets/javascripts/theme.custom.js"></script>
	<script src="assets/javascripts/theme.init.js"></script>
	<script src="assets/javascripts/jquery.colorbox.js"></script>
 	</body>
</html>