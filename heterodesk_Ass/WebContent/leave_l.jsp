<!doctype html>
<html class="fixed">

<%@page import="java.util.*" %>
<%
   Map EMAILDATA=new HashMap();
      try{
        EMAILDATA=(Map)session.getAttribute("EMAILDATA_MAP");
      }catch(Exception Err){
    	  
      }
   
%>
	<head>
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
		<!-- <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css"> -->
		<!-- Specific Page Vendor CSS -->
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
		<!-- Theme CSS -->
		<link rel="stylesheet" href="assets/stylesheets/theme.css" />
		<link rel="stylesheet" href="assets/stylesheets/date.css" />
		<link rel="stylesheet" href="assets/stylesheets/jquery-ui.css" />

		<!-- Skin CSS -->
		<link rel="stylesheet" href="assets/stylesheets/skins/default.css" />
		<!-- Theme Custom CSS -->
		<link rel="stylesheet" href="assets/stylesheets/theme-custom.css">
		<link href="Per_src/perfect-scrollbar.css" rel="stylesheet">
		   <script src="Per_src/jquery.mousewheel.js"></script>
        <script src="Per_src/perfect-scrollbar.js"></script>
<!-- <script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script> -->
		<!-- Head Libs -->
<style>
.center_div{
    margin: 0 auto;
    width:50% /* value of your choice which suits your alignment */
}
.center_div1{
    margin: 10px auto;
	
    width:100% /* value of your choice which suits your alignment */
}
.select1 {
	border: 1px solid #E5E7E9;
	border-radius: 6px;
	height: 30px;
	padding: 4px;
	outline: none;
}
.select2 {
	border: 1px solid #E5E7E9;
	border-radius: 6px;
	height: 34px;
	padding: 4px;
	outline: none;
	width:100px;
}
.clearfix{

height:20px;
}


</style>

<style>
.loader {
  border: 16px solid #f3f3f3;
  border-radius: 0%;
  border-top: 16px solid #3498db;
  width: 50px;
  height: 50px;
  -webkit-animation: spin 1s linear infinite; /* Safari */
  animation: spin 1s linear infinite;
}

