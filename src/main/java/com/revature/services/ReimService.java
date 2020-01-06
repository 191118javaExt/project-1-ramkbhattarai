package com.revature.services;


import java.util.ArrayList;
import java.util.List;

import com.revature.dao.DaoForAll;
import com.revature.dao.ReimDaoImpl;
import com.revature.models.Reim;
import com.revature.models.ReimDTO;





public class ReimService {
	static DaoForAll<Reim, Integer> rs = new ReimDaoImpl();
	static List<Reim> reims = new ArrayList<>();
	
	
	public static List<Reim> getAllReims(){
		reims = rs.getAll();
		if(reims.size() == 0) {
			return null;
		}else {
			return reims;
		}
	}
	
	public static Reim getReimById(int id) {
		return rs.getById(id);
	}
	
	public static Reim add(Reim r) {
		//System.out.println("inside services");
		return rs.add(r);
	}
	
	public static boolean update(Reim r) {
		return rs.update(r);
	}
	
	public static List<Reim> getAllPendingReims(){
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
	
	
	public static List<Reim> getAllReimsByUserId(int id) {
		return rs.getAllReimsByUserId(id);
	}
	public static ReimDTO convertToDTO(Reim r) {
		return new ReimDTO(r.getId(),
				r.getAmount(),
				r.getDescription(),
				r.getAuthor_id(),
				r.getResolver_id(),
				r.getReceipt(),
				r.getSubmitted(),
				r.getResolved(),
				r.getStatus_id(),
				r.getType_id()
				);
	}

	public static List<ReimDTO> convertAllReimsToDTO(List<Reim> rlist) {
		List<ReimDTO> allReimsDTO = new ArrayList<>();
		for(Reim r : rlist) {
			allReimsDTO.add(convertToDTO(r));
		}
		return allReimsDTO;
	}
	
}
