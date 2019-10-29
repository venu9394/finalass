<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <% 
  String Emp_Payslip=(String)session.getAttribute("PaySlip");
  
  String Emp_ForeCast=(String)session.getAttribute("ForeCast");
  
  
  String Emp_id=(String)session.getAttribute("EMP_ID");
  String TDSFLAG=(String)session.getAttribute("TDSFLAG");
  String EMP_NAME=(String)session.getAttribute("EMP_NAME");
  System.out.println("BioData at JSP::" +Emp_Payslip);
  System.out.println("Emp_BioData at JSP::" +Emp_ForeCast);
%>
    
    
<!doctype html>
<html class="fixed">
	<head>

		<!-- Basic -->
		<meta charset="UTF-8">

		
		<meta name="keywords" content="HETERO" />
		<meta name="description" content="Hetero">
		<meta name="author" content="Hetero">

         <title>Hetero Healthcare LTD</title>
          <link rel="icon" href="LOH.png" />
          
		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

		<!-- Web Fonts  -->
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

		<!-- Vendor CSS -->
		<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
		<link rel="stylesheet" href="assets/vendor/magnific-popup/magnific-popup.css" />
	
<link rel="stylesheet" href="assets/stylesheets/GScroll.css" />
		<link rel="shortcut icon" href="/images.png" type="image/x-icon" />

		<!-- Specific Page Vendor CSS -->
		<link rel="stylesheet" href="assets/vendor/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css" />
		

		<!-- Theme CSS -->
		<link rel="stylesheet" href="assets/stylesheets/theme.css" />

		<!-- Skin CSS -->
		<link rel="stylesheet" href="assets/stylesheets/skins/default.css" />
	
		<!-- Theme Custom CSS -->
		<link rel="stylesheet" href="assets/stylesheets/theme-custom.css">
			<!-- <script src="assets/vendor/jquery/jquery.js"></script> -->
			 <link rel="stylesheet" href="colorbox.css" />
	<script src="jquery.colorbox.js"></script> 
	
	
	
	<link rel="stylesheet" href="css/icon-font.min.css" type='text/css' />
<link rel="stylesheet" href="css/jqueryui.css" type='text/css' />
<link rel="stylesheet" href="jquery-ui-1.12.1.custom/jquery-ui.css">
   <script src="jquery-ui-1.12.1.custom/jquery-1.12.4.js"></script>
   <script src="jquery-ui-1.12.1.custom/jquery-ui.js"></script>
   
   
   
     <script src="MyAng.js"></script>
   
<!-- 	 <script src="assets/vendor/nanoscroller/nanoscroller.js"></script>  -->
<!-- 	<link rel="stylesheet" href="assets/vendor/nanoscroller/nanoscroller.css"> -->
		
	<!--  <script src="assets/javascripts/jquery-1.8.3.js"></script> 
	<script src="assets/javascripts/GScroll.js"></script> 
	
	<script type="text/javascript">
            $(document).ready(function(){
                $('#content').GScroll();
                $('#content3').GScroll({height: 100});
                $('<a href=""></a>')
            });
        </script>
		<script type="text/javascript">
            $(document).ready(function(){
                $('#content-1').GScroll();
                $('#content3').GScroll({height: 100});
                $('<a href=""></a>')
            });
        </script>
		<script type="text/javascript">
            $(document).ready(function(){
                $('#content-2').GScroll();
                $('#content3').GScroll({height: 100});
                $('<a href=""></a>')
            });
        </script>
		<script type="text/javascript">
            $(document).ready(function(){
                $('#content-3').GScroll();
                $('#content3').GScroll({height: 100});
                $('<a href=""></a>')
            });
        </script>
		<script type="text/javascript">
            $(document).ready(function(){
                $('#content-4').GScroll();
                $('#content3').GScroll({height: 100});
                $('<a href=""></a>')
            });
        </script>
		<script type="text/javascript">
            $(document).ready(function(){
                $('#content-5').GScroll();
                $('#content3').GScroll({height: 100});
                $('<a href=""></a>')
            });
        </script>
       <script type="text/javascript">
            $(document).ready(function(){
                $('#content-6').GScroll();
                $('#content3').GScroll({height: 100});
                $('<a href=""></a>')
            });
        </script> -->
        
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

