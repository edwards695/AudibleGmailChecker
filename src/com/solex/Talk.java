package com.solex;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class Talk {

	private int totalMessages;
	private String fromField = new String();
	private String subjectLine = new String();
	
	VoiceManager voiceManager = VoiceManager.getInstance();
    Voice voice = voiceManager.getVoice("kevin16");
    
    public Talk() {
    	
    }

    public Talk(int totalMessages) {
    	this.totalMessages = totalMessages;
    }
    
    public Talk(int totalMessages, String fromField, String subjectLine){
    	this.totalMessages = totalMessages;
    	this.fromField = fromField;
    	this.subjectLine = subjectLine;
    }
    
    public void speakTotalMessages(){
    	
    	if (totalMessages == 1){
    		voice.allocate();
			voice.speak("You have a new message in your inbox");
			voice.deallocate();
    	} else {
	    	voice.allocate();
			voice.speak("You have " + totalMessages + " new messages in your inbox");
			voice.deallocate();
    	}
    }
    
    public void speakNoNewMessages(){
    	voice.allocate();
    	voice.speak("You have no new messages in your inbox");
    	voice.deallocate();
    }
    
    public void speakSummary(){
    	voice.allocate();
    	voice.speak("First message is from " + fromField + "... regarding " + subjectLine);
    	voice.deallocate();
    }
    
    public void speakNewMessage(){
    	voice.allocate();
    	if (totalMessages == 1) {
    		voice.speak("You have a new message in your inbox from " + fromField + "... regarding" + subjectLine);
    	} else if (totalMessages >1 ){
    		voice.speak("You have " + totalMessages + " new messages in your inbox, most recent is from " + fromField + "... regarding" + subjectLine);
    	}
    	voice.deallocate();
    }
    
	
}
