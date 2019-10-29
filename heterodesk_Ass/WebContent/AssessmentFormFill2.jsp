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
  String empCode=(String)request.getParameter("empCode"); 
  String formStatus=(String)request.getParameter("formStatus");

	if(empCode!=null){
		/* System.out.println("eCode~"+eCode);
		System.out.println((String)request.getParameter("formStatus")); */
	}else{
		response.sendRedirect("Hhc_desk_login.jsp");
	}
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

<link rel="stylesheet" href="status-barcss/style.css">


<script src="jquery-ui-1.12.1.custom/jquery-1.12.4.js"></script>
<script src="jquery-ui-1.12.1.custom/jquery-ui.js"></script>
<script src="jquery.colorbox.js"></script>
<link rel="stylesheet" href="colorbox.css" />


<script src="MyAng.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.6/angular.js"></script> -->
<style>
table.status{
overflow: auto;
border-collapse: collapse;
width: 100%;
}
table.status td, table.status th {
  border: 1px solid #ddd;
  padding: 8px;
}

table.status tr:nth-child(even){background-color: #f2f2f2;}

table.status th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #2981bb;
  color: white;
</style>

<script type="text/javascript" >   
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
    
    $scope.empid = Data.EMPLOYEESEQUENCENO;
    $scope.empname= Data.CALLNAME;
    $scope.emp_doj= Data.DATEOFJOIN;
    $scope.emp_Dep= Data.DEPARTMENT;
    $scope.emp_Des= Data.DESIGNATION;
    $scope.emp_job_type= Data.TYPE;
    //Start Function empdetails
    
    $scope.DesiList=function(){
    	//alert("Loading...");
    	$scope.loading = true;
    	$scope.button="Create";
    	
    	$http({
    	    url: "LeaveInfo",
    	    method: "POST",
    	    params: {
    	    	Routing :"DesignationLoad"
    	    },
    	}).then(function mySucces(response) {
    		 $scope.msg="";
    		 $scope.loading = false;
    		 var data=response.data;
    		 $scope.DivisionsList="";
    		/*  $scope.student = [  
    	                  {"name":"Physics",marks:850},  
    	                  {name:'Chemistry',marks:80},  
    	                  {name:'Math',marks:90},  
    	                  {name:'English',marks:80},  
    	                  {name:'Hindi',marks:70}  
    	               ]; */
    		 $scope.student = data;
    		 
    		/*  $scope.desilist=data;
    		alert("Data-->"+data); */

		}, function myError(response) {
			$scope.myWelcome = response.statusText;
			alert($scope.myWelcome);
		});
	};
	
	
	// Function for Populate into edit 
	
	$scope.clicked=function(event){
	
    	// alert(event.target.id);

		var ischeck=event.target.checked;
		var Desicode=event.target.id;
		/* var k=document.getElementById(Desicode);
		alert(k.checked); */
		
		//alert(Desicode);
		
		var bu=$scope.DivisionsList;
		//alert(bu);
	 		
		if(!bu==""){ 
			$scope.loading = true;
			$http({
	    	    url: "LeaveInfo",
	    	    method: "POST",
	    	    params: {
	    	    	Routing :"Assign",
	    	    	CompanyID : bu,
	    	    	DesiID : Desicode,
	    	    	Check : ischeck	    	    	
	    	    },
	    	}).then(function mySucces(response) {
	    		 $scope.msg="";
	    		 $scope.loading = false;
	    		 var data=response.data;
	    		/*  $scope.student = data; */
	    		 if(data=="1"){
	    			 $scope.msg="Data Saved Successfully";
	    		}
	    		 
			}, function myError(response) {
				$scope.myWelcome = response.statusText;
				alert($scope.myWelcome);
			});
		}
		};

		//End Of Function empdetails
		
	$scope.GetBuDesi = function() {

	  		 //$scope.student="";
			var Company = $scope.DivisionsList;
			if(!Company==''){
				 $scope.loading = true;
				$http({
		    	    url: "LeaveInfo",
		    	    method: "POST",
		    	    params: {
		    	    	Routing :"GetBUDesiList",
		    	    	CompanyID : Company
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
				$scope.DesiList();
			}
		};
	
		$scope.Empdetails = function() {

	  		 //$scope.student="";
			/* var Company = $scope.DivisionsList;
			if(!Company==''){
				 $scope.loading = true;
				$http({
		    	    url: "LeaveInfo",
		    	    method: "POST",
		    	    params: {
		    	    	Routing :"GetBUDesiList",
		    	    	CompanyID : Company
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
				$scope.DesiList();
			} */
			
			$scope.employeeid= <%=empCode%>;
			$http({
	    	    url: "AssessmentForm",
	    	    method: "POST",
	    	    params: {
	    	    	Routing :"getEmpDetails",
	    	    	employeeid: <%=empCode%>
	    	    },
	    	}).then(function mySucces(response) {
	    		 $scope.msg="";
	    		 $scope.loading = false;
	    		 
	    		 var data=response.data.split('#')[0];
	    		 $scope.employeeid = data.split('~')[0];
	    		 $scope.employeename = data.split('~')[1];
	    		 $scope.Emp_DOJ = data.split('~')[2];
	    		 $scope.Emp_Qual = data.split('~')[3];
	    		 $scope.Emp_Dept = data.split('~')[4];
	    		 $scope.Emp_Desg = data.split('~')[5];
	    		 $scope.Emp_Unit = data.split('~')[6];
	    		 
	    		 $scope.Emp_Priv = data.split('~')[7];
	    		 $scope.Emp_Current = data.split('~')[8];
	    		 $scope.Emp_Total = data.split('~')[9];
	    		 
	    		 /* alert(data.split('~')[11]); */
	    		 $scope.options = [{ name: data.split('~')[11], value: data.split('~')[10]},{ name: "HR Dept", value: 10452 }];
	    		 $scope.selectedOption = $scope.options[1];
	    //**********************************************************************		 
	    		 //$scope.overall_rating="C";
	    		 var formName='<%=formStatus%>';
	    		 //Fill Form
	    		 if(formName=='View'){
	    			 $scope.displyBtn=false;
	    		 }else{
	    			 $scope.displyBtn=true;
	    		 }
	  		 
	    		 var oldDatadisCode=response.data.split('#')[2];
	    		 if(oldDatadisCode==1){
	    			 
	    		 $scope.mrgFiller=oldDatadisCode;
	    	
	    		 // Disabling Category
    			var radioButtons = document.getElementsByName("overall_rating");
				for(var i=0;i<radioButtons.length;i++) {
				  // radioButtons[i].disabled = true;
					}    		 
	    		 
			//	document.getElementById("hodComments").disabled = true;
				
				var x = document.getElementsByTagName("input");
				var i;
				 for (i = 0; i < x.length; i++) {
					 
					if(x[i].name=="FormSub" ||  x[i].name=="mrgFiller" || x[i].name=="docFwd" || x[i].name=="employeeid"){
						
				//	x[i].disabled = false;
					}else{
					//	x[i].disabled = true;
					}
				}
	    		 }
	    	//**************************************************************************************************************
	    	
	    	//********************************* GET DETAILS ************************************************
	    		 if(response.data.split('#')[1].replace('~','')!=''){
	    			
	    		 var oldData=response.data.split('#')[1];
	    		// alert(oldData.split('~')[1]);
	    		 $scope.overall_rating=oldData.split('~')[1];
	    		 $scope.hodComments=oldData.split('~')[2];
	    		 
	    		 if(oldData.split('~')[9].length==0){
	    			 $scope.prvCom=false;
	    		 }else if(oldData.split('~')[9].length!=0){
	    			 $scope.prvCom=true;
	    			 $scope.otherRemarks1=oldData.split('~')[9];
	    		 }
	    		

	    		 if(oldData.split('~')[3]=='N'){
	    		 $scope.r1=true;
	    		 $scope.Contribution='N';
	    		 $scope.monthCount=Number(oldData.split('~')[4]);
	    		 }else if(oldData.split('~')[3]=='Y'){
	    			 $scope.r2=true;
	    			 $scope.Contribution='Y';
	    		 }
	    		 
	    	     var area = oldData.split('~')[5];//Area's
	    		 var temparea = new Array();
	    		 temparea = area.split(",");
	    		 var marea;
	    		 temparea.forEach(function(entry) {
	    			 
	    			if(entry==1){
	    				$scope.achk1=true;
	    			}else if(entry==2){
	    				$scope.achk2=true;
	    			}else if(entry==3){
	    				$scope.achk3=true;
	    			}else if(entry==4){
	    				$scope.achk4=true;
	    			}else if(entry==5){
	    				$scope.achk5=true;
	    			}else if(entry==6){
	    				$scope.achk6=true;
	    			}else if(entry==7){
	    				$scope.achk7=true;
	    			}else if(entry==8){
	    				$scope.achk8=true;
	    			}
		 
	    			 /* $scope.achk[entry] = true; */
	    			// $scope.areastr=true;
	    			// $scope.achk1=true;
	    		 });
	    		 
	    		 
	    		 /* var checkedValue = null; 
	    		 var inputElements = document.getElementsByTagName('checkbox');
	    		 alert(inputElements.type);
	    		 for(var i=0; inputElements[i]; ++i){
	    		       if(inputElements[i].checked){
	    		            checkedValue = inputElements[i].value;
	    		            break;
	    		       }
	    		 } */
	    		 
	    		 
	    		 var str = oldData.split('~')[7];//Trainings
	    		 var temp = new Array();
	    		 temp = str.split(",");
	    		 var str1="tchk";
	    		 var m;
	    		 temp.forEach(function(entry) {
	    		    // console.log("tchk"+entry);
	    		    if(entry==1){
	    		    	$scope.tchk1=true;
	    		    }else if(entry==2){
	    		    	$scope.tchk2=true;
	    		    }else if(entry==3){
	    		    	$scope.tchk3=true;
	    		    }else if(entry==4){
	    		    	$scope.tchk4=true;
	    		    	$scope.tckOthers=oldData.split('~')[8];
	    		    }
	    		 });
	    		// alert("---");
	    		// ***********************CHNAGE BY HEMA *************** APPROVAL LEVEL
	    		 var str = oldData.split('~')[10]; //approval levels
	    		 var temp = new Array();
	    		 temp = str.split(",");
	    		 $scope.Approvlist=temp;
	    	// temp.forEach(function(entry) {
	    			// alert(entry);
	    	//	 });
	    	//------------------------------------------------all comments
	    	 var Allcomentslist=response.data.split('#')[3];

	    	 var obj = JSON.parse(Allcomentslist);
	    	 $scope.Allcommentslist=obj;
	    	//alert(obj[1].id);
	    	//----------------------------------------------------------
	    		 }// if close
			}, function myError(response) {
				$scope.myWelcome = response.statusText;
				alert($scope.myWelcome);
			});
		};
		$scope.clearingHOD=function(){
			$scope.hodComments="";
		}
		$scope.techOthers=function(){
			$scope.tckOthers="";
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
    width: 60%;
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
.clear{
margin-bottom:5px;
}
</style>
</head>
<!-- data-ng-init="empdetails();" -->
<body data-ng-app="myApp" data-ng-controller="formCtrl" data-ng-init="Empdetails();" onload="disableBackButton(); noBack();">
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
                                            
                                            <form action="AssessmentForm" method="POST" name="SaveForm">
                                            
                                            
                                        <div class="panel-body">
                                            <!-- Table Starts-->
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered">
                                                    <tbody>
                                                        <tr>
                                                            <td>Emp. Name</td>
                                                            <td>
                                                                <input type="text" class="form-control" data-ng-model="employeename" name="Emp_name" readonly />
                                                            </td>
                                                            <td>Qualification</td>
                                                            <td>
                                                                <input type="text" class="form-control"  data-ng-model="Emp_Qual" name="Emp_Qual" readonly />
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Emp. No</td>
                                                            <td>
                                                                <input type="text" class="form-control"  data-ng-model="employeeid" name="employeeid"  value="" readonly />
                                                            </td>
                                                            <td>Date of Joining</td>
                                                            <td>
                                                                <input type="text" class="form-control"  data-ng-model="Emp_DOJ" name="Emp_DOJ" readonly />
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Designation</td>
                                                            <td>
                                                                <input type="text" class="form-control" data-ng-model="Emp_Desg" name="Emp_Desg" readonly/>
                                                            </td>
                                                            <td>Experience</td>
															<td>
															<span>Previous:&nbsp;&nbsp;&nbsp;<b>{{Emp_Priv}}</b></span>
															<span>Hetero:&nbsp;&nbsp;&nbsp;<b>{{Emp_Current}}</b></span>
															<span>Total:&nbsp;&nbsp;&nbsp;<b>{{Emp_Total}}</b></span>
															</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Department</td>
                                                            <td>
                                                                <input type="text" class="form-control" data-ng-model="Emp_Dept" name="Emp_Dept" readonly/>
                                                            </td>
                                                            <td>Unit And Location</td>
                                                            <td>
                                                                <input type="text" class="form-control" data-ng-model="Emp_Unit" name="Emp_Unit" readonly/>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
 <section class="statusbar-block">
  <ol class="progress-bar"  ng-if="Approvlist.length>1">
 
    <li class="is-active"  ng-repeat="x in Approvlist"><span>{{x}}</span></li>  
    <!-- <li><span>Options</span></li>  
    <li><span>Step</span></li>
    <li><span>In a Nutshell</span></li>  
    <li><span>Step</span></li>
    <li><span>Step</span></li>  -->
  </ol>
</section>
                                            <!-- Table Ends-->
                                            <div class="col-sm-12 text-center">
                                                <strong><small>According to you, which performance category he/she falls under:(Please select ) A:Very Good; B:Good;C:Average;D:Poor</small></strong>
                                               
                                                 <input type="button"  data-toggle='modal' data-target='#myModaloverallratinglistview'  value="Details" />	
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
                                                            <td class="vcenter">Category - A</td>
                                                            <td>
																<ul>
																	<li>Very sincere towards Job Responsibilities</li>
																	<li>High quality of work</li>
																	<li>Exceptional Knowledge of work</li>
																	<li>Familiar with SOPs & Systems</li>
																	<li>Team Leader</li>
																	<li>Safety Conscious</li>
																	<li>Dependable</li>
																</ul>
															</td>
                                                            <td>
																<ul>
																	<li>Very initiative</li>
																	<li>Very good learning attitude</li>
																	<li>Well behaved</li>
																	<li>Very good planning & organizing skills</li>
																	<li>Positive attitude</li>
																	<li>Well disciplined</li>
																	<li>Very punctual</li>
																</ul>
															</td>
                                                            <td>
                                                                <div class="radio">
                                                                    <label>
                                                                    <!-- ng-required="!color" -->
                                                                        <input type="radio" value="A" data-ng-model="overall_rating" name="overall_rating" id="a" data-ng-required="!overall_rating" data-ng-change="clearingHOD();">
                                                                    </label>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="vcenter">Category - B</td>
                                                            <td>
																<ul>
																	<li>Sincere towards Job Responsibilities</li>
																	<li>Good quality of work</li>
																	<li>Well behaved</li>
																	<li>Team player</li>
																	<li>Safety Conscious</li>
																	<li>Good knowledge on SOPs & Systems</li>
																	<li>Dependable</li>
																</ul>
															</td>
                                                            <td>
																<ul>
																	<li>Good planning & organizing skills</li>
																	<li>Good learning attitude</li>
																	<li>Initiative</li>
																	<li>Well behaved</li>
																	<li>Positive attitude</li>
																	<li>Good knowledge on SOPs & Systems</li>
																	<li>Disciplined</li>
																</ul>
															</td>
                                                            <td>
                                                                <div class="radio">
                                                                    <label>
                                                                        <input type="radio" value="B" id="b" data-ng-model="overall_rating" name="overall_rating" data-ng-required="!overall_rating" data-ng-change="clearingHOD();">
                                                                    </label>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="vcenter">Category - C</td>
                                                            <td>
																<ul>
																	<li>General commitment to job responsibilities</li>
																	<li>Quality conscious</li>
																	<li>Team member</li>
																	<li>Safety Conscious</li>
																</ul>
															</td>
                                                            <td>
																<ul>
																	<li>Average planning & organizing skills</li>
																	<li>Average learning attitude</li>
																	<li>Not a self-motivator</li>
																	<li>Disciplined</li>
																</ul>
															</td>
                                                            <td>
                                                                <div class="radio">
                                                                    <label>
                                                                        <input type="radio" value="C" data-ng-model="overall_rating" name="overall_rating" id="c" data-ng-required="!overall_rating" data-ng-change="clearingHOD();">
                                                                    </label>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="vcenter">Category - D</td>
                                                             <td>
																<ul>
																	<li>Poor working knowledge</li>
																	<li>No knowledge on SOPs & Systems</li>
																	<li>Less conscious Quality of work</li>
																</ul>
															</td>
                                                            <td>
																<ul>
																	<li>Cannot shoulder responsibilities</li>
																	<li>Poor discipline</li>
																	<li>Cannot handle anything independently</li>
																</ul>
															</td>
                                                            <td>
                                                                <div class="radio">
                                                                    <label>
                                                                        <input type="radio" value="D" data-ng-model="overall_rating" name="overall_rating" id="d" data-ng-required="!overall_rating" data-ng-change="clearingHOD();">
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
                                                        <textarea class="form-control" rows="5" id="hodComments"   maxlength="500" name="hodComments" data-ng-model="hodComments" data-ng-required="overall_rating == 'A' "></textarea>
                                                   <!-- data-ng-readonly="overall_rating != 'A' " -->
                                                 
                                                    
                                                    
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            <div class="col-sm-12" data-ng-show="prvCom">
                                                <div class="form-group">
                                                    <label class="control-label"><strong>Previous comments:</strong></label>
                                                    <div class="col-sm-12">
                                                        <textarea class="form-control" data-ng-model="otherRemarks1" name="otherRemarks1" rows="5" data-ng-disabled='true'></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                    <label class="control-label"><strong>Any other comments:</strong></label>
                                                    <div class="col-sm-12">
                                                        <textarea class="form-control" data-ng-model="otherRemarks" maxlength="500" name="otherRemarks" rows="5"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-sm-12">
                                                <small>
													<strong>Please select the check boxes for areas of improvement
													</strong>
												</small>
												
												 <input type="button"  data-toggle='modal' data-target='#myModalAreaslistview'  value="Details" />
												
                                            </div>
                                            <div class="col-sm-12">
                                                <div class="table-responsive">
                                                    <table class="table table-bordered table-striped">
                                                        <tr>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" data-ng-model="achk1" name="achk1" value="1"/>Work Knowledge</label>
                                                            </td>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" data-ng-model="achk2" name="achk2" value="2"/>Analytical Skills</label>
                                                            </td>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" data-ng-model="achk3" name="achk3" value="3"/>Communication Skills</label>
                                                            </td>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" data-ng-model="achk4" name="achk4" value="4"/>Interpersonal Skills</label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" data-ng-model="achk5" name="achk5" value="5"/>Team Work</label>
                                                            </td>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" data-ng-model="achk6" name="achk6" value="6"/>Attitude/Behaviour</label>
                                                            </td>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" data-ng-model="achk7" name="achk7" value="7"/>cGMP</label>
                                                            </td>
                                                            <td>
                                                                <label><input type="checkbox" data-ng-model="achk8" name="achk8" value="8" data-ng-change="achkOthers='' " />Others</label>
                                                                <label data-ng-show="achk8" ><input type="text" class="form-control" data-ng-model="achkOthers" maxlength="100" name="achkOthers" data-ng-required='achk8==true'  /></label>
                                                            </td>
                                                        </tr>
														<tr>
															<td colspan="4"><strong>Training Requirements</strong>
											 <input type="button"  data-toggle='modal' data-target='#myModalTraininglistview'  value="Details" />
															
															
															</td>
														</tr>
														 <tr>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" data-ng-model="tchk1" name="tchk1" value="1" />Functional Training</label>
                                                            </td>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" data-ng-model="tchk2" name="tchk2" value="2"/>Technical Training</label>
                                                            </td>
                                                            <td>
                                                                <label>
                                                                    <input type="checkbox" data-ng-model="tchk3" name="tchk3" value="3"/>Behavioral Training</label>
                                                            </td>
                                                            <td>
                                                                <label><input type="checkbox" data-ng-model="tchk4" name="tchk4" value="4" data-ng-change="tckOthers='' " />Others</label>
                                                                <label data-ng-show="tchk4" ><input type="text" class="form-control" data-ng-model="tckOthers" name="tckOthers" maxlength="100" data-ng-required='tchk4==true'/></label>
                                                            </td>
                                                        </tr>
                                                    </table>
													<div class="col-sm-12">
													</div>
                                                </div>
                                            </div>
											<div class="row" style="margin-bottom:1em">
												<div class="col-sm-5">
													<span><strong>*</strong></span>
													<label><strong>Recommended for Probation period completion : </strong></label>
												</div>
												<div class="col-sm-4">
												<!-- <label class="radio-inline"><input type="radio" data-ng-model="r1" value="r1" name="optradio" >Yes</label>
												<label class="radio-inline"><input type="radio" data-ng-model="r1" value="r2" name="optradio" >No</label> -->
												<label><input type="checkbox" data-ng-model="r2"  data-ng-change='r1=false;Contribution="Y";monthCount="" ' data-ng-required='Contribution==Y' >&nbsp;YES</label> &nbsp;&nbsp;
												<label><input type="checkbox" data-ng-model="r1"  data-ng-change='r2=false;Contribution="N" '  >&nbsp;No</label>
												<label data-ng-show="false"><input type="text" data-ng-model="Contribution" name="Contribution" /></label>
												
												</div>
											</div>
											
											<div class="row" data-ng-show="r1" style="margin-bottom:1em">
												<div class="col-sm-5" >
													<span><strong>*</strong></span>
													<label><strong>Please Provide Months to be Extended:</strong></label>
												</div>
												<div class="col-sm-4">
												<!-- data-ng-blur="monthsExtend();" -->
												<input type="number" min="1"  max="12" class="form-control" data-ng-model="monthCount" name="monthCount"  data-ng-required="Contribution=='N' ">
												</div>
											</div>
											
											<div class="row" style="margin-bottom:2em">
												<div class="col-sm-5">
													<span><strong>*</strong></span>
													<label><strong>Select to whom the approved document has to be sent:</strong></label>
												</div>
												<div class="form-group col-sm-4">
												<select class="form-control" data-ng-options="o.name for o in options" data-ng-model="selectedOption" ></select>
												<label data-ng-show="false"><input type="text" data-ng-model="docFwd" name="docFwd" data-ng-value="selectedOption.value"/></label>
												
												</div>
												
												
												
											</div>

												<div class="col-sm-12" style="margin-bottom:1em">
													<div class="text-center">
														<input type="submit" class="form-control" style="width:150px;margin:0px auto;background:#0070bb;color:white;height: 45px;font-weight:600" data-ng-model="FormSub" name="FormSub" Value="Submit Form" data-ng-show='displyBtn' />
														<label data-ng-show="false"><input type="text" data-ng-model="mrgFiller" name="mrgFiller"/></label>
												
													</div>
												</div>


											</div>
                                        <!-- Panel Body-->
                                        </form>
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
				<!-- end: page -->


		<!-- end: page -->
		<!--Change Password Modal-->


	</section>
	</div>
	</section>
	
<div class="modal fade" id="myModalAreaslistview" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Status</h4>
        </div>
        <div class="modal-body">
    <table class="table">
		<thead>
			<tr>
			<th >By</th>
			<th>status</th>
			</tr>
	</thead>
		<tbody>
			 <tr data-ng-repeat=" y in Allcommentslist" ng-show="Allcommentslist.length!=0 " >
				<td>  {{ y.createdby }}   </td>
				<td>  {{ y.areacode }}   </td>
             </tr> 
              </tr> 
             <tr> <td ng-show="Allcommentslist.length==0 ||Allcommentslist=='null' || Allcommentslist=='undefined' ">
      No data.                
        </td>
    </tr>	
       </tbody>
</table>
        </div>
        <div class="modal-footer">
      <!--     <button type="button" class="btn btn-default btn-sm modalclose" id="modalclose" data-dismiss="modal">Close</button> -->
        </div>
      </div>
    </div>
</div>
	
<div class="modal fade" id="myModalTraininglistview" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Status</h4>
        </div>
        <div class="modal-body">
    <table class="table">
		<thead>
			<tr>
			<th >By</th>
			<th>status</th>
			</tr>
	</thead>
		<tbody>
			<tr data-ng-repeat=" y in Allcommentslist" ng-show="Allcommentslist.length!=0 ">
				<td>  {{ y.createdby }}   </td>
				<td>  {{ y.trainingcode }}   </td>
             </tr> 
              </tr> 
             <tr> <td ng-show="Allcommentslist.length==0 ||Allcommentslist=='null' || Allcommentslist=='undefined' ">
      No data.                
        </td>
    </tr>	
       </tbody>
</table>
        </div>
        <div class="modal-footer">
      <!--     <button type="button" class="btn btn-default btn-sm modalclose" id="modalclose" data-dismiss="modal">Close</button> -->
        </div>
      </div>
    </div>
</div>
<div class="modal fade" id="myModaloverallratinglistview" role="dialog">
    <div class="modal-dialog"  style="width:85% ">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Status</h4>
        </div>
        <div class="modal-body">
    <table class="table status">
		<thead>
			<tr>
			<th >By</th>
			<th>OverallRating</th>
			<th >HODComments</th>
			<th>ExtendMonths</th>
			</tr>
	</thead>
		<tbody>
			<tr data-ng-repeat=" y in Allcommentslist"  ng-show="Allcommentslist.length!=0 ">
				<td>  {{ y.createdby }}   </td>
				<td>  {{ y.rating }}   </td>
				<td>  {{ y.hodcomments }}   </td>
				<td>  {{ y.extendmonths }}   </td>
             </tr> 
             <tr> <td ng-show="Allcommentslist.length==0 ||Allcommentslist=='null' || Allcommentslist=='undefined' ">
      No data.                
        </td>
    </tr>	
       </tbody>
</table>

        </div>
        <div class="modal-footer">
      <!--     <button type="button" class="btn btn-default btn-sm modalclose" id="modalclose" data-dismiss="modal">Close</button> -->
        </div>
      </div>
    </div>
</div>


	
		<script src="assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
		<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
		<script src="assets/vendor/nanoscroller/nanoscroller.js"></script>
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
	
	
	
	<script type="text/javascript">
$("#Date").datepicker({
	dateFormat : 'yy-mm-dd',
	changeMonth: true,
    changeYear: true,
    yearRange: '1950:2050'

});

</script>



</body>
</html>