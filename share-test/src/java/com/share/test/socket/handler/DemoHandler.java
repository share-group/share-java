package com.share.test.socket.handler;

import org.springframework.stereotype.Component;

import com.share.core.annotation.SocketHandler;
import com.share.core.interfaces.BaseHandler;
import com.share.test.protocol.ReqDemo;
import com.share.test.protocol.ResDemo;

@Component
public class DemoHandler extends BaseHandler {

	@SocketHandler(clazz = ReqDemo.class, interval=5000)
	public ResDemo demo1(ReqDemo reqDemo) {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("this is a demo");
		ResDemo resDemo = new ResDemo();
		resDemo.setEmail("fdfdf");
		return resDemo;
	}
}
