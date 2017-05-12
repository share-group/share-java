package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 点子文件
 */
@Pojo
public class DProjectIdeaFile extends DSuper {
	/**
	 * 点子id
	 */
	private long ideaId;
	/**
	 * 文件名
	 */
	private String name;
	/**
	 * 文件链接
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
	 * 获取文件名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置文件名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取文件链接
	 */
	public String getLink() {
		return link;
	}

	/**
	 * 设置文件链接
	 */
	public void setLink(String link) {
		this.link = link;
	}


}