package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

public class RoleDaoImpl implements DaoForAll<Role, Integer>{
	private static Logger log = Logger.getLogger(RoleDaoImpl.class);
	@Override
	public List<Role> getAll() {
		List<Role> userRoles = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT * FROM project1.user_roles;";
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String usrRoles = rs.getString("user_role");
				
				Role r = new Role(id, usrRoles);
				
				userRoles.add(r);
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("Unable to retrieve all roles", e);
		}

		
		return userRoles;
	}

	@Override
	public Role getById(Integer id) {
		Role r = null;
		
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT * FROM project1.user_roles WHERE id = ? ;";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id1 = rs.getInt("id");
				String usrRoles = rs.getString("user_roles");
				
				 r = new Role(id1, usrRoles);
				
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("Unable to retrieve the role", e);
		}
		return r;
	}

	@Override
	public Role add(Role r) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "INSERT INTO project1.user_roles (user_roles) " +
					"VALUES (?);";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, r.getUserRole());
			
			
			int rows = stm.executeUpdate();
			if(rows > 0) {
				ResultSet rs = stm.getGeneratedKeys();
				while(rs.next()) {
					r.setId(rs.getInt("id")); // this line is to be checked
					r.setUserRole((rs.getString("user_roles")));
				}
			}
		} catch(SQLException e) {
			log.warn("Unable to add role", e);
			return null;
		}
		
		return r;
	}

	@Override
	public boolean update(Role r) {
		int id = r.getId();
		String usrRole = r.getUserRole();
		
		
		
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "UPDATE project1.user_roles SET user_roles = ? WHERE id = ?;"; 
					
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, usrRole);
			stm.setInt(2, id);
			
			if(!stm.execute()) {
				return false;
			}
		} catch(SQLException ex) {
			log.warn("Unable to update the role", ex);
			return false;
		}
		
		return true;
	}

	@Override
	public boolean delete(Role r) {
		// TODO Auto-generated method stub
		return false;
	}

}
