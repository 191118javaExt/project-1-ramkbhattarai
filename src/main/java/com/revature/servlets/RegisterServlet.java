package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.RegisterTemplet;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.services.UserService;

public class RegisterServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(RegisterServlet.class);
	private static ObjectMapper om = new ObjectMapper();

	protected void doPost(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException{
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		//System.out.println(body);
		RegisterTemplet registerAttempt = om.readValue(body, RegisterTemplet.class);
		String username = registerAttempt.getUsername();
		String password = registerAttempt.getPassword();
		String fname = registerAttempt.getFname();
		String lname = registerAttempt.getLname();
		String email = registerAttempt.getEmail();
		String role = registerAttempt.getRole_id();
		int roleId = 0;
		if(role.equalsIgnoreCase("employee")) {
			roleId = 1;
		}else {
			roleId = 2;
		}
		User u = new User(0,fname, lname, username, email, password, roleId);
		//System.out.println("new user = " + u);
		logger.info("User attempted to register with username " + username);
		User user = UserService.add(u);
		if(user != null) {
			//HttpSession session = req.getSession();
			// Gets the current session, or creates one if it did not exist
			//session.setAttribute("username", username);
			
			PrintWriter out = res.getWriter();
			res.setContentType("application/json");
			UserDTO uDTO = UserService.convertToDTO(user);
			
			out.println(om.writeValueAsString(uDTO));
			
			logger.info(username + " has successfully registered in");
		} else {
			res.setContentType("application/json");
			res.setStatus(204);
		}
	}
}
