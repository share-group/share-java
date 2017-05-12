package com.share.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.share.protocol.ReqUserReg;
import com.share.protocol.ResUserReg;

@Controller("demo模块")
public class DemoController {
	/**
	 * demo xcxcc 注册
	 * @param reqUserReg
	 * @return
	 */
	@PostMapping("/demo/reg")
	public ResUserReg userReg(ReqUserReg reqUserReg) {
		ResUserReg resUserReg = new ResUserReg();
		resUserReg.setUserId(12);
		return resUserReg;
	}

	/**
	 * demo登录
	 */
	@PostMapping("/demo/login")
	public ResUserReg userLogin(ReqUserReg reqUserReg) {
		ResUserReg resUserReg = new ResUserReg();
		resUserReg.setUserId(12);
		return resUserReg;
	}

	/**
	 * demo列表
	 */
	@GetMapping("/demo/list")
	public ResUserReg userList(ReqUserReg reqUserReg) {
		ResUserReg resUserReg = new ResUserReg();
		resUserReg.setUserId(12);
		return resUserReg;
	}
}