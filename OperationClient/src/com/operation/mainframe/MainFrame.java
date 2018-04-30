package com.operation.mainframe;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.operation.appoint.MainOperationPane;
import com.operation.appoint.PatientPane;
import com.operation.common.Message;
import com.operation.common.Operation;
import com.operation.common.Worker;
import com.operation.myComponent.BackFrame;
import com.operation.myComponent.BackPane;
import com.operation.myComponent.BackToolBar;
import com.operation.rpc.RPCHelper;
import com.operation.select.MainSelectPane;

public class MainFrame extends BackFrame {
	private JToolBar jtb = null;
	private JButton operationButton, patientButton, selectButton;
	private ToolBarListener toolBarListener = null;
	private JPanel jp = null;
	private RPCHelper helper = null;
	private String id = null;
	private String position = null;
	private Worker worker = null;

	private JToolBar createToolBar() {
		toolBarListener = new ToolBarListener();// ���ü�����
		JToolBar jtb = new BackToolBar();// �½�������
		// �½���Ť
		operationButton = new JButton("����");
		operationButton.addActionListener(toolBarListener);
		selectButton = new JButton("��ѯ");
		selectButton.addActionListener(toolBarListener);
		operationButton.setOpaque(false);
		selectButton.setOpaque(false);
		// ���Ӱ�Ť
		jtb.add(operationButton);
		jtb.add(selectButton);
		// �����ҽ���˺ţ�����������Ӳ���ѡ��
		if (this.position.equals("ҽ��")) {
			patientButton = new JButton("���Ӳ���");
			patientButton.addActionListener(toolBarListener);
			patientButton.setOpaque(false);
			jtb.add(patientButton);
		}
		return jtb;
	}

