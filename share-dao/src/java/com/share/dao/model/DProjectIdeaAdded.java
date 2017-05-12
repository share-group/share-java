package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 创意补充表
 */
@Pojo
public class DProjectIdeaAdded extends DSuper {
	/**
	 * 用户ID
	 */
	private long userId;
	/**
	 * 创意ID
	 */
	private long ideaId;
	/**
	 * 内容
	 */
	private String content;

	/**
	 * 获取用户ID
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置用户ID
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 获取创意ID
	 */
	public long getIdeaId() {
		return ideaId;
	}

	/**
	 * 设置创意ID
	 */
	public void setIdeaId(long ideaId) {
		this.ideaId = ideaId;
	}

	/**
	 * 获取内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 */
	public void setContent(String content) {
		this.content = content;
	}


}