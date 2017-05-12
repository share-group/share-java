package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 企业执行方作品
 */
@Pojo
public class DExecutiveCompanyOpus extends DSuper {
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 封面
	 */
	private String cover;
	/**
	 * 套餐介绍
	 */
	private String description;
	/**
	 * 图片列表
	 */
	private String imageList;

	/**
	 * 获取企业id
	 */
	public long getCompanyId() {
		return companyId;
	}

	/**
	 * 设置企业id
	 */
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	/**
	 * 获取标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取封面
	 */
	public String getCover() {
		return cover;
	}

	/**
	 * 设置封面
	 */
	public void setCover(String cover) {
		this.cover = cover;
	}

	/**
	 * 获取套餐介绍
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置套餐介绍
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取图片列表
	 */
	public String getImageList() {
		return imageList;
	}

	/**
	 * 设置图片列表
	 */
	public void setImageList(String imageList) {
		this.imageList = imageList;
	}


}