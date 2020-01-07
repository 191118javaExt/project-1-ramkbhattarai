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

import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.models.WelcomeTemplate;
import com.revature.services.UserService;

public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(WelcomeServlet.class);
	private static ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		
		WelcomeTemplate loginAttempt = om.readValue(body, WelcomeTemplate.class);
		String username = loginAttempt.getuserName();
		String password = loginAttempt.getPassword();
		
		logger.info("User attempted to process the application with username " + username);
		User u = UserService.confirmLogin(username, password);
		if(u != null) {
			HttpSession session = req.getSession();
			
			session.setAttribute("username", u);
			
			PrintWriter out = res.getWriter();
			res.setContentType("application/json");
			UserDTO uDTO = UserService.convertToDTO(u);
			
			out.println(om.writeValueAsString(uDTO));
			
			logger.info(username + " has successfully processed the application in");
		} else {
			res.setContentType("application/json");
			res.setStatus(204);
		}
	}
}