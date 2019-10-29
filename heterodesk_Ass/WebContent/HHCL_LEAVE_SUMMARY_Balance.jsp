<!doctype html>
<html class="fixed">


<% 
  
  String Emp_BioData=(String)request.getAttribute("LeaveBalance");

  
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
		
<link rel="stylesheet" href="stylesheets/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="javascripts/bootstrap.min.js"></script>
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
         <link href="assets/stylesheets/tooltip.css" rel="stylesheet" type="text/css" />
    <script src="assets/javascripts/tooltip.js" type="text/javascript"></script>
        <style>
      #description {
        border: 1px solid gray;
        height:100%;
        width: 100%;
        overflow: hidden;
        position: absolute;
      }
      .sai{
      font-weight:normal;
      font-style:normal;
      
      }
      
      
       h3 { font: normal 20px/30px Arial;}
        h4 { font-family: "Trebuchet MS", Verdana; }    
        #span4 img {cursor:pointer;margin:20px;}   
        
        .left {
  border-left: thin solid;
  border-color: black;
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
								<div class="panel panel-danger" style="margin-top: 4%;">
									<div class="panel-heading1">
										<span><b>Leave Balance Report <!-- </b><a href="#"><span class="note" href="src/tooltip-ajax.html" width="320px"  style='cursor:hand;color:red;' onmouseover="tooltip.ajax(this, 'src/tooltip-ajax.html');" onclick="return false;">Note</span></a>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span id='Requ_Date_ERROR' style='color:white'> --> </span>
									<!-- 	<a href="src/tooltip-ajax.html" onmouseover="tooltip.ajax(this, 'src/tooltip-ajax.html');" onclick="return false;"><span class="note tooltip red-tooltip">Note</span> </a> --></span>
							
							<!-- <p><a class="tooltip" href="src/tooltip-ajax.html" onmouseover="tooltip.ajax(this, 'src/tooltip-ajax.html');" onclick="return false;">triggered by hijax link</a></p> -->
							 <script type="text/javascript">
        //don't copy the below script into your page.
        if (!document.domain) alert("The Ajax will not work if opening the page by local path instead of through HTTP on a web or localhost server");    
    </script>
							 <script type="text/javascript">

        var myAjaxSetting = {
            context: { index: -1 },
            success: myCallback,
            responseType: "xml"
        };

        function myCallback(response, context) {
            var x = response.documentElement.getElementsByTagName("cd")[context.index];
            var title = x.getElementsByTagName("title")[0].childNodes[0].nodeValue;
            var artist = x.getElementsByTagName("artist")[0].childNodes[0].nodeValue;
            var price = x.getElementsByTagName("price")[0].childNodes[0].nodeValue;
            var image = "<img src='src/tooltips-cd" + context.index + ".jpg' style='float:right;margin-left:12px;width:75px;height:75px;' />";
            return "<div style='width:220px;'>" + image + "<b>" + title + "</b><br /><i>" + artist + "</i><br /><br />Price: <span class='red'>$" + price + "</span></div>";
        }   

    </script>
							<!-- 	 <script>
															$(document).ready(function(){
															    $('[data-toggle="tooltip"]').tooltip();   
															});
														</script> -->
									</div>
									<div class="panel-body">
									<div >
										<div class="table-responsive">
										
										
									<!-- 	<table class="table table-striped">
															<tr  ng-repeat="(key, value) in Data_1" ><td style="">{{ key }}</td> <td>{{ value }}</td></tr>
								                   </table>	 -->
								                   
								            <table class="table">
											             
											             <thead class="thead-default text-center">
														<tr >
															<th>Leave Type</th>
															<th>Leave Quota</th>
															<th>Used Leaves</th>
															
															<th>Deducted Leaves</th>
															
															<th>Available Leaves</th>
															<!-- <th class='left' >Pending</th>
															<th>Approved</th>
															<th>Reject</th> -->
															
														</tr>
													</thead>
													
				<!-- 		 var markup = "<tr><td>" + tempjason.FULLNAME + " <input type='hidden' id='"+ tempjason.SHORTNAME+"' 
						 value='"+tempjason.AVAIL+"-"+tempjason.MAXLEAVE+"-"+tempjason.DAYMODE+"-"+tempjason.COUNT_WOFF+"-"+tempjason.BACKDATE+"' >
						 </td><td>" + tempjason.QTY + "</td><td>" + tempjason.USEDQTY + "</td> <td> " + tempjason.AVAIL + " </td>
						  </tr>"; -->
			               										
														<tbody class="sai">														
														<tr ng-repeat="x in Data_1">
														
														<td>{{x.FULLNAME}}</td>
														<td>{{x.QTY}}</td>
														<td>{{x.USEDQTY}}</td>
														<td>{{x.DEDUCTED}}</td>
														<td>{{x.AVAIL}}</td>
															<!-- <td>Casual Leaves</td><td> {{Data_1.CL_TOT}} </td> <td>{{Data_1.CL_USED}} </td><td> {{Data_1.CL_REM}}</td>
															<td class='left'>{{Data_1.CL_P}}</td> <td><span style='color:green'>{{Data_1.CL_A}}</span></td> <td><span style='color:red'> {{Data_1.CL_R}}</span></td> -->
														</tr>
														
													<!-- 	<tr >
															<td>Sick Leaves</td><td>{{Data_1.SL_TOT}}</td> <td>{{Data_1.SL_USED}} </td> <td>{{Data_1.SL_REM}}</td>
															<td class='left'>{{Data_1.SL_P}}</td> <td><span style='color:green'> {{Data_1.SL_A}}</span></td> <td><span style='color:red'> {{Data_1.SL_R}}</span></td>
														</tr>
														
														
														
														<tr >
															<td  >Casual Leaves</td><td>Total:</td> <td>Used:</td> <td>Rem:</td>
															<td class='left'>Applied:</td> <td>Approved:</td> <td>Reject:</td><td>Pending:</td>
														</tr>
														
														<tr >
															<td  >Earned Leaves</td><td>{{Data_1.EL_TOT}} </td> <td>{{Data_1.EL_USED}}</td> <td>{{Data_1.EL_REM}}</td>
															<td class='left'>{{Data_1.EL_P}}</td> <td><span style='color:green'> {{Data_1.EL_A}}</span></td> <td><span style='color:red'> {{Data_1.EL_R}}</span></td>
														</tr>
														
														<tr >
															<td  >COMP-Off</td><td>--</td> <td>{{Data_1.CF_USED}} </td> <td>--</td>
															<td class='left'>{{Data_1.CF_P}}</td> <td><span style='color:green'>{{Data_1.CF_A}}</span></td> <td><span style='color:red'> {{Data_1.CF_R}}</span></td>
														</tr>
														
														 <tr >
															<td>OnDuty</td><td>--</td> <td>{{Data_1.OD_USED}}</td> <td>--</td>
															<td class='left'>{{Data_1.OD_P}}</td> <td><span style='color:green'> {{Data_1.OD_A}}</span></td> <td><span style='color:red'> {{Data_1.OD_R}}</span></td>
														</tr> 
														
														<tr >
															<td>LOP</td> <td>{{Data_1.LOP}}</td> <td>--</td> <td>--</td>
															<td class='left'>--</td> <td>--</td> <td>--</td>
														</tr> -->
														</tbody>
														<!-- <tr >
															<td><td><td>&nbsp;&nbsp;&nbsp;<td> <td>&nbsp;&nbsp;&nbsp;<td> <td>&nbsp;&nbsp;<td>
															<td class='left'>&nbsp;&nbsp;&nbsp;<td> <td>&nbsp;&nbsp;&nbsp;<td> <td>&nbsp;&nbsp;&nbsp;<td><td>&nbsp;&nbsp;&nbsp;<td>
														</tr> -->
														
													<!-- </thead> -->
													<!-- <tr >
														    <td  >10<td><td>5<td><td>5<td>
															<td  >10<td><td>5<td><td>5<td>
															<td  >10<td><td>5<td><td>5<td>
															
															<td  >10<td><td>5<td><td>5<td>
															<td  >10<td><td>5<td><td>5<td>
															<td  >10<td><td>5<td><td>5<td>
															
															<td  >10<td><td>5<td><td>5<td>
															<td  >10<td><td>5<td><td>5<td>
															<td  >10<td><td>5<td><td>5<td>
														
													</tr> -->
													
											</table>	
											<!-- <div class="panel-heading1">
											 <span >Applied Leaves Status</span>
											</div>
											<table class="table table-striped">
											
													<thead class="thead-default text-center">
														<tr   >
															<th>Applied date</th>
															<th>From Date</th>
															<th>To Date</th>
															<th>Reason</th>
															<th>Leave Status</th>
															<th>E-mail status</th>
															<th>Approved by/Comment</th>
															<th>Leave Cancel</th>
														</tr>
													</thead>
													<tr ng-repeat="x in Data_1">
														<td>{{ x.REQ_DATE }}</td>
														<td >{{ x.FRM_DATE }}  </td>
														<td>{{ x.TO_DATE }}</td>
														<td><a href="#"><span    class="red-tooltip"  data-toggle="tooltip" data-placement="bottom" title='{{ x.MESSAGE }} ' > <span class="glyphicon glyphicon-envelope"></span>  </span></a></td>
														<td>{{ x.LEAVE_STATUS }}</td>
														<td>{{ x.MAIL_STATUS }}</td>
														<td> <a href="#"><span    class="red-tooltip"  data-toggle="tooltip" data-placement="top" title='{{ x.COMMENT }}' >  {{ x.APPROVEDNAME }} </span></a>  </td>
														
														<td ><input type='button' class='btn btn-primary btn-sm'  name='fg'  id='{{ x.RID }}'  style='display:{{ x.BUTSTA }}' onclick='ICT_Req(this);'  value='Cancel' > </td>
														
													</tr>
													
											</table>	 -->
										</div>
									</div>
									</div>
									
								</div>
							</div>
						</div>	
						
							<span > 
						<p style='text-size:7px;'>Available Leaves = Total Leaves &#8722; Used Leaves</p>
						<p style='text-size:7px;'>Note : Used leaves count includes deducted leaves as well (Deducted leaves have been calculated as per the Flexi timings policy)</p>
						</span>
						<!-- <span style='text-size:10px;'>Note:Leave quota not applicable.</span> -->
						
					</div>
					<!-- end: page -->
		</div>
		
		<script>
		
		function ICT_Req(data){
			 
			 //document.getElementById("Requ_Date_ERROR").innerHTML='Please Submit your approvals before payroll closing';
			/*  document.getElementById("date").value=data.id; 
			 document.getElementById("Requ_Date").innerHTML=data.id;
			 document.getElementById("Responce_Message").innerHTML='';
			 document.getElementById("Responce_Message_btn").style.display='';
			 document.getElementById("Requ_Date_Temp").value=data.name; 
			  */
			 var person = prompt("Reason For Leave Cancel..!", "No_Data");
			  if(person!=null){
			  AttendanceRequest_Month_VEN(data.id,person);
			  }else{
				  return false;
			  }
			  
		 }

		 function AttendanceRequest_Month_VEN(val1,val2){
			 
			  /* alert(val1 + "~"  + val2 + "~" + val3 );
			    if(val3=="Approve"){
			    	val3="A";
			    }else if(val3=="Canceld"){
			    	val3="C";
			    }else if(val3=="Reject"){
			    	val3="R";
			    } */
				var formData = {ROUTING:"EMPLOYEE",rid:""+val1+"",flag:"SC",comment:""+val2+""};
			try{
			    $.ajax({
			          type: "post",
			          url: "ReqAcpt_app_emp",
			          data: formData,
			          success: function(responseData, textStatus, jqXHR) {
			              document.getElementById("Requ_Date_ERROR").innerHTML=responseData;
			              try{
			        	  if(responseData=="Successfully Submit."){
			        		  document.getElementById(val1).style.display='none';
			        		  
			        	  }
			              }catch(err){
			            	  
			              }
			        	 
			          },
			          error: function(jqXHR, textStatus, errorThrown) {
			              console.log(errorThrown);
			              document.getElementById("Requ_Date_ERROR").innerHTML=errorThrown;
			              //alert("Error;");
			          }
			      })
			}catch(err){
				 alert(err.value);
			}
					return  eval(responseData);
		}
		
		</script>

	<script>
			$(document).ready(function(){
			 $('[data-toggle="tooltip"]').tooltip();   
			});
	</script>


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