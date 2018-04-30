package com.operation.dao;

import java.sql.Date;
import java.util.Vector;

import com.operation.common.Operation;
import com.operation.common.Worker;

public class OperationHelper {

	// ��ѯ��������,����null��ʾû������
	public Vector<Operation> selectAllOperations() {
		Vector<Vector<String>> data = new SqlHelper().query(
				"select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord from operation");
		if (data.size() == 0)
			return null;
		Vector<Operation> operations = new Vector<Operation>();
		Operation operation = null;
		for (Vector e : data) {
			operation = Operation.VectorToOperation(e);
			operations.add(operation);
		}
		return operations;
	}

	// ����id�鵥������,����null��ʾid������
	public Operation selectOperationById(String id) {
		Vector<Vector<String>> data = new SqlHelper().query(
				"select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord from operation where id=?",
				new String[] { id });
		if (data.size() == 0)
			return null;
		Operation operation = Operation.VectorToOperation(data.get(0));
		return operation;
	}

	// ĳ�����������,����null��ʾ����û������
	public Vector<Operation> selectOperationByDate(Date date) {
		Vector<Vector<String>> data = new SqlHelper().query(
				"select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord from operation where beginTime=?",
				new String[] { date.toString() });
		if (data.size() == 0)
			return null;
		Vector<Operation> operations = new Vector<Operation>();
		Operation operation = null;
		for (Vector e : data) {
			operation = Operation.VectorToOperation(e);
			operations.add(operation);
		}
		return operations;
	}

	// ��ѯ��ĳ����֮�������
	public Vector<Operation> selectOperationAfterDate(Date date) {
		Vector<Vector<String>> data = new SqlHelper().query(
				"select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord from operation where beginTime>=?",
				new String[] { date.toString() });
		if (data.size() == 0)
			return null;
		Vector<Operation> operations = new Vector<Operation>();
		Operation operation = null;
		for (Vector e : data) {
			operation = Operation.VectorToOperation(e);
			operations.add(operation);
		}
		return operations;
	}

	// ����������֮�������,����null��ʾ��������֮��û������
	public Vector<Operation> selectOperationsBetween(Date begin, Date end) {
		Vector<Vector<String>> data = new SqlHelper().query(
				"select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord from operation where beginTime between ? and ?",
				new String[] { begin.toString(), end.toString() });
		if (data.size() == 0)
			return null;
		Vector<Operation> operations = new Vector<Operation>();
		Operation operation = null;
		for (Vector e : data) {
			operation = Operation.VectorToOperation(e);
			operations.add(operation);
		}
		return operations;
	}

	// ��ĳԱ�������ȫ������,����null��ʾԱ��û������
	public Vector<Operation> selectWorkerAllOperations(String workerId) {
		Vector<Vector<String>> data = new SqlHelper().query(
				"select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord from operation where doctorId = ? or nurseId=? or anesthetistId=?",
				new String[] { workerId, workerId, workerId });
		if (data.size() == 0)
			return null;
		Vector<Operation> operations = new Vector<Operation>();
		Operation operation = null;
		for (Vector e : data) {
			operation = Operation.VectorToOperation(e);
			operations.add(operation);
		}
		return operations;
	}

	// ��ĳԱ��ĳ���ڲ���ĵ�������,����null��ʾԱ������û������
	public Operation selectWorkerOperationsByDate(String workerId, Date date) {
		Vector<Vector<String>> data = new SqlHelper().query(
				"select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord from operation where (doctorId = ? or nurseId=? or anesthetistId=?) and beginTime = ?",
				new String[] { workerId, workerId, workerId, date.toString() });
		if (data.size() == 0)
			return null;
		Operation operation = Operation.VectorToOperation(data.get(0));
		return operation;
	}