/* Safari */
@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
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
		 <script>
  /* $( function() {
   var dateFormat = "yy-mm-dd",
      from = $( "#from" )
        .datepicker({
		//dateFormat: 'dd-mm-yy',
		  dateFormat: 'yy-mm-dd',
          defaultDate: "+1w",
          changeMonth: true,
          
        })
        .on( "change", function() {
          to.datepicker( "option", "minDate", getDate( this ) );
        }),
      to = $( "#to" ).datepicker({
    	  dateFormat: 'yy-mm-dd',
        defaultDate: "+1w",
        changeMonth: true,
        
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
  var EMAILDATA_MNG= "<%=EMAILDATA.get("MNGEMAIL")%>";
  var tempStartDate ="";
  var startingdate ="";
  var default_end11="";
  var startingdate;
  $( function() {
	   var tempStartDate = new Date();
	   
	   
	   startingdate = new Date(tempStartDate.getFullYear(), tempStartDate.getMonth(), tempStartDate.getDate()-4);
	   
	 /*   var SelectedLeaveType=$("#Leave_Type").val();
	   alert("SelectedLeaveType::"+SelectedLeaveType);
	   var LeaveConditions=$("#"+SelectedLeaveType+"").val();
	   
	   alert("LeaveConditions::"+LeaveConditions); */
	   
	   
	 //  alert(startingdate);
	  $("#from1").datepicker({
	 //************************************************************
	
		   changeMonth: true,
	       changeYear:true,
			//maxDate : 0,
			
			//dateFormat : 'yy-mm-dd',
			
			dateFormat : 'dd-mm-yy',
			
			yearRange: ""+tempStartDate.getFullYear()+" : "+tempStartDate.getFullYear()+"",
		   //yearRange: '2016:2020',
			 minDate : startingdate,
			 
			 onSelect : function(date) {
				 
				
              /*var tempStartDate = new Date(date);
		        var default_end = new Date(tempStartDate.getFullYear(), tempStartDate.getMonth()+1, 11); 
                $("#to").datepicker('option', 'minDate', default_end );
              */
		    //$("#to").datepicker('option', '2017-10-10', date); 
              
          //    alert(date);
              
 			var tempStartDate = new Date(date);
 		
 			var default_end = new Date(tempStartDate.getFullYear(), tempStartDate.getMonth(), tempStartDate.getDate()+5); 
 			
 			//var default_end = new Date(tempStartDate.getDate()+5, tempStartDate.getMonth(), tempStartDate.getFullYear());
 			
 			//alert(default_end +"~~" +tempStartDate.getFullYear());
 			
			$("#to1").datepicker('option', 'minDate', date );
			$("#to1").datepicker("option","maxDate", default_end); 
				//$("#to").datepicker('option', 'minDate', date);
			} 
	  
	  //***************************************************************
		});
	$("#to1").datepicker({
			changeMonth: true,
	        changeYear:true,
	        yearRange: ""+tempStartDate.getFullYear()+" : "+tempStartDate.getFullYear()+"",
	        //maxDate: 0,
			//dateFormat : 'yy-mm-dd',
			dateFormat : 'dd-mm-yy',
				 //yearRange: '2016:2020'
		});
	  });
 
  
  function Dpicker(){
	  
	 //  alert(default_end11 + "~startingdate::~" +startingdate);
	   
	   var from_dat_validation=$("#from").val();
	   
	   
	  $("#Responce_Message").html("");
	  var SelectedLeaveType1=$("#Leave_Type").val();
	 // var tempStartDate1 = new Date();
	  
	 
	 
	  $("#from").datepicker('option', 'minDate', startingdate );
	  $("#from").datepicker("option","maxDate", default_end11);
	 // $("#from").datepicker('option', 'maxDate', '2018-01-04' );
	  
	  
	  $( function() {
		 //  var tempStartDate = new Date();
		//   var startingdate = new Date(tempStartDate.getFullYear(), tempStartDate.getMonth(), tempStartDate.getDate()-4);
		 //  alert(startingdate);
		  $("#from").datepicker({
		 //************************************************************
			  changeMonth: true,
		      changeYear:true,
			  maxDate : default_end11,
			  //dateFormat : 'yy-mm-dd',
			  dateFormat : 'dd-mm-yy',
			  //yearRange: ""+tempStartDate.getFullYear()+" : "+tempStartDate.getFullYear()+1+"",
			  yearRange: '2017:2018',
			  minDate : startingdate,
			//minDate : '2017-12-01',
				 onSelect : function(date) {
					 
					 
					// alert("HI" +date);
				 		
						
	              /*  var tempStartDate = new Date(date);
			        var default_end = new Date(tempStartDate.getFullYear(), tempStartDate.getMonth()+1, 11); 
	               $("#to").datepicker('option', 'minDate', default_end );
	          */
				//$("#to").datepicker('option', '2017-10-10', date); 
	          var dates2=date.split("-");
	          var dates1=dates2[2]+"-"+dates2[1]+"-"+dates2[0];
	          date=dates1;
	          
	 		var tempStartDate = new Date(date);
	 		
	 		//alert(tempStartDate);
	 		
	 		var tempStartDate3 = new Date();
	 		
	 		//alert(tempStartDate3);
	 		
	 		
	 		
	 	//	var default_end = new Date(tempStartDate.getFullYear(), tempStartDate.getMonth(), tempStartDate.getDate()+5); 
	 		var SelectedLeaveType=$("#Leave_Type").val();
	 		//  alert("SelectedLeaveType::"+SelectedLeaveType);
	 		var LeaveConditions="";
	 		document.getElementById("Responce_Message").innerHTML="";
	 		if(SelectedLeaveType=="Select"){
	 			
	 			$("#from").val("");
	 			$("#to").val("");
	 			$("#Responce_Message").html("Plese Select Leave Type");
	 			return false;
	 			
	 		}
			  // alert("SelectedLeaveType::"+SelectedLeaveType);
			   LeaveConditions=$("#"+SelectedLeaveType+"").val();
			 //  alert("LeaveConditions::"+LeaveConditions);
			  /*  var MaxLeaves="1";
			   var MaxLeaves_temp="0";
			   var BackDays="0"; */
			   try{
		var flagl=false;
		
		var dates=date;
		
		if(SelectedLeaveType!="LOP" && SelectedLeaveType!="OD"){
			
			document.getElementById("ImgScroll").style.display="";
			var LeaveDays=0;
	     // Ajex Service For Calling Data From BackEnd	 
	     
	        //**********************************
			var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth()+1; //January is 0!

			var yyyy = today.getFullYear();
			if(dd<10){
			    dd='0'+dd;
			} 
			if(mm<10){
			    mm='0'+mm;
			} 
			var today = yyyy+'-'+mm+'-'+dd;
	        
	        
	        //*************************************
	     
			 var formData = {Routing:"FetchLeaveDays",Leave_Type:""+SelectedLeaveType+"",from_date:""+date+"",todaydate:""+today+""};
				try{
				    $.ajax({
				    	
				          type: "post",
				          url: "FetchAjexService",
				          data: formData,
				          success: function(responseData, textStatus, jqXHR) {
				        	 
				             try{
				            	// alert("responseData"+responseData);
				            	 var obj = JSON.parse(responseData);
				            	document.getElementById("Responce_Message").innerHTML=obj.Atten_Req_Message;
				            	//document.getElementById('from').value='';
				            	var dates=obj.DATES;
				            	
				            	if(obj.todayDateFlag=="true"){
				            		
				            		 document.getElementById("Send_Button").disabled=false;
				            		 document.getElementById("to").disabled=false;
				            	}else{
				            		 document.getElementById("Send_Button").disabled=true;
				            		 document.getElementById("to").disabled=true;
				            	}
				            	// alert(obj.todayDateFlag);
				            	//alert(date+ "dates::: "+dates);
				            	
				            	var tempStartDate2 = new Date(dates);
				            	var tempStartDate3 =  new Date();
				            	
				            	/* if(SelectedLeaveType=="SL"){
				            		
				            		tempStartDate2 = tempStartDate3;
				            	} */
				            	
				            	LeaveDays=obj.availableQuantity;
				            	
				            //	alert("Leave Days::: "+tempStartDate2);
				            	
				            	LeaveConditions=$("#"+SelectedLeaveType+"").val();
							     //  alert(LeaveConditions);
								   var SplitData=LeaveConditions.split("-");
								   MaxLeaves=SplitData[1];
								   MaxLeaves_temp=SplitData[1];
								   MaxLeaves= MaxLeaves_temp;
								   BackDays=SplitData[4];
								   
								   if(SelectedLeaveType=="SL"){
								   try{
								   MaxLeaves=obj.SL_MX_LEAVE;
								   }catch(err){
									   
								   }
								   
								 //  alert(LeaveDays +" SLLLLL " +obj.SL_MX_LEAVE);
								   }
								   
								  // alert(BackDays);
								   
								   /* if(eval(MaxLeaves)<=eval(MaxLeaves_temp)){
									   MaxLeaves=MaxLeaves;
								   }else if(eval(MaxLeaves)>=eval(MaxLeaves_temp)){
									   MaxLeaves=MaxLeaves_temp;
								   } */
								   if(SelectedLeaveType=="SL"){
									   
								   }else{
								      MaxLeaves=parseInt(LeaveDays.toString());
								   }
								   // alert(MaxLeaves +"~~~"+eval(MaxLeaves))
								   if(eval(MaxLeaves)>0 && eval(MaxLeaves)<1){
									   MaxLeaves=0;
								   }else{
									   
									  
									   MaxLeaves=eval(MaxLeaves)-1;
									   
									   if(MaxLeaves<0){
										   MaxLeaves=0  ;
										   tempStartDate2=startingdate;
									   }
									   
								   }
				            	
								  try{
				            	  // alert(tempStartDate2 +"~~~~" + startingdate);
								  }catch(err){
									  alert("Error Message::" +err.message);
								  }
								  
								  
								   startingdate = new Date(tempStartDate.getFullYear(), tempStartDate.getMonth(), tempStartDate.getDate()-BackDays);
								   
								 //  alert(tempStartDate2 +"~~~~" + startingdate);
							 	 // alert("MaxLeaves:: "+LeaveDays);
							 	   
							 	// alert("MaxLeaves:: "+MaxLeaves);
							 	// var default_end=null;
							 	 try{
							 		 
							 	  default_end = new Date(tempStartDate.getFullYear(), tempStartDate.getMonth(), tempStartDate.getDate()+eval(MaxLeaves)); 
							 	  
							 	  
							 	//  default_end = new Date(tempStartDate2.getFullYear(), tempStartDate2.getMonth(), tempStartDate2.getDate());
							 	   
							 	 }catch(err3){
							 		 alert(err3.message);
							 	 }
							 	 
							 	 
							 	 
							 //	 alert(tempStartDate2+"~~~~~~~"+default_end)
							 	 
							 		$("#to").datepicker('option', 'minDate', tempStartDate2 );
									$("#to").datepicker("option","maxDate", default_end); 
											//$("#to").datepicker('option', 'minDate', date);
									var df=$("#to").val();
				            	    flagl=true;
				            	    document.getElementById("ImgScroll").style.display="none";
				             }catch(err){
				            	 
				            	 // alert("go for error");
				            	 //document.getElementById("Responce_Message").innerHTML=err;
				            	 document.getElementById("ImgScroll").style.display="none";
				            	 
				            	// document.getElementById("Responce_Message").innerHTML=responseData;
					            	//document.getElementById('from').value='';
					            	  LeaveDays=0;
					            	  LeaveConditions=$("#"+SelectedLeaveType+"").val();
								    //   alert(LeaveConditions);
									   var SplitData=LeaveConditions.split("-");
									   MaxLeaves=SplitData[1];
									   MaxLeaves_temp=SplitData[1];
									   MaxLeaves= MaxLeaves_temp;
									   BackDays=SplitData[4];
									   
								//	   alert(BackDays);
									   
									   /* if(eval(MaxLeaves)<=eval(MaxLeaves_temp)){
										   MaxLeaves=MaxLeaves;
									   }else if(eval(MaxLeaves)>=eval(MaxLeaves_temp)){
										   MaxLeaves=MaxLeaves_temp;
									   } */
									   MaxLeaves=parseInt(LeaveDays.toString());
									   if(eval(MaxLeaves)>0 && eval(MaxLeaves)<1){
										   MaxLeaves=0;
									   }else{
										   MaxLeaves=eval(MaxLeaves)-1;
										   
										   if(MaxLeaves<0){
											   MaxLeaves=0;
										   }
									   }
					         		   startingdate = new Date(tempStartDate.getFullYear(), tempStartDate.getMonth(), tempStartDate.getDate()-BackDays);
								 	  // alert("MaxLeaves:: "+LeaveDays);
								 	 // alert("MaxLeaves:: "+MaxLeaves);
								 	  var default_end = new Date(tempStartDate.getFullYear(), tempStartDate.getMonth(), tempStartDate.getDate()+eval(MaxLeaves)); 
								 	  
								//  alert(date+"-------"+default_end);
								 		$("#to").datepicker('option', 'minDate', date );
										$("#to").datepicker("option","maxDate", default_end); 
												//$("#to").datepicker('option', 'minDate', date);
										var df=$("#to").val();
										
									
				             }
				          },
				          error: function(jqXHR, textStatus, errorThrown) {
				               console.log(errorThrown);
				               document.getElementById("ImgScroll").style.display="none";
				               
				               
				              // document.getElementById("Responce_Message").innerHTML=responseData;
				            	//document.getElementById('from').value='';
				            	 LeaveDays=0;
				            	
				            	
				            	  LeaveConditions=$("#"+SelectedLeaveType+"").val();
							       
							      // alert(LeaveConditions);
								   var SplitData=LeaveConditions.split("-");
								   MaxLeaves=SplitData[1];
								   MaxLeaves_temp=SplitData[1];
								   MaxLeaves= MaxLeaves_temp;
								   BackDays=SplitData[4];
								   
								   //alert(BackDays);
								   
								   /* if(eval(MaxLeaves)<=eval(MaxLeaves_temp)){
									   MaxLeaves=MaxLeaves;
								   }else if(eval(MaxLeaves)>=eval(MaxLeaves_temp)){
									   MaxLeaves=MaxLeaves_temp;
								   } */
								   MaxLeaves=parseInt(LeaveDays.toString());
								   if(eval(MaxLeaves)>0 && eval(MaxLeaves)<1){
									   MaxLeaves=0;
								   }else{
									   MaxLeaves=eval(MaxLeaves)-1;
									   
									  
								   }
				            	
								  
				            	
								   startingdate = new Date(tempStartDate.getFullYear(), tempStartDate.getMonth(), tempStartDate.getDate()-BackDays);
							 	  // alert("MaxLeaves:: "+LeaveDays);
							 	   
							 	 // alert("MaxLeaves:: "+MaxLeaves);
							 	  var default_end = new Date(tempStartDate.getFullYear(), tempStartDate.getMonth(), tempStartDate.getDate()+eval(MaxLeaves)); 
							 		$("#to").datepicker('option', 'minDate', date );
									$("#to").datepicker("option","maxDate", default_end); 
											//$("#to").datepicker('option', 'minDate', date);
									var df=$("#to").val();
				                   
				          }
				      })
				}catch(err){
			      alert("Message" +err.message);	
			      document.getElementById("ImgScroll").style.display="none";
			}  
				
				
		}else{
			
			try{
				
				
				
				document.getElementById("ImgScroll").style.display="";
				var today = new Date();
				var dd = today.getDate();
				var mm = today.getMonth()+1; //January is 0!
				var yyyy = today.getFullYear();
				if(dd<10){
				    dd='0'+dd;
				} 
				if(mm<10){
				    mm='0'+mm;
				} 
				var today = yyyy+'-'+mm+'-'+dd;
		    	 var formData = {Routing:"FetchLeaveDays1",Leave_Type:""+SelectedLeaveType+"",from_date:""+date+"",todaydate:""+today+""};
				
				try{
					    $.ajax({
					          type: "post",
					          url: "FetchAjexService",
					          data: formData,
					          success: function(responseData, textStatus, jqXHR) {
					        	 
					             try{
					            	 var obj = JSON.parse(responseData);
					            	document.getElementById("Responce_Message").innerHTML=obj.Atten_Req_Message;
					            	if(obj.todayDateFlag=="true"){
					            		
					            		 document.getElementById("Send_Button").disabled=false;
										  document.getElementById("ImgScroll").style.display="none";
										  document.getElementById("to").disabled=false;
					            	}else{
					            		 document.getElementById("Send_Button").disabled=true;
										  document.getElementById("ImgScroll").style.display="none";
										  document.getElementById("to").disabled=true;
					            	}
								 }catch(err){}
									 
					      },
						   error: function(jqXHR, textStatus, errorThrown) {
					               console.log(errorThrown);
					               document.getElementById("ImgScroll").style.display="none";
						   }
						})
					}catch(err){
				      alert("Message" +err.message);	
				      document.getElementById("ImgScroll").style.display="none";
				   }  
					
				
				
				
				
			}catch(err){
				
				alert(err.message);
				
			}
			/* var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth()+1; //January is 0!

			var yyyy = today.getFullYear();
			if(dd<10){
			    dd='0'+dd;
			} 
			if(mm<10){
			    mm='0'+mm;
			} 
			var today = yyyy+'-'+mm+'-'+dd;
			
               var formData = {Routing:"FetchLeaveDays1",Leave_Type:""+SelectedLeaveType+"",from_date:""+date+"",todaydate:""+today+""};
				try{
				    $.ajax({
				    	
				          type: "post",
				          url: "FetchAjexService",
				          data: formData,
				            success: function(responseData, textStatus, jqXHR) {
				        	  document.getElementById("Send_Button").disabled=false;
				             try{
							 
							 
				            	  alert(responseData);
							 
							    var obj = JSON.parse(responseData);
							    alert(obj.todayDateFlag);
				            	document.getElementById("Responce_Message").innerHTML=obj.Atten_Req_Message;;

                           }catch(err){
						   }
						   
						   },
				          error: function(jqXHR, textStatus, errorThrown) {
				               console.log(errorThrown);
				               document.getElementById("ImgScroll").style.display="none";
				               
				                   
				          }
				      });
				      
				       */
			
			
			// alert("last");
			/*  startingdate = new Date(tempStartDate.getFullYear(), tempStartDate.getMonth(), tempStartDate.getDate()-4);
		 	 var default_end = new Date(tempStartDate.getFullYear(), tempStartDate.getMonth(), tempStartDate.getDate()+365); 
			
			$("#from").datepicker('option', 'minDate', date );
			$("#from").datepicker("option","maxDate", default_end);  */
			
			// var tempStartDate=new Date(date);
			  startingdate = new Date(tempStartDate.getFullYear(), tempStartDate.getMonth(), tempStartDate.getDate());
		 	  var default_end = new Date(tempStartDate.getFullYear(), tempStartDate.getMonth(), tempStartDate.getDate()+365); 
		 		$("#to").datepicker('option', 'minDate', startingdate );
				$("#to").datepicker("option","maxDate", default_end); 
						//$("#to").datepicker('option', 'minDate', date);
				var df=$("#to").val();
				
			
		}
		
			   }catch(err){
				    alert("Browser :"+err.message);
			   }
			   
	      // Ajex Service For Calling Data From BackEnd	
		
			
			       /* LeaveConditions=$("#"+SelectedLeaveType+"").val();
			       
			       alert(LeaveConditions);
				   var SplitData=LeaveConditions.split("-");
				   MaxLeaves=SplitData[1];
				   MaxLeaves_temp=SplitData[1];
				   MaxLeaves= MaxLeaves_temp;
				   BackDays=SplitData[4];
				   
				   alert(BackDays);
				   
				   if(eval(MaxLeaves)<=eval(MaxLeaves_temp)){
					   MaxLeaves=MaxLeaves;
				   }else if(eval(MaxLeaves)>=eval(MaxLeaves_temp)){
					   MaxLeaves=MaxLeaves_temp;
				   }
				   if(eval(MaxLeaves)>0 && eval(MaxLeaves)<1){
					   MaxLeaves=0;
				   }else{
					   MaxLeaves=eval(MaxLeaves)-1;
				   }
			   }else{
				   var MaxLeaves="365"; 
			   }
			   }catch(err){
				    alert("Browser :"+err.message);
			   }
			   alert("MaxLeaves:: "+LeaveDays);
			   startingdate = new Date(tempStartDate.getFullYear(), tempStartDate.getMonth(), tempStartDate.getDate()-BackDays);
	 	 //  alert("MaxLeaves:: "+LeaveDays);
	 	   MaxLeaves=parseInt(LeaveDays.toString());
	 	  alert("MaxLeaves:: "+MaxLeaves);
	 	  var default_end = new Date(tempStartDate.getFullYear(), tempStartDate.getMonth(), tempStartDate.getDate()+eval(MaxLeaves)); 
	 		$("#to").datepicker('option', 'minDate', date );
			$("#to").datepicker("option","maxDate", default_end); 
					//$("#to").datepicker('option', 'minDate', date);
			var df=$("#to").val(); */
			
			
			
          //alert(df +"::DF");
     			} 
		  //***************************************************************
			});
			$("#to").datepicker({
				
				changeMonth: true,
		        changeYear:true,
		        yearRange: ""+tempStartDate.getFullYear()+" : "+tempStartDate.getFullYear()+"",
		        //maxDate: 0,
				//dateFormat : 'yy-mm-dd',
				dateFormat : 'dd-mm-yy',
				yearRange: '2017:2018',
			   onSelect : function(date) {
				   
				   
				   var OTHFLAG="Y";
				   var Leave_Type= "";
				   var from_date="";
				   var to_date="";
				   var HalfDay="false";
				   var Hal_date="false";
				   var Leave_Type=document.getElementById('Leave_Type').value;
					 var from_date=document.getElementById('from').value;
				 var to_date=document.getElementById('to').value;
				 var HalfDay=document.getElementById('HalfDay').checked;
				    if(HalfDay){
				    	HalfDayFlg=document.getElementById('HalfSt').value;
				    }
				 var compoff=document.getElementById('compoff').checked;
				 var Hal_date=document.getElementById('date').value;
				 var comm_date=document.getElementById('date1').value;
				 var to_mail=document.getElementById('tags').value; // to mail
				 var cc_mail=document.getElementById('tags1').value; // ccmail
				 var subject=document.getElementById('subject').value;
				 var reason=document.getElementById('reason').value;
				 var From_location=document.getElementById("From_location").value;
				 var To_location=document.getElementById("To_location").value;
				 
				   Apply_Leave(Leave_Type,from_date,to_date,HalfDay,Hal_date,compoff,comm_date,to_mail,cc_mail,subject,reason,OTHFLAG);
				   RefreshRadio_temp(date);
			     //alert("123" +date);
			   }
					 
			  
			});
			
		  });
	 
	  
  }
  </script>
  <script>
  $( function() {
    $( "#date" ).datepicker(
    {
    	//dateFormat: 'yy-mm-dd'
    	dateFormat : 'dd-mm-yy'
    }		
    );
    
  } );
 
  $( function() {
    $( "#date1" ).datepicker(
    {
    	//dateFormat: 'yy-mm-dd'
    	dateFormat : 'dd-mm-yy'
    }
    );
    
  } );
  </script>
   <script>
  $( function() {
	  
	  try{
	    $( "#tags" ).val(EMAILDATA_MNG);
	  }catch(err){}
	  var availableTags = 
		  [
			  "03.saurav@gmail.com",
			  "1981abhay@gmail.com",
			  "666puneeth@gmail.com",
			  "a.k.singh134@gmail.com",
			  "abhaypatel86@yahoo.com",
			  "abhijit.jathi2012@gmail.com",
			  "abhijit.maity97@gmail.com",
			  "abhishek.garg@heterohealthcare.com",
			  "abhisheksingh@heterohealthcare.com",
			  "adityasharma@heterohealthcare.com",
			  "aftab886629@gmail.com",
			  "aintwardhiraj@gmail.com",
			  "ajaykumar.s@heterohealthcare.com",
			  "ajayshinde14@gmail.com",
			  "ajit.linge@gmail.com",
			  "ajitkumaralkem@gmail.com",
			  "aksahoo@heterohealthcare.com",
			  "aliahmed@heterohealthcare.com",
			  "alok.p@heterohealthcare.com",
			  "alok2007@rediffmail.com",
			  "amaroajmire@gmail.com",
			  "amarvyas79@gmail.com",
			  "ameet.upadhyay31@gmail.com",
			  "amin.shaikh81@yahoo.com",
			  "amit.joshi@heterohealthcare.com",
			  "amit.s@heterohealthcare.com",
			  "amithetero12@gmail.com",
			  "amitmondal210981@yahoo.com",
			  "amitnarwal1980@gmail.com",
			  "amitsaha1976@yahoo.co.in",
			  "amitverma5060@gmail.com",
			  "amman_ullah90@yahoo.com",
			  "amol.kalbage@gmail.com",
			  "amolphule83@gmail.com",
			  "amsrinivas.genx@gmail.com",
			  "anand9452834006@gmail.com",
			  "aneeskhan8522@gmail.com",
			  "anilbhandari29@gmail.com",
			  "anjan.roychowdhury15@gmail.com",
			  "ankittripathi.742@rediffmail.com",
			  "ankitvi1@gmail.com",
			  "antoni.gounder@heterohealthcare.com",
			  "anuj7162@gmail.com",
			  "anujdewani@heterohealthcare.com",
			  "anupamsuneet@gmail.com",
			  "anuradha@heterohealthcare.com",
			  "anuragkumarpatel1983@gmail.com",
			  "anwar.shaik@heterohealthcare.com",
			  "apoorva12890@gmail.com",
			  "arbindks@rediffmail.com",
			  "ardendu_swift@rediffmail.com",
			  "arijit.dutta@heterohealthcare.com",
			  "arunmishra010@gmail.com",
			  "arup@heterohealthcare.com",
			  "arupmuk77@yahoo.com",
			  "arvind.mishra768@gmail.com",
			  "arvindabbottsingh@gmail.com",
			  "arvindsidana85@yahoo.co.in",
			  "arvindyadav1036@yahoo.com",
			  "ashaygawade@gmail.com",
			  "ashish.phl@gmail.com",
			  "ashutosh00784@gmail.com",
			  "ashutosh_glenmark@yahoo.com",
			  "asim.shataxi@rediffmail.com",
			  "ateeqahmed786@gmail.com",
			  "atul.salve@heterohealthcare.com",
			  "audayvansi1@gmail.com",
			  "aurokanta.m@heterohealthcare.com",
			  "avadhesh.r@heterohealthcare.com",
			  "avadhut.sas@gmail.com",
			  "avadhutsangar85@gmail.com",
			  "aveshsrivastav.fdc@gmail.com",
			  "avinash.koturwar1984@gmail.com",
			  "ayanbal@gmail.com",
			  "ayyaj.mulla@gmail.com",
			  "b.behuria@rediffmail.com",
			  "b.jena@heterohealthcare.com",
			  "babuveeresh6@gmail.com",
			  "bajirao.karande@heterohealthcare.com",
			  "balajune693@gmail.com",
			  "balaskh1100@gmail.com",
			  "baldevrampatel@rediffmail.com",
			  "bangal.soumen@gmail.com",
			  "banik_abhijeet@rediffmail.com",
			  "basavarajyaligar7@gmail.com",
			  "basheerali.nzb@gmail.com",
			  "bhanupratap@heterohealthcare.com",
			  "bharat.gehalot@heterohealthcare.com",
			  "bharatpandya@heterohealthcare.com",
			  "bharatsiddy@yahoo.com",
			  "bhaskarasarma_my@yahoo.com",
			  "bhaveshsavalia2006@yahoo.co.in",
			  "bhavin_shah7887@yahoo.co.in",
			  "bhayekar_hhl1@yahoo.com",
			  "bhushan.yewale@heterohealthcare.com",
			  "bijay_zephyr@rediffmail.com",
			  "birendra.y@heterohealthcare.com",
			  "bj_ajwalia05@yahoo.co.in",
			  "bm.nagamuthuraja@yahoo.co.in",
			  "bose@heterohealthcare.com",
			  "brajgaurav@azistaindustries.com",
			  "brijeshsharma@heterohealthcare.com",
			  "bsk.hussain@yahoo.com",
			  "bulbulekishor@gmail.com",
			  "bunke_hemant@rediffmail.com",
			  "cgajjar19@gmail.com",
			  "chakrabortyindrajit5@gmail.com",
			  "chandankumar.singh@heterohealthcare.com",
			  "chandrashekhar@heterohealthcare.com",
			  "charan.chandana@gmail.com",
			  "charanpreet.singh@heterohealthcare.com",
			  "chaubey.pramod@gmail.com",
			  "chetan.marne26@gmail.com",
			  "chhavvie@gmail.com",
			  "chiragkanugo@rocketmail.com",
			  "chiragsetia15@gmail.com",
			  "chiranjeevi.velagala@heterohealthcare.com",
			  "cprasad80@rediffmail.com",
			  "creativepravesh@gmail.com",
			  "csreddy@heterohealthcare.com",
			  "damodararao.m@heterohealthcare.com",
			  "dattanivas123@gmail.com",
			  "dattatraya.d@heterohealthcare.com",
			  "dayasuryawanshi15@gmail.com",
			  "debasishkol90@gmail.com",
			  "deepakdhunna96@gmail.com",
			  "deepaksharma@heterohealthcare.com",
			  "deshmukh.v@heterohealthcare.com",
			  "dev.com36@gmail.com",
			  "devendra.pal2009@gmail.com",
			  "devijay.pharma@gmail.com",
			  "dhananjay.sandve@heterohealthcare.com",
			  "dhaval.patel@heterohealthcare.com",
			  "dhruvparmar16@gmail.com",
			  "diaspaahmedabad.asm@heterohealthcare.com",
			  "diaspaaurangabad.asm@heterohealthcare.com",
			  "diaspabangalore.asm@heterohealthcare.com",
			  "diaspabaroda.asm@heterohealthcare.com",
			  "diaspabhopal.asm@heterohealthcare.com",
			  "diaspabhubaneswar.asm@heterohealthcare.com",
			  "diaspacalicut.asm@heterohealthcare.com",
			  "diaspachandigarh.asm@heterohealthcare.com",
			  "diaspachennai.asm1@heterohealthcare.com",
			  "diaspachennai.asm2@heterohealthcare.com",
			  "diaspacoimbatore.asm@heterohealthcare.com",
			  "diaspacoimbatore.fe2@heterohealthcare.com",
			  "diaspacuttack.asm@heterohealthcare.com",
			  "diaspadelhi.asm1@heterohealthcare.com",
			  "diaspadelhi.asm2@heterohealthcare.com",
			  "diaspadelhi.asm3@heterohealthcare.com",
			  "diaspaernakulam.asm@heterohealthcare.com",
			  "diaspaguwahati.asm@heterohealthcare.com",
			  "diaspahowrah.asm@heterohealthcare.com",
			  "diaspahubli.asm@heterohealthcare.com",
			  "diaspahyderabad.asm1@heterohealthcare.com",
			  "diaspahyderabad.asm2@heterohealthcare.com",
			  "diaspaindore.asm@heterohealthcare.com",
			  "diaspajabalpur.asm@heterohealthcare.com",
			  "diaspajaipur.asm@heterohealthcare.com",
			  "diaspajodhpur.asm@heterohealthcare.com",
			  "diaspakanpur.asm@heterohealthcare.com",
			  "diaspakolhapur.asm@heterohealthcare.com",
			  "diaspakolkata.asm1@heterohealthcare.com",
			  "diaspakolkata.asm2@heterohealthcare.com",
			  "diaspakolkata.fe3@heterohealthcare.com",
			  "diaspakurnool.asm@heterohealthcare.com",
			  "diaspalucknow.asm@heterohealthcare.com",
			  "diaspaludhiana.asm@heterohealthcare.com",
			  "diaspamadurai.asm@heterohealthcare.com",
			  "diaspamangalore.asm@heterohealthcare.com",
			  "diaspameerut.asm@heterohealthcare.com",
			  "diaspamumbai.asm1@heterohealthcare.com",
			  "diaspamumbai.asm2@heterohealthcare.com",
			  "diaspamumbai.asm3@heterohealthcare.com",
			  "diaspamuzaffarpur.asm@heterohealthcare.com",
			  "diaspanagpur.asm@heterohealthcare.com",
			  "diaspanavsari.fe@heterohealthcare.com",
			  "diaspapatna.asm@heterohealthcare.com",
			  "diaspapune.asm1@heterohealthcare.com",
			  "diaspapune.asm2@heterohealthcare.com",
			  "diasparajkot.asm@heterohealthcare.com",
			  "diasparanchi.asm@heterohealthcare.com",
			  "diaspasilchar.asm@heterohealthcare.com",
			  "diaspasiliguri.asm@heterohealthcare.com",
			  "diaspasrinagar.asm@heterohealthcare.com",
			  "diaspavaranasi.asm@heterohealthcare.com",
			  "diaspavijayawada.asm@heterohealthcare.com",
			  "diaspavizag.asm@heterohealthcare.com",
			  "dibyendu.roy@heterohealthcare.com",
			  "dilip.t@heterohealthcare.com",
			  "dineshkumark2011@yahoo.com",
			  "divya.srinivasulu@yahoo.com",
			  "dk_awasthi123@rediffmail.com",
			  "dp9702@gmail.com",
			  "dpdrl194@rediffmail.com",
			  "dpkk1987@gmail.com",
			  "dr.indrajeetkumar@gmail.com",
			  "dvk.ravishankar@heterohealthcare.com",
			  "dvshivajirao@gmail.com",
			  "fakrushaik744@gmail.com",
			  "g.senthilhetero@gmail.com",
			  "gaikwadmd2007@rediffmail.com",
			  "gallasarathmba@gmail.com",
			  "ganesh.kale@heterohealthcare.com",
			  "gangadhartarlekar@yahoo.in",
			  "gargjai_143@yahoo.co.in",
			  "gaurav@heterohealthcare.com",
			  "gautam_p10@yahoo.in",
			  "genxhardeep@gmail.com",
			  "girishgorekar@gmail.com",
			  "gonthinabp@gmail.com",
			  "goswaminitin0007@gmail.com",
			  "gunda.srikanth@gmail.com",
			  "guptaadesh88@yahoo.com",
			  "guptashashikant71@gmail.com",
			  "hadibandhumajhi@yahoo.co.in",
			  "harikant4@gmail.com",
			  "harish_hetro@yahoo.com",
			  "harshapalyam@gmail.com",
			  "heeralal.babu@rediffmail.com",
			  "hemant.patil@heterohealthcare.com",
			  "hemant.thakur@heterohealthcare.com",
			  "hemantrm121@gmail.com",
			  "heteroashok@gmail.com",
			  "heteromau@gmail.com",
			  "heterovikram@gmail.com",
			  "himanshubhatia.smu@gmail.com",
			  "hiradhar@heterohealthcare.com",
			  "imlakkhan@gmail.com",
			  "imran.1692@rediffmail.com",
			  "indrajeetrajput87@gmail.com",
			  "indreshbkumar@gmail.com",
			  "jadhavprashant001@gmail.com",
			  "jagdish.n@heterohealthcare.com",
			  "jainendrabagdaniya@gmail.com",
			  "jeet1103@gmail.com",
			  "jeet87@rocketmail.com",
			  "jigar.shah@heterohealthcare.com",
			  "jignesh.patel@heterohealthcare.com",
			  "jintu_daimari@yahoo.com",
			  "jitendra.lim@gmail.com",
			  "jitendrasingh@heterohealthcare.com",
			  "jitheshkumar2007@yahoo.co.in",
			  "jolly.k@heterohealthcare.com",
			  "joyanta.banerjee@rediffmail.com",
			  "jskayastha@gmail.com",
			  "kaalinga.narayan@azistaindustries.com",
			  "kallolchakraborti@yahoo.co.in",
			  "kalpana.sinkar@heterohealthcare.com",
			  "kalyandas@heterohealthcare.com",
			  "kambam2@rediffmail.com",
			  "kanahaiya.tc3@gmail.com",
			  "kapilksharma85@gmail.com",
			  "kapilmukati@heterohealthcare.com",
			  "kapsepawan@rediffmail.com",
			  "karanhetero@gmail.com",
			  "karthik.m@heterohealthcare.com",
			  "kashinath.sontakke@yahoo.com",
			  "kdaraji007@gmail.com",
			  "kdesai17@gmail.com",
			  "khanna_shivam21@rediffmail.com",
			  "khare.mudit@rediffmail.com",
			  "khasgiwal@heterohealthcare.com",
			  "khasim_khan464@yahoo.co.in",
			  "khatiksamir@gmail.com",
			  "khatrilokesh52@gmail.com",
			  "khetanaashish@yahoo.com",
			  "kiran.singh@heterohealthcare.com",
			  "kisanlalge@gmail.com",
			  "kishan.k@heterohealthcare.com",
			  "kishore.mk18@gmail.com",
			  "kothireddyravinderreddy@gmail.com",
			  "koti.mba34@gmail.com",
			  "kotireddy.mekala2@gmail.com",
			  "kotireddy@heterohealthcare.com",
			  "kpandey251@gmail.com",
			  "ksthakur02@gmail.com",
			  "ksvitthal@gmail.com",
			  "kuldip.rathour@gmail.com",
			  "kumar.kn.83@gmail.com",
			  "kumar.shambhu860@gmail.com",
			  "kumarjanga@yahoo.com",
			  "kumarsnlsinha183@gmail.com",
			  "kumarswamyheterohealthcare@gmail.com",
			  "kundu.abhijit4@gmai.com",
			  "kvkreddy@heterohealthcare.com",
			  "lakshman_kundur@yahoo.co.in",
			  "lakshmesh77@gmail.com",
			  "m.imranrosan1993@gmail.com",
			  "madan.d@heterohealthcare.com",
			  "mahaveerdevrath@yahoo.com",
			  "mahesh.s@heterohealthcare.com",
			  "mahesh5gunjite@yahoo.co.in",
			  "maheshgs123@gmail.com",
			  "mail2syedjaveed@gmail.com",
			  "majumdaramit41@gmail.com",
			  "manash@heterohealthcare.com",
			  "manasp@heterohealthcare.com",
			  "manbolokhande@gmail.com",
			  "mangesh.p@heterohealthcare.com",
			  "mangeshawate2011@gmail.com",
			  "mangeshpaswan@yahoo.co.in",
			  "mani.ps@heterohealthcare.com",
			  "manikanta.atyam@gmail.com",
			  "manish958@gmail.com",
			  "manishhaya@gmail.com",
			  "manishshingh2689@gmail.com",
			  "manish_nadkar@yahoo.com",
			  "mankarand@heterohealthcare.com",
			  "manoj.pahwa@live.com",
			  "manoj.saxena@heterohealthcare.com",
			  "manoj@heterohealthcare.com",
			  "manojsingh@heterohealthcare.com",
			  "masoomsarwar@gmail.com",
			  "mayurgutpa636@gmail.com",
			  "meetmyheart@ymail.com",
			  "mgajendra82@gmail.com",
			  "mgaswani@yahoo.in",
			  "mitulpatel@heterohealthcare.com",
			  "mmpl.chandru@gmail.com",
			  "mohannavalav@gmail.com",
			  "momjidabhi@gmail.com",
			  "mona.kazi@heterohealthcare..com",
			  "monu4546@gmail.com",
			  "mr.nasir21@rediffmail.com",
			  "mrkrvarma@yahoo.in",
			  "msyogesh92@gmail.com",
			  "mudassar.inamdar@rediffmail.com",
			  "mufaddalbhavti@gmail.com",
			  "mukeshgupta@heterohealthcare.com",
			  "murale_satish@rediffmail.com",
			  "murli_kdm06@yahoo.com",
			  "murthy_narasimha@yahoo.in",
			  "mushtaq_rs@yahoo.co.in",
			  "nagar.sanjeev79@gmail.com",
			  "nagaraju.v@heterohealthcare.com",
			  "namamipandey@heterohealthcare.com",
			  "nandlal.s@heterohealthcare.com",
			  "nandu.bdm@gmail.com",
			  "narayanankannan01@gmail.com",
			  "narendra84trivedi@gmail.com",
			  "naveen_deshpande9@yahoo.com",
			  "nazef@heterohealthcare.com",
			  "neerajhetero@gmail.com",
			  "nehru.dadisetti@heterohealthcare.com",
			  "nemai.mukherjee@heterohealthcare.com",
			  "nigam_sameer@yahoo.com",
			  "niharrsilu1983@gmail.com",
			  "nikunj1987joshi@gmail.com",
			  "nikunjdave1114@yahoo.co.in",
			  "nilay.chatterjee@heterohealthcare.com",
			  "nileshpatel@heterohealthcare.com",
			  "niraj.shah@azistaspace.com",
			  "niranjan.chanamala@yahoo.com",
			  "nirav.bhatt@heterohealthcare.com",
			  "nirav_sheth2110@yahoo.co.in",
			  "nishaar25@gmail.com",
			  "nishant.emcure@gmail.com",
			  "nishanth.d@heterohealthcare.com",
			  "nishu_sweetbacha089@yahoo.com",
			  "nitin9oct@gmail.com",
			  "nitinb@heterohealthcare.com",
			  "nitinpatil1986@yahoo.co.in",
			  "nnramesh70@yahoo.co.in",
			  "obulareddy.pbc@heterohealthcare.com",
			  "om.ashish6852@gmail.com",
			  "omkar.yadav@heterohealthcare.com",
			  "omprakash.b@heterohealthcare.com",
			  "omprakash.swami@heterohealthcare.com",
			  "onkardas@gmail.com",
			  "p.munivel@gmail.com",
			  "palashdas@heterohealthcare.com",
			  "palnarendra88@gmail.com",
			  "pandeylesante1979@gmail.com",
			  "pankaj.dubey@heterohealthcare.com",
			  "pankaj.dwivedi110@gmail.com",
			  "pankaj.surendra.sharma@gmail.com",
			  "pankajkumar.r@heterohealthcare.com",
			  "pankajneve2012@gmail.com",
			  "pankajvyaskaithal@yahoo.com",
			  "paresh_jarande@rediffmail.com",
			  "parveenkumar333@gmail.com",
			  "pathak87pawan@gmail.com",
			  "pathakamitj@gmail.com",
			  "patil.hetero@gmail.com",
			  "pattnaiklipu@gmail.com",
			  "pavanborle_hhl1@yahoo.com",
			  "pdubey1947@rediffmail.com",
			  "peertalib11@gmail.com",
			  "pingal.1982@gmail.com",
			  "pp.pareek@gmail.com",
			  "pra.patel18@gmail.com",
			  "prabhat.kumar481@gmail.com",
			  "prabhatsharma394@gmail.com",
			  "prabhulove.dwivedi@gmail.com",
			  "pradeepmalandkar1903@gmail.com",
			  "pradeepmishra@heterohealthcare.com",
			  "pradip.3june@gmail.com",
			  "pradip.chavan84@gmail.com",
			  "prajithlaxman@gmail.com",
			  "pranava.kj@gmail.com",
			  "prasanna0501@rediffmail.com",
			  "prasannavarma29@yahoo.com",
			  "prashant@heterohealthcare.com",
			  "prashantdumma@gmail.com",
			  "prashanthd@heterohealthcare.com",
			  "prateek.jain@heterohealthcare.com",
			  "praveengupta11828@gmail.com",
			  "praveshtiwari82@gmail.com",
			  "pravin26.p@gmail.com",
			  "pravir.k.srivastava@gmail.com",
			  "psgpso@rediffmail.com",
			  "ptamrakar.9012@gmail.com",
			  "puneet24janv@gmail.com",
			  "purnendu.kar1@gmail.com",
			  "pushpaksonawane26@gmail.com",
			  "pushpendar_kumar@yahoo.com",
			  "r.hospure31@gmail.com",
			  "radhakrishna.pharma@gmail.com",
			  "raghavendran.vedam@azistaaerospace.com",
			  "raghupathijatti@gmail.com",
			  "raghuvendra.singh@heterohealthcare.com",
			  "rahu.lanke@gmail.com",
			  "rahuldhamande21@gmail.com",
			  "rajaislam7@gmail.com",
			  "rajanaristo52@gmail.com",
			  "rajarims@gmail.com",
			  "raja_bhaskar@yahoo.com",
			  "rajeev.awasthi.ps@gmail.com",
			  "rajeevojha.ojha30@gmail.com",
			  "rajeevranjanmukund@yahoo.in",
			  "rajendra.rajendra029@gmail.com",
			  "rajendra291987@gmail.com",
			  "rajesh.todupunuri4447@gmail.com",
			  "rajeshdharmik12@gmail.com",
			  "rajeshsodha409@gmail.com",
			  "rajiv.zenovz@yahoo.in",
			  "rajkaushikmeerut2mumbai@gmail.com",
			  "rajsinghemure@gmail.com",
			  "raju.budime1@gmail.com",
			  "raju.shanmuki01@gmail.com",
			  "rakesh.singh@heterohealthcare.com",
			  "rakeshdhamde@gmail.com",
			  "ramanuj.banerjee@heterohealthcare.com",
			  "ramanuja@heterohealthcare.com",
			  "ramaswamy.v@heterohealthcare.com",
			  "ramesh.chimurkar@heterohealthcare.com",
			  "rameshdeepi101@gmail.com",
			  "rameshmishra6761@gmail.com",
			  "rane@heterohealthcare.com",
			  "rathore.dops@gmail.com",
			  "ratnadeep_paul@yahoo.com",
			  "ravaljignesh83@gmail.com",
			  "ravi.k@heterohealthcare.com",
			  "ravichandra@heterohealthcare.com",
			  "ravindrakhose2011@gmail.com",
			  "ravisaini533@gmail.com",
			  "ravish9211@gmail.com",
			  "raviswaraj@gmail.com",
			  "rawal_sun@yahoo.com",
			  "rishishukla@heterohealthcare.com",
			  "ritamore210@gmail.com",
			  "riteshde@gmail.com",
			  "rkreddy.ch@heterohealthcare.com",
			  "rmheterofrenza@gmail.com",
			  "rofiqulislam98@yahoo.com",
			  "rohitks.genx@gmail.com",
			  "rrbsamy@gmail.com",
			  "rsaravanaguru@gmail.com",
			  "ruturajpawar11@gmail.com",
			  "s@heterohealthcare.com",
			  "sachinbangar38@gmail.com",
			  "sachinghuge33@gmail.com",
			  "sachinpadwal24@gmail.com",
			  "sachinrahate@heterohealthcare.com",
			  "sachinshah9958@gmail.com",
			  "sadrulali@heterohealthcare.com",
			  "saheebn@gmail.com",
			  "saikatchakraborty@heterohealthcare.com",
			  "saikumar.b@heterohealthcare.com",
			  "sam.shinde67@gmail.com",
			  "sameer11105@gmail.com",
			  "samya1069@gmail.com",
			  "sandeepbejjanki966@gmail.com",
			  "sandeepdeongp@yahoo.in",
			  "sandhya@heterohealthcare.com",
			  "sandipgodbole_2008@rediffmail.com",
			  "sandiplonari@gmail.com",
			  "sangam.partap45@gmail.com",
			  "sanjay.hetero@gmail.com",
			  "sanjay.thobhani@azistaspace.com",
			  "sanjayalk.sk@gmail.com",
			  "sanjaypandeyhhcl@gmail.com",
			  "sanjeet.hetero@gmail.com",
			  "sanjeev.m@heterohealthcare.com",
			  "sanjibb66@gmail.com",
			  "sanjivhhl@gmail.com",
			  "sanjoy.barasat.sutta@gmail.com",
			  "santhoshreddy.y@heterohealthcare.com",
			  "santhu_1163@yahoo.com",
			  "santoshkhamgal@gmail.com",
			  "sarabindu.ghosh1986@gmail.com",
			  "sarvesh.gupta2012@gmail.com",
			  "satish.verma@heterohealthcare.com",
			  "satishabbott@gmail.com",
			  "satishpa6761@gmail.com",
			  "satyam85kumar@gmail.com",
			  "saxenagaurav2009@gmail.com",
			  "say2mohan@gmail.com",
			  "sbelmanchi@gmail.com",
			  "sdprasad@heterohealthcare.com",
			  "sebabrata@heterohealthcare.com",
			  "seemakadam76@yahoo.com",
			  "sendtosumesh@gmail.com",
			  "senthilsch@yahoo.co.in",
			  "sgd310@gmail.com",
			  "shah.alam81@gmail.com",
			  "shahminkal2006@yahoo.co.in",
			  "shahnish26@gmail.com",
			  "shailesh@heterohealthcare.com",
			  "shail_kgpharma@yahoo.com",
			  "shantaram.p@heterohealthcare.com",
			  "shantaram@heterohealthcare.com",
			  "sharad@heterohealthcare.com",
			  "sharma.chandan54@gmial.com",
			  "sharmaamit342@gmail.com",
			  "sharmashrikrishna38@gmail.com",
			  "sharmisthabose1985@gmail.com",
			  "shashikanth_mr@yahoo.co.in",
			  "shastri@heterohealthcare.com",
			  "sheetal@heterohealthcare.com",
			  "shravan.reddy@heterohealthcare.com",
			  "shreekant.panigrahi@gmail.com",
			  "shreyas.krishetero@gmail.co",
			  "shri.tambade@gmail.com",
			  "shrirang.bapat@heterohealthcare.com",
			  "shritakate@gmail.com",
			  "shubhadeep2003@rediffmail.com",
			  "shyamendrafdc@gmail.com",
			  "siddunbl@gmail.com",
			  "sikandar@heterohealthcare.com",
			  "sisodiadevendra@yahoomail.com",
			  "sk.khuntia87@gmail.com",
			  "sk.valli28@gmail.com",
			  "sk.wale@rediffmail.com",
			  "sm_177@rediffmail.com",
			  "solankibhavesh121@gmail.com",
			  "souradipdas89@gmail.com",
			  "sp.coiabm@heterohealthcare.com",
			  "sp.hydabm@heterohealthcare.com",
			  "sp.punabm@heterohealthcare.com",
			  "sp.thrabm@heterohealthcare.com",
			  "sp.triabm@heterohealthcare.com",
			  "sp.vijabm@heterohealthcare.com",
			  "sps.ppgroups@gmail.com",
			  "sree0870@gmail.com",
			  "sridhars@heterohealthcare.com",
			  "srihari.b@heterohealthcare.com",
			  "srikanth.lakkaraju@heterohealthcare.com",
			  "srimuthamil@gmail.com",
			  "srinivas.gajendrula@heterohealthcare.com",
			  "srinivasa.ch@heterohealthcare.com",
			  "srinivasarao.m@heterohealthcare.com",
			  "srinivassarma@ymail.com",
			  "srinoo_gajjelli@yahoo.co.in",
			  "sriramraj.sri@gmail.com",
			  "srreddy@heterohealthcare.com",
			  "subash@heterohealthcare.com",
			  "subburama.ankani@gmail.com",
			  "subham.mail@gmail.com",
			  "subhrohetero@gmail.com",
			  "subrata.chakraborty@heterohealthcare.com",
			  "subrata1211971@gmail.com",
			  "sudhirkurale@heterohealthcare.com",
			  "sudhirnbd@gmail.com",
			  "sudhir_pinki@yahoo.co.in",
			  "sufiyanahmed@heterohealthcare.com",
			  "sugumargenxpharma@gmail.com",
			  "sumanfrenza@gmail.com",
			  "suman_172001@yahoo.com",
			  "sumit.dave@azistaspace.com",
			  "sumit0151989@gmail.com",
			  "sumit07libra@gmail.com",
			  "sumit_muk81@yahoo.com",
			  "sunil@azistaindustries.com",
			  "sunilbasate@gmail.com",
			  "sunilcharak@gmail.com",
			  "sunilgenxhiv@gmail.com",
			  "sunilkarna1975@gmail.com",
			  "sunilkarpe81@rediffmail.com",
			  "sunilp@heterohealthcare.com",
			  "suniltiwaridamarua@gmail.com",
			  "sunilunadkat@heterohealthcare.com",
			  "sunitgupta@heterohealthcare.com",
			  "sunnychawlapanipat@gmail.com",
			  "sureshbabu.n@heterohealthcare.com",
			  "sureshbabu@heterohealthcare.com",
			  "sureshmk.mk@gmail.com",
			  "survase147@gmail.com",
			  "suryaaaa@gmail.com",
			  "sushant.mali28@gmail.com",
			  "susheelgodara27@gmail.com",
			  "tabitha@heterohealthcare.com",
			  "tabrose12@gmail.com",
			  "talk2pranabkr@gmail.com",
			  "tausifshaikh972@gmail.com",
			  "tejasp166@gmail.com",
			  "thangsambi@gmail.com",
			  "thatsmeshiva@rediffmail.com",
			  "tiwari.alok031@gmail.com",
			  "tiwariamit08.2011@rediffmail.com",
			  "tiwari_vinod2@yahoo.com",
			  "umapathi.govindan@gmail.com",
			  "umeshgajbhare@gmail.com",
			  "umeshsvt@rediffmail.com",
			  "umesht45@gmail.com",
			  "uttamvaishnav1947@yahoo.co.in",
			  "vaibhav.khairnar@heterohealthcare.com",
			  "vaibhav01kanade@gmail.com",
			  "varsha.jain86@icloud.com",
			  "varunt@heterohealthcare.com",
			  "vasuaeran@gmail.com",
			  "veena@heterohealthcare.com",
			  "venkysmart01@yahoo.co.in",
			  "verma.rajesh225@gmail.com",
			  "vgp99@rediffmail.com",
			  "vidyasagar.akuthota@azistaindustries.com",
			  "vijay.chorghade@gmail.com",
			  "vijaychorghe01@gmail.com",
			  "vijaym396@gmail.com",
			  "vijay_07sheru@yahoo.co.in",
			  "vikas1979sachdeva@yahoo.co.in",
			  "vikasjainmittal@gmail.com",
			  "vik_jadhav77@yahoo.co.in",
			  "vilaspawale@yahoo.co.in",
			  "vinayaklawate@gmail.com",
			  "vineshpournami@gmail.com",
			  "vinodbhondave27@gmail.com",
			  "vinodcrema@gmail.com",
			  "vinodkanawade970@gmail.com",
			  "vipin.rathi@heterohealthcare.com",
			  "virender.gumber@gmail.com",
			  "vishal.pawar@heterohealthcare.com",
			  "vishal25chandel@gmail.com",
			  "vishalgulati171986@yahoo.com",
			  "vishwanathan.iyer@heterohealthcare.com",
			  "vithal.gangarde@heterohealthcare.com",
			  "vjygkp@rediffmail.com",
			  "volga.cheasm@heterohealthcare.com",
			  "volga.delasm@heterohealthcare.com",
			  "volga.jaiasm@heterohealthcare.com",
			  "volga.kolasm@heterohealthcare.com",
			  "waseem.khanday@gmail.com",
			  "yahiya_at@yahoo.com",
			  "yashsalvia@gmail.com",
			  "yjojireddy@heterohealthcare.com",
			  "yog.sabale@gmail.com",
			  "yogendra.dutt@heterohealthcare.com",
			  "yogendra.paliewal1986@gmail.com",
			  "yogeshpatil@heterohealthcare.com",
			  "yogi18487@gmail.com",
			  "zahid_27sd@yahoo.com"
		  ];
	  
	     try{
	     availableTags.push(EMAILDATA_MNG);
	     }catch(err){}
	  
    function split( val ) {
      return val.split( /,\s*/ );
    }
    function extractLast( term ) {
      return split( term ).pop();
    }
 
    $( "#tags" )
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
          this.value = terms.join( ", " );
          return false;
        }
      });
  } );
  </script>
  <script>

  var availableTags1 =[
                 	  "03.saurav@gmail.com",
                 	 "1981abhay@gmail.com",
                 	 "666puneeth@gmail.com",
                 	 "a.k.singh134@gmail.com",
                 	 "abhaypatel86@yahoo.com",
                 	 "abhijit.jathi2012@gmail.com",
                 	 "abhijit.maity97@gmail.com",
                 	 "abhishek.garg@heterohealthcare.com",
                 	 "abhisheksingh@heterohealthcare.com",
                 	 "adityasharma@heterohealthcare.com",
                 	 "aftab886629@gmail.com",
                 	 "aintwardhiraj@gmail.com",
                 	 "ajaykumar.s@heterohealthcare.com",
                 	 "ajayshinde14@gmail.com",
                 	 "ajit.linge@gmail.com",
                 	 "ajitkumaralkem@gmail.com",
                 	 "aksahoo@heterohealthcare.com",
                 	 "aliahmed@heterohealthcare.com",
                 	 "alok.p@heterohealthcare.com",
                 	 "alok2007@rediffmail.com",
                 	 "amaroajmire@gmail.com",
                 	 "amarvyas79@gmail.com",
                 	 "ameet.upadhyay31@gmail.com",
                 	 "amin.shaikh81@yahoo.com",
                 	 "amit.joshi@heterohealthcare.com",
                 	 "amit.s@heterohealthcare.com",
                 	 "amithetero12@gmail.com",
                 	 "amitmondal210981@yahoo.com",
                 	 "amitnarwal1980@gmail.com",
                 	 "amitsaha1976@yahoo.co.in",
                 	 "amitverma5060@gmail.com",
                 	 "amman_ullah90@yahoo.com",
                 	 "amol.kalbage@gmail.com",
                 	 "amolphule83@gmail.com",
                 	 "amsrinivas.genx@gmail.com",
                 	 "anand9452834006@gmail.com",
                 	 "aneeskhan8522@gmail.com",
                 	 "anilbhandari29@gmail.com",
                 	 "anjan.roychowdhury15@gmail.com",
                 	 "ankittripathi.742@rediffmail.com",
                 	 "ankitvi1@gmail.com",
                 	 "antoni.gounder@heterohealthcare.com",
                 	 "anuj7162@gmail.com",
                 	 "anujdewani@heterohealthcare.com",
                 	 "anupamsuneet@gmail.com",
                 	 "anuradha@heterohealthcare.com",
                 	 "anuragkumarpatel1983@gmail.com",
                 	 "anwar.shaik@heterohealthcare.com",
                 	 "apoorva12890@gmail.com",
                 	 "arbindks@rediffmail.com",
                 	 "ardendu_swift@rediffmail.com",
                 	 "arijit.dutta@heterohealthcare.com",
                 	 "arunmishra010@gmail.com",
                 	 "arup@heterohealthcare.com",
                 	 "arupmuk77@yahoo.com",
                 	 "arvind.mishra768@gmail.com",
                 	 "arvindabbottsingh@gmail.com",
                 	 "arvindsidana85@yahoo.co.in",
                 	 "arvindyadav1036@yahoo.com",
                 	 "ashaygawade@gmail.com",
                 	 "ashish.phl@gmail.com",
                 	 "ashutosh00784@gmail.com",
                 	 "ashutosh_glenmark@yahoo.com",
                 	 "asim.shataxi@rediffmail.com",
                 	 "ateeqahmed786@gmail.com",
                 	 "atul.salve@heterohealthcare.com",
                 	 "audayvansi1@gmail.com",
                 	 "aurokanta.m@heterohealthcare.com",
                 	 "avadhesh.r@heterohealthcare.com",
                 	 "avadhut.sas@gmail.com",
                 	 "avadhutsangar85@gmail.com",
                 	 "aveshsrivastav.fdc@gmail.com",
                 	 "avinash.koturwar1984@gmail.com",
                 	 "ayanbal@gmail.com",
                 	 "ayyaj.mulla@gmail.com",
                 	 "b.behuria@rediffmail.com",
                 	 "b.jena@heterohealthcare.com",
                 	 "babuveeresh6@gmail.com",
                 	 "bajirao.karande@heterohealthcare.com",
                 	 "balajune693@gmail.com",
                 	 "balaskh1100@gmail.com",
                 	 "baldevrampatel@rediffmail.com",
                 	 "bangal.soumen@gmail.com",
                 	 "banik_abhijeet@rediffmail.com",
                 	 "basavarajyaligar7@gmail.com",
                 	 "basheerali.nzb@gmail.com",
                 	 "bhanupratap@heterohealthcare.com",
                 	 "bharat.gehalot@heterohealthcare.com",
                 	 "bharatpandya@heterohealthcare.com",
                 	 "bharatsiddy@yahoo.com",
                 	 "bhaskarasarma_my@yahoo.com",
                 	 "bhaveshsavalia2006@yahoo.co.in",
                 	 "bhavin_shah7887@yahoo.co.in",
                 	 "bhayekar_hhl1@yahoo.com",
                 	 "bhushan.yewale@heterohealthcare.com",
                 	 "bijay_zephyr@rediffmail.com",
                 	 "birendra.y@heterohealthcare.com",
                 	 "bj_ajwalia05@yahoo.co.in",
                 	 "bm.nagamuthuraja@yahoo.co.in",
                 	 "bose@heterohealthcare.com",
                 	 "brajgaurav@azistaindustries.com",
                 	 "brijeshsharma@heterohealthcare.com",
                 	 "bsk.hussain@yahoo.com",
                 	 "bulbulekishor@gmail.com",
                 	 "bunke_hemant@rediffmail.com",
                 	 "cgajjar19@gmail.com",
                 	 "chakrabortyindrajit5@gmail.com",
                 	 "chandankumar.singh@heterohealthcare.com",
                 	 "chandrashekhar@heterohealthcare.com",
                 	 "charan.chandana@gmail.com",
                 	 "charanpreet.singh@heterohealthcare.com",
                 	 "chaubey.pramod@gmail.com",
                 	 "chetan.marne26@gmail.com",
                 	 "chhavvie@gmail.com",
                 	 "chiragkanugo@rocketmail.com",
                 	 "chiragsetia15@gmail.com",
                 	 "chiranjeevi.velagala@heterohealthcare.com",
                 	 "cprasad80@rediffmail.com",
                 	 "creativepravesh@gmail.com",
                 	 "csreddy@heterohealthcare.com",
                 	 "damodararao.m@heterohealthcare.com",
                 	 "dattanivas123@gmail.com",
                 	 "dattatraya.d@heterohealthcare.com",
                 	 "dayasuryawanshi15@gmail.com",
                 	 "debasishkol90@gmail.com",
                 	 "deepakdhunna96@gmail.com",
                 	 "deepaksharma@heterohealthcare.com",
                 	 "deshmukh.v@heterohealthcare.com",
                 	 "dev.com36@gmail.com",
                 	 "devendra.pal2009@gmail.com",
                 	 "devijay.pharma@gmail.com",
                 	 "dhananjay.sandve@heterohealthcare.com",
                 	 "dhaval.patel@heterohealthcare.com",
                 	 "dhruvparmar16@gmail.com",
                 	 "diaspaahmedabad.asm@heterohealthcare.com",
                 	 "diaspaaurangabad.asm@heterohealthcare.com",
                 	 "diaspabangalore.asm@heterohealthcare.com",
                 	 "diaspabaroda.asm@heterohealthcare.com",
                 	 "diaspabhopal.asm@heterohealthcare.com",
                 	 "diaspabhubaneswar.asm@heterohealthcare.com",
                 	 "diaspacalicut.asm@heterohealthcare.com",
                 	 "diaspachandigarh.asm@heterohealthcare.com",
                 	 "diaspachennai.asm1@heterohealthcare.com",
                 	 "diaspachennai.asm2@heterohealthcare.com",
                 	 "diaspacoimbatore.asm@heterohealthcare.com",
                 	 "diaspacoimbatore.fe2@heterohealthcare.com",
                 	 "diaspacuttack.asm@heterohealthcare.com",
                 	 "diaspadelhi.asm1@heterohealthcare.com",
                 	 "diaspadelhi.asm2@heterohealthcare.com",
                 	 "diaspadelhi.asm3@heterohealthcare.com",
                 	 "diaspaernakulam.asm@heterohealthcare.com",
                 	 "diaspaguwahati.asm@heterohealthcare.com",
                 	 "diaspahowrah.asm@heterohealthcare.com",
                 	 "diaspahubli.asm@heterohealthcare.com",
                 	 "diaspahyderabad.asm1@heterohealthcare.com",
                 	 "diaspahyderabad.asm2@heterohealthcare.com",
                 	 "diaspaindore.asm@heterohealthcare.com",
                 	 "diaspajabalpur.asm@heterohealthcare.com",
                 	 "diaspajaipur.asm@heterohealthcare.com",
                 	 "diaspajodhpur.asm@heterohealthcare.com",
                 	 "diaspakanpur.asm@heterohealthcare.com",
                 	 "diaspakolhapur.asm@heterohealthcare.com",
                 	 "diaspakolkata.asm1@heterohealthcare.com",
                 	 "diaspakolkata.asm2@heterohealthcare.com",
                 	 "diaspakolkata.fe3@heterohealthcare.com",
                 	 "diaspakurnool.asm@heterohealthcare.com",
                 	 "diaspalucknow.asm@heterohealthcare.com",
                 	 "diaspaludhiana.asm@heterohealthcare.com",
                 	 "diaspamadurai.asm@heterohealthcare.com",
                 	 "diaspamangalore.asm@heterohealthcare.com",
                 	 "diaspameerut.asm@heterohealthcare.com",
                 	 "diaspamumbai.asm1@heterohealthcare.com",
                 	 "diaspamumbai.asm2@heterohealthcare.com",
                 	 "diaspamumbai.asm3@heterohealthcare.com",
                 	 "diaspamuzaffarpur.asm@heterohealthcare.com",
                 	 "diaspanagpur.asm@heterohealthcare.com",
                 	 "diaspanavsari.fe@heterohealthcare.com",
                 	 "diaspapatna.asm@heterohealthcare.com",
                 	 "diaspapune.asm1@heterohealthcare.com",
                 	 "diaspapune.asm2@heterohealthcare.com",
                 	 "diasparajkot.asm@heterohealthcare.com",
                 	 "diasparanchi.asm@heterohealthcare.com",
                 	 "diaspasilchar.asm@heterohealthcare.com",
                 	 "diaspasiliguri.asm@heterohealthcare.com",
                 	 "diaspasrinagar.asm@heterohealthcare.com",
                 	 "diaspavaranasi.asm@heterohealthcare.com",
                 	 "diaspavijayawada.asm@heterohealthcare.com",
                 	 "diaspavizag.asm@heterohealthcare.com",
                 	 "dibyendu.roy@heterohealthcare.com",
                 	 "dilip.t@heterohealthcare.com",
                 	 "dineshkumark2011@yahoo.com",
                 	 "divya.srinivasulu@yahoo.com",
                 	 "dk_awasthi123@rediffmail.com",
                 	 "dp9702@gmail.com",
                 	 "dpdrl194@rediffmail.com",
                 	 "dpkk1987@gmail.com",
                 	 "dr.indrajeetkumar@gmail.com",
                 	 "dvk.ravishankar@heterohealthcare.com",
                 	 "dvshivajirao@gmail.com",
                 	 "fakrushaik744@gmail.com",
                 	 "g.senthilhetero@gmail.com",
                 	 "gaikwadmd2007@rediffmail.com",
                 	 "gallasarathmba@gmail.com",
                 	 "ganesh.kale@heterohealthcare.com",
                 	 "gangadhartarlekar@yahoo.in",
                 	 "gargjai_143@yahoo.co.in",
                 	 "gaurav@heterohealthcare.com",
                 	 "gautam_p10@yahoo.in",
                 	 "genxhardeep@gmail.com",
                 	 "girishgorekar@gmail.com",
                 	 "gonthinabp@gmail.com",
                 	 "goswaminitin0007@gmail.com",
                 	 "gunda.srikanth@gmail.com",
                 	 "guptaadesh88@yahoo.com",
                 	 "guptashashikant71@gmail.com",
                 	 "hadibandhumajhi@yahoo.co.in",
                 	 "harikant4@gmail.com",
                 	 "harish_hetro@yahoo.com",
                 	 "harshapalyam@gmail.com",
                 	 "heeralal.babu@rediffmail.com",
                 	 "hemant.patil@heterohealthcare.com",
                 	 "hemant.thakur@heterohealthcare.com",
                 	 "hemantrm121@gmail.com",
                 	 "heteroashok@gmail.com",
                 	 "heteromau@gmail.com",
                 	 "heterovikram@gmail.com",
                 	 "himanshubhatia.smu@gmail.com",
                 	 "hiradhar@heterohealthcare.com",
                 	 "imlakkhan@gmail.com",
                 	 "imran.1692@rediffmail.com",
                 	 "indrajeetrajput87@gmail.com",
                 	 "indreshbkumar@gmail.com",
                 	 "jadhavprashant001@gmail.com",
                 	 "jagdish.n@heterohealthcare.com",
                 	 "jainendrabagdaniya@gmail.com",
                 	 "jeet1103@gmail.com",
                 	 "jeet87@rocketmail.com",
                 	 "jigar.shah@heterohealthcare.com",
                 	 "jignesh.patel@heterohealthcare.com",
                 	 "jintu_daimari@yahoo.com",
                 	 "jitendra.lim@gmail.com",
                 	 "jitendrasingh@heterohealthcare.com",
                 	 "jitheshkumar2007@yahoo.co.in",
                 	 "jolly.k@heterohealthcare.com",
                 	 "joyanta.banerjee@rediffmail.com",
                 	 "jskayastha@gmail.com",
                 	 "kaalinga.narayan@azistaindustries.com",
                 	 "kallolchakraborti@yahoo.co.in",
                 	 "kalpana.sinkar@heterohealthcare.com",
                 	 "kalyandas@heterohealthcare.com",
                 	 "kambam2@rediffmail.com",
                 	 "kanahaiya.tc3@gmail.com",
                 	 "kapilksharma85@gmail.com",
                 	 "kapilmukati@heterohealthcare.com",
                 	 "kapsepawan@rediffmail.com",
                 	 "karanhetero@gmail.com",
                 	 "karthik.m@heterohealthcare.com",
                 	 "kashinath.sontakke@yahoo.com",
                 	 "kdaraji007@gmail.com",
                 	 "kdesai17@gmail.com",
                 	 "khanna_shivam21@rediffmail.com",
                 	 "khare.mudit@rediffmail.com",
                 	 "khasgiwal@heterohealthcare.com",
                 	 "khasim_khan464@yahoo.co.in",
                 	 "khatiksamir@gmail.com",
                 	 "khatrilokesh52@gmail.com",
                 	 "khetanaashish@yahoo.com",
                 	 "kiran.singh@heterohealthcare.com",
                 	 "kisanlalge@gmail.com",
                 	 "kishan.k@heterohealthcare.com",
                 	 "kishore.mk18@gmail.com",
                 	 "kothireddyravinderreddy@gmail.com",
                 	 "koti.mba34@gmail.com",
                 	 "kotireddy.mekala2@gmail.com",
                 	 "kotireddy@heterohealthcare.com",
                 	 "kpandey251@gmail.com",
                 	 "ksthakur02@gmail.com",
                 	 "ksvitthal@gmail.com",
                 	 "kuldip.rathour@gmail.com",
                 	 "kumar.kn.83@gmail.com",
                 	 "kumar.shambhu860@gmail.com",
                 	 "kumarjanga@yahoo.com",
                 	 "kumarsnlsinha183@gmail.com",
                 	 "kumarswamyheterohealthcare@gmail.com",
                 	 "kundu.abhijit4@gmai.com",
                 	 "kvkreddy@heterohealthcare.com",
                 	 "lakshman_kundur@yahoo.co.in",
                 	 "lakshmesh77@gmail.com",
                 	 "m.imranrosan1993@gmail.com",
                 	 "madan.d@heterohealthcare.com",
                 	 "mahaveerdevrath@yahoo.com",
                 	 "mahesh.s@heterohealthcare.com",
                 	 "mahesh5gunjite@yahoo.co.in",
                 	 "maheshgs123@gmail.com",
                 	 "mail2syedjaveed@gmail.com",
                 	 "majumdaramit41@gmail.com",
                 	 "manash@heterohealthcare.com",
                 	 "manasp@heterohealthcare.com",
                 	 "manbolokhande@gmail.com",
                 	 "mangesh.p@heterohealthcare.com",
                 	 "mangeshawate2011@gmail.com",
                 	 "mangeshpaswan@yahoo.co.in",
                 	 "mani.ps@heterohealthcare.com",
                 	 "manikanta.atyam@gmail.com",
                 	 "manish958@gmail.com",
                 	 "manishhaya@gmail.com",
                 	 "manishshingh2689@gmail.com",
                 	 "manish_nadkar@yahoo.com",
                 	 "mankarand@heterohealthcare.com",
                 	 "manoj.pahwa@live.com",
                 	 "manoj.saxena@heterohealthcare.com",
                 	 "manoj@heterohealthcare.com",
                 	 "manojsingh@heterohealthcare.com",
                 	 "masoomsarwar@gmail.com",
                 	 "mayurgutpa636@gmail.com",
                 	 "meetmyheart@ymail.com",
                 	 "mgajendra82@gmail.com",
                 	 "mgaswani@yahoo.in",
                 	 "mitulpatel@heterohealthcare.com",
                 	 "mmpl.chandru@gmail.com",
                 	 "mohannavalav@gmail.com",
                 	 "momjidabhi@gmail.com",
                 	 "mona.kazi@heterohealthcare..com",
                 	 "monu4546@gmail.com",
                 	 "mr.nasir21@rediffmail.com",
                 	 "mrkrvarma@yahoo.in",
                 	 "msyogesh92@gmail.com",
                 	 "mudassar.inamdar@rediffmail.com",
                 	 "mufaddalbhavti@gmail.com",
                 	 "mukeshgupta@heterohealthcare.com",
                 	 "murale_satish@rediffmail.com",
                 	 "murli_kdm06@yahoo.com",
                 	 "murthy_narasimha@yahoo.in",
                 	 "mushtaq_rs@yahoo.co.in",
                 	 "nagar.sanjeev79@gmail.com",
                 	 "nagaraju.v@heterohealthcare.com",
                 	 "namamipandey@heterohealthcare.com",
                 	 "nandlal.s@heterohealthcare.com",
                 	 "nandu.bdm@gmail.com",
                 	 "narayanankannan01@gmail.com",
                 	 "narendra84trivedi@gmail.com",
                 	 "naveen_deshpande9@yahoo.com",
                 	 "nazef@heterohealthcare.com",
                 	 "neerajhetero@gmail.com",
                 	 "nehru.dadisetti@heterohealthcare.com",
                 	 "nemai.mukherjee@heterohealthcare.com",
                 	 "nigam_sameer@yahoo.com",
                 	 "niharrsilu1983@gmail.com",
                 	 "nikunj1987joshi@gmail.com",
                 	 "nikunjdave1114@yahoo.co.in",
                 	 "nilay.chatterjee@heterohealthcare.com",
                 	 "nileshpatel@heterohealthcare.com",
                 	 "niraj.shah@azistaspace.com",
                 	 "niranjan.chanamala@yahoo.com",
                 	 "nirav.bhatt@heterohealthcare.com",
                 	 "nirav_sheth2110@yahoo.co.in",
                 	 "nishaar25@gmail.com",
                 	 "nishant.emcure@gmail.com",
                 	 "nishanth.d@heterohealthcare.com",
                 	 "nishu_sweetbacha089@yahoo.com",
                 	 "nitin9oct@gmail.com",
                 	 "nitinb@heterohealthcare.com",
                 	 "nitinpatil1986@yahoo.co.in",
                 	 "nnramesh70@yahoo.co.in",
                 	 "obulareddy.pbc@heterohealthcare.com",
                 	 "om.ashish6852@gmail.com",
                 	 "omkar.yadav@heterohealthcare.com",
                 	 "omprakash.b@heterohealthcare.com",
                 	 "omprakash.swami@heterohealthcare.com",
                 	 "onkardas@gmail.com",
                 	 "p.munivel@gmail.com",
                 	 "palashdas@heterohealthcare.com",
                 	 "palnarendra88@gmail.com",
                 	 "pandeylesante1979@gmail.com",
                 	 "pankaj.dubey@heterohealthcare.com",
                 	 "pankaj.dwivedi110@gmail.com",
                 	 "pankaj.surendra.sharma@gmail.com",
                 	 "pankajkumar.r@heterohealthcare.com",
                 	 "pankajneve2012@gmail.com",
                 	 "pankajvyaskaithal@yahoo.com",
                 	 "paresh_jarande@rediffmail.com",
                 	 "parveenkumar333@gmail.com",
                 	 "pathak87pawan@gmail.com",
                 	 "pathakamitj@gmail.com",
                 	 "patil.hetero@gmail.com",
                 	 "pattnaiklipu@gmail.com",
                 	 "pavanborle_hhl1@yahoo.com",
                 	 "pdubey1947@rediffmail.com",
                 	 "peertalib11@gmail.com",
                 	 "pingal.1982@gmail.com",
                 	 "pp.pareek@gmail.com",
                 	 "pra.patel18@gmail.com",
                 	 "prabhat.kumar481@gmail.com",
                 	 "prabhatsharma394@gmail.com",
                 	 "prabhulove.dwivedi@gmail.com",
                 	 "pradeepmalandkar1903@gmail.com",
                 	 "pradeepmishra@heterohealthcare.com",
                 	 "pradip.3june@gmail.com",
                 	 "pradip.chavan84@gmail.com",
                 	 "prajithlaxman@gmail.com",
                 	 "pranava.kj@gmail.com",
                 	 "prasanna0501@rediffmail.com",
                 	 "prasannavarma29@yahoo.com",
                 	 "prashant@heterohealthcare.com",
                 	 "prashantdumma@gmail.com",
                 	 "prashanthd@heterohealthcare.com",
                 	 "prateek.jain@heterohealthcare.com",
                 	 "praveengupta11828@gmail.com",
                 	 "praveshtiwari82@gmail.com",
                 	 "pravin26.p@gmail.com",
                 	 "pravir.k.srivastava@gmail.com",
                 	 "psgpso@rediffmail.com",
                 	 "ptamrakar.9012@gmail.com",
                 	 "puneet24janv@gmail.com",
                 	 "purnendu.kar1@gmail.com",
                 	 "pushpaksonawane26@gmail.com",
                 	 "pushpendar_kumar@yahoo.com",
                 	 "r.hospure31@gmail.com",
                 	 "radhakrishna.pharma@gmail.com",
                 	 "raghavendran.vedam@azistaaerospace.com",
                 	 "raghupathijatti@gmail.com",
                 	 "raghuvendra.singh@heterohealthcare.com",
                 	 "rahu.lanke@gmail.com",
                 	 "rahuldhamande21@gmail.com",
                 	 "rajaislam7@gmail.com",
                 	 "rajanaristo52@gmail.com",
                 	 "rajarims@gmail.com",
                 	 "raja_bhaskar@yahoo.com",
                 	 "rajeev.awasthi.ps@gmail.com",
                 	 "rajeevojha.ojha30@gmail.com",
                 	 "rajeevranjanmukund@yahoo.in",
                 	 "rajendra.rajendra029@gmail.com",
                 	 "rajendra291987@gmail.com",
                 	 "rajesh.todupunuri4447@gmail.com",
                 	 "rajeshdharmik12@gmail.com",
                 	 "rajeshsodha409@gmail.com",
                 	 "rajiv.zenovz@yahoo.in",
                 	 "rajkaushikmeerut2mumbai@gmail.com",
                 	 "rajsinghemure@gmail.com",
                 	 "raju.budime1@gmail.com",
                 	 "raju.shanmuki01@gmail.com",
                 	 "rakesh.singh@heterohealthcare.com",
                 	 "rakeshdhamde@gmail.com",
                 	 "ramanuj.banerjee@heterohealthcare.com",
                 	 "ramanuja@heterohealthcare.com",
                 	 "ramaswamy.v@heterohealthcare.com",
                 	 "ramesh.chimurkar@heterohealthcare.com",
                 	 "rameshdeepi101@gmail.com",
                 	 "rameshmishra6761@gmail.com",
                 	 "rane@heterohealthcare.com",
                 	 "rathore.dops@gmail.com",
                 	 "ratnadeep_paul@yahoo.com",
                 	 "ravaljignesh83@gmail.com",
                 	 "ravi.k@heterohealthcare.com",
                 	 "ravichandra@heterohealthcare.com",
                 	 "ravindrakhose2011@gmail.com",
                 	 "ravisaini533@gmail.com",
                 	 "ravish9211@gmail.com",
                 	 "raviswaraj@gmail.com",
                 	 "rawal_sun@yahoo.com",
                 	 "rishishukla@heterohealthcare.com",
                 	 "ritamore210@gmail.com",
                 	 "riteshde@gmail.com",
                 	 "rkreddy.ch@heterohealthcare.com",
                 	 "rmheterofrenza@gmail.com",
                 	 "rofiqulislam98@yahoo.com",
                 	 "rohitks.genx@gmail.com",
                 	 "rrbsamy@gmail.com",
                 	 "rsaravanaguru@gmail.com",
                 	 "ruturajpawar11@gmail.com",
                 	 "s@heterohealthcare.com",
                 	 "sachinbangar38@gmail.com",
                 	 "sachinghuge33@gmail.com",
                 	 "sachinpadwal24@gmail.com",
                 	 "sachinrahate@heterohealthcare.com",
                 	 "sachinshah9958@gmail.com",
                 	 "sadrulali@heterohealthcare.com",
                 	 "saheebn@gmail.com",
                 	 "saikatchakraborty@heterohealthcare.com",
                 	 "saikumar.b@heterohealthcare.com",
                 	 "sam.shinde67@gmail.com",
                 	 "sameer11105@gmail.com",
                 	 "samya1069@gmail.com",
                 	 "sandeepbejjanki966@gmail.com",
                 	 "sandeepdeongp@yahoo.in",
                 	 "sandhya@heterohealthcare.com",
                 	 "sandipgodbole_2008@rediffmail.com",
                 	 "sandiplonari@gmail.com",
                 	 "sangam.partap45@gmail.com",
                 	 "sanjay.hetero@gmail.com",
                 	 "sanjay.thobhani@azistaspace.com",
                 	 "sanjayalk.sk@gmail.com",
                 	 "sanjaypandeyhhcl@gmail.com",
                 	 "sanjeet.hetero@gmail.com",
                 	 "sanjeev.m@heterohealthcare.com",
                 	 "sanjibb66@gmail.com",
                 	 "sanjivhhl@gmail.com",
                 	 "sanjoy.barasat.sutta@gmail.com",
                 	 "santhoshreddy.y@heterohealthcare.com",
                 	 "santhu_1163@yahoo.com",
                 	 "santoshkhamgal@gmail.com",
                 	 "sarabindu.ghosh1986@gmail.com",
                 	 "sarvesh.gupta2012@gmail.com",
                 	 "satish.verma@heterohealthcare.com",
                 	 "satishabbott@gmail.com",
                 	 "satishpa6761@gmail.com",
                 	 "satyam85kumar@gmail.com",
                 	 "saxenagaurav2009@gmail.com",
                 	 "say2mohan@gmail.com",
                 	 "sbelmanchi@gmail.com",
                 	 "sdprasad@heterohealthcare.com",
                 	 "sebabrata@heterohealthcare.com",
                 	 "seemakadam76@yahoo.com",
                 	 "sendtosumesh@gmail.com",
                 	 "senthilsch@yahoo.co.in",
                 	 "sgd310@gmail.com",
                 	 "shah.alam81@gmail.com",
                 	 "shahminkal2006@yahoo.co.in",
                 	 "shahnish26@gmail.com",
                 	 "shailesh@heterohealthcare.com",
                 	 "shail_kgpharma@yahoo.com",
                 	 "shantaram.p@heterohealthcare.com",
                 	 "shantaram@heterohealthcare.com",
                 	 "sharad@heterohealthcare.com",
                 	 "sharma.chandan54@gmial.com",
                 	 "sharmaamit342@gmail.com",
                 	 "sharmashrikrishna38@gmail.com",
                 	 "sharmisthabose1985@gmail.com",
                 	 "shashikanth_mr@yahoo.co.in",
                 	 "shastri@heterohealthcare.com",
                 	 "sheetal@heterohealthcare.com",
                 	 "shravan.reddy@heterohealthcare.com",
                 	 "shreekant.panigrahi@gmail.com",
                 	 "shreyas.krishetero@gmail.co",
                 	 "shri.tambade@gmail.com",
                 	 "shrirang.bapat@heterohealthcare.com",
                 	 "shritakate@gmail.com",
                 	 "shubhadeep2003@rediffmail.com",
                 	 "shyamendrafdc@gmail.com",
                 	 "siddunbl@gmail.com",
                 	 "sikandar@heterohealthcare.com",
                 	 "sisodiadevendra@yahoomail.com",
                 	 "sk.khuntia87@gmail.com",
                 	 "sk.valli28@gmail.com",
                 	 "sk.wale@rediffmail.com",
                 	 "sm_177@rediffmail.com",
                 	 "solankibhavesh121@gmail.com",
                 	 "souradipdas89@gmail.com",
                 	 "sp.coiabm@heterohealthcare.com",
                 	 "sp.hydabm@heterohealthcare.com",
                 	 "sp.punabm@heterohealthcare.com",
                 	 "sp.thrabm@heterohealthcare.com",
                 	 "sp.triabm@heterohealthcare.com",
                 	 "sp.vijabm@heterohealthcare.com",
                 	 "sps.ppgroups@gmail.com",
                 	 "sree0870@gmail.com",
                 	 "sridhars@heterohealthcare.com",
                 	 "srihari.b@heterohealthcare.com",
                 	 "srikanth.lakkaraju@heterohealthcare.com",
                 	 "srimuthamil@gmail.com",
                 	 "srinivas.gajendrula@heterohealthcare.com",
                 	 "srinivasa.ch@heterohealthcare.com",
                 	 "srinivasarao.m@heterohealthcare.com",
                 	 "srinivassarma@ymail.com",
                 	 "srinoo_gajjelli@yahoo.co.in",
                 	 "sriramraj.sri@gmail.com",
                 	 "srreddy@heterohealthcare.com",
                 	 "subash@heterohealthcare.com",
                 	 "subburama.ankani@gmail.com",
                 	 "subham.mail@gmail.com",
                 	 "subhrohetero@gmail.com",
                 	 "subrata.chakraborty@heterohealthcare.com",
                 	 "subrata1211971@gmail.com",
                 	 "sudhirkurale@heterohealthcare.com",
                 	 "sudhirnbd@gmail.com",
                 	 "sudhir_pinki@yahoo.co.in",
                 	 "sufiyanahmed@heterohealthcare.com",
                 	 "sugumargenxpharma@gmail.com",
                 	 "sumanfrenza@gmail.com",
                 	 "suman_172001@yahoo.com",
                 	 "sumit.dave@azistaspace.com",
                 	 "sumit0151989@gmail.com",
                 	 "sumit07libra@gmail.com",
                 	 "sumit_muk81@yahoo.com",
                 	 "sunil@azistaindustries.com",
                 	 "sunilbasate@gmail.com",
                 	 "sunilcharak@gmail.com",
                 	 "sunilgenxhiv@gmail.com",
                 	 "sunilkarna1975@gmail.com",
                 	 "sunilkarpe81@rediffmail.com",
                 	 "sunilp@heterohealthcare.com",
                 	 "suniltiwaridamarua@gmail.com",
                 	 "sunilunadkat@heterohealthcare.com",
                 	 "sunitgupta@heterohealthcare.com",
                 	 "sunnychawlapanipat@gmail.com",
                 	 "sureshbabu.n@heterohealthcare.com",
                 	 "sureshbabu@heterohealthcare.com",
                 	 "sureshmk.mk@gmail.com",
                 	 "survase147@gmail.com",
                 	 "suryaaaa@gmail.com",
                 	 "sushant.mali28@gmail.com",
                 	 "susheelgodara27@gmail.com",
                 	 "tabitha@heterohealthcare.com",
                 	 "tabrose12@gmail.com",
                 	 "talk2pranabkr@gmail.com",
                 	 "tausifshaikh972@gmail.com",
                 	 "tejasp166@gmail.com",
                 	 "thangsambi@gmail.com",
                 	 "thatsmeshiva@rediffmail.com",
                 	 "tiwari.alok031@gmail.com",
                 	 "tiwariamit08.2011@rediffmail.com",
                 	 "tiwari_vinod2@yahoo.com",
                 	 "umapathi.govindan@gmail.com",
                 	 "umeshgajbhare@gmail.com",
                 	 "umeshsvt@rediffmail.com",
                 	 "umesht45@gmail.com",
                 	 "uttamvaishnav1947@yahoo.co.in",
                 	 "vaibhav.khairnar@heterohealthcare.com",
                 	 "vaibhav01kanade@gmail.com",
                 	 "varsha.jain86@icloud.com",
                 	 "varunt@heterohealthcare.com",
                 	 "vasuaeran@gmail.com",
                 	 "veena@heterohealthcare.com",
                 	 "venkysmart01@yahoo.co.in",
                 	 "verma.rajesh225@gmail.com",
                 	 "vgp99@rediffmail.com",
                 	 "vidyasagar.akuthota@azistaindustries.com",
                 	 "vijay.chorghade@gmail.com",
                 	 "vijaychorghe01@gmail.com",
                 	 "vijaym396@gmail.com",
                 	 "vijay_07sheru@yahoo.co.in",
                 	 "vikas1979sachdeva@yahoo.co.in",
                 	 "vikasjainmittal@gmail.com",
                 	 "vik_jadhav77@yahoo.co.in",
                 	 "vilaspawale@yahoo.co.in",
                 	 "vinayaklawate@gmail.com",
                 	 "vineshpournami@gmail.com",
                 	 "vinodbhondave27@gmail.com",
                 	 "vinodcrema@gmail.com",
                 	 "vinodkanawade970@gmail.com",
                 	 "vipin.rathi@heterohealthcare.com",
                 	 "virender.gumber@gmail.com",
                 	 "vishal.pawar@heterohealthcare.com",
                 	 "vishal25chandel@gmail.com",
                 	 "vishalgulati171986@yahoo.com",
                 	 "vishwanathan.iyer@heterohealthcare.com",
                 	 "vithal.gangarde@heterohealthcare.com",
                 	 "vjygkp@rediffmail.com",
                 	 "volga.cheasm@heterohealthcare.com",
                 	 "volga.delasm@heterohealthcare.com",
                 	 "volga.jaiasm@heterohealthcare.com",
                 	 "volga.kolasm@heterohealthcare.com",
                 	 "waseem.khanday@gmail.com",
                 	 "yahiya_at@yahoo.com",
                 	 "yashsalvia@gmail.com",
                 	 "yjojireddy@heterohealthcare.com",
                 	 "yog.sabale@gmail.com",
                 	 "yogendra.dutt@heterohealthcare.com",
                 	 "yogendra.paliewal1986@gmail.com",
                 	 "yogeshpatil@heterohealthcare.com",
                 	 "yogi18487@gmail.com",
                 	 "zahid_27sd@yahoo.com"
            		  ];
            //availableTags.push('venu@gmail.com');
            	 
  $( function() {
	  
    function split( val ) {
      return val.split( /,\s*/ );
    }
    function extractLast( term ) {
      return split( term ).pop();
    }
 
    $( "#tags1" )
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
            availableTags1, extractLast( request.term ) ) );
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
          terms.push("");
          this.value = terms.join(",");
          return false;
        }
      });
  } );
  </script>
  <script>
  function GetLoadData(val){
	  
	  $("#from").val("");
	  $("#to").val("");
	  
	 if(val.value=="OD"){
		 document.getElementById("From_location1").style.display='';
		 document.getElementById("To_location1").style.display='';
		 document.getElementById("From_location").style.display='';
		 document.getElementById("To_location").style.display='';
	 }else{
		 document.getElementById("From_location").style.display='none';
		 document.getElementById("To_location").style.display='none';
		 
		 document.getElementById("From_location1").style.display='none';
		 document.getElementById("To_location1").style.display='none';
		 
	 }
	 
  if(val.value!="Select"){
	  // alert("34");
	   $("#from").val("");
	   startingdate ="";
	   
	   $("#HalfDay").attr('checked',false);
	  $("#HalfDay").attr('disabled',false);
	  try{
		  var SelectedLeaveType=$("#Leave_Type").val();
		  var LeaveConditions="";
		  
			if(SelectedLeaveType!="LOP"){
				
				        if(SelectedLeaveType=="EL"){
				        	
				        	$("#HalfDay").attr('checked',false);
				        	$("#HalfDay").attr('disabled',true);
				        }else{
				        	
				        	$("#HalfDay").attr('checked',false);
				        	$("#HalfDay").attr('disabled',false);
				        }
				
				       LeaveConditions=$("#"+SelectedLeaveType+"").val();
					   var SplitData=LeaveConditions.split("-");
					   MaxLeaves=SplitData[1];
					   MaxLeaves_temp=SplitData[1];
					   MaxLeaves= MaxLeaves_temp;
					   
					   BackDays=SplitData[4]
					   
					  // alert(BackDays);
					   
					   if(eval(MaxLeaves)<=eval(MaxLeaves_temp)){
						   MaxLeaves=MaxLeaves;
					   }else if(eval(MaxLeaves)>=eval(MaxLeaves_temp)){
						   MaxLeaves=MaxLeaves_temp;
					   }
					   if(eval(MaxLeaves)>0 && eval(MaxLeaves)<1){
						   MaxLeaves=0;
					   }else{
						   MaxLeaves=eval(MaxLeaves)-1;
					   }
					   
					   
					   if(SelectedLeaveType=="SL"){
							 // alert(SelectedLeaveType);
							var tempStartDate11 = new Date();
							
							default_end11 = new Date(tempStartDate11.getFullYear(), tempStartDate11.getMonth(), tempStartDate11.getDate());
							
							//alert(default_end11);
							
							//  $("#from").datepicker('option', 'minDate', startingdate );
							 // $("#from").datepicker("option","maxDate", default_end11);
							  
							
							
					  } else{
							  
							   var tempStartDate11 = new Date();
							   // alert(tempStartDate11.getMonth() +"tempStartDate11.getMonth()");
							default_end11 = new Date(tempStartDate11.getFullYear(), 12, 31);
					  }
					   
					   
					   
					   
				   }else{
					   var MaxLeaves="365"; 
					 //  tempStartDate = new Date();
					 //  startingdate = new Date(tempStartDate.getFullYear(), tempStartDate.getMonth(), tempStartDate.getDate()-4);
					   BackDays="0";
					   
					   var tempStartDate11 = new Date();
					   // alert(tempStartDate11.getMonth() +"tempStartDate11.getMonth()");
					  default_end11 = new Date(tempStartDate11.getFullYear(), 12, 31);
					
				   }
				   }catch(err){
					    alert("Browser :"+err.message);
				   }
				   
				   tempStartDate = new Date();
				   
				   startingdate = new Date(tempStartDate.getFullYear(), tempStartDate.getMonth(), tempStartDate.getDate()-BackDays);
				   
				 
				   
				//  alert("startingdate:"+startingdate);
				   Dpicker();
  }
	  
}  
/* function Choose_Location(val){
		 if(val.id=="From_loc"){
			 if(val.value=="OTHER"){
			  document.getElementById("From_location").style.display='';
			  document.getElementById("From_location").value='';
			 }else{
				 document.getElementById("From_location").style.display='none';
				 document.getElementById("From_location").value=val.value; 
			 }
		 }else if(val.id=="To_loc"){
			 if(val.value=="OTHER"){
			  document.getElementById("To_location").style.display='';
			  document.getElementById("To_location").value="";
			 }else{
				 document.getElementById("To_location").style.display='none';
				 document.getElementById("To_location").value=val.value; 
			 }
		 }
} */

