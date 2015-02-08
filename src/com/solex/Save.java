package com.solex;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

public class Save {

	private String username = new String();
	private String password = new String();
	private PrintWriter writer;
	
	
	public Save(String user, String pass){
		this.username = user;
		this.password = pass;
	}
	
	public void write() {
		
		try {
			
			Base64.Encoder encoder = Base64.getEncoder();
			String encodedUsername = encoder.encodeToString(username.getBytes(StandardCharsets.UTF_8));
			String encodedPassword = encoder.encodeToString(password.getBytes(StandardCharsets.UTF_8));
			
			
			writer = new PrintWriter("cache", "UTF-8");
			writer.println(encodedUsername + "," + encodedPassword);
			writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
