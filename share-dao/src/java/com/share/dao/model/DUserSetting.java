package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 用户设置表
 */
@Pojo
public class DUserSetting extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 对所有人可见
	 */
	private byte allVisit;
	/**
	 * 对好友可见
	 */
	private byte friendVisit;

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
	 * 获取对所有人可见
	 */
	public byte getAllVisit() {
		return allVisit;
	}

	/**
	 * 设置对所有人可见
	 */
	public void setAllVisit(byte allVisit) {
		this.allVisit = allVisit;
	}

	/**
	 * 获取对好友可见
	 */
	public byte getFriendVisit() {
		return friendVisit;
	}

	/**
	 * 设置对好友可见
	 */
	public void setFriendVisit(byte friendVisit) {
		this.friendVisit = friendVisit;
	}


}