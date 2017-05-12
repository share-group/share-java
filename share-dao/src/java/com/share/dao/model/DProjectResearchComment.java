package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 调研资料评论表
 */
@Pojo
public class DProjectResearchComment extends DSuper {
	/**
	 * 用户ID
	 */
	private long userId;
	/**
	 * 资料ID
	 */
	private long researchId;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 回复的用户id
	 */
	private long replyUserId;

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
	 * 获取资料ID
	 */
	public long getResearchId() {
		return researchId;
	}

	/**
	 * 设置资料ID
	 */
	public void setResearchId(long researchId) {
		this.researchId = researchId;
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

	/**
	 * 获取回复的用户id
	 */
	public long getReplyUserId() {
		return replyUserId;
	}

	/**
	 * 设置回复的用户id
	 */
	public void setReplyUserId(long replyUserId) {
		this.replyUserId = replyUserId;
	}


}