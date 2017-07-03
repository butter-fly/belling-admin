package com.belling.base.util;

import java.net.InetAddress;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

/**  
 * <pre>
 * Description	Http请求工具类
 * Copyright:	Copyright (c)2017
 * Company:		Suny
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年4月13日 下午5:18:06  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */

public final class RequestUtil {
	
	/**
	 * 私有化
	 */
	private RequestUtil() {
		
	}
	
	/**
	 * 获取用户请求真实IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getAddr(HttpServletRequest request) {
		if (request == null) {
			return "unknown";
		}
		String ip = request.getHeader("x-forwarded-for");
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("X-Real-IP");
		}

		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getRemoteAddr();
			if (("127.0.0.1".equals(ip)) || ("0:0:0:0:0:0:0:1".equals(ip))) {
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (Exception e) {
					e.printStackTrace();
				}
				ip = inet.getHostAddress();
			}
		}
		return ip;
	}
	
	/**
	 * 将request参数转化为Map
	 * 
	 * @param request HttpServletRequest
	 * @return Map<String, String>
	 */
	public static Map<String, String> getParameterMap(HttpServletRequest request) {
		Map<String, String> params = Maps.newHashMap();
		if (null != request) {
			Set<String> paramsKey = request.getParameterMap().keySet();
			for (String key : paramsKey) {
				String value = request.getParameter(key);
				if (!Strings.isNullOrEmpty(value)) {
					params.put(key, value.trim());
				}
			}
		}
		return params;
	}
	
	/**
	 * SpringMvc下获取request
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;

	}
}
