package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Bashekim extends user {

	Connection con = conn.connSQL();
	Statement st= null;
	ResultSet rs= null;
	PreparedStatement preparedStatement=null;
	

	public Bashekim(int id, String tcno, String name, String password, String type) {
	
		
	super(id,tcno,name,password,type);
}

public Bashekim() {}

public ArrayList<user>getDoctorList() throws SQLException{
	ArrayList<user> list =new ArrayList<>(); 
	
	//sql den çektiğimiz verileri arrayliste atıyoruz
	user obj;
	try {
		
		st=con.createStatement();
		rs =st.executeQuery("SELECT * FROM user WHERE type = 'doktor'");
	
	while(rs.next()) {
		obj =new user(
				rs.getInt("id"),
				rs.getString("tcno"),rs.getString("name"),
				rs.getString("password"),rs.getString("type"));
		list.add(obj);
		
	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
	}

public ArrayList<user>getClinicDoctorList(int clinic_id) throws SQLException{
	ArrayList<user> list =new ArrayList<>(); 
	
	//sql den çektiğimiz verileri arrayliste atıyoruz
	user obj;
	try {
		
		st=con.createStatement();
		rs =st.executeQuery("SELECT u.id,u.tcno,u.type,u.name,u.password FROM worker w LEFT JOIN user u ON w.user_id = u.id WHERE clinic_id = "+clinic_id);
	
	while(rs.next()) {
		obj =new user(
				rs.getInt("u.id"),
				rs.getString("u.tcno"),rs.getString("u.name"),
				rs.getString("u.password"),rs.getString("u.type"));
		list.add(obj);
		
	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
	}






//doktor ekleme metodu

     public boolean addDoctor(String tcno,String passsword,String name) throws SQLException {
	//database sorgusu
	String query= "INSERT INTO user"+"(tcno,password,name,type) VALUES" + "(?,?,?,?)";
	
	boolean key =false;
	try {
		st=con.createStatement();
		
		//hazırlama iişlemi
		preparedStatement=con.prepareStatement(query);
		preparedStatement.setString(1,tcno);
		preparedStatement.setString(2,password);
		preparedStatement.setString(3,name);
		preparedStatement.setString(4,"doktor");
		preparedStatement.executeUpdate();
		key=true;
		} catch (Exception e) {
		e.printStackTrace();
	}
	if(key)
		return true;
	
	else
		return false;
	
	
}
     
     //doktor silme metodu
     public boolean deleteDoctor(int id) throws SQLException {
    		//database sorgusu
    		String query= "DELETE FROM user WHERE id=?";
    		
    		boolean key =false;
    		try {
    			
    			
    			//hazırlama işlemi
    			st=con.createStatement();
    			preparedStatement=con.prepareStatement(query);
    			preparedStatement.setInt(1,id);
    			
    			preparedStatement.executeUpdate();
    			key=true;
    			} catch (Exception e) {
    			e.printStackTrace();
    		}
    		if(key)
    			return true;
    		
    		else
    			return false;
    		
    		
    	}
     public boolean updateDoctor(int id,String tcno,String password,String name) throws SQLException {
 		//database sorgusu
 		String query= "UPDATE user SET name=?,tcno=?,password=? WHERE id=?";
 		
 		boolean key =false;
 		try {
 			
 			
 			//hazırlama işlemi
 			st=con.createStatement();
 			preparedStatement=con.prepareStatement(query);
 			preparedStatement.setString(1,name);
 			preparedStatement.setString(2,tcno);
 			preparedStatement.setString(3,password);
 			preparedStatement.setInt(4,id);
 			
 			preparedStatement.executeUpdate();
 			key=true;
 			} catch (Exception e) {
 			e.printStackTrace();
 		}
 		if(key)
 			return true;
 		
 		else
 			return false;
 		
 		
 	}
     public boolean addWorker(int user_id , int clinic_id) throws SQLException {
    		//database sorgusu
    	 String query = "INSERT INTO `hospital1`.`worker` (`user_id`, `clinic_id`) VALUES" + "(?,?)";
    		
    		boolean key =false;
    		int count =0;
    		try {
    			st=con.createStatement();
    			rs = st.executeQuery("SELECT * FROM  worker WHERE clinic_id AND user_id =" + clinic_id + user_id);
    			while(rs.next()) {
    				count++;
    				
    			}
    			if (count==0) {
    			//hazırlama iişlemi
    			preparedStatement=con.prepareStatement(query);
    			preparedStatement.setInt(1,user_id);
    			preparedStatement.setInt(2,clinic_id);
    			preparedStatement.executeUpdate();
    			key=true;
    			}
    			
    			} catch (Exception e) {
    			e.printStackTrace();
    		}
    		if(key)
    			return true;
    		
    		else
    			return false;
    		
    		
    	}

}
