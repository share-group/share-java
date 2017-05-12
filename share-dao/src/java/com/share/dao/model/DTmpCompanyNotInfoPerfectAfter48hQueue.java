package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 企业资料48h没完善临时表
 */
@Pojo
public class DTmpCompanyNotInfoPerfectAfter48hQueue extends DSuper {
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 过期时间
	 */
	private int endTime;

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
	 * 获取过期时间
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * 设置过期时间
	 */
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}


}