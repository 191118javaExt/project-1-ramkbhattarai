package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.DaoForAll;
import com.revature.dao.UserDaoImpl;
import com.revature.models.User;

public class UserService {

	static DaoForAll<User, Integer> us = new UserDaoImpl();
	static List<User> users = new ArrayList<>();
	
	public List<User> getAllUsers(){
		users = us.getAll();
		if(users.size() == 0) {
			return null;
		}else {
			return users;
		}
	}
	
	public User getUserById(int id) {
		return us.getById(id);
	}
	
	public User add(User u) {
		return us.add(u);
	}
	
	public boolean update(User u) {
		return us.update(u);
	}
	
	public User getUserByEmailAndPassword(String email, String password) {
		List<User> allUsers = us.getAll();
		for(User u : allUsers) {
			if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;
	}
	
}
