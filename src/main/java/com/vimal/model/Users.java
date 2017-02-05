package com.vimal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import java.io.Serializable;

@Entity
public class Users implements Serializable{
 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
    private String userName;
    private String password;
    private byte enabled;
     
    public Users()  {
         
    }
 
    public Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }

	public int getId() {
		return id;
	}

	

	public byte getEnabled() {
		return enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}
 
}