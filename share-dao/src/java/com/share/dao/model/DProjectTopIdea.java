package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 项目获奖点子
 */
@Pojo
public class DProjectTopIdea extends DSuper {
	/**
	 * 项目id
	 */
	private long projectId;
	/**
	 * 点子id
	 */
	private long ideaId;
	/**
	 * 获奖说明
	 */
	private String content;

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
	 * 获取点子id
	 */
	public long getIdeaId() {
		return ideaId;
	}

	/**
	 * 设置点子id
	 */
	public void setIdeaId(long ideaId) {
		this.ideaId = ideaId;
	}

	/**
	 * 获取获奖说明
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置获奖说明
	 */
	public void setContent(String content) {
		this.content = content;
	}


}