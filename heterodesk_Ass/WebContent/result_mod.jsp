<!doctype html>
<html class="fixed">
	<head>

		<!-- Basic -->
		<meta charset="UTF-8">

		<title>HETERO HEALTHCARE LIMITED</title>
		<meta name="keywords" content="HETERO" />
		<meta name="description" content="Hetero" >
		<meta name="author" content="Hetero">

		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

		<!-- Web Fonts  -->
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

		<!-- Vendor CSS -->
		<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
			

		<!-- Theme CSS -->
		<link rel="stylesheet" href="assets/stylesheets/theme.css" />

		<!-- Skin CSS -->
		<link rel="stylesheet" href="assets/stylesheets/skins/default.css" />

		<!-- Theme Custom CSS -->
		<link rel="stylesheet" href="assets/stylesheets/theme-custom.css">

		<!-- Head Libs -->
		<script src="assets/vendor/modernizr/modernizr.js"></script>
		<script src="assets/vendor/jquery/jquery.js"></script>

		

<!-- Java Code Start -->
<%@page import="java.util.*" %>
<% Map fmap=(Map)session.getAttribute("mymap"); 
/*  out.print(fmap.get("_empname")); */


%>




<script>

 /* function ICT_Req(data){
	 
	 // alert(data.name);
	 document.getElementById("date").value=data.name; 
	 document.getElementById("Requ_Date").innerHTML=data.name;
	 document.getElementById("Responce_Message").innerHTML='';
	 document.getElementById("Responce_Message_btn").style.display='';
 } */

 function AttendanceRequest(){
	 var User_id=document.getElementById("auth_emp_id").value;
	 var User_pwd=document.getElementById("auth_emp_pwd").value;
	 var name=document.getElementById("empid").value;
	 var comments=document.getElementById("comments").value;
	 var RID=<%=fmap.get("_rid")%>;
	 var RANDOMID=<%=fmap.get("_randomid")%>;
	// alert(RANDOMID);
	 var approval_flg="";
	 var elements = document.getElementById("rej").checked;
	 var elements1 = document.getElementById("accept").checked;
	 
	 //alert(elements+"----"+elements1)
	  if(elements){
		 approval_flg="R";
	 }else if(elements1){
		 approval_flg="A";
		 
	 }
	 
	 //alert(""+approval_flg+"");
	
	 //alert("In Script-2");
	//alert(name);
	var formData = {id:""+name+"",flag:""+approval_flg+"",rid:""+RID+"",randomid:""+RANDOMID+"",comment:""+comments+"",User_id:""+User_id+"",User_pwd:""+User_pwd+""};
	
	
	   /* var date=document.getElementById("date").value;
	   var Subject=document.getElementById("Subject").value;
	   var message=document.getElementById("message").value;
		var formData = {id:""+date+"",Subject:""+Subject+"",message:""+message+""}; */
		
	try{
	    $.ajax({
	    	 
	          type: "post",
	          url: "ReqAcpt",
	          data: formData,
	          success: function(responseData, textStatus, jqXHR) {
	             // alert(responseData);
	              //alert("In Script-2.2");
	             // var resp=eval(responseData);
	           var res = responseData.split("~");
	          // alert(res[0]+"<------");
	          // alert(res[1]+"<------");  
	             
	             try{
	            	// alert("In Script-3");
	              document.getElementById("Responce_Message").innerHTML=res[0];
	              document.getElementById("Responce_Message_btn").style.display='none';
	             // alert("date:"+date);
	              //document.getElementById(""+date+"").innerHTML='Processed';
	             // document.getElementById(date).style.display='none';
	            //  document.getElementById(date+"_ST").innerHTML='Processed';
	             		//document.getElementById("date").value='';
	       	   			//document.getElementById("Subject").value='';
	       	  			//document.getElementById("message").value='';
	       	  	
	             }catch(err){
	            	 alert(err);
	             }
	          },
	          error: function(jqXHR, textStatus, errorThrown) {
	              console.log(errorThrown);
	              document.getElementById("Responce_Message").innerHTML=errorThrown;
	             document.getElementById("Responce_Message_btn").style.display='';
	              //alert("Error;");
	          }
	      });
	}catch(err){
		
		alert(err+":at Last");
	}
	  
 }
 
 
 
/*  function windowClose() { 
	 
	 alert("Close....");
 //window.open('','_parent',''); 
 window.close();
 }  */
 
