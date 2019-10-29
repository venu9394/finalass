<!doctype html>
<html class="fixed">
	<head>
	
	<style>
	#toolbar{
	display:none;
	}
	</style>
	
 <% 
  String Emp_Payslip=(String)session.getAttribute("PaySlip");
  
  String Emp_ForeCast=(String)session.getAttribute("ForeCast");
  
  
  String Emp_id=(String)session.getAttribute("EMP_ID");
  String TDSFLAG=(String)session.getAttribute("TDSFLAG");
  String EMP_NAME=(String)session.getAttribute("EMP_NAME");
  System.out.println("BioData at JSP::" +Emp_Payslip);
  System.out.println("Emp_BioData at JSP::" +Emp_ForeCast);
  
  String  MGRFLAG=(String)session.getAttribute("Manage_Rights");
  String MGRFLAG_S="none";
  if(MGRFLAG!=null && Integer.parseInt(MGRFLAG)>0){
	  
	  MGRFLAG_S=" ";
  }
  
  String  HR_HRMS=(String)session.getAttribute("HR_HRMS");
  if(HR_HRMS!=null && HR_HRMS.equalsIgnoreCase("YES")){
  	HR_HRMS=" ";
  }else{
  	HR_HRMS="none";
  }
  
  
%>
		<!-- Basic -->
		<meta charset="UTF-8">

		<title>Hetero Healthcare LTD</title>
          <link rel="icon" href="LOH.png" />
          
		<meta name="keywords" content="HETERO" />
		<meta name="description" content="Hetero">
		<meta name="author" content="Hetero">

		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

		<!-- Web Fonts  -->
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

		<!-- Vendor CSS -->
		<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
		<link rel="stylesheet" href="assets/vendor/magnific-popup/magnific-popup.css" />
		<link rel="stylesheet" href="assets/vendor/bootstrap-datepicker/css/datepicker3.css" />
		<link rel="stylesheet" href="assets/stylesheets/GScroll.css" />

		<!-- Specific Page Vendor CSS -->
		<link rel="stylesheet" href="assets/vendor/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css" />
		

		<!-- Theme CSS -->
		<link rel="stylesheet" href="assets/stylesheets/theme.css" />

		<!-- Skin CSS -->
		<link rel="stylesheet" href="assets/stylesheets/skins/default.css" />

		<!-- Theme Custom CSS -->
		<link rel="stylesheet" href="assets/stylesheets/theme-custom.css">

		<!-- Head Libs -->
		<script src="assets/vendor/modernizr/modernizr.js"></script>

      <script src="MyAng.js"></script>
     <script src="assets/javascripts/jquery-1.8.3.js"></script>
	<script src="assets/javascripts/GScroll.js"></script>
	<script type="text/javascript">
            $(document).ready(function(){
               // $('#content').GScroll();
              //  $('#content3').GScroll({height: 100});
               // $('<a href=""></a>')
            });
        </script>
        <!-- <script type="text/javascript">
            $(document).ready(function(){
                $('#content-1').GScroll();
                $('#content3').GScroll({height: 100});
                $('<a href=""></a>')
            });
        </script> -->
     <script>
     
    
var app = angular.module('myApp', []);
 app.controller('formCtrl', function($scope) {
	var Data="";
	$scope.Data_2="";
	$scope.Data_1="";
	try{
    $scope.Data_1=<%=Emp_Payslip%>;
    $scope.Data_2=<%=Emp_ForeCast%>;
	}catch(err){
		
	}
    
    
  /*   $scope.empid = Data.EMPLOYEESEQUENCENO;
    $scope.empname= Data.CALLNAME;
    $scope.emp_doj= Data.DATEOFJOIN;
    $scope.emp_Dep= Data.DEPARTMENT;
    $scope.emp_Des= Data.DESIGNATION;
    $scope.emp_job_type= Data.TYPE; */
    
 });
 
function  PaySlip(val){
	  
if(val!="0"){
  var Data=val.split("-");
try{
   
	
   document.getElementById("content").src="http://iconnect.heterohcl.com/EmployeeAuth/download/files_mydesk/<%=Emp_id%>/"+Data[0]+"-PAYSLIP-<%=Emp_id%>.pdf";	 
  }catch(err){
    alert(err.message);
  }
}else{
	document.getElementById("content").src="assets/images/pdf.png";
}
   
  }

function  ForeCast(val){
	
	if(val!="0"){
	 var Data=val.split("-");
try{
	document.getElementById("content").src="http://iconnect.heterohcl.com/EmployeeAuth/download/files_mydesk/<%=Emp_id%>/"+Data[0]+"-ITFORECAST-<%=Emp_id%>.pdf ";	 
  }catch(err){
    alert(err.message);
  }
	}else{
		document.getElementById("content").src="assets/images/pdf.png";
	}
  
  } //
document.onmousedown=disableclick;
function disableclick(event)
{
if(event.button==2)
  {

 }
}

</script>

<script>

