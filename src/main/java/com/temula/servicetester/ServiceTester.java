package com.temula.servicetester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceTester extends HttpServlet{
	static final Logger logger = Logger.getLogger(ServiceTester.class.getName());
	ResourceBundle bundle = ResourceBundle.getBundle("servicetester");
	public void doGet(HttpServletRequest req, HttpServletResponse resp){ 
		try{	
			resp.getOutputStream().write(new String("started...").getBytes());
			resp.flushBuffer();
			long sleepTime = Long.parseLong((String)bundle.getObject("sleeptimemillis"));
		
			
			while(true){
				testServices();
				logger.info("sleeping for "+sleepTime+" millis");
				Thread.sleep(sleepTime);
			}
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	boolean testServices(){
			logger.info("waking up...");
			boolean ret=true;
			String serviceNameStr = (String)bundle.getObject("servicenames");
			logger.info("here are the services i'll be testing: "+serviceNameStr);

			String[]serviceNames = serviceNameStr.split(",");
			for(String serviceName:serviceNames){
				String serviceURL = (String)bundle.getObject(serviceName);
				logger.info("testing service:"+serviceName+" at URL "+serviceURL);
				try{
					URL url = new URL(serviceURL);
					HttpURLConnection conn = (HttpURLConnection) this.getConnection(url);
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
	
	protected URLConnection getConnection(URL serviceURL) throws IOException{
		return serviceURL.openConnection();
	}
	
	
}