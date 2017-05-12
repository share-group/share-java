package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 案列展示
 */
@Pojo
public class DCaseDemoShow extends DSuper {
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 图片列表
	 */
	private String imageListData;
	/**
	 * 是否显示
	 */
	private int isShow;

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
	 * 获取图片列表
	 */
	public String getImageListData() {
		return imageListData;
	}

	/**
	 * 设置图片列表
	 */
	public void setImageListData(String imageListData) {
		this.imageListData = imageListData;
	}

	/**
	 * 获取是否显示
	 */
	public int getIsShow() {
		return isShow;
	}

	/**
	 * 设置是否显示
	 */
	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}


}