package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 新创意置顶过期队列
 */
@Pojo
public class DTmpIdeaNewTimeTopQueue extends DSuper {
	/**
	 * 创意id
	 */
	private long ideaId;
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 结束时间
	 */
	private int endTime;

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
	 * 获取结束时间
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * 设置结束时间
	 */
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}


}