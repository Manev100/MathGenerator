package com.marcneveling.main;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;

public class MathRobot {
	Robot robot;
	public MathRobot(){
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void typeMathProblem(int x, int y, Operations operation){
		keyTypeNumber(x);
		keyType(KeyEvent.VK_SPACE);
		keyTypeOperation(operation);
		keyType(KeyEvent.VK_SPACE);
		keyTypeNumber(y);
		keyType(KeyEvent.VK_SPACE);
		
		robot.keyPress(KeyEvent.VK_SHIFT);
		keyType(KeyEvent.VK_0);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		
		keyType(KeyEvent.VK_TAB);
	}
	
	public void typeMathProblem(MathProblem problem){
		Iterator<Integer> constants = problem.getConstantsIterator();
		Iterator<Operations> operations = problem.getOperationsIterator();
		while(constants.hasNext()){
			keyTypeNumber(constants.next());
			keyType(KeyEvent.VK_SPACE);
			if(operations.hasNext()){
				keyTypeOperation(operations.next());
				keyType(KeyEvent.VK_SPACE);
			}
		}

		robot.keyPress(KeyEvent.VK_SHIFT);
		keyType(KeyEvent.VK_0);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		
		keyType(KeyEvent.VK_TAB);
	}
	
	public void keyTypeNumber(int number){
		int[] digits = getDigitsAsArray(number);
		for(int digit: digits){
			keyType(numberToKeyCode(digit));
		}
	}
	
	public void keyTypeOperation(Operations operation){
		if(operation.getModifier() != 0){
			robot.keyPress(operation.getModifier());
			keyType(operation.getkeyCode());
			robot.keyRelease(operation.getModifier());
			
		}else{
			keyType(operation.getkeyCode());
		}
	}
	
	public void tab(int tabs) {
		for (int i = 0; i < tabs; i++) {
			keyType(KeyEvent.VK_TAB);
		}
	}
	
	public void tab() {
		tab(1);
	}

	public void enter() {
		keyType(KeyEvent.VK_ENTER);
	}
	
		
	private void keyType(int keycode){
		robot.keyPress(keycode);
		robot.keyRelease(keycode);
	}
	private int numberToKeyCode(int n){
		if(n < 0 || n > 9){
			throw new IllegalArgumentException("Out of Range");
		}
		return 48 + n;
	}
	private int[] getDigitsAsArray(long num){
		if(num == 0L){
			int[] result = {0};
			return result;
		}
		LinkedList<Integer> digits = new LinkedList<>();
		long number = num;
		while(number>0){
			digits.addFirst((int)number%10);
			number/=10;
		}
		int[] digitsArray = new int[digits.size()];
		for(int i =0; i< digits.size(); i++){
			digitsArray[i] = digits.get(i);
		}
		return digitsArray;
	}

	
}
