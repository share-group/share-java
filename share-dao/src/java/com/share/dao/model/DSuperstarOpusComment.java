package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 大咖说_作品评论表
 */
@Pojo
public class DSuperstarOpusComment extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 作品id
	 */
	private long superstarOpusId;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 回复的用户id
	 */
	private long replyUserId;

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
	 * 获取作品id
	 */
	public long getSuperstarOpusId() {
		return superstarOpusId;
	}

	/**
	 * 设置作品id
	 */
	public void setSuperstarOpusId(long superstarOpusId) {
		this.superstarOpusId = superstarOpusId;
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