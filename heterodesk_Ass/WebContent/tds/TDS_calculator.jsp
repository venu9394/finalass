<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TDS Calculator</title>
</head>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.9/jquery-ui.js" type="text/javascript"></script>
<link href="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.9/themes/blitzer/jquery-ui.css"
    rel="stylesheet" type="text/css" />
<script type="text/javascript">
    $(function () {
    	
    	
    	
        $("#dialog").dialog({
            modal: true,
            autoOpen: false,
            title: "TDS Calculator",
            width: 520,
            height: 420
        });
        
        
        $("#btnShow").click(function () {
        	
        	
            $('#dialog').dialog('open');
            
        });
        
       // $(window).unbind('scroll');
    });
    
   
    
    
</script>


<script type="text/javascript">


function calculate()
{
var result = document.getElementById('result');
var resultfinal = document.getElementById('resultfinal');


var a1 = document.getElementById('a1');
var a2 = document.getElementById('a2');
var a3 = document.getElementById('a3');
var v2 = parseInt(document.getElementById('v2').value);
var v3 = parseInt(document.getElementById('v3').value);
var v4 = parseInt(document.getElementById('v4').value);
var v5 = parseInt(document.getElementById('v5').value);
var v6 = parseInt(document.getElementById('v6').value);
var el, i = 2, total = 0; 

var deduc = 0, totalfinal1=0;
	var r1 = 0 ,r2 = 0,r3 = 0,r4 = 0,r5 = 0;
	
	  total = v2 + v5;
	  deduc = v3 + v4 + v6;
	  totalfinal1 = total - deduc ;

	  result.value = totalfinal1;
	
	  
	  if(result.value <= 250000)
		  
{
  	alert("Not Eligible For TAX");
	  	
}	  
		  
	  else{
		  
	  
	if (a1.checked)
	{
		
		alert("CORRECT a1");



             if(document.getElementById('v2').value =="" && document.getElementById('v3').value =="" && document.getElementById('v4').value =="" && document.getElementById('v5').value =="" && document.getElementById('v6').value =="")
             {
                   result.value ="";
                  }
           
             
             

    if(result.value > 250000 && result.value <= 500000 )
{
	
	alert("CORRECT A  Calllllll");
	
	
	
r1 = totalfinal1 - 250000;
r2 = r1 * 0.1;
r3 = r2 - 5000;
r4 = r3 * (3/100);
r5 = r3 + r4;
resultfinal.value = r5; 
}


else if(result.value > 500000 && result.value <= 1000000 )
{
	
	
	alert("CORRECT A 222222222");
r1 = totalfinal1 - 500000;
r2 = (r1 * 0.2) + 25000;
//r3 = r2 - 5000;
r4 = r2 * (3/100);
r5 = r2 + r4;
resultfinal.value = r5; 
}


else if(result.value > 1000000 && result.value <= 100000000 )
{
	
	alert("CORRECT A 3333333");
r1 = totalfinal1 -1000000 ;
r2 = (r1 * 0.3) + 125000;
//r3 = r2 - 5000;
r4 = r2 * (3/100);
r5 = r2 + r4;
resultfinal.value = r5; 
}
  
	
} 

 
	 if(a2.checked){
		
		alert("CORRECT A2");
		
		var abc = true;

		if(document.getElementById('v2').value =="" && document.getElementById('v3').value =="" && document.getElementById('v4').value =="" && document.getElementById('v5').value =="" && document.getElementById('v6').value =="")
		{
		 result.value ="";
		 
		 
		}

		
	

	    if(result.value > 300000 && result.value <= 500000 )
		{
	    	
	    	alert("CORRECT A2  111111");
		r1 = totalfinal1 - 250000;
		r2 = r1 * 0.1;
		r3 = r2 - 5000;
		r4 = r3 * (3/100);
		r5 = r3 + r4;
		resultfinal.value = r5; 
		}


		else if(result.value > 500000 && result.value <= 1000000 )
		{
			
			alert("CORRECT A2 222222222");
		r1 = totalfinal1 - 500000;
		r2 = (r1 * 0.2) + 25000;
		//r3 = r2 - 5000;
		r4 = r2 * (3/100);
		r5 = r2 + r4;
		resultfinal.value = r5; 
		}


		else if(result.value > 1000000 && result.value <= 100000000 )
		{
			
			alert("CORRECT A2  33333333");
		r1 = totalfinal1 -1000000 ;
		r2 = (r1 * 0.3) + 125000;
		//r3 = r2 - 5000;
		r4 = r2 * (3/100);
		r5 = r2 + r4;
		resultfinal.value = r5; 
		}
		
		
	}
	
    if(a3.checked){
		alert("CORRECT A3");
		
		

		if(document.getElementById('v2').value =="" && document.getElementById('v3').value =="" && document.getElementById('v4').value =="" && document.getElementById('v5').value =="" && document.getElementById('v6').value =="")
		{
		 result.value ="";
		}

		
		
		result.value = totalfinal1;



		 if(result.value > 500000 && result.value <= 1000000 )
		{
			 
			 alert("CORRECT A 3 1111111");
		r1 = totalfinal1 - 500000;
		r2 = (r1 * 0.2) + 25000;
		//r3 = r2 - 5000;
		r4 = r2 * (3/100);
		r5 = r2 + r4;
		resultfinal.value = r5; 
		}


		else if(result.value > 1000000 && result.value <= 100000000 )
		{
			alert("CORRECT A3  222222222");
		r1 = totalfinal1 -1000000 ;
		r2 = (r1 * 0.3) + 125000;
		//r3 = r2 - 5000;
		r4 = r2 * (3/100);
		r5 = r2 + r4;
		resultfinal.value = r5; 
		}
		
	
		
}
    
	  }
}


	
</script>
<style>
.tooltip {
    position: relative;
    display: inline-block;
    border-bottom: 1px dotted black;
}

