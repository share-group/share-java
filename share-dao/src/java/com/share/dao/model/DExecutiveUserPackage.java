package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 用户执行方套餐
 */
@Pojo
public class DExecutiveUserPackage extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 标签
	 */
	private int tag;
	/**
	 * 套餐标题
	 */
	private String title;
	/**
	 * 套餐简介
	 */
	private String description;
	/**
	 * 金额
	 */
	private int amount;

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
	 * 获取标签
	 */
	public int getTag() {
		return tag;
	}

	/**
	 * 设置标签
	 */
	public void setTag(int tag) {
		this.tag = tag;
	}

	/**
	 * 获取套餐标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置套餐标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取套餐简介
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置套餐简介
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取金额
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * 设置金额
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}


}