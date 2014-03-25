package com.marcneveling.main;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import com.marcneveling.gui.AbstractModel;
import com.marcneveling.operation.Operation;
import com.marcneveling.operation.Plus;

public class MathModel extends AbstractModel{
	public static final String MATH_SETUP_MIN_VAL = "minValue";
	public static final String MATH_SETUP_MAX_VAL = "maxValue";
	public static final String MATH_SETUP_MIN_RES = "maxResult";
	public static final String MATH_SETUP_MAX_RES = "minResult";
	public static final String MATH_SETUP_NUM_OF_CONSTS = "numberOfConstants";

	
	private List<Operation> operations;
	private int minValue;
	private int maxValue;
	private int maxResult;
	private int minResult;
	private int numberOfConstants;
	
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
		super();
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
			firePropertyChange("operations", oldList, operations);
		}
	}
	public void removeOperation(Operation operation) {
		if(operations.contains(operation)){
			List<Operation> oldList = new LinkedList<Operation>(operations);
			operations.remove(operation);
			firePropertyChange("operations", oldList, operations);
		}
	}
	public int getMinValue() {
		return minValue;
	}
	public void setMinValue(int rangeX) {
		int oldVal = this.minValue;
		this.minValue = rangeX;
		firePropertyChange("minValue", oldVal, minValue);
	}
	public int getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(int rangeY) {
		int oldVal = this.maxValue;
		this.maxValue = rangeY;
		firePropertyChange("maxValue", oldVal, maxValue);
	}
	public int getMaxResult() {
		return maxResult;
	}
	public void setMaxResult(int maxResult) {
		int oldVal = this.maxResult;
		this.maxResult = maxResult;
		firePropertyChange("maxResult", oldVal, maxResult);
	}
	public int getMinResult() {
		return minResult;
	}
	public void setMinResult(int minResult) {
		int oldVal = this.minResult;
		this.minResult = minResult;
		firePropertyChange("minResult", oldVal, minResult);
	}

	public int getNumberOfConstants() {
		return numberOfConstants;
	}

	public void setNumberOfConstants(int numberOfConstants) {
		int oldVal = this.numberOfConstants;
		this.numberOfConstants = numberOfConstants;
		firePropertyChange("numberOfConstants", oldVal, numberOfConstants);
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(MATH_SETUP_MIN_VAL + " " + minValue + "\n");
		builder.append(MATH_SETUP_MAX_VAL + " " + maxValue + "\n");
		builder.append(MATH_SETUP_MIN_RES + " " + minResult + "\n");
		builder.append(MATH_SETUP_MAX_RES + " " + maxResult + "\n");
		builder.append(MATH_SETUP_NUM_OF_CONSTS + " " + numberOfConstants + "\n");
		return builder.toString();
	}
    
}
		
				
