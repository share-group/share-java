package com.share.core.session;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.share.core.interfaces.Session;
import com.share.core.redis.Redis;
import com.share.core.util.FileSystem;
import com.share.core.util.Secret;
import com.share.core.util.SerialUtil;
import com.share.core.util.StringUtil;
import com.share.core.util.SystemUtil;

/**
 * 分布式Session<br>
 * 依赖redis实现
 */
@Component
public class DistributedSession implements Session {
	/**
	 * logger
	 */
	private final static Logger logger = LogManager.getLogger(DistributedSession.class);
	/**
	 * 公共sessionKey
	 */
	private final static String distributedSessionGlobalCookieKey = Secret.sha(FileSystem.getProjectName() + SystemUtil.getSystemKey());
	/**
	 * redis key
	 */
	private final static String distributedSessionRedisKey = "session:";
	/**
	 * 一个空的byte数组
	 */
	private final static byte[] emptyBytes = new byte[0];
	/**
	 * 默认1小时session失效
	 */
	private int maxAge = 3600;
	/**
	 * session path
	 */
	private String sessionPath = "";
	/**
	 * session domain
	 */
	private String sessionDomain = "";
	/**
	 * redis
	 */
	@Autowired
	private Redis redis;
	/**
	 * 加一个本地缓存保护redis
	 */
	private LoadingCache<byte[], byte[]> localCache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).build(new CacheLoader<byte[], byte[]>() {
		public byte[] load(byte[] key) throws Exception {
			byte[] bytes = redis.STRINGS.get(key);
			if (bytes == null) {
				return emptyBytes;
			}
			return bytes;
		}
	});

	/**
	 * 私有构造函数
	 */
	private DistributedSession() {
	}

	/**
	 * 设置session失效时间
	 * @param maxAge 单位：秒
	 */
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	/**
	 * 设置session path
	 * @author ruan 
	 * @param sessionPath
	 */
	public void setSessionPath(String sessionPath) {
		this.sessionPath = sessionPath;
	}

	/**
	 * 设置 session domain
	 * @author ruan 
	 * @param sessionDomain
	 */
	public void setSessionDomain(String sessionDomain) {
		this.sessionDomain = sessionDomain;
	}

	/**
	 * 生成分布式sessionKey
	 * @author ruan 
	 */
	private String genDistributedSessionKey(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		// 从浏览器获取session
		String distributedSessionKey = "";
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			cookies = new Cookie[0];
		}
		for (Cookie cookie : cookies) {
			if (!distributedSessionGlobalCookieKey.equals(StringUtil.getString(cookie.getName()))) {
				continue;
			}
			distributedSessionKey = StringUtil.getString(cookie.getValue());
		}

		// 如果丢失session，重新生成
		if (distributedSessionKey.isEmpty()) {
			distributedSessionKey = Secret.md5(String.valueOf(System.nanoTime()));

			// 写入cookie
			Cookie cookie = new Cookie(distributedSessionGlobalCookieKey, distributedSessionKey);
			if (!sessionPath.isEmpty()) {
				cookie.setPath(sessionPath);
			}
			if (!sessionDomain.isEmpty()) {
				cookie.setDomain(sessionDomain);
			}
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
		}

		// 以redis为准，如果redis获取不了数据，就证明session丢失
		String key = distributedSessionRedisKey + distributedSessionKey;
		if (!redis.KEYS.exists(key)) {
			redis.KEYS.del(key + "_" + sessionKey);
			redis.STRINGS.setex(key, maxAge, "1");
		} else {
			redis.KEYS.expire(key, maxAge);
		}
		return key + "_" + sessionKey;
	}

	/**
	 * 向session写入数据
	 */
	public void addValue(HttpServletRequest request, HttpServletResponse response, String sessionKey, Object value) {
		String distributedSessionKey = genDistributedSessionKey(request, response, sessionKey);
		DistributedSessionData data = new DistributedSessionData();
		data.setData(SerialUtil.toBytes(value));
		redis.STRINGS.setex(distributedSessionKey.getBytes(), maxAge, SerialUtil.toBytes(data));
	}

	/**
	 * 从session删除数据
	 */
	public void removeValue(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		String distributedSessionKey = genDistributedSessionKey(request, response, sessionKey);
		redis.KEYS.del(distributedSessionKey, distributedSessionKey.substring(0, distributedSessionKey.indexOf("_")));
	}

	/**
	 * getInt
	 */
	public int getInt(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getInt(getT(request, response, sessionKey, Integer.class));
	}

	/**
	 * getLong
	 */
	public long getLong(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getLong(getT(request, response, sessionKey, Long.class));
	}

	/**
	 * getShort
	 */
	public short getShort(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getShort(getT(request, response, sessionKey, Short.class));
	}

	/**
	 * getByte
	 */
	public byte getByte(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getByte(getT(request, response, sessionKey, Byte.class));
	}

	/**
	 * getFloat
	 */
	public float getFloat(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getFloat(getT(request, response, sessionKey, Float.class));
	}

	/**
	 * getDouble	
	 */
	public double getDouble(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getDouble(getT(request, response, sessionKey, Double.class));
	}

	/**
	 * getString	
	 */
	public String getString(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getString(getT(request, response, sessionKey, String.class));
	}

	/**
	 * getBytes	
	 */
	public byte[] getBytes(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		String distributedSessionKey = genDistributedSessionKey(request, response, sessionKey);
		return get(distributedSessionKey.getBytes());
	}

	/**
	 * getObject
	 */
	public Object getObject(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return getT(request, response, sessionKey, DistributedSessionData.class);
	}

	/**
	 * getT
	 */
	public <T> T getT(HttpServletRequest request, HttpServletResponse response, String sessionKey, Class<T> t) {
		String distributedSessionKey = genDistributedSessionKey(request, response, sessionKey);
		byte[] bytes = get(distributedSessionKey.getBytes());
		if (bytes.length <= 0) {
			return null;
		}
		DistributedSessionData distributedSessionData = SerialUtil.fromBytes(bytes, DistributedSessionData.class);
		if (distributedSessionData == null) {
			return null;
		}
		return (T) SerialUtil.fromBytes(distributedSessionData.getData(), t);
	}

	/**
	 * 从guava获取数据
	 * @author ruan 
	 * @param key	
	 */
	private byte[] get(byte[] key) {
		try {
			return localCache.get(key);
		} catch (Exception e) {
			logger.error("", e);
		}
		return emptyBytes;
	}
}