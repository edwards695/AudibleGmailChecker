package com.solex;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class SysTray {

	TrayIcon trayIcon = null;
	SystemTray tray = SystemTray.getSystemTray();
	
	Image image = Toolkit.getDefaultToolkit().getImage("images/email-icon.png");
	
	
	public void load() {
		
		
		trayIcon = new TrayIcon(image, "Demo");
	//	trayIcon.addMouseListener(new MouseClick());
		
		trayIcon.addActionListener(new clickListener());    // Currently SystemTray does not seem to be fully supported in Gnome Shell.
		trayIcon.addMouseListener(new mouseClickListener());// It may still work in Windows.
		
		try {
			tray.add(trayIcon);
			
		} catch (AWTException e){
			e.printStackTrace();
		}
	}
	
	private class clickListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Settings settings = new Settings();
			settings.launch(true);
			System.out.println("System tray is finally working");
			
		}
	}
	
	private class mouseClickListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			Settings settings = new Settings();
			settings.launch(true);
			
			System.out.println("System tray is finally working");
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
