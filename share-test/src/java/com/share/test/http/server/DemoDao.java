package com.share.test.http.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.share.test.db.AdminDbService;

@Service
public class DemoDao {
	@Autowired
	private AdminDbService jdbc;

	public void aaaaaaaaaaa() {
		System.err.println(jdbc);
	}
}
