package com.temula.servicetester;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceTester extends HttpServlet{
	static final Logger logger = Logger.getLogger(ServiceTester.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse resp){ 
		testServices();
	}

	boolean testServices(){
			logger.info("waking up...");
			boolean ret=true;
			ResourceBundle bundle = ResourceBundle.getBundle("servicetester");
			String serviceNameStr = (String)bundle.getObject("servicenames");
			logger.info("here are the services i'll be testing: "+serviceNameStr);

			String[]serviceNames = serviceNameStr.split(",");
			for(String serviceName:serviceNames){
				String serviceURL = (String)bundle.getObject(serviceName);
				logger.info("testing service:"+serviceName+" at URL "+serviceURL);
				try{
					URL url = new URL(serviceURL);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Accept", "text/plain");
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
					String output;
					logger.info("Output from Server .... \n");
					while ((output = br.readLine()) != null) {
						logger.info(output);
					}
				}
				catch(Exception e){
					logger.severe("problem with service "+serviceName+": "+ e.getMessage());
					e.printStackTrace();
					ret=false;
				}
			}
			return ret;
			
	}
}