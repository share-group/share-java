package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 机器人
 */
@Pojo
public class DRobot extends DSuper {
	/**
	 * 任务类型
	 */
	private String type;
	/**
	 * 任务执行时间
	 */
	private int executiveTime;
	/**
	 * 数据
	 */
	private String data;

	/**
	 * 获取任务类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置任务类型
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取任务执行时间
	 */
	public int getExecutiveTime() {
		return executiveTime;
	}

	/**
	 * 设置任务执行时间
	 */
	public void setExecutiveTime(int executiveTime) {
		this.executiveTime = executiveTime;
	}

	/**
	 * 获取数据
	 */
	public String getData() {
		return data;
	}

	/**
	 * 设置数据
	 */
	public void setData(String data) {
		this.data = data;
	}


}