function AddRow(table_ID) {



	//var form=document.getElementById("myform");
	var TDS=$('#TDS').val();
//	alert($('#Description').val().length);
	var Surcharge=$('#Surcharge').val();
	var Education_Cess=$('#Education_Cess').val();
	var Interest=$('#Interest').val();
	var Fees=$('#Fees').val();
	var Penalty_Others=$('#Penalty_Others').val();
	var Total_Tax=$('#Total_Tax').val();
	var Mode_Of_Deposit=$('#Mode_Of_Deposit').val();
	var Bank_Branch_Code=$('#Bank_Branch_Code').val();
	var Challan_Serial_No=$('#Challan_Serial_No').val();
	var Date_Of_Amount_Deposited=$('#Date_Of_Amount_Deposited').val();
	var Minor_Head_Challan=$('#Minor_Head_Challan').val();

	var Interest_Statement=$('#Interest_Statement').val();
	var Other_Statement=$('#Other_Statement').val();


	if (TDS.length >0 && Surcharge.length >0 && Education_Cess.length>0 && Interest.length>0 && Fees.length>0 && Penalty_Others.length>0 && Total_Tax.length>0 && Mode_Of_Deposit.length>0 && Bank_Branch_Code.length>0 
			&& Challan_Serial_No.length>0 && Date_Of_Amount_Deposited.length>0 && Minor_Head_Challan.length>0 && Interest_Statement.length>0 && Other_Statement.length>0) {

		var table = document.getElementById(table_ID);
		var rowCount = table.rows.length;
		//		 alert(rowCount);
		var row = table.insertRow(rowCount);
		var cell = row.insertCell(0);
		var element = document.createElement("input");
		element.type = "text";
		element.id = "TDS";
		element.name = "TDS";
		element.value = TDS;
//		element.style.width = '90px';
//		element.style.width = '!important';
		element.setAttribute("readonly", "true");
		element.setAttribute("class", "form-control1 input-sm");
		cell.appendChild(element);

		var cell1 = row.insertCell(1);
		var element1 = document.createElement("input");
		element1.type = "text";
		element1.id = "Surcharge";
		element1.name = "Surcharge";
		element1.value =Surcharge;
//		element1.size = 3;
//		element1.style.width = '50px';
//		element1.style.width = '!important';
		element1.setAttribute("readonly", "true");
		element1.setAttribute("class", "form-control1 input-sm");
		cell1.appendChild(element1);

		var cell2 = row.insertCell(2);
		var element2 = document.createElement("input");
		element2.type = "text";
		element2.id = "Education_Cess";
		element2.name = "Education_Cess";
		element2.value = Education_Cess;
//		element2.size = 3;
//		element2.style.width = '70px';
//		element2.style.width = '!important';
		element2.setAttribute("readonly", "true");
		element2.setAttribute("class", "form-control1 input-sm");
		
		cell2.appendChild(element2);


		var cell3 = row.insertCell(3);
		var element3 = document.createElement("input");
		element3.type = "text";
		element3.id = "Interest";
		element3.name = "Interest";
		element3.value = Interest;
//		element3.size = 3;
//		element3.style.width = '40px';
//		element3.style.width = '!important';
		element3.setAttribute("readonly", "true");
		element3.setAttribute("class", "form-control1 input-sm");
		cell3.appendChild(element3);


		var cell4 = row.insertCell(4);
		var element4 = document.createElement("input");
		element4.type = "text";
		element4.id = "Fees";
		element4.name = "Fees";
		element4.value = Fees;
//		element4.size = 3;
//		element4.style.width = '40px';
//		element4.style.width = '!important';
		element4.setAttribute("readonly", "true");
		element4.setAttribute("class", "form-control1 input-sm");
		cell4.appendChild(element4);

		var cell5 = row.insertCell(5);
		var element5 = document.createElement("input");
		element5.type = "text";
		element5.id = "Penalty_Others";
		element5.name = "Penalty_Others";
		element5.value = Penalty_Others;
//		element5.size = 3;
//		 element5.style.width = '40px';
//		 element5.style.width = '!important';
		element5.setAttribute("readonly", "true");
		element5.setAttribute("class", "form-control1 input-sm");
		cell5.appendChild(element5);

		var cell6 = row.insertCell(6);
		var element6 = document.createElement("input");
		element6.type = "text";
		element6.id = "Total_Tax";
		element6.name = "Total_Tax";
		element6.value = Total_Tax;
//		element6.size = 3;
//		element6.style.width = '80px';
//		element6.style.width = '!important';
		element6.setAttribute("readonly", "true");
		element6.setAttribute("class", "form-control1 input-sm");
		cell6.appendChild(element6);

		var cell7 = row.insertCell(7);
		var element7 = document.createElement("input");
		element7.type = "text";
		element7.id = "Mode_Of_Deposit";
		element7.name = "Mode_Of_Deposit";
		element7.value = Mode_Of_Deposit;
//		element7.size = 3;
//		element7.style.width = '80px';
//		element7.style.width = '!important';
		element7.setAttribute("readonly", "true");
		element7.setAttribute("class", "form-control1 input-sm");
		cell7.appendChild(element7);

		var cell8 = row.insertCell(8);
		var element8 = document.createElement("input");
		element8.type = "text";
		element8.id = "Bank_Branch_Code";
		element8.name = "Bank_Branch_Code";
		element8.value = Bank_Branch_Code;
//		element8.size = 3;
//		element8.style.width = '40px';
//		element8.style.width = '!important';
			element8.setAttribute("readonly", "true");
		element8.setAttribute("class", "form-control1 input-sm");
		cell8.appendChild(element8);

		var cell9 = row.insertCell(9);
		var element9 = document.createElement("input");
		element9.type = "text";
		element9.id = "Challan_Serial_No";
		element9.name = "Challan_Serial_No";
		element9.value = Challan_Serial_No;
//		element9.size = 3;
//		element9.style.width = '80px';
//		element9.style.width = '!important';
		element9.setAttribute("readonly", "true");
		element9.setAttribute("class", "form-control1 input-sm");
		cell9.appendChild(element9);

		var cell10 = row.insertCell(10);
		var element10 = document.createElement("input");
		element10.type = "text";
		element10.id = "Date_Of_Amount_Deposited";
		element10.name = "Date_Of_Amount_Deposited";
		element10.value = Date_Of_Amount_Deposited;
//		element10.size = 3;
//		element10.style.width = '100px';
//		element10.style.width = '!important';
			element10.setAttribute("readonly", "true");
		element10.setAttribute("class", "form-control1 input-sm");
		cell10.appendChild(element10);


		var cell11 = row.insertCell(11);
		var element11 = document.createElement("input");
		element11.type = "text";
		element11.id = "Minor_Head_Challan";
		element11.name = "Minor_Head_Challan";
		element11.value = Minor_Head_Challan;
//		element11.size = 3;
//		element11.style.width = '100px';
//		element11.style.width = '!important';
		element11.setAttribute("readonly", "true");
		element11.setAttribute("class", "form-control1 input-sm");
		cell11.appendChild(element11);

		var cell12 = row.insertCell(12);
		var element12 = document.createElement("input");
		element12.type = "text";
		element12.id = "Interest_Statement";
		element12.name = "Interest_Statement";
		element12.value = Interest_Statement;
//		element12.size = 3;
//		element12.style.width = '60px';
//		element12.style.width = '!important';
		element12.setAttribute("readonly", "true");
		element12.setAttribute("class", "form-control1 input-sm ");
		cell12.appendChild(element12);
		
		var cell13 = row.insertCell(13);
		var element13 = document.createElement("input");
		element13.type = "text";
		element13.id = "Other_Statement";
		element13.name = "Other_Statement";
		element13.value = Other_Statement;
//		element13.size = 3;
//		element13.style.width = '60px';
//		element13.style.width = '!important';
		element13.setAttribute("readonly", "true");
		element13.setAttribute("class", "form-control1 input-sm ");
		cell13.appendChild(element13);

		var cell14 = row.insertCell(14);
		//var element13 = document.createElement("input");
		cell14.innerHTML = "<input type='checkbox' name='chkbox2' id='chkbox_2"
			+ rowCount
			+ "' value="
			+ rowCount
			+ "  onClick=deleteRow1('datatable')  checked style='display:none;'> ";

		// &nbsp; <input type='button' class='form-control' value='DELETE'
		// valign='top' onClick=deleteRowss("+rowCount+") >

	
		var cell16 = row.insertCell(15);
		var element16 = document.createElement("input");
		element16.type = "button";
		element16.id = "LICBUT";
		element16.name = "button";
		element16.value = "Edit";
		element16.setAttribute("onClick", "deleteRowss1("+rowCount+",'E')");
		element16.setAttribute("class", "btn btn-danger btn-xs btn-rounded");
		//element14.setAttribute("class", "btn btn-danger");
		cell16.appendChild(element16);

		
		var cell15 = row.insertCell(16);
		var element15 = document.createElement("input");
		element15.type = "button";
		element15.id = "LICBUT";
		element15.name = "button";
		element15.value = "Delete";
		element15.setAttribute("onClick", "deleteRowss1("+rowCount+",'D')");
		element15.setAttribute("class", "btn btn-danger btn-xs btn-rounded");
		//element14.setAttribute("class", "btn btn-danger");
		cell15.appendChild(element15);
		$('#TDS').val('');
		$('#Surcharge').val('');
		$('#Education_Cess').val('');
		$('#Interest').val('');
		$('#Fees').val('');
		$('#Penalty_Others').val('');
		$('#Total_Tax').val('');
		$('#Mode_Of_Deposit').val('C - Challan');
		$('#Bank_Branch_Code').val('');
		$('#Challan_Serial_No').val('');
		$('#Date_Of_Amount_Deposited').val('');
		$('#Minor_Head_Challan').val('200 - TDS payable by taxpayer');  
		$('#Interest_Statement').val('');
		$('#Other_Statement').val('');


		//	addRow('dataTable');
	//	Add_RowValues();


		//}


	}
	else
	{
		if(TDS=="")
		{
			alert("Please Enter TDS..");
			//form.Description.focus();
			//	return false;
		}
		else if(Surcharge==""){
			alert("Please Enter Surcharge..");
			/*	form.HSNCode.focus();
			return false;*/
		}
		else if(Education_Cess==""){
			alert("Please Enter Education Cess..");
			/*form.UoM.focus();
			return false;*/
		}
		else if(Interest==""){
			alert("Please Enter Interest..");
			/*	form.Qty.focus();
			return false;*/
		}
		else if(Fees==""){
			alert("Please Enter Fees..");
			/*	form.Rate.focus();
			return false;*/
		}
		else if(Penalty_Others==""){
			alert("Please Enter Penalty Others..");
			/*form.Taxable.focus();
			return false;*/
		}
		/*else if(Total_Tax==""){
			alert("Please Enter Total Tax..");
				form.CGST_Rate.focus();
			return false;
		}*/
		else if(Mode_Of_Deposit==""){
			alert("Please Enter Mode Of Deposit..");
			/*		form.SGST_Rate.focus();
			return false;*/
		}
		else if(Bank_Branch_Code==""){
			alert("Please Enter Bank Branch Code..");
			/*	form.IGST_Rate.focus();
			return false;*/
		}else if(Challan_Serial_No==""){
			alert("Please Enter Challan Serial No..");
			
		}else if(Date_Of_Amount_Deposited==""){
			alert("Please Enter Date Of Amount Deposited..");
			
		}else if(Minor_Head_Challan==""){
			alert("Please Enter Minor Head Challan..");
			
		}else if(Interest_Statement==""){
			alert("Please Enter Interest Statement..");
			
		}else if(Other_Statement==""){
			alert("Please Enter Other Statement..");
			
		}/*else if(Total==""){

			alert("Please");
		}*/

	}

}


