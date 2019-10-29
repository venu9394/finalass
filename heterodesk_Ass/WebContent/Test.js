$("#type").change(function() {

	// $('#Location_type').val();
	
	//LocationChange();

	if($('#EmployeeId').val().length!=0)
		{
		
		
	
	if ($('#type').val() == 'EmployeeName') {
		$('#Date_text').hide();
		$('#field_Name_text').show();
		$('#common_text').hide();
		$('#button_show').show();
		$('#dropdownlist').hide();
		
		$('#Addres_text').hide();
		
		$('#Responce_Message').html('');
		
		$('#Others_text').hide();
		
	 
	} 
	
	 
	else if ($('#type').val() == 'DateOfBirth') {

		$('#Date_text').show();
		$('#field_Name_text').hide();
		
		$('#common_text').hide();
		$('#button_show').show();
		$('#dropdownlist').hide();
		$('#Addres_text').hide();
		$('#Responce_Message').html('');
		$('#Others_text').hide();
	}

	else if ($('#type').val()=='Select') {

		$('#Date_text').hide();
		$('#field_Name_text').hide();
		$('#common_text').hide();
		$('#Addres_text').hide();
		$('#Responce_Message').html('');
		$('#Others_text').hide();
		
		$('#button_show').hide();
		 
	}
	
	else if ($('#type').val()=='PFNO') {

		$('#Date_text').hide();
		$('#field_Name_text').hide();
		$('#common_text').show();
		$('#button_show').show();
		
		//$('#button_show').show();
		
		$('#textboxname').html('PF NO');
		$('#dropdownlist').hide();
		$('#Addres_text').hide();
		$('#Responce_Message').html('');
		$('#Others_text').hide();
		
	}
	
	/*else if ($('#type').val()=='ACCOUNTNO') {

		$('#Date_text').hide();
		$('#field_Name_text').hide();
		$('#common_text').show();
		$('#button_show').show();
		
		//$('#button_show').show();
		
		$('#textboxname').html('A/C NO');
		$('#dropdownlist').hide();
		$('#Addres_text').hide();
	}*/
	
	else if ($('#type').val()=='PFUAN') {

		$('#Date_text').hide();
		$('#field_Name_text').hide();
		
		
		$('#common_text').show();
		$('#button_show').show();
		
		//$('#button_show').show();
		
		$('#textboxname').html('PF UAN NO');
		$('#dropdownlist').hide();
		
		$('#Addres_text').hide();
		$('#Responce_Message').html('');
		$('#Others_text').hide();
		
	}
	
	else if ($('#type').val()=='BANKDETAILS') {

		$('#Date_text').hide();
		$('#field_Name_text').hide();
		$('#common_text').hide();
		$('#button_show').show();
		
		$('#dropdownlist').show();
		
		$('#Addres_text').hide();
		
		$('#Responce_Message').html('');
		//$('#button_show').show();
		
		//$('#textboxname').html('PF UAN NO');
		$('#Others_text').hide();
		
		
	}
	
	else if ($('#type').val()=='PerAddress') {

		
		//alert('');
		$('#Date_text').hide();
		$('#field_Name_text').hide();
		$('#common_text').hide();
		$('#button_show').show();
		
		$('#dropdownlist').hide();
		
		$('#Addres_text').show();
		
		$('#Responce_Message').html('');
		//$('#button_show').show();
		
		//$('#textboxname').html('PF UAN NO');
		$('#Others_text').hide();
		
		
	}
	
	
	else if($('#type').val()=='Network')
		{
		
		$('#Date_text').hide();
		$('#field_Name_text').hide();
		$('#common_text').hide();
		$('#button_show').show();
		
		$('#dropdownlist').hide();
		
		$('#Addres_text').hide();
		
		$('#Responce_Message').html('');
		
		
		$('#Others_text').show();
		$('#Issue_show').show();
		
		}
	
	
	else if($('#type').val()=='Others')
	{
	
	$('#Date_text').hide();
	$('#field_Name_text').hide();
	$('#common_text').hide();
	$('#button_show').show();
	
	$('#dropdownlist').hide();
	
	$('#Addres_text').hide();
	
	$('#Responce_Message').html('');
	
	
	$('#Others_text').show();
	
	$('#Issue_show').hide();
	
	}

	
	
		}
	
	else
		{
		
		
		
		 $('#Responce_Message').html('Please enter Employee Id..');
		
		//alert('Please enter Employee Id..');
		 
		
        $('#type').val('Select');
        
        
		}

});

