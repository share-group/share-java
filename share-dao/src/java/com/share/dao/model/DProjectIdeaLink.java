package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 点子链接
 */
@Pojo
public class DProjectIdeaLink extends DSuper {
	/**
	 * 点子id
	 */
	private long ideaId;
	/**
	 * 链接名称
	 */
	private String name;
	/**
	 * 链接地址
	 */
	private String link;

	/**
	 * 获取点子id
	 */
	public long getIdeaId() {
		return ideaId;
	}

	/**
	 * 设置点子id
	 */
	public void setIdeaId(long ideaId) {
		this.ideaId = ideaId;
	}

	/**
	 * 获取链接名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置链接名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取链接地址
	 */
	public String getLink() {
		return link;
	}

	/**
	 * 设置链接地址
	 */
	public void setLink(String link) {
		this.link = link;
	}


}