<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%

  String BioData=(String)request.getAttribute("BioData");
  String EMP_NAME=(String)session.getAttribute("EMP_NAME");
  //String Emp_BioData=(String)request.getAttribute("DOJ_DOB");
  
  String Emp_BioData=(String)request.getAttribute("EM_DOJ");
  String EmpDOB=(String)request.getAttribute("EM_DOB");
  
  String HOLIDAYS_PG=(String)request.getAttribute("HOLIDAYS");

  String TDSFLAG=(String)session.getAttribute("TDSFLAG");
  
  String  MGRFLAG=(String)session.getAttribute("Manage_Rights");
  String MGRFLAG_S="none";
  if(MGRFLAG!=null && Integer.parseInt(MGRFLAG)>0){
	  MGRFLAG_S=" ";
  }
  //String EMP_NAME=(String)session.getAttribute("EMP_NAME");
     if(EMP_NAME==null){
    	 response.sendRedirect("/login.html");
     }
    String Notice_board=(String)session.getAttribute("Notice");
    System.out.println("BioData at JSP::" +BioData);
    System.out.println("Emp_BioData at JSP::" +Emp_BioData);
%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hetero Healthcare LTD</title>
          <link rel="icon" href="LOH.png" />
          
		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<!-- Vendor CSS -->
		<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
		<link rel="stylesheet" href="assets/vendor/magnific-popup/magnific-popup.css" />
	
         <!-- <link rel="stylesheet" href="assets/stylesheets/GScroll.css" /> -->
		<link rel="shortcut icon" href="/images.png" type="image/x-icon" />

		<!-- Specific Page Vendor CSS -->
		<link rel="stylesheet" href="assets/vendor/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css" />
		<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
		<script src="assets/javascripts/theme.js"></script>
		<script src="assets/javascripts/theme.init.js"></script>
		<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
		<link rel="stylesheet" href="assets/stylesheets/theme.css" />
		<link rel="stylesheet" href="assets/stylesheets/skins/default.css" />
		<link rel="stylesheet" href="assets/stylesheets/theme-custom.css">
		<link rel="stylesheet" href="colorbox.css" />
	   <script src="jquery.colorbox.js"></script> 
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

  
      
<script src="MyAng.js"></script>

<script>
var app = angular.module('myApp', []);
app.controller('formCtrl', function($scope) {
		var Data="";
		$scope.Data_2="";
		$scope.Data_1="";
		$scope.Data_3="";
	try{
		Data=<%=BioData%>;
    	$scope.Data_1=<%=Emp_BioData%>;
    	$scope.Data_2=<%=EmpDOB%>;
    	$scope.Data_3=<%=HOLIDAYS_PG%>;
	}catch(err){
		alert(err.message);
	}
    	$scope.empid = Data.EMPLOYEESEQUENCENO;
    	$scope.empname= Data.CALLNAME;
    	$scope.emp_doj= Data.DATEOFJOIN;
    	$scope.emp_Dep= Data.DEPARTMENT;
    	$scope.emp_Des= Data.DESIGNATION;
    	$scope.emp_job_type= Data.TYPE;
}); 
</script>
 <script>
  $( function() {
	  var availableTags = 
		  [
		  "ravichandra@heterohealthcare.com",
		  "srreddy@heterohealthcare.com",
		  "bose.n@heterohealthcare.com",
		  "saikumar.b@heterohealthcare.com",
		  "shantaram.p@heterohealthcare.com",
		  "praveenreddy.c@heterohealthcare.com",
		  "santoshreddy.y@heterohealthcare.com",
		  "sridhars@heterohealthcare.com",
		  "sunitgupta@heterohealthcare.com",
		  "srihari.b@heterohealthcare.com",
		  "rakesh.singh@heterohealthcare.com",
		  "nilay.chatterjee@heterohealthcare.com",
		  "mani.ps@heterohealthcare.com",
		  "ansari@heterohealthcare.com",
		  "hepsibha.t@heterohealthcare.com",
		  "sangeetha.b@heterohealthcare.com",
		  "deepaksharma@heterohealthcare.com",
		  "chiranjeevi.velagala@heterohealthcare.com",
		  "yjojireddy@heterohealthcare.com",
		  "dprasad@heterohealthcare.com",
		  "sunilp@heterohealthcare.com",
		  "varunt@heterohealthcare.com"
		  ];
	  
    function split( val ) {
      return val.split( /,\s*/ );
    }
    function extractLast( term ) {
      return split( term ).pop();
    }
 
    $("#tags").on( "keydown", function( event ) {
        if ( event.keyCode === $.ui.keyCode.TAB &&
            $( this ).autocomplete( "instance" ).menu.active ) {
          event.preventDefault();
        }
      })
      .autocomplete({
        minLength: 0,
        source: function( request, response ) {
          // delegate back to autocomplete, but extract the last term
          response( $.ui.autocomplete.filter(
            availableTags, extractLast( request.term ) ) );
        },
        focus: function() {
          // prevent value inserted on focus
          return false;
        },
        select: function( event, ui ) {
          var terms = split( this.value );
          // remove the current input
          terms.pop();
          // add the selected item
          terms.push( ui.item.value );
          // add placeholder to get the comma-and-space at the end
          terms.push( "" );
          this.value = terms.join( ", " );
          return false;
        }
      });
  } );
