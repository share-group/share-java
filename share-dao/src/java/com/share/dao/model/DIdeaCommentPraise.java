package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 创意评论点赞列表
 */
@Pojo
public class DIdeaCommentPraise extends DSuper {
	/**
	 * 评论id
	 */
	private long ideaCommentId;
	/**
	 * 点赞用户id
	 */
	private long praiseUserId;

	/**
	 * 获取评论id
	 */
	public long getIdeaCommentId() {
		return ideaCommentId;
	}

	/**
	 * 设置评论id
	 */
	public void setIdeaCommentId(long ideaCommentId) {
		this.ideaCommentId = ideaCommentId;
	}

	/**
	 * 获取点赞用户id
	 */
	public long getPraiseUserId() {
		return praiseUserId;
	}

	/**
	 * 设置点赞用户id
	 */
	public void setPraiseUserId(long praiseUserId) {
		this.praiseUserId = praiseUserId;
	}


}