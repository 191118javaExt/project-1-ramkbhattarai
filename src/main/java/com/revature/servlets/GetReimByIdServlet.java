package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.GetAllReimsOfUserTemplate;
import com.revature.models.LoginTemplate;
import com.revature.models.Reim;
import com.revature.models.ReimDTO;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.services.ReimService;
import com.revature.services.UserService;

public class GetReimByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(GetReimByIdServlet.class);
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
		//System.out.println(body);
		GetAllReimsOfUserTemplate loginAttempt = om.readValue(body, GetAllReimsOfUserTemplate.class);
		String id1 = loginAttempt.getId();
		
		int id = Integer.parseInt(id1);
		//logger.info("User attempted to login with username " + username);
		Reim r = ReimService.getReimById(id);
		//System.out.println("login attempt of user with = " + u);
		System.out.println(r);
		if(r != null) {
			//HttpSession session = req.getSession();
			// Gets the current session, or creates one if it did not exist
			
			//session.setAttribute("id", u.getId());
			
			PrintWriter out = res.getWriter();
			res.setContentType("application/json");
			ReimDTO rDTO = ReimService.convertToDTO(r);
			
			out.println(om.writeValueAsString(rDTO));
			System.out.println("response of login attempt is user with = " + rDTO);
			//session.setAttribute("userName", uDTO);
			//logger.info(username + " has successfully logged in");
		} else {
			res.setContentType("application/json");
			res.setStatus(204);
		}
	}
}