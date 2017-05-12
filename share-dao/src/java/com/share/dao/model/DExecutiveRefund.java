package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 需求方退款表
 */
@Pojo
public class DExecutiveRefund extends DSuper {
	/**
	 * 执行方任务id
	 */
	private long executiveTaskId;
	/**
	 * 退款渠道
	 */
	private int channel;
	/**
	 * 银行名
	 */
	private String bank;
	/**
	 * 帐号
	 */
	private String account;
	/**
	 * 帐号对应的姓名
	 */
	private String name;
	/**
	 * 退款状态(0-退款中 1-退款成功)
	 */
	private int status;

	/**
	 * 获取执行方任务id
	 */
	public long getExecutiveTaskId() {
		return executiveTaskId;
	}

	/**
	 * 设置执行方任务id
	 */
	public void setExecutiveTaskId(long executiveTaskId) {
		this.executiveTaskId = executiveTaskId;
	}

	/**
	 * 获取退款渠道
	 */
	public int getChannel() {
		return channel;
	}

	/**
	 * 设置退款渠道
	 */
	public void setChannel(int channel) {
		this.channel = channel;
	}

	/**
	 * 获取银行名
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * 设置银行名
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * 获取帐号
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * 设置帐号
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * 获取帐号对应的姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置帐号对应的姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取退款状态(0-退款中 1-退款成功)
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 设置退款状态(0-退款中 1-退款成功)
	 */
	public void setStatus(int status) {
		this.status = status;
	}


}