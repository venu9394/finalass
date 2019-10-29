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
<meta charset="UTF-8">
<meta name="keywords" content="HETERO" />
<meta name="description" content="Hetero">
<meta name="author" content="Hetero">
<title>Hetero Healthcare LTD</title>
<link rel="icon" href="LOH.png" />
<!-- Mobile Metas -->
<meta name="viewport" Content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

<!-- Web Fonts  -->
<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

<!-- Vendor CSS -->
<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
<link rel="stylesheet" href="assets/vendor/magnific-popup/magnific-popup.css" />
<link rel="stylesheet" href="assets/stylesheets/GScroll.css" />
<link rel="shortcut icon" href="/images.png" type="image/x-icon" />
<link rel="stylesheet" href="assets/vendor/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css" />

<!-- Theme CSS -->
<link rel="stylesheet" href="assets/stylesheets/theme.css" />

<!-- Skin CSS -->
<link rel="stylesheet" href="assets/stylesheets/skins/default.css" />

<!-- Theme Custom CSS -->
<link rel="stylesheet" href="assets/stylesheets/theme-custom.css">
<link rel="stylesheet" href="colorbox.css" />
<script src="jquery.colorbox.js"></script>
<link rel="stylesheet" href="css/icon-font.min.css" type='text/css' />
<link rel="stylesheet" href="css/jqueryui.css" type='text/css' />
<link rel="stylesheet" href="jquery-ui-1.12.1.custom/jquery-ui.css">
<script src="jquery-ui-1.12.1.custom/jquery-1.12.4.js"></script>
<script src="jquery-ui-1.12.1.custom/jquery-ui.js"></script>


<style>

table {
    border-collapse: collapse;
    width: 100%;
    overflow-x: none;
    display: block;
	
}
thead {
   background-color:#0088cc;
	color:#ffffff;
	text-align:center;
	
}
thead, tbody {
    display: block;
}
tbody {
    overflow-y: scroll;
    overflow-x: hidden;
    height: 280px;
	margin-top:5px;
}
td {
    min-width: 180px;
	height:30px;
	padding:5px;
	border-bottom:1px solid #ddd;
	
  
}
th {
    min-width: 180px;
	height:30px;
	padding:5px;
	text-align:left;
  
}
.select {
	border: 1px solid #E5E7E9;
	border-radius: 2px;
	height: 25px;
	padding: 2px;
	outline: none;
	color:#0088cc;
	margin-bottom:10px;
	overflow:hidden;
	
}
 .a option {
        height: 15px;
    }

