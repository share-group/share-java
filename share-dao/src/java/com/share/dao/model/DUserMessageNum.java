package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 用户消息数量
 */
@Pojo
public class DUserMessageNum extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 消息大类型
	 */
	private int messageType;
	/**
	 * 消息小类型
	 */
	private int messageSign;
	/**
	 * 数量
	 */
	private int num;

	/**
	 * 获取用户id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置用户id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 获取消息大类型
	 */
	public int getMessageType() {
		return messageType;
	}

	/**
	 * 设置消息大类型
	 */
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	/**
	 * 获取消息小类型
	 */
	public int getMessageSign() {
		return messageSign;
	}

	/**
	 * 设置消息小类型
	 */
	public void setMessageSign(int messageSign) {
		this.messageSign = messageSign;
	}

	/**
	 * 获取数量
	 */
	public int getNum() {
		return num;
	}

	/**
	 * 设置数量
	 */
	public void setNum(int num) {
		this.num = num;
	}


}