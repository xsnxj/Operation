package com.operation.appoint;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import com.operation.common.Operation;
import com.operation.myComponent.BackFrame;
import com.operation.myComponent.BackPane;
import com.operation.myComponent.BackScrollPane;

public class OperationListPane extends BackPane{
	Operation opTest=new Operation("id", "name", Date.valueOf("1980-1-1"), "roomId", "patientId", "doctorId", "nurseId", "anesthetistId", "doctorRecord", "nurseRecord", "anesthetistRecord");
	Vector<ExistOperation> ops=null;
	CardLayout fatherCard=null;
	BackPane fatherPane=null;
	public OperationListPane(CardLayout fatherCard, BackPane fatherPane) {
		this.fatherCard=fatherCard;
		this.fatherPane=fatherPane;
		this.setLayout(new GridLayout(10,1));
		for(int i=0;i<10;i++) {
			ExistOperation p=new ExistOperation(opTest,fatherCard,fatherPane);
			p.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			p.addMouseListener(new MouseHoverListener(p));
			this.add(p);
		}
	}
	private class MouseHoverListener extends MouseAdapter{
		private BackPane c=null;
		public MouseHoverListener(BackPane c) {
			this.c=c;
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			c.setBackground(null);
			c.setForeground(Color.BLACK);
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
			c.setBackground(Color.red);
			c.setForeground(Color.YELLOW);
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			fatherCard.show(fatherPane, "手术编辑");
		}
	}
	public static void main(String[] args) {
		JFrame f =new BackFrame("test","./imgs/bg2.jpg");
		JScrollPane jsp = new BackScrollPane(new OperationListPane(null	,null));
		jsp.getVerticalScrollBar().setUnitIncrement(20);
		jsp.setWheelScrollingEnabled(true);
		f.add(jsp);
		// 设置大小和显示类型
		f.setBounds(200, 100, 800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
