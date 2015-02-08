package com.solex;

import java.io.*;
import java.util.Base64;

public class Read {
	
	private String line = new String();
	private int lineLength;
	private String username = new String();
	private String password = new String();
	private String encodedUsername = new String();
	private String encodedPassword = new String();
	private int lineSub;
	
	public void load(){
		try {
			FileReader input = new FileReader("cache");
			BufferedReader bufInput = new BufferedReader(input);
			
			line = bufInput.readLine();
			
			lineLength = line.length();
			lineSub = line.indexOf(",");
			encodedUsername = line.substring(0,lineSub);
			encodedPassword = line.substring(lineSub+1, lineLength);
				
			bufInput.close();
			
			Base64.Decoder decoder = Base64.getDecoder();
			byte[] decodedUsername = decoder.decode(encodedUsername);
			username = new String(decodedUsername);
			
			byte[] decodedPassword = decoder.decode(encodedPassword);
			password = new String(decodedPassword);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println("File not found.");
		}
		
		
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
}