function deleteRowss1(val,type){

	// alert(val);
	var id="chkbox_2"+val ;

	// alert(id);
	try{
		document.getElementById(""+id+"").checked=false;	
	}catch(err){
		alert(err.value);
	}
	deleteRow1('datatable',type);

}

function deleteRow1(table_ID,type){
	// alert("tableID::"+tableID);
	try {
		var table = document.getElementById(table_ID);
		var rowCount = table.rows.length;
 
		
        //   alert(rowCount);
	/*	var TDS=$('#TDS').val();
//		alert($('#Description').val().length);
		var Surcharge=$('#Surcharge').val();
		var Education_Cess=$('#Education_Cess').val();
		var Interest=$('#Interest').val();
		var Fees=$('#Fees').val();
		var Penalty_Others=$('#Penalty_Others').val();
		var Total_Tax=$('#Total_Tax').val();
		var Mode_Of_Deposit=$('#Mode_Of_Deposit').val();
		var Bank_Branch_Code=$('#Bank_Branch_Code').val();
		var Challan_Serial_No=$('#Challan_Serial_No').val();
		var Date_Of_Amount_Deposited=$('#Date_Of_Amount_Deposited').val();
		var Minor_Head_Challan=$('#Minor_Head_Challan').val();

		var Interest_Statement=$('#Interest_Statement').val();
		var Other_Statement=$('#Other_Statement').val();*/
		
		
		
		
		
	
		for(var i=0; i<rowCount; i++) {
			var row = table.rows[i];
			
			//alert(table.rows[i]);
			var chkbox = row.cells[14].childNodes[0];
			//alert(chkbox.name);
			//alert(chkbox.value);
			/* try{

		 }catch(err){
		   alert(err.value);
		 }*/   
			// if(null != chkbox && true == chkbox.checked) {
			if(null != chkbox && false == chkbox.checked) {
				// var  id="Amt_2_"+chkbox.value+"";
				//var net=document.getElementById(""+id+"").value;
				//var netOld=document.getElementById("FF0005").value;
				//document.getElementById("FF0005").value=eval(netOld)-eval(net);
				
				
				
				if(type=='E'){
					
					//alert($("input[name='TDS']")[rowCount-1].value);
					
					//alert(rowCount-1);
					$('#TDS').val($("input[name='TDS']")[i].value);
					$('#Surcharge').val($("input[name='Surcharge']")[i].value);
					$('#Education_Cess').val($("input[name='Education_Cess']")[i].value);
					$('#Interest').val($("input[name='Interest']")[i].value);
					$('#Fees').val($("input[name='Fees']")[i].value);
					$('#Penalty_Others').val($("input[name='Penalty_Others']")[i].value);
					$('#Total_Tax').val($("input[name='Total_Tax']")[i].value);
					$('#Mode_Of_Deposit').val($("input[name='Mode_Of_Deposit']")[i].value);
					$('#Bank_Branch_Code').val($("input[name='Bank_Branch_Code']")[i].value);
					$('#Challan_Serial_No').val($("input[name='Challan_Serial_No']")[i].value);
					$('#Date_Of_Amount_Deposited').val($("input[name='Date_Of_Amount_Deposited']")[i].value);
					$('#Minor_Head_Challan').val($("input[name='Minor_Head_Challan']")[i].value);  
					$('#Interest_Statement').val($("input[name='Interest_Statement']")[i].value);
					$('#Other_Statement').val($("input[name='Other_Statement']")[i].value);

					
					
					// alert(rowCount);
					
				}
				
				
				 
				table.deleteRow(i);
				rowCount--;
				i--;
			}
			try{
			}catch(err){	
			}
		}
	}catch(e) {
		alert(e);
	}
}


