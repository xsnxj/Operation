package com.operation.mainframe;

import com.operation.appoint.MainOperationPane;
import com.operation.appoint.PatientPane;
import com.operation.common.Worker;
import com.operation.rpc.RPCHelper;
import com.operation.select.MainSelectPane;

public class InitComponent {
	//"手术室查询", "病人查询", "医生排班查询", "护士排班查询","麻醉师排班查询","手术查询","与我相关的手术"
	public static RPCHelper helper = null;
	public static Worker worker=null;
	public static PatientPane patientPane;//添加病人
	public static MainSelectPane mainSelectPane;//主查询
	public static MainOperationPane mainOperationPane;//主手术
	public static void initClient() {
		patientPane=new PatientPane();
		mainSelectPane=new MainSelectPane();
		mainOperationPane=new MainOperationPane();
	}
}