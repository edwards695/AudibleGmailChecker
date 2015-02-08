package com.solex;

// THIS IS REQUIRED TO RUN THE PROGRAM http://freetts.sourceforge.net/docs/jsapi_setup.html

public class GmailChecker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Settings settings = new Settings();
		settings.launch(true);
		
		SysTray sysTray = new SysTray();
		sysTray.load();
		
		CheckerThread thread = new CheckerThread();
		thread.start();
		
		
	}

}
