package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 专案结束后1周队列临时表
 */
@Pojo
public class DTmpCaseFinishAfter1WeekQueue extends DSuper {
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 专案结束后1周时间
	 */
	private int finishAfter1WeekEndTime;

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
	 * 获取专案结束后1周时间
	 */
	public int getFinishAfter1WeekEndTime() {
		return finishAfter1WeekEndTime;
	}

	/**
	 * 设置专案结束后1周时间
	 */
	public void setFinishAfter1WeekEndTime(int finishAfter1WeekEndTime) {
		this.finishAfter1WeekEndTime = finishAfter1WeekEndTime;
	}


}