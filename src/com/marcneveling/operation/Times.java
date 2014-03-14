package com.marcneveling.operation;

import java.awt.event.KeyEvent;

public class Times extends Operation {

	public Times() {
		super(KeyEvent.VK_PLUS, KeyEvent.VK_SHIFT, '*', true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int result(int x, int y) {
		return x * y;
	}

}
