package com.share.core.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.share.core.interfaces.Session;
import com.share.core.util.StringUtil;

/**
 * 本地Session
 */
@Component
public class LocalSession implements Session {
	/**
	 * 私有构造函数
	 */
	private LocalSession() {
	}

	public void setMaxAge(int maxAge) {
	}

	public void setSessionPath(String sessionPath) {
	}

	public void setSessionDomain(String sessionDomain) {
	}

	/**
	 * 向session写入数据
	 * @param request.getSession()
	 * @param sessionKey
	 * @param value
	 */
	public void addValue(HttpServletRequest request, HttpServletResponse response, String sessionKey, Object value) {
		request.getSession().setAttribute(sessionKey, value);
	}

	/**
	 * 从session删除数据
	 * @param request.getSession()
	 * @param sessionKey
	 */
	public void removeValue(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		request.getSession().removeAttribute(sessionKey);
	}

	/**
	 * getInt
	 */
	public int getInt(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getInt(request.getSession().getAttribute(sessionKey));
	}

	/**
	 * getLong
	 */
	public long getLong(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getLong(request.getSession().getAttribute(sessionKey));
	}

	/**
	 * getShort
	 */
	public short getShort(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getShort(request.getSession().getAttribute(sessionKey));
	}

	/**
	 * getByte
	 */
	public byte getByte(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getByte(request.getSession().getAttribute(sessionKey));
	}

	/**
	 * getFloat
	 */
	public float getFloat(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getFloat(request.getSession().getAttribute(sessionKey));
	}

	/**
	 * getDouble	
	 */
	public double getDouble(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getDouble(request.getSession().getAttribute(sessionKey));
	}

	/**
	 * getString	
	 */
	public String getString(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getString(request.getSession().getAttribute(sessionKey));
	}

	/**
	 * getBytes	
	 */
	public byte[] getBytes(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return (byte[]) request.getSession().getAttribute(sessionKey);
	}

	/**
	 * getObject
	 */
	public Object getObject(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return request.getSession().getAttribute(sessionKey);
	}

	/**
	 * getT
	 */
	@SuppressWarnings("unchecked")
	public <T> T getT(HttpServletRequest request, HttpServletResponse response, String sessionKey, Class<T> t) {
		return (T) request.getSession().getAttribute(sessionKey);
	}
}