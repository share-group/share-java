package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 专案案例展示
 */
@Pojo
public class DCaseShow extends DSuper {
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 文字
	 */
	private String content;

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
	 * 获取文字
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置文字
	 */
	public void setContent(String content) {
		this.content = content;
	}


}