</script>
  
  <%-- 	<script src="MyAng.js"></script>
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
   
    $scope.Data_2=<%=EmpDOB%>;
    $scope.Data_3=<%=HOLIDAYS_PG%>;
  
    $scope.empid = Data.EMPLOYEESEQUENCENO;
    $scope.empname= Data.CALLNAME;
    $scope.emp_doj= Data.DATEOFJOIN;
    $scope.emp_Dep= Data.DEPARTMENT;
    $scope.emp_Des= Data.DESIGNATION;
    $scope.emp_job_type= Data.TYPE;
    
	}catch(err){
		alert("Please Try Again..");
	}
 });
</script> --%>
</head>
	<body onload="LeaveBalLoad();">
		<section class="body">
					<!-- start: page -->
					
				
					<div id="description" class="wrapper">
					<div class="container">
						<div class="row center_div1" >
							<div class="col-md-12">
								<div class="panel panel-danger" >
									<div class="panel-heading1">
										<span><b>Apply Leave & Check Leave Balance</b> &nbsp;&nbsp;&nbsp; <i class="fa fa-plus-circle"  id="iconin"    onclick="OpenFrame(this)" style="font-size:20px;color:red;v-align:bottom;cursor: pointer;" ></i> </span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								        <!-- <span id="Responce_Message" style="color:white;" >  </span> -->
									</div>
									
										<div class="table-responsive">
										
										 <table id="LeavBal" class="table" style="display:none;">
											             
											             <thead class="thead-default text-center">
														<tr >
															<th>Leave Type</th>
															<th>Leave Quota</th>
															<th>Used Leaves</th>
															<th>Remaining Leaves</th>
															<!-- <th class='left' >Pending</th> -->
															<!-- <th>Approved</th>
															<th>Reject</th> -->
															
														</tr>
													</thead>
																										
														<tbody id='tbodys' class="sai">														
														
														
														<!-- <tr >
															<td>&nbsp;&nbsp;</td> <td>&nbsp;&nbsp;</td> <td>&nbsp;&nbsp;</td> <td>&nbsp;&nbsp;</td>
															<td class='left'>&nbsp;&nbsp;</td> <td>&nbsp;&nbsp;</td> <td>&nbsp;&nbsp;</td>
														</tr> -->
														
														
														</tbody>
													
											</table>	
											
										</div>
										
							<!--  <div id="DotEdLine" style="backgroung-color:red ;display:none;" class="panel-heading6">
										
								        <span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
							</div> -->
										<hr id="DotEdLine" style="display:none;background-color:#0088cc;">
									<div class="panel-body">
										<form action="" method="post" >
											<div class="col-md-6">	
												<div class="form-group row">
													<label class="col-sm-6 control-label">Select Leave Category</label>
													<select  id="Leave_Type" name="Leave_Type" class="select1" onChange="GetLoadData(this);" class=" col-sm-6">
														
														<!-- <option value="SL">Sick Leave</option>
														<option value="CL" selected>Casual Leave</option>
														<option value="EL">Earned Leave</option>
														<option value="CF">COMP-OFF</option>
														<option value="OD">On Duty</option> -->
														
													</select> 
													<!-- &nbsp;&nbsp;
													"WebContent/images/Loading_2.gif"
													<span><a href="javascript:onclick=OpenFrame('LEAVE_BALANCE');" class="btn-link1">
													<i  id="clickableAwesomeFont" style="color:#08c;" align='right'>
													<img src="balance.png" width="24px" height="24px"></i></a>
													</span> -->
													
													<!-- <div class="loader"></div> -->
													<!-- <i class="fa fa-spinner fa-pulse fa-3x fa-fw"></i> -->
													<span id="ImgScroll" style="display:none;">
													<img  src="images/Loading_2.gif" width="24px" height="24px">
													</span>
													
													<span id="ImgScroll_2" style="display:none;">
													<img  src="images/Loading_2.gif" width="24px" height="24px">
													</span>
													
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
										<!-- No of Days Selection -->
										<div class="form-group row" >
											<div class="col-md-12">
												
													<i><span id="Responce_Message" style="color:red; overflow-wrap: break-word;"  ></span></i>
												
												
											</div>
										
										</div>
										<!-- ---------ADD FOR LOCATION  ------------->
												
                                     <div class="form-group row">
														<div class="col-md-5" id="From_location1" style='display:none;'>
														
														<!-- <input type="text" id="From_location" style='display:;' name="date" maxlength="100" class="form-control"    /> -->
														<lable>From Location</lable>
														<input  list="potentials1" type="text" style='display:none;' placeholder="Search from Location" id="From_location"  maxlength="100" class="form-control" onkeypress="locationSearch(this);" ></input>
														<datalist id="potentials1" >
														<option value="HYDERABAD">
														</datalist>
														
															<!-- <label class="col-sm-9 control-label">Half Day Applicable</label>
															<div class="col-sm-3">
																<label>
																	<input class="form-check-input" type="checkbox" name="HalfDay" onclick="RefreshRadio(this,'date')"  id="HalfDay" value="Yes"> Yes
																</label>
															</div> -->
														</div>	
														
														<div class="col-md-2"> &nbsp; </div>
														<div class="col-md-5" style='display:none;' id="To_location1">
														
														<!-- <input type="text" id="To_location" style='display:;' name="date" maxlength="100" class="form-control"    /> -->
														<!-- tabindex='1' -->
														<lable>To Location</lable>
														<input  list="potentials" type="text" placeholder="Search to Location" style='display:none;' id="To_location" maxlength="100" class="form-control" onkeypress="locationSearch(this);" ></input>
														<datalist id="potentials" >
														<option  value="HYDERABAD">
                                                        
														</datalist>
																<!-- <label  class="col-sm-4 control-label">Date</label>
																<div class="col-sm-8">
																	<input type="text" id="date" name="date" class="form-control" Disabled  readOnly=true />
																</div> -->
														</div>
														
							</div>
												
												
										
											<!-- ---------ADD FOR LOCATION  ------------->	
												
												<div class="form-group row">
														<div class="col-md-12">
															<label class="col-sm-3 control-label">Half Day</label>
															<div class="col-sm-2">
																	<input class="form-check-input" type="checkbox"  name="HalfDay" onclick="RefreshRadio(this,'date');LoadHalfDay(this);"  id="HalfDay"   value="Yes"> Yes
															</div>														
														
																<label  class="col-sm-1 control-label">Date</label>
																<div class="col-sm-3">
																	<!-- <input type="text" id="date" name="date" class="form-control" Disabled  readOnly=true /> -->
																	
																	<select name='date' id='date' class="select2" onchange="LoadHalfDay(this);" >
																	<option value='0000-00-00' > Select </option>
																	<!-- <option value='1st Half'>1st Half</option>
																	<option value='2nd Half'>2nd Half</option> -->
																	</select>
																	
																	
																</div>
																<div class="col-sm-3">
																<select name='HalfSt' id='HalfSt' class="select2" >
																	<option value='1st Half'>1st Half</option>
																	<option value='2nd Half'>2nd Half</option>
																	</select>
																	</div>
																
														</div>
														
												</div>
												<div class="form-group row" style="display:none;">
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
												
											</div>
											
											
											<div class="col-sm-6 col-md-6">
											
									
											
												<div class="form-group">
													<label class="col-md-4 control-label">To</label>
													<div class="col-md-8">
														<input type="email" class="form-control" name="toemail" id="tags"    placeholder="someone@example.com">
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-4 control-label">Cc</label>
													<div class="col-md-8">
														<input type="email" class="form-control" name="ccemail" id="tags1"   placeholder="someone@example.com">
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-4 control-label">Subject</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="subject" value="Request For Leave" id="subject" placeholder="Subject">
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-4 control-label">Reason for Leave</label>
													<div class="col-md-8">
														<textarea class="form-control" rows="5" id="reason" name="reason" placeholder="Just mention reason only, not required a letter format "></textarea>
													</div>
												</div>
												
												
												<!-- <span id="Responce_Message" style="color:red;" >  </span> -->
												
												
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
												<button type="button"  id="Send_Button" class="btn btn-primary align-right" onclick="Leave_Validation();" >Send</button>
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
				

			

			
		</section>