.tooltip .tooltiptext {
    visibility: hidden;
    width: 120px;
    background-color: green;
    color: #fff;
    text-align: center;
    border-radius: 10px;
    padding: 9px 0;

    /* Position the tooltip */
    position: absolute;
    z-index: 1;
}

.tooltip:hover .tooltiptext {
    visibility: visible;
}


 #ac-wrapper {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(255,255,255,.6);
        z-index: 1001;
    }
    




</style>


<body>


<input type="button" id="btnShow" value="Calculate" />
<div id="dialog" style="display: none" align = "center">
<table><tr><td>Assessment Year:</td><td><input type="text" id ="v0"  readonly value="2016-2017"></td><td class="tooltip">?
  <span class="tooltiptext">Tooltip xcfvgbdxbvxnbvncbv
  
  </span></td></tr>
<tr><td>Employee Age:</td><td>
  <input type="radio" name="age"  id ="a1" value="a1" checked>Age < 60<br>
  <input type="radio" name="age" id ="a2" value="a2">Age > 60<br>
  <input type="radio" name="age" id ="a3" value="a3">Age > 80</td></tr> 

<tr><td>
Gross Salary:</td><td><input type="text" id ="v2" ></td>
<td class="tooltip">?
  <span class="tooltiptext">Tooltip xcfvgbdxbvxnbvncbv
  
  </span>
</td></tr>
<tr><td>
Exemptions:</td><td> <input type="text" id="v3" ></td>
<td class="tooltip">?
  <span class="tooltiptext">Tooltip xcfvgbdxbvxnbvncbv
  
  </span></td></tr>
<tr><td>
Sec 16 deductions :</td><td><input type="text" id="v4" ></td>
<td class="tooltip">?
  <span class="tooltiptext">Tooltip xcfvgbdxbvxnbvncbv
  
  </span></td></tr>
<tr><td>
Other source of income :</td><td><input type="text" id="v5" ></td>
<td class="tooltip">?
  <span class="tooltiptext">Tooltip xcfvgbdxbvxnbvncbv
  
  </span></td></tr>
<tr><td>
Chapter VIA Deductions :</td><td><input type="text" id="v6"></td>
<td class="tooltip">?
  <span class="tooltiptext">Tooltip xcfvgbdxbvxnbvncbv
  
  </span></td></tr>
<tr><td><input type="button"  onclick="calculate()" value="Calculate"></td><td><input type="text" id="result" readonly ></td>
</tr>

<tr><td>
<b>Final TDS Amount :</b></td>
<td><input type="text" id="resultfinal" readonly></td></tr>
</table> 

</div>
</body>
</html>

