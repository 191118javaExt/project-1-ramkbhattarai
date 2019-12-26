package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Type;
import com.revature.utils.ConnectionUtil;

public class TypeDaoImpl implements DaoForAll<Type, Integer>{
	private static Logger log = Logger.getLogger(TypeDaoImpl.class);
	@Override
	public List<Type> getAll() {
		List<Type> reimTypes = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT * FROM project1.reim_types;";
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String reimType = rs.getString("reim_type");
				
				Type r = new Type(id, reimType);
				
				reimTypes.add(r);
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("Unable to retrieve all types", e);
		}

		
		return reimTypes;
	}

	@Override
	public Type getById(Integer id) {
		Type t = null;
		
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT * FROM project1.reim_types WHERE id = ? ;";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id1 = rs.getInt("id");
				String usrRoles = rs.getString("reim_type");
				
				 t = new Type(id1, usrRoles);
				
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("Unable to retrieve the type", e);
		}
		return t;
	}

	@Override
	public Type add(Type t) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "INSERT INTO project1.reim_types (reim_type) " +
					"VALUES (?);";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, t.getReimbursementType());
			
			
			int rows = stm.executeUpdate();
			if(rows > 0) {
				ResultSet rs = stm.getGeneratedKeys();
				while(rs.next()) {
					t.setId(rs.getInt("id")); // this line is to be checked
					t.setReimbursementType((rs.getString("reim_type")));
				}
			}
		} catch(SQLException e) {
			log.warn("Unable to add type", e);
			return null;
		}
		
		return t;
	}

	@Override
	public boolean update(Type t) {
		int id = t.getId();
		String reimTpye = t.getReimbursementType();
		
		
		
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "UPDATE project1.reim_types SET reim_type = ? WHERE id = ?;"; 
					
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, reimTpye);
			stm.setInt(2, id);
			
			if(!stm.execute()) {
				return false;
			}
		} catch(SQLException ex) {
			log.warn("Unable to update the type", ex);
			return false;
		}
		
		return true;
	}

	@Override
	public boolean delete(Type t) {
		// TODO Auto-generated method stub
		return false;
	}

}
