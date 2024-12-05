package helper;
import java.sql.*;
public class SQLConnection {
	
	Connection c =null;
	
	public SQLConnection() {
	}
	
	public Connection connSQL() {
		try {
			this.c=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital1?user=root&password=123456");
		    return c;
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		return c;

	}
	
	

}
