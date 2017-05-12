package com.share.test.http.server;

import com.share.core.server.HttpServer;
import com.share.core.util.FileSystem;

public class HttpDemo {
	public static void main(String[] a) throws Exception {
		HttpServer s = new HttpServer(FileSystem.getPropertyInt("http.port"));
		s.start();
	}
}