</script>




<!-- Java Code End -->

		
		
		
		
		<style>
		
		.align-center{
	float: right !important;
    margin: 0px auto;	
}
.center_div{
    margin: 50px auto;
    width:80% /* value of your choice which suits your alignment */
}
.center_div1{
    margin: 10px auto;
	width:100% /* value of your choice which suits your alignment */
}
.top{
	margin:150px auto;
	
}
.top1{
	margin:50px auto;
}
center_div2{
    margin: 10px auto;
    width:80% /* value of your choice which suits your alignment */
}

		</style>
			<script>
$(document).ready(function(){
    $("#Responce_Message_btn").click(function(){
        $("#myModal").modal();
    });
});
</script>
		
		
	<script>
var isOpera = (!!window.opr && !!opr.addons) || !!window.opera || navigator.userAgent.indexOf(' OPR/') >= 0;
var isFirefox = typeof InstallTrigger !== 'undefined';
var isSafari = Object.prototype.toString.call(window.HTMLElement).indexOf('Constructor') > 0;
var isIE = /*@cc_on!@*/false || !!document.documentMode;
var isEdge = !isIE && !!window.StyleMedia;
var isChrome = !!window.chrome && !!window.chrome.webstore;
var isBlink = (isChrome || isOpera) && !!window.CSS;
var output = 'Detecting browsers by ducktyping:<hr>';
output += 'isFirefox: ' + isFirefox + '<br>';
output += 'isChrome: ' + isChrome + '<br>';
output += 'isSafari: ' + isSafari + '<br>';
output += 'isOpera: ' + isOpera + '<br>';
output += 'isIE: ' + isIE + '<br>';
output += 'isEdge: ' + isEdge + '<br>';
output += 'isBlink: ' + isBlink + '<br>';

