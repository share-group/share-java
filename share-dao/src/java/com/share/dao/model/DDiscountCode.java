package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 优惠码
 */
@Pojo
public class DDiscountCode extends DSuper {
	/**
	 * 优惠码
	 */
	private String code;
	/**
	 * 折扣规则
	 */
	private String rule;
	/**
	 * 是否已经使用(1-是 0-否)
	 */
	private int status;
	/**
	 * 使用时间
	 */
	private int useTime;
	/**
	 * 使用这个优惠码的用户id
	 */
	private long userId;
	/**
	 * 使用这个优惠码的企业id
	 */
	private long companyId;
	/**
	 * 是否已发放(1-是 0-否)
	 */
	private int isSend;

	/**
	 * 获取优惠码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置优惠码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取折扣规则
	 */
	public String getRule() {
		return rule;
	}

	/**
	 * 设置折扣规则
	 */
	public void setRule(String rule) {
		this.rule = rule;
	}

	/**
	 * 获取是否已经使用(1-是 0-否)
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 设置是否已经使用(1-是 0-否)
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 获取使用时间
	 */
	public int getUseTime() {
		return useTime;
	}

	/**
	 * 设置使用时间
	 */
	public void setUseTime(int useTime) {
		this.useTime = useTime;
	}

	/**
	 * 获取使用这个优惠码的用户id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置使用这个优惠码的用户id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 获取使用这个优惠码的企业id
	 */
	public long getCompanyId() {
		return companyId;
	}

	/**
	 * 设置使用这个优惠码的企业id
	 */
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	/**
	 * 获取是否已发放(1-是 0-否)
	 */
	public int getIsSend() {
		return isSend;
	}

	/**
	 * 设置是否已发放(1-是 0-否)
	 */
	public void setIsSend(int isSend) {
		this.isSend = isSend;
	}


}