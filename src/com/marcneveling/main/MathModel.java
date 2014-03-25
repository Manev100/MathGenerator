package com.marcneveling.main;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.List;

import com.marcneveling.operation.Operation;
import com.marcneveling.operation.Plus;

public class MathModel {
	
	private List<Operation> operations;
	private int minValue;
	private int maxValue;
	private int maxResult;
	private int minResult;
	private int numberOfConstants;
	private PropertyChangeSupport mPcs = new PropertyChangeSupport(this);
	
	public MathModel(List<Operation> operations, int rangeX, int rangeY,
			int maxResult, int minResult, int numberOfConstants) {
		this.operations = operations;
		this.minValue = rangeX;
		this.maxValue = rangeY;
		this.maxResult = maxResult;
		this.minResult = minResult;
		this.numberOfConstants = numberOfConstants;
	}
	
	public MathModel() {
		this.operations = new LinkedList<Operation>();
		operations.add(new Plus());
		this.minValue = 0;
		this.maxValue = 20;
		this.maxResult = 20;
		this.minResult = 0;
		this.setNumberOfConstants(3);
	}
	
	public List<Operation> getOperations() {
		return operations;
	}
	public void addOperation(Operation operation) {
		if(!operations.contains(operation)){
			List<Operation> oldList = new LinkedList<Operation>(operations);
			operations.add(operation);
			mPcs.firePropertyChange("operations", oldList, operations);
		}
	}
	public void removeOperation(Operation operation) {
		if(operations.contains(operation)){
			List<Operation> oldList = new LinkedList<Operation>(operations);
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
