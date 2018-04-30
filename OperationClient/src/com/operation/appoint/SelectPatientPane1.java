package com.operation.appoint;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.operation.common.Patient;
import com.operation.common.Worker;
import com.operation.mainframe.InitComponent;
import com.operation.myComponent.BackButton;
import com.operation.myComponent.BackFrame;
import com.operation.myComponent.BackPane;
import com.operation.myComponent.BackScrollPane;
import com.operation.myComponent.BackToolBar;
import com.operation.select.DataTableModel;

public class SelectPatientPane1 extends BackPane {
	BackButton backParent, submit;
	BackToolBar toolBar;
	BackButton selectDate, selectName, selectId;
	CardLayout card;
	BackPane select;
	String selectType = "查询全部";
	JLabel beginDate;
	JTextField name;
	JTextField id;
	DataTableModel model;
	JTable table;
	Vector<Vector<String>> rowData = new Vector<Vector<String>>();
	AppointPane parentPane=null;
	public SelectPatientPane1(final AppointPane parentPane) {
		this.parentPane=parentPane;
		System.out.println("进入病人选择界面");
		this.setLayout(new BorderLayout());
		// 上:边界布局[工具条,卡片布局(查询全部,姓名查询,ID查询),开始查询]
		BackPane north = new BackPane();
		north.setLayout(new BorderLayout());
		this.add(north, BorderLayout.NORTH);
		// 1.工具条
		toolBar = new BackToolBar();
		selectDate = new BackButton("查询全部");
		selectName = new BackButton("按姓名查询");
		selectId = new BackButton("按ID查询");
		toolBar.add(selectDate);
		toolBar.add(selectName);
		toolBar.add(selectId);
		north.add(toolBar, BorderLayout.NORTH);
		// 2.卡片布局
		card = new CardLayout();
		select = new BackPane();
		select.setLayout(card);
		north.add(select, BorderLayout.CENTER);
		// (1).查询全部
		BackPane selectDatePane = new BackPane();
		JLabel jl1 = new JLabel("查询全部病人");
		selectDatePane.add(jl1);
		select.add(selectDatePane, "查询全部");
		// (2).姓名查询
		BackPane selectNamePane = new BackPane();
		JLabel jl3 = new JLabel("姓名:");
		name = new JTextField(10);
		selectNamePane.add(jl3);
		selectNamePane.add(name);
		select.add(selectNamePane, "按姓名查询");
		// (3).ID查询
		BackPane selectIDPane = new BackPane();
		JLabel jl4 = new JLabel("ID:");
		id = new JTextField(10);
		selectIDPane.add(jl4);
		selectIDPane.add(id);
		select.add(selectIDPane, "按ID查询");
		// 3.开始查询
		BackButton beginSelect = new BackButton("开始查询");
		north.add(beginSelect, BorderLayout.SOUTH);

		// 中:数据表格
		model = new DataTableModel();
		model.setColumnNames(getColumnNames());
		model.setRowData(getRowData());
		table = new JTable(model);
		BackScrollPane center = new BackScrollPane(table);
		this.add(center, BorderLayout.CENTER);

		// 监听器
		// 1.查询方式选择监听器
		selectDate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(select, "查询全部");
				selectType = "查询全部";
			}
		});
		selectName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(select, "按姓名查询");
				selectType = "按姓名查询";
			}
		});
		selectId.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(select, "按ID查询");
				selectType = "按ID查询";
			}
		});
		// 2.开始查询监听器
		beginSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (selectType.equals("查询全部")) {
					System.out.println("查询全部");
					if (InitComponent.helper == null) {
						JOptionPane.showMessageDialog(SelectPatientPane1.this, "未连接服务器!");
					} else {
						Vector<Patient> patients = InitComponent.helper.selectAllPatients();
						SelectPatientPane1.this.setRowData(patients);
					}
				} else if (selectType.equals("按姓名查询")) {
					System.out.println("按姓名查询");
					if (InitComponent.helper == null) {
						JOptionPane.showMessageDialog(SelectPatientPane1.this, "未连接服务器!");
					} else {
						Vector<Patient> patients = InitComponent.helper.selectPatientByName(name.getText());
						SelectPatientPane1.this.setRowData(patients);
					}

				} else if (selectType.equals("按ID查询")) {
					System.out.println("按ID查询");
					if (InitComponent.helper == null) {
						JOptionPane.showMessageDialog(SelectPatientPane1.this, "未连接服务器!");
					} else {
						Patient patient = InitComponent.helper.selectPatientById(id.getText());
						SelectPatientPane1.this.setRowData(patient);
					}

				}
			}
		});

		// 下:提交,返回
		backParent = new BackButton("返回");
		submit = new BackButton("选择");
		BackPane bottom = new BackPane();
		bottom.add(backParent);
		bottom.add(submit);
		this.add(bottom, BorderLayout.SOUTH);		
		// 3.选择/返回监听器
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int select=table.getSelectedRow();
				if(select<0) {
					JOptionPane.showMessageDialog(SelectPatientPane1.this, "未选择病人!");
					return;
				}
				String str=(String) model.getValueAt(select, 0);
				Patient patient=InitComponent.helper.selectPatientById(str);
				JOptionPane.showMessageDialog(SelectPatientPane1.this, "选择了"+patient.getName()+"!");
				parentPane.showMainWithPatient(patient);
			}
		});
		backParent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parentPane.showMain();
			}
		});
	}

	public Vector<String> getColumnNames() {
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("ID");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("出生日期");
		columnNames.add("联系方式");
		return columnNames;
	}

	public Vector<Vector<String>> getRowData() {
		return rowData;
	}

	public void setRowData(Vector<Patient> patients) {
		Vector<Vector<String>> datas = new Vector<Vector<String>>();
		if (patients != null && patients.size() != 0)
			for (int i = 0; i < patients.size(); i++) {
				datas.add(patients.get(i).toVector());
			}
		this.rowData = datas;
		model.setRowData(rowData);
		model.fireTableDataChanged();
	}

	public void setRowData(Patient patient) {
		Vector<Vector<String>> datas = new Vector<Vector<String>>();
		if (patient != null)
			datas.add(patient.toVector());
		this.rowData = datas;
		model.setRowData(rowData);
		model.fireTableDataChanged();
	}

	public static void main(String[] args) {
		SelectPatientPane1 p = new SelectPatientPane1(null);
		JFrame f = new BackFrame("test", "./imgs/bg2.jpg");
		f.add(p);
		f.setBounds(200, 100, 800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.repaint();
	}
}