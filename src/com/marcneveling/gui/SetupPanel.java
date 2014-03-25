package com.marcneveling.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.marcneveling.main.MathModel;
import com.marcneveling.main.PageModel;

public class SetupPanel extends JPanel {
	private Map<String, JTextField> textFieldMapping;
	private MathModel mathModel;
	private PageModel pageModel;
	private GuiController controller;
	
	public SetupPanel(MathModel mathModel, PageModel pageModel, GuiController controller){
		super();
		textFieldMapping = new HashMap<>();
		this.mathModel = mathModel;
		this.pageModel = pageModel;
		this.controller = controller;
		add(buildPagePanel());
		add(buildMathPanel());
		this.mathModel.addPropertyChangeListener(new DataModelsListener());
		this.pageModel.addPropertyChangeListener(new DataModelsListener());	
	}
	
	
	private JPanel buildMathPanel() {
		JPanel panel = new JPanel();
		FormElement page = new FormElement(controller);
		
		textFieldMapping.put("minValue", page.addForm("Min value", 0, "minValue"));
		textFieldMapping.put("maxValue", page.addForm("Max value", 20, "maxValue"));
		textFieldMapping.put("minResult", page.addForm("Min result", 0, "minResult"));
		textFieldMapping.put("maxResult", page.addForm("Max result", 20, "maxResult"));
		textFieldMapping.put("numberOfConstants", page.addForm("# of constants", 2, "numberOfConstants"));
		
		
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"Math Setup"));
		panel.add(page);
		return panel;
	}


	private JPanel buildPagePanel() {
		JPanel panel = new JPanel();
		FormElement page = new FormElement(controller);
		
		textFieldMapping.put("lines", page.addForm("Lines", 3, "lines"));
		textFieldMapping.put("columns", page.addForm("Columns", 4, "columns"));
		textFieldMapping.put("tabs", page.addForm("Tabs", 4, "tabs"));
		textFieldMapping.put("numberOfProblems", page.addForm("# of problems", 4, "numberOfProblems"));
		
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"Page Setup"));
		panel.add(page);
		return panel;
	}
	
	private class DataModelsListener implements PropertyChangeListener{
		private MathModel math;
		private PageModel page;
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			JTextField fieldToChange = textFieldMapping.get(evt.getPropertyName());
			System.out.println("aa");
			if(fieldToChange != null){
				fieldToChange.setText((String) evt.getNewValue());
				System.out.println(fieldToChange + " changed to " + (String) evt.getNewValue());
			}
			
		}

	}
	
}
