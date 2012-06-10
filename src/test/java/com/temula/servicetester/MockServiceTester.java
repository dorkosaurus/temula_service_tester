package com.temula.servicetester;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

@SuppressWarnings("serial")
public class MockServiceTester extends ServiceTester {
	
	protected URLConnection getConnection(URL serviceURL) throws IOException{
		return new Conn(serviceURL);
	}
	/** Simple stub for testing...*/
	private static class Conn extends HttpURLConnection{
		protected Conn(URL u) {
			super(u);
		}
		@Override
		public InputStream getInputStream(){
			String str="OKAY";
			ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
			return bais;
		}

		@Override
		public void disconnect() {
		}

		@Override
		public boolean usingProxy() {
			return false;
		}

		@Override
		public void connect() throws IOException {
		}
	}
	
}
