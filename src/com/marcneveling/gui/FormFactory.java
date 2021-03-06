package com.marcneveling.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;



public class FormFactory {

	public FormFactory() {
	}
	
	public JLabel buildLabel(String name){
		JLabel label = new JLabel(name);
		return label;
	}
	
	public JFormattedTextField buildTextInputField(int defaultVal){
		JFormattedTextField tf = new JFormattedTextField();
		
		tf.setValue(defaultVal);
		tf.setColumns(5);
		
		return tf;
	}
	
	public GridBagConstraints getLabelConstraints(){
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(1, 10, 1, 1);
		c.fill = GridBagConstraints.HORIZONTAL;
		return c;
	}
	
	public GridBagConstraints getTextConstraints(){
		GridBagConstraints c = getLabelConstraints();
		c.gridx = 1;
		return c;
	}
	
	public JToggleButton buildToggleButton(){
		JToggleButton tButton = new JToggleButton();
		
		tButton.setBackground(Color.RED);
		tButton.setContentAreaFilled(false);
		tButton.setOpaque(true);
		tButton.setFocusable(false);
		/*
		move to setupPanel
		tButton.setAction(new ColorChangeAction("+"));
		
		
		*/
		return tButton;
		
	}

}
