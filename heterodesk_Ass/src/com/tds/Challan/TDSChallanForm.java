package com.tds.Challan;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.hhcdesk.db.*;


@SuppressWarnings("serial")
public class TDSChallanForm extends HttpServlet {
	static PreparedStatement pstmt=null;
	static PreparedStatement pstmt1=null;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  Connection con=null;
		  HttpSession session = request.getSession(false);
		
		ResultSet rs=null;
		
		String pattern = "yyyy-MM-dd-HH-mm-ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		String TDS=request.getParameter("TDS");
		String Surcharge=request.getParameter("Surcharge");
		String Education_Cess=request.getParameter("Education_Cess");
		String Interest=request.getParameter("Interest");
		String Fees=request.getParameter("Fees");
		String Penalty_Others=request.getParameter("Penalty_Others");
		String Total_Tax=request.getParameter("Total_Tax");
		String Mode_Of_Deposit=request.getParameter("Mode_Of_Deposit");
		String Bank_Branch_Code=request.getParameter("Bank_Branch_Code");
		String Challan_Serial_No=request.getParameter("Challan_Serial_No");
		String Date_Of_Amount_Deposited=request.getParameter("Date_Of_Amount_Deposited");
		String Minor_Head_Challan=request.getParameter("Minor_Head_Challan");
		String Interest_Statement=request.getParameter("Interest_Statement");
		String Other_Statement=request.getParameter("Other_Statement");
		String condition=request.getParameter("condition");
		
		
		String fromdate=request.getParameter("fromdate");
		String todate=request.getParameter("todate");
		String username=(String)session.getAttribute("EMP_ID");
		
