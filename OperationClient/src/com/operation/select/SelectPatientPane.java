package com.operation.select;

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

public class SelectPatientPane extends BackPane {
	BackToolBar toolBar;
	BackButton selectDate, selectName, selectId;
	CardLayout card;
	BackPane select;
	String selectType = "��ѯȫ��";
	JLabel beginDate;
	JTextField name;
	JTextField id;
	DataTableModel model;
	JTable table;
	Vector<Vector<String>> rowData = new Vector<Vector<String>>();

	public SelectPatientPane() {
		System.out.println("���벡�˲�ѯ����");
		this.setLayout(new BorderLayout());
		// ��:�߽粼��[������,��Ƭ����(��ѯȫ��,������ѯ,ID��ѯ),��ʼ��ѯ]
		BackPane north = new BackPane();
		north.setLayout(new BorderLayout());
		this.add(north, BorderLayout.NORTH);
		// 1.������
		toolBar = new BackToolBar();
		selectDate = new BackButton("��ѯȫ��");
		selectName = new BackButton("��������ѯ");
		selectId = new BackButton("��ID��ѯ");
		toolBar.add(selectDate);
		toolBar.add(selectName);
		toolBar.add(selectId);
		north.add(toolBar, BorderLayout.NORTH);
		// 2.��Ƭ����
		card = new CardLayout();
		select = new BackPane();
		select.setLayout(card);
		north.add(select, BorderLayout.CENTER);
		// (1).��ѯȫ��
		BackPane selectDatePane = new BackPane();
		JLabel jl1 = new JLabel("��ѯȫ������");
		selectDatePane.add(jl1);
		select.add(selectDatePane, "��ѯȫ��");
		// (2).������ѯ
		BackPane selectNamePane = new BackPane();
		JLabel jl3 = new JLabel("����:");
		name = new JTextField(10);
		selectNamePane.add(jl3);
		selectNamePane.add(name);
		select.add(selectNamePane, "��������ѯ");
		// (3).ID��ѯ
		BackPane selectIDPane = new BackPane();
		JLabel jl4 = new JLabel("ID:");
		id = new JTextField(10);
		selectIDPane.add(jl4);
		selectIDPane.add(id);
		select.add(selectIDPane, "��ID��ѯ");
		// 3.��ʼ��ѯ
		BackButton beginSelect = new BackButton("��ʼ��ѯ");
		north.add(beginSelect, BorderLayout.SOUTH);

		// ��:���ݱ���
		model = new DataTableModel();
		model.setColumnNames(getColumnNames());
		model.setRowData(getRowData());
		table = new JTable(model);
		BackScrollPane center = new BackScrollPane(table);
		this.add(center, BorderLayout.CENTER);

		// ��:��ʾ��ѯ����
		JLabel jl = new JLabel("��ѯ����");
		this.add(jl, BorderLayout.SOUTH);
		// ������
		// 1.��ѯ��ʽѡ�������
		selectDate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(select, "��ѯȫ��");
				selectType = "��ѯȫ��";
			}
		});
		selectName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(select, "��������ѯ");
				selectType = "��������ѯ";
			}
		});
		selectId.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(select, "��ID��ѯ");
				selectType = "��ID��ѯ";
			}
		});
		// 2.��ʼ��ѯ������
		beginSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (selectType.equals("��ѯȫ��")) {
					System.out.println("��ѯȫ��");
					if (InitComponent.helper == null) {
						JOptionPane.showMessageDialog(SelectPatientPane.this, "δ���ӷ�����!");
					} else {
						Vector<Patient> patients = InitComponent.helper.selectAllPatients();
						SelectPatientPane.this.setRowData(patients);
					}
				} else if (selectType.equals("��������ѯ")) {
					System.out.println("��������ѯ");
					if (InitComponent.helper == null) {
						JOptionPane.showMessageDialog(SelectPatientPane.this, "δ���ӷ�����!");
					} else {
						Vector<Patient> patients = InitComponent.helper.selectPatientByName(name.getText());
						SelectPatientPane.this.setRowData(patients);
					}

				} else if (selectType.equals("��ID��ѯ")) {
					System.out.println("��ID��ѯ");
					if (InitComponent.helper == null) {
						JOptionPane.showMessageDialog(SelectPatientPane.this, "δ���ӷ�����!");
					} else {
						Patient patient = InitComponent.helper.selectPatientById(id.getText());
						SelectPatientPane.this.setRowData(patient);
					}

				}
			}
		});

	}

	public Vector<String> getColumnNames() {
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("ID");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("��������");
		columnNames.add("��ϵ��ʽ");
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
		SelectPatientPane p = new SelectPatientPane();
		JFrame f = new BackFrame("test", "./imgs/bg2.jpg");
		f.add(p);
		f.setBounds(200, 100, 800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.repaint();
	}
}