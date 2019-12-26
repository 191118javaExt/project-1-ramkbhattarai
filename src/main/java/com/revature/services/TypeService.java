package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.DaoForAll;
import com.revature.dao.TypeDaoImpl;
import com.revature.models.Type;

public class TypeService {
	static DaoForAll<Type, Integer> ts = new TypeDaoImpl();
	static List<Type> types = new ArrayList<>();
	
	public List<Type> getAllTypes(){
		types = ts.getAll();
		if(types.size() == 0) {
			return null;
		}else {
			return types;
		}
	}
	
	public Type getTypeById(int id) {
		return ts.getById(id);
	}
	
	public Type add(Type t) {
		return ts.add(t);
	}
	
	public boolean update(Type t) {
		return ts.update(t);
	}

}
