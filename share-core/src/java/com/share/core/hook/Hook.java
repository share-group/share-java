package com.share.core.hook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.share.core.interfaces.AbstractTask;

/**
 * 钩子<br>
 * 如果需要在JDK结束之前要执行某些任务的，可以使用这个类
 * @author ruan
 */
@Component
public class Hook {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(Hook.class);
	/**
	 * runtime
	 */
	private final static Runtime runtime = Runtime.getRuntime();

	/**
	 * 只能用spring实例化
	 */
	private Hook() {
	}

	/**
	 * 添加钩子
	 * @param task runnable任务
	 */
	public void addShutdownHook(AbstractTask task) {
		runtime.addShutdownHook(new Thread(task));
		logger.warn("add hook task {}", task.toString());
	}
}