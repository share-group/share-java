package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 企业回复表
 */
@Pojo
public class DIdeaCompanyComment extends DSuper {
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 创意id
	 */
	private long ideaId;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 动作(1-我回复了xx 2-xx回复了我)
	 */
	private byte action;

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

	/**
	 * 获取内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取动作(1-我回复了xx 2-xx回复了我)
	 */
	public byte getAction() {
		return action;
	}

	/**
	 * 设置动作(1-我回复了xx 2-xx回复了我)
	 */
	public void setAction(byte action) {
		this.action = action;
	}


}