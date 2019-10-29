<!doctype html>
<html class="fixed">


 <!--  <link rel="stylesheet" href="stylesheets/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="javascripts/bootstrap.min.js"></script>  -->
  
   <link href="assets/stylesheets/tooltip.css" rel="stylesheet" type="text/css" />
   <script src="assets/javascripts/tooltip.js" type="text/javascript"></script>
    
    
<%@ page import="java.util.*"%>
<%

/* System.out.println("1--->"+ request.getAttribute("resig_status2"));
System.out.println("2--->"+ request.getAttribute("resig_status")); */


String  MGRFLAG=(String)session.getAttribute("Manage_Rights");
String MGRFLAG_S="none";
if(MGRFLAG!=null && Integer.parseInt(MGRFLAG)>0){
	  
	  MGRFLAG_S=" ";
}

String TDSFLAG=(String)session.getAttribute("TDSFLAG");

String EMP_NAME=(String)session.getAttribute("EMP_NAME");

if(EMP_NAME==null){
	  
	  response.sendRedirect("login.html");
}

%>


<%-- <%

  String BioData=(String)request.getAttribute("BioData");
  
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
  String EMP_NAME=(String)session.getAttribute("EMP_NAME");
     if(EMP_NAME==null){
    	 response.sendRedirect("/login.html");
     }
    String Notice_board=(String)session.getAttribute("Notice");
    System.out.println("BioData at JSP::" +BioData);
    System.out.println("Emp_BioData at JSP::" +Emp_BioData);
%>
  --%>
<head>

<!-- Basic -->
<meta charset="UTF-8">


<meta name="keywords" content="HETERO" />
<meta name="description" content="Hetero">
<meta name="author" content="Hetero" />


<title>Hetero Healthcare LTD</title>
<link rel="icon" href="LOH.png" />
<!-- <link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css"> -->

<!-- Mobile Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

<!-- Web Fonts  -->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light"
	rel="stylesheet" type="text/css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<!-- Vendor CSS -->
<<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
<link rel="stylesheet"
	href="assets/vendor/font-awesome/css/font-awesome.css" />
<link rel="stylesheet"
	href="assets/vendor/magnific-popup/magnific-popup.css" />

<!-- <link rel="stylesheet" href="assets/stylesheets/GScroll.css" /> -->
<link rel="shortcut icon" href="/images.png" type="image/x-icon" />

<!-- Specific Page Vendor CSS -->
<link rel="stylesheet"
	href="assets/vendor/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css" />
<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
<script src="assets/javascripts/theme.js"></script>
<script src="assets/javascripts/theme.init.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<link rel="stylesheet" href="assets/stylesheets/theme.css" />
<link rel="stylesheet" href="assets/stylesheets/skins/default.css" />
<link rel="stylesheet" href="assets/stylesheets/theme-custom.css">
<link rel="stylesheet" href="colorbox.css" />
<script src="jquery.colorbox.js"></script>

<!--    <link href="stylemail.css" rel="stylesheet" />
    <script src="script.js"></script> -->


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
		<%-- Data=<%=BioData%>;
    	$scope.Data_1=<%=Emp_BioData%>;
    	$scope.Data_2=<%=EmpDOB%>;
    	$scope.Data_3=<%=HOLIDAYS_PG%>; --%>
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

<!-- UMA CODE  For angular validation -->

<script src="http://code.angularjs.org/1.2.6/angular.js"></script>
<script>
    
    
    
    
    var validationApp = angular.module('myApp', []);

 // create angular controller
 validationApp.controller('formCtrl', function($scope) {

   // function to submit the form after all validation has occurred            
   $scope.submitForm = function(isValid) {

     // check to make sure the form is completely valid
     if (isValid) {
       alert('our form is amazing');
     }

   };

 });
 </script>
