package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import helper.SQLConnection;

public class appointment {
	
	
	
	private  int id,hasta_ID,doctor_ID;
	private String doctorName,hastaName,appDate;
	
	
	
	
SQLConnection conn = new SQLConnection();
	
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	public appointment(int id, int hasta_ID, int doctor_ID, String doctorName, String hastaName, String appDate) {
		super();
		this.id = id;
		this.hasta_ID = hasta_ID;
		this.doctor_ID = doctor_ID;
		this.doctorName = doctorName;
		this.hastaName = hastaName;
		this.appDate = appDate;
	}
	
	
	public appointment(){
		
	}
	public void deleteAppoint(String date, String name) {
		Connection con = conn.connSQL();
		try {
			st = con.createStatement();
			String query1 = "DELETE FROM appointment WHERE app_date='" + date + "'";
			String query2 = "UPDATE whour SET status='a' WHERE doctor_name='" + name + "' AND wdate='" + date + "' ";

			preparedStatement = con.prepareStatement(query1);
			preparedStatement.executeUpdate();

			preparedStatement = con.prepareStatement(query2);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<appointment> getHastaList(int hasta_id) throws SQLException {
		ArrayList<appointment> list = new ArrayList<>();
		appointment obj;
		Connection con = conn.connSQL();

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment WHERE hasta_id =" + hasta_id);
			while (rs.next()) {
				obj = new appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctor_ID(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setHasta_ID(rs.getInt("hasta_id"));
				obj.setHastaName(rs.getString("hasta_name"));
				obj.setAppDate(rs.getString("app_date"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}

		return list;
	}

	public ArrayList<appointment> getRandevuList(int doctor_id) throws SQLException {
		ArrayList<appointment> list = new ArrayList<>();
		appointment obj;
		Connection con = conn.connSQL();

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment WHERE doctor_id =" + doctor_id);
			while (rs.next()) {
				obj = new appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctor_ID(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setHasta_ID(rs.getInt("hasta_id"));
				obj.setHastaName(rs.getString("hasta_name"));
				obj.setAppDate(rs.getString("app_date"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}

		return list;
	}

	public ArrayList<appointment> getDoctorList(int doctor_id) throws SQLException {
		ArrayList<appointment> list = new ArrayList<>();
		appointment obj;
		Connection con = conn.connSQL();

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment WHERE hasta_id =" + doctor_id);
			while (rs.next()) {
				obj = new appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctor_ID(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setHasta_ID(rs.getInt("hasta_id"));
				obj.setHastaName(rs.getString("hasta_name"));
				obj.setAppDate(rs.getString("app_date"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
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
	 * @return the hasta_ID
	 */
	public int getHasta_ID() {
		return hasta_ID;
	}


	/**
	 * @param hasta_ID the hasta_ID to set
	 */
	public void setHasta_ID(int hasta_ID) {
		this.hasta_ID = hasta_ID;
	}


	/**
	 * @return the doctor_ID
	 */
	public int getDoctor_ID() {
		return doctor_ID;
	}


	/**
	 * @param doctor_ID the doctor_ID to set
	 */
	public void setDoctor_ID(int doctor_ID) {
		this.doctor_ID = doctor_ID;
	}


	/**
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}


	/**
	 * @param doctorName the doctorName to set
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}


	/**
	 * @return the hastaName
	 */
	public String getHastaName() {
		return hastaName;
	}


	/**
	 * @param hastaName the hastaName to set
	 */
	public void setHastaName(String hastaName) {
		this.hastaName = hastaName;
	}


	/**
	 * @return the appDate
	 */
	public String getAppDate() {
		return appDate;
	}


	/**
	 * @param appDate the appDate to set
	 */
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	
	
	
	
	
	

}
