package model;

import helper.SQLConnection;

public class user {
	
    private	int id;
    String tcno,name,password,type;
    SQLConnection conn =new SQLConnection();
    
    
    
    public user(int id, String tcno, String name, String password, String type) {
	
		
		this.id = id;
		this.tcno = tcno;
		this.name = name;
		this.password = password;
		this.type = type;
	}
	public user() {
		
		
	
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
	 * @return the tcno
	 */
	public String getTcno() {
		return tcno;
	}
	/**
	 * @param tcno the tcno to set
	 */
	public void setTcno(String tcno) {
		this.tcno = tcno;
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
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
    
    
    
    


}
