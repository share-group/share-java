package com.share.test.nsq;

import com.share.core.interfaces.NsqMessageHandler;

public class DemoNsqMessageHandler implements NsqMessageHandler {
	//@NsqCallback(topic = "a", channel = "a", onlyChannel = false)
	public boolean handle(byte[] message) {
		// 先getMessage()
		// 然后做你想要做的事情
		// 最后finished()
		System.err.println("xxx收到消息：  " + new String(message));
		return true;
	}
}