package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 用户token
 */
@Pojo
public class DUserToken extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * token
	 */
	private String token;
	/**
	 * 过期时间
	 */
	private int expire;

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
	 * 获取token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 设置token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 获取过期时间
	 */
	public int getExpire() {
		return expire;
	}

	/**
	 * 设置过期时间
	 */
	public void setExpire(int expire) {
		this.expire = expire;
	}


}