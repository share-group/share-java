package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * cpc项目idea表
 */
@Pojo
public class DCpcIdea extends DSuper {
	/**
	 * cpc项目id
	 */
	private long cpcId;
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 图片地址
	 */
	private String image;
	/**
	 * 分享链接
	 */
	private String shareLink;
	/**
	 * 获得报酬
	 */
	private double reward;

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
	 * 获取图片地址
	 */
	public String getImage() {
		return image;
	}

	/**
	 * 设置图片地址
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * 获取分享链接
	 */
	public String getShareLink() {
		return shareLink;
	}

	/**
	 * 设置分享链接
	 */
	public void setShareLink(String shareLink) {
		this.shareLink = shareLink;
	}

	/**
	 * 获取获得报酬
	 */
	public double getReward() {
		return reward;
	}

	/**
	 * 设置获得报酬
	 */
	public void setReward(double reward) {
		this.reward = reward;
	}


}