package com.marcneveling.main;

import javax.swing.SwingUtilities;

import com.marcneveling.gui.GuiController;
import com.marcneveling.gui.MainFrame;
import com.marcneveling.gui.TransitionManager;



public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				MathModel math = new MathModel();
				PageModel page = new PageModel();
				new GuiController(math, page);
			}
		});		
	}

}





