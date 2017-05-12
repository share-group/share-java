package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 专案报告
 */
@Pojo
public class DCaseReport extends DSuper {
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 项目名称
	 */
	private String caseName;
	/**
	 * 下载地址
	 */
	private String url;
	/**
	 * 状态(1-进行中 0-已结束)
	 */
	private int status;

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
	 * 获取项目名称
	 */
	public String getCaseName() {
		return caseName;
	}

	/**
	 * 设置项目名称
	 */
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	/**
	 * 获取下载地址
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置下载地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取状态(1-进行中 0-已结束)
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 设置状态(1-进行中 0-已结束)
	 */
	public void setStatus(int status) {
		this.status = status;
	}


}