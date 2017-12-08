package com.share.test.socket;

import com.share.core.server.SocketServer;
import com.share.core.util.FileSystem;
import com.share.test.socket.initializer.PbSocketServerInitializer;

public class SocketDemo {

	public static void main(String[] args) throws Exception {
		SocketServer s = new SocketServer(FileSystem.getPropertyInt("socket.port"), new PbSocketServerInitializer(50));
		s.start();
	}
}