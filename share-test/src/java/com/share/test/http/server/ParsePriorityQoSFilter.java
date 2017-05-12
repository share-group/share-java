package com.share.test.http.server;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.servlets.QoSFilter;

public class ParsePriorityQoSFilter extends QoSFilter {
	 protected int getPriority(ServletRequest request)
     {
		 HttpServletRequest req = (HttpServletRequest) request;
		 if("/demo/test2".equals(req.getRequestURI())){
			 return 1;
		 }
         return 4;
     }
}
