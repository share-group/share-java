package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 用户消息表
 */
@Pojo
public class DUserMessage extends DSuper {
	/**
	 * 类型
	 */
	private int type;
	/**
	 * 消息号码
	 */
	private int sign;
	/**
	 * 接收者id
	 */
	private long userId;
	/**
	 * 发送者id
	 */
	private long senderId;
	/**
	 * 消息内容(JSON)
	 */
	private String body;

	/**
	 * 获取类型
	 */
	public int getType() {
		return type;
	}

	/**
	 * 设置类型
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * 获取消息号码
	 */
	public int getSign() {
		return sign;
	}

	/**
	 * 设置消息号码
	 */
	public void setSign(int sign) {
		this.sign = sign;
	}

	/**
	 * 获取接收者id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置接收者id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 获取发送者id
	 */
	public long getSenderId() {
		return senderId;
	}

	/**
	 * 设置发送者id
	 */
	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	/**
	 * 获取消息内容(JSON)
	 */
	public String getBody() {
		return body;
	}

	/**
	 * 设置消息内容(JSON)
	 */
	public void setBody(String body) {
		this.body = body;
	}


}