</script>
<script>
  $( function() {
	  var availableTags = 
		  [
		  "ravichandra@heterohealthcare.com",
		  "srreddy@heterohealthcare.com",
		  "bose.n@heterohealthcare.com",
		  "saikumar.b@heterohealthcare.com",
		  "shantaram.p@heterohealthcare.com",
		  "praveenreddy.c@heterohealthcare.com",
		  "santoshreddy.y@heterohealthcare.com",
		  "sridhars@heterohealthcare.com",
		  "sunitgupta@heterohealthcare.com",
		  "srihari.b@heterohealthcare.com",
		  "rakesh.singh@heterohealthcare.com",
		  "nilay.chatterjee@heterohealthcare.com",
		  "mani.ps@heterohealthcare.com",
		  "ansari@heterohealthcare.com",
		  "hepsibha.t@heterohealthcare.com",
		  "sangeetha.b@heterohealthcare.com",
		  "deepaksharma@heterohealthcare.com",
		  "chiranjeevi.velagala@heterohealthcare.com",
		  "yjojireddy@heterohealthcare.com",
		  "dprasad@heterohealthcare.com",
		  "sunilp@heterohealthcare.com",
		  "varunt@heterohealthcare.com"
		  ];
    function split( val ) {
      return val.split( /,\s*/ );
    }
    function extractLast( term ) {
      return split( term ).pop();
    }
 
    $( "#tags1" ).on( "keydown", function( event ) {
        if ( event.keyCode === $.ui.keyCode.TAB &&
            $( this ).autocomplete( "instance" ).menu.active ) {
          event.preventDefault();
        }
      })
      .autocomplete({
        minLength: 0,
        source: function( request, response ) {
          // delegate back to autocomplete, but extract the last term
          response( $.ui.autocomplete.filter(
            availableTags, extractLast( request.term ) ) );
        },
        focus: function() {
          // prevent value inserted on focus
          return false;
        },
        select:function( event, ui ) {
          var terms = split( this.value );
          // remove the current input
          terms.pop();
          // add the selected item
          terms.push( ui.item.value );
          // add placeholder to get the comma-and-space at the end
          terms.push( "" );
          this.value = terms.join(",");
          return false;
        }
      });
  } );
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
              $.colorbox({iframe:true, width:"80%", height:"80%", href:"HHCL_HR_LEAVE_POLICY.html"});
    	  }else if(data=="HR_NOTICE") {
              $.colorbox({iframe:true, width:"80%", height:"80%", href:"HHCL_HR_NOTICE_INFO.html"});
    	  }
    	  else if(data=="APPLYLEAVE") {
              $.colorbox({iframe:true, width:"95%", height:"85%", href:"leave.html"});
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
     	  }
   	//{iframe:true, width:"80%", height:"80%", href: "http://www.sitepoint.com"}
      }
