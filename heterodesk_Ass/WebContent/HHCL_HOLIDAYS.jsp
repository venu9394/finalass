<!doctype html>
<html class="fixed">


<% 
  
  String Emp_BioData=(String)request.getAttribute("DOJ_DOB");

  
%>
	<head>
	<script src="MyAng.js"></script>
<script>
var app = angular.module('myApp', []);
app.controller('formCtrl', function($scope) {
	var Data="";
	$scope.Data_2="";
	$scope.Data_1="";
	$scope.Data_3="";
	$scope.xy=1;
	try{
		 $scope.Data_1=<%=Emp_BioData%>;
   
   <%--  $scope.Data_2=<%=EmpDOB%>;
    $scope.Data_3=<%=HOLIDAYS_PG%>;
  
    $scope.empid = Data.EMPLOYEESEQUENCENO;
    $scope.empname= Data.CALLNAME;
    $scope.emp_doj= Data.DATEOFJOIN;
    $scope.emp_Dep= Data.DEPARTMENT;
    $scope.emp_Des= Data.DESIGNATION;
    $scope.emp_job_type= Data.TYPE;
     --%>
	}catch(err){
		alert("Please Try Again..");
	}
 });
 
 
</script>

		<!-- Basic -->
		<meta charset="UTF-8">

		<title>Hetero</title>
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

          <link href="Per_src/perfect-scrollbar.css" rel="stylesheet">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="Per_src/jquery.mousewheel.js"></script>
        <script src="Per_src/perfect-scrollbar.js"></script>
        <style>
      #description {
        border: 1px solid gray;
        height:100%;
        width: 100%;
        overflow: hidden;
        position: absolute;
      }
    </style>
    
    <script type="text/javascript">
      $(document).ready(function ($) {
        $('#description').perfectScrollbar({
          wheelSpeed: 20,
          wheelPropagation: false
        });
      });
    </script>



	</head>
	<body ng-app="myApp" ng-controller="formCtrl" >
	
	<div id="description" class="wrapper">
     
      
	
		


				
					<!-- start: page -->
					<div class="container">
						<div class="row center_div1" >
							<div class="col-md-12">
								<div class="panel panel-danger" >
									<div class="panel-heading1">
										<span><b>HOLIDAYS LIST</b></span>
								
									</div>
									<div class="panel-body">
									<div >
										<div class="table-responsive">
										
										
									<!-- 	<table class="table table-striped">
															<tr  ng-repeat="(key, value) in Data_1" ><td style="">{{ key }}</td> <td>{{ value }}</td></tr>
								                   </table>	 -->
											<table class="table table-striped">
											
													<thead class="thead-default text-center">
														<tr   >
															<th>EVENT DATE</th>
															<th>EVENT</th>
															<th>STATUS</th>
															<th>HOLIDAY TYPE</th>
														</tr>
													</thead>
													<tr ng-repeat="x in Data_1">
														<td>{{ x.EVENTDATE }}</td>
														<td>{{ x.EVENT }}</td>
														<td>{{ x.FLAG }}</td>
														<td>{{ x.HOLIDAYTYPE }}</td>
													</tr>
													
											</table>	
										</div>
									</div>
									</div>
									
								</div>
							</div>
						</div>	
						
					</div>
					<!-- end: page -->
				

			

			
		
		
		
		
		
		
		</div>
		
		
		<!-- Vendor -->
		<!-- <script src="assets/vendor/jquery/jquery.js"></script> -->
		<!-- <script src="assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script> -->
		<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
		<!-- <script src="assets/vendor/nanoscroller/nanoscroller.js"></script> -->
		<script src="assets/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
		
		
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
		<script src="assets/javascripts/pages/examples.calendar.js"></script>
		 
	</body>
</html>