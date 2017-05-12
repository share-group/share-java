package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 项目调研
 */
@Pojo
public class DProjectResearch extends DSuper {
	/**
	 * 项目id
	 */
	private long projectId;
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 调研资料
	 */
	private String content;
	/**
	 * 评论数
	 */
	private int commentNum;
	/**
	 * 点击量
	 */
	private int pv;

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
	 * 获取用户id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置用户id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 获取标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取调研资料
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置调研资料
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取评论数
	 */
	public int getCommentNum() {
		return commentNum;
	}

	/**
	 * 设置评论数
	 */
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	/**
	 * 获取点击量
	 */
	public int getPv() {
		return pv;
	}

	/**
	 * 设置点击量
	 */
	public void setPv(int pv) {
		this.pv = pv;
	}


}