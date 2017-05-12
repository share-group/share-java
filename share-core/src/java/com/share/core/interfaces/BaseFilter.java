package com.share.core.interfaces;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.share.core.exception.ClassInterfacesException;
import com.share.core.session.LocalSession;
import com.share.core.util.HttpServerUtil;
import com.share.core.util.SpringUtil;
import com.share.core.util.StringUtil;
import com.share.core.util.Time;

/**
 * 所有filter类都继承这个类
 * @author ruan
 */
public abstract class BaseFilter implements Filter {
	/**
	 * logger
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 当前皮肤名
	 */
	private String skin;
	/**
	 * 本地session
	 */
	protected static Session session;
	/**
	 * 采用的session类名
	 */
	protected static String sessionClassName = "";
	/**
	 * session path
	 */
	protected static String sessionPath = "";
	/**
	 * session domain
	 */
	protected static String sessionDomain = "";
	/**
	 * 采用的session类
	 */
	protected static Class<?> sessionClass;
	/**
	 * session过期时间(单位：秒)
	 */
	protected static int sessionExpire = 3600;

	public void init(FilterConfig config) throws ServletException {
		try {
			// 初始化session类
			sessionClassName = StringUtil.getString(config.getInitParameter("sessionClass"));
			if (!sessionClassName.isEmpty()) {
				sessionClass = Class.forName(sessionClassName);
				Class<?>[] interfacesClass = sessionClass.getInterfaces();
				if (interfacesClass.length != 1) {
					throw new ClassInterfacesException("class " + sessionClass.getName() + " must only implements " + Session.class.getName());
				}
				if (!Session.class.equals(interfacesClass[0])) {
					throw new ClassInterfacesException("class " + sessionClass.getName() + " must only implements " + Session.class.getName());
				}
			} else {
				sessionClass = LocalSession.class;
			}

			// 初始化session失效时间
			int sessionExpireConfig = StringUtil.getInt(config.getInitParameter("sessionExpire"));
			if (sessionExpireConfig > 0) {
				sessionExpire = sessionExpireConfig;
			}

			// 初始化 session path
			sessionPath = StringUtil.getString(config.getInitParameter("sessionPath"));

			// 初始化 session domain
			sessionDomain = StringUtil.getString(config.getInitParameter("sessionDomain"));
		} catch (Exception e) {
			logger.error("", e);
			System.exit(0);
		} finally {
			logger.info("session strategy: {}, expire: {}", sessionClass.getName(), Time.showTime(TimeUnit.SECONDS.toNanos(sessionExpire)));
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;

		// 获取皮肤名
		if (skin == null) {
			//这样是为了防止并发
			synchronized (this) {
				skin = SpringUtil.getBean(JSPResourceViewResolver.class).getSkin();
			}
		}

		// 初始化sesion策略
		if (session == null) {
			//这样是为了防止并发
			synchronized (this) {
				session = (Session) SpringUtil.getBean(sessionClass);
				session.setMaxAge(sessionExpire);
				session.setSessionPath(sessionPath);
				session.setSessionDomain(sessionDomain);
			}
		}

		request.setAttribute("skin", getSkin());
		boolean b = doFilter(req, res, chain);
		if (!b) {
			return;
		}
		chain.doFilter(request, response);
	}

	protected abstract boolean doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

	/**
	 * 获取皮肤名
	 */
	public String getSkin() {
		return skin;
	}
}