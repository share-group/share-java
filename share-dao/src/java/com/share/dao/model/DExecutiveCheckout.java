package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 需求方提现表
 */
@Pojo
public class DExecutiveCheckout extends DSuper {
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 提现渠道
	 */
	private int channel;
	/**
	 * 提现银行名
	 */
	private String bank;
	/**
	 * 提现帐号
	 */
	private String account;
	/**
	 * 提现帐号对应的姓名
	 */
	private String name;
	/**
	 * 提现状态(0-提现中 1-成功到帐)
	 */
	private int status;

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
	 * 获取提现渠道
	 */
	public int getChannel() {
		return channel;
	}

	/**
	 * 设置提现渠道
	 */
	public void setChannel(int channel) {
		this.channel = channel;
	}

	/**
	 * 获取提现银行名
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * 设置提现银行名
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * 获取提现帐号
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * 设置提现帐号
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * 获取提现帐号对应的姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置提现帐号对应的姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取提现状态(0-提现中 1-成功到帐)
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 设置提现状态(0-提现中 1-成功到帐)
	 */
	public void setStatus(int status) {
		this.status = status;
	}


}