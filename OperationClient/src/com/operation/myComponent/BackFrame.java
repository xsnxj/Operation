package com.operation.myComponent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BackFrame extends JFrame {

	PicBackPane contentPane=null;
	private void init(String picURL) {
		//设置容器
		contentPane=new PicBackPane(picURL);
		//设置自定义标题栏
		contentPane.add(new BackPane(), BorderLayout.CENTER);
		
		this.setContentPane(contentPane);
	}
	

	public BackFrame(String title,String picURL) {
		super(title);
		this.init(picURL);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		BackFrame f = new BackFrame("test","./imgs/bg2.jpg");
		f.setSize(1000, 600);
		f.setLocationByPlatform(true);
		f.setVisible(true);
	}
}
