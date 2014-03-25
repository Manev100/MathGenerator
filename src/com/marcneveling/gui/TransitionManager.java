package com.marcneveling.gui;

import com.marcneveling.main.MathModel;
import com.marcneveling.main.PageModel;


public class TransitionManager {
	
	private static MainFrame mainFrame;
	
	private TransitionManager() {
	}
	
	public static MainFrame buildMainFrame(MathModel math, PageModel page, GuiController controller){
		mainFrame = new MainFrame(math, page, controller);
		
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
