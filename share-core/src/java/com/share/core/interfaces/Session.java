package com.share.core.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Session
 * @author ruan
 */
public interface Session {
	/**
	 * 设置session失效时间
	 * @param maxAge 单位：秒
	 */
	public void setMaxAge(int maxAge);

	/**
	 * 设置session path
	 * @author ruan 
	 * @param sessionPath
	 */
	public void setSessionPath(String sessionPath);

	/**
	 * 设置 session domain
	 * @author ruan 
	 * @param sessionDomain
	 */
	public void setSessionDomain(String sessionDomain);

	/**
	 * 向session写入数据
	 */
	public void addValue(HttpServletRequest request, HttpServletResponse response, String sessionKey, Object value);

	/**
	 * 从session删除数据
	 */
	public void removeValue(HttpServletRequest request, HttpServletResponse response, String sessionKey);

	/**
	 * getInt
	 */
	public int getInt(HttpServletRequest request, HttpServletResponse response, String sessionKey);

	/**
	 * getLong
	 */
	public long getLong(HttpServletRequest request, HttpServletResponse response, String sessionKey);

	/**
	 * getShort
	 */
	public short getShort(HttpServletRequest request, HttpServletResponse response, String sessionKey);

	/**
	 * getByte
	 */
	public byte getByte(HttpServletRequest request, HttpServletResponse response, String sessionKey);

	/**
	 * getFloat
	 */
	public float getFloat(HttpServletRequest request, HttpServletResponse response, String sessionKey);

	/**
	 * getDouble	
	 */
	public double getDouble(HttpServletRequest request, HttpServletResponse response, String sessionKey);

	/**
	 * getString	
	 */
	public String getString(HttpServletRequest request, HttpServletResponse response, String sessionKey);

	/**
	 * getBytes	
	 */
	public byte[] getBytes(HttpServletRequest request, HttpServletResponse response, String sessionKey);

	/**
	 * getObject
	 */
	public Object getObject(HttpServletRequest request, HttpServletResponse response, String sessionKey);

	/**
	 * getT
	 */
	public <T> T getT(HttpServletRequest request, HttpServletResponse response, String sessionKey, Class<T> t);
}