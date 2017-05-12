package com.share.test.http.server;

import java.io.PrintWriter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.share.core.annotation.Menu;

public class UserController {
	@RequestMapping(value = "/user/1", method = RequestMethod.GET)
	@Menu(menu = "user1", parentMenu = "user")
	public void user1(PrintWriter printWriter) {
	}

	@RequestMapping(value = "/user/2", method = RequestMethod.GET)
	@Menu(menu = "user2", parentMenu = "user")
	public void user2(PrintWriter printWriter) {
	}

	@RequestMapping(value = "/user/3", method = RequestMethod.GET)
	@Menu(menu = "user3", parentMenu = "user")
	public void user3(PrintWriter printWriter) {
	}

	@RequestMapping(value = "/user/4", method = RequestMethod.GET)
	@Menu(menu = "user4", parentMenu = "user")
	public void user4(PrintWriter printWriter) {
	}

	@RequestMapping(value = "/user/5", method = RequestMethod.GET)
	@Menu(menu = "user5", parentMenu = "xx")
	public void user5(PrintWriter printWriter) {
	}

	@RequestMapping(value = "/user/6", method = RequestMethod.GET)
	@Menu(menu = "user6", parentMenu = "xx")
	public void user6(PrintWriter printWriter) {
	}
}