<script>
function RefreshRadio(evn,data){
	
	document.getElementById("Responce_Message").innerHTML="";
	 if(document.getElementById(""+evn.id+"").checked){
		 document.getElementById("date").length=0;
		 var fromlen=document.getElementById("from");
		 var tolen=document.getElementById("to");
		 if(fromlen.value.length==0 || tolen.value.length==0){
			 document.getElementById(""+evn.id+"").checked=false;
			 document.getElementById("Responce_Message").innerHTML="Please Select From & To Dates";
			 $("#date").append ("<option value='0000-00-00' > Select </option>");
			 return false;
		 }else{
			 document.getElementById("date").length=0;
			 if(fromlen.value==tolen.value){
			 $("#date").append ("<option value="+fromlen.value+" selected > "+fromlen.value+" </option>");
			 }else{
				 $("#date").append ("<option value="+fromlen.value+" > "+fromlen.value+" </option>");
			     $("#date").append ("<option value="+tolen.value+" > "+tolen.value+" </option>");
			 }
		 }
		 
		 //document.getElementById(""+data+"").disabled=false;
	   
	   
	  }else{
		  document.getElementById("date").length=0;
		  $("#date").append ("<option value='0000-00-00' > Select </option>");
		 //document.getElementById(""+data+"").value='';
		 //document.getElementById(""+data+"").disabled=true;
	 }
	 
	 
	 
	 
}