<!-- <script>  
   
 var app = angular.module("myAppaaa", []);

 app.controller('formCtrl', ['$scope', function($scope){

 }])
     .directive('multipleEmails', function () {
       return {
         require: 'ngModel',
         link: function(scope, element, attrs, ctrl) {
           ctrl.$parsers.unshift(function(viewValue) {
   
             var emails = viewValue.split(',');
             // define single email validator here
             var re = /\S+@\S+\.\S+/; 
               
             // angular.foreach(emails, function() {
               var validityArr = emails.map(function(str){
                   return re.test(str.trim());
               }); // sample return is [true, true, true, false, false, false]
               console.log(emails, validityArr); 
               var atLeastOneInvalid = false;
               angular.forEach(validityArr, function(value) {
                 if(value === false)
                   atLeastOneInvalid = true; 
               }); 
               if(!atLeastOneInvalid) { 
                 // ^ all I need is to call the angular email checker here, I think.
                 ctrl.$setValidity('multipleEmails', true);
                 return viewValue;
               } else {
                 ctrl.$setValidity('multipleEmails', false);
                 return undefined;
               }
             // })
           });
         }
       };
     });
    </script>
     -->
<style>
.ng-valid {
	
}

.ng-invalid {
	
}

.ng-pristine {
	
}

.ng-dirty {
	
}

.ng-touched {
	
}

/* really specific css rules applied by angular */
.ng-invalid-required {
	
}

.ng-invalid-minlength {
	
}

