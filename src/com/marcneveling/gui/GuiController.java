package com.marcneveling.gui;

import com.marcneveling.main.KeyStrokesGenerator;
import com.marcneveling.main.MathModel;
import com.marcneveling.main.PageModel;

public class GuiController {
	private MainFrame mainFrame;
	private MathModel math;
	private PageModel page;
	private KeyStrokesGenerator generator;
	
	public GuiController(MathModel math, PageModel page) {
		this.math = math;
		this.page = page;
		
		this.generator = new KeyStrokesGenerator(math, page);
		this.mainFrame = TransitionManager.buildMainFrame(math, page, this);
	}
	
	public void startGenerating(){
		generator.generateIt();
	}

}

