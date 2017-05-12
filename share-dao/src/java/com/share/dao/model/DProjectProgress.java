package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 项目阶段
 */
@Pojo
public class DProjectProgress extends DSuper {
	/**
	 * 项目id
	 */
	private long projectId;
	/**
	 * 阶段
	 */
	private byte stage;
	/**
	 * 阶段名称
	 */
	private String name;
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
	 * 阶段描述
	 */
	private String description;

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
	 * 获取阶段名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置阶段名称
	 */
	public void setName(String name) {
		this.name = name;
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
	 * 获取阶段描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置阶段描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}


}