package com.marcneveling.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class FormElement extends JPanel {
	private JPanel content;
	private int numberOfElements;
	private int defaultVal;
	private GuiController controller;
	
	public FormElement(GuiController controller){
		super(new GridBagLayout());
		this.controller = controller;
		numberOfElements = 0;
		defaultVal = 0;
	}
	
	public JTextField addForm(String name, int defaultVal, String field){
		this.defaultVal = defaultVal;
		JLabel label = new JLabel(name);
		
		BoundTextField tf = new BoundTextField(field);
		tf.setText("" + defaultVal);
		tf.setColumns(5);
		tf.setInputVerifier(new NumberVerifier());
		
		//verify when pressing enter
		tf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BoundTextField source = (BoundTextField)arg0.getSource();
				controller.verifyInput(source);
				controller.commitChangesToModel(source, Integer.parseInt(source.getText()));
				
			}
		});
		
		tf.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				BoundTextField source = (BoundTextField) e.getSource();
				controller.verifyInput(source);
				controller.commitChangesToModel(source, Integer.parseInt(source.getText()));
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
			}
		});
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = numberOfElements;
		c.insets = new Insets(1, 10, 1, 1);
		c.fill = GridBagConstraints.HORIZONTAL;
		add(label,c);
		
		c.gridx = 1;
		c.gridy = numberOfElements;
		add(tf,c);
		numberOfElements++;
		return tf;
	}
	
	private class NumberVerifier extends InputVerifier{
		@Override
		public boolean verify(JComponent input) {
			JTextField tf = (JTextField) input;
			try{
				int num = Integer.parseInt(tf.getText());
				tf.setText("" + num);
			}catch(NumberFormatException e){
				tf.setText("" + defaultVal);
			}
			return true;
		}
		
	}
}
