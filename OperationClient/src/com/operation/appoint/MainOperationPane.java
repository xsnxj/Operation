package com.operation.appoint;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.operation.common.Operation;
import com.operation.mainframe.InitComponent;
import com.operation.myComponent.BackButton;
import com.operation.myComponent.BackFrame;
import com.operation.myComponent.BackPane;
import com.operation.myComponent.BackScrollPane;

//未做自动调取ExustOperation()显示在此页面
public class MainOperationPane extends BackPane {
	BackButton jb = null;
	OperationInfoPane infoPane = null;
	BackPane contentPane = null;
	public CardLayout operationCard = null;
	AppointPane appointPane = null;
	OperationListPane operationListPane = null;

	public MainOperationPane() {
		// 设置卡片布局
		operationCard = new CardLayout();
		this.setLayout(operationCard);

		this.createMainPane();
		this.add(contentPane, "手术表");

		infoPane = new OperationInfoPane(this);
		this.add(infoPane, "手术编辑");

		appointPane = new AppointPane(this);
		this.add(appointPane, "新建预约");
	}

	public void showMain() {
		operationCard.show(this, "手术表");
	}

	public void showInfoPane(Operation operation) {
		infoPane.setOperaion(operation);
		operationCard.show(this, "手术编辑");
	}

	public void showMainWithOperation(Operation operation) {
		System.out.println(operation);
		// JOptionPane.showMessageDialog(this, "showMainWithOperation未写!");
		operationListPane.updateOperations();
		operationCard.show(this, "手术表");
	}
	//刷新手术列表,并重绘布局
	public void updateListPane() {
		operationListPane.updateOperations();
		operationListPane.validate();
		operationListPane.repaint();
	}

	//布局
	private void createMainPane() {
		contentPane = new BackPane();
		contentPane.setLayout(new BorderLayout());
		jb = new BackButton(new ImageIcon("./imgs/add.png"));
		//只有医生会显示新建按扭
		if (InitComponent.worker != null && InitComponent.worker.getPosition().equals("医生")) {
			BackPane p = new BackPane();
			p.add(jb);
			contentPane.add(p, BorderLayout.NORTH);
			jb.addActionListener(new ActionListener() {// 定义监听器类
				@Override
				public void actionPerformed(ActionEvent a) {
					if (a.getSource() == jb) {
						System.out.println("点击了新建预约按扭");
						appointPane.clearSelect();
						operationCard.show(MainOperationPane.this, "新建预约");
					}
				}
			});
		}
		operationListPane = new OperationListPane(this);
		operationListPane.updateOperations();
		contentPane.add(new BackScrollPane(operationListPane), BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		JFrame f = new BackFrame("test", "./imgs/bg2.jpg");
		f.add(new MainOperationPane());
		// 设置大小和显示类型
		f.setBounds(200, 100, 800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
