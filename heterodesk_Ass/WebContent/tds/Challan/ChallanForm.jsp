<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<% 
  String Emp_Payslip=(String)session.getAttribute("PaySlip");
  String Emp_ForeCast=(String)session.getAttribute("ForeCast");
  String Emp_id=(String)session.getAttribute("EMP_ID");
  String TDSFLAG=(String)session.getAttribute("TDSFLAG");
  String EMP_NAME=(String)session.getAttribute("EMP_NAME");
 /*  String HR_LINKS=(String)session.getAttribute("HR_LINKS"); */
  String AC_LINKS=(String)session.getAttribute("AC_LINKS");
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
	$scope.AC_LINKS="";
	$scope.SelectBox="";
	
	try{
		Data="";
	$scope.Data_1="";
    $scope.AC_LINKS=<%=AC_LINKS%>;
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
    $scope.empdetails=function(){
		/* alert("Mahesh"); */
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
					$scope.CL = 0;
					$scope.SL = 0;
					$scope.EL = 0;
					$scope.Year = "";
					
				} else {

					$scope.msg = "Please enter Valid Employeeid";
				}

			}, function myError(response) {
				$scope.myWelcome = response.statusText;
				alert($scope.myWelcome);
			});
		};
		//End Of Function empdetails

		// Start Of Year Load
		$scope.LoadQuota = function() {

			$scope.CL = 0;
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
			}
		};
		// End Of Year Load

		// Start Of Submission
		$scope.ConfirmQuota = function() {

			if ($scope.empcode != null && $scope.empcode.trim() != ""
					&& $scope.Year != "" && $scope.CL != null
					&& $scope.SL != null && $scope.EL != null
					&& $scope.Year != "0") {
				//alert("Sure?");
				$scope.loading = true;
				$http({
					url : "LeaveInfo",
					method : "POST",
					params : {
						EmpID : $scope.empcode.trim(),
						Year : $scope.Year,
						SL : $scope.SL,
						CL : $scope.CL,
						EL : $scope.EL,
						Routing : "QuotaSubmission"
					},
				}).then(function mySucces(response) {

					$scope.msg = "";
					$scope.loading = false;
					var res = response.data;
					if (res == "1") {
						$scope.msg = "Data Saved Successfully";
					} else {
						$scope.msg = "Processed Data Aborted";
					}
				}, function myError(response) {
					$scope.myWelcome = response.statusText;
					alert($scope.myWelcome);
				});

			} else {
				$scope.msg = "Please Enter Valid Data";
			}

		};
		// End Of Submission

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
.select {
	border: 1px solid #E5E7E9;
	border-radius: 6px;
	height: 25px;
	padding: 2px;
	outline: none;
	color:#0088cc;
	margin-bottom:10px;
	
}

