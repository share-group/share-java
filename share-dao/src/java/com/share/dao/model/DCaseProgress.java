package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 专案阶段
 */
@Pojo
public class DCaseProgress extends DSuper {
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 阶段
	 */
	private byte stage;
	/**
	 * 阶段开始时间
	 */
	private int startTime;
	/**
	 * 阶段结束时间
	 */
	private int endTime;
	/**
	 * 进行状态(1-进行中 0-已结束)
	 */
	private byte status;
	/**
	 * 是否通知品牌
	 */
	private int notice;
	/**
	 * 进入本阶段的创意数(stage>1)
	 */
	private int ideaNum;

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
	 * 获取阶段
	 */
	public byte getStage() {
		return stage;
	}

	/**
	 * 设置阶段
	 */
	public void setStage(byte stage) {
		this.stage = stage;
	}

	/**
	 * 获取阶段开始时间
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * 设置阶段开始时间
	 */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取阶段结束时间
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * 设置阶段结束时间
	 */
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取进行状态(1-进行中 0-已结束)
	 */
	public byte getStatus() {
		return status;
	}

	/**
	 * 设置进行状态(1-进行中 0-已结束)
	 */
	public void setStatus(byte status) {
		this.status = status;
	}

	/**
	 * 获取是否通知品牌
	 */
	public int getNotice() {
		return notice;
	}

	/**
	 * 设置是否通知品牌
	 */
	public void setNotice(int notice) {
		this.notice = notice;
	}

	/**
	 * 获取进入本阶段的创意数(stage>1)
	 */
	public int getIdeaNum() {
		return ideaNum;
	}

	/**
	 * 设置进入本阶段的创意数(stage>1)
	 */
	public void setIdeaNum(int ideaNum) {
		this.ideaNum = ideaNum;
	}


}