function openColorBox_Event(){
    	  
    	  var Notice_Data="<%=Notice_board%>";
    	  
    	  if(Notice_Data=="Y"){
    	     openColorBox("notice")
    	   }
     }
    setTimeout(openColorBox_Event, 2000);
	window.onkeydown = function(e){
    if(e.keyCode === 27){ // Key code for ESC key
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
			</header>
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
									  <a href="resignation.html" target="_blank">
										<i class="fa fa-sign-out"></i>
										<span class="font-bold">Resignation</span>
									  </a>
									</li>
									<li>
									  <a href="NewJoinees?Routing=DEPINFO">
										<i class="fa fa-users"></i>
										<span class="font-bold">Department Information</span>
									  </a>
									</li>
									
									<!-- Manager Approvals End -->
									<li style="display:<%=TDSFLAG%>;">
									  <a href="http://mydesk.heterohcl.com/IT/" target="_blank">
										<i class="fa fa-money "></i>
										<span class="font-bold">TDS Declaration</span>
									  </a>
									</li>
									
									
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

				<section role="main" class="content-body resignation-body">
					<header class="page-header">
					<div class="clear col-md-7" style="color:red;"> <marquee> <%=session.getAttribute("HHCL_EVENT_INFO")%>  </marquee></div>
					
				 <!--  <div class="col-md-2 tool-tip-header">
				   <div class="weather">
						<a href="#" class="tooltip">
							<img src="images/ww.png" class="icon-img" />
							<span>
								<img class="callout" src="images/callout_black.gif" />
								<img src="images/css3-tooltip-image.png" style="float:right;" />
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
								<img src="images/css3-tooltip-image.png" style="float:right;" />
								<strong>CSS only Tooltip</strong><br />
								Pure CSS popup tooltips with clean semantic XHTML.
							</span>
						</a>
				   </div>
				 
				 </div> -->
				 
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
					<div id="description" class="wrapper">
					<div class="container">
						<div class="row " >
							<div class="col-md-11 middle-reg">
								<div class="panel panel-danger" >
									<div class="panel-heading1">
										<span><b>Resignation </b> </span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								        <!-- <span id="Responce_Message" style="color:white;" >  </span> -->
									</div>
									<div class="panel-body" style="margin-top: 15px;">
										<form action="" method="post" >
											<!-- <div class="col-md-6">	
												<div class="form-group row">
													<label class="col-sm-6 control-label">Select Leave Category</label>
													<select  id="Leave_Type" class="select1" class=" col-sm-6">
														
														<option value="Sick_Leave">Sick Leave</option>
														<option value="Casual_Leave" selected>Casual Leave</option>
														<option value="Earned_Leave">Earned Leave</option>
														<option value="COMP_OFF">COMP-OFF</option>
													</select>
												</div>
																						
												<div class="form-group row">
													<div class="col-md-6">
														<label  class="col-sm-4 control-label">From</label>
														<div class="col-sm-8">
														<input type="text" id="from" name="from" class="form-control " readOnly=true >
														</div>
													</div>	
													<div class="col-md-6">
														<label  class="col-sm-4 control-label" >To</label>
														<div class="col-sm-8">
														<input type="text" id="to" name="to" class="form-control " readOnly=true >
														</div>
													</div>
													
												</div>	
												<div class="form-group row">
														<div class="col-md-7">
															<label class="col-sm-9 control-label">Half Day Applicable</label>
															<div class="col-sm-3">
																<label>
																	<input class="form-check-input" type="checkbox" name="HalfDay" onclick="RefreshRadio(this,'date')"  id="HalfDay" value="Yes"> Yes
																</label>
															</div>
														</div>	
														<div class="col-md-5">
																<label  class="col-sm-4 control-label">Date</label>
																<div class="col-sm-8">
																	<input type="text" id="date" name="date" class="form-control" Disabled  readOnly=true />
																</div>
																
														</div>
														
												</div>
												<div class="form-group row">
														<div class="col-md-7">
															<label class="col-sm-9 control-label">COMP-OFF Applicable</label>
															<div class="col-sm-3">
																<label>
																	<input class="form-check-input" type="checkbox" name="compoff" onclick="RefreshRadio(this,'date1')"  id="compoff" value="Yes"> Yes
																</label>
															</div>
														</div>	
														<div class="col-md-5">
																<label  class="col-sm-4 control-label">Date</label>
																<div class="col-sm-8">
																	<input type="text" id="date1" name="date1" class="form-control" Disabled readOnly=true />
																</div>
																
														</div>
														
												</div>
												<div class="form-group row">
													<div class="col-sm-12">
													
													
														<strong>Guidelines:</strong>
														<ul>
															<li>If you select Half day applicable, then half day worked date should be matched with either FROM date or TO date, else it will be considered as full day leave.</li>
															<li>If you select Comp-off applicable, then comp off date should be matched with either week-off or public holiday, else it won't be considered.</li>
															<li>In case of sick leaves, if any public holiday or week off days present in between the dates selected, those days will also be considered as part of the leave.</li>
															<li>If you have to use multiple mails please mention them separated with comma(,)</li>
															<li>By default, request copies will be sent to Ms. Sangeetha from HR team. So, there is no need to specify her mail id in CC. For any status information/track, kindly mention your mail id also in CC.</li>
															<li><B>Note: Please contact HR team for further information.</B></li>
														</ul>
													</div>
												</div>
												
											</div> -->
											<div class="col-sm-6 col-md-6 center_div">
											
												
												<div class="form-group">
													<label class="col-md-4 control-label">To-mail</label>
													<div class="col-md-8">
														<input type="email" class="form-control" name="toemail" id="tags"  placeholder="someone@example.com">
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-4 control-label">Subject</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="subject"  id="subject" placeholder="Subject" value='Resignation'>
													</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-4 control-label">Reason For Resignation</label>
													<div class="col-md-8">
														<textarea class="form-control text-reg" rows="5" id="reason" name="reason" placeholder="Reason For Resignation with valid resons "></textarea>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-4 control-label">Feedback</label>
													<div class="col-md-8">
														<textarea class="form-control text-reg" rows="5" id="FeedBack" name="FeedBack" placeholder="Feedback about your Team& Organization" style="height: 83px;"></textarea>
													</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-4 control-label"> suggest anyone to replace your position </label>
													<div class="col-md-8">
														<textarea class="form-control text-reg" rows="5" id="suggest" name="suggest" placeholder="Will you suggest anyone to replace your position in your team" style="height: 83px;"></textarea>
													</div>
												</div>
												
												
											
												<span id="Responce_Message" style="color:red;" >  </span>
												<!-- <div class="form-group">
													<label class="col-md-4 control-label">Attachment</label>
													<div class="col-md-8">
														<div class="fileupload fileupload-new" data-provides="fileupload">
															<input type="file" id="resume">
															<span class="fileupload-preview"></span>
															
														</div>
													</div>
												</div> -->
												
												<div class="clearfix"></div>
											</form>
											
										</div>
											<div class="col-sm-6 col-md-6 center_div">
											<div class="form-group">
													<label class="col-md-4 control-label">CC-mail</label>
													<div class="col-md-8">
													<input type="email" class="form-control" name="ccemail" id="tags1"   placeholder="someone@example.com">
													</div>
												</div>
												
												
												
												
												
												<span id="Responce_Message" style="color:red;" >  </span>
												<!-- <div class="form-group">
													<label class="col-md-4 control-label">Attachment</label>
													<div class="col-md-8">
														<div class="fileupload fileupload-new" data-provides="fileupload">
															<input type="file" id="resume">
															<span class="fileupload-preview"></span>
															
														</div>
													</div>
												</div> -->
												<div class="form-group">
												<button type="button"  id="Send_Button" class="btn btn-primary align-right" onclick="Leave_Validation();" style="margin-top: 85%;" >Send</button>
												</div>
												
												<div class="clearfix"></div>
											</form>
											
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
		
		<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
		<!-- Specific Page Vendor -->
		<script src="assets/vendor/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>
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
  <script>
  function Leave_Validation(){
	
	document.getElementById("Responce_Message").innerHTML="Please Wait...!";
	
 var Leave_Type=document.getElementById('Leave_Type').value;
 var from_date=document.getElementById('from').value;
 var to_date=document.getElementById('to').value;
 var HalfDay=document.getElementById('HalfDay').checked;
 var compoff=document.getElementById('compoff').checked;
 
 var Hal_date=document.getElementById('date').value;
 
 var comm_date=document.getElementById('date1').value;
 
 var to_mail=document.getElementById('tags').value; // to mail
 var cc_mail=document.getElementById('tags1').value; // ccmail
 var subject=document.getElementById('subject').value;
 var reason=document.getElementById('reason').value;
   try{
   		to_mail=to_mail.trim();
   		cc_mail=cc_mail.trim();
   		subject=subject.trim();
   		reason=reason.trim();
   }catch(err){
	   alert(err.message);
   }
   
 if(from_date.length<4){
	   
	   document.getElementById("Responce_Message").innerHTML="Please Select From Date";
	   return false;
   }else if(to_date.length<4){
	   document.getElementById("Responce_Message").innerHTML="Please Select To Date";
	   return false;
   }else if(HalfDay && Hal_date.length<4){
	   
	   document.getElementById("Responce_Message").innerHTML="Please select Half Day applicable date";
	   return false;
   }else if(compoff && comm_date.length<4){
	   document.getElementById("Responce_Message").innerHTML="Please select COMP-OFF applicable  date";
	   return false;
   }else if(HalfDay && Hal_date.length>4){
	   if(from_date!=Hal_date && to_date!=Hal_date ){
	   	document.getElementById("Responce_Message").innerHTML="Half Day Applicable date should be match with From date or To date ";
	   	return false;
	   }
   }else if(to_mail.length<4){
	   
	   document.getElementById("Responce_Message").innerHTML="Please enter valid e-mail address else it should'nt process to your manager ";
	   	return false; 
	   
	   
   }else if(subject.length<3){
	   
	   document.getElementById("Responce_Message").innerHTML="Please enter subject";
	   	return false; 
	   
	   
   }else if(reason.length<4){
	   
	   document.getElementById("Responce_Message").innerHTML="Please enter reason for leave";
	   	return false; 
	   
	   
   }
 
   document.getElementById("Responce_Message").innerHTML="";
	
   // alert(reason.length +"~~~"+ to_mail.length);
   
   if(reason.length>4 && to_mail.length>4){
	   
	   Apply_Leave(Leave_Type,from_date,to_date,HalfDay,Hal_date,compoff,comm_date,to_mail,cc_mail,subject,reason);
	   
   }else{
	   
	   document.getElementById("Responce_Message").innerHTML="Please check To mail & reason for leave ";
	   
   }
   	
	
	
	
}

function Apply_Leave(Leave_Type,from_date,to_date,HalfDay,Hal_date,compoff,comm_date,to_mail,cc_mail,subject,reason){
	    
	    document.getElementById("Send_Button").disabled=true;
		var formData = {Routing:"LeaveMgM",Leave_Type:""+Leave_Type+"",from_date:""+from_date+"",to_date:""+to_date+"",HalfDay:""+HalfDay+"",Hal_date:""+Hal_date+"",compoff:""+compoff+"",comm_date:""+comm_date+"",to_mail:""+to_mail+"",cc_mail:""+cc_mail+"",subject:""+subject+"",reason:""+reason+"",};
	try{
	    $.ajax({
	          type: "post",
	          url: "LeaveManagement",
	          data: formData,
	          success: function(responseData, textStatus, jqXHR) {
	        	  document.getElementById("Send_Button").disabled=false;
	             try{
	            	document.getElementById("Responce_Message").innerHTML=responseData;
	            	document.getElementById('from').value='';
	            	document.getElementById('to').value='';
	            	document.getElementById('HalfDay').checked=false;
	            	document.getElementById('compoff').checked=false;
	            	document.getElementById('date').value='';
	            	document.getElementById('date1').value='';
	            	document.getElementById('tags').value=''; // to mail
	            	document.getElementById('tags1').value=''; // ccmail
	            	document.getElementById('subject').value;
	            	document.getElementById('reason').value='';
	             }catch(err){
	            	 document.getElementById("Responce_Message").innerHTML=err;
	             }
	          },
	          error: function(jqXHR, textStatus, errorThrown) {
	               console.log(errorThrown);
	               document.getElementById("Send_Button").disabled=false;
	               document.getElementById("Responce_Message").innerHTML=errorThrown;
	             // document.getElementById("Responce_Message_btn").style.display='';            
	          }
	      })
	}catch(err){
		 document.getElementById("Responce_Message").innerHTML=err;
	}  
}	
	
</script>	
  
  
  
 	</body>
</html>