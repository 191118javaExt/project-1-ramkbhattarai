package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorld extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	// servlets have 3 primary methods in their lifecycle 
	// 1. init(), which is called to instantiate the servlet if it is not already (since servlets are Singletons)
	// 
	
	public void init() throws ServletException{
		System.out.println(this.getServletName()+ " is instantiated");
		super.init(); // this is calling parent class init() method.
	}
	
	// the next method we'll write is the service() method. This is used for the business logic and to populate the response.
	// when using servlets normally, you should never evr override the service() method.
	
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		System.out.println(this.getServletName() + " service method called");
		
		
		// so in order to create the response, we must write to it.
		// the way we write to the response is with its own printwriter object
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		// this is going to send back an html page that says Hello world
		out.println("<h1>Hello World</h1>");
		
		// At the end of the se
	}
	
	
	
	// after a very long time of not being used, the web container will called invoke the destroy method on your servlet.
	public void destroy() {
		System.out.println(this.getServletName() + "Destroy Method called");
		super.destroy();
	}
}
