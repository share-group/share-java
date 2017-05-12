package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 创意评论表
 */
@Pojo
public class DIdeaComment extends DSuper {
	/**
	 * 用户ID
	 */
	private long userId;
	/**
	 * 创意ID
	 */
	private long ideaId;
	/**
	 * 层级id
	 */
	private long layerId;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 回复的用户id
	 */
	private long replyUserId;
	/**
	 * 回复条数
	 */
	private int replyNum;
	/**
	 * 点赞数
	 */
	private int praiseNum;

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
	 * 获取层级id
	 */
	public long getLayerId() {
		return layerId;
	}

	/**
	 * 设置层级id
	 */
	public void setLayerId(long layerId) {
		this.layerId = layerId;
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

	/**
	 * 获取回复条数
	 */
	public int getReplyNum() {
		return replyNum;
	}

	/**
	 * 设置回复条数
	 */
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	/**
	 * 获取点赞数
	 */
	public int getPraiseNum() {
		return praiseNum;
	}

	/**
	 * 设置点赞数
	 */
	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}


}