package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 创意评论回复表
 */
@Pojo
public class DProjectIdeaCommentReply extends DSuper {
	/**
	 * 用户ID
	 */
	private long userId;
	/**
	 * 创意评论ID
	 */
	private long ideaCommentId;
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
	 * 获取创意评论ID
	 */
	public long getIdeaCommentId() {
		return ideaCommentId;
	}

	/**
	 * 设置创意评论ID
	 */
	public void setIdeaCommentId(long ideaCommentId) {
		this.ideaCommentId = ideaCommentId;
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