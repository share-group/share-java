package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 用户实名认证信息表
 */
@Pojo
public class DUserProve extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 认证信息
	 */
	private String proveInfo;
	/**
	 * 身份证图片
	 */
	private String identityCard;
	/**
	 * 其他证件图片
	 */
	private String otherCard;
	/**
	 * 是否实名认证
	 */
	private int isProve;
	private int checkTime;

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
	 * 获取认证信息
	 */
	public String getProveInfo() {
		return proveInfo;
	}

	/**
	 * 设置认证信息
	 */
	public void setProveInfo(String proveInfo) {
		this.proveInfo = proveInfo;
	}

	/**
	 * 获取身份证图片
	 */
	public String getIdentityCard() {
		return identityCard;
	}

	/**
	 * 设置身份证图片
	 */
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	/**
	 * 获取其他证件图片
	 */
	public String getOtherCard() {
		return otherCard;
	}

	/**
	 * 设置其他证件图片
	 */
	public void setOtherCard(String otherCard) {
		this.otherCard = otherCard;
	}

	/**
	 * 获取是否实名认证
	 */
	public int getIsProve() {
		return isProve;
	}

	/**
	 * 设置是否实名认证
	 */
	public void setIsProve(int isProve) {
		this.isProve = isProve;
	}

	public int getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(int checkTime) {
		this.checkTime = checkTime;
	}


}