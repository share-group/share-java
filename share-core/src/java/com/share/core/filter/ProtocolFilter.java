package com.share.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ProtocolFilter implements Filter {
	public final static String protocolFilterKey = ProtocolFilter.class.getName();
	public final static String url = "url";

	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setAttribute(protocolFilterKey, request.getInputStream());
		request.setAttribute(url, ((HttpServletRequest) request).getRequestURI());
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
	}
}