</style>
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
app.controller('formCtrl', function($scope,$http) {
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
    //Start Function empdetails
    
    $scope.DesiList=function(){
    	//alert("Loading...");
    	$scope.loading = true;
    	$scope.button="Create";
    	
    	$http({
    	    url: "LeaveInfo",
    	    method: "POST",
    	    
    	    params: {
    	    	Routing :"DesignationLoad"
    	    },
    	}).then(function mySucces(response) {
    		
    		 $scope.msg="";
    		 $scope.loading = false;
    		 var data=response.data;
    		 $scope.DivisionsList="";
    		 
    		/*  $scope.student = [  
    	                  {"name":"Physics",marks:850},  
    	                  {name:'Chemistry',marks:80},  
    	                  {name:'Math',marks:90},  
    	                  {name:'English',marks:80},  
    	                  {name:'Hindi',marks:70}  
    	               ]; */
    		 
    		 $scope.student = data;
    		 
    		/*  $scope.desilist=data;
    		alert("Data-->"+data); */

		}, function myError(response) {
			$scope.myWelcome = response.statusText;
			alert($scope.myWelcome);
		});
	};
	
	
	// Function for Populate into edit 
	
	$scope.clicked=function(event){
	
    	// alert(event.target.id);

		var ischeck=event.target.checked;
		var Desicode=event.target.id;
		/* var k=document.getElementById(Desicode);
		alert(k.checked); */
		
		//alert(Desicode);
		
		var bu=$scope.DivisionsList;
		//alert(bu);
	 		
		if(!bu==""){ 
			$scope.loading = true;
			$http({
	    	    url: "LeaveInfo",
	    	    method: "POST",
	    	    params: {
	    	    	Routing :"Assign",
	    	    	CompanyID : bu,
	    	    	DesiID : Desicode,
	    	    	Check : ischeck	    	    	
	    	    },
	    	}).then(function mySucces(response) {
	    		 $scope.msg="";
	    		 $scope.loading = false;
	    		 var data=response.data;
	    		/*  $scope.student = data; */
	    		 if(data=="1"){
	    			 $scope.msg="Data Saved Successfully";
	    		}
	    		 
			}, function myError(response) {
				$scope.myWelcome = response.statusText;
				alert($scope.myWelcome);
			});
		}
		};
	
    

		//End Of Function empdetails

		
		
	$scope.GetBuDesi = function() {

	  		 //$scope.student="";
			var Company = $scope.DivisionsList;
			if(!Company==''){
				 $scope.loading = true;
				$http({
		    	    url: "LeaveInfo",
		    	    method: "POST",
		    	    params: {
		    	    	Routing :"GetBUDesiList",
		    	    	CompanyID : Company
		    	    },
		    	}).then(function mySucces(response) {
		    		 $scope.msg="";
		    		 $scope.loading = false;
		    		 var data=response.data;
		    		 $scope.student = data;
		    		 
				}, function myError(response) {
					$scope.myWelcome = response.statusText;
					alert($scope.myWelcome);
				});
				
			}else{
				$scope.DesiList();
			}

		};

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
	display: table;
	height: 100%;
	width: 100%;
}

.vertical-align-center { /* To center vertically */
	display: table-cell;
	vertical-align: middle;
}

.modal-content {
	/* Bootstrap sets the size of the modal in the modal-dialog class, we need to inherit it */
	width: inherit;
	height: inherit;
	/* To center horizontally */
	margin: 0 auto;
}
.form-control1 {
    display: block;
    width: 60%;
    height: 25px;
    padding: 2px 6px;
    font-size: 12px;
    line-height: 1.42857143;
    color: #555555;
    background-color: #ffffff;
    background-image: none;
    border: 1px solid #cccccc;
    border-radius: 4px;
    -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
    box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
    -webkit-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
    -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
    transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}
td{
border-top: none !important;
}
.clear{
margin-bottom:5px;
}
</style>
</head>
<!-- data-ng-init="empdetails();" -->
<body ng-app="myApp" ng-controller="formCtrl" data-ng-init="DesiList();">
	<section class="body">

		<header class="header">
			<div class="logo-container">
				<a href="#" class="logo"> <img src="assets/images/logo.png"
					alt="" />
				</a>
				<div class="visible-xs toggle-sidebar-left"
					data-toggle-class="sidebar-left-opened" data-target="html"
					data-fire-event="sidebar-left-opened">
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

						</div> <i class="fa custom-caret"></i>
					</a>

					<div class="dropdown-menu">
						<ul class="list-unstyled">
							<li class="divider"></li>
							<li><a role="menuitem" tabindex="-1" href="logout"><i
									class="fa fa-power-off"></i> Logout</a></li>
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

								<li class="active"><a href="User_Auth?Routing=DashBoard">
										<i class="fa fa-tachometer"></i> <span class="font-bold">Home</span>
								</a></li>



								<li style='display:{{ HR_LINKS.LINK1 }}'><a
									href="PayslipDownload?Routing=UPDATE_emp"> <i
										class="fa fa-user"></i> <span class="font-bold">Emp
											Profile Update</span>
								</a></li>

								<li style='display:{{ HR_LINKS.LINK2 }}'><a
									href="PayslipDownload?Routing=APPROVAL"> <i
										class="fa fa-check"></i> <span class="font-bold">Profile
											Update approval</span>
								</a></li>


								<li style='display:{{ HR_LINKS.LINK3 }}'><a
									href="ManagerApprovals?Routing=ManagerAppr_Resi&APP_MOD=HR"
									target="_parent"> <i class="fa fa-check"></i> <span
										class="font-bold">Resignation Approvals</span>
								</a></li>

								<li style='display:{{ HR_LINKS.LINK4 }}'><a
									href="ManagerApprovals?Routing=Dept_att_HR" target="_parent">
										<i class="fa fa-users"></i> <span class="font-bold">BusinessUnit.Attendance</span>
								</a></li>

								<li style='display:{{ HR_LINKS.LINK5 }}'><a
									href="PayslipDownload?Routing=LeaveQuota" target="_parent">
										<i class="fa fa-users"></i> <span class="font-bold">Leave
											Quota</span>
								</a></li>
								
								<li style='display:{{ HR_LINKS.LINK6 }}'><a
									href="PayslipDownload?Routing=Designation" target="_parent">
										<i class="fa fa-users"></i> <span class="font-bold">Designation Master</span>
								</a></li>
								
								<!-- Manager Approvals Start -->


							</ul>

						</nav>

					</div>


				</div>
			</aside>
			<!-- end: sidebar -->

			<section role="main" class="content-body resignation-body">
				<header class="page-header">
					<div class="clear col-md-7" style="color: red;">
						<marquee>
							<%=session.getAttribute("HHCL_EVENT_INFO")%>
						</marquee>
					</div>

					<div class="col-md-2 tool-tip-header">
						<div class="weather">
							<a href="#" class="tooltip"> <img src="images/ww.png"
								class="icon-img" /> <span> <img class="callout"
									src="images/callout_black.gif" /> <img
									src="images/css3-tooltip-image1.png" style="float: right;" />
									<strong>CSS only Tooltip</strong><br /> Pure CSS popup
									tooltips with clean semantic XHTML.
							</span>
							</a>
						</div>
						<div class="quote">
							<a href="#" class="tooltip"> <img src="images/hand.png"
								class="icon-img" /> <span> <img class="callout"
									src="images/callout_black.gif" /> <img
									src="images/css3-tooltip-image1.png" style="float: right;" />
									<strong>CSS only Tooltip</strong><br /> Pure CSS popup
									tooltips with clean semantic XHTML.
							</span>
							</a>
						</div>

					</div>

					<div class="col-md-3 col-sm-3">
						<span><b>Date:</b> <span> <script>
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
				<div class="col-md-12">
				<div class="panel panel-primary">
				<div class="panel-heading1">
					Designation Creation  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:red; font-weight:bold;">{{msg}}<img ng-show="loading" src="assets/images/spinner-blue1.gif" height="24" width="24"> </span>
				</div>
				<div class="panel-body">
				
				<div class="container-fluid">
				<!-- <table class="table">
					<thead class="thead-default">
						
					</thead>
					<tbody>
					
					<tr>
					 <td>Name</td><td><input type="text" class="form-control" ng-model="Desiname" name="Desiname"  ng-blur="empdetails()"></td>
					 <td>Short Code</td><td><input type="text" class="form-control" ng-model="Desicode" name="Desicode"  ></td>
					 <td>Status</td>
					 <td>
							<select class="form-control" ng-model="Status" >
							<option value="">Select</option>
							<option value="1001">Active</option>
							<option value="1002">InActive</option>
							</select>
					</td>
					<td></td>
					
					<td><button class="btn btn-primary btn-md " ng-model="Create" value="C" ng-click="CreateUpdate()">{{button}}</button></td>
					 
					 </tr>
						<tr>
						ng-selected="true"
							<td colspan="5" style="background-color:#0088cc; color:#fff;">Designation List</td>
							<td style="background-color:#0088cc; color:#fff;">Division</td>
							<td style="background-color:#0088cc; color:#fff;">
							<select class="form-control" ng-model="DivisionsList" ng-Change="GetBuDesi()">
							<option value="">Select</option>
							<option value="3">HHC-Genx</option>
							<option value="5">HHC-Main</option>
							<option value="6">Oncology</option>
							<option value="7">Kris</option>
							<option value="8">Gastro</option>
							<option value="10">R&D</option>
							<option value="11">HHC-Corporate</option>
							<option value="12">ARV</option>
							<option value="13">Frenza</option>
							<option value="14">Azista</option>
							<option value="15">ASSAM</option>
							<option value="16">ASHYD</option>
							</select></td>
						</tr>
					 
					 
					 
					 
						</tbody>
				</table> -->
						<div class="row">
							<div class="form=group">
								<label class="col-md-1 control-label">Name</label>
								<div class="col-md-3">
									<input type="text" class="form-control" ng-model="Desiname" name="Desiname"  ng-blur="empdetails()">
								</div>
								<label class="col-md-2 control-label">Short Code</label>
								<div class="col-md-3">
									<input type="text" class="form-control" ng-model="Desicode" name="Desicode"  >
								</div>
								<div class="col-md-3">
									<button class="btn btn-primary btn-md " ng-model="Create" value="C" ng-click="CreateUpdate()">{{button}}</button>
								</div>
							</div>
						</div>
						<div class="clear"></div>
						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-primary">
									<div class="panel-heading2"">
										<div class="col-md-9">
								<span style="color:#fff;">Designation List</span>
							</div>
							<div class="col-md-1">
							<label><b>Division</b></label>
							</div>
							<div class="col-md-2">
								
								<select class="form-control select" ng-model="DivisionsList"  ng-Change="GetBuDesi()">
								
									<option value="">Select</option>
									<option value="3">HHC-Genx</option>
									<option value="5">HHC-Main</option>
									<option value="6">Oncology</option>
									<option value="7">Kris</option>
									<option value="8">Gastro</option>
									<option value="10">R&D</option>
									<option value="11">HHC-Corporate</option>
									<option value="12">ARV</option>
									<option value="13">Frenza</option>
									<option value="14">Azista</option>
									<option value="15">ASSAM</option>
									<option value="16">ASHYD</option>
								</select>
							</div>
									</div>
									<div class="panel-body">
									<table>
								<thead>
								<tr>
								<th>Sno</th>
								<th>Action</th>
								<th>Name</th>
								<th>Code</th>
								<th>Status</th>
								<!-- <td>Action</td> -->
								
								
								</tr>
								
								
								</thead>
								
								
									<tbody  >
									
										<tr ng-repeat = "subject in student">
											<td>{{ subject.SNO }}</td>
											<!-- <td>{{ subject.DesiID }}</td>  ng-model="checkbox" {{ subject.ISCHECK}}-->
											<td><input type="checkbox"   ng-model="mycheck"  name="mycheck" id="{{ subject.DesiID}}" ng-checked="{{ subject.ISCHECK}}=='1'" ng-click="clicked($event)"></td> 
											<td>{{ subject.Name }}</td>  
                        					<td>{{ subject.Code }}</td>
                        					<td>{{ subject.Status }}</td>  
                        					<!-- <td><input type="button" ng-model="edit" name="{{ subject.Name}}" value="Edit" id="{{ subject.Code}}" ng-click="clicked($event)"></td> -->
										</tr>
									
									</tbody>
									
								</table>
									
									</div>
								
							
							   </div>
							</div>
						
								
						</div>
				</div>
				</div>
				</div>

				<!-- start: page -->
				<div id="description" class="wrapper" style="margin-top: 0%;">

					<div class="container">
						<div class="row ">
							<div class="col-md-10 middle-reg2">
							</div>

						</div>
					</div>
				</div>
		</div>
		<!-- end: page -->


		<!-- end: page -->
		<!--Change Password Modal-->


	</section>
	<!-- Vendor -->
	<!-- 	<script src="assets/vendor/jquery/jquery.js"></script> -->
	<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
	<!-- Specific Page Vendor -->
	<!-- 	<script src="assets/vendor/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script> -->
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

	<!-- Change Password -->

	<script type="text/javascript">
$("#Date").datepicker({
	dateFormat : 'yy-mm-dd',
	changeMonth: true,
    changeYear: true,
    yearRange: '1950:2050'

});

</script>


</body>
</html>