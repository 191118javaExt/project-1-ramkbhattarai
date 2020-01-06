package com.revature.models;

import java.io.Serializable;

public class RegisterTemplet  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private int id;
	private String fname;
	private String lname;
	private String username;
	private String email;
	private String password;
	private String role_id;
	public RegisterTemplet() {
		super();
	}
	public RegisterTemplet(String fname, String lname, String username, String email, String password, String role_id) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role_id = role_id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getUsername() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegisterTemplet other = (RegisterTemplet) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (lname == null) {
			if (other.lname != null)
				return false;
		} else if (!lname.equals(other.lname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role_id != other.role_id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RegisterTemplet [fname=" + fname + ", lname=" + lname + ", userName=" + username + ", email=" + email
				+ ", password=" + password + ", role_id=" + role_id + "]";
	}

	
}
