package com.tds.services;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.DecimalFormat;
import java.text.NumberFormat;

//import org.apache.commons.io.FileUtils;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;

public class Form16B
{
	// Connection connection;

	public static void generateReport(String proposedMonths, Map Basics, Map TaxException, ArrayList taxempids, ArrayList empids, Map FinalComponents, Map TaxSections,List Components,Map Components_map,String yeardata,Map Tax_dec_Map,Connection connection,String Date_Path)
	{
		     String name=null;
		
		
	
			String FilePath="C:/TDS_FORECAST";
			//String Sub_FilePath="C:/TDS_FORECAST/";
			
			String Sub_FilePath="C:/TDS_FORECAST/"+Date_Path+"/16B/";
			
			//Date_Path
			NumberFormat formatter = new DecimalFormat("#0.00");
			
			Map hm=new HashMap();
			//Sub_FilePath="//10.30.0.24/f/java/TDS_ forcast/";
			
		//	Sub_FilePath=Sub_FilePath.concat(Basics.get("BuName").toString());
			
			Sub_FilePath=Sub_FilePath.concat(Basics.get("BuName").toString());
            
			Sub_FilePath=Sub_FilePath.concat("_16B");
			
			File file = new File(FilePath);
			if (!file.exists()) {
				if (file.mkdir()) {
					//System.out.println("Directory is created!");
				} else {
					//System.out.println("Failed to create directory!");
				}
			}

			File files = new File(Sub_FilePath);
			if (!files.exists()) {
				if (files.mkdirs()) {
					//System.out.println("Multiple directories are created!");
				} else {
					//System.out.println("Failed to create multiple directories!");
				}
			}


			//System.out.println("step 1");

			Date dNow = new Date();
			SimpleDateFormat ft=new SimpleDateFormat ("dd.MM.yyyy");
			try
		  {
				java.util.Iterator empseq = taxempids.iterator();


	while(empseq.hasNext()){
		      
			    hm=new HashMap();
			    String empid= empseq.next().toString(); 
			  //  System.out.println("JRXML" +empid +"~~"+Tax_dec_Map.get(empid+"$DE_80CCD").toString());
			    hm.put("EMPID", empid);
			    
			    hm.put("EMP_NAME", Basics.get(empid+".callname").toString().trim());
			    
			    String CompanyTitle=Basics.get(empid+"_EMPCOMPANYTITLE_V").toString();
			    
			    hm.put("BU_NAME", ""+CompanyTitle+"");
			    
			   // hm.put("BU_NAME", "HETERO HEALTHCARE LTD");
			    
			   // hm.put("BU_NAME", Basics.get("BuName").toString());
			    //**************************************************************
			    hm.put("_1_a",  Double.parseDouble(FinalComponents.get(empid+"-ANN_gross").toString())); //gross
			    hm.put("_1_b",  0.00); // Medical reembresment
			    hm.put("_1_c",  0.00); // 
			    hm.put("_1_d",  Double.parseDouble(FinalComponents.get(empid+"-ANN_gross").toString())); //total gross
			    
			//    System.out.println("HIAAAAAAAAAAAAAAAAA");
			    //CTC Exemptions
			    hm.put("_2_a",  Double.parseDouble(TaxException.get(empid+".Basi40_RE_y").toString()));
			    hm.put("_2_b",  Double.parseDouble(TaxException.get(empid+"_CA_M_y").toString()));
			    hm.put("_2_c",  Double.parseDouble(TaxException.get(empid+".Medical_y").toString()));
			    hm.put("_2_d",  Double.parseDouble(TaxSections.get(empid+"_LTA_P").toString()));
			    hm.put("_2_e",  Double.parseDouble(TaxException.get(empid+".ChildEdu_y").toString()));  // Child Education
			    hm.put("_2_f",  Double.parseDouble(TaxException.get(empid+".ANN_ExmpAmount").toString()));  // Total Gross of 2a-2d
			    hm.put("_3",    Double.parseDouble(FinalComponents.get(empid+"-ANN_gross_ExAmt").toString()));  //After Deduction of CA,child,etc
			    hm.put("_4_a",  0.00);  // Entertinement Allow
			    hm.put("_4_b",   Double.parseDouble(FinalComponents.get(empid+"-ANN_Pt").toString()));  // PT 
			    hm.put("_5",   Double.parseDouble(FinalComponents.get(empid+"-ANN_Pt").toString()));    //Aggrate AMt
			    hm.put("_6",   Double.parseDouble(TaxException.get(empid+".ANN_FinalIncome_B").toString()));    /// Final income after pt deduct
			    // Other Income Details
			    
			    hm.put("_7_a",   Double.parseDouble(TaxSections.get(empid+"INHR").toString())); // House property
			    
			    hm.put("_7_b",   Double.parseDouble(TaxSections.get(empid+"_OTHERINCOME").toString())); // income from other source
			    
			    hm.put("_7_c.1",   Double.parseDouble(TaxSections.get(empid+"_RENTINTEREST_E").toString())); //Less Deduction Housing Loan Intrest
             // need to add in jasper
 
			    Double TOTAL_let_iNTREST=(Double.parseDouble(TaxSections.get(empid+"INHR").toString())+Double.parseDouble(TaxSections.get(empid+"_OTHERINCOME").toString()))-Double.parseDouble(TaxSections.get(empid+"_RENTINTEREST_E").toString());
			    
			    hm.put("_7_c",   TOTAL_let_iNTREST); //Less Deduction Housing Loan Intrest total of above
			    
			    //7_d ===_7_c 7_d not Used
			  //  hm.put("_7_d",  Double.parseDouble(TaxException.get(empid+".CUmm_LEXAMT").toString())); // income total A+B-c
			    
			    hm.put("_8",   Double.parseDouble(TaxException.get(empid+".ANN_LEXAMT").toString())); //ncome total 6+7
			    
			    hm.put("_9_a_1",  Double.parseDouble(FinalComponents.get(empid+"_ANN-PF").toString()));  //80C section  PF
			    
			    hm.put("_9_a_4",  Double.parseDouble(TaxSections.get(empid+"_80C_E_y").toString()));  //80C section eligible
			    
			    hm.put("_9_1_4.1",  Double.parseDouble(TaxSections.get(empid+"_80C_E_y_Temp").toString()));  //80C section main total
			    
			    hm.put("_9_1_4.2",  Double.parseDouble(TaxSections.get(empid+"_80C_E_y").toString()));  //80C section deductible
			    
			    
			   // hm.put("_9_b_4",  TaxSections.get(empid+"80C").toString());
			    hm.put("_10",  Double.parseDouble(TaxSections.get(empid+"_80D_E").toString()));
			    
			    hm.put("_10_t",  Double.parseDouble(Tax_dec_Map.get(empid+"$ToTAL_80D").toString()));  // 80D over all Sum
			    
			    hm.put("_11",  Double.parseDouble(TaxSections.get(empid+"ANN_TxamT").toString()));
			    hm.put("_12",  Double.parseDouble(TaxSections.get(empid+"_ANN_Emp_Tax_E").toString()));
			    hm.put("_13", Double.parseDouble(TaxSections.get(empid+"ANN_Educess").toString()));
			    hm.put("_14", Double.parseDouble(TaxSections.get(empid+"ANN_tx_Paid").toString()));
			    hm.put("_15", Double.parseDouble(TaxSections.get(empid+"_ANN_Before_Dedu_Relif").toString())); 
			    hm.put("_16", Double.parseDouble(TaxSections.get(empid+"ANN_tx_Paid").toString())); 
			    
			    //***************************************************************
			    // ADD on 19-03-2018
			    double _80C$=Double.parseDouble(Tax_dec_Map.get(empid+"$DE_80C").toString()) +Double.parseDouble(FinalComponents.get(empid+"_ANN-PF").toString());
			    
			    hm.put("DE_80C" , _80C$);
			    
			  //  hm.put("DE_80C" , Double.parseDouble(Tax_dec_Map.get(empid+"$DE_80C").toString()));
			    
			    
			    hm.put("DE_80CCC" ,Double.parseDouble(Tax_dec_Map.get(empid+"$DE_80CCC").toString()));
			    hm.put("DE_80CCD" ,Double.parseDouble(Tax_dec_Map.get(empid+"$DE_80CCD").toString()));
			    hm.put("DE_80D" , Double.parseDouble(Tax_dec_Map.get(empid+"$DE_80D").toString()));
			    hm.put("DE_S80DD" ,Double.parseDouble( Tax_dec_Map.get(empid+"$DE_S80DD").toString()));
			    hm.put("DE_S80DD2" , Double.parseDouble(Tax_dec_Map.get(empid+"$DE_S80DD2").toString()));
			    hm.put("DE_S80DDB" , Double.parseDouble(Tax_dec_Map.get(empid+"$DE_S80DDB").toString()));
			    hm.put("DE_S80DDB2" , Double.parseDouble(Tax_dec_Map.get(empid+"$DE_S80DDB2").toString()));
			    hm.put("DE_S80U" , Double.parseDouble(Tax_dec_Map.get(empid+"$DE_S80U").toString()));
			    hm.put("DE_S80U2" , Double.parseDouble(Tax_dec_Map.get(empid+"$DE_S80U2").toString()));
			    hm.put("DE_S80E" , Double.parseDouble(Tax_dec_Map.get(empid+"$DE_S80E").toString()));
			    hm.put("DE_S80CCG" , Double.parseDouble(Tax_dec_Map.get(empid+"$DE_S80CCG").toString()));
			    hm.put("DE_S80CCG2" ,Double.parseDouble( Tax_dec_Map.get(empid+"$DE_S80CCG2").toString()));
			    hm.put("DE_S80TTA" , Double.parseDouble(Tax_dec_Map.get(empid+"$DE_S80TTA").toString()));
			    hm.put("DE_S80TTA1" , Double.parseDouble(Tax_dec_Map.get(empid+"$DE_S80TTA1").toString()));
			    hm.put("DE_S80G" , Double.parseDouble(Tax_dec_Map.get(empid+"$DE_S80G").toString()));
			    hm.put("DE_LETOUT" , Double.parseDouble(Tax_dec_Map.get(empid+"$DE_LETOUT").toString()));
			    hm.put("DE_OTHERINCOME" , Double.parseDouble(Tax_dec_Map.get(empid+"$DE_OTHERINCOME").toString()));
			    
			    hm.put("emp_father" , " .............................................  ");
			    hm.put("emp_desc" , Basics.get(empid+"_EMP_PROF_DESIG").toString());
			    
			    if(TaxSections.get(empid+"LOCATION").toString().trim().equalsIgnoreCase("HYD")){
			    	hm.put("emp_loc" , "HYDERABAD");
			    }else if(TaxSections.get(empid+"LOCATION").toString().trim().equalsIgnoreCase("MUM")){
			    	
			    	hm.put("emp_loc" , "MUMBAI");
			    	
			    }else{
			    	
			    	hm.put("emp_loc" , " ");
			    }
			    
			    
			    hm.put("emp_date" , ft.format(dNow));
			    
			   
		try{
			
			  try{
			   JasperFillManager.fillReportToFile("C:\\Form16BJrxml\\NEW_PART_B.jasper",hm,new JREmptyDataSource());
			  }catch(Exception EFD){
				  
				  System.out.println("Jrxml Generation ::"+EFD);
				  
			  }
			   String printFileName="C:\\Form16BJrxml\\NEW_PART_B.jrprint";
		       JasperExportManager.exportReportToPdfFile(printFileName, Sub_FilePath+"\\"+empid+".pdf");
		  }catch(JRException jrxml){
			  	jrxml.printStackTrace();
		  }
		
	}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	
	}
	
}