$("#type")
		.change(
				function() {
					
					
					
					 if($('#EmployeeId').val().length!=0)
						{
					 
					if($("#type").val()=='EmployeeName')
						{
					 
					try {
						$
								.ajax({
									type : "get",
									url : "Fetch_Data",
									data : {
										type : $("#type").val(),
										EmployeeId : $("#EmployeeId").val()
									},
									success : function(responseData,
											textStatus, jqXHR) {

										var returnedData = JSON
												.parse(responseData);
										// alert(returnedData[0]);
										//alert(returnedData);
                                      
									 

										if (returnedData[0] == "NODATA") {

											
											
											/*$('#button').prop('disabled', true);*/

										}

										else {
											
											$('#first_name').val(returnedData[0]);
											$('#Last_name').val(returnedData[1]);
											 
											/*$('#button').prop('disabled', false);*/
										}

										//$('#first_name').val('');
										//$('#Last_name').val('');

									},
									error : function(jqXHR, textStatus,
											errorThrown) {
										console.log(errorThrown);
										// document.getElementById("Responce_Message").innerHTML=errorThrown;
										// document.getElementById("Responce_Message_btn").style.display='';
										
										
										 $('#Responce_Message').html('Please relogin may be your session is closed..!');
										//alert("Error;" + textStatus);
										//alert("Error;" + errorThrown);

										//alert("jqXHR;" + jqXHR.status);

									}
								});
					} catch (err) {
						
						 $('#Responce_Message').html('Please relogin may be your session is closed..!');

						//alert(err + ":at Last");
					}
					
						}
					
					else if($("#type").val()=='DateOfBirth')
						{
						try {
							
							
							$
									.ajax({
										type : "get",
										url : "Fetch_Data",
										data : {
											type : $("#type").val(),
											EmployeeId : $("#EmployeeId").val(),
											 
										},
										success : function(responseData,
												textStatus, jqXHR) {

											var returnedData = JSON
													.parse(responseData);
											// alert(returnedData[0])
											// alert(returnedData);

										/*	$('#exist_date').html(
													"(<font color='green' size='2'>" + returnedData+ "</font>)");
*/
											 
											if (returnedData[0]== "NODATA") {

												/*$('#button').prop('disabled', true);*/
												$('#NUMBER').val('');
											}

											else {
												
												
												$('#Date').val(returnedData);
												/*$('#button')
														.prop('disabled', false);*/
											}

											

										},
										error : function(jqXHR, textStatus,
												errorThrown) {
											console.log(errorThrown);
											// document.getElementById("Responce_Message").innerHTML=errorThrown;
											// document.getElementById("Responce_Message_btn").style.display='';
											
											 $('#Responce_Message').html('Please relogin may be your session is closed..!');
											/*alert("Error;" + textStatus);
											alert("Error;" + errorThrown);

											alert("jqXHR;" + jqXHR.status);*/

										}
									});
						} catch (err) {

							 $('#Responce_Message').html('Please relogin may be your session is closed..!');
							//alert(err + ":at Last");
						}
						}
					
					
					
					else if($("#type").val()=='PFNO')
					{
					try {
						//$('#NUMBER').val('');
						
						$.ajax({
									type : "get",
									url : "Fetch_Data",
									data : {
										type : $("#type").val(),
										EmployeeId : $("#EmployeeId").val(),
										 
									},
									success : function(responseData,
											textStatus, jqXHR) {

										var returnedData = JSON
												.parse(responseData);
										// alert(returnedData[0])
										//alert(returnedData);

										/*$('#exist_number').html(
												"(<font color='green' size='2'>" + returnedData+ "</font>)");*/

										 
										if (returnedData[0]== "NODATA") {

											/*$('#button').prop('disabled', true);*/
											$('#NUMBER').val('');
										}

										else {
											
											$('#NUMBER').val(returnedData);
											/*$('#button')
													.prop('disabled', false);*/
										}

										

									},
									error : function(jqXHR, textStatus,
											errorThrown) {
										console.log(errorThrown);
										// document.getElementById("Responce_Message").innerHTML=errorThrown;
										// document.getElementById("Responce_Message_btn").style.display='';
										
										 $('#Responce_Message').html('Please relogin may be your session is closed..!');
										 
										/*alert("Error;" + textStatus);
										alert("Error;" + errorThrown);

										alert("jqXHR;" + jqXHR.status);*/

									}
								});
					} catch (err) {

						 $('#Responce_Message').html('Please relogin may be your session is closed..!');
						//alert(err + ":at Last");
					}
					}
					
					 
					
					else if($("#type").val()=='PFUAN')
					{
					try {
						
						//$('#NUMBER').val('');
						$.ajax({
									type : "get",
									url : "Fetch_Data",
									data : {
										type : $("#type").val(),
										EmployeeId : $("#EmployeeId").val(),
										 
									},
									success : function(responseData,
											textStatus, jqXHR) {

										var returnedData = JSON
												.parse(responseData);
										// alert(returnedData[0])
										//alert(returnedData);

										/*$('#exist_number').html(
												"(<font color='green' size='2'>" + returnedData+ "</font>)");*/

										 
										if (returnedData[0]== "NODATA") {

											//$('#button').prop('disabled', true);
											$('#NUMBER').val('');
										}

										else {
											
											$('#NUMBER').val(returnedData);
											
											//$('#button').prop('disabled', false);
										}

										

									},
									error : function(jqXHR, textStatus,
											errorThrown) {
										console.log(errorThrown);
										// document.getElementById("Responce_Message").innerHTML=errorThrown;
										// document.getElementById("Responce_Message_btn").style.display='';
										
										 $('#Responce_Message').html('Please relogin may be your session is closed..!');
										/*alert("Error;" + textStatus);
										alert("Error;" + errorThrown);

										alert("jqXHR;" + jqXHR.status);*/

									}
								});
					} catch (err) {

						 $('#Responce_Message').html('Please relogin may be your session is closed..!');
						//alert(err + ":at Last");
					}
					}
					
					
					else if($("#type").val()=='BANKDETAILS')
					{
					try {
						$.ajax({
									type : "get",
									url : "Fetch_Data",
									data : {
										type : $("#type").val(),acno:$('#ACNUMBER').val(),
										EmployeeId : $("#EmployeeId").val(),
										 
									},
									success : function(responseData,
											textStatus, jqXHR) {

										var returnedData = JSON
												.parse(responseData);
										// alert(returnedData[0])
										//alert(returnedData);
/*
										$('#exist_bank').html(
												"(<font color='green' size='2'>" + returnedData[0]+ "</font>)");
										$('#exist_acnumber').html(
												"(<font color='green' size='2'>" + returnedData[1]+ "</font>)");*/

										 
										if (returnedData[0]== "NODATA") {

											//$('#button').prop('disabled', true);

										}

										else {
											
											$('#bank_type').val(returnedData[0]);
											$('#ACNUMBER').val(returnedData[1]);
											
											$('#button')
													.prop('disabled', false);
										}

										

									},
									error : function(jqXHR, textStatus,
											errorThrown) {
										console.log(errorThrown);
										// document.getElementById("Responce_Message").innerHTML=errorThrown;
										// document.getElementById("Responce_Message_btn").style.display='';
										 $('#Responce_Message').html('Please relogin may be your session is closed..!');
										/*alert("Error;" + textStatus);
										alert("Error;" + errorThrown);

										alert("jqXHR;" + jqXHR.status);*/

									}
								});
					} catch (err) {

						 $('#Responce_Message').html('Please relogin may be your session is closed..!');
						//alert(err + ":at Last");
					}
					}
					
					
					
					else if($("#type").val()=='PerAddress')
					{
						
						 
						
					try {
						$.ajax({
									type : "get",
									url : "Fetch_Data",
									data : {
										type : $("#type").val(),
										EmployeeId : $("#EmployeeId").val(),
										 
									},
									success : function(responseData,
											textStatus, jqXHR) {

										var returnedData = JSON
												.parse(responseData);
									//	alert("hhh");
										//alert(returnedData);

									/*	$('#exist_address1').html(
												"(<font color='green' size='2'>" + returnedData[0]+ "</font>)");
												
												
												$('#exist_address2').html(
												"(<font color='green' size='2'>" + returnedData[1]+ "</font>)");
												$('#exist_email').html(
												"(<font color='green' size='2'>" + returnedData[2]+ "</font>)");
												$('#exist_mobile').html(
												"(<font color='green' size='2'>" + returnedData[3]+ "</font>)");
												$('#exist_state').html(
												"(<font color='green' size='2'>" + returnedData[4]+ "</font>)");
												$('#exist_city').html(
												"(<font color='green' size='2'>" + returnedData[5]+ "</font>)");*/
												 
												

										 
												 if (returnedData[0]== "0") {

											/*$('#button').prop('disabled', true);*/
													 
													 $('#address1').val("");
														$('#address2').val(""); 
														$('#email').val(""); 
														$('#mobile').val(""); 
														//$('#state').val("Selcet");
														//$('#cityload').val("Select");
														
														// StateChange(returnedData[5]);	 
													 

										}

										else {
											
											
											    $('#address1').val( returnedData[0]);
												$('#address2').val(returnedData[1]); 
												$('#email').val(returnedData[2]); 
												$('#mobile').val(returnedData[3]); 
												
												
												if(returnedData[5]!=0 || returnedData[4]=="0" ){
													
													$('#state').val(returnedData[4]);

												$('#cityload').val(returnedData[5]);
												
												//alert(returnedData[5]);
												
												 StateChange(returnedData[5]);
						                      }
												
											
											
											/*$('#button')
													.prop('disabled', false);*/
										}

												          

									},
									error : function(jqXHR, textStatus,
											errorThrown) {
										console.log(errorThrown);
										// document.getElementById("Responce_Message").innerHTML=errorThrown;
										// document.getElementById("Responce_Message_btn").style.display='';
										 $('#Responce_Message').html('Please relogin may be your session is closed..!');
										/*alert("Error;" + textStatus);
										alert("Error;" + errorThrown);

										alert("jqXHR;" + jqXHR.status);*/

									}
								});
					} catch (err) {

						 $('#Responce_Message').html('Please relogin may be your session is closed..!');
						//alert(err + ":at Last");
					}
					}
						}

 
 

				});

