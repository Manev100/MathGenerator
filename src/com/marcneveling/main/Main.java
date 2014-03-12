package com.marcneveling.main;

import javax.swing.SwingUtilities;

import com.marcneveling.gui.MainFrame;
import com.marcneveling.gui.TransitionManager;



public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				MainFrame frame = TransitionManager.buildMainFrame();
			}
		});
		
		/*Generator gen = new Generator(new MathModel(), new PageModel());
		MathRobot robot = new MathRobot();
		
		gen.generateIt(robot);*/
		
	}

}







