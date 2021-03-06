package com.operation.common;

import java.io.Serializable;
import java.sql.Date;
import java.util.Vector;

public class Worker implements Serializable {
	private String id;
	private String password;
	private String name;
	private String sex;
	private Date birth;
	private String position;//职位
	private String call;//联系方式
	private String section;//科室
	public Worker(String id, String password, String name, String sex, Date birth, String position, String call,
			String section) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.birth = birth;
		this.position = position;
		this.call = call;
		this.section = section;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getCall() {
		return call;
	}
	public void setCall(String call) {
		this.call = call;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	@Override
	public String toString() {
		return "Worker [id=" + id + ", password=" + password + ", name=" + name + ", sex=" + sex + ", birth=" + birth
				+ ", position=" + position + ", call=" + call + ", section=" + section + "]";
	}
	public String[] toStringList() {
		return new String[]{id,password,name,sex,birth.toString(),position,call,section};
	}
	public static Worker VectorToWorker(Vector<String> v) {
		Worker worker = new Worker(v.get(0),v.get(1),v.get(2),v.get(3),Date.valueOf(v.get(4)),v.get(5),v.get(6),v.get(7));
		return worker;
	}
//	Vector<String> columnNames = new Vector<String>();
//	columnNames.add("ID");
//	columnNames.add("姓名");
	//性别
	
//	columnNames.add("科室");
//	columnNames.add("出生日期");
//	columnNames.add("联系方式");
//	return columnNames;
	public Vector<String> toVector(){
		Vector<String> data=new Vector<String>();
		data.add(this.getId());
		data.add(this.getName());
		data.add(this.getSex());
		data.add(this.getBirth()+"");
		data.add(this.getPosition());
		data.add(this.getCall());
		data.add(this.getSection());
		return data;
	}
}
