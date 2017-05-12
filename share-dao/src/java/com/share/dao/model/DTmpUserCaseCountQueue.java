package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 用户专案结算队列临时表
 */
@Pojo
public class DTmpUserCaseCountQueue extends DSuper {
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 专案结算时间
	 */
	private int endTime;

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
	 * 获取专案结算时间
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * 设置专案结算时间
	 */
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}


}