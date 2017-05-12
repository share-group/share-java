package com.share.protocol;

import java.util.List;
import java.util.Set;

import com.share.core.annotation.Require;

public class ReqUserReg {
	/**
	 * 手机号
	 */
	private long mobile;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 登录密码
	 */
	@Require(require = false)
	private String password;
	/**
	 * demo列表
	 */
	private List<String> list;
	/**
	 * demo集合
	 */
	private Set<Integer> set;
	/**
	 * 数组
	 */
	private long[] array;

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getNickname() {
		return nickName;
	}

	public void setNickname(String nickname) {
		this.nickName = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Set<Integer> getSet() {
		return set;
	}

	public void setSet(Set<Integer> set) {
		this.set = set;
	}

	public long[] getArray() {
		return array;
	}

	public void setArray(long[] array) {
		this.array = array;
	}

}
