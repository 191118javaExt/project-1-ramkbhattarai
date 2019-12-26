package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Reim;
import com.revature.utils.ConnectionUtil;

public class ReimDaoImpl implements DaoForAll<Reim, Integer>{
	private static Logger log = Logger.getLogger(ReimDaoImpl.class);
	@Override
	public List<Reim> getAll() {
		List<Reim> list = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT * FROM project1.reimbursements;";
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				double amount = rs.getDouble("amount");
				Timestamp submitted = rs.getTimestamp("submitted");
				Timestamp resolved = rs.getTimestamp("resolved");
				String description = rs.getString("description");
				byte[] receipt = rs.getBytes("receipt");
				int authorId = rs.getInt("author_id");
				int resolverId = rs.getInt("resolver_id");
				int statusId = rs.getInt("status_id");
				int typeId = rs.getInt("type_id");
				
				Reim r = new Reim(id, amount, description, authorId, resolverId, receipt, submitted.toLocalDateTime(),resolved.toLocalDateTime(),statusId, typeId);
				
				list.add(r);
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("Unable to retrieve all reimbursments", e);
		}

		
		return list;
	}

	@Override
	public Reim getById(Integer id) {
		Reim r = null;
		try (Connection con = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT * FROM project1.reimbursements where id = ?;";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id1 = rs.getInt("id");
				double amount = rs.getDouble("amount");
				Timestamp submitted = rs.getTimestamp("submitted");
				Timestamp resolved = rs.getTimestamp("resolved");
				String description = rs.getString("description");
				byte[] receipt = rs.getBytes("receipt");
				int authorId = rs.getInt("author_id");
				int resolverId = rs.getInt("resolver_id");
				int statusId = rs.getInt("status_id");
				int typeId = rs.getInt("type_id");
				
				 r = new Reim(id1, amount, description, authorId, resolverId, receipt, submitted.toLocalDateTime(),resolved.toLocalDateTime(),statusId, typeId);
				
				
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("Unable to retrieve  a reimbursment", e);
		}
		return r;
	}

	@Override
	public Reim add(Reim r) {
			try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "INSERT INTO project1.reimbursements (amount, submitted, resolved, description, receipt, author_id, resolver_id, status_id, type_id) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setDouble(1, r.getAmount());
			stm.setTimestamp(2, Timestamp.valueOf(r.getSubmitted()));
			stm.setTimestamp(3, Timestamp.valueOf(r.getResolved()));
			stm.setString(4, r.getDescription());
			stm.setBytes(5, r.getReceipt());
			stm.setInt(6,r.getAuthor_id());
			stm.setInt(7, r.getResolver_id());
			stm.setInt(8, r.getStatus_id());
			stm.setInt(9, r.getType_id());
			
			int rows = stm.executeUpdate();
			if(rows > 0) {
				ResultSet rs = stm.getGeneratedKeys();
				while(rs.next()) {
					r.setId(rs.getInt("id")); // this line is to be checked
					r.setAmount(rs.getDouble("amount"));
					r.setSubmitted(rs.getTimestamp("submitted").toLocalDateTime());
					r.setResolved(rs.getTimestamp("resolved").toLocalDateTime());
					r.setDescription(rs.getString("description"));
					r.setReceipt(rs.getBytes("receipt"));
					r.setAuthor_id(rs.getInt("author_id"));
					r.setResolver_id(rs.getInt("resolver_id"));
					r.setStatus_id(rs.getInt("status_id"));
					r.setType_id(rs.getInt("type_id"));
			
				}
			}
		} catch(SQLException e) {
			log.warn("Unable to add reimbursment", e);
			return null;
		}
		
		return r;
	}

	@Override
	public boolean update(Reim r) {
		int id = r.getId();
		double amount = r.getAmount();
		Timestamp submitted = Timestamp.valueOf(r.getSubmitted());
		Timestamp resolved = Timestamp.valueOf(r.getResolved());
		String des = r.getDescription();
		byte[] receipt = r.getReceipt();
		int authorId = r.getAuthor_id();
		int resolverId = r.getResolver_id();
		int statusId = r.getStatus_id();
		int typeId = r.getType_id();
		

		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "UPDATE public.reimbursements SET amount = ?, submitted = ?,resolved = ?, description = ?, receipt = ?, author_id = ?, resolver_id = ?, status_id = ?, type_id = ? WHERE id = ?;"; 
					
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setDouble(1, amount);
			stm.setTimestamp(2, submitted);
			stm.setTimestamp(3, resolved);
			stm.setString(4, des);
			stm.setBytes(5, receipt);
			stm.setInt(6, authorId);
			stm.setInt(7, resolverId);
			stm.setInt(8, statusId);
			stm.setInt(9, typeId);
			stm.setInt(10, id);
			
			if(!stm.execute()) {
				return false;
			}
		} catch(SQLException ex) {
			log.warn("Unable to update the reimbursment", ex);
			return false;
		}
		
		return true;
		
	}

	@Override
	public boolean delete(Reim reim) {
		// TODO Auto-generated method stub
		return false;
	}

}