<!-- <link href="scroll/perfect-scrollbar.css" rel="stylesheet">
      <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
      <script src="scroll/jquery.mousewheel.js"></script>
      <script src="scroll/perfect-scrollbar.js"></script> -->
      
      
 
 
	
	 
    
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
  
  </style>
	</head>
	
	<body onload="Bank_Type()">
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
									<a role="menuitem" tabindex="-1" href="data-toggle="modal" data-target="#myModal"><i class="fa fa-cogs"></i>Change Password</a>
									<a role="menuitem" tabindex="-1" data-toggle="modal" href="#changepassword" ><i class="fa fa-cogs"></i>Change Password</a>
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
										<span class="font-bold">Home</span>
									  </a>
									</li>
									
									<li>
									  <a href="PayslipDownload?Routing=UPDATE"  >
										<i class="fa fa-user"></i>
										<span class="font-bold">Profile Update</span>
									  </a>
									</li>
									
									
									<!-- <li>
									  <a href="PayslipDownload?Routing=APPROVAL"  >
										<i class="fa fa-user"></i>
										<span class="font-bold">HR approval</span>
									  </a>
									</li>
									 -->
									
							     <!-- Manager Approvals Start -->
							
									 
									
									
									
								</ul>
								
							</nav>
							
					</div>
				
							
<!-- <script type="text/javascript">
				$(function() {
  $( "#datepicker" ).datepicker({ firstDay: 1});
});
				</script>	 -->		
						</div>
				</aside>
				<!-- end: sidebar -->

				<section role="main" class="content-body resignation-body">
					<header class="page-header">
					<div class="clear col-md-7" style="color:red;"> <marquee> <%=session.getAttribute("HHCL_EVENT_INFO")%>  </marquee></div>
					
				  <div class="col-md-2 tool-tip-header">
				   <div class="weather">
						<a href="#" class="tooltip">
							<img src="images/ww.png" class="icon-img" />
							<span>
								<img class="callout" src="images/callout_black.gif" />
								<img src="images/css3-tooltip-image1.png" style="float:right;" />
								<strong>CSS only Tooltip</strong><br />
								Pure CSS popup tooltips with clean semantic XHTML.
							</span>
						</a>
				   </div>
				   <div class="quote">
						<a href="#" class="tooltip">
							<img src="images/hand.png" class="icon-img" />
							<span>
								<img class="callout" src="images/callout_black.gif" />
								<img src="images/css3-tooltip-image1.png" style="float:right;" />
								<strong>CSS only Tooltip</strong><br />
								Pure CSS popup tooltips with clean semantic XHTML.
							</span>
						</a>
				   </div>
				 
				 </div>
				 
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
					
									
					<!-- start: page -->
					<div id="description" class="wrapper" style="margin-top: 8%;">
					<div class="container">
						<div class="row " >
							<div class="col-md-10 middle-reg2">
								<div class="panel panel-danger" >
									<div class="panel-heading1">
										<span><b>Employee Profile Update</b> </span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								         <span id="Responce_Message" style="color:red;" >  </span> 
									</div>
									<div class="panel-body">
				                              
                                       <div class="col-md-5" style="margin-top:20px;">
                                       <!--  <span id="err_Message" style="color:red;" style="margin-top:20px;" >  sdf</span> -->
                                    
                                       <div class="form-group">
                                       
                                              
													<label class="col-md-2 control-label">EMP_ID </label>
													<div class="col-md-8"><%=Emp_id %>
														<input type="hidden" class="form-control " id="EmployeeId" name="EmployeeId"  value='<%=Emp_id %>' readonly >
													</div> 
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">Type </label>
													<div class="col-md-8" style="margin-left: 18px;">
																 <select class="form-control " id="type" name="type">
															<option value="Select">Select</option>
															<option value="EmployeeName">EMPLOYEE NAME</option>
															<option value="DateOfBirth">DATE OF BIRTH</option>
															<!-- <option value="PFNO">PF NUMBER</option>
															<option value="PFUAN">PF UAN NO</option> -->
															<option value="BANKDETAILS">BANK DETAILS</option>
															<option value="PerAddress">PERMANENT ADDRESS</option>
														    <!--<option value="Network">Network Issue</option>-->
															<option value="Others">Others</option>
													</select>									
													</div>
												</div>
                                       </div>
									 <div class="col-md-12" style="margin-top:20px;">
									 <div id="field_Name_text" style="display:none">
									 <!-- Dispaly Name -->
									 <div class="col-md-6">
	                                       <div class="form-group">
														<label class="col-md-3 control-label" style="margin-left: -13px;">First Name</label>
														<div class="col-md-7" style="margin-left: -7%;">
														 <input type="text" class="form-control " id="first_name" name="first_name"> 
															<span id="exist_first_name"></span>
														</div> 
													</div>
						                   </div>
										
									<div class="col-md-6">
	                                       <div class="form-group" style="margin-left: -153px;">
														<label class="col-md-3 control-label">Last Name </label>
														<div class="col-md-5" style="margin-left: -68px;">
														 <input type="text" class="form-control " id="Last_name" name="Last_name"> 
															<span id="exist_last_name"></span>
															
														</div> 
														<!-- <div id="button_show" style="display:none">	
									<div class="form-group">
										<button type="button"   type="button" id="button"  class="btn btn-primary align-right btn-up" style="margin-right: 35% !important;
margin-top: -6% !important;
margin-bottom: 3%;">Send</button>
									</div>
								 </div> -->
													</div>
													 
												 
	                                       
	                                       </div>
                                       
                                       </div>
                                       <!-- <form class="form-inline">
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" class="form-control" id="email" placeholder="Enter email">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password">
    </div>
    <div class="checkbox">
      <label><input type="checkbox"> Remember me</label>
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form> -->
                                       <!-- Dipaly Date -->
                                       
                                       <div id="Date_text" style="display:none" >
									 
									 
										
									<div class="col-md-6">
	                                       <div class="form-group">
														<label class="col-md-3 control-label" style="margin-left: -21px;">Date Of Join</label>
														<div class="col-md-6" style="margin-left: -20px;">
														  <input type="text" class="form-control " id="Date" name="Date" style="width: 114%;">
															<span id="exist_date"></span>
															
														</div> 
													</div>
													 
												 
	                                       
	                                       </div>
                                       
                                       </div>
                                       
                                       
                                       <!--TEXT BOX-->
                                       
                                       
                                        <div id="common_text" style="display:none" >
									 
									 <div class="col-md-4">
	                                       
												 
	                                       
	                                       </div>
										
									<div class="col-md-4">
	                                       <div class="form-group">
														<label class="col-md-4 control-label"><span id="textboxname"></span></label>
														<div class="col-md-8">
														    <input type="text" class="form-control " id="NUMBER" name="NUMBER">
															<span id="exist_number"></span>
															
														</div> 
													</div>
													 
												 
	                                       
	                                       </div>
                                       
                                       </div>
                                       
                                       
                                       <!--  -->
                                       
                                       
                                       
                                       <div id="dropdownlist" style="display:none">
									 
									
										
									<div class="col-md-12">
									<form class="form-inline">
	                                       <div class="form-group">
														<label class="col-md-4 control-label" style="margin-left: -14px;">Bank Type</label>
														<div class="col-md-8" style="margin-left: -22px;">
														     <select id="bank_type" name="bank_type"  class="form-control" >
		                                                      <option value="Select" >--Select One--</option>
		                                                       </select>
															<span id="exist_bank"></span>
														</div> 
													</div>
													
													<div class="form-group">
														<label class="col-md-4 control-label">A/C NO:</label>
														<div class="col-md-8" style="margin-left: -14%;">
														    <input type="text" class="form-control " id="ACNUMBER" name="ACNUMBER">
															<span id="exist_acnumber"></span>
															
														</div> 
													</div>
													 
											</form>
	                                       
	                                       </div>
                                       
                                       </div>
                                       
                                       
                                       
                                       
                                       
                                       
                                       <div id="Others_text" style="display:none">
									 
									 <div class="col-md-4">
	                                       
												 
	                                       
	                                       </div>
										
									<div class="col-md-12 form-inline">
	                                       <div class="form-group col-md-4">
														<label class="col-md-3 control-label" style="margin-left: -17px;">E-mail</label>
														<div class="col-md-8">
														      <input type="email" class="form-control " id="Email_id" name="Email_id" style="width: 143%;">
														</div> 
													</div>
													
													<div class="form-group col-md-4" id="Issue_show" >
														<label class="col-md-5 control-label" style="margin-left: -24px;">Issue type</label>
														<div class="col-md-8">
														    <select id="Issuetype" name="Issuetype" style="margin-left: -38px;">
														    <option value="Select">Select</option>
														    <option value="HardWare&Repalcement">HardWare&Repalcement</option>
														    <option value="System StarUp Issuse">System StarUp Issuse</option>
														    <option value="Power Related Issuse">Power Related Issuse</option>
														    <option value="Software Issues/Update">Software Issues/Update</option>
														    <option value="E-mail Issuse">E-mail Issuse</option>
														    <option value="Others">Others</option>
														    </select>
														</div> 
													</div>
													 
												 <div class="form-group col-md-4">
														<label class="col-md-3 control-label" style="margin-left: -34px">Reason</label>
														<div class="col-md-8">
														
														<textarea class="form-control" rows="5"  name="request_msg" id="request_msg"  maxlength="300" placeholder="" style="height: 80px;
