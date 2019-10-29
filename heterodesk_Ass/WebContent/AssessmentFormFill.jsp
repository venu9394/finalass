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
<%
	String eCode=(String)request.getParameter("eCode"); 

	if(eCode!=null){
		System.out.println("eCode~"+eCode);
	}else{
		response.sendRedirect("Hhc_desk_login.jsp");
	}
	String Emp_BioData = (String) request.getAttribute("DOJ_DOB");
	String ATT_MONTHS = (String) request.getAttribute("ATT_MONTHS");
%>

<!doctype html>
<html class="fixed">
	<head>
	<script src="MyAng.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
   <link href="assets/stylesheets/tooltip.css" rel="stylesheet" type="text/css" />
   <script src="assets/javascripts/tooltip.js" type="text/javascript"></script>
    







	
	
<script>

var app = angular.module('myApp', []);
app.controller('formCtrl', function($scope,$http) {
	var Data="";
	$scope.Data_2=<%=ATT_MONTHS%>;
	$scope.Data_1="";
	$scope.Data_3="";
	$scope.xy=1;
	$scope.Data_1=<%=Emp_BioData%>;
	 
	
	$scope.getEmpdetails = function() {
		
		alert($scope.EMPLOYEECODE);
	} 
	 

 });

 
</script>


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
    

	</head>
	<body  ng-app="myApp" ng-controller="formCtrl"  data-ng-init="getEmpdetails()" onload="disableBackButton(); noBack();">
	
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
					
					
					<!-- New Content add Start -->
					<div class="col-md-12">
                    <!-- start: page -->
                    <div id="description" class="wrapper" style="margin-top: 0%;">

                        <div class="container-fluid">
                            <div class="row ">
                                <div class="col-md-12 middle-reg2">
                                    <div class="panel panel-primary">
                                        <div class="panel-heading2 text-center">
                                            <span><strong>EMPLOYEE ASSESSMENT FORM</strong></span></div>
                                        <div class="panel-body">
                                            <!-- Table Starts-->
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered">
                                                    <tbody>
                                                        <tr>
                                                            <td>Emp. Name</td>
                                                            <td>
                                                                <input type="text" class="form-control" readonly name="Emp_name" />
                                                            </td>
                                                            <td>Qualification</td>
                                                            <td>
                                                                <input type="text" class="form-control" readonly name="Emp_Qual" />
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Emp. No</td>
                                                            <td>
                                                                <input type="text" class="form-control"  ng-model="EMPLOYEECODE"  value="123" />
                                                                
                                                               
                                                                
                                                            </td>
                                                            <td>Date of Joining</td>
                                                            <td>
                                                                <input type="text" class="form-control" readonly name="Emp_DOJ" />
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Designation</td>
                                                            <td>
                                                                <input type="text" class="form-control" readonly name="Emp_Desg"/>
                                                            </td>
                                                            <td>Experience</td>
                                                            <td><span>Previous:</span> &nbsp;&nbsp;&nbsp;<span>Hetero:&nbsp;&nbsp;&nbsp;</span><span>Total:</span>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Department</td>
                                                            <td>
                                                                <input type="text" class="form-control" readonly name="Emp_Dept"/>
                                                            </td>
                                                            <td>Unit & Location</td>
                                                            <td>
                                                                <input type="text" class="form-control" readonly name="Emp_Unit"/>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>

                                            </div>
                                            <!-- Table Ends-->
                                            <div class="col-sm-12 text-center">
                                                <strong><small>According to you, which performance category he/she falls under:(Please select ) A:Very Good; B:Good;C:Average;D:Poor</small></strong>
                                            </div>
                                            <!-- <div class="table-responsive"> -->
                                            <div class="table-responsive1">
                                                <table class="table table-striped table-bordered">
                                                    <thead class="thead-default">
                                                        <tr>
                                                            <th>Category</th>
                                                            <th>Functional Qualities</th>
                                                            <th>Behavioral Qualities</th>
                                                            <th>Overall Rating</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>Category - A</td>
                                                            <td>
                                                            -Very sincere towards Job Responsibilities <br/>
                                                            -High quality of work
                                                            </td>
                                                            <td>-Very initiative</td>
                                                            <td>
                                                                <div class="checkbox">
                                                                    <label>
                                                                        <input type="radio" value="A" name="empCategory">
                                                                    </label>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Category - B</td>
                                                            <td>&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                            <td>
                                                                <div class="checkbox">
                                                                    <label>
                                                                        <input type="radio" value="B" name="empCategory">
                                                                    </label>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Category - C</td>
                                                            <td>&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                            <td>
                                                                <div class="checkbox">
                                                                    <label>
                                                                        <input type="radio" value="C" name="empCategory">
                                                                    </label>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Category - D</td>
                                                            <td>&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                            <td>
                                                                <div class="checkbox">
                                                                    <label>
                                                                        <input type="radio" value="D" name="empCategory">
                                                                    </label>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <!-- End Table-->
                                            
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                    <label class="control-label"><strong>HOD justification for 'EXCEPTIONAL' performers:</strong></label>
                                                    <div class="col-sm-12">
                                                        <textarea class="form-control" rows="5"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                    <label class="control-label"><strong>Any other comments:</strong></label>
                                                    <div class="col-sm-12">
                                                        <textarea class="form-control" rows="5"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-sm-12">
                                                <small>
													<strong>Please select the check boxes for areas of improvement
													</strong>
												</small>
                                            </div>
                                            <div class="col-sm-12">
                                                <div class="table-responsive">
                                                    <table class="table table-bordered table-striped">
                                                        <tr>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" value="Work Knowledge">Work Knowledge</label>
                                                            </td>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" value="Analytical Skills">Analytical Skills</label>
                                                            </td>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" value="Communication Skills">Communication Skills</label>
                                                            </td>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" value="Interpersonal Skills">Interpersonal Skills</label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" value="Team Work">Team Work</label>
                                                            </td>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" value="Attitude/Behaviour">Attitude/Behaviour</label>
                                                            </td>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" value="Interpersonal Skills">cGMP</label>
                                                            </td>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" value="Others">Others</label>
                                                            </td>
                                                        </tr>
														<tr>
															<td colspan="4"><strong>Training Requirements</strong></td>
														</tr>
														 <tr>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" value="Functional Training">Functional Training</label>
                                                            </td>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" value="Technical Training">Technical Training</label>
                                                            </td>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" value="Behavioral Training">Behavioral Training</label>
                                                            </td>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" value="Others">Others</label>
                                                            </td>
                                                        </tr>
                                                    </table>
													<div class="col-sm-12">
														
													</div>
                                                </div>
                                            </div>
											
                                            
											<div class="col-sm-12">
												<div class="col-sm-9">
													<span><strong>*</strong></span>
													<label><strong>Recommended for Probation period completion :</strong></label>
												</div>
												<div class="col-sm-3">
												<label class="radio-inline"><input type="radio" name="optradio">Yes</label>
											<label class="radio-inline"><input type="radio" name="optradio">No</label>
												</div>
											</div>

                                        </div>
                                        <!-- Panel Body-->
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
					
					<!-- New Content add End -->
					
				</section>
			</div>

			
		</section>
		
		
		
		<!-- Vendor -->
		
		<script src="assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
		<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
		<script src="assets/vendor/nanoscroller/nanoscroller.js"></script>
		<!-- <script src="assets/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
		 -->
		
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