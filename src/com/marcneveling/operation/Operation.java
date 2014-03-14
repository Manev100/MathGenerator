package com.marcneveling.operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Operation {
	public static final List<Operation> ALL_OPERATIONS = Arrays.asList(new Plus(), new Minus(), new Times(), new Div());
	private int keyCode;
	private int modifier;
	private char sign;
	private boolean prioritized;
	
	public Operation(int keyCode, int modifier, char sign, boolean prioritized) {
		this.keyCode = keyCode;
		this.modifier = modifier;
		this.sign = sign;
		this.prioritized = prioritized;
	}
	
	public int getkeyCode(){
		return keyCode;
	}
	
	public int getModifier(){
		return modifier;
	}
	
	public char getSign(){
		return sign;
	}
	
	public boolean isPrioritized(){
		return prioritized;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Operation){
			return ((Operation) o).getSign() == this.getSign();
		}else{
			return false;
		}
	}
	
	public abstract int result(int x, int y);

}
