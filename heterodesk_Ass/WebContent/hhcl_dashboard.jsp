<!doctype html>
<html class="fixed">
	<head>
<% 
  String BioData=(String)request.getAttribute("BioData");
  
  //String Emp_BioData=(String)request.getAttribute("DOJ_DOB");
  String Emp_BioData=(String)request.getAttribute("EM_DOJ");
  String EmpDOB=(String)request.getAttribute("EM_DOB");
  
  String HOLIDAYS_PG=(String)request.getAttribute("HOLIDAYS");

  String TDSFLAG=(String)session.getAttribute("TDSFLAG");
  
  String  MGRFLAG=(String)session.getAttribute("Manage_Rights");
  
  String TDS_FORECAST=(String)session.getAttribute("TDS_FORECAST");
  String apprisalPwd=(String)session.getAttribute("apprisalPwd");
  String username_apprisal=(String)session.getAttribute("EMP_ID");
  String apprisalPwdBut="none";
  if(apprisalPwd!=null){
	  
	  apprisalPwdBut="";
	  
  }
  String EmpBuid=session.getAttribute("EMPBUID").toString();
  String  FlexiPolicy="Attendance2?Routing=ATTENDANCE_BIO";
  String  FlexiPolicy_flag=(String)session.getAttribute("FlexiPolicy");
          
  if(FlexiPolicy_flag!=null && FlexiPolicy_flag.equalsIgnoreCase("Y") && !EmpBuid.equalsIgnoreCase("16") && !EmpBuid.equalsIgnoreCase("15") ){
        	 FlexiPolicy="Attendance_flexi2?Routing=ATTENDANCE_BIO&ATT_FLAG=MONTHS&Month=currmonth&PPWise=true";
  }else{
	  FlexiPolicy="Attendance_assam2?Routing=ATTENDANCE_BIO&ATT_FLAG=DATES&Month=currmonth" ;
         }
  System.out.println(FlexiPolicy_flag +"~Main Page::"+FlexiPolicy);
  
  String  CostCenter=(String)session.getAttribute("Cost_Center");
  
  
  
  
  int  totalusers=15;
  int  currentusers=12;
  try{
    totalusers=Integer.parseInt(session.getAttribute("totalusers").toString());
    currentusers=Integer.parseInt(session.getAttribute("currentusers").toString());
  }catch(Exception ERRR){
	  
	  System.out.println("ERRR"+ ERRR);
	  
  }
  
  String MGRFLAG_S="none";
  if(MGRFLAG!=null && Integer.parseInt(MGRFLAG)>0){
	  
	  MGRFLAG_S=" ";
  }
  
  
  String EMP_NAME=(String)session.getAttribute("EMP_NAME");
  
     if(EMP_NAME==null){
    	 
    	 response.sendRedirect("/login.html");
    	 
     }
  
  String Notice_board=(String)session.getAttribute("Notice");
  
  
  String  HR_HRMS=(String)session.getAttribute("HR_HRMS");
    if(HR_HRMS!=null && HR_HRMS.equalsIgnoreCase("YES")){
    	HR_HRMS=" ";
    }else{
    	HR_HRMS="none";
    }
    
    
    String  AC_ITS=(String)session.getAttribute("AC_ITS");
    if(AC_ITS!=null && AC_ITS.equalsIgnoreCase("YES")){
    	AC_ITS=" ";
    }else{
    	AC_ITS="none";
    }

   System.out.println("BioData at JSP::" +BioData);
 
   System.out.println("Emp_BioData at JSP::" +Emp_BioData);
%>
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
      
      
<script src="MyAng.js"></script>

