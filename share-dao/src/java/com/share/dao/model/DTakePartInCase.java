package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 用户参与过的专案
 */
@Pojo
public class DTakePartInCase extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 被参与专案id
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
	 * 获取被参与专案id
	 */
	public long getCaseId() {
		return caseId;
	}

	/**
	 * 设置被参与专案id
	 */
	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}


}