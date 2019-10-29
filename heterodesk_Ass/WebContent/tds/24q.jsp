
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.DecimalFormat,java.text.NumberFormat" %>

<html>
<head>
<style>
table {
  border-collapse: collapse;
}

 th {
  border: 1px solid Black;
   font-size: 12px;
    font-family: sans-serif;
    font-weight:600;
}
td{

  border: 1px solid Black;
   font-size: 12px;
    font-family: sans-serif;
    font-weight:500;

}
.emp-t{
    padding: 8px 54px;
}
.emp-name-t{
    padding: 0px 50px;
}
/* Ensure that the demo table scrolls */
    th, td { white-space: nowrap; }
    div.dataTables_wrapper {
        width: 1500px;
        margin: 0 auto;
    }
	th,td{
	text-align:center;
	}
</style>

<link rel="stylesheet" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.2.4/css/buttons.dataTables.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/fixedcolumns/3.2.2/css/fixedColumns.dataTables.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/fixedcolumns/3.2.2/css/fixedColumns.bootstrap.min.css">


<% 
Map Basics = (Map) session.getAttribute("Basic");
Map FinalComponents = (Map) session.getAttribute("FinalComponents");
Map TaxException = (Map) session.getAttribute("TaxException");
Map TaxSections = (Map) session.getAttribute("TaxSections");
ArrayList empids = (ArrayList) session.getAttribute("empids");
ArrayList taxempids = (ArrayList) session.getAttribute("taxempids");
String ProposedMonths = (String) session.getAttribute("ProposedMonths");



int Total_Months=0;
java.util.Iterator empseq = taxempids.iterator();

//java.util.Iterator empseq = empids.iterator();

%>

</head>
<body>

