package com.operation.GUI;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import com.operation.server.ServerThread;
import com.operation.server.ThreadPool;

/**
 * 本类为服务器主类,运行该类启动服务器
 */
public class MainFrame extends JFrame {
	private static final int THREAD_NUM = 3;// 服务器同时开启的等待连接的线程数(已连接到客户端的线程不计入内)
	public static boolean opened = false;// 判断是否已开启服务器

	private JToolBar jtb;
	private JButton jb_open, jb_close;
	private MyListener myListener;
	private JPanel jp;
	private static ServerTableModel model = new ServerTableModel();
	private static JTable threadTable = new JTable(model);
	private static JScrollPane jsp = null;

	private JToolBar createToolBar() {
		myListener = new MyListener();// 设置监听器
		JToolBar jtb = new JToolBar();// 新建工具条
		// 新建按扭
		jb_open = new JButton("开启服务器");
		jb_open.addActionListener(myListener);
		jb_close = new JButton("关闭服务器");
		jb_close.addActionListener(myListener);
		// jb3 = new JButton("正在连接的线程");
		// jb3.addActionListener(myListener);
		// 添加按扭
		jtb.add(jb_open);
		jtb.add(jb_close);
		// jtb.add(jb3);
		return jtb;
	}

	public MainFrame() {
		super("服务器");
		jtb = createToolBar();
		this.add(jtb, BorderLayout.NORTH);
		jp = new JPanel();
		this.add(jp, BorderLayout.CENTER);
		this.setBounds(200, 100, 800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	class MyListener implements ActionListener {// 监听器类
		@Override
		public void actionPerformed(ActionEvent a) {
			if (a.getSource() == jb_open) {
				if (opened) {
					JOptionPane.showMessageDialog(MainFrame.this, "服务器不能重复启动!");
					return;
				}
				System.out.println("点击了开启服务器");
				open();
				remove(jp);
				jsp = new JScrollPane(threadTable);
				add(jsp);
				validate();
				repaint();
			} else if (a.getSource() == jb_close) {
				System.out.println("点击了关闭服务器");
				System.exit(0);
			}
		}
	}

	private void open() {
		if (opened) {
			JOptionPane.showMessageDialog(this, "服务器不能重复启动!");
			return;
		}
		opened = true;
		System.out.println("服务器正在启动...");
		// 建立一个开启连接客户端的线程的线程
		Thread t = new Thread() {
			public void run() {
				ServerSocket ss = null;
				try {
					ss = new ServerSocket(9999);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (ThreadPool.getWaitThreadsSize() < MainFrame.THREAD_NUM) {
						// Server server = new Server(ss);
						new Thread(new ServerThread(ss)).start();
					}
				}
			}
		};
		t.start();
	}

	private void close() {
		System.exit(0);
	}

	public synchronized static void refreshTable() {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				model.fireTableDataChanged();
			}
		});
	}

	public static void main(String[] args) {
		// 注册服务：把事先创建的RemoteServceImpl 对象加入到服务器的缓存中
		// Register.register(ServerHelper.class.getName(), new ServerHelper());
		new MainFrame();
	}

//	public void draw(Graphics g) {
//		Graphics2D g2d = (Graphics2D) g;
//		// 设置透明度
//		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f); // 最后一个参数代表不透明度 即 当 最后一个参数为 0.1f
//																						// 时 画笔的透明度就为90%
//		g2d.setComposite(ac);
//
//	}
//
//	@Override
//	public void paint(Graphics arg0) {
//		draw(arg0);
//		super.paint(arg0);
//	}

}
