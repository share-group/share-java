package com.share.test.http.server;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShareFilter implements Filter {
	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if ("/".equals(req.getRequestURI().trim())) {
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect("demo/fuck");
			return;
		}
		request.setAttribute("test", request.getInputStream());
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
	}
}