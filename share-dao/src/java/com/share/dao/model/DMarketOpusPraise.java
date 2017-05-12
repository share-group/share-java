package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 创意圈_作品点赞表
 */
@Pojo
public class DMarketOpusPraise extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 作品id
	 */
	private long marketOpusId;

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


}