<!doctype html>
<html class="fixed">

	<head>

 
<% 
  String TDSFLAG=(String)session.getAttribute("TDSFLAG");
  String EMP_NAME=(String)session.getAttribute("EMP_NAME");
  String  MGRFLAG=(String)session.getAttribute("Manage_Rights");
  String MGRFLAG_S="none";
  if(MGRFLAG!=null && Integer.parseInt(MGRFLAG)>0){
	  
	  MGRFLAG_S=" ";
  }
  
  if(EMP_NAME==null){
	  
	  response.sendRedirect("login.html");
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
		<!-- <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css"> -->

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
	<body  onpageshow="if (event.persisted) noBack();" onload="disableBackButton(); noBack();" >
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
							<div class="profile-info" data-lock-name="sairam" data-lock-email="">
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
										<i class="fa fa-users"></i>
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
										<i class="fa fa-money "></i>
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
					<div class="conatiner">
						<div class="row" style="padding-right:50px;">
							<div class="col-md-12">
								<div class="panel-group" id="accordion2">
									<div class="panel1 panel-accordion panel-accordion-primary">
										<div class="panel-heading1">
											<h4 class="panel-title1">
												<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapse2One">
													<i class="fa fa-briefcase"></i>&nbsp;&nbsp;&nbsp;Sr .Java Developer  &nbsp;&nbsp;&nbsp; Positions available - 2
												</a>
												<p class="pull-right">Status:Open &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!--  Openings:&nbsp;&nbsp;&nbsp;<span>1</span> --></p>
											<!-- 	<p class="pull-right">Status:Open &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Openings:&nbsp;&nbsp;&nbsp;<span>3</span></p> -->
											</h4>
										</div>
										<div id="collapse2One" class="accordion-body collapse in">
											<div class="panel-body">
												<div class="col-md-6">
													<div class="col-md-12">
														<h4 style="color:#000;">Job Description</h4>
													</div>	
													<div class="col-md-1">
													</div>
													<div class="col-md-11">
													<h5>Required skills:</h5>
													<ul>
													<li>Experience of 4 to 6 years.</li>
							<li>Design, develop, implement and maintain series of programs, subsystem, revision and enhancement of current system.</li>
							<li>Leading development team of at least 4 people and ability to take technology decisions.</li>
							<li>Must be a self-starter and able to work well with others.</li>
							<li>AJAX, JQuery knowledge is required.</li>
							<li>Database knowledge is required.</li>
							<li>Experience with web services is must.</li>
							<li>Strong Core Java, Servlets, JSPs and J2EE knowledge.</li>
							<li>JavaScript, HTML, CSS knowledge required.</li>
							<li>Experience integrating web services with mobile clients such as iPhone/iPad applications</li>
													</ul>
													<h5>Desired skills:</h5> 
													<ul>
													<li>Java framework knowledge such as Spring, Hibernate etc.</li>
													</ul>
													</div>
													<div class="col-md-1">
													</div>
													<div class="col-md-5">
														<h5>Qualification:</h5><p>Any Graduate or above.</p>
													</div>
													<div class="col-md-5">
														<h5>Experience:<h5> <p> 4 to 6 years</p>
													</div>
												</div>	
												<div class="col-md-2">
												</div>
												<div class="col-md-4">
													<h4>HR Details:</h4>
													<p>Contact Person: SATYA NARAYANA REDDY N</p>
													
													<a href="mailto:nsreddy@heterohealthcare.com?subject=Resume(Java  Developer)&body=For The Post Of Java Developer">
													<span><i class="fa fa-paper-plane" aria-hidden="true"></i> nsreddy@heterohealthcare.com</span>
													</a> 
													<!--  
													<button class="btn btn-primary" data-toggle="modal" data-target="#myModal" ><i class="fa fa-envelope"></i>&nbsp;&nbsp;Send Resume</button>
											      -->
											</div>
										</div>
									</div>
									<div class="panel1 panel-accordion panel-accordion-primary">
										<div class="panel-heading1">
											<h4 class="panel-title1">
												<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapse2Two">
													<i class="fa fa-briefcase"></i>&nbsp;&nbsp;&nbsp;Application Support Team: &nbsp;&nbsp;&nbsp;  Positions available – 2
												</a>
												<p class="pull-right">Status:Open &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!--  Openings:&nbsp;&nbsp;&nbsp;<span>1</span> --></p>
											</h4>
										</div>
										<div id="collapse2Two" class="accordion-body collapse">
											<div class="panel-body">
												<div class="col-md-6">
													<div class="col-md-12">
														<h4 style="color:#000;">Job Description</h4>
													</div>	
													<div class="col-md-1">
													</div>
													<div class="col-md-11">
													<h5>Required skills:</h5>
													<ul>
													<li>Experience of 0 to 2 years.</li>
													<li>Able to maintain and support existing application.</li>
													<li>Good Typing Skills.</li>
													<li>Need to work with teams and address their issues/queries.</li>
													</ul>
													<!-- <h5>Desired skills:</h5> 
													<ul>
													<li>Experience working in an Agile/Scrum development process</li>
													<li>Knowledge on Illustrator or other visual design and wire-framing tools</li>
													</ul> -->
													</div>
													<div class="col-md-1">
													</div>
													<div class="col-md-5">
														<h5>Qualification:</h5><p>Any Graduate or above.</p>
													</div>
													<div class="col-md-5">
														<h5>Experience:<h5> <p> 0 to 2 years</p>
													</div>
												</div>	
												<div class="col-md-2">
												</div>
												<div class="col-md-4">
													<h4>HR Details:</h4>
													<p>Contact Person: SATYA NARAYANA REDDY N</p>
													
													<a href="mailto:nsreddy@heterohealthcare.com?subject=Resume(Application Support Team)&body=For The Post Of Application Support Team">
													<span><i class="fa fa-paper-plane" aria-hidden="true"></i> nsreddy@heterohealthcare.com</span>
													</a> 
													
													<!-- <button class="btn btn-primary" data-toggle="modal" data-target="#myModal" ><i class="fa fa-envelope"></i>&nbsp;&nbsp;Send Resume</button> -->
												</div>
											</div>
										</div>
									</div>
									
									<div class="panel1 panel-accordion panel-accordion-primary">
										<div class="panel-heading1">
											<h4 class="panel-title1">
												<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapse2Three">
													<i class="fa fa-briefcase"></i>&nbsp;&nbsp;&nbsp; Sales Coordinator: : &nbsp;&nbsp;&nbsp;  Positions Available - 1
												</a>
												<p class="pull-right">Status:Open &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!--  Openings:&nbsp;&nbsp;&nbsp;<span>1</span> --></p>
											</h4>
										</div>
										<div id="collapse2Three" class="accordion-body collapse">
											<div class="panel-body">
												<div class="col-md-6">
													<div class="col-md-12">
														<h4 style="color:#000;">Job Description</h4>
													</div>	
													<div class="col-md-1">
													</div>
													<div class="col-md-11">
													<h5>Required skills:</h5>
													<ul>
													<li>MS Office (particularly MS Excel proficiency)</li>
													<li>Prepare Reports</li>
													<li>Decent English Communication</li>
													</ul>
													</div>
													<div class="col-md-1">
													</div>
													<div class="col-md-5">
														<h5>Qualification:</h5><p>Any Graduate or above.</p>
													</div>
													<div class="col-md-5">
														<h5>Experience:<h5> <p> 1 to 3 years</p>
													</div>
												</div>	
												<div class="col-md-2">
												</div>
												<div class="col-md-4">
													<h4>HR Details:</h4>
													<p>Contact Person: SATYA NARAYANA REDDY N</p>
													
													<a href="mailto:nsreddy@heterohealthcare.com?subject=Resume(Sales Coordinator)&body=For The Post Of Sales Coordinator">
													<span><i class="fa fa-paper-plane" aria-hidden="true"></i> nsreddy@heterohealthcare.com</span>
													</a> 
													
													<!-- <button class="btn btn-primary" data-toggle="modal" data-target="#myModal" ><i class="fa fa-envelope"></i>&nbsp;&nbsp;Send Resume</button> -->
												</div>
											</div>
										</div>
									</div>
			<!------------------------------------------------------>
			<div class="panel1 panel-accordion panel-accordion-primary">
										<div class="panel-heading1">
											<h4 class="panel-title1">
												<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapse2Four">
													<i class="fa fa-briefcase"></i>&nbsp;&nbsp;&nbsp;HORECA Sales Executives : &nbsp;&nbsp;&nbsp;  Positions Available - 2
												</a>
												<p class="pull-right">Status:Open &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!--  Openings:&nbsp;&nbsp;&nbsp;<span>1</span> --></p>
											</h4>
										</div>
										<div id="collapse2Four" class="accordion-body collapse">
											<div class="panel-body">
												<div class="col-md-6">
													<div class="col-md-12">
														<h4 style="color:#000;">Job Description</h4>
													</div>	
													<div class="col-md-1">
													</div>
													<div class="col-md-11">
													<h5>Required skills:</h5>
													<ul>
													<li>Prior Sales Experience in FMCG or HORECA (Hotels, Restaurants and Caterers)</li>
													<li>On Field Sales</li>
													</ul>
													</div>
													<div class="col-md-1">
													</div>
													<div class="col-md-5">
														<h5>Qualification:</h5><p>Any Graduate</p>
													</div>
													<div class="col-md-5">
														<h5>Experience:<h5> <p> 1 to 3 years</p>
													</div>
												</div>	
												<div class="col-md-2">
												</div>
												<div class="col-md-4">
													<h4>HR Details:</h4>
													<p>Contact Person: SATYA NARAYANA REDDY N</p>
													
													<a href="mailto:nsreddy@heterohealthcare.com?subject=Resume(HORECA Sales Executives)&body=For The Post Of HORECA Sales Executives">
													<span><i class="fa fa-paper-plane" aria-hidden="true"></i> nsreddy@heterohealthcare.com</span>
													</a> 
													
													<!-- <button class="btn btn-primary" data-toggle="modal" data-target="#myModal" ><i class="fa fa-envelope"></i>&nbsp;&nbsp;Send Resume</button> -->
												</div>
											</div>
										</div>
									</div>
	<!------------------------------------------------------>
			  <div class="panel1 panel-accordion panel-accordion-primary">
										<div class="panel-heading1">
											<h4 class="panel-title1">
												<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapse2Five">
													<i class="fa fa-briefcase"></i>&nbsp;&nbsp;&nbsp; Data Entry Operator : &nbsp;&nbsp;&nbsp;  Positions Available - 1
												</a>
												<p class="pull-right">Status:Open &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!--  Openings:&nbsp;&nbsp;&nbsp;<span>1</span> --></p>
											</h4>
										</div>
										<div id="collapse2Five" class="accordion-body collapse">
											<div class="panel-body">
												<div class="col-md-6">
													<div class="col-md-12">
														<h4 style="color:#000;">Job Description</h4>
													</div>	
													<div class="col-md-1">
													</div>
													<div class="col-md-11">
													<h5>Required skills:</h5>
													<ul>
													<li>MS Office (particularly MS Excel proficiency)</li>
													<li>Must know Telugu</li>
													<li>Good Typing Skills / 50-60 Words per minute</li>
													<li>Decent English Communication</li>
													<li>Decent English Communication</li>
													</ul>
													</div>
													<div class="col-md-1">
													</div>
													<div class="col-md-5">
														<h5>Qualification:</h5><p>Any Graduate</p>
													</div>
													<div class="col-md-5">
														<h5>Experience:<h5> <p> 0 to 2 years</p>
													</div>
												</div>	
												<div class="col-md-2">
												</div>
												<div class="col-md-4">
													<h4>HR Details:</h4>
													<p>Contact Person: SATYA NARAYANA REDDY N</p>
													
													<a href="mailto:nsreddy@heterohealthcare.com?subject=Resume(Data Entry Operator)&body=For The Post Of Data Entry Operator">
													<span><i class="fa fa-paper-plane" aria-hidden="true"></i> nsreddy@heterohealthcare.com</span>
													</a> 
													
													<!-- <button class="btn btn-primary" data-toggle="modal" data-target="#myModal" ><i class="fa fa-envelope"></i>&nbsp;&nbsp;Send Resume</button> -->
												</div>
											</div>
										</div>
									</div>
								<!-- 	<div class="panel1 panel-accordion panel-accordion-primary">
										<div class="panel-heading1">
											<h4 class="panel-title1">
												<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapse2Three">
													<i class="fa fa-briefcase"></i>&nbsp;&nbsp;.NET Developer
												</a>
												<p class="pull-right">Status:Closed &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Openings:&nbsp;&nbsp;&nbsp;<span>1</span></p>
											</h4>
										</div>
										<div id="collapse2Three" class="accordion-body collapse">
											<div class="panel-body">
												<div class="col-md-6">
													<div class="col-md-12">
														<h4 style="color:#000;">Job Description</h4>
													</div>	
													<div class="col-md-1">
													</div>
													<div class="col-md-11">
													<h5>Required skills:</h5>
													<ul>
													<li>Strong knowledge with C#.Net, Asp.Net Web Forms or MVC</li>
													<li>Experience with .NET Web Services, WCF, REST, MVC Database Development experience</li>
													<li>Must have experience in using JQuery, AJAX</li>
													<li>Should be excellent in OO programming, VB.Net/C# 3.5/ 4.0& design</li>
													<li>Should have worked on SQL Server 2005/2008/2012</li>
													<li>Should have idea or knowledge in Mobile Application development</li>
													<li>User Interface-HTLM5 & CSS3 &, VBA (Excel VBA & MS Access), VSS</li>
													<li>In depth knowledge on Database Designing, ERP Application Development, Strong Technical exposure and Practical knowledge on .NET MVC, OOPS, C#, SQL Server and JQuery. Should be an individual contributor as well as team player and aware of the entire SDLC (Software Development Life Cycle), should have developed ERP application from Scratch.</li>
													</ul>
													<h5>Desired skills:</h5> 
													<ul>
													<li>Exposure to Entity Framework/LINQ, Angular JS</li>
													</ul>
													</div>
													<div class="col-md-1">
													</div>
													<div class="col-md-5">
														<h5>Qualification:</h5><p>Any Graduate or above.</p>
													</div>
													<div class="col-md-5">
														<h5>Experience:<h5> <p> 3 to 4 years</p>
													</div>
												</div>	
												<div class="col-md-2">
												</div>
												<div class="col-md-4">
													<h4>HR Details:</h4>
													<p>Contact Person: SATYA NARAYANA REDDY N</p>
													<a href="mailto:nsreddy@heterohealthcare.com?subject=Resume(Java  Developer)& body=For The Post Of Java Developer" style='display:none'>
													Send Resume
													</a>
													
													<button class="btn btn-primary" data-toggle="modal" data-target="#myModal" ><i class="fa fa-envelope"></i>&nbsp;&nbsp;Send Resume</button>
												</div>
											</div>
											</div>
										</div> -->
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
		<script src="assets/javascripts/ui-elements/examples.modals.js"></script>
		<script src="assets/javascripts/pages/examples.calendar.js"></script>
		
		
	</body>
</html>