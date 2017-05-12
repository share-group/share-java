package com.share.core.interfaces;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.share.core.util.SystemUtil;

/**
 * 系统线程池(如果不设置默认是cpu core x 2)
 * @author ruan
 */
public abstract class ThreadPool {
	/**
	 * logger
	 */
	protected Logger logger;
	/**
	 * 线程池
	 */
	protected ExecutorService executor;
	/**
	 * 线程池大小
	 */
	private int poolSize = SystemUtil.getCore();
	/**
	 * 线程池名字
	 */
	private String name = getClass().getName();

	/**
	 * 设置线程池大小
	 * @param poolSize
	 */
	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	/**
	 * 改变线程池的名字
	 * @param name
	 */
	public void changeName(String name) {
		if (name == null || name.isEmpty()) {
			return;
		}
		this.name = name;
	}

	/**
	 * 初始化
	 */
	public void init() {
		if (getClass().equals(ThreadPool.class)) {
			throw new RuntimeException("please define a class extends " + ThreadPool.class);
		}
		executor = Executors.newFixedThreadPool(poolSize);
		logger = LoggerFactory.getLogger(name);
		logger.info("inited threadPool, size {}", poolSize);
	}

	/**
	 * 关闭方法
	 */
	public void close() {
		executor.shutdown();
		logger.info("threadPool is shutdown");
	}

	/**
	 * 执行一个任务无超时时间
	 * @param task 任务
	 */
	public void execute(Runnable task) {
		try {
			executor.execute(task);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * 执行一个任务，并设置过期时间
	 * @param task 任务
	 * @param timeout 过期时间(如果小于等于0，相当于无超时时间)
	 * @param timeUnit 时间单位
	 * @return 成功返回任务返回的对象，失败返回null
	 */
	public void execute(Runnable task, long timeout, TimeUnit timeUnit) {
		Future<?> future = null;
		try {
			future = executor.submit(task, Object.class);
			if (timeout > 0) {
				future.get(timeout, timeUnit);
			} else {
				execute(task);
			}
		} catch (Exception e) {
			future.cancel(true);
			logger.error("", e);
		}
	}
}