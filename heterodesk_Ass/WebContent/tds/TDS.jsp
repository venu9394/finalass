<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="java.util.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<P>
<b> Components Title Description :</b>
<p>
TA:Travel Allowance &nbsp;&nbsp; CA:Conveyance Allowance &nbsp;&nbsp; MD:Medical Allowance &nbsp;&nbsp; EA:Education Allowance &nbsp;&nbsp;<br/> SA:Special Allowance &nbsp;&nbsp; PT:Professional Tax &nbsp;&nbsp; OA:Other Allow &nbsp;&nbsp;
<br/><th>EAR</th> <th>+</th>  <th>AB  </th>  <th>-</th> <th> DED </th> <th>=</th>  <th>TN</th> 
EAR:Earnings &nbsp;&nbsp;  AB: Annual Benifits &nbsp;&nbsp; DED: Deductions &nbsp;&nbsp; TN:TotalNet(Credit)/Year

</p>
</P>
<% 
Map map=(Map)request.getAttribute("comptitle");   //Component Titles
ArrayList components=(ArrayList)request.getAttribute("empcomponents");     //total components as per business unit and its pay Structure
ArrayList empids=(ArrayList)request.getAttribute("empids");                // employee ids who is active in the given business unit
Map maxvals=(Map)request.getAttribute("Qry2"); 

// Component Wise Employee Data Sum.


Map ComMod=(Map)request.getAttribute("ComMods");

Map empseq=(Map)request.getAttribute("empseq");



%>
<table> <tr><th>EmpID</th>
  <%
  java.util.Iterator comptitle = components.iterator();
  while(comptitle.hasNext()) {
  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  String componenttitle=(String)map.get(comptitle.next().toString());
	  if(componenttitle.equalsIgnoreCase("Conveyance Allowance")){
		  componenttitle="CA";
	  }else if(componenttitle.equalsIgnoreCase("Travel Allowance")){
		  componenttitle="TA";
	  }else if(componenttitle.equalsIgnoreCase("Medical Allowance")){
		  componenttitle="MA";
	  }else if(componenttitle.equalsIgnoreCase("Education Allowance")){
		  componenttitle="EA";
	  }else if(componenttitle.equalsIgnoreCase("Special Allowance")){
		  componenttitle="SA";
	  }else if(componenttitle.equalsIgnoreCase("Professional Tax")){
		  componenttitle="PT";
	  }else if(componenttitle.equalsIgnoreCase("Other Allow")){
		  componenttitle="OA";
	  }
		  
  
  %>
            <th><%= componenttitle %></th>

  <% }%>
    <th>EEAR</th> <th>-</th> <th>EDED</th> <th>=</th> <th>ENET</th>
    <th>EAR</th> <th>+</th>  <th>AB  </th>  <th>-</th> <th> DED </th> <th>=</th>  <th>TN</th>  <th>TotalNet/Annual</th> 
  </tr>
<%
java.util.Iterator empid= empids.iterator();
//java.util.Iterator compo = components.iterator();
String empid1=null;
double Earnings=0,Deductions=0,annualBe=0;

double FEarnings=0,FDeductions=0,FannualBe=0;


while(empid.hasNext()) {
	empid1=empid.next().toString();
	java.util.Iterator compo = components.iterator();
	Earnings=0;
	Deductions=0;
	annualBe=0;
%>
   <tr><td align='center' ><%= empseq.get(empid1) %></td>
   
   <% while(compo.hasNext()) { 
   
	 String componenTy=(String)compo.next().toString();
	 
	 System.out.println("componenTy::"+componenTy);
	 
    String loaddata=(String)maxvals.get(empid1+"-"+componenTy);
    
    String Floaddata=(String)maxvals.get(empid1+"-"+componenTy+"-FR");
    
    System.out.println("loaddata::"+loaddata);
    
    String mode=(String)ComMod.get(componenTy+"-M");
    
    String mode1=mode;
    System.out.println("mode::"+mode);
    if(Floaddata==null ){
    	Floaddata="0";
    }
    if(loaddata==null ){
    	loaddata="0";
    }
    
   if(mode.equalsIgnoreCase("E") && Floaddata.equalsIgnoreCase("22")){
    	 
    	 Earnings=Earnings+Double.parseDouble(loaddata); 
    	 
    	 System.out.println("Earnings::"+Earnings);
    }else if(mode.equalsIgnoreCase("D")){
   	 
    	Deductions=Deductions+Double.parseDouble(loaddata); 
   	 
   	 System.out.println("Earnings::"+Deductions);
   }else if(mode.equalsIgnoreCase("O")){
	   	 
	      annualBe=annualBe+Double.parseDouble(loaddata); 
	   	 
	   	 System.out.println("Earnings::"+Deductions);
	   }  
    //   FR Caliculation
    
   if(mode1.equalsIgnoreCase("E") && componenTy.equalsIgnoreCase("22") ){
  	 
  	 FEarnings=FEarnings+Double.parseDouble(Floaddata); 
  	 
  	 System.out.println("FEarnings::"+FEarnings);
  }else if(mode1.equalsIgnoreCase("D")){
 	 
  	FDeductions=FDeductions+Double.parseDouble(Floaddata); 
 	 
 	 System.out.println("FDeductions::"+FDeductions);
 }else if(mode1.equalsIgnoreCase("O")){
	   	 
	      annualBe=annualBe+Double.parseDouble(loaddata); 
	   	 
	   	 System.out.println("Earnings::"+Deductions);
	   } 
    
   %>
       
      
       
     <td align='right' >  <%= loaddata %></td>
     
   
    <%}%>
       
       <td align='right'> <%= FEarnings %></td><td>-</td><td align='right'> <%= FDeductions %> </td> <td align='center' ><b>=</b></td><td align='right' > <%= FEarnings-FDeductions %> </td>
       
       <td align='right'> <%= Earnings %> </td>  <td style="color:red" align='center'> <b>+</b> </td>  <td align='right' > <%= annualBe %> </td> <td style="color:green"  align='center' ><b>-<b/></td><td align='right'> <%= Deductions %> </td> <td  align='center' ><b>=<b/></td> <td align='right'> <%=(Earnings+annualBe)-Deductions %> </td>
       <td align='right'> <%= (((Earnings+annualBe)-Deductions)+(FEarnings-FDeductions))%> </td>
   
   </tr>


<% } %>

</table>
</body>
</html>