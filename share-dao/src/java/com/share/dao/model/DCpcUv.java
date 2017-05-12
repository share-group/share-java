package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * cpc项目uv列表
 */
@Pojo
public class DCpcUv extends DSuper {
	/**
	 * cpc项目id
	 */
	private long cpcId;
	/**
	 * 点击用户
	 */
	private String stranger;

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
	 * 获取点击用户
	 */
	public String getStranger() {
		return stranger;
	}

	/**
	 * 设置点击用户
	 */
	public void setStranger(String stranger) {
		this.stranger = stranger;
	}


}