/*$('#Rate').blur(function(){

	var Qty=$('#Qty').val();
	var Rate=$('#Rate').val();

	var Total_Taxable=Qty*Rate;

	$('#Taxable').val(Total_Taxable);

});

$('#CGST_Rate').blur(function(){

	var Qty=$('#Qty').val();
	var Rate=$('#Rate').val();
	var Total_Taxable=Qty*Rate;
	var CGST_Rate=$('#CGST_Rate').val();
	var CGST_Total=Math.round((Total_Taxable*CGST_Rate)/100);
	$('#CGST_Amt').val(CGST_Total);

	var SGST_Rate=$('#SGST_Rate').val();
	var SGST_Amt=$('#SGST_Amt').val();
	var IGST_Rate=$('#IGST_Rate').val();
	var IGST_Amt=$('#IGST_Amt').val();
});
$('#SGST_Rate').blur(function(){
	var Qty=$('#Qty').val();
	var Rate=$('#Rate').val();
	var Total_Taxable=Qty*Rate;
	var SGST_Rate=$('#SGST_Rate').val();
	var SGST_Total=Math.round((Total_Taxable*SGST_Rate)/100);
	$('#SGST_Amt').val(SGST_Total);

});

$('#IGST_Rate').blur(function(){
	var Qty=$('#Qty').val();
	var Rate=$('#Rate').val();
	var Total_Taxable=Qty*Rate;
	var IGST_Rate=$('#IGST_Rate').val();
	var IGST_Total=Math.round((Total_Taxable*IGST_Rate)/100);
	$('#IGST_Amt').val(IGST_Total);

});
 */
