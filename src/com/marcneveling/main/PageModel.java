package com.marcneveling.main;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Field;

import com.jgoodies.binding.PresentationModel;
import com.marcneveling.gui.AbstractModel;

public class PageModel extends AbstractModel{
	
	public static final String PAGE_SETUP_LINES = "lines";
	public static final String PAGE_SETUP_COLUMNS = "columns";
	public static final String PAGE_SETUP_TABS = "tabs";
	public static final String PAGE_SETUP_NUMBER_OF_PROBLEMS = "numberOfProblems";
	
	private int lines;
	private int columns;
	private int tabs;
	private int numberOfProblems;
	
	private boolean changedFlag;
	
	public PageModel(int lines, int columns, int tabs, int numberOfProblems) {
		super();
		this.lines = lines;
		this.columns = columns;
		this.tabs = tabs;
		this.numberOfProblems = numberOfProblems;
		this.changedFlag = false;
		
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
		if(changedFlag == false){
            changedFlag = true;
            setNumberOfProblems(lines * columns);
            changedFlag = false;
        }
		
		firePropertyChange(PAGE_SETUP_LINES, oldLines, lines);
	}
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		int oldColumns = this.columns;
		this.columns = columns;
		if(changedFlag == false){
            changedFlag = true;
            setNumberOfProblems(lines * columns);
            changedFlag = false;
        }
		firePropertyChange(PAGE_SETUP_COLUMNS, oldColumns, columns);
	}
	public int getTabs() {
		return tabs;
	}
	public void setTabs(int tabs) {
		int oldTabs = this.tabs;
		this.tabs = tabs;
		firePropertyChange(PAGE_SETUP_TABS, oldTabs, tabs);
	}

	public int getNumberOfProblems() {
		return numberOfProblems;
	}

	public void setNumberOfProblems(int numberOfProblems) {
		int oldVal = this.numberOfProblems;
		this.numberOfProblems = numberOfProblems;
		if(changedFlag == false){
            changedFlag = true;
            int newLines = numberOfProblems / columns;
            if(numberOfProblems % columns != 0){
                newLines++;
            }
            setLines(newLines);
            changedFlag = false;
        }
		firePropertyChange(PAGE_SETUP_NUMBER_OF_PROBLEMS, oldVal, numberOfProblems);
	}
}
