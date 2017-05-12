package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 项目人员
 */
@Pojo
public class DProjectPerson extends DSuper {
	/**
	 * 项目id
	 */
	private long projectId;
	/**
	 * 职位类型
	 */
	private String type;
	/**
	 * 用户id
	 */
	private String userId;

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
	 * 获取职位类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置职位类型
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取用户id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 设置用户id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


}