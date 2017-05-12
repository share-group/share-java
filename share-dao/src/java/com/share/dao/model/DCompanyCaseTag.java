package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 企业所有专案的标签
 */
@Pojo
public class DCompanyCaseTag extends DSuper {
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 标签id(自定义的+100000000)
	 */
	private long typeId;

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
	 * 获取标签id(自定义的+100000000)
	 */
	public long getTypeId() {
		return typeId;
	}

	/**
	 * 设置标签id(自定义的+100000000)
	 */
	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}


}