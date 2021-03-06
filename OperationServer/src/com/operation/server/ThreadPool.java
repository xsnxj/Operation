package com.operation.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.operation.common.Message;

/**
 * 本类为线程池,用来存放所有的线程
 * 
 * @author pre
 *
 */
public class ThreadPool {
	// 存放待连接线程
	private static List<ServerThread> waitThreads = Collections.synchronizedList(new ArrayList<ServerThread>());
	// 存放已连接到客户端的线程,String为用户id
	private static Map<String, ServerThread> serverThreads = new HashMap<String, ServerThread>();
	//
	// private static Map<String,Message> messages = new HashMap<String, Message>();
	private static List<Message> messages = Collections.synchronizedList(new ArrayList<Message>());

	public static boolean addMessage(Message message) {
		messages.add(message);
		System.out.println("新增消息:"+message);
		return true;
	}

	public static Vector<Message> getMessages(String id) {
		Vector<Message> ms = new Vector<Message>();
		Message m=null;
		for(int i=0;i<messages.size();i++) {
			m=messages.get(i);
			if (m.getToId().equals(id)) {
				ms.add(m);
			}
		}
		return ms;
	}

	public static boolean removeMessage(Message message) {
		boolean flag=false;
		Message m=null;
		for(int i=0;i<messages.size();i++) {
			m=messages.get(i);
			if (m.equals(message)) {
				messages.remove(m);
				System.out.println("删除消息:"+m);
				flag=true;
			}
		}
		return flag;
	}

	public static void addWaitThread(ServerThread waitThread) {
		waitThreads.add(waitThread);
	}

	public static void removeWaitThread(ServerThread waitThread) {
		waitThreads.remove(waitThread);
	}

	//判断账号是否已经登陆
	public static boolean isLogin(String id) {
		Set<String> keys=serverThreads.keySet();
		Iterator<String> it=keys.iterator();
		while(it.hasNext()) {
			String s=it.next();
			if(s.equals(id))
				return true;
		}
		return false;
	}
	public static boolean addServerThread(String id, ServerThread serverThread) {
		if (serverThreads.get(id) != null) {
			System.out.println("该用户已登陆,不能重复登陆!");
			return false;
		}
		serverThreads.put(id, serverThread);
		return true;
	}

	public static void removeServerThread(String id) {
		serverThreads.remove(id);
	}

	public static int getWaitThreadsSize() {
		return waitThreads.size();
	}

	// 存放各个Message

}
