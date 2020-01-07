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
import com.revature.models.ApplyTemplate;
import com.revature.models.Reim;
import com.revature.models.ReimDTO;
import com.revature.models.UpdateTemplate;
import com.revature.services.ReimService;

public class UpdateReimServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(UpdateReimServlet.class);
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
	
		UpdateTemplate registerAttempt = om.readValue(body, UpdateTemplate.class);
		int id = registerAttempt.getId();
		double amount = registerAttempt.getAmount();
		String description = registerAttempt.getDescription();
		
		int resolver_id = registerAttempt.getResolver_id();
		int status_id = registerAttempt.getStatus_id();
		
		int type_id = registerAttempt.getType_id();
		
		int author_id = registerAttempt.getAuthor_id();
		
//		System.out.println(type instanceof String);
//		int type_id = Integer.parseInt(type);
//        switch(type){
//        case "Housing": 
//        type_id = 1;
//        break;
//        case "Travel":
//            type_id = 2;
//            break;
//        case "Food":
//            type_id = 3;
//            break;
//        case "Medicine":
//            type_id = 5;
//            break;
//        case "Study":
//            type_id = 4;
//            break;
//        case "Others":
//            type_id = 6;
//            break;
//        default: 
//        break;
//
//        }
        
        Reim r = new Reim(id, amount, description, author_id, resolver_id, status_id, type_id);
        logger.info("User attempted to apply for reimbursement ");
		boolean reim = ReimService.update(r);
		System.out.println(reim);
		if(reim) {

			PrintWriter out = res.getWriter();
			res.setContentType("application/json");
			ReimDTO rDTO = ReimService.convertToDTO(r);
			
			out.println(om.writeValueAsString(rDTO));
			
			
			logger.info("successfully updated");
		} else {
			System.out.println("reimbursement is not processed");
			res.setContentType("application/json");
			res.setStatus(204);
		}
	}
}
