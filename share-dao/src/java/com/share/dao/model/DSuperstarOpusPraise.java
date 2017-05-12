package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 大咖说_作品点赞表
 */
@Pojo
public class DSuperstarOpusPraise extends DSuper {
	/**
	 * 大咖用户id
	 */
	private long userId;
	/**
	 * 作品id
	 */
	private long superstarOpusId;

	/**
	 * 获取大咖用户id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置大咖用户id
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


}