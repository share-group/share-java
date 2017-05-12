package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 执行方统计
 */
@Pojo
public class DExecutiveStat extends DSuper {
	/**
	 * 用户数
	 */
	private int user;
	/**
	 * 企业数
	 */
	private int company;

	/**
	 * 获取用户数
	 */
	public int getUser() {
		return user;
	}

	/**
	 * 设置用户数
	 */
	public void setUser(int user) {
		this.user = user;
	}

	/**
	 * 获取企业数
	 */
	public int getCompany() {
		return company;
	}

	/**
	 * 设置企业数
	 */
	public void setCompany(int company) {
		this.company = company;
	}


}