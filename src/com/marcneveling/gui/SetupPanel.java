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
	
	public SetupPanel(MathModel mathModel, PageModel pageModel){
		super();
		textFieldMapping = new HashMap<>();
		this.mathModel = mathModel;
		this.pageModel = pageModel;
		add(buildPagePanel());
		add(buildMathPanel());
		this.mathModel.addPropertyChangeListener(new DataModelsListener());
		this.pageModel.addPropertyChangeListener(new DataModelsListener());	
	}
	
	
	private JPanel buildMathPanel() {
		JPanel panel = new JPanel();
		FormElement page = new FormElement();
		
		textFieldMapping.put("minValue", page.addForm("Min value", 0));
		textFieldMapping.put("maxValue", page.addForm("Max value", 20));
		textFieldMapping.put("minResult", page.addForm("Min result", 0));
		textFieldMapping.put("maxResult", page.addForm("Max result", 20));
		textFieldMapping.put("numberOfConstants", page.addForm("# of constants", 2));
		
		
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"Math Setup"));
		panel.add(page);
		return panel;
	}


	private JPanel buildPagePanel() {
		JPanel panel = new JPanel();
		FormElement page = new FormElement();
		
		textFieldMapping.put("lines", page.addForm("Lines", 3));
		textFieldMapping.put("columns", page.addForm("Columns", 4));
		textFieldMapping.put("tabs", page.addForm("Tabs", 4));
		textFieldMapping.put("numberOfProblems", page.addForm("# of problems", 4));
		
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
