package com.solex;

public class CheckerThread extends Thread {

	private static boolean threadSwitch = true;
	private final int DURATION = 300000;
	
	private boolean isReady;
	private String username = new String();
	private String password = new String();
	private int newEmails;
	private int emailsTracker;
	private String subject = new String();
	private String from = new String();

	
	
	
	public void run() {
		while (threadSwitch != false) {
			try {
				
				checker();
				CheckerThread.sleep(DURATION); // Duration of gmail checking.
				
			}catch(InterruptedException e){
				System.out.println("Thread has been terminated");
			}
		}
	}
	
	public void checker(){
		
		Settings settings = new Settings();
		
		isReady = settings.getReady();
		
		if (isReady == true){
			
			Read read = new Read();
			read.load();
			username = read.getUsername();
			password = read.getPassword();
			
			ReadEmails readEmails = new ReadEmails();
			readEmails.readNow(username, password);
			
			
			newEmails = readEmails.getUnread();
			from = readEmails.getFromField();
			subject = readEmails.getSubjectLine();
			
			Talk talk = new Talk(newEmails, from, subject);
			
			if (newEmails > emailsTracker) {
				talk.speakNewMessage();
				emailsTracker = newEmails;
			} else if (newEmails <= emailsTracker) {
				emailsTracker = newEmails;
			}
			
			
		} else {
			threadSwitch = false;
			this.interrupt();
			
		}
	}
	
	public void setReady(){
		Settings settings = new Settings();
		settings.setReady();
		threadSwitch = true;
	}
	
}