$('#TDS,#Surcharge,#Education_Cess,#Interest,#Fees,#Penalty_Others').keyup(function(){


	var TDS=$('#TDS').val();
	var Surcharge=$('#Surcharge').val();
	var Education_Cess=$('#Education_Cess').val();
	var Interest=$('#Interest').val();
	var Fees=$('#Fees').val();
	var Penalty_Others=$('#Penalty_Others').val();
	
	
	var n1 = parseFloat(TDS) || 0.00;
	var n2 = parseFloat(Surcharge) || 0.00;
	var n3 = parseFloat(Education_Cess) || 0.00;
	var n4 = parseFloat(Interest) || 0.00;
	var n5 = parseFloat(Fees) || 0.00;
	var n6 = parseFloat(Penalty_Others) || 0.00;
	
	//alert(TDS.length);
	//alert(Surcharge.length);
	
/*	var n1 = parseInt(TDS) || 0;
	var n2 = parseInt(Surcharge) || 0;
	var n3 = parseInt(Education_Cess) || 0;
	var n4 = parseInt(Interest) || 0;
	var n5 = parseInt(Fees) || 0;
	var n6 = parseInt(Penalty_Others) || 0;*/
	
	 
	//if(isNaN(TDS) || isNaN(Surcharge) || isNaN(Education_Cess) || isNaN(Interest) || isNaN(Fees) || isNaN(Penalty_Others)) {
	
		/* if(TDS.length==0 || Surcharge.length==0 || Education_Cess.length==0 || Interest.length==0 ||  Fees.length==0 || Penalty_Others.length==0){
	//alert("bhanu");
		TDS=0;
		Surcharge=0;
		Education_Cess=0;
		Interest=0;
		Fees=0;
		Penalty_Others=0;
	 
	} */
	
	var Total_Tax=n1+n2+n3+n4+n5+n6;
		
	/*	var Total_Tax=parseInt(TDS)+parseInt(0)+
		parseInt(0)+parseInt(0)+
		parseInt(0)+parseInt(0);*/
	
	//alert(Total_Tax);

	$('#Total_Tax').val(Total_Tax.toFixed(2));
	
});