function LoadHalfDay(dataval){
	
	
	//alert(0);
	var OTHFLAG="Y";
	   var Leave_Type= "";
	   var from_date="";
	   var to_date="";
	   var HalfDay="false";
	   var Hal_date="false";
	   var Leave_Type=document.getElementById('Leave_Type').value;
		 var from_date=document.getElementById('from').value;
	 var to_date=document.getElementById('to').value;
	 var HalfDay=document.getElementById('HalfDay').checked;
	    if(HalfDay){
	    	HalfDayFlg=document.getElementById('HalfSt').value;
	    }
	 var compoff=document.getElementById('compoff').checked;
	 var Hal_date=document.getElementById('date').value;
	 var comm_date=document.getElementById('date1').value;
	 var to_mail=document.getElementById('tags').value; // to mail
	 var cc_mail=document.getElementById('tags1').value; // ccmail
	 var subject=document.getElementById('subject').value;
	 var reason=document.getElementById('reason').value;
	 var From_location=document.getElementById("From_location").value;
	 var To_location=document.getElementById("To_location").value;
	if(HalfDay){
	 Apply_Leave(Leave_Type,from_date,to_date,HalfDay,Hal_date,compoff,comm_date,to_mail,cc_mail,subject,reason,OTHFLAG);
	}
	
}
function RefreshRadio_temp(date1){
	
	try{
	document.getElementById("Responce_Message").innerHTML="";
	 if(document.getElementById("HalfDay").checked){
		 document.getElementById("date").length=0;
		 var fromlen=document.getElementById("from");
		 var tolen=date1.toString();
		 if(fromlen.value.length==0 || tolen.length==0){
			 document.getElementById("HalfDay").checked=false;
			 document.getElementById("Responce_Message").innerHTML="Please Select From & To Dates";
			 $("#date").append ("<option value='0000-00-00' > Select </option>");
			 return false;
		 }else{
			 document.getElementById("date").length=0;
			 if(fromlen.value==tolen){
			 $("#date").append ("<option value="+fromlen.value+" selected > "+fromlen.value+" </option>");
			 }else{
				 $("#date").append ("<option value="+fromlen.value+" > "+fromlen.value+" </option>");
			     $("#date").append ("<option value="+tolen+" > "+tolen+" </option>");
			 }
		 }
		 
		 //document.getElementById(""+data+"").disabled=false;
	   
	   
	  }else{
		  document.getElementById("date").length=0;
		  $("#date").append ("<option value='0000-00-00' > Select </option>");
		 //document.getElementById("date").value='';
		// document.getElementById("date").disabled=true;
	 }
	}catch(err){
		
	}
}
var HalfDayFlg="false";
var OTHFLAG="N";
function Leave_Validation(){
  document.getElementById("Responce_Message").innerHTML="Please Wait...!";
 var Leave_Type=document.getElementById('Leave_Type').value;
 
 OTHFLAG="N";
 
   // This Script Add for Location Filter ...
  /*  if(Leave_Type=="OD"){
	  var From_location=document.getElementById("From_location").value;
	  var To_location=document.getElementById("To_location").value;
	  if((From_location.trim()).length==0 || (To_location.trim()).length==0){
		  document.getElementById("Responce_Message").innerHTML="Please Select From & To locations OR Write location in given text box for other location.";
		  return false;
	  }
   } */
   //This Script Add for Loacation Fetching ..
 var from_date=document.getElementById('from').value;
 var to_date=document.getElementById('to').value;
 var HalfDay=document.getElementById('HalfDay').checked;
 
    if(HalfDay){
    	HalfDayFlg=document.getElementById('HalfSt').value;
    }
 var compoff=document.getElementById('compoff').checked;
 
 var Hal_date=document.getElementById('date').value;
 
 var comm_date=document.getElementById('date1').value;
 
 var to_mail=document.getElementById('tags').value; // to mail
 var cc_mail=document.getElementById('tags1').value; // ccmail
 var subject=document.getElementById('subject').value;
 var reason=document.getElementById('reason').value;
 
 var From_location=document.getElementById("From_location").value;
 var To_location=document.getElementById("To_location").value;
 
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
    }else if(Leave_Type=="OD" && ((From_location.trim()).length==0 || (To_location.trim()).length==0)){
    
   /*  if((From_location.trim()).length==0 || (To_location.trim()).length==0){ */
	  
	    document.getElementById("Responce_Message").innerHTML="Please Select From & To locations OR Write location in given text box for other location.";
	    return false;
      /* } */
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
   //alert(reason.length +"~~~"+ to_mail.length);
   if(reason.length>4 && to_mail.length>4){
	   OTHFLAG="N";
	   Apply_Leave(Leave_Type,from_date,to_date,HalfDay,Hal_date,compoff,comm_date,to_mail,cc_mail,subject,reason,OTHFLAG);
	   
   }else{
	   
	   document.getElementById("Responce_Message").innerHTML="Please check To mail & reason for leave ";
   }
}

