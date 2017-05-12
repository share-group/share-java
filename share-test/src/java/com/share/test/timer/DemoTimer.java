package com.share.test.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DemoTimer {
	public Logger logger = LoggerFactory.getLogger(getClass());

	@Scheduled(cron = "* * * * * *")
	public void demoTimer() {
		//logger.info("demo 定时器，１ｓ一次");
	}
}
