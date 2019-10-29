<!doctype html>
<html class="fixed">
	<head>
	 <link rel="stylesheet" href="stylesheets/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="javascripts/bootstrap.min.js"></script>
  
   <link href="assets/stylesheets/tooltip.css" rel="stylesheet" type="text/css" />
   <script src="assets/javascripts/tooltip.js" type="text/javascript"></script>
    
<%@page import="java.util.*" %>
<% 
  
  String Emp_BioData=(String)request.getAttribute("DOJ_DOB");
  String TDSFLAG=(String)session.getAttribute("TDSFLAG");
  String EMP_NAME=(String)session.getAttribute("EMP_NAME");
  
  String ATT_MONTHS=(String)request.getAttribute("ATT_MONTHS");
  String HR_LINKS=(String)session.getAttribute("HR_LINKS");
%>
<%
	Random rand = new Random();
	int nRand = rand.nextInt(90000) + 10000;
%>

	<head>
	<script src="MyAng.js"></script>
<script>

var app = angular.module('myApp', []);
app.controller('formCtrl', function($scope,$http) {
	var Data="";
	$scope.Data_2=<%=ATT_MONTHS%>;
	$scope.Data_1="";
	$scope.Data_3="";
	$scope.xy=1;
	 $scope.Data_1=<%=Emp_BioData%>;
	 
	 $scope.HR_LINKS=<%=HR_LINKS%>;
	 
	 
	 
try{
	 $scope.myFunction = function(val) {
		 
		 document.getElementById("errMessage").innerHTML="";
		if(val=="My") {
			
			document.getElementById("errMessage").innerHTML="";
				
			document.getElementById("from_1").value="";
			document.getElementById("to_1").value="";
			 
		 var Month_Sel=document.getElementById("Month_Sel").value;
		 $scope.Data_1="";
		 
		 $http({
		        method : "POST",
		        url : "Attendance?Routing=ATTENDANCE_BIO_DATES&ATT_FLAG=MONTHS&Month="+Month_Sel+"",
		        
		        		
		    }).then(function mySucces(response) {
		    	$scope.Data_1 = response.data;
		       // alert(response.data);
		    }, function myError(response) {
		        $scope.Data_1 = response.statusText;
		    });
		}
		
		if(val=="My_Datas"){
			
			document.getElementById("errMessage").innerHTML="";
			
			var Month_FROM="";
			var Month_TO="";
			
			try{
			 Month_FROM=document.getElementById("from_1").value;
			 Month_TO=document.getElementById("to_1").value;
			
			  // alert(Month_FROM.length +"~haa~"+Month_TO.length );
			 
			 
			}catch(err){
				alert(err.message);
			}
			
			
			var Month_Sel="";
			 if(Month_FROM.length<1 || Month_TO.length<1 ){
				
				
				 document.getElementById("errMessage").innerHTML="Please Select From & To Date";
				 
				 //alert("Please Select From & To Date");
				 
				     return false;
			 } 
			 
			 else if(Month_FROM.length>1 && Month_TO.length>1)
				 {
				 
				 
				    var fromdate = Month_FROM;
					var todate = Month_TO;
					var from = new Date(fromdate);
					var to = new Date(todate);
					
					var diffDays = parseInt((to - from) / (1000 * 60 * 60 * 24));
					var Days = diffDays + 1;
					 // alert(Days);
					  if(Days<0 ){
						    
						  document.getElementById("errMessage").innerHTML='From date should be < To Date';
						  //alert("From date should be < To Date");
						   return false;
						   
					  }else if(Days>60){
						  
						  document.getElementById("errMessage").innerHTML="Please select period of 60 or <60 days only";
					 //alert("Please select period of 60 or <60 days only");
					return false;
					}
				 }
			 
			 $scope.Data_1="";
			 
			 $http({
			        method : "POST",
			        url : "Attendance?Routing=ATTENDANCE_BIO_DATES&ATT_FLAG=DATES&Month_FROM="+Month_FROM+"&Month_TO="+Month_TO+" ",
			        
			        		
			    }).then(function mySucces(response) {
			    	$scope.Data_1 = response.data;
			       // alert(response.data);
			    }, function myError(response) {
			        $scope.Data_1 = response.statusText;
			    });
			
			
		}
		
		
		 
		 
	    }
	 
	
		
 		

		   <%-- 	 $scope.Data_1=<%=Emp_BioData%>;
   
 $scope.Data_2=<%=EmpDOB%>;
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

<script>

 function ICT_Req(data){
	 
	 document.getElementById("Requ_Date_ERROR").innerHTML='Please Submit your approvals before payroll closing';
	/*  document.getElementById("date").value=data.id; 
	 document.getElementById("Requ_Date").innerHTML=data.id;
	 document.getElementById("Responce_Message").innerHTML='';
	 document.getElementById("Responce_Message_btn").style.display='';
	 
	 document.getElementById("Requ_Date_Temp").value=data.name; 
	  */
var promptsdd=window.prompt("Comment/Feed Back");
	  
	  //alert(promptsdd);
	  
	  if(promptsdd!=null){
	  AttendanceRequest_Month_VEN(data.alt,data.name,data.value,data.id,promptsdd);
	  }else{
		  document.getElementById("Requ_Date_ERROR").innerHTML="Please Fill the Comment/Feed Back message "
	  }
 }

 function AttendanceRequest_Month_VEN(val1,val2,val3,val4,promptsdd){
	 
	 // alert(val1 + "~"  + val2 + "~" + val3 );
	  
	    if(val3=="Approve"){
	    	val3="A";
	    }else if(val3=="Cancel"){
	    	val3="MC";
	    }else if(val3=="Reject"){
	    	val3="R";
	    }
		var formData = {id:""+val1+"",rid:""+val2+"",flag:""+val3+"",APP_MOD:"HR" ,comment:""+promptsdd+""};
	try{
	    $.ajax({
	          type: "post",
	          url: "ReqAcpt_Resi",
	          data: formData,
	          success: function(responseData, textStatus, jqXHR) {
	            
	        	  
	        	  
	        	  document.getElementById("Requ_Date_ERROR").innerHTML=responseData;
	        	  
	        	  if(responseData.indexOf("Successfully")!=-1 && val3=="A"){
	        		 // alert("Hai");
	        		  document.getElementById(""+val2+"_1").style.display='none';
	        		  document.getElementById(""+val2+"_1").value="Cancel";
	        		  document.getElementById(""+val2+"_2").style.display='none';
	        		 // document.getElementById(""+val2+"_ST").innerHTML="Approved";
	        		  
	        	  }else if(responseData.indexOf("Successfully")!=-1 && (val3=="R"||val3=="MC" )){
	        		  document.getElementById(""+val2+"_1").style.display='none';
	        		  document.getElementById(""+val2+"_2").style.display='none';
	        		 /// document.getElementById(""+val2+"_ST").innerHTML="Rejected";
	        	  }
	        	  
	        	  
	        	  
	        	   // alert("responseData::"+responseData);
	              // var resp=eval(responseData);
	               
				 //  alert("resp::"+resp);
				   
				   //$scope.Data_1=eval(responseData);
				  
				   //return  eval(responseData);
                    
				   //myFunction('My');
				   
				   
					   
	             /* try{
	             }catch(err){
	            	 alert(err);
	             } */
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

 
 function AttendanceRequest(){
	 var date_Temp=document.getElementById("Requ_Date_Temp").value;
	 var Data_Split="";
	 var ReQDate="";
	 var FIN="";
	 var FOUT="";
	 var TIME="";
	 
	 try{
	  	Data_Split=date_Temp.split("#");
	  	ReQDate=Data_Split[0];
	  	FIN=Data_Split[1];
	  	FOUT=Data_Split[2];
	  	TIME=Data_Split[3];
	 }catch(err){
		 alert(err);
	 }

 var  ccemail=document.getElementById("tags3").value;
 var toemail=document.getElementById("tags2").value;
 var message=document.getElementById("message").value;

 
    if(toemail.length <4){
    	
    	document.getElementById("Responce_Message").innerHTML="Please Enter To mail id.";
    	return false;
    	
    } else if(message.length < 3){
    	
    	document.getElementById("Responce_Message").innerHTML="Please Enter Request Message";
    	return false;
    	
    } 


	 
	   var date=document.getElementById("date").value;
	   var Subject=document.getElementById("Subject").value;
	   
		var formData = {toemail:""+toemail+"",ccemail:""+ccemail+"",Routing:"Att_Request",id:""+ReQDate+"",Subject:""+Subject+"",message:""+message+"",FIN:""+FIN+"",FOUT:""+FOUT+"",TIME:""+TIME+"",RanDm:"<%=nRand%>" };
		
	try{
	    $.ajax({
	          type: "post",
	         // url: "Attendance",
	          data: formData,
	          success: function(responseData, textStatus, jqXHR) {
	              //alert(responseData);
	             // var resp=eval(responseData);
	             try{
	              document.getElementById("Responce_Message").innerHTML=responseData;
	              document.getElementById("Responce_Message_btn").style.display='none';
	             // alert("date:"+date);
	              //document.getElementById(""+date+"").innerHTML='Processed';
	              document.getElementById(date).style.display='none';
	              document.getElementById(date+"_ST").innerHTML='Processed';
	             		//document.getElementById("date").value='';
	       	   			//document.getElementById("Subject").value='';
	       	  			document.getElementById("message").value='';
	       	  	
	             }catch(err){
	            	 //alert(err);
	             }
	          },
	          error: function(jqXHR, textStatus, errorThrown) {
	              console.log(errorThrown);
	              document.getElementById("Responce_Message").innerHTML=errorThrown;
	              document.getElementById("Responce_Message_btn").style.display='';
	             // alert("Error;");
	          }
	      })
	}catch(err){
		
		//alert(err.value);
	}
	  
 }
 
 
 
 function AttendanceRequest_Month(){
	 
	  // alert("2");
		var formData = {Routing:"ATTENDANCE_BIO_DATES", FromDate:"20-20-11",ToDate:"22-20-1986"  };
	try{
	    $.ajax({
	          type: "post",
	          url: "Attendance",
	          data: formData,
	          success: function(responseData, textStatus, jqXHR) {
	            
	               var resp=eval(responseData);
	               
				   //alert("resp::"+resp);
				   
				   $scope.Data_1=eval(responseData);
				  
				   //return  eval(responseData);
                     
				   //myFunction('My');
				   
				   
					   
	             /* try{
	             }catch(err){
	            	 alert(err);
	             } */
	          },
	          error: function(jqXHR, textStatus, errorThrown) {
	              console.log(errorThrown);
	              alert("Error;");
	          }
	      })
	}catch(err){
		 alert(err.value);
	}
	return  eval(responseData);
}
 
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
		
   <!-- <script src="jquery.js"></script>
  <script src="jqueryui.js"></script> -->
		   <script>
 /*  $( function() {
    
      from = $( "#from_1" )
        .datepicker({
           changeMonth: true,
           changeYear:true,
          dateFormat: "yy-mm-dd",
          maxDate: 0,
          yearRange: '2016:2020',
		  
        })
        .on( "change", function() {
          to.datepicker( "option", "minDate", getDate( this ) );
        }),
      to = $( "#to_1" ).datepicker({
         changeMonth: true,
         changeYear:true,
		 dateFormat: "yy-mm-dd",
		 maxDate: 0,
		 yearRange: '2016:2020',
		 
		
        
      })
      .on( "change", function() {
        from.datepicker( "option", "maxDate", getDate( this ) );
      });
 
    function getDate( element ) {
      var date;
      try {
        date = $.datepicker.parseDate( dateFormat, element.value );
      } catch( error ) {
        date = null;
      }
 
      return date;
    }
  } ); */
  $( function() {
  $("#from_1").datepicker({
	  
	  changeMonth: true,
      changeYear:true,
		maxDate : 0,
		dateFormat : 'yy-mm-dd',
		 yearRange: '2016:2020',
		onSelect : function(date) {
			$("#to_1").datepicker('option', 'minDate', date);
		}
	});

	$("#to_1").datepicker({
		changeMonth: true,
        changeYear:true,
        maxDate: 0,
		dateFormat : 'yy-mm-dd',
			 yearRange: '2016:2020'

	});
  });

  </script>
		<!-- <style>
		tbody{
		display:block;
		height:350px;
		width:auto;
		overflow-y:auto;
		}
		thead,tbody tr{
		display:table;
		width:100%;
		table-layout:fixed;
		}
		thead{
		width: calc(100%-4em);
		}
		</style> -->


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
    $( ".addresspicker" ).autocomplete({
        source: availableTags
      });
  // $('#myModal').modal('show');
  
    $( "#tags2" )
      // don't navigate away from the field on tab when selecting an item
      .on( "keydown", function( event ) {
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
          this.value = terms.join( "," );
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
 
    $( "#tags3" )
      // don't navigate away from the field on tab when selecting an item
      .on( "keydown", function( event ) {
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
          this.value = terms.join( "," );
          return false;
        }
      });
  } );
  </script> 
 <style>
.ui-autocomplete-input {
  border: none; 
  font-size: 14px;
  width:100%;
  height: 34px;
  margin-bottom: 5px;
  padding-top: 2px;
  border: 1px solid #DDD !important;
  padding-top: 0px !important;
  z-index: 1511;
  position: relative;
}
.ui-menu .ui-menu-item a {
  font-size: 12px;
  color:#0088cc;
}
.ui-autocomplete {
  position: absolute;
  top: 100%;
  left: 0;
  z-index: 1051 !important;
  float: left;
  display: none;
  min-width: 160px;
  _width: 160px;
  padding: 4px 0;
  margin: 2px 0 0 0;
  list-style:none;
  background-color: #ffffff;
  color:#0088cc;
  border-color: #ccc;
  border-color: rgba(0, 0, 0, 0.2);
  border-style: solid;
  border-width: 1px;
  -webkit-border-radius: 2px;
  -moz-border-radius: 2px;
  border-radius: 2px;
  -webkit-box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
  -moz-box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
  -webkit-background-clip: padding-box;
  -moz-background-clip: padding;
  background-clip: padding-box;
  *border-right-width: 2px;
  *border-bottom-width: 2px;
}
.ui-menu-item > a.ui-corner-all {
    display: block;
    padding: 3px 15px;
    clear: both;
    font-weight: normal;
    line-height: 18px;
    color: #0088cc;
    white-space: nowrap;
    text-decoration: none;
}
/* .ui-state-hover, .ui-state-active {
      color: #ffffff;
      text-decoration: none;
      background-color: #0088cc;
      border-radius: 0px;
      -webkit-border-radius: 0px;
      -moz-border-radius: 0px;
      background-image: none;
} */
.ui-state-hover,
.ui-widget-content .ui-state-hover,
.ui-widget-header .ui-state-hover,
.ui-state-focus,
.ui-widget-content .ui-state-focus,
.ui-widget-header .ui-state-focus {
	border: 1px solid #fbcb09;
	background: #fdf5ce url("images/ui-bg_glass_100_fdf5ce_1x400.png") 50% 50% repeat-x;
	font-weight: bold;
	color: #0088cc;
}
.ui-state-hover a,
.ui-state-hover a:hover,
.ui-state-hover a:link,
.ui-state-hover a:visited,
.ui-state-focus a,
.ui-state-focus a:hover,
.ui-state-focus a:link,
.ui-state-focus a:visited {
	color: #0088cc;
	text-decoration: none;
}
/* #modalIns{
    width: 500px;
} */
.select {
	border: 1px solid #E5E7E9;
	border-radius: 6px;
	height: 25px;
	padding: 2px;
	outline: none;
	color:#0088cc;
	margin-bottom:10px;
	
}

.panel-headingattn{
        height:50px;
		background-color:#0088cc;
		line-height:20px;
		color:#fff;
		padding-left:20px;
		padding-top:10px;
		
}
.btn1{
  display: inline-block;
  margin-bottom: 0;
  font-weight: normal;
  text-align: center;
  vertical-align: middle;
  touch-action: manipulation;
  cursor: pointer;
  background-image: none;
  border: 1px solid transparent;
  white-space: nowrap;
  padding: 3px 3px;
  font-size: 10px;
  line-height: 1.42857143;
  border-radius: 4px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  background-color:#fff;
  color:#0088cc;
}
.form-control1 {
    display: block;
    width: 100%;
    height: 20px;
    padding: 3px 6px;
    font-size: 10px;
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

<script>
  function disableBackButton() {
	   window.history.forward();
	}
	setTimeout("disableBackButton()", 0);

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
	<body  ng-app="myApp" ng-controller="formCtrl"  onload="disableBackButton(); noBack();">
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
									
									
								<li style='display:{{ HR_LINKS.LINK3 }}' >
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
									
									<!--  <li>
									  <a href="ManagerApprovals?Routing=Dept_att" target="_parent">
										<i class="fa fa-users" ></i>
										<span class="font-bold">Dep.Attendance</span>
									  </a>
									</li> -->
									
									<!-- <li>
									  <a href="PayslipDownload">
										<i class="fa fa-download"></i>
										<span class="font-bold">Attendance Approvals</span>
									  </a>
									</li>
									
									<li>
									  <a href="PayslipDownload">
										<i class="fa fa-download"></i>
										<span class="font-bold">Dep. Attendance Summary</span>
									  </a>
									</li> -->
									
									<%-- <li>
									  <a href="hhcl_careers.jsp">
										<i class="fa fa-briefcase"></i>
										<span class="font-bold">Careers</span>
									  </a>
									</li>
									
									<li style="display:<%=TDSFLAG%>;">
									  <a href="http://mydesk.heterohcl.com/IT/" target="_blank">
										<i class="fa fa-info-circle"></i>
										<span class="font-bold">TDS Declaration</span>
									  </a>
									</li>
									
									<li>
									  <a href="NewJoinees?Routing=DEPINFO">
										<i class="fa fa-info-circle"></i>
										<span class="font-bold">Department Information</span>
									  </a>
									</li>
									<li>
									  <a href="http://www.heterohealthcare.com/" target="_blank">
										<i class="fa fa-building-o "></i>
										<span class="font-bold">About Our Organization</span>
									  </a>
									</li>
									
									<li>
										<!-- <div id="datepicker" class="calendar"></div> -->
									</li> --%>
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
					<!-- start: page  ng-selected="item.selected == true"  ng-change='myFunction(this.value);' -->
					 <!--  <select id="Month_Sel" >
					  <option  ng-repeat="(key, value) in Data_2" ng-click="key==9" value="{{key}}"> {{value}} </option>
					  </select> -->
					   &nbsp; &nbsp; &nbsp; 
					   
					    <div id='errMessage' align='center' style='color:red'> OUR MOST VALUABLE RESOURCE IS OUR EMPLOYEE   </div>
						<div class="row" style="padding-left:50px;">
						  
						  
							<div class="col-xs-12 col-sm-6 col-md-11">
								<div class="panel panel-primary">
								   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								   <span id='Requ_Date_ERROR' style='color:red;' align='right'>   </span>
									<div class="panel-headingattn">
										<!-- <div class="col-md-3" style="margin-top:5px;">
										 <select class="select" id="Month_Sel" >
									  		<option  ng-repeat="(key, value) in Data_2"  value="{{key}}"  ng-selected="currmonth"> {{value}} </option>
									  	</select>&nbsp;&nbsp;&nbsp;<button class="btn1"  ng-click="myFunction('My')">View</button>
									  	</div>
									  	<div class="col-md-6" style="margin-top:5px;">
									  	<span class="col-sm-1">From</span>
									  	<div class="col-sm-3">
									  	<input type="text" id="from_1" name="from"  readOnly class="form-control1">
									  	</div>
									  	<span class="col-sm-1">To</span>
									  	<div class="col-sm-3">
									  	<input type="text" id="to_1" name="to" readOnly class="form-control1">
									  	</div>
									  	<button class="btn1"  ng-click="myFunction('My_Datas')">View</button>
									  </div> -->
									  <div class="col-md-3">
										<span><i class="fa fa-calendar fa-lg"></i>&nbsp;Resignation Approvals 
										
										<!-- <a href="#"><span class="note" href="src/tooltip_map.html" width="320px"  style='cursor:hand;color:red;' onmouseover="tooltip.ajax(this, 'src/tooltip_map.html');" onclick="return false;">Note</span></a> -->
										</span>
										
										
										</div>
										
										<!-- <button onclick="AttendanceRequest_Month();"> ClickAjex </button> &nbsp; <button ng-click="myFunction('My')">Click Me!</button> -->	
										
									</div>
									
									<div class="panel-body">
										<div class="col-md-12">
											<div class="table-responsive">
												<table class="table table-striped ">
													<thead class="thead-default " style="background-color:#f2f2f2; color:#777777;">
														<tr>
															
															<th class="text-center">ID</th>
															<th class="text-center">NAME</th>
														   <th class="text-center">Applied On</th> 
															<th class="text-center">Reason</th>
															<!-- <th class="text-center">To Date</th> -->
														   <th class="text-center">Feed Back</th>
														 	<!-- <th class="text-center">Approve Status</th> -->
															
															 <th class="text-center">Action By(Mgr)</th>
															<!-- <th class="text-center">Approver Status</th> -->
															
															<th class="text-center" colspan='2'>Action</th>
															
															
															
															
														</tr>
													</thead>
											
						
						
													<tr ng-repeat="x in Data_1">
													
													  <!--   ID,NAME,DEPT,SUBJECT,DURATION,DAYS,LEAVE_TYPE,Manager_Status  -->
														 <td class="text-center">{{ x.ID }}</td>
														  <td class="text-center">{{ x.NAME }}</td> 
														 <td class="text-center">{{ x.Applied }}</td> 
														<!--  <td class="text-center">{{ x.Re }}</td> -->
													<!--  <td class="text-center"><a href="#"><span  id="{{ x.DATE }}_ST"  class="red-tooltip"  data-toggle="tooltip" data-placement="top" title='Actual hours:9.00' >  {{ x.DAYS }} </span></a></td>  -->
														<!--  <td align="center";>  <input type='button' class="btn btn-primary btn-sm"  name="{{ x.INNER }}" id="{{ x.DATE }}" data-toggle="modal" data-target="#myModal" id="myBtn"onclick="ICT_Req(this);" value='Request' style='display:{{ x.DAF }}'  >   </td>  -->
														
										<td class="text-center"><a href="#" data-toggle="tooltip" data-placement="bottom"  class="red-tooltip" title='{{ x.REASON }} '><span class="glyphicon glyphicon-envelope"></span> REASON</a>	</td>		
										<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});
</script>
										<td class="text-center"><a href="#" class="red-tooltip"  data-toggle="tooltip" data-placement="bottom" title='{{ x.FEEDBACK }} ' ><span class="glyphicon glyphicon-envelope"></span>FEEDBACK</a>	</td>			
													<!-- 	 <td class="text-center"> <a href="#"><span  id="{{ x.DATE }}_ST"  class="red-tooltip"  data-toggle="tooltip" data-placement="top" title='{{ x.MESSAGE }}' >   {{ x.LEAVE_TYPE }}  </span></a> </td> --> 
														
														<!-- <ul class="list-inline">
    <li><a href="#" data-toggle="tooltip" data-placement="top" title="Hooray!">Top</a></li>
    <li><a href="#" data-toggle="tooltip" data-placement="bottom" title="Hooray!">Bottom</a></li>
    <li><a href="#" data-toggle="tooltip" data-placement="left" title="Hooray!">Left</a></li>
    <li><a href="#" data-toggle="tooltip" data-placement="right" title="Hooray!">Right</a></li>
  </ul> -->
														<!--  <td class="text-center"> <span  id="{{ x.DATE }}_ST" > {{ x.DAREQ }}  </span> </td>  -->
														 
														 
														<td class="text-center"> <a href="#" class="red-tooltip"  data-toggle="tooltip" data-placement="bottom" title='{{ x.MANAGERS_COMMENTS }} ' ><span class="glyphicon glyphicon-envelope"></span>{{ x.MANAGER_MSTATUS }} </a> </td>
														 
														 <td class="text-center" colspan='2'> <input type='button' class='btn btn-primary btn-sm'  name='{{ x.ID }}' alt='{{ x.ID }}' id='{{ x.ID }}_1'   onclick='ICT_Req(this);' value='Approve' style='display:{{x.A}}' / > &nbsp; <input type='button' class='btn btn-primary btn-sm'  alt='{{x.ID}}' name='{{x.ID}}'  id='{{x.ID}}_2'   onclick='ICT_Req(this);' value='Reject' style='display:{{x.R}}' /> </td> 
														 
														<!--  data-toggle="modal" data-target="#myModal" -->
														 
														    
													</tr>
												</table>
											</div>
										</div>
									</div>
								</div>	
							</div>
						</div>
					
					
					<!-- end: page -->
					
				</section>
			

			
		</section>
		
		
		<!-- Model Box -->
		 <!--request Modal-->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="vertical-alignment-helper">
					  <div class="modal-dialog vertical-align-center" role="document">
						<div class="modal-content">
						  <div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							  <span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Send Request</h4>
							<!-- Please Submit in between Payroll i.e., 26-previous Month-Current Year ------   27-Current Month-Current Year -->
						  </div>
							<div class="modal-body">
								<form action="ictrequestmail" method="post" >
									
									<!-- <div class="form-group">
									<label class="col-md-4 control-label">To</label>
									  <div class="col-sm-8">
									    <input type="text" class="form-control" name="to" id="to">
									  </div>
									</div>
									<div class="form-group">
									  <label class="col-md-4 control-label">CC</label>
									   <div class="col-sm-8">
									    <input type="text" class="form-control" name="cc" id="cc">
									  </div>
									</div> -->
									
									<div class="form-group">
									  <label class="col-md-4 control-label">Request Date</label>
									   <div class="col-sm-8">
									    <span id="Requ_Date"> </span>
									    
									    <input type="hidden" class="form-control" name="date" id="date"  readOnly>
									    
									     <input type="hidden" class="form-control" name="Requ_Date_Temp" id="Requ_Date_Temp"  >
									     
									    
									    
									    
									  </div>
									</div>
									
									<div class="form-group">
									  <label class="col-md-4 control-label">To-Mail</label>
									   <div class="col-sm-8">
									     <input type="email" class="form-control addresspicker" name="toemail"  id="tags2" value="" placeholder="someone@example.com" >
									  </div>
									</div>
									
									
									<div class="form-group">
									  <label class="col-md-4 control-label">CC-Mail</label>
									   <div class="col-sm-8">
									     <input type="email" class="form-control addresspicker" name="ccemail"  id="tags3" value="" placeholder="someone@example.com" >
									  </div>
									</div>
									
									
									
									<div class="form-group">
									  <label class="col-md-4 control-label">Subject</label>
									   <div class="col-sm-8">
									     <input type="text" class="form-control" name="Subject" id="Subject" value="Re: Incomplete Hours" >
									  </div>
									</div>
									
									<div class="form-group">
										<label class="col-md-4 control-label">Request Message</label>
										<div class="col-md-8">
											<textarea class="form-control" rows="5" id="message" name="message"></textarea>
										</div>
									</div>
									
								

							</div>
							<div class="modal-footer">
							
							<span id="Responce_Message" style="color:red;" class="align-left"> </span>
							
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>&nbsp;
							<input type="button" id="Responce_Message_btn" style="display:" class="btn btn-primary" onclick="AttendanceRequest()" value="Send Request"/>
							
							
							
							</div>
							
						  </div>
		
		<!-- Model Box Ending -->
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
					$(document).ready(function(){
					$('[data-toggle="tooltip1"]').tooltip();   
					});
				</script>
				
				<script>
					$(document).ready(function(){
					$('[data-toggle="tooltip"]').tooltip();   
					});
				</script>
				

				<!-- <script>
$(document).ready(function(){
    $("#myBtn").click(function(){
        $("#myModal").modal();
    });
});
</script> -->

	</body>
</html>