$('#EmployeeId').keyup(function() {

	$('#type').val('Select');

});

 


$("#button").click(
		function() {
			// alert( "Handler for .click() called." );

			
			if($('#type').val()=="Select")
				{
				
				 $('#Responce_Message').html('Please Select Type....');
				 
				//alert('Please Select Type....');
				}
			
			
			else if($('#type').val()=="EmployeeName")
				{
				if ($('#first_name').val() =="") {
					
					 $('#Responce_Message').html('Please Enter First Name..');
					//alert('Please Enter First Name..');
				}

				else if ($('#Last_name').val() == "") {
					
					 $('#Responce_Message').html('Please Enter Last Name..');
					//alert('Please Enter Last Name..');
				}

				else if ($('#EmployeeId').val() != "" && $('#type').val() != "Select"
						&& $('#first_name').val() != ""
						&& $('#Last_name').val() != "") {

					try {
						$.ajax({
							type : "get",
							url : "Update_Servlet",
							data : {
								type : $("#type").val(),
								EmployeeId : $("#EmployeeId").val(),
								first_name : $('#first_name').val(),
								last_name : $('#Last_name').val()
							},
							success : function(responseData, textStatus, jqXHR) {

								//alert(responseData);
								if(eval(responseData)==1)
								{
								 $('#Responce_Message').html("Successfully Inserted...");
								 
								 
								    $('#type').val('Select');
								    $('#button_show').hide();
									$('#field_Name_text').hide();
								 
								}
							
							else if(eval(responseData)==0)
								{
								 $('#Responce_Message').html('Unable to Process Please Contact Our Admin....');
								}

							},
							error : function(jqXHR, textStatus, errorThrown) {
								console.log(errorThrown);
								// document.getElementById("Responce_Message").innerHTML=errorThrown;
								// document.getElementById("Responce_Message_btn").style.display='';
								/*alert("Error;" + textStatus);
								alert("Error;" + errorThrown);

								alert("jqXHR;" + jqXHR.status);
*/
							}
						});
					} catch (err) {

						 $('#Responce_Message').html('Please relogin may be your session is closed..!');
						//alert(err + ":at Last");
					}

				}
				}
			
			
			else if($('#type').val()=="DateOfBirth")
			{
				
				
				if ($('#Date').val() == "") {
					$('#Responce_Message').html('Please Select Date..');
					}
		 
					else if ($('#EmployeeId').val() != "" && $('#type').val()!="Select"
							&& $('#Date').val() != "") {

						try {
							$.ajax({
								type : "get",
								url : "Update_Servlet",
								data : {
									type : $("#type").val(),
									EmployeeId : $("#EmployeeId").val(),
									Date : $('#Date').val(),
									 
								},
								success : function(responseData, textStatus, jqXHR) {

									if(eval(responseData)==1)
									{
									 $('#Responce_Message').html("Successfully Inserted...");
									 
									    $('#type').val('Select');
									    $('#button_show').hide();
										$('#Date_text').hide(); 
									 
									}
								
								else if(eval(responseData)==0)
									{
									 $('#Responce_Message').html('Unable to Process Please Contact Our Admin....');
									}

								},
								error : function(jqXHR, textStatus, errorThrown) {
									console.log(errorThrown);
									// document.getElementById("Responce_Message").innerHTML=errorThrown;
									// document.getElementById("Responce_Message_btn").style.display='';
									/*alert("Error;" + textStatus);
									alert("Error;" + errorThrown);

									alert("jqXHR;" + jqXHR.status);*/
									 $('#Responce_Message').html('Please relogin may be your session is closed..!');

								}
							});
						} catch (err) {

							 $('#Responce_Message').html('Please relogin may be your session is closed..!');
							//alert(err + ":at Last");
						}

					}
			}
			
			else if($('#type').val()=="PFNO")
			{
				
				
				if ($('#NUMBER').val() == "") {
					
					 $('#Responce_Message').html('Please Enter PF NO..');
					 
					//	alert('Please Enter PF NO..');
					}
		 
					else if ($('#EmployeeId').val() != "" && $('#type').val()!="Select"
							&& $('#NUMBER').val() != "") {

						try {
							$.ajax({
								type : "get",
								url : "Update_Servlet",
								data : {
									type : $("#type").val(),
									EmployeeId : $("#EmployeeId").val(),
									NUMBER : $('#NUMBER').val(),
									 
								},
								success : function(responseData, textStatus, jqXHR) {

									
									if(eval(responseData)==1)
									{
									 $('#Responce_Message').html("Successfully Inserted...");
									 
									    $('#type').val('Select');
									    $('#button_show').hide();
										$('#common_text').hide(); 
									
									}
								
								else if(eval(responseData)==0)
									{
									 $('#Responce_Message').html('Unable to Process Please Contact Our Admin....');
									}
									
									
									//alert(responseData);

									//location.reload();

								},
								error : function(jqXHR, textStatus, errorThrown) {
									console.log(errorThrown);
									// document.getElementById("Responce_Message").innerHTML=errorThrown;
									// document.getElementById("Responce_Message_btn").style.display='';
									
									$('#Responce_Message').html('Please relogin may be your session is closed..!');
									/*alert("Error;" + textStatus);
									alert("Error;" + errorThrown);

									alert("jqXHR;" + jqXHR.status);*/

								}
							});
						} catch (err) {

							$('#Responce_Message').html('Please relogin may be your session is closed..!');
						//	alert(err + ":at Last");
						}

					}
			}
			
		
			
			else if($('#type').val()=="PFUAN")
			{
				
				
				if ($('#NUMBER').val() == "") {
					$('#Responce_Message').html('Please Enter PFUAN NO..');
					}
		 
					else if ($('#EmployeeId').val() != "" && $('#type').val()!="Select"
							&& $('#NUMBER').val() != "") {

						try {
							$.ajax({
								type : "get",
								url : "Update_Servlet",
								data : {
									type : $("#type").val(),
									EmployeeId : $("#EmployeeId").val(),
									NUMBER : $('#NUMBER').val(),
									 
								},
								success : function(responseData, textStatus, jqXHR) {

									if(eval(responseData)==1)
										{
										 $('#Responce_Message').html("Successfully Inserted...");
										 
										    $('#type').val('Select');
										    $('#button_show').hide();
											$('#common_text').hide(); 
										}
									
									else if(eval(responseData)==0)
										{
										 $('#Responce_Message').html('Unable to Process Please Contact Our Admin....');
										}

									//location.reload();

								},
								error : function(jqXHR, textStatus, errorThrown) {
									console.log(errorThrown);
									// document.getElementById("Responce_Message").innerHTML=errorThrown;
									// document.getElementById("Responce_Message_btn").style.display='';
									/*alert("Error;" + textStatus);
									alert("Error;" + errorThrown);

									alert("jqXHR;" + jqXHR.status);*/
									
									$('#Responce_Message').html('Please relogin may be your session is closed..!');

								}
							});
						} catch (err) {

							$('#Responce_Message').html('Please relogin may be your session is closed..!');
							//alert(err + ":at Last");
						}

					}
			}
			
			else if($('#type').val()=="BANKDETAILS")
			{
				
				
				if ($('#bank_type').val() == "0") {
					
					$('#Responce_Message').html('Please Select Bank Type..');
						
					//alert('Please Select Bank Type..');
					}
				
				
				else if ($('#ACNUMBER').val() =="") {
					
					$('#Responce_Message').html('Please Enter A/C NO..');
					//alert('Please Enter A/C NO..');
				}
				
		 
					else if ($('#EmployeeId').val() != "" && $('#type').val()!="Select"
							&& $('#bank_type').val() !="0"&&$('#ACNUMBER').val()!="") {

						try {
							$.ajax({
								type : "get",
								url : "Update_Servlet",
								data : {
									type : $("#type").val(),
									EmployeeId : $("#EmployeeId").val(),
									bank_type : $('#bank_type').val(),
									acno:$('#ACNUMBER').val(),
									 
								},
								success : function(responseData, textStatus, jqXHR) {
									
									
									if(eval(responseData)==1)
									{
									 $('#Responce_Message').html("Successfully Inserted...");
									 
									  $('#type').val('Select');
									    $('#button_show').hide();
										$('#dropdownlist').hide(); 
									 
									}
								
								else if(eval(responseData)==0)
									{
									 $('#Responce_Message').html('Unable to Process Please Contact Our Admin....');
									}

								},
								error : function(jqXHR, textStatus, errorThrown) {
									console.log(errorThrown);
									// document.getElementById("Responce_Message").innerHTML=errorThrown;
									// document.getElementById("Responce_Message_btn").style.display='';
									
									$('#Responce_Message').html('Please relogin may be your session is closed..!');
									/*alert("Error;" + textStatus);
									alert("Error;" + errorThrown);

									alert("jqXHR;" + jqXHR.status);
*/
								}
							});
						} catch (err) {

							$('#Responce_Message').html('Please relogin may be your session is closed..!');
							//alert(err + ":at Last");
						}

					}
			}
			
			
			else if($('#type').val()=="PerAddress")
			{
				
				
				if ($('#address1').val() =="") {
					
					$('#Responce_Message').html('Please Enter Address1...');
					
					//	alert('Please Enter Address1...');
					}
				
				else if ($('#address2').val() =="") {
					
					$('#Responce_Message').html('Please Enter Address2...');
					
					//alert('Please Enter Address2...');
				}
				else if ($('#email').val() =="") {
					$('#Responce_Message').html('Please Enter EMAILID...');
					//alert('Please Enter EMAILID...');
				}
				else if ($('#mobile').val() =="") {
					
					$('#Responce_Message').html('Please Enter MOBILE NUMBER...');
					//alert('Please Enter MOBILE NUMBER...');
				}
				else if ($('#state').val() =="Select") {
					
					$('#Responce_Message').html('Please Select State...');
					//alert('Please Select State...');
				}
				else if ($('#city').val() =="Select") {
					
					$('#Responce_Message').html('Please Select City...');
					//alert('Please Select City...');
				}
				
		 
					else if ($('#EmployeeId').val() != "" && $('#type').val()!="Select"
							&& $('#address1').val() !=""&& $('#address2').val() !=""&& $('#email').val() !=""&& $('#mobile').val() !=""&&$('#state').val() !="Select"&&$('#city').val() !="Select") {

						
						/*String per_address1=request.getParameter("per_address1");
						String per_address2=request.getParameter("per_address2");
						String email_id=request.getParameter("email_id");
						String mobile_id=request.getParameter("mobile_id");
						String state=request.getParameter("state");
						String city=request.getParameter("bank_type");*/
						
						
						try {
							$.ajax({
								type : "get",
								url : "Update_Servlet",
								data : {
									
									type : $("#type").val(),
									EmployeeId : $("#EmployeeId").val(),
									per_address1 : $('#address1').val(),
									per_address2 : $('#address2').val(),
									email_id : $('#email').val(),
									mobile_id : $('#mobile').val(),
									state : $('#state').val(),
									city : $('#city').val(),
									 
								},
								success : function(responseData, textStatus, jqXHR) {

									if(eval(responseData)==1)
									{
									 $('#Responce_Message').html("Successfully Inserted...");
									 $('#type').val('Select');
									    $('#button_show').hide();
										$('#Addres_text').hide(); 
									 
									}
								
								else if(eval(responseData)==0)
									{
									 $('#Responce_Message').html('Unable to Process Please Contact Our Admin....');
									}

								},
								error : function(jqXHR, textStatus, errorThrown) {
									console.log(errorThrown);
						
									// document.getElementById("Responce_Message").innerHTML=errorThrown;
									// document.getElementById("Responce_Message_btn").style.display='';
									
									$('#Responce_Message').html('Please relogin may be your session is closed..!');
									/*alert("Error;" + textStatus);
									alert("Error;" + errorThrown);

									alert("jqXHR;" + jqXHR.status);*/

								}
							});
						} catch (err) {

							$('#Responce_Message').html('Please relogin may be your session is closed..!');
							//alert(err + ":at Last");
						}

					}
			}
			
			
			
			
			
			
			else if($('#type').val()=="Network")
			{
				
				
				if ($('#Email_id').val() =="") {
					
					$('#Responce_Message').html('Please Enter E-mail...');
					
					//	alert('Please Enter Address1...');
					}
				
				else if ($('#Issuetype').val() =="Select") {
					
					$('#Responce_Message').html('Please Select Type Of Issue..');
					
					//alert('Please Enter Address2...');
				}
				else if ($('#request_msg').val() =="") {
					$('#Responce_Message').html('Please Enter Reason/Message...');
					//alert('Please Enter EMAILID...');
				}
				 
					else if ($('#Email_id').val()!= "" && $('#type').val()!="Select"
							&& $('#request_msg').val() !=""&&$('#Issuetype').val()!="Select") {

						
						/*String per_address1=request.getParameter("per_address1");
						String per_address2=request.getParameter("per_address2");
						String email_id=request.getParameter("email_id");
						String mobile_id=request.getParameter("mobile_id");
						String state=request.getParameter("state");
						String city=request.getParameter("bank_type");*/
						 
						
						try {
							$.ajax({
								type : "get",
								url : "Update_Servlet",
								data : {
									type : $("#type").val(),
									Issuetype : $('#Issuetype').val(),
									request_msg :$('#request_msg').val().trim(),
									email_id : $('#Email_id').val(),
									EmployeeId : $("#EmployeeId").val(),
									 
									 
								},
								success : function(responseData, textStatus, jqXHR) {

									if(eval(responseData)==1)
									{
									 $('#Responce_Message').html("Successfully Inserted...");
									 
									    $('#type').val('Select');
									    $('#button_show').hide();
										$('#Others_text').hide(); 
										$('#Issue_show').hide();
										
										$('#Email_id').val('');
										$('#Issuetype').val('Select');
										$('#request_msg').val('');
									 
									}
								
								else if(eval(responseData)==0)
									{
									 $('#Responce_Message').html('Unable to Process Please Contact Our Admin....');
									}

								},
								error : function(jqXHR, textStatus, errorThrown) {
									console.log(errorThrown);
						
									// document.getElementById("Responce_Message").innerHTML=errorThrown;
									// document.getElementById("Responce_Message_btn").style.display='';
									
									$('#Responce_Message').html('Please relogin may be your session is closed..!');
									/*alert("Error;" + textStatus);
									alert("Error;" + errorThrown);

									alert("jqXHR;" + jqXHR.status);*/

								}
							});
						} catch (err) {

							$('#Responce_Message').html('Please relogin may be your session is closed..!');
							//alert(err + ":at Last");
						}

					}
			}
			else if($('#type').val()=="Others")
			{
				
				
				if ($('#Email_id').val() =="") {
					
					$('#Responce_Message').html('Please Enter E-mail...');
					
					//	alert('Please Enter Address1...');
					}
				 
				else if ($('#request_msg').val() =="") {
					$('#Responce_Message').html('Please Enter Reason/Message...');
					//alert('Please Enter EMAILID...');
				}
				 
					else if ($('#Email_id').val()!= "" && $('#type').val()!="Select"
							&& $('#request_msg').val() !="") {

						
						/*String per_address1=request.getParameter("per_address1");
						String per_address2=request.getParameter("per_address2");
						String email_id=request.getParameter("email_id");
						String mobile_id=request.getParameter("mobile_id");
						String state=request.getParameter("state");
						String city=request.getParameter("bank_type");*/
						 
						
						try {
							$.ajax({
								type : "get",
								url : "Update_Servlet",
								data : {
									type : $("#type").val(),
									request_msg : $('#request_msg').val().trim(),
									email_id : $('#Email_id').val(),
									EmployeeId : $("#EmployeeId").val(),
									 
									 
								},
								success : function(responseData, textStatus, jqXHR) {

									if(eval(responseData)==1)
									{
									 $('#Responce_Message').html("Successfully Inserted...");
									 
									    $('#type').val('Select');
									    $('#button_show').hide();
										$('#Others_text').hide(); 
										$('#Issue_show').hide();
										
										$('#Email_id').val('');
										$('#Issuetype').val('Select');
										$('#request_msg').val('');
									 
									}
								
								else if(eval(responseData)==0)
									{
									 $('#Responce_Message').html('Unable to Process Please Contact Our Admin....');
									}

								},
								error : function(jqXHR, textStatus, errorThrown) {
									console.log(errorThrown);
						
									// document.getElementById("Responce_Message").innerHTML=errorThrown;
									// document.getElementById("Responce_Message_btn").style.display='';
									
									$('#Responce_Message').html('Please relogin may be your session is closed..!');
									/*alert("Error;" + textStatus);
									alert("Error;" + errorThrown);

									alert("jqXHR;" + jqXHR.status);*/

								}
							});
						} catch (err) {

							$('#Responce_Message').html('Please relogin may be your session is closed..!');
							//alert(err + ":at Last");
						}

					}
			}
			

		});

