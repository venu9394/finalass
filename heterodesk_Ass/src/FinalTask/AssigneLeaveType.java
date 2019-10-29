package FinalTask;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AssigneLeaveType {

 public String generate(Connection conn,String empid) {
	
	ResultSet Res=null;	
	GetDbData DataObj=new GetDbData();
	ResourceBundle rb=ResourceBundle.getBundle("Qry"); 
	JSONArray jsnAry = new JSONArray();
	 JSONObject finalobj = new JSONObject();
	 System.out.println("servlet::"+empid);
	 String statusmessage="";
	 int count=0;
		ArrayList arr=new ArrayList();
	try {
		 arr.add(empid);
		 count=1;
		Res=(ResultSet)DataObj.FetchData(rb.getString("existstatus").toString(), "Allcomments", Res ,conn,count,arr);
		if(Res.next()) {
			if(Integer.parseInt(Res.getString(1))>0) {
				 Res=null;	
					Res=(ResultSet)DataObj.FetchData(rb.getString("getempleave").toString(), "Allcomments", Res ,conn,count,arr);
					while(Res.next()){
						 JSONObject empdetails = new JSONObject();	
						 empdetails.put("leavetypeid", Res.getString("leavetypeid"));
						 empdetails.put("leavetypename", Res.getString("name"));
						 empdetails.put("status", Res.getString("status"));                                                                                 
						 jsnAry.add(empdetails);
					} 
			}
		}
	}catch(Exception Er){
		System.out.println("Exception At Er:3:"+Er);
	}
    String status="1001";

    finalobj.put("statusmessage",statusmessage);
	finalobj.put("message",jsnAry);
	finalobj.put("status",status);

return finalobj.toString();
	  }
}