.ng-valid-max-length {
	
}
</style>
<script>
  $( function() {
	  var availableTags = 
		  [
		  "ravichandra@heterohealthcare.com",
		  "srreddy@heterohealthcare.com",
		  "bose@heterohealthcare.com",
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
		  "varunt@heterohealthcare.com",
		  "srinivasarao.m@heterohealthcare.com"
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
		  "bose@heterohealthcare.com",
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
		  "varunt@heterohealthcare.com",
		  "srinivasarao.m@heterohealthcare.com"
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
    	  
    	  <%-- var Notice_Data="<%=Notice_board%>"; --%>
    	  
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
	display: table;
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
	width: inherit;
	height: inherit;
	/* To center horizontally */
	margin: 0 auto;
}
</style>


<script>
function  REG_Status(){
	
	<%
	ArrayList REG_Status=null;
	ArrayList REG_Status2=null;
	String f1=null;
	String f2=null;
	String d1=null;
	String d2=null;
	String d3=null;
	String d4=null;
	String d5=null;
	String d6=null;
	
	String d7=null;
	String d8=null;
	System.out.println("000>>>>>>"+request.getAttribute("resig_status2"));
	if(request.getAttribute("resig_status2")!=null){
		
		 REG_Status = (ArrayList) request.getAttribute("resig_status2");
		// System.out.println("1");
		 f2= REG_Status.get(0).toString();
		 
		 		d1 = REG_Status.get(1).toString();
				d2 = REG_Status.get(2).toString();
				d3 = REG_Status.get(3).toString();
				d4 = REG_Status.get(4).toString();
				d5 = REG_Status.get(5).toString();
				d6 = REG_Status.get(6).toString();
				d7 = REG_Status.get(7).toString();
				d8 = REG_Status.get(8).toString();

			}

			else {
				REG_Status2 = (ArrayList) request.getAttribute("resig_status");
				//System.out.println("2");
				f1 = REG_Status2.get(0).toString();
			}%>
	
	var z1= "<%=f1%>";
	var z2= "<%=f2%>";
	
	var k1="<%=d1%>";
	var k2="<%=d2%>";
	var k3="<%=d3%>";
	var k4="<%=d4%>";
	
	var k5="<%=d5%>";
	var k6="<%=d6%>";
	
	var k7="<%=d7%>";
	var k8="<%=d8%>";
	
	if(z1!='null' && z1=="NO"){
		
	//alert("Open");
		
		document.getElementById("apply_status").style.display="none"; 
		document.getElementById("apply_form").style.display="";
		
	}
	
	if(z2!='null' && z2=="YES"){
		
		//alert("Close");
		 
		 document.getElementById('tab_0').innerHTML = k1 ;
		// document.getElementById('tab_1').innerHTML = k2 ;
		 
		 document.getElementById('tab_1').innerHTML ="<a href='#' data-toggle='tooltip' data-placement='bottom'  class='red-tooltip' title='"+k2+"'><span class='glyphicon glyphicon-envelope'></span> Comment(Mgr)</a>";	
		 document.getElementById('tab_2').innerHTML = k3 ;
		
		 //document.getElementById('tab_3').innerHTML = k4 ;
		  document.getElementById('tab_3').innerHTML ="<a href='#' data-toggle='tooltip' data-placement='bottom'  class='red-tooltip' title='"+k4+"'><span class='glyphicon glyphicon-envelope'></span> Comment(HR)</a>";	
		
		 document.getElementById('tab_4').innerHTML = k5 ;
		 document.getElementById('tab_5').innerHTML = k6 ;
		 //
		 document.getElementById('tab_6').innerHTML = k7 ;
		 document.getElementById('tab_7').innerHTML = k8 ;
		 
		 var z1= "null";
			var z2= "YES";
			
		/* 	var k1="APPROVED";
			var k2="APPROVE FROM APPLICATION";
			var k3="APPROVED";
			var k4="APPROVE FROM APPLICATION"; */
			
			
		 if(k1=="Approved" ||k3=="Approved" || k1=="Reject" || k3=="Reject" ){
			 document.getElementById('drop').disabled =true; 
		 }else{
			 document.getElementById('drop').disabled =false;
		 }
		 
		 document.getElementById('apply_status').style.display = ''; 
		 document.getElementById('apply_form').style.display = 'none';
	}
	
	  
}

</script>
</head>
<body OnLoad="REG_Status();">

	<!-- - ng-app="myApp" ng-controller="formCtrl" " -->
	<section class="body">

		<!-- start: header onload="disableBackButton();-->
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
				<%-- <div id="userbox" class="userbox">
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
				</div> --%>
			</div>
		</header>
		<div class="inner-wrapper">
			<!-- start: sidebar -->
			<aside id="sidebar-left" class="sidebar-left">
				<div class="nano">
					<div class="nano-content">
						<nav id="menu" class="nav-main" role="navigation">
							<ul class="nav nav-main">

								<li class="active"><a href="User_Auth?Routing=DashBoard">
										<i class="fa fa-tachometer"></i> <span class="font-bold">Dashboard</span>
								</a></li>
								<li><a href="NewJoinees?Routing=MyProfile"> <i
										class="fa fa-user"></i> <span class="font-bold">Profile</span>
								</a></li>
								<li><a href="PayslipDownload"> <i
										class="fa fa-download"></i> <span class="font-bold">Downloads</span>
								</a></li>

								<li><a href="hhcl_careers.jsp"> <i
										class="fa fa-briefcase"></i> <span class="font-bold">Careers</span>
								</a></li>


								<!-- Manager Approvals Start -->

								 <li style="display:<%=MGRFLAG_S%>;">
								<a href="ManagerApprovals?Routing=ManagerAppr" target="_parent">
									<i class="fa fa-check"></i> <span class="font-bold">Manager
										Approvals</span>
								</a>
								</li>
								
								 
									
								<li><a href="NewJoinees?Routing=DEPINFO"> <i
										class="fa fa-users"></i> <span class="font-bold">Department
											Information</span>
								</a></li>
                                
                                <li>
									  <a href="PayslipDownload?Routing=REGION">
										<i class="fa fa-sign-out"></i>
										<span class="font-bold">Resignation</span>
									  </a>
								</li> 
									
								<!-- Manager Approvals End -->
								 <li style="display:<%=TDSFLAG%>;">
								<a href="http://mydesk.heterohcl.com/IT/" target="_blank"> <i
									class="fa fa-money "></i> <span class="font-bold">TDS
										Declaration</span>
								</a>
								</li>


								<li><a href="http://www.heterohealthcare.com/"
									target="_blank"> <i class="fa fa-info-circle"></i> <span
										class="font-bold">About Our Organization</span>
								</a></li>



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
					<div class="clear col-md-7" style="color: red;">
						<marquee>
						 <%=session.getAttribute("HHCL_EVENT_INFO")%> 
						</marquee>
					</div>

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


				<!-- start: page -->

				<form action="Resignation" method="post"
					ng-submit="submitForm(userForm.$valid)" novalidate
					name="shareSelectionForm">
					<div id="description" class="wrapper">
						<div class="container">
							<div class="row ">
								<div class="col-md-11 middle-reg">
									<div class="panel panel-danger">
										<div class="panel-heading1">
											<span><b>Resignation </b> </span>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<!-- <span id="Responce_Message" style="color:white;" >  </span> -->
										</div>


										<div class="panel-body" style="margin-top: 15px;"
											id="apply_form" style="display: none">

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
														<input type="email" class="form-control" multiple-emails
															required
															ng-model="shareSelectionFormFields.recipientEmail"
															name="toemail" id="tags" onchange="validemail('to');"
															placeholder="someone@example.com"> <span
															id="result1" style="color: red;"></span>

													</div>


												</div>
												<div class="form-group">
													<label class="col-md-4 control-label">Subject</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="subject"
															id="subject" placeholder="Subject" value="Resignation">
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-4 control-label">Reason For
														Resignation</label>
													<div class="col-md-8">
														Max length 300.<br>
														<textarea class="form-control text-reg" rows="5"
															id="reason" ng-model="reason" name="reason"
															placeholder="Reason For Resignation with valid resons "
															ng-minlength="1" ng-maxlength="300"></textarea>
														<p
															ng-show="userForm.reason.$invalid && !userForm.reason.$pristine"
															class="help-block">Reason is required.</p>
													</div>

												</div>
												<div class="form-group">
													<label class="col-md-4 control-label">Feedback</label>
													<div class="col-md-8">
														Max length 200.<br>
														<textarea class="form-control text-reg" rows="5"
															id="FeedBack" ng-model="FeedBack" name="FeedBack"
															placeholder="Feedback about your Team& Organization"
															style="height: 83px;" ng-minlength="1" ng-maxlength="300"></textarea>
													</div>
												</div>

												<span id="Responce_Message" style="color: red;"> </span>
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


											</div>
											<div class="col-sm-6 col-md-6 center_div">
												<div class="form-group">
													<label class="col-md-4 control-label">CC-mail</label>
													<div class="col-md-8">
														<input type="email" class="form-control" name="ccemail"
															ng-model="ccemail" id="tags1"
															placeholder="someone@example.com"
															onchange="validemail('cc');"> <span id="result2"
															style="color: red;"></span>
													</div>
												</div>


												<span id="Responce_Message" style="color: red;"> </span>
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
													<!-- <button type="button"  id="Send_Button" class="btn btn-primary align-right" onclick="Leave_Validation();" style="margin-top: 85%;" >Send</button> -->

													<input type="button" id="Send_Button"
														class="btn btn-primary align-right"
														style="margin-top: 85%;" value="Submit"
														onclick="Resignation_Validation();" />
												</div>


												<div class="clearfix"></div>


											</div>




										</div>

										<div class="panel-body" style="margin-top: 15px;"
											id="apply_status" style="display: none">

											<table  class="table table-striped">
												<tr>
												<th>Mgr Status &nbsp;&nbsp;</th>
													<th>Mgr Comments &nbsp;&nbsp;</th>
													<th>HR Status &nbsp;&nbsp;</th>
													<th>HR Comments &nbsp;&nbsp;</th>
													<th>Mail Status &nbsp;&nbsp;</th>
													<th>Applied&nbsp;&nbsp;</th>
													<th>Mgr Update &nbsp;&nbsp;</th>
													<th>HR Update &nbsp;&nbsp;</th>
													<th aligen='center'>Action &nbsp;&nbsp;</th>
													
													

												</tr>
												<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});</script>
												<tr>
													<td id="tab_0"> &nbsp;&nbsp; </td>
													<td id="tab_1"> &nbsp;&nbsp; </td>
													
	
													
													<td id="tab_2"> &nbsp;&nbsp; </td>
													<td id="tab_3"> &nbsp;&nbsp; </td>
													
													<td id="tab_4"> &nbsp;&nbsp; </td>
													<td id="tab_5"> &nbsp;&nbsp; </td>
													
													<td id="tab_6"> &nbsp;&nbsp; </td>
													
													<td id="tab_7"> &nbsp;&nbsp; </td>
													
													<!-- <td id="tab_8"> &nbsp;&nbsp; </td> -->
													
													<td ><input id='drop' type='button' value='Self Drop' class="btn btn-primary"  Onclick="Call_submit(this)" > &nbsp<input id='Reference' type='button' value='Ref.Print' Onclick="Call_submit(this)" class="btn btn-primary" ></td>
													
													

												</tr>


											</table>

											<span class="clearfix" id="Responce_Message_status"></span>
                             
                              
                              

										</div>

									</div>


								</div>
							</div>

						</div>
					</div>











				</form>
				<!-- end: page -->







				<!-- 				end: page
					Change Password Modal
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
										
													
							</div>
							<div class="modal-footer">
							<span id="Responce_Message" style="color:red;" class="align-left" >  </span>
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>&nbsp;
							<button type="button" id="Responce_Message_btn" class="btn btn-primary" onclick="validatePassword('Y')">Save</button>
							
							<button type="submit" id="Responce_Message_btn" class="btn btn-primary">Save</button>
							
						
						  </div>
						</div>
					  </div>
					  </div>
					</div> -->

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


	<!--  ...................... Uma script Start ............................................. -->



	<script>
  function Resignation_Validation(){
	
	  document.getElementById("Responce_Message").innerHTML="";
	 var con1= validemail('to');
	 var con2=false;
	 if(con1!=false){
	  var con2=validemail('cc');
	 }
	 //alert(con1 +"~~"+con2);
	if(con1==false || con2==false){
	 
		return false;
	}
	 
	  
	document.getElementById("Responce_Message").innerHTML="Please Wait...!";
 var to_mail=document.getElementById('tags').value; // to mail
 var cc_mail=document.getElementById('tags1').value;
 var subject=document.getElementById('subject').value;// ccmail

 var reason=document.getElementById('reason').value;
   try{
   		to_mail=to_mail.trim();
   		cc_mail=cc_mail.trim();
   		subject=subject.trim();
   		reason=reason.trim();
   }catch(err){
	   alert(err.message);
   }
   
 if(to_mail.length<4){
	   
	   document.getElementById("Responce_Message").innerHTML="Please Select To mail";
	   return false;
   
   }else if(cc_mail.length<4){
	   
	   document.getElementById("Responce_Message").innerHTML="Please enter valid Cc e-mail address else it should'nt process to your Hr ";
	   	return false; 
	   
	   
   }else if(subject.length<3){
	   
	   document.getElementById("Responce_Message").innerHTML="Please enter subject";
	   	return false; 
	   
	   
   }else if(reason.length<4){
	   
	   document.getElementById("Responce_Message").innerHTML="Please enter reason for Resignation";
	   	return false; 
   }
 
   document.getElementById("Responce_Message").innerHTML="";
   
     myFunction();
	
	
}
 
  jQuery(document).ready(function($) {
	    var max = 300;
	    $('#reason').keypress(function(e) {
	        if (e.which < 0x20) {
	           
	            return; 
	        }
	        if (this.value.length == max) {
	            e.preventDefault();
	        } else if (this.value.length > max) {
	           
	            this.value = this.value.substring(0, max);
	        }
	    });
	});  
  
  jQuery(document).ready(function($) {
	    var max = 200;
	    $('#FeedBack').keypress(function(e) {
	        if (e.which < 0x20) {
	            
	            return; 
	        }
	        if (this.value.length == max) {
	            e.preventDefault();
	        } else if (this.value.length > max) {
	         
	            this.value = this.value.substring(0, max);
	        }
	    });
	});  
  
  jQuery(document).ready(function($) {
	    var max = 100;
	    $('#suggest').keypress(function(e) {
	        if (e.which < 0x20) {
	            return;  
	        }
	        if (this.value.length == max) {
	            e.preventDefault();
	        } else if (this.value.length > max) {
	           
	            this.value = this.value.substring(0, max);
	        }
	    });
	});  
	
  
