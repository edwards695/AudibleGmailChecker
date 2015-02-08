package com.solex;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/* TODO
 *  Create Save Handle
 *  Create Hide handle
 *  Create Settings MenuItem
 *  Create Settings Window to input First name, Last name, save password.
 */

public class Settings {

	private Frame frame;	//Created objects
	private Panel panel;
	private Button startButton;
	private Button quitButton;
	private Button checkNow;
	private Button hide;
	private TextField usernameField;
	private TextField passwordField;
	private char c = 42;
	private Label label;
	
	private int unreadMessages;
	private String fromField = new String();
	private String subjectLine = new String();
	
	private static boolean visibility = true;
	
	private static boolean ready;  // This is static because the CheckerThread will use it.
	
	
	
	public void launch(boolean showHide){
		visibility = showHide;	
		SetupWindow(visibility);
	
	}
	
	private void SetupWindow(boolean showHide) {
		
		frame = new Frame("Gmail Checker");  //Set objects
		panel = new Panel();
		panel.setLayout(new GridLayout(2,2));
		checkNow = new Button("Check Gmail");
		hide = new Button("Hide");
		startButton = new Button("Start");
		quitButton = new Button("Quit");
		
		MenuBar menuBar = new MenuBar(); //Set up the menu bar
		frame.setMenuBar(menuBar);
		Menu file = new Menu("File");  //Set up the menu
		Menu help = new Menu("Help");
		menuBar.setHelpMenu(help);
		menuBar.add(file);
		MenuItem quit = new MenuItem("Quit"); //Set up the buttons that go in the menu.
		MenuItem about = new MenuItem("About");
		
		quit.addActionListener(new CloseHandler());  // Set up the action handlers for those buttons
		help.addActionListener(new AboutWindow());
		
		file.add(quit);  //Add the menu items
		help.add(about);
		
		File f = new File("cache");
		if (f.exists() && !f.isDirectory()) {
			
			Read read = new Read();
			read.load();
	
			usernameField = new TextField(read.getUsername(), 20); //Exists
			passwordField = new TextField(read.getPassword(), 20); //Get username and password from Read (cached file)
			passwordField.setEchoChar(c);
			ready = true;  //Sending this to checker thread to see if the automated checking can start.
			
		} else {
			usernameField = new TextField("Username", 20);  // File does not exist.
			passwordField = new TextField("Password", 20);
			ready = false;
		}
		
		
		frame.add(panel);
		
		panel.add(usernameField);
		panel.add(passwordField);
		panel.add(checkNow);			//Added buttons where neeeded
		panel.add(hide);
		panel.add(startButton);
		panel.add(quitButton);
		
		checkNow.addActionListener(new CheckHandler());
		hide.addActionListener(new HideWindowHandler());
		quitButton.addActionListener(new CloseHandler());  //Close application events
		frame.addWindowListener(new CloseWindowHandler());
		startButton.addActionListener(new SaveHandler());
		
		usernameField.addFocusListener(new FocusListenerU());
		passwordField.addFocusListener(new FocusListenerP());
		
		frame.pack();  //Auto-size frame
		frame.setLocationRelativeTo(null);
		
		quitButton.requestFocusInWindow();  //  Set focus to quit button so my FocusListeners don't mess up.
		
		frame.setVisible(visibility);
	}
	
	public boolean getReady() {
		return ready;
	}
	
	public void setReady() {
		ready = true;
	}
	
	private class FocusListenerU extends FocusAdapter {
		public void focusGained(FocusEvent e){
			if (usernameField.getText().equals("Username")){
				usernameField.setText("");
			}
		}
	}
	
	private class FocusListenerP extends FocusAdapter {
		public void focusGained(FocusEvent e){
			passwordField.setText("");
			passwordField.echoCharIsSet();
			passwordField.setEchoChar(c);
			if (usernameField.getText().equals("")){
				usernameField.setText("Username");
			}
		}
	}
	
	private class CloseWindowHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e){
			System.exit(0);
		}
	}
	
	private class CloseHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	private class SaveHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Save save = new Save(usernameField.getText(),passwordField.getText());
			save.write();
			CheckerThread thread = new CheckerThread();
			thread.setReady();
			
			thread.start();
		}
	}
	
	private class CheckHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			ReadEmails read = new ReadEmails();
			read.readNow(usernameField.getText(), passwordField.getText());  // Access the email with username and password to set variables
			
			unreadMessages = read.getUnread();  // Get number of unread messages
			fromField = read.getFromField();
			subjectLine = read.getSubjectLine();
			
			Talk talk = new Talk(unreadMessages);
			
			if (unreadMessages >= 1) {
				talk.speakTotalMessages();
			} else {
				talk.speakNoNewMessages();
			}
			
			Talk talk2 = new Talk(unreadMessages, fromField, subjectLine);

			if (unreadMessages >= 1 ) {
				talk2.speakSummary();
			}		
			
		}
	}
	
	private class HideWindowHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
		}
	}
	
	private class AboutWindow implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			frame = new Frame("About");
			panel = new Panel();
			label = new Label();
			
			panel.setLayout(new BorderLayout());
			quitButton = new Button("Quit");
			label.setText("Gmail Checker 2015 by Brian Edwards");
			
			frame.add(panel);
			panel.add(quitButton, BorderLayout.SOUTH);
			panel.add(label, BorderLayout.CENTER);
			
			quitButton.addActionListener(new HideWindowHandler());
			
			frame.pack();
			frame.setLocationRelativeTo(null);
			
			frame.setVisible(true);		
		}
	}
	
}
