package com.marcneveling.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.binder.Binders;
import com.jgoodies.binding.binder.PresentationModelBinder;
import com.marcneveling.main.MathModel;
import com.marcneveling.main.PageModel;

public class SetupPanel extends JPanel {
	private MathModel mathModel;
	private PageModel pageModel;
	private GuiController controller;
	
	private JFormattedTextField minValField;
	private JFormattedTextField maxValField;
	private JFormattedTextField minResField;
	private JFormattedTextField maxResField;
	private JFormattedTextField numOfConstsField;
	private JFormattedTextField linesField;
	private JFormattedTextField columnsField;
	private JFormattedTextField tabsField;
	private JFormattedTextField numOfProbsField;
	
	
	
	public SetupPanel(MathModel mathModel, PageModel pageModel, GuiController controller){
		super();
		this.mathModel = mathModel;
		this.pageModel = pageModel;
		this.controller = controller;
		
		initComponents();
		initBinding();	
		
	}

	private void initComponents() {
		FormFactory factory = new FormFactory();
		
		JPanel mathPanel = new JPanel(new GridBagLayout());
		mathPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"Math Setup"));
		
		GridBagConstraints labelConstraints = factory.getLabelConstraints();
		mathPanel.add(factory.buildLabel("Min value"),labelConstraints);
		labelConstraints.gridy++;
		mathPanel.add(factory.buildLabel("Max value"),labelConstraints);
		labelConstraints.gridy++;
		mathPanel.add(factory.buildLabel("Min result"),labelConstraints);
		labelConstraints.gridy++;
		mathPanel.add(factory.buildLabel("Max result"),labelConstraints);
		labelConstraints.gridy++;
		mathPanel.add(factory.buildLabel("# of constants"),labelConstraints);
		
		GridBagConstraints textConstraints = factory.getTextConstraints();
		mathPanel.add(minValField = factory.buildTextInputField(0), textConstraints);
		textConstraints.gridy++;
		mathPanel.add(maxValField = factory.buildTextInputField(20), textConstraints);
		textConstraints.gridy++;
		mathPanel.add(minResField = factory.buildTextInputField(0), textConstraints);
		textConstraints.gridy++;
		mathPanel.add(maxResField = factory.buildTextInputField(20), textConstraints);
		textConstraints.gridy++;
		mathPanel.add(numOfConstsField = factory.buildTextInputField(2), textConstraints);
		
		JPanel pagePanel = new JPanel(new GridBagLayout());
		pagePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"Page Setup"));
		
		labelConstraints = factory.getLabelConstraints();
		pagePanel.add(factory.buildLabel("Lines"),labelConstraints);
		labelConstraints.gridy++;
		pagePanel.add(factory.buildLabel("Columns"),labelConstraints);
		labelConstraints.gridy++;
		pagePanel.add(factory.buildLabel("Tabs"),labelConstraints);
		labelConstraints.gridy++;
		pagePanel.add(factory.buildLabel("# of problems"),labelConstraints);
		
		
		textConstraints = factory.getTextConstraints();
		pagePanel.add(linesField = factory.buildTextInputField(3), textConstraints);
		textConstraints.gridy++;
		pagePanel.add(columnsField = factory.buildTextInputField(4), textConstraints);
		textConstraints.gridy++;
		pagePanel.add(tabsField = factory.buildTextInputField(4), textConstraints);
		textConstraints.gridy++;
		pagePanel.add(numOfProbsField = factory.buildTextInputField(12), textConstraints);
		
		add(pagePanel);
		add(mathPanel);
		
	}
	
	private void initBinding() {
		PresentationModelBinder pageBinder = Binders.binderFor(new PresentationModel<PageModel>(pageModel));
		pageBinder.bindBeanProperty(PageModel.PAGE_SETUP_LINES).to(linesField);
		pageBinder.bindBeanProperty(PageModel.PAGE_SETUP_COLUMNS).to(columnsField);
		pageBinder.bindBeanProperty(PageModel.PAGE_SETUP_TABS).to(tabsField);
		pageBinder.bindBeanProperty(PageModel.PAGE_SETUP_NUMBER_OF_PROBLEMS).to(numOfProbsField);
		
		PresentationModelBinder mathBinder = Binders.binderFor(new PresentationModel<MathModel>(mathModel));
		mathBinder.bindBeanProperty(MathModel.MATH_SETUP_MIN_VAL).to(minValField);
		mathBinder.bindBeanProperty(MathModel.MATH_SETUP_MAX_VAL).to(maxValField);
		mathBinder.bindBeanProperty(MathModel.MATH_SETUP_MIN_RES).to(minResField);
		mathBinder.bindBeanProperty(MathModel.MATH_SETUP_MAX_RES).to(maxResField);
		mathBinder.bindBeanProperty(MathModel.MATH_SETUP_NUM_OF_CONSTS).to(numOfConstsField);
	}
	
}
