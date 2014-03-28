package com.marcneveling.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.binder.Binders;
import com.jgoodies.binding.binder.PresentationModelBinder;
import com.marcneveling.main.MathModel;
import com.marcneveling.main.PageModel;
import com.marcneveling.operation.*;

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
	private JToggleButton plusButton;
	private JToggleButton minusButton;
	private JToggleButton timesButton;
	private JToggleButton divButton;
	
	
	
	
	public SetupPanel(MathModel mathModel, PageModel pageModel, GuiController controller){
		super();
		this.mathModel = mathModel;
		this.pageModel = pageModel;
		this.controller = controller;
		
		initComponents();
		initBinding();	
		initEventHandling();
		
		buildPanel();
	}

	

	private void initComponents() {
		FormFactory factory = new FormFactory();
		
		minValField = factory.buildTextInputField(0);
		maxValField = factory.buildTextInputField(20);
		minResField = factory.buildTextInputField(0);
		maxResField = factory.buildTextInputField(20);
		numOfConstsField = factory.buildTextInputField(2);
		
		linesField = factory.buildTextInputField(3);
		columnsField = factory.buildTextInputField(4);
		tabsField = factory.buildTextInputField(4);
		numOfProbsField = factory.buildTextInputField(12);
		
		plusButton = factory.buildToggleButton();
		minusButton = factory.buildToggleButton();
		timesButton = factory.buildToggleButton();
		divButton = factory.buildToggleButton();
	
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
	
	
	private void initEventHandling(){
		plusButton.setAction(controller.getColorChangeAction(new Plus()));
		minusButton.setAction(controller.getColorChangeAction(new Minus()));
		timesButton.setAction(controller.getColorChangeAction(new Times()));
		divButton.setAction(controller.getColorChangeAction(new Div()));
	}
	
	private void buildPanel() {
		add(buildPageSetupPanel());
		add(buildMathSetupPanel());
	}
	
	private JPanel buildMathSetupPanel(){
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
		mathPanel.add(minValField, textConstraints);
		textConstraints.gridy++;
		mathPanel.add(maxValField, textConstraints);
		textConstraints.gridy++;
		mathPanel.add(minResField, textConstraints);
		textConstraints.gridy++;
		mathPanel.add(maxResField, textConstraints);
		textConstraints.gridy++;
		mathPanel.add(numOfConstsField, textConstraints);
		
		JPanel operationsPanel = new JPanel();
		operationsPanel.setBorder(BorderFactory.createTitledBorder("Operations"));
		
		plusButton.setSelected(true);
		plusButton.setBackground(Color.GREEN);
		
		operationsPanel.add(plusButton);
		operationsPanel.add(minusButton);
		operationsPanel.add(timesButton);
		operationsPanel.add(divButton);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = textConstraints.gridy+1;
		gbc.gridwidth = 2;
		
		mathPanel.add(operationsPanel, gbc);
		
		return mathPanel;
		
	}
	
	private JPanel buildPageSetupPanel(){
		FormFactory factory = new FormFactory();
		
		JPanel pagePanel = new JPanel(new GridBagLayout());
		pagePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"Page Setup"));
		
		GridBagConstraints labelConstraints = factory.getLabelConstraints();
		pagePanel.add(factory.buildLabel("Lines"),labelConstraints);
		labelConstraints.gridy++;
		pagePanel.add(factory.buildLabel("Columns"),labelConstraints);
		labelConstraints.gridy++;
		pagePanel.add(factory.buildLabel("Tabs"),labelConstraints);
		labelConstraints.gridy++;
		pagePanel.add(factory.buildLabel("# of problems"),labelConstraints);
		
		GridBagConstraints textConstraints = factory.getTextConstraints();
		pagePanel.add(linesField, textConstraints);
		textConstraints.gridy++;
		pagePanel.add(columnsField, textConstraints);
		textConstraints.gridy++;
		pagePanel.add(tabsField, textConstraints);
		textConstraints.gridy++;
		pagePanel.add(numOfProbsField, textConstraints);
		
		
		
		return pagePanel;
	}
	
	
			
}
