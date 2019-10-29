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
<link rel="stylesheet" href="jquery-ui-1.12.1.custom/jquery-ui.css">


<!-- datatable css -->
<link rel="stylesheet" type="text/css"  href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" type="text/css"  href="https://cdn.datatables.net/fixedcolumns/3.2.2/css/fixedColumns.bootstrap.min.css">
<link rel="stylesheet" type="text/css"  href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.bootstrap.min.css">

<script src="jquery-ui-1.12.1.custom/jquery-1.12.4.js"></script>



<!-- Mahesh -->

<script src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/fixedcolumns/3.2.2/js/dataTables.fixedColumns.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.flash.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/pdfmake.min.js"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.print.min.js"></script>
<!-- <script src="jquery-ui-1.12.1.custom/jquery-ui.js"></script> -->
<script src="MyAng.js"></script>


<!-- datatable start -->
<!-- <link rel="stylesheet" type="text/css"  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->


<!-- end -->
<!-- datatable js start -->


<script>
$(document).ready(function() {
    var table = $('#example').DataTable( {
		dom: 'Bfrtip',
        buttons: [
             'csv', 'excel'
        ],
        scrollY:        "300px",
        scrollX:        true,
        scrollCollapse: true,
        paging:         false,
        fixedColumns:   true
    } );
} );
</script>
<!-- end -->


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
		 $scope.loading = true;
	    	$http({
	    	    url: "LeaveInfo",
	    	    method: "POST",
	    	    params: {
	    	    	Routing :"ReDec-BuList"
	    	    },
	    	}).then(function mySucces(response) {
	    		
	    		 $scope.msg="";
	    		 $scope.loading = false;
	    		 var data=response.data;
	    		 $scope.cars = data;
			}, function myError(response) {
				$scope.myWelcome = response.statusText;
				alert($scope.myWelcome);
			});
		};
		//End Of Function empdetails
		
		
		
		//Start Function Get EmpList
		$scope.LoadEmpList=function(){
			
			var Bu=$scope.selectedCar;
			
			if(Bu!=null){
				 $scope.loading = true;
			    	$http({
			    	    url: "LeaveInfo",
			    	    method: "POST",
			    	    async: true,
			    	    cache: false,
			    	    params: {
			    	    	BU : Bu,
			    	    	Routing :"ReDec-EmpList"
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
				$scope.msg="Please Select Division";
			}
			
			
			
		
		};
		//End Function Get EmpList
		

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
    width: 120%;
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
<body ng-app="myApp" ng-controller="formCtrl" data-ng-init="empdetails();">
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
								
								<li style='display:{{ AC_LINKS.LINK7 }}'><a
									href="PayslipDownload?Routing=EmpReDeclare" target="_parent">
										<i class="fa fa-unlock"></i> <span class="font-bold">ITax Re-Declaration</span>
								</a></li>
								
								<li style='display:{{ AC_LINKS.LINK7 }}'><a
									href="PayslipDownload?Routing=TDS" target="_parent">
										<i class="fa fa-cogs"></i> <span class="font-bold">Forecast Generation</span>
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
				<!-- <div class="col-md-12"> -->
				<div class="panel panel-primary">
				
				
				<div class="panel-heading1">
				<div class="col-sm-12">
					<div class="col-sm-4">
						<span>Employee List</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>{{msg}} <img ng-show="loading" src="assets/images/spinner-blue1.gif" height="24" width="24"> </span>
					</div>
					<div class="col-sm-4">
						
					</div>
					<div class="col-sm-4 pull-right">
					<div class="form-group">
							<label class="control-label col-sm-6">Select Division</label>
							<div class="col-sm-6">
								<select class="form-control1" ng-model="selectedCar" ng-options="x for (x, y) in cars" ng-change="LoadEmpList();">
								<option value="">Select </option></select>
							</div>
						</div>
					</div>
				</div>
				</div>
				
				<div class="panel-body">







						<form>
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-addon">
										<i class="fa fa-search"></i>
									</div>
									<input type="text" class="form-control"
										placeholder="Search da Fish" ng-model="searchFish">
								</div>
							</div>
						</form>
						
						
						
						

						<!-- <table id="example" class="table table-striped table-bordered nowrap" width="100%" > -->
						
					
						
				<div id="scrollable-area">
				<table class="table table-striped table-bordered ">
							<thead>
								<tr>
									<th>Employeeid</th>
									<th>Name</th>
									<th>Division</th>
									<th>EmpStatus</th>
									<th>DOJ</th>
									<th>Department</th>
									<th>Designation</th>
									<th>PAN No.</th>
									<th>Gross</th>
									<th>Status</th>
									<th>Last Update</th>
									<th>Re-Declare.?</th>
									

								</tr>
							</thead>
							<tbody>
						
      

								<tr ng-repeat="xy in student |   filter:searchFish">
									<td>{{ xy.ID }}</td>
									<td>{{ xy.Name }}</td>
									<td>{{ xy.BU }}</td>
									<td>{{ xy.EmpStatus }}</td>
									<td>{{ xy.DOJ }}</td>
									<td>{{ xy.DEPT }}</td>
									<td>{{ xy.DESI }}</td>
									<td>{{ xy.PAN }}</td>
									<td>{{ xy.GR }}</td>
									<td>{{ xy.Status }}</td>
									<td>{{ xy.LUP }}</td>
									<!-- <td>{{ xy.ReD }}</td> -->
<td><input type="button"  class="button5"ng-model="mybutton"  name="mybutton" id="{{ xy.ID}}"  value="Re Declare" ng-show={{xy.ReD}} ng-click="clicked($event)"></td>

					 <!-- ng-disabled="xy.ReD != 1" -->			</tr>
							</tbody>
							
						</table>
                  </div>
				</div>
				</div>
				<!-- </div> -->
				
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
	<!-- Change Password -->

	<script type="text/javascript">
$("#Date").datepicker({
	dateFormat : 'yy-mm-dd',
	changeMonth: true,
    changeYear: true,
    yearRange: '1950:2050'

});

</script>
<style>

 #scrollable-area {
    height: 350px;
    overflow-y: scroll; /* <-- here is what is important*/
}
table {
  width: 100%;
}
thead {
    background: #fff;
}
.button5 {border-radius: 50%;background-color: #4CAF50;text-align: center;
    text-decoration: none;
    display: inline-block;color: white;}
</style>


</body>
</html>