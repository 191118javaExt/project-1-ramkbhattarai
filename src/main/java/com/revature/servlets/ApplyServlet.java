package com.revature.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reim;
import com.revature.models.ReimDTO;
import com.revature.services.ReimService;


@MultipartConfig
public class ApplyServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ApplyServlet.class);
	private static ObjectMapper om = new ObjectMapper();
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException{
		
		InputStream receiptToSend = null; 
  
   Part filePart = req.getPart("receipt");
   System.out.println("this is file that is send =" + filePart);
   if (filePart != null) {

	   System.out.println("this says file part is not null");
       receiptToSend = filePart.getInputStream();
   }
   HttpSession session = req.getSession(false);
  
   byte[] receipt = IOUtils.toByteArray(receiptToSend);
		
	double amount = Double.parseDouble(req.getParameter("amount"));
		String description = req.getParameter("description"); 
		int status_id = Integer.parseInt(req.getParameter("status_id"));
		
		String type = req.getParameter("type_id");
		
		int author_id = Integer.parseInt(req.getParameter("author_id"));
		int type_id = 0;
        switch(type){
        case "Housing": 
        type_id = 1;
        break;
        case "Travel":
            type_id = 2;
            break;
        case "Food":
            type_id = 3;
            break;
        case "Medicine":
            type_id = 5;
            break;
        case "Study":
            type_id = 4;
            break;
        case "Others":
            type_id = 6;
            break;
        default: 
        break;

        }

        Reim r = new Reim(0, new Double(amount), new String(description), new Integer(author_id), 1, receipt, new Integer(status_id), new Integer(type_id));
        logger.info("User attempted to apply for reimbursement ");
		
        Reim reim = ReimService.add(r);
		System.out.println(reim);
		if(reim != null) {

			PrintWriter out = res.getWriter();
			res.setContentType("application/json");
			ReimDTO rDTO = ReimService.convertToDTO(reim);
			
			out.println(om.writeValueAsString(rDTO));
			
			
			logger.info("successfully applied in");
		} else {
			System.out.println("reimbursement is not processed");
			res.setContentType("application/json");
			res.setStatus(204);
		}
        
	}
}