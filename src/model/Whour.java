package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import helper.SQLConnection;

public class Whour {
	private int id,doctor_id;
	private String doctor_name,wdate,status;
SQLConnection conn = new SQLConnection();
	
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	
	public Whour(int id, int doctor_id, String doctor_name, String wdate, String status) {
		this.id = id;
		this.doctor_id = doctor_id;
		this.doctor_name = doctor_name;
		this.wdate = wdate;
		this.status = status;
	}
	
	public Whour() {
		
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
	 * @return the doctor_id
	 */
	public int getDoctor_id() {
		return doctor_id;
	}


	/**
	 * @param doctor_id the doctor_id to set
	 */
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}


	/**
	 * @return the doctor_name
	 */
	public String getDoctor_name() {
		return doctor_name;
	}


	/**
	 * @param doctor_name the doctor_name to set
	 */
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}


	/**
	 * @return the wdate
	 */
	public String getWdate() {
		return wdate;
	}


	/**
	 * @param wdate the wdate to set
	 */
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList<Whour>getWhourList(int doctor_id) throws SQLException{
		ArrayList<Whour> list =new ArrayList<>(); 
		
		//sql den çektiğimiz verileri arrayliste atıyoruz
		Whour obj;
		try {
	         Connection con = conn.connSQL();

			st=con.createStatement();
			rs =st.executeQuery("SELECT * FROM whour WHERE status ='a' AND doctor_id ="+doctor_id);
		
		while(rs.next()) {
			obj =new Whour();
			obj.setId(rs.getInt("id"));
			obj.setDoctor_id(rs.getInt("doctor_id"));
			obj.setDoctor_name(rs.getString("doctor_name"));
			obj.setStatus(rs.getString("status"));
			obj.setWdate(rs.getString("wdate"));
					
			list.add(obj);
			
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		}
	
	
	
	
	

	
	

}
