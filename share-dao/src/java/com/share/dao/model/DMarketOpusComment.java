package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 创意圈_作品评论表
 */
@Pojo
public class DMarketOpusComment extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 作品id
	 */
	private long marketOpusId;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 回复的用户id
	 */
	private int replyUserId;

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
	public long getMarketOpusId() {
		return marketOpusId;
	}

	/**
	 * 设置作品id
	 */
	public void setMarketOpusId(long marketOpusId) {
		this.marketOpusId = marketOpusId;
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
	public int getReplyUserId() {
		return replyUserId;
	}

	/**
	 * 设置回复的用户id
	 */
	public void setReplyUserId(int replyUserId) {
		this.replyUserId = replyUserId;
	}


}