package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDaoImpl implements DaoForAll<User, Integer>{

	private static Logger log = Logger.getLogger(UserDaoImpl.class);
	@Override
	public List<User> getAll() {
		List<User> userList = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT * FROM project1.users;";
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String email = rs.getString("email");
				String username = rs.getString("user_name");
				String password = rs.getString("password");
				int roleid = rs.getInt("role_id");
				User u = new User(id, fname, lname,username, email, password, roleid);
				
				userList.add(u);
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("Unable to retrieve all users", e);
		}

		
		return userList;
	}

	@Override
	public User getById(Integer id) {
		
			User u = null;
		
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT * FROM project1.users WHERE id = ? ;";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id1 = rs.getInt("id");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String email = rs.getString("email");
				String username = rs.getString("user_name");
				String password = rs.getString("password");
				int roleid = rs.getInt("role_id");
				 u = new User(id1, fname, lname,username, email, password, roleid);
				
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("Unable to retrieve the user", e);
		}
		return u;
	}

	@Override
	public User add(User u) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "INSERT INTO project1.users (fname, lname, user_name, email, password,role_id) " +
					"VALUES (?, ?, ?, ?, ?, ?);";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, u.getFname());
			stm.setString(2, u.getLname());
			stm.setString(3, u.getLname());
			stm.setString(4, u.getLname());
			stm.setString(5, u.getPassword());
			stm.setInt(6, u.getRole_id());
			
			int rows = stm.executeUpdate();
			if(rows > 0) {
				ResultSet rs = stm.getGeneratedKeys();
				while(rs.next()) {
					u.setId(rs.getInt("id")); // this line is to be checked
					u.setFname(rs.getString("fname"));
					u.setLname(rs.getString("lname"));
					u.setUserName(rs.getString("user_name"));
					u.setEmail(rs.getString("email"));
					u.setPassword(rs.getString("password"));
					u.setRole_id(rs.getInt("role_id"));
			
				}
			}
		} catch(SQLException e) {
			log.warn("Unable to add user", e);
			return null;
		}
		
		return u;
	}

	@Override
	public boolean update(User u) {
		int id = u.getId();
		String fname =  u.getFname();
		String lname =  u.getLname();
		String password =  u.getPassword();
		String email = u.getEmail();
		String username = u.getUserName();
		int roleId = u.getRole_id();
		
		
		
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "UPDATE project1.users SET fname = ?, lname = ?,user_name = ?, email = ?, password = ?, role_id = ? WHERE id = ?;"; 
					
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, fname);
			stm.setString(2, lname);
			stm.setString(3, username);
			stm.setString(4, email);
			stm.setString(5, password);
			stm.setInt(6, roleId);
			stm.setInt(7, id);
			
			if(!stm.execute()) {
				return false;
			}
		} catch(SQLException ex) {
			log.warn("Unable to update the user", ex);
			return false;
		}
		
		return true;
	}

	@Override
	public boolean delete(User u) {
		return false;
		// TODO Auto-generated method stub
		
	}
	

}