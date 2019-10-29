<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html class="fixed">
	<head>

<%
  String message=(String)request.getAttribute("message");

   session.removeAttribute("EMP_NAME");

   if(message==null){
	   message="";
   }
%>
 		<meta charset="UTF-8">

		<meta name="keywords" content="Hetero HealthCare Ltd" />
		<meta name="description" content="Hetero HealthCare Ltd">
		<meta name="author" content="Sairam Duggi">
		
		<title>Hetero Healthcare LTD</title>
          <link rel="icon" href="LOH.png" />
		<!-- <link rel="icon" type="image/png"  href="favicon.ico"> -->

		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" />

		<!-- Web Fonts  -->
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

		<!-- Vendor CSS -->
		<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
		<link rel="stylesheet" href="assets/vendor/magnific-popup/magnific-popup.css" />
		<link rel="stylesheet" href="assets/vendor/bootstrap-datepicker/css/datepicker3.css" />
		
		
		
		

		<!-- Theme CSS -->
		<link rel="stylesheet" href="assets/stylesheets/theme.css" />

		<!-- Skin CSS -->
		<link rel="stylesheet" href="assets/stylesheets/skins/default.css" />

		<!-- Theme Custom CSS -->
		<link rel="stylesheet" href="assets/stylesheets/theme-custom.css">
		<link rel="stylesheet" href="assets/stylesheets/login.css">

		<!-- Head Libs -->
		<script src="assets/vendor/modernizr/modernizr.js"></script>
		
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
<script>
function pwvalidate(){
	
	
	var user_name=document.getElementById("username");
	var user_pwd=document.getElementById("pwd");
	
	if (user_name.value.length>0 && user_pwd.value.length>0){
		
		document.forms[0].submit();
		
	}else{
		document.getElementById("errmsg").innerHTML="Please Enter your Username & Password";
		return false;
	}
	
}

//only number

function isNumber(evt) {
	evt = (evt) ? evt : window.event;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	}
	return true;
}


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

 function handleKeyPress(e){
		 var key=e.keyCode || e.which;
		  if (key==13){
		     pwvalidate();
		  }
		}

	</script>

	<script type="text/javascript">
		window.history.forward();
		function noBack() { 
		
			
			window.history.forward(); 
			
		}
		
	
  </script>


	</head>
	<body onload="DetecBrowser(); disableBackButton(); noBack();">
		<!-- start: page -->
	<div id="loginbdy_IE" style="display:none" > 
	<div class="conatiner">
		
		
			
			<div class="text-center"><a href="http://www.heterohealthcare.com/" /> <h3>Compatible only with Chrome , Firefox & Edge Browser's</h3>  </a></div>
			
			
		
		</div>
	</div>
	<div id="loginbdy" style="display:none">	
		<section class="body-sign">
			<div class="center-sign">
				<a href="/" class="logo pull-left">
					<img src="assets/images/logo.png"  alt="Hetero HealthCare Ltd" />
				</a>

				<div class="panel1 panel-sign">
					<div class="panel-title-sign mt-xl text-right">
					    
						<h2 class="title text-uppercase text-bold m-none"><i class="fa fa-user mr-xs"></i> Login</h2>
					</div>
					<div class="panel-body">
						<div style="margin-top:30px;"></div>
						<form action="User_Auth_main" method="post">
							<div class="form-group ">
								<label class="col-sm-4">User ID</label>
								<div class="col-sm-6">
									<div class="input-group input-group-icon">
										<input name="username" type="text" maxlength='6'  placeholder="User ID/Employee ID" id="username"class="form-control" onkeypress="return isNumber(event)"/>
										<span class="input-group-addon">
											<span class="icon">
												<i class="fa fa-user"></i>
											</span>
										</span>
									</div>
								</div>
							</div>
                             
							<div class="form-group">
								<label class="col-sm-4">Password</label>
								
								<div class="col-sm-6">
									<div class="input-group input-group-icon">
										<input  type="password" placeholder="Password" name="pwd" id="pwd" onkeypress="handleKeyPress(event)"; class="form-control" />
										<span class="input-group-addon">
											<span class="icon">
												<i class="fa fa-lock"></i>
											</span>
										</span>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-4">
									<!-- <a href="#" style="padding-left:25px;">Lost Password?</a> -->
								</div>
								<div class="col-sm-4 text-right">
								    <button type="button" onclick="pwvalidate();" class="btn btn-primary btn-block btn-flat">Sign In</button>
									<!--<button type="submit" class="btn btn-primary btn-block visible-xs ">Sign In</button>-->
								</div>
							</div>

							<div class="text-center"><span style="color:red" class="text-center" id="errmsg"><%=message %></span></div>

						</form>
					</div>
				</div>

				
			</div>
		</section>
		<!-- end: page -->

		<!-- Vendor -->
		<script src="assets/vendor/jquery/jquery.js"></script>
		<script src="assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
		<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
		<script src="assets/vendor/nanoscroller/nanoscroller.js"></script>
		<script src="assets/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
		<script src="assets/vendor/magnific-popup/magnific-popup.js"></script>
		<script src="assets/vendor/jquery-placeholder/jquery.placeholder.js"></script>
		
		<!-- Theme Base, Components and Settings -->
		<script src="assets/javascripts/theme.js"></script>
		
		<!-- Theme Custom -->
		<script src="assets/javascripts/theme.custom.js"></script>
		
		<!-- Theme Initialization Files -->
		<script src="assets/javascripts/theme.init.js"></script>
    </div>
	</body>
</html>