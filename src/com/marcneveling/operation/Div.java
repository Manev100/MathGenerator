package com.marcneveling.operation;

import java.awt.event.KeyEvent;

public class Div extends Operation {

	public Div() {
		super(KeyEvent.VK_PERIOD, KeyEvent.VK_SHIFT, ':', true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int result(int x, int y) {
		if(y == 0){
			throw new IllegalArgumentException("Trying to divide by 0!");
		}
		return x / y;
	}

}
