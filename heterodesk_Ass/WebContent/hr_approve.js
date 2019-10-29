function myFunction(){
           // var inp = $('#dept').val().toUpperCase();
  
           
         if($('#type').val()!="Select")
        	 {
        	 
            $.ajax({
                async: false,
                type: "GET",
                contentType: "application/json; charset=utf-8",
                url: "Data",
                data: { type: $('#type').val()}
            }).success(function(response){
            	
                var jsondata = JSON.parse(response);
               // loadData(jsondata);
                
                loadData1(jsondata);
                
               // $('#conCode').html(data);
            }).fail(function(jqXHR, textStatus){
                console.log("[AJAX] Error: JSON WSDL Lookup>>>" + textStatus);
            }).done(function(){
                console.log("[AJAX] Complete: JSON WSDL Lookup");
            });
            
            
        }
            
           else  
       	 {
       	 //alert("Please Select Type..");
        	   
        $('#Responce_Message').html('Please Select Type..');
       	 $('#table').hide();
       	 }
        }
 //===================
  
function loadData1(datas){
	
	return datas;
}
//=====================
        function loadData(datas){
            var sb = '';
            var table = $('#tableBody');
           // $('#json').show();
            
            table.html('');
         //alert($('#type').val());
         
         if($('#type').val()=="EmployeeName")
        	 {
        	// $('#FL_NAME_TABLE').show();
        	 
        	 $('#tr1').show();
        	 $('#tr2').hide();
        	 $('#tr3').hide();
        	 $('#tr4').hide();
        	 $('#tr5').hide(); 
        	 $('#tr6').hide();
        	 $('#tr7').hide();
        	 $('#table').show();
        	 
            $.each(datas, function(index, value){
                sb += '<tr>';
                sb += '    <td>' + value.EMPLOYEEID + '</td>';
                sb += '    <td>' + value.FIRSTNAME + '</td>';
                sb += '    <td>' + value.LASTNAME + '</td>';
                sb += '    <td>' + value.BUTTON.concat("&nbsp;").concat(value.BUTTON1) + ' </td>';
                //sb += '    <td>' + value.BUTTON1 + '</td>';
                sb += '</tr>';
            });
 
            table.append(sb);
        	 }
         
         else if($('#type').val()=="DateOfBirth")
        	 {
        	// $('#FL_NAME_TABLE1').show();
        	 
        	 $('#tr1').hide();
        	 $('#tr2').show();
        	 $('#tr3').hide();
        	 $('#tr4').hide();
        	 $('#tr5').hide();
        	 $('#tr6').hide();
        	 $('#tr7').hide();
        	 $('#table').show();
        	 
             $.each(datas, function(index, value){
                 sb += '<tr>';
                 sb += '    <td>' + value.EMPLOYEEID + '</td>';
                 sb += '    <td>' + value.DATEOFBIRTH + '</td>';
                 //sb += '    <td>' + value.BUTTON + '</td>';
                 sb += '    <td width="25%">' + value.BUTTON.concat("&nbsp;").concat(value.BUTTON1) + ' </td>';
                 sb += '</tr>';
             });
  
             table.append(sb);
        	 }
         
         else if($('#type').val()=="PFNO")
    	 {
    	// $('#FL_NAME_TABLE').show();
    	 
    	     $('#tr1').hide();
        	 $('#tr2').hide();
        	 $('#tr3').show();
        	 $('#tr4').hide();
        	 $('#tr5').hide();
        	 $('#tr6').hide();
        	 $('#tr7').hide();
        	 $('#table').show();
         $.each(datas, function(index, value){
             sb += '<tr>';
             sb += '    <td>' + value.EMPLOYEEID + '</td>';
             sb += '    <td>' + value.PFNO + '</td>';
             //sb += '    <td>' + value.BUTTON + '</td>';
             sb += '    <td>' + value.BUTTON.concat("&nbsp;").concat(value.BUTTON1) + ' </td>';
             sb += '</tr>';
         });

         table.append(sb);
    	 }
         
         
         else if($('#type').val()=="PFUAN")
    	 {
    	 //$('#FL_NAME_TABLE').show();
    	 
    	     $('#tr1').hide();
        	 $('#tr2').hide();
        	 $('#tr3').hide();
        	 $('#tr4').hide();
        	 $('#tr5').show();
        	 $('#tr6').hide();
        	 $('#tr7').hide();
        	 $('#table').show();
    	 
         $.each(datas, function(index, value){
             sb += '<tr>';
             sb += '    <td>' + value.EMPLOYEEID + '</td>';
             sb += '    <td>' + value.UANNO + '</td>';
            // sb += '    <td>' + value.BUTTON + '</td>';
             //sb += '    <td>' + value.BUTTON1 + '</td>';
             sb += '    <td>' + value.BUTTON.concat("&nbsp;").concat(value.BUTTON1) + ' </td>';
             
             sb += '</tr>';
         });

         table.append(sb);
    	 }
         else if($('#type').val()=="BANKDETAILS")
    	 {
    	 //$('#FL_NAME_TABLE').show();
    	 
    	     $('#tr1').hide();
        	 $('#tr2').hide();
        	 $('#tr3').hide();
        	 $('#tr4').hide();
        	 $('#tr5').hide();
        	 $('#tr6').show();
        	 $('#tr7').hide();
    	 
         $.each(datas, function(index, value){
             sb += '<tr>';
             sb += '    <td>' + value.EMPLOYEEID + '</td>';
             sb += '    <td>' + value.BANKTYPE + '</td>';
             sb += '    <td>' + value.ACCOUNTNO + '</td>';
           // sb += '    <td>' + value.BUTTON + '</td>';
             sb += '    <td>' + value.BUTTON.concat("&nbsp;").concat(value.BUTTON1) + ' </td>';             sb += '</tr>';
         });

         table.append(sb);
    	 }
         
         else if($('#type').val()=="PerAddress")
    	 {
    	// $('#FL_NAME_TABLE').show();
    	 
    	     $('#tr1').hide();
        	 $('#tr2').hide();
        	 $('#tr3').hide();
        	 $('#tr4').hide();
        	 $('#tr5').hide();
        	 $('#tr6').hide();
        	 $('#tr7').show();
        	 $('#table').show();
    	
    	
         $.each(datas, function(index, value){
             sb += '<tr>';
             sb += '    <td>' + value.EMPLOYEEID + '</td>';
             sb += '    <td>' + value.PERADDRESS1 + '</td>';
             sb += '    <td>' + value.PERADDRESS2 + '</td>';
             sb += '    <td>' + value.EMAILID + '</td>';
             sb += '    <td>' + value.STATE + '</td>';
             sb += '    <td>' + value.CITY + '</td>';
            // sb += '    <td>' + value.BUTTON + '</td>';
             sb += '    <td>' + value.BUTTON.concat("&nbsp;").concat(value.BUTTON1) + ' </td>';
             
             sb += '</tr>';
         });

         table.append(sb);
    	 }
         
        }
        
       
 
        /*function Approve(val){*//*
        	//alert( val.alt+""+val.name);
        	
        	
        	if(val.alt=="Employee_approve")
        		{
        		 
        	try {
				$.ajax({
					type : "get",
					url : "Fetch_Data",
					data : {
						 type:val.alt,data:val.name
						 
					},
					success : function(responseData, textStatus, jqXHR) {


					 if(eval(responseData)==1)
						 {
						 $('#'+val.id+'').hide();
						 
						 $('#'+val.id.concat('H')+'').html('<input type="button"  class="btn btn-success"  value="Approved...">');
						 
						// alert('Approved......');
						 }
					 
					 else
						 {
						 
						// alert(responseData);
						 $('#Responce_Message').html(responseData);
						 }

					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(errorThrown);
						// document.getElementById("Responce_Message").innerHTML=errorThrown;
						// document.getElementById("Responce_Message_btn").style.display='';
					//	alert("Error;" + textStatus);
					//	alert("Error;" + errorThrown);

					//	alert("jqXHR;" + jqXHR.status);

					}
				});
			} catch (err) {

				//alert(err + ":at Last");
			}
        }
        	
        	else if(val.alt=="Dateofbirth_approve")
        		{
        		try {
    				$.ajax({
    					type : "get",
    					url : "Fetch_Data",
    					data : {
    						 type:val.alt,data:val.name
    						 
    					},
    					success : function(responseData, textStatus, jqXHR) {
    						 //alert(responseData);

    					 if(eval(responseData)==1)
    						 {
    						 $('#'+val.id+'').hide();
    						 
    						 $('#'+val.id.concat('D')+'').html('<input type="button" class="btn btn-success"  value="Approved...">');
    						 
    						// alert('Approved......');
    						 }
    					 
    					 else
    						 {
    						 $('#Responce_Message').html(responseData);
    						 //alert(responseData);
    						 }

    					},
    					error : function(jqXHR, textStatus, errorThrown) {
    						console.log(errorThrown);
    						// document.getElementById("Responce_Message").innerHTML=errorThrown;
    						// document.getElementById("Responce_Message_btn").style.display='';
    						//alert("Error;" + textStatus);
    						//alert("Error;" + errorThrown);

    						//alert("jqXHR;" + jqXHR.status);

    					}
    				});
    			} catch (err) {

    				//alert(err + ":at Last");
    			}
        		}
        	
        	
        	else if(val.alt=="PFNO_approve")
    		{
    		try {
				$.ajax({
					type : "get",
					url : "Fetch_Data",
					data : {
						 type:val.alt,data:val.name
						 
					},
					success : function(responseData, textStatus, jqXHR) {
						 //alert(responseData);

					 if(eval(responseData)==1)
						 {
						 $('#'+val.id+'').hide();
						 
						 $('#'+val.id.concat('P')+'').html('<input type="button" class="btn btn-success"  value="Approved...">');
						 
						// alert('Approved......');
						 }
					 
					 else
						 {
						 $('#Responce_Message').html(responseData);
						 //alert(responseData);
						 }

					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(errorThrown);
						// document.getElementById("Responce_Message").innerHTML=errorThrown;
						// document.getElementById("Responce_Message_btn").style.display='';
						//alert("Error;" + textStatus);
						//alert("Error;" + errorThrown);

						//alert("jqXHR;" + jqXHR.status);

					}
				});
			} catch (err) {

				alert(err + ":at Last");
			}
    		}
        	 
        	else if(val.alt=="PFUAN_approve")
    		{
    		try {
				$.ajax({
					type : "get",
					url : "Fetch_Data",
					data : {
						 type:val.alt,data:val.name
						 
					},
					success : function(responseData, textStatus, jqXHR) {
						 //alert(responseData);

					 if(eval(responseData)==1)
						 {
						 $('#'+val.id+'').hide();
						 
						 $('#'+val.id.concat('U')+'').html('<input type="button" class="btn btn-success"  value="Approved...">');
						 
						// alert('Approved......');
						 }
					 
					 else
						 {
						 $('#Responce_Message').html(responseData);
						// alert(responseData);
						 }

					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(errorThrown);
						// document.getElementById("Responce_Message").innerHTML=errorThrown;
						// document.getElementById("Responce_Message_btn").style.display='';
						//alert("Error;" + textStatus);
						//alert("Error;" + errorThrown);

						//alert("jqXHR;" + jqXHR.status);

					}
				});
			} catch (err) {

				//alert(err + ":at Last");
			}
    		}
        	
        	else if(val.alt=="Bank_approve")
    		{
    		try {
				$.ajax({
					type : "get",
					url : "Fetch_Data",
					data : {
						 type:val.alt,data:val.name
						 
					},
					success : function(responseData, textStatus, jqXHR) {
						 //alert(responseData);

					 if(eval(responseData)==1)
						 {
						 $('#'+val.id+'').hide();
						 
						 $('#'+val.id.concat('B')+'').html('<input type="button"class="btn btn-success"  value="Approved...">');
						 
						// alert('Approved......');
						 }
					 
					 else
						 {
						 $('#Responce_Message').html(responseData);
						// alert(responseData);
						 }

					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(errorThrown);
						// document.getElementById("Responce_Message").innerHTML=errorThrown;
						// document.getElementById("Responce_Message_btn").style.display='';
					//	alert("Error;" + textStatus);
					//	alert("Error;" + errorThrown);

					//	alert("jqXHR;" + jqXHR.status);

					}
				});
			} catch (err) {

				//alert(err + ":at Last");
			}
    		}
        	
        	else if(val.alt=="Adresss_approve")
    		{
    		try {
				$.ajax({
					type : "get",
					url : "Fetch_Data",
					data : {
						 type:val.alt,data:val.name
						 
					},
					success : function(responseData, textStatus, jqXHR) {
						 //alert(responseData);

					 if(eval(responseData)==1)
						 {
						 $('#'+val.id+'').hide();
						 
						 $('#'+val.id.concat('ADD')+'').html('<input type="button" class="btn btn-success"  value="Approved...">');
						 
						// alert('Approved......');
						 }
					 
					 else if(eval(responseData)==0)
						 {
						 $('#Responce_Message').html("Not Approve Please Contact to our Admin...");
						// alert("Not Approve.........");
						 }
					 
					else
						 {
						
						$('#Responce_Message').html(responseData);
						 //alert(responseData);
						 }

					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(errorThrown);
						// document.getElementById("Responce_Message").innerHTML=errorThrown;
						// document.getElementById("Responce_Message_btn").style.display='';
						//alert("Error;" + textStatus);
						//alert("Error;" + errorThrown);

						//alert("jqXHR;" + jqXHR.status);

					}
				});
			} catch (err) {

				//alert(err + ":at Last");
			}
    		}
        	
        *///}