.panel-headingattn{
        height:50px;
		background-color:#0088cc;
		line-height:20px;
		color:#fff;
		padding-left:20px;
		padding-top:10px;
		
}
.btn1{
  display: inline-block;
  margin-bottom: 0;
  font-weight: normal;
  text-align: center;
  vertical-align: middle;
  touch-action: manipulation;
  cursor: pointer;
  background-image: none;
  border: 1px solid transparent;
  white-space: nowrap;
  padding: 4px 4px;
  font-size: 10px;
  line-height: 1.42857143;
  border-radius: 4px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  background-color:#fff;
  color:#0088cc;
  margin-top: 5px;
}
.form-control1 {
    display: block;
    width: 80px;
    height: 20px;
    padding: 3px 6px;
    font-size: 10px;
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
    transition: border-color ease-in-out .15s, box-shadow ease-in-out ;
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
								<!-- Accounts Approvals Start -->
								<li style='display:{{ AC_LINKS.LINK7 }}'><a
									href="PayslipDownload?Routing=EmpDeclare" target="_parent">
										<i class="fa fa-file-text-o"></i> <span class="font-bold">ITax Declaration</span>
								</a></li>
								
								
								<!-- <li style='display:{{ AC_LINKS.LINK7 }}'><a
									href="PayslipDownload?Routing=EmpReDeclare" target="_parent">
										<i class="fa fa-unlock"></i> <span class="font-bold">ITax Re-Declaration</span>
								</a></li> -->
								
								
								<li style='display:{{ AC_LINKS.LINK7 }}'><a
									href="PayslipDownload?Routing=TDS" target="_parent">
										<i class="fa fa-cogs"></i> <span class="font-bold">Forecast Generation</span>
								</a>
								</li>
								
								<li style='display:{{ AC_LINKS.LINK8 }}'><a
									href="PayslipDownload?Routing=TDSChallan" target="_parent">
										<i class="fa fa-cogs "></i> <span class="font-bold">TDS Challan Generation</span>
								</a></li>
								
								
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
				
				<div class="page-contentbar">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="panel panel panel-primary">
                                <div class="panel-headingattn">
                                	<div class="col-md-6" style="margin-top:5px;">
									  	<span class="col-sm-1">From</span>
									  	<div class="col-sm-3">
									  	<input type="text" id="fromdate" name="from"  readOnly class="form-control1">
									  	</div>
									  	<span class="col-sm-1">To</span>
									  	<div class="col-sm-3">
									  	<input type="text" id="todate" name="to" readOnly class="form-control1">
									  	</div>
                               			 </div>
                               			 <div class="col-md-3">
                               			 	<input type="Submit" class="btn1" value="Download" id="Download">
											<input type="Submit" class="btn1" value="Reset" id="ResetDownload">
                               			 </div>
                               			 </div>
								
									
                                          <div class="panel-body table-responsive">
												   
												   <table class="table table-bordered  ">
														<thead class="thead-default">
														<tr >
															<th colspan="1" class="text-center">TDS</th>
															<th colspan="1" class="text-center">Surcharge</th>
															<th colspan="1" class="text-center">Education Cess</th>
															<th colspan="1" class="text-center">Interest</th>
															<th colspan="1" class="text-center">Fees u/s 234E</th>
															<th colspan="1" class="text-center">Penalty / Others</th>
															<th colspan="1" class="text-center">Total Tax Deposited</th>
															<th colspan="1" class="text-center">Mode of deposit through Challan (C) Book Adjustment (B)</th>
															<th colspan="1" class="text-center">Bank-Branch Code/ Form 24G Receipt Number</th>
															<th colspan="1" class="text-center">Challan Serial No./DDO Serial no. of Form No.24G</th>
															<th colspan="1" class="text-center">Date of amount deposited through challan/Date of transfer voucher </th>
															<th colspan="1" class="text-center">Minor Head of Challan</th>
															<th colspan="1" class="text-center">Interest (Corresponding to Regular Statement)</th>
															<th colspan="1" class="text-center">Other (Corresponding to Regular Statement)</th>
															<th class="text-center">Action</th>
															
															
														</tr>
														<tr>
															
															<th class="text-center">302</th>
															<th class="text-center">303</th>
															<th class="text-center">304</th>
															<th class="text-center">305</th>
															<th class="text-center">306</th>
															<th class="text-center">307</th>
															<th class="text-center">308</th>
															<th class="text-center">309</th>
															<th class="text-center">310</th>
															<th class="text-center">311</th>
															<th class="text-center">312</th>
															<th class="text-center">313</th>
															<th class="text-center"></th>
															<th class="text-center"></th>
															<th class="text-center"></th>
															
														</tr>
														</thead>
														<tbody>
															<tr>
															<!-- <td style="width:180px;vertical-align:middle !important;"><input type="text" class="form-control input-sm " id="TDS" onkeypress="return RateisNumber(event,this)"></td>
															<td style="width:30px;vertical-align:middle !important;"><input type="text" class="form-control input-sm" id="Surcharge"  size="3" onkeypress="return RateisNumber(event,this)"></td>
															<td style="width:100px;vertical-align:middle !important;"><input type="text" class="form-control input-sm" id="Education_Cess" size="3" onkeypress="return RateisNumber(event,this)"></td>
															<td style="width:60px;vertical-align:middle !important;"><input type="text" class="form-control input-sm" id="Interest" size="3" onkeypress="return RateisNumber(event,this)"></td>
															<td style="width:60px;vertical-align:middle !important;"><input type="text" class="form-control input-sm" id="Fees"   size="3" onkeypress="return RateisNumber(event,this)"></td>
															<td style="width:60px;vertical-align:middle !important;"><input type="text" class="form-control input-sm" id="Penalty_Others"  size="3" onkeypress="return RateisNumber(event,this)"></td>
															<td style="width:200px;vertical-align:middle !important;"><input type="text" class="form-control input-sm"  id="Total_Tax" size="3" readonly></td>
															<td style="width:150px;vertical-align:middle !important;"><input type="text" class="form-control input-sm" id="Mode_Of_Deposit" value="C - Challan" size="3"></td>
															<td style="width:60px;vertical-align:middle !important;"><input type="text" class="form-control input-sm"  id="Bank_Branch_Code"  size="3" onkeypress="return RateisNumber(event,this)"></td>
															<td style="width:60px;vertical-align:middle !important;"><input type="text" class="form-control input-sm" id="Challan_Serial_No"  size="3" onkeypress="return RateisNumber(event,this)"></td>
															<td style="width:60px;vertical-align:middle !important;"><input type="text" class="form-control input-sm"  id="Date_Of_Amount_Deposited" readonly size="3"></td>
															<td style="width:290px;vertical-align:middle !important;"><input type="text" class="form-control input-sm" id="Minor_Head_Challan"  value="200 - TDS payable by taxpayer" size="3" ></td>
															<td style="width:60px;vertical-align:middle !important;"><input type="text" class="form-control input-sm" size="3" id="Interest_Statement" onkeypress="return RateisNumber(event,this)"></td>
															<td style="width:60px;vertical-align:middle !important;"><input type="text" class="form-control input-sm" size="3" id="Other_Statement" onkeypress="return RateisNumber(event,this)"></td>
															<td style="vertical-align:middle !important;"><button type="button" class="btn btn-primary btn-xs btn-rounded" onclick="AddRow('datatable');">Add</button></td>																				
															 -->
															</tr>
															<tr>
															<td><input type="text" class="form-control1 input-sm " id="TDS"  onkeypress="return RateisNumber(event,this)"></td>
															<td><input type="text" class="form-control1 input-sm" id="Surcharge"  size="3" onkeypress="return RateisNumber(event,this)"></td>
															<td><input type="text" class="form-control1 input-sm" id="Education_Cess" size="3" onkeypress="return RateisNumber(event,this)"></td>
															<td><input type="text" class="form-control1 input-sm" id="Interest" size="3" onkeypress="return RateisNumber(event,this)"></td>
															<td><input type="text" class="form-control1 input-sm" id="Fees"   size="3" onkeypress="return RateisNumber(event,this)"></td>
															<td><input type="text" class="form-control1 input-sm" id="Penalty_Others"  size="3" onkeypress="return RateisNumber(event,this)"></td>
															<td><input type="text" class="form-control1 input-sm"  id="Total_Tax" size="3" readonly></td>
															<td><input type="text" class="form-control1 input-sm" id="Mode_Of_Deposit" value="C - Challan" size="3"></td>
															<td><input type="text" class="form-control1 input-sm"  id="Bank_Branch_Code"  size="3" onkeypress="return RateisNumber(event,this)"></td>
															<td><input type="text" class="form-control1 input-sm" id="Challan_Serial_No"  size="3" onkeypress="return RateisNumber(event,this)"></td>
															<td><input type="text" class="form-control1 input-sm"  id="Date_Of_Amount_Deposited" readonly size="3"></td>
															<td><input type="text" class="form-control1 input-sm" id="Minor_Head_Challan"  value="200 - TDS payable by taxpayer" size="3" ></td>
															<td><input type="text" class="form-control1 input-sm" size="3" id="Interest_Statement" onkeypress="return RateisNumber(event,this)"></td>
															<td><input type="text" class="form-control1 input-sm" size="3" id="Other_Statement" onkeypress="return RateisNumber(event,this)"></td>
															<td><button type="button" class="btn btn-primary btn-xs btn-rounded"  onclick="AddRow('datatable');">Add</button></td>																				

															</tr>
															
														</tbody>
												   </table>
													<table class="table table-bordered" >
														<thead class="thead-default">
														<tr style="display:none;]">
															<th colspan="1" class="text-center">TDS</th>
															<th colspan="1" class="text-center">Surcharge</th>
															<th colspan="1" class="text-center">Education Cess</th>
															<th colspan="1" class="text-center">Interest</th>
															<th colspan="1" class="text-center">Fees u/s 234E</th>
															<th colspan="1" class="text-center">Penalty / Others</th>
															<th colspan="1" class="text-center">Total Tax Deposited</th>
															<th colspan="1" class="text-center">Mode of deposit through Challan (C) Book Adjustment (B)</th>
															<th colspan="1" class="text-center">Bank-Branch Code/ Form 24G Receipt Number</th>
															<th colspan="1" class="text-center">Challan Serial No./DDO Serial no. of Form No.24G</th>
															<th colspan="1" class="text-center">Date of amount deposited through challan/Date of transfer voucher (dd/mm/yyyy)</th>
															<th colspan="1" class="text-center">Minor Head of Challan</th>
															<th colspan="1" class="text-center">Interest (Corresponding to Regular Statement)</th>
															<th colspan="2" class="text-center">Other (Corresponding to Regular Statement)</th>
														</tr>
														</thead>
														<tbody id="datatable">
														</tbody>
														
													</table>
																							
													
													<div align="center"><input type="button"  class="btn btn-primary" id="Submit"  value="Submit">
													<input type="button" class="btn btn-primary" id="Reset" value="Reset"></div>
											    
                                          </div>
                                         </div>

                            </div>
                        </div> 
                        <!-- end row -->
	            	</div>
				<!-- End: page -->
				
				</section>
					
				




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

 <script src="tds/Challan/FormScript/ChallanScript.js"></script>


	<script type="text/javascript">
$("#Date").datepicker({
	dateFormat : 'yy-mm-dd',
	changeMonth: true,
    changeYear: true,
    yearRange: '1950:2050'

});



// Script Start

$('#Date_Of_Amount_Deposited').datepicker({
	yearRange : "-90:+0",
	changeMonth : true,
	changeYear : true,
	maxDate:0,
	dateFormat : 'dd-mm-yy'

}); 

 
 $('#fromdate').datepicker ({
		yearRange : "-90:+0",
		changeMonth : true,
		changeYear : true,
		maxDate:0,
		dateFormat : 'dd-mm-yy',
		onSelect : function(date) {
			$("#todate").datepicker('option', 'minDate', date);
		}

	}); 
 
 
 $('#todate').datepicker({
			yearRange : "-90:+0",
			changeMonth : true,
			changeYear : true,
			minDate:0,
			dateFormat : 'dd-mm-yy'			

		}); 
	 
  
function isNumber(evt) {
	evt = (evt) ? evt : window.event;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode >= 48 && charCode <= 57) {
		return true;
	}
	return false;
}
 
