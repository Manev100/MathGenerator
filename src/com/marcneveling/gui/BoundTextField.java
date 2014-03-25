package com.marcneveling.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTextField;
import javax.swing.text.Document;

public class BoundTextField extends JTextField implements
		PropertyChangeListener {
	private String field;
	
	
	public BoundTextField(String field) {
		super();
		this.field = field;
	}
	
	public String getField(){
		return field;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if(e.getPropertyName().equals(field)){
			setText((String) e.getNewValue());
		}
	}
	
	

}
