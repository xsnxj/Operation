package com.operation.dao;

import java.sql.Date;
import java.util.Vector;

import com.operation.common.Operation;
import com.operation.common.Worker;

public class WorkerHelper {

	public WorkerHelper() {

	}

	// �ж�id�Ƿ����
	public boolean isIdExist(String id) {
		Vector<Vector<String>> data = new SqlHelper().query("select id from worker where id = ?", new String[] { id });
		if (data.size() == 0)
			return false;
		return true;
	}

	// ����Id��ѯWorker������null���ʾid������
	public Worker selectWorkerById(String id) {
		Vector<Vector<String>> data = new SqlHelper().query(
				"select id,password,name,sex,birth,position,call,section from worker where id=?", new String[] { id });
		if (data.size() == 0)
			return null;
		Worker worker = Worker.VectorToWorker(data.get(0));
		return worker;
	}
	// ����������ѯWorker������null���ʾid������
	public Vector<Worker> selectWorkerByName(String name) {
		Vector<Vector<String>> data = new SqlHelper().query(
				"select id,password,name,sex,birth,position,call,section from worker where name=?", new String[] { name });
		if (data.size() == 0)
			return null;
		Vector<Worker> workers = new Vector<Worker>();
		Worker worker = null;
		for (Vector e : data) {
			worker = Worker.VectorToWorker(e);
			workers.add(worker);
		}
		return workers;
	}

	// ��ѯĳ���пյĻ�ʿ������null���ʾ����û���пյĻ�ʿ
	public Vector<Worker> selectNursesByDate(Date date) {
		Vector<Vector<String>> data = new SqlHelper().query(
				"select id,password,name,sex,birth,position,call,section from worker where position = '��ʿ' and id not in (select nurseId from operation where beginTime = ?)",
				new String[] { date.toString() });
		if (data.size() == 0)
			return null;
		Vector<Worker> workers = new Vector<Worker>();
		Worker worker = null;
		for (Vector e : data) {
			worker = Worker.VectorToWorker(e);
			workers.add(worker);
		}
		return workers;
	}

	// ��ѯĳ���пյ�����ʦ������null���ʾ����û���пյ�����ʦ
	public Vector<Worker> selectAnesthetistsByDate(Date date) {
		Vector<Vector<String>> data = new SqlHelper().query(
				"select id,password,name,sex,birth,position,call,section from worker where position = '����ʦ' and id not in (select anesthetistId from operation where beginTime = ?)",
				new String[] { date.toString() });
		if (data.size() == 0)
			return null;
		Vector<Worker> workers = new Vector<Worker>();
		Worker worker = null;
		for (Vector e : data) {
			worker = Worker.VectorToWorker(e);
			workers.add(worker);
		}
		return workers;
	}

	// ����Ա��,����false��ʾ����ʧ��
	public boolean addWorker(Worker worker) {
		return new SqlHelper().update(
				"insert into worker (id,password,name,sex,birth,position,call,section) values(?, ?, ?, ?, ?, ?, ?,?)",
				worker.toStringList());
	}

	// ɾ��Ա��,����false��ʾɾ��ʧ��
	public boolean deleteWorker(String id) {
		return new SqlHelper().update("delete from worker where id = ?", new String[] { id });
	}

	// ��ѯ��refuseId��date+-3���������������ٵĻ�ʿ,����null��ʾ����û���пյĻ�ʿ,���ػ�ʿrefuseId��ʾ���˵����idû���пյĻ�ʿ
	public Worker autoSelectNurse(Date date, String refuseId) {
		Vector<Worker> nurses = new WorkerHelper().selectNursesByDate(date);
		if (nurses.size() == 0)
			return null;
		if(nurses.size()==1) {
			return nurses.get(0);
		}
		int size=8;
		Worker selectNurse=null;
		for (Worker nurse : nurses) {
			if(nurse.getId().equals(refuseId))
				continue;
			Vector<Operation> operations = new OperationHelper().selectWorkerAllOperationsBetween(nurse.getId(),
					new Date(date.getTime() - (long) 3 * 24 * 60 * 60 * 1000),
					new Date(date.getTime() + (long) 4 * 24 * 60 * 60 * 1000));
			if(operations.size()<size) {
				size=operations.size();
				selectNurse=nurse;
			}
		}
		return selectNurse;
	}

	// ��ѯ��refuseId��date+-3���������������ٵ�����ʦ,����null��ʾ����û���пյ�����ʦ,��������ʦrefuseId��ʾ���˵����idû���пյ�����ʦ
	public Worker autoSelectAnesthetist(Date date, String refuseId) {
		Vector<Worker> anesthetists = new WorkerHelper().selectAnesthetistsByDate(date);
		if (anesthetists.size() == 0)
			return null;
		if(anesthetists.size()==1) {
			return anesthetists.get(0);
		}
		int size=8;
		Worker selectAnesthetist=null;
		for (Worker anesthetist : anesthetists) {
			if(anesthetist.getId().equals(refuseId))
				continue;
			Vector<Operation> operations = new OperationHelper().selectWorkerAllOperationsBetween(anesthetist.getId(),
					new Date(date.getTime() - (long) 3 * 24 * 60 * 60 * 1000),
					new Date(date.getTime() + (long) 4 * 24 * 60 * 60 * 1000));
			if(operations.size()<size) {
				size=operations.size();
				selectAnesthetist=anesthetist;
			}
		}
		return selectAnesthetist;
	}

}