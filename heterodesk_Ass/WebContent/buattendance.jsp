
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
  String tblData=(String)request.getAttribute("tblData");
  String tblDataHeader=(String)request.getAttribute("tblDataHeader");
  String EMPBUID=(String)session.getAttribute("EMPBUID");

%>
    
    
    
<!doctype html>
<html class="fixed">
	<head>

		<!-- Basic -->
		<meta charset="UTF-8">

		
		<meta name="keywords" content="HETERO" />
		<meta name="description" content="Hetero">
		<meta name="author" content="Hetero">

         <title>Report</title>
          <link rel="icon" href="LOH.png" />
          
          
          
		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

		<!-- Web Fonts  -->
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">
 		
 		
 		
 		
 		
 		<!-- Specific Page Vendor CSS -->
		<link rel="stylesheet" href="jquery-ui-1.12.1.custom/jquery-ui.css">
		
		
		<!-- Vendor CSS -->
		<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
		
		<link rel="stylesheet" href="assets/vendor/magnific-popup/magnific-popup.css" />
	
		<link rel="stylesheet" href="assets/stylesheets/GScroll.css" />
		<link rel="shortcut icon" href="/images.png" type="image/x-icon" />

		<script src="jquery-ui-1.12.1.custom/jquery-1.12.4.js"></script>
 		<script src="jquery-ui-1.12.1.custom/jquery-ui.js"></script>
		

		<!-- Theme CSS -->
		<link rel="stylesheet" href="assets/stylesheets/theme.css" />

		<!-- Skin CSS -->
		<link rel="stylesheet" href="assets/stylesheets/skins/default.css" />
	
		<!-- Theme Custom CSS -->
		<link rel="stylesheet" href="assets/stylesheets/theme-custom.css">
			 
			<script src="jquery.colorbox.js"></script> 
			<link rel="stylesheet" href="colorbox.css" />
	
	
   
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
app.controller('formCtrl', function($scope, $http) {
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
    
   
	// Start Of Loading Bu's
	$scope.loadBu = function() {
		 $scope.loading = true;
			$http({
				url : "DivisionalAttendance",
				method : "POST",
				params : {
					Routing : "loadingempBu",
					empCode:<%=Emp_id%>
				}
			}).then(function mySucces(response) {
				
				var res = response.data;
				
				 $scope.cars = res;
				
				// $scope.divisionCode = $scope.cars[2];
				 
					
				 $scope.loading = false;
				 
			}, function myError(response) {
			});
			
	};
	 $scope.divisionCode=""+<%=EMPBUID%>+"";
	// End Of Loading Bu's
    
	
	
	
	
 
    
    
    
    
 // Start Of Submission
	$scope.firsttime = function() {
		 $scope.loading = true;
			$http({
				url : "DivisionalAttendance",
				method : "POST",
				params : {
					Routing : "empattendanceReport",
					Div:$scope.divisionCode
				}
			}).then(function mySucces(response) {
				var res = response.data;
				//$("#example").dataTable().fnDestroy();
				$("#tblData").html(res.split("~")[0]);
				$("#tblDataHeader").html(res.split("~")[1]);
				a();
				 $scope.loading = false;
				
			}, function myError(response) {
			});
	};
	// End Of Submission
    
	
	
    
		$scope.f1 = function() {
				//	document.getElementById("tableloading").style.display = "none";

				/* dataTable.fnClearTable();
				dataTable.fnDraw();
				dataTable.fnDestroy(); */
				
				
				var countries = [];
		        $.each($("#bu option:selected"), function(){            
		            countries.push($(this).val().split(":")[1]);
		        });
				var tot=countries;
				
		       // alert("You have selected the country - " + countries.join(", "));
		    	$scope.divisionCode=tot;
				$("#example").dataTable().fnClearTable();
				$("#example").dataTable().fnDestroy();
				$scope.firsttime();
				
			}

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
.hrs-panel {
	height: 53px;
}
.hrs-span {
	width: 300px;
    float: left;
    overflow: hidden;
    padding-top: 7px;
}
.hrs-span small {
	font-size: 12px;
    padding-left: 40px;
}
.multiselect-native-select {
	width: 110px;
    float: left;
}
.multiselect-container {
	min-height: 150px;
	max-height: 150px;
	overflow: auto;
}
  
  </style>
  
  
  
  <!-- Mahesh DataTables Links -->
  
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/fixedcolumns/3.2.6/js/dataTables.fixedColumns.min.js"></script>


<script src="HR/Multiselect.js"></script>
  <script type="text/javascript">
$(function() {
    $('.multiselect-ui').multiselect({
        includeSelectAllOption: true
    });
});
</script>





<script src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.html5.min.js"></script>



<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.5.2/css/buttons.dataTables.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/fixedcolumns/3.2.6/css/fixedColumns.dataTables.min.css">






<style>
/* Ensure that the demo table scrolls */
    th, td { white-space: nowrap; }
    div.dataTables_wrapper {
        width: 95%;
        margin: 0 auto;
    }
</style>



<script>
//$(document).ready(function() {
	
	
	function a(){
		
		
    var table = $('#example').DataTable( {
        scrollY:        "300px",
        scrollX:        true,
        scrollCollapse: false,
        paging: false,
		dom: 'Bfrtip',
		buttons: ['excel'],
        fixedColumns:   {
            leftColumns: 1
            /* rightColumns: 1 */
        }
    } );
    //$("#example").dataTable().fnDestroy();
   
} ;
</script>

<style type="text/css">

.form-control1 {
  /*   display: block; */
    width: 17%;
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

</style>
  
  <!-- Mahesh DataTables Links End -->
  
  
  <!-- MultiSelect Box start -->
<style>
span.multiselect-native-select {
	position: relative
}
span.multiselect-native-select select {
	border: 0!important;
	clip: rect(0 0 0 0)!important;
	height: 1px!important;
	margin: -1px -1px -1px -3px!important;
	overflow: hidden!important;
	padding: 0!important;
	position: absolute!important;
	width: 1px!important;
	left: 50%;
	top: 30px
}
.multiselect-container {
	position: absolute;
	list-style-type: none;
	margin: 0;
	padding: 0
}
.multiselect-container .input-group {
	margin: 5px
}
.multiselect-container>li {
	padding: 0
}
.multiselect-container>li>a.multiselect-all label {
	font-weight: 700
}
.multiselect-container>li.multiselect-group label {
	margin: 0;
	padding: 3px 20px 3px 20px;
	height: 100%;
	font-weight: 700
}
.multiselect-container>li.multiselect-group-clickable label {
	cursor: pointer
}
.multiselect-container>li>a {
	padding: 0
}
.multiselect-container>li>a>label {
	margin: 0;
	height: 100%;
	cursor: pointer;
	font-weight: 400;
	padding: 3px 0 3px 30px
}
.multiselect-container>li>a>label.radio, .multiselect-container>li>a>label.checkbox {
	margin: 0
}
.multiselect-container>li>a>label>input[type=checkbox] {
	margin-bottom: 5px
}
.btn-group>.btn-group:nth-child(2)>.multiselect.btn {
	border-top-left-radius: 4px;
	border-bottom-left-radius: 4px
}
.form-inline .multiselect-container label.checkbox, .form-inline .multiselect-container label.radio {
	padding: 3px 20px 3px 40px
}
.form-inline .multiselect-container li a label.checkbox input[type=checkbox], .form-inline .multiselect-container li a label.radio input[type=radio] {
	margin-left: -20px;
	margin-right: 0
}
</style>
  
<!-- MultiSelect Box  End-->
  
  
  
  
  
  
  
  
  
  
  
  
	</head>
	
	<body   ng-app="myApp" ng-controller="formCtrl" ng-init="loadBu();firsttime();">
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
				<div class="row" style="padding-left:0px;">
							<div class="col-xs-12 col-sm-8 col-md-11">
								<div class="panel panel-primary">
									<div class="panel-heading1 hrs-panel">
										<span class="hrs-span"><i class="fa fa-info-circle fa-lg"></i>&nbsp;&nbsp;&nbsp;Attendance Information <small>Division</small></span>
										
										
										<!-- <select ng-model="divisionCode" class="form-control1" ng-options="x for (x, y) in cars"></select> -->
										<!-- <select id="bu" class="multiselect-ui form-control" ng-model="divisionCode"   ng-options="x for (x, y) in cars" multiple="multiple"> -->
										<select id="bu" class="multiselect-ui form-control" ng-model="divisionCode"   ng-options="x for (x, y) in cars"  multiple="multiple" >
										
									
										
										
							            
        								</select>
										
										
										
										
										
										
										
										<input type="button" name="buselect" value="Get" style="color:red;width: 80px;padding: 5px;" ng-click='f1();'/>
										
										<img ng-show="loading" src="assets/images/spinner-blue1.gif" height="24" width="24">
										
										
									</div>
									
									
									<!-- <div class="form-group">
   
    <div class="col-md-2">
        <select id="bu" class="multiselect-ui form-control" multiple="multiple" ">
            <option value="cheese">Cheese</option>
            <option value="tomatoes">Tomatoes</option>
            <option value="mozarella">Mozzarella</option>
            <option value="mushrooms">Mushrooms</option>
            <option value="pepperoni">Pepperoni</option>
            <option value="onions">Onions</option>
        </select>
    </div>
	<input type="button"  value="Get" onclick="f1();"/>
	
	
</div> -->
									
									
									
									
									
									
									<div class="panel-body" >
										
											<div class="table-responsive" >
												<table id="example" class="table table-striped table-bordered stripe row-border order-column" >
												<thead>
										            <tr id="tblDataHeader">
										              <%--   <%=tblDataHeader %> --%>
										            </tr>
										        </thead>
										       
												<tbody id="tblData">
										       <%--  <%=tblData%> --%>
										        </tbody>
													
												</table>
											</div>
										</div>
									</div>
								</div>	
							</div>
						</div>
					
					
					<!-- end: page -->
				
					
				</section>
			
		
	




	<script src="Test.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
	<script src="assets/javascripts/theme.custom.js"></script>
	<script src="assets/javascripts/theme.init.js"></script>
	<script src="assets/javascripts/jquery.colorbox.js"></script>
	
	


</section>
 	</body>
 	
 	
 	
 	
 	
 	
 	
</html>