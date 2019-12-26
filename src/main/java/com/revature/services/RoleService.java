package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.DaoForAll;
import com.revature.dao.RoleDaoImpl;
import com.revature.models.Role;

public class RoleService {

	static DaoForAll<Role, Integer> rs = new RoleDaoImpl();
	static List<Role> roles = new ArrayList<>();
	
	public List<Role> getAllRoles(){
		roles = rs.getAll();
		if(roles.size() == 0) {
			return null;
		}else {
			return roles;
		}
	}
	
	public Role getRoleById(int id) {
		return rs.getById(id);
	}
	
	public Role add(Role r) {
		return rs.add(r);
	}
	
	public boolean update(Role r) {
		return rs.update(r);
	}
}
