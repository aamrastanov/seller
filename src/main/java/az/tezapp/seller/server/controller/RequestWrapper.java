package az.tezapp.seller.server.controller;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestWrapper extends HttpServletRequestWrapper {

	private String body;
	
	public RequestWrapper(HttpServletRequest request) {
		super(request);
		StringBuffer jb = new StringBuffer();
		  String line = null;
		  BufferedReader reader = null;
		  try { 
		    reader = request.getReader();
		    reader.mark(0);
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } 
		  catch (Exception e) {			  
		  }
		  finally{
			  if (reader != null){
				  try {
					reader.close();
				  } 
				  catch (IOException e) {					  
				  }
			  }
		  }
		  body = jb.toString();
	}
	
	 @Override
	 public ServletInputStream getInputStream() throws IOException {
	   final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
		   	ServletInputStream servletInputStream = new ServletInputStream() {			
			@Override
			public int read() throws IOException {
				return byteArrayInputStream.read();
			}
			
			@Override
			public void setReadListener(ReadListener listener) {			
			}
			
			@Override
			public boolean isReady() {			
				return false;
			}
			
			@Override
			public boolean isFinished() {			
				return false;
			}
		};
	 	return servletInputStream;
	 }

	 @Override
	 public BufferedReader getReader() throws IOException {
	   return new BufferedReader(new InputStreamReader(this.getInputStream()));
	 }

	 public String getBody() {
	   return this.body;
	 }

}