/*function Add_RowValues() {
	var sumAmt=0;

	var data =document.getElementsByName("Qty").length;
	//alert("data::"+data[i]);
	for(var i=0;i<data;i++){
		//alert("data::"+document.getElementsByName("rate")[i].value);
		sumAmt=sumAmt+eval(document.getElementsByName("Qty")[i].value);
	}

	$('#Total_Qty').html(sumAmt.toFixed(2));
	sumAmt=0;

	var data1=document.getElementsByName("Taxable").length;
	for(var i=0;i<data1;i++){
		//alert("data::"+document.getElementsByName("rate")[i].value);
		sumAmt=sumAmt+eval(document.getElementsByName("Taxable")[i].value);
	}
	$('#Total_TaxableAmt').html(sumAmt.toFixed(2));
	$('#Total_TaxableAmt_1').html(sumAmt.toFixed(2));
	sumAmt=0;

	var data2=document.getElementsByName("CGST_Amt").length;
	for(var i=0;i<data2;i++){
		//alert("data::"+document.getElementsByName("rate")[i].value);
		sumAmt=sumAmt+eval(document.getElementsByName("CGST_Amt")[i].value);
	}
	$('#Total_CGSTAmt').html(sumAmt.toFixed(2));

	sumAmt=0;        	
	var data2=document.getElementsByName("SGST_Amt").length;
	for(var i=0;i<data2;i++){
		//alert("data::"+document.getElementsByName("rate")[i].value);
		sumAmt=sumAmt+eval(document.getElementsByName("SGST_Amt")[i].value);
	}
	$('#Total_SGSTAmt').html(sumAmt.toFixed(2));

	sumAmt=0;        	
	var data2=document.getElementsByName("IGST_Amt").length;
	for(var i=0;i<data2;i++){
		//alert("data::"+document.getElementsByName("rate")[i].value);
		sumAmt=sumAmt+eval(document.getElementsByName("IGST_Amt")[i].value);
	}
	$('#Total_IGSTAmt').html(sumAmt.toFixed(2)); 
	sumAmt=0;        	
	var data2=document.getElementsByName("Total").length;
	for(var i=0;i<data2;i++){
		//alert("data::"+document.getElementsByName("rate")[i].value);
		sumAmt=sumAmt+eval(document.getElementsByName("Total")[i].value);
	}
	$('#Total_TotalAmt').html(sumAmt.toFixed(2));            
	$('#Total_TotalAmt_1').html(sumAmt.toFixed(2));



	var Total_GSTAmt_1=eval(document.getElementById("Total_CGSTAmt").innerText)+eval(document.getElementById("Total_SGSTAmt").innerText)+eval(document.getElementById("Total_IGSTAmt").innerText);

	$('#Total_GSTAmt_1').html(Total_GSTAmt_1.toFixed(2));  


}*/




   $('#Submit').click(function(e) {
	   
	   
	   
	   
	    var TDS=$('#TDS').val();
	   var Surcharge=$('#Surcharge').val();
	   var Education_Cess=$('#Education_Cess').val();
	   var Interest=$('#Interest').val();
	   var Fees=$('#Fees').val();
	   var Penalty_Others=$('#Penalty_Others').val();
	   var Total_Tax=$('#Total_Tax').val();
	   var Mode_Of_Deposit=$('#Mode_Of_Deposit').val();
	   var Bank_Branch_Code=$('#Bank_Branch_Code').val();
	   var Challan_Serial_No=$('#Challan_Serial_No').val();
	   var Date_Of_Amount_Deposited=$('#Date_Of_Amount_Deposited').val();
	   var Minor_Head_Challan=$('#Minor_Head_Challan').val();
	   var Interest_Statement=$('#Interest_Statement').val();
	   var Other_Statement=$('#Other_Statement').val(); 
	   
	   
	   
//alert("P");
	   
	   
	   
	   var table = document.getElementById('datatable');
		var rowCount = table.rows.length;


		if(TDS!=""&&Surcharge==""){

			alert("Please Fill All Product Details..");
			
			return false;
		} 

		else if(TDS!="" && Surcharge!="" && Education_Cess!="" && Interest!="" && Fees!="" && Penalty_Others!="" 
			&& Total_Tax!="" && Mode_Of_Deposit!="" && Bank_Branch_Code!="" && Challan_Serial_No!="" && Date_Of_Amount_Deposited!="" 
				&& Minor_Head_Challan!="" && Interest_Statement!="" && Other_Statement!=""){
			alert("Please Click on Add Button");
			form.Add.focus();
			return false;
		}else if(rowCount==0)
		{
			alert("Please Enter Atleast one Product details");
			return false;
		}
	   
	   
	    
 

  TDS="";
$("input[name=TDS]").each(function() {
	   // alert($(this).val());
	   TDS=TDS.concat($(this).val()).concat(",");
	    //Data=Data.append($(this).val());
	});	
//alert(Data1);

  Surcharge="";
$("input[name=Surcharge]").each(function() {
	   // alert($(this).val());
	Surcharge=Surcharge.concat($(this).val()).concat(",");
	    //Data=Data.append($(this).val());
	});	

  Education_Cess="";
$("input[name=Education_Cess]").each(function() {
	   // alert($(this).val());
	Education_Cess=Education_Cess.concat($(this).val()).concat(",");
	    //Data=Data.append($(this).val());
	});	

  Interest="";
$("input[name=Interest]").each(function() {
	   // alert($(this).val());
	Interest=Interest.concat($(this).val()).concat(",");
	    //Data=Data.append($(this).val());
	});	

  Fees="";
$("input[name=Fees]").each(function() {
	   // alert($(this).val());
	Fees=Fees.concat($(this).val()).concat(",");
	    //Data=Data.append($(this).val());
	});	

  Penalty_Others="";
$("input[name=Penalty_Others]").each(function() {
	   // alert($(this).val());
	Penalty_Others=Penalty_Others.concat($(this).val()).concat(",");
	    //Data=Data.append($(this).val());
	});	

  Total_Tax="";
$("input[name=Total_Tax]").each(function() {
	   // alert($(this).val());
	Total_Tax=Total_Tax.concat($(this).val()).concat(",");
	    //Data=Data.append($(this).val());
	});	

  Mode_Of_Deposit="";
$("input[name=Mode_Of_Deposit]").each(function() {
	   // alert($(this).val());
	Mode_Of_Deposit=Mode_Of_Deposit.concat($(this).val()).concat(",");
	    //Data=Data.append($(this).val());
	});	

  Bank_Branch_Code="";
$("input[name=Bank_Branch_Code]").each(function() {
	   // alert($(this).val());
	Bank_Branch_Code=Bank_Branch_Code.concat($(this).val()).concat(",");
	    //Data=Data.append($(this).val());
	});	

  Challan_Serial_No="";
$("input[name=Challan_Serial_No]").each(function() {
	   // alert($(this).val());
	Challan_Serial_No=Challan_Serial_No.concat($(this).val()).concat(",");
	    //Data=Data.append($(this).val());
	});	

  Date_Of_Amount_Deposited="";
$("input[name=Date_Of_Amount_Deposited]").each(function() {
	   // alert($(this).val());
	Date_Of_Amount_Deposited=Date_Of_Amount_Deposited.concat($(this).val()).concat(",");
	    //Data=Data.append($(this).val());
	});	

  Minor_Head_Challan="";
$("input[name=Minor_Head_Challan]").each(function() {
	   // alert($(this).val());
	Minor_Head_Challan=Minor_Head_Challan.concat($(this).val()).concat(",");
	    //Data=Data.append($(this).val());
	});	

 
  Interest_Statement="";
$("input[name=Interest_Statement]").each(function() {
	   // alert($(this).val());
	Interest_Statement=Interest_Statement.concat($(this).val()).concat(",");
	    //Data=Data.append($(this).val());
	});	


  Other_Statement="";
$("input[name=Other_Statement]").each(function() {
	   // alert($(this).val());
	Other_Statement=Other_Statement.concat($(this).val()).concat(",");
	    //Data=Data.append($(this).val());
	});	


 

$('#Submit').prop('disabled', true);

 try {
		
		$.ajax({
			type : "get",
			url : "TDSChallanForm",
			data:{TDS:TDS.replace(/.$/,""),Surcharge:Surcharge.replace(/.$/,""),Education_Cess:Education_Cess.replace(/.$/,""),
				Interest:Interest.replace(/.$/,""),Fees:Fees.replace(/.$/,""),Penalty_Others:Penalty_Others.replace(/.$/,""),
				Total_Tax:Total_Tax.replace(/.$/,""),Mode_Of_Deposit:Mode_Of_Deposit.replace(/.$/,""),Bank_Branch_Code:Bank_Branch_Code.replace(/.$/,""),
				Challan_Serial_No:Challan_Serial_No.replace(/.$/,""),Date_Of_Amount_Deposited:Date_Of_Amount_Deposited.replace(/.$/,""),
				Minor_Head_Challan:Minor_Head_Challan.replace(/.$/,""),
				Interest_Statement:Interest_Statement.replace(/.$/,""),Other_Statement:Other_Statement.replace(/.$/,""),condition:"TDSGEN",
				 },
			success : function(response, textStatus, jqXHR) {
				 
							
				
			//	alert(response);
				
				
			//	 document.getElementById("Search_Scrolling").style.display = "none";
				 var reportName = response;
					window.location.href = "tds/Challan/downloadPrints.jsp?filename="
							+ reportName;
				
				$('#datatable').empty();
				$('#TDS').val('');
				$('#Surcharge').val('');
				$('#Education_Cess').val('');
				$('#Interest').val('');
				$('#Fees').val('');
				$('#Penalty_Others').val('');
				$('#Total_Tax').val('');
				$('#Mode_Of_Deposit').val('C - Challan');
				$('#Bank_Branch_Code').val('');
				$('#Challan_Serial_No').val('');
				$('#Date_Of_Amount_Deposited').val('');
				$('#Minor_Head_Challan').val('200 - TDS payable by taxpayer');  
				$('#Interest_Statement').val('');
				$('#Other_Statement').val('');
				$('#Submit').prop('disabled', false);
					        		 
		 	},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(errorThrown);
				alert("Error;" + textStatus);
				alert("Error;" + errorThrown);
				alert("jqXHR;" + jqXHR.status);
			}
		});
	} catch (err) {
		alert(err + ":at Last");
	} 		
});   


   
   
   
   $('#Download').click(function(e) {
	    

var Payperiod=$('#Payperiod').val();
var fromdate=$('#fromdate').val();
var todate=$('#todate').val();

/*if(Payperiod=="Select"){
	
	alert("Please Select PayPeriod");
	return false;
}else*/
if(fromdate==""){
	alert("Please Select fromdate");
	return false;
}else if(todate==""){
	alert("Please Select todate");
	return false;
}
	  

try {
	   		
	   		$.ajax({
	   			type : "get",
	   			url : "TDSChallanForm",
	   			data:{Payperiod:Payperiod,fromdate:fromdate,todate:todate,condition:"TDSREPORT",
	   				 },
	   			success : function(response, textStatus, jqXHR) {
	   				 
	   	   				
	   				///alert(response);
	   				
	   				if(response==100){
	   					
	   					alert("Connection Problem. Please try again later.....");
	   				}else if(response==101){
	   					
	   					alert("Please Contact Admin...");
	   					
	   				}
	   				
	   				else if(response==500)
	   					{
	   					 alert("Nodata Found");
	   					 
	   					$('#fromdate').val('');
						$('#todate').val(''); 
	   					}
	   				
	   				
	   				else{
	   				
	   				
	   		/*	 document.getElementById("Search_Scrolling").style.display = "none";*/
				 var reportName = response;
					window.location.href = "tds/Challan/downloadPrints.jsp?filename="
							+ reportName;  	
					
					//$('#Payperiod').val('Select');
					$('#fromdate').val('');
					$('#todate').val('');
	   				}
	   					        		 
	   		 	},
	   			error : function(jqXHR, textStatus, errorThrown) {
	   				console.log(errorThrown);
	   				alert("Error;" + textStatus);
	   				alert("Error;" + errorThrown);
	   				alert("jqXHR;" + jqXHR.status);
	   			}
	   		});
	   	} catch (err) {
	   		alert(err + ":at Last");
	   	} 		
	   });   

   
  
   
  /*function Dropdown() {

	   try {
	   		
	   		$.ajax({
	   			type : "get",
	   			url : "TDSChallanForm",
	   			data:{condition:"DropdownPayperiod",},
	   			success : function(response, textStatus, jqXHR) {
	   				 
	   			//	alert(response);
	   				
	   				var dat =  eval(response);
	   			  try {

	   				document.getElementById('Payperiod').length = 0;
	   				var Payperiod_1 = document
	   						.getElementById('Payperiod');

	   				for (var i = 0; i < dat.length; i++) {

	   					myOption = document.createElement("option");
	   					myOption.innerHTML = dat[i].split("#")[0];
	   					myOption.value = dat[i].split("#")[1];
	   					Payperiod_1.appendChild(myOption);

	   				}

	   			} catch (err) {

	   				alert(err.message);
	   			}  
	   					        		 
	   		 	},
	   			error : function(jqXHR, textStatus, errorThrown) {
	   				console.log(errorThrown);
	   				alert("Error;" + textStatus);
	   				alert("Error;" + errorThrown);
	   				alert("jqXHR;" + jqXHR.status);
	   			}
	   		});
	   	} catch (err) {
	   		alert(err + ":at Last");
	   	} 		
   
   }*/
   
   
  
  $('#Reset').click(function(){
	  
		$('#datatable').empty();
		$('#TDS').val('');
		$('#Surcharge').val('');
		$('#Education_Cess').val('');
		$('#Interest').val('');
		$('#Fees').val('');
		$('#Penalty_Others').val('');
		$('#Total_Tax').val('');
		$('#Mode_Of_Deposit').val('C - Challan');
		$('#Bank_Branch_Code').val('');
		$('#Challan_Serial_No').val('');
		$('#Date_Of_Amount_Deposited').val('');
		$('#Minor_Head_Challan').val('200 - TDS payable by taxpayer');  
		$('#Interest_Statement').val('');
		$('#Other_Statement').val('');
		
		
	  
  });
  
  
  $('#fromdate,#todate').change(function(){
	  
	  
	  
	  if($('#fromdate').val()=="")
		  {
		  alert("Please Select From Date");
		  $('#todate').val('');
		  }
	  
	/*  else if($('#todate').val()=="")
		  {
		  alert("Please Select Todate");
		  }*/
	  
  });
  
  $('#ResetDownload').click(function(){
  
	  
	  $('#fromdate,#todate').val('');
	  
  });
  
  
  