<table id="example" class="" cellspacing="0" width="100%">
  <thead>
    <tr>
      <th rowspan="3">Sno</th>
      
      
       
      <th rowspan="2">PAN of Employee</th>
      <th rowspan="2">PAN Ref. No.</th>
	  <th rowspan="2" class="emp-name-t">Name of the Employee</th>
	  <th rowspan="2">Employee Designation</th>
	  <th rowspan="2">Select W - Woman, S - Senior Citizen & G - Others<br/> ( 'O' for super senior citizen <br/> (applicable from FY 2011-12 onwards) )</th>
	  <th colspan="2" class="emp-t">Employment Period with <br/> Current Employer during FY</th>
	  <th colspan="5">Current Employer Gross Salary <br/>  (Only for autofilling Form 16 <br/> from 24Q, Not required for e-TDS )</th>
	  <th rowspan="2">Taxable Amount on which tax <br/> is deducted by the current employer</th>
	  <th rowspan="2">Taxable Amount on which <br/> tax is deducted by previous employer(s)</th>
	  <th rowspan="2">Total amount of salary (See Note 1) ( 335+336)</th>
	  <th rowspan="2">Total deduction <br/>under section 16(ii)</th>
	  <th rowspan="2">Total deduction<br/> under section 16(iii)</th>
	  <th rowspan="2">Total deductions <br/>u/s 16(ii) & 16(iii)</th>
	  <th rowspan="2">Income chargeable under the <br/>  head "Salaries" (337 - (338 + 339))</th>
	  <th rowspan="2">Income (including admissible<br/> loss from house property)<br/>  under any head other than the head <br/> "Salaries" offered for TDS [sec192(2B)]</th>
	  <th rowspan="2">Gross total income (Total of Columns 340 and 341)</th>
	  <th rowspan="2">Aggregate amount of deductions <br/>  admissible u/s 80C, 80CCC,and 80CCD<br/>  (1)<br/> (Total to be limited to <br/>amount specified u/s 80CCE)</th>
	  <th rowspan="2">Details of 80CCE</th>
	  <th rowspan="2">80CCG</th> 
	  <th rowspan="2">Amount deductible under any <br/> other provision(s) of Chapter VI-A</th>
	  <th rowspan="2">Other Chap-VIA Details</th>
	  <th rowspan="2">Total amount deductible under <br/> Chapter VI-A (Total of columns 343 and 344)</th>
	  <th rowspan="2">Total taxable income <br/> (Column 342 minus 345)</th>  
	  <th rowspan="2">Income tax on total income<br/>  (After Rebate u/s 87A)</th>
	  <th rowspan="2">Surcharge</th>
	  <th rowspan="2">Education cess</th>
	  <th rowspan="2">Income-tax relief under section 89,<br/>  when salary, etc. is paid <br/> in arrear or advance</th>
	  <th rowspan="2">Net tax payable <br/> (347 + 348 + 349 - 350)</th>
	  <th rowspan="2">Total amount of tax deducted<br/>  at source by the current <br/> employer for the whole year.</th>
      <th rowspan="2">Reported amount of tax deducted <br/> at source by previous <br/> employer(s)/deductor(s)</th>
      <th rowspan="2">Total amount of tax deducted at<br/>  source for the whole year <br/> (Total of columns 352,353 and I)</th>
      <th rowspan="2">Shortfall in tax deduction (+)/Excess<br/>  tax deduction (-)<br/>  [Column 351 - 354]</th>
      <th rowspan="2">Whether tax deducted <br/> at higher rate due to non <br/> furnishing of PAN by deductee</th>
      <th rowspan="2">PAN Flag</th>
      <th rowspan="2">Rebate u/s 87A</th>
      <th rowspan="2">Whether contributions paid<br/>  by the trustees of <br/> an Approved Superannuation Fund</th>
      <th rowspan="2">Name of the superannuation fund</th>
      <th rowspan="2">Date from which the Employee <br/> has contributed to <br/> the Superannuation Fund</th>
      <th rowspan="2">Date to which the Employee has <br/> contributed to the<br/>  Superannuation Fund</th>
      <th rowspan="2">The amount of contribution <br/> repaid on account of principal <br/> and interest from Superannuation Fund</th>
      <th rowspan="2">The Average Rate of Deduction<br/>  of Tax during the <br/> Preceding Three Years</th>
      <th rowspan="2">The Amount of Tax Deducted on<br/>  Repayment of <br/> Superannuation Fund</th>
      <th rowspan="2">Gross Total Income including <br/> Contribution repaid on Account <br/>of Principal and interest <br/> from Superannuation Fund</th>
			
			 
    </tr>
    <tr>
      <th>Date From</th>
      <th>Date To</th>
      <th>Salary as per provisions <br/> contained in section 17(1)</th>
      <th>Value of perquisites under <br/> section 17(2) as per Form No. 12BA</th>
	  <th>Profit in liue of salery under <br/> section 17(3) as per Form No. 12BA</th>
	  <th>Less: Allowance to the <br/> extent exempt u/s 10</th>
	  <th>U/s 10 Details</th>
	  </tr>
	  
	 <tr>
	 <th>331</th>
	 <th>331</th>
	 <th>332</th>
	 <th>.</th>
	 <th>333</th>
	 <th>334</th>
	 <th>334</th>
	 <th>A</th>
	 <th>B</th>
	 <th>C</th>
	 <th>D</th>
	      <th>.</th>
	 <th>335</th>
	 <th>336</th>
	 <th>337</th>
	 <th>338</th>
	 <th>339</th>
	 <th>.</th>
	 <th>340</th>
	 <th>341</th>
	 <th>342</th>
	 <th>343</th>
	 <th></th>
	 <th>343(a)</th>
	 <th>344</th>
	 <th>.</th>
	 <th>345</th>
	 <th>346</th>
	 <th>347</th>
	 <th>348</th>
	 <th>349</th>
	 <th>350</th>
	 <th>351</th>
	 <th>352</th>
	 <th>353</th>
	 <th>354</th>
	 <th>355</th>
	 <th>356</th>
	 <th>.</th>
	 <th></th>
	 <th></th>
	 <th></th>
	 <th></th>
	 <th></th>
	 <th></th> <th></th> 
	 <th></th>
	 <th></th> 
	 </tr>
  </thead>
  <tbody>
  
  <% 
  NumberFormat formatter = new DecimalFormat("#0.00");
    int SNO=1;
    while(empseq.hasNext()){
    	
    	response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachement; filename=" + "24Q.xls");
		
	 String empid= empseq.next().toString(); 
	 Double gross=0.0 , pt=0.0 , income=0.0 ;
	 gross=Double.parseDouble(Basics.get(empid+".gross").toString());
	 pt=Double.parseDouble(Basics.get(empid+".Pt").toString());
	 income=gross-pt;
	 Total_Months=(Integer.parseInt(Basics.get(empid+".pm").toString()))+(Integer.parseInt(ProposedMonths));
		
	 Double FinalTax_Month=0.00;
	// System.out.println("Income :: "+income);
	if(Integer.parseInt(ProposedMonths)!=0){
	
	    FinalTax_Month=(((Double.parseDouble(TaxSections.get(empid+"ANN_tx_Paid").toString() ))-(Double.parseDouble(TaxSections.get(empid+"_TDS_F").toString()))) / Integer.parseInt(ProposedMonths));
	}else{
		
		FinalTax_Month=(((Double.parseDouble(TaxSections.get(empid+"ANN_tx_Paid").toString() ))-(Double.parseDouble(TaxSections.get(empid+"_TDS_F").toString()))));
	}
	
	
	double TaxDue_pm_ann=(Double.parseDouble(TaxSections.get(empid+"ANN_tx_Paid").toString() ))-Double.parseDouble(TaxSections.get(empid+"_TDS_F").toString());
	 
  %>
    <tr>
    <td><%=SNO%></td>
      <td><%=Basics.get(empid+"_EMP_PROF_PAN") %> </td>
      <td></td>
      <td><%=Basics.get(empid+".callname") %></td> <!--Name of the employee  -->
      
      <td><%=Basics.get(empid+"_EMP_PROF_DESIG") %></td>
      
      <td><%=Basics.get(empid+"_EMP_PROF_GENDER") %></td>
      
      <td ><%=Basics.get(empid+"_TAX_CAL_FRM") %></td>
      
      <td><%=Basics.get("_TAX_CAL_TO") %></td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>Details</td>
      
      <td><%=formatter.format(Double.parseDouble(TaxException.get(empid+".ANN_FinalIncome_B").toString())) %></td>  <!--Taxable amount which is deducted standards   -->
      
      <td>0</td><!-- old employear Salary  -->
      <td><%=formatter.format(Double.parseDouble(TaxException.get(empid+".ANN_FinalIncome_B").toString())) %></td>
      
      <td>0</td>
      <td><%=formatter.format(Double.parseDouble(FinalComponents.get(empid+"-ANN_Pt").toString())) %></td>
      <td><%=formatter.format(Double.parseDouble(FinalComponents.get(empid+"-ANN_Pt").toString())) %></td>
      <td><%=formatter.format(Double.parseDouble(TaxException.get(empid+".ANN_FinalIncome_B").toString())) %></td>
      <!-- up to deduct PT -->
      <td><%=formatter.format((Double.parseDouble(TaxSections.get(empid+"INHR").toString())+Double.parseDouble(TaxSections.get(empid+"_OTHERINCOME").toString()))-Double.parseDouble(TaxSections.get(empid+"_RENTINTEREST_E").toString()))    %></td>
      <!-- in come from other -->
      
      <!--After Adding the other income  -->
      <td><%=formatter.format(Double.parseDouble(TaxException.get(empid+".ANN_LEXAMT").toString())) %></td>
      
      <td><%=formatter.format(Double.parseDouble(TaxSections.get(empid+"_80C_E_y").toString()))%></td>
      <!-- 80 C Eligible Amount  -->
      <td>Details</td>
      <td><%=formatter.format(Double.parseDouble(TaxSections.get(empid+"S_80G_E").toString()))%></td>
      <td><%=formatter.format(Double.parseDouble(TaxSections.get(empid+"_80D_E").toString()))%></td>
      <td>Details</td>
      <td>
   <%=formatter.format(Double.parseDouble(TaxSections.get(empid+"_80C_E_y").toString())+Double.parseDouble(TaxSections.get(empid+"S_80G_E").toString())+Double.parseDouble(TaxSections.get(empid+"_80D_E").toString()))%>
     </td>
      <td> <%=formatter.format(Double.parseDouble(TaxSections.get(empid+"ANN_TxamT").toString())) %></td>
      <!--  above is Taxble Amount -->
      <td><%=formatter.format(Double.parseDouble(TaxSections.get(empid+"_ANN_Emp_Tax_E").toString()))%></td>
      <td><%=formatter.format(Double.parseDouble(TaxSections.get(empid+"_ANN_SURCHARGE").toString()))%></td>
      <td><%=formatter.format(Double.parseDouble(TaxSections.get(empid+"ANN_Educess").toString()))%></td>
      <td> <%= formatter.format(Double.parseDouble(TaxSections.get(empid+"_ANN_Before_Dedu_Relif").toString()))  %></td>
      <td><%= formatter.format(Double.parseDouble(TaxSections.get(empid+"ANN_tx_Paid").toString())) %></td>
      
      <td><%= formatter.format(Double.parseDouble(TaxSections.get(empid+"_TDS_F").toString())) %></td>
      <td>0</td>  <!-- old employear paid tax -->
      <td> <%= formatter.format(Double.parseDouble(TaxSections.get(empid+"_TDS_F").toString())) %></td>
      <td><%= TaxDue_pm_ann  %></td> 
      <td>No</td>
      <td>Y</td>
      <td><%=formatter.format(Double.parseDouble(TaxSections.get(empid+"_ANN_EmpTaxAddl_E").toString()))%></td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      <td></td> <td></td> <td></td>
      
     
    </tr>
    <%
 SNO++;
    } %> 
    
  </tbody>
</table>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.13/js/dataTables.bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/fixedcolumns/3.2.2/js/dataTables.fixedColumns.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.2.4/js/dataTables.buttons.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.2.4/js/buttons.html5.min.js"></script>
<script>
$(document).ready(function() {
    var table = $('#example').DataTable( {
	 dom: 'Bfrtip',
        buttons: [ {
            extend: 'excelHtml5',
            customize: function ( xlsx ){
                var sheet = xlsx.xl.worksheets['sheet1.xml'];
 
                // jQuery selector to add a border
                $('row c[r*="10"]', sheet).attr( 's', '25' );
            }
        } ],
        scrollY:        "300px",
        scrollX:        true,
        scrollCollapse: true,
        paging:         false,
        fixedColumns:   {
            leftColumns: 4
            
        }
    } );
} );
</script>




</body>
</html>
