package com.marcneveling.main;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.List;

public class MathModel {
	
	private List<Operations> operations;
	private int minValue;
	private int maxValue;
	private int maxResult;
	private int minResult;
	private int numberOfConstants;
	private PropertyChangeSupport mPcs = new PropertyChangeSupport(this);
	
	public MathModel(List<Operations> operations, int rangeX, int rangeY,
			int maxResult, int minResult, int numberOfConstants) {
		this.operations = operations;
		this.minValue = rangeX;
		this.maxValue = rangeY;
		this.maxResult = maxResult;
		this.minResult = minResult;
		this.numberOfConstants = numberOfConstants;
	}
	
	public MathModel() {
		this.operations = new LinkedList<Operations>();
		operations.add(Operations.PLUS);
		this.minValue = 0;
		this.maxValue = 20;
		this.maxResult = 20;
		this.minResult = 0;
		this.setNumberOfConstants(3);
	}
	
	public List<Operations> getOperations() {
		return operations;
	}
	public void addOperation(Operations operation) {
		if(!operations.contains(operation)){
			List<Operations> oldList = new LinkedList<Operations>(operations);
			operations.add(operation);
			mPcs.firePropertyChange("operations", oldList, operations);
		}
	}
	public void removeOperation(Operations operation) {
		if(operations.contains(operation)){
			List<Operations> oldList = new LinkedList<Operations>(operations);
			operations.remove(operation);
			mPcs.firePropertyChange("operations", oldList, operations);
		}
	}
	public int getMinValue() {
		return minValue;
	}
	public void setMinValue(int rangeX) {
		int oldVal = this.minValue;
		this.minValue = rangeX;
		mPcs.firePropertyChange("minValue", oldVal, minValue);
	}
	public int getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(int rangeY) {
		int oldVal = this.maxValue;
		this.maxValue = rangeY;
		mPcs.firePropertyChange("maxValue", oldVal, maxValue);
	}
	public int getMaxResult() {
		return maxResult;
	}
	public void setMaxResult(int maxResult) {
		int oldVal = this.maxResult;
		this.maxResult = maxResult;
		mPcs.firePropertyChange("maxResult", oldVal, maxResult);
	}
	public int getMinResult() {
		return minResult;
	}
	public void setMinResult(int minResult) {
		int oldVal = this.minResult;
		this.minResult = minResult;
		mPcs.firePropertyChange("minResult", oldVal, minResult);
	}

	public int getNumberOfConstants() {
		return numberOfConstants;
	}

	public void setNumberOfConstants(int numberOfConstants) {
		int oldVal = this.numberOfConstants;
		this.numberOfConstants = numberOfConstants;
		mPcs.firePropertyChange("numberOfConstants", oldVal, numberOfConstants);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
        mPcs.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        mPcs.removePropertyChangeListener(listener);
    }
	
	
}
