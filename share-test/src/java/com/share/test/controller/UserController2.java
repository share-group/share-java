package com.share.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.share.protocol.ReqUserReg;
import com.share.protocol.ResUserReg;

@Controller("哈哈哈模块")
public class UserController2 {
	/**
	 * 用户注册
	 */
	@PostMapping("/user2/reg")
	public ResUserReg userReg(ReqUserReg reqUserReg) {
		ResUserReg resUserReg = new ResUserReg();
		resUserReg.setUserId(12);
		return resUserReg;
	}

	/**
	 * 用户登录
	 */
	@PostMapping("/user2/login")
	public ResUserReg userLogin(ReqUserReg reqUserReg) {
		ResUserReg resUserReg = new ResUserReg();
		resUserReg.setUserId(12);
		return resUserReg;
	}

	/**
	 * 用户列表
	 */
	@GetMapping("/user2/list")
	public ResUserReg userList(ReqUserReg reqUserReg) {
		ResUserReg resUserReg = new ResUserReg();
		resUserReg.setUserId(12);
		return resUserReg;
	}
}