	// ��ĳԱ��������֮������ȫ������,����null��ʾԱ����������֮��û������
	public Vector<Operation> selectWorkerAllOperationsBetween(String workerId, Date begin, Date end) {
		Vector<Vector<String>> data = new SqlHelper().query(
				"select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord from operation where (doctorId = ? or nurseId=? or anesthetistId=?) and beginTime between ? and ?",
				new String[] { workerId, workerId, workerId, begin.toString(), end.toString() });
		if (data.size() == 0)
			return null;
		Vector<Operation> operations = new Vector<Operation>();
		Operation operation = null;
		for (Vector e : data) {
			operation = Operation.VectorToOperation(e);
			operations.add(operation);
		}
		return operations;
	}

	// ��ĳ���˲����ȫ������,����null��ʾ����û������
	public Vector<Operation> selectPatientAllOperations(String patientId) {
		Vector<Vector<String>> data = new SqlHelper().query(
				"select id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord from operation where patientId = ?",
				new String[] { patientId });
		if (data.size() == 0)
			return null;
		Vector<Operation> operations = new Vector<Operation>();
		Operation operation = null;
		for (Vector e : data) {
			operation = Operation.VectorToOperation(e);
			operations.add(operation);
		}
		return operations;
	}

	// ��������,����false��ʾ����ʧ��
	public boolean addOperation(Operation operation) {
		return new SqlHelper().update(
				"insert into operation (id,name,beginTime,roomId,patientId,doctorId,nurseId,anesthetistId,doctorRecord,nurseRecord,anesthetistRecord) values(?, ?, ?, ?, ? ,? ,? ,? ,? ,? ,?)",
				operation.toStringList());
	}

	// ���ӻ��޸Ĳ���������ҽ��,����false��ʾ�޸�ʧ��
	public boolean updateDoctorToOperation(String id, String doctorId) {
		Worker worker=new WorkerHelper().selectWorkerById(doctorId);
		if(!worker.getPosition().equals("ҽ��"))
			return false;
		return new SqlHelper().update(
				"update operation set doctorId = ? where id = ?",
				new String[] {doctorId,id});
	}
	// ���ӻ��޸Ĳ��������Ļ�ʿ,����false��ʾ�޸�ʧ��
	public boolean updateNurseToOperation(String id, String nurseId) {
		Worker worker=new WorkerHelper().selectWorkerById(nurseId);
		if(!worker.getPosition().equals("��ʿ"))
			return false;
		return new SqlHelper().update(
				"update operation set nurseId = ? where id = ?",
				new String[] {nurseId,id});
	}
	// ���ӻ��޸Ĳ�������������ʦ,����false��ʾ�޸�ʧ��
	public boolean updateAnesthetistToOperation(String id, String anesthetistId) {
		Worker worker=new WorkerHelper().selectWorkerById(anesthetistId);
		if(!worker.getPosition().equals("����ʦ"))
			return false;
		return new SqlHelper().update(
				"update operation set anesthetistId = ? where id = ?",
				new String[] {anesthetistId,id});
	}
	
	// ���ӻ��޸�ҽ��������¼,����false��ʾ�޸�ʧ��
	public boolean updateDoctorRecordToOperation(String id, String doctorRecord) {
		return new SqlHelper().update(
				"update operation set doctorRecord = ? where id=?",
				new String[] {doctorRecord,id});
	}
	// ���ӻ��޸Ļ�ʿ������¼,����false��ʾ�޸�ʧ��
	public boolean updateNurseRecordToOperation(String id, String nurseRecord) {
		return new SqlHelper().update(
				"update operation set nurseRecord = ? where id=?",
				new String[] {nurseRecord,id});
	}
	// ���ӻ��޸�����ʦ������¼,����false��ʾ�޸�ʧ��
	public boolean updateAnesthetistRecordToOperation(String id, String anesthetistRecord) {
		return new SqlHelper().update(
				"update operation set anesthetistRecord = ? where id=?",
				new String[] {anesthetistRecord,id});
	}
	
	// ���ӻ��޸�������,����false��ʾ�޸�ʧ��
	public boolean updateRoomToOperation(String id, String roomId) {
		return new SqlHelper().update(
				"update operation set roomId = ? where id=?",
				new String[] {roomId,id});
	}
	

}