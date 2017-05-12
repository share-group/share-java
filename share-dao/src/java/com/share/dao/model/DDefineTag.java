package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 自定义标签表
 */
@Pojo
public class DDefineTag extends DSuper {
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 企业使用数量
	 */
	private int companyNum;
	/**
	 * 用户使用数量
	 */
	private int userNum;

	/**
	 * 获取名字
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名字
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取企业使用数量
	 */
	public int getCompanyNum() {
		return companyNum;
	}

	/**
	 * 设置企业使用数量
	 */
	public void setCompanyNum(int companyNum) {
		this.companyNum = companyNum;
	}

	/**
	 * 获取用户使用数量
	 */
	public int getUserNum() {
		return userNum;
	}

	/**
	 * 设置用户使用数量
	 */
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}


}