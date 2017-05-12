package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 吐槽表
 */
@Pojo
public class DFlow extends DSuper {
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 吐槽人用户id
	 */
	private long userId;
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
	 * 获取吐槽人用户id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置吐槽人用户id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


}