</script>

	<script>
 
	function validemail(condition) {
		
		document.getElementById("Responce_Message").innerHTML="";
		
		if(condition=="to")
		{	
			
	   var group_mails = document.getElementById('tags').value;
	   
		var mail_split = group_mails.split(',');	
		  for (var n = 0; n < mail_split.length; n++) {
		  var mail_split_info = mail_split[n];
		  
		  var validRegExp = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		  
		  if (mail_split_info.trim().search(validRegExp) == -1 ){
			  
			  document.getElementById("Responce_Message").innerHTML= "To-mail not valid E-mail,for multiple mails add with(,) or remove(,)";
			  return false;
			  
			  
		  }
		}
	    
	
		}
	
		else if(condition=="cc"){
			
			
			 var group_mails = document.getElementById('tags1').value;
			 
			 
				var mail_split = group_mails.split(',');	
				  for (var n = 0; n < mail_split.length; n++) {
				  var mail_split_info = mail_split[n];
				  
				  var validRegExp = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
				  
				  if (mail_split_info.trim().search(validRegExp) == -1 ){
					  
					  document.getElementById("Responce_Message").innerHTML= "Cc-mail not valid E-mail,for multiple mails add with(,) or remove(,)";
					  return false;
					  
					  
				  }
				}
				  
				  
			
		 }else if(condition=="self")
		 {
			 
			 var group_mails = document.getElementById('Self_mail').value;

           
      }
		
	/* 	var mail_split = group_mails.split(',');	
	  for (var n = 0; n < mail_split.length; n++) {
	  var mail_split_info = mail_split[n];
	  
	  var validRegExp = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	  
	  if (mail_split_info.trim().search(validRegExp) == -1 ){
		  
		  document.getElementById("Responce_Message").innerHTML= "To-mail not valid E-mail,for multiple mails add with(,)";
		  return false;
		  
		  
	  }
	} */
	  
	 /*  var  emailNo = n + 1;
	    if (mail_split_info.trim().search(validRegExp) == -1 && condition == "to") {
	    	
	    	
	       document.getElementById("Responce_Message").innerHTML= "To-mail not valid E-mail,for multiple mails add with(,)";
	    	 
	    	 document.getElementById('tags1').disabled = true;
	    	 document.getElementById('subject').disabled = true;
	    	 document.getElementById('reason').disabled = true;
	    	 document.getElementById('FeedBack').disabled = true;
	    	 document.getElementById('suggest').disabled = true;
	    	 document.getElementById('Self_mail').disabled = true;
	    	 
	    	 
	    }
	    else if (mail_split_info.search(validRegExp) == 0 && condition == "to") {
	    	
	    	
	    	// document.getElementById("result1").innerHTML=" Done ";
	    	 
	    	 document.getElementById('tags1').disabled = false;
	    	 document.getElementById('subject').disabled = false;
	    	 document.getElementById('reason').disabled = false;
	    	 document.getElementById('FeedBack').disabled = false;
	    	 document.getElementById('suggest').disabled = false;
	    	 document.getElementById('Self_mail').disabled = false;
	    }
	    
	    if (mail_split_info.trim().search(validRegExp) == -1 && condition == "cc") {
	    	
	    	
	    	  document.getElementById("Responce_Message").innerHTML= "Cc-mail not valid E-mail,for multiple mails add with(,)";
		    	 
		    	 document.getElementById('tags').disabled = true;
		    	 document.getElementById('subject').disabled = true;
		    	 document.getElementById('reason').disabled = true;
		    	 document.getElementById('FeedBack').disabled = true;
		    	 document.getElementById('suggest').disabled = true;
		    	 document.getElementById('Self_mail').disabled = true;
		    	 
		    }
		    else if (mail_split_info.search(validRegExp) == 0 && condition == "cc") {
		    	
		    	
		    	// document.getElementById("result2").innerHTML=" Done ";
		    	 
		    	 document.getElementById('tags').disabled = false;
		    	 document.getElementById('subject').disabled = false;
		    	 document.getElementById('reason').disabled = false;
		    	 document.getElementById('FeedBack').disabled = false;
		    	 document.getElementById('suggest').disabled = false;
		    	 document.getElementById('Self_mail').disabled = false;
		    }
	    
	    if (mail_split_info.trim().search(validRegExp) == -1 && condition == "self") {
	    	
	    	
		       document.getElementById("result3").innerHTML= "EMAIL "+ emailNo +" NOT  Valid Email or Delete Ending Comma(,)";
		       document.getElementById('tags').disabled = true;
		    	 document.getElementById('tags1').disabled = true;
		    	 document.getElementById('subject').disabled = true;
		    	 document.getElementById('reason').disabled = true;
		    	 document.getElementById('FeedBack').disabled = true;
		    	 document.getElementById('suggest').disabled = true;
		    	
		    	 
		    }
		    else if (mail_split_info.search(validRegExp) == 0 && condition == "self") {
		    	
		    	
		    	 document.getElementById("result3").innerHTML=" Done ";
		    	 document.getElementById('tags').disabled = false;
		    	 document.getElementById('tags1').disabled = false;
		    	 document.getElementById('subject').disabled = false;
		    	 document.getElementById('reason').disabled = false;
		    	 document.getElementById('FeedBack').disabled = false;
		    	 document.getElementById('suggest').disabled = false;
		    	
		    	 
		    }
	} */
	
	
	
	
	}
	
	
	
	
 
 </script>
	<script type="text/javascript">
		var request;
		function myFunction() {
			
			var to_mail=document.getElementById('tags').value; // to mail
			 var cc_mail=document.getElementById('tags1').value;
			 var subject=document.getElementById('subject').value;// ccmail

			 var reason=document.getElementById('reason').value;
			
			var FeedBack = document.getElementById('FeedBack').value;
			
			
			
		   var url = "Resi?to_mail="+to_mail+"&cc_mail="+cc_mail+"&subject="+subject+"&reason="+reason+
				   "&FeedBack="+FeedBack;
				 
			//	alert(url);	 
					 
		
		 if (window.XMLHttpRequest) {
					request = new XMLHttpRequest();
				} else if (window.ActiveXObject) {
					request = new ActiveXObject("Microsoft.XMLHTTP");
				}

				try {
					request.onreadystatechange = getInfo2;
					request.open("GET", url, true);
					request.send();
				} catch (e) {
					alert("Unable to connect to server");
				}
				

	}	
			function getInfo2() {
				
			           
			           
			           if (request.readyState == 4) 
						{  								// Main IF
			        
			        	 var myObjJSON = JSON.parse(request.responseText);
			        	 
			        
						 if (myObjJSON.Out!=null && myObjJSON.Out!="")
							{
							
							
					//		 alert("_____________"+myObjJSON.Out);
							 if (myObjJSON.Out[0] == "sql_error")
								 {
								 var color = "red";
								   var error = "Please check given data.....!";
								  
								    
								    document.getElementById('Responce_Message').innerHTML = "<font color="+color+"  size='+1'>"
									+ error + "</font>";
							
							  }
							  
							 
							 
							 else {
								  
						
						  document.getElementById('apply_status').style.display = ''; 
						  document.getElementById('apply_form').style.display = 'none'; 
						  //document.getElementById('Responce_Message_status').style.display = ''; 
						  
						 

							document.getElementById('tab_0').innerHTML = myObjJSON.Out[1];
							document.getElementById('tab_1').innerHTML = myObjJSON.Out[2];
							document.getElementById('tab_2').innerHTML = myObjJSON.Out[3];
							document.getElementById('tab_3').innerHTML = myObjJSON.Out[4];
							document.getElementById('tab_4').innerHTML = myObjJSON.Out[5];
							document.getElementById('tab_5').innerHTML = myObjJSON.Out[6];
						  
							  var color = "green";
						      
						       
								var msg = "Successfully submitted  and Check here For Status.....!";
								document.getElementById('Responce_Message_status').innerHTML = "<font color="+color+"  size='+1'>"
										+ msg + "</font>";
						  
							document.getElementById('tags').value="";
							document.getElementById('tags1').value="";
							document.getElementById('subject').value="";
							document.getElementById('reason').value="";
							document.getElementById('FeedBack').value="";
							document.getElementById("result1").innerHTML="";
							document.getElementById('suggest').value="";
							document.getElementById("result2").innerHTML="";
							document.getElementById("result3").innerHTML="";
							document.getElementById('Self_mail').value = "";
							  } 
						}
				     else if(myObjJSON.Out =="")
					  {
					   
				    	 
				    	 document.getElementById('Responce_Message').style.display = ''; 
				    	 
				    	 var color = "red";
					      
					       
							var msg = "Due to improper data So...Not Inserted  .....!";
							document.getElementById('Responce_Message').innerHTML = "<font color="+color+"  size='+1'>"
									+ msg + "</font>";
						 	
							
							document.getElementById("result1").innerHTML="";
							
							document.getElementById("result2").innerHTML="";
							document.getElementById("result3").innerHTML="";
							
					  } 
				
			} 

			} 
			
			
			
			
			
			
			
	</script>


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


		<!-- Examples -->
		<script src="assets/javascripts/dashboard/examples.dashboard.js"></script>

		                 <script>
                              function Call_submit(val){
                            	
                            	  if(val.value=="Self Drop"){
                            	  document.forms[0].action="Self_Drop";
                            	  }else{
                            		  document.forms[0].action="Document_Dowload";
                            	  }
                            	  document.forms[0].submit();
                            	  
                              }
                              </script>		

</body>
</html>



