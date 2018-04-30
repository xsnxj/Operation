package com.operation.common;

import java.io.Serializable;

public class Message implements Serializable {
	public static final int CHOOSE = 1;// �㱻������������
	public static final int ACCEPT = 2;// �Է��������������
	public static final int REFUSE = 3;// �Է��ܾ����������
	public static final int AUTOCHOOSE = 4;// �����������߾ܾ��˲�������,�㱻�Զ�ѡ��Ϊѡ��Ϊ�����,���ɾܾ�
	public static final int NOTCHOOSE = 5;// �㱻�Ӹ��������޳�
	private String fromId;
	private String toId;
	private String operationId;
	private int messageType;

	public Message(String fromId, String toId, String operationId, int messageType) {
		super();
		this.fromId = fromId;
		this.toId = toId;
		this.operationId = operationId;
		this.messageType = messageType;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	@Override
	public String toString() {
		return "Message [fromId=" + fromId + ", toId=" + toId + ", operationId=" + operationId + ", messageType="
				+ messageType + "]";
	}

	@Override
	public boolean equals(Object obj) {
		Message m = (Message) obj;
		if (this.fromId.equals(m.getFromId()) && this.toId.equals(m.toId) && this.operationId.equals(m.getOperationId())
				&& this.messageType == m.getMessageType())
			return true;
		return false;
	}

}