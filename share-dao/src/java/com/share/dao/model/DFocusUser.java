package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 粉丝表(fans关注user)
 */
@Pojo
public class DFocusUser extends DSuper {
	/**
	 * 关注人id
	 */
	private long userId;
	/**
	 * 被关注人id
	 */
	private long friendId;

	/**
	 * 获取关注人id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置关注人id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 获取被关注人id
	 */
	public long getFriendId() {
		return friendId;
	}

	/**
	 * 设置被关注人id
	 */
	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}


}