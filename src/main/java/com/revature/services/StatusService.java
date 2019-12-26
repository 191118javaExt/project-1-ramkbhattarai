package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.DaoForAll;
import com.revature.dao.StatusDaoImpl;
import com.revature.models.Status;

public class StatusService {

	static DaoForAll<Status, Integer> ss = new StatusDaoImpl();
	static List<Status> statuses = new ArrayList<>();
	
	public List<Status> getAllStatus(){
		statuses = ss.getAll();
		if(statuses.size() == 0) {
			return null;
		}else {
			return statuses;
		}
	}
	
	public Status getStatusById(int id) {
		return ss.getById(id);
	}
	
	public Status add(Status s) {
		return ss.add(s);
	}
	
	public boolean update(Status s) {
		return ss.update(s);
	}

}
