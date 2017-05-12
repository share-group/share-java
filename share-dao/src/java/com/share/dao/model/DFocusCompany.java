package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 关注企业表(fans关注company)
 */
@Pojo
public class DFocusCompany extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 被关注企业id
	 */
	private long companyId;

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
	 * 获取被关注企业id
	 */
	public long getCompanyId() {
		return companyId;
	}

	/**
	 * 设置被关注企业id
	 */
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}


}