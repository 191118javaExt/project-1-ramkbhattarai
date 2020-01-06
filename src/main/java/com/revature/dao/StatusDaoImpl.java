package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Status;
import com.revature.utils.ConnectionUtil;

public class StatusDaoImpl implements DaoForAll<Status, Integer>{
	private static Logger log = Logger.getLogger(StatusDaoImpl.class);
	@Override
	public List<Status> getAll() {
		List<Status> reimStatuses = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT * FROM project1.reim_status;";
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String reimStatus = rs.getString("reim_status");
				
				Status s = new Status(id, reimStatus);
				
				reimStatuses.add(s);
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("Unable to retrieve all status", e);
		}

		
		return reimStatuses;
	}

	@Override
	public Status getById(Integer id) {
		Status s = null;
		
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT * FROM project1.reim_status WHERE id = ? ;";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id1 = rs.getInt("id");
				String reimStatus = rs.getString("reim_status");
				
				 s = new Status(id1, reimStatus);
				
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("Unable to retrieve the status", e);
		}
		return s;
	}

	@Override
	public Status add(Status s) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "INSERT INTO project1.reim_status (reim_status) " +
					"VALUES (?);";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, s.getReimbursementStatus());
			
			
			int rows = stm.executeUpdate();
			if(rows > 0) {
				ResultSet rs = stm.getGeneratedKeys();
				while(rs.next()) {
					s.setId(rs.getInt("id")); // this line is to be checked
					s.setReimbursementStatus((rs.getString("reim_status")));
				}
			}
		} catch(SQLException e) {
			log.warn("Unable to add status", e);
			return null;
		}
		
		return s;
	}

	@Override
	public boolean update(Status s) {
		int id = s.getId();
		String reimStatus = s.getReimbursementStatus();
		
		
		
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "UPDATE project1.reim_status SET reim_status = ? WHERE id = ?;"; 
					
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, reimStatus);
			stm.setInt(2, id);
			
			if(!stm.execute()) {
				return false;
			}
		} catch(SQLException ex) {
			log.warn("Unable to update the status", ex);
			return false;
		}
		
		return true;
	}

	@Override
	public boolean delete(Status s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Status> getAllReimsByUserId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Status> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}