/*function FormSubmit()
{
	
	
	var form=document.getElementById("myform");
	var TDS=$('#TDS').val();
	var Surcharge=$('#Surcharge').val();
	var Education_Cess=$('#Education_Cess').val();
	var Interest=$('#Interest').val();
	var Fees=$('#Fees').val();
	var Penalty_Others=$('#Penalty_Others').val();
	var Total_Tax=$('#Total_Tax').val();
	var Mode_Of_Deposit=$('#Mode_Of_Deposit').val();
	var Bank_Branch_Code=$('#Bank_Branch_Code').val();
	var Challan_Serial_No=$('#Challan_Serial_No').val();
	var Date_Of_Amount_Deposited=$('#Date_Of_Amount_Deposited').val();
	var Minor_Head_Challan=$('#Minor_Head_Challan').val();
	var Interest_Statement=$('#Interest_Statement').val();
	var Other_Statement=$('#Other_Statement').val();
	

	var table = document.getElementById('datatable');
	var rowCount = table.rows.length;


	if(TDS!=""&&Surcharge==""){

		alert("Please Fill All Product Details..");
		
		return false;
	} 

	else if(TDS!="" && Surcharge!="" && Education_Cess!="" && Interest!="" && Fees!="" && Penalty_Others!="" 
		&& Total_Tax!="" && Mode_Of_Deposit!="" && Bank_Branch_Code!="" && Challan_Serial_No!="" && Date_Of_Amount_Deposited!="" 
			&& Minor_Head_Challan!="" && Interest_Statement!="" && Other_Statement!=""){
		alert("Please Click on Add Button");
		form.Add.focus();
		return false;
	}else if(rowCount==0)
	{
		alert("Please Enter Atleast one Product details");
		return false;
	}
	else{
		
		
		
		
		$('#myform').submit();
		$('#datatable').empty();
		$('#TDS').val('');
		$('#Surcharge').val('');
		$('#Education_Cess').val('');
		$('#Interest').val('');
		$('#Fees').val('');
		$('#Penalty_Others').val('');
		$('#Total_Tax').val('');
		$('#Mode_Of_Deposit').val('C - Challan');
		$('#Bank_Branch_Code').val('');
		$('#Challan_Serial_No').val('');
		$('#Date_Of_Amount_Deposited').val('');
		$('#Minor_Head_Challan').val('200 - TDS payable by taxpayer');  
		$('#Interest_Statement').val('');
		$('#Other_Statement').val('');

	//	window.location.href="ChallanForm.html"; 
		//DisplayChallanNo();
	}
} */

/*function Delete()
{ 



var sumAmt=0;
var data =document.getElementsByName("Qty").length;
//alert("data::"+data[i]);

for(var i=0;i<data;i++){

//alert("data::"+document.getElementsByName("rate")[i].value);
sumAmt=sumAmt+eval(document.getElementsByName("Qty")[i].value);

}
$('#Total_Qty').html(sumAmt);
document.getElementById("Total_Amount").innerHTML='<input type="hidden" id="t_amount" value='+sumAmt.toFixed(2)+' readonly></input>';
var color = "DC143C";
document.getElementById("Total_print").innerHTML="<font color=#"+color+" size='2'>"+sumAmt.toFixed(2)+"</font>";
}*/

