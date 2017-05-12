package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 预留手机号送G点
 */
@Pojo
public class DStoreMobile extends DSuper {
	/**
	 * 手机号
	 */
	private long mobile;
	/**
	 * G点数
	 */
	private int points;

	/**
	 * 获取手机号
	 */
	public long getMobile() {
		return mobile;
	}

	/**
	 * 设置手机号
	 */
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取G点数
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * 设置G点数
	 */
	public void setPoints(int points) {
		this.points = points;
	}


}