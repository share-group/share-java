package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 企业消息数量
 */
@Pojo
public class DCompanyMessageNum extends DSuper {
	/**
	 * 企业id
	 */
	private long companyId;
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
	 * 获取企业id
	 */
	public long getCompanyId() {
		return companyId;
	}

	/**
	 * 设置企业id
	 */
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
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