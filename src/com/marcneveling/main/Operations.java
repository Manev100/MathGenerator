package com.marcneveling.main;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public enum Operations {
	
	PLUS(KeyEvent.VK_PLUS ,0 ,'+', false), MINUS(KeyEvent.VK_MINUS,0, '-', false), TIMES(KeyEvent.VK_PLUS ,KeyEvent.VK_SHIFT ,'*', true), DIV(KeyEvent.VK_PERIOD ,KeyEvent.VK_SHIFT, ':', true);
	
	private int keyCode;
	private int modifier;
	private char sign;
	private boolean prioritized;
	
	
	private Operations(int keyCode, int modifier, char sign, boolean prioritized){
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
	
	public int result(int x, int y){
		if(sign == '+'){
			return x + y;
		}else if(sign == '-'){
			return x - y;
		}else if(sign == '/'){
			return x / y;
		}else{
			return x * y;
		}
	}
}