/*  var jq = $.noConflict();
  alert(jq);
jq(document).ready(function () {
  "use strict";
  jq('#mybody').perfectScrollbar();
  //jq('#mypdf').perfectScrollbar();
}); */
</script>

 <style>
 
  #content {
    overflow:hidden;
} 


 </style>
  <style>
         .contentHolder { position:relative; margin:0px auto; padding:0px; width: 600px; height: 400px; overflow: hidden; }
        .contentHolder .content { background-image: url('./azusa.jpg'); width: 1280px; height: 720px; }
        .spacer { text-align:center } */
      </style>
      
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
  
	</head>
	<body onload="noBack();" onpageshow="if (event.persisted) noBack();" id="mybody"  ng-app="myApp" ng-controller="formCtrl" >
		<section class="body">

			<!-- start: header -->
			<header class="header">
				<div class="logo-container">
					<a href="#" class="logo">
						<img src="assets/images/logo.png" height="35" alt="Hetero" />
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
							<div class="profile-info" data-lock-name="sairam" data-lock-email="sairam.d@heterohealthcare.com">
								<span class="name"><%=EMP_NAME %></span>
								
							</div>
			
							<i class="fa custom-caret"></i>
						</a>
			
						<div class="dropdown-menu">
							<ul class="list-unstyled">
								<li class="divider"></li>
								<!-- <li>
									<a role="menuitem" tabindex="-1" href="hhcl_changepassword.html"><i class="fa fa-cogs"></i>Change Password</a>
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
										<span class="font-bold">Dashboard</span>
									  </a>
									</li>
									<li>
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
									</li>
									
									 <!-- Manager Approvals Start -->
							
								  <li style="display:<%=MGRFLAG_S%>;">
									  <a  href="ManagerApprovals?Routing=ManagerAppr" target="_parent">
										<i class="fa fa-check" ></i>
										<span class="font-bold">Manager Approvals</span>
									  </a>
									</li> 
									
									<li>
									  <a href="NewJoinees?Routing=DEPINFO">
										<i class="fa fa-users"  ></i>
										<span class="font-bold">Department Information</span>
									  </a>
									</li>
									
									
									<li>
									  <a href="PayslipDownload?Routing=UPDATE" style="display:<%=HR_HRMS%>;"  >
										<i class="fa fa-user"></i>
										<span class="font-bold">HR/HRMS</span>
									  </a>
									 </li>  
									 
									 
									<li style="display:<%=TDSFLAG%>;">
									  <a href="http://mydesk.heterohcl.com/IT/" target="_blank">
										<i class="fa fa-money " ></i>
										<span class="font-bold">TDS Declaration</span>
									  </a>
									</li>
									
									
									<li>
									  <a href="http://www.heterohealthcare.com/" target="_blank">
										<i class="fa fa-building-o "></i>
										<span class="font-bold">About Our Organization</span>
									  </a>
									</li>
									
									<li>
										<div id="datepicker" class="calendar"></div>
									</li>
								</ul>
							</nav>
							
				
							
						</div>
				
					</div>
				
				</aside>
				<!-- end: sidebar -->

				<section role="main" class="content-body">
					<header class="page-header">
					<div class="clear col-md-9" style="color:red;"> <marquee> <%=session.getAttribute("HHCL_EVENT_INFO")%>  </marquee></div>
					<div class="col-md-3 col-sm-3">
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
					</header>
					<!-- start: page -->
					<div class="container">
						<div class="row" style="padding-left:80px;">
							<div class="col-xs-12 col-sm-6 col-md-9">
									<div class="panel panel-primary">
										<div style="height:10px;"></div>
										<div class="row">
											<div class="col-md-6">
												
												<div class="form-group">
													<label class="col-sm-4" style="color:#0088cc;"><b><i class="fa fa-inr fa-lg"></i>&nbsp;&nbsp;PaySlip</b></label>
													<div class="col-sm-6">
														<select class="form-control" id="payslip_month" name="payslipmonth" onchange="PaySlip(this.value)">
															<option  ng-repeat="(key, value) in Data_1" value="{{key}}" >{{ value }} </option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="col-sm-4"><b>ITS</b></label>
													<div class="col-sm-6">
														<select class="form-control" id="ITS" name="hhcl_ITS" onchange="ForeCast(this.value)" >
														 <option ng-repeat="(key, value) in Data_2" value="{{key}}">{{value}}</option>
														 </select>
													</div>
												</div>
											</div>
										</div>
										<div style="height:10px;"></div>
									</div>
							</div>	

						</div>
						<div class="row" style="padding-left:80px;">
						
							<div class="col-xs-12 col-sm-6 col-md-9">
							
							
							<div id="mypdf_div22" class="panel panel-primary" style="overflow:hidden; height:800px;" >
							
							
							<iframe id="content"  src="assets/images/pdf.png" height="800" width="800" >  </iframe>
							
							
							</div>

<!-- 
<div class="panel panel-primary">
<div id="content-1"  style="background-color:transparent;">
 <!--   <iframe id="mypdf"  src="" height="400" width="800" >  </iframe> -->
<!--  	  <iframe id="content"   src="assets/images/pdf.png" align='center' class="text-center"   height="100%" width="100%"> </iframe> -->
<!-- <img src="assets/images/pdf.png" style="padding-left:80px;" class="img-responsive">  -->
<!--<embed id="content" src="assets/images/pdf.png"  width="100%" height="800px"></embed> -->
<!--  </div>
</div>-->
								
							</div>
						</div>
					</div>
					<!-- end: page -->
					

				</section>
			</div>

			
		</section>
		
		
		<!-- Vendor -->
		<script src="assets/vendor/jquery/jquery.js"></script>
		<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
		
		
		
		<!-- Specific Page Vendor -->
		<script src="assets/vendor/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>
		
		
		<!-- Theme Base, Components and Settings -->
		<script src="assets/javascripts/theme.js"></script>
		
		<!-- Theme Custom -->
		<script src="assets/javascripts/theme.custom.js"></script>
		
		<!-- Theme Initialization Files -->
		<script src="assets/javascripts/theme.init.js"></script>


		<!-- Examples -->
		<script src="assets/javascripts/dashboard/examples.dashboard.js"></script>
		
		
	

	</body>
</html>