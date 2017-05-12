package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * cpc项目过期队列临时表
 */
@Pojo
public class DTmpCpcExpireQueue extends DSuper {
	/**
	 * cpc项目id
	 */
	private long cpcId;
	/**
	 * cpc项目结束时间
	 */
	private int endTime;

	/**
	 * 获取cpc项目id
	 */
	public long getCpcId() {
		return cpcId;
	}

	/**
	 * 设置cpc项目id
	 */
	public void setCpcId(long cpcId) {
		this.cpcId = cpcId;
	}

	/**
	 * 获取cpc项目结束时间
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * 设置cpc项目结束时间
	 */
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}


}