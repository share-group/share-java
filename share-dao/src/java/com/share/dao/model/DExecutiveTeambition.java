package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * teambition记录表
 */
@Pojo
public class DExecutiveTeambition extends DSuper {
	/**
	 * 任务id
	 */
	private long taskId;
	/**
	 * 任务排序
	 */
	private int order;
	/**
	 * teambition项目id
	 */
	private String tbProjectId;
	/**
	 * teambition任务id
	 */
	private String tbTaskId;

	/**
	 * 获取任务id
	 */
	public long getTaskId() {
		return taskId;
	}

	/**
	 * 设置任务id
	 */
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	/**
	 * 获取任务排序
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * 设置任务排序
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * 获取teambition项目id
	 */
	public String getTbProjectId() {
		return tbProjectId;
	}

	/**
	 * 设置teambition项目id
	 */
	public void setTbProjectId(String tbProjectId) {
		this.tbProjectId = tbProjectId;
	}

	/**
	 * 获取teambition任务id
	 */
	public String getTbTaskId() {
		return tbTaskId;
	}

	/**
	 * 设置teambition任务id
	 */
	public void setTbTaskId(String tbTaskId) {
		this.tbTaskId = tbTaskId;
	}


}