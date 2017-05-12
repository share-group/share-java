package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 爬虫执行队列
 */
@Pojo
public class DCrawlerQueue extends DSuper {
	/**
	 * 爬虫类型
	 */
	private String type;
	/**
	 * 执行时间
	 */
	private int executiveTime;

	/**
	 * 获取爬虫类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置爬虫类型
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取执行时间
	 */
	public int getExecutiveTime() {
		return executiveTime;
	}

	/**
	 * 设置执行时间
	 */
	public void setExecutiveTime(int executiveTime) {
		this.executiveTime = executiveTime;
	}


}