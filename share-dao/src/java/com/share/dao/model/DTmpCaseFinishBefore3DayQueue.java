package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 专案剩3天过期队列临时表
 */
@Pojo
public class DTmpCaseFinishBefore3DayQueue extends DSuper {
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 专案剩3天过期时间
	 */
	private int finishBefore3DayEndTime;

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
	 * 获取专案剩3天过期时间
	 */
	public int getFinishBefore3DayEndTime() {
		return finishBefore3DayEndTime;
	}

	/**
	 * 设置专案剩3天过期时间
	 */
	public void setFinishBefore3DayEndTime(int finishBefore3DayEndTime) {
		this.finishBefore3DayEndTime = finishBefore3DayEndTime;
	}


}