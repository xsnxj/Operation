 package com.operation.myComponent;

import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;

public class BackToolBar extends JToolBar{

	public BackToolBar() {
		super();
		this.setOpaque(false);
		this.setEnabled(false);
		this.setBorder(new EtchedBorder(EtchedBorder.RAISED));
	}

	public BackToolBar(int arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public BackToolBar(String arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public BackToolBar(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
