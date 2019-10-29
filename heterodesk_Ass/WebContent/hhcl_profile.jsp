<!doctype html>
<html class="fixed">
	<head>
<% 
  String BioData=(String)request.getAttribute("BioData");
  
  String Emp_BioData=(String)request.getAttribute("DOJ_DOB");

  String EmpDOB=(String)request.getAttribute("EM_DOB");
  
  String HOLIDAYS_PG=(String)request.getAttribute("HOLIDAYS");

  String TDSFLAG=(String)session.getAttribute("TDSFLAG");
  
  String EMP_NAME=(String)session.getAttribute("EMP_NAME");
  
  String Notice_board=(String)session.getAttribute("Notice");
  
  String Emp_exp=(String)request.getAttribute("Emp_exp");
  
  System.out.println("Emp_exp at JSP::" +Emp_exp.toString());
  
   System.out.println("BioData at JSP::" +BioData);
 
   System.out.println("Emp_BioData at JSP::" +Emp_BioData);
   
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

<script>
var app = angular.module('myApp', []);
app.controller('formCtrl', function($scope) {
	var Data="";
	$scope.Data_2="";
	$scope.Data_1="";
	$scope.Data_3="";
	$scope.Data_4="";
	
	try{
		Data=<%=BioData%>;
    $scope.Data_1=<%=Emp_BioData%>;
    $scope.Data_2=<%=EmpDOB%>;
    $scope.Data_3=<%=HOLIDAYS_PG%>;
    $scope.Data_4=<%=Emp_exp%>;
    
  
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
    
   /*  $scope.empid = Data.EMPLOYEESEQUENCENO;
    $scope.empname= Data.CALLNAME;
    $scope.emp_doj= Data.DATEOFJOIN;
    $scope.emp_Dep= Data.DEPARTMENT;
    $scope.emp_Des= Data.DESIGNATION;
    $scope.emp_job_type= Data.TYPE; */
   
    
   // $scope.HOLIDAYS = Data_3.EVENTDATE;
    
   // alert("$scope.HOLIDAYS::"+$scope.HOLIDAYS);
    
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

	<SCRIPT type="text/javascript">
		window.history.forward();
		function noBack() { window.history.forward(); }
	
  </script>
 
	</head>
	<body  onload="noBack();" onpageshow="if (event.persisted) noBack();" ng-app="myApp" ng-controller="formCtrl" >
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
							<div class="profile-info" data-lock-name="" data-lock-email="">
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
									
									 <li style="display:<%=MGRFLAG_S%>;">
									  <a  href="ManagerApprovals?Routing=ManagerAppr" target="_parent">
										<i class="fa fa-check" ></i>
										<span class="font-bold">Manager Approvals</span>
									  </a>
									</li> 
									
									
									<li>
									  <a href="NewJoinees?Routing=DEPINFO">
										<i class="fa fa-users" ></i>
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
						<div class="row" style="padding-left:20px;">
							<div class="col-xs-12 col-sm-6 col-md-10">
								<div class="panel panel-danger" >
									<div class="panel-heading1">
										<span><b>My Info</b></span>
								
									</div>
									<div class="panel-body">
										<table class="table table-striped">
										
												<thead class="thead-default">
													<tr>
														<th class="text-center" colspan="2" style="padding-right:240px; background-color:#0088cc; color:#fff;">Primary Details</th>
														<th class="text-center" colspan="2" style="background-color:#0088cc; color:#fff;">Account Details</th>
													</tr>
												
												</thead>
												<tr>
													<td>Employee ID</td>
													<td>{{Data_1[0].ID}}</td>
													<td>PAN Number</td>
													<td>{{Data_1[0].pan}}</td>
												</tr>
												<tr>
													<td>Reporting Manager</td>
													<td>{{Data_1[0].callname}}</td>
													<td>PF Number</td>
													<td>{{Data_1[0].pfno}}</td>
												</tr>
												<tr>
													<td>Department</td>
													<td>{{Data_1[0].dept}}</td>
													<td>ESI Number</td>
													<td>{{Data_1[0].esino}}</td>
													
												
												</tr>
												<tr>
													<td>Designation</td>
													<td>{{Data_1[0].desi}}</td>
													<td>PF UAN</td>
													<td>{{Data_1[0].pfuan}}</td>
													
													
												</tr>
												<tr><!-- -->
													<td>Date Of Join</td>
													<td>{{Data_1[0].doj}}</td>
													<td>Sal Acct Bank Name</td>
													<td>{{Data_1[0].bankname}}</td>
													
												</tr>
												<tr>
													<td></td>
													<td></td>
													<td>Sal Acct Number</td>
													<td>{{Data_1[0].accountno}}</td>
													
													
													
													
												</tr>
										</table>
					
										<table class="table table-striped">
											
												<tr>
													<th class="text-center" colspan="2" style="padding-right:150px; background-color:#0088cc; color:#fff;">Professional</th>
													<th class="text-center" colspan="2" style="background-color:#0088cc; color:#fff;">Personal</th>
												</tr>
											
											<tr>
												<td>Email</td>
												<td>{{Data_1[0].email}}</td>
												<td>Email</td>
												<td>{{Data_1[0].pemail}}</td>
											</tr>
											<tr>
												<td>Phone Number</td>
												<td>{{Data_1[0].mobile}}</td>
												<td>Phone Number</td>
												<td>{{Data_1[0].pmobile}}</td>
											</tr>
										</table>
										
										<table class="table table-striped">
											<tr>
												<th colspan="2" class="text-left" style="background-color:#0088cc; color:#fff;">ICE Details</th>
												<th colspan="2" style="background-color:#0088cc; color:#fff;"></th>
											
											</tr>
											<tr>
												<td>Contact Person</td>
												<td>{{Data_1[0].iecname}}</td>
												<td></td>
												<td></td>
											</tr>
											<tr>
												<td>Address</td>
												<td>{{Data_1[0].iecaddress}}</td>
												<td></td>
												<td></td>
											</tr>
											<tr>
												<td>Location</td>
												<td>{{Data_1[0].icelocation}}</td>
												<td></td>
												<td></td>
											</tr>
											<tr>
												<td>Phone Number</td>
												<td>{{Data_1[0].iecmobile}}</td>
												<td></td>
												<td></td>
											</tr>
										</table>	
										<table class="table">
											<tr>
												<th class="text-center" style="padding-right:40px; background-color:#0088cc; color:#fff;" >Communication Address</th>
												<th style="padding-left:110px;background-color:#0088cc; color:#fff;" class="text-center">Permanent Address</th>
											</tr>
											<tr>
												<td>{{Data_1[0].comaddress}}</td>
												<td>{{Data_1[0].peraddress}} </td>
											</tr>
										</table>
									
										<table class="table">
											<thead>
												<th style="background-color:#0088cc; color:#fff;">Experience Details</th>
											</thead>
										</table>										
										<table class="table">
											<thead>
												<tr>
													<th>Qualification</th>
													<th>Branch</th>
													<th>Exp(months)</th>
													<th>Is Exp Relevant</th>
													<th>Designation</th>
													<th>Organization</th>
												</tr>
												</thead>
												<tbody>
												<tr  ng-repeat="x in Data_4" >
													<td>{{x.qualification}}</td>
													<td>{{x.branch_id}}</td>
													<td>{{x.experience}}</td>
													<td>{{x.revlavent}}</td>
													<td>{{x.exp_desg}}</td>
													<td>{{x.employername}}</td>
												</tr>
												</tbody>
											
										</table>
									</div>
									
								</div>
							</div>
						</div>	
						
					</div>
					<!-- end: page -->
					
				</section>
			</div>

			
		</section>
		
		<!-- Vendor -->
		<script src="assets/vendor/jquery/jquery.js"></script>
		<script src="assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
		<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
		<script src="assets/vendor/nanoscroller/nanoscroller.js"></script>
		
		
		
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


		<!-- Examples -->
		<script src="assets/javascripts/dashboard/examples.dashboard.js"></script>
		
		
	</body>
</html>