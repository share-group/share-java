package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 企业token
 */
@Pojo
public class DCompanyToken extends DSuper {
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * token
	 */
	private String token;
	/**
	 * 过期时间
	 */
	private int expire;

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
	 * 获取token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 设置token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 获取过期时间
	 */
	public int getExpire() {
		return expire;
	}

	/**
	 * 设置过期时间
	 */
	public void setExpire(int expire) {
		this.expire = expire;
	}


}