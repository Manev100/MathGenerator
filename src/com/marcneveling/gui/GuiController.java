package com.marcneveling.gui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JTextField;

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

	public void verifyInput(BoundTextField source) {
		source.getInputVerifier().verify(source);
		source.setFocusable(false);
		source.setFocusable(true);
		
	}

	public void commitChangesToModel(BoundTextField source, int newValue) {
		String field = source.getField();
		try{
			Method method = math.getClass().getMethod("set" + capitalizeFirstLetter(field), 
														Integer.TYPE);
			method.invoke(math, newValue);
		}catch(NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
			//ignore
			System.out.println("val: " + source.getText() +  " " + e.toString());
		}
		
		try{
			Method method = page.getClass().getMethod("set" + capitalizeFirstLetter(field), 
														Integer.TYPE);
			method.invoke(page, newValue);
		}catch(NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
			//ignore
			System.out.println("val: " + source.getText() + " " + e.toString());
		}
		
	}
	
	private String capitalizeFirstLetter(String original){
	    if(original.length() == 0)
	        return original;
	    return original.substring(0, 1).toUpperCase() + original.substring(1);
	}

}

