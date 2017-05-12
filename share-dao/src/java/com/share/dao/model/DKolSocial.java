package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * KOL的社交帐号
 */
@Pojo
public class DKolSocial extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 社交配置id
	 */
	private int snsId;
	/**
	 * 好友数
	 */
	private int friendsNum;
	/**
	 * 圈子内的报价
	 */
	private double price;

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
	 * 获取社交配置id
	 */
	public int getSnsId() {
		return snsId;
	}

	/**
	 * 设置社交配置id
	 */
	public void setSnsId(int snsId) {
		this.snsId = snsId;
	}

	/**
	 * 获取好友数
	 */
	public int getFriendsNum() {
		return friendsNum;
	}

	/**
	 * 设置好友数
	 */
	public void setFriendsNum(int friendsNum) {
		this.friendsNum = friendsNum;
	}

	/**
	 * 获取圈子内的报价
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * 设置圈子内的报价
	 */
	public void setPrice(double price) {
		this.price = price;
	}


}