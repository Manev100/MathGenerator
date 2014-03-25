package com.marcneveling.main;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.jgoodies.binding.PresentationModel;

public class PageModel{
	
	public static final String PAGE_SETUP_LINES = "lines";
	public static final String PAGE_SETUP_COLUMNS = "columns";
	public static final String PAGE_SETUP_TABS = "tabs";
	public static final String PAGE_SETUP_NUMBER_OF_PROBLEMS = "numberOfProblems";
	
	private int lines;
	private int columns;
	private int tabs;
	private int numberOfProblems;
	private PropertyChangeSupport mPcs = new PropertyChangeSupport(this);
	public PageModel(int lines, int columns, int tabs, int numberOfProblems) {
		this.lines = lines;
		this.columns = columns;
		this.tabs = tabs;
		this.numberOfProblems = numberOfProblems;
		
		
	}
	
	public PageModel() {
		this.lines = 20;
		this.columns = 2;
		this.tabs = 4;
		this.numberOfProblems = lines*columns;
	}
	
	public int getLines() {
		return lines;
	}
	public void setLines(int lines) {
		int oldLines = this.lines;
		this.lines = lines;
		mPcs.firePropertyChange(PAGE_SETUP_LINES, oldLines, lines);
	}
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		int oldColumns = this.columns;
		this.columns = columns;
		mPcs.firePropertyChange(PAGE_SETUP_COLUMNS, oldColumns, columns);
		setNumberOfProblems(lines * columns);
	}
	public int getTabs() {
		return tabs;
	}
	public void setTabs(int tabs) {
		int oldTabs = this.tabs;
		this.tabs = tabs;
		mPcs.firePropertyChange(PAGE_SETUP_TABS, oldTabs, tabs);
	}

	public int getNumberOfProblems() {
		return numberOfProblems;
	}

	public void setNumberOfProblems(int numberOfProblems) {
		int oldVal = this.numberOfProblems;
		this.numberOfProblems = numberOfProblems;
		mPcs.firePropertyChange(PAGE_SETUP_NUMBER_OF_PROBLEMS, oldVal, numberOfProblems);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
        mPcs.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        mPcs.removePropertyChangeListener(listener);
    }
}
