package com.vimal.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User_Roles {

	@Id
	private int role_id;

	private String  username;
	
	private String user_role;

	
	
	public int getRole_id() {
		return role_id;
	}



	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getUser_role() {
		return user_role;
	}



	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}



	public User_Roles() {
		// TODO Auto-generated constructor stub
	}

}
