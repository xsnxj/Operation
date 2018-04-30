package temp;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.operation.myComponent.BackFrame;
import com.operation.myComponent.BackPane;
import com.operation.select.SelectAnesthetistPane;
import com.operation.select.SelectNursePane;
import com.operation.select.SelectPatientPane;

//没做field取值
public class Appoint extends BackPane{
	
	private JTextField textField_patient;
	private JTextField textField_nurse;
	private JTextField textField_anesthetist;
	private AppointListener listener;
	private JButton button_select_patient;
	private JButton button_select_nurse;
	private JButton button_select_anesthetist;
	private JButton button_save;
	private JComboBox cb_select_name;private String select_name;
	private JComboBox cb_select_year;private String select_year;
	private JComboBox cb_select_month;private String select_month;
	private JComboBox cb_select_day;private String select_day;
	private JComboBox cb_select_stime;private String select_stime;
	private JComboBox cb_select_room;private String select_room;
		
	public BackPane panel_acenter;
	public BackPane j;
	
	
	public Appoint() {
		listener = new AppointListener();
		initialize();
	}
	
	class AppointListener implements ActionListener {//定义监听器类
		@Override
		public void actionPerformed(ActionEvent a) {
			if(a.getActionCommand().equals("button_select_patient")) {
				System.out.println("点击了病人选择按扭");
				j = panel_acenter;
				remove(panel_acenter);
				panel_acenter = new SelectPatientPane();
				panel_acenter.setBounds(0, 0, 580, 500);
				add(panel_acenter);
				validate();//更新布局
				repaint();//重绘画面
				//接受并显示回传信息后，继续执行代码
				/*JOptionPane.showMessageDialog(null, "恢复");
				
				remove(panel_center);
				panel_center = j;
				panel_center.setBounds(0, 0, 580, 500);
				add(panel_center);
				validate();//更新布局
				repaint();//重绘画面*/
				
			}else if(a.getActionCommand().equals("button_select_nurse")) {
				System.out.println("点击了护士选择按扭");
				j = panel_acenter;
				remove(panel_acenter);
				panel_acenter = new SelectNursePane();
				panel_acenter.setBounds(0, 0, 580, 500);
				add(panel_acenter);
				validate();//更新布局
				repaint();//重绘画面
				
				/*JOptionPane.showMessageDialog(null, "恢复");
				
				remove(panel_center);
				panel_center = j;
				panel_center.setBounds(0, 0, 580, 500);
				add(panel_center);
				validate();//更新布局
				repaint();//重绘画面*/
				
			}else if(a.getActionCommand().equals("button_select_anesthetist")) {
				System.out.println("点击了麻醉师选择按扭");
				j = panel_acenter;
				remove(panel_acenter);
				panel_acenter = new SelectAnesthetistPane();
				panel_acenter.setBounds(0, 0, 580, 500);
				add(panel_acenter);
				validate();//更新布局
				repaint();//重绘画面
				
				/*JOptionPane.showMessageDialog(null, "恢复");
				
				remove(panel_center);
				panel_center = j;
				panel_center.setBounds(0, 0, 580, 500);
				add(panel_center);
				validate();
				repaint();*/
			}else if(a.getActionCommand().equals("button_save")) {
				System.out.println("点击了保存按扭");
				//录入并保存信息
				JOptionPane.showMessageDialog(null, "保存成功");
				
				select_name = (String)cb_select_name.getSelectedItem();
				System.out.println("手术类型选择了"+select_name);
				select_year = (String)cb_select_year.getSelectedItem();
				System.out.println("年："+select_year);
				select_month = (String)cb_select_month.getSelectedItem();
				System.out.println("月："+select_month);	
				select_day = (String)cb_select_day.getSelectedItem();
				System.out.println("日："+select_day);
				select_stime = (String)cb_select_stime.getSelectedItem();
				System.out.println("手术时间选择了："+select_stime);
				select_room = (String)cb_select_room.getSelectedItem();
				System.out.println("手术室选择了："+select_room);
				
				
//				new ExistOperation();//待修改，在手术界面上显示新建预约手术的信息，新建后保存，新增NewOperation界面，下次查看时显示
								
//				new MainOperationPane();//待修改，用remove回到手术界面
			}
		}
	}
	
	
	private void initialize() {
		this.setLayout(null);	
		
		panel_acenter = new BackPane();
		panel_acenter.setBounds(10, 10, 580, 500);
		this.add(panel_acenter);
		panel_acenter.setLayout(null);
		panel_acenter.setVisible(true);
		
		JLabel label_title = new JLabel("手术预约");
		label_title.setFont(new Font("宋体", Font.PLAIN, 16));
		label_title.setBounds(100, 4, 109, 15);
		panel_acenter.add(label_title);
		
		
		JLabel label_patient = new JLabel("病人：");
		label_patient.setFont(new Font("宋体", Font.PLAIN, 15));
		label_patient.setBounds(45, 29, 87, 18);
		panel_acenter.add(label_patient);
		
		button_select_patient = new JButton();
		button_select_patient.setText("病人选择");
		button_select_patient.setBounds(142, 27, 110, 23);
		panel_acenter.add(button_select_patient);
		button_select_patient.setActionCommand("button_select_patient");
		button_select_patient.addActionListener(listener);

		textField_patient = new JTextField();
		textField_patient.setColumns(10);
		textField_patient.setBounds(142, 60, 235, 21);
		panel_acenter.add(textField_patient);
		

		JLabel label_name = new JLabel("手术名字：");
		label_name.setFont(new Font("宋体", Font.PLAIN, 15));
		label_name.setBounds(45, 92, 87, 18);
		panel_acenter.add(label_name);
		
		String[] name = {"----请选择手术名字----","第一种","第二种","第三种","第四种"};
		cb_select_name = new JComboBox(name);
		cb_select_name.setBounds(142, 91, 235, 21);
		panel_acenter.add(cb_select_name);
		
		
		
		JLabel label_date = new JLabel("手术日期：");
		label_date.setFont(new Font("宋体", Font.PLAIN, 15));
		label_date.setBounds(45, 120, 87, 18);
		panel_acenter.add(label_date);
		
		JLabel label_year = new JLabel("年：");
		label_year.setBounds(142, 124, 29, 15);
		panel_acenter.add(label_year);

		String[] year = {"2018","2019","2020","2021","2022"};
		cb_select_year = new JComboBox(year);
		cb_select_year.setBounds(167, 119, 56, 21);
		panel_acenter.add(cb_select_year);
		
				
		JLabel label_month = new JLabel("月：");
		label_month.setBounds(233, 123, 29, 15);
		panel_acenter.add(label_month);
		
		String[] month = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		cb_select_month = new JComboBox(month);
		cb_select_month.setBounds(256, 119, 56, 21);
		panel_acenter.add(cb_select_month);
		
		
		JLabel label_day = new JLabel("日：");
		label_day.setBounds(325, 123, 29, 15);
		panel_acenter.add(label_day);
		
		String[] day = {"1","2","3","4","5","6","7","8","9","10",
				"11","12","13","14","15","16","17","18","19","20",
				"21","22","23","24","25","26","27","28","29","30","31"};
		cb_select_day = new JComboBox(day);
		cb_select_day.setBounds(347, 119, 56, 21);
		panel_acenter.add(cb_select_day);
		
		
		
		
		JLabel label_time = new JLabel("手术时间：");
		label_time.setFont(new Font("宋体", Font.PLAIN, 15));
		label_time.setBounds(45, 148, 87, 18);
		panel_acenter.add(label_time);
		
		String[] time = {"00:00","01:00","02:00","03:00","04:00","05:00",
				"06:00","07:00","08:00","09:00","10:00","11:00",
				"12:00","13:00","14:00","15:00","16:00","17:00",
				"18:00","19:00","20:00","21:00","22:00","23:00"};
		
		cb_select_stime = new JComboBox(time);
		cb_select_stime.setBounds(142, 147, 113, 21);
		panel_acenter.add(cb_select_stime);
		
		
		
		JLabel label_room = new JLabel("手术室：");
		label_room.setFont(new Font("宋体", Font.PLAIN, 15));
		label_room.setBounds(45, 178, 87, 18);
		panel_acenter.add(label_room);
		
		String[] room = {"1号","2号","3号","4号","5号","6号","7号","8号","9号","10号",
				         "11号","12号","13号","14号","15号"};
		
		cb_select_room = new JComboBox(room);
		cb_select_room.setBounds(142, 177, 113, 21);
		panel_acenter.add(cb_select_room);
		
		
		JLabel label_nurse = new JLabel("护士：");
		label_nurse.setFont(new Font("宋体", Font.PLAIN, 15));
		label_nurse.setBounds(45, 206, 87, 18);
		panel_acenter.add(label_nurse);
		
		button_select_nurse = new JButton("护士选择");
		button_select_nurse.setBounds(142, 204, 110, 23);
		panel_acenter.add(button_select_nurse);
		//这里设置了监听器,以及指令名,建议把需要操作的组件声明为成员变量,这样会方便其它方法找到这个组件,详见Message.java******************
		button_select_nurse.setActionCommand("button_select_nurse");
		button_select_nurse.addActionListener(listener);
		
		textField_nurse = new JTextField();
		textField_nurse.setBounds(142, 229, 235, 21);
		panel_acenter.add(textField_nurse);
		textField_nurse.setColumns(10);
		
		
		JLabel label_anesthetist = new JLabel("麻醉师：");
		label_anesthetist.setFont(new Font("宋体", Font.PLAIN, 15));
		label_anesthetist.setBounds(45, 262, 87, 18);
		panel_acenter.add(label_anesthetist);
		
		button_select_anesthetist = new JButton("麻醉师选择");
		button_select_anesthetist.setBounds(142, 260, 110, 23);
		panel_acenter.add(button_select_anesthetist);
		button_select_anesthetist.setActionCommand("button_select_anesthetist");
		button_select_anesthetist.addActionListener(listener);
		
		textField_anesthetist = new JTextField();
		textField_anesthetist.setColumns(10);
		textField_anesthetist.setBounds(142, 287, 235, 21);
		panel_acenter.add(textField_anesthetist);
		
		
		button_save = new JButton("保存");
		button_save.setBounds(143, 352, 93, 23);
		panel_acenter.add(button_save);
		button_save.setActionCommand("button_save");
		button_save.addActionListener(listener);
		
	}
	public static void main(String[] args) {
		JFrame f =new BackFrame("test","./imgs/bg2.jpg");
//		f.setLayout(new FlowLayout());
		f.add(new Appoint());
		// 设置大小和显示类型
		f.setBounds(200, 100, 800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
