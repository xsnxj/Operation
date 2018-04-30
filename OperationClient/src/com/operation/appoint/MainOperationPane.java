package com.operation.appoint;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.operation.common.Operation;
import com.operation.myComponent.BackButton;
import com.operation.myComponent.BackFrame;
import com.operation.myComponent.BackPane;
import com.operation.myComponent.BackScrollPane;

//δ���Զ���ȡExustOperation()��ʾ�ڴ�ҳ��
public class MainOperationPane extends BackPane{
	BackButton jb=null;
	OperationInfoPane infoPane=null;
	BackPane contentPane=null;
	public CardLayout operationCard=null;
	AppointPane appointPane=null;
	public MainOperationPane() {
		//���ÿ�Ƭ����
		operationCard=new CardLayout();
		this.setLayout(operationCard);
		
		this.createMainPane();
		this.add(contentPane, "������");
		
		infoPane=new OperationInfoPane();
		this.add(infoPane, "�����༭");
		
		appointPane=new AppointPane(this);
		this.add(appointPane,"�½�ԤԼ");
	}
	public void showMain() {
		operationCard.show(this, "������");
	}
	public void showMainWithOperation(Operation operation) {
		System.out.println(operation);
		System.out.println("showMainWithOperationδд");
		JOptionPane.showMessageDialog(this, "showMainWithOperationδд!");
		operationCard.show(this, "������");
	}
	private void createMainPane() {
		contentPane=new BackPane();
		contentPane.setLayout(new BorderLayout());
		jb=new BackButton(new ImageIcon("./imgs/add.png"));
//		jb.setText("�½�ԤԼ");
		BackPane p=new BackPane();
		p.add(jb);
		contentPane.add(p,BorderLayout.NORTH);
		jb.addActionListener(new ActionListener (){//�����������
			@Override
			public void actionPerformed(ActionEvent a) {
				if(a.getSource()==jb) {
					System.out.println("������½�ԤԼ��Ť");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
					appointPane.clearSelect();
					operationCard.show(MainOperationPane.this, "�½�ԤԼ");    
				}
			}
		});
		contentPane.add(new BackScrollPane(new OperationListPane(operationCard,this)),BorderLayout.CENTER);
	}
	
	
	public static void main(String[] args) {
		JFrame f =new BackFrame("test","./imgs/bg2.jpg");
		f.add(new MainOperationPane() );
		// ���ô�С����ʾ����
		f.setBounds(200, 100, 800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}