function Apply_Leave(Leave_Type,from_date,to_date,HalfDay,Hal_date,compoff,comm_date,to_mail,cc_mail,subject,reason ,OTHFLAG){
	    
	  
	
	  
	 try{
		   
	       document.getElementById("ImgScroll_2").style.display=""; 
	   }catch(err){
		   
		  // alert(err.message);
	   }
	   
	  document.getElementById("Responce_Message").innerHTML='Please wait ...!';
	   try{
		   
		  
		   
	     document.getElementById("Send_Button").disabled=true;
	    var To_loc=document.getElementById("To_location").value;
	    var From_loc=document.getElementById("From_location").value;
	    
	    //alert("Leave_Type ::" +Leave_Type);
	    
	    if(Leave_Type!="OD"){
	    	To_loc='NA';
	    	From_loc='NA';
	    }
	    
	    
	    var formData = {Routing:"LeaveMgM",Leave_Type:""+Leave_Type+"",from_date:""+from_date+"",to_date:""+to_date+"",HalfDay:""+HalfDayFlg+"",Hal_date:""+Hal_date+"",compoff:""+compoff+"",comm_date:""+comm_date+"",to_mail:""+to_mail+"",cc_mail:""+cc_mail+"",subject:""+subject+"",reason:""+reason+"",From_loc:""+From_loc+"",To_loc:""+To_loc+"",OTHFLAG:""+OTHFLAG+""};
	/*   var formData = {Routing:"LeaveMgM",Leave_Type:""+Leave_Type+"",from_date:""+from_date+"",to_date:""+to_date+"",HalfDay:""+HalfDay+"",Hal_date:""+Hal_date+"",compoff:""+compoff+"",comm_date:""+comm_date+"",to_mail:""+to_mail+"",cc_mail:""+cc_mail+"",subject:""+subject+"",reason:""+reason+"",From_loc:""+From_loc+"",To_loc:""+To_loc+""};
    */
	try{
	    
	    $.ajax({
	    	
	    	
	          type: "post",
	          url: "LeaveManagement",
	          data: formData,
	          success: function(responseData, textStatus, jqXHR) {
	        	  document.getElementById("Send_Button").disabled=false;
	        	 
	             try{
	                
	            	document.getElementById("Responce_Message").innerHTML=responseData;
	            	
	            	if(OTHFLAG=="N"){
	            	document.getElementById('from').value='';
	            	document.getElementById('to').value='';
	            	document.getElementById('HalfDay').checked=false;
	            	document.getElementById('compoff').checked=false;
	            	document.getElementById('date').value='0000-00-00';
	            	document.getElementById('date1').value='';
	            	document.getElementById('tags').value=''; // to mail
	            	document.getElementById('tags1').value=''; // ccmail
	            	document.getElementById('subject').value;
	            	document.getElementById('reason').value='';
	            	document.getElementById("To_location").value='';
	            	document.getElementById("From_location").value='';
	            	 LeaveBalLoad(); 
	            	 
	            	 
	          		   
	          		   try{
	          		       document.getElementById("ImgScroll_2").style.display="none"; 
	          		   }catch(err){
	          			   
	          			  // alert(err.message);
	          		   }
	            	 
	            	}else{
	            		try{
		          		       document.getElementById("ImgScroll_2").style.display="none"; 
		          		   }catch(err){
		          			   
		          			  // alert(err.message);
		          		   }
	            		
	            	}
	            	
	            	
	            	
	             }catch(err){
	            	 document.getElementById("Responce_Message").innerHTML="Your request not processed Please try again, ErCode1:"+err.message+"";
	            	 
	            	 try{
	          		       document.getElementById("ImgScroll_2").style.display="none"; 
	          		   }catch(err){
	          			   
	          			  // alert(err.message);
	          		   }
	          		   
	             }
	          },
	          error: function(jqXHR, textStatus, errorThrown) {
	               console.log(errorThrown);
	               document.getElementById("Send_Button").disabled=false;
	               document.getElementById("Responce_Message").innerHTML="Your request not processed Please try again, ErCode2:"+errorThrown+"";
	               try{
          		       document.getElementById("ImgScroll_2").style.display="none"; 
          		   }catch(err){
          			   
          			  // alert(err.message);
          		   }
	             // document.getElementById("Responce_Message_btn").style.display='';            
	          }
	      });
	}catch(err){
		 document.getElementById("Responce_Message").innerHTML="Your request not processed Please try again, ErCode3:"+err.message+"";
		 document.getElementById("Send_Button").disabled=false;
		 
		 try{
		      // document.getElementById("ImgScroll_2").style.display="none"; 
		   }catch(err){
			   
			  // alert(err.message);
		   }
		   
	}  
	
	   }catch(err){
		   document.getElementById("Responce_Message").innerHTML="Your request not processed Please try again, ErCode4:"+err.message+"";
		   document.getElementById("Send_Button").disabled=false;
		   
		   try{
  		       document.getElementById("ImgScroll_2").style.display="none"; 
  		   }catch(err){
  			   
  			  // alert(err.message);
  		   }
  		   
	   }
	   
	   document.getElementById("Responce_Message").innerHTML='';
	   
	   try{
	       //document.getElementById("ImgScroll_2").style.display="none"; 
	   }catch(err){
		   
		  // alert(err.message);
	   }
	   
}	
	
