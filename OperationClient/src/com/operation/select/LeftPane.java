package com.operation.select;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import com.operation.myComponent.BackFrame;
import com.operation.myComponent.BackPane;
import com.operation.myComponent.BackScrollPane;

public class LeftPane extends BackPane {
	private static String[] names = { "�����Ҳ�ѯ", "���˲�ѯ", "ҽ���Ű��ѯ", "��ʿ�Ű��ѯ","����ʦ�Ű��ѯ","������ѯ","������ص�����"};
	private JLabel[] labels=new JLabel[names.length];
	private Font font=new Font("����",Font.BOLD,20);
	RightPane controlPanel;
	public LeftPane(RightPane controlPanel) {
		this.controlPanel=controlPanel;
		this.setLayout(new GridLayout(10,1));
		initGUI();
	}
	private void initGUI() {
		for(int i=0;i<names.length;i++) {
			labels[i]=new JLabel(names[i]);
			labels[i].addMouseListener(new MouseHoverListener(labels[i]));
			labels[i].setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			labels[i].setFont(font);
			
			this.add(labels[i]);
		}
	}
	private class MouseHoverListener extends MouseAdapter{
		private JLabel c=null;
		public MouseHoverListener(JLabel c) {
			this.c=c;
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			c.setBackground(null);
			c.setForeground(Color.BLACK);
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			c.setBackground(Color.red);
			c.setForeground(Color.YELLOW);
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			controlPanel.getCard().show(controlPanel, c.getText());
		}
	}


	public static void main(String[] args) {
		JFrame f =new BackFrame("test","./imgs/bg2.jpg");
		JScrollPane jsp = new BackScrollPane(new LeftPane(null));
		jsp.getVerticalScrollBar().setUnitIncrement(20);
		jsp.setWheelScrollingEnabled(true);
		f.add(jsp);
		// ���ô�С����ʾ����
		f.setBounds(200, 100, 200, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}