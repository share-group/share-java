package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 专案过期队列临时表
 */
@Pojo
public class DTmpCaseExpireQueue extends DSuper {
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 专案结束时间
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
	 * 获取专案结束时间
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * 设置专案结束时间
	 */
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}


}