package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.services.UserService;

//@CrossOrigin(origins = "http://localhost:4200")
public class GetAllUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(GetAllUsersServlet.class);
	private static ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("application/json");
		List<User> ulist = UserService.getAllUsers();
		List<UserDTO> uDTO = UserService.convertAllUsersToDTO(ulist);
		out.println(om.writeValueAsString(uDTO));
		
		logger.info("all users are changed to object and packed into an array");
	}

}
