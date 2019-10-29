<!DOCTYPE html>
<html>
<head>
<%@ page import="java.util.*" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>

<%@page import="java.util.*" %>
<%
Random rand = new Random();
int nRand = rand.nextInt(90000) + 10000;
System.out.println(nRand);
%>

<% 
	ArrayList components=new ArrayList();
	HashMap   empmap=new HashMap();
	//session.setAttribute("TbInfo ", components);
	//session.setAttribute("TbData", empmap);
%>
<script>

function AjexCall(val,val1,h1,h2,h3){
	    alert("H");
	//var postData = document.getElementById("keyid").value;
	var formData = {id:""+val+"",tableid:""+val1+"",Header1:""+h1+"",Header2:""+h2+"",Header3:""+h3+"",RanDm:"<%=nRand%>"};
	//var formData = {id:"venu" };
try{
    $.ajax({
          type: "post",
          url: "ajexPackage",
          data: formData,
          success: function(responseData, textStatus, jqXHR) {
              alert(responseData);
              var resp=eval(responseData);
             /*document.getElementById("name").value=resp[1];
              document.getElementById("desi").value=resp[2];
              document.getElementById("dept").value=resp[3];
              document.getElementById("addrs").value=resp[4]+resp[5];
              document.getElementById("mobile").value=resp[6];
              document.getElementById("age").value=resp[7];
               */
          },
          error: function(jqXHR, textStatus, errorThrown) {
              console.log(errorThrown);
              alert("Error;");
          }
      })
}catch(err){
	 alert(err.value);
}
	
	}

</script>
<style>


</style>
</head>
<body >

<p>MultiRow Sample</p>
<p>Header1 <input type="text" id="heder1" value="Header 1" > &nbsp; Header2  <input type="text" id="heder2" value="Header 2" > 
Header2  <input type="text" id="heder3" value="Header 3" >
</p>

<p style="margin-left:120px"><button id="addbut" onclick="myFunction()">ADD TABLE</button>&nbsp;<button onclick="DeleteMain()">DELETE</button></p>

<table  id="myTable"  width='40%'>
</table>

<!-- <table  id="myTable" width='30%'>
<tr> 
<td >
<table id="0" border='1' width='100%' >
  <tr>
    <td>Row1 cell1</td>
    <td>Row1 cell2</td>
  </tr>
  <tr>
    <td>Row2 cell1</td>
    <td>Row2 cell2</td>
  </tr>
  <tr>
    <td>Row3 cell1</td>
    <td>Row3 cell2</td>
  </tr>
</table> -->




<script>
/*
var table = $('#myTable').DataTable();
 
$('#myTable').on( 'click', 'tr', function () {
    var id = table.row( this ).id();
 
    alert( 'Clicked row id '+id );
} );*/
var AddSub=0;
function myFunction() {
 
       AddSub=AddSub+1;
    var table = document.getElementById("myTable");
	var rows=table.rows.length;
	 //  alert(rows);
    var row = table.insertRow(0);
    var cell1 = row.insertCell(0);
	
	var header1=document.getElementById("heder1").value;
	var header2=document.getElementById("heder2").value;
	var header3=document.getElementById("heder3").value;
	
	if(header1.length!=0 && header2.length!=0 && header3.length!=0 ){
    //var cell2 = row.insertCell(1);
    cell1.innerHTML = "<table id='"+AddSub+"' border='1' width='100%' ><tr><td align='center' colspan='3'><input type='button' value='ADD' onclick='myADD1("+AddSub+")'>&nbsp;<input type='button' value='Delete' onclick='Delete("+AddSub+")'>&nbsp;<input type='button' value='Save' onclick='SaveTb("+AddSub+")'></td></tr>  <tr><th>Title 1</th><th>Title 2</th><th>Title 3</th></tr><tr style='background-color:red'> <td ><span id='H_"+AddSub+"'>"+header1+"</span></td><td > <span id='HH_"+AddSub+"'> "+header2+" </span> </td>  <td ><span id='HHH_"+AddSub+"' >"+header3+" </span></td> </tr> <tr><th>Proposed</th><th>Existing</th><th>Remark</th></tr></table>";
    //cell2.innerHTML = "NEW CELL2";
	document.getElementById("addbut").disabled=true;
	}else{
	    alert("Please Fill The All Header Fields..!");
	}
	
}
function SaveTb(val){
    var length=0;
	var FrmData="myfrm:0";
	var FrmDataTemp="myfrm:0";
    var table = document.getElementById(""+val+"");
	var row=table.rows.length;
	var savflg=true;
	var rowCn=table.rows.length;
	var FHeader="";
	var SHeader="";
	var THeader="";
	   try{
	 	FHeader=document.getElementById("H_"+val+"").innerHTML;
	 	SHeader=document.getElementById("HH_"+val+"").innerHTML;
	 	THeader=document.getElementById("HHH_"+val+"").innerHTML;
	  }catch(err){
		  alert(err.value);
	  } 
	alert("FHeader"+FHeader);
	 if(row>4){
	 for(var i=4;i<row;i++){
	
	try{
	var id1=val.toString()+"#frow#"+i.toString();
	var id2=val.toString()+"#srow#"+i.toString();
	var id3=val.toString()+"#trow#"+i.toString();
	
	var field1=document.getElementById(""+id1+"").value;
	var field2=document.getElementById(""+id2+"").value;
	var field3=document.getElementById(""+id3+"").value;
	if(field1.length==0 && field2.length==0 && field3.length==0){
	    alert("Please Fill The All Columns Before Save ..!");
		savflg=false;
		return false;
	}else{
	  length=i;
	    if(i==4){
	    	 FrmData=field1+"#"+field2+"#"+field3;
	    }else{
	    FrmData=FrmData+"~"+field1+"#"+field2+"#"+field3;
	    }
	 // FrmData=FrmData+","+val+"-"+length+":"+field1+"#"+field2+"#"+field3;
	  document.getElementById(""+id1+"").readOnly=true;
	  document.getElementById(""+id2+"").readOnly=true;
	  document.getElementById(""+id3+"").readOnly=true;
	}
	}catch(err){
	   
	   alert(err.value);
	 }
	}
   }else{
      savflg=false;
      alert("Please Add Minumem One Row To Save Data ..!");
   }
	if(savflg==true){
	alert("FrmData:" +FrmData);
	AjexCall(FrmData,val,FHeader,SHeader,THeader);
   document.getElementById("addbut").disabled=false;
   }
   
}
function myFunction1111() {
    var firstRow = document.getElementById("myTable").rows[0];
    firstRow.remove(1);
}
 
