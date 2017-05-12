package com.share.core.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.notnoop.apns.APNS;
import com.share.core.util.FileSystem;

/**
 * 苹果官方推送<br>
 * https://github.com/notnoop/java-apns
 */
public class ApnsService {
	private final static Logger logger = LoggerFactory.getLogger(ApnsService.class);

	/**
	 * 私有化构造函数，加载p12证书
	 */
	private ApnsService() {
		// 密码：cc.gatherup.GatherUp
		com.notnoop.apns.ApnsService service = APNS.newService().withCert(ClassLoader.getSystemResourceAsStream("apns/" + FileSystem.getPropertyString("system.env") + ".p21"), "MyCertPassword").withSandboxDestination().build();
		logger.warn(service.toString());
	}
}