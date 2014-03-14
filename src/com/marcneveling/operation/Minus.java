package com.marcneveling.operation;

import java.awt.event.KeyEvent;

public class Minus extends Operation {

	public Minus() {
		super(KeyEvent.VK_MINUS, 0, '-', false);
	}

	@Override
	public int result(int x, int y) {
		return x - y;
	}

}
