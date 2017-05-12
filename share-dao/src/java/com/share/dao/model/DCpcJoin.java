package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * cpc项目参与人员
 */
@Pojo
public class DCpcJoin extends DSuper {
	/**
	 * cpc项目id
	 */
	private long cpcId;
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 获得报酬
	 */
	private double reward;

	/**
	 * 获取cpc项目id
	 */
	public long getCpcId() {
		return cpcId;
	}

	/**
	 * 设置cpc项目id
	 */
	public void setCpcId(long cpcId) {
		this.cpcId = cpcId;
	}

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
	 * 获取获得报酬
	 */
	public double getReward() {
		return reward;
	}

	/**
	 * 设置获得报酬
	 */
	public void setReward(double reward) {
		this.reward = reward;
	}


}