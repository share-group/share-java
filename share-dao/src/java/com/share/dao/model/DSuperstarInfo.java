package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 大咖介绍
 */
@Pojo
public class DSuperstarInfo extends DSuper {
	/**
	 * 大咖用户id
	 */
	private long userId;
	/**
	 * 大咖简介
	 */
	private String detail;

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
	 * 获取大咖简介
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * 设置大咖简介
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}


}