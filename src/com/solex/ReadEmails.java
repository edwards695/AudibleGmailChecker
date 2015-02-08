package com.solex;

import java.util.*;
import javax.mail.*;

// http://www.compiletimeerror.com/2013/06/reading-email-using-javamail-api-example.html#.VNMS83JGjUY

public class ReadEmails {
	
	String username = new String();
	String password = new String();

	private int totalMessages;
	private int unreadMessages;
	
	String messageFrom = new String();
	private int messageSub1;
	String messageSubject = new String();
	private int messageLength;
	private int DESIRED_LENGTH = 100;

	public void readNow(String username, String password){
		
		this.username = username; // Get the username and password from the previous fields.
		this.password = password;
		
		Properties properties = new Properties();  
		properties.setProperty("mail.store.protocol", "imaps"); // Define the protocol
		
		try {
			
			Session session = Session.getInstance(properties, null);  // Get a session instance to read email.
			
			Store store = session.getStore(); // Access emails through a store class
			store.connect("imap.gmail.com", username, password);
			
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);
			
			unreadMessages = inbox.getUnreadMessageCount();  // Get numer of unread messages
			totalMessages = inbox.getMessageCount();  // Sets total number of messages in inbox
			
			
			Message msg = inbox.getMessage(totalMessages); //Gets the message
			Address[] in = msg.getFrom(); // Gets the from field
			
			for (Address address : in) {
				
				messageFrom = address.toString();
				messageSub1 = messageFrom.indexOf("<");
				messageFrom = messageFrom.substring(0, (messageSub1 - 1)); // This will send back just the name, without
				  														   // it the email address will return also.
			}
			
			
			messageSubject = msg.getSubject();  // Get the subject line.
			messageLength = msg.getSubject().length();
			
			if (messageLength > DESIRED_LENGTH){  // This will cut the subject line off so it doesn't ramble.
				messageSubject = messageSubject.substring(0,DESIRED_LENGTH);
			}
			store.close();
			
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
	public int getUnread() {
		return unreadMessages;
	}
	
	public String getFromField() {
		return messageFrom;
	}
	
	public String getSubjectLine(){
		return messageSubject;
	}
}
