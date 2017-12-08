package com.share.soa.thrift.client;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.share.core.client.HttpClient;

public final class ThriftHttpClient implements InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(ThriftHttpClient.class);
	private Map<String, ThreadLocal<Object>> clientMap = Maps.newHashMap();
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	private Properties properties = new Properties();
	@Autowired
	private HttpClient httpClient;

	@SuppressWarnings("unchecked")
	public <T> T get(String className) {
		lock.readLock().lock();
		try {
			ThreadLocal<Object> local = clientMap.get(className);
			if (local != null) {
				Object obj = local.get();
				if (obj != null) {
					return (T) obj;
				}
			}
		} finally {
			lock.readLock().unlock();
		}

		String url = properties.getProperty(className);
		if (url == null) {
			throw new IllegalStateException("cannot find url for:" + className);
		}
		lock.writeLock().lock();
		try {
			ThreadLocal<Object> local = clientMap.get(className);
			if (local == null) {
				local = new ThreadLocal<Object>();
				clientMap.put(className, local);
			}
			Object obj = local.get();
			if (obj == null) {
				try {
					THttpClient transport = new THttpClient(url, httpClient.getClient());
					TProtocol protocol = new TCompactProtocol(transport);
					obj = Class.forName(className + "$Client").getDeclaredConstructor(TProtocol.class).newInstance(protocol);
					local.set(obj);
				} catch (Exception e) {
					logger.error("", e);
				}
			}
			return (T) obj;
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("thrift.conf"));
			for (Entry<Object, Object> e : properties.entrySet()) {
				logger.info("service '{}' soa address is {}", e.getKey(), e.getValue());
			}
		} catch (Exception e) {
			logger.error("", e);
			logger.error("can not find thrift.properties!");
			System.exit(0);
		}
	}
}
