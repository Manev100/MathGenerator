package com.marcneveling.gui;


public class TransitionManager {
	
	private static MainFrame mainFrame;
	
	private TransitionManager() {
	}
	
	public static MainFrame buildMainFrame(){
		mainFrame = new MainFrame();
		
		return mainFrame;
	}
	
	static MainFrame getMainFrame(){
		return mainFrame;
	}
	
	static void showSetupView(){
		mainFrame.showSetupView();
	}
	
	static void showCountDown(){
		mainFrame.showCountDown();
	}

}
