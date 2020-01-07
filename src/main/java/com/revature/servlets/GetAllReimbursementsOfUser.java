package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

public class GetAllReimbursementsOfUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(GetAllReimbursementsOfUser.class);
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
		
		List<Reim> rlist = ReimService.getAllReimsByUserId(id);
		
		
		if(rlist.size() != 0) {
			PrintWriter out = res.getWriter();
			res.setContentType("application/json");
			List<ReimDTO> rDTO = ReimService.convertAllReimsToDTO(rlist);
			out.println(om.writeValueAsString(rDTO));
			logger.info("all user's reims are changed to object and packed into an array");
		}else {
			res.setContentType("application/json");
			res.setStatus(204);
		}
		
		
		
	}

}
