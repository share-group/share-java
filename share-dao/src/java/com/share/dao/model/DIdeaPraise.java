package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 用户点赞表
 */
@Pojo
public class DIdeaPraise extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 创意id
	 */
	private long ideaId;
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 创意值
	 */
	private double creative;

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
	 * 获取创意id
	 */
	public long getIdeaId() {
		return ideaId;
	}

	/**
	 * 设置创意id
	 */
	public void setIdeaId(long ideaId) {
		this.ideaId = ideaId;
	}

	/**
	 * 获取专案id
	 */
	public long getCaseId() {
		return caseId;
	}

	/**
	 * 设置专案id
	 */
	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	/**
	 * 获取创意值
	 */
	public double getCreative() {
		return creative;
	}

	/**
	 * 设置创意值
	 */
	public void setCreative(double creative) {
		this.creative = creative;
	}


}