function isAlphaNumber(evt) {
	evt = (evt) ? evt : window.event;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if ((charCode >= 65 && charCode <= 90) || (charCode >= 97 && charCode <= 122) || (charCode >= 48 && charCode <= 57)) {
		return true;
	}
	return false;
}

function isAlpha(evt) {
	evt = (evt) ? evt : window.event;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if ((charCode >= 65 && charCode <= 90) || (charCode >= 97 && charCode <= 122)) {
		return true;
	}
	return false;
}
 
 function DotisNumber(evt) {
		evt = (evt) ? evt : window.event;
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (charCode==46 || charCode >= 48 && charCode <= 57) {
			return true;
		}
		return false;
	}
 
 function RateisNumber(evt,val) {
	 evt = (evt) ? evt : window.event;
	 var charCode = (evt.which) ? evt.which : evt.keyCode;
	 // var dat=document.getElementById('VAT').value;
	 var y = String.fromCharCode(event.keyCode);
	 var Data= document.getElementById(""+val.id+"").value;
	 //alert(Data.length);
	 if(Data.length==0){

	 if(y=="."){
	 // alert(1);
	 return false;
	 }
	 }
	 Data=Data.concat(y);
	 var char='';
	 var count=0;
	 try{
	 for(var i=0;i<Data.length;i++){
	 char=Data.charAt(i);

	 if(char=="."){

	 count=count+1; 
	 }
	 //alert("char::"+char);

	 }
	 }catch(err){

	 alert(err.message);
	 }
	 //alert(count+"::count")
	 if(count>1){
	 return false;
	 }


	 if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode!=46) {

	 return false;
	 }
	 return true;
	 }

//Script End



</script>


</body>
</html>