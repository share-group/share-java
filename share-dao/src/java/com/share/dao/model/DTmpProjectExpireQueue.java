package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 项目过期临时队列表
 */
@Pojo
public class DTmpProjectExpireQueue extends DSuper {
	/**
	 * 项目id
	 */
	private long projectId;
	/**
	 * 阶段检查时间
	 */
	private int checkTime;

	/**
	 * 获取项目id
	 */
	public long getProjectId() {
		return projectId;
	}

	/**
	 * 设置项目id
	 */
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	/**
	 * 获取阶段检查时间
	 */
	public int getCheckTime() {
		return checkTime;
	}

	/**
	 * 设置阶段检查时间
	 */
	public void setCheckTime(int checkTime) {
		this.checkTime = checkTime;
	}


}