function DetecBrowser(){
if(isIE){
	
	document.getElementById("loginbdy_IE").style.display='';
	//window.open("leave.html");
}else{
	
	try{
		document.getElementById("loginbdy").style.display='';
	}catch(err){
		alert(err.message+":err");
	}
	}
}
</script>

	</head>
	<body onload="DetecBrowser();sai('<%=fmap.get("_type")%>');">
	
	
	<div id="loginbdy_IE" style="display:none" > 
	<div class="conatiner">
		
		
			
			<div class="text-center"><a href="http://www.heterohealthcare.com/" /> <h3>Compatible only with Chrome , Firefox & Edge Browser's</h3>  </a></div>
			
			
		
		</div>
	</div>
	<div id="loginbdy" style="display:none">
	
		<section class="body">
			
		
					<!-- start: page -->
					<div class="row center_div">
						<div class="col-md-12">
								<div class="panel panel-primary">
									<div class="panel-heading1">
										<span>Management Approvals</span>									
									</div>
									<div class="panel-body">
										<form role="form" method="POST" action="">
												<div class="form-group">
													<div class="col-md-6">
														<label  class="col-sm-4 control-label">Employee ID</label>
														<div class="col-sm-8">
														<input type="text" id="empid" name="empid" class="form-control"  value="<%=fmap.get("_empid")%>"readonly>
														</div>
													</div>	
													<div class="col-md-6">
														<label  class="col-sm-4 control-label" >Employee Name</label>
														<div class="col-sm-8">
														<input type="text"  name="empname" class="form-control"  value="<%=fmap.get("_empname")%>" readonly>
														</div>
													</div>
													
												</div>	
												<div class="form-group">
													<div class="col-md-6">
														<label  class="col-sm-4 control-label">From</label>
														<div class="col-sm-8">
														<input type="text" name="from" class="form-control" value=<%=fmap.get("_from")%> readonly>
														</div>
													</div>	
													<div class="col-md-6">
														<label  class="col-sm-4 control-label">To</label>
														<div class="col-sm-8">
														<input type="text" name="to" class="form-control" value=<%=fmap.get("_to")%> readonly>
														</div>
													</div>
													
												</div>	
												<div class="form-group" id="late" style="display:;" >
													<div class="col-md-6">
														<label  class="col-sm-4 control-label">Subject</label>
														<div class="col-sm-8">
														<input type="text"  name="subject" class="form-control" value="<%=fmap.get("_subject")%>" readonly>
														</div>
													</div>	
													<div class="col-md-6">
														<label  class="col-sm-1 control-label">IN</label>
														<div class="col-sm-3">
														<input type="text"  name="IN" class="form-control" value=<%=fmap.get("_fin")%> readonly>
														</div>
														<label  class="col-sm-1 control-label">OUT</label>
														<div class="col-sm-3">
														<input type="text"  name="OUT" class="form-control" value=<%=fmap.get("_lout")%> readonly>
														</div>
														<label  class="col-sm-1 control-label">NET</label>
														<div class="col-sm-3">
														<input type="text"  name="NET" class="form-control" value=<%=fmap.get("_neth")%> readonly>
														</div>
													</div>
													
												</div>	
												<div class="form-group" id="leave" style="display:">
													<div class="col-md-12">
														<label  class="col-sm-2 control-label">Subject</label>
														<div class="col-sm-10">
														<input type="text"  name="subject1" class="form-control" value="<%=fmap.get("_subject")%>" readonly>
														</div>
													</div>	
													
													
												</div>	
												<div class="form-group">
													<div class="col-md-12">
														<label  class="col-sm-2 control-label">Reason</label>
														<div class="col-sm-10">
														<textarea class="form-control" rows="5" name="raeson" readonly ><%=fmap.get("_message")%></textarea>
														</div>
													</div>	
													
													
												</div>	
												<div class="form-group">
													<div class="col-md-12">
														<label  class="col-sm-2 control-label">Comments</label>
														<div class="col-sm-10">
														<input type="text"  name="comment" id="comments" class="form-control"  value="Approver Comments" placeholder="No Comment" >
														</div>
													</div>	
													
													
												</div>	
												<div class="form-group">
													<div class="col-md-6">
														<label  class="col-sm-4 control-label">Status</label>
														<div class="col-sm-8">
															<label class="form-check-inline">
																<input class="form-check-input" type="radio" name="approvals" id="accept"  value="A" checked/> Approved
															</label>
															<label class="form-check-inline">
															  <input class="form-check-input" type="radio" name="approvals" id="rej" value="R"/> Rejected
															</label>
														</div>
													</div>
												</div>	
													
											<div class="center_div2">
											
											
											
											<!-- <button type="button" class="btn btn-default" data-dismiss="modal" onclick="windowClose();">Close</button>&nbsp; -->
												<span style="padding-left:480px"><input type="button"  name="submit" value="Approve"  class="btn btn-primary" id="Responce_Message_btn" style="display:"></span>
											</div>
											
										</form>	
									</div>
								</div>
						</div>
					</div>	
					<!-- end: page -->
					<!--Authenication Modal-->
					 <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">User Authenication</h4>
        </div>
        <div class="modal-body">
			<div class="form-group">
				<label class="col-md-4 control-label">Employee ID</label>
				<div class="col-md-8">
					<input type="text" class="form-control" name="auth_emp_id" id="auth_emp_id" placeholder="Employee ID">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label">Password</label>
				<div class="col-md-8">
					<input type="password" class="form-control" name="auth_emp_pwd" id="auth_emp_pwd" placeholder="Password">
				</div>
			</div>
        </div>
        <div class="modal-footer">
		<b><span id="Responce_Message" style="color:red;"  class="align-left"> </span></b>
				  <input type="button" class="btn btn-default" value="Close" data-dismiss="modal">
          <input type="button" class="btn btn-primary"  onclick="AttendanceRequest()" value="Submit">

        </div>
      </div>
      
    </div>
  </div>
  
</div>

				
		</section>
		
	</div>
		<!-- Vendor -->
		<script src="assets/vendor/jquery/jquery.js"></script>
		<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
		
		
		
		<!-- Specific Page Vendor -->
		<script src="assets/vendor/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>
		
		
		<!-- Theme Base, Components and Settings -->
		<script src="assets/javascripts/theme.js"></script>
		
		<!-- Theme Custom -->
		<script src="assets/javascripts/theme.custom.js"></script>
		
		<!-- Theme Initialization Files -->
		<script src="assets/javascripts/theme.init.js"></script>

<script>

function sai(val){
	//alert(val);
						
	if(val=="AR"){
		document.getElementById("late").style.display='';
		document.getElementById("leave").style.display='none';
	}else if(val=="LR"){
		document.getElementById("leave").style.display='';
		document.getElementById("late").style.display='none';
	}
	
}
</script>
		 
		 
	</body>
</html>