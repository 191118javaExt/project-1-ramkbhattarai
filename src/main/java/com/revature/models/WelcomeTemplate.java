package com.revature.models;

import java.io.Serializable;

public class WelcomeTemplate implements Serializable{
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private int id;
		private String fname;
		private String lname;
		private String userName;
		private String email;
		private String password;
		private int role_id;
		
		
		public WelcomeTemplate() {
			super();
		}


		public WelcomeTemplate(int id, String fname, String lname, String userName, String email, String password,
				int role_id) {
			super();
			this.id = id;
			this.fname = fname;
			this.lname = lname;
			this.userName = userName;
			this.email = email;
			this.password = password;
			this.role_id = role_id;
		}


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
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


		public String getuserName() {
			return userName;
		}


		public void setuserName(String userName) {
			this.userName = userName;
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


		public int getRole_id() {
			return role_id;
		}


		public void setRole_id(int role_id) {
			this.role_id = role_id;
		}
		
		

}
