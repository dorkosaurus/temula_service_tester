package com.temula.servicetester;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceTester extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp){ 
		ResourceBundle bundle = ResourceBundle.getBundle("servicetester");
		String spaceshibernateurl = (String)bundle.getObject("spaceshibernateurl");
		String imageshibernateurl = (String)bundle.getObject("imageshibernateurl");

	}
	

}