package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 关注专案表
 */
@Pojo
public class DFocusCase extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 被关注专案id
	 */
	private long caseId;

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
	 * 获取被关注专案id
	 */
	public long getCaseId() {
		return caseId;
	}

	/**
	 * 设置被关注专案id
	 */
	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}


}