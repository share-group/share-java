package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 用户点赞统计表
 */
@Pojo
public class DCasePraiseCount extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 点赞数量
	 */
	private int count;

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
	 * 获取点赞数量
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 设置点赞数量
	 */
	public void setCount(int count) {
		this.count = count;
	}


}