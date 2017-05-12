package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * G点支出列表
 */
@Pojo
public class DCompanyExpense extends DSuper {
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 费用
	 */
	private int points;

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
	 * 获取专案id
	 */
	public long getCaseId() {
		return caseId;
	}

	/**
	 * 设置专案id
	 */
	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	/**
	 * 获取费用
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * 设置费用
	 */
	public void setPoints(int points) {
		this.points = points;
	}


}