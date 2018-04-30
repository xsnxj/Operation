package com.operation.mainframe;

import com.operation.appoint.MainOperationPane;
import com.operation.appoint.PatientPane;
import com.operation.common.Worker;
import com.operation.rpc.RPCHelper;
import com.operation.select.MainSelectPane;

public class InitComponent {
	//"�����Ҳ�ѯ", "���˲�ѯ", "ҽ���Ű��ѯ", "��ʿ�Ű��ѯ","����ʦ�Ű��ѯ","������ѯ","������ص�����"
	public static RPCHelper helper = null;
	public static Worker worker=null;
	public static PatientPane patientPane;//���Ӳ���
	public static MainSelectPane mainSelectPane;//����ѯ
	public static MainOperationPane mainOperationPane;//������
	public static void initClient() {
		patientPane=new PatientPane();
		mainSelectPane=new MainSelectPane();
		mainOperationPane=new MainOperationPane();
	}
}