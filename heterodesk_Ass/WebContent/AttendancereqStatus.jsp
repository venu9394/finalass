<!doctype html>
<html class="fixed">
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
 
<link href="assets/stylesheets/tooltip.css" rel="stylesheet" type="text/css" />
<script src="assets/javascripts/tooltip.js" type="text/javascript"></script>
    
<%@page import="java.util.*" %>


<%
	/* String assessmentObj = (String) request.getAttribute("assessmentObj");
	if(assessmentObj!=null){
	}else{
		response.sendRedirect("Hhc_desk_login.jsp");
		session.invalidate();
	} */

	/* String Emp_BioData = (String) request.getAttribute("DOJ_DOB"); */
	String TDSFLAG = (String) session.getAttribute("TDSFLAG");
	String EMP_NAME = (String) session.getAttribute("EMP_NAME");
	String EMP_ID = (String) session.getAttribute("EMP_ID");

/* 	String ATT_MONTHS = (String) request.getAttribute("ATT_MONTHS"); */
%>

<%
	Random rand = new Random();
	int nRand = rand.nextInt(90000) + 10000;
%>


<script>
//*************** accpetance of numbers*******************// 
$(document).on('keypress', '.numeric', function(evt) {
		 evt = (evt) ? evt : window.event;
		  var charCode = (evt.which) ? evt.which : evt.keyCode;
		  if (charCode > 31 && (charCode < 48 || charCode > 57))
	            return false;
		 /*  if (charCode == 8 || charCode == 37) {
		    return true;
		  } else if (charCode == 46 && $(this).val().indexOf('.') != -1) {
		    return false;
		  } else if (charCode > 31 && charCode != 46 && (charCode < 48 || charCode > 57)) {
		    return false;
		  } */
		  return true;
	}); 
//*********************** END*********************//
</script>


<script src="MyAng.js"></script>



		<!-- Basic -->
		<meta charset="UTF-8">

		<title>Hetero Healthcare LTD</title>
          <link rel="icon" href="LOH.png" />
          
		<meta name="keywords" content="HETERO" />
		<meta name="description" content="Hetero">
		<meta name="author" content="Hetero">

		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		
		<meta http-equiv="X-UA-Compatible" content="IE=8" />

		<!-- Web Fonts  -->
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

		<!-- Vendor CSS -->
		<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
		<link rel="stylesheet" href="assets/vendor/magnific-popup/magnific-popup.css" />
		<!-- <link rel="stylesheet" href="assets/vendor/bootstrap-datepicker/css/datepicker3.css" /> -->
		

		<!-- Specific Page Vendor CSS -->
		<!-- <link rel="stylesheet" href="assets/vendor/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css" /> -->
 		<link rel="stylesheet" href="jqueryui_date.css">
		

		<!-- Theme CSS -->
		<link rel="stylesheet" href="assets/stylesheets/theme.css" />

		<!-- Skin CSS -->
		<link rel="stylesheet" href="assets/stylesheets/skins/default.css" />

		<!-- Theme Custom CSS -->
		<link rel="stylesheet" href="assets/stylesheets/theme-custom.css">

		<!-- Head Libs -->
		<script src="assets/vendor/modernizr/modernizr.js"></script>
		
			<!-- Specific Page Vendor CSS -->
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
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

	<script type="text/javascript">
		window.history.forward();
		function noBack() { window.history.forward(); }
		

</script>
  
  
  
<style type="text/css">
 
.button {
    display: block;
    width: 85px;
    height: 25px;
    background: #4E9CAF;
    padding: 0px;
    text-align: center;
    border-radius: 5px;
    color: white;
    font-weight: bold;
}
a:hover {
    color: Red;
}


.table-fixed tbody {
height: 300px;
overflow-y: auto;
width: 100%;
}
.table-fixed thead,
.table-fixed tbody,
.table-fixed tr,
.table-fixed td,
.table-fixed th {
display: block;
}
.table-fixed tr:after {
content: "";
display: block;
visibility: hidden;
clear: both;
}
.table-fixed tbody td,
.table-fixed thead > tr > th {
float: left;
}
.table > thead > tr > th,
.table > thead > tr > td {
font-size: .9em;
font-weight: 400;
border-bottom: 0;
letter-spacing: 1px;
vertical-align: top;
padding: 8px;
background: #51596a;
text-transform: uppercase;
color: #ffffff;
}
}
</style>
  
<script>
function loadDoc(){
	 document.myform.submit();
}
</script>
  	
<script>
var app = angular.module('myApp', []);
app.controller('formCtrl', function($scope,$http) {
	
	$scope.EmpLeavedetails = function() {

		$http({
    	    url: "AssignMain",
    	    method: "POST",
    	    params: {
    	    	Routing :"getEmpleave",
    	    	Empid: $scope.empid
    	    },
    	}).then(function mySucces(response) {
    		$scope.Data_1=response.data.message;
    	}, function myError(response) {
		/* 	$scope.myWelcome = response.statusText;
			alert($scope.myWelcome); */
		});
	}
 });
</script>