function Bank_Type()
{
	 
		// alert("In Script-2"); 
		try {
			// alert("In Script-2.1");

			$.ajax({
				type : "get",
				 
				url : "Fetch_Data",
				data:{type:"Banktypes"},
				success : function(responseData, textStatus, jqXHR) {
					//alert(responseData);

					var dat = eval(responseData);
					 
					try {

						document.getElementById('bank_type').length = 0;
						var comm_city = document.getElementById('bank_type');

						for (var i = 0; i < dat.length; i++) {

							myOption = document.createElement("option");
							myOption.text = dat[i].split("#")[1];
							myOption.value = dat[i].split("#")[0];
							//myOption.id = dat[i].split("#")[0];
							comm_city.appendChild(myOption);

						}

					} catch (err) {

						alert(err.message);

					}

				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(errorThrown);
				 
					$('#Responce_Message').html('Please relogin may be your session is closed..!');

				}
			});
		} catch (err) {

			$('#Responce_Message').html('Please relogin may be your session is closed..!');
		}
		
		
		try {
			//alert("In Script-2.1");

			$.ajax({
				type : "get",
				url : "Fetch_Data",
				data:{type:"State"},
				success : function(responseData, textStatus, jqXHR) {
					
					/*//var obj = $.parseJSON(responseData);
					alert(responseData);*/
					//alert(responseData);
					 var dat = eval(responseData);
					 
					 
					try {

						document.getElementById('state').length = 0;
						var comm_city = document.getElementById('state');

						for (var i = 0; i < dat.length; i++) {

							myOption = document.createElement("option");
							myOption.text = dat[i].split("#")[1];
							myOption.value = dat[i].split("#")[0];
							myOption.id = dat[i].split("#")[0];
							comm_city.appendChild(myOption);

						}
						
						// StateChange(val);

					} catch (err) {

						$('#Responce_Message').html(err.message);
						//alert(err.message);

					} 

				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(errorThrown);
				 
					$('#Responce_Message').html(textStatus);
					
					/*alert("Error;" + textStatus);
					alert("Error;" + errorThrown);

					alert("jqXHR;" + jqXHR.status);*/

				}
			});
		} catch (err) {

			$('#Responce_Message').html('Communication issue please try again');
			//alert(err + ":at Last");
		}
		
}


 