function myADD() {
    var table = document.getElementById(""+AddSub+"");
	var rows=table.rows.length;
	   alert(rows);
    var row = table.insertRow(rows);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    cell1.innerHTML = "VENU";
    cell2.innerHTML = "VENU1";
}

function myADD1(val) {
  
        //alert("myADD1");
	var flag=false;
    var table = document.getElementById(""+val+"");
	var row=table.rows.length;
	var rowCn=table.rows.length;
	   //alert(row);
	var id1=val.toString()+"#frow#"+row.toString();
	var id2=val.toString()+"#srow#"+row.toString();
	var id3=val.toString()+"#trow#"+row.toString();
	 if(row>4){
	var fid1=val.toString()+"#frow#"+(eval(row.toString())-1).toString();
	var fid2=val.toString()+"#srow#"+(eval(row.toString())-1).toString();
	var fid3=val.toString()+"#trow#"+(eval(row.toString())-1).toString();
	//alert("fid1:" + fid1);
	var field1=document.getElementById(""+fid1+"").value;
	//alert("field1:" + field1);
	var field2=document.getElementById(""+fid2+"").value;
	var field3=document.getElementById(""+fid3+"").value;
	//alert("field1::" +field2);
	if(field1.length!=0 && field2.length!=0 && field3.length!=0){
	  flag=true;
	}
	
	}
	//alert(flag +"~"+ row);
	if(row==4 && flag==false){
	   //  alert("Add1 in if");
		var row1 = table.insertRow(rowCn);
		var cell1 = row1.insertCell(0);
		var cell2 = row1.insertCell(1);
		var cell3 = row1.insertCell(2);
		cell1.innerHTML = "<input type='text' name="+id1+" id="+id1+" value=''  />";
		cell2.innerHTML = "<input type='text' name="+id2+" id="+id2+" value=''  />";
		cell3.innerHTML = "<input type='text' name="+id3+" id="+id3+" value=''  />";
	
	}else if(row!=4 && flag==true){
	 
	     // alert("Add1 in else if");
    var row2 = table.insertRow(rowCn);
    var cell1 = row2.insertCell(0);
    var cell2 = row2.insertCell(1);
	var cell3 = row2.insertCell(2);
	cell1.innerHTML = "<input type='text' name="+id1+" id="+id1+" value=''  />";
    cell2.innerHTML = "<input type='text' name="+id2+" id="+id2+" value=''  />";
	cell3.innerHTML = "<input type='text' name="+id3+" id="+id3+" value=''  />";
	}else{
	      alert("Please Fill The Columns");
	}
   // cell1.innerHTML = "&nbsp;<input type='text' name='"+table+"-"+frow+"-"+rows+"' value='0' style='height:30px;' >";
   // cell2.innerHTML = "&nbsp;<input type='text' name='"+table+"-"+srow+"-"+rows+"' value='0'  style='height:30px;'>";
}

function Delete(val) {
		var tables = document.getElementById(""+val+"");
		var row=tables.rows.length;
	   if(row!=4){
	      alert("in IF");
		  try{
	      tables.deleteRow(row-1);
		  }catch(err){
		   alert(err.value);
		  }
	  }else{
	   alert("No Records...!")
	  }
	 
}

function DeleteMain() {
    var tables = document.getElementById("myTable");
	var row=tables.rows.length;
	try{
	tables.deleteRow(0);
	}catch(err){
	}
}

</script>

</body>
</html>
