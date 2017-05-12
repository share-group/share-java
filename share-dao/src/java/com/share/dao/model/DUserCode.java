package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 用户输入过的优惠码
 */
@Pojo
public class DUserCode extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 此用户使用的优惠码
	 */
	private String code;
	/**
	 * 来自谁的优惠码
	 */
	private long fromUserId;
	/**
	 * 获得的奖励G币数
	 */
	private int awardPoints;

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
	 * 获取此用户使用的优惠码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置此用户使用的优惠码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取来自谁的优惠码
	 */
	public long getFromUserId() {
		return fromUserId;
	}

	/**
	 * 设置来自谁的优惠码
	 */
	public void setFromUserId(long fromUserId) {
		this.fromUserId = fromUserId;
	}

	/**
	 * 获取获得的奖励G币数
	 */
	public int getAwardPoints() {
		return awardPoints;
	}

	/**
	 * 设置获得的奖励G币数
	 */
	public void setAwardPoints(int awardPoints) {
		this.awardPoints = awardPoints;
	}


}