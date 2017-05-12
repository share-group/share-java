package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * app用户邮箱
 */
@Pojo
public class DTeambitionEmail extends DSuper {
	/**
	 * 用户手机
	 */
	private String phone;
	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 获取用户手机
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置用户手机
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}


}