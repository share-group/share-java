package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 创意点赞列表
 */
@Pojo
public class DProjectIdeaPraise extends DSuper {
	/**
	 * 评论id
	 */
	private long ideaId;
	/**
	 * 点赞用户id
	 */
	private long userId;
	/**
	 * 项目id
	 */
	private long projectId;

	/**
	 * 获取评论id
	 */
	public long getIdeaId() {
		return ideaId;
	}

	/**
	 * 设置评论id
	 */
	public void setIdeaId(long ideaId) {
		this.ideaId = ideaId;
	}

	/**
	 * 获取点赞用户id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置点赞用户id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

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


}