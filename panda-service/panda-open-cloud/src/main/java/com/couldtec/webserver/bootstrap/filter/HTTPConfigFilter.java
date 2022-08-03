package com.couldtec.webserver.bootstrap.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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

/**
 * Servlet Filter implementation class HTTPConfigFilter
 */
//@WebFilter(urlPatterns ="/*",asyncSupported =true)
public class HTTPConfigFilter implements Filter {
	public static final Logger logger = LoggerFactory.getLogger(HTTPConfigFilter.class);
	/**
	 * Default constructor.
	 */
	public HTTPConfigFilter() {
		
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		logger.warn("接收到请求"
				+ ""
				+ "！ThreadId[{}],requ_id[{}],headInfo[{}]");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		if(httpServletRequest.getMethod().equals("OPTIONS")){
			httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
			httpServletResponse.setHeader("Access-Control-Allow-Methods",
					"POST, GET, PUT, OPTIONS, DELETE");
			httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
			httpServletResponse.setHeader("Access-Control-Allow-Headers",
					"Origin, X-Requested-With, Content-Type, Accept,Callback-Url,Action-Execution-Id");
			httpServletResponse.setStatus(204);
			return;
		}
		httpServletRequest.setAttribute("requestId", System.currentTimeMillis());
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Methods",
				"POST, GET, PUT, OPTIONS, DELETE");
		
		httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
		httpServletResponse.setHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Content-Type, Accept,Callback-Url,Action-Execution-Id");
		logger.warn("有请求！ThreadId[{}],requ_id[{}],headInfo[{}]",new Object[]{
				Thread.currentThread().getId(),request.getParameter("requ_id"),
				parseHeadStr(httpServletRequest)
		});
		chain.doFilter(httpServletRequest, httpServletResponse);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

	
	private String parseHeadStr(HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
	    Enumeration<String> headerNames = request.getHeaderNames();
	    while (headerNames.hasMoreElements()) {
	        String key = (String) headerNames.nextElement();
	        String value = request.getHeader(key);
	        map.put(key, value);
	    }
	    return map.toString();
	}
}