		String Query="";
		
		
		try {
			con=(java.sql.Connection)session.getAttribute("ConnectionObj");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		PrintWriter pw=response.getWriter();

		response.setContentType("text/html");
		
		int sno=0;
		
		boolean flag=false;
		boolean Dataflag=false;
		if(con!=null){
			
		
			
		if(condition.equalsIgnoreCase("TDSGEN")){
		 
		  try{
			  
			  
			 pstmt=con.prepareStatement("INSERT INTO test.tds_challan (NAME) VALUES (?)",Statement.RETURN_GENERATED_KEYS); 
			 pstmt.setString(1,"CHALLAN");
			 int a=0;
			 a=pstmt.executeUpdate();
			 rs = pstmt.getGeneratedKeys();
	            while (rs.next()) {
	            	sno=rs.getInt(1);
	                System.out.println("Key returned from getGeneratedKeys():" + rs.getInt(1));
	            } 
	                      
	          String sql = "INSERT INTO test.tds_challan_details (CHALLAN_ID, TDS, SURCHARGE, EDUCATION_CESS, INTEREST, FEES, "
	          		+ "PENALTY_OTHERS, TOTAL_TAX, MODE_OF_DEPOSIT, BANK_BRANCH_CODE, CHALLAN_SERIAL_NO, DATE_OF_DEPOSIT, MINOR_HEAD_CHALLAN, "
	          		+ "INTERESR_STATEMENT, OTHER_STATEMENT,LOGIN) VALUES (?,?,?,?,?,?,?,?,?,?,?,DATE_FORMAT(STR_TO_DATE(?, '%d-%m-%Y'), '%Y-%m-%d'),?,?,?,?)";
		         
	          pstmt1 = con.prepareStatement(sql);
	        	  for(int i=0;i<TDS.split(",").length;i++){
	        		  		pstmt1.setInt(1, sno);
	        		  		pstmt1.setString(2,TDS.split(",")[i]);
	        				pstmt1.setString(3,Surcharge.split(",")[i]);
	        				pstmt1.setString(4,Education_Cess.split(",")[i]);
	        				pstmt1.setString(5,Interest.split(",")[i]);
	        				pstmt1.setString(6,Fees.split(",")[i]);
	        				pstmt1.setString(7,Penalty_Others.split(",")[i]);
	        				pstmt1.setString(8,Total_Tax.split(",")[i]);
	        				pstmt1.setString(9,Mode_Of_Deposit.split(",")[i]);
	        				pstmt1.setString(10,Bank_Branch_Code.split(",")[i]);
	        				pstmt1.setString(11,Challan_Serial_No.split(",")[i]);
	        				pstmt1.setString(12,Date_Of_Amount_Deposited.split(",")[i]);
	        				pstmt1.setString(13,Minor_Head_Challan.split(",")[i]);  
	        				pstmt1.setString(14,Interest_Statement.split(",")[i]);
	        				pstmt1.setString(15,Other_Statement.split(",")[i]);
	        				
	        				pstmt1.setString(16, username);
	  			
	  			
						 pstmt1.addBatch();
	  			}
	        	  
	        	  pstmt1.executeBatch();
	        	  Query="B.SNO ="+sno;
	        	  flag=true;
		 }catch(Exception e){
			 pw.println("101");
		 }
		}
		else if(condition.equalsIgnoreCase("TDSREPORT"))
		{
			  Query="DATE_FORMAT(B.LUPDATE,'%Y-%m-%d') BETWEEN DATE_FORMAT(STR_TO_DATE('"+fromdate+"', '%d-%m-%Y'), '%Y-%m-%d') AND DATE_FORMAT(STR_TO_DATE('"+todate+"', '%d-%m-%Y'), '%Y-%m-%d')";
			  flag=true;
		} 
		
		if(flag)
		{
			
		
		GetDbData DataObj=new GetDbData();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("EmployeeDetails");
       
       DecimalFormat dec = new DecimalFormat("#.##");
       
	   StringBuffer PayrollData=new StringBuffer();
	   
      
      PayrollData.append(" SELECT @a:=@a+1 as no,TDS, SURCHARGE, EDUCATION_CESS,   ");
      PayrollData.append(" INTEREST, FEES, PENALTY_OTHERS, TOTAL_TAX, MODE_OF_DEPOSIT, BANK_BRANCH_CODE,  ");
      PayrollData.append(" CHALLAN_SERIAL_NO, date_format(DATE_OF_DEPOSIT,'%d-%m-%Y')DATE_OF_DEPOSIT,  ");
      PayrollData.append(" MINOR_HEAD_CHALLAN, INTERESR_STATEMENT, OTHER_STATEMENT  ");
      PayrollData.append(" FROM (SELECT @a:= 0) AS a,TEST.TDS_CHALLAN B  ");
      PayrollData.append(" LEFT JOIN TEST.TDS_CHALLAN_DETAILS C ON B.SNO=C.CHALLAN_ID where "+Query+"" );
      PayrollData.append(" ORDER BY no ASC");
      
      Map<String, Object[]> data = new LinkedHashMap<String, Object[]>();
      data.put("0", new Object[] {"SNO", "TDS", "Surcharge" ,"Education Cess","Interest" ,"Fees u/s 234E", "Penalty / Others","Total Tax Deposited","Mode of deposit through Challan (C) Book Adjustment (B)","Bank-Branch Code/ Form 24G Receipt Number","Challan Serial No./DDO Serial no. of Form No.24G","Date of amount deposited through challan/Date of transfer voucher (dd/mm/yyyy)","Minor Head of Challan","Interest (Corresponding to Regular Statement)","Other (Corresponding to Regular Statement)"});
      data.put("1", new Object[] {"", "302" ,"303","304" ,"305", "306","307","308","309","310","311","312","313","",""});

      rs=null;
	    try {
	    	rs=(ResultSet)DataObj.FetchData(PayrollData.toString(), "PayrollStatus", rs ,con);
			int MycountRow=2;
			while(rs.next()){
				 data.put(""+MycountRow+"", new Object[] {""+rs.getString("no")+"",""+rs.getString("TDS")+"" ,""+rs.getString("SURCHARGE")+"",""+rs.getString("EDUCATION_CESS")+"" ,""+rs.getString("INTEREST")+"", ""+rs.getString("FEES")+"",""+rs.getString("PENALTY_OTHERS")+"",""+rs.getString("TOTAL_TAX")+"",""+rs.getString("MODE_OF_DEPOSIT")+"",""+rs.getString("BANK_BRANCH_CODE")+"",""+rs.getString("CHALLAN_SERIAL_NO")+"",""+rs.getString("DATE_OF_DEPOSIT")+"",""+rs.getString("MINOR_HEAD_CHALLAN")+"",""+rs.getString("INTERESR_STATEMENT")+"",""+rs.getString("OTHER_STATEMENT")+""});
				 MycountRow++;
				 Dataflag=true;
			}
	    }catch(Exception Er2){
	    	 System.out.println("Exception:: "+Er2);
	    	 pw.println("101");
	    }
      
      Row row = sheet.createRow(0);
      Cell cell = row.createCell(0);
      CellStyle style=null;
      XSSFFont font= workbook.createFont();
         font.setFontHeightInPoints((short)11);
         font.setFontName("Arial");
         font.setColor(IndexedColors.RED.getIndex());
         font.setBold(true);
         font.setItalic(false);
         style=workbook.createCellStyle();;
         style.setFillPattern(CellStyle.ALT_BARS);
         style.setAlignment(CellStyle.ALIGN_CENTER);
         style.setFont(font);
         row = sheet.createRow(1);
         cell = row.createCell(2);
          
      Set<String> keyset = data.keySet();
       int rownum = 0;
      for (String key : keyset)
      {
         row = sheet.createRow(rownum++);
          Object [] objArr = data.get(key);
          int cellnum = 0;
          for (Object obj : objArr)
          {
          	int sd=cellnum++;
              cell = row.createCell(sd);
             
              if(rownum<=2)
              {
                  style=null;
                  
                  font= workbook.createFont();
                  font.setFontHeightInPoints((short)11);
                  font.setFontName("Arial");
                  font.setColor(IndexedColors.WHITE.getIndex());
                  font.setBold(true);
                  font.setItalic(false);

                  style=workbook.createCellStyle();
                  style.setFillPattern(CellStyle.SOLID_FOREGROUND);
                  style.setAlignment(CellStyle.ALIGN_CENTER);
                  style.setFont(font);
                  cell.setCellStyle(style);
              }
              
             if(obj instanceof String){
                  cell.setCellValue((String)obj);
             }else if(obj instanceof Integer){
                  cell.setCellValue((Integer)obj);
             }
          }
      }
      if(Dataflag)
      {
  	     String  path =  "C:/TDSReports/";
  	     
  	     
  	   File file = new File(path);
       if (!file.exists()) {
           if (file.mkdir()) {
               System.out.println("Directory is created!");
           } 
       }
  	     
  	     
	  String filename=date+".xlsx";
	File myFile = new File(path+""+filename);
	FileOutputStream out = new FileOutputStream(myFile);
	//String absolutePath = myFile.getAbsolutePath();
	workbook.write(out);
	out.close();
	pw.println(filename);
      }
      else{
    	  pw.println("500");
      }
		}
		}
		else
		{
			pw.print("100");
		}
	}
}