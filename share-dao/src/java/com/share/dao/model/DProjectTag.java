package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 项目标签
 */
@Pojo
public class DProjectTag extends DSuper {
	/**
	 * 项目id
	 */
	private long projectId;
	/**
	 * 标签名
	 */
	private String name;

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
	 * 获取标签名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置标签名
	 */
	public void setName(String name) {
		this.name = name;
	}


}