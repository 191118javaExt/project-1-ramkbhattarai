package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.DaoForAll;
import com.revature.dao.UserDaoImpl;
import com.revature.models.User;
import com.revature.models.UserDTO;

public class UserService {
		static DaoForAll<User, Integer> udto = new UserDaoImpl();
		
		
		public static List<User> getAllUsers(){
			List<User>  users = udto.getAllUsers();
			if(users.size() == 0) {
				return null;
			}else {
				return users;
			}
		}
		
		public static User getUserById(int id) {
			return udto.getById(id);
		}
		
		public static User add(User u) {
			return udto.add(u);
		}
		
		public static boolean update(User u) {
			return udto.update(u);
		}
		
		public static User getUserByUsernameAndPassword(String username, String password) {
			List<User> allUsers = udto.getAll();
			//System.out.println(allUsers);
			//User user = new User();
			for(User u : allUsers) {
				if(u.getUserName().equalsIgnoreCase(username) && u.getPassword().equalsIgnoreCase(password)) {
					return u;
				}
			}
			return null;
		}
		
		public static UserDTO convertToDTO(User u) {
			return new UserDTO(u.getId(),
					u.getFname(),
					u.getLname(),
					u.getUserName(),
					u.getEmail(),
					u.getPassword(),
					u.getRole_id()
					);
		}
		
		public static User confirmLogin(String username, String password) {
			User u = getUserByUsernameAndPassword(username, password);
			System.out.println("user from the db with username and password" + u);
			if(u == null) {
				return null;
			}
			if(u.getPassword().equals(password)) {
				return u;
			} else {
				return null;
			}
		}
		
		public static List<UserDTO> convertAllUsersToDTO(List<User> allUsers){
			List<UserDTO> allUsersDTO = new ArrayList<>();
			for(User u : allUsers) {
				allUsersDTO.add(convertToDTO(u));
			}
			return allUsersDTO;
		}
		
		
}
