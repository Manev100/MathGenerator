package com.marcneveling.operation;

import java.awt.event.KeyEvent;

public class Plus extends Operation {

	public Plus() {
		super(KeyEvent.VK_PLUS , 0, '+', false);
	}

	@Override
	public int result(int x, int y) {
		return x + y;
	}

}
