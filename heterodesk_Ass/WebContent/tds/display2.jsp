<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script>
function CallRad(val){
  var id=val+"g";
  alert("Total Gross Value::" +document.getElementById(val+'g').innerHTML );
}

</script>
<title>Insert title here</title>
<% 
Map Basics=(Map)request.getAttribute("Basic");
Map FinalComponents=(Map)request.getAttribute("FinalComponents");

ArrayList empids=(ArrayList)request.getAttribute("empids");
java.util.Iterator empseq = empids.iterator();


%>
</head>
<body>

<div >


</div>


<table border='1' >
 <tr><th>Check</th> <th> ID</th> <th>Basic</th> <th>CA</th> <th>HRA</th> <th>Medical</th> <th>Education</th> <th>PT(Dedu)</th> <th>Gross</th> <th>FGross</th>  <th>Income</th> 
  <th>TaxbleIncome</th>
 </tr>
  <% while(empseq.hasNext()){
	String empid= empseq.next().toString(); 
	Double gross=0.0 , pt=0.0 , income=0.0 ;
	gross=Double.parseDouble(Basics.get(empid+".gross").toString());
	pt=Double.parseDouble(Basics.get(empid+".Pt").toString());
	income=gross-pt;
	System.out.println("Income :: "+income);
  %>
   <tr>
 
 <td> <input type='checkbox' name="<%=empid %>" onclick="CallRad('<%=empid %>')" /> </td> 
 <td  > <%=empid %> </td>
 <td id="<%=empid+"b" %>" > <%=Basics.get(empid+".basic") %>  </td>
 <td id="<%=empid+"c" %>" > <%=Basics.get(empid+".Ca") %>  </td>
 <td id="<%=empid+"h" %>" > <%=Basics.get(empid+".Hra") %>  </td>
 <td id="<%=empid+"m" %>" > <%=Basics.get(empid+".Medical") %>  </td>
 <td id="<%=empid+"e" %>" > <%=Basics.get(empid+".Education") %>  </td>
  <td id="<%=empid+"p" %>" > <%=Basics.get(empid+".Pt") %>  </td> 
  
  <td id="<%=empid+"g" %>" > <%=Basics.get(empid+".gross") %>  </td>
  
 <td id="<%=empid+"g" %>" > <%=FinalComponents.get(empid+".gross") %>  </td>

 <td id="<%=empid+"i" %>" > <%=income %>  </td>
 
 <td id="<%=empid+"tx" %>" >  </td> 
</tr>




 <%}%> 
 

 <table>
</body>
</html>