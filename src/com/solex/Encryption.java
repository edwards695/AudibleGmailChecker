package com.solex;
import java.nio.charset.*;
import java.util.*;


public class Encryption {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String username = new String();
		String password = new String();
		
		username = "Edwards695";
		password = "Confidential";
		
			//ENCODE
		
		Base64.Encoder encoder = Base64.getEncoder();
		String encodedUsername = encoder.encodeToString(username.getBytes(StandardCharsets.UTF_8));

		System.out.println(encodedUsername);
		
		String encodedPassword = encoder.encodeToString(password.getBytes(StandardCharsets.UTF_8));
		
		System.out.println(encodedPassword);
		
			//DECODE
		
		
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] decodedUsername = decoder.decode(encodedUsername);
		
		System.out.println(new String(decodedUsername));
		
		byte[] decodedPassword = decoder.decode(encodedPassword);
		
		System.out.println(new String(decodedPassword));
	}

}