	CardLayout card=null;
	MainOperationPane mainOperationPane=null;
	private CardLayout createCardPanes(JPanel c) {
		CardLayout card=new CardLayout();
		c.setLayout(card);
		mainOperationPane=InitComponent.mainOperationPane;
		c.add(mainOperationPane, "����");
		c.add(InitComponent.patientPane,"����");
		c.add(InitComponent.mainSelectPane,"��ѯ");
		return card;
	}
	public MainFrame(RPCHelper helper, Worker worker) {
		super("����ԤԼ�Ű�ϵͳ", "./imgs/bg2.jpg");
		this.id = worker.getId();
		this.position = worker.getPosition();
		this.worker = worker;
		InitComponent.worker=worker;
		this.openGetMessagesThread();
		
		// �õ�RPCHelper
		this.helper = helper;
		InitComponent.helper=helper;
		// ���Ӵ��ڹرռ�����
		this.addWindowListener(new MyWindowListener());
		// ���ӹ�����
		jtb = createToolBar();
		this.add(jtb, BorderLayout.NORTH);
		// �������
		jp = new BackPane();
		this.add(jp, BorderLayout.CENTER);
		card=createCardPanes(jp);

		this.setBounds(200, 100, 800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	class ToolBarListener implements ActionListener {// ��������
		@Override
		public void actionPerformed(ActionEvent a) {
			if (a.getSource() == operationButton) {
				System.out.println("�����������Ť");
				card.show(jp, "����");
//				mainOperationPane.operationCard.show(mainOperationPane, "������");
			} else if (a.getSource() == patientButton) {
				System.out.println("����˲��˰�Ť");
				card.show(jp, "����");

			} else if (a.getSource() == selectButton) {
				System.out.println("����˲�ѯ��Ť");
				card.show(jp, "��ѯ");
			}
		}
	}

	class MyWindowListener extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {
			super.windowClosing(e);
			try {
				helper.close();
				System.out.println("�رտͻ���");

			} catch (Exception e2) {
				System.out.println(e2 + "�������ѹرգ�");
				System.exit(0);
			}
		}

	}

	private void openGetMessagesThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					Vector<Message> messages = helper.getMessages();
					String mes = null;
					ImageIcon imageIcon = new ImageIcon("./imgs/1.jpg");
					Object[] options = { "����", "�ܾ�" };
					for (Message m : messages) {
						int type = m.getMessageType();
						// �õ���Ϣ
						Operation op = helper.selectOperationById(m.getOperationId());
						String operationName = op.getName();
						Worker fromWorker = helper.selectWorkerById(m.getFromId());
						int choose = 0;
						switch (type) {
						case Message.CHOOSE:// �㱻������������
							mes = "ҽ��[" + fromWorker.getName() + "]���������[" + op.getBeginTime() + "]������[" + operationName
									+ "]!";
							// ����Ϣ����
							choose = JOptionPane.showOptionDialog(MainFrame.this, mes, "��������",
									JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, imageIcon, options,
									options[0]);
							if (choose == 0) {// ����
								helper.sendMessage(m.getFromId(), m.getOperationId(), Message.ACCEPT);
							} else if (choose == 1) {// �ܾ�
								helper.sendMessage(m.getFromId(), m.getOperationId(), Message.REFUSE);
								if (fromWorker.getPosition().equals("��ʿ")) {
									helper.updateNurseToOperation(m.getOperationId(), null);
								} else if (fromWorker.getPosition().equals("����ʦ")) {
									helper.updateAnesthetistToOperation(m.getOperationId(), null);
								}
							}
							break;
						case Message.ACCEPT:// �Է��������������
							mes = fromWorker.getPosition() + "[" + fromWorker.getName() + "]���������[" + operationName
									+ "]��������!";
							// ����Ϣ����
							JOptionPane.showMessageDialog(MainFrame.this, mes, "�Է�����������",
									JOptionPane.INFORMATION_MESSAGE, imageIcon);
							break;
						case Message.REFUSE:// �Է��ܾ����������
							mes = fromWorker.getPosition() + "[" + fromWorker.getName() + "�ܾ������[" + operationName
									+ "]��������,���ȷ��ϵͳ���Զ����������!";
							// ����Ϣ����
							JOptionPane.showMessageDialog(MainFrame.this, mes, "�Է��ܾ�������",
									JOptionPane.INFORMATION_MESSAGE, imageIcon);
							// �Զ�����Ա��
							Worker selectWorker = null;
							if (fromWorker.getPosition().equals("��ʿ")) {
								selectWorker = helper.autoSelectNurse(op.getBeginTime(), m.getFromId());
								if (selectWorker == null) {
									mes = "����û���пյĻ�ʿ,�����޸�����ʱ��!";
									JOptionPane.showMessageDialog(MainFrame.this, mes, "��Աæµ",
											JOptionPane.INFORMATION_MESSAGE, imageIcon);
									break;
								} else if (selectWorker.getId().equals(m.getFromId())) {
									mes = "���û�ʿ�⵱��û���пյĻ�ʿ,�����޸�����ʱ��!";
									JOptionPane.showMessageDialog(MainFrame.this, mes, "��Աæµ",
											JOptionPane.INFORMATION_MESSAGE, imageIcon);
									break;
								}
								helper.updateNurseToOperation(op.getId(), selectWorker.getId());
								helper.sendMessage(selectWorker.getId(), op.getId(), Message.AUTOCHOOSE);
								mes = "ϵͳΪ������˻�ʿ[" + selectWorker.getName() + "]!";
								JOptionPane.showMessageDialog(MainFrame.this, mes, "�Զ�����",
										JOptionPane.INFORMATION_MESSAGE, imageIcon);
							} else if (fromWorker.getPosition().equals("����ʦ")) {
								selectWorker = helper.autoSelectAnesthetist(op.getBeginTime(), m.getFromId());
								if (selectWorker == null) {
									mes = "����û���пյ�����ʦ,�����޸�����ʱ��!";
									JOptionPane.showMessageDialog(MainFrame.this, mes, "��Աæµ",
											JOptionPane.INFORMATION_MESSAGE, imageIcon);
									break;
								} else if (selectWorker.getId().equals(m.getFromId())) {
									mes = "��������ʦ�⵱��û���пյ�����ʦ,�����޸�����ʱ��!";
									JOptionPane.showMessageDialog(MainFrame.this, mes, "��Աæµ",
											JOptionPane.INFORMATION_MESSAGE, imageIcon);
									break;
								}
								helper.updateAnesthetistToOperation(op.getId(), selectWorker.getId());
								helper.sendMessage(selectWorker.getId(), op.getId(), Message.AUTOCHOOSE);
								mes = "ϵͳΪ�����������ʦ[" + selectWorker.getName() + "]!";
								JOptionPane.showMessageDialog(MainFrame.this, mes, "�Զ�����",
										JOptionPane.INFORMATION_MESSAGE, imageIcon);
							}
							break;
						case Message.NOTCHOOSE:// �㱻�Ӹ��������޳�
							mes = "[" + op.getBeginTime() + "]������[" + operationName + "]�ķ����߸����˲�����,��ע��!";
							// ����Ϣ����
							JOptionPane.showMessageDialog(MainFrame.this, mes, "���������߸���",
									JOptionPane.INFORMATION_MESSAGE, imageIcon);
							break;
						case Message.AUTOCHOOSE:// �����������߾ܾ��˲�������,�㱻�Զ�ѡ��Ϊѡ��Ϊ�����,���ɾܾ�
							mes = "[" + op.getBeginTime() + "]������[" + operationName
									+ "]ѡ��Ĳ����߾ܾ��˲�������,�㱻�Զ�ѡ��Ϊѡ��Ϊ�����,���ɾܾ�!";
							// ����Ϣ����
							JOptionPane.showMessageDialog(MainFrame.this, mes, "��������", JOptionPane.INFORMATION_MESSAGE,
									imageIcon);
							break;
						}
						// ��������Ƴ�����Ϣ
						helper.removeMessage(m);
					}

					// �ȴ�10��
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();
		;
	}

	public static void main(String[] args) {
		RPCHelper helper = null;
		Worker worker = null;

		helper = new RPCHelper();
		helper.login("w0001", "123");
		worker = helper.selectWorkerById("w0001");
		InitComponent.initClient();
		new MainFrame(helper, worker);
	}
}