</head>
<body  ng-app="myApp" ng-controller="formCtrl"  onload="disableBackButton(); noBack();">
		<section class="body">

			<!-- start: header -->
			<header class="header">
				<div class="logo-container">
					<a href="#l" class="logo">
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
									<li>
									  <a href="ManagerApprovals?Routing=ManagerAppr" target="_parent" >
										<i class="fa fa-check" ></i>
										<span class="font-bold">Leave Approvals</span>
									  </a>
									</li>
								 <li>
									  <a href="ManagerApprovals?Routing=ManagerAppr_att" target="_parent">
										<i class="fa fa-check" ></i>
										<span class="font-bold">Attendance Approvals</span>
									  </a>
									</li>
								   <li>
									  <a href="ManagerApprovals?Routing=Dept_att" target="_parent">
										<i class="fa fa-users" ></i>
										<span class="font-bold">Dep.Attendance</span>
									  </a>
									</li> 
									<li>
									  <a href="AssessmentForm?Routing=FormAssessment" target="_parent">
										<i class="fa fa-file" aria-hidden="true" ></i>
										<span class="font-bold">Assessment Form</span>
									  </a>
									</li> 
									<li>
									  <a href="AttendancereqStatus.jsp">
										<i class="fa fa-briefcase"></i>
										<span class="font-bold">AttendancereqStatus</span>
									  </a>
									</li>
									<li>
									  <a href="AttendancereqStatus.jsp">
										<i class="fa fa-briefcase"></i>
										<span class="font-bold">AttendancereqStatus</span>
									  </a>
									</li>
								</ul>
							</nav>
						</div>
					</div>
				</aside>
				<!-- end: sidebar -->
				<section role="main" class="content-body">
					<header class="page-header">
						
						<span style="padding-left:850px;"><b>Date:</b>
							<span>
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
							</span>
						</span>
					</header>
					<!-- end: page -->
                <div class="col-md-12">
                    <!-- start: page -->
                    <div id="description" class="wrapper" style="margin-top: 0%;">

                        <div class="container-fluid">
                            <div class="row ">
                                <div class="col-md-12 middle-reg2">
                                    <div class="panel panel-primary">
                                        <div class="panel-heading2 text-center">
                                            <span><strong>EMPLOYEE ASSESSMENT LIST</strong></span></div>
                                        <!-- Panel Body-->
                                    </div>
                                      <div class="panel-body table-responsive">
                                 First name: <input type="text" class="numeric" ng-model="empid" name="fname">                               
                                 <input type="submit" ng-click="EmpLeavedetails()" value="getdata">
                                  <table class="table">
											<thead>
												<th style="background-color:#0088cc; color:#fff;">AssignedDetails</th>
											</thead>
										</table>
										<table>
											<tbody>
												<tr ng-show="Data_1.length!=0  " ng-repeat="x in Data_1">
													<td ng-if="x.status==1002" >{{x.leavetypename}}</td>
												</tr>
												<tr>
													<td
														ng-show="Data_1.length==0 ||Data_1=='null' || Data_1=='undefined' ">
														No data.</td>
												</tr>
											</tbody>
										</table>
                                  <table class="table">
											<thead>
												<th style="background-color:#0088cc; color:#fff;">Details</th>
											</thead>
										</table>
										<table>
											<tbody>
												<tr ng-show="Data_1.length!=0" >
<td>
<span ng-repeat="x in Data_1">
<span ng-if="x.status==1001">
<input type="checkbox"
           name="operations"
           ng-model="x.leavetypeid"
           value="{{x.leavetypeid}}"/>
    {{x.leavetypename}}<br>
</span>
</span>
	 <input type="submit" value="Assign">  
</td>
												</tr>
												<tr>
													<td
														ng-show="Data_1.length==0 ||Data_1=='null' || Data_1=='undefined' ">
														No data.</td>
												</tr>
											</tbody>
										</table>
                                      </div>
                                    <!--Panel-->
                                </div>
                                <!--col-->
                            </div>
                            <!-- row-->
                        </div>
                        <!-- Conatiner-->
                    </div>
                    <!-- End description-->
                </div>
				</section>
			</div>
		</section>
		<!-- Vendor -->
		<script src="assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
		<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
		<script src="assets/vendor/nanoscroller/nanoscroller.js"></script>
		<!-- <script src="assets/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>	 -->
		<!-- Specific Page Vendor -->
		<script src="assets/vendor/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>
		<script src="assets/vendor/jquery-ui-touch-punch/jquery.ui.touch-punch.js"></script>
		<script src="assets/vendor/jquery-appear/jquery.appear.js"></script>
		
		
		<!-- Theme Base, Components and Settings -->
		<script src="assets/javascripts/theme.js"></script>
		
		<!-- Theme Custom -->
		<script src="assets/javascripts/theme.custom.js"></script>
		
		<!-- Theme Initialization Files -->
		<script src="assets/javascripts/theme.init.js"></script>



	<script>
	$(document).ready(function(){
	  $('[data-toggle="tooltip"]').tooltip();   
	});
	</script>



</body>
</html>