function StateChange(val)
		{
	
	    // alert('11::' +val);
	
	if($('#state').val()!="Select")
		{
		  
	try {
		//alert("In Script-2.1"+val);

		$.ajax({
			type : "get",
			url : "Fetch_Data",
			data:{type:"City",stateid:$('#state').val()},
			success : function(responseData, textStatus, jqXHR) {
				
				/*//var obj = $.parseJSON(responseData);
				alert(responseData);*/
			//alert(responseData);
				 var dat = eval(responseData);
				 
				try {

					document.getElementById('city').length = 0;
					var comm_city = document.getElementById('city');

					for (var i = 0; i < dat.length; i++) {

						myOption = document.createElement("option");
						myOption.text = dat[i].split("#")[1];
						myOption.value = dat[i].split("#")[0];
						myOption.id = dat[i].split("#")[0];
						comm_city.appendChild(myOption);

					}
					
					SelectLoad('city',val);

				} catch (err) {

					$('#Responce_Message').html(err.message);

				} 

			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(errorThrown);
			 
				$('#Responce_Message').html("Error;" + textStatus);
				/*alert("Error;" + errorThrown);

				alert("jqXHR;" + jqXHR.status);*/

			}
		});
	} catch (err) {

		$('#Responce_Message').html('Communication issue please try again..');
	}
	
	
		}
 

		};




		function SelectLoad(val1,val){
			
			var $Seleted=document.getElementById(val1);
			for(var i, j = 0; i = $Seleted.options[j]; j++) {
				    if(i.value == val) {
				    	$Seleted.selectedIndex = j;
				        break;
				    }
			   }
				
			
			
		}
		
		
		$('#EmployeeId').blur(function(){
			
			 
			
			
			try {
				//alert("In Script-2.1"+val);

				$.ajax({
					type : "get",
					url : "Fetch_Data",
					data:{type:"EMPIDCHECK",EmployeeId:$('#EmployeeId').val()},
					success : function(responseData, textStatus, jqXHR) {
						 
						 var dat = eval(responseData);
						 
						 if(dat==0)
							 {
							 
							 
								$('#button').prop('disabled', true);
							 }
						 else
							 {
							 $('#button').prop('disabled', false);
							 }

					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(errorThrown);
					 
						$('#Responce_Message').html( textStatus);
						/*alert("Error;" + errorThrown);

						alert("jqXHR;" + jqXHR.status);*/

					}
				});
			} catch (err) {

				$('#Responce_Message').html('Communication issue please try again');
			}
			
		});

 
 

