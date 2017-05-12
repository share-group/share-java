package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * qq唯一识别ID表
 */
@Pojo
public class DOpenQq extends DSuper {
	/**
	 * 关联user表id
	 */
	private int userId;
	/**
	 * 第三方唯一识别ID
	 */
	private String openId;

	/**
	 * 获取关联user表id
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * 设置关联user表id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 获取第三方唯一识别ID
	 */
	public String getOpenId() {
		return openId;
	}

	/**
	 * 设置第三方唯一识别ID
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}


}