function locationSearch(val){
	//document.getElementById("Responce_Message").innerHTML="Press minimum three letters for searching location and Use Back space if your not geting existing locations."
	var loc_f_t=document.getElementById(""+val.id+"").value;
	
	   if((loc_f_t.trim()).length<2){
	       
		   return false;
	   }
	    //var To_loc=document.getElementById("To_location").value;
	   // var From_loc=document.getElementById("From_location").value;
	    
	   var formData = {Routing:"Location",loc:""+loc_f_t+"" };
		try{
		    $.ajax({
		          type: "post",
		          url: "Location_Search",
		          data: formData,
		          success: function(responseData, textStatus, jqXHR) {
		             try{
		            	 
		            	 //alert(responseData);
		            	 //JSON.stringify
		            	 var Data=JSON.parse(responseData);
		            	  
		            	    //alert("7~");
		            	/*   for (var i=0;i<Data.length;i++) {
		            	       alert(responseData[i])
		            	   }  */
		            	  
		            	  $.each(Data , function(k, v){
		            		  
		            		if(val.id=="To_location"){
		            		 $('#potentials').append("<option value='" + k + "'>"); 
		            		}else if(val.id=="From_location"){
		            		   $('#potentials1').append("<option value='" + k + "'>"); 
		            		}
		            		// $(this).focus();
		                }); 
		          
		            	    
		            	   //alert(3);
		            	//document.getElementById("Responce_Message").innerHTML=responseData;
		            	
		             }catch(err){
		            	 document.getElementById("Responce_Message").innerHTML=err;
		             }
		          },
		          error: function(jqXHR, textStatus, errorThrown) {
		               console.log(errorThrown);
		               alert(errorThrown);
		               document.getElementById("Responce_Message").innerHTML=errorThrown;
		             // document.getElementById("Responce_Message_btn").style.display='';            
		          }
		      })
		}catch(err){
			 document.getElementById("Responce_Message").innerHTML=err;
		}  
		
		   /*  document.getElementById(""+val.id+"").focus();
		    document.getElementById(""+val.id+"").value='';
		    document.getElementById(""+val.id+"").value=loc_f_t
		    document.getElementById(""+val.id+"").focus(); */
		    
		    
		   /*  $('#To_location1')
	        .children()
	        .filter(function(){
	            return $(this).data('show') === 'ANA';
	        })
	        .show(); */
		    
		    
			//$("#To_location1").trigger('focus.autocomplete');
			
			
			/* $('#To_location1').on( "focus", function( event, ui ) {
			    $(this).trigger(jQuery.Event("tab"));
			   // Since I know keydown opens the menu, might as well fire a keydown event to the element
			}); */
			
		 //$('#potentials').show();
		//document.getElementById(""+val.id+"").focus();
		//document.getElementById(""+val.id+"").value='ANN';
}

