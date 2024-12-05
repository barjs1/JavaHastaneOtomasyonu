package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import helper.SQLConnection;

public class Clinic {

	private int id;
	private String name;

	SQLConnection conn = new SQLConnection();
	
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Clinic() {
	}

	public Clinic(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ArrayList<Clinic> getList() throws SQLException {
		ArrayList<Clinic> list = new ArrayList<>();
		Clinic obj;
		// sql den çektiğimiz verileri arrayliste atıyoruz
         Connection con = conn.connSQL();
		try {
			
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM clinic ");

			while (rs.next()) {
				obj = new Clinic();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				list.add(obj);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}
		return list;
	}
	
	
	
	
	
	public Clinic getFetch(int id) {
        Connection con = conn.connSQL();
        Clinic c = new Clinic() ;
        try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM clinic WHERE id= "+id);
			while (rs.next()) {
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				
				break;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return c;
	}
	
	//clinic ekleme
	public boolean addClinic(String name) throws SQLException {
		//database sorgusu
		String query= "INSERT INTO clinic"+"(name) VALUES" + "(?)";
		
		boolean key =false;
		Connection con= conn.connSQL();
		try {
			st=con.createStatement();
			
			//hazırlama iişlemi
			preparedStatement=con.prepareStatement(query);
			preparedStatement.setString(1,name);
			
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
	//clinic silme
	 public boolean deleteClinic(int id) throws SQLException {
 		//database sorgusu
 		String query= "DELETE FROM clinic WHERE id=?";
 		
 		boolean key =false;
		Connection con= conn.connSQL();

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
	 public boolean updateClinic(int id,String name) throws SQLException {
	 		//database sorgusu
	 		String query= "UPDATE clinic SET name=? WHERE id=?";
			Connection con= conn.connSQL();

	 		boolean key =false;
	 		try {
	 			
	 			
	 			//hazırlama işlemi
	 			st=con.createStatement();
	 			preparedStatement=con.prepareStatement(query);
	 			preparedStatement.setString(1,name);
	 			preparedStatement.setInt(2,id);
	 			
	 			
	 			
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
	 
	 public ArrayList<user>getClinicDoctorList(int clinic_id) throws SQLException{
			ArrayList<user> list =new ArrayList<>(); 
			
			//sql den çektiğimiz verileri arrayliste atıyoruz
			user obj;
			try {
				Connection con= conn.connSQL();

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



	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
