/**
 * 
 */
/**
 * @author jlp
 *
 */
package com.couldtec.webserver.apps.config.exception;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 全局异常捕获
 * @author jlp
 *
 */
@Deprecated
public class GlobalExceptionResolver extends SimpleMappingExceptionResolver  {
	private final String[] needHeadInfo=new String[]{};
	/** log */
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);
	GlobalExceptionResolver(){
		super.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		return null;
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
	
	/*private String parseHeadStr(HttpServletResponse resp){
		Map<String, String> map = new HashMap<String, String>();
	    Collection<String> headerNames = resp.get;
	    Iterator itr=headerNames.iterator();
	    while (itr.hasNext()) {
	        String key = (String) itr.next();
	        String value = resp.getHeader(key);
	        map.put(key, value);
	    }
	    return map.toString();
	}
	*/
	
}