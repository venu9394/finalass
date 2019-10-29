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
    
    $scope.LeaveType='Select';
   $scope.Year='2018';
    
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
    $scope.empdetails=function(){
    	
    	
		/* alert("Mahesh"); */
		if($('#empcode').val()=="")
			{
			$scope.msg="Please Enter EmployeeCode..";
			  return false;
			}
		
		 $scope.loading = true;
	    	$http({
	    	    url: "LeaveInfo",
	    	    method: "POST",
	    	    
	    	    params: {
	    	    	EmpID : $scope.empcode,
	    	    	Routing :"EmpInfo"
	    	    },
	    	}).then(function mySucces(response) {
	    		
	    		 $scope.msg="";
	    		 $scope.loading = false;
	    		 var data=response.data;
	    		 if(data.EmpName!=null){
	    			 
	                $scope.empname = data.EmpName;
					$scope.empDesi = data.EmpDESI;
					$scope.empDept = data.EmpDEPT;
					$scope.empDoj = data.EmpDOJ;
					$scope.empEmployment = data.EmpTYPE;
					$scope.CL = 0;
					$scope.SL = 0;
					$scope.EL = 0;
				//	$scope.Year = "Select";
					
				} else {

					$scope.msg = "Please enter Valid Employeeid";
					
					
					    $scope.empname = "";
						$scope.empDesi = "";
						$scope.empDept = "";
						$scope.empDoj ="";
					//	$scope.Year = "Select";
				}

			}, function myError(response) {
				$scope.myWelcome = response.statusText;
				alert($scope.myWelcome);
			});
		};
		//End Of Function empdetails

		// Start Of Year Load
		$scope.LoadQuota = function() {
			
			
			
			 $('#LeaveType').val('Select');
			 
			 $('#LeaveQuotaDisplay').html('');

			/* $scope.CL = 0;
			$scope.SL = 0;
			$scope.EL = 0;

			if ($scope.empcode != null && $scope.empcode != ""
					&& $scope.Year != "") {
				$scope.loading = true;
				$http({
					url : "LeaveInfo",
					method : "POST",

					params : {
						EmpID : $scope.empcode,
						Year : $scope.Year,
						Routing : "LoadQuota"
					},
				}).then(function mySucces(response) {
					$scope.msg = "";
					$scope.loading = false;
					var data = response.data;
					$scope.CL = data.CL;
					$scope.SL = data.SL;
					$scope.EL = data.EL;
				}, function myError(response) {
					$scope.myWelcome = response.statusText;
					alert($scope.myWelcome);
				});
			} else {
				$scope.msg = "InValid Request";
			} */
		};
		// End Of Year Load

		// Start Of Submission
		$scope.ConfirmQuota = function() {
			
			
			
			if($('#empcode').val()=="")
				{
				 $scope.msg="Please Enter EmployeeCode..";
				 
				 return false;
				}
			
			  else if($('#empname').val()=="")
			{
				  $scope.msg="Invalid EmployeeID...";
			 return false;
			} 
			 
			else if($('#Year').val()=="Select")
				{
				 $scope.msg="Please Select Year..";
				 return false;
				}
			else if($('#LeaveType').val()=='Select')
				
				{
				 $scope.msg='Please Select Leave Type..';
				   return false;
				}
			else if($('#Quantity').val()=='')
			{
				 $scope.msg='Please Enter Quantity..';
				return false;
			}
			
			else if($('#MaxLeave').val()=='Select')
				{
				 $scope.msg='Please Select CountWeekOff..';
				   return false;
				}
			
			else if($('#CountWeekOff').val()=='Select')
			{
				 $scope.msg='Please Select CountWeekOff..';
				return false;
			}
			else if($('#Status').val()=='Select')
				{
				 $scope.msg='Please Select Status..';
				    return false;
				}

			/* if ($scope.empcode != null && $scope.empcode.trim() != ""&& $scope.Year != ""&& $scope.Year != "0") { */
				//alert("Sure?");
				$scope.loading = true;
				$http({
					url : "LeaveInfo",
					method : "POST",
					params : {
						EmpID : $scope.empcode.trim(),
						Year : $scope.Year,
						LeveTypeid : $('#LeaveType').val(),
						Quantity:$('#Quantity').val(),
						MaxLeave:$('#MaxLeave').val(),
						CountWeekOff:$('#CountWeekOff').val(),
						Status:$('#Status').val(),
						
						Routing : "QuotaSubmission"
							 
						
					},
				}).then(function mySucces(response) {

					$scope.msg = "";
					$scope.loading = false;
					var res = response.data;
					//alert(res.split("~")[1]);
					if (res == "1") {
						$scope.msg = "Data Saved Successfully";
						$('#LeaveQuotaDisplay').html('');
						
						//$scope.Year="";
						$('#LeaveType').val('Select');
						$('#MaxLeave').val('Select');
						$('#Quantity').val('');
						$('#CountWeekOff').val('0');
						$('#Status').val('0');
						
					}if(res == "2"){
						$scope.msg = "Leave Quota Already Configured";
						$('#LeaveQuotaDisplay').html('');
						
						//$scope.Year="";
						$('#LeaveType').val('Select');
						$('#MaxLeave').val('Select');
						$('#Quantity').val('');
						$('#CountWeekOff').val('0');
						$('#Status').val('0');
					} 
					
					
					
					
					/* else {
						$scope.msg = "Processed Data Aborted";
					} */
				}, function myError(response) {
					$scope.myWelcome = response.statusText;
					alert($scope.myWelcome);
				});

			/* } else {
				
				$scope.msg = "Please Enter Valid Data";
			} */

		};
		// End Of Submission
		
		
		$scope.GetQuantity=function()
		{
			//alert("OOOOO");
			//alert($('#MaxLeave').val());
			
			if($('#empcode').val()=="")
				{
				   $scope.msg="Please Enter EmployeeCode....";
				   
				   $('#LeaveType').val('Select');
				   
				   return false;
				}
			
			else if($('#Year').val()=="Select")
				{
				 $scope.msg="Please Select Year....";
				     $('#LeaveType').val('Select');
				     return false;
				}
			
			/* else if($('#MaxLeave').val()=="Select")
			{
			     alert("Please Select Max Leave....");
			     $('#LeaveType').val('Select');
			     return false;
			} */
			
			
			
			else if($('#LeaveType').val()=="Select")
			{
				 $scope.msg="Please Select LeaveType....";
			   //  $('#LeaveType').val('Select');
			     return false;
			}
			
			
			 
			/* if ($scope.empcode != null && $scope.empcode != ""&&$scope.Year != null && $scope.Year != "") { */
				//$scope.loading = true;
				$http({
					url : "LeaveInfo",
					method : "POST",

					params : {
						EmpID : $scope.empcode,
						Leave :$('#LeaveType').val(),
						Year : $scope.Year,
						Routing : "GetQuantity"
					},
				}).then(function mySucces(response) {
					/* $scope.msg = "";
					$scope.loading = false; */
					var data=response.data;
					
     $("#LeaveQuotaDisplay").html("<font color='red'>Total Quantity:</font>"+data.QUANTITY+",<font color='red'>AvailableQty:</font>"+data.AVAILABLEQTY+",<font color='red'>UsedQty:</font>"+data.USEDQTY+",<font color='red'>Hold:</font>"+data.HOLD+"");
					
					
					
					//alert(JSON.Stringfy(response));
					/* 
					   alert(data.QUANTITY);
					alert(data.AVAILABLEQTY);
					alert(data.USEDQTY);
					alert(data.HOLD);   */
					 
					 
				}, function myError(response) {
					$scope.myWelcome = response.statusText;
					alert($scope.myWelcome);
				});
			/* } else {
				$scope.msg = "InValid Request";
			} */
		};
		
		
		$scope.MaxLeaveCount=function($event)
		{
		
		
		var dat=$event.target.value;
		
		
		document.getElementById('MaxLeave').length = 0;
		var comm_city = document.getElementById('MaxLeave');
		
		myOption = document.createElement("option");
		myOption.text='Select';
		myOption.value='Select';
		myOption.id='Select';
		comm_city.appendChild(myOption);
		
		
		
		for (var i = 0; i <=dat; i++) {

			myOption = document.createElement("option");
			myOption.text = i;
			myOption.value = i;
			myOption.id =i;
			comm_city.appendChild(myOption);

		}
		
		document.getElementById("MaxLeave").selectedIndex =dat;
		}

	});
 
 
function AdhadarisNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
   // var regexp=/^[a-zA-Z,\/,0-9]+$/;
         
    
    
 // alert(charCode);
    /*if (( !charCode==64 &&  charCode > 49 && charCode < 123)|| charCode==16 ||charCode==47  &&   96 <= charCode && charCode <= 105) {*/
    	if ( charCode>=48 && charCode<=57 ) {
    	return true;
    }
    return false;
}

	 
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
</style>
</head>
<!-- data-ng-init="empdetails();" -->
<body ng-app="myApp" ng-controller="formCtrl">
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
					Leave Quota  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:red">{{msg}} <img ng-show="loading" src="assets/images/spinner-blue1.gif" height="24" width="24"> </span>
				</div>
				<div class="panel-body">
				
				<div class="container-fluid">
				<table class="table">
					<thead class="thead-default">
						
					</thead>
					<tbody>
						<tr>
						  <td>Employee ID</td>
						  <td><input type="text" class="form-control" ng-model="empcode" name="empcode" maxlength="6"  id="empcode" ng-blur="empdetails()"></td>
						   <td>Employee Name</td>
						  <td><input type="text" class="form-control" id="empname" ng-model="empname" name="empname" readOnly> </td>
						  <td>DOJ</td>
						  <td><input type="text" class="form-control" id="empcode"  ng-model="empDoj"  name="empDoj" readOnly></td>
						  
						<tr>
						  <td>Designation</td>
						  <td><input type="text" class="form-control" id="empcode"   ng-model="empDesi" name="empDesi" readOnly></td>
						   <td>Department</td>
						  <td><input type="text" class="form-control" id="empcode"  ng-model="empDept" name="empDept" readOnly></td>
						   
						  <td>Employment</td>
						  <td><input type="text" class="form-control" id="empcode"  ng-model="empEmployment"  name="empEmployment" readOnly></td>
						</tr>
						  <!--  <td>Leave Type</td>
						  <td><input type="text" class="form-control" id="empcode" name="empcode"></td> -->
						<tr>
						<!-- ng-selected="true" -->
							<td colspan="6" style="background-color:#0088cc; color:#fff;">Leave Configuration</td>
							<td style="background-color:#0088cc; color:#fff;">Year</td>
							<td style="background-color:#0088cc; color:#fff;">
							<select class="form-control" ng-model="Year" ng-change="LoadQuota()" id="Year" >
							<option value="Select">Select Year</option>
							<option value="2018">2018</option>
							</select></td>
						</tr>
					    <tr>
							<td>
							Leave Type:
							      <select class="form-control1"  ng-model="LeaveType" id="LeaveType" ng-change="GetQuantity()">
							                        <option value="Select">Select</option>
													<option value="1">CASUAL LEAVE</option>
													<option value="2">SICK LEAVE</option>
													<option value="3">MATERNITY LEAVE</option>
													<option value="4">EARNED LEAVE</option>
													<option value="6">MARRIAGE LEAVE</option>
													<option value="14">ONDUTY</option>
													<option value="17">FIELD WORK</option>
													<option value="18">CYCLE MEETING</option>
											</select></td>
											
											<td>
											
											Quantity:
								      <input type="text" class="form-control1" id="Quantity" maxlength="3"  name="Quantity" onkeypress="return AdhadarisNumber(event)" ng-keyup="MaxLeaveCount($event)" >
			                                        </td>
			                                        
			                                        
			                                        
			                                <td>
											
											Monthly Utilization:
								      <input type="text" class="form-control1" id="MonthlyQuantity" maxlength="3"  name="MonthlyQuantity" onkeypress="return AdhadarisNumber(event)">
			                                        </td>

	<td>Back Days: 
		<input type="text" class="form-control1" id="backDays" maxlength="2" size='2' name="backDays"onkeypress="return AdhadarisNumber(event)">
	</td>
	<td>Category: 
		
		<select	class="form-control1" id="dayMode" name="dayMode">
			<option value="-1">Select Category</option>
			<option value="0">Regular</option>
			<option value="1">Special</option>
		</select>
	</td>



											<!--  <td>
			                                        
			                                        Avaliable Quantity:
								      <input type="text" class="form-control1"    id="AvailableQuantity">
			                                        </td> -->

											<td style='display: none;'>Max Leave: 
											<select	class="form-control1" id="MaxLeave">
													<option value="Select">Select</option>
											</select>
											</td>

											<td>
			                                       Consider WeekOffs/Holidays:
			                                       
			                                        <select class="form-control1"  id="CountWeekOff">
							                         <option value="Select" selected>Select</option>
			                                        <option value="0">Yes</option>
													<option value="1" >No</option>
													 
											</select>
			                                 
			                                        </td>
			                                        
			                                        <td style='display: none;' >
			                                        
			                                      Status:
			                                       
			                                        <select class="form-control1"  id="Status">
							                         <option value="Select">Select</option>
			                                        <option value="1">Probationary</option>
													<option value="0" selected>Permanent</option>
													 
											</select>
			                                 
			                                        </td>
			                                         
			                                        	
											
											
							<!-- <td>SL &nbsp;&nbsp;&nbsp;
							<input type="text" style="width:30%;"></td> -->
										
						</tr>
						
						
						</tbody>
				</table>
				
				 
						
						<span id="LeaveQuotaDisplay"> </span>
						
						 
				
					<div align="center">
					<button class="btn btn-primary btn-md " ng-click="ConfirmQuota()">Submit</button>
					</div>
				<div class="pull-right">
					<ul>
						<li><b>EL's :</b> Are Transferrable to the Next Year</li>
					<!-- 	<li>EL's : Are transferrable to the next year</li>
						<li>EL's : Are transferrable to the next year</li>
						<li>EL's : Are transferrable to the next year</li>
						<li>EL's : Are transferrable to the next year</li>
						<li>EL's : Are transferrable to the next year</li> -->
					
					</ul>
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