function OpenFrame(data){
	 var myClass = $("#iconin").attr("class");
	 //alert(myClass);
	  if(myClass=="fa fa-plus-circle"){
		  
		  $("#iconin").removeClass( "fa fa-plus-circle" ).addClass( "fa fa-minus-circle" );
		  document.getElementById("LeavBal").style.display="";
		  document.getElementById("DotEdLine").style.display="";
		  
	  }
	  if(myClass=="fa fa-minus-circle"){
		  
		  $("#iconin").removeClass( "fa fa-minus-circle" ).addClass( "fa fa-plus-circle" );
		  document.getElementById("LeavBal").style.display="none";
		  document.getElementById("DotEdLine").style.display="none";
		  
		  
	  }
	
}

function LeaveBalLoad(){
	var formData = {Routing:"LEAVE_BALANCE_AJX"};
	try{
	   $.ajax({
	   type: "post",
	   url: "Version2",
	   data: formData,
	   success: function(responseData, textStatus, jqXHR) {
			             try{
			            	// alert(responseData);
			            	 var Data=JSON.parse(responseData);
			            	 
			            	//  var Data=responseData;
			            	//alert(Data.length);
			            	 var tempjason="";
			            	 var CALLNAME="";
			           
			         //   	 [{"SHORTNAME":"CL","QTY":"10","AVAIL":"0.0","HOLD":"0.0", "STILLAVAIL":"10.0","USEDQTY":"10.0"}]
			             var LopCon="0";
			             document.getElementById("Leave_Type").length=0;
			             document.getElementById("tbodys").innerHTML="";
			            
			             
			            // $("#Leave_Type").length=0;
			             $("#Leave_Type").append ('<option value="Select" selected> Select </option>');
		            	 for(var i=0;i<Data.length;i++){
			            		 tempjason=Data[i];
			            		// alert(tempjason);
			            		// alert(tempjason.QTY);
			                	 var markup = "<tr><td>" + tempjason.FULLNAME + " <input type='hidden' id='"+ tempjason.SHORTNAME+"' value='"+tempjason.AVAIL+"-"+tempjason.MAXLEAVE+"-"+tempjason.DAYMODE+"-"+tempjason.COUNT_WOFF+"-"+tempjason.BACKDATE+"' ></td><td>" + tempjason.QTY + "</td><td>" + tempjason.USEDQTY + "</td> <td> " + tempjason.AVAIL + " </td> </tr>";
			                <%--     var markup = "<tr><td>" + tempjason.SEQNO + "</td><td>" + tempjason.CALLNAME + "</td><td>" + tempjason.DATEOFJOIN + "</td><td> <input type='text' value='"+tempjason.LOP+"' name='"+tempjason.EMPID+"' id='" + tempjason.EMPID + "'  <%=Mode%> onkeypress='return isNumber(event)' style='width:50px' /> </td> <td></td> <td></td> <td></td> </tr>";
			                    var markup = "<tr><td><input type='checkbox' checked name='" + tempjason.SEQNO + "' disabled > </td><td>" + tempjason.SEQNO + "</td><td>" + tempjason.CALLNAME + "</td><td>" + tempjason.DATEOFJOIN + "</td><td> <input type='text' value='"+tempjason.LOP+"' name='"+tempjason.EMPID+"' id='" + tempjason.EMPID + "'  <%=Mode%> onkeypress='return isNumber(event)' style='width:50px' /> </td> <td></td> <td></td> <td></td> </tr>";
			                    $("#tbodys").append(markup); --%>
			                  
			                 if(tempjason.LEAVETYPE!="NODATA" && tempjason.LEAVETYPE!="Leave Quota Not Assigned /No Leave Balance"){
			                     $("#Leave_Type").append ('<option value= '+tempjason.SHORTNAME+'>' + tempjason.LEAVETYPE + '</option>');
			                    
			                     if(tempjason.SHORTNAME=="LOP"){
			                       LopCon="1";
			                     }
			                 }
			                    
			                    $("#tbodys").append(markup);
			            	 }
		            	
		            	 if(LopCon=="0"){
		            	 $("#Leave_Type").append ('<option value=LOP > LOSS OF PAY </option>');
		            	 }
		            	 
			             }catch(err){
			            	 alert(err.message);
			             }
			          },
			          error: function(jqXHR, textStatus, errorThrown) {
			               console.log(errorThrown);
			               alert(errorThrown);
			          }
			      })
			}catch(err){
				 document.getElementById("Responce_Message").innerHTML=err;
			}  
	}
	


</script>	
	
		
		<!-- Vendor -->
	 		<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
	
		<!-- Theme Base, Components and Settings -->
		<script src="assets/javascripts/theme.js"></script>
	
		
		<!-- Theme Initialization Files -->
		<script src="assets/javascripts/theme.init.js"></script>


		
	</body>
</html>