<script>
var app = angular.module('myApp', []);
app.controller('formCtrl', function($scope) {
	var Data="";
	$scope.Data_2="";
	$scope.Data_1="";
	$scope.Data_3="";
	$scope.CostCenters="";
	try{
		Data=<%=BioData%>;
    $scope.Data_1=<%=Emp_BioData%>;
    $scope.Data_2=<%=EmpDOB%>;
    $scope.Data_3=<%=HOLIDAYS_PG%>;
    $scope.CostCenters=<%=CostCenter%>;
  
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
    
    $scope.empid = Data.EMPLOYEESEQUENCENO;
    $scope.empname= Data.CALLNAME;
    $scope.emp_doj= Data.DATEOFJOIN;
    $scope.emp_Dep= Data.DEPARTMENT;
    $scope.emp_Des= Data.DESIGNATION;
    $scope.emp_job_type= Data.TYPE;
   
    
   // $scope.HOLIDAYS = Data_3.EVENTDATE;
    
   // alert("$scope.HOLIDAYS::"+$scope.HOLIDAYS);
    
 });
 
 
</script>

 
	
	<script>
      function openColorBox(data){
    	  
    	     //alert(data);HR HR_NOTICE
    	  if(data=="NewJoinee"){
    		  $.colorbox({iframe:true, width:"80%", height:"80%", href:"NewJoinee.html"});
    	  }else if(data=="BIRTHDAYS") {
              $.colorbox({iframe:true, width:"80%", height:"80%", href: "hhcl_birthdays.html"});
    	  }else if(data=="HOLIDAYS") {
               $.colorbox({iframe:true, width:"80%", height:"80%", href: "HHCL_HOLIDAYS.html"});
    	  }else if(data=="HR") {
    		  
    		  $.colorbox({iframe:true, width:"80%", height:"80%", href:"HHCL_HR_LEAVE_POLICY_New.html"});
              //$.colorbox({iframe:true, width:"80%", height:"80%", href:"HHCL_HR_LEAVE_POLICY.html"});
              
              
    	  }else if(data=="HR_NOTICE") {
              $.colorbox({iframe:true, width:"80%", height:"80%", href:"HHCL_HR_NOTICE_INFO.html"});
    	  }
    	  else if(data=="APPLYLEAVE") {
    		  
             // $.colorbox({iframe:true, width:"95%", height:"85%", href:"leave_l.jsp"});
    		  $.colorbox({iframe:true, width:"95%", height:"85%", href:"leave_l_temp.html"});
              
              
    	  }else if(data=="FIELD_STAFF_ATT") {
              $.colorbox({iframe:true, width:"80%", height:"80%", href:"FIELD_STAF_DATA.html"});
    	  }
    	  else if(data=="notice"){
    		  $.colorbox({iframe:true, width:"80%", height:"80%", href: "Notice_Board.html"});
    		 // $.colorbox({iframe:true, width:"80%", height:"80%", href: "newpage.html"});
    	  }else if(data=="MANNUAL_ATT"){
    		  $.colorbox({iframe:true, width:"80%", height:"80%", href: "Under_con.html"});
     		 // $.colorbox({iframe:true, width:"80%", height:"80%", href: "newpage.html"});
     	  }else if(data=="LEAVE_SUMMARY"){
    		  $.colorbox({iframe:true, width:"80%", height:"80%", href: "HHCL_LEAVE_SUMMARY.html"});
     		 // $.colorbox({iframe:true, width:"80%", height:"80%", href: "newpage.html"});
     	  }else if(data=="LEAVE_BALANCE"){
     		 // alert("LEAVE_BALANCE");
   		  $.colorbox({iframe:true, width:"80%", height:"80%", href: "HHCL_LEAVE_BALANCE.jsp"});
    		 // $.colorbox({iframe:true, width:"80%", height:"80%", href: "newpage.html"});
    	  }
    	     
    	     
   	//{iframe:true, width:"80%", height:"80%", href: "http://www.sitepoint.com"}
      }
      
      
      function openColorBox_Event(){
    	  
    	  var Notice_Data="<%=Notice_board%>";
    	  
    	  if(Notice_Data=="Y" ){
    	     //openColorBox("notice")
    	   }
    	  
      }
      //$.colorbox({iframe:true, width:"80%", height:"80%", href: "PieChart.html"});
      setTimeout(openColorBox_Event, 2000);
	  
	  window.onkeydown = function(e){
	  
	  
    if(e.keyCode === 27){ // Key code for ESC key
        
		//alert(1);
		e.preventDefault();
    }
		
};
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
  


  
  
  
  </style>
	</head>
	
	<body  onpageshow="if (event.persisted) noBack();"  ng-app="myApp" ng-controller="formCtrl" ">
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
								<span class="name">{{empname}}</span>
								
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
									
									
									<!-- <li>
									  <a href="PayslipDownload?Routing=MyProfile"  >
										<i class="fa fa-user"></i>
										<span class="font-bold">HR_APPROVE</span>
									  </a>
									</li>
									 -->
									
									
									<li>
									  <a href="hhcl_careers.jsp">
										<i class="fa fa-briefcase"></i>
										<span class="font-bold">Careers</span>
									  </a>
									</li>
									
									<li>
									  <a href="AttendancereqStatus.jsp">
										<i class="fa fa-briefcase"></i>
										<span class="font-bold">AttendancereqStatus</span>
									  </a>
									</li>
							     <!-- Manager Approvals Start -->
							
								<%--    <li style="display:<%=MGRFLAG_S%>;"> --%>
								 <li style="display:block;">
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
									  <a href="PayslipDownload?Routing=REGION">
										<i class="fa fa-sign-out"></i>
										<span class="font-bold">Resignation</span>
									  </a>
									</li> 
									
									
									 <li>
									  <a href="PayslipDownload?Routing=Accounts" style="display:<%=AC_ITS%>;"  >
										<i class="fa fa-inr"></i>
										<span class="font-bold">Accounts</span>
									  </a>
									 </li> 
									 
									 
									 <li>
									  <a href="PayslipDownload?Routing=UPDATE" style="display:<%=HR_HRMS%>;"  >
										<i class="fa fa-user"></i>
										<span class="font-bold">HR/HRMS</span>
									  </a>
									 </li> 
									 
									  
									 
									<!-- Manager Approvals End -->
								<%-- 	<li style="display:<%=TDSFLAG%>;">
									  <a href="http://mydesk.heterohcl.com/IT/" target="_blank">
										<i class="fa fa-money "></i>
										<span class="font-bold">TDS Declaration</span>
									  </a>
									
									</li> --%>
									
						<%-- 			<li style="display:<%=TDS_FORECAST%>;" ><a
									href="PayslipDownload?Routing=TDS&EMP=EMP" target="_parent">
										<i class="fa fa-cogs"></i> <span class="font-bold">Forecast Generation</span>
								    </a></li>
								
								
 --%>								<li style="display:<%=TDS_FORECAST%>;"  >
 
                                <a style="display:<%=apprisalPwdBut%>;" href="apprasal.jsp" target="_blank"> <i class="glyphicon glyphicon-file">
                                </i> <span class="font-bold">Performance appraisal</span>
								 </a>
                                 <%--  <a style="display:<%=apprisalPwdBut%>;"
									href="http://services.heterohcl.com/php/appraisal/authenticate?username=<%=username_apprisal%>&password=<%=apprisalPwd%>&submit=Login" target="_blank">
										<i class="glyphicon glyphicon-file"></i> <span class="font-bold">Performance appraisal</span>
								 </a> --%>
								
								 </li>
								    
								
									<%-- <li style="display:<%=TDSFLAG%>;">
									  <a href="tds/Hhcl_EmployeeUpdate_by_hr.jsp" >
										<i class="fa fa-money "></i>
										<span class="font-bold">TDS forcast Info</span>
									  </a>
									</li> --%>
									
							
									
									<li>
									  <a href="http://www.heterohealthcare.com/" target="_blank">
										<i class="fa fa-info-circle"></i>
										<span class="font-bold">About Our Organization</span>
									  </a>
									</li>
									 
								
										
									
									
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

				<section role="main" class="content-body">
					<header class="page-header">
					<div class="clear col-md-7" style="color:red;"> <marquee> <%=session.getAttribute("HHCL_EVENT_INFO")%>  </marquee></div>
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
					<div class="col-md-2 user">
					  
					  <ul class="user-img">
					  <li style="list-style-type:none;"> <a href="#" ><img src="assets/images/user1.png" alt="user"> :&nbsp;<a href="#"><span    class="red-tooltip"  data-toggle="tooltip" data-placement="top" title='To day Logins:<%=session.getAttribute("LoginAccess")%> ' ></a><%=session.getAttribute("LoginAccess")%>   </span></a>  </li>
					  <!-- <li><span> <p>1238945456</p> </span></li> -->
					  
					  </ul>
					
					  
					</div>
	
					
				</header>
					<!-- start: page -->
					<div class="row">
							<div class="col-md-4">
									<section class="panel panel-primary">
										<div class="panel-heading1">
										  <i class="fa fa-user"></i>
										  Profile      
										<span style="padding-left:100px;"><a data-toggle="modal" href="#changepassword"  Onclick="RefreshCh();" class="btn-link1"><i class="fa fa-lock "></i>&nbsp;&nbsp;<strong>Change Password</strong></a></span>
										</div>	
										<div class="myscroll">
												<section class="panel-body">
													<article class="media">
															<div id="content">
																<table class="table table-bordered">
																	<tr>
																		<td><b>ID:</b></td>
																		<td>{{empid}}</td>
																	</tr>
																	<tr>
																		<td><b>Name:</b></td>
																		<td>{{empname}}</td>
																	</tr>
																	<tr>
																		<td><b>Department</b></td>
																		<td>{{emp_Dep}}</td>
																	</tr>
																	<tr>
																		<td><b>Designation</b></td>
																		<td>{{emp_Des}}</td>
																	</tr>
																	<tr>
																		<td><b>Date of Joining:</b></td>
																		<td>{{emp_doj}}</td>
																	</tr>
																	<tr>
																		<td><b>Status:</b></td>
																		<td>{{emp_job_type}}</td>
																	</tr>
																	
																</table>
																
															</div> 
													</article>
												 
												</section>
											</div>	
									</section>
									 
								</div>
								<div class="col-md-4">
									  <section class="panel panel-primary portlet-item">
									  
									  
										 <header class="panel-heading1">
											  <i class="fa fa-bookmark"></i>
											  HR Policies                 
											</header>
											<div class="myscroll">
												<div class="panel-body1">
													<article class="media">
														<div id="content-1">
															<table class="table table-bordered">
																
																<!-- <tr>
																	<td>Leave Policy  </td>
																	<td><a href="javascript:onclick=openColorBox('HR');"><i class="fa fa-file-pdf-o" aria-hidden="true"></i></a></td>
																</tr> -->
																
																 <tr>
																	<td>Leave Policy (Location Hyderabad) </td>
																	<td><a href="javascript:onclick=openColorBox('HR');"><i class="fa fa-file-pdf-o" aria-hidden="true"></i></a></td>
																</tr>
																
																<!-- <tr>
																	<td>HR NOTICE <sup style='color:red'>* new</sup> </td>
																	<td><a href="javascript:onclick=openColorBox('HR_NOTICE');"><i class="glyphicon glyphicon-bullhorn" aria-hidden="true"></i></a>NA</td>
																</tr> -->
																
															</table>
														</div>
														
													</article>
												</div>
											</div>	
									  </section>
							</div>
							<div class="col-md-4">
								<section class="panel panel-primary portlet-item">
								<div class="panel-heading1">
										  <i class="fa fa-bullhorn"></i>
										 New Joinees      
										<span style="padding-left:180px;"><a href="javascript:onclick=openColorBox('NewJoinee');" class="btn-link1"><i class="fa fa-external-link fa-lg" id="clickableAwesomeFont"></i></a></span>
										</div>	
									 
									<!-- <div class="panel-heading1">
										<i class="fa fa-bullhorn">
										New Joinees    <span style="padding-left:210px;"><a href="javascript:onclick=openColorBox('NewJoinee');" class="btn-link1"><i class="fa fa-external-link fa-lg" id="clickableAwesomeFont"></i></a></span>              
									
									  </i>
									</div> -->
								
									
									<div class="myscroll">
										<section class="panel-body1">
											<div id="content-2">
											<table class="table table-striped text-center" style='padding-left:10px' >
														<thead class="thead-default">
															<tr class="employ-td"><th>Employee Name</th> <th>Department</th></tr>
								            			</thead>
								            </table>	
								                   
												<marquee behavior="scroll" direction="up" width="100%" scrollamount="1" style="padding-left:10px; overflow:hidden;">
												
													<table class="table table-striped">
															<tr ng-repeat="(key, value) in Data_1" ><td style="border-top:0" >{{ key }}</td> <td style="border-top:0">{{ value }}</td></tr>
								                   </table>	
												</marquee>
												
											</div>
										</section>
									</div>	
								</section>									
							</div>		
					</div>
					<div class="row">	
							<div class="col-md-4">
								<section class="panel panel-primary portlet-item">
									<header class="panel-heading1">
									
									   <i class="fa fa-cogs "></i>
										Leave & Attendance Monitoring 				   
										<!-- <span style="padding-left:40px;"  id="clickableAwesomeFont"><a href="hhcl_leave.html" class="btn-link1"><i class="fa fa-plus-circle fa-lg" id="clickableAwesomeFont"></i></a></span>-->
										
									</header>
									<div class="myscroll">
										<section class="panel-body1">
											<article class="media">
												<div id="content-3">
											
										<table class="table table-striped">
										
										<!-- <tr><td style="border-top:0" >Approved Leaves</td> <td style="border-top:0">04</td></tr>
										<tr><td style="border-top:0" >Available Leaves</td> <td style="border-top:0">10</td></tr> -->
										<tr><td style="border-top:0"  > <span style="display:{{CostCenters.CostCenter}}"> Apply For Leave </span></td> <td style="border-top:0"><span style="display:{{CostCenters.CostCenter}}"  id="clickableAwesomeFont" ><a href="javascript:onclick=openColorBox('APPLYLEAVE');"  class="btn-link1"><i class="fa fa-envelope-square fa-2x" id="clickableAwesomeFont" style="color:#08c;"></i></a></span> <span style="display:{{CostCenters.Note}}">Note:For Field Employes Leave and Attendance info can access through SFA </span></td></tr>
										<tr><td style="border-top:0" ><span style="display:{{CostCenters.CostCenter}}">Leaves Summary Report </span> </td> <td style="border-top:0"><span  style="display:{{CostCenters.CostCenter}}" id="clickableAwesomeFont" ><a href="javascript:onclick=openColorBox('LEAVE_SUMMARY');" class="btn-link1"><i class="glyphicon glyphicon-print fa-2x" id="clickableAwesomeFont" style="color:#08c;"></i></a></span></td></tr>	
								<tr><td style="border-top:0" ><span style="display:{{CostCenters.CostCenter}}">Leave Balance </span> </td> <td style="border-top:0"><span  style="display:{{CostCenters.CostCenter}}" id="clickableAwesomeFont" ><a href="javascript:onclick=openColorBox('LEAVE_BALANCE');" class="btn-link1"><i  id="clickableAwesomeFont" style="color:#08c;" align='right'><img src="balance.png" width="24px" height="24px"></i></a></span></td></tr>				
										<tr><td style="border-top:0" ><span style="display:{{CostCenters.CostCenter}}">Bio/Swipe Attendance Report </span></td> <td style="border-top:0" ><span style="display:{{CostCenters.CostCenter}}"  id="clickableAwesomeFont" ><a href="<%=FlexiPolicy %>" class="btn-link1"><i class="fa fa-clock-o fa-2x fa-spin" id="clickableAwesomeFont" style="color:#08c;"></i></a></span></td></tr>
									<!-- 	<tr><td style="border-top:0" >Employee Manual  Attendance</td> <td style="border-top:0"><span  id="clickableAwesomeFont" ><a href="javascript:onclick=openColorBox('MANNUAL_ATT');" class="btn-link1"><i  class="fa fa-pencil-square-o fa-2x" id="clickableAwesomeFont" style="color:#08c;"></i></a></span></td></tr>
										<tr><td style="border-top:0" >Field Staff Attendance Reports</td> <td style="border-top:0"><span  id="clickableAwesomeFont" ><a href="javascript:onclick=openColorBox('FIELD_STAFF_ATT');"  class="btn-link1"><i class="glyphicon glyphicon-print fa-2x" id="clickableAwesomeFont" style="color:#08c;"></i></a></span></td></tr> -->
										
								        </table>	
											
											</div>		
										  </article>
										  
										</section>
									</div>  
								</section>
							</div>
							<div class="col-md-4">
								<section class="panel panel-primary portlet-item">
									<header class="panel-heading1">
									<i class="fa fa-calendar"></i>
									 &nbsp;&nbsp;&nbsp;Holidays
									  <span style="padding-left:180px;"><a href="javascript:onclick=openColorBox('HOLIDAYS');" class="btn-link1"><i class="fa fa-external-link fa-lg" id="clickableAwesomeFont"></i></a></span>
									</header>
									<div class="myscroll">
										<section class="panel-body1">
										  <article class="media">
											<div id="content-4">
											
												<table class="table table-striped table-bordered" >
										           <tr ng-repeat="x in Data_3" ><td style=""> {{x.EVENTDATE}} </td>  <td>{{x.EVENT}}</td> <!--  <td >{{x.STATUS}}</td> --> </tr>
								                   </table>	
								                   
												<!--  <marquee behavior="scroll" direction="up" width="100%" scrollamount="2" style="padding-left:25px; overflow:hidden; ">
													
												</marquee>--->
											</div>	
										  </article>
										</section>
									</div>	
								</section>
							</div>
							<div class="col-md-4">
								<section class="panel panel-primary portlet-item">
									<header class="panel-heading1">
									<i class="fa fa-birthday-cake"></i>
									 &nbsp;&nbsp;&nbsp;Today Birthday's
									  <span style="padding-left:150px;"><a href="javascript:onclick=openColorBox('BIRTHDAYS');" class="btn-link1"><i class="fa fa-external-link fa-lg" id="clickableAwesomeFont"></i></a></span>
									</header>
									<div class="myscroll">
										<section class="panel-body1">
										  <article class="media">
											<div id="content-5">
																						
											<table class="table table-striped text-center" style='padding-left:10px' >
															<thead class="thead-default">
															<tr  ><th style="">Employee Name</th> <th> Department</th></tr>
								          					</thead>
								            </table>
								            
								            
												<marquee behavior="scroll" direction="up" width="100%" scrollamount="2" style="padding-left:5px; overflow:hidden; ">
													
													
								                   
								                   <table class="table table-striped">
											      <tr ng-repeat="(key, value) in Data_2" ><td style="border-top:0">{{ key }}</td> <td style="border-top:0">{{ value }}</td></tr>
								                  </table>
													
													<!--  <div class="col-sm-12"  ng-repeat="x in Data_2" >{{x}}</div> -->
												</marquee>
											</div>	
										  </article>
										</section>
									</div>	
								</section>
							</div>
							
										  </article>
										</section>
									</div>	
								</section>
							</div>
							
					</div>
					<section>
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
							<span id="Responce_Message" style="color:red;" class="align-left" >  </span>
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
		<script src="assets/vendor/jquery/jquery.js"></script>
		<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
		<!-- Specific Page Vendor -->
		<script src="assets/vendor/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>
		<!-- Theme Base, Components and Settings -->
		<!-- <script src="assets/javascripts/theme.js"></script> -->
		<!-- Theme Custom -->
		<script src="assets/javascripts/theme.custom.js"></script>
		<!-- Theme Initialization Files -->
		<script src="assets/javascripts/theme.init.js"></script>
		<!-- Examples -->
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
 <script>
          function validatePassword(val) {
				var newPassword = document.getElementById('newpassword').value;
				var ConformPassword = document.getElementById('confirmpassword').value;
				var currentpassword=document.getElementById('currentpassword').value;
				if(currentpassword.length<=0){
					document.getElementById("Responce_Message").innerHTML="Please enter current password";
					return false;
				}if(newPassword.length<=0){
					document.getElementById("Responce_Message").innerHTML="Please enter new and confirm password";
					return false;
				}
				
				var regularExpression  = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\w\s]).{6,15}$/;
				var FGGG= /^(?=.*[0-9])(?=.*[!@#$&])[a-zA-Z0-9!@#$&]{6,15}$/;
		
				if(newPassword.match(FGGG))   
				{   
					document.getElementById("Responce_Message").innerHTML="";
				  //return true;  
				}  
				else  
				{   
					document.getElementById("Responce_Message").innerHTML="Invalid Password Please Fallow The given instructions"; 
				    return false;  
				}  
				
				if( newPassword!=ConformPassword){
					document.getElementById("Responce_Message").innerHTML="New Password and Conform password should be same";
					return false;
					
				}if(val=="Y" && newPassword.match(FGGG) && newPassword==ConformPassword && currentpassword.length>0){
					
					document.getElementById("Responce_Message").innerHTML="";
					
					Change_Password(currentpassword,newPassword,ConformPassword);
					///alert("Finaly Success Password");
				}
			}
          
function Change_Password(currentpassword,newPassword,ConformPassword){
     		var formData = {Routing:"changePwd",currentpassword:""+currentpassword+"",newPassword:""+newPassword+"",ConformPassword:""+ConformPassword+""};
       	try{
       	    $.ajax({
       	          type: "post",
       	          url: "ChangePassword",
       	          data: formData,
       	          success: function(responseData, textStatus, jqXHR) {
       	             try{
       	              document.getElementById("Responce_Message").innerHTML=responseData;
       	              //document.getElementById("Responce_Message_btn").style.display='none';
       	              
       	           	    document.getElementById('newpassword').value="";
       	       		    document.getElementById('confirmpassword').value="";
       	        		document.getElementById('currentpassword').value="";
       	           
       	             }catch(err){
       	            	 
       	             }
       	          },
       	          error: function(jqXHR, textStatus, errorThrown) {
       	              console.log(errorThrown);
       	              document.getElementById("Responce_Message").innerHTML=errorThrown;
       	              document.getElementById("Responce_Message_btn").style.display='';
       	             
       	          }
       	      })
       	}catch(err){
       		
       	}
       	  
        }
        
  function RefreshCh(){
	  
	  document.getElementById("Responce_Message_btn").style.display='';
	  document.getElementById("Responce_Message").innerHTML="";
	  document.getElementById('newpassword').value="";
	  document.getElementById('confirmpassword').value="";
	  document.getElementById('currentpassword').value="";
  }
  </script>
 	</body>
</html>