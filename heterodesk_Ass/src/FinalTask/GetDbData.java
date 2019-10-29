package FinalTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetDbData {
	
java.sql.Statement stmt=null;
PreparedStatement ps=null;


public synchronized ResultSet FetchData(String qry, String KeyColumn,ResultSet rs,Connection con,int count,ArrayList arr) throws SQLException{
	 ps=null;
	ps = con.prepareStatement(qry);
	while(count>0) {
	int arrind=count-1;
	ps.setString(count, arr.get(arrind).toString());
	count--;
	}
	   rs=	ps.executeQuery();
		return  rs;	
 }

 //close Map Function

}  // Close Main Class



