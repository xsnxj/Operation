package temp;

import java.sql.Date;
import java.util.Vector;

import com.operation.common.Operation;
import com.operation.common.Patient;
import com.operation.common.RemoteCall;
import com.operation.common.Worker;
import com.operation.mainframe.MainFrame;
import com.operation.rpc.RPCHelper;

public class TestRPCHelper {
	//叉掉后所有窗口一起关闭是因为这是一个程序，而MainFrame设置的是[this.setDefaultCloseOperation(EXIT_ON_CLOSE)]即叉掉后关闭程序
	public static void main(String[] args) {
		RPCHelper helper = null;
		Worker worker = null;

		System.out.println("**********************************************Login**********************************************************");
		helper = new RPCHelper();
		//向服务器发送登陆请求,服务器作出判断后返加一个int参数,返回的int参数意义见RPCHelper定义的静态变量
		helper.login("w0001", "123");
		// 根据Id查询Worker，返回null则表示id不存在
		worker = helper.selectWorkerById("w0001");
		new MainFrame(helper,worker);
		System.out.println("**********************************************Worker**********************************************************");
		// 查询某天有空的护士，返回null则表示该天没有有空的护士
		helper.selectNursesByDate(Date.valueOf("2018-5-18"));
		// 查询某天有空的麻醉师，返回null则表示该天没有有空的麻醉师
		helper.selectAnesthetistsByDate(Date.valueOf("2018-5-18"));
		// 增加员工,返回false表示增加失败
		helper.addWorker(new Worker("w1005", "123", "王九", "女", Date.valueOf("1995-5-5"), "护士", "158-0001-0005","儿科"));
		// 删除员工,返回false表示删除失败
		helper.deleteWorker("w1005");
		System.out.println("**********************************************Patient**********************************************************");
		// 查询所有病人,返回null表示没有病人
		helper.selectAllPatients();
		// 根据Id查询病人，返回null则表示id不存在
		helper.selectPatientById("p0001");
		// 根据名字查询病人，返回null则表示名字不存在
		helper.selectPatientByName("李四");
		// 增加病人,返回false表示增加失败
//		helper.addPatient(new Patient("p0005", "李八", "女", Date.valueOf("1993-8-8"), "135-0000-0050"));
		System.out.println("**********************************************Operation**********************************************************");
		// 查询所有手术,返回null表示没有手术
		helper.selectAllOperations();
		// 根据id查单个手术,返回null表示id不存在
		helper.selectOperationById("o0001");
		// 某天的所有手术,返回null表示当天没有手术
		helper.selectOperationByDate(Date.valueOf("2018-3-3"));
		// 查询自某日期之后的手术
		helper.selectOperationAfterDate(Date.valueOf("2018-3-3"));
		// 查两个日期之间的手术,返回null表示两个日期之间没有手术
		helper.selectOperationsBetween(Date.valueOf("2018-1-1"), Date.valueOf("2018-3-3")); 
		// 查某员工参与的全部手术,返回null表示员工没有手术
		helper.selectWorkerAllOperations("w0001");
		// 查某员工某日期参与的单个手术,返回null表示员工当天没有手术
		helper.selectWorkerOperationsByDate("w0001", Date.valueOf("2018-1-1"));
		// 查某员工两日期之间参与的全部手术,返回null表示员工两个日期之间没有手术
		helper.selectWorkerAllOperationsBetween("w0001", Date.valueOf("2018-1-1"), Date.valueOf("2018-3-3")); 
		// 查某病人参与的全部手术,返回null表示病人没有手术
		helper.selectPatientAllOperations("p0001");
		// 增加手术,返回false表示增加失败
//		helper.addOperation(new Operation("o0006","手术6",Date.valueOf("2018-5-20"),"14","p0001","w0001","w1001","w2001",null,null,null));
		// 添加或修改参与手术的医生,返回false表示修改失败
		helper.updateDoctorToOperation("o0001", "w0001");
		// 添加或修改参与手术的护士,返回false表示修改失败
		helper.updateNurseToOperation("o0001", "w1001");
		// 添加或修改参与手术的麻醉师,返回false表示修改失败
		helper.updateAnesthetistToOperation("o0001", "w2001"); 
		// 添加或修改医生手术记录,返回false表示修改失败
		helper.updateDoctorRecordToOperation("o0001", "医生记录1");
		// 添加或修改护士手术记录,返回false表示修改失败
		helper.updateNurseRecordToOperation("o0001", "护士记录1");
		// 添加或修改麻醉师手术记录,返回false表示修改失败
		helper.updateAnesthetistRecordToOperation("o0001", "麻醉师记录1");
		// 添加或修改手术室,返回false表示修改失败
		helper.updateRoomToOperation("o0001", "10");
		System.out.println("**********************************************Room**********************************************************");
		//查询某天有空的手术室,返回null表示当天没有手术室有空
		helper.selectRoomByDate(Date.valueOf("2018-1-1"));
		//查询某手术室被占用的时间,返回null表示该手术室没有任何一天在使用或id不存在
		helper.selectRoomBusyTimesById("10");
	
//		helper = new RPCHelper();
//		helper.login("w1001", "123");
//		worker = helper.selectWorkerById("w1001");
//		new MainFrame(helper,worker);
//		
//		helper = new RPCHelper();
//		helper.login("w2001", "123");
//		worker = helper.selectWorkerById("w2001");
//		new MainFrame(helper,worker);
		

	}
}
