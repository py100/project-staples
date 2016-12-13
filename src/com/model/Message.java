package com.model;

import java.sql.SQLException;

import com.db.Dao;

public class Message {
	
	/*
	 * type 1 : register group
	 * 		2 : 
	 * 		3 : normal
	 * 		4 : invite to Quiz
	 */
	
	
	private long id;
	private int type;
	private long fromid;
	private long toid;
	private String msg;
	private User fromUser;
	private int tmpId;
	
	
	public int getTmpId() {
		return tmpId;
	}

	public void setTmpId(int tmpId) {
		this.tmpId = tmpId;
	}

	public int getToGroupId() {
		return tmpId;
	}

	public void setToGroupId(int toGroupId) {
		this.tmpId = toGroupId;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Message(long id, int type, long fromid, long toid, String msg) {
		super();
		this.id = id;
		this.type = type;
		this.fromid = fromid;
		this.toid = toid;
		if (type==1) {
			this.tmpId = Integer.valueOf(msg.split("###")[0]);
			this.msg = msg.split("###")[1];
		}
		else if (type==4) {
			this.msg = null;
			this.tmpId = Integer.valueOf(msg);
		}
		else {
			this.msg = msg;
		}
	}

	public long getId() {
		return id;
	}

	public long getFromid() {
		return fromid;
	}

	public long getToid() {
		return toid;
	}

	public String getMsg() {
		return msg;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setFromid(long fromid) {
		this.fromid = fromid;
	}

	public void setToid(long toid) {
		this.toid = toid;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static String formRegisterGroup(int id2, int toId, int groupId, String groupName) {
		return String.format("values(null, %d, %d, '%d###%s', 1)", id2, toId, groupId, groupName);
	}

	public void adjust() {
		if (this != null) {
			if (this.type == 1) {
			this.msg = String.valueOf(this.tmpId)+"###"+this.msg;
			}
		}
	}

	public String tosql() {
		// TODO Auto-generated method stub
		return String.format("insert into message values(null,%d,%d,'%s',%d)", fromid,toid,msg,type);
	}
	
	public static Message formInviteMessage(int _fromid, int _toid, int _quizId) throws ClassNotFoundException, SQLException {
		Message ret = new Message();
		ret.fromid = _fromid;
		ret.toid = _toid;
		ret.type = 4;
		Dao dao = new Dao();
		dao.close();
		ret.msg = String.format("%d", _quizId);
		return ret;
	}
	
	public static Message formGroupInviteMessage(int _fromid, int _toid, int _quizId) throws ClassNotFoundException, SQLException {
		Message ret = new Message();
		ret.fromid = _fromid;
		ret.toid = _toid;
		ret.type = 5;
		Dao dao = new Dao();
		dao.close();
		ret.msg = String.format("%d", _quizId);
		return ret;
	}
	
	public Message() { 
		this.id = 0;
	}
}
