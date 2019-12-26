package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.DaoForAll;
import com.revature.dao.ReimDaoImpl;
import com.revature.models.Reim;

public class ReimService {
	static DaoForAll<Reim, Integer> rs = new ReimDaoImpl();
	static List<Reim> reims = new ArrayList<>();
	
	
	public List<Reim> getAllReims(){
		reims = rs.getAll();
		if(reims.size() == 0) {
			return null;
		}else {
			return reims;
		}
	}
	
	public Reim getReimById(int id) {
		return rs.getById(id);
	}
	
	public Reim add(Reim r) {
		return rs.add(r);
	}
	
	public boolean update(Reim r) {
		return rs.update(r);
	}
	
	public List<Reim> getAllPendingReims(){
		List<Reim> allReims = rs.getAll();
		List<Reim> pendingReims = new ArrayList<>();
		for(Reim r : allReims) {
			if(r.getStatus_id() == 2) {
				pendingReims.add(r);
			}
		}
		if(pendingReims.size() == 0) {
			return null;
		}
		return pendingReims;
	}
	
	

}
