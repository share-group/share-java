package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 企业收藏创意表
 */
@Pojo
public class DCollectIdea extends DSuper {
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 创意id
	 */
	private long ideaId;

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
	 * 获取创意id
	 */
	public long getIdeaId() {
		return ideaId;
	}

	/**
	 * 设置创意id
	 */
	public void setIdeaId(long ideaId) {
		this.ideaId = ideaId;
	}


}