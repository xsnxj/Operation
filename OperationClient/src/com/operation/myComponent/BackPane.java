package com.operation.myComponent;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public class BackPane extends JPanel{
	public BackPane() {
		super();
		this.setBackground(null);
		this.setOpaque(false);
	}

	public BackPane(boolean arg0) {
		super(arg0);
		this.setBackground(null);
		this.setOpaque(false);
	}

	public BackPane(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		this.setBackground(null);
		this.setOpaque(false);
	}

	public BackPane(LayoutManager arg0) {
		super(arg0);
		this.setBackground(null);
		this.setOpaque(false);
	}
	
}