margin-left: -10px;"></textarea>
														     
															
														</div> 
													</div>
	                                       
	                                       </div>
                                       
                                       </div>
                                       
                                       
                                       
                                       
                                       
                                       <!-- Dipaly Addresss -->
                                       <div id="Addres_text" style="display:none">
                                       
                                      
                                       <div class="col-md-4">
	                                       <div class="form-group">
														<label class="col-md-4 control-label" style="margin-left:-14px;">Address1</label>
														<div class="col-md-8" style="margin-left: -12px;">
															<textarea class="form-control" rows="5"  id="address1" placeholder="" style="width: 137% ; height:80px;"></textarea>
															<span id="exist_address1">   </span>
														</div> 
														
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label" >EMAIL</label>
														<div class="col-md-9" style="margin-left: -1px;">
																	  <input type='text'  class="form-control" id="email" style="width: 119%;">
		                                                        <span id="exist_email"> </span>
														</div>
													</div>
													
													<div class="clearfix"></div>
	                                       
	                                       
	                                       
	                                       </div>
										
									<div class="col-md-4">
	                                       <div class="form-group">
														<label class="col-md-4 control-label">Address2</label>
														<div class="col-md-8" style="margin-left: -22px;">
															<textarea class="form-control" rows="5"   id="address2" placeholder="" style="width: 137% ; height:80px;"></textarea>
															<span id="exist_address2"></span>
															
														</div> 
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">MOBILE</label>
														<div class="col-md-9">
																	  <input type='text'  class="form-control" id="mobile" style="width: 119%;">
		                                                           <span id="exist_mobile"></span>
														</div>
													</div>
													
													<div class="clearfix"></div>
	                                       
	                                       
	                                       
	                                       </div>
										
									<div class="col-md-4">
	                                       <div class="form-group">
														<label class="col-md-3 control-label">STATE</label>
														<div class="col-md-9">
																  <select id="state" class="form-control"  onchange="StateChange(this.value)"><option value="Select"  >--Select One--</option> </select>
		                                                     <span id="exist_state"> </span>
															
														</div> 
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">CITY </label>
														<div class="col-md-9">
																	 <select id="city" class="form-control"><option value="Select"  >--Select One--</option></select>
		                                                     <span id="exist_city"> </span>
														</div>
													</div>
													
													 
	                                       
	                                       
	                                       
	                                       </div>
	                                       
	                                       </div>
										
									
									 <div id="button_show" style="display:none">	
									<div class="form-group">
										<button type="button"   type="button" id="button"  class="btn btn-primary align-right btn-up" style="margin-right: 45% !important;
    margin-top: 3% !important;"  >Send</button>
									</div>
								 </div>
								</div>
								
							</div>
							
						</div>	
						
									
					</div>
					</div>
					<!-- end: page -->
				

			

			
		
					
					<!-- end: page -->
					<!--Change Password Modal-->
					<div class="modal fade" id="changepassword" tabindex="-1" role="dialog" aria-labelledby="myModalLabel-pwd" aria-hidden="true">
					<div class="vertical-alignment-helper">
					  <div class="modal-dialog vertical-align-center" role="document">
						<div class="modal-content ">
						  <div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							  <span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel-pwd">Change Password</h4>
						  </div>
							<div class="modal-body">
								<form action="" method="post" enctype="multipart/form-data">
									<div class="form-group ">
											<label class="col-sm-5 control-label">Current Password</label>
											<div class="col-sm-7">
												<input type="password" class="form-control" name="currentpassword" id="currentpassword" placeholder="Current Password">
											</div>
										</div>
										<div class="form-group ">
											<label class="col-sm-5 control-label">New Password</label>
											<div class="col-sm-7">
												<input type="password" class="form-control"  name="newpassword" id="newpassword" placeholder="New Password" onblur="validatePassword('N');">
												
											</div>
										</div>
										<div class="form-group ">
											<label class="col-sm-5 control-label">Confirm Password</label>
											<div class="col-sm-7">
												<input type="password" class="form-control" name="confirmpassword" id="confirmpassword" onblur="validatePassword('N');" placeholder="Confirm Password">
											</div>
										</div>
										<br>
										<span style="color:red;">Instructions:</span>Password should be minimum 6 & maximum 15 Characters with <br>minimum one letter [a-z(OR)A-Z] ,minimum one special Character [!,@,#,$,&] and Number[0-9].
										<span style="color:#08c;">Example:Hetero@123</span>	
										
																		
								</form>
							</div>
							<div class="modal-footer">
							<!-- <span id="Responce_Message" style="color:red;" class="align-left" >  </span> -->
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>&nbsp;
							<button type="button" id="Responce_Message_btn" class="btn btn-primary" onclick="validatePassword('Y')" >Save</button>
						  </div>
						</div>
					  </div>
					  </div>
					</div>
					
				</section>
			</div>
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
	<!--	<script>
			$(document).ready(function(){
                 setTimeout(function() {
					$(".group1").colorbox({rel:'group1' ,open:true});  

                ///$.fn.colorbox({href:"img/image002.png", open:true});
				}, 1500);
				//Examples of how to assign the Colorbox event to elements
				//$(".group1").colorbox({rel:'group1'});
				//$.fn.colorbox({href:"../content/01.jpg", open:true});
			});
		</script>
		<div style="display:none;">
		<h1>Colorbox Demonstration</h1>
		<h2>Elastic Transition</h2>
		<p><a class="group1" href="assets/images/01.jpg" title="Drawing & Painting Competition">Grouped Photo 1</a></p>
		<p><a class="group1" href="assets/images/01.jpg" title="Drawing & Painting Competition">Grouped Photo 2</a></p>
		<p><a class="group1" href="assets/images/01.jpg" title="Drawing & Painting Competition">Grouped Photo 3</a></p>
		<p><a class="group1" href="assets/images/01.jpg" title="Drawing & Painting Competition">Grouped Photo 4</a></p>
		</div>-->
	<!-- end popup ad here -->
<!-- Change Password -->

<script type="text/javascript">
$("#Date").datepicker({
	dateFormat : 'yy-mm-dd',
	changeMonth: true,
    changeYear: true,
    yearRange: '1950:2050'

});


</script>

<script src